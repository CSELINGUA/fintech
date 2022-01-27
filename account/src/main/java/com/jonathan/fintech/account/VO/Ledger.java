package com.jonathan.fintech.account.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {
    private Integer id;
    private String actorAccountNumber;
    private String receiverAccountNumber;
    private String operationType;
    private LocalDateTime dateAndTime;
    private String amount;
}
