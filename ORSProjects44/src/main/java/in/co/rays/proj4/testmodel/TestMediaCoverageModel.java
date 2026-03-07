package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MediaCoverageBean;
import in.co.rays.proj4.bean.NotificationBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.EmiModel;
import in.co.rays.proj4.model.MediaCoverageModel;
import in.co.rays.proj4.model.NotificationModel;

public class TestMediaCoverageModel {
	public static MediaCoverageModel model = new MediaCoverageModel();

	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testSearch();
	}

	public static void testAdd()  {
		MediaCoverageBean bean = new MediaCoverageBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setMediaName("NEWS 36");
			bean.setCoverageDate(sdf.parse("2001-09-12"));
			bean.setReporter("shhfk");
			model.add(bean);
			System.out.println("MediaCoverage added successfully");
		} catch (ApplicationException | DuplicateRecordException | ParseException e) {
			// TODO: handle exception
		}
		
	}

	public static void testUpdate() {
		MediaCoverageBean bean = new MediaCoverageBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setId(1);
			bean.setMediaName("NEWS 8789");
			bean.setCoverageDate(sdf.parse("2001-09-12"));
			bean.setReporter("shhfk");
			model.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MediaCoverage updated successfully");
	}

	public static void testDelete() {
		MediaCoverageBean bean = new MediaCoverageBean();
		try {
			bean.setId(1);

			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MediaCoverage deleted successfully");
	}

	public static void testFindByPk() {
		try {
			MediaCoverageBean bean = model.findByPk(1);
			System.out.println(bean.getId());
			System.out.println(bean.getMediaName());
			System.out.println(bean.getCoverageDate());
			System.out.println(bean.getReporter());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {
		MediaCoverageBean bean = new MediaCoverageBean();
		List list = new ArrayList();
		try {
			list = model.search(bean);

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (MediaCoverageBean) it.next();

				System.out.println(bean.getId());
				System.out.println(bean.getMediaName());
				System.out.println(bean.getCoverageDate());
				System.out.println(bean.getReporter());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
