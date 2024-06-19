package qu.cipherr.QServer.Auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UID {
    protected String uid;

    public UID(){
        this.uid = new UidGenerator().generate().getUid();
    }

    public UID getUID(){
        return this;
    }

    private static class UidGenerator {
        private String result;
    
        private UidGenerator generate() {
            try {
                SecureRandom random = new SecureRandom();
                byte[] randomBytes = new byte[64];
                random.nextBytes(randomBytes);
    
                MessageDigest digest = MessageDigest.getInstance("SHA-512");
                digest.update(randomBytes);
                byte[] hashBytes = digest.digest();
    
                StringBuilder hexString = new StringBuilder();
                for (byte hashByte : hashBytes) {
                    String hex = Integer.toHexString(0xff & hashByte);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
    
                this.result = hexString.toString();
    
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-512 algorithm not found.", e);
            }
    
            return this;
        }
    
        private String getUid() {
            return this.result;
        }
    }
}
