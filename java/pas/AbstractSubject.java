package pas;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {
    private List<IF_Observer> listObserver;

    public AbstractSubject() {
        this.listObserver = new ArrayList<>();
    }

    public void attach(IF_Observer observer) {
        if (observer != null && !this.listObserver.contains(observer)) {
            this.listObserver.add(observer);
        }
    }

    public void deatach(IF_Observer observer) {
        this.listObserver.remove(observer);
    }

    public void detach(IF_Observer observer) {
        this.deatach(observer);
    }

    public void notifyAll_() {
        for (IF_Observer observer : this.listObserver) {
            observer.update();
        }
    }
}
