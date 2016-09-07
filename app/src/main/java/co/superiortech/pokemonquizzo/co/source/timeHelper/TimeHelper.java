package co.superiortech.pokemonquizzo.co.source.timeHelper;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by josemunoz on 9/7/16.
 */
public class TimeHelper extends TimerTask {

    private Date rightNow;
    private Date fiveMins;
    private long remainingMins;
    private long remainingSeconds;

    public TimeHelper(Date now, Date goal) {
        this.rightNow = now;
        this.fiveMins = goal;
        remainingMins = this.calculateMins(now,goal);
        remainingSeconds = this.calculateSecs(now,goal);
    }

    @Override
    public void run() {
        if(rightNow.before(fiveMins)){
            DateUtils.addSeconds(rightNow,1);
            this.setRemainingMins(this.calculateMins(this.rightNow,this.fiveMins));
            this.setRemainingSeconds(this.calculateSecs(this.rightNow,this.fiveMins));
        }
        else{
            this.cancel();
        }
    }

    private long calculateMins(Date start,Date finish){
        return DateUtils.truncate(finish.getTime(),Calendar.MINUTE).getTime()-DateUtils.truncate(start.getTime(),Calendar.MINUTE).getTime()/(1000*60);
    }

    private long calculateSecs(Date start, Date finish){
        return (this.calculateMins(start,finish)*60) - (finish.getTime()-start.getTime()/1000);
    }

    public long getRemainingMins() {
        return remainingMins;
    }

    public void setRemainingMins(long remainingMins) {
        this.remainingMins = remainingMins;
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }
}
