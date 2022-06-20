package bakery;

/**
 * Enumeration class Ingredients - ingredients used to make products
 *
 * @author Nikola Janickova
 * @version 1
 */
public enum Ingredients
{
    FLOUR(1),
    SUGAR(1),
    SALT(1),
    CHOCOLATE(5),
    OIL(4),
    BUTTER(7),
    CINNAMON(10),
    BAKING_POWDER(1),
    YEAST(2),
    EGGS(10),
    MILK(3),
    WATER(1);
    
    public final int price;
    
    /**
     * gives a price to the ingredients
     */
    private Ingredients(int price){
        this.price = price;
    }
}
