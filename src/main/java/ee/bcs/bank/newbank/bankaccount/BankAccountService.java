package ee.bcs.bank.newbank.bankaccount;

import ee.bcs.bank.newbank.customer.Customer;
import ee.bcs.bank.newbank.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BankAccountService {

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private BankAccountRepository bankAccountRepository;

    @Resource
    private BankAccountMapper bankAccountMapper;


    public void addNewAccountByLastName(BankAccountDto bankAccountDto, String lastName) {
        Customer customer = customerRepository.findByLastName(lastName);
        BankAccount bankAccount = bankAccountMapper.bankAccountDtoToBankAccount(bankAccountDto);
        bankAccount.setCustomer(customer);
        bankAccount.setLocked(false);
        bankAccountRepository.save(bankAccount);

    }
}
