package com.team14.cms;

import java.util.List;

public class Subject {
    List<Observer> observers;

    public void attach(Observer obs) {
        observers.add(obs);
    }

    public void detach(Observer obs) {

    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
