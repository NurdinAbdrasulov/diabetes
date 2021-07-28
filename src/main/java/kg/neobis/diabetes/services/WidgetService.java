package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserWidgets;
import kg.neobis.diabetes.entity.enums.Widgets;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.UsersWidgetsModel;
import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.repositories.WidgetRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WidgetService {

    private final WidgetRepository repository;
    private final MyUserServiceImpl userService;
    private final NormalUserPropertiesService normalService;

    @Autowired
    WidgetService(WidgetRepository repository, MyUserServiceImpl userService, NormalUserPropertiesService normalService){
        this.repository = repository;
        this.userService = userService;
        this.normalService = normalService;
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

        if(model.getUserSleep() != null)
            normalService.setSleep(model.getUserSleep(), currentUser);

        if(model.getUserPressure() != null)
            normalService.setPressure(model.getUserPressure(), currentUser);

        if(model.getUserSugar() != null)
            normalService.setSugar(model.getUserSugar(), currentUser);

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

    public static void checkTime(String value) throws WrongDataException{
        if (!isTime(value))
            throw new WrongDataException("время должно быть в формате HH:mm");
    }

}
