<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@page import="src.dao.AttentionDao ,src.entity.User, src.entity.Artical"%>
<%@page import="src.util.DBconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="src.entity.Comment"%>
<%@page import="src.dao.CommentDao"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    <meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<title>登录</title>
	<link href="https://res.udb.duowan.com/zc/css/base.css?v180329" rel="stylesheet" type="text/css" />
	<link href="https://res.udb.duowan.com/zc/css/udb_reg.css?v180329" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
       <div class="col-md-7 col-md-offset-4" >
	<div id="m_mainForm" class="m_forceRegStep m_forceRegStep_needMbf m_forceRegStep1 m_cus_forceRegStep1" style="display:block">
		<div class="m_form">
			<div class="m_head">
				<h3><a class="field_title" href="javascript:void(0);"  onclick="return false;"></a>用户登录</h3>
			</div>
			<div class="form_item user_choose_item" node-name="user">
				<form action="adduser.php" method="post" name="" enctype="text/plain">				
					<span class="field_title" node-name="user_choose_item">选择身份:</span>
					<select name="" id="m_mobileU">
						<option value="普通用户">普通用户</option>
						<option value="图书馆用户">图书馆用户</option>
					</select>
			</div>
			<div class="form_item pwd_item" node-name="mobile">
				<span class="field_title">账号：</span>
       			<span class="text_input_v3" node-name="tbMobile"><input placeholder="11位手机号"  type="text" node-name="inMobile" /><span class="space"></span></span>
				<span class="form_tip"></span>
			</div>
			<div class="form_item pwd_item" node-name="password">
				<span class="field_title">密码：</span>
				<span class="text_input_v3" node-name="tbPassword"><input placeholder="8-20个字符" type="password" node-name="inPassword"/><span class="space"></span></span>
				<span class="form_tip"></span>
			</div>					
			<div class="opra">
				<a class="btn_blue_v3" node-name="send" href="系统主页.html" ><span>登录</span></a>
				<a class="btn_blue_v3" node-name="send" href="注册.html" ><span>注册</span></a>
			</div>
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
