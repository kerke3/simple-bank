package com.kerke3.simple_bank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO : Consider flattening the approach, i.e save userId in the account. will it be more efficient ???
public class Account {
    /* From my experience it is better to store potentially large number with extreme precision as strings,
       but this will not be needed here for our simple example. A way to represent things

       private BigDecimal balance;
       private String preciseBalance;
    */
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
