package example.com.vp.model;

import javax.xml.datatype.Duration;

public class RollingViewPagerConfig {
    private int DURATION_TIME;
    private int DELAYED_TIME;
    private int TOTAL_PAGE;
    public RollingViewPagerConfig(int DURATION_TIME, int DELAYED_TIME, int TOTAL_PAGE){
        this.DURATION_TIME = DURATION_TIME;
        this.DELAYED_TIME = DELAYED_TIME;
        this.TOTAL_PAGE = TOTAL_PAGE;
    }

    public int getDURATION_TIME() {
        return DURATION_TIME;
    }

    public void setDURATION_TIME(int DURATION_TIME) {
        this.DURATION_TIME = DURATION_TIME;
    }

    public int getDELAYED_TIME() {
        return DELAYED_TIME;
    }

    public void setDELAYED_TIME(int DELAYED_TIME) {
        this.DELAYED_TIME = DELAYED_TIME;
    }

    public int getTOTAL_PAGE() {
        return TOTAL_PAGE;
    }

    public void setTOTAL_PAGE(int TOTAL_PAGE) {
        this.TOTAL_PAGE = TOTAL_PAGE;
    }
}
