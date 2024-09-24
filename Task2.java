import java.util.*;
public class Task2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of subjects: ");
        int totalSubjects=sc.nextInt();
        int totalMarks=0;
        int mark;
        for(int i=1;i<=totalSubjects;i++){
            System.out.print("Enter marks of subject "+i+" out of 100 : ");
            mark=sc.nextInt();
            totalMarks=totalMarks+mark;
        }

        double avgPercentage=(double)totalMarks/totalSubjects;
        char grade=calculateGrade(avgPercentage);
        System.out.println("Total Marks Obtained : "+totalMarks);
        System.out.println("Percentage : "+avgPercentage);
        System.out.println("Grade :"+grade);
        sc.close();
    }
    public static char calculateGrade(double avgPercentage){

        if(avgPercentage>=90){
            return 'A';
        }else if(avgPercentage>=80){
            return 'B';
        }else if(avgPercentage>=70){
            return 'C';
        }else if(avgPercentage>=60){
            return 'D';
        }else{
            return 'F';
        }
    }
}