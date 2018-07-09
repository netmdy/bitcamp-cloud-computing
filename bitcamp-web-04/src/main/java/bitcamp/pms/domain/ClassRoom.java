package bitcamp.pms.domain;

import java.sql.Date;

public class ClassRoom {
    
    String title, room;
    Date startDate,endDate;
    int crno;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
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
    public int getCrno() {
        return crno;
    }
    public void setCrno(int crno) {
        this.crno = crno;
    }
    
}