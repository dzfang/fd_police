package com.soft.common.domain;

@SuppressWarnings("serial")
public class TWebSite extends BaseEntity {

	private long id;
	
	private String logoTitle;
	
	private long videoSize;
	
	private long docSize;

	private String videoSuffix;
	
	private String docSuffix;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogoTitle() {
		return logoTitle;
	}

	public void setLogoTitle(String logoTitle) {
		this.logoTitle = logoTitle;
	}

	public long getVideoSize() {
		return videoSize;
	}

	public void setVideoSize(long videoSize) {
		this.videoSize = videoSize;
	}

	public long getDocSize() {
		return docSize;
	}

	public void setDocSize(long docSize) {
		this.docSize = docSize;
	}

	public String getVideoSuffix() {
		return videoSuffix;
	}

	public void setVideoSuffix(String videoSuffix) {
		this.videoSuffix = videoSuffix;
	}

	public String getDocSuffix() {
		return docSuffix;
	}

	public void setDocSuffix(String docSuffix) {
		this.docSuffix = docSuffix;
	}
	
}
