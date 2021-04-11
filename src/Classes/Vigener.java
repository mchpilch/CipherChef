package Classes;

public class Vigener extends EncryptionMethod {
    char[][] matrix = new char[26][26];
    String alphabet = "abcdefghijklmnopqrstuvwxyz";



    public Vigener(String plainText, String key) {
        String plainTextNoSpaceces = plainText.replaceAll("\\s+","");//usuwa spacje itp.
        super.setInput(plainTextNoSpaceces.toLowerCase());

        String keyAccurateLength = matchKeyLength(plainTextNoSpaceces,key);//zwraca nowy klucz o odpowiedniej długości
        super.setKey(keyAccurateLength.toLowerCase());

        generateMatrix();
        super.setOutput(encrypt());

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
//            System.out.println(key);
            return key;
        }else{
            System.out.println("klucz ma wystarczającą długość");
            return key;
        }
    }

    public String encrypt() {
        System.out.println("getInput() " + getInput());
        System.out.println("getKey() " + getKey());
        String encryptedText = "";

        char currentInputLetter;
        char currentKeyLetter;
        boolean wasCurrentInputLetterFound = false;
        boolean wasCurrentKeyLetterFound = false;
        int currentInputLetterIndex = 0;
        int currentKeyLetterIndex = 0;

        char currentLetterOfOutput;

        for(int i = 0; i < getInput().length();i++){//kolejne literki w input'cie i kluczu
            currentInputLetter = getInput().charAt(i) ;//wyszukuje litere w inpucie
            currentKeyLetter = getKey().charAt(i);//wyszukuje litere w kluczu
            for(int k = 0; k < matrix.length; k++){
                if(currentInputLetter == matrix[0][k] && wasCurrentInputLetterFound == false){
                    //System.out.println(currentInputLetter + " " + k);
                    currentInputLetterIndex = k;
                    wasCurrentInputLetterFound = true;
                }
                if(currentKeyLetter == matrix[0][k] && wasCurrentKeyLetterFound == false){
                    //System.out.println(currentKeyLetter + " " + k);
                    currentKeyLetterIndex = k;
                    wasCurrentKeyLetterFound = true;
                }
                if((wasCurrentInputLetterFound && wasCurrentKeyLetterFound)){
                    wasCurrentInputLetterFound = false;
                    wasCurrentKeyLetterFound = false;
                    break;
                }
            }
            currentLetterOfOutput = (matrix[currentInputLetterIndex][currentKeyLetterIndex]);
            encryptedText += currentLetterOfOutput;
        }

        return encryptedText;
    }

}
