package com.bingkong.bkbase.utils;

import java.util.Date;

public class RetryTimeMgr {
    private Date mLastSendTime = new Date();
    private Date mSecLastSendTime = new Date();

    public void setLastSendTime(Date date) {
        mSecLastSendTime = mLastSendTime;
        mLastSendTime = date;
    }

    /**
     * Return wait mm sec time before send next retry command.
     **/
    public long getDelayedSendTime() {
        long duration = CommentTime.getDuration(mSecLastSendTime, mLastSendTime);
        if (duration < 2) {
            duration = 2;
        } else {
            duration = 2 * duration;
        }
        return duration * 1000;
    }

    public static long getRandtime(long duration) {
        return (long) ((double) duration * Math.random());
    }
}
