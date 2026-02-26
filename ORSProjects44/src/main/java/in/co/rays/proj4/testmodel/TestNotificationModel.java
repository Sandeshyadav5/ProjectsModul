package in.co.rays.proj4.testmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.NotificationBean;
import in.co.rays.proj4.model.NotificationModel;

public class TestNotificationModel {
	public static NotificationModel model=new NotificationModel();
	public static void main(String[] args) throws Exception {
		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByCode();
//		testSearch();
	}
	public static void testAdd() throws Exception {
		NotificationBean bean=new NotificationBean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
		bean.setNotificationCode("jddjdf");
		bean.setMessage("happy code");
		bean.setSentTo("colleague");
		bean.setSentTime(sdf.parse("2001-09-05"));
		model.add(bean);
	    System.out.println("Notification added successfully");
	}
	public static void testUpdate() throws Exception {
		NotificationBean bean=new NotificationBean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(2);		
		bean.setNotificationCode("sandesh");
		bean.setMessage("happy code");
		bean.setSentTo("freinds");
		bean.setSentTime(sdf.parse("2001-09-05"));
		model.update(bean);
	    System.out.println("Notification updated successfully");
	}
	public static void testDelete() throws Exception {
		NotificationBean bean=new NotificationBean();
				
		bean.setId(3);
		
		model.delete(bean);
	    System.out.println("Notification deleted successfully");
	}
	public static void testFindByPk() throws Exception {
		NotificationBean bean=model.findByPk(1);
		
		System.out.println(bean.getId());
		System.out.println(bean.getNotificationCode());
		System.out.println(bean.getMessage());
		System.out.println(bean.getSentTo());
		System.out.println(bean.getSentTime());
	}
	public static void testFindByCode() throws Exception {
		NotificationBean bean=model.findByCode("jddjdf");
		
		System.out.println(bean.getId());
		System.out.println(bean.getNotificationCode());
		System.out.println(bean.getMessage());
		System.out.println(bean.getSentTo());
		System.out.println(bean.getSentTime());
	}
	public static void testSearch() throws Exception {
		NotificationBean bean=new NotificationBean();
		List list=new ArrayList();
		list=model.search(bean);
		Iterator it =list.iterator();
		while(it.hasNext()) {
			bean=(NotificationBean)it.next();
		System.out.println(bean.getId());
		System.out.println(bean.getNotificationCode());
		System.out.println(bean.getMessage());
		System.out.println(bean.getSentTo());
		System.out.println(bean.getSentTime());
	}
}}

