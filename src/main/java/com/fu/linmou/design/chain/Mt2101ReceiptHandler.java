package com.fu.linmou.design.chain;

import com.fu.linmou.design.Receipt;
import org.apache.commons.lang.StringUtils;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 11:09
 * @Version: 1.0
 */
public class Mt2101ReceiptHandler implements IReceiptHandler{
    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if(StringUtils.equals("MT2101",receipt.getType())) {
            System.out.println("解析报文MT2101:" + receipt.getMessage());
        //处理不了该回执就往下传递
        }else {
            handleChain.handleReceipt(receipt);
        }
    }
}

class Mt8104ReceiptHandler implements IReceiptHandler {
    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT8104",receipt.getType())) {
            System.out.println("解析报文MT8104:" + receipt.getMessage());
        }
        //处理不了该回执就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }
}