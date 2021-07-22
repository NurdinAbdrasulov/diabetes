package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.*;
import kg.neobis.diabetes.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class RegistrationController {
    private final RegistrationService registrationService;


    @Autowired
    RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/step1")//email and password
    public ResponseEntity<MessageModel> step1 (@RequestBody RegistrationModel registrationModel){
        MessageModel model = new MessageModel();
        try {
            registrationService.doStep1(registrationModel);
            model.setMessage("successful");
            return ResponseEntity.ok(model);
        } catch ( WrongDataException e) {
            model.setMessage(e.getMessage());
            return new ResponseEntity<>(model, e.getStatus());
        }
    }

    @PostMapping("/step2")//confirm code
    public ResponseEntity<?> step2 (@RequestBody ModelToConfirmEmail model){
        try {
             return  registrationService.doStep2(model);
        } catch (RecordNotFoundException | WrongDataException e) {
            return new ResponseEntity<>(new MessageModel(e.getMessage()),e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageModel(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/step3")//questioning
    public ResponseEntity<UserModel> step3(@RequestBody QuestioningModel model){
        return ResponseEntity.ok(registrationService.doStep3(model));
    }

    @GetMapping("/getGenders")
    public ResponseEntity<List<GenderModel>> getGenders(){
        List<GenderModel> list = new ArrayList<>();
        for (Gender gender :Gender.values())
            list.add(new GenderModel(gender.ordinal(), gender.toString()));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getDiabetesStatuses")
    public ResponseEntity<List<DiabetesStatusModel>> getDiabetes(){
        List<DiabetesStatusModel> list = new ArrayList<>();
        for (DiabetesStatus status :DiabetesStatus.values())
            list.add(new DiabetesStatusModel(status.ordinal(), status.toString()));
        return ResponseEntity.ok(list);
    }
}
