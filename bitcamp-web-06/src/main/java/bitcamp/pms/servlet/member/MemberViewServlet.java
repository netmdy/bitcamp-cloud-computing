package bitcamp.pms.servlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@SuppressWarnings("serial")
@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            MemberDao memberDao = (MemberDao) getServletContext().getAttribute("memberDao");
            Member member = memberDao.selectOne(id);
            request.setAttribute("member", member); 
            request.setAttribute("view", "/member/view.jsp"); 
            
        } catch (Exception e) {
            request.setAttribute("error", e);           
        }
        
        }
    
}