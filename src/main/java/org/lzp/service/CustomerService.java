package org.lzp.service;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.lzp.model.Customer;
import org.lzp.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerService {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);
    static {
        Properties conf= PropsUtil.loadProps("config.properties");
        DRIVER=conf.getProperty("jdbc.driver");
        URL=conf.getProperty("jdbc.url");
        USERNAME=conf.getProperty("jdbc.username");
        PASSWORD=conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e)
        {
           LOGGER.error("can not load jdbc driver");
        }
    }


    public List<Customer>getCustomerList(String keyword)
    {
        return null;
    }
    public Customer getCustomer(long id)
    {
        return null;
    }
    public boolean createCustomer(Map<String,Object>fieldMap)
    {
        return false;
    }
    public boolean updateCustomer(Map<String,Object>fieldMap)
    {
        return false;
    }
    public boolean deleteCustomer(long id)
    {
        return false;
    }
}
