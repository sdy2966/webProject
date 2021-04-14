<%@page import="boardPkg.Board"%>
<%@page import="java.util.List"%>
<%@page import="boardPkg.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.getBoardList();

	//list => JSON파일
	//{"boardNo": "1", "title":"test", "content": "test", "writer":"user"}
	//{"boardNo": "2", "title":"test2", "content": "test2", "writer":"user2"}
	
	String result = "[";
	for(int i=0; i<list.size(); i++) {
	result += "{\"boardNo\":\""+list.get(i).getBoardNo()+"\","
               + "\"title\":\""+list.get(i).getTitle().trim()+"\","
               + "\"content\":\""+list.get(i).getContent().trim()+"\","
               + "\"writer\":\""+list.get(i).getWriter().trim()+"\"}";
         result += (i == list.size() -1 ? "" : "," );
	}
	result += "]";
	out.print(result);
%>