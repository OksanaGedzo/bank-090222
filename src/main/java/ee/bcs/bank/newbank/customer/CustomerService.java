package ee.bcs.bank.newbank.customer;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerRepository customerRepository;

    public void addNewCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customerRepository.save(customer);
    }

    public CustomerDto findCustomerDtoByLastName(String lastName) {
        Customer customer = customerRepository.findByLastName(lastName);
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
        return customerDto;
    }


    public Customer findCustomerByLastName(String lastName) {
        Customer customer = customerRepository.findByLastName(lastName);
        return customer;
    }

    public void updateCustomerByLastName(String lastName, CustomerDto customerDto){
        Customer customer = customerRepository.findByLastName(lastName);
        customerMapper.updateCustomerFromCustomerDto(customerDto, customer);
        customerRepository.save(customer);
    }
}
