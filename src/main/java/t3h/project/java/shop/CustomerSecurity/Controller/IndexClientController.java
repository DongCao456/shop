package t3h.project.java.shop.CustomerSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Repository.CustomerRepository;
import t3h.project.java.shop.User.Model.User;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class IndexClientController {

    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/index")
    public String indexPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/admin/login";
        }

        Optional<Customer> customer=customerRepository.findByUsername(principal.getName());
        model.addAttribute("customer",customer.get());
        return "IndexClient";
    }
}
