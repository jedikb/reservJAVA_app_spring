<%@page import="reservJAVA_app.dto.MemberDTO"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page import="org.springframework.ui.Model"%>
<%@page import="java.sql.*, java.sql.Date, javax.sql.*, javax.naming.*, 
					java.util.*, java.io.PrintWriter" %>
					
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Gson gson = new Gson();
String json = gson.toJson((MemberDTO)request.getAttribute("anMemberLogin"));

out.println(json);	

/* try{
	out.println("<lists>"); 

for(MemberDTO dto  : (ArrayList<MemberDTO>)request.getAttribute("anSelectMulti")){
	out.println("<list>");
	out.println("<id>" + dto.getMember_id() + "</member_id>");
	out.println("<name>" + dto.getMember_name() + "</member_name>");
	out.println("<date>" + dto.getMember_nick() + "</member_nick>");
	out.println("<image>" + dto.getMember_tel() + "</member_tel>");
 	out.println("<uploadtype>" + dto.getUploadtype() + "</uploadtype>");
	
	if(dto.getUploadtype().equals("video")){
		String fileNamePath = (dto.getImage1().split("/")[dto.getImage1().split("/").length-1]);
		String replacePath = (dto.getImage1().split("/")[dto.getImage1().split("/").length-1]).replace(".", "_");
		System.out.println("replacePath :" + replacePath);				
		String videoImagePath = dto.getImage1().replace(fileNamePath, "videoImages/" + replacePath) + ".jpg";
		System.out.println("videoImagePath :" + videoImagePath);
		
		out.println("<videoimage>" + videoImagePath + "</videoimage>");				
	
	}else if(dto.getUploadtype().equals("image")){
		out.println("<videoimage>" + "novideo" + "</videoimage>");
	}
	 
	out.println("</list>");
}
	out.println("</lists>");
}catch(Exception e) {
	System.out.println("select list failed" + e.getMessage());
}	  */

%>