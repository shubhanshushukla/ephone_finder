package com.generalmobi.smart.helmet.events;

/**
 * Created by shubhanshu on 17/1/16.
 */
public class SmsEvent
{
    private String sms;

    public String getSms() {
        return sms;
    }

    public   SmsEvent(String sms) {
        this.sms = sms;
    }

}
