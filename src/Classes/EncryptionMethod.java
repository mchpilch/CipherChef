package Classes;

public class EncryptionMethod {
    private String input;
    private String output;
    private String key;
    private String name;

    public EncryptionMethod(String input, String output, String key, String name) {
        this.input = input;
        this.output = output;
        this.key = key;
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

}
