import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class JSPParser {
	
	static String flag="";
	static Element element;
	static FileWriter fw;
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
		fw = new FileWriter("out.txt",true);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(new File("test.txt"));
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		 
	
		NodeList nList = document.getElementsByTagName("bean:write");
	
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		     element = (Element) node;
		     flag="";
		    
		    if(element.hasAttribute("bundle")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("filter")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("format")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("formatKey")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("ignore")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("locale")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    
		    if(element.hasAttribute("name")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("property")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		    if(element.hasAttribute("scope")) {
		    	flag += "1";
		    }
		    else {
		    	flag += "0";
		    }
		    
		 }
		 
		 handleBeanWrite(flag,element);
		 
		}
	}
	
	static void handleBeanWrite(String flag,Element element) throws IOException {
		String scope;
		String output = "";
	
		
		switch(flag) {
		
		//name
		//000000100
		case "000000100":
							System.out.println("<c:out value=\""+"${"+element.getAttribute("name")+"}\" />");
							output = "<c:out value=\""+"${"+element.getAttribute("name")+"}\" />";
							fw.write(output);
							break;
		
		//name property
		//000000110
		case "000000110":
							System.out.println("<c:out value=\""+"${"+element.getAttribute("name")+"."+element.getAttribute("property")+"}\" />");
							output = "<c:out value=\""+"${"+element.getAttribute("name")+"."+element.getAttribute("property")+"}\" />";
							fw.write(output);
							break;					
		
		//name scope
		//000000101
		case "000000101":							
							scope = element.getAttribute("scope");
							if(scope.equals("request")) {
								System.out.println("<c:out value=\""+"${"+"requestScope."+element.getAttribute("name")+"}\" />");
							}
							output = "<c:out value=\""+"${"+"requestScope."+element.getAttribute("name")+"}\" />";
							fw.write(output);
							break;
		
		//name scope filter
		//010000101
		case "010000101":
							
							scope = element.getAttribute("scope");
							if(scope.equals("request")) {
								System.out.println("<c:out value=\""+"${"+"requestScope."+element.getAttribute("name")+"}\" escapeXml=\""+element.getAttribute("filter")+"\"/>");
							}
							output = "<c:out value=\""+"${"+"requestScope."+element.getAttribute("name")+"}\" escapeXml=\""+element.getAttribute("filter")+"\"/>";
							fw.write(output);
							break;
		default:
			System.out.println("flag value passed:"+flag);	
			break;
		
		}
	}
}

