package com.pm.ecommerce.customer.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

}
