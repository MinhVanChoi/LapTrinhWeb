<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname" required><br>
  
  <label for="images">Images:</label><br>
  <input type="file" id="images" name="images"  required><br>
  
  <label for="status">Status:</label><br>
  <input type="text" id="status" name="status" required><br><br>
  
  <input type="submit" value="Submit">
</form>


