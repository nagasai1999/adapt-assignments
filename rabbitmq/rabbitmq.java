public class Customer implements Serializable {

    private String custFirstName;
    private String custLastName;

    public Customer() {
    }

    public Customer(String custFirstName, String custLastName) {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }
}


import com.sagar.CustomerProd.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

}

import com.sagar.CustomerProd.Model.Customer;
import com.sagar.CustomerProd.RabbitMQConfig;
import com.sagar.CustomerProd.Repo.CustomerRepo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-service")
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    AmqpTemplate amqpTemplate;

    /*@Autowired
    RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();*/

    @PostMapping("add-customer")
    public String addCustomer(@RequestBody Customer customer){
        amqpTemplate.convertAndSend(RabbitMQConfig.topicExchange,RabbitMQConfig.routingKey,customer);
        return "Customer added";
    }

    @GetMapping("/get-customers")
    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }
}