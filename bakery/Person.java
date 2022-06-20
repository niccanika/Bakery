package bakery;


/**
 * Abstract class Person - write a description of the class here
 *
 * @author Nikola Janickova
 * @version 1
 */
public abstract class Person
{
    protected String name;
    protected Gender gender;
    protected int age;
    
    /**
     * constructor that sets the name, gender and age of a person
     */
    public Person(String name,Gender gender, int age){
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    
    /**
     * returns a string with info of a person
     */
    public abstract String getInfo();
    
}
