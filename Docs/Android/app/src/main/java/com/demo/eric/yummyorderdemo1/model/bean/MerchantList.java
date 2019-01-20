package com.demo.eric.yummyorderdemo1.model.bean;

public class MerchantList {
        public static class Obj {
            private int numfound;
            private int[] storeId;

            public int getNumfound() {
                return numfound;
            }

            public void setNumfound(int numfound) {
                this.numfound = numfound;
            }


            public int[] getStoreId() {
                return storeId;
            }

            public void setStoreId(int[] storeId) {
                this.storeId = storeId;
            }
        }
        private int code;
        private String message;
    private Obj obj;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
//        public Obj getObj() {
//            return obj;
//        }
//        public void setObj(Obj obj) {
//            this.obj=obj;
//        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
