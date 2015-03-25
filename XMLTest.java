import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest {

	//No generics
	List sectors;
	Document dom;


	public XMLTest(){
		//create a list to hold the employee objects
		sectors = new ArrayList();
	}

	public void runExample() {

		//parse the xml file and get the dom object
		parseXmlFile();

		//get each employee element and create a Employee object
		parseDocument();

		//Iterate through the list and print the data
		printData();

	}


	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse("stars.xml");


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}


	private void parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();

		//get a nodelist of <employee> elements
		NodeList nl = docEle.getElementsByTagName("sector");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Employee object
				Sector e = getSector(el);

				//add it to list
				sectors.add(e);
			}
		}
	}


	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 * @param empEl
	 * @return
	 */
	private void oldGetSector(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name

		String sectorId = empEl.getAttribute("sectorId");
		String name = empEl.getAttribute("name");
		int[] coords = new int[3];
		coords[0] = Integer.parseInt(empEl.getAttribute("x"));
		coords[1] = Integer.parseInt(empEl.getAttribute("y"));
		coords[2] = Integer.parseInt(empEl.getAttribute("z"));
		//StarSystem[] systems = empEl.getAttribute("type");


		//Create a new Employee with the value read from the xml nodes
		//Sector e = new Sector(sectorId, name, coords);

		//return e;
	}

	private Sector getSector(Element secEl) {

		ArrayList<StarSystem> systems = new ArrayList<StarSystem>();

		// Do element in a node have their own elements?
		NodeList nl = secEl.getElementsByTagName("system");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the element
				Element el = (Element)nl.item(i);

				//add star systems to arrayList
				systems.add(getStarSystem(el));
			}
		}

			return new Sector(getId(secEl),getName(secEl),getCoords(secEl),systems);
	}

	private StarSystem getStarSystem(Element sysEl) {

		ArrayList<Wormhole> wh = new ArrayList<Wormhole>();
		ArrayList<Body> body = new ArrayList<Body>();

		// Do element in a node have their own elements?
		NodeList nl = sysEl.getElementsByTagName("system");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the element
				Element el = (Element)nl.item(i);

				//add wormhole and bodies objects to arrayList
				wh.add(getWormhole(el));
				body.add(getBody(el));
			}
		}

		return new StarSystem(getId(sysEl),getName(sysEl),getCoords(sysEl),(sysEl.getAttribute("eod").charAt(0)),wh,body);
	}

	private Resource getResource(Element recEl) {
		int[] q, a;
		String name = recEl.getAttribute("name");

		if(recEl.hasAttribute("qualityZone3")==true) {
			q = new int[3];
			a = new int[3];

			q[0] = Integer.parseInt(recEl.getAttribute("qualityZone1"));
			q[1] = Integer.parseInt(recEl.getAttribute("qualityZone2"));
			q[2] = Integer.parseInt(recEl.getAttribute("qualityZone3"));

			a[0] = Integer.parseInt(recEl.getAttribute("abundanceZone1"));
			a[1] = Integer.parseInt(recEl.getAttribute("abundanceZone2"));
			a[2] = Integer.parseInt(recEl.getAttribute("abundanceZone3"));
		}
		else if (recEl.hasAttribute("qualityZone2")==true) {
			q = new int[2];
			a = new int[2];

			q[0] = Integer.parseInt(recEl.getAttribute("qualityZone1"));
			q[1] = Integer.parseInt(recEl.getAttribute("qualityZone2"));

			a[0] = Integer.parseInt(recEl.getAttribute("abundanceZone1"));
			a[1] = Integer.parseInt(recEl.getAttribute("abundanceZone2"));
		}
		else {
			q = new int[1];
			a = new int[1];

			q[0] = Integer.parseInt(recEl.getAttribute("qualityZone1"));
			a[0] = Integer.parseInt(recEl.getAttribute("abundanceZone1"));
		}

		return new Resource(name,q,a);
	}

	private Wormhole getWormhole(Element whEl) {

	}

	private Body getBody(Element bodyEl) {

	}

	private <T> T getCoords(Element coordEl) {

		//get coordinates
		T[] coords;
		coords[0] = Integer.parseInt(coordEl.getAttribute("x"));
		coords[1] = Integer.parseInt(coordEl.getAttribute("y"));
		coords[2] = Integer.parseInt(coordEl.getAttribute("z"));
		return coords;
	}
	private String getName(Element nameEl) {
		return nameEl.getAttribute("name");
	}
	private String getId(Element idEl) {
		return idEl.getAttribute(idEl.getTagName()+"Id");
	}
/**
	private Employee getEmployee(Element empEl) {

	//for each <employee> element get text or int values of
	//name ,id, age and name
	String name = getTextValue(empEl,"Name");
	int id = getIntValue(empEl,"Id");
	int age = getIntValue(empEl,"Age");

	String type = empEl.getAttribute("type");

	//Create a new Employee with the value read from the xml nodes
	Employee e = new Employee(name,id,age,type);

	return e;
}
**/

	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}

	/**
	 * Iterate through the list and print the
	 * content to console
	 */
	private void printData(){

		System.out.println("No of Sectors '" + sectors.size() + "'.");

		Iterator it = sectors.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}


	public static void main(String[] args){
		//create an instance
		XMLTest dpe = new XMLTest();

		//call run example
		dpe.runExample();
	}

}
