package edu.handong.analysis.datamodel;

import org.apache.commons.csv.CSVRecord;

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
	
	// make 2nd constructor: parameter is 'CSVRecord' type
	public Course(CSVRecord csvRecord) {
		studentID = csvRecord.get("StudentID");
		yearMonthGraduated = csvRecord.get("YearMonthGraduated");
		firstMajor = csvRecord.get("FistMajor");
		secondMajor = csvRecord.get("SecondMajor");
		courseCode = csvRecord.get("CourseCode");
		courseName = csvRecord.get("CourseName");
		courseCredit = csvRecord.get("CourseCredit");
		yearTaken = Integer.parseInt(csvRecord.get("YearTaken"));
		semesterCourseTaken = Integer.parseInt(csvRecord.get("SemesterTaken"));
	}
	
	// make getter method
	public String getStudentID() {
		return studentID;
	}

	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}

	public String getFirstMajor() {
		return firstMajor;
	}

	public String getSecondMajor() {
		return secondMajor;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseCredit() {
		return courseCredit;
	}
	
	public int getYearTaken() {
		return yearTaken;
	}
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	
	// for check
	public void printCourse(Course course) {
		System.out.println("[" + course.getStudentID() + "]");
		System.out.println(course.getYearMonthGraduated());
		System.out.println(course.getFirstMajor());
		System.out.println(course.getSecondMajor());
		System.out.println(course.getCourseCode());
		System.out.println(course.getCourseName());
		System.out.println(course.getCourseCredit());
		System.out.println(course.getYearTaken());
		System.out.println(course.getSemesterCourseTaken());
	}
}