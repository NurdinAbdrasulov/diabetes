package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.services.PressureWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pressure-widget")
public class PressureWidgetController {

    private final PressureWidgetService service;

    @Autowired
    public PressureWidgetController(PressureWidgetService service) {
        this.service = service;
    }
}
