package ee.bcs.bank.newbank.customer;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/add/new")
    public void addNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addNewCustomer(customerDto);
    }

    @GetMapping("/by/lastname")
    public CustomerDto findCustomerByLastName(@RequestParam String lastName) {
        CustomerDto result = customerService.findCustomerDtoByLastName(lastName);
        return result;
    }

    @PutMapping("/update/by/lastname")
    public void updateCustomerByLastName(@RequestParam String lastName, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomerByLastName(lastName, customerDto);
    }



}
