package ee.bcs.bank.newbank.banktransaction;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankTransactionMapper {
    BankTransaction toEntity(BankTransactionDto bankTransactionDto);

    BankTransactionDto toDto(BankTransaction bankTransaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(BankTransactionDto bankTransactionDto, @MappingTarget BankTransaction bankTransaction);
}
