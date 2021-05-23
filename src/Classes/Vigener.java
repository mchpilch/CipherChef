package Classes;

public class Vigener extends EncryptionMethod {
    char[][] matrix = new char[26][26];
    String alphabet = "abcdefghijklmnopqrstuvwxyz";


    public Vigener(String plainText, String key) {
        String plainTextNoSpaceces = plainText.replaceAll("\\s+","");//usuwa spacje itp.
        setInput(plainTextNoSpaceces.toLowerCase());

        String keyAccurateLength = matchKeyLength(plainTextNoSpaceces,key);//zwraca nowy klucz o odpowiedniej długości
        setKey(keyAccurateLength.toLowerCase());

        generateMatrix();

    }

    public Vigener() {
        generateMatrix();
        super.setName("Vigener");
    }

    public void generateMatrix() {
        int index = 0;

        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < 26; j++) {

                matrix[i][j] = alphabet.charAt((index+i)%26);
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

            key = (key.repeat(calosci)+key.substring(0,reszta));

            return key;
        }else{

            return key;
        }
    }

    public void encrypt() {
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
                    currentInputLetterIndex = k;
                    wasCurrentInputLetterFound = true;
                }
                if(currentKeyLetter == matrix[k][0] && wasCurrentKeyLetterFound == false){
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

        setOutput(encryptedText);
    }
    // w macierzy [wybor rzedu][wybor kolumny]
    public void decrypt(){
        System.out.println("getInput() " + getInput());
        System.out.println("getKey() " + getKey());
        String decryptedText = "";

        char currentInputLetter;
        char currentKeyLetter;
        boolean wasCurrentKeyLetterFound = false;
        int currentInputLetterIndex = 0;
        int currentKeyLetterIndex = 0;

        char currentLetterOfOutput;

        for(int i = 0; i < getKey().length();i++){//kolejne literki w input'cie i kluczu
            currentKeyLetter = getKey().charAt(i);//wyszukuje litere w kluczu
            currentInputLetter = getInput().charAt(i) ;//wyszukuje litere w inpucie
            for(int n = 0; n < matrix.length; n++){
                if(currentKeyLetter == matrix[n][0] && wasCurrentKeyLetterFound == false){
                    currentKeyLetterIndex = n;
                    wasCurrentKeyLetterFound = true;
                }//znalazłem w macierzy litere klucza teraz szukam litery szyfrogramu w linii litery klucza
                if(wasCurrentKeyLetterFound == true){
                    for(int j = 0; j < matrix.length; j++){
                        if(currentInputLetter == matrix[currentKeyLetterIndex][j]){
                            currentInputLetterIndex = j;
                            wasCurrentKeyLetterFound = false;
                            break;
                        }
                    }
                }
            }
        currentLetterOfOutput = (matrix[0][currentInputLetterIndex]);
        decryptedText += currentLetterOfOutput;
        }
    setOutput(decryptedText);
    }

    @Override
    public void setInput(String input) {
        String plainTextNoSpaceces = input.replaceAll("\\s+","");//usuwa spacje itp.
        super.setInput(plainTextNoSpaceces.toLowerCase());
    }

    @Override
    public void setKey(String key) {
        String keyAccurateLength = matchKeyLength(getInput(), key);//zwraca nowy klucz o odpowiedniej długości
        super.setKey(keyAccurateLength.toLowerCase());
    }

    public String getAlphabet() {
        return alphabet;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
