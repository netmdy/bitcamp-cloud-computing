package bitcamp.pms.servlet.teammember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
@WebServlet("/team/member/add")
public class TeamMemberAddServlet extends HttpServlet {
    
    TeamDao teamDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;

    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String teamName = request.getParameter("teamName");
        String memberId = request.getParameter("memberId");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.printf("<meta http-equiv='Refresh' content='1;url=../view?name=%s'>\n", 
                teamName);
        out.println("<title>팀 회원 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>팀 회원 등록 결과</h1>");
        
        try {
            Team team = teamDao.selectOne(teamName);
            if (team == null) {
                throw new Exception(teamName + " 팀은 존재하지 않습니다.");
            }
            Member member = memberDao.selectOne(memberId);
            if (member == null) {
                throw new Exception(memberId + " 회원은 없습니다.");
            }
            if (teamMemberDao.isExist(teamName, memberId)) {
                throw new Exception("이미 등록된 회원입니다.");
            }
            teamMemberDao.insert(teamName, memberId);
            out.println("<p>팀에 회원을 추가하였습니다.</p>");
            
        } catch (Exception e) {
            out.printf("<p>%s</p>\n", e.getMessage());
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
}
