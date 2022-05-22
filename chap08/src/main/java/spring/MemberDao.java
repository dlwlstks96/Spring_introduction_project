package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    //MemberDao 클래스에 JdbcTemplate 객체 생성
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //임의 클래스 이용해서 RowMapper 객체 생성 및 메소드 구현
    //sql 쿼리문의 ?에 메소드 인자로 넘어온 email 들어감
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                new RowMapper<Member>() { //임의 클래스
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime()
                        );
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                },
                email);
        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {

    }

    public void update(Member member) {

    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime()
                        );
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                });
        return results;
    }

}
