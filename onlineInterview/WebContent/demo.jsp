<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>interview demo</title>
<!-- load AnyChat for Web SDK -->
<script language="javascript" type="text/javascript"
	src="./js/anychatsdk.js" charset=utf-8></script>
<script language="javascript" type="text/javascript"
	src="./js/anychatobject.js" charset=utf-8></script>
<script language="javascript" type="text/javascript"
	src="./js/anychatevent.js" charset=utf-8></script>
<!-- load logic control script -->
<script language="javascript" type="text/javascript"
	src="./js/logicfunc.js" charset=utf-8></script>

</head>
<body onload="LogicInit()">
	<div id="room_div" style="float: left;">
		<div id="room_div_close">
			<div id="leaveroom" style="padding-right: 20px; text-align: right">close</div>
		</div>
		<div style="padding: 10px 20px 10px 20px;">
			<div id="room_div_left"
				style="border: 1px solid #00F; float: left; width: 49%">
				<div id="room_div_myobject">
					<div id="AnyChatLocalVideoDiv" style="height: 200px;"></div>
					<div style="text-align: left; width: 193px; height: 4px;">
						<div id="LocakAudioVolume"></div>
					</div>
				</div>
			</div>

			<div id="room_div_right"
				style="border: 1px solid #666; float: right; width: 49%">
				<div id="room_div_otherobject" style="text-align: center;">
					<div id="AnyChatRemoteVideoDiv" style="height: 200px;"></div>
					<div style="width: 502px; height: 4px; text-align: center;">
						<div id="RemoteAudioVolume"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<div id="operation_div" style="clear:both; padding:20px;text-align:center">
		<div id="Login" style="border: 1px solid #666;width: 50px; height:20px;float:left;">
			<span style="padding:10px;text-align:center">login</span>
		</div>
	
		<div id="Open_Local" style="border: 1px solid #666;width: 50px; height:20px;float:left">
			<span style="padding:10px;text-align:center">open</span>
		</div>
		
		<div id="Start" style="border: 1px solid #666;width: 50px; height:20px;float:left">
			<span style="padding:10px;text-align:center">start</span>
		</div>
	</div>
	
	<!--系统日志信息层-->
	<div id="LOG_DIV_BODY" style="clear:both; padding : 20px">
		<div id="LOG_DIV_TITLE">系统日志</div>
		<div id="LOG_DIV_CONTENT"></div>
	</div>

	

	<!--div id="setting_div">
		<div id="setting_div_input">
			服务器:&nbsp<input type="text" id="ServerAddr" style="width: 120px;" />&nbsp&nbsp&nbsp
			端口:&nbsp<input type="text" id="ServerPort" style="width: 40px;" />
		</div>
	</div-->

</body>
</html>