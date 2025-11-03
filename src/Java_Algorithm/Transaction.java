
import java.time.*;
import java.security.*;
import java.util.*;

 class Transaction{
  private String sender;
  private String receiver;
  private double amount;
  private ZonedDateTime timeStamp;
  private String hash;
  private int height;

  Transaction(String sender, String receiver, double amount, int height){
    this.sender=sender;
    this.receiver=receiver;
    this.amount=amount;
    timeStamp=ZonedDateTime.now(ZoneId.of("America/Chicago"));
    hash=HelperMethods.convertToHash(sender+receiver+Double.toString(amount)+timeStamp);
    this.height=height;
  }

   public String getSender(){
     return sender;
   }

   public String getReceiver(){
      return receiver;
    }

   public double getAmount(){
     return amount;
   }

   public ZonedDateTime getTimeStamp(){
     return timeStamp;
   }
   public String getHash(){
     return hash;
   }
   public boolean isValid(){
     boolean legitHash=HelperMethods.convertToHash(sender+receiver+Double.toString(amount)+timeStamp).equals(hash);
     if(sender.equals("")||receiver.equals("")||sender.equals(receiver)||amount<0||(!legitHash)){
       amount=0;
       return false;
     }
     return true;
   }

   public String toString(){
     return sender+" sent "+amount+" to "+receiver+" at "+timeStamp;
   }
   @Override
   public boolean equals(Object o){
     if (o==null || getClass() != o.getClass()){
       return false;
     }
     Transaction t=(Transaction) o;
     return this.hash.equals(t.getHash());
   }

   @Override
   public int hashCode(){
     return hash.hashCode();
   }

   public String transactId(){
     return hash.substring(0,12);
   }

   public double getFee(){
     return amount*0.05;
   }
}
