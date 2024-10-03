package com.kerke3.simple_bank.accounts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO : Consider flattening the approach, i.e save userId in the account. will it be more efficient ???
public class Account {
    /*
        An enum can be used instead in order to preserve a certain amount of account types, or an account type field can be added
         to let the user have as many accounts of a certain type. Currently, for simplicity the user can create as many unique accounts
     */
    private String accountId;
    /* From my experience it is better to store potentially large number with extreme precision as strings,
   but this will not be needed here for our simple example. A way to represent things

   private BigDecimal balance;
   private String preciseBalance;
*/
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
