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
import in.co.rays.proj4.bean.CompanyBean;
import in.co.rays.proj4.bean.CustomerBean;
import in.co.rays.proj4.util.JDBCDataSource;

public class CustomerModel {
	public Integer  nextPk() throws DatabaseException {
		Connection conn=null;
		int pk=0;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select max(id) from st_customer");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				pk=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in gettin by pk");
		}
		return pk + 1;
	}
	public long	  add(CustomerBean bean) throws ApplicationException, DuplicateRecordException  {
		Connection conn=null;
		int pk=0;
		CustomerBean beanExistCode=findByCode(bean.getCustomerCode());
		if(beanExistCode!=null) {
			throw new DuplicateRecordException("Company code already Exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			pk=nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("insert into st_customer values( ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getCustomerCode());
			pstmt.setString(3, bean.getCustomerName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getContactNumber());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in rollback");
			}throw new ApplicationException("Exception: Exception in gettin by pk");
		}finally {
			JDBCDataSource.getConnection();
		}
		return pk;
	}
	public void	  update(CustomerBean bean) throws ApplicationException, DuplicateRecordException  {
		Connection conn=null;
		CustomerBean beanExistCode=findByCode(bean.getCustomerCode());
		if(beanExistCode!=null  && beanExistCode.getId()!=bean.getId()) {
			throw new DuplicateRecordException("Company code already Exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("update st_customer set customer_code=?, customer_name=?, email=?, contact_number=? where id=?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getCustomerCode());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getContactNumber());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in rollback");
			}throw new ApplicationException("Exception: Exception in gettin by pk");
		}finally {
			JDBCDataSource.getConnection();
		}
		
	}
	public void	  delete(CustomerBean bean) throws ApplicationException  {
		Connection conn=null;
		
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("delete from st_customer where id=?");
			pstmt.setLong(1, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in rollback");
			}throw new ApplicationException("Exception: Exception in gettin by pk");
		}finally {
			JDBCDataSource.getConnection();
		}
		
	}
	public CustomerBean findByPk(long pk) throws DatabaseException {
		Connection conn=null;
		CustomerBean bean=null;
		StringBuffer sql=new StringBuffer("select * from st_customer where id=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CustomerBean();
				bean.setId(rs.getInt(1));
				bean.setCustomerCode(rs.getString(2));
				bean.setCustomerName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setContactNumber(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in gettin by pk");
		}
		return bean;
	}
	public CustomerBean findByCode(String code) throws ApplicationException {
		Connection conn=null;
		CustomerBean bean=null;
		StringBuffer sql=new StringBuffer("select * from st_customer where code=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CustomerBean();
				bean.setId(rs.getInt(1));
				bean.setCustomerCode(rs.getString(2));
				bean.setCustomerName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setContactNumber(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in gettin by code");
		}
		return bean;
	}
	public List search(CustomerBean bean) throws DatabaseException {
		Connection conn=null;
		List list=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from st_customer where 1=1");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CustomerBean();
				bean.setId(rs.getInt(1));
				bean.setCustomerCode(rs.getString(2));
				bean.setCustomerName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setContactNumber(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in gettin by code");
		}
		return list;
	}



}
