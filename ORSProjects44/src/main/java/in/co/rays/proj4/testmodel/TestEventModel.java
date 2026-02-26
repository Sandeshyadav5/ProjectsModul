package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.model.EventModel;

public class TestEventModel {
	public static EventModel model=new EventModel();
	public static void main(String[] args) {
	testAdd();
//	testUpdate();
//	testDelete();
//	testFindByPk();
//	testFindByTitle();
//	testSearch();
	}

	public static void testAdd()   {
		EventBean bean=new EventBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		bean.setTitle("BirthdayParty");
		bean.setDescription("Have fun");
		try {
			bean.setEvent_date(sdf.parse("2025-04-04"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setStartTime("morning");
		bean.setEndTime("evening");
		bean.setVenue("indore");
		bean.setOrganizerName("sandesh");
		bean.setContactEmail("ahirsandesh5@gmail");
		bean.setContactMobile("9323742883");
		bean.setStatus("Active");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			
			long pk=model.add(bean);
			System.out.println("event added sucessfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testUpdate()  {
		EventBean bean=new EventBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		bean.setId(1);
		bean.setTitle("marriage");
		bean.setDescription("Have fun");
		try {
			bean.setEvent_date(sdf.parse("2025-05-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setStartTime("morning");
		bean.setEndTime("evening");
		bean.setVenue("indore");
		bean.setOrganizerName("durgesh");
		bean.setContactEmail("ahirsandesh5@gmail.com");
		bean.setContactMobile("9323742883");
		bean.setStatus("Active");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			model.update(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("event Updated sucessfully");
	}
	public static void testDelete()  {
		EventBean bean=new EventBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		try {
			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("event deleted sucessfully");
	}
	public static void testFindByPk()  {
		try {
			EventBean bean=model.findByPk(1);
			System.out.println(bean.getId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getDescription());
			System.out.println(bean.getEvent_date());
			System.out.println(bean.getStartTime());
			System.out.println(bean.getEndTime());
			System.out.println(bean.getVenue());
			System.out.println(bean.getOrganizerName());
			System.out.println(bean.getContactEmail());
			System.out.println(bean.getContactMobile());
			System.out.println(bean.getStatus());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testFindByTitle()  {
		try {
			EventBean bean=model.findByTitle("party");
			System.out.println(bean.getId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getDescription());
			System.out.println(bean.getEvent_date());
			System.out.println(bean.getStartTime());
			System.out.println(bean.getEndTime());
			System.out.println(bean.getVenue());
			System.out.println(bean.getOrganizerName());
			System.out.println(bean.getContactEmail());
			System.out.println(bean.getContactMobile());
			System.out.println(bean.getStatus());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testSearch()  {
		try {
			EventBean bean=model.findByTitle("party");
			List list =new ArrayList();
		
			list=model.search(bean);
			Iterator it=list.iterator();
			while(it.hasNext()) {
			  bean=(EventBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getDescription());
			System.out.println(bean.getEvent_date());
			System.out.println(bean.getStartTime());
			System.out.println(bean.getEndTime());
			System.out.println(bean.getVenue());
			System.out.println(bean.getOrganizerName());
			System.out.println(bean.getContactEmail());
			System.out.println(bean.getContactMobile());
			System.out.println(bean.getStatus());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
