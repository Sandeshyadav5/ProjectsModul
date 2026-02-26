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
import in.co.rays.proj4.util.JDBCDataSource;

public class CompanyModel {
	public Integer nextPk() throws DatabaseException {
		Connection conn=null;
		int pk=0;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select max(id) from st_company");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				pk=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in getting by pk");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk +1;
		
	}
	public long add(CompanyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn=null;
		int pk=0;
		CompanyBean beanExist=findByCode(bean.getCompanyCode());
		if(beanExist!=null) {
			throw new DuplicateRecordException("Company code already Exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			pk=nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("insert into st_company values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getCompanyCode());
			pstmt.setString(3, bean.getCompanyName());
			pstmt.setString(4, bean.getLocation());
			pstmt.setString(5, bean.getContactPerson());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in getting add company");
			}
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk ;
		
	}
	public void update(CompanyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn=null;
		CompanyBean beanExistCode=findByCode(bean.getCompanyCode());
		if(beanExistCode!=null  && beanExistCode.getId()!=bean.getId()) {
			throw new DuplicateRecordException("Company code already Exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("update st_company set company_code=?, company_name=?, location=?, contact_person=? where id=?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getCompanyCode());
			pstmt.setString(2, bean.getCompanyName());
			pstmt.setString(3, bean.getLocation());
			pstmt.setString(4, bean.getContactPerson());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in getting update company");
			}
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		
	}
	public void delete(CompanyBean bean) throws ApplicationException {
		Connection conn=null;
		
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("delete from st_company where id =?");
			pstmt.setLong(1, bean.getId());
	
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception: Exception in getting update company");
			}
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		
	}
	public CompanyBean findByPk(long pk) throws ApplicationException {
		Connection conn=null;
		CompanyBean bean=null;
		StringBuffer  sql=new StringBuffer("select * from st_company where id=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CompanyBean();
				bean.setId(rs.getInt(1));
				bean.setCompanyCode(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setLocation(rs.getString(4));
				bean.setContactPerson(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting by pk");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
		
	}
	public CompanyBean findByCode(String code) throws ApplicationException {
		Connection conn=null;
		CompanyBean bean=null;
		StringBuffer  sql=new StringBuffer("select * from st_company where company_code=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CompanyBean();
				bean.setId(rs.getInt(1));
				bean.setCompanyCode(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setLocation(rs.getString(4));
				bean.setContactPerson(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting by pk");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
		
	}
	public  List search(CompanyBean bean,int pageSize, int pageNo) throws ApplicationException {
		
		StringBuffer  sql=new StringBuffer("select * from st_company where 1=1");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append("and id like this"+bean.getId()+"%");
			}
			if(bean.getCompanyCode()!=null && bean.getCompanyCode().length()>0) {
				sql.append("and companycode like this"+bean.getCompanyCode()+"%");
			}
			if(bean.getCompanyName()!=null && bean.getCompanyName().length()>0) {
				sql.append("and companyName like this"+bean.getCompanyName()+"%");
			}
			if(bean.getLocation()!=null && bean.getLocation().length()>0) {
				sql.append("and location like this"+bean.getLocation()+"%");
			}
			if(bean.getContactPerson()!=null && bean.getContactPerson().length()>0) {
				sql.append("and contactPerson like this"+bean.getContactPerson()+"%");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		Connection conn=null;
		List list=new ArrayList();
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new CompanyBean();
				bean.setId(rs.getInt(1));
				bean.setCompanyCode(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setLocation(rs.getString(4));
				bean.setContactPerson(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting by pk");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
		
	}

}
