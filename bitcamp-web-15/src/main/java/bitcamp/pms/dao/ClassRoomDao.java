package bitcamp.pms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.ClassRoom;

public class ClassRoomDao {

    SqlSessionFactory sqlSessionFactory;

    public ClassRoomDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<ClassRoom> selectList(HashMap<String, Object> params) throws Exception {

        try (SqlSession ss = sqlSessionFactory.openSession()) {
            return ss.selectList("classroom.selectList", params);
        }
    }

    public ClassRoom selectOne(int no) throws Exception {

        try (SqlSession ss = sqlSessionFactory.openSession()) {
            return ss.selectOne("classroom.selectOne", no);
        }
    }

    public void update(ClassRoom cr) throws Exception {

        try (SqlSession ss = sqlSessionFactory.openSession()) {
            ss.update("classroom.update", cr);
            ss.commit();
        }
    }

    public void delete(int no) throws Exception {

        try (SqlSession ss = sqlSessionFactory.openSession()) {
            ss.delete("classroom.delete", no);
            ss.commit();
        }
    }

    public void insert(ClassRoom cr) throws Exception {

        try (SqlSession ss = sqlSessionFactory.openSession()) {
            ss.insert("classroom.insert", cr);
            ss.commit();
        }
    }
}
