package entity;

import json.JsonHelper;
import json.JsonSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "agents")
public class Agent implements JsonSerializable {
    private final static String AGENT_CODE_PROPERTY = "agent_code";
    private final static String AGENT_NAME_PROPERTY = "agent_name";
    private final static String WORKING_AREA_PROPERTY = "working_area";
    private final static String COMMISSION_PROPERTY = "commission";
    private final static String PHONE_NO_PROPERTY = "phone_no";
    private final static String COUNTRY_PROPERTY = "country";

    private String agentCode;
    private String agentName;
    private String workingArea;
    private double commission;
    private String phoneNo;
    private String country;

    public Agent() {
    }

    public Agent(String json) {
        Map<String, String> properties = JsonHelper.toPropertiesMap(json);
        agentCode = properties.getOrDefault(AGENT_CODE_PROPERTY, "");
        agentName = properties.getOrDefault(AGENT_NAME_PROPERTY, "");
        workingArea = properties.getOrDefault(WORKING_AREA_PROPERTY, "");
        commission = Double.parseDouble(properties.getOrDefault(COMMISSION_PROPERTY, "0.0"));
        phoneNo = properties.getOrDefault(PHONE_NO_PROPERTY, "");
        country = properties.getOrDefault(COUNTRY_PROPERTY, "");
    }

    @Id
    @Column(name = "agent_code", unique = true, nullable = false, updatable = false, insertable = false)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "agent_name")
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Column(name = "working_area")
    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(String workingArea) {
        this.workingArea = workingArea;
    }

    @Column(name = "commission")
    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agent_code='" + agentCode + '\'' +
                ", agent_name='" + agentName + '\'' +
                ", working_area='" + workingArea + '\'' +
                ", commission=" + commission +
                ", phone_no='" + phoneNo + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @PostLoad
    protected void trim(){
        agentCode = trimIfNotNull(agentCode);
        agentName = trimIfNotNull(agentName);
        workingArea = trimIfNotNull(workingArea);
        phoneNo = trimIfNotNull(phoneNo);
        country = trimIfNotNull(country);
    }

    private static String trimIfNotNull(String s){
        return s == null ? null : s.trim();
    }

    @Override
    public String toJson() {
        return "{" +
                JsonHelper.toJsonEntry(AGENT_CODE_PROPERTY, agentCode) + JsonHelper.SEPARATOR +
                JsonHelper.toJsonEntry(AGENT_NAME_PROPERTY, agentName) + JsonHelper.SEPARATOR +
                JsonHelper.toJsonEntry(WORKING_AREA_PROPERTY, workingArea) + JsonHelper.SEPARATOR +
                JsonHelper.toJsonEntry(COMMISSION_PROPERTY, commission) + JsonHelper.SEPARATOR +
                JsonHelper.toJsonEntry(PHONE_NO_PROPERTY, phoneNo) + JsonHelper.SEPARATOR +
                JsonHelper.toJsonEntry(COUNTRY_PROPERTY, country) +
                '}';
    }
}
