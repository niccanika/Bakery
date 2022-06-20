package bakery;


/**
 * interface for bakery's storage
 *
 * @author Nikola Janickova
 * @version 1
 */
public interface StorageAble
{
    /**
     * returns a StringBuffer with information about the storage - the total amount of ingredients and amount of every single ingredient (in kg and g)
     */
    StringBuffer checkStorage();
    
    /**
     * increases the amount of a specific ingredient as well as the total and decreases the balance of bakery's bank account
     */
    void buyIngredient(Ingredients a, int amount, Finances fin);
    
    /**
     * returns total amount of ingredients in storage
     */
    int getTotal();
    
    /**
     * returns true if there is a specific amount or more of an ingredient available, false otherwise
     */
    boolean checkIngredient(Ingredients a, int amount);
    
    /**
     * uses a specific amount of an ingredient
     */
    boolean useIngredient(Ingredients a, int amount);
}
