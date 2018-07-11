package bitcamp.pms.domain;

import java.util.Date;

public class Board {

    int bno;
    String titl, cont;
    Date cdt;

    public int getBno() {
        return bno;
    }
    public void setBno(int bno) {
        this.bno = bno;
    }
    public String getTitl() {
        return titl;
    }
    public void setTitl(String titl) {
        this.titl = titl;
    }
    public String getCont() {
        return cont;
    }
    public void setCont(String cont) {
        this.cont = cont;
    }
    public Date getCdt() {
        return cdt;
    }
    public void setCdt(Date cdt) {
        this.cdt = cdt;
    }
    
    
}
