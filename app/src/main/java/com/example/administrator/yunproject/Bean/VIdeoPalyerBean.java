package com.example.administrator.yunproject.Bean;

/**
 * Created by Administrator on 2018/3/30.
 */

public class VIdeoPalyerBean {


    /**
     * data : {"id":"296","uid":"1","video_title":"平安普惠","video_category":"452","fullcategorypath":",452,","video_intro":"对方水电费大师傅稍等\n胜多负少的防守打法 电风扇 大杀四方","video_address":"http://p5xji5gad.bkt.clouddn.com/eduline81522068590","cover":"http://192.168.252.111:88/data/upload/2018/0326/20/5ab8ec853a899_280_160.png","teacher_id":"1","videofile_ids":"0","str_tag":"平安","tag_id":",171,","is_part_album":"1","v_price":"0.00","t_price":"0.00","discount":"0.00","is_tlimit":"0","starttime":"0","endtime":"0","limit_discount":"1.00","qiniu_key":"eduline81522068590","video_collect_count":"1","video_comment_count":"0","video_question_count":"0","video_note_count":"0","video_score":0,"video_order_count":"3","is_activity":"1","is_best":"0","is_del":"0","listingtime":"1522068634","uctime":"1622500635","ctime":"1522068640","utime":"1523861880","video_selNum":null,"video_clickNum":null,"reviewCount":"0","video_category_name":"25万会员口碑","iscollect":"0","mzprice":{"oriPrice":"0","vipPrice":null,"disPrice":null,"discount":"10","dis_type":"0","price":"0"},"isSufficient":false,"isGetResource":{"video":false,"upload":false,"note":false,"question":false},"isBuy":1,"freetime":3,"is_play_all":1}
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
         * id : 296
         * uid : 1
         * video_title : 平安普惠
         * video_category : 452
         * fullcategorypath : ,452,
         * video_intro : 对方水电费大师傅稍等
         胜多负少的防守打法 电风扇 大杀四方
         * video_address : http://p5xji5gad.bkt.clouddn.com/eduline81522068590
         * cover : http://192.168.252.111:88/data/upload/2018/0326/20/5ab8ec853a899_280_160.png
         * teacher_id : 1
         * videofile_ids : 0
         * str_tag : 平安
         * tag_id : ,171,
         * is_part_album : 1
         * v_price : 0.00
         * t_price : 0.00
         * discount : 0.00
         * is_tlimit : 0
         * starttime : 0
         * endtime : 0
         * limit_discount : 1.00
         * qiniu_key : eduline81522068590
         * video_collect_count : 1
         * video_comment_count : 0
         * video_question_count : 0
         * video_note_count : 0
         * video_score : 0
         * video_order_count : 3
         * is_activity : 1
         * is_best : 0
         * is_del : 0
         * listingtime : 1522068634
         * uctime : 1622500635
         * ctime : 1522068640
         * utime : 1523861880
         * video_selNum : null
         * video_clickNum : null
         * reviewCount : 0
         * video_category_name : 25万会员口碑
         * iscollect : 0
         * mzprice : {"oriPrice":"0","vipPrice":null,"disPrice":null,"discount":"10","dis_type":"0","price":"0"}
         * isSufficient : false
         * isGetResource : {"video":false,"upload":false,"note":false,"question":false}
         * isBuy : 1
         * freetime : 3
         * is_play_all : 1
         */

        private String id;
        private String uid;
        private String video_title;
        private String video_category;
        private String fullcategorypath;
        private String video_intro;
        private String video_address;
        private String cover;
        private String teacher_id;
        private String videofile_ids;
        private String str_tag;
        private String tag_id;
        private String is_part_album;
        private String v_price;
        private String t_price;
        private String discount;
        private String is_tlimit;
        private String starttime;
        private String endtime;
        private String limit_discount;
        private String qiniu_key;
        private String video_collect_count;
        private String video_comment_count;
        private String video_question_count;
        private String video_note_count;
        private int video_score;
        private String video_order_count;
        private String is_activity;
        private String is_best;
        private String is_del;
        private String listingtime;
        private String uctime;
        private String ctime;
        private String utime;
        private Object video_selNum;
        private Object video_clickNum;
        private String reviewCount;
        private String video_category_name;
        private String iscollect;
        private MzpriceBean mzprice;
        private boolean isSufficient;
        private IsGetResourceBean isGetResource;
        private int isBuy;
        private int freetime;
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

        public String getVideo_title() {
            return video_title;
        }

        public void setVideo_title(String video_title) {
            this.video_title = video_title;
        }

        public String getVideo_category() {
            return video_category;
        }

        public void setVideo_category(String video_category) {
            this.video_category = video_category;
        }

        public String getFullcategorypath() {
            return fullcategorypath;
        }

        public void setFullcategorypath(String fullcategorypath) {
            this.fullcategorypath = fullcategorypath;
        }

        public String getVideo_intro() {
            return video_intro;
        }

        public void setVideo_intro(String video_intro) {
            this.video_intro = video_intro;
        }

        public String getVideo_address() {
            return video_address;
        }

        public void setVideo_address(String video_address) {
            this.video_address = video_address;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(String teacher_id) {
            this.teacher_id = teacher_id;
        }

        public String getVideofile_ids() {
            return videofile_ids;
        }

        public void setVideofile_ids(String videofile_ids) {
            this.videofile_ids = videofile_ids;
        }

        public String getStr_tag() {
            return str_tag;
        }

        public void setStr_tag(String str_tag) {
            this.str_tag = str_tag;
        }

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        public String getIs_part_album() {
            return is_part_album;
        }

        public void setIs_part_album(String is_part_album) {
            this.is_part_album = is_part_album;
        }

        public String getV_price() {
            return v_price;
        }

        public void setV_price(String v_price) {
            this.v_price = v_price;
        }

        public String getT_price() {
            return t_price;
        }

        public void setT_price(String t_price) {
            this.t_price = t_price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getIs_tlimit() {
            return is_tlimit;
        }

        public void setIs_tlimit(String is_tlimit) {
            this.is_tlimit = is_tlimit;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getLimit_discount() {
            return limit_discount;
        }

        public void setLimit_discount(String limit_discount) {
            this.limit_discount = limit_discount;
        }

        public String getQiniu_key() {
            return qiniu_key;
        }

        public void setQiniu_key(String qiniu_key) {
            this.qiniu_key = qiniu_key;
        }

        public String getVideo_collect_count() {
            return video_collect_count;
        }

        public void setVideo_collect_count(String video_collect_count) {
            this.video_collect_count = video_collect_count;
        }

        public String getVideo_comment_count() {
            return video_comment_count;
        }

        public void setVideo_comment_count(String video_comment_count) {
            this.video_comment_count = video_comment_count;
        }

        public String getVideo_question_count() {
            return video_question_count;
        }

        public void setVideo_question_count(String video_question_count) {
            this.video_question_count = video_question_count;
        }

        public String getVideo_note_count() {
            return video_note_count;
        }

        public void setVideo_note_count(String video_note_count) {
            this.video_note_count = video_note_count;
        }

        public int getVideo_score() {
            return video_score;
        }

        public void setVideo_score(int video_score) {
            this.video_score = video_score;
        }

        public String getVideo_order_count() {
            return video_order_count;
        }

        public void setVideo_order_count(String video_order_count) {
            this.video_order_count = video_order_count;
        }

        public String getIs_activity() {
            return is_activity;
        }

        public void setIs_activity(String is_activity) {
            this.is_activity = is_activity;
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

        public Object getVideo_selNum() {
            return video_selNum;
        }

        public void setVideo_selNum(Object video_selNum) {
            this.video_selNum = video_selNum;
        }

        public Object getVideo_clickNum() {
            return video_clickNum;
        }

        public void setVideo_clickNum(Object video_clickNum) {
            this.video_clickNum = video_clickNum;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getVideo_category_name() {
            return video_category_name;
        }

        public void setVideo_category_name(String video_category_name) {
            this.video_category_name = video_category_name;
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

        public int getIsBuy() {
            return isBuy;
        }

        public void setIsBuy(int isBuy) {
            this.isBuy = isBuy;
        }

        public int getFreetime() {
            return freetime;
        }

        public void setFreetime(int freetime) {
            this.freetime = freetime;
        }

        public int getIs_play_all() {
            return is_play_all;
        }

        public void setIs_play_all(int is_play_all) {
            this.is_play_all = is_play_all;
        }

        public static class MzpriceBean {
            /**
             * oriPrice : 0
             * vipPrice : null
             * disPrice : null
             * discount : 10
             * dis_type : 0
             * price : 0
             */

            private String oriPrice;
            private Object vipPrice;
            private Object disPrice;
            private String discount;
            private String dis_type;
            private String price;

            public String getOriPrice() {
                return oriPrice;
            }

            public void setOriPrice(String oriPrice) {
                this.oriPrice = oriPrice;
            }

            public Object getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(Object vipPrice) {
                this.vipPrice = vipPrice;
            }

            public Object getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(Object disPrice) {
                this.disPrice = disPrice;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getDis_type() {
                return dis_type;
            }

            public void setDis_type(String dis_type) {
                this.dis_type = dis_type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
