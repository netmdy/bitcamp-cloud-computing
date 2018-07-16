package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Board;

@Repository
public class BoardDao {

    SqlSessionFactory sqlsessionFactory;
    
    @Autowired
    public void setSqlsessionFactory(SqlSessionFactory sqlsessionFactory) {
        this.sqlsessionFactory = sqlsessionFactory;
    }

    public BoardDao(SqlSessionFactory sqlsessionFactory) {
        this.sqlsessionFactory = sqlsessionFactory;
    }
    
    public BoardDao() {}
    
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
