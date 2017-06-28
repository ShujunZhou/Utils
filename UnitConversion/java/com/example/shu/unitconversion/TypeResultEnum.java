package com.example.shu.unitconversion;

/**
 * Created by shu on 2017/6/18.
 *
 */

public enum TypeResultEnum {
        CM(0, "厘米"),
        KG(1, "公斤"),
        KILOMETERS(2, "公里"),
        MI(3, "毫升");
        
        private int position;
        
        private String result;
        
        TypeResultEnum(int position, String result) {
                this.position = position;
                this.result = result;
        }
        
        public int getPosition() {
                return position;
        }
        
        public String getResult() {
                return result;
        }
}
