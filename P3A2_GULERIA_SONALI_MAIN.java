
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class P3A2_GULERIA_SONALI_MAIN  {

	
public static void main(String[] args) throws IOException {
	
	
	P3A2_GULERIA_SONALI_FILES obj1 = new P3A2_GULERIA_SONALI_FILES();
	System.out.println("Please Enter the File Name");
	Scanner reply = new Scanner(System.in);
    String fileName = reply.nextLine();
	
	
try{
		
		obj1.logFile = new File(fileName);
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
        	
        	obj1.storeFiles(obj1.text);
        	
        	
        	if(obj1.text.equals("revision 1.1"))
        	{
        		obj1.textNew = obj1.bufferedLog.readLine();
        		obj1.findFirstCommit(obj1.textNew);
        		obj1.storeAuthors(obj1.textNew);
        	}

                obj1.storeAuthors(obj1.text); 

        }
        
       // obj1.firstCommits = obj1.getFirstCommit();

       System.out.println("Activity 2 output");
       
      for(int i=0; i< obj1.getFiles().size();i++)
        {
        
        System.out.println("<"+obj1.getFile(i)+">"+" <"+obj1.getCommitFile(i)+">"+" <"+obj1.getMaxAuthor(i)+">"+" <"+obj1.getMaxCommits(i)+">");
      
        } 
       
        
        
}

}







