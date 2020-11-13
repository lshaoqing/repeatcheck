package com.fu.linmou.design;

import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 10:31
 * @Version: 1.0
 */
public class ReceiptService {

    public void getReceipt() {
        List<Receipt> receiptList = ReceiptBuilder.generateRecepitList();

        for(Receipt receipt : receiptList) {
        }
    }
}
