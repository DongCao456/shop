package t3h.project.java.shop.Customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/{name}")
    public ResponseEntity<Object> getByName(@PathVariable String name){
        Optional<Customer> customer=customerService.findByUsername(name);
        return ResponseHandler.getResponse(customer.get(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getList(){
        List<Customer> customer=customerService.findAll();
        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }
}
