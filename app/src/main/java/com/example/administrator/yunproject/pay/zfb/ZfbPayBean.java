package com.example.administrator.yunproject.pay.zfb;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ZfbPayBean {
    private String body;//订单内容
    public String subject;//标题
    public String timeout_express;//超时时间
    private String out_trade_no;//设置唯一订单号
    private String total_amount;//金额;
    private String goods_type;//商品主类型：0—虚拟类商品，1—实物类商品


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }
}
