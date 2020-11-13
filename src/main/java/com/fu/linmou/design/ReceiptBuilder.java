package com.fu.linmou.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 10:26
 * @Version: 1.0
 */
public class ReceiptBuilder {

    public static List<Receipt> generateRecepitList() {
        //直接模拟一堆回执对象
        List<Receipt> receiptList = new ArrayList<>(4);
        receiptList.add(new Receipt("我是MT2101回执喔","MT2101"));
        receiptList.add(new Receipt("我是MT1101回执喔","MT1101"));
        receiptList.add(new Receipt("我是MT8104回执喔","MT8104"));
        receiptList.add(new Receipt("我是MT9999回执喔","MT9999"));
        //......
        return receiptList;
    }

}
