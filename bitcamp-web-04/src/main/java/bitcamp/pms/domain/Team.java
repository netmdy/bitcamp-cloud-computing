package bitcamp.pms.domain;

import java.sql.Date;

public class Team {

    String name,dscrt;
    int max_qty;
    Date startDate, endDate;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDscrt() {
        return dscrt;
    }
    public void setDscrt(String dscrt) {
        this.dscrt = dscrt;
    }
    public int getMax_qty() {
        return max_qty;
    }
    public void setMax_qty(int max_qty) {
        this.max_qty = max_qty;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
