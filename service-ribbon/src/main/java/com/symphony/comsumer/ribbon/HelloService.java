package com.symphony.comsumer.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HelloService {
    private static final Logger LOG = Logger.getLogger(HelloService.class.getName());

    private RestTemplate restTemplate;
    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        String ret =  restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);

        LOG.log(Level.INFO, "calling trace service-hi  ");

        return ret + ", by ribbon";
    }

    public String hiError(String name) {
        return String.format("hi, %s, error occurred", name);
    }
}
