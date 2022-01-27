package com.jonathan.fintech.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@SuperBuilder

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    SequenceGenerator(name = "sequence_id_generator", method="sequence_id_generator")
    private Integer id;
    private String name;
    private String dob;
    private LocalDateTime accountCreated;
    private String bvn;
    private String email;
    private String accountNumber;
    private Long balance;

}
