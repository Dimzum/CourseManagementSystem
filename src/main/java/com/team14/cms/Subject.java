package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    public void attach(Observer obs) {
        observers.add(obs);
    }
    public void detach(Observer obs) { observers.remove(obs); }

    public abstract void notifyObservers();
}
