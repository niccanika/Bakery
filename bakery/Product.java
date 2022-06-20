package bakery;
import java.util.HashMap;


/**
 * Abstract class Product - products of the bakery in general
 *
 * @author Nikola Janickova
 * @version 1
 */
public abstract class Product
{
    protected int weight;
    protected HashMap<Ingredients, Integer> ingredients;
    protected int time;
    protected int price;
    protected int amount;
    
    //public abstract void bake(Storage s, Employee baker, Finances money);
    /**
     * returns the amount available of a product
     */
    public abstract int getAmount();
    
    /**
     * decreases the amount of a product by one when sold
     */
    public abstract void soldProduct();
    
    /**
     * increases the amount of a product by one, uses ingredients from Storage and lowers the balance in Finances
     */
    public void bake(Storage s, Employee baker, Finances bank){
        boolean finished = true;
        for (Ingredients i: ingredients.keySet()){
            if (s.checkIngredient(i, ingredients.get(i))){
                s.useIngredient(i, ingredients.get(i));
            } else {
                finished = false;
                break;
            }
        }
        if (finished == true){
            int cost = time*baker.getSalary();
            bank.expense(cost);
            amount++;
        }
    }
    
    
    /**
     * sets the price of a product
     */
    public void setPrice(int price){
        this.price = price;
    }
    
    
    /**
     * returns the price of a product
     */
    public int getPrice(){
        return price;
    }
    
    /**
     * calculates the weight of a product based on the amount of ingredients used
     */
    protected int getWeight(){
        weight = 0;
        for (Ingredients i: ingredients.keySet()){
            weight += ingredients.get(i);
        }
        
        return weight;
    }
}
