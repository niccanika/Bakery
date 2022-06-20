package bakery;
import java.util.HashMap;


/**
 * Product of the bakery - Challah
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Challah extends Product
{
    
    /**
     * constructor that adds the ingredients to a hashmap, calculates weight of the product, sets time to bake to 3, 
     * price of product to 30 and amount of product to 0 (because none has been baked yet)
     */
    public Challah(){
        ingredients = new HashMap<Ingredients, Integer>();
        ingredients.put(Ingredients.FLOUR, 510);
        ingredients.put(Ingredients.SALT, 9);
        ingredients.put(Ingredients.YEAST, 12);
        ingredients.put(Ingredients.WATER, 170);
        ingredients.put(Ingredients.SUGAR, 63);
        ingredients.put(Ingredients.EGGS, 100);
        weight = this.getWeight();
        time = 3;
        price = 30;
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
}
