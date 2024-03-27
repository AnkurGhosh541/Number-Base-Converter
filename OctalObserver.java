public class OctalObserver extends Observer{
    private String octal;

    public String getOctal() {
        return octal;
    }

    public void setOctal(String octal) {
        this.octal = octal;
    }

    @Override
    public void update() {
        Object ob = subject.getState();
        int number;
        if (ob != null) {
            number = (int) ob;
            octal = Integer.toOctalString(number);
        } else {
            octal = "";
        }
    }
}
