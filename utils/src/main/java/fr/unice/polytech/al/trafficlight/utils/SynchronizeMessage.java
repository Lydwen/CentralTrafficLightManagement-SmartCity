package fr.unice.polytech.al.trafficlight.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nathael on 13/01/17.
 */
public class SynchronizeMessage {
    private Date date;
    private RuleGroup ruleGroup; // this ruleGroup should Switch to Green state at the specified date

    /**
     *  Create a synchronizeMessage
     * @param ruleGroup This ruleGroup should Switch to Green state at the specified date
     * @param date Formatted as "yyyy-MM-dd HH:mm:ss"
     */
    public SynchronizeMessage(RuleGroup ruleGroup, String date) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");

        this.date = dt.parse(date);
        this.ruleGroup = ruleGroup;
    }

    /**
     *  Create a synchronizeMessage
     * @param ruleGroup This ruleGroup should Switch to Green state at the specified date
     * @param date As Date object
     */
    public SynchronizeMessage(RuleGroup ruleGroup, Date date) {
        this.date = (Date) date.clone();
        this.ruleGroup = ruleGroup;
    }

    public Date getDate() {
        return date;
    }

    public RuleGroup getRuleGroup() {
        return ruleGroup;
    }
}
