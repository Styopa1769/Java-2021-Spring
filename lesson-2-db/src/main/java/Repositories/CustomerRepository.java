package Repositories;

import DataBase.DBConnectionManager;
import Entities.Customer;

import java.sql.*;

public class CustomerRepository {


    private final DBConnectionManager dbConnectionManager;

    public CustomerRepository(){
        this.dbConnectionManager = new DBConnectionManager();
    }


    public Customer getCustomerByCode(String code){
        Customer customer = null;
        try {
            Connection conn = this.dbConnectionManager.connect();
            PreparedStatement st = conn.prepareStatement("select * from customer where cust_code = ?");
            st.setString(1, code);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                customer = new Customer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getString(11),
                        rs.getString(12));
            }
            st.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public void addCustomer(Customer customer){
        try {
            Connection conn = this.dbConnectionManager.connect();
            PreparedStatement st = conn.prepareStatement("insert into customer values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, customer.getCust_code());
            st.setString(2, customer.getCust_name());
            st.setString(3, customer.getCust_city());
            st.setString(4, customer.getWorking_area());
            st.setString(5, customer.getCust_country());
            st.setDouble(6, customer.getGrade());
            st.setDouble(7, customer.getOpening_ant());
            st.setDouble(8, customer.getReceive_ant());
            st.setDouble(9, customer.getPayment_ant());
            st.setDouble(10, customer.getOutstanding_ant());
            st.setString(11, customer.getPhone_no());
            st.setString(12, customer.getAgent_code());
            st.executeUpdate();
            st.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomerByCode(String code){
        try{
            Connection conn = this.dbConnectionManager.connect();
            PreparedStatement st = conn.prepareStatement("delete from customer where cust_code = ?");
            st.setString(1, code);
            st.execute();
            st.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
