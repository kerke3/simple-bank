package com.kerke3.simple_bank.common.repository;

import com.kerke3.simple_bank.common.exceptions.InactiveUserException;
import com.kerke3.simple_bank.common.exceptions.InvalidOperationException;
import com.kerke3.simple_bank.common.exceptions.InvalidTransactionAmountException;
import com.kerke3.simple_bank.common.exceptions.UserNotFoundException;
import com.kerke3.simple_bank.users.model.User;

import java.util.HashMap;

/*
    This is a messy but quick way of doing things, a better way is make users private and manage with setters and getters,
    also the Util can be moved to another class and users can be passed as a parameter
 */

public class CommonRepository {
    /*
    As our application will run on the main thread only, we can use a simple
    HashMap, however we can use ConcurrentHashMap if concurrency is needed and implements locks where needed.
    This will mimic a database transactional isolation.
*/
    public final static HashMap<String, User> users = new HashMap<>();

    /*
        Utils
     */

    public static void checkUserActive(User user){
        if (!user.getActive()){
            throw new InactiveUserException("Your user profile " + user.getUserId() + " is deactivated, please contact support if you wish to reactivate your profile.");
        }
    }

    public static void checkRecipientActive(User recipient){
        if (!recipient.getActive()){
            throw new InactiveUserException("Your recipient profile " + recipient.getUserId() + " is deactivated, Transfers to inactive profiles are not allowed.");
        }
    }

    public static void userNotFound(String userId){
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("Your user profile " + userId + " is not registered, please create a profile first!");
        }
    }
    public static void recipientNotFound(String recipientId){
        if (!users.containsKey(recipientId)) {
            throw new UserNotFoundException("Your recipient profile " + recipientId + " does not exist, please refer to the recipient for the correct ID.");
        }
    }

    public static void accountNotFound(User user,String accountId){
        if(!user.getAccounts().containsKey(accountId)) {
            throw new UserNotFoundException("Your user profile " + user.getUserId() + " does not have " + accountId + " account, please open the account first.");
        }
    }
    public static void recipientAccountNotFound(User recipient,String recipientAccountId){
        if(!recipient.getAccounts().containsKey(recipientAccountId)) {
            throw new UserNotFoundException("Your recipient profile " + recipient.getUserId() + " does not have " + recipientAccountId + " account, please refer to the recipient for the correct account ID.");
        }
    }

    public static void checkBalance(String accountId,double balance, double amount) {
        if (amount > balance){
            throw new InvalidTransactionAmountException(String.format("Your %s account current balance is %.2f, it is not sufficient for transaction amount %.2f", accountId,balance,amount));
        }
    }

    public static void checkIfValidTransfer(String userId, String accountId, String recipientId, String recipientAccountId) {
        if (userId.equals(recipientId) && accountId.equals(recipientAccountId)){
            throw new InvalidOperationException("Am I a joke to you ???");
        }
    }
}
