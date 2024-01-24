package com.zeni.accountssale.presistance.entity.impl;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    
    private final String status;
    private final Date date;
    private final UUID transactionId;
    private final double amount;
    
    private Transaction(String status, Date date, UUID transactionId, double amount) {
        this.status = status;
        this.date = date;
        this.transactionId = transactionId;
        this.amount = amount;
    }
    
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }
    
    public String getStatus() {
        return status;
    }
    
    public Date getDate() {
        return date;
    }
    
    public UUID getTransactionId() {
        return transactionId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    @Override
    public String toString() {
        return String.format("Transaction{status='%s', date=%s, transactionId=%s, amount=%.2f}",
                status, date, transactionId, amount);
    }
    
    public static class TransactionBuilder {
        
        private String status;
        private Date date;
        private UUID transactionId;
        private double amount;
        
        private TransactionBuilder() {
            this.transactionId = UUID.randomUUID();
            this.date = new Date();
        }
        
        public TransactionBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        public TransactionBuilder date(Date date) {
            this.date = date;
            return this;
        }
        
        public TransactionBuilder transactionId(UUID transactionId) {
            this.transactionId = transactionId;
            return this;
        }
        
        public TransactionBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }
        
        public Transaction build() {
            return new Transaction(status, date, transactionId, amount);
        }
    }
}

