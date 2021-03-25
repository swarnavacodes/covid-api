package com.sg.covidapi.job.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.covidapi.model.CaseSummary;
import com.sg.covidapi.service.CovidInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class CovidDataDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CovidDataDao.class);;
    private final RestTemplate appRestClient;

    public CovidDataDao(RestTemplate appRestClient) {
        this.appRestClient = appRestClient;
    }


    @Retryable(maxAttempts=5, value = RuntimeException.class,
            backoff = @Backoff(delay = 15000, multiplier = 2))
    public void insertCovidData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CaseSummary> responseCaseSummary = new ArrayList<>();
        String response;
        JsonNode rawNode=null;
        try
        {
            response= appRestClient.getForObject("https://corona.lmao.ninja/v2/continents?yesterday=true&sort=", String.class);
            rawNode = objectMapper.readTree(response);
            Iterator<JsonNode> iterator = rawNode.elements();

            while(iterator.hasNext()) {
                JsonNode childNode = iterator.next();

                jdbcTemplate.update("insert into cases (cases,active,critical,deaths,continent) values (?,?,?,?,?,?)", childNode.get("continent").asText(),childNode.get("cases").asInt(),
                        childNode.get("active").asInt(),
                        childNode.get("critical").asInt(),
                        childNode.get("deaths").asInt(),""
                        );
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            LOGGER.error("Error connecting to service and retrying....");
        }
    }
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
}
