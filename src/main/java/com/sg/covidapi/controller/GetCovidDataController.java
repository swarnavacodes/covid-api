package com.sg.covidapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sg.covidapi.exception.ApiError;
import com.sg.covidapi.service.CovidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCovidDataController {
    
    @Autowired
    private  CovidInfoService covidInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String healthCheck() {
        return "hello!";
    }

    @RequestMapping(value = "/covid-search", method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> covidSearch(@RequestParam(required = false, defaultValue = "default") String searchType
                                             ,@RequestParam(required = false, defaultValue = "cases,active,continent") String printFields) throws JsonProcessingException {
        if(searchType.equals("default"))
            return new ResponseEntity<>(covidInfoService.callSummaryRestService(printFields), HttpStatus.OK) ;

        else return new ResponseEntity<>("The api is under construction", HttpStatus.NOT_FOUND);
    }
}
