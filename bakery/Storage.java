package bakery;
import java.util.HashMap;


/**
 * Storage of bakery
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Storage implements StorageAble
{
    private HashMap<Ingredients, Integer> ingredients;
    private int total = 0;
    
    /**
     * constructor that adds 10kg of each ingredients to ingredients hashmap
     */
    public Storage(){
        ingredients = new HashMap<Ingredients, Integer>();
        for (Ingredients a: Ingredients.values()){
            ingredients.put(a,10000);
            total += 10000;
        }
    }
    
    /**
     * returns a StringBuffer with information about the storage - the total amount of ingredients and amount of every single ingredient (in kg and g)
     */
    public StringBuffer checkStorage(){
        StringBuffer s = new StringBuffer();
        s.append("Total: " + total/1000 + "kg " + total%1000 + "g\n");
        for (Ingredients a: ingredients.keySet()){
            s.append(a + ": " + ingredients.get(a)/1000 + "kg " + ingredients.get(a)%1000 + "g\n");
        }
        return s;
    }
    
    /**
     * increases the amount of a specific ingredient as well as the total and decreases the balance of bakery's bank account
     */
    public void buyIngredient(Ingredients a, int amount, Finances fin){
        //buy in kg
        fin.expense(a.price*amount);
        ingredients.put(a, ingredients.get(a) + amount*1000);
        total += (amount*1000);
    }
    
    /**
     * uses a specific amount of an ingredient
     */
    public boolean useIngredient(Ingredients a, int amount){
        //use in g
        if (checkIngredient(a, amount)){
            ingredients.put(a, ingredients.get(a) - amount);
            total -= amount;
            return true;
        } else {
            return false;
        }
    }
    
    /**
    public boolean buyIngredients(Ingredients a, int amount){
        int c = capacity/11;
        if (ingredients.get(a) + amount <= c){
            ingredients.put(a, ingredients.get(a) + amount);
            return true;
        } else {
            return false;
        }
    }
    **/
    
    /**
     * returns true if there is a specific amount or more of an ingredient available, false otherwise
     */
    public boolean checkIngredient(Ingredients a, int amount){
        // in g
        return ingredients.get(a) >= amount;
    }
    
    /**
     * returns total amount of ingredients in storage
     */
    public int getTotal(){
        return total;
    }
}
