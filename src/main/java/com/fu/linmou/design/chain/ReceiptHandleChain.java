package com.fu.linmou.design.chain;

import com.fu.linmou.design.Receipt;

import java.util.List;

/**
 * @author linMou
 * @Description: 责任链实现类
 * @Date: 2020/10/21 11:04
 * @Version: 1.0
 */
public class ReceiptHandleChain implements IReceiptHandleChain{

    //记录当前处理者位置
    private int index = 0;
    //处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static{
        //从容器中获取处理器对象
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();
    }

    @Override
    public void handleReceipt(Receipt receipt) {
        if(receiptHandlerList != null && receiptHandlerList.size() > 0) {
            if(index != receiptHandlerList.size()) {
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt,this);
            }
        }
    }
}
