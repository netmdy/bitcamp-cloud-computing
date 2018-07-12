package bitcamp.pms.controller.classroom;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

public class ClassroomListController {

    ClassRoomDao cRoom;
    
    public ClassroomListController(ClassRoomDao cRoom) {
        super();
        this.cRoom = cRoom;
    }

    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // DB에서 가져올 테이터의 페이지 정보
        HashMap<String,Object> params =new HashMap<>();
        if (request.getParameter("page") != null &&
                request.getParameter("size")!=null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page -1) * size);
            params.put("pageSize", size);
        }
           
            List<ClassRoom> list = cRoom.selectList(params);
            request.setAttribute("list", list);
            return "/classroom/list.jsp";
    }
    
}