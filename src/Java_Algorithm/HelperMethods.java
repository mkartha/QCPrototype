import java.time.*;
import java.security.*;
import java.util.*;
import java.nio.charset.StandardCharsets;


public class HelperMethods{
  public static String convertToHash(String input){
  StringBuilder hexS=new StringBuilder();
  try{
  MessageDigest dig=MessageDigest.getInstance("SHA-256");
  byte[] hashB=dig.digest(input.getBytes(StandardCharsets.UTF_8));

  for(byte b: hashB){
    hexS.append(String.format("%02x",b));
  }
  }
  catch(NoSuchAlgorithmException e){
    throw new RuntimeException("SHA-256 algorithm not found", e);
  }
  return hexS.toString();
}
  public String getMerkleRoot(ArrayList<Transaction> Transacts){
    ArrayList<Transaction> tCopy=new ArrayList<Transaction>(Transacts);
  
    ArrayList<String> hashCopies=new ArrayList<String>();
    for(Transaction t: tCopy){
      hashCopies.add(t.getHash());
    }
    while(hashCopies.size()>1){
      ArrayList<String> temp=new ArrayList<>();
      if(hashCopies.size()%2==1){
        hashCopies.add(hashCopies.get(hashCopies.size()-1));
      }
      for(int j=0; j<hashCopies.size()-1; j+=2){
        String te=convertToHash(hashCopies.get(j)+hashCopies.get(j+1));
        temp.add(te);
      }
      hashCopies=temp;
    }
    return hashCopies.get(0);
  }
}
