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

//import P3A3_GULERIA_SONALI_FILES.REVISIONS;


public class P3A3_GULERIA_SONALI_MAIN {



public static void main(String[] args) throws IOException {
	
	
	P3A3_GULERIA_SONALI_FILES obj1 = new P3A3_GULERIA_SONALI_FILES();
	P3A3_GULERIA_SONALI_FILES.REVISIONS obj2 = obj1.new REVISIONS();
	System.out.println("Please Enter the File Name");
	Scanner reply = new Scanner(System.in);
    String fileNames = reply.nextLine();
	
	    obj1.readFile(fileNames);
	    
	    while((obj1.text = obj1.bufferedLog.readLine()) != null)
		 {
   
	    	obj1.storeFiles(obj1.text);
        	obj2.storeRevisions(obj1.text);
        	obj1.storeFirstLastCommit(obj1.text);

        }	
        
        
        
        
       String logCsv = "P3A3_Guleria_Sonali.csv";
       CSVWriter writer = new CSVWriter(new FileWriter(logCsv, true));
        
       
       String header[] = "File Name, Number of Commits, First Date of Commit, Last Day of Commit".split(",");;
       writer.writeNext(header);
        
        
        for(int i =0; i< obj1.getFiles().size(); i++)
        {
        	
        	String record[] =	{obj1.getFile(i), Integer.toString(obj2.getRevision(i)),
        			obj1.getFirstCommit(i), obj1.getLastCommit(i)};
        	
        	writer.writeNext(record);   
       
        }

    	writer.close(); 
        System.out.println("File Exported to CSV");     
        reply.close();
        obj1.bufferedLog.close();

}

}









