package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.ConfirmEmail;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.*;
import kg.neobis.diabetes.models.security.AuthenticationResponse;
import kg.neobis.diabetes.repositories.ConfirmEmailRepository;
import kg.neobis.diabetes.repositories.UserRepository;
import kg.neobis.diabetes.services.impl.EmailSenderService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class RegistrationService {

    private final MyUserServiceImpl userService;
    private final EmailSenderService emailSenderService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserRepository userRepository;


    @Autowired
    private ConfirmEmailRepository repository;


    @Autowired
    RegistrationService(UserRepository userRepository, JwtUtil jwtUtil, MyUserServiceImpl userService, EmailSenderService emailSenderService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtUtil;
        this.userRepository = userRepository;

    }


    /**
     * At least 6 chars
     * Contains at least one digit
     * Contains at least one lower alpha char and one upper alpha char
     * Contains at least one char within a set of special chars (!@#%$^ etc.)
     * Does not contain space, tab, etc.
     */
    public static boolean isPasswordValid(String password) {
        String pattern = "^.*(?=.{6,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$";
        return password.matches(pattern);
    }

    private static boolean isEmailValid(String email) {
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return email.matches(pattern);
    }

    private boolean isEmailExist(String email){
        User user = userService.getByEmail(email);
        return user != null;
    }


    public void doStep1(RegistrationModel registrationModel) throws WrongDataException {
        if(!isEmailValid(registrationModel.getEmail()))
            throw new WrongDataException("email is not valid");
        else if(isEmailExist(registrationModel.getEmail()))
            throw new WrongDataException("email is already exist");
        else if(!isPasswordValid(registrationModel.getPassword()))
            throw new WrongDataException("password is not valid");
        else if(!registrationModel.getPassword().equals(registrationModel.getConfirmPassword()))
            throw new WrongDataException("confirm password is wrong");


        String confirmCode = getRandomFourDigitNumber();
        emailSenderService.sendEmailToConfirmEmail(registrationModel.getEmail(), confirmCode);

        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setEmail(registrationModel.getEmail());
        confirmEmail.setPassword(registrationModel.getPassword());
        confirmEmail.setCode(confirmCode);
        confirmEmail.setCreatedDate(new java.util.Date());
        repository.save(confirmEmail);


    }

    public String getRandomFourDigitNumber(){// на сколько это правильно? При вызове метода каждый раз создается новый объект
        Random random = new Random();
        return Integer.toString (random.nextInt(9000) + 1000); //1000 - 9999
    }

    public ResponseEntity doStep2(ModelToConfirmEmail model) throws Exception {
        ConfirmEmail confirmEmail = repository.findByEmail(model.getEmail());
        if(confirmEmail == null)
            throw  new RecordNotFoundException("на почту " + model.getEmail() + " код не был отправлен");

        else if(!confirmEmail.getCode().equals(model.getConfirmCode()))
            throw new WrongDataException("не правильный код");

        User user = new User();
        user.setEmail(confirmEmail.getEmail());
        user.setPassword(confirmEmail.getPassword());
        userRepository.save(user);


        return ResponseEntity.ok(authenticate(user));

    }

    public void doStep3(QuestioningModel model) {

        User user = userService.getCurrentUser();
        user.setName(model.getName());
        user.setBirthDate(model.getBirthDate());
        user.setGender(Gender.values()[model.getGenderId()]);
        user.setDiabetesStatus(DiabetesStatus.values()[model.getDiabetesStatusId()]);
        user.setWeight(model.getWeight());
        user.setHeight(model.getHeight());
        userRepository.save(user);
    }


    public AuthenticationResponse authenticate( User user) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(user.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
