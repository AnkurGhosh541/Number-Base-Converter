public class BinaryObserver extends Observer{
    private String binary;

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    @Override
    public void update() {
        Object ob = subject.getState();
        int number;
        if (ob != null) {
            number = (int) ob;
            binary = Integer.toBinaryString(number);
        } else {
            binary = "";
        }
    }
}
