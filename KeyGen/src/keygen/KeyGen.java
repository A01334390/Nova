/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keygen;

/**
 *
 * @author Luna
 */
public class KeyGen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        decryptString(encryptString());
    }

    public static void createKeys() {
        String publicKeyFilename = null;
        String privateKeyFilename = null;

        GenerateRSAKeys generateRSAKeys = new GenerateRSAKeys();

        publicKeyFilename = "pub";
        privateKeyFilename = "priv";
        generateRSAKeys.generate(publicKeyFilename, privateKeyFilename);
    }

    public static String encryptString() {
        String publicKeyFilename = null;
        String inputData = null;

        RSAEncryption rsaEncryption = new RSAEncryption();

        publicKeyFilename = "pub";
        inputData = "B";
        return rsaEncryption.encrypt(publicKeyFilename, inputData);
    }
    
    public static void decryptString(String encryptData){
        
        String privateKeyFilename = null;
        String encryptedData = null;

        RSADecryption rsaDecryption = new RSADecryption();

        privateKeyFilename = "priv";
        encryptedData = encryptData;
        rsaDecryption.decrypt(privateKeyFilename, encryptedData);

    }

}
