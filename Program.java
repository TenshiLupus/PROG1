package Kod;

//import libraries
import java.util.ArrayList;

/**
 * @author Angel Cardenas Martinez anca8079
 */

public class Program {

    private ArrayList<Dog> listOfDogs = new ArrayList<>();
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private ArrayList<Auction> auctions = new ArrayList<>();
    private Input methodAccessor = new Input();
    private int universalCounter;

    // initializes main program body
    public void startProgram() {
        getPresentation();
        runLoop();
        exit();
    }
    // prints out text at the start of the program.
    private void getPresentation() {
        System.out.println("Hi and welcome to the bid!");
    }

    private void runLoop() {
        String userInput;
        // run loop to get user input and pass over to getmenu
        do {
            userInput = methodAccessor.getInput();
            getMenu(userInput);
        } while (!userInput.equalsIgnoreCase("exit"));
    }

    //pass in input and compare to possible cases to initialize
    private void getMenu(String userInput) {

        switch (userInput) {

        case "register new dog":
            registerNewDog();
            break;
        case "list dogs":
            listDogs();
            break;
        case "increase age":
            increaseAge();
            break;
        case "remove dog":
            removeDog();
            break;
        case "register new user":
            addUser();
            break;
        case "list users":
            listUsers();
            break;
        case "give dog":
            giveDog();
            break;
        case "remove user":
            removeUser();
            break;
        case "exit":
            break;
        case "start auction":
            startAuction();
            break;
        case "make bid":
            makeBid();
            break;
        case "list bids":
            listBids();
            break;
        case "list auctions":
            listAuctions();
            break;
        case "close auction":
            closeAuction();
            break;
        default:
            getFailedAttempt();
        }
    }

    // registers name and breed in separate controll methods, rest is read in the same block
    private void registerNewDog() {

        String name = registerDogName();
        String breed = registerDogBreed();
        System.out.print("Age?> ");
        int age = methodAccessor.readInt();
        System.out.print("weight?> ");
        int weight = methodAccessor.readInt();
       
        System.out.printf("%s added to the register\n", name);
        Dog newDog = new Dog(name, breed, age, weight, null);
        listOfDogs.add(newDog);
    }

    // make a empty list control and continue to list dogs if false
    private void listDogs() {
        if (listOfDogs.isEmpty()) {
            System.out.println("Error: no dogs in register");
            return;
        }
        displayDogs();
    }
    // display dogs in ordered sequence 
    private void displayDogs() {
        final double nolistBase = 0.0;
        System.out.print("Smallest tail length to display?> ");
        double listBase = methodAccessor.readDouble();
        System.out.print("The following dogs has such a large tail:\n");
        //sorts the dogs before printout.
        sortDogs();
        // go trough each object trough loop depending of controll, and print out name
        for (Dog newDog : listOfDogs) {
            if (listBase == nolistBase || listBase <= newDog.getTailLength()) {
                if (newDog.getOwner() != null) {
                    System.out.printf("* %s (%s, %d years, %d kilo, %.1f cm tail, owned by %s)\n", newDog.getName(),
                            newDog.getBreed(), newDog.getAge(), newDog.getWeight(), newDog.getTailLength(),
                            newDog.getOwner());
                } else {
                    System.out.printf("* %s (%s, %d years, %d kilo, %.1f cm tail)\n", newDog.getName(),
                            newDog.getBreed(), newDog.getAge(), newDog.getWeight(), newDog.getTailLength());
                }
            }
        }
    }

    // increases the age of the given dog
    private void increaseAge() {
        boolean isNotFound = false;
        int counter;
        if (listOfDogs.isEmpty()) {
            System.out.println("Error: the list is empty");
            return;
        }
        System.out.print("Enter the name of the dog ?> ");
        String givenName = methodAccessor.readString();
        givenName = givenName.trim();//removes blank spaces in between the words and is saved inside a variable. Trim is based on the java documentation.
        //converts first letter to upperCase and and rest remains lower case.
        givenName = givenName.substring(0, 1).toUpperCase() + givenName.substring(1).toLowerCase();
        for (counter = 0; counter < listOfDogs.size(); counter++) {
            Dog currentDog = listOfDogs.get(counter);
            if (currentDog.getName().equalsIgnoreCase(givenName)) {
                currentDog.increaseDogAge(1);
                System.out.printf("%s is now one year older \n", currentDog.getName());
                return;
            }
        }
        isNotFound = true;
        if (isNotFound) {
            System.out.println("Error: no such dog");
        }
    }

    // prints out default case of switch
    private void getFailedAttempt() {
        System.out.println("Error: Not a command!");
        return;
    }

    // Fing dog in array
    //Disk handledning 2020-01-16 11:40
    private Dog findDog(String dogToFind){
        for (Dog dog : listOfDogs){
            if (dog.getName().equalsIgnoreCase(dogToFind)){
                return dog;
            }
        }
        return null;
    }

    // better way of finding user in array compared to previous attempt.
    private User findUser(String userToFind) {
        for (User user : listOfUsers) {
            if (user.getName().equalsIgnoreCase(userToFind)) {
                return user;
            }
        }
        return null;
    }

    // get dog from list to be assgined to an owner of type User
    private void giveDog() {
        Dog dog;
        User user;
        //asks for the name of the dog and checks the return value.
        dog = displayDogQuestion();
        if(dog == null){
            System.out.println("Error: no dog with that name");
            return;
        }
        if (dog.getOwner() != null) {
            System.out.println("Error: the dog already has an owner");
            return;
        }
        //Asks for the name of the user and checks the return value.
        user = displayUserQuestion();
        if(user == null){
            System.out.println("Error: no such user");
            return;
        }
        //assigns the dog to the owner and displays the values in console. 
        dog.assignOwner(user);
        user.addUserDog(dog, user);
        System.out.printf("%s now owns %s \n", user.getName(), dog.getName());
    }
    
    //Removes the given dog from the list of dogs, and displays values in console.
    private void removeDog(){
        Dog currentDog;
        
        if(listOfDogs.isEmpty()) {//execute if list is empty
            System.out.println("Error: the list is empty");
            return;
        }
        currentDog = displayDogQuestion();
        if(currentDog == null){//execute if the returned dog is null 
            System.out.print("Error: no such dog");
            return;
        }
        if(currentDog.getOwner() != null){//Remove dog from the the users owned dogs
            currentDog.getOwner().removeUserDog(currentDog);
        }
        removeDogInAuction(currentDog);//remove the removed dog from the arraylist.
        listOfDogs.remove(currentDog);
        System.out.printf("%s is removed from the register\n", currentDog.getName());

    }
    //remove given dog from an existing auction.
    private void removeDogInAuction(Dog dog){
        for(int i = 0; i < auctions.size(); i++){
            Auction currentAuctionDog = auctions.get(i);
            if(currentAuctionDog.getAuctionDog().getName() == dog.getName()){
                auctions.remove(i);
            }
        }
    }
    //removes user from the list of users and the users dogs, and displays values in console. 
    private void removeUser() {
        User user;

        if(listOfUsers.isEmpty()){//execute if the list of dogs is empty.
            System.out.println("Error: the list is empty");
            return;
        }
        user = displayUserQuestion();
        if(user == null){//execute if the returned user is empty.
            System.out.println("Error: no such user");
            return;
        }
        for(Auction auction : auctions){//remove each bid the user has put for auction
            auction.sortBids();
            auction.removeUserBids(user);
        }
        for(int i = 0; i < listOfDogs.size(); i++){//remove the current user together with its dogs in the list if it matches the given user. 
            if(listOfDogs.get(i).getOwner() == user){
                listOfDogs.remove(i);
            }
        }
        listOfUsers.remove(user);
        System.out.printf("%s has been removed from the list\n", user.getName());
    }

    //gets return value from dog question and passes it through controll. Then checks for e
    private void listBids(){
        Dog dog;
        Auction foundAuction;
        
        
        //1st controll
        dog = displayDogQuestion();
        foundAuction = findDogAuction(dog);
        if(foundAuction == null){
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        //2nd controll
        for(Auction auction : auctions){
            if(auction.getAuctionDog() == dog){
                if(auction.getBidsSize() <= 0){
                    System.out.println("No bids registered yet for this auction");
                    return;
                }
            }
        }
        //list bids
        System.out.println("Here are the bids for this auction");
        for(Auction auction : auctions){
            if(auction.getAuctionDog() == dog){
                auction.listDogBids();
            }
        }
    }
    
    //ask dog name and check if it exist to create and auction for the dog.
    private void startAuction(){
        Dog dog;
       
        dog = displayDogQuestion();
        if(dog == null){
            System.out.println("Error: no such dog");
            return;
        }
        User isOwned = checkIfOwned(dog);
        Auction auctionExists = checkDogAuction(dog); 
        if(isOwned != null){
            System.out.println("Error: this dog is already owned");
            return;
        }
        if(auctionExists != null){
            System.out.println("Error: this dog is already up for auction");
            return;
        }
        createAuction(dog);
    }
    
    //Creates a new object of auction.
    private void createAuction(Dog dog){
        Auction auction;
        Auction dogAuction;

        dogAuction = checkDogAuction(dog);
        if(dogAuction == null){
            universalCounter++;
            auction = new Auction(dog, universalCounter, 0);
            auctions.add(auction);
            System.out.printf("%s has been put up for auction in auction #%d\n", dog.getName(), universalCounter);
        }
    }

    //close the auction for the given dog and assign it to the top bidder.
    private void closeAuction(){
        Dog dog;

        dog = displayDogQuestion();
        processCloseAuction(dog);
    }
    //shrinked cheking process of the close auction method.
    private void processCloseAuction(Dog dog){
        for(Auction auction : auctions){
            if(auction.getAuctionDog() == dog){
                if(auction.getBidsSize() == 0){
                    System.out.printf("The auction is closed. No bids where made for %s", auction.getAuctionDog().getName());
                    return;
                }
                Bid bid = auction.findDogBid(dog);
                dog.assignOwner(bid.getbidOwner());
                bid.getbidOwner().addUserDog(dog, bid.getbidOwner());
                System.out.printf("The auction is closed. The winning bid was %dkr and was made by %s\n", bid.getBidAmount(), bid.getbidOwner());
                auctions.remove(auction);
                return;
            }
            else if(findDogAuction(dog) == null){
                System.out.println("Error this dog is not for auction");
                return;
            }
        }
    } 
   
    //check for owner dog
    private User checkIfOwned(Dog dog){
        User user = dog.getOwner();
        if(user != null){
            return user;
        }
        return null;
    }
    //checks for a existing dog auction.
    private Auction checkDogAuction(Dog dog){
        for(Auction dogAuction : auctions){
            if(dogAuction.getAuctionDog() == dog){
                System.out.println("Error: this dog is already up for auction");
                return dogAuction;
            }
        }
        return null;
    }
    //creates a new bid on given dog by given user and replaces the old bid with the new bid.
    private void makeBid(){
        User user;
        Dog dog;
        int amount;
        Auction foundAuction;
        user = askForUser();
        if(user == null){
            return;
        }
        dog = displayDogQuestion();
        foundAuction = checkAuction(dog);
        amount = bidAmount(foundAuction);
        foundAuction.makeBids(dog, user, amount);
    }

    private Auction checkAuction(Dog dog){
        Auction foundAuction;
        foundAuction = findDogAuction(dog);
        if(foundAuction == null){
            System.out.println("Error: this dog is not up for auction");
            return null;
        }
        return foundAuction;
    }

    private User askForUser(){
        User user;
        user = displayUserQuestion();
        if(user == null){
            System.out.println("Error: no such user");
            return null;
        }
        return user;
    }

    private Auction findDogAuction(Dog dog){
        for(Auction auction : auctions){
            if(auction.getAuctionDog() == dog){
                return auction;
            }
        }
        return null;
    }

    //Checks if the amount to give is not equal to the previous, and returns the new higher amount to bid.
    private int bidAmount(Auction auction){
        int amount;
        int bidAmount;
        bidAmount = auction.getHighestBid();
        do{
            System.out.printf("Amount to bid (min %d)?>", bidAmount + 1);
            amount = methodAccessor.readInt();
            if(amount < bidAmount + 1){
                System.out.println("Error: too low bid!");
            }
        }while(amount < bidAmount + 1);
        auction.setNewHighestBid(amount);
        System.out.println("new highest amount "+ amount);
        return amount;
    }

    //checks for username and passes it over for searching in list of users and returns a value.
    private User displayUserQuestion() {
        User user;
        String userName;

        do {
            System.out.print("Enter the name of the User?> ");
            userName = methodAccessor.readFormatedString();
            if (userName.equals("")) {
                System.out.println("Error: the name can't be empty");
            }
        } while (userName.equals(""));
        user = findUser(userName);
        return user;
    }
    
    //checks for the name of the dog and passes it over for searching in the list of dogs 
    private Dog displayDogQuestion() {
        Dog dog;
        String dogName;

        do{
            System.out.print("Enter the name of the dog?> ");
            dogName = methodAccessor.readFormatedString();
            if (dogName.equals("")) {
                System.out.println("Error: the name can't be empty");
            }
        }while (dogName.equals(""));
        dog = findDog(dogName);
        return dog;
    }

    //asks for the name of the dog, formats it and returns it.
    private String registerDogName() {
        System.out.print("Name?> ");
        String name = methodAccessor.readString();
        if (name.replaceAll(" ", "").equals("")) {
            System.out.println("Error: the name can't be empty");
            registerDogName();
        } else {
            name = name.trim();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return name;
    }

    //asks for the breed of the dog, formats it and returns it.
    private String registerDogBreed() {
        System.out.print("Breed?> ");
        String breed = methodAccessor.readString();

        if (breed.replaceAll(" ", "").equals("")) {
            System.out.println("Error: the name can't be empty");
            registerDogBreed();
        } else {
            breed = breed.substring(0, 1).toUpperCase() + breed.substring(1).toLowerCase();
        }
        return breed;
    }
    //asks for the name of user and saves it to a new obejct to be added to the list of users. 
    
    private void addUser() {
        String name = registerUserName();
        User newUser = new User(name);
        System.out.printf("%s added to the register\n", name);
        listOfUsers.add(newUser);
    }

    //asks for name input, formats it and returns it.
    private String registerUserName() {
        System.out.print("Name?> ");
        String name = methodAccessor.readString();
        if (name.replaceAll(" ", "").equals("")) {
            System.out.println("Error: the name can't be empty");
            addUser();
        } else {
            name = name.trim();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return name;
    }

    //checks if the list of users is empty and displays each interation in the list to the console.
    private void listUsers() {
        if (listOfUsers.isEmpty()) {
            System.out.println("Error: no users in register");
            return;
        }
        for (User user : listOfUsers) {
            user.listOwnedDogs();
        }
    }
    
    //Checks if the list of auctions is empty and displays each iteration in the list to the console.
    private void listAuctions(){
        if(auctions.isEmpty()){
            System.out.print("Error: no auctions in progress\n");
            return;
        }
        for(Auction auction : auctions){
            auction.listAuctionBids();
        }
    }

    //sort arraylist thrugh bubblesorting. Code based on https://www.geeksforgeeks.org/bubble-sort/
    //This algorithm is also explained on Harvards course CS50
    
    //Handledning disk 16/1/2020 11:23.
    private void sortDogs() {
        // Här ska du implementera sorteringsalgoritmen
        int n = listOfDogs.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listOfDogs.get(j).getTailLength() > listOfDogs.get(j + 1).getTailLength()) {
                    // swap temp and arr[i]
                    Dog temp = listOfDogs.get(j);
                    // arr[j] = arr[j+1];
                    listOfDogs.set(j, listOfDogs.get(j + 1));
                    listOfDogs.set(j + 1, temp);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listOfDogs.get(j).getTailLength() == listOfDogs.get(j + 1).getTailLength()) {
                    if (listOfDogs.get(j).getName().compareTo(listOfDogs.get(j + 1).getName()) > 0) {

                        // swap temp and arr[i]
                        Dog temp = listOfDogs.get(j);
                        listOfDogs.set(j, listOfDogs.get(j + 1));
                        listOfDogs.set(j + 1, temp);
                    }
                }
            }
        }
    }
    //Exits the program
    private void exit() {
        System.out.println();
        System.out.println("Good Bye!");
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.startProgram();
    }
}