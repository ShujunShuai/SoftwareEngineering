<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script language='javascript' src='..\图书管理交流系统\files\登录\data.js'></script>
    <link type="text/css" rel="stylesheet" href="..\图书管理交流系统\files\登录\styles.css"></link> //引入外部的文件
    <link type="text/css" rel="stylesheet" href="..\图书管理交流系统\style\main.css"></link> //引入外部的文件
</head>

<script type="text/javascript">
    var InterValObj; //timer变量，控制时间
    var count = 30; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    function sendMessage(){curCount = count;
        $("#btn").attr("disabled", "true");
        $("#btn").val(curCount + "秒后可重新发送");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
    }
    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btn").removeAttr("disabled");//启用按钮
            $("#btn").val("重新发送验证码");
        }
        else {
            curCount--;
            $("#btn").val(curCount + "秒后可重新发送");
        }
    }
</script>
<body>
       <div class="container">
           <div  id="login">
           <form class="form-horizontal" role="form">
               <div class="form-group">
                   <label class="col-sm-2 control-label">手机号</label>
                   <div class="col-sm-5">
                       <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入您的手机号"  required autofocus>
                       
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-sm-2 control-label">验证码</label>
                   <div class="col-sm-3">
                       <input type="code" class="form-control" id="code" name="code" placeholder="验证码" required>
                       <input class="btn btn-default" id="btn" name="btn" value="发送验证码" onclick="sendMessage()" />
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-offset-2 col-sm-10">
                       <button type="button" class="btn btn-info" id="lo">登陆</button>
                   </div>
               </div>
           </form>
       </div>
       </div>
</body>
<script type="text/javascript">

    var sms="";
    $("#btn").click(function(){
        var phone=$("#phone").val();
        if(phone!="")
        {
            $.ajax({
                url:"sendSMS",
                type:"post",
                data:{"phone":phone},
                success:function(result){
                    sms=result;
                }
            });
        }else{
             alert("请输入手机号");
            return false;
        }

    });
    $("#lo").click(function(){
        var code=$("#code").val();
        if(code==""){
            alert("请输入验证码");
        }else{
            if(sms==code){
                window.location.href="success.jsp";
            }else{
                alert("验证码错误");

            };
        };

    });
</script>
</html>
