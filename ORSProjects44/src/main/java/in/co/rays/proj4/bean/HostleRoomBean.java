package in.co.rays.proj4.bean;

import java.util.Date;

public class HostleRoomBean extends BaseBean{
	private String student_name;
	private int room_number;
	private String block_name;
	private Date allotment_date;
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public Date getAllotment_date() {
		return allotment_date;
	}
	public void setAllotment_date(Date allotment_date) {
		this.allotment_date = allotment_date;
	}
	

}
