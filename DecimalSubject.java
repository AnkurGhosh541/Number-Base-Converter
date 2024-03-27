public class DecimalSubject extends Subject{
    private Integer number;

    public void setNumber(Integer number) {
        this.number = number;
        notifyObservers();
    }

    @Override
    public Object getState() {
        return number;
    }
}
