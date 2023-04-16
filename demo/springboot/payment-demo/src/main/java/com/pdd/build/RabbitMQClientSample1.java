package com.pdd.build;

import com.pdd.vo.R;

/**
 *
 */
public class RabbitMQClientSample1 {
    private String host = "127.0.0.1";
    private int port = 5672;
    private int mode;
    private String exchange;
    private String queue;
    private boolean isDurable = true;
    int connectionTimeout = 1000;


    /**
     * 构造方法私有化，不给调用
     */
    private RabbitMQClientSample1(String host, int port, int mode, String exchange, String queue, boolean isDurable, int connectionTimeout) {
        this.host = host;
        this.port = port;
        this.mode = mode;
        this.exchange = exchange;
        this.queue = queue;
        this.isDurable = isDurable;
        this.connectionTimeout = connectionTimeout;
        if (mode == 1) {// 工作队列模式不需要设置交换机，但queue必填
            if (exchange != null){
                throw new RuntimeException("工作队列模式无须设置交换机");
            }
            if (queue == null || queue.trim().equals("")){
                throw new RuntimeException("工作队列模式必须设置队列名称");
            }
            if(isDurable == false){
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
        // 其他模式
    }
    public void sendMessage(String msg){
        System.out.println("正在发送消息："+msg);
    }

    public static void main(String[] args) {
//        RabbitMQClientSample1 client = new RabbitMQClientSample1();
    }
}
