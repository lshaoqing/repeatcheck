package com.fu.linmou.design;

import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 10:49
 * @Version: 1.0
 */
public class Client {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateRecepitList();
        //策略上下文
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        for(Receipt receipt : receiptList) {
            //获取并设置策略
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);
            //执行策略
            receiptStrategyContext.handleReceipt(receipt);
        }
    }
}
