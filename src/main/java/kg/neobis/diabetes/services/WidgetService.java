package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.NormalUserSleep;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserWidgets;
import kg.neobis.diabetes.entity.enums.Widgets;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.*;
import kg.neobis.diabetes.repositories.WidgetRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class WidgetService {

    private final WidgetRepository repository;
    private final MyUserServiceImpl userService;
    private final NormalUserPropertiesService normalService;
    private final MedicationService medicationService;

    @Autowired
    WidgetService(WidgetRepository repository, MyUserServiceImpl userService, NormalUserPropertiesService normalService, MedicationService medicationService){
        this.repository = repository;
        this.userService = userService;
        this.normalService = normalService;
        this.medicationService = medicationService;
    }

    public List<WidgetModel> getAllWidgets(){
        List<WidgetModel> list = new ArrayList<>();
        for(Widgets widget : Widgets.values())
            list.add(new WidgetModel(widget.getId(), widget.getName()));
        return list;
    }


    private Set<Widgets> getSetOfWidgets(Set<Long> widgetIds){
        Set<Widgets> widgets = new HashSet<>();

        for(  long widgetId : widgetIds)
            widgets.add(Widgets.get(widgetId));
        return widgets;
    }



    public List<WidgetModel> setWidgets(UsersWidgetsModel model) {
        User currentUser = userService.getCurrentUser();

//        if(model.getUserSleep() != null)
//            normalService.setSleep(model.getUserSleep(), currentUser);
//
//        if(model.getUserPressure() != null)
//            normalService.setPressure(model.getUserPressure(), currentUser);
//
//        if(model.getUserSugar() != null)
//            normalService.setSugar(model.getUserSugar(), currentUser);

        Optional<UserWidgets> byUser = repository.findByUser(currentUser);
        UserWidgets userWidgets = byUser.orElseGet(UserWidgets::new);
        userWidgets.setUser(currentUser);
        userWidgets.setWidgets(getSetOfWidgets(model.getWidgetIds()));
        repository.save(userWidgets);

        Set<Widgets> widgets = userWidgets.getWidgets();
        List<WidgetModel> list = new ArrayList<>();
        for(Widgets widget :  widgets)
            list.add(new WidgetModel(widget.getId(), widget.getName()));

        return list;
    }

    private static boolean isTime(String value){
        return value.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
    }

    public static boolean isToday(Date date){
        Calendar trackedTime = Calendar.getInstance();
        trackedTime.setTime(date);

        Calendar today = Calendar.getInstance();

        return today.get(Calendar.YEAR) == trackedTime.get(Calendar.YEAR)
                && today.get(Calendar.DAY_OF_YEAR) == trackedTime.get(Calendar.DAY_OF_YEAR);
    }


    public static void checkTime(String value) throws WrongDataException{
        if (!isTime(value))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"время должно быть в формате HH:mm");
    }

    public static Date today(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public ResponseEntity<?> setNormalPressure(ModelToAddNormalUserPressure model) {
        User currentUser = userService.getCurrentUser();

        normalService.setPressure(model, currentUser);
        return ResponseEntity.ok(new MessageModel("added!"));
    }

    public MessageModel setNormalSleep(ModelToAddNormalUserSleep model) {
        normalService.setSleep(model, userService.getCurrentUser());
        return new MessageModel("added!");
    }

    public MessageModel setNormalSugar(ModelToAddNormalUserSugar model) {
        normalService.setSugar(model, userService.getCurrentUser());
        return new MessageModel("added!");
    }

    public WidgetsAndNormalValuesModel getWidgetsOfCurrentUser() {

        WidgetsAndNormalValuesModel result = new WidgetsAndNormalValuesModel();
        User user = userService.getCurrentUser();

        Optional<UserWidgets> byUser = repository.findByUser(user);
        if(byUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "у пользователя нет виджетов");

        UserWidgets userWidgets = byUser.get();
        Set<Widgets> widgets = userWidgets.getWidgets();
        List<WidgetModel> listOfWidgets = new ArrayList<>();
        for(Widgets widget :  widgets)
            listOfWidgets.add(new WidgetModel(widget.getId(), widget.getName()));

        ModelToAddNormalUserSleep sleep = null;
        ModelToAddNormalUserPressure pressure = null;
        ModelToAddNormalUserSugar sugar = null;
        try {
            NormalUserSleep normalSleepValue = normalService.getNormalSleepValue(user);
            sleep = new ModelToAddNormalUserSleep(normalSleepValue.getStartTime(), normalSleepValue.getEndTime());

        } catch (ResponseStatusException e){

        }
        try {
            Double normalSugarValue = normalService.getNormalSugarValue(user);
            sugar = new ModelToAddNormalUserSugar(normalSugarValue);

        } catch (ResponseStatusException e){

        }
        try {
            Map<String, Double> normalPressureValue = normalService.getNormalPressureValue(user);
            Double systolic = normalPressureValue.get(NormalUserPropertiesService.SYSTOLIC);
            Double diastolic = normalPressureValue.get(NormalUserPropertiesService.DIASTOLIC);

            pressure = new ModelToAddNormalUserPressure(systolic, diastolic);

        } catch (ResponseStatusException e){

        }

        List<MedicationModel> medications = medicationService.getByUser(user);


        result.setWidgets(listOfWidgets);
        result.setUserSleep(sleep);
        result.setUserSugar(sugar);
        result.setUserPressure(pressure);
        result.setMedications(medications);
        return result;


    }
}
