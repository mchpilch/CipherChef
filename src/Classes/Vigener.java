package Classes;

public class Vigener extends EncryptionMethod {
    char[][] matrix = new char[26][26];
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String output = "";


    public Vigener(String plainText, String key) {
        String plainTextNoSpaceces = plainText.replaceAll("\\s+","");//usuwa spacje itp.
        super.setInput(plainTextNoSpaceces.toLowerCase());

        String keyAccurateLength = matchKeyLength(plainTextNoSpaceces,key);//zwraca nowy klucz o odpowiedniej długości
        super.setKey(keyAccurateLength.toLowerCase());

        generateMatrix();
        encrypt();

    }

    public void generateMatrix() {
        int index = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                matrix[i][j] = alphabet.charAt((index+i)%26);
                //System.out.println(index+" "+index%26);
                index++;
            }
        }
    }

    public void displayMatrix() {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String matchKeyLength(String plainTextNoSpaceces, String key){

        if(plainTextNoSpaceces.length() > key.length()){
            int difference = plainTextNoSpaceces.length()-key.length();
            int reszta = difference%(key.length());
            int calosci = ((difference + key.length()) - reszta)/key.length();
//            System.out.println("difference "+ difference);
//            System.out.println("reszta "+ reszta);
//            System.out.println("calosci "+ calosci);
            key = (key.repeat(calosci)+key.substring(0,reszta));
            System.out.println(key);
            return key;
        }else{
            System.out.println("klucz ma wystarczającą długość");
            return key;
        }
    }

    public void encrypt() {

    }

}
