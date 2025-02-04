import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class RSAKeyGenerator {

    public static void main(String[] args) {
        try {
            // Generate RSA key pair
            KeyPair keyPair = generateRSAKeyPair(2048); // 2048-bit RSA key

            // Save the private and public keys as PEM files
            savePrivateKeyToPEM(keyPair.getPrivate(), "privateKey.pem");
            savePublicKeyToPEM(keyPair.getPublic(), "publicKey.pem");

            System.out.println("RSA key pair generated and saved as privateKey.pem and publicKey.pem");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate an RSA key pair with the specified key size (e.g., 2048 bits)
    public static KeyPair generateRSAKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    // Method to save the private key as a PEM file
    public static void savePrivateKeyToPEM(PrivateKey privateKey, String fileName) throws IOException {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        String privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" 
                + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()) 
                + "\n-----END PRIVATE KEY-----";

        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            fileWriter.write(privateKeyPEM);
        }
    }

    // Method to save the public key as a PEM file
    public static void savePublicKeyToPEM(PublicKey publicKey, String fileName) throws IOException {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" 
                + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()) 
                + "\n-----END PUBLIC KEY-----";

        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            fileWriter.write(publicKeyPEM);
        }
    }
}
