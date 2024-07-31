package com.meerity.yourgym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerAndTraineesDTO {
    private String TrainerFirstName;
    private String TrainerLastName;
    private long traineesCount;
}
