import java.time.*;
import java.security.*;
import java.util.*;

public class GenesisBlock extends Block {
  private String data;
  private String uName;
  private ZonedDateTIme gtStamp;
  private final String  CREATORSIG=HelperMethods.convertToHash("mkDLPMSA");
  private final String  NVERSION="QuantChain v1.0"
  public final String pubKey;
  // Constructor for an empty block (e.g., Genesis or placeholder)
  public GenesisBlock(String creatorName) {
    super("", new ArrayList<Transaction>, 0, uName); 
    this.gtStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    this.uName=uName;
    data="QC Genesis Block Initialized By ["+uName+"] on "+gtStamp+".";
  }

  // Constructor with transactions
  public GenesisBlock(String prevHash, ArrayList<Transaction> t) {
    super(prevHash, t, 0);
    this.gtStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    this.uName=uName;
    data="QC Genesis Block Initialized By ["+uName+"] on "+gtStamp+".";
}

  
public string gBlockInfo(){
  return data;
}

public string getVersion(){
  return NVERSION;
}

public static boolean isPrimeBruteForce(int number) {
    for (int i = 2; i < number; i++) {
        if (number % i == 0) {
            return false;
        }
    }
    return true;
}
 public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
  
public static String genPubKey(){
  int p=(int) (Math.random()*1696)+1338;
  while (!isPrimeBruteForce(p){
    p=(int) (Math.random()*1696)+1338;
}
  int q=(int)  (Math.random()*1696)+1338;
  while(!isPrimeBruteForce(q)||(q==p)){
    q=(int)  (Math.random()*1696)+1338;
  }
  int n=p*q;
  int aln=(p-1)*(q-1);
  int e=(Math.random()*144000)+14949;
  while(gcd(aln,e)!=1){
    int e=(Math.random()*144000)+14949;
  }
  return n+","+e;
}
