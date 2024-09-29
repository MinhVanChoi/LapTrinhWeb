<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="jakarta.tags.core" %>
     <form action ="<c:url value = '/admin/category/update'> </c:url>" method="post">
   
			
  <label for="fname">Category name:</label><br>
  
  <input type="text" id="fname" name="categoryname"><br>
  <label for="lname">Image: </label><br>
  <input type="file" id="images" name="images">
    		<c:if test="${cate.images.substring(0,5)==https }">
						<c:url value="${cate.images }" var ="imgUrl"></c:url>
			
			</c:if>
			<c:if test="${cate.images.substring(0,5)!=https }">
						<c:url value="/image?fname=${cate.images }" var ="imgUrl"></c:url>
			
			</c:if>
	<p> Status: </p>
	<input type="radio" id="ston" name="status" value="${cate.status==1?'checked':''}">
	<label for="html"> Đang hoạt động</label><br>
	<input type="radio" id="stoff" name="status" value="${cate.status==0?'checked':''">
		<label for="html">Khóa </label><br>
	<input type="submit" value="Update">
	
	
</form>