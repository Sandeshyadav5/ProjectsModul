package in.co.rays.proj4.bean;

import java.util.Date;

public class ExamBean extends BaseBean{
	private String examName;
	private String subject;
	private Integer totalMarks;
	private Date examDate;
	private String duration;
	private Integer passingMarks;
	private String  examType;
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getPassingMarks() {
		return passingMarks;
	}
	public void setPassingMarks(Integer passingMarks) {
		this.passingMarks = passingMarks;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}

}
