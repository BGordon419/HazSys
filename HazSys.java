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

public class HazSys {
   List sectors;
   Document dom;

   public HazSys() {
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
            Sector e = getSectorList(el);

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
	private Sector getSectorList(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name

		String sectorId = empEl.getAttribute("sectorId");
		String name = empEl.getAttribute("name");
		int[] coords = new int[3];
		coords[0] = Integer.parseInt(empEl.getAttribute("x"));
		coords[1] = Integer.parseInt(empEl.getAttribute("y"));
		coords[2] = Integer.parseInt(empEl.getAttribute("z"));
		StarSystem[] systems = empEl.getAttribute("type");


		//Create a new Employee with the value read from the xml nodes
		Sector e = new Sector(sectorId, name, coords);

		return e;
	}
}
