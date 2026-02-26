package in.co.rays.proj4.testmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.bean.CustomerBean;
import in.co.rays.proj4.model.CustomerModel;

public class TestCustomerModel {
	public static CustomerModel model = new CustomerModel();

	public static void main(String[] args) {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByCode();
		testSearch();

	}

	public static void testAdd() {

		CustomerBean bean = new CustomerBean();
		bean.setCustomerCode("23334");
		bean.setCustomerName("sandesh");
		bean.setEmail("sdf@gmail.com");
		bean.setContactNumber("64487364723");
		try {
			model.add(bean);
			System.out.println("Customer Added successfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		CustomerBean bean = new CustomerBean();
		bean.setId(1);

		bean.setCustomerCode("32455");
		bean.setCustomerName("durgesh");
		bean.setEmail("sandesh@gmail.com");
		bean.setContactNumber("64487364723");
		try {
			model.update(bean);
			System.out.println("Customer updated successfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		CustomerBean bean = new CustomerBean();
		bean.setId(3);
		try {
			model.delete(bean);
			System.out.println("Customer deleted successfully");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		try {
			CustomerBean bean = model.findByPk(1);
			System.out.println(bean.getId());
			System.out.println(bean.getCustomerCode());
			System.out.println(bean.getCustomerName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getContactNumber());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testFindByCode() {

		CustomerBean bean;
		try {
			bean = model.findByCode("32455");
			System.out.println(bean.getId());
			System.out.println(bean.getCustomerCode());
			System.out.println(bean.getCustomerName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getContactNumber());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void testSearch() {
	 CustomerBean bean=new CustomerBean();
		List list =new ArrayList();
		try {
			list=model.search(bean);
			Iterator it=list.iterator();
			while(it.hasNext()) {
			bean=(CustomerBean)it.next();
				System.out.println(bean.getId());
			System.out.println(bean.getCustomerCode());
			System.out.println(bean.getCustomerName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getContactNumber());
			}	} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
