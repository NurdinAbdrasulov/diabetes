package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import kg.neobis.diabetes.models.AgeStatisticsModel;
import kg.neobis.diabetes.models.DiabetesStatisticsModel;
import kg.neobis.diabetes.models.GenderStatisticsModel;
import kg.neobis.diabetes.models.UserModel;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
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

    public List<AgeStatisticsModel> getAge() {
        List<AgeStatisticsModel> list = new ArrayList<>();

        for(int i = 0; i <= 70; i += 10){

            int start = i;
            int end = i + 9;
            AgeStatisticsModel model = new AgeStatisticsModel();
            model.setAgeDiapason(start + " - " + end);
            model.setPercent(getPercentOfAge(start,end));

            list.add(model);
        }

        return list;
    }

    private int getPercentOfAge(int start, int end){
        List<UserModel> allUsers = userService.getAllUsers();
        int allUserNumber = allUsers.size();
        int percent = 0;

        LocalDate currentDate = LocalDate.now();

        for(UserModel userModel: allUsers) {
            java.sql.Date userBirth = userModel.getBirthDate();
            int age = Period.between(userBirth.toLocalDate(), currentDate).getYears();

            if (age >= start && age <= end )
                percent++;
        }

        return percent * 100 / allUserNumber;
    }
}


/*
    калории
 */