// AnyChat for Web SDK
 
var mDefaultServerAddr = "demo.anychat.cn"   //"192.168.191.4"  default server addressַ  demo.anychat.cn
var mDefaultServerPort = 8906;					// default server port
var mSelfUserId = -1; 							// local userID
var mTargetUserId = -1;							// target userID
var mRefreshVolumeTimer = -1; 					
var mRefreshPluginTimer = -1;					

// log type with different color
var LOG_TYPE_NORMAL = 0;
var LOG_TYPE_API = 1;
var LOG_TYPE_EVENT = 2;
var LOG_TYPE_ERROR = 3;

// ֪ͨnotify type
var NOTIFY_TYPE_NORMAL = 0;
var NOTIFY_TYPE_SYSTEM = 1;


function LogicInit() {
    setTimeout(function () {
		if (navigator.plugins && navigator.plugins.length) {
			window.navigator.plugins.refresh(false);
		}
        var NEED_ANYCHAT_APILEVEL = "0"; 						
        var errorcode = BRAC_InitSDK(NEED_ANYCHAT_APILEVEL); 	
        AddLog("BRAC_InitSDK(" + NEED_ANYCHAT_APILEVEL + ")=" + errorcode, LOG_TYPE_API);
        if (errorcode == GV_ERR_SUCCESS) {
			if(mRefreshPluginTimer != -1)
				clearInterval(mRefreshPluginTimer); 			
            ShowLoginDiv(true);
            AddLog("AnyChat Plugin Version:" + BRAC_GetVersion(0), LOG_TYPE_NORMAL);
            AddLog("AnyChat SDK Version:" + BRAC_GetVersion(1), LOG_TYPE_NORMAL);
            AddLog("Build Time:" + BRAC_GetSDKOptionString(BRAC_SO_CORESDK_BUILDTIME), LOG_TYPE_NORMAL);
			
			GetID("prompt_div").style.display = "none"; 		
			InitInterfaceUI();
        } else { 						
            GetID("prompt_div").style.display = "block";
            SetDivTop("prompt_div", 300);
            if (errorcode == GV_ERR_PLUGINNOINSTALL)
                GetID("prompt_div_line1").innerHTML = "请安装插件";
            else if (errorcode == GV_ERR_PLUGINOLDVERSION)
                GetID("prompt_div_line1").innerHTML = "请更新插件";
				
			if(mRefreshPluginTimer == -1) {
				mRefreshPluginTimer = setInterval(function(){ LogicInit(); }, 1000);
			}
		}
    }, 500);
}

function ConfigAnyChatParameter(){
	
}

function InitInterfaceUI() {
	//录像及拍照
	GetID("TakeRecord").onclick = function(){
		if(GetID("record_control").value=="start"){
			BRAC_StreamRecordCtrl(mTargetUserId,1,0,1);
			GetID("record_control").value="end";
    		GetID("TakeRecord").innerHTML="停止录制";
		}
		else{
			 BRAC_StreamRecordCtrl(mTargetUserId,0,0,1);
			GetID("record_control").value="start";
    		GetID("TakeRecord").innerHTML="开始录制";
		}
    }
    GetID("TakePhoto").onclick = function(){
    	BRAC_SnapShot(mTargetUserId,BRAC_RECORD_FLAGS_SNAPSHOT,1);
    }
    GetID("setting").onclick = function () {
        if (GetID("setting_div").style.display == "block")
            GetID("setting_div").style.display = "none";
        else
            GetID("setting_div").style.display = "block";
    }
    GetID("loginbtn").onclick = function () {
		if(GetID("password").value == "密码可为空")
			GetID("password").value = "";
        if (GetID("username").value != "") {
            //DisplayLoadingDiv(true);
            var errorcode = BRAC_Connect(GetID("ServerAddr").value, parseInt(GetID("ServerPort").value)); //���ӷ�����
            AddLog("BRAC_Connect(" + GetID("ServerAddr").value + "," + GetID("ServerPort").value + ")=" + errorcode, LOG_TYPE_API);
            errorcode = BRAC_Login(GetID("username").value, GetID("password").value, 0);
            AddLog("BRAC_Login(" + GetID("username").value + ")=" + errorcode, LOG_TYPE_API);
            GetID("setting_div").style.display = "none";
        }
        else {
            GetID("a_error_user").style.color = "red";
            AddLog("The user name can not be empty!", LOG_TYPE_ERROR);
            GetID("username").focus();
        }
    }
    GetID("ExitSystemBtn").onclick = function () {
        var errorcode = BRAC_Logout();
        AddLog("BRAC_Logout()=" + errorcode, LOG_TYPE_API);
        ShowHallDiv(false);
        ShowLoginDiv(true);
    }
    GetID("leaveroom").onclick = function () {
        var errorcode = BRAC_LeaveRoom(-1);
        AddLog("BRAC_LeaveRoom(" + -1 + ")=" + errorcode, LOG_TYPE_API);
		if(mRefreshVolumeTimer != -1)
			clearInterval(mRefreshVolumeTimer); 
        ShowRoomDiv(false); 					
        ShowHallDiv(true); 						
        mTargetUserId = -1;
    }
    GetID("EnterRoomBtn").onclick = function () {
        if (GetID("customroomid").value != "") {
            var re = /^[1-9]+[0-9]*]*$/;   
            if (re.test(GetID("customroomid").value)) {
                EnterRoomRequest(parseInt(GetID("customroomid").value));
            } else {
                AddLog("Room ID must be number!", LOG_TYPE_ERROR);
                GetID("customroomid").value = "";
                GetID("customroomid").focus();
            }
        }
    }
    
    GetID("SendMsg").onclick = function () {
        SendMessage();
    }
    GetID("MessageInput").onkeydown = function (e) {
        e = e ? e : window.event; 
        //press down enter key to send message
        if (e.keyCode == 13 && GetID("MessageInput").value != "") {
            SendMessage();
        }
    }
    GetID("prompt_div_btn_load").onmouseover = function () {
        GetID("prompt_div_btn_load").style.backgroundColor = "#ffc200";
    }
    GetID("prompt_div_btn_load").onmouseout = function () {
        GetID("prompt_div_btn_load").style.backgroundColor = "#ff8100";
    }
    GetID("prompt_div_headline2").onclick = function () {
        document.URL = location.href;
    }
    GetID("LOG_DIV_BODY").onmousemove = function () {
        GetID("LOG_DIV_BODY").style.zIndex = 100;
        GetID("LOG_DIV_CONTENT").style.backgroundColor = "#FAFADD";
        GetID("LOG_DIV_CONTENT").style.border = "1px solid black";
    }
    GetID("LOG_DIV_BODY").onmouseout = function () {
        GetID("LOG_DIV_BODY").style.zIndex = -1;
        GetID("LOG_DIV_CONTENT").style.backgroundColor = "#C4CEDD";
        GetID("LOG_DIV_CONTENT").style.border = "";
    }
    GetID("advanceset_div_close").onclick = function () {
        GetID("advanceset_div").style.display = "none";
    }

}


function PasswordFocus(obj,color){
	if(obj.value=="密码可为空")
		obj.value="";
	obj.type="password";
	obj.style.backgroundColor=color;
}
function myblur(obj,color){
	obj.style.background=color;
}

function SetDivTop(id, TheHeight) {
    var BodyHeight = document.documentElement.clientHeight; 
	if (TheHeight < BodyHeight) {
	    GetID("margintop").style.height = (BodyHeight - TheHeight) / 4 + "px";
	    GetID(id).style.marginTop = "0px";
    }
}

function DisplayScroll(id) {
    var offset = GetID(id); 
	if (offset.offsetHeight < offset.scrollHeight) {
		GetID(id).style.overflowY = "scroll";
		GetID(id).scrollTop = GetID(id).scrollHeight;
	}
	else
		GetID(id).style.overflowY = "hidden";
}

function SendMessage() {
    if (GetID("MessageInput").value != "") {
        var Msg = GetID("MessageInput").value;
        BRAC_SendTextMessage(0, 0, Msg); 
		DisplayTextMessage(mSelfUserId, Msg);
		GetID("MessageInput").value = "";
		GetID("MessageInput").focus();
	}
}

function DisplayTextMessage(fromuserid, message) {
	var namestr = BRAC_GetUserName(fromuserid) + "&nbsp" + GetTheTime();
	if(fromuserid==mSelfUserId)
		namestr = namestr.fontcolor("#008000");
	else
		namestr = namestr.fontcolor("#000080");
	message = message.fontcolor("#333333");

	var msgdiv = document.createElement("div");
	msgdiv.setAttribute("class", "TheMsgStyle");
	msgdiv.innerHTML = namestr + "：&nbsp&nbsp" + message;
//	GetID("ReceiveMsgDiv").appendChild(msgdiv);
//	DisplayScroll("ReceiveMsgDiv");
}

function ShowNotifyMessage(message, type) {
    if (type == NOTIFY_TYPE_SYSTEM) {
        message = message.fontcolor("#FF0000");
    } else {
        message = message.fontcolor("#333333");
    }
	var msgdiv = document.createElement("div");
    msgdiv.setAttribute("class", "TheMsgStyle");
	msgdiv.innerHTML = message + "&nbsp(" + GetTheTime().fontcolor("#999999") + ")";
//    GetID("ReceiveMsgDiv").appendChild(msgdiv);
//    DisplayScroll("ReceiveMsgDiv");
}

function ShowLoginDiv(bShow) {
	if(bShow) {
		GetID("login_div").style.display = "block"; 	
		GetID("username").focus();
		SetDivTop("login_div", 195); 				
		GetID("LOG_DIV_BODY").style.display = "block"; 	
		GetID("ServerAddr").value = mDefaultServerAddr;
		GetID("ServerPort").value = mDefaultServerPort;
	} else {
	
	}
}

function ShowHallDiv(bShow) {
    if (bShow) {
        GetID("room_div_userlist").innerHTML = ""; 
		GetID("login_div").style.display = "none"; 		
		GetID("hall_div").style.display = "block"; 		
		GetID("customroomid").value = "";
		SetDivTop("hall_div", 400); 					
		GetID("customroomid").focus();
		GetID("a_error_user").style.color = "#FAFADD";
		
		GetID("hall_div_td_name").innerHTML = BRAC_GetUserName(mSelfUserId);
		GetID("hall_div_td_id").innerHTML = mSelfUserId;
		GetID("hall_div_td_level").innerHTML = BRAC_GetUserLevel(mSelfUserId);
		GetID("hall_div_td_ip").innerHTML = BRAC_QueryUserStateString(mSelfUserId, BRAC_USERSTATE_LOCALIP);
	} else {
		GetID("hall_div").style.display = "none";
	}
}

function ShowRoomDiv(bShow) {
    if (bShow) {
        GetID("hall_div").style.display = "none"; 
        GetID("room_div").style.display = "block"; 
        SetDivTop("room_div", 610); 				
//        GetID("MessageInput").focus();
    } else {
        GetID("advanceset_div").style.display = "none"; 
//        GetID("ReceiveMsgDiv").innerHTML = ""; 		
        GetID("room_div").style.display = "none"; 	
    }
}

function EnterRoomRequest(roomid) {
	var errorcode = BRAC_EnterRoom(roomid, "", 0); 
	AddLog("BRAC_EnterRoom(" + roomid + ")=" + errorcode, LOG_TYPE_API);
	// if(errorcode == 0)
	// 	DisplayLoadingDiv(true);
}

function GetID(id) {
	if (document.getElementById) {
		return document.getElementById(id);
	} else if (window[id]) {
		return window[id];
	}
	return null;
}
function RequestOtherUserVideo(userid) {
    var userlist = GetID("room_div_userlist");
    var userdivobj = userlist.getElementsByTagName("div");
    for (var i = 0; i < userdivobj.length; i++) {
        userdivobj[i].style.backgroundColor = "White"; 
    }
    var userimgobj = userlist.getElementsByTagName("img");
    for (var j = 0; j < userimgobj.length; j++) {
        if (userimgobj[j].getAttribute("class") == "MicrophoneTag") { 
            userimgobj[j].src = "./images/advanceset/microphone_false.png";
            userimgobj[j].onclick = ""; 
            userimgobj[j].style.cursor = "";
        }
    }
    if (mTargetUserId != -1) {
		reVideoDivSize();
        BRAC_UserCameraControl(mTargetUserId, 0);
        BRAC_UserSpeakControl(mTargetUserId, 0);
		BRAC_TransBuffer(mTargetUserId,"goodbye");
    }
    GetID(userid + "_MicrophoneTag").src = "./images/advanceset/microphone_true.png"; 
    GetID(userid + "_UserDiv").style.backgroundColor = "#E6E6E6"; 

    mTargetUserId = userid; 					
    BRAC_UserCameraControl(userid, 1); 		
    BRAC_UserSpeakControl(userid, 1);
    document.getElementById("studentid").value=userid;
    BRAC_SetVideoPos(userid, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");
    MicrophoneOnclick(userid); 
	BRAC_TransBuffer(userid,"hello");
}

function RoomUserListControl(userid, bInsert) {
    var userlist = GetID("room_div_userlist");
    if (bInsert) {
        var itemdiv = document.createElement("div");
        itemdiv.setAttribute("class", "UserListStyle");
        itemdiv.id = userid + "_UserDiv";

        if (BRAC_GetCameraState(userid) == 0)
            AddImage(itemdiv, userid + "_CameraTag", "CameraTag", "", userid); 
        if (BRAC_GetCameraState(userid) == 1)
            AddImage(itemdiv, userid + "_CameraTag", "CameraTag", "./images/advanceset/camera_false.png", userid); 
        if (BRAC_GetCameraState(userid) == 2)
            AddImage(itemdiv, userid + "_CameraTag", "CameraTag", "./images/advanceset/camera_true.png", userid); 
        
        if (userid == mSelfUserId) {
            AddImage(itemdiv, mSelfUserId + "_MicrophoneTag", "mSelfMicrophoneTag", "./images/advanceset/microphone_true.png", userid); 
            itemdiv.innerHTML += "&nbsp" + BRAC_GetUserName(mSelfUserId) + "(自己)";
        } else {
            AddImage(itemdiv, userid + "_MicrophoneTag", "MicrophoneTag", "./images/advanceset/microphone_false.png", userid);
            
            var a = document.createElement("a");
            a.id = userid + "_UserTag";
            a.title = BRAC_GetUserName(userid);
            a.innerHTML = BRAC_GetUserName(userid);
            a.href = "javascript:RequestOtherUserVideo(" + userid + ")";
            itemdiv.appendChild(a);
        }
        GetID("room_div_userlist").appendChild(itemdiv);
        MicrophoneOnclick(mSelfUserId);
    } else {
        var my = GetID(userid + "_UserDiv");
        userlist.removeChild(my);
    }
    DisplayScroll("room_div_userlist");
}

function Mouseover(id) {
	GetID(id).style.backgroundColor = "#FFFFCC";
}
function Mouseout(id) {
	GetID(id).style.backgroundColor = "#E6E6E6";
}
function GetTheTime() {
	var TheTime = new Date();
	return TheTime.toLocaleTimeString();
}

function AddLog(message, type) {
    if (type == LOG_TYPE_API) {			
        message = message.fontcolor("Green");
	} else if(type == LOG_TYPE_EVENT) {	
        message = message.fontcolor("#CC6600");
	} else if(type == LOG_TYPE_ERROR) {	
        message = message.fontcolor("#FF0000");
	} else {							
        message = message.fontcolor("#333333");
	}
    GetID("LOG_DIV_CONTENT").innerHTML += message + "&nbsp" + GetTheTime().fontcolor("#333333") + "<br />";
	DisplayScroll("LOG_DIV_CONTENT");
}

function DisplayLoadingDiv(bShow) {
    if (bShow) {
        GetID("LOADING_DIV").style.display = "block";
        GetID("LOADING_GREY_DIV").style.display = "block";
        var TheHeight = document.documentElement.clientHeight;
        var TheWidth = document.body.offsetWidth;
        GetID("LOADING_DIV").style.marginTop = (TheHeight - 50) / 2 + "px";
        GetID("LOADING_DIV").style.marginLeft = (TheWidth - 130) / 2 + "px";
    }
    else {
        GetID("LOADING_DIV").style.display = "none";
        GetID("LOADING_GREY_DIV").style.display = "none";
    }
}

function AddImage(parent_id, img_id, img_class, fir_img, userid) {
    var imgs = document.createElement("img");
    imgs.id = img_id;
    imgs.className = img_class;
    imgs.src = fir_img;
    imgs.style.width = "15px";
    imgs.style.height = "15px";
    parent_id.appendChild(imgs);
}

function MicrophoneOnclick(userid) {
    GetID(userid + "_MicrophoneTag").style.cursor = "pointer";
    GetID(userid + "_MicrophoneTag").onclick = function () { 
        var ImgPath = GetID(userid + "_MicrophoneTag").src.split('/');
        if (ImgPath[ImgPath.length - 1] == "microphone_true.png") {
            GetID(userid + "_MicrophoneTag").src = "./images/advanceset/microphone_false.png";
            BRAC_UserSpeakControl(userid, 0); 
        }
        else {
            GetID(userid + "_MicrophoneTag").src = "./images/advanceset/microphone_true.png";
            BRAC_UserSpeakControl(userid, 1); 
        }
    }
}
function reVideoDivSize()
{
	var divWidth=GetID("AnyChatRemoteVideoDiv").offsetWidth;
	var divHeight=GetID("AnyChatRemoteVideoDiv").offsetHeight;
	if(divWidth<divHeight){
		GetID("AnyChatRemoteVideoDiv").style.width=(4.0/3*divHeight)+"px";
		GetID("AnyChatRemoteVideoDiv").style.height=divHeight+"px";
	}
}