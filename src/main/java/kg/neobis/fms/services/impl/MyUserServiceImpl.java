package kg.neobis.fms.services.impl;

import kg.neobis.fms.entity.People;
import kg.neobis.fms.entity.Role;
import kg.neobis.fms.entity.User;
import kg.neobis.fms.entity.enums.UserStatus;
import kg.neobis.fms.exception.RecordNotFoundException;
import kg.neobis.fms.exception.WrongDataException;
import kg.neobis.fms.models.ModelToChangePassword;
import kg.neobis.fms.models.RegistrationModel;
import kg.neobis.fms.models.UserModel;
import kg.neobis.fms.models.security.MyUserDetails;
import kg.neobis.fms.repositories.UserRepository;
import kg.neobis.fms.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public MyUserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException("Invalid email or password.");
        Role role = user.getRole();

        return new MyUserDetails(user, role);
    }

    public void addNewUser(UserModel userModel, String password, People people) throws RecordNotFoundException {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(password);
        user.setPerson(people);
        user.setUserStatus(UserStatus.APPROVED);// т.к. добавляет супер админ
        user.setRole(roleService.getById(2l));// доб бухгалтера после миграции, иначе будут конфликты из-за пустой базы
        userRepository.save(user);
    }


    public User getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userEmail= authentication.getName();
        return getByEmail(userEmail);
    }

    public UserModel retrieveCurrentUser() {
        User user = getCurrentUser();
        UserModel model = new UserModel();
        model.setId(user.getPerson().getId());
        model.setSurname(user.getPerson().getSurname());
        model.setName(user.getPerson().getName());
        model.setEmail(user.getEmail());
        model.setPhoneNumber(user.getPerson().getPhoneNumber());
        model.setRole(user.getRole());
        model.setGroups(user.getPerson().getGroupOfPeople());
        model.setUserStatus(user.getUserStatus());
        return model;
    }

    public void setNewPassword(ModelToChangePassword model) throws WrongDataException {
        User user = getCurrentUser();

        if(!model.getOldPassword().equals(user.getPassword()))
            throw new WrongDataException("текущий пароль не правильный");
        if(!RegistrationService.isPasswordValid(model.getNewPassword()))
            throw new WrongDataException("новый пароль не соответствует требованиям");

        user.setPassword(model.getNewPassword());
        userRepository.save(user);
    }
}