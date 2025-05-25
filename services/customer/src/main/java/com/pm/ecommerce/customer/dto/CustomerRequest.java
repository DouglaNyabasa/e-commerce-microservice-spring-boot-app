package com.pm.ecommerce.customer.dto;

import com.pm.ecommerce.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;




public record CustomerRequest(
         String id,
         @NotNull(message = "Customer first name is required")
         String firstName,
         @NotNull(message = "Customer first last name is required")
         String lastName,
         @Email(message = "Customer email is not a valid email address")
         @NotNull(message = "Customer first email is required")
         String email,

         Address address

) {
}
