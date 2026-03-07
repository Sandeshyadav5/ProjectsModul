package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.bean.MediaCoverageBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MediaCoverageModel {
	public Integer nextpk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_mediacoverage");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(MediaCoverageBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		MediaCoverageBean existName = findByName(bean.getMediaName());
		if (existName != null) {
			throw new DuplicateRecordException("MediaCoverage title already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pk = nextpk();
			PreparedStatement pstmt = conn.prepareStatement("insert into st_mediacoverage values( ?, ?, ?, ?) ");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getMediaName());
			pstmt.setDate(3, new java.sql.Date(bean.getCoverageDate().getTime()));
			pstmt.setString(4, bean.getReporter());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback add pk");
			}
			throw new ApplicationException("Exception in adding media");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(MediaCoverageBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		MediaCoverageBean existName = findByName(bean.getMediaName());
		if (existName != null && existName.getId() != bean.getId()) {
			throw new DuplicateRecordException("MediaCoverage title already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_mediacoverage set media_name=?, coverage_date=?, reporter=? where id=? ");
			pstmt.setLong(4, bean.getId());
			pstmt.setString(1, bean.getMediaName());
			pstmt.setDate(2, new java.sql.Date(bean.getCoverageDate().getTime()));
			pstmt.setString(3, bean.getReporter());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback update pk");
			}
			throw new ApplicationException("Exception in updating media");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(MediaCoverageBean bean) throws ApplicationException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_mediacoverage where id =? ");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Exception in rollback delete pk");
			}
			throw new ApplicationException("Exception in updating media");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public MediaCoverageBean findByPk(long pk) throws ApplicationException {
		Connection conn = null;
		MediaCoverageBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_mediacoverage where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setMediaName(rs.getString(2));
				bean.setCoverageDate(rs.getDate(3));
				bean.setReporter(rs.getString(4));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public MediaCoverageBean findByName(String name) throws ApplicationException {
		Connection conn = null;
		MediaCoverageBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_mediacoverage where media_name=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setMediaName(rs.getString(2));
				bean.setCoverageDate(rs.getDate(3));
				bean.setReporter(rs.getString(4));
			}
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List search(MediaCoverageBean bean) throws ApplicationException {
			
			Connection conn=null;
			List list=new ArrayList();
			StringBuffer sql=new StringBuffer("select * from st_mediacoverage where 1=1");
			if(bean!=null) {
				if (bean.getId()>0) {
					if (bean.getId() > 0) {
						sql.append(" and id = " + bean.getId());
					}
					if (bean.getMediaName() != null && bean.getMediaName().length() > 0) {
						sql.append(" and name like '" + bean.getMediaName() + "%'");
					}
					if (bean.getCoverageDate() != null ) {
						sql.append(" and CoverageDate = " + bean.getCoverageDate());
					}
					if (bean.getReporter() != null && bean.getReporter().length() > 0) {
						sql.append(" and Reporter like '" + bean.getReporter() + "%'");
					}
				}

			try {
				conn=JDBCDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					bean.setId(rs.getInt(1));
					bean.setMediaName(rs.getString(2));
					bean.setCoverageDate(rs.getDate(3));
					bean.setReporter(rs.getString(4));
					list.add(bean);
				}
				pstmt.close();
			} catch (Exception e) {
				throw new ApplicationException("Exception in getting pk");
			}finally {
				JDBCDataSource.closeConnection(conn);
			}
			
		}
			return list;

	}}
