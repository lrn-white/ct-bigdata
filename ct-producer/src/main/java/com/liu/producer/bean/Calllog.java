package com.liu.producer.bean;

/**
 * 通话记录
 */
public class Calllog {
    private String call1;
    private String call2;
    private String callTime;
    private String duration;

    public String getCall1() {
        return call1;
    }

    public void setCall1(String call1) {
        this.call1 = call1;
    }

    public String getCall2() {
        return call2;
    }

    public void setCall2(String call2) {
        this.call2 = call2;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Calllog(String call1, String call2, String callTime, String duration) {
        this.call1 = call1;
        this.call2 = call2;
        this.callTime = callTime;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return call1 + "\t" + call2 + "\t" + callTime + "\t" + duration;
    }
}
