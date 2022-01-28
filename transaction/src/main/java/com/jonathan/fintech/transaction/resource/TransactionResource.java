package com.jonathan.fintech.transaction.resource;

import com.jonathan.fintech.transaction.model.*;
import com.jonathan.fintech.transaction.service.LedgerService;
import com.jonathan.fintech.transaction.service.SecurityConfigurationService;
import com.jonathan.fintech.transaction.service.TransactionService;
import com.jonathan.fintech.transaction.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;


/**
 * @author CSEJOE
 * @version 1.0
 */
@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LedgerService ledgerService;

    @Autowired
    private SecurityConfigurationService securityConfigurationService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     *
     * @param transaction
     * @return {@link Response}
     * @apiNote Saves a Transaction object into the database and returns the transaction object in a map in a response object
     */
    @PostMapping("/clients")
    public ResponseEntity<Response> makeTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now( ))
                        .statusCode(OK.value())
                        .status(OK)
                        .data(of("Transaction Data: ",transactionService.makeTransaction(transaction)))
                        .message("Transaction operation")
                        .build()
                );
    }

    /**
     *
     * @param id
     * @return {@link Response}
     * @apiNote Takes an Integer Id and gets the client whose details correspond to the id
     */
    @GetMapping("/clients/{id}")
    public ResponseEntity<Response> makeTransaction(@PathVariable Integer id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now( ))
                        .data(of("Account creation: ",transactionService.getTransactionById(id)))
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Saving transaction")
                        .build()
        );
    }

    /**
     *
     * @return {@link Response}
     * @apiNote Returns all the clients in the database. Takes no argument
     */
    @GetMapping("/clients")
    public ResponseEntity<Response> getAllTransactions(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .data(of("Transactions: ",transactionService.getAllTransactions()))
                        .message("Transactions retrieval")
                        .build()
        );
    }

    /**
     *
     * @param ledger
     * @return {@link Response}
     * @apiNote Saves a Ledger object into the database and returns the Ledger object in a map in a Response object
     */
    @PostMapping("/ledger")
    public ResponseEntity<Response> addLedger(@RequestBody Ledger ledger){
        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(of("Operation :",ledgerService.addLedger(ledger)))
                .timeStamp(now())
                .message("Ledger insertion operation")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    /**
     *
     * @return {@link Response}
     * @apiNote returns all Ledger objects in a map inside a Response object. Takes no argument.
     */
    @GetMapping("/ledger")
    public ResponseEntity<Response> getAllLedgers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("Operation :",ledgerService.getAllLedgers()))
                        .timeStamp(now())
                        .message("Available Ledger records")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     *
     * @param jwtRequest
     * @return {@link JwtResponse}
     * @throws Exception
     * @apiNote Exposes API to authenticate other microservices using this service and returns a JWT
     */
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }
        catch (BadCredentialsException badCredentialsException){
            throw new Exception ("INVALID CREDENTIALS SUPPLIED ", badCredentialsException);
        }
        final UserDetails userDetails = securityConfigurationService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }
}