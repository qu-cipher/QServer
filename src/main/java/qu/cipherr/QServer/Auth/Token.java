package qu.cipherr.QServer.Auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Token {
    protected String token;

    public Token(){
        token = new TokenGenerator().generate().getToken();
    }

    public Token getToken(){
        return this;
    }

    private static class TokenGenerator{
        private String result;
        private TokenGenerator generate() {
            try {
                SecureRandom secureRandom = new SecureRandom();
                byte[] randomBytes = new byte[64];

                secureRandom.nextBytes(randomBytes);

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = digest.digest(randomBytes);

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
                throw new RuntimeException("SHA-256 algorithm not found.", e);
            }

            return this;
        }

        private String getToken() {
            return this.result;
        }
    }
}