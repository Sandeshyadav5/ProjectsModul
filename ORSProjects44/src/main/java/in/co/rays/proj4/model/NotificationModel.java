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
import in.co.rays.proj4.bean.NotificationBean;
import in.co.rays.proj4.util.JDBCDataSource;

public class NotificationModel {
	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_notification");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(NotificationBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		NotificationBean existCode=findByCode(bean.getNotificationCode());
		if(existCode!=null) {
			throw new DuplicateRecordException("Notification code already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_notification values( ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getNotificationCode());
			pstmt.setString(3, bean.getMessage());
			pstmt.setString(4, bean.getSentTo());
			pstmt.setDate(5, new java.sql.Date(bean.getSentTime().getTime()));
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(" Exception in add rollback"+ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add notification");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(NotificationBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_notification set notification_code=?, message=?, sent_to=? , sent_time=? where id =?  ");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getNotificationCode());
			pstmt.setString(2, bean.getMessage());
			pstmt.setString(3, bean.getSentTo());
			pstmt.setDate(4, new java.sql.Date(bean.getSentTime().getTime()));
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(" Exception in update rollback"+ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in update notification");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(NotificationBean bean) throws ApplicationException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_notification where id =?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(" Exception in delete rollback");
			}
			throw new ApplicationException("Exception : Exception in delete ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public NotificationBean findByPk(long pk) throws DatabaseException {
		Connection conn = null;
		NotificationBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_notification where id =?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new NotificationBean();
				bean.setId(rs.getLong(1));
				bean.setNotificationCode(rs.getString(2));
				bean.setMessage(rs.getString(3));
				bean.setSentTo(rs.getString(4));
				bean.setSentTime(rs.getDate(5));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public NotificationBean findByCode(String code) throws DatabaseException {
		Connection conn = null;
		NotificationBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_notification where notification_code =?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new NotificationBean();
				bean.setId(rs.getLong(1));
				bean.setNotificationCode(rs.getString(2));
				bean.setMessage(rs.getString(3));
				bean.setSentTo(rs.getString(4));
				bean.setSentTime(rs.getDate(5));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List search(NotificationBean bean) throws DatabaseException {
		Connection conn = null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_notification where 1=1");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new NotificationBean();
				bean.setId(rs.getLong(1));
				bean.setNotificationCode(rs.getString(2));
				bean.setMessage(rs.getString(3));
				bean.setSentTo(rs.getString(4));
				bean.setSentTime(rs.getDate(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
