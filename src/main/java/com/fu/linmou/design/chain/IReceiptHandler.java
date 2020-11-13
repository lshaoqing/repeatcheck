package com.fu.linmou.design.chain;

import com.fu.linmou.design.Receipt;

/**
 * @author linMou
 * @Description: 抽象回执处理者接口
 * @Date: 2020/10/21 11:02
 * @Version: 1.0
 */
public interface IReceiptHandler {

    void handleReceipt(Receipt receipt,IReceiptHandleChain handleChain);
}
