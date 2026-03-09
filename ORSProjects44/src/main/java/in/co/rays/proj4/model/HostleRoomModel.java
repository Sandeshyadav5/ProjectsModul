package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.HostleRoomBean;
import in.co.rays.proj4.bean.MediaCoverageBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HostleRoomModel {
	public Integer nextpk() throws DatabaseException {
		Connection conn=null;
		int pk=0;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select max(id) from st_hostleroom");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				pk=rs.getInt(1);
			}
			pstmt.close();
		} catch (Exception e) {
		throw new DatabaseException("Exception in getting pk");
		}
		return pk+1;
	}
	public long add(HostleRoomBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn=null;
		int pk=0;
		HostleRoomBean existName = findByStudent(bean.getStudent_name());
		if (existName != null ) {
			throw new DuplicateRecordException("Hostle Student name already exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pk=nextpk();
			PreparedStatement pstmt=conn.prepareStatement("insert into st_hostleroom values( ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getStudent_name());
			pstmt.setInt(3, bean.getRoom_number());
			pstmt.setString(4, bean.getBlock_name());
			pstmt.setDate(5, new java.sql.Date(bean.getAllotment_date().getTime()));
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");
			}throw new ApplicationException("Exception in adding student");
		
		}
		return pk;
	}
	public void update(HostleRoomBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn=null;
		HostleRoomBean existName = findByStudent(bean.getStudent_name());
		if (existName != null && existName.getId() != bean.getId()) {
			throw new DuplicateRecordException("Hostle Student name already exist");
		}
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt=conn.prepareStatement("update st_hostleroom set student_name=?, room_number=?, block_name=?, allotment_date=? where id=?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getStudent_name());
			pstmt.setInt(2, bean.getRoom_number());
			pstmt.setString(3, bean.getBlock_name());
			pstmt.setDate(4, new java.sql.Date(bean.getAllotment_date().getTime()));
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");
			}throw new ApplicationException("Exception in updating student");
		
		}
		
	}
	public void delete(HostleRoomBean bean) throws ApplicationException {
		Connection conn=null;
		
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt=conn.prepareStatement("delete from st_hostleroom where id=?");
			pstmt.setLong(1, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback");
			}throw new ApplicationException("Exception in deleting student");
		
		}
		
	}
	public HostleRoomBean findByPk(long pk) throws  ApplicationException {
		Connection conn=null;
		HostleRoomBean bean=null;
		StringBuffer sql=new StringBuffer("select * from st_hostleroom where id=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new HostleRoomBean();
				bean.setId(rs.getLong(1));
				bean.setStudent_name(rs.getString(2));
				bean.setRoom_number(rs.getInt(3));
				bean.setBlock_name(rs.getString(4));
				bean.setAllotment_date(rs.getDate(5));
			}
			pstmt.close();
		} catch (Exception e) {
		throw new ApplicationException("Exception in getting pk");
		}
		return bean;
	}
	public HostleRoomBean findByStudent(String name) throws  ApplicationException {
		Connection conn=null;
		HostleRoomBean bean=null;
		StringBuffer sql=new StringBuffer("select * from st_hostleroom where student_name=?");
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean=new HostleRoomBean();
				bean.setId(rs.getLong(1));
				bean.setStudent_name(rs.getString(2));
				bean.setRoom_number(rs.getInt(3));
				bean.setBlock_name(rs.getString(4));
				bean.setAllotment_date(rs.getDate(5));
			}
			pstmt.close();
		} catch (Exception e) {
		throw new ApplicationException("Exception in getting name");
		}
		return bean;
	}
	
	public List search(HostleRoomBean bean) throws  ApplicationException {
		Connection conn=null;
		List list=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from st_hostleroom where 1=1");
		if(bean!=null) {
			if (bean.getId()>0) {
				if (bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
				if (bean.getStudent_name() != null && bean.getStudent_name().length() > 0) {
					sql.append(" and name like '" + bean.getStudent_name() + "%'");
				}
				if (bean.getRoom_number() >0 ) {
					sql.append(" and Room number = " + bean.getRoom_number());
				}
				if (bean.getBlock_name() != null && bean.getBlock_name().length() > 0) {
					sql.append(" and Block_name like '" + bean.getBlock_name() + "%'");
				}
				if (bean.getAllotment_date() != null ) {
					sql.append(" and Allotment_date like '" + bean.getAllotment_date() + "%'");
				}
			}
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setStudent_name(rs.getString(2));
				bean.setRoom_number(rs.getInt(3));
				bean.setBlock_name(rs.getString(4));
				bean.setAllotment_date(rs.getDate(5));
				list.add(bean);
			}
			pstmt.close();
		} catch (Exception e) {
		throw new ApplicationException("Exception in getting pk");
		}
		
	}
		return list;
	
	}
}
