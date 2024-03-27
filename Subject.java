import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observerList = new ArrayList<>();

    public void attach(Observer observer) {
        observerList.add(observer);
        observer.subject = this;
    }

    public void detach(Observer observer) {
        observerList.remove(observer);
        observer.subject = null;
    }

    public abstract Object getState();

    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
