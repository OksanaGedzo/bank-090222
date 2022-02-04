package ee.bcs.bank.customer;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "personalIdentificationCode", source = "idCode")
    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "personalIdentificationCode", source = "idCode")
    void updateCustomer(CustomerRequest customerRequest, @MappingTarget Customer customer);
}
