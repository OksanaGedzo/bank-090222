package ee.bcs.bank.newbank.bankaccount;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankAccountMapper {
    BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

    BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankAccountFromBankAccountDto(BankAccountDto bankAccountDto, @MappingTarget BankAccount bankAccount);
}
