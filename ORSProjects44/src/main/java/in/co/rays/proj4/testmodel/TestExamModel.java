package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.ExamModel;

public class TestExamModel {
	public static ExamModel model = new ExamModel();

	public static void main(String[] args) throws ParseException {
//         testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindBySubject();
//		testSearch();
	}

	public static void testAdd() throws ParseException {
		ExamBean bean = new ExamBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setExamName("Board");
			bean.setSubject("Maths");
			bean.setTotalMarks(87);
			bean.setExamDate(sdf.parse("2025-08-18"));
			bean.setDuration("3hrs");
			bean.setPassingMarks(34);
			bean.setExamType("offline");
       
			model.add(bean);
			System.out.println("Exam added successfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testUpdate() throws ParseException {
		ExamBean bean = new ExamBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setId(2);
			bean.setExamName("Board");
			bean.setSubject("chemistry");
			bean.setTotalMarks(89);
			bean.setExamDate(sdf.parse("2025-08-19"));
			bean.setDuration("3hrs");
			bean.setPassingMarks(34);
			bean.setExamType("offline");

			model.update(bean);
			System.out.println("Exam updated successfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testDelete() {
		ExamBean bean = new ExamBean();
		try {
			bean.setId(2);
			
			model.delete(bean);
			System.out.println("Exam deleted successfully");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testFindByPk()  {
		try {
			ExamBean bean = model.findByPk(1);
			System.out.println(bean.getId());
			System.out.println(bean.getExamName());
			System.out.println(bean.getSubject());
			System.out.println(bean.getTotalMarks());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getDuration());
			System.out.println(bean.getPassingMarks());
			System.out.println(bean.getExamType());

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testFindBySubject()  {
		try {
			ExamBean bean = model.findBySubject("Maths");
			System.out.println(bean.getId());
			System.out.println(bean.getExamName());
			System.out.println(bean.getSubject());
			System.out.println(bean.getTotalMarks());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getDuration());
			System.out.println(bean.getPassingMarks());
			System.out.println(bean.getExamType());

		} catch (  ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testSearch()  {
		ExamBean bean = new ExamBean();
		try {
			List list =new ArrayList();
			list=model.search(bean);
			Iterator it=list.iterator();
			while(it.hasNext()) {
				
			bean=(ExamBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getExamName());
			System.out.println(bean.getSubject());
			System.out.println(bean.getTotalMarks());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getDuration());
			System.out.println(bean.getPassingMarks());
			System.out.println(bean.getExamType());
			}
		} catch (  ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
