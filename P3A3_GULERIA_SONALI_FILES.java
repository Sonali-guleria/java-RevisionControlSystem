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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class P3A3_GULERIA_SONALI_FILES {
	


	/* declaration of all the variables begin*/
	
	protected String text;
	private String fileName;
	protected int totalFiles;
    private ArrayList<String> files = new ArrayList<>();
	private ArrayList<String> firstCommits = new ArrayList<>();
	private File logFile;
	private FileReader fileReaderLog;
	protected BufferedReader bufferedLog;	
	private ArrayList<String> lastCommits= new ArrayList<>();;
	private ArrayList <String> dateNew = new ArrayList<>();
	private String[] revisionSplit;
	private String revisionc;
	private int revisionCount;
	private ArrayList<Integer> revisions;
	


	/*method to read file*/
	
	public void readFile(String t)
	
	{
		try{
			
			logFile = new File(t);
			fileReaderLog = new FileReader(logFile);
			bufferedLog = new BufferedReader(fileReaderLog);

		}
		
		catch (FileNotFoundException e)
	   	   {
				System.out.println("File Not Found. Make sure it exists.");
			}
		
	   	catch(IOException ex) 
	   	 {
	   		System.out.println("Error reading file");
	   	 }

	}
	
// File information begin
	
/* method to get total number of files in a log*/
	
	public int getTotalFiles(String t)
	{
		if(t.startsWith("Working file: "))
		{
			totalFiles++;
			
		}
		
		return(totalFiles);
		
		
	}
	
	/*method to store file names */
	
	public void storeFiles(String t)
	{
		if(t.startsWith("Working file: "))
		{
		
			fileName = t.substring("Working file: ".length());
			files.add(fileName);
			
		}
		
	}
	
	/* Method to get all the files*/ 
	
	
	public ArrayList<String> getFiles()
	{
		return(files);
	}
	
	/* method to get required filename*/
	
	public String getFile(int i)
	{
		
		return(files.get(i));
		
	}
	
	/* method to get first and last commits of the file */
	
	public void storeFirstLastCommit(String t) throws IOException
	{
	
				   String date[] = new String[100];
				    String dateSplit;
				    
			     	if(t.startsWith("date:"))
					{
	
			     		
						date = t.split("date: ");
				        dateSplit = date[1];
				        date = dateSplit.split(";");
						dateSplit= date[0].trim();
						dateNew.add(dateSplit);
						
					}
			     	
			     	if(t.startsWith("==="))
			     	{
			     		lastCommits.add(dateNew.get(0));
			     		
			     		firstCommits.add(dateNew.get((dateNew.size()-1)));
			     		dateNew.clear();
			     		
			     	}
	                 
					} 
		   
			
		
	
	
	
	
/* get first commit of a file**/
	
	
	public String getFirstCommit(int i)
	{
		return(firstCommits.get(i));
	}
  
/*get last commit of a file*/
	
	public String getLastCommit(int i)
	{
		return(lastCommits.get(i));
	}
	/* method to get revisions per file*/
	
	public ArrayList<Integer> storeRevisions(String t)
	{
		
		
	if(t.startsWith("total revisions"))
	{
		
		revisionSplit = t.split("total revisions:");
		revisionc = revisionSplit[1].trim();
		revisionSplit =revisionc.split(";");
		revisionc = revisionSplit[0].trim();
		revisionCount = Integer.parseInt(revisionc);
	    revisions.add(revisionCount);
		
	}
	
	return(revisions);
	
   }

	
	
	/*class for revisions */
	
	public class REVISIONS
	{
	
		private int maxRevision;
		private ArrayList<Integer> revisions = new ArrayList<>();
		private ArrayList<Integer> revIndices = new ArrayList<>();
		private String maxFileName;
		
		
		/* method to get revisions per file*/
		
		public ArrayList<Integer> storeRevisions(String t)
		{
			
			
		if(t.startsWith("total revisions"))
		{
			
			revisionSplit = t.split("total revisions:");
			revisionc = revisionSplit[1].trim();
			revisionSplit =revisionc.split(";");
			revisionc = revisionSplit[0].trim();
			revisionCount = Integer.parseInt(revisionc);
		    revisions.add(revisionCount);
			
		}
		
		return(revisions);
		
	   }
		
		
		/*method to get max revision*/
		public int getMaxRevValue()
		{
			maxRevision = Collections.max(getRevisions());
			return maxRevision;
		}
		
		/*indices of files with max revisions*/
		
		public ArrayList<Integer> storeMaxRevisionIndices(int maxRevision)
		{
			 
			

			 for (int i = 0; i < getRevisions().size(); i++) 
			 {
				
			       if(getRevisions().get(i)==(maxRevision)) 
			    	   
			       {
			    	   revIndices.add(i);
			    	   
			        }
			       
			 }	
			 
			 return(revIndices);
		}
	
	
	//getter for Revisions	
		
       public ArrayList<Integer> getRevisions()
       {
    	   return(this.revisions);
       }
       
     //getter for Revisions	
		
       public int getRevision(int i )
       {
    	   return(revisions.get(i));
       }
       
   //getter for maximum revision indices 
       public ArrayList<Integer> getrevIndices()
       {
    	   return(this.revIndices);
       }
	
}
	
}
