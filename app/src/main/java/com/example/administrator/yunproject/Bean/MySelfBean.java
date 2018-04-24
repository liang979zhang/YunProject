package com.example.administrator.yunproject.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class MySelfBean {

    /**
     * data : {"uid":"41","login":"17606161017","login_salt":"81460","uname":"张峰","email":null,"phone":"17606161017","sex":"女","location":"","is_audit":"1","is_active":"1","is_init":"1","ctime":"1522286637","identity":"1","api_key":null,"domain":"","province":"0","city":"0","area":"0","reg_ip":"127.0.0.1","lang":"zh-cn","timezone":"PRC","is_del":"0","first_letter":"Z","intro":"","profession":"","last_login_time":"1523084474","last_feed_id":"0","last_post_time":"0","search_key":"张锋 zhangfeng","invite_code":null,"feed_email_time":null,"send_email_time":null,"my_college":"0","signup_college":"0","study_phase":"0","my_study_level":"0","find_study_level":"0","mail_activate":"0","phone_activate":"0","avatar_original":"http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_big":"http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_middle":"http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/middle.jpg","avatar_small":"http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/small.jpg","avatar_tiny":"http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/tiny.jpg","avatar_url":"http://192.168.252.111:88/index.php?app=public&mod=Attach&act=avatar&uid=41","space_url":"http://192.168.252.111:88/app/userShow/41","space_link":"<a href='http://192.168.252.111:88/app/userShow/41' target='_blank' uid='41' event-node='face_card'>张峰<\/a>","space_link_no":"<a href='http://192.168.252.111:88/app/userShow/41' title='张峰' target='_blank'>张峰<\/a>","medals":[],"api_user_group":[],"user_group":[],"group_icon":"","profile":{"email":{"name":"邮箱","value":null},"intro":{"name":null,"value":""},"work_position":{"name":null,"value":""},"mobile":{"name":null,"value":""},"tel":{"name":null,"value":""},"work_director":{"name":null,"value":""},"department":{"name":null,"value":""}},"count_info":{"following_count":0,"follower_count":0,"feed_count":0,"favorite_count":0,"unread_atme":0,"weibo_count":0},"user_tag":"","follow_state":{"following":0,"follower":0},"last_feed":[],"isMyContact":0}
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
         * uid : 41
         * login : 17606161017
         * login_salt : 81460
         * uname : 张峰
         * email : null
         * phone : 17606161017
         * sex : 女
         * location :
         * is_audit : 1
         * is_active : 1
         * is_init : 1
         * ctime : 1522286637
         * identity : 1
         * api_key : null
         * domain :
         * province : 0
         * city : 0
         * area : 0
         * reg_ip : 127.0.0.1
         * lang : zh-cn
         * timezone : PRC
         * is_del : 0
         * first_letter : Z
         * intro :
         * profession :
         * last_login_time : 1523084474
         * last_feed_id : 0
         * last_post_time : 0
         * search_key : 张锋 zhangfeng
         * invite_code : null
         * feed_email_time : null
         * send_email_time : null
         * my_college : 0
         * signup_college : 0
         * study_phase : 0
         * my_study_level : 0
         * find_study_level : 0
         * mail_activate : 0
         * phone_activate : 0
         * avatar_original : http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/big.jpg
         * avatar_big : http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/big.jpg
         * avatar_middle : http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/middle.jpg
         * avatar_small : http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/small.jpg
         * avatar_tiny : http://192.168.252.111:88/addons/theme/stv1/_static/image/noavatar/tiny.jpg
         * avatar_url : http://192.168.252.111:88/index.php?app=public&mod=Attach&act=avatar&uid=41
         * space_url : http://192.168.252.111:88/app/userShow/41
         * space_link : <a href='http://192.168.252.111:88/app/userShow/41' target='_blank' uid='41' event-node='face_card'>张峰</a>
         * space_link_no : <a href='http://192.168.252.111:88/app/userShow/41' title='张峰' target='_blank'>张峰</a>
         * medals : []
         * api_user_group : []
         * user_group : []
         * group_icon :
         * profile : {"email":{"name":"邮箱","value":null},"intro":{"name":null,"value":""},"work_position":{"name":null,"value":""},"mobile":{"name":null,"value":""},"tel":{"name":null,"value":""},"work_director":{"name":null,"value":""},"department":{"name":null,"value":""}}
         * count_info : {"following_count":0,"follower_count":0,"feed_count":0,"favorite_count":0,"unread_atme":0,"weibo_count":0}
         * user_tag :
         * follow_state : {"following":0,"follower":0}
         * last_feed : []
         * isMyContact : 0
         */

        private String uid;
        private String login;
        private String login_salt;
        private String uname;
        private Object email;
        private String phone;
        private String sex;
        private String location;
        private String is_audit;
        private String is_active;
        private String is_init;
        private String ctime;
        private String identity;
        private Object api_key;
        private String domain;
        private String province;
        private String city;
        private String area;
        private String reg_ip;
        private String lang;
        private String timezone;
        private String is_del;
        private String first_letter;
        private String intro;
        private String profession;
        private String last_login_time;
        private String last_feed_id;
        private String last_post_time;
        private String search_key;
        private Object invite_code;
        private Object feed_email_time;
        private Object send_email_time;
        private String my_college;
        private String signup_college;
        private String study_phase;
        private String my_study_level;
        private String find_study_level;
        private String mail_activate;
        private String phone_activate;
        private String avatar_original;
        private String avatar_big;
        private String avatar_middle;
        private String avatar_small;
        private String avatar_tiny;
        private String avatar_url;
        private String space_url;
        private String space_link;
        private String space_link_no;
        private String group_icon;
        private ProfileBean profile;
        private CountInfoBean count_info;
        private String user_tag;
        private FollowStateBean follow_state;
        private int isMyContact;
        private List<?> medals;
        private List<?> api_user_group;
        private List<?> user_group;
        private List<?> last_feed;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getLogin_salt() {
            return login_salt;
        }

        public void setLogin_salt(String login_salt) {
            this.login_salt = login_salt;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIs_audit() {
            return is_audit;
        }

        public void setIs_audit(String is_audit) {
            this.is_audit = is_audit;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }

        public String getIs_init() {
            return is_init;
        }

        public void setIs_init(String is_init) {
            this.is_init = is_init;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public Object getApi_key() {
            return api_key;
        }

        public void setApi_key(Object api_key) {
            this.api_key = api_key;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getFirst_letter() {
            return first_letter;
        }

        public void setFirst_letter(String first_letter) {
            this.first_letter = first_letter;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_feed_id() {
            return last_feed_id;
        }

        public void setLast_feed_id(String last_feed_id) {
            this.last_feed_id = last_feed_id;
        }

        public String getLast_post_time() {
            return last_post_time;
        }

        public void setLast_post_time(String last_post_time) {
            this.last_post_time = last_post_time;
        }

        public String getSearch_key() {
            return search_key;
        }

        public void setSearch_key(String search_key) {
            this.search_key = search_key;
        }

        public Object getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(Object invite_code) {
            this.invite_code = invite_code;
        }

        public Object getFeed_email_time() {
            return feed_email_time;
        }

        public void setFeed_email_time(Object feed_email_time) {
            this.feed_email_time = feed_email_time;
        }

        public Object getSend_email_time() {
            return send_email_time;
        }

        public void setSend_email_time(Object send_email_time) {
            this.send_email_time = send_email_time;
        }

        public String getMy_college() {
            return my_college;
        }

        public void setMy_college(String my_college) {
            this.my_college = my_college;
        }

        public String getSignup_college() {
            return signup_college;
        }

        public void setSignup_college(String signup_college) {
            this.signup_college = signup_college;
        }

        public String getStudy_phase() {
            return study_phase;
        }

        public void setStudy_phase(String study_phase) {
            this.study_phase = study_phase;
        }

        public String getMy_study_level() {
            return my_study_level;
        }

        public void setMy_study_level(String my_study_level) {
            this.my_study_level = my_study_level;
        }

        public String getFind_study_level() {
            return find_study_level;
        }

        public void setFind_study_level(String find_study_level) {
            this.find_study_level = find_study_level;
        }

        public String getMail_activate() {
            return mail_activate;
        }

        public void setMail_activate(String mail_activate) {
            this.mail_activate = mail_activate;
        }

        public String getPhone_activate() {
            return phone_activate;
        }

        public void setPhone_activate(String phone_activate) {
            this.phone_activate = phone_activate;
        }

        public String getAvatar_original() {
            return avatar_original;
        }

        public void setAvatar_original(String avatar_original) {
            this.avatar_original = avatar_original;
        }

        public String getAvatar_big() {
            return avatar_big;
        }

        public void setAvatar_big(String avatar_big) {
            this.avatar_big = avatar_big;
        }

        public String getAvatar_middle() {
            return avatar_middle;
        }

        public void setAvatar_middle(String avatar_middle) {
            this.avatar_middle = avatar_middle;
        }

        public String getAvatar_small() {
            return avatar_small;
        }

        public void setAvatar_small(String avatar_small) {
            this.avatar_small = avatar_small;
        }

        public String getAvatar_tiny() {
            return avatar_tiny;
        }

        public void setAvatar_tiny(String avatar_tiny) {
            this.avatar_tiny = avatar_tiny;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getSpace_url() {
            return space_url;
        }

        public void setSpace_url(String space_url) {
            this.space_url = space_url;
        }

        public String getSpace_link() {
            return space_link;
        }

        public void setSpace_link(String space_link) {
            this.space_link = space_link;
        }

        public String getSpace_link_no() {
            return space_link_no;
        }

        public void setSpace_link_no(String space_link_no) {
            this.space_link_no = space_link_no;
        }

        public String getGroup_icon() {
            return group_icon;
        }

        public void setGroup_icon(String group_icon) {
            this.group_icon = group_icon;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public CountInfoBean getCount_info() {
            return count_info;
        }

        public void setCount_info(CountInfoBean count_info) {
            this.count_info = count_info;
        }

        public String getUser_tag() {
            return user_tag;
        }

        public void setUser_tag(String user_tag) {
            this.user_tag = user_tag;
        }

        public FollowStateBean getFollow_state() {
            return follow_state;
        }

        public void setFollow_state(FollowStateBean follow_state) {
            this.follow_state = follow_state;
        }

        public int getIsMyContact() {
            return isMyContact;
        }

        public void setIsMyContact(int isMyContact) {
            this.isMyContact = isMyContact;
        }

        public List<?> getMedals() {
            return medals;
        }

        public void setMedals(List<?> medals) {
            this.medals = medals;
        }

        public List<?> getApi_user_group() {
            return api_user_group;
        }

        public void setApi_user_group(List<?> api_user_group) {
            this.api_user_group = api_user_group;
        }

        public List<?> getUser_group() {
            return user_group;
        }

        public void setUser_group(List<?> user_group) {
            this.user_group = user_group;
        }

        public List<?> getLast_feed() {
            return last_feed;
        }

        public void setLast_feed(List<?> last_feed) {
            this.last_feed = last_feed;
        }

        public static class ProfileBean {
            /**
             * email : {"name":"邮箱","value":null}
             * intro : {"name":null,"value":""}
             * work_position : {"name":null,"value":""}
             * mobile : {"name":null,"value":""}
             * tel : {"name":null,"value":""}
             * work_director : {"name":null,"value":""}
             * department : {"name":null,"value":""}
             */

            private EmailBean email;
            private IntroBean intro;
            private WorkPositionBean work_position;
            private MobileBean mobile;
            private TelBean tel;
            private WorkDirectorBean work_director;
            private DepartmentBean department;

            public EmailBean getEmail() {
                return email;
            }

            public void setEmail(EmailBean email) {
                this.email = email;
            }

            public IntroBean getIntro() {
                return intro;
            }

            public void setIntro(IntroBean intro) {
                this.intro = intro;
            }

            public WorkPositionBean getWork_position() {
                return work_position;
            }

            public void setWork_position(WorkPositionBean work_position) {
                this.work_position = work_position;
            }

            public MobileBean getMobile() {
                return mobile;
            }

            public void setMobile(MobileBean mobile) {
                this.mobile = mobile;
            }

            public TelBean getTel() {
                return tel;
            }

            public void setTel(TelBean tel) {
                this.tel = tel;
            }

            public WorkDirectorBean getWork_director() {
                return work_director;
            }

            public void setWork_director(WorkDirectorBean work_director) {
                this.work_director = work_director;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public static class EmailBean {
                /**
                 * name : 邮箱
                 * value : null
                 */

                private String name;
                private Object value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getValue() {
                    return value;
                }

                public void setValue(Object value) {
                    this.value = value;
                }
            }

            public static class IntroBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class WorkPositionBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class MobileBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class TelBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class WorkDirectorBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class DepartmentBean {
                /**
                 * name : null
                 * value :
                 */

                private Object name;
                private String value;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class CountInfoBean {
            /**
             * following_count : 0
             * follower_count : 0
             * feed_count : 0
             * favorite_count : 0
             * unread_atme : 0
             * weibo_count : 0
             */

            private int following_count;
            private int follower_count;
            private int feed_count;
            private int favorite_count;
            private int unread_atme;
            private int weibo_count;

            public int getFollowing_count() {
                return following_count;
            }

            public void setFollowing_count(int following_count) {
                this.following_count = following_count;
            }

            public int getFollower_count() {
                return follower_count;
            }

            public void setFollower_count(int follower_count) {
                this.follower_count = follower_count;
            }

            public int getFeed_count() {
                return feed_count;
            }

            public void setFeed_count(int feed_count) {
                this.feed_count = feed_count;
            }

            public int getFavorite_count() {
                return favorite_count;
            }

            public void setFavorite_count(int favorite_count) {
                this.favorite_count = favorite_count;
            }

            public int getUnread_atme() {
                return unread_atme;
            }

            public void setUnread_atme(int unread_atme) {
                this.unread_atme = unread_atme;
            }

            public int getWeibo_count() {
                return weibo_count;
            }

            public void setWeibo_count(int weibo_count) {
                this.weibo_count = weibo_count;
            }
        }

        public static class FollowStateBean {
            /**
             * following : 0
             * follower : 0
             */

            private int following;
            private int follower;

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public int getFollower() {
                return follower;
            }

            public void setFollower(int follower) {
                this.follower = follower;
            }
        }
    }
}
