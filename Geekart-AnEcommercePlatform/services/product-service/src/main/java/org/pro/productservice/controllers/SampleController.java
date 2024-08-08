package org.pro.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//first sample controller
@RestController
@RequestMapping("/say")
public class SampleController {
    @GetMapping("/{name}/{age}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("age") int age) {
        return "hello"+name+age;
    }
}