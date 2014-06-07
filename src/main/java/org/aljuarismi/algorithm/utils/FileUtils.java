package org.aljuarismi.algorithm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.aljuarismi.algorithm.graph.node.*;
import org.aljuarismi.algorithm.list.SortedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Int;

import static org.aljuarismi.algorithm.graph.node.DNode.*;


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
    
    public static List<GraphNode<Integer>> readFileAsGraphNode(String filePath) throws java.io.IOException {

        InputStream  stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);

        InputStreamReader  inputStream = new InputStreamReader( stream, Charset.defaultCharset());
        BufferedReader 			reader = new BufferedReader( inputStream);
        String line;

        List<GraphNode<Integer>> graph = new ArrayList<GraphNode<Integer>>();
        List<String> fileLines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            // process the line.
            log.debug(line);
            fileLines.add(line);
            int nodeID = Integer.parseInt(line.split("\t")[0]);
            GraphNode<Integer> node = new GraphNode<Integer>();
            node.setNodeID(nodeID);
            SortedList.insert(node,graph,null,null);
        }
        reader.close();

        // Add Node References
        for(int nodeIndex = 0; nodeIndex < graph.size(); nodeIndex++){
            String[] nodeLine = fileLines.get(nodeIndex).split("\t");

            GraphNode<Integer> node = graph.get(Integer.parseInt(nodeLine[0])-1);
            for(int edgesPoints = 1; edgesPoints < nodeLine.length; edgesPoints++){
                node.addEdge(graph.get(Integer.parseInt(nodeLine[edgesPoints])-1));
            }
        }

        if(log.isDebugEnabled()){
            //volcado lista de nodos
            StringBuffer sBuffer= new StringBuffer();

            sBuffer.append("Readed Graph:\n");
            for(GraphNode<Integer> node: graph){
                sBuffer.append(node.getNodeID()).append("# ");
                for(GraphNode<Integer> edge: node.getNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("\n");
            }
            sBuffer.append("GRAPH END\n");
            log.debug(sBuffer.toString());

        }

        return graph;
    }

//TODO: Eliminar
    public static List<DirGraphNode<Integer>> readFileAsDirectedGraphNode(String filePath, int graphNodeNumber) throws java.io.IOException {


        InputStream  stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);

        InputStreamReader  inputStream = new InputStreamReader( stream, Charset.defaultCharset());
        BufferedReader 			reader = new BufferedReader( inputStream);
        String line;
        Integer currentNodeId = 1;

        List<DirGraphNode<Integer>> graph = new ArrayList<DirGraphNode<Integer>>();

        //Populate the list
        log.debug("Populating the node list");
        for(int i=1; i <= graphNodeNumber; i++){
            DirGraphNode<Integer> node = new DirGraphNode<Integer>();
            node.setNodeID(i);
            node.setTopologicalSortNumber(i);
            graph.add(node);
        }

        log.debug("Loading Nodes");
        while ((line = reader.readLine()) != null) {
            // process the line.
            log.trace(line);

            String[] nodeLine = line.split("\\s+");

            graph.get(Integer.parseInt(nodeLine[0])-1).addForwardNode(graph.get(Integer.parseInt(nodeLine[1])-1));
            graph.get(Integer.parseInt(nodeLine[1])-1).addBackwardNode(graph.get(Integer.parseInt(nodeLine[0])-1));
        }
        reader.close();

        if(log.isDebugEnabled()){
            //volcado lista de nodos
            StringBuffer sBuffer= new StringBuffer();

            sBuffer.append("Readed Graph:\n");
            for(DirGraphNode<Integer> node: graph){
                sBuffer.append(node.getNodeID()).append("# ");
                for(DirGraphNode<Integer> edge: node.getForwardsNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("# ");
                for(DirGraphNode<Integer> edge: node.getBackwardsNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("\n");
            }
            sBuffer.append("GRAPH END\n");
            log.debug(sBuffer.toString());

        }

        return graph;
    }





    // Esta es la versión definitiva refactorizada
    public static List<GNode<Integer>> readFileAsGNodeList(String filePath, int graphNodeNumber) throws java.io.IOException {


        InputStream  stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);

        InputStreamReader  inputStream = new InputStreamReader( stream, Charset.defaultCharset());
        BufferedReader 			reader = new BufferedReader( inputStream);
        String line;
        Integer currentNodeId = 1;

        List<GNode<Integer>> graph = new ArrayList<GNode<Integer>>();

        //Populate the list
        log.debug("Populating the node list");
        for(int i=1; i <= graphNodeNumber; i++){
            GNode<Integer> node = new GNode<Integer>();
            node.setNodeID(i);
            node.setPayLoad(new Integer(i));
            graph.add(node);
        }

        log.debug("Loading Nodes");
        while ((line = reader.readLine()) != null) {
            // process the line.
            log.trace(line);

            String[] nodeLine = line.split("\\s+");

            graph.get(Integer.parseInt(nodeLine[0])-1).addForwardNode(graph.get(Integer.parseInt(nodeLine[1])-1));
            graph.get(Integer.parseInt(nodeLine[1])-1).addBackwardNode(graph.get(Integer.parseInt(nodeLine[0])-1));
        }
        reader.close();

        if(log.isDebugEnabled()){
            //volcado lista de nodos
            StringBuffer sBuffer= new StringBuffer();

            sBuffer.append("Readed Graph:\n");
            for(GNode<Integer> node: graph){
                sBuffer.append(node.getNodeID()).append("# ");
                for(GNode<Integer> edge: node.getForwardsNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("# ");
                for(GNode<Integer> edge: node.getBackwardsNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("\n");
            }
            sBuffer.append("GRAPH END\n");
            log.debug(sBuffer.toString());

        }

        return graph;
    }



    public static List<DNode<Integer>> readFileAsDNodeList(String filePath) throws java.io.IOException {

        InputStream  stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        if(stream == null) throw new java.io.IOException("Error accessing file: " + filePath);

        InputStreamReader  inputStream = new InputStreamReader( stream, Charset.defaultCharset());
        BufferedReader 			reader = new BufferedReader( inputStream);
        String line;

        //GENERATE NODE LIST
        List<DNode<Integer>> graph = new ArrayList<DNode<Integer>>();
        List<String> fileLines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            // process the line.
            log.debug(line);
            fileLines.add(line);
            int nodeID = Integer.parseInt(line.split("\t")[0]);
            DNode<Integer> node = new DNode<Integer>();
            node.setNodeID(nodeID);
            node.setPayLoad(nodeID);
            graph.add(node);
        }
        reader.close();

        // ADD NODE REFERENCES
        for(int nodeIndex = 0; nodeIndex < graph.size(); nodeIndex++){
            String[] nodeLine = fileLines.get(nodeIndex).split("\t");

            DNode<Integer> node = graph.get(Integer.parseInt(nodeLine[0])-1);
            for(int edgesPoints = 1; edgesPoints < nodeLine.length; edgesPoints++){
                String[] edgeInfo = nodeLine[edgesPoints].split(",");
                Edge<Integer> newEdge;
                newEdge = new Edge<Integer>(graph.get(Integer.parseInt(edgeInfo[0]) - 1),
                                                             Long.parseLong(edgeInfo[1]));

                node.addForwardNode(newEdge);
            }
        }

        if(log.isDebugEnabled()){
            //volcado lista de nodos
            StringBuffer sBuffer= new StringBuffer();

            sBuffer.append("Readed Graph:\n");
            for(DNode<Integer> node: graph){
                sBuffer.append(node.getNodeID()).append("# ");
                for(Edge<Integer> edge: node.getForwardsNodeEdges()){
                    sBuffer.append(edge.destination.getNodeID()).append(",")
                            .append(edge.distance).append("; ");
                }
                sBuffer.append("\n");
            }
            sBuffer.append("GRAPH END\n");
            log.debug(sBuffer.toString());

        }

        return graph;
    }


}
