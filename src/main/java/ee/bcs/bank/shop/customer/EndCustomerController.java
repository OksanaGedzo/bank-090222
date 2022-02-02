package ee.bcs.bank.shop.customer;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/end/customer")
public class EndCustomerController {

    @Resource
    private EndCustomerRepository endCustomerRepository;

    @GetMapping("/all")
    public List<EndCustomer> getAllCustomers() {
        List<EndCustomer> allCustomers = endCustomerRepository.findAll();
        return allCustomers;
    }

    @GetMapping("/by/address")
    public List<EndCustomer> getCustomersByAddress(@RequestParam String address) {
        List<EndCustomer> customers = endCustomerRepository.findByAddress(address);
        return customers;
    }

    @PostMapping("/add")
    public EndCustomer addCustomer(@RequestBody EndCustomer customer) {
        EndCustomer save = endCustomerRepository.save(customer);
        return customer;
    }

    @GetMapping("/by/name")
    public EndCustomer getCustomerByLastName(@RequestParam String lastName) {
        EndCustomer customer = endCustomerRepository.findByLastName(lastName);
        return customer;
    }

}
