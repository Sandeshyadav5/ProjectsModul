package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.util.JDBCDataSource;

public class EventModel {
	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_event");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}
	
	public long add(EventBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
		EventBean existTitle=findByTitle(bean.getTitle());
		if(existTitle!=null) {
			throw new DuplicateRecordException("title name already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_event values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getEvent_date().getTime()));
			pstmt.setString(5, bean.getStartTime());
			pstmt.setString(6, bean.getEndTime());
			pstmt.setString(7, bean.getVenue());
			pstmt.setString(8, bean.getOrganizerName());
			pstmt.setString(9, bean.getContactEmail());
			pstmt.setString(10, bean.getContactMobile());
			pstmt.setString(11, bean.getStatus());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			int i=pstmt.executeUpdate();
			System.out.println("success" +i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");

			}
			throw new ApplicationException("Exception: Exception in getting by add");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(EventBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		EventBean existName=findByTitle(bean.getTitle());
		if(existName!=null && existName.getId()!=bean.getId()) {
			throw new DuplicateRecordException("Event title already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_event set title=?, description=?, event_date=?, start_time=?, end_time=?,venue=?, organizer_name=?,contact_email=?,contact_mobile=?,status=?, created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=? ");
			pstmt.setLong(15, bean.getId());
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getEvent_date().getTime()));
			pstmt.setString(4, bean.getStartTime());
			pstmt.setString(5, bean.getEndTime());
			pstmt.setString(6, bean.getVenue());
			pstmt.setString(7, bean.getOrganizerName());
			pstmt.setString(8, bean.getContactEmail());
			pstmt.setString(9, bean.getContactMobile());
			pstmt.setString(10, bean.getStatus());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");

			}
			throw new ApplicationException("Exception: Exception in update");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	public void delete(EventBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"delete from st_event where id =?");
			pstmt.setLong(1, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");

			}
			throw new ApplicationException("Exception: Exception in delete");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	public EventBean findByPk(long pk) throws  ApplicationException {
		Connection conn = null;
		EventBean bean=null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_event where id=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean=new EventBean();
				bean.setId(rs.getLong(1));
				bean.setTitle(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setEvent_date(rs.getDate(4));
				bean.setStartTime(rs.getString(5));
				bean.setEndTime(rs.getString(6));
				bean.setVenue(rs.getString(7));
				bean.setOrganizerName(rs.getString(8));
				bean.setContactEmail(rs.getString(9));
				bean.setContactMobile(rs.getString(10));
				bean.setStatus(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception:  Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	public EventBean findByTitle(String title) throws  ApplicationException {
		Connection conn = null;
		EventBean bean=null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_event where title=?");
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean=new EventBean();
				bean.setId(rs.getLong(1));
				bean.setTitle(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setEvent_date(rs.getDate(4));
				bean.setStartTime(rs.getString(5));
				bean.setEndTime(rs.getString(6));
				bean.setVenue(rs.getString(7));
				bean.setOrganizerName(rs.getString(8));
				bean.setContactEmail(rs.getString(9));
				bean.setContactMobile(rs.getString(10));
				bean.setStatus(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
			pstmt.close();
		}catch (Exception e) {
			throw new ApplicationException("Exception:  Exception in getting name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	public List search(EventBean bean) throws  ApplicationException {
		Connection conn = null;
		List list=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from st_event where 1=1");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean=new EventBean();
				bean.setId(rs.getLong(1));
				bean.setTitle(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setEvent_date(rs.getDate(4));
				bean.setStartTime(rs.getString(5));
				bean.setEndTime(rs.getString(6));
				bean.setVenue(rs.getString(7));
				bean.setOrganizerName(rs.getString(8));
				bean.setContactEmail(rs.getString(9));
				bean.setContactMobile(rs.getString(10));
				bean.setStatus(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception:  Exception in search");
		} finally {

			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
