// Assignment Boggle.update92.UML
// Program BoggleFiles
// Author Bryan Fritz
// Date Dec 3, 2015

package boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//public class BoggleFiles 
//{
	import javax.swing.*;
	import java.io.*;
	import java.util.HashMap;
	import java.util.Hashtable;
	import java.util.Map;
	import java.util.function.BiConsumer;

	public class BoggleFiles {
		 // Stores paths to files with the global jarFilePath as the key
		private static Hashtable<String, String> fileCache = new Hashtable<String, String>();
		File f = new File("c:\\temp2\\boggle\\highscores.txt");
		File folder = new File("c:\\temp2\\boggle");
		//File bfolder = new File("c:\\temp\\boggle");
				
		
		
		public BoggleFiles()
		{
			//extract("highscores.txt");
			createHighScoresLocal();
		}
		
		public void createHighScoresLocal()
		{
			try {
				folder.mkdirs();
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Error creating highscores.txt");
			}
		}

	    /**
	     * Extract the specified resource from inside the jar to the local file system.
	     * @param jarFilePath absolute path to the resource
	     * @return full file system path if file successfully extracted, else null on error
	     */
	    public static String extract(String jarFilePath){

	        if(jarFilePath == null)
	        {
	        	System.out.println("NO GO NULL");
	            return null;
	        }
	        // See if we already have the file
	        if(fileCache.contains(jarFilePath))
	            return fileCache.get(jarFilePath);

	        // Alright, we don't have the file, let's extract it
	        try {
	            // Read the file we're looking for
	            InputStream fileStream = BoggleFiles.class.getResourceAsStream(jarFilePath);

	            // Was the resource found?
	            if(fileStream == null)
	            {
	            	System.out.println("NO GO NULL2");
	            	return null;
	            }
	            // Grab the file name
	            String[] chopped = jarFilePath.split("\\/");
	            String fileName = chopped[chopped.length-1];

	            // Create our temp file (first param is just random bits)
	            File tempFile = File.createTempFile("c:\\temp\\highscores.txt", fileName);

	            // Set this file to be deleted on VM exit
	            //tempFile.deleteOnExit();

	            // Create an output stream to barf to the temp file
	            OutputStream out = new FileOutputStream(tempFile);

	            // Write the file to the temp file
	            byte[] buffer = new byte[1024];
	            int len = fileStream.read(buffer);
	            while (len != -1) {
	                out.write(buffer, 0, len);
	                len = fileStream.read(buffer);
	            }

	            // Store this file in the cache list
	            fileCache.put(jarFilePath, tempFile.getAbsolutePath());

	            // Close the streams
	            fileStream.close();
	            out.close();

	            // Return the path of this sweet new file
	            return tempFile.getAbsolutePath();

	        } catch (IOException e) {
	            return null;
	        }
	    }
	
	
	
	/*public BoggleFiles()
	{
		//writeHighScoreFile();
		//wHScorefile();
		try {
			ExportResource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("NoGo!");
		}
	}

	public void wHScorefile()
	{ 
	
		Path FROM = Paths.get("wolf.wav");
		Path TO = Paths.get("c:\\temp\\wolf.wav");
		    //overwrite existing file, if exists
		 CopyOption[] options = new CopyOption[]
		 {
		 StandardCopyOption.REPLACE_EXISTING,
		 StandardCopyOption.COPY_ATTRIBUTES
		 }; 
		 try {
			Files.copy(FROM, TO, options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
    *//** Export a resource embedded into a Jar file to the local file path.
    *
    * @param resourceName ie.: "/SmartLibrary.dll"
    * @return The path to the exported resource
    * @throws Exception
    *//*
   static public String ExportResource() throws Exception {
       InputStream stream = null;
       OutputStream resStreamOut = null;
       String jarFolder;
       File newone;
       URL resourceName2 = BoggleFiles.class.getResource("highscores.txt");
       String resourceName = "highscores.txt";
       try {
           stream = BoggleFiles.class.getResourceAsStream(resourceName);
           if(stream == null) {
               throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
           }

           int readBytes;
           byte[] buffer = new byte[4096];
           //jarFolder = new File(BoggleFiles.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
           newone = new File("c:\\temp\\boggle\\");
           //resStreamOut = new FileOutputStream(jarFolder + resourceName);
           resStreamOut = new FileOutputStream(newone + resourceName);
           while ((readBytes = stream.read(buffer)) > 0) {
               resStreamOut.write(buffer, 0, readBytes);
           }
       } catch (Exception ex) {
           throw ex;
       } finally {
           stream.close();
           resStreamOut.close();
       }

       return newone + resourceName;
   }*/

}
