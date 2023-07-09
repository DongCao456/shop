package t3h.project.java.shop.CustomerSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Service.CustomerService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class IndexClientController {
    private final CustomerService service;

    public IndexClientController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String indexPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/client/login";
        }

        Optional<Customer> customer = service.findByUsername(principal.getName());
        model.addAttribute("customer", customer.get());

        return "client/index";
    }
}
