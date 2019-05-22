package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		/*
		for (String keyValue : sortedStudents.keySet()) {
			System.out.println(keyValue + "==================================================================");
			Student student = sortedStudents.get(keyValue);
			ArrayList<Course> coursesTaken = student.getCoursesTaken();
			for (Course course : coursesTaken) {
				course.printCourse(course);
			}
			
		}
		*/
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
 		students = new HashMap<String, Student>();
		/*
		for (String line : lines) {
			String studentID = line.split(",")[0].trim();
			Student student = new Student(studentID);
			Course course = new Course(line);
			student.addCourse(course);
			
			students.put(studentID, student);
		}*/
		for (int index = 0; index < lines.size(); index++) {
			String studentID = lines.get(index).split(",")[0].trim();
			Student student = new Student(studentID);
			Course course = new Course(lines.get(index));
			student.addCourse(course);
			if (index == lines.size() - 1) {
				students.put(studentID, student);
				break;
			}
			if (!(lines.get(index).split(",")[0].trim()).equals(lines.get(index+1).split(",")[0].trim())) {
				students.put(studentID, student);
			}
			// check ArrayList<Course> in Student
			ArrayList<Course> courses = student.getCoursesTaken();
			System.out.println("[" + courses.size() + "]");
			for (Course coursea : courses) {
				coursea.printCourse(coursea);
			}
		}
		
		for (String keyValue : students.keySet()) {
			Student student = students.get(keyValue);
			ArrayList<Course> courses = student.getCoursesTaken();
			System.out.println(courses.size());
			/*
			for (Course course : courses) {
				course.printCourse(course);
			}*/
		}
		
		return students; // do not forget to return a proper variable.
	}
//  StudentID, YearMonthGraduated, FistMajor, SecondMajor, 
//	CourseCode, CourseName, CourseCredit, YearTaken, SemesterTaken
//	0001, 200802, Major1, Major2, 
//	GEK10001, 채플(한국어) 1, 0.0, 2002, 1
	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		ArrayList<String> linesToBeSaved = new ArrayList<String>();
		linesToBeSaved.add("StudentID,TotalNumberOfSemestersRegistered,Semester,NumCoursesTakenInTheSemester");
		for (String key : sortedStudents.keySet()) {
			Student student = sortedStudents.get(key);
			// studentID
			String studentID = student.getStudentID();
			// total semesters
			student.setSemestersByYearAndSemester(student.getCoursesTaken());
			HashMap<String, Integer> semestersByYearAndSemester = student.getSemestersByYearAndSemester();
			/*
			for (String hi : semestersByYearAndSemester.keySet()) {
				int a = semestersByYearAndSemester.get(hi);
				System.out.println(a);
			}*/
			String totalNumOfSemesters = semestersByYearAndSemester.size() + "";
			// semester
			for (String yearSemester : semestersByYearAndSemester.keySet()) {
				String semester = semestersByYearAndSemester.get(yearSemester) + "";
				String NumCoursesTakenInTheSemester = student.getNumCourseInNthSemester(Integer.parseInt(semester)) + "";
				linesToBeSaved.add(studentID + "," + totalNumOfSemesters + "," + semester + "," + NumCoursesTakenInTheSemester);
			}
		}
		return linesToBeSaved; // do not forget to return a proper variable.
	}
}
