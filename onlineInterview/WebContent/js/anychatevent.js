// AnyChat for Web SDK

/********************************************
 *				事件回调部分			*
 *******************************************/
 
 // 异步消息通知，包括连接服务器、登录系统、进入房间等消息
function OnAnyChatNotifyMessage(dwNotifyMsg, wParam, lParam) {
	switch(dwNotifyMsg) {
		case WM_GV_CONNECT:			OnAnyChatConnect(wParam, lParam);			break;
		case WM_GV_LOGINSYSTEM:		OnAnyChatLoginSystem(wParam, lParam);		break;
		case WM_GV_ENTERROOM:		OnAnyChatEnterRoom(wParam, lParam);			break;
		case WM_GV_ONLINEUSER:		OnAnyChatRoomOnlineUser(wParam, lParam);	break;
		case WM_GV_USERATROOM:		OnAnyChatUserAtRoom(wParam, lParam);		break;
		case WM_GV_LINKCLOSE:		OnAnyChatLinkClose(wParam, lParam);			break;
		case WM_GV_MICSTATECHANGE:	OnAnyChatMicStateChange(wParam, lParam);	break;
		case WM_GV_CAMERASTATE:		OnAnyChatCameraStateChange(wParam, lParam);	break;
		case WM_GV_P2PCONNECTSTATE:	OnAnyChatP2PConnectState(wParam, lParam);	break;
		case WM_GV_PRIVATEREQUEST:	OnAnyChatPrivateRequest(wParam, lParam);	break;
		case WM_GV_PRIVATEECHO:		OnAnyChatPrivateEcho(wParam, lParam);		break;
		case WM_GV_PRIVATEEXIT:		OnAnyChatPrivateExit(wParam, lParam);		break;
		case WM_GV_USERINFOUPDATE:	OnAnyChatUserInfoUpdate(wParam, lParam);	break;
		case WM_GV_FRIENDSTATUS:	OnAnyChatFriendStatus(wParam, lParam);		break;
		case WM_GV_VIDEOSIZECHG:	OnAnyChatVideoSizeChange(wParam, lParam);	break;
		default:
			break;
	}
}

// 收到文字消息
function OnAnyChatTextMessage(dwFromUserId, dwToUserId, bSecret, lpMsgBuf, dwLen) {
//	DisplayTextMessage(dwFromUserId, lpMsgBuf);
}

//收到透明通道传输数据
function OnAnyChatTransBuffer(dwUserId, lpBuf, dwLen) {

}

// 收到透明通道（扩展）传输数据
function OnAnyChatTransBufferEx(dwUserId, lpBuf, dwLen, wParam, lParam, dwTaskId) {

}

// 文件传输完成通知֪
function OnAnyChatTransFile(dwUserId, lpFileName, lpTempFilePath, dwFileLength, wParam, lParam, dwTaskId) {

}

// 系统音量改变通知֪
function OnAnyChatVolumeChange(device, dwCurrentVolume) {

}

// 收到服务器发送的SDK Filter数据
function OnAnyChatSDKFilterData(lpBuf, dwLen) {

}

// 收到录像或拍照完成事件
function OnAnyChatRecordSnapShot(dwUserId, lpFileName, dwParam, bRecordType) {

}

// 收到录像或拍照完成事件（扩展）
function OnAnyChatRecordSnapShotEx(dwUserId, lpFileName, dwElapse, dwFlags, dwParam, lpUserStr) {

}


/********************************************
 *		AnyChat SDK核心业务流程			*
 *******************************************/
 
// 客户端连接服务器，bSuccess表示是否连接成功，errorcode表示出错代码
function OnAnyChatConnect(bSuccess, errorcode) {
	alert("onAnyChatConnect");
    if (errorcode == 0) {
		alert("服务器连接成功");
    }
    else {
    	alert("连接服务器失败");
    }
}

//客户端登录系统，dwUserId表示自己的用户ID号，errorcode表示登录结果：0成功，否则为出错代码
function OnAnyChatLoginSystem(dwUserId, errorcode) {
	AddLog("OnAnyChatLoginSystem(userid=" + dwUserId + ", errorcode=" + errorcode + ")", LOG_TYPE_EVENT);
    if (errorcode == 0) {	
//		ConfigAnyChatParameter();
		mSelfUserId = dwUserId;	
//		ShowHallDiv(true);
    } else {
//		ShowHallDiv(false);
    }
}

// 客户端进入房间，dwRoomId表示所进入房间的ID号，errorcode表示是否进入房间，0：成功进入，否则为出错代码
function OnAnyChatEnterRoom(dwRoomId, errorcode) {
//    DisplayLoadingDiv(false);
	AddLog("OnAnyChatEnterRoom(roomid=" + dwRoomId + ", errorcode=" + errorcode + ")", LOG_TYPE_EVENT);
    if (errorcode == 0) {
//        ShowRoomDiv(true);
//		RoomUserListControl(mSelfUserId, true);		// 将自己插入用户列表
        BRAC_UserCameraControl(mSelfUserId, 1); 	// 打开本地视频
        alert("camera ok");
        BRAC_UserSpeakControl(mSelfUserId, 1); 		// 打开本地语音
        alert("speak ok");

//		ShowNotifyMessage("Welcome use AnyChat, successfully enter the room:" + dwRoomId, NOTIFY_TYPE_SYSTEM);
        // 设置本地视频显示位置
        BRAC_SetVideoPos(mSelfUserId, GetID("AnyChatLocalVideoDiv"), "ANYCHAT_VIDEO_LOCAL");
        // 设置远程视频显示位置（没有关联到用户，只是占位置）
        BRAC_SetVideoPos(0, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");

//        mRefreshVolumeTimer = setInterval(function () {
//            GetID("LocalAudioVolume").style.width = GetID("AnyChatLocalVideoDiv").offsetHeight * BRAC_QueryUserStateInt(mSelfUserId, BRAC_USERSTATE_SPEAKVOLUME) / 100 + "px";
//			if(mTargetUserId != -1)
//				GetID("RemoteAudioVolume").style.width = GetID("AnyChatRemoteVideoDiv").offsetHeight * BRAC_QueryUserStateInt(mTargetUserId, BRAC_USERSTATE_SPEAKVOLUME) / 100 + "px";
//			else
//				GetID("RemoteAudioVolume").style.width = "0px";
//        }, 100);
//    } else {
//
    }
}

// 收到当前房间的在线用户信息，进入房间后触发一次，dwUserCount表示在线的用户数（包括自己），dwRoomId表示房间ID
function OnAnyChatRoomOnlineUser(dwUserCount, dwRoomId) {
	AddLog("OnAnyChatRoomOnlineUser(count=" + dwUserCount + ", roomid=" + dwRoomId + ")", LOG_TYPE_EVENT);
	var useridlist = BRAC_GetOnlineUser();
	for (var i = 0; i < useridlist.length; i++) {
		RoomUserListControl(useridlist[i], true);
    }
	// 请求其中一个用户的音视频
	for (var k=0; k<useridlist.length; k++) {
		if(useridlist[k] == mSelfUserId)
			continue;
		RequestOtherUserVideo(useridlist[k]);
		break;
	}
}

// 用户进入（离开）房间，dwUserId表示用户Id号，bEnterRoom表示该用户是进入（1）或离开（0）房间
function OnAnyChatUserAtRoom(dwUserId, bEnterRoom) {
	AddLog("OnAnyChatUserAtRoom(userid=" + dwUserId + ", benter=" + bEnterRoom + ")", LOG_TYPE_EVENT);
	RoomUserListControl(dwUserId, bEnterRoom ? true : false);
    if (bEnterRoom == 1) {
		ShowNotifyMessage(BRAC_GetUserName(dwUserId) +"&nbspenter room!", NOTIFY_TYPE_NORMAL);
		if(mTargetUserId == -1)						// 默认打开一个用户的音视频
			RequestOtherUserVideo(dwUserId);
    }
    else {
		ShowNotifyMessage(BRAC_GetUserName(dwUserId) +"&nbspleave room!", NOTIFY_TYPE_NORMAL);
        if (dwUserId == mTargetUserId) {			// ��ǰ��������û��뿪���䣬Ĭ�����󷿼��������û�������Ƶ
			reVideoDivSize();
			var bRequestOtherUser = false;
			var useridlist = BRAC_GetOnlineUser();
			for (var k=0; k<useridlist.length; k++) {
				if(useridlist[k] == mSelfUserId)
				continue;
				RequestOtherUserVideo(useridlist[k]);
				bRequestOtherUser = true;
				break;
			}
			if(!bRequestOtherUser) {				// ���û�������û����ߣ������״̬
				mTargetUserId = -1;
				BRAC_SetVideoPos(0, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");
			}
		}
    }
    DisplayScroll("room_div_userlist");
}

// ���������ѹرգ�����Ϣֻ���ڿͻ������ӷ������ɹ�֮�������쳣�ж�֮ʱ������reason��ʾ���ӶϿ���ԭ��
function OnAnyChatLinkClose(reason, errorcode) {
	AddLog("OnAnyChatLinkClose(reason=" + reason + ", errorcode=" + errorcode + ")", LOG_TYPE_EVENT);
	reVideoDivSize();
	DisplayLoadingDiv(false);
	ShowRoomDiv(false);
	ShowHallDiv(false);
	ShowLoginDiv(true);
}

// �û�����Ƶ�豸״̬�仯��Ϣ��dwUserId��ʾ�û�ID�ţ�State��ʾ���û��Ƿ��Ѵ���Ƶ�ɼ��豸��0���رգ�1���򿪣�
function OnAnyChatMicStateChange(dwUserId, State) {

}

// �û�����ͷ״̬�����仯��dwUserId��ʾ�û�ID�ţ�State��ʾ����ͷ�ĵ�ǰ״̬��0��û������ͷ��1��������ͷ��û�д򿪣�2���򿪣�
function OnAnyChatCameraStateChange(dwUserId, State) {
    if (State == 0) GetID(dwUserId + "_CameraTag").src = "";
    if (State == 1) GetID(dwUserId + "_CameraTag").src = "./images/advanceset/camera_false.png";
    if (State == 2) GetID(dwUserId + "_CameraTag").src = "./images/advanceset/camera_true.png";
	if(dwUserId==mTargetUserId){
		if(State==1){
			reVideoDivSize();
			BRAC_SetVideoPos(0, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");
		}
		else
		{
			BRAC_SetVideoPos(dwUserId, GetID("AnyChatRemoteVideoDiv"), "ANYCHAT_VIDEO_REMOTE");
		}
		
	}
	
}

// �����û��������û���P2P��������״̬�����仯��dwUserId��ʾ�����û�ID�ţ�State��ʾ�����û��������û��ĵ�ǰP2P��������״̬��0��û�����ӣ�1����TCP���ӣ�2����UDP���ӣ�3��TCP��UDP���ӣ�
function OnAnyChatP2PConnectState(dwUserId, State) {

}

// �û�����˽������dwUserId��ʾ�����ߵ��û�ID�ţ�dwRequestId��ʾ˽�������ţ���ʶ������
function OnAnyChatPrivateRequest(dwUserId, dwRequestId) {

}

// �û��ظ�˽������dwUserId��ʾ�ظ��ߵ��û�ID�ţ�errorcodeΪ�������
function OnAnyChatPrivateEcho(dwUserId, errorcode) {

}

// �û��˳�˽�ģ�dwUserId��ʾ�˳��ߵ��û�ID�ţ�errorcodeΪ�������
function OnAnyChatPrivateExit(dwUserId, errorcode) {

}

// ��Ƶͨ����Ϣ֪ͨ�ص�����
function OnAnyChatVideoCallEvent(dwEventType, dwUserId, dwErrorCode, dwFlags, dwParam, szUserStr) {
	AddLog("OnAnyChatVideoCallEvent(dwEventType=" + dwEventType + ", dwUserId=" + dwUserId + ", dwErrorCode=" + dwErrorCode + ", dwFlags=" + dwFlags + ", dwParam=" + dwParam + ", szUserStr=" + szUserStr + ")", LOG_TYPE_EVENT);
	
}

// �û���Ϣ����֪ͨ��dwUserId��ʾ�û�ID�ţ�dwType��ʾ�������
function OnAnyChatUserInfoUpdate(dwUserId, dwType) {
	AddLog("OnAnyChatUserInfoUpdate(dwUserId=" + dwUserId + ", dwType=" + dwType + ")", LOG_TYPE_EVENT);
}

// ��������״̬�仯��dwUserId��ʾ�����û�ID�ţ�dwStatus��ʾ�û��ĵ�ǰ�״̬��0 ���ߣ� 1 ����
function OnAnyChatFriendStatus(dwUserId, dwStatus) {
	AddLog("OnAnyChatFriendStatus(dwUserId=" + dwUserId + ", dwStatus=" + dwStatus + ")", LOG_TYPE_EVENT);
	
}

// �û���Ƶ�ֱ��ʷ����仯��dwUserId��INT����ʾ�û�ID�ţ�dwResolution��INT����ʾ�û�����Ƶ�ֱ������ֵ����16λ��ʾ��ȣ���16λ��ʾ�߶ȣ�
function OnAnyChatVideoSizeChange(dwUserId,dwResolution){
	if(dwUserId!=mTargetUserId)
		return;
	var height=dwResolution>>16;
	var width=dwResolution&0x0000ffff;
	var divWidth=GetID("AnyChatRemoteVideoDiv").offsetWidth;
	var divHeight=GetID("AnyChatRemoteVideoDiv").offsetHeight;
	//���������Ƶ��ʾ�ü�ģʽ�Ƕ�̬ģʽ���ɸ��ݷֱ��ʵ��������̬�ı�div���֣�ʹ�û��治���Ρ�
	if(width>height){
		if(divWidth<divHeight){
			//�����л����������
			GetID("AnyChatRemoteVideoDiv").style.width=(4.0/3*divHeight)+"px";
			GetID("AnyChatRemoteVideoDiv").style.height=divHeight+"px";
		}
	}
	else{
		if(divWidth>divHeight){
			//�����л����������
			GetID("AnyChatRemoteVideoDiv").style.width=(3.0/4*divHeight)+"px";
			GetID("AnyChatRemoteVideoDiv").style.height=divHeight+"px";
		}
	}	
}

