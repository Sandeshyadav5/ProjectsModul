package in.co.rays.proj4.testmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.bean.CompanyBean;
import in.co.rays.proj4.model.CompanyModel;

public class TestCompanyModel {
	public static CompanyModel model=new CompanyModel();
	public static void main(String[] args) {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByCode();
//		testFindByPk();
		testSearch();
	}
	public static void testAdd() {
		CompanyBean bean=new CompanyBean();
		
		bean.setCompanyCode("GKKJS21");
		bean.setCompanyName("Infosys");
		bean.setLocation("Indore");
		bean.setContactPerson("8749293332");
		try {
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
		System.out.println("Company added successfully");
	}
	public static void testUpdate() {
		try {
		CompanyBean bean=new CompanyBean();
		bean.setId(2);
		bean.setCompanyCode("JSDSN78");
		bean.setCompanyName("TCS");
		bean.setLocation("Indore");
		bean.setContactPerson("8749293332");
			model.update(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
		System.out.println("Company updated successfully");

	}
	public static void testDelete() {
		CompanyBean bean=new CompanyBean();
		
		bean.setId(1);
		try {
			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		System.out.println("Company deleted successfully");
	}
	public static void testFindByPk() {
		try {
			CompanyBean bean=model.findByPk(1);
			
			System.out.println(bean.getId());
			System.out.println(bean.getCompanyCode());
			System.out.println(bean.getCompanyName());
			System.out.println(bean.getLocation());
			System.out.println(bean.getContactPerson());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testFindByCode() {
		try {
			CompanyBean bean=model.findByCode("JSDSN78");
			
			System.out.println(bean.getId());
			System.out.println(bean.getCompanyCode());
			System.out.println(bean.getCompanyName());
			System.out.println(bean.getLocation());
			System.out.println(bean.getContactPerson());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testSearch() {
		CompanyBean bean=new CompanyBean();
		try {
			List list=new ArrayList();
		
			list=model.search(bean, 0, 0);
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			bean=(CompanyBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCompanyCode());
			System.out.println(bean.getCompanyName());
			System.out.println(bean.getLocation());
			System.out.println(bean.getContactPerson());
		}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
