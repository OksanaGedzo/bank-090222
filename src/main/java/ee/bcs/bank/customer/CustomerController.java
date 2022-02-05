package ee.bcs.bank.customer;

import ee.bcs.bank.RequestResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    // Lisab uue kliendi
    @PostMapping("/add/new")
    public RequestResponse addNewCustomer(@RequestBody CustomerRequest request) {
        RequestResponse response = customerService.addNewCustomer(request);
        return response;
    }

    // Leiab kliendi (ees JA perekonna) nime järgi
    @PostMapping("/by/name")
    public CustomerResponse findCustomerByName(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.findCustomerByName(request);
        return response;
    }

    // uuendab isikukoodi järgi isikuanmdeid (uuendab firstName ja lastName)
    @PutMapping("/update/by/id/code")
    public RequestResponse updateCustomerByIdCode(@RequestBody CustomerRequest customerRequest) {
        RequestResponse response = customerService.updateCustomerByIdCode(customerRequest);
        return response;
    }

}
