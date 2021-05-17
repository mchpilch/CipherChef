package Classes;

import java.math.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RSA extends EncryptionMethod{
    public RSA(String plainText){
        stringToNumbers(plainText);
    }
    ArrayList<Integer> message = new ArrayList<Integer>();
    ArrayList<Integer> messageEncrypted = new ArrayList<Integer>();

    public void stringToNumbers(String str){
        for(int i = 0; i < str.length();i++){
            char currentLetter = str.charAt(i);
            int currentAsciiSign = (int) currentLetter;
            message.add(currentAsciiSign);
        }
        System.out.println(message);
    }

    public  BigInteger oneNumberCoding(Integer currentNum){//szyfrowanie

        int p, q, n, z, d = 0, e, i;
        //wybieramy liczbę e mniejszą od iloczynu (p-1)*(q-1) i nie mającą większego wspólnego dzielnika z liczbą (p-1)*(q-1) niż 1
        // The number to be encrypted and decrypted
        int msg = currentNum;
        double c;
        BigInteger msgback;
        //znajdujemy dwie, duże liczby pierwsze p i q
        // 1st prime number p
        p = 3;

        // 2nd prime number q
        q = 11;
        n = p * q;   //obliczamy ich iloczyn n

        z = (p - 1) * (q - 1);
        System.out.println("the value of z = " + z);
        //wybieramy liczbę e mniejszą od iloczynu (p-1)*(q-1) i nie mającą większego wspólnego dzielnika z liczbą (p-1)*(q-1) niż 1
        for (e = 2; e < z; e++) {

            // e is for public key exponent
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("PUBLIC KEY the value of e = " + e + " the value of n = " + n);//klucz publiczny to liczby: (e, n)
        for (i = 0; i <= 9; i++) {//obliczamy element odwrotny d względem liczby e modulo (p-1)*(q-1), a wiec d powinno być taką liczbą, aby wynikiem mnożenia e*d była jedynka, oczywiście modulo (p-1)*(q-1)
            int x = 1 + (i * z);

            // d is for private key exponent
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("PRIVATE KEY the value of d = " + d + " the value of n = " + n);//klucz prywatny to liczby: (d, n)
        c = (Math.pow(msg, e)) % n; //Szyfrowanie: wiadomość M podnsimy do potęgi e i wykonujemy operację modulo n:  (C = M^e mod n  )
        System.out.println("Encrypted message is : " + c);

        // converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);

        // converting float value of c to BigInteger
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : "
                + msgback);

        return msgback;
    }

    static int gcd(int e, int z) {//rekurencyjny najwiekszy wspolny dzielnik
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public void encrypt(){
        String encrypted = "";
        for(int i = 0; i < message.size(); i++){
            Integer numberToEncrypt = message.get(i);
            Integer numberEncrypted =  oneNumberCoding(numberToEncrypt).intValue();
            messageEncrypted.add(numberEncrypted);
            encrypted += numberEncrypted.toString();
            encrypted += " ";
        }
        setOutput(encrypted);
    }
}

