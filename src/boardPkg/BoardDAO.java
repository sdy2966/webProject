package boardPkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import boardPkg.common.DBCon;
//import boardPkg.model.Board;

public class BoardDAO {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = null;

	public void close() {
		if (rs != null) {
			try { // /?��?��처리
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {

			try { // /?��?��처리
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try { // /?��?��처리
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ?���? 리스?��
	public Board[] selectBoards() {
		sql = "select * from board order by board_no desc";
		conn = DBCon.getConnection();
		Board[] boards = new Board[100];
		int i = 0;

		try { // ?��?��처리
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Board brd = new Board();
				brd.setBoardNo(rs.getInt("board_no"));
				brd.setTitle(rs.getString("title"));
				brd.setContent(rs.getNString("content"));
				brd.setWriter(rs.getString("writer"));
				brd.setCreationDate(rs.getString("creation_date"));

				boards[i++] = brd;

			}

		} catch (SQLException e) {
			System.out.println("조회중 에러 발생");
//			e.printStackTrace();
		} finally {
			// ?��?��처리/ ?��?��발생 -> ?��?�� ?��?��?��?�� ?�� ?��?��?�� ?��?���? 구현
			close();
		}

		return boards;

	}

	// 1건조회
	public Board selectBoard(int boardNo) {
		sql = "select * from board where board_no =" + boardNo;
		conn = DBCon.getConnection();
		Board brd = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				brd = new Board(rs.getInt("board_no")//
						, rs.getString("title")//
						, rs.getString("content")//
						, rs.getString("writer")//
						, rs.getString("creation_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return brd;

	}

	// 입력한 결과 반환
	public Board insertBoardResult(Board board) {
		conn = DBCon.getConnection();
		sql = "insert into board values("+ board.getBoardNo() + ",'" + board.getTitle() + "'" + ",'"
				+ board.getContent() + "'" + ",'" + board.getWriter() + "'" + ", sysdate" + ")";
		
		String sql1 = "select * from board where board_no = " + board.getBoardNo();
		Board retunVal = new Board();
		try {
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(r + "건 입력");
			
			rs = stmt.executeQuery(sql1);
			if(rs.next()) {
				retunVal.setBoardNo(rs.getInt("board_no"));
				retunVal.setTitle(rs.getString("title"));
				retunVal.setContent(rs.getString("content"));
				retunVal.setWriter(rs.getString("writer"));
				retunVal.setCreationDate(rs.getString("creationDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return retunVal;
	}

	// ?��?��
	public void updateBoard(Board board) {
		conn = DBCon.getConnection();
		sql = "update board set content ="+"'"+ board.getContent() + "'" +"where board_no ="+ board.getBoardNo();
		try {
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(r + "건 수정완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}		
			public List<Board> getBoardList() {
				sql = "select * from board order by board_no desc";
				conn = DBCon.getConnection();
				List<Board> boards = new ArrayList<>();
				int i = 0;
				try { 
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Board brd = new Board();
						brd.setBoardNo(rs.getInt("board_no"));
						brd.setTitle(rs.getString("title"));
						brd.setContent(rs.getString("content"));
						brd.setWriter(rs.getString("writer"));
						brd.setCreationDate(rs.getString("creation_date"));

						boards.add(brd);
					}

				} catch (SQLException e) {
					System.out.println("조회중 에러발생");
				} finally {
					close();
				}
				return boards;

			

	}

	// ?��?��
	public Board deleBoardList (Board board) {
		conn = DBCon.getConnection();
		sql = "delete from board where board_no ="+ board.getBoardNo();
		
		try {
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(r + "건 삭제...?");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return retunVal;
	}
	
//	
//	public void deleteBoard(Board board) {
//		conn = DBCon.getConnection();
//		sql = "delete from board where board_no ="+ board.getBoardNo();
//		try {
//			stmt = conn.createStatement();
//			int r = stmt.executeUpdate(sql);
//			System.out.println(r + "삭제...?");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//	}
	
}
