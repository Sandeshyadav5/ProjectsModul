package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ExamModel {
	public Integer nextpk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_exam");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting by pk");
		}
		return pk + 1;
	}

	public long add(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		ExamBean existSubject=findBySubject(bean.getSubject());
		if(existSubject!=null) {
			throw new DuplicateRecordException("title name already exist");
		}
		try {
			pk = nextpk();
			conn = JDBCDataSource.getConnection();
		
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("insert into st_exam values( ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExamName());
			pstmt.setString(3, bean.getSubject());
			pstmt.setLong(4, bean.getTotalMarks());
			pstmt.setDate(5, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(6, bean.getDuration());
			pstmt.setLong(7, bean.getPassingMarks());
			pstmt.setString(8, bean.getExamType());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException(" Exception in rollback add");
			}
			throw new ApplicationException(" Exception in Add exam");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		ExamBean existSubject=findBySubject(bean.getSubject());
		if(existSubject!=null && existSubject.getId()!=bean.getId()) {
			throw new DuplicateRecordException("title name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_exam set exam_name=?,subject=?,total_marks=?, exam_date=?,duration=?, passing_marks=?,exam_type=? where id=? ");
			pstmt.setLong(8, bean.getId());
			pstmt.setString(1, bean.getExamName());
			pstmt.setString(2, bean.getSubject());
			pstmt.setLong(3, bean.getTotalMarks());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(5, bean.getDuration());
			pstmt.setLong(6, bean.getPassingMarks());
			pstmt.setString(7, bean.getExamType());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException(" Exception in rollback update");
			}
			throw new ApplicationException(" Exception in Update exam");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}
	public void delete(ExamBean bean) throws ApplicationException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"delete from st_exam where id=?");
			pstmt.setLong(1, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException(" Exception in rollback delete");
			}
			throw new ApplicationException(" Exception in Delete exam");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}
	public ExamBean findByPk(long pk) throws ApplicationException {
		Connection conn = null;
		ExamBean bean=new ExamBean();
		StringBuffer sql=new StringBuffer("select * from st_exam where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setExamName(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setTotalMarks(rs.getInt(4));
				bean.setExamDate(rs.getDate(5));
				bean.setDuration(rs.getString(6));
				bean.setPassingMarks(rs.getInt(7));
				bean.setExamType(rs.getString(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting by pk");
		}
		return bean;
	}
	public ExamBean findBySubject(String subject) throws ApplicationException {
		Connection conn = null;
		ExamBean bean=new ExamBean();
		StringBuffer sql=new StringBuffer("select * from st_exam where subject=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, subject);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setExamName(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setTotalMarks(rs.getInt(4));
				bean.setExamDate(rs.getDate(5));
				bean.setDuration(rs.getString(6));
				bean.setPassingMarks(rs.getInt(7));
				bean.setExamType(rs.getString(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting by pk");
		}
		return bean;
	}
	public List search(ExamBean bean) throws ApplicationException {
		Connection conn = null;
		List list=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from st_exam where 1=1");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean=new ExamBean();
				bean.setId(rs.getInt(1));
				bean.setExamName(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setTotalMarks(rs.getInt(4));
				bean.setExamDate(rs.getDate(5));
				bean.setDuration(rs.getString(6));
				bean.setPassingMarks(rs.getInt(7));
				bean.setExamType(rs.getString(8));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting by pk");
		}
		return list;
	}

}
