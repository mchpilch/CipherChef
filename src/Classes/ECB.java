package Classes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ECB  {

    private String outputString;
    private String inputString;
    private byte[] outputBytes;
    private byte[] inputBytes;
    private String keyString;
    private byte[] keyBytes;

    public void encrypt()
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        setOutputBytes(cipher.doFinal(inputBytes));
    }

    public void decrypt()
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        setOutputBytes(cipher.doFinal(inputBytes));
    }

    public String getOutputString() {
        outputString = new String(outputBytes, StandardCharsets.UTF_8);
        return outputString;
    }

    public String getKeyString() {
        keyString = new String(keyBytes, StandardCharsets.UTF_8);
        return keyString;
    }

    public void setOutputBytes(byte[] outputBytes) {
        this.outputBytes = outputBytes;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
        this.inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
        this.keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
    }
}
