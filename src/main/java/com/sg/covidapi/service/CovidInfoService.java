package com.sg.covidapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sg.covidapi.model.CaseSummary;

import java.util.List;

public interface CovidInfoService {
    public List<CaseSummary> callSummaryRestService(String printFields) throws JsonProcessingException;
}
