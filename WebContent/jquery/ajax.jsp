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
 	
	Board retVal = new Board();
	retVal = dao.insertBoardResult(board);
	
		 
	
 	String result = 
 		"{\"boardNo\":\""+ retVal.getBoardNo() + "\","
 		+"\"title\":\""+ retVal.getTitle() + "\","
 		+"\"content\":\""+ retVal.getContent() + "\","
		+"\"creationDate\":\""+ retVal.getCreationDate() + "\","
 		+"\"writer\":\""+ retVal.getWriter() + "\"}";
 		out.print(result);
	//{"boardNo": "1", "title":"test", "content": "test", "writer":"user1"}
 %>