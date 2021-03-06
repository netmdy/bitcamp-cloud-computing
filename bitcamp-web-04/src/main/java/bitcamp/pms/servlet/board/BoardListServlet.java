package bitcamp.pms.servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        // 출력할 때 String 객체의 값(UTF-16)을 어떤 문자표를 사용하여 인코딩해서 보낼 것인지 설정한다.
        // => 반드시 출력 스트림을 얻기 전에 설정해야 한다.
        // filter로 utf-8 처리를 넘김
        response.setContentType("text/html;charset=UTF-8");
        try {
            BoardDao boardDao = new BoardDao(); 
            
             List<Board> list = boardDao.selectList();
             
             request.setAttribute("list", list);
             
             RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
             rd.include(request, response);


        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

    }
    
}

