package com.example.administrator.yunproject.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class VideoListBean {

    /**
     * data : {"catalog":[{"id":"293","video_title":"关爱男人","is_down":0},{"id":"294","video_title":"如何操作电脑","is_down":0},{"id":"295","video_title":"电脑行不行","is_down":0}],"is_collect":-1}
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
         * catalog : [{"id":"293","video_title":"关爱男人","is_down":0},{"id":"294","video_title":"如何操作电脑","is_down":0},{"id":"295","video_title":"电脑行不行","is_down":0}]
         * is_collect : -1
         */

        private int is_collect;
        private List<CatalogBean> catalog;

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public List<CatalogBean> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<CatalogBean> catalog) {
            this.catalog = catalog;
        }

        public static class CatalogBean {
            /**
             * id : 293
             * video_title : 关爱男人
             * is_down : 0
             */

            private String id;
            private String video_title;
            private int is_down;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getVideo_title() {
                return video_title;
            }

            public void setVideo_title(String video_title) {
                this.video_title = video_title;
            }

            public int getIs_down() {
                return is_down;
            }

            public void setIs_down(int is_down) {
                this.is_down = is_down;
            }
        }
    }
}
