package in.co.rays.proj4.bean;

import java.util.Date;

public class NotificationBean extends BaseBean {
	
	private String notificationCode;
	private String message;
	private String sentTo;
	private Date sentTime;
	public String getNotificationCode() {
		return notificationCode;
	}
	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSentTo() {
		return sentTo;
	}
	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}
	public Date getSentTime() {
		return sentTime;
	}
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

}
