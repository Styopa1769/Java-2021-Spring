package Entities;

public class Customer {
    private String cust_code;
    private String cust_name;
    private String cust_city;
    private String working_area;
    private String cust_country;
    private double grade;
    private double opening_ant;
    private double receive_ant;
    private double payment_ant;
    private double outstanding_ant;
    private String phone_no;
    private String agent_code;

    public Customer(String cust_code, String cust_name, String cust_city, String working_area, String cust_country, double grade, double opening_ant, double receive_ant, double payment_ant, double outstanding_ant, String phone_no, String agent_code) {
        this.cust_code = cust_code;
        this.cust_name = cust_name;
        this.cust_city = cust_city;
        this.working_area = working_area;
        this.cust_country = cust_country;
        this.grade = grade;
        this.opening_ant = opening_ant;
        this.receive_ant = receive_ant;
        this.payment_ant = payment_ant;
        this.outstanding_ant = outstanding_ant;
        this.phone_no = phone_no;
        this.agent_code = agent_code;
    }

    public Customer(String cust_code, String cust_name, String cust_city) {
        this.cust_code = cust_code;
        this.cust_name = cust_name;
        this.cust_city = cust_city;
        this.working_area = cust_city;
        this.cust_country = "COUNTRY";
        this.grade = 100;
        this.opening_ant = 0;
        this.receive_ant = 0;
        this.payment_ant = 0;
        this.outstanding_ant = 0;
        this.phone_no = "123456789";
        this.agent_code = "A001";
    }

    public String getAgent_code() {
        return agent_code;
    }

    public void setAgent_code(String agent_code) {
        this.agent_code = agent_code;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public double getOutstanding_ant() {
        return outstanding_ant;
    }

    public void setOutstanding_ant(double outstanding_ant) {
        this.outstanding_ant = outstanding_ant;
    }

    public double getPayment_ant() {
        return payment_ant;
    }

    public void setPayment_ant(double payment_ant) {
        this.payment_ant = payment_ant;
    }

    public double getReceive_ant() {
        return receive_ant;
    }

    public void setReceive_ant(double receive_ant) {
        this.receive_ant = receive_ant;
    }

    public double getOpening_ant() {
        return opening_ant;
    }

    public void setOpening_ant(double opening_ant) {
        this.opening_ant = opening_ant;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCust_country() {
        return cust_country;
    }

    public void setCust_country(String cust_country) {
        this.cust_country = cust_country;
    }

    public String getWorking_area() {
        return working_area;
    }

    public void setWorking_area(String working_area) {
        this.working_area = working_area;
    }

    public String getCust_city() {
        return cust_city;
    }

    public void setCust_city(String cust_city) {
        this.cust_city = cust_city;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }

    @Override
    public String toString() {
        return "Entities.Customer{" +
                "cust_code='" + cust_code + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", cust_city='" + cust_city + '\'' +
                ", working_area='" + working_area + '\'' +
                ", cust_country='" + cust_country + '\'' +
                ", grade=" + grade +
                '}';
    }
}
