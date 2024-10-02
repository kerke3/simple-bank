package com.kerke3.simple_bank.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.HashMap;

@Getter
@Setter
public class User {
    /* Account ID will be supplied by the user at the creation of the account to act as unique identifier,
       that way we can prevent users from creating multiple accounts.
    */
    private String userId;
    private Boolean active;
    /* From my experience it is better to store potentially large number with extreme precision as strings,
       but this will not be needed here for our simple example. A way to represent things

       private BigDecimal balance;
       private String preciseBalance;
    */

    private final HashMap<String,Account> accounts = new HashMap<>();

    private final ArrayDeque<Transaction> transactions = new ArrayDeque<>();

    public User(String userId) {
        this.userId = userId;
        this.active = true;
        Account account = new Account();
        this.accounts.put(account.getAccountId(),account);
    }
    public User(String userId, String accountId) {
        this.userId = userId;
        this.active = true;
        Account account = new Account(accountId);
        this.accounts.put(account.getAccountId(),account);
    }

    public void updateAccounts(Account account){
        this.accounts.put(account.getAccountId(), account);
    }

    public void addTransactions(Transaction transaction){
        this.transactions.addFirst(transaction);
    }
}
