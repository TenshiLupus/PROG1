package Kod;

import java.util.Arrays;

/**
 * @author Angel Cardenas Martinez anca8079
 */

public class Auction{
    private static final int HIGHEST_BIDS = 3;
    private int auctionNumber;
    private int highestBid;
    private Dog auctionDog = new Dog(null, null, 0, 0, null);
    private Bid[] listOfBids = new Bid[0];

    public Auction(Dog auctionDog,int auctionNumber, int highestBid){
        this.auctionDog = auctionDog;
        this.auctionNumber = auctionNumber;
        this.highestBid = highestBid;
    }

    //returns the dog object in auction
    public Dog getAuctionDog(){
        return auctionDog;
    }

    //returns the number of the auction
    public int getAuctionNumber(){
        return auctionNumber;
    }

    public int getHighestBid(){
        if(listOfBids.length == 0){
            return 0;
        }
        return highestBid;
    }

    public void setNewHighestBid(int newAmount){
        this.highestBid = newAmount;
    }

    //adds a new bid to the list of bids in the auction
    private void addAuctionBid(Bid bid){
        Bid b = bid;
        listOfBids = Arrays.copyOf(listOfBids, listOfBids.length + 1);
        listOfBids[listOfBids.length - 1] = b;
    }

    //Sorts the list of bids, gives the values to format and calls the toString method of each bid in the list, as well as the 3 highest bids if the list contains more than 3.
    public void listAuctionBids(){
        if(listOfBids.length <= HIGHEST_BIDS){
            sortBids();
            System.out.printf("Auction #%d: %s", getAuctionNumber(), getAuctionDog().getName() + ". Top bids: " + Arrays.deepToString(listOfBids));
            System.out.println();
        }
        if(listOfBids.length >= HIGHEST_BIDS){
            sortBids();
            System.out.printf("Auction #%d: %s", getAuctionNumber(), getAuctionDog().getName() + ". Top bids: " + (Arrays.deepToString(Arrays.copyOf(listOfBids, HIGHEST_BIDS))));
            System.out.println();
        }
    }

    //Iterates through the list of bids calling the to String method of the bid and displays them vertically.
    public void listDogBids(){
        sortBids();
        for(Bid currentBid : listOfBids){
            System.out.println(currentBid.toString());
        }
    }   
    //returns the size of the list of bids.
    public int getBidsSize(){
       return listOfBids.length;
    }

    public void makeBids(Dog dog, User user, int amount){
        //set the the new bid amount to existing bid.
        if(getBidsSize() > 0){
            for(int i = 0; i < listOfBids.length; i++){
                Bid currentBid = listOfBids[i];
                if(currentBid.getbidOwner() == user){
                    currentBid.setNewBidAmount(amount);
                    return;
                }
            }
        }
        //creates a new bid for auction.
        Bid newbid = new Bid(amount, user, dog);
        if(getAuctionDog() == dog){
            addAuctionBid(newbid);
        }
        System.out.println("bid made");
    }

    //searches for a bid of the corresponding dog.
    public Bid findDogBid(Dog dog){
        for(int i = 0; i < listOfBids.length; i++){
            Bid currentBid = this.listOfBids[i];
            if(currentBid.getBidDog() == dog){
                return currentBid;
            }
        }
        return null;
    }
    
    //structure of code based on https://www.geeksforgeeks.org/bubble-sort/
    public void sortBids(){
        //set local array to instance
        int n = listOfBids.length;//assign array length to local variable
        for (int i = 0; i < n - 1; i++){//go trough first line of loop
            for(int j = i + 1; j < n; j++){
                if(n > 1){
                    if(listOfBids[i].getBidAmount() < listOfBids[j].getBidAmount()){//check if current array is less than the next array. 
                        // swap arr[i+1] and arr[i] 
                        Bid temp = listOfBids[i]; //assign lesser value to temporary variable.
                        listOfBids[i] = listOfBids[j];// assign the higher value to the index of the lesser value
                        listOfBids[j] = temp;//assign lesser value to the index of the previous highest position.
                    }
                }
            }  
        }
    }

    //get bid by owner and compare the bid in auctions to delete the owner.
    public void removeUserBids(User user){
        sortBids();
        if(listOfBids.length != 0){
            for(int i = 0; i < listOfBids.length - 1; i++){
                if(listOfBids[i].getbidOwner() == user){
                    listOfBids[i] = listOfBids[i + 1];// writes over the value of the next position on to the current position.
                }
            }
        }
        if(listOfBids.length != 0){
            listOfBids = Arrays.copyOf(listOfBids, listOfBids.length - 1);//makes a new array and removes the element in the new array by copying an array with one less space.
        }
        System.out.println("New list of array " + listOfBids.length);
        if(listOfBids.length >= 1){   
            setNewHighestBid(listOfBids[0].getBidAmount());
        }
    }
}
            