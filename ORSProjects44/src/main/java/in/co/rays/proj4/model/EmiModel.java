package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EmiBean;
import in.co.rays.proj4.bean.NotificationBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EmiModel {
	public Integer nextpk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_emi");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(EmiBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		EmiBean existBean=findByPk(bean.getId());
		if (existBean!=null) {
			throw new DuplicateRecordException("already exist");
			
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_emi values( ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setDouble(2, bean.getAmount());
			pstmt.setDate(3, new java.sql.Date(bean.getDueDate().getTime()));
			pstmt.setString(4, bean.getStatus());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");
			}
			throw new ApplicationException("Exception in adding user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(EmiBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		EmiBean existBean=findByPk(bean.getId());
		if (existBean!=null && existBean.getId()!=bean.getId()) {
			throw new DuplicateRecordException("already exist");
			
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update st_emi set amount=?, due_date=?, status=? where id=?");
			pstmt.setLong(4, bean.getId());
			pstmt.setDouble(1, bean.getAmount());
			pstmt.setDate(2, new java.sql.Date(bean.getDueDate().getTime()));
			pstmt.setString(3, bean.getStatus());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");

			}
			throw new ApplicationException("Exception in updating user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(EmiBean bean) throws ApplicationException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_emi where id=?");
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
			throw new ApplicationException("Exception in deleting user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public EmiBean findByPk(long pk) throws ApplicationException {
		Connection conn = null;
		EmiBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_emi where id =?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EmiBean();
				bean.setId(rs.getLong(1));
				bean.setAmount(rs.getDouble(2));
				bean.setDueDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List search(EmiBean bean) throws ApplicationException {
		Connection conn = null;
		List list =new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_emi where 1=1");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EmiBean();
				bean.setId(rs.getLong(1));
				bean.setAmount(rs.getDouble(2));
				bean.setDueDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}


}
