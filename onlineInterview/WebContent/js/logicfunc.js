/**
 * 
 */
var mRefreshPluginTimer = -1;// timer for checking plugin
var mRefreshVolumTimer = -1;// timer for volum
var mDefaultServerAddr = "demo.anychat.cn";		// 默认服务器地址
var mDefaultServerPort = 8906;	
var mSelfUserId = -1; //local user id
var mTargetUserId = -1;//target user id 


//日志记录类型，在日志信息栏内显示不同的颜色
var LOG_TYPE_NORMAL = 0;
var LOG_TYPE_API = 1;
var LOG_TYPE_EVENT = 2;
var LOG_TYPE_ERROR = 3;

//通知类型，在文字消息栏内显示不同的颜色
var NOTIFY_TYPE_NORMAL = 0;
var NOTIFY_TYPE_SYSTEM = 1;

function LogicInit(){
	setTimeout(function(){
		if(navigator.plugins && navigator.plugins.length){
			window.navigator.plugins.refresh(false);
		}
		var NEED_ANYCHAT_APILEVEL = "0";
		var errorcode = BRAC_InitSDK(NEED_ANYCHAT_APILEVEL);
	    AddLog("BRAC_InitSDK(" + NEED_ANYCHAT_APILEVEL + ")=" + errorcode, LOG_TYPE_API);
		if(errorcode == 0){
			if(mRefreshPluginTimer != -1)
				clearInterval(mRefreshPluginTimer); 
				AddLog("AnyChat Plugin Version:" + BRAC_GetVersion(0), LOG_TYPE_NORMAL);
				AddLog("AnyChat SDK Version:" + BRAC_GetVersion(1), LOG_TYPE_NORMAL);
				AddLog("Build Time:" + BRAC_GetSDKOptionString(BRAC_SO_CORESDK_BUILDTIME), LOG_TYPE_NORMAL);
		}
		else{
			if(errorcode = GV_ERR_PLUGINNOINSTALL){
				
			}
			else if(errorcode = GV_ERR_PLUGINOLDVERSION){
				
			}
			if(mRefreshPluginTimer == -1){
				mRefreshPluginTimer = setInterval(function(){
					LogicInit();
				}, 1000);
			}
		}
	}, 500);
	login();
}

function login(){
	GetID("Login").onclick=function(){
		var code0 = BRAC_Connect(mDefaultServerAddr, mDefaultServerPort);  // 连接服务器
		var code1 = BRAC_Login(mSelfUserId, "", 0);    // 登录系统，密码为空也可登录
		AddLog("connect: " + code0, LOG_TYPE_NORMAL);
		AddLog("login: " + code1, LOG_TYPE_NORMAL);
	}
	
	GetID("Open_Local").onclick=function(){
		EnterRoomRequest(1); 
	}
	
	GetID("Start").onclick=function(){
		 BRAC_UserCameraControl(mSelfUserId, 1); 	// 打开本地视频
		 AddLog("-----opencamera ", LOG_TYPE_NORMAL);
	     BRAC_UserSpeakControl(mSelfUserId, 1); 		// 打开本地语音
	     AddLog("-----openvoice ", LOG_TYPE_NORMAL);
	     BRAC_SetVideoPos(mSelfUserId, GetID("AnyChatLocalVideoDiv"), "ANYCHAT_VIDEO_LOCAL");
	     BRAC_SetVideoPos(0, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");
	}

}

function InitInterfaceUI(){
	var errorcode = BRAC_Connect(mDefaultServerAddr, mDefaultServerPort); //连接服务器
    errorcode = BRAC_Login(GetID("username").value, GetID("password").value, 0);
//    AddLog("BRAC_Login(" + GetID("username").value + ")=" + errorcode, LOG_TYPE_API);	
}

//请求进入指定的房间
function EnterRoomRequest(roomid) {
	var errorcode = BRAC_EnterRoom(roomid, "", 0); //进入房间
	AddLog("BRAC_EnterRoom(" + roomid + ")=" + errorcode, LOG_TYPE_API);
}


function OnAnyChatConnect(bSuccess, errorcode){
	if(errorcode == 0){}
	else alert("服务器连接失败");
}

function OnAnyChatLoginSystem(dwUserId, errorcode){
	if(errorcode == 0){
		mSelfUserId = dwUserId;
	}
}

function EnterRoom(){
}

function OnAnyChatEnterRoom(dwRoodId, errorcode){
	if(errorcode == 0){
		
	}
}

function GetID(id) {
	if (document.getElementById) {
		return document.getElementById(id);
	} else if (window[id]) {
		return window[id];
	}
	return null;
}

//获取当前时间  (00:00:00)
function GetTheTime() {
	var TheTime = new Date();
	return TheTime.toLocaleTimeString();
}


//添加日志并显示，根据不同的类型显示不同的颜色
function AddLog(message, type) {
    if (type == LOG_TYPE_API) {			// API调用日志，绿色
        message = message.fontcolor("Green");
	} else if(type == LOG_TYPE_EVENT) {	// 回调事件日志，黄色
        message = message.fontcolor("#CC6600");
	} else if(type == LOG_TYPE_ERROR) {	// 出错日志，红色
        message = message.fontcolor("#FF0000");
	} else {							// 普通日志，灰色
        message = message.fontcolor("#333333");
	}
    GetID("LOG_DIV_CONTENT").innerHTML += message + "&nbsp" + GetTheTime().fontcolor("#333333") + "<br />";
}