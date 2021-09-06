package com.example.cglib;

/**
 * 一个生产者
 * @author Avarice
 * @date 2021/9/5 21:38
 */
public class Producer {

    /**
     * 销售
     *
     * @param money 售卖的金额
     */
    public void saleProduct(float money) {
        System.out.println("销售产品，并拿到钱：" + money);
    }

    /**
     * 售后服务
     * @param money 售后所需的服务费
     */
    public void afterService(float money) {
        System.out.println("提供售后服务，并拿到钱：" + money);
    }
}
