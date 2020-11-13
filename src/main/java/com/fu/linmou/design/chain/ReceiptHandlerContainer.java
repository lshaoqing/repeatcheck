package com.fu.linmou.design.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 11:12
 * @Version: 1.0
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer() {

    }

    public static List<IReceiptHandler> getReceiptHandlerList() {
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        receiptHandlerList.add(new Mt2101ReceiptHandler());
        receiptHandlerList.add(new Mt8104ReceiptHandler());
        return receiptHandlerList;
    }
}
