package org.aljuarismi.algorithm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class FileUtils {
	
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);
	
    /** 
     * Open a file identified by filePath and return its content as a String
     * @param filePath      name of file to open. The file can reside
     *                      anywhere in the classpath
     * @return String File content as String
     */
    public static String readFileAsString(String filePath) throws java.io.IOException {
        
    	StringBuffer 		  fileData = new StringBuffer(1000);
        InputStream  stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        
        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);
        
        InputStreamReader  inputStream = new InputStreamReader( stream, Charset.defaultCharset());
        BufferedReader 			reader = new BufferedReader( inputStream);
        char[] 					   buf = new char[1024];
        int 				   numRead = 0;
        
        try{
	        while ((numRead = reader.read(buf)) != -1) {
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	            buf = new char[1024];
	        }
        }catch(IOException e){
			String error ="Error reading file: " + filePath;
		    log.error(error);
			throw e;       	
        }finally{
            reader.close();        	
        }
        return fileData.toString();
    }

    /** 
     * Open a file identified by filePath and return its content as a XMLStreamReader
     * @param filePath      name of file to open. The file can reside
     *                      anywhere in the classpath
     * @return XMLStreamReader Reader to access to the xml file
     * @throws FactoryConfigurationError 
     * @throws XMLStreamException 
     */
    public static XMLStreamReader readFileAsXmlStream(String filePath) throws java.io.IOException, XMLStreamException, FactoryConfigurationError {
		
    	String xml = readFileAsString(filePath); 
    	XMLStreamReader resultReader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));

    	return resultReader; 
    } 
    
    
    /** 
     * Open a file identified by filePath and return its content as a integer array
     * The file must contain a integer in each line.
     * @param filePath      name of file to open. The file can reside
     *                      anywhere in the classpath
     * @return int[] File content as Array
     */
    public static ArrayList<Integer> readFileAsIntArray(String filePath) throws java.io.IOException {
        
    	ArrayList<Integer> resultArray = new ArrayList<Integer>();
        InputStream    stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        
        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);
        
        Scanner  inputScanner = new Scanner( stream);

        while( inputScanner.hasNext()){
        	resultArray.add(inputScanner.nextInt());
        }
        
        inputScanner.close();
        
        return resultArray;
    }
    
    
    
}
