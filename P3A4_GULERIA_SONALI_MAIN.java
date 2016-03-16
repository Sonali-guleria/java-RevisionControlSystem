/*Author: Sonali Guleria
 * Date Created- 11-01-2015
 * Date Modified: 11-14-2015
 * Description: the program parsing the log file and stores results to csv answering below questions:
 * First column: the name of every file changed
 * Second column: Number of commits
 * Third column: First date of commit
 * Fourth column: Last date of commit
 * What’s the earliest commit to a file?
 * What user has the most number of commits?
 */



import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import com.opencsv.*;


public class P3A4_GULERIA_SONALI_MAIN {
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("Please Enter the File Name");
		Scanner reply = new Scanner(System.in);
	    String fileNames = reply.nextLine();
		
		P3A4_GULERIA_SONALI_FILES obj1 = new P3A4_GULERIA_SONALI_FILES();
		obj1.readFile(fileNames);
		obj1.getFileName(fileNames);
		
		 while((obj1.text = obj1.bufferedLog.readLine()) != null)
		 {
			 obj1.allAuthors(obj1.text);
	 
		 }
		 obj1.getUniqueAuthors();
		 obj1.getTotalCommitsUsers();
		 obj1.getFirstLastCommit();
		
		 String logCsv = "P3A4_Guleria_Sonali_CSV.csv";
	     CSVWriter writer = new CSVWriter(new FileWriter(logCsv, true));
	     
	     String header[] = "User ID,Number of Files Committed, Number of Commits, First time of Commit, Last time of Commit".split(",");
	     writer.writeNext(header);
	     
	  
	     for(int i =0; i< obj1.getAllAuthors().size(); i++)
	     {
	     	String record[] =	{obj1.getAuthor(i), Integer.toString(obj1.getTotalFileAuthor(i)),
	     			Integer.toString(obj1.getTotalCommitAuthor(i)), obj1.getFirstCommitDate(i),obj1.getLastCommitDate(i)};
	     
	      writer.writeNext(record);   
	     }

	     	writer.close(); 
	        System.out.println("File Exported to CSV");
	        
	}

}
