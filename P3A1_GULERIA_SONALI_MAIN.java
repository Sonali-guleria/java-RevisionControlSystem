/*Author: Sonali Guleria
 * Date Created- 11-01-2015
 * Date Modified: 11-14-2015
 * Description: the program parsing the log file and answering below questions:
 * How many files are in the logging data?
 *Which file has the most number of revisions?
 *Which file has the most number of users committing things to it?
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class P3A1_GULERIA_SONALI_MAIN {

	

	
	
public static void main(String[] args) throws IOException {
	
	
	P3A1_GULERIA_SONALI_FILE obj1 = new P3A1_GULERIA_SONALI_FILE();
	
	
	P3A1_GULERIA_SONALI_FILE.REVISIONS obj2 = obj1.new REVISIONS();
	
	System.out.println("Please Enter the File Name");
	Scanner reply = new Scanner(System.in);
    String fileNames;
	fileNames = reply.nextLine();
	
	
try{
		
	
		
		obj1.logFile = new File(fileNames);
		obj1.fileReaderLog = new FileReader(obj1.logFile);
		obj1.bufferedLog = new BufferedReader(obj1.fileReaderLog);
		
	
	}
	
	catch (FileNotFoundException e)
   	   {
			System.out.println("File Not Found. Make sure it exists.");
		}
	
   	catch(IOException ex) 
   	 {
   		System.out.println("Error reading file");
   	 }

        while((obj1.text = obj1.bufferedLog.readLine()) != null)
       
        {
        	 /*parsing file for required fields*/
        	obj1.getTotalFiles(obj1.text);
        	 obj1.storeFiles(obj1.text);
        	 obj2.storeRevisions(obj1.text);
        	// obj1.authorsName = obj1.storeAuthors(obj1.text);
        	//obj1.firstCommit = obj1.getFirstCommit(obj1.text);
        	 obj1.storeAuthors(obj1.text);
        	 
        }
        
        /* evaluating parsed files*/
        
  
        obj2.storeMaxRevisionIndices(obj2.getMaxRevValue());
        obj1.storeMaxAuthorIndices();
        //obj1.maxUserCommits = obj1.getMaxAuthors();
        
        System.out.println("Activity 1 output:");
        
        /*Activity 1 question 1*/
        
        System.out.println("**************************************************************");
        System.out.println("the total number of files are: "+ obj1.getTotalFilesCount());
      
        /*Activity 1 question 2*/
        
        System.out.println("**************************************************************");
        System.out.println("The Files with maximum number of revisions are: "+obj2.getrevIndices().size());
        for(int i = 0; i< obj2.getrevIndices().size() ; i++)   
        {
        	
   		    System.out.println("The corresponding Files are: " +obj1.getFile((obj2.getrevIndices()).get(i)));
   		    System.out.println("The Total number of revisions made to this file are: "+obj2.getMaxRevValue());
   	    }
        
        /*Activity 1 question 3*/
        
        System.out.println("**************************************************************");
        System.out.println("The Files with maximum number of users commiting to it are: "+obj1.getMaxAuthorIndices().size());
        for(int i = 0; i< obj1.getMaxAuthorIndices().size() ; i++)   
        {
        	
   		    System.out.println("The corresponding Files are: " +obj1.getFile(obj1.getMaxAuthorIndices().get(i)));
   		    System.out.println("The Total number of users who commited to this file are: "+obj1.getMaxAuthors());
   	    }
        
        System.out.println("**************************************************************");
        reply.close();
        obj1.fileReaderLog.close();
        obj1.bufferedLog.close();
}

  
}
