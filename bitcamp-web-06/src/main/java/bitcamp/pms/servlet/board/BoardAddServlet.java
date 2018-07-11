package bitcamp.pms.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/board/form.jsp");
    }


    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        // 클라이언트가 보낸 데이터가 어떤 문자표를 사용해서 작성한지 알아야만 
        // String 객체(UTF-16)로 값을 꺼낼 수 있다. 
//        request.setCharacterEncoding("UTF-8");
        
        // 지정된 시간이 경과하면 특정 서블릿을 요청하도록 태그를 삽입!
        // => 웹브라우저는 meta 태그의 내용대로 동작한다.
        //    content='경과시간(초);url=요청할URL'

//        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        try {
                Board board = new Board();
                
                board.setTitl(request.getParameter("title"));
                board.setCont(request.getParameter("content"));
                
                BoardDao boardDao = (BoardDao) getServletContext().getAttribute("boardDao");
                      
                boardDao.insert(board);
                request.setAttribute("view", "redirect:list");
                
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }

}