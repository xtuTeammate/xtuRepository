<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
   <title>所有学生</title>
    
	<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/custom.css">
    <style type="text/css">
    .container{
      margin-top:50;
    }
    .function a{
     margin:0 20;
    }
    </style>
  </head>
  <body>
  	   <div class="container">
  	   <table class="table table-hover table-bordered" >
  	   <thead>
  	   <tr><td class="col-md-2">学号</td><td class="col-md-2">姓名</td><td class="col-md-3">专业</td><td class="col-md-3">班级</td><td class="col-md-2"><input type="checkbox" value="1" id="checkAll" onclick="checkAll()">全选</td>
  	   </tr></thead>
  	   <tbody>
  	   <tr><td>2013550336</td><td>朱义</td><td>计算机</td><td>1班</td><td><input type="checkbox" name="select"></td></tr>
  	   <tr><td>2013550337</td><td>潘定荣</td><td>软件</td><td>3班</td><td><input type="checkbox" name="select"></td></tr>
  	   </tbody>
  	   </table>
  	   <div class="function">
  	   <a class="btn btn-lg btn-primary " href="teacher/addStudent.jsp">添加学生</a>
  	   <a class="btn btn-lg btn-primary " href="deleteStuServlet">删除选中</a>
  	   </div>
  	   </div>
  	   <script type="text/javascript">
  	    function checkAll(){
  	      var a=document.getElementById("checkAll");
  	      var b=document.getElementsByName("select");
  	      if(a.checked==true){
  	         for(var i=0;i<b.length;i++){
  	           b[i].checked=true;
  	         }
  	      }else{
  	       for(var i=0;i<b.length;i++){
  	          b[i].checked=false;
  	       }
  	      }
  	    }
  	   </script>
  </body>
</html>
