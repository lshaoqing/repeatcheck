package com.fu.linmou.design.chain;

import com.fu.linmou.design.Receipt;
import com.fu.linmou.design.ReceiptBuilder;

import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 11:14
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        List<Receipt> receiptList = ReceiptBuilder.generateRecepitList();
        for(Receipt receipt : receiptList) {
            //回执处理链对象
            ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
            receiptHandleChain.handleReceipt(receipt);
        }
    }
}
