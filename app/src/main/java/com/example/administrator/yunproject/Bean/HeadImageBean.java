package com.example.administrator.yunproject.Bean;

/**
 * Created by Administrator on 2018/4/13.
 */

public class HeadImageBean {


    /**
     * data : {"big":"http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_200_200.jpg?v1523868064.3994","middle":"http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_100_100.jpg?v1523868064.3994","small":"http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_50_50.jpg?v1523868064.3994","tiny":"http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_30_30.jpg?v1523868064.3994"}
     * code : 0
     * msg : ok
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * big : http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_200_200.jpg?v1523868064.3994
         * middle : http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_100_100.jpg?v1523868064.3994
         * small : http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_50_50.jpg?v1523868064.3994
         * tiny : http://192.168.252.111:88/data/upload/avatar/34/16/a7/original_30_30.jpg?v1523868064.3994
         */

        private String big;
        private String middle;
        private String small;
        private String tiny;

        public String getBig() {
            return big;
        }

        public void setBig(String big) {
            this.big = big;
        }

        public String getMiddle() {
            return middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getTiny() {
            return tiny;
        }

        public void setTiny(String tiny) {
            this.tiny = tiny;
        }
    }
}
