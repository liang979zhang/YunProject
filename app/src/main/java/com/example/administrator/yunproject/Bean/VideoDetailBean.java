package com.example.administrator.yunproject.Bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class VideoDetailBean {

    /**
     * data : {"id":"171","uid":"1","album_category":"447","fullcategorypath":",447,","album_title":"投行先锋2018《保代宝典》系列","album_intro":"的说法是大是大非安抚阿道夫ad发电房大师傅 答复","cover":"http://192.168.252.111:88/data/upload/2018/0326/19/5ab8ddc5d8438_280_160.png","is_best":"1","is_del":"0","str_tag":"保代","tag_ids":",169,","album_question_count":"0","album_note_count":"0","album_comment_count":"3","album_score":0,"album_collect_count":"0","album_order_count":"1","listingtime":"1522064860","uctime":"1522496863","ctime":"1522064871","utime":"1522134708","reviewCount":"3","album_category_name":"保代宝典","iscollect":"0","mzprice":{"oriPrice":2,"vipPrice":0,"disPrice":0,"discount":30,"dis_type":0,"price":2,"overplus":2},"isSufficient":false,"isGetResource":{"video":false,"upload":false,"note":false,"question":false},"is_buy":false,"is_play_all":0}
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
         * id : 171
         * uid : 1
         * album_category : 447
         * fullcategorypath : ,447,
         * album_title : 投行先锋2018《保代宝典》系列
         * album_intro : 的说法是大是大非安抚阿道夫ad发电房大师傅 答复
         * cover : http://192.168.252.111:88/data/upload/2018/0326/19/5ab8ddc5d8438_280_160.png
         * is_best : 1
         * is_del : 0
         * str_tag : 保代
         * tag_ids : ,169,
         * album_question_count : 0
         * album_note_count : 0
         * album_comment_count : 3
         * album_score : 0
         * album_collect_count : 0
         * album_order_count : 1
         * listingtime : 1522064860
         * uctime : 1522496863
         * ctime : 1522064871
         * utime : 1522134708
         * reviewCount : 3
         * album_category_name : 保代宝典
         * iscollect : 0
         * mzprice : {"oriPrice":2,"vipPrice":0,"disPrice":0,"discount":30,"dis_type":0,"price":2,"overplus":2}
         * isSufficient : false
         * isGetResource : {"video":false,"upload":false,"note":false,"question":false}
         * is_buy : false
         * is_play_all : 0
         */

        private String id;
        private String uid;
        private String album_category;
        private String fullcategorypath;
        private String album_title;
        private String album_intro;
        private String cover;
        private String is_best;
        private String is_del;
        private String str_tag;
        private String tag_ids;
        private String album_question_count;
        private String album_note_count;
        private String album_comment_count;
        private int album_score;
        private String album_collect_count;
        private String album_order_count;
        private String listingtime;
        private String uctime;
        private String ctime;
        private String utime;
        private String reviewCount;
        private String album_category_name;
        private String iscollect;
        private MzpriceBean mzprice;
        private boolean isSufficient;
        private IsGetResourceBean isGetResource;
        private boolean is_buy;
        private int is_play_all;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAlbum_category() {
            return album_category;
        }

        public void setAlbum_category(String album_category) {
            this.album_category = album_category;
        }

        public String getFullcategorypath() {
            return fullcategorypath;
        }

        public void setFullcategorypath(String fullcategorypath) {
            this.fullcategorypath = fullcategorypath;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public String getAlbum_intro() {
            return album_intro;
        }

        public void setAlbum_intro(String album_intro) {
            this.album_intro = album_intro;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIs_best() {
            return is_best;
        }

        public void setIs_best(String is_best) {
            this.is_best = is_best;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getStr_tag() {
            return str_tag;
        }

        public void setStr_tag(String str_tag) {
            this.str_tag = str_tag;
        }

        public String getTag_ids() {
            return tag_ids;
        }

        public void setTag_ids(String tag_ids) {
            this.tag_ids = tag_ids;
        }

        public String getAlbum_question_count() {
            return album_question_count;
        }

        public void setAlbum_question_count(String album_question_count) {
            this.album_question_count = album_question_count;
        }

        public String getAlbum_note_count() {
            return album_note_count;
        }

        public void setAlbum_note_count(String album_note_count) {
            this.album_note_count = album_note_count;
        }

        public String getAlbum_comment_count() {
            return album_comment_count;
        }

        public void setAlbum_comment_count(String album_comment_count) {
            this.album_comment_count = album_comment_count;
        }

        public int getAlbum_score() {
            return album_score;
        }

        public void setAlbum_score(int album_score) {
            this.album_score = album_score;
        }

        public String getAlbum_collect_count() {
            return album_collect_count;
        }

        public void setAlbum_collect_count(String album_collect_count) {
            this.album_collect_count = album_collect_count;
        }

        public String getAlbum_order_count() {
            return album_order_count;
        }

        public void setAlbum_order_count(String album_order_count) {
            this.album_order_count = album_order_count;
        }

        public String getListingtime() {
            return listingtime;
        }

        public void setListingtime(String listingtime) {
            this.listingtime = listingtime;
        }

        public String getUctime() {
            return uctime;
        }

        public void setUctime(String uctime) {
            this.uctime = uctime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getAlbum_category_name() {
            return album_category_name;
        }

        public void setAlbum_category_name(String album_category_name) {
            this.album_category_name = album_category_name;
        }

        public String getIscollect() {
            return iscollect;
        }

        public void setIscollect(String iscollect) {
            this.iscollect = iscollect;
        }

        public MzpriceBean getMzprice() {
            return mzprice;
        }

        public void setMzprice(MzpriceBean mzprice) {
            this.mzprice = mzprice;
        }

        public boolean isIsSufficient() {
            return isSufficient;
        }

        public void setIsSufficient(boolean isSufficient) {
            this.isSufficient = isSufficient;
        }

        public IsGetResourceBean getIsGetResource() {
            return isGetResource;
        }

        public void setIsGetResource(IsGetResourceBean isGetResource) {
            this.isGetResource = isGetResource;
        }

        public boolean isIs_buy() {
            return is_buy;
        }

        public void setIs_buy(boolean is_buy) {
            this.is_buy = is_buy;
        }

        public int getIs_play_all() {
            return is_play_all;
        }

        public void setIs_play_all(int is_play_all) {
            this.is_play_all = is_play_all;
        }

        public static class MzpriceBean {
            /**
             * oriPrice : 2
             * vipPrice : 0
             * disPrice : 0
             * discount : 30
             * dis_type : 0
             * price : 2
             * overplus : 2
             */

            private int oriPrice;
            private int vipPrice;
            private int disPrice;
            private int discount;
            private int dis_type;
            private int price;
            private int overplus;

            public int getOriPrice() {
                return oriPrice;
            }

            public void setOriPrice(int oriPrice) {
                this.oriPrice = oriPrice;
            }

            public int getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(int vipPrice) {
                this.vipPrice = vipPrice;
            }

            public int getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(int disPrice) {
                this.disPrice = disPrice;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getDis_type() {
                return dis_type;
            }

            public void setDis_type(int dis_type) {
                this.dis_type = dis_type;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getOverplus() {
                return overplus;
            }

            public void setOverplus(int overplus) {
                this.overplus = overplus;
            }
        }

        public static class IsGetResourceBean {
            /**
             * video : false
             * upload : false
             * note : false
             * question : false
             */

            private boolean video;
            private boolean upload;
            private boolean note;
            private boolean question;

            public boolean isVideo() {
                return video;
            }

            public void setVideo(boolean video) {
                this.video = video;
            }

            public boolean isUpload() {
                return upload;
            }

            public void setUpload(boolean upload) {
                this.upload = upload;
            }

            public boolean isNote() {
                return note;
            }

            public void setNote(boolean note) {
                this.note = note;
            }

            public boolean isQuestion() {
                return question;
            }

            public void setQuestion(boolean question) {
                this.question = question;
            }
        }
    }
}
