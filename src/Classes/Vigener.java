package Classes;

public class Vigener extends EncryptionMethod {
    char[][] matrix = new char[26][26];
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Vigener() {
        generateMatrix();
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

}
