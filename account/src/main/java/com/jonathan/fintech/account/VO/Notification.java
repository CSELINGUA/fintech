package com.jonathan.fintech.account.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Notification {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String email;
    private String operation;
    private String message;
    private String accountNumber;
}
