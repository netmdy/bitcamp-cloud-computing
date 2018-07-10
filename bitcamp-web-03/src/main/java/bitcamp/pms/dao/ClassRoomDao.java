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
    
    String jdbcUrl, username, password;
    
    public ClassRoomDao(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public List<ClassRoom> selectList() throws Exception{

        try (Connection con = DriverManager.getConnection(jdbcUrl,username,password);
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

        try (Connection con = DriverManager.getConnection(jdbcUrl,username,password);
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
    
    public void update(ClassRoom cr) throws Exception{
        
        try (Connection con = DriverManager.getConnection(jdbcUrl,username,password);
                PreparedStatement stmt = con.prepareStatement(
                    "update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?");) {
                
                stmt.setString(1, cr.getTitle());
                stmt.setDate(2, cr.getStartDate());
                stmt.setDate(3, cr.getEndDate());
                stmt.setString(4, cr.getRoom());
                stmt.setInt(5, cr.getCrno());
                
                stmt.executeUpdate();
                    
            }
        }

    public void delete(int no) throws Exception{
        
        try (Connection con = DriverManager.getConnection(jdbcUrl,username,password);
                    PreparedStatement stmt = con.prepareStatement(
                            "delete from pms2_classroom where crno=?");) {
                
                stmt.setInt(1, no);
                
                stmt.executeUpdate();
        
            }
        
    }
    
    public void insert(ClassRoom cr) throws Exception{
        
        try (Connection con = DriverManager.getConnection(jdbcUrl,username,password);
                    PreparedStatement stmt = con.prepareStatement(
                            "insert into pms2_classroom(titl,sdt,edt,room) values(?,?,?,?)");) {
                
                stmt.setString(1, cr.getTitle());
                stmt.setDate(2, cr.getStartDate());
                stmt.setDate(3, cr.getEndDate());
                stmt.setString(4, cr.getRoom());
                
                stmt.executeUpdate();
                }
            
    }
}
