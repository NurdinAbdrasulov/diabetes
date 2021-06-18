package kg.neobis.diabetes.services;

import javassist.NotFoundException;
import kg.neobis.diabetes.entity.RestorePassword;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.exception.AlreadyExistException;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.ModelToChangePassword;
import kg.neobis.diabetes.models.ModelToConfirmEmail;
import kg.neobis.diabetes.models.RestorePasswordModel;
import kg.neobis.diabetes.models.security.AuthenticationResponse;
import kg.neobis.diabetes.repositories.RestorePasswordRepository;
import kg.neobis.diabetes.services.impl.EmailSenderService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RestorePasswordService {

    private RestorePasswordRepository repository;
    private MyUserServiceImpl userService;
    private RegistrationService registrationService;
    private EmailSenderService emailSenderService;

    @Autowired
    RestorePasswordService(RestorePasswordRepository repository, MyUserServiceImpl userService, RegistrationService registrationService, EmailSenderService emailSenderService){
        this.repository = repository;
        this.userService = userService;
        this.registrationService = registrationService;
        this.emailSenderService = emailSenderService;
    }

    /**
     * check if this email exist
     * @param email
     */
    public void doStep1(String email) throws RecordNotFoundException, AlreadyExistException {
        if(userService.getByEmail(email) == null)
            throw new RecordNotFoundException("пользователь с почтой " + email + " не существует");
        if(repository.findByEmail(email) != null)
            throw new AlreadyExistException("на указанную почту" + email + "уже был отправлен код для восстановления пароля");

        String  codeToRestorePassword = registrationService.getRandomFourDigitNumber();

        emailSenderService.sendEmailToRestorePassword(email, codeToRestorePassword);// try catch если ввел не сущ почту

        RestorePassword restorePassword = new RestorePassword();
        restorePassword.setEmail(email);
        restorePassword.setCreatedDate(new Date());
        restorePassword.setCode(codeToRestorePassword);
        repository.save(restorePassword);
    }

    public AuthenticationResponse doStep2(ModelToConfirmEmail model) throws RecordNotFoundException, WrongDataException, Exception {
        RestorePassword restorePassword = repository.findByEmail(model.getEmail());
        if(restorePassword == null)
            throw  new RecordNotFoundException("на почту " + model.getEmail() + " код не был отправлен");
        else if(!restorePassword.getCode().equals(model.getConfirmCode()))
            throw new WrongDataException("не правильный код");

        repository.delete(restorePassword);

        User user = userService.getByEmail(model.getEmail());
        return registrationService.authenticate(user);


    }


    public void doStep3(RestorePasswordModel model) throws WrongDataException {
        if(!RegistrationService.isPasswordValid(model.getPassword()))
            throw new WrongDataException("пароль не соответствует требованиям");
        else if(!model.getPassword().equals(model.getConfirmPassword()))
            throw new WrongDataException("пароли не совпадают");

        userService.setNewPassword(model.getPassword());

    }
}
