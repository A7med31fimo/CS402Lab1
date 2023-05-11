import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private static final int PRIME_CERTAINTY = 64;

    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger modulus;

    public RSA(int keySize) {
        generateKeys(keySize);
    }

    public String encrypt(String plaintext) {
        BigInteger message = new BigInteger(plaintext.getBytes());
        BigInteger ciphertext = message.modPow(publicKey, modulus);
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        BigInteger message = new BigInteger(ciphertext);
        BigInteger plaintext = message.modPow(privateKey, modulus);
        return new String(plaintext.toByteArray());
    }

    private void generateKeys(int keySize) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(keySize / 2, random);
        BigInteger q = BigInteger.probablePrime(keySize / 2, random);
        while (!isPrime(p, PRIME_CERTAINTY)) {
            p = BigInteger.probablePrime(keySize / 2, random);
        }
        while (!isPrime(q, PRIME_CERTAINTY)) {
            q = BigInteger.probablePrime(keySize / 2, random);
        }
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537); // A common choice for e
        BigInteger d = e.modInverse(phi);
        publicKey = e;
        privateKey = d;
        modulus = n;
    }

    private boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0 || n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return n.equals(BigInteger.valueOf(2));
        }

        // Write n - 1 as d * 2^r
        BigInteger d = n.subtract(BigInteger.ONE);
        int r = 0;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.valueOf(2));
            r++;
        }

        // Repeat the test k times
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            BigInteger a = new BigInteger(n.bitLength(), random);
            if (!millerTest(n, a, d, r)) {
                return false;
            }
        }
        return true;
    }

    private boolean millerTest(BigInteger n, BigInteger a, BigInteger d, int r) {
        BigInteger x = a.modPow(d, n);
        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
            return true;
        }
        for (int i = 1; i < r; i++) {
            x = x.modPow(BigInteger.valueOf(2), n);
            if (x.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
            if (x.equals(BigInteger.ONE)) {
                return false;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        RSA cipher = new RSA(1024);
//        String plaintext = "Hello, world!";
//        String ciphertext = cipher.encrypt(plaintext);
//        String decrypted = cipher.decrypt(ciphertext);
//        System.out.println("Plaintext: " + plaintext);
//        System.out.println("Ciphertext: " + ciphertext);
//        System.out.println("Decrypted: " + decrypted);
//    }
}