
import java.time.*;
import java.security.*;
import java.util.*;

public class Block {
  private final ZonedDateTime tStamp;
  private final String prevHash;
  private String thisHash;
  private ArrayList<Transaction> transacts;
  private StringBuilder transString;
  private boolean isSealed;
  private int index;
  protected String merkleRoot;
  // Constructor for an empty block (e.g., Genesis or placeholder)
  public Block(String prevHash) {
    this.index=0;
    this.isSealed = false;
    this.transacts = new ArrayList<>();
    this.prevHash = prevHash;
    this.thisHash = "";
    this.tStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    this.transString = new StringBuilder();
    this.merkleRoot=HelperMethods.getMerkleRoot(transacts);
  }

  // Constructor with transactions
  public Block(String prevHash, ArrayList<Transaction> t, int index) {
    this.index=index;
    this.isSealed = false;
    this.prevHash = prevHash;
    this.transacts = new ArrayList<>(t);
    this.tStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    this.transString = new StringBuilder();
    this.merkleRoot=HelperMethods.getMerkleRoot(transacts);

    for (Transaction tx : transacts) {
      transString.append(tx.getHash()).append(" ");
    }

    this.thisHash = HelperMethods.convertToHash(
      prevHash + tStamp.toString() + transString.toString() + index
    );
  }

  // --- Getters ---
  public String getThisHash() {
    return thisHash;
  }

  public String getPreviousHash() {
    return prevHash;
  }

  public List<Transaction> getTransacts() {
    return Collections.unmodifiableList(transacts);
  }

  public ZonedDateTime getTimeStamp() {
    return tStamp;
  }

  public String getTransString() {
    return transString.toString();
  }

  // --- Core Methods ---
  public void sealBlock() {
    isSealed = true;
  }

  public boolean isSealed() {
    return isSealed;
  }

  public void addTransaction(Transaction t) {
    if (isSealed) {
      System.out.println("You can't add more transactions! Block is sealed.");
      return;
    }

    transacts.add(t);
    transString.append(t.getHash()).append(" ");
    thisHash = HelperMethods.convertToHash(
      prevHash + tStamp.toString() + transString.toString() + index
    );

    System.out.println("Successfully added transaction! ~uwu~");
  }

  // Verify all transactions and ensure block hash integrity
  public boolean validateBlock() {
    String recalculatedHash = HelperMethods.convertToHash(
      prevHash + tStamp.toString() + transString.toString()
    );
    if (!recalculatedHash.equals(thisHash)) return false;

    for (Transaction tx : transacts) {
      if (!tx.isValid()) return false;
    }
    return true;
  }
}
