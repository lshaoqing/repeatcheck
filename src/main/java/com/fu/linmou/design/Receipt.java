package com.fu.linmou.design;

import lombok.Data;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/10/21 10:24
 * @Version: 1.0
 */
@Data
public class Receipt {
    /**
     * 回执信息
     */
   private String message;

    /**
     * 回执类型（MT1101/MT2101/MT4101/MT8104/MT8105/MT999）
     */
   private String type;

   public Receipt() {

   }

   public Receipt(String message,String type) {
       this.message = message;
       this.type = type;
   }
}
