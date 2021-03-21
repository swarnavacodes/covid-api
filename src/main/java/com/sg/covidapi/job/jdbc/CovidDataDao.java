package com.sg.covidapi.job.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CovidDataDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertCovidData()
    {
        jdbcTemplate.update("insert into cases (cases,active,critical,deaths,continent) values (1,1,1,1,'Asia')");
    }


}
