package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.services.PhysicalActivityWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity-widget")
public class PhysicalActivityWidgetController {

    private final PhysicalActivityWidgetService service;

    @Autowired
    public PhysicalActivityWidgetController(PhysicalActivityWidgetService service) {
        this.service = service;
    }


}
