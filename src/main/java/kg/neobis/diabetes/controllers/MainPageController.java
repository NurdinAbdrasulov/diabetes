package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.services.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main-page")
public class MainPageController {

    private final MainPageService service;

    @Autowired
    public MainPageController(MainPageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getMainPage(){
        return service.getMainPage();
    }
}
