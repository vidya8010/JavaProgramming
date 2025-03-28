import java.sql.SQLOutput;
import java.util.Scanner;
public class StudentGradeCalculator {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int NoOfSub;
        System.out.println("Enter number of subject:");
        NoOfSub=sc.nextInt();
        if(NoOfSub<0){
            System.out.println("number is not valid");
        }
        double []submarks=new double[NoOfSub];
        double total=0;
        for(int i=0;i<NoOfSub;i++){
            System.out.println("Enter marks obtained in the sub(OutOf 100): ");
            submarks[i]=sc.nextInt();

            if(submarks[i]<0||submarks[i]>100){
                System.out.println("you enter invalid marks...!");
            }
            total+=submarks[i];
        }
        //total marks section
        System.out.println("Total marks of student:"+total);
        //average marks
        double avgMarks;
        avgMarks=total/NoOfSub;
        System.out.println("Average percentage of student:"+avgMarks);
        //grade section
        if(avgMarks>=90){
            System.out.println("Grade: A");
        } else if (avgMarks>=80) {
            System.out.println("Grade: B");
        } else if (avgMarks>=70) {
            System.out.println("Grade: C");
        } else if (avgMarks>=60) {
            System.out.println("Grade: D");
        } else if (avgMarks>=50) {
            System.out.println("Grade: E");
        } else {
            System.out.println("Fail");
        }
    }
}
