package kg.neobis.diabetes.services.impl;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.*;
import kg.neobis.diabetes.models.security.MyUserDetails;
import kg.neobis.diabetes.repositories.UserRepository;
import kg.neobis.diabetes.services.RegistrationService;
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

    @Autowired
    public MyUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException("Invalid email or password.");

        return new MyUserDetails(user);
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

//    public UserModel retrieveCurrentUser() {
//        User user = getCurrentUser();
//        UserModel model = new UserModel();
//        model.setId(user.getPerson().getId());
//        model.setSurname(user.getPerson().getSurname());
//        model.setName(user.getPerson().getName());
//        model.setEmail(user.getEmail());
//        model.setPhoneNumber(user.getPerson().getPhoneNumber());
//        model.setRole(user.getRole());
//        model.setGroups(getGroupModels(user.getPerson().getGroupOfPeople()));
//        model.setUserStatus(user.getUserStatus());
//        model.setResetToken(user.getResetToken());
//
//        return model;
//    }

//    private Set<GroupModel> getGroupModels(Set<GroupOfPeople> groupOfPeople ){
//        Set<GroupModel> resultSet = new HashSet<>();
//        for(GroupOfPeople group: groupOfPeople)
//            resultSet.add(new GroupModel(group.getId(), group.getName(),group.getGroupStatus()));
//        return resultSet;
//    }

    public void setNewPassword(ModelToChangePassword model) throws WrongDataException {
        User user = getCurrentUser();

        if(!model.getOldPassword().equals(user.getPassword()))
            throw new WrongDataException("текущий пароль не правильный");
        if(!RegistrationService.isPasswordValid(model.getNewPassword()))
            throw new WrongDataException("новый пароль не соответствует требованиям");

        user.setPassword(model.getNewPassword());
        userRepository.save(user);
    }
    public void setNewPassword(String password) throws WrongDataException {
        User user = getCurrentUser();

        user.setPassword(password);
        userRepository.save(user);
    }

//    public List<UserModel> getAllUsers() {
//        List<UserModel> resultList = new ArrayList<>();
//
//        List<User> usersList = userRepository.findAll();
//        for(User user : usersList){
//            UserModel model = new UserModel();
//            model.setId(user.getPerson().getId());
//            model.setEmail(user.getEmail());
//            model.setUserStatus(user.getUserStatus());
//            model.setRole(user.getRole());
//            model.setSurname(user.getPerson().getSurname());
//            model.setName(user.getPerson().getName());
//            model.setPhoneNumber(user.getPerson().getPhoneNumber());
//            model.setGroups(getGroupModels(user.getPerson().getGroupOfPeople()));
//            resultList.add(model);
//        }
//        return resultList;
//    }

//    public void updateUser(ModelToUpdateUser model) throws RecordNotFoundException {
//        User user = userRepository.findByEmail(model.getEmail());
//        if(user == null)
//            throw new RecordNotFoundException("пользователь с указанной почтой не найден");
//
//        if(model.getUserStatus() != null)
//            user.setUserStatus(model.getUserStatus());
//        peopleService.update(model);
//        userRepository.save(user);
//    }

//    public User findUserByResetToken(String resetToken) {
//        return userRepository.findByResetToken(resetToken);
//    }


}