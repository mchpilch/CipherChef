package Classes;

public class EncryptionMethod {
    private String input;
    private String output;
    private String key;

    public EncryptionMethod(String input, String output, String key) {
        this.input = input;
        this.output = output;
        this.key = key;
    }

    public EncryptionMethod() {
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getKey() {
        return key;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void displayOutput() {}
    public void displayInput() {}
    public void displayKey() {}
}
