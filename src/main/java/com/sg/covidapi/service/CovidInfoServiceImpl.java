package com.sg.covidapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.covidapi.CovidApiApplication;
import com.sg.covidapi.model.CaseSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"CaseSummary"})
public class CovidInfoServiceImpl implements CovidInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CovidInfoServiceImpl.class);;
    private final RestTemplate appRestClient;

    public CovidInfoServiceImpl(RestTemplate appRestClient) {
        this.appRestClient = appRestClient;
    }

    @Cacheable(value = "continent")
    public List<CaseSummary> callSummaryRestService(String printFields) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<CaseSummary> responseCaseSummary = new ArrayList<>();
        try{
            String response= appRestClient.getForObject("https://corona.lmao.ninja/v2/continents?yesterday=true&sort=", String.class);
            JsonNode rawNode = objectMapper.readTree(response);

            Map<String,String> fieldMap = new HashMap<>();

            Iterator<JsonNode> iterator = rawNode.elements();
            String[] printFieldArr = printFields.split(",");
            while(iterator.hasNext())
            {
                JsonNode childNode = iterator.next();

                if(Arrays.stream(printFieldArr).anyMatch("cases"::equals))
                    fieldMap.put("cases",childNode.get("cases").asText());
                if(Arrays.stream(printFieldArr).anyMatch("active"::equals))
                    fieldMap.put("active",childNode.get("active").asText());
                if(Arrays.stream(printFieldArr).anyMatch("critical"::equals))
                    fieldMap.put("critical",childNode.get("critical").asText());
                if(Arrays.stream(printFieldArr).anyMatch("deaths"::equals))
                    fieldMap.put("deaths",childNode.get("deaths").asText());
                if(Arrays.stream(printFieldArr).anyMatch("continent"::equals))
                    fieldMap.put("continent",childNode.get("continent").asText());
                //responseCaseSummary.add(new CaseSummary(cases,active,critical,death,continent));
                responseCaseSummary.add(new CaseSummary(fieldMap));
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Error fetching covid data");
        }

        LOGGER.info("getting data from remomte call");
        //responseCaseSummary.sort(Comparator.comparing(c->c.getActive()));
        //responseCaseSummary = responseCaseSummary.stream().sorted(Comparator.comparing(CaseSummary::getActive).reversed()).collect(Collectors.toList());
        //animals = animals.stream().sorted(Comparator.comparing(Animal::getName).reversed()).collect(Collectors.toList());

        return  responseCaseSummary;
    }
}