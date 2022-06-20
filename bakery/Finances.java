package bakery;


/**
 * Bank account of bakery
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Finances
{
    private int balance;
    
    /**
     * constructor that sets the balance to 1000
     */
    public Finances(){
        balance = 1000;
    }
    
    /**
     * constructor that sets the balance to whatever int we choose
     */
    public Finances(int balance){
        this.balance = balance;
    }
    
    
    /**
     * returns the accounts balance
     */
    public int getBalance(){
        return balance;
    }
    
    /**
     * increases the balance by amount
     */
    public void income(int amount){
        if (amount > 0) {
            balance += amount;
        }
    }
    
    
    /**
     * decreases the balance by amount
     */
    public void expense(int amount){
        if (amount > 0) {
            balance -= amount;
        } else {
            balance += amount;
        }
    }
}
