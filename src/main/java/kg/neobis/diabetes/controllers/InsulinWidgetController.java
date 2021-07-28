package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.services.InsulinWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("insulin-widget")
public class InsulinWidgetController {

    private final InsulinWidgetService service;

    @Autowired
    public InsulinWidgetController(InsulinWidgetService service) {
        this.service = service;
    }
}
