package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.HostleRoomBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.ExamModel;
import in.co.rays.proj4.model.HostleRoomModel;

public class TestHostleRoomModel {
	public static HostleRoomModel model = new HostleRoomModel();

	public static void main(String[] args) throws ParseException {
//         testAdd();
//		testUpdate();
//		testDelete();
		testFindByPk();
//		testFindByStudent();
//		testSearch();
	}

	public static void testAdd() throws ParseException {
		HostleRoomBean bean = new HostleRoomBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setStudent_name("sandesh");
			bean.setRoom_number(103);
			bean.setBlock_name("djsjdk");
			bean.setAllotment_date(sdf.parse("2022-09-08"));
       
			model.add(bean);
			System.out.println("Room added successfully");
		} catch (ApplicationException | DuplicateRecordException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testUpdate() throws ParseException {
		HostleRoomBean bean = new HostleRoomBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setId(1);
			bean.setStudent_name("durgesh");
			bean.setRoom_number(106);
			bean.setBlock_name("$43");
			bean.setAllotment_date(sdf.parse("2022-09-08"));
			
			model.update(bean);
			System.out.println("Room updated successfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testDelete() {
		HostleRoomBean bean = new HostleRoomBean();
		try {
			bean.setId(2);
			
			model.delete(bean);
			System.out.println("Room deleted successfully");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testFindByPk()  {
		try {
			HostleRoomBean bean = model.findByPk(1);
			System.out.println(bean.getId());
			System.out.println(bean.getStudent_name());
			System.out.println(bean.getRoom_number());
			System.out.println(bean.getBlock_name());
			System.out.println(bean.getAllotment_date());
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testFindByStudent()  {
		try {
			HostleRoomBean bean = model.findByStudent("durgesh");
			System.out.println(bean.getId());
			System.out.println(bean.getStudent_name());
			System.out.println(bean.getRoom_number());
			System.out.println(bean.getBlock_name());
			System.out.println(bean.getAllotment_date());

		} catch (  ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testSearch()  {
		HostleRoomBean bean = new HostleRoomBean();
		bean.setStudent_name("durgesh");
		try {
			List list =new ArrayList();
			list=model.search(bean);
			Iterator it=list.iterator();
			while(it.hasNext()) {
				
			bean=(HostleRoomBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getId());
			System.out.println(bean.getStudent_name());
			System.out.println(bean.getRoom_number());
			System.out.println(bean.getBlock_name());
			System.out.println(bean.getAllotment_date());
			}
		} catch (  ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
