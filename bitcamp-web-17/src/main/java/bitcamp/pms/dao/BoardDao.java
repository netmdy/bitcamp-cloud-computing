package bitcamp.pms.dao;

import java.util.List;

import bitcamp.pms.domain.Board;

public interface BoardDao {

    public List<Board> selectList();
    
    public Board selectOne(int no);    
    public int update(Board board) ;
    
    public int delete(int no) ;    
    public int insert(Board board);
}
