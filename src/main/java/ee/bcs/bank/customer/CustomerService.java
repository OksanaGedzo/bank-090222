package ee.bcs.bank.customer;

import ee.bcs.bank.RequestResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerRepository customerRepository;

    public RequestResponse addNewCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerRepository.save(customer);

        RequestResponse response = new RequestResponse();
        response.setMessage("Uus klient edukalt lisatud");
        return response;
    }

    public CustomerResponse findCustomerByName(CustomerRequest customerRequest) {
        String firstName = customerRequest.getFirstName();
        String lastName = customerRequest.getLastName();
        Customer customer = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        CustomerResponse response = customerMapper.toCustomerResponse(customer);
        return response;
    }


    public RequestResponse updateCustomerByIdCode(CustomerRequest customerRequest) {
        String idCode = customerRequest.getIdCode();

        RequestResponse response = new RequestResponse();
        if (customerRepository.existsByIdCode(idCode)) {
            Customer customer = customerRepository.findByIdCode(idCode);
            customerMapper.updateCustomer(customerRequest, customer);
            customerRepository.save(customer);
            response.setMessage("Kliendi isikukoodiga " + idCode + " andmed said edukalt uuendatud!");
        } else {
            response.setError("Kliendi isikukoodiga " + idCode + " andmed ei uuendatud, kuna pole sellise isikukoodiga klienti!");
        }

        return response;
    }
}
