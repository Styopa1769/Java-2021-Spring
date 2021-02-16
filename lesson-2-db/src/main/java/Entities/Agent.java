package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Agent {
    private String agent_code;
    private String agent_name;
    private String working_area;
    private double commission;
    private String phone_no;
    private String country;

    public Agent() {
    }

    public Agent(String agent_code) {
        this.agent_code = agent_code;
    }

    public String getAgent_code() {
        return agent_code;
    }

    public void setAgent_code(String agent_code) {
        this.agent_code = agent_code;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getWorking_area() {
        return working_area;
    }

    public void setWorking_area(String working_area) {
        this.working_area = working_area;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agent_code='" + agent_code + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", working_area='" + working_area + '\'' +
                ", commission=" + commission +
                ", phone_no='" + phone_no + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
