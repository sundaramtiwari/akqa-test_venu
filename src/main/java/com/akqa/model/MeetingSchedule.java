package com.akqa.model;

import java.util.Date;

/**
 * 
 * @author Venu Narukulla (email: nvkumar_mca@yahoo.com) 
 * 
 *         This java bean is to Wrap the data for each record using ItemReader 
 *         the properties of bean also reflects the columns in database
 *         if you output data to database using sqlitemWriter.
 * 
 */
public class MeetingSchedule implements Comparable<MeetingSchedule> {

	private Date requestDate;
	private String requestTime;
	private String empId;

	private Date meetingDate;
	private String meetStartTime;

	private String meetingDuration;

	private String meetEndTime;

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetStartTime() {
		return meetStartTime;
	}

	public void setMeetStartTime(String meetStartTime) {
		this.meetStartTime = meetStartTime;
	}

	public String getMeetingDuration() {
		return meetingDuration;
	}

	public void setMeetingDuration(String meetingDuration) {
		this.meetingDuration = meetingDuration;
	}

	public String getMeetEndTime() {
		return meetEndTime;
	}

	public void setMeetEndTime(String meetEndTime) {
		this.meetEndTime = meetEndTime;
	}

	@Override
	public String toString() {
		return "MeetingSchedule [meetingDate=" + meetingDate + ",empId="
				+ empId + ",  meetStartTime=" + meetStartTime
				+ ", meetingDuration=" + meetingDuration + ", meetEndTime="
				+ meetEndTime + "]";
	}

	@Override
	public int compareTo(MeetingSchedule arg0) {
		// TODO Auto-generated method stub
		return meetingDate.compareTo(arg0.meetingDate);

	}

}