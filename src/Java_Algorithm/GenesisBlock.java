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
  public GenesisBlock(String creatorName) {
        super("0", new ArrayList<>()); 
        this.uName = (creatorName == null || creatorName.isBlank()) ? "Unknown": creatorName;
        this.gtStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        this.data = String.format("QC Genesis Block initialized by [%s] on %s", this.uName, gtStamp.toString());
        this.pubKey = generatePublicKeyBase64();
    
    public GenesisBlock(String creatorName, ArrayList<Transaction> txs) {
        super("0", txs == null ? new ArrayList<>() : new ArrayList<>(txs));
        this.uName = (creatorName == null || creatorName.isBlank()) ? "Unknown" : creatorName;
        this.gtStamp = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        this.data = String.format("QC Genesis Block initialized by [%s] on %s", this.uName, gtStamp.toString());
        this.pubKey = generatePublicKeyBase64();
    }

  
public string gBlockInfo(){
  return data;
}

public string getVersion(){
  return NVERSION;
}

public String getCreatorSig() {
        return CREATORSIG;
}

public ZonedDateTime getGenesisTimestamp() {
        return gtStamp;
}
  
public static String genPubKey(){
 KeyPairGenerator kGen=KeyPairGenerator.getInstance("RSA");

keyGen.initialize(2048, new SecureRandom());
KeyPair pair=kGen.generateKeyPair();

PublicKey pKey=pair.getPublic();
return Base64.getEncoder().encodeToString(pKey.getEncoded());
}
}
