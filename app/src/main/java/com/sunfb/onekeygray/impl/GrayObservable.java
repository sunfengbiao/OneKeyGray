package com.sunfb.onekeygray.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体观察者
 */
public class GrayObservable implements  Observable {
    List<Observer> observerList =new ArrayList<>();
    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyMessage() {
        for(Observer observer:observerList){
            observer.update();
        }
    }

    @Override
    public void open() {
        for(Observer observer:observerList){
            observer.open();
        }
    }

    @Override
    public void close() {
        for(Observer observer:observerList){
            observer.close();
        }
    }
}
