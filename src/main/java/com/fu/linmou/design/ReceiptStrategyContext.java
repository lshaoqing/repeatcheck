package com.fu.linmou.design;

/**
 * @author linMou
 * @Description: 上下文类，持有策略接口
 * @Date: 2020/10/21 10:39
 * @Version: 1.0
 */
public class ReceiptStrategyContext {

    private IReceiptHandleStrategy receiptHandleStrategy;

    /**
     * 设置策略接口
     * @param receiptHandleStrategy
     */
    public  void setReceiptHandleStrategy(IReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    public void handleReceipt(Receipt receipt) {
        if(receiptHandleStrategy != null) {
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }
}
