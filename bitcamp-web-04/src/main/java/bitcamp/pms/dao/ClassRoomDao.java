package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.pms.domain.ClassRoom;

public class ClassRoomDao {
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public List<ClassRoom> selectList() throws Exception{

        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.209.8.213:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("select crno,titl,sdt,edt,room from pms2_classroom");
                ResultSet rs = stmt.executeQuery();) {
            List<ClassRoom> list = new ArrayList<>();
            while (rs.next()) {

                ClassRoom cr = new ClassRoom();
                cr.setCrno(rs.getInt("crno"));
                cr.setTitle(rs.getString("titl"));
                cr.setStartDate(rs.getDate("sdt"));
                cr.setEndDate(rs.getDate("edt"));
                cr.setRoom(rs.getString("room"));

                list.add(cr);
            }
            return list;
        }
    }
    
    public ClassRoom selectOne(int no) throws Exception {

            try (Connection con = DriverManager.getConnection("jdbc:mysql://13.209.8.213:3306/studydb", "study",
                    "1111");
                 PreparedStatement stmt = con.prepareStatement
                         ("select crno,titl,sdt,edt,room from pms2_classroom where crno=?");) {

                stmt.setInt(1, no);

                try (ResultSet rs = stmt.executeQuery();) {
                    if (!rs.next()) {
                        return null;
                    } 
                        ClassRoom cr = new ClassRoom();
                        cr.setCrno(rs.getInt("crno"));
                        cr.setTitle(rs.getString("titl"));
                        cr.setStartDate(rs.getDate("sdt"));
                        cr.setEndDate(rs.getDate("edt"));
                        cr.setRoom(rs.getString("room"));

                        return cr;
                }
        }
    }
}
