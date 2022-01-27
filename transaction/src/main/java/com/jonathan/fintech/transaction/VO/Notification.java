package com.jonathan.fintech.transaction.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Notification {
    @Id
    private String id;
    private String email;
    private String operation;
    private String message;
    private String accountNumber;
}
