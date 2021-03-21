package com.sg.covidapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CaseSummary implements Serializable {
    private int cases;
    private int active;
    private int critical;
    private int deaths;
    private String continent;


    public CaseSummary(int cases, int active, int critical, int deaths, String continent) {
        this.cases = cases;
        this.active = active;
        this.critical = critical;
        this.deaths = deaths;
        this.continent = continent;
    }

    public CaseSummary(int cases, String continent) {
        this.cases = cases;
        this.continent = continent;
    }

    public CaseSummary(Map<String,String> modelMap)
    {
        for(String fields:modelMap.keySet())
        {
            if(fields.equals("cases"))
                this.cases = Integer.parseInt(modelMap.get(fields));
            else if (fields.equals("active"))
                this.active= Integer.parseInt(modelMap.get(fields));
            else if (fields.equals("critical"))
                this.critical = Integer.parseInt(modelMap.get(fields));
            else if (fields.equals("deaths"))
                this.deaths = Integer.parseInt(modelMap.get(fields));
            else if (fields.equals("continent"))
                this.continent=modelMap.get(fields);
        }
    }

    public int getCases() {
        return cases;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}
