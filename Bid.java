package Kod;

/**
 * @author Angel Cardenas Martinez anca8079
 */

public class Bid{

    private int bidAmount;
    private User bidOwner = new User(null);
    private Dog bidDog = new Dog(null, null, 0, 0, null);

    public Bid(int bidAmount, User bidOwner, Dog bidDog){
        this.bidAmount = bidAmount;
        this.bidOwner = bidOwner;
        this.bidDog = bidDog;
    } 

    //returns the owner of the bid. 
    public User getbidOwner(){
        return this.bidOwner;
    }
    //returns the dog that has been bided
    public Dog getBidDog(){
        return this.bidDog;
    }
    //returns the amount of the bid.
    public int getBidAmount(){
        return this.bidAmount;
    }
    //Assign the passed value into the bid amount.
    public void setNewBidAmount(int newamount){
        System.out.println(this.bidAmount);
        this.bidAmount = newamount;
        System.out.println(this.bidAmount);
        System.out.println("bid made");
    }
    //Displays the bid amount of the bids owner.
    @Override
    public String toString(){
        return String.format("%s %d kr",this.getbidOwner().getName(), this.getBidAmount());
    }
}