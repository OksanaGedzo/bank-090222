package ee.bcs.bank.bankaccount;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankAccountMapper {

    BankAccount toBankAccount(BankAccountRequest bankAccountRequest);

    BankAccountRequest toRequest(BankAccount bankAccount);

    // eraldi on mäpitud firstName ja lastName, sest ülejäänud välja nimed on nendel objektidel samad.
    @Mapping(target = "firstName", source = "customer.firstName")
    @Mapping(target = "lastName", source = "customer.lastName")
    BankAccountResponse toResponse(BankAccount bankAccount);

    List<BankAccountResponse> toResponseList(List<BankAccount> bankAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankAccount(BankAccountRequest request, @MappingTarget BankAccount bankAccount);
}
