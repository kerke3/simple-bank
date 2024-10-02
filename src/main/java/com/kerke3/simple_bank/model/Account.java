package com.kerke3.simple_bank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO : Consider flattening the approach, i.e save userId in the account. will it be more efficient ???
public class Account {
    private String accountId;
    private double balance;

    public Account(){
        this.accountId = "Current";
        this.balance = 0;
    }

    public Account(String accountId){
        this.accountId = accountId;
        this.balance = 0;
    }
}
