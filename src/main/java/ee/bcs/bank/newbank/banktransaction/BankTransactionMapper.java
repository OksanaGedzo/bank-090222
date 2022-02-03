package ee.bcs.bank.newbank.banktransaction;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankTransactionMapper {
    BankTransaction bankTransactionDtoToBankTransaction(BankTransactionDto bankTransactionDto);

    BankTransactionDto bankTransactionToBankTransactionDto(BankTransaction bankTransaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankTransactionFromBankTransactionDto(BankTransactionDto bankTransactionDto, @MappingTarget BankTransaction bankTransaction);
}
