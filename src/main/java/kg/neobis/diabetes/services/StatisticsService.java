package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import kg.neobis.diabetes.models.DiabetesStatisticsModel;
import kg.neobis.diabetes.models.GenderStatisticsModel;
import kg.neobis.diabetes.models.UserModel;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    private final MyUserServiceImpl userService;

    @Autowired
    StatisticsService(MyUserServiceImpl userService){
        this.userService = userService;
    }

    public GenderStatisticsModel getGenders() {
        List<UserModel> allUsers = userService.getAllUsers();
        int count = allUsers.size();
        int male = 0;
        int female = 0;

        for(UserModel userModel: allUsers)
            if(userModel.getGender().equals(Gender.MALE))
                male++;
            else
                female++;
        male = male * 100 / count;
        female = female * 100/ count;

        return  new GenderStatisticsModel(female, male);


    }

    public DiabetesStatisticsModel getDiabetes() {
        List<UserModel> allUsers = userService.getAllUsers();
        int count = allUsers.size();
        int diabetes = 0;
        int notDiabetes = 0;

        for(UserModel userModel: allUsers)
            if(userModel.getDiabetesStatus().equals(DiabetesStatus.NONE))
                notDiabetes++;
            else
                diabetes++;
        notDiabetes = notDiabetes * 100 / count;
        diabetes = diabetes * 100/ count;

        return  new DiabetesStatisticsModel(diabetes, notDiabetes);
    }
}
