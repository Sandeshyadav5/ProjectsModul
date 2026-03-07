package in.co.rays.proj4.testmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.EmiBean;
import in.co.rays.proj4.bean.NotificationBean;
import in.co.rays.proj4.model.EmiModel;
import in.co.rays.proj4.model.NotificationModel;

public class TestEmiModel {
	public static EmiModel model=new EmiModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testSearch();
	}
	public static void testAdd() throws Exception {
		EmiBean bean=new EmiBean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
		bean.setAmount(32432.33);
		bean.setDueDate(sdf.parse("2001-09-12"));
		bean.setStatus("shhfk");
		model.add(bean);
	    System.out.println("EMI added successfully");
	}
	public static void testUpdate() throws Exception {
		EmiBean bean=new EmiBean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1);		
		bean.setAmount(656564);
		bean.setDueDate(sdf.parse("2002-09-32"));
		bean.setStatus("dggrgrd");
		model.update(bean);
	    System.out.println("EMI updated successfully");
	}
	public static void testDelete() throws Exception {
		EmiBean bean=new EmiBean();
				
		bean.setId(2);
		
		model.delete(bean);
	    System.out.println("EMI deleted successfully");
	}
	public static void testFindByPk() throws Exception {
		EmiBean bean=model.findByPk(1);
		
		System.out.println(bean.getId());
		System.out.println(bean.getAmount());
		System.out.println(bean.getDueDate());
		System.out.println(bean.getStatus());
		
	}
	public static void testSearch() throws Exception {
		EmiBean bean=new EmiBean();
		List list=new ArrayList();
		list=model.search(bean);
		Iterator it =list.iterator();
		while(it.hasNext()) {
			bean=(EmiBean)it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getAmount());
			System.out.println(bean.getDueDate());
			System.out.println(bean.getStatus());
	}
}}

