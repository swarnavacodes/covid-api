package com.sg.covidapi.job.entity;

public class CovidData {
    private int cases;
    private int active;
    private int critical;
    private int deaths;
    private String continent;

    public int getCases() {
        return cases;
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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public CovidData(int cases, int active, int critical, int deaths, String continent) {
        this.cases = cases;
        this.active = active;
        this.critical = critical;
        this.deaths = deaths;
        this.continent = continent;
    }
}
