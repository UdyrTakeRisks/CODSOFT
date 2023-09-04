import java.io.*;
import java.util.ArrayList;

public class Model {
    // data and logic
    public static String studentName;
    public static int studentID, numOfSubjects, studentMark, totalMarks = 0;
    public static double averagePercentage;
    public static ArrayList<Integer> studentMarks;
    private static String formattedAvg;
    private static final String fileName = "StudentGradesLog.txt";
    private static final File file = new File(fileName);
    public static void calculateTotalMarks(){
        for (int studentMark : studentMarks)
            totalMarks += studentMark;
    }
    public static void calculateAveragePercentage(){
        if(totalMarks <= 0)
            View.displayMarksError();
        else {
            averagePercentage = (double) totalMarks / numOfSubjects;
            formattedAvg = String.format("%.2f%%", averagePercentage);
            View.showAveragePercentage(formattedAvg);
        }
    }
    public static void assignOverallGrade(){
        if(totalMarks<=0 || averagePercentage<=0)
            View.displayGradeError();
        else {
            if (averagePercentage >= 90)
                // A
                View.overallGradeChoice(1);
            else if (averagePercentage >= 80 && averagePercentage <= 89)
                // B
                View.overallGradeChoice(2);
            else if (averagePercentage >= 70 && averagePercentage <= 79)
                // C
                View.overallGradeChoice(3);
            else if (averagePercentage >= 50 && averagePercentage <= 69)
                // D
                View.overallGradeChoice(4);
            else
                // F
                View.overallGradeChoice(5);
        }
    }
    public static void assignGradePerSubject(){
        // use student mark
        if(studentMark >= 90 && studentMark <= 100)
            // A+
            View.gradePerSubjectChoice(1);
        else if(studentMark >= 85 && studentMark <= 89)
            // A
            View.gradePerSubjectChoice(2);
        else if(studentMark >= 80 && studentMark <= 84)
            // B+
            View.gradePerSubjectChoice(3);
        else if(studentMark >= 75 && studentMark <= 79)
            // B
            View.gradePerSubjectChoice(4);
        else if(studentMark >= 70 && studentMark <= 74)
            // C+
            View.gradePerSubjectChoice(5);
        else if(studentMark >= 65 && studentMark <= 69)
            // C
            View.gradePerSubjectChoice(6);
        else if(studentMark >= 60 && studentMark <= 64)
            // D+
            View.gradePerSubjectChoice(7);
        else if (studentMark >= 50 && studentMark <= 59)
            // D
            View.gradePerSubjectChoice(8);
        else
            // F
            View.gradePerSubjectChoice(9);
    }
    public static void displayResults(){
        View.displayStudentData(studentName,studentID,numOfSubjects);
        View.showTotalMarks(totalMarks);
        View.showAveragePercentage(formattedAvg);
        assignOverallGrade();
    }
    public static String GradePerSubject(int mark){
        // use student mark
        if(mark >= 90 && mark <= 100)
            // A+
            return "A+";
        else if(mark >= 85 && mark <= 89)
            // A
            return "A";
        else if(mark >= 80 && mark <= 84)
            // B+
            return "B+";
        else if(mark >= 75 && mark <= 79)
            // B
            return "B";
        else if(mark >= 70 && mark <= 74)
            // C+
            return "C+";
        else if(mark >= 65 && mark <= 69)
            // C
            return "C";
        else if(mark >= 60 && mark <= 64)
            // D+
            return "D+";
        else if (mark >= 50 && mark <= 59)
            // D
            return "D";
        else
            // F
            return "F";
    }
    public static String OverallGrade(double avgPercentage){
        if(avgPercentage >= 90)
            // A
            return "A";
        else if(avgPercentage >= 80 && avgPercentage <= 89)
            // B
            return "B";
        else if(avgPercentage >= 70 && avgPercentage <= 79)
            // C
            return "C";
        else if (avgPercentage >= 50 && avgPercentage <= 69)
            // D
            return "D";
        else
            // F
            return "F";
    }
    public static void writeToFile(){
        int i = 1;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
            writer.write("Student Name: " + studentName + "\n");
            writer.write("Student ID: " + studentID + "\n");
            writer.write("Number Of Subjects: " + numOfSubjects + "\n");
            for (int mark : studentMarks) {
                writer.write("Subject("+i+"): " + mark + "\n");
                writer.write("Grade: " + GradePerSubject(mark) + "\n");
                i++;
            }
            writer.write("Total Marks: " + totalMarks + "\n");
            writer.write("Average Percentage: " + formattedAvg + "\n");
            writer.write("Overall Grade: " + OverallGrade(averagePercentage));
            writer.write("\n-----------------------------\n");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void readFromFile(int password){
        String line;
        try{
            if(file.exists() && password == 1234) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while ((line = reader.readLine()) != null) {
                    View.displayContentsFromFile(line);
                }
                reader.close();
            }else{
                View.displayNoStudents();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
