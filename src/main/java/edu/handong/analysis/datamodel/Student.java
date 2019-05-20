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
		coursesTaken.add(newRecord);
	}
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSementer(int semester) {
		
		return 0;
	}
}
