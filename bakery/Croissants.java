package bakery;
import java.util.HashMap;


/**
 * Product of the bakery - Croissants
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Croissants extends Product
{
    /**
     * constructor that adds the ingredients to a hashmap, calculates weight of the product, sets time to bake to 5, 
     * price of product to 20 and amount of product to 0 (because none has been baked yet)
     */
    public Croissants(){
        ingredients = new HashMap<Ingredients, Integer>();
        ingredients.put(Ingredients.FLOUR, 250);
        ingredients.put(Ingredients.SALT, 5);
        ingredients.put(Ingredients.YEAST, 6);
        ingredients.put(Ingredients.WATER, 60);
        ingredients.put(Ingredients.SUGAR, 25);
        ingredients.put(Ingredients.BUTTER, 25);
        ingredients.put(Ingredients.MILK, 85);
        ingredients.put(Ingredients.CHOCOLATE, 20);
        weight = this.getWeight();
        time = 5;
        price = 20;
        amount = 0;
    }
    
    /**
     * returns the amount of a product that was baked
     */
    public int getAmount(){
        return amount;
    }
    
    /**
     * decreases the amount after being sold
     */
    public void soldProduct(){
        amount--;
    }
    
    /**
     * increases the amount of a product by 6, uses ingredients from Storage and lowers the balance in Finances
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
            amount += 6;
        }
    }
}
