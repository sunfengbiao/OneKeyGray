package com.sunfb.onekeygray.impl;

/**
 * 抽象被观察者
 */
public interface  Observable {
    //添加观察者
    void add(Observer observer);
    //删除观察者
    void remove(Observer observer);
    //通知观察者
    void notifyMessage();

    void open();

    void close();
}