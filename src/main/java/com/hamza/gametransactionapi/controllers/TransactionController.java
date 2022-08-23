package com.hamza.gametransactionapi.controllers;

import com.hamza.gametransactionapi.models.Transaction;
import com.hamza.gametransactionapi.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * This a simple endpoint which saves a transaction into DB. It uses a Transaction POJO and consumes it as
     * JSON template to persist into DB.
     *
     * @param transaction
     * @return
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        Transaction savedTransaction;
        try {
            savedTransaction = transactionService.saveTransaction(transaction);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(new URI("/transactions/" +
                savedTransaction.getTransactionId())).body(savedTransaction);
    }

    /**
     * This endpoint returns all transactions, if no query parameters are entered. There are two optional parameters,
     * greater which returns all transactions greater than a certain amount. Less returns all transaction less than
     * a certain amount.
     *
     * @param less
     * @param greater
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllTransaction(@RequestParam Optional<BigDecimal> less,
                                               @RequestParam Optional<BigDecimal> greater) {
        try {
            if (less.isPresent() && greater.isPresent()) {
                return ResponseEntity.badRequest().body("You can only search for greater or less than");
            }
            if (less.isPresent()) {
                List<Transaction> lessThan = transactionService.getTransactionsLessThan(less.get());
                return ResponseEntity.ok(lessThan);
            }
            if (greater.isPresent()) {
                List<Transaction> lessThan = transactionService.getTransactionsMoreThan(greater.get());
                return ResponseEntity.ok(lessThan);
            }
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * This endpoint uses a path variable to find a transaction. The transaction id is passed in as an argument.
     *
     * @param transactionId
     * @return ResponseEntity
     */
    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<?> findTransactionById(@PathVariable Long transactionId) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * This endpoint uses a query param to find a transaction. The userId is passed in as an argument and
     * all relevant transaction(s) are returned.
     *
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping("/user")
    public ResponseEntity<?> findTransactionByUserId(@RequestParam Long userId) {
        try {
            List<Optional<Transaction>> transactions = transactionService.getTransactionByUserId(userId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * This endpoint uses a path variable to delete a transaction from the database, the id passed in from the path
     * variable is unique to the transaction. The method return a HTTP NOT_FOUND if the transaction Id is not valid.
     *
     * @param transactionId
     * @return ResponseEntity
     */
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long transactionId) {
        try {
            String deleted = "Transaction with id:  " + transactionId + " has been deleted";
            transactionService.deleteTransactionById(transactionId);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
