public class HexObserver extends Observer{
    private String hex;

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public void update() {
        Object ob = subject.getState();
        int number;
        if (ob != null) {
            number = (int) ob;
            hex = Integer.toHexString(number);
        } else {
            hex = "";
        }
    }
}
