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
      sectors = new ArrayList<Sector>();
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
      // Create Sector Object + return
      String id = getId(secEl);
      String name = getName(secEl);

      int[] coords = getCoordsInt(secEl);

      return new Sector(id,name,coords,systems);
   }

   private StarSystem getStarSystem(Element sysEl) {

      ArrayList<Wormhole> wh = new ArrayList<Wormhole>();
      ArrayList<Body> body = new ArrayList<Body>();

      NodeList whnl = sysEl.getElementsByTagName("wormhole");
      if(whnl != null && whnl.getLength() > 0) {
         for(int i = 0 ; i < whnl.getLength();i++) {

            //get the element
            Element el = (Element)whnl.item(i);

            //add wormhole and bodies objects to arrayList
            wh.add(getWormhole(el));
         }
      }
      NodeList stars = sysEl.getElementsByTagName("star");
      if(stars != null && stars.getLength() > 0) {
         for(int i = 0 ; i < stars.getLength();i++) {

            //get the element
            Element el = (Element)stars.item(i);

            body.add(getStar(el));
         }
      }
      NodeList planets = sysEl.getElementsByTagName("planet");
      if(planets != null && planets.getLength() > 0) {
         for(int i = 0 ; i < planets.getLength();i++) {

            //get the element
            Element el = (Element)planets.item(i);

            body.add(getPlanet(el));
         }
      }
      // Create StarSystem Object + return
      String id = getId(sysEl);
      String name = getName(sysEl);

      double[] coords = getCoords(sysEl);

      char eod = (sysEl.getAttribute("eod").charAt(0));

      return new StarSystem(id,name,coords,eod,wh,body);
   }

   private Wormhole getWormhole(Element whEl) {

      double[] dest = new double[3];
      dest[0] = Double.parseDouble(whEl.getAttribute("destX"));
      dest[1] = Double.parseDouble(whEl.getAttribute("destY"));
      dest[2] = Double.parseDouble(whEl.getAttribute("destZ"));

      String destId = whEl.getAttribute("destSystemId");

      boolean explored;
      if(whEl.getAttribute("explored")=="Yes")
         explored = true;
      else
         explored = false;

      boolean polarity;
      if(whEl.getAttribute("polarity")=="positive")
         polarity = true;
      else
         polarity = false;

      return new Wormhole(dest,destId,explored,polarity);

   }

   private Body getStar(Element starEl) {
      ArrayList<Resource> photo = new ArrayList<Resource>();

      NodeList ph = starEl.getElementsByTagName("resource");
      if(ph != null && ph.getLength() > 0) {
         for(int i = 0 ; i < ph.getLength();i++) {
            //get the element
            Element el = (Element)ph.item(i);
            //Add photosphere elements to arraylist
            photo.add(getResource(el));
         }
      }

      String id = getId(starEl);
      String name = getName(starEl);
      String orbit = getOrbit(starEl);

      String specClass = starEl.getAttribute("spectralClass");

      String size = starEl.getAttribute("size");

      String hab = starEl.getAttribute("hab");

      String diameter = starEl.getAttribute("diameter");

      Star star = new Star(id,name,orbit,specClass,size,hab,diameter);
      star.fill(photo);
      return star;
   }

   private Body getPlanet(Element plEl) {

      String id = getId(plEl);
      String name = getName(plEl);
      String orbit = getOrbit(plEl);
      String zone = plEl.getAttribute("zone");

      // Gas Giant
      if(plEl.getAttribute("bodyType")=="Gas Giant") {
         ArrayList<Resource> atmo = new ArrayList<Resource>();

         NodeList ge = plEl.getElementsByTagName("atmosphere");
         if(ge != null && ge.getLength() > 0) {

            Element el = (Element)ge.item(0);

            NodeList rec = el.getElementsByTagName("resource");
            if(rec != null && rec.getLength() > 0) {
               for(int i = 0 ; i < rec.getLength();i++) {
                  //get the element
                  Element atmoEl = (Element)rec.item(i);
                  //Add photosphere elements to arraylist
                  atmo.add(getResource(atmoEl));
               }
            }
         }

         GasGiant gg = new GasGiant(id,name,orbit,zone);
         gg.fill(atmo);
         return gg;
      }
      // Small Moon
      else if(plEl.getAttribute("bodyType")=="Moon") {
         ArrayList<Resource> geo = new ArrayList<Resource>();
         ArrayList<Resource> bio = new ArrayList<Resource>();

         NodeList sphere = plEl.getElementsByTagName("*");
         if(sphere != null && sphere.getLength() > 0) {
            for(int i=0;i<sphere.getLength();i++) {
               Element el = (Element)sphere.item(i);

               NodeList rec = el.getElementsByTagName("resource");
               if(rec != null && rec.getLength() > 0) {
                  for(int x = 0 ; x < rec.getLength();x++) {
                     //get the element
                     Element atmoEl = (Element)rec.item(x);
                     //Add photosphere elements to arraylist
                     if(el.getTagName()=="geosphere") {
                        geo.add(getResource(atmoEl));
                     }
                     else if(el.getTagName()=="biosphere"){
                        bio.add(getResource(atmoEl));
                     }
                  }
               }
            }
         }

         Moon moon = new Moon(id,name,orbit, zone);
         moon.fill(geo,bio);
         return moon;
      }
      // Ringworld Arc / Planet / Large Moon
      else { //if(plEl.getAttribute("bodyType").contains("Ringworld Arc")==true)
         ArrayList<Resource> geo = new ArrayList<Resource>();
         ArrayList<Resource> hydro = new ArrayList<Resource>();
         ArrayList<Resource> atmo = new ArrayList<Resource>();
         ArrayList<Resource> bio = new ArrayList<Resource>();

         NodeList sphere = plEl.getElementsByTagName("*");
         if(sphere != null && sphere.getLength() > 0) {
            for(int i=0;i<sphere.getLength();i++) {
               Element el = (Element)sphere.item(i);

               NodeList rec = el.getElementsByTagName("resource");
               if(rec != null && rec.getLength() > 0) {
                  for(int x = 0 ; x < rec.getLength();x++) {
                     //get the element
                     Element atmoEl = (Element)rec.item(x);
                     //Add photosphere elements to arraylist
                     if (el.getTagName()=="geosphere") {
                        geo.add(getResource(atmoEl));
                     }
                     else if(el.getTagName()=="hydrosphere"){
                        bio.add(getResource(atmoEl));
                     }
                     else if(el.getTagName()=="atmosphere"){
                        bio.add(getResource(atmoEl));
                     }
                     else if(el.getTagName()=="biosphere"){
                        bio.add(getResource(atmoEl));
                     }
                  }
               }
            }
         }
         if (plEl.getTagName().contains("Arc")==true) {
            Arc arc = new Arc(id,name,orbit,zone);
            arc.fill(geo,hydro,atmo,bio);
            return arc;
         }
         else {
            Planet planet = new Planet(id,name,orbit,zone);
            planet.fill(geo,hydro,atmo,bio);
            return planet;
         }
      }
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
      else if (recEl.hasAttribute("qualityZone1")){
         q = new int[1];
         a = new int[1];

         q[0] = Integer.parseInt(recEl.getAttribute("qualityZone1"));
         a[0] = Integer.parseInt(recEl.getAttribute("abundanceZone1"));
      }
      else {
         q = new int[1];
         a = new int[1];

         q[0] = Integer.parseInt(recEl.getAttribute("quality"));
         a[0] = Integer.parseInt(recEl.getAttribute("abundance"));
      }

      return new Resource(name,q,a);
   }


   private double[] getCoords(Element coordEl) {

      double[] coords = new double[3];
      coords[0] = Double.parseDouble(coordEl.getAttribute("x"));
      coords[1] = Double.parseDouble(coordEl.getAttribute("y"));
      coords[2] = Double.parseDouble(coordEl.getAttribute("z"));

      return coords;
   }

   private int[] getCoordsInt(Element coordEl) {

      int[] coords = new int[3];
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
   private String getOrbit(Element orbEl) {
      return orbEl.getAttribute("orbit");
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
