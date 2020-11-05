package com.sunfb.onekeygray.impl;

/**
 * 抽象观察者
 */
public interface Observer {
    //更新方法
    public void update();

    void open();

    void close();
}
