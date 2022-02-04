package ee.bcs.bank.newbank.bankaccount;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankAccountMapper {

    BankAccount toEntity(BankAccountDto bankAccountDto);

    BankAccountDto toDto(BankAccount bankAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(BankAccountDto bankAccountDto, @MappingTarget BankAccount bankAccount);
}
