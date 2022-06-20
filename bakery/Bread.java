package bakery;
import java.util.HashMap;


/**
 * Product of the bakery - Bread
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Bread extends Product
{
    /**
     * constructor that adds the ingredients to a hashmap, calculates weight of the product, sets time to bake to 8, 
     * price of product to 50 and amount of product to 0 (because none has been baked yet)
     */
    public Bread(){
        ingredients = new HashMap<Ingredients, Integer>();
        ingredients.put(Ingredients.FLOUR, 500);
        ingredients.put(Ingredients.SALT, 10);
        ingredients.put(Ingredients.YEAST, 10);
        ingredients.put(Ingredients.WATER, 230);
        weight = this.getWeight();
        time = 8;
        price = 50;
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
