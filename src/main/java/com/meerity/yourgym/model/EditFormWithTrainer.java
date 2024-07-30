package com.meerity.yourgym.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class EditFormWithTrainer extends EditForm {
    private long trainerId;
}
