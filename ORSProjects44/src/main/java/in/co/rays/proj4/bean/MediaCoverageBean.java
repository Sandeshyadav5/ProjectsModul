package in.co.rays.proj4.bean;

import java.util.Date;

public class MediaCoverageBean extends BaseBean{
	private String mediaName;
	private Date coverageDate;
	private String reporter;
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public Date getCoverageDate() {
		return coverageDate;
	}
	public void setCoverageDate(Date coverageDate) {
		this.coverageDate = coverageDate;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

}
