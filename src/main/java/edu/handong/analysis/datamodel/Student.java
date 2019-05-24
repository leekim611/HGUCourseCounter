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
	public int getNumCourseInNthSemester(int semester) {
		int courseCount = 0;
		for (String yearSemester : semestersByYearAndSemester.keySet()) {
			int nthSemester = semestersByYearAndSemester.get(yearSemester);
			if (nthSemester == semester) {
				for (Course course : coursesTaken) {
					String yearSemesterInCourse = course.getYearTaken() + "" + "-" + course.getSemesterCourseTaken() + "";
					if (yearSemester.equals(yearSemesterInCourse)) {
						courseCount++;
					}
				}
			}
		}
		return courseCount;
	}
	
	// additional setter
	public void setSemestersByYearAndSemester(ArrayList<Course> coursesTaken){
		semestersByYearAndSemester = new HashMap<String, Integer>();
		int nthSemester = 1;
		String previousYearTaken = coursesTaken.get(0).getYearTaken() + "";
		String previousSemesterCourseTaken = coursesTaken.get(0).getSemesterCourseTaken() + "";
		for (int index = 0; index < coursesTaken.size(); index++) {
			String yearTaken = coursesTaken.get(index).getYearTaken() + "";
			String semesterCourseTaken = coursesTaken.get(index).getSemesterCourseTaken() + "";
			String semester = yearTaken + "-" + semesterCourseTaken;
			if (!previousYearTaken.equals(yearTaken) || !previousSemesterCourseTaken.equals(semesterCourseTaken)) {
				nthSemester++;
			}
			previousYearTaken = yearTaken;
			previousSemesterCourseTaken = semesterCourseTaken;
			semestersByYearAndSemester.put(semester, nthSemester);
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
	// additional setter
	public void setCoursesTaken(ArrayList<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}
	// additional setter
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
}
