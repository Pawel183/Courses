package com.embarkx.embarkx;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse("Hello, World!");
    }

    @PostMapping("/hello")
    public String helloPost(@RequestBody HelloResponse helloResponse) {
        return "Message: " + helloResponse.getMessage() + "!";
    }

    @GetMapping("/hello/{name}")
    public String helloPostWithName(@PathVariable String name) {
        return "Hello " + name + "!";
    }
}
