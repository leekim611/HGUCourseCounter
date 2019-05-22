package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
// add ArrayList, HashMap import
public class Student {

	private String studentID;
	private ArrayList<Course> coursesTaken;
	private HashMap<String, Integer> semestersByYearAndSemester;
	
	public Student(String studentID) {
		this.studentID = studentID;
	}
	public void addCourse(Course newRecord) {
		coursesTaken = new ArrayList<Course>();
		coursesTaken.add(newRecord);
	}
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSemester(int semester) {
		semestersByYearAndSemester = getSemestersByYearAndSemester();
		
		return 0;
	}
	
	// additional setter
	public void setSemestersByYearAndSemester(ArrayList<Course> coursesTaken){
		semestersByYearAndSemester = new HashMap<String, Integer>();
		int nthSemester = 1;
		for (int index = 0; index < coursesTaken.size(); index++) {
			String yearTaken = coursesTaken.get(index).getYearTaken() + "";
			String semesterCourseTaken = coursesTaken.get(index).getSemesterCourseTaken() + "";
			String semester = yearTaken + semesterCourseTaken;
			semestersByYearAndSemester.put(semester, nthSemester++);
		}
	}
	// additional getter
	public String getStudentID() {
		return studentID;
	}
	// additional getter
	public ArrayList<Course> getCoursesTaken(){
		return coursesTaken;
	}
}
