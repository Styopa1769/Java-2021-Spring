package Servlets;

import Entities.Customer;
import Repositories.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    private final CustomerRepository customerRepository;

    public CustomerServlet(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("cust_code");
        Customer customer = customerRepository.getCustomerByCode(code);
        if (customer != null){
            resp.getWriter().println(customer);
        } else {
            resp.getWriter().println("Entities.Customer not found");
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("cust_code");
        String name = req.getParameter("cust_name");
        String city = req.getParameter("cust_city");
        Customer customer = new Customer(code, name, city);
        customerRepository.addCustomer(customer);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("cust_code");
        customerRepository.deleteCustomerByCode(code);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
