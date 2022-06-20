package bakery;


/**
 * extends the class person, customer of the bakery
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Customer extends Person
{
   private boolean loyal;
   private int id;
   
   /**
    * constructor that sets the customer's id, name, gender and age
    */
   public Customer(int id, String name, Gender gender, int age){
       super(name,gender, age);
       this.loyal = false;
       this.id = id;
   } 
   
   /**
    * constructor that sets the customer's id, name, gender, age and also whether they're a loyal customer or not
    */
   public Customer(int id, String name, Gender gender, int age, boolean loyal){
       super(name,gender, age);
       this.loyal = loyal;
       this.id = id;
   }
   
   /**
    * sets customer's loyalty to true
    */
   public void beLoyal(){
       loyal = true;
   }
   
   /**
    * sets customer's loyalty to false
    */
   public void stopBeingLoyal(){
       loyal = false;
   }
   
   /**
    * returns a string with the information of a customer
    */
   public String getInfo(){
       String s = "Customer id: " + id + "\nName: " + name +"\nGender: " + gender + "\nAge: " + age + "\nLoyal: " +loyal;
       return s;
   }
   
   /**
    * returns whether the customer's loyal or not
    */
   public boolean getLoyal(){
       return loyal;
   }
   
   /**
    * returns id of the customer
    */
   public int getID(){
        return id;
    }
}
