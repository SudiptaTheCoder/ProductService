package com.future.productservice.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Items")
public class ProductController {

    @GetMapping("/welcome/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return ("Hello "+name);
    }

    @GetMapping("/exit")
    public  String sayGoodbye(){
        return ("Goodbye World");
    }
}
