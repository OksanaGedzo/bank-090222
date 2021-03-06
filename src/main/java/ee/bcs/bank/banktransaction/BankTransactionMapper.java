package ee.bcs.bank.banktransaction;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankTransactionMapper {
    String ATM = "ATM";

    //    mäpitakse ära väljad: senderAccountNumber, receiverAccountNumber, amount
    BankTransaction toBankTransaction(TransactionRequest request);

    // eraldi on mäpitud firstName ja lastName, sest ülejäänud välja nimed on nendel objektidel samad.
    @Mapping(target = "firstName", source = "bankAccount.customer.firstName")
    @Mapping(target = "lastName", source = "bankAccount.customer.lastName")
    @Mapping(target = "idCode" , source = "bankAccount.customer.personalIdentificationCode")
    BankTransactionResponse toResponse(BankTransaction transaction);


    // Sama mis eelmine kohe siin üleval, aga tehakse List sisend -> List väljund
    List<BankTransactionResponse> toResponseList(List<BankTransaction> transaction);


    //    mäpitakse ära väljad: accountNumber -> receiverAccountNumber
    //    mäpitakse ära väljad: konstant "ATM" -> senderAccountNumber
    //    amount -> amount
    @Mapping(target = "receiverAccountNumber", source = "accountNumber")
    @Mapping(target = "senderAccountNumber", constant = ATM)
    BankTransaction toDepositTransaction(OwnerAccountTransactionRequest request);


    //    mäpitakse ära väljad: accountNumber -> receiverAccountNumber
    //    mäpitakse ära väljad: konstant ATM -> senderAccountNumber
    //    amount -> amount
    @Mapping(target = "senderAccountNumber", source = "accountNumber")
    @Mapping(target = "receiverAccountNumber", constant = ATM)
    BankTransaction toWithdrawTransaction(OwnerAccountTransactionRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankTransaction(BankTransactionResponse response, @MappingTarget BankTransaction transaction);
}
