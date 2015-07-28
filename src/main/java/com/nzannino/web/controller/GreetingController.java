package com.nzannino.web.controller;

import com.nzannino.web.model.Greeting;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * User: zannino
 * Date: 20/03/2015
 * Time: 10:53
 */
@Api(basePath = "/greeting", value = "greeting", description = "greeting", produces = "application/json")
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "greeting", notes = "greeting")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 200, message = "OK") })
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}
