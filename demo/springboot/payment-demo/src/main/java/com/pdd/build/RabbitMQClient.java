package com.pdd.build;

import io.swagger.models.auth.In;

public class RabbitMQClient {
    private RabbitMQClient(Builder builder) {

    }

    public void sendMessage(String msg) {
        System.out.println("正在发送消息：" + msg);
    }

    public static class Builder {
        private String host = "127.0.0.1";
        private int port = 5672;
        private int mode;
        private String exchange;
        private String queue;
        private boolean isDurable = true;
        int connectionTimeout = 1000;

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setMode(Integer mode) {
            this.mode = mode;
            return this;
        }

        public Builder setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public Builder setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public Builder setIsDurable(Boolean isDurable) {
            this.isDurable = isDurable;
            return this;
        }

        public RabbitMQClient build(){
            if (mode == 1) {// 工作队列模式不需要设置交换机，但queue必填
                if (exchange != null){
                    throw new RuntimeException("工作队列模式无须设置交换机");
                }
                if (queue == null || queue.trim().equals("")){
                    throw new RuntimeException("工作队列模式必须设置队列名称");
                }
                if(!isDurable){
                    throw new RuntimeException("工作队列模式必须开启数据持久化");
                }
            } else if (mode == 2) {//路由模式必须设置交换机，但不能设置queue队列
                if(exchange == null || exchange.trim().equals("")){
                    throw new RuntimeException("路由模式必须设置交换机");
                }
                if (queue != null){
                    throw new RuntimeException("路由模式不须设置队列");
                }
            }
            return new RabbitMQClient(this);
        }
    }
}
