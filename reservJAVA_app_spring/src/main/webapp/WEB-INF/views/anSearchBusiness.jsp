<%@page import="reservJAVA_app.dto.BusinessDTO"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page import="org.springframework.ui.Model"%>
<%@page import="java.sql.*, java.sql.Date, javax.sql.*, javax.naming.*, 
					java.util.*, java.io.PrintWriter" %>
					
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Gson gson = new Gson();
String json = gson.toJson((ArrayList<BusinessDTO>)request.getAttribute("anSearchBusiness") );

out.println(json);	

 /* try{
	out.println("<lists>"); 

	for(BusinessDTO dto  : (ArrayList<BusinessDTO>)request.getAttribute("anBusinessSelect")){
		out.println("<list>");
		out.println("<id>" + dto.getBusiness_code() + "</business_id>");
		out.println("<name>" + dto.getBusiness_name() + "</business_name>");
	
	 if(dto.getUploadtype().equals("video")){
		String fileNamePath = (dto.getImage1().split("/")[dto.getImage1().split("/").length-1]);
		String replacePath = (dto.getImage1().split("/")[dto.getImage1().split("/").length-1]).replace(".", "_");
		System.out.println("replacePath :" + replacePath);				
		String videoImagePath = dto.getImage1().replace(fileNamePath, "videoImages/" + replacePath) + ".jpg";
		System.out.println("videoImagePath :" + videoImagePath);
		
		out.println("<videoimage>" + videoImagePath + "</videoimage>");				
	
	} else if(dto.getUploadtype().equals("image")){
		out.println("<videoimage>" + "novideo" + "</videoimage>");
	}
	 
	out.println("</list>"); 
}1
	out.println("</lists>");
}catch(Exception e) {
	System.out.println("select list failed" + e.getMessage());
}	    */

%>