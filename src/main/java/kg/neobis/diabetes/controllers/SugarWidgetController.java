package kg.neobis.diabetes.controllers;


import kg.neobis.diabetes.services.SugarWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sugar-widget")
public class SugarWidgetController {

    private final SugarWidgetService service;

    @Autowired
    public SugarWidgetController(SugarWidgetService service) {
        this.service = service;
    }
}
