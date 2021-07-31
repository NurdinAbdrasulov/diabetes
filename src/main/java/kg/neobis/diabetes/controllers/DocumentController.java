package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.UserAgreementModel;
import kg.neobis.diabetes.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("documents")
public class DocumentController {

    private final DocumentService service;

    @Autowired
    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("user-agreement")
    public ResponseEntity<?> getUserAgreement(){
        return service.getUserAgreement();
    }

    @PutMapping("user-agreement")
    public ResponseEntity<?> getUserAgreement(@RequestBody UserAgreementModel model){
        return service.changeUserAgreement(model);
    }
}
