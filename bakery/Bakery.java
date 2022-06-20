package bakery;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Bakery
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Bakery
{
    protected Storage storage;
    private Finances fin;
    protected Product [] products = {new Bread(), new Challah(), new Croissants()};
    protected Employee [] employees;
    protected String name;
    
    /**
     * constructor of bakery that sets its name, creates an array of size 10 of employees and creates storage and bank account (Finances)
     */
    public Bakery(String name){
        storage = new Storage();
        fin = new Finances();
        employees = new Employee[10];
        this.name = name;
    }
    
    /**
     * constructor of bakery that sets its name, creates an array of any size of employees and creates storage and bank account (Finances) 
     * with any amount of money
     */
    public Bakery(String name, int money, int nOfEmployees){
        storage = new Storage();
        fin = new Finances(money);
        employees = new Employee[nOfEmployees];
        this.name = name;
    }
    
    /**
     * adds a new employee to the employees array if there's space with default salary
     */
    public void addEmployee(int id, String name, Gender gender, int age, EmployeeType type){
        for (int i = 0; i < employees.length; i++){
            if (employees[i] == null){
                employees[i] = new Employee(id, name, gender, age, type);
                break;
            }
        }
    }
    
    /**
     * adds a new employee to the employees array if there's space with custom salary
     */
    public void addEmployee(int id, String name, Gender gender, int age, EmployeeType type, int salary){
        for (int i = 0; i < employees.length; i++){
            if (employees[i] == null){
                employees[i] = new Employee(id, name, gender, age, type, salary);
                break;
            }
        }
    }
    
    /**
     * removes an employee from employees array
     */
    public void removeEmployee(int id){
        for (int i = 0; i < employees.length; i++){
            if (employees[i] != null){
                if (employees[i].getID() == id){
                    employees[i] = null;
                }
            }
        }
    }
    
    /**
     * returns current number of employees
     */
    public int currentNOfEmployees(){
        int n = 0;
        for (int i = 0; i < employees.length; i++){
            if (employees[i] != null){
                n++;
            }
        }
        return n;
    }
    
    /**
     * returns a chosen customer
     */
    public Customer chooseCustomer(ArrayList<Customer> customers){
        Scanner sc = new Scanner(System.in);
        System.out.println("Available customers: ");
        
        for(Customer c: customers){
            System.out.println(c.getInfo());
            System.out.println("------------------------------------");
        }
        
        System.out.println("Choose by ID: ");
        int id = sc.nextInt();
        Customer customer = customers.get(0);
        
        for(Customer c: customers){
            if (id == c.getID()){
                customer = c;
                break;
            }
        }
        
        return customer;
    }
    
    /**
     * Sell a product to a chosen customer and decrease the bank account balance by the price of the product.
     * If the customer is loyal he/she gets a discount
     */
    public void sell(Customer c, int i){
        if (products[i].getAmount() > 0){
            products[i].soldProduct();
            if (c.getLoyal()==true){
                fin.income(products[i].getPrice()-1);
            } else {
                fin.income(products[i].getPrice());
            }
        }
    }
    
    /**
     * Lets the user choose a baker/pastry cook (who were filtered from the employee array) by the id to bake a product
     */
    public void bake(Product p, Storage s, Employee [] emp, Finances bank){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Available bakers: ");
        
        for(int i = 0; i<emp.length; i++){
            if (employees[i] != null){
                Employee e = employees[i];
                if (e.type == EmployeeType.BAKER || e.type == EmployeeType.PASTRY_COOK || e.type == EmployeeType.ASSISTANT || e.type == EmployeeType.APPRENTICE){
                    System.out.println(e.getInfo());
                    System.out.println("------------------------------------");
                }
            }
        }
        
        System.out.println("Choose by ID: ");
        int id = sc.nextInt();
        Employee baker = emp[0];
        
        for(Employee e: emp){
            if (id == e.getID()){
                baker = e;
                break;
            }
        }
        p.bake(s, baker, bank);
    }

    /**
     * returns a string with information about the bakery
     */
    public String getInfo(){
        String s1 = "Name: "+name+"\nNumber of employees: "+this.currentNOfEmployees()+"\nBalance: "+fin.getBalance() +"€\n";
        String s2 = "Available products: \n"+ products[0].getAmount()+"x bread\n"+products[1].getAmount()+"x challah\n"+products[2].getAmount()+"x croissant\n";
        return s1+s2;
    }
    
    /**
     * returns the bank account
     */
    public Finances getFin(){
        return fin;
    }
}
