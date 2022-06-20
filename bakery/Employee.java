package bakery;


/**
 * Employee of the bakery
 *
 * @author Nikola Janickova
 * @version 1
 */
public class Employee extends Person implements EmployAble
{
    protected EmployeeType type;
    private int id;
    private int salary;
    //salary wont be lower than this
    static final int MIN_SALARY = 3;
    
    /**
     * constructor that sets the id, name, gender, age and job position of an employee
     */
    public Employee(int id, String name, Gender gender, int age, EmployeeType type){
       super(name, gender, age);
       this.id = id;
       this.type = type;
       this.salary = 5;
    }
     
    /**
     * constructor that sets the id, name, gender, age, job position and also the salary of an employee
     */
    public Employee(int id, String name, Gender gender, int age, EmployeeType type, int salary){
       super(name, gender, age);
       this.id = id;
       this.type = type;
       this.salary = salary;
    }
    
    /**
     * changes the job position of an employee
     */
    public void changePosition(int newPos){
        switch(newPos){
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
    }
    
    /**
     * returns the salary
     */
    public int getSalary(){
        return salary;
    }
    
    /**
     * returns employee's id
     */
    public int getID(){
        return id;
    }
    
    /**
     * changes employee's salary if it's the same as minumum salary or bigger
     */
    public void setSalary(int salary){
        if (salary >= MIN_SALARY) {
            this.salary = salary;
        }
    }
    
    /**
     * returns a string with info about employee
     */
    public String getInfo(){
        String s = "Name: " + name + "\nID: " + id + "\nGender: " + gender + "\nAge: " + age + "\nEmployment type: " + type + "\nSalary: " + this.getSalary();
        return s;
    }
     
}
