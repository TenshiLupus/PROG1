package Kod;

import java.util.Arrays;

/**
 * @author Angel Cardenas Martinez anca8079
 */

public class User {
    
    private Dog[] ownedDogs = new Dog[0];
    private String name;
    
    //User constructor
    public User(String name){
        this.name = name;
    }
    
    //returns the name of the value.
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return String.format("%s", this.name);
    }
    //calls the toString method of the user, then calls the toString method of the list of owned dogs. 
    public void listOwnedDogs(){
        System.out.print(toString() + " " + Arrays.deepToString(ownedDogs));
            System.out.println();
    }
    //adds a passed dog to the the users owned dogs by creating a new list with an extra new slot.
    //koden baserar sig på den andra sättet att kopiera över värden till en ny array.
    public void addUserDog(Dog dog, User user) {
        Dog d = dog;
        User u = user;
        u.ownedDogs = Arrays.copyOf(u.ownedDogs, u.ownedDogs.length + 1);
        u.ownedDogs[u.ownedDogs.length - 1] = d;

    }
    //creates temporary dog array, when the dog is found, the list palces the value of the next slot into the current slot
    //The list then copies over the values to the new array and then back to the old array.
    //The code is based on the selected page in the book regarding arrays.
    public void removeUserDog(Dog dogToDelete){
        Dog[] newOwnedDogs = new Dog[ownedDogs.length - 1];

        for(int i = 0; i < ownedDogs.length; i++){
            if(ownedDogs[i] == dogToDelete){
                for(int j = i; j < ownedDogs.length - 1; j++){
                    ownedDogs[j] = ownedDogs[j + 1];
                }
            }
        }

        for(int i = 0; i < ownedDogs.length - 1; i++){//passes over old values of the old array over to the new array, then the new array is set to be the old array.
            newOwnedDogs[i] = ownedDogs[i];
        }
        ownedDogs = newOwnedDogs;
    }
}