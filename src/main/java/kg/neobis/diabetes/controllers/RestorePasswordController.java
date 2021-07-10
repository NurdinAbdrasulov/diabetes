package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.exception.AlreadyExistException;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.EmailModel;
import kg.neobis.diabetes.models.ModelToChangePassword;
import kg.neobis.diabetes.models.ModelToConfirmEmail;
import kg.neobis.diabetes.models.RestorePasswordModel;
import kg.neobis.diabetes.models.security.AuthenticationResponse;
import kg.neobis.diabetes.services.RestorePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("restorePassword")
@CrossOrigin
public class RestorePasswordController {

    private RestorePasswordService service;

    @Autowired
    RestorePasswordController( RestorePasswordService service){
        this.service = service;
    }

    @PostMapping("step1")// send email
    public ResponseEntity<String> step1(@RequestBody EmailModel model){

//        String email = request.getParameter("email");
        String message;

        try {
            service.doStep1(model.getEmail());
            message = "successfull";
            return ResponseEntity.ok(message);
        } catch (RecordNotFoundException | AlreadyExistException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("step2")// comfirm code and autorise
    public ResponseEntity<?> step2(@RequestBody ModelToConfirmEmail model){
        String message;
        try {
            return  ResponseEntity.ok(service.doStep2(model));
        } catch (RecordNotFoundException | WrongDataException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = e.getMessage();
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("step3") // new password
    public ResponseEntity<?> step3(@RequestBody RestorePasswordModel model){
        String message;
        try {
            service.doStep3(model);
            message = "successful";
            return ResponseEntity.ok(message);
        } catch (WrongDataException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("helo")
    public ResponseEntity Test(){
        return ResponseEntity.ok("hellooojkdsjf");
    }

}
