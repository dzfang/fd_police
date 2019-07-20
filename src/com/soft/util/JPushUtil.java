package com.soft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

public class JPushUtil {

	private static final String TITLE_KEY = "MESSAGE_TITLE";
	private static final String MESSAGE_TYPE_KEY = "MESSAGE_TYPE";
	private static final String SEND_TIME_KEY = "SEND_TIME";

	/**
	 * 系统消息
	 */
	public static final String SYS_MESSAGE = "001";

	/**
	 * 客服回复消息
	 */
	public static final String SERVICE_REPLY = "002";

	/**
	 * 用户反馈回复消息
	 */
	public static final String FEEBACK_REPLY = "003";

//	private static final long timeToLive = 60 * 60 * 24;

	public static final int MAX_RETRY_TIMES = 3;//尝试重发次数

	/**
	 * 推送消息给所有用户
	 * 
	 * @param content
	 *            消息内容
	 * @param title
	 *            标题
	 * @param messageType
	 *            消息类型
	 * @see JPushUtil.SYS_MESSAGE
	 *      、JPushUtil.SERVICE_REPLY、JPushUtil.FEEBACK_REPLY
	 */
	public static PushResult pushMessage(String content, String title,
			String messageType, String... users) {
		JPushClient jpushClient = new JPushClient(Config.getSecretKey(), Config.getAppKey(), MAX_RETRY_TIMES);
		PushPayload payload = buildWithExtrasAndMessage(content, title, messageType, users);
		try {
			PushResult result = jpushClient.sendPush(payload);
			return result;
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PushPayload buildWithExtrasAndMessage(
			String content, String title, String messageType, String... users) {
		Builder b = PushPayload.newBuilder();
		if (users.length > 0) {
			b.setAudience(Audience.newBuilder()
					.addAudienceTarget(AudienceTarget.alias(users)).build());
		}else{
			b.setAudience(Audience.newBuilder().setAll(true).build());
		}
		b.setPlatform(Platform.all());
		return b.setMessage(
				Message.newBuilder()
						.setMsgContent(content)
						.addExtra(TITLE_KEY, title)
						.addExtra(MESSAGE_TYPE_KEY, messageType)
						.addExtra(SEND_TIME_KEY,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
						.build()).build();
	}

	public static void main(String[] args){
		PushResult r = pushMessage("测试服务器推送", "服务器推送", SYS_MESSAGE);
		if(r != null){
			System.out.println(r);
		}
	}
}
