
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class Encryption{

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128; 
    private static final int BUFFER_SIZE = 8192; 


    public static void main(String args[]){
        // System.out.println("hiii");

        try{
            SecretKey secretKey = generateSecretKey();
            String inputFile = "C:\\Users\\NACHIKET\\Desktop\\Encrypt\\target\\classes\\Input.txt";
            String encryptedFilePath = "C:\\Users\\NACHIKET\\Desktop\\Encrypt\\target\\classes\\encrypt.enc";
            String decryptedFilePath = "C:\\Users\\NACHIKET\\Desktop\\Encrypt\\target\\classes\\decrpt.txt";

              // Encrypt-------
              encryptFile(secretKey, inputFile, encryptedFilePath);

              // Decrypt ---------------
              decryptFile(secretKey, encryptedFilePath, decryptedFilePath);

              System.out.println("Encryption and decryption completed successfully.");
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    private static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE, new SecureRandom());
        return keyGen.generateKey();
    }



    private static void encryptFile(SecretKey secretKey, String inputFilePath, String outputFilePath) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }


    private static void decryptFile(SecretKey secretKey, String inputFilePath, String outputFilePath) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}