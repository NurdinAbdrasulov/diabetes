package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserWidgets;
import kg.neobis.diabetes.entity.enums.Widgets;
import kg.neobis.diabetes.models.UsersWidgetsModel;
import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.repositories.WidgetRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WidgetService {

    private final WidgetRepository repository;

    @Autowired
    WidgetService(WidgetRepository repository){
        this.repository = repository;
    }

    public List<WidgetModel> getAllWidgets(){
        List<WidgetModel> list = new ArrayList<>();
        for(Widgets widget : Widgets.values())
            list.add(new WidgetModel(widget.getId(), widget.getName()));
        return list;
    }

    public UserWidgets setWidgetsForCurrentUser(UsersWidgetsModel model, User user) {
        UserWidgets userWidgets = repository.findByUser(user);

        if(userWidgets == null){
            userWidgets = new UserWidgets();
            userWidgets.setUser(user);
            userWidgets.setWidgets(getSetOfWidgets(model.getWidgetIds()));
        } else{
            userWidgets.setWidgets(getSetOfWidgets(model.getWidgetIds()));
        }
        return repository.save(userWidgets);

    }

    private Set<Widgets> getSetOfWidgets(Set<Long> widgetIds){
        Set<Widgets> widgets = new HashSet<>();

        for(  long widgetId : widgetIds)
            widgets.add(Widgets.get(widgetId));
        return widgets;


    }
}
