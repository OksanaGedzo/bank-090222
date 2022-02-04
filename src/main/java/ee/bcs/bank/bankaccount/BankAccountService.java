package ee.bcs.bank.bankaccount;

import ee.bcs.bank.RequestResponse;
import ee.bcs.bank.customer.Customer;
import ee.bcs.bank.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BankAccountService {

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private BankAccountRepository bankAccountRepository;

    @Resource
    private BankAccountMapper bankAccountMapper;

    public RequestResponse addNewAccount(BankAccountRequest request) {
        String idCode = request.getPersonalIdentificationCode();

        RequestResponse response = new RequestResponse();
        if (customerRepository.existsByIdCode(idCode)) {
            Customer customer = customerRepository.findByIdCode(idCode);
            BankAccount bankAccount = bankAccountMapper.toBankAccount(request);
            bankAccount.setCustomer(customer);
            bankAccount.setLocked(false);
            bankAccountRepository.save(bankAccount);
            response.setMessage("Kliendile isikukoodiga"+ idCode +" lisati uus konto!");
        } else {
            response.setError("Kliendile isikukoodiga " + idCode + " ei lisatut uut kontot, kuna pole sellise isikukoodiga klienti!");
        }
        return response;
    }

    public List<BankAccountResponse> getAccountsByFirstNameStartsWith(String nameStartsWith) {
        // BOONUS ÜLESANNE (Leia ülesse kõik pangakontod (pangakontod, mitte kliendid) mille kasutaja eesnimi algab....)
        // (näiteks kui sisendiks on "k", siis peaks tagastama, Keit'i ja Kadi kontod)

        // mõtle, et kuidas saaksid sellistele andmetele ligi (kogumik/list pangakontodest) .....
        // andmebaasi entiteid me edastada ei tohi.... ilmselt peab need enne tagastamist vajalikule kujule mäppima
        // vaata, et kas leiad mingi hea mapperi
        return null;
    }
}
