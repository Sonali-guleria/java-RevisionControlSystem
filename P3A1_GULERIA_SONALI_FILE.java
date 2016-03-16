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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;


public class P3A1_GULERIA_SONALI_FILE {
	


	/* declaration of all the variables begin*/
	
	protected String text;
	private int totalFiles;
	private String fileName;
	private int maxAuthor;
	private ArrayList<Integer> authorCount = new ArrayList<>();
	//private ArrayList<String> firstCommit = new ArrayList<>();
    private ArrayList<String> files = new ArrayList<>();
    private ArrayList<String> authorsFile = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<Integer> authorsMaxCount = new ArrayList<>();
    protected File logFile;
	protected FileReader fileReaderLog;
	protected BufferedReader bufferedLog;	

	



//Files generic information begins 
	
/* method to get total number of files in a log*/
	
	public void getTotalFiles(String t)
	{
		if(t.startsWith("Working file: "))
		{
			totalFiles++;
			
		}
		

		
	}
	
	/* method to get total files*/
	public int getTotalFilesCount()
	{
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
	
	/*method to get all the files*/
	
	public ArrayList<String> getFiles()
	{
		return(files);
	}
	
	
	/* method to get required */
	
	public String getFile(int i)
	{
		
		return(files.get(i));
		
	}
	
// Authors information begin
	
	/* method to store list of authors of each file */
	
	public void storeAuthors(String t)
	{
	
		if(t.contains("author: "))
				
		{
			//System.out.println(t);
	  String author[] = new String[100];
	  author = t.split("author: ");
	  String authorName=author[1];
	  author = authorName.split(";");
	  authorName = author[0].trim();
	 
	  authors.add(authorName); //maintaining  array of all the authors
	  
	  if(!authorsFile.contains(authorName))
	      {
		  authorsFile.add(authorName);
	      }
	   
	    }
		
	if(t.startsWith("=========="))
	{
		// System.out.println(authorsFile);
		 storeAuthorCount(authorsFile);
		 authorsFile.clear();  		//clear for next file
	}
	
	}
	
	
	/* method to store author count per file*/
	
	public void storeAuthorCount(ArrayList<String> author)
	{
	
		int i = author.size();
		authorCount.add(i);
		
		
	}
	
	
	/* get method for storeAuthorCount */
	
	public ArrayList <Integer> getAuthorCount()
	
	{
		return(authorCount);
	}
	/* method to get indices of files with maximum commits to it */
	
	public int getMaxAuthors()
	{
		maxAuthor = Collections.max(getAuthorCount());
		
		return(maxAuthor);
	}
	
	/*indices of files with max authors committing to it */
	
	public void storeMaxAuthorIndices()
	{
		getMaxAuthors();
		

		 for (int i = 0; i < getAuthorCount().size(); i++) 
		 {
			
		       if(getAuthorCount().get(i)==(maxAuthor)) 
		    	   
		       {
		    	   authorsMaxCount.add(i);
		    	   
		        }
		       
		 }	
		 
		
	}
	
/* getter for storeMaxAuthorIndices */
	public ArrayList<Integer> getMaxAuthorIndices() 
	{
		 return(authorsMaxCount);
	}
	
	
	
	/*class for revisions */
	
	public class REVISIONS
	{
	
		private int maxRevision;
		private ArrayList<Integer> revisions = new ArrayList<>();
		private ArrayList<Integer> revIndices = new ArrayList<>();
	    private String revisionc;
		private int revisionCount;
		private String revisionSplit [] = new String[2];
		
		
		/* method to get revisions per file*/
		
		public void storeRevisions(String t)
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

		
	   }
		
		
		/*method to get max revision*/
		public int getMaxRevValue()
		{
			maxRevision = Collections.max(getRevisions());
			return maxRevision;
		}
		
		/*indices of files with max revisions*/
		
		public void storeMaxRevisionIndices(int maxRevision)
		{
			 
			

			 for (int i = 0; i < getRevisions().size(); i++) 
			 {
				
			       if(getRevisions().get(i)==(maxRevision)) 
			    	   
			       {
			    	   revIndices.add(i);
			    	   
			        }
			       
			 }	
			 
		
		}

		
	//getter for Revisions	
		
       public ArrayList<Integer> getRevisions()
       {
    	   return(this.revisions);
       }
       
   //getter for maximum revision indices 
       public ArrayList<Integer> getrevIndices()
       {
    	   return(revIndices);
       }
	
  //getter for revisions per file
       
       public int getRevision(int i)
       {
    	   return(revisions.get(i));
       }
}
	
}

	








