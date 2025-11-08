//Official Blockchain structure for QuantChain 
//Initialized on November 7th 2025 at 18:43 CST
//© MK 2025
import java.time.*;
import java.security.*;
import java.util.*;
import java.io.*;

public class QCDataChain implements Serializable{
  private static final long serialVersionUID=1L;
  protected ArrayList<Block> blockChain;
  public static final String CHAINVERSION="QuantChain V1";
  protected final String creator;
  protected boolean valid;
  protected ZonedDateTime createdAt;
  protected String chainHash;

  
  public QCDataChain(String creatorName){
    creator=creatorName;
    blockChain=new ArrayList<Block>();
    createdAt=ZonedDateTime.now(ZoneId.of("America/Chicago"));
    valid=true;
    chainHash="0";
  }

  //add a block to the end
  public void addBlock(Block nb){
    if(!blockChain.isEmpty()){
      nb.setPrevHash(nb.getHash());
    }
      blockChain.add(nb);
      chainHash=nb.getThisHash();
  }

  //get most recently made block
  public Block getLatestBlock(){
    if(!blockChain.isEmpty()){
      return blockChain.get(blockChain.size()-1);
    }
    return null;
  }

  //get block at a certain index
  public Block getBlock(int ind){
    if (ind < 0 || ind >= blockChain.size()) 
      return null;
    return blockChain.get(ind);
  }

  //returns size of the chain
  public int getSize(){
    return blockChain.size();
  }

  //determines validity of the chain for security purposes
  public boolean isChainValid(){
    for(int i=1; i<blockChain.size(); i++){
      boolean hashValidity=blockChain.get(i).getPrevHash().equals(blockChain.get(i-1).getThisHash());
      boolean blockValidity=blockChain.get(i).isValidBlock();
      if(!blockValidity||!hashValidity){
        System.out.println("Invalid link between block "+(i-1)+" and "+i);
        valid=false;
        return false;
      }
    }
    valid=true;
    return true;
  }

  //string representation of the whole chain

  public String getSummary() {
      StringBuilder sb = new StringBuilder();
      sb.append("=== ").append(CHAINVERSION).append(" Summary ===\n")
        .append("Creator: ").append(creator).append("\n")
        .append("Created At: ").append(createdAt).append("\n")
        .append("Version: ").append(CHAINVERSION).append("\n")
        .append("Valid: ").append(valid).append("\n")
        .append("Total Blocks: ").append(getSize()).append("\n\n");

      for (int i = 0; i < getSize(); i++) {
          sb.append("Block ").append(i + 1).append(":\n")
            .append(blockChain.get(i).toString()).append("\n\n");
      }
      return sb.toString();
  }


  //save info to a file
  public void saveToFile(String fileName){
    try{
    ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream(fileName));
      o.writeObject(this);
      System.out.println("Successfully saved information");
    }
    catch (IOException n){
      System.out.println("Couldn't Save Data");
    }
  }

  public static QCDataChain loadFromFile(String fileName) {
      try  {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName);
          return (QCDataChain) ois.readObject();
      } catch (IOException e) {
          System.out.println(" Couldn't load chain: " + e.getMessage());
          return null;
      }
  }

  
}
