package com.picpaychallenge.dtos;

import java.math.BigDecimal;


public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, com.picpaychallenge.domain.user.UserType userType) {
  
}
