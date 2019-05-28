package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	String input;
	String output;
	String analysis;
	String coursecode;
	String startyear;
	String endyear;
	boolean help;
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		Options options = createOptions();
		
		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}
			
			// input
			
			// output
			
			// analysis
			
			// coursecode
			if (Integer.parseInt(analysis) == 2) {
				
			}
			
			// startyear
			
			// endyear
			
			
			
		}
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
	  HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		students = new HashMap<String, Student>();
		for (String line : lines) {
			String studentID = line.split(",")[0].trim();
			Course course = new Course(line);
			if (students.containsKey(studentID)) {
				students.get(studentID).addCourse(course);
			}
			else {
				Student student = students.get(studentID);
				student = new Student(studentID);
				ArrayList<Course> courses = new ArrayList<Course>();
				student.setCoursesTaken(courses);
				student.addCourse(course);
				students.put(studentID, student);
			}
		}
		/*
		// check ArrayList<Course> in Student
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		for (String studentID : sortedStudents.keySet()) {
			Student studentch = sortedStudents.get(studentID);
			ArrayList<Course> courses = studentch.getCoursesTaken();
			for (Course course : courses) {
				course.printCourse(course);
			}
		}*/
		
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
		linesToBeSaved.add("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
		for (String key : sortedStudents.keySet()) {
			Student student = sortedStudents.get(key);
			// StudentID
			String studentID = student.getStudentID();
			// TotalNumberOfSemestersRegistered
			student.setSemestersByYearAndSemester(student.getCoursesTaken());
			HashMap<String, Integer> semestersByYearAndSemester = student.getSemestersByYearAndSemester();
			/*
			// check
			Map<String, Integer> sorted = new TreeMap<String, Integer>(semestersByYearAndSemester);
			for (String hi : sorted.keySet()) {
				int a = semestersByYearAndSemester.get(hi);
				System.out.println(hi);
				System.out.println(a);
			}*/
			String totalNumOfSemesters = semestersByYearAndSemester.size() + "";
			// semester
			Map<String, Integer> sortedSemester = new TreeMap<String,Integer>(semestersByYearAndSemester); 
			for (String yearSemester : sortedSemester.keySet()) {  // yearSemester: 2002-1
				String semester = semestersByYearAndSemester.get(yearSemester) + "";
				String NumCoursesTakenInTheSemester = student.getNumCourseInNthSemester(Integer.parseInt(semester)) + "";
				linesToBeSaved.add(studentID + "," + totalNumOfSemesters + "," + semester + "," + NumCoursesTakenInTheSemester);
			}
			/*
			for (String yearSemester : semestersByYearAndSemester.keySet()) {
				String semester = semestersByYearAndSemester.get(yearSemester) + "";
				String NumCoursesTakenInTheSemester = student.getNumCourseInNthSemester(Integer.parseInt(semester)) + "";
				linesToBeSaved.add(studentID + "," + totalNumOfSemesters + "," + semester + "," + NumCoursesTakenInTheSemester);
			}*/
		}
		return linesToBeSaved; // do not forget to return a proper variable.
	}
	
	private Options createOptions() {
		Options options = new Options();
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.build());
				
		return options;
	}
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			if (Integer.parseInt(analysis) == 2) {
				coursecode = cmd.getOptionValue("c");
			}
			startyear = cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");
			help = cmd.hasOption("h");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer = "";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
}
