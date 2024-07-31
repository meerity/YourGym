package com.meerity.yourgym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientInfoDTO {
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private LocalDate lastPaymentDate;
}
