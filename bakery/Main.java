package bakery;
 
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Class with simple console menu
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Main
{   
    
    /**
     * console menu
     */
    public static void main(String args[]){
        Bakery bakery = new Bakery("Default");
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        Scanner sc = new Scanner(System.in);
        
        boolean running = true;
        
        try {
            System.out.println("Create new bakery");
            System.out.println("Press 1-default bakery, 2-personalised bakery, 3-end");
        
            int a = sc.nextInt();
            
            /*
            while ((a != 1) || (a != 2) || (a != 3)){
                System.out.println("Try again");
                System.out.println("Press 1-default bakery, 2-personalised bakery, 3-end");
                a = sc.nextInt();
            }*/
        
            if (a == 1){
                System.out.println("Name of bakery: ");
                String name = sc.next();
                bakery = new Bakery(name);
                System.out.println("Bakery " + name +" was created");
            } else if (a == 2){
                System.out.println("Name of bakery: ");
                String name = sc.next();
                System.out.println("Amount of money you want to start with: ");
                int money = sc.nextInt();
                System.out.println("Max. number of employees: ");
                int emp = sc.nextInt();
                bakery = new Bakery(name, money, emp);
                System.out.println("Bakery " + name +" was created");
            } else {
                running = false;
            }
            
            if (running == true){
                System.out.println("Load data?");
                System.out.println("Press y-YES, n-NO");
                String c = sc.next();
                switch(c){
                    case "y":
                        // read data from employees.txt
                        BufferedReader csvReader = new BufferedReader(new FileReader("employees.txt"));
                        
                        String row;
                        while ((row = csvReader.readLine()) != null) {
                            String[] data = row.split(",");
                            
                            Gender g;
                            EmployeeType e = EmployeeType.MANAGER;
                        
                            if (data[2].equals("MALE")){
                                g = Gender.MALE;                                   
                            } else {
                                g = Gender.FEMALE;
                            }
                            
                            switch(data[4]){
                                case "MANAGER":
                                    e = EmployeeType.MANAGER;
                                    break;
                                case "BAKER":
                                    e = EmployeeType.BAKER;
                                    break;
                                case "PASTRY_COOK":
                                    e = EmployeeType.PASTRY_COOK;
                                    break;
                                case "APPRENTICE":
                                    e = EmployeeType.APPRENTICE;
                                    break;
                                case "ASSISTANT":
                                    e = EmployeeType.ASSISTANT;
                                    break;
                                case "DISHWASHER":
                                    e = EmployeeType.DISHWASHER;
                                    break;
                                default:
                                    System.out.println("Something went wrong");
                                    break;
                            }
                            
                            bakery.addEmployee(Integer.valueOf(data[0]), data[1], g, Integer.valueOf(data[3]), e);
                        }
                                          
                        csvReader.close();
                        //read customer.txt data
                        BufferedReader csvReader2 = new BufferedReader(new FileReader("customers.txt"));
                        String row1;
                        while ((row1 = csvReader2.readLine()) != null) {
                            String[] data = row1.split(",");
                            Gender g = Gender.MALE;
                            
                            if (data[2].equals("MALE")){
                                g = Gender.MALE;                                   
                            } else {
                                g = Gender.FEMALE;
                            }
                            if (data.length == 5){
                                boolean loyal = false;
                                if (data[4] == "false"){
                                    loyal = false;
                                } else {
                                    loyal = true;
                                }
                                customers.add(new Customer(Integer.parseInt(data[0]),data[1],g,Integer.valueOf(data[3]),loyal));
                            } else {
                                customers.add(new Customer(Integer.parseInt(data[0]),data[1],g,Integer.valueOf(data[3])));
                            }
                        }
                        csvReader2.close();
                        break;
                    case "n":
                        System.out.println("No data were loaded");
                        break;
                    default:
                        System.out.println("No data were loaded");
                        break;
                }
                System.out.println("Bakery set up complete");
                System.out.println("Press 1 to continue");
                a = sc.nextInt();
            }
            //System.out.print("\u000C");
        } catch(InputMismatchException e){
            System.out.println("Creating your new business was unsuccessful");
            e.printStackTrace();
            running = false;
        } catch(FileNotFoundException fnfe){
           System.out.println("File not found");
        } catch (java.io.IOException ioe){
           System.out.println("Error");
        }
        
    
        while (running == true){
            try{
                System.out.print("\u000C");
                System.out.println("1 = Employee management  2 = Customer management  \n3 = Storage management 4 = Check finance");
                System.out.println("5 = Bakery info");
                System.out.println("6 = BAKE  7 = SELL");
                System.out.println("8 = SAVE DATA  9 = END");
                
                int a = sc.nextInt();
                
                switch(a){
                    case 1:
                        boolean end = false;
                        while (end == false){
                            System.out.print("\u000C");
                            System.out.println("EMPLOYEE MANAGEMENT");
                            System.out.println("1 = Add employee  2 = Remove employee  \n3 = Set salary 4 = Change job type  \n5 = Get info");
                            System.out.println("6 = Return to menu");
                            int p = sc.nextInt();
                            switch(p){
                                case 1:
                                    System.out.println("ADDING AN EMPLOYEE");
                                    
                                    if (bakery.currentNOfEmployees() < bakery.employees.length){
                                        System.out.print("Name: ");
                                        String name = sc.next();
                                        
                                        System.out.print("\n");
                                        
                                        int id = bakery.currentNOfEmployees() + 1;
                                        
                                        System.out.println("Gender: \n1=female  2=male");
                                        int g = sc.nextInt();
                                        Gender gender = Gender.FEMALE;
                                        
                                        if (g == 2){
                                            gender = Gender.MALE;
                                        }
                                        
                                        System.out.print("Age: ");
                                        int age = sc.nextInt();
                                        
                                        System.out.println("\nPosition: \n1 = manager  2 = baker\n3 = pastry cook  4 = apprentice\n5 = assistant  6 = dishwasher");
                                        g = sc.nextInt();
                                        EmployeeType type = EmployeeType.BAKER;
                                        switch(g){
                                            case 1:
                                                type = EmployeeType.MANAGER;
                                                break;
                                            case 2:
                                                type = EmployeeType.BAKER;
                                                break;
                                            case 3:
                                                type = EmployeeType.PASTRY_COOK;
                                                break;
                                            case 4:
                                                type = EmployeeType.APPRENTICE;
                                                break;
                                            case 5:
                                                type = EmployeeType.ASSISTANT;
                                                break;
                                            case 6:
                                                type = EmployeeType.DISHWASHER;
                                                break;
                                            default:
                                                System.out.println("Something went wrong");
                                                break;
                                        }
                                        
                                        System.out.println("Do you want to set the salary?\ny = YES   n = NO");
                                        String ans = sc.next();
                                        
                                        if (ans.equals("y")){
                                            System.out.print("Salary: ");
                                            int salary = sc.nextInt();
                                            bakery.addEmployee(id, name, gender, age, type, salary);
                                        } else {
                                            bakery.addEmployee(id, name, gender, age, type);
                                        }
                                        
                                        System.out.println("\nNew employee added");
                                    } else {
                                        System.out.println("You have reached the max. amount of employees");
                                    }
                                    System.out.println("Press 1 to continue");
                                    int p1 = sc.nextInt();
                                    break;
                                case 2:
                                    System.out.println("REMOVING AN EMPLOYEE");
                                    
                                    System.out.println("List all employees?\ny = YES   n = NO");
                                    String ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        for(Employee e: bakery.employees){
                                            if (e != null){
                                                System.out.println(e.getInfo());
                                                System.out.println("------------------------------------");   
                                            }
                                        }
                                    }
                                    
                                    System.out.println("Choose by ID: ");
                                    int id = sc.nextInt();
                                    
                                    bakery.removeEmployee(id);
                                    
                                    System.out.println("Employee removed");
                                    System.out.println("Press 1 to continue");
                                    p1 = sc.nextInt();
                                    break;
                                case 3:
                                    System.out.println("SETTING A NEW SALARY");
                                    System.out.println("List all employees?\ny = YES   n = NO");
                                    
                                    ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        for(Employee e: bakery.employees){
                                            if (e != null){
                                                System.out.println(e.getInfo());
                                                System.out.println("------------------------------------");   
                                            }
                                        }
                                    }
                                    
                                    System.out.println("Choose by ID: "); 
                                    id = sc.nextInt();
                                    
                                    for(Employee e: bakery.employees){
                                        if (e != null){
                                            if (e.getID() == id){
                                                System.out.println("Current salary: " + e.getSalary());
                                                System.out.print("New salary: ");
                                                int newSalary = sc.nextInt();
                                                e.setSalary(newSalary);
                                                break;
                                            }
                                        }
                                    }
                                    
                                    System.out.println("\nNew salary was set\nPress 1 to continue");
                                    p1 = sc.nextInt();
                                    break;
                                case 4:
                                    System.out.println("CHANGING JOB TYPE");
                                    System.out.println("List all employees?\ny = YES   n = NO");
                                    
                                    ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        for(Employee e: bakery.employees){
                                            if (e != null){
                                                System.out.println(e.getInfo());
                                                System.out.println("------------------------------------");   
                                            }
                                        }
                                    }
                                    
                                    System.out.println("Choose by ID: "); 
                                    id = sc.nextInt();
                                    
                                    for(Employee e: bakery.employees){
                                        if (e != null){
                                            if (e.getID() == id){
                                                System.out.println("Current job: " + e.type);
                                                System.out.println("1 = manager  2 = baker 3 = pastry cook\n4 = apprentice  5 = assistant  6 = dishwasher");
                                                System.out.print("New job: ");
                                                int newPos = sc.nextInt();
                                                e.changePosition(newPos);
                                                break;
                                            }
                                        }
                                    }
                                    
                                    System.out.println("\nJob type was set\nPress 1 to continue");
                                    p1 = sc.nextInt();
                                    break;
                                case 5:
                                    boolean infoEnd = false;
                                    
                                    while (infoEnd == false){
                                        System.out.print("\u000C");
                                        System.out.println("EMPLOYEES INFO");
                                        System.out.println("1 = Employee info by id  2 = Filter  3 = All employees info");
                                        System.out.println("4 = Return to menu");
                                        p = sc.nextInt();
                                        switch(p){
                                            case 1:
                                                System.out.println("ID: ");
                                                id = sc.nextInt();
                                                
                                                for(Employee e: bakery.employees){
                                                    if (e != null){
                                                        if (e.getID() == id){
                                                            System.out.println(e.getInfo());
                                                            break;
                                                        }
                                                    }
                                                }
                                                
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 2:
                                                System.out.println("Filter by: \n1 = gender  2 = position");
                                                int f = sc.nextInt();
                                                if (f == 1){
                                                    
                                                    System.out.println("1 = female only  2 = male only");
                                                    int g = sc.nextInt();
                                                    
                                                    Gender gender = Gender.FEMALE;
                                                    if (g == 2){
                                                        gender = Gender.MALE;
                                                    }
                                                    
                                                    for(Employee e: bakery.employees){
                                                        if (e != null){
                                                            if (e.gender == gender){
                                                                System.out.println(e.getInfo());
                                                                System.out.println("------------------------------------");
                                                            }
                                                        }
                                                    }
                                                    
                                                } else {
                                                    
                                                    System.out.println("Position: \n1 = manager  2 = baker\n3 = pastry cook  4 = apprentice\n5 = assistant  6 = dishwasher");
                                                    int pos = sc.nextInt();
                                                    
                                                    EmployeeType type = EmployeeType.BAKER;
                                                    switch(pos){
                                                        case 1:
                                                            type = EmployeeType.MANAGER;
                                                            break;
                                                        case 2:
                                                            type = EmployeeType.BAKER;
                                                            break;
                                                        case 3:
                                                            type = EmployeeType.PASTRY_COOK;
                                                            break;
                                                        case 4:
                                                            type = EmployeeType.APPRENTICE;
                                                            break;
                                                        case 5:
                                                            type = EmployeeType.ASSISTANT;
                                                            break;
                                                        case 6:
                                                            type = EmployeeType.DISHWASHER;
                                                            break;
                                                        default:
                                                            System.out.println("Something went wrong");
                                                            break;
                                                    }
                                                    
                                                    for(Employee e: bakery.employees){
                                                        if (e != null){
                                                            if (e.type == type){
                                                                System.out.println(e.getInfo());
                                                                System.out.println("------------------------------------");
                                                            }
                                                        }
                                                    }
                                                }
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 3:
                                                System.out.println("ALL EMPLOYEES INFO");
                                                
                                                for(Employee e: bakery.employees){
                                                    if (e != null){
                                                        System.out.println(e.getInfo());
                                                        System.out.println("------------------------------------");
                                                    }
                                                }
                                                
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 4:
                                                infoEnd = true;
                                                break;
                                            default:
                                                infoEnd = true;
                                                break;
                                        }
                                    }
                                    break;
                                case 6:
                                    end = true;
                                    break;
                                default:
                                    end = true;
                                    break;
                            }
                        }
                        break;
                    case 2:
                        end = false;
                        while (end == false){
                            System.out.print("\u000C");
                            System.out.println("CUSTOMER MANAGEMENT");
                            System.out.println("1 = Add customer  2 = Remove customer  \n3 = Change loyalty  4 = Get info");
                            System.out.println("5 = Return to menu");
                            
                            int p = sc.nextInt();
                            switch(p){
                                case 1:
                                    System.out.print("\u000C");
                                    System.out.println("ADDING A NEW CUSTOMER");
                                    
                                    System.out.print("Name: ");
                                    String name = sc.next();
                                    
                                    System.out.println("\nGender: 1-female  2-male");
                                    int g = sc.nextInt();
                                    Gender gender = Gender.FEMALE;
                                    if (g == 2){
                                        gender = Gender.MALE;
                                    }
                                    
                                    System.out.print("Age: ");
                                    int age = sc.nextInt();
                                    
                                    int id = customers.size()+1;
                                    
                                    System.out.println("\nSet loyalty now: y-YES  n-NO");
                                    String ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        System.out.println("Is this customer loyal: y-YES  n-NO");
                                        ans = sc.next();
                                        
                                        if (ans.equals("y")){
                                            customers.add(new Customer(id, name, gender, age, true));
                                        } else {
                                            customers.add(new Customer(id, name, gender, age, false));
                                        }
                                        
                                    } else {
                                        customers.add(new Customer(id, name, gender, age));
                                    }
                                    System.out.println("Customer added\nPress 1 to continue");
                                    int p1 = sc.nextInt();
                                    break;
                                case 2:
                                    System.out.println("REMOVING A CUSTOMER");
                                    System.out.println("List all customers?\ny = YES   n = NO");
                                    ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        for(Customer c: customers){
                                            System.out.println(c.getInfo());
                                            System.out.println("------------------------------------");
                                        }
                                    }
                                    
                                    System.out.println("Choose by ID: ");
                                    id = sc.nextInt();
                                    
                                    for(Customer c: customers){
                                        if (c.getID() == id){
                                            customers.remove(c);
                                            break;
                                        }
                                    }
                                    
                                    System.out.println("Customer removed");
                                    System.out.println("Press 1 to continue");
                                    p1 = sc.nextInt();
                                    break;
                                case 3:
                                    System.out.println("CHANGING LOYALTY");
                                    System.out.println("List all customers?\ny = YES   n = NO");
                                    ans = sc.next();
                                    
                                    if (ans.equals("y")){
                                        for(Customer c: customers){
                                            System.out.println(c.getInfo());
                                            System.out.println("------------------------------------");
                                        }
                                    }
                                    
                                    System.out.println("Choose by ID: ");
                                    id = sc.nextInt();
                                    
                                    for(Customer c: customers){
                                        if (c.getID() == id){
                                            if (c.getLoyal()){
                                                c.stopBeingLoyal();
                                            } else {
                                                c.beLoyal();
                                            }
                                            break;
                                        }
                                    }
                                    
                                    System.out.println("Customer's loyalty updated");
                                    System.out.println("Press 1 to continue");
                                    p1 = sc.nextInt();
                                    break;
                                case 4:
                                    boolean infoEnd = false;
                                    
                                    while (infoEnd == false){
                                        System.out.print("\u000C");
                                        System.out.println("CUSTOMERS INFO");
                                        System.out.println("1 = Customer info by id  2 = Filter  3 = All customers info");
                                        System.out.println("4 = Return to menu");
        
                                        p = sc.nextInt();
                                        switch(p){
                                            case 1:
                                                System.out.println("ID: ");
                                                id = sc.nextInt();
                                                
                                                for(Customer c: customers){
                                                    if (c.getID() == id){
                                                        System.out.println(c.getInfo());
                                                        break;
                                                    }
                                                }
                                                
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 2:
                                                System.out.println("Filter by: \n1 = gender  2 = loyalty");
                                                int f = sc.nextInt();
                                                if (f == 1){
                                                    
                                                    System.out.println("1 = female only  2 = male only");
                                                    g = sc.nextInt();
                                                    
                                                    gender = Gender.FEMALE;
                                                    if (g == 2){
                                                        gender = Gender.MALE;
                                                    }
                                                    
                                                    for(Customer c: customers){
                                                        if (c.gender == gender){
                                                            System.out.println(c.getInfo());
                                                            System.out.println("------------------------------------");
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("1 = loyal customers only  2 = non loyal customers only");
                                                    int l = sc.nextInt();
                                                    
                                                    for(Customer c: customers){
                                                        if (l == 1){
                                                            if (c.getLoyal()) {
                                                                System.out.println(c.getInfo());
                                                                System.out.println("------------------------------------");
                                                            }
                                                        } else {
                                                            if (c.getLoyal() == false) {
                                                                System.out.println(c.getInfo());
                                                                System.out.println("------------------------------------");
                                                            }
                                                        }
                                                    }
                                                }
                                                
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 3:
                                                System.out.println("ALL CUSTOMERS INFO");
                                                
                                                for(Customer c: customers){
                                                    System.out.println(c.getInfo());
                                                    System.out.println("------------------------------------");
                                                }
                                                
                                                System.out.println("Press 1 to continue");
                                                p1 = sc.nextInt();
                                                break;
                                            case 4:
                                                infoEnd = true;
                                                break;
                                            default:
                                                infoEnd = true;
                                                break;
                                        }
                                    }
                                    break;
                                case 5:
                                    end = true;
                                    break;
                                default:
                                    end = true;
                                    break;
                                }
                        }
                        break;
                    case 3:
                        end = false;
                        while (end == false){
                            System.out.print("\u000C");
                            System.out.println("STORAGE MANAGEMENT");
                            System.out.println("1 = Check storage  2 = Buy  3 = Return to menu");
                            int p1 = sc.nextInt();
                            switch(p1){
                                case 1:
                                    System.out.print("\u000C");
                                    System.out.println(bakery.storage.checkStorage());
                                    System.out.println("Press 1 to go back");
                                    p1 = sc.nextInt();
                                    break;
                                case 2:
                                    System.out.print("\u000C");
                                    System.out.println("Which ingredient do you want to buy? ");
                                    int i1 = 0;
                                    
                                    for (Ingredients ingredient: Ingredients.values()){
                                        System.out.println(i1+ ": " +ingredient);
                                        i1++;
                                    }
                                    
                                    int ing1 = sc.nextInt();
                                    
                                    Ingredients ing2 = Ingredients.FLOUR;
                                    for (int i = 0; i<Ingredients.values().length; i++){
                                        if (ing1 == i){
                                            ing2 = Ingredients.values()[i];
                                            break;
                                        }
                                    }
                                    
                                    System.out.println("How much? (in kg)");
                                    int amount = sc.nextInt();
                                    bakery.storage.buyIngredient(ing2, amount, bakery.getFin());
                                    break;
                                case 3:
                                    end = true;
                                    break;
                                default:
                                    end = true;
                                    break;
                            }
                        }
                        break;
                    case 4:
                        System.out.print("\u000C");
                        System.out.println("FINANCE");
                        
                        System.out.println("1 = Get balance");
                        
                        int p1 = sc.nextInt();
                        
                        if (p1 == 1){
                            System.out.println(bakery.getFin().getBalance() + "€");
                        }
                        
                        System.out.println("Press 1 to go back");
                        p1 = sc.nextInt();
                        break;
                    case 5:
                        System.out.print("\u000C");
                        System.out.println("BAKERY INFO");
                        System.out.println(bakery.getInfo());
                        System.out.println("Press 1 to go back");
                        p1 = sc.nextInt();
                        break;
                    case 6:
                        end = false;
                        while (end == false){
                            System.out.print("\u000C");
                            System.out.println("BAKING");
                            System.out.println("What do you want to bake?");
                            System.out.println("0 = Bread  1 = Challah  2 = Croissant\n3 = Return to menu");
                            int p = sc.nextInt();
                            
                            if (p == 0 || p == 1 || p == 2){
                                bakery.bake(bakery.products[p], bakery.storage, bakery.employees, bakery.getFin());
                            }
                            
                            if (p == 3){
                                end = true;
                            }
                        }
                        break;
                    case 7:
                        end = false;
                        while (end == false){
                            System.out.print("\u000C");
                            System.out.println("SELLING");
                            System.out.println("What do you want to sell?");
                            System.out.println("0 = Bread  1 = Challah  2 = Croissant\n3 = Return to menu");
                            int prod = sc.nextInt();
                            
                            if (prod == 0 || prod == 1 || prod == 2){
                                bakery.sell(bakery.chooseCustomer(customers), prod);
                            }
                            
                            if (prod == 3){
                                end = true;
                            }
                        }
                        break;
                    case 8:
                        try{
                            FileWriter fe = new FileWriter(new File("employees.txt"),false);
                            FileWriter fc = new FileWriter(new File("customers.txt"),false);
                            
                            String line = "";
                            for(Employee e: bakery.employees){
                                if (e != null){
                                    line = String.valueOf(e.getID())+","+ e.name+","+String.valueOf(e.gender)+","+String.valueOf(e.age)+","+String.valueOf(e.type)+"\n";
                                    fe.write(line);
                                }
                            }
                            
                            for(Customer c: customers){
                                line = String.valueOf(c.getID())+","+c.name+","+String.valueOf(c.gender)+","+String.valueOf(c.age)+","+String.valueOf(c.getLoyal())+"\n";
                                fc.write(line);
                            }
                            
                            fe.close();
                            fc.close();
                        } catch(FileNotFoundException fnfe){
                            System.out.println("File not found");
                        } catch (java.io.IOException ioe){
                            System.out.println("Error");
                        }
                        break;
                    case 9:
                        running = false;
                        break;
                    default:
                        running = false;
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Something went wrong.");
                e.printStackTrace();
                running = false;
            }
        }
    }
}
