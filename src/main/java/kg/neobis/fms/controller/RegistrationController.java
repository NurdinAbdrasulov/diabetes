package kg.neobis.fms.controller;


import kg.neobis.fms.model.RegistrationModel;
import kg.neobis.fms.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {


    private RegistrationService registrationService;

    @Autowired
    RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/newAccountant")
    public ResponseEntity<String> registerNewAccountant(@RequestBody RegistrationModel registrationModel){
        return registrationService.addNewAccountant(registrationModel);
    }
}
