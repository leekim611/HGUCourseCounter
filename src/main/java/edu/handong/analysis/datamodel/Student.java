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
	/*public HashMap<String, Integer> setSemestersByYearAndSemester(ArrayList<Course> coursesTaken){
		for(int index = 0; index < coursesTaken.size(); index++) {
			semestersByYearAndSemester.put(coursesTaken.get(index))
		}
	}*/
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		semestersByYearAndSemester = new HashMap<String, Integer>();
		int nthSemester = 1;
		for (int index = 0; index < coursesTaken.size(); index++) {
			String yearTaken = coursesTaken.get(index).getYearTaken() + "";
			String semesterCourseTaken = coursesTaken.get(index).getSemesterCourseTaken() + "";
			String semester = yearTaken + semesterCourseTaken;
			semestersByYearAndSemester.put(semester, nthSemester++);
		}
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSemester(int semester) {
		semestersByYearAndSemester = getSemestersByYearAndSemester();
		
		return 0;
	}
	
	public String getStudentID() {
		return studentID;
	}
}
