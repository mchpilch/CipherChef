package Classes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ElGamal extends EncryptionMethod{
    ArrayList<Integer> message = new ArrayList<Integer>();
    ArrayList<DoubleCCryptogram> messageEncrypted = new ArrayList<DoubleCCryptogram>();
    private int q,a,y,x;

    public ElGamal(){
        super.setName("ElGamal");
    }

    public ElGamal(String plaintext, int pierwsza) {//q do 19
        //wybieramy liczbę pierwszą q i jej pierwiastek pierwotny a
        //int q = 11; //liczba pierwsza
        stringToNumbers(plaintext);
        setQ(pierwsza);
        setA(pierwsza);
        setX();
        setY(pierwsza,getA(),getX());

        System.out.println("klucz publiczny (q, a, y) -> " + "(" + q +"," + a +"," + y +")");
        System.out.println("klucz prywatny (x) -> " + "(" + x  +")");

//        int m = 2; //m to plaintext zamieniony na liczbe
//        int q = pierwsza;
//        System.out.println("q " + q);
//        int a = smallestPrimitiveRoot(q);
//        System.out.println("a " + a);
//        //następnie losowo liczbę x, mniejszą od q-1
//        Random random = new Random();
//        int x = random.nextInt(q-2)+1;//losowa liczba od 1 do q-2 łącznie
//        System.out.println("x " + x);
//        //obliczamy liczbę y = a^x mod q
//        int y =  (int)Math.pow(a,x)%q;
//        System.out.println("y " + y);
//        //klucz publiczny to zbiór:  (q, a, y)
//        System.out.println("klucz publiczny (q, a, y) -> " + "(" + q +"," + a +"," + y +")");
//        System.out.println("klucz prywatny (x) -> " + "(" + x  +")");
//        DoubleCCryptogram sth = oneNumberCoding(m);
//        System.out.println(sth);
//        System.out.println("C1 -> " + "(" + sth.getC1() +")" +  " C2 -> " + "(" + sth.getC2()+")");
    }

    public void setQ(int q) {
        this.q = q;
    }
    public void setA(int q) {
        this.a = smallestPrimitiveRoot(q);
    }
    public void setX() {
        Random random = new Random();
        this.x = random.nextInt(q-2)+1;//losowa liczba od 1 do q-2 łącznie
    }
    public void setY(int q, int a, int x) {
        this.y = (int)Math.pow(a,x)%q;
    }

    public int getQ() {
        return q;
    }
    public int getA() {
        return a;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public void encrypt(){//szyfruje juz jakis ciag
        String encrypted = "Dla każdego znaku wg. wzoru (C1;C2) zaszyfrowana wiadomość to: ";
        for(int i = 0; i < message.size(); i++){
            Integer numberToEncrypt = message.get(i);
            DoubleCCryptogram numberEncrypted =  oneNumberCoding(numberToEncrypt);//q,a,y
            messageEncrypted.add(numberEncrypted);
            encrypted += " (";
            encrypted += messageEncrypted.get(i).getC1();
            encrypted += ";";
            encrypted += messageEncrypted.get(i).getC2();
            encrypted += ") ";
            //System.out.println(encrypted);
        }
        setOutput(encrypted);
    }

    public void stringToNumbers(String str){
        for(int i = 0; i < str.length();i++){
            char currentLetter = str.charAt(i);
            int currentAsciiSign = (int) currentLetter;
            message.add(currentAsciiSign - 95);//poki co - 95 bo nieefektywny dla duzych liczb, jak zmieni sie smallestprimitiveroot funkcja to mozna olac 90
        }
        System.out.println("Wiadomość przekonwertowana na liczby: " + message);
    }

    public DoubleCCryptogram oneNumberCoding(int m) {//szyfrowanie pojedycznczego znaku //int q, int a, int y,
        int c1 = -10;
        int k = 3; //k dowlona liczba calkowita
        c1 = ((int)Math.pow(a,k))%q;

        int c2 = -10;
        c2 = (((int)Math.pow(y,k))*m)%q;

        return new DoubleCCryptogram(c1,c2);
    }

    class DoubleCCryptogram{
        private int c1;
        private int c2;

        public DoubleCCryptogram(int c1, int c2){
            this.c1 = c1;
            this.c2 = c2;
        }
        public int getC1(){
            return c1;
        }
        public int getC2(){
            return c2;
        }
    }

    public static int smallestPrimitiveRoot(int q){ //oblicza pierwiastek pierwotny z liczby pierwszej, poki co dziala do 19 bo potem jest problem z zakresem pamieci
        // (to jest zrobione na chłopski rozum madrzej robic za pomoca funkcji phi o ktorej bylo na WZMW)
        if(isPrime(q)==false){
            return -1;
        }else{        //r^x(mod n)
            int power;
            int rest;
            boolean wasSmallestPrimitiveRootFound = false;
            int smallestPrimitiveRoot = -10;
            ArrayList<Integer> rests = new ArrayList<Integer>();
            for(int r = 2; r <= q - 1; r++){//r od 2 do n-1  n=q
                if(wasSmallestPrimitiveRootFound == false){
                    for(int x = 0; x <= q-2;x++){//x od 0 do n-2
                        power = (int)Math.pow(r,x);// tu jest pies pogrzebany bo do int ma zakres do 2 147 483 647 (ze znakiem) a liczby na których operuje są dużo większe
                        rest = power%q; // po 14 petli zakres inta sie konczy
                        rests.add(rest);
                    }
                    Collections.sort(rests);
                    //System.out.println(r + " " + rests);
                    for(int i = 0; i < rests.size(); i++){
                        if(rests.get(i) == i+1){
                            //System.out.println("git");
                            if(i == rests.size()-1){
                                //System.out.println("primitive root is " + r);;
                                wasSmallestPrimitiveRootFound = true;
                                smallestPrimitiveRoot = r;
                                break;
                            }
                        }else{
                            //System.out.println("SPADAM");
                            break;
                        }
                    }
                    rests.clear();
                }
            }
            return smallestPrimitiveRoot;
        }
    }

    public  static boolean isPrime(int n){//sprawdza czy liczba jest pierwsza
        // Corner cases
        if (n <= 1)
        {
            return false;
        }
        if (n <= 3)
        {
            return true;
        }

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
        {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
            {
                return false;
            }
        }
        return true;
    }

    public void setMessage(String str) {
        stringToNumbers(str);
    }

}
