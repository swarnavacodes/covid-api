package com.sg.covidapi.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sg.covidapi.job.jdbc.CovidDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CovidJob {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CovidDataDao covidDataDao;

//    @Scheduled(fixedRate = 5000L)
    void someJob() throws JsonProcessingException {
        System.out.println("Time is now - " + new Date());

        covidDataDao.insertCovidData();
        //jdbcTemplate.update("insert into cases (cases,active,critical,deaths,continent) values (1,1,1,1,'Asia')");
    }
}
