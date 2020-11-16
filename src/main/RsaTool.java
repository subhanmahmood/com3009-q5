package main;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;

import javax.crypto.*;

import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

public class RsaTool {
    private String mod;
    private String pubExp;
    private BigInteger modulus;
    private BigInteger pubExponent;
    private RSAPublicKey key;
    private Cipher cipher;

    public RsaTool(String mod, String pubExp) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        this.mod = mod;
        this.pubExp = pubExp;

        this.modulus = new BigInteger(mod);
        this.pubExponent = new BigInteger(pubExp);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(this.modulus, this.pubExponent);
        this.key = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        this.cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);

    }

    public byte[] encrypt(String plaintext) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        return cipher.doFinal(plaintext.getBytes("UTF-8"));
    }
}
