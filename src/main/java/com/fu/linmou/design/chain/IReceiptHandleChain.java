package com.fu.linmou.design.chain;

import com.fu.linmou.design.Receipt;

/**
 * 责任链接口
 */
public interface IReceiptHandleChain {

    void handleReceipt(Receipt receipt);
}
