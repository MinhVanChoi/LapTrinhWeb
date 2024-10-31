<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h2>${category.isEdit ? 'Edit Category' : 'Add Category'}  </h2>
<form action="/admin/categories/save" method="post" enctype="multipart/form-data">
	<input type="hidden" value="${category.isEdit}" name="isEdit">
	<input type="hidden" value="${category.id}" name="id">
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="name" value="${category.name}"><br>
  
  <label for="images">Images</label><br>
  	<input type="text" id="images" name="images" value="${category.images}"><br>
  
	<p>Status</p>
		<input type="radio" id="ston" name="status" value="${category.status}" checked >
		<label for="hoatdong">Dang hoat dong</label><br>
		<input type="radio" id="stoff" name="status" value="0" >
		<label for="khoa">Khoa</label><br> 
  <c:if test="${category.isEdit}">
  		<input type="submit" value="Update">
  	</c:if>
  	<c:if test="${!category.isEdit}">
  		<input type="submit" value="Insert">
  	</c:if>
  <input type="submit" value="Submit">
</form>


