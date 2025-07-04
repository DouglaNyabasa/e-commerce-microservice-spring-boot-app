package com.pm.ecommerce.customer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class Address {

    private String street;
    private String zipcode;
    private String houseNumber;
}
