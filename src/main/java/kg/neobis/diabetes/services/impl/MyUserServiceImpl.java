package kg.neobis.diabetes.services.impl;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserWidgets;
import kg.neobis.diabetes.entity.enums.Widgets;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.ModelToChangePassword;
import kg.neobis.diabetes.models.UserModel;
import kg.neobis.diabetes.models.UsersWidgetsModel;
import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.models.security.MyUserDetails;
import kg.neobis.diabetes.repositories.UserPaginationRepository;
import kg.neobis.diabetes.repositories.UserRepository;
import kg.neobis.diabetes.services.RegistrationService;
import kg.neobis.diabetes.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MyUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserPaginationRepository paginationRepository;

    private final WidgetService widgetService;

    @Autowired
    public MyUserServiceImpl(UserRepository userRepository, WidgetService widgetService, UserPaginationRepository paginationRepository) {
        this.userRepository = userRepository;
        this.widgetService = widgetService;
        this.paginationRepository = paginationRepository;
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

    public void setNewPassword(ModelToChangePassword model) throws WrongDataException {
        User user = getCurrentUser();

        if(!model.getOldPassword().equals(user.getPassword()))
            throw new WrongDataException("текущий пароль не правильный");
        if(!RegistrationService.isPasswordValid(model.getNewPassword()))
            throw new WrongDataException("новый пароль не соответствует требованиям");

        user.setPassword(model.getNewPassword());
        userRepository.save(user);
    }
    public void setNewPassword(String password){
        User user = getCurrentUser();

        user.setPassword(password);
        userRepository.save(user);
    }

    public List<WidgetModel> setWidgets(UsersWidgetsModel model) {
        UserWidgets userWidgets = widgetService.setWidgetsForCurrentUser(model, getCurrentUser());
        Set<Widgets> widgets = userWidgets.getWidgets();

        List<WidgetModel> list = new ArrayList<>();
        for(Widgets widget :  widgets)
            list.add(new WidgetModel(widget.getId(), widget.getName()));

        return list;

    }

    public Page<UserModel> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<User> usersList = userRepository.findAll();
        Page<User> userPage = paginationRepository.findAll(pageable);
        List<UserModel> resultList = new ArrayList<>();

        for(User user : usersList){
            UserModel model = new UserModel();
            model.setId(user.getId());
            model.setEmail(user.getEmail());
            model.setName(user.getName());
            model.setBirthDate(user.getBirthDate());
            model.setGender(user.getGender());
            model.setWeight(user.getWeight());
            model.setHeight(user.getHeight());
            model.setDiabetesStatus(user.getDiabetesStatus());
            resultList.add(model);
        }
        return new PageImpl<>(resultList, PageRequest.of(pageNo, pageSize, Sort.by(sortBy)), usersList.size());

    }

    public List<UserModel> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        List<UserModel> resultList = new ArrayList<>();

        for(User user : usersList){
            UserModel model = new UserModel();
            model.setId(user.getId());
            model.setEmail(user.getEmail());
            model.setName(user.getName());
            model.setBirthDate(user.getBirthDate());
            model.setGender(user.getGender());
            model.setWeight(user.getWeight());
            model.setHeight(user.getHeight());
            model.setDiabetesStatus(user.getDiabetesStatus());
            resultList.add(model);
        }
        return resultList;
    }

    public UserModel convertToModel(User user){
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setEmail(user.getEmail());
        model.setHeight(user.getHeight());
        model.setWeight(user.getWeight());
        model.setGender(user.getGender());
        model.setName(user.getName());
        model.setDiabetesStatus(user.getDiabetesStatus());
        model.setBirthDate(user.getBirthDate());

        return model;
    }

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