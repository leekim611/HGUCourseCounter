package edu.handong.analysis.datamodel;

public class Course {

	private String studentID;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	public Course(String line) {											// e.g, first line
		studentID = line.split(",")[0].trim();								// 0001
		yearMonthGraduated = line.split(",")[1].trim();						// 200802
		firstMajor = line.split(",")[2].trim();								// Major1
		secondMajor = line.split(",")[3].trim();							// Major2
		courseCode = line.split(",")[4].trim();								// GEK10001
		courseName = line.split(",")[5].trim();								// 채플(한국어) 1
		courseCredit = line.split(",")[6].trim();							// 0.0
		yearTaken = Integer.parseInt(line.split(",")[7].trim());			// 2002
		semesterCourseTaken = Integer.parseInt(line.split(",")[8].trim());	// 1
	}
	
	public int getYearTaken() {
		return yearTaken;
	}
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
}