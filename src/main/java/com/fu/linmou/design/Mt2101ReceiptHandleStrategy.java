package com.fu.linmou.design;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 10:34
 * @Version: 1.0
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101: " + receipt.getMessage());
    }
}

class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101:" + receipt.getMessage());
    }

}

class Mt8104ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT8104:" + receipt.getMessage());
    }

}

class Mt9999ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT9999:" + receipt.getMessage());
    }

}