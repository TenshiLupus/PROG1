package Kod;

/**
 * 
 * @author Angel Cardenas Martinez anca8079;
 * 
 */

public class Dog {
    // instance variables
    private static final double TAX_DEFAULT_LENGTH = 3.7;
    private static final int TAIL_DIVISOR = 10;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private User owner = new User(null);

    // Dog constructor
    public Dog(String name, String breed, int age, int weight, User owner) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
        this.owner = owner;

    }

    // ACCESSOR METHODS

    //returns the name of the dog
    public String getName() {

        return name;
    }
    //returns the breed name of the dog
    public String getBreed() {

        return breed;

    }
    //returns the age value of the dog.
    public int getAge() {

        return age;
    }
    //returns the weight value of the dog.
    public int getWeight(){
        return weight;
    }
    //returns the User object
    public User getOwner(){
        if(owner == null){
            return null;
        }
        return owner;
    }

    // get the tail of dog function
    public double getTailLength() {

        // Get dog exception
        if (breed.equalsIgnoreCase("Tax") || breed.equalsIgnoreCase("Dachshund")) {
            return TAX_DEFAULT_LENGTH;
        }
        // otherwise continue normally
        else {
            double dogTailLength = age * (double) weight / TAIL_DIVISOR;
            return dogTailLength;
        }

    }

    // increase the age by given amount of ageincrement
    public int increaseDogAge(int ageIncrement) {

        return age += ageIncrement;
    }

    //Calls the toString method of the dog.
    public String toString() {

        return this.getName();

    }
    //Assigns the passed user to the owner  field of the dog.
    public void assignOwner(User user){

        this.owner = user;

    }
    //checks if the the dog has a owner or not.
    public boolean isOwned(){
        if(this.owner != null){
            return true;
        }
        return false;
    }
}