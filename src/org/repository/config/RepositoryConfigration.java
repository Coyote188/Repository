package org.repository.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private static List<SessionConfiguration> configurations;
	
	public RepositoryConfigration(){
		
	}
	
	static {
		RepositoryConfigration.setConfigurations(new ArrayList<SessionConfiguration>());
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		List<Map<String, String>> config = new ArrayList<Map<String, String>>();
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			String path = System.getProperty("user.dir");
			InputStream inputStream = new FileInputStream(new File(path + "\\etc\\repository.cfg.xml"));
			Document document = builder.parse(inputStream);
			Element rootElement = document.getDocumentElement();

			NodeList nodeList = rootElement
					.getElementsByTagName("session-factory");
			if (null != nodeList) {
				for (int index = 0; index < nodeList.getLength(); index++) {
					Map<String, String> configOR = new HashMap<String, String>();
					Node node = nodeList.item(index);
					NamedNodeMap map = node.getAttributes();
					Node sessionName = map.getNamedItem("name");
					configOR.put("sessionfactory", sessionName.getNodeValue());
					Node sessionNature = map.getNamedItem("nature");
					if (null != sessionNature) {
						configOR.put("nature", sessionName.getNodeValue());
					}
					NodeList properties = node.getChildNodes();
					for (int idx = 0; idx < properties.getLength(); idx++) {
						Node property = properties.item(idx);
						if (property.getNodeName().equals("property")) {
							NamedNodeMap propertyAttr = property
									.getAttributes();
							Node name = propertyAttr.getNamedItem("name");
							configOR.put(name.getNodeValue(),
									property.getTextContent());
						}
					}
					config.add(configOR);
				}
			}
			/**
			 * Close FileOptions
			 */
			inputStream.close();
		} catch (ParserConfigurationException e) {
			log.error(
					"Repository initialization file load and analytical failure"
							+ e.getMessage(), e);
		} catch (FileNotFoundException e) {
			log.error(
					"Repository initialization file load and analytical failure"
							+ e.getMessage(), e);
		} catch (SAXException e) {
			log.error(
					"Repository initialization file load and analytical failure"
							+ e.getMessage(), e);
		} catch (IOException e) {
			log.error(
					"Repository initialization file load and analytical failure"
							+ e.getMessage(), e);
		}
		for (Map<String, String> map : config) {
			SessionConfiguration scf = new SessionConfiguration();
			try {
				scf.setDhcp(Boolean.parseBoolean(map.get("repository.dbcp")));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}

			try {
				scf.setDriveClass(map.get("repository.driverClass"));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			try {
				scf.setInitActive(Integer.parseInt(map
						.get("repository.initActive")));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			try {
				scf.setMaxActive(Integer.parseInt(map
						.get("repository.maxActive")));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			try {
				scf.setPassword(map.get("repository.password"));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			try {
				scf.setUrl(map.get("repository.url"));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			try {
				scf.setUsername(map.get("repository.username"));
			} catch (NullPointerException e) {
				log.error("java.lang.NullPointerException " + e.getMessage(), e);
			}
			RepositoryConfigration.getConfigurations().add(scf);
		}

	}
	
	/**
	 * @return the configurations
	 */
	public static List<SessionConfiguration> getConfigurations() {
		return configurations;
	}
	/**
	 * @param configurations the configurations to set
	 */
	public static void setConfigurations(List<SessionConfiguration> configurations) {
		RepositoryConfigration.configurations = configurations;
	}

}
