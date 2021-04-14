<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@page import="boardPkg.BoardDAO"%>
<%@page import="boardPkg.Board"%>
 <%
 	String bno = request.getParameter("boardNo");
 	String til = request.getParameter("title");
 	String cnt = request.getParameter("content");
 	String wrt = request.getParameter("writer");
 	
 	int boardNo = Integer.parseInt(bno);

	Board board = new Board();
	BoardDAO dao = new BoardDAO();
	
	board.setBoardNo(boardNo);
	board.setTitle(til);
	board.setContent(cnt);
	board.setWriter(wrt);
 	
	dao.insertBoard(board);
	
 	String result = 
 		"{\"boardNo\":\""+ bno + "\","
 		+"\"title\":\""+ til + "\","
 		+"\"content\":\""+ cnt + "\","
 		+"\"writer\":\""+ wrt + "\"}";
 		out.print(result);
	//{"boardNo": "1", "title":"test", "content": "test", "writer":"user1"}
 %>