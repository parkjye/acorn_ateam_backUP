package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.dto.*;
import app.util.*;

public class BoardDao {
	private static BoardDao dao;
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(dao==null) {
			dao=new BoardDao();
		}
		return dao;
	}

	// ---------- 키워드 검색 메소드 ----------
	// 1. 제목
	public List<BoardDto> getWordTitle(BoardDto dto){
		
		List<BoardDto> list=new ArrayList<>();
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 준비하기
			String sql = "select * from (select result1.*, rownum as rnum"
					+ " from (select board_num, users_id, board_title, board_content,"
					+ " board_view, board_comment_count, board_up, board_down,"
					+ " to_char(board_date, 'yy/mm/dd hh24:mi:ss') as board_date"
					+ " from tb_board where board_title like '%'|| ? ||'%' order by board_num desc) result1)"
					+ " where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//sql 문에 ? 에 바인딩할 값이 있으면 바인딩하고
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setInt(2, dto.getStartRowNum());
			pstmt.setInt(3, dto.getEndRowNum());
			
			//select 문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();
			
			//반복문 돌면서 결과 값 추출하기
			while (rs.next()) {
				
				BoardDto tmp = new BoardDto();
				
				tmp.setBoard_num(rs.getInt("board_num"));
				tmp.setUsers_id(rs.getString("users_id"));
				tmp.setBoard_title(rs.getString("board_title"));
				tmp.setBoard_content(rs.getString("board_content"));
				tmp.setBoard_view(rs.getInt("board_view"));
				tmp.setBoard_comment_count(rs.getInt("board_comment_count"));
				tmp.setBoard_up(rs.getInt("board_up"));
				tmp.setBoard_down(rs.getInt("board_down"));
				tmp.setBoard_date(rs.getString("board_date"));
				
				list.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
		}
	
	// 1-1. 제목 (row 갯수)
	public int getWordTitleRow(BoardDto dto) {
		//전체 row의 갯수를 담을 지역변수
		int count = 0;
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();
	
			//실행할 sql 문 준비하기
			/*
			 * ROWNUM중에서 가장 큰 수 select하면 전체 row의 갯수가 된다.
			 * 만약 row가 하나도 없으면 null을 리턴하기 때문에
			 * null인 경우 0으로 바꿔줘야한다. NVL(max(ROWNUM), 0)
			 */
			String sql = "select NVL(max(ROWNUM), 0) as board_num"
					+ " from tb_board where board_title like '%'|| ? || '%'";
			
			pstmt = conn.prepareStatement(sql);
	
			//sql문 values내의 ?에 바인딩
			pstmt.setString(1, dto.getBoard_title());
	
			//select문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();
	
			//반복문 돌면서 결과 값 추출하기
			if (rs.next()) {
				count=rs.getInt("board_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection반납
			} catch (Exception e) {
				}
		}
		return count;
	}
	
	// 2. 작성자
	public List<BoardDto> getWordUsers(BoardDto dto){
		List<BoardDto> list=new ArrayList<>();
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 준비하기
			String sql = "select * from (select result1.*, rownum as rnum"
					+ " from (select board_num, users_id, board_title, board_content,"
					+ " board_view, board_comment_count, board_up, board_down,"
					+ " to_char(board_date, 'yy/mm/dd hh24:mi:ss') as board_date"
					+ " from tb_board where users_id like '%'|| ? ||'%' order by board_num desc) result1)"
					+ " where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//sql 문에 ? 에 바인딩할 값이 있으면 바인딩하고
			pstmt.setString(1, dto.getUsers_id());
			pstmt.setInt(2, dto.getStartRowNum());
			pstmt.setInt(3, dto.getEndRowNum());
			
			//select 문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();
			
			//반복문 돌면서 결과 값 추출하기
			while (rs.next()) {
				
				BoardDto tmp = new BoardDto();
				
				tmp.setBoard_num(rs.getInt("board_num"));
				tmp.setUsers_id(rs.getString("users_id"));
				tmp.setBoard_title(rs.getString("board_title"));
				tmp.setBoard_content(rs.getString("board_content"));
				tmp.setBoard_view(rs.getInt("board_view"));
				tmp.setBoard_comment_count(rs.getInt("board_comment_count"));
				tmp.setBoard_up(rs.getInt("board_up"));
				tmp.setBoard_down(rs.getInt("board_down"));
				tmp.setBoard_date(rs.getString("board_date"));
				
				list.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;	
	}
	
	// 2-1. 작성자 (row 갯수)
	public int getWordUsersRow(BoardDto dto) {
		//전체 row의 갯수를 담을 지역변수
		int count=0;
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			//실행할 sql 문 준비하기
			/*
			 * ROWNUM중에서 가장 큰 수 select하면 전체 row의 갯수가 된다.
			 * 만약 row가 하나도 없으면 null을 리턴하기 때문에
			 * null인 경우 0으로 바꿔줘야한다. NVL(max(ROWNUM), 0)
			 */
			String sql = "select NVL(max(ROWNUM), 0) as board_num"
					+ " from tb_board where users_id like '%'|| ? || '%'";
			pstmt = conn.prepareStatement(sql);

			//sql문 values내의 ?에 바인딩
			pstmt.setString(1, dto.getUsers_id());

			//select문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();

			//반복문 돌면서 결과 값 추출하기
			if (rs.next()) {
				count=rs.getInt("board_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection반납
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	// 3. 제목+작성자
	public List<BoardDto> getWordTitleUsers(BoardDto dto) {
		
		List<BoardDto> list=new ArrayList<>();
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 준비하기
			String sql = "select * from (select result1.*, rownum as rnum"
					+ " from (select board_num, users_id, board_title, board_content,"
					+ " board_view, board_comment_count, board_up, board_down,"
					+ " to_char(board_date, 'yy/mm/dd hh24:mi:ss') as board_date"
					+ " from tb_board where users_id like '%'|| ? ||'%' OR"
					+ " board_title like '%'|| ? ||'%' order by board_num desc) result1)"
					+ " where rnum between ? and ?";

			pstmt = conn.prepareStatement(sql);
			
			//sql 문에 ? 에 바인딩할 값이 있으면 바인딩하고
			pstmt.setString(1, dto.getUsers_id());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setInt(3, dto.getStartRowNum());
			pstmt.setInt(4, dto.getEndRowNum());
			
			//select 문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();
			
			//반복문 돌면서 결과 값 추출하기
			while (rs.next()) {
				
				BoardDto tmp = new BoardDto();
				
				tmp.setBoard_num(rs.getInt("board_num"));
				tmp.setUsers_id(rs.getString("users_id"));
				tmp.setBoard_title(rs.getString("board_title"));
				tmp.setBoard_content(rs.getString("board_content"));
				tmp.setBoard_view(rs.getInt("board_view"));
				tmp.setBoard_comment_count(rs.getInt("board_comment_count"));
				tmp.setBoard_up(rs.getInt("board_up"));
				tmp.setBoard_down(rs.getInt("board_down"));
				tmp.setBoard_date(rs.getString("board_date"));
				
				list.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오라클 sql문법 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	// 3-1. 제목+작성자 (row 갯수)
	public int getWordTitleUsersRow(BoardDto dto) {
		//전체 row의 갯수를 담을 지역변수
		int count=0;
		
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			//실행할 sql 문 준비하기
			/*
			 * ROWNUM중에서 가장 큰 수 select하면 전체 row의 갯수가 된다.
			 * 만약 row가 하나도 없으면 null을 리턴하기 때문에
			 * null인 경우 0으로 바꿔줘야한다. NVL(max(ROWNUM), 0)
			 */
			String sql = "select NVL(max(ROWNUM), 0) as board_num"
					+ " from tb_board"
					+ "	where board_title like '%'|| ? || '%' or users_id like '%' || ? || '%'";			
			pstmt = conn.prepareStatement(sql);

			//sql문 values내의 ?에 바인딩
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getUsers_id());

			//select문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();

			//반복문 돌면서 결과 값 추출하기
			if (rs.next()) {
				count=rs.getInt("board_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection반납
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	
	//return 글 목록 (+페이징)
	public List<BoardDto> getListofReviews(BoardDto dto) {
		
		List<BoardDto> list = new ArrayList<>();
		
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//Connection 객체의 참조값 얻어오기 
			conn = new DbcpBean().getConn();
			
			//실행할 sql 문 준비하기 (글 내용을 제외한 모든 요소, 글번호 기준으로 desc)
			String sql = "SELECT * from (select result1.*, rownum as rnum"
					+ " from (select board_num, users_id, board_title,"
					+ " board_view, board_comment_count, board_up, board_down,"
					+ " to_char(board_date, 'yy/mm/dd hh24:mi:ss') as board_date"
					+ " from tb_board order by board_num desc) result1)"
					+ " where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//sql 문에 ? 에 바인딩 
			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());
			
			//select 문 수행하고 결과 받아오기 
			rs = pstmt.executeQuery();
			
			//반복문 돌면서 결과 값 추출하기 
			while(rs.next()) {
				BoardDto tmp = new BoardDto();
				
				tmp.setBoard_num(rs.getInt("board_num"));
				tmp.setUsers_id(rs.getString("users_id"));
				tmp.setBoard_title(rs.getString("board_title"));
				tmp.setBoard_view(rs.getInt("board_view"));
				tmp.setBoard_comment_count(rs.getInt("board_comment_count"));
				tmp.setBoard_up(rs.getInt("board_up"));
				tmp.setBoard_down(rs.getInt("board_down"));
				tmp.setBoard_date(rs.getString("board_date"));
				
				list.add(tmp);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
		}

	//return 글
	public BoardDto getReview(int board_num) {
		
		BoardDto dto = null;
		
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//Connection 객체의 참조값 얻어오기 
			conn = new DbcpBean().getConn();

			//실행할 sql 문 준비하기
			String sql = "select board_num, users_id, board_title, board_content,"
					+ " board_view, board_up, board_down,"
					+ " to_char(board_date, 'yy/mm/dd hh24:mi:ss') as board_date"
					+ " from tb_board"
					+ " where board_num=?";

			pstmt = conn.prepareStatement(sql);

			//sql 문에 ? 에 바인딩 
			pstmt.setInt(1, board_num);

			//select 문 수행하고 결과 받아오기 
			rs = pstmt.executeQuery();

			//반복문 돌면서 결과 값 추출하기 
			while(rs.next()) {
				dto = new BoardDto();
				dto.setBoard_num(board_num);
				dto.setUsers_id(rs.getString("users_id"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_view(rs.getInt("board_view"));
				dto.setBoard_up(rs.getInt("board_up"));
				dto.setBoard_down(rs.getInt("board_down"));
				dto.setBoard_date(rs.getString("board_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}
		
	//return 전체 글 갯수
	public int getCount() {
		//전체 row의 갯수를 담을 지역변수
		int count=0;

		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			//실행할 sql 문 준비하기
			String sql = "select NVL(max(ROWNUM), 0) as num"
					+ " from tb_board";
			pstmt = conn.prepareStatement(sql);

			//select문 수행하고 결과 받아오기
			rs = pstmt.executeQuery();

			//반복문 돌면서 결과 값 추출하기
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection반납
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	//조회수 +1
	public int addBoardView(BoardDto dto) {
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int viewCnt = dto.getBoard_view();
		
		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			String sql = "update tb_board"
					+ " set board_view = board_view+1"
					+ " where board_num=?";

			pstmt = conn.prepareStatement(sql);

			//sql문에 ? 에 바인딩 
			pstmt.setInt(1, dto.getBoard_num());

			//sql문 수행하고 update or insert or delete된 row의 갯수 리턴받기
			viewCnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection 반납하기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return viewCnt;
	}

	//글 작성
	public boolean writeReview(BoardDto dto) {
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;

		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();
			
			//조회수, 댓글수, 추천, 비추천 default=0; 
			//board_num은 trigger구현으로 sql문에서 작성안함
			String sql = "insert into tb_board"
					+ " (users_id, board_title, board_content,"
					+ " board_view, board_comment_count, board_up, board_down,"
					+ " board_date)"
					+ " values(?, ?, ?, 0, 0, 0, 0, sysdate)";

			pstmt = conn.prepareStatement(sql);

			//sql문에 ? 에 바인딩 
			pstmt.setString(1, dto.getUsers_id());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_content());

			//sql문 수행하고 update or insert or delete된 row의 갯수 리턴받기
			flag = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection 반납하기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}	
	}

	//글 수정
	public boolean modifyReview(BoardDto dto) {
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;

		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			String sql = "update tb_board"
					+ " set board_title=?, board_content=?"
					+ " where board_num=?";

			pstmt = conn.prepareStatement(sql);

			//sql문에 ? 에 바인딩 
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_content());
			pstmt.setInt(3, dto.getBoard_num());

			//sql문 수행하고 update or insert or delete된 row의 갯수 리턴받기
			flag = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection 반납하기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	//글 삭제
	public boolean deleteReview(int board_num) {
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;

		try {
			//Connection Pool에서 Connection 객체를 하나 가지고 온다.
			conn = new DbcpBean().getConn();

			String sql = "delete from tb_board where board_num=?";

			pstmt = conn.prepareStatement(sql);

			//sql문에 ? 에 바인딩 
			pstmt.setInt(1, board_num);

			//sql문 수행하고 update or insert or delete된 row의 갯수 리턴받기
			flag = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); //Connection 반납하기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

}//BoardDao
