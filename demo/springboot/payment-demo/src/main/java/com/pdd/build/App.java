package com.pdd.build;

public class App {
    /**
     * 1 复杂的参数，如果使用构造函数，那么多参数的复杂组合？
     * 2 不可变对象的状态的问题，对象构造出来后，就没有set可变了。
     *
     * @param args
     */
    public static void main(String[] args) {
        RabbitMQClient client = new RabbitMQClient.Builder()
                .setHost("127.0.0.1")
                .setIsDurable(false)
                .setMode(1)
                .setExchange("")
                .setQueue("").build();
    }
}
