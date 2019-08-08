package com.symphony.comsumer.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    private SchedualServiceHi service;
    public HiController(SchedualServiceHi service) {
        this.service = service;
    }

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return service.sayHiFromClientOne(name);
    }
}
