package com.jonathan.fintech.transaction.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


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
