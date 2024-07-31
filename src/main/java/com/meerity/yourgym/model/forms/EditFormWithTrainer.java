package com.meerity.yourgym.model.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class EditFormWithTrainer extends EditForm {
    private long trainerId;
}
