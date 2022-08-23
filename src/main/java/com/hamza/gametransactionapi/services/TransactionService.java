package com.hamza.gametransactionapi.services;

import com.hamza.gametransactionapi.models.Transaction;
import com.hamza.gametransactionapi.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private final TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    /**
     * This method returns a list of all the transactions that are found within the DB.
     *
     * @return List Transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    /**
     * This a simple method that persists a transaction into a DB. It takes a transaction POJO as an argument and
     * returns a transaction.
     *
     * @param transaction
     * @return Transaction
     */
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    /**
     * This method returns a list of all transactions which are less than a certain monetary amount. It takes amount as
     * an argument and returns all relevant transactions.
     *
     * @param amount
     * @return List of transactions
     * @throws Exception
     */
    public ArrayList<Transaction> getTransactionsLessThan(BigDecimal amount) throws Exception {

        ArrayList<Transaction> matchingTransactions = transactionRepo.findByAmountLessThan(amount);

        if (matchingTransactions.isEmpty()) {
            throw new Exception("There are no transactions less than or equal to " + amount + "!");
        }
        return matchingTransactions;
    }

    /**
     * This method returns a list of all transactions which are more than a certain monetary amount. It takes amount as
     * an argument and returns all relevant transactions.
     *
     * @param amount
     * @return List of transactions
     * @throws Exception
     */
    public ArrayList<Transaction> getTransactionsMoreThan(BigDecimal amount) throws Exception {

        ArrayList<Transaction> matchingTransactions = transactionRepo.findByAmountGreaterThan(amount);

        if (matchingTransactions.isEmpty()) {
            throw new Exception("There are no transactions more than or equal to " + amount + "!");
        }
        return matchingTransactions;
    }

    /**
     * This method finds and returns a unique transaction based on the transaction id. It takes transaction_id as an
     * argument. If no transaction with the value exists an exception error is thrown.
     *
     * @param id
     * @return Transaction
     * @throws Exception
     */
    public Transaction getTransactionById(Long id) throws Exception {

        Optional<Transaction> transactionOptional = transactionRepo.findById(id);

        if (transactionOptional.isEmpty()) {
            throw new Exception("No transaction with id: " + id + " found!!");
        }
        return transactionOptional.get();
    }

    /**
     * This method finds and returns transaction(s) based on the user id. It takes user_id as an
     * argument. If no transaction(s) with the value exists an exception error is thrown.
     *
     * @param id
     * @return List of transactions
     * @throws Exception
     */
    public List<Optional<Transaction>> getTransactionByUserId(Long id) throws Exception {

        List<Optional<Transaction>> transactionOptional = transactionRepo.findByUserId(id);

        if (transactionOptional.isEmpty()) {
            throw new Exception("No transaction with user_id: " + id + " found!!");
        }
        return new ArrayList<>(transactionOptional);
    }

    /**
     * A simple method that deletes a transaction from the the DB. It passes id as an argument in the parameter,this
     * unique id coincides with the transaction id, of the transaction which is being removed.
     *
     * @param id
     */
    public void deleteTransactionById(Long id) {
        transactionRepo.deleteById(id);
    }
}
