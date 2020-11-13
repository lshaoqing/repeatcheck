package com.fu.linmou.design;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linMou
 * @Description: 策略工厂
 * @Date: 2020/10/21 10:44
 * @Version: 1.0
 */
public class ReceiptHandleStrategyFactory {

    private static Map<String,IReceiptHandleStrategy> receiptHandleStrategyMap;

    private ReceiptHandleStrategyFactory() {
        receiptHandleStrategyMap = new HashMap<>();
        receiptHandleStrategyMap.put("MT2101",new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT8104",new Mt8104ReceiptHandleStrategy());
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType) {
        // IReceiptHandleStrategy receiptHandleStrategy = null;
        // if(StringUtils.equals("MT2101",receiptType)) {
        //     receiptHandleStrategy = new Mt2101ReceiptHandleStrategy();
        // }else if (StringUtils.equals("MT8104",receiptType)) {
        //     receiptHandleStrategy = new Mt8104ReceiptHandleStrategy();
        // }
        // return receiptHandleStrategy;
        return receiptHandleStrategyMap.get(receiptType);
    }
}
