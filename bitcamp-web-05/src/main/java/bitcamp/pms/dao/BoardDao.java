package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Board;

public class BoardDao {

    SqlSessionFactory sqlsessionFactory;
    
    public BoardDao(SqlSessionFactory sqlsessionFactory) {
        this.sqlsessionFactory = sqlsessionFactory;
    }
    
    public List<Board> selectList() throws Exception{
        
        try (SqlSession ss = sqlsessionFactory.openSession()){
            
            return ss.selectList("board.selectList");
        }
    }
    
    public Board selectOne(int no) throws Exception{

        try (SqlSession ss = sqlsessionFactory.openSession()){
            return ss.selectOne("board.selectOne", no);
        }  
    }   
    
    public int update(Board board) throws Exception{
            
        try (SqlSession ss = sqlsessionFactory.openSession()){
            
            int count = ss.update("board.update", board);
            ss.commit();
            return count; 
              
        }
   }
    
    public int delete(int no) throws Exception{

        try (SqlSession ss = sqlsessionFactory.openSession()){

            int count = ss.delete("board.delete", no);
            ss.commit();
            return count;
        }
    }
    
    public int insert(Board board) throws Exception{
        try (SqlSession ss = sqlsessionFactory.openSession()){
            int count = ss.insert("board.insert", board);
            ss.commit();
            return count;
            
        }
    }
}
