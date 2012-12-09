package org.repository.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RepositoryConfigration {
	
	private static final Logger log = Logger.getLogger(RepositoryConfigration.class);
	
	public RepositoryConfigration(){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			String path = System.getProperty("user.dir");
			InputStream inputStream = new FileInputStream(new File(path + "\\etc\\repository.cfg.xml"));
			Document document = builder.parse(inputStream);
			Element rootElement = document.getDocumentElement();
			log.info("" + rootElement.getNodeName());
			
			NodeList nodeList = rootElement.getElementsByTagName("session-factory");
			if(null != nodeList){
				for (int index = 0; index < nodeList.getLength(); index++) {
					Node node = nodeList.item(index);
					NamedNodeMap map = node.getAttributes();
					Node sessionName = map.getNamedItem("name");
					log.info("session name =" + sessionName.getNodeValue());
					Node sessionNature = map.getNamedItem("nature");
					log.info("session nature = " + sessionNature.getNodeValue());
					NodeList properties = node.getChildNodes();
					for(int idx = 0; idx < properties.getLength(); idx ++){
						Node property = properties.item(idx);
						
						if(property.getNodeName().equals("property")){
//							SessionConfiguration configuration = new SessionConfiguration();
							NamedNodeMap propertyAttr = property.getAttributes();
							Node name = propertyAttr.getNamedItem("name");
							log.info(name.getNodeValue() +" = " + property.getTextContent());
						}
//						log.info(property.getNodeName() + " - " + property.getUserData() );
					}
				}
			}
			
			
			/**
			 * Close FileOptions
			 */
			inputStream.close();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
