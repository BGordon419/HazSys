import java.util.ArrayList;

public class StarSystem {

   private String systemId;
   private String name;
   private double[] coords = new double[3];
   private char eod;

   private ArrayList<Wormhole> wormholes;
   private ArrayList<Body> bodies;

   public StarSystem(String id, String nameIn, double[] coordsIn, char exp, ArrayList<Wormhole> whIn, ArrayList<Body> bodyIn) {
      systemId = id;
      name = nameIn;
      coords = coordsIn;
      eod = exp;
   }
   public StarSystem(String id, String nameIn, double x, double y, double z, char exp, ArrayList<Wormhole> whIn, ArrayList<Body> bodyIn) {
      systemId = id;
      name = nameIn;
      coords[0] = x;
      coords[1] = y;
      coords[2] = z;
      eod = exp;
      wormholes = whIn;
      bodies = bodyIn;
   }

   public ArrayList<Body> getBodies() {
      return bodies;
   }
   public ArrayList<Wormhole> getWormholes() {
      return wormholes;
   }

   public StarSystem copy() {
      StarSystem newSystem =  new StarSystem(systemId, name, coords, eod, wormholes, bodies);
      return newSystem;
   }
   public String toString() {
      if (wormholes==null||bodies==null)
         return "Error: System Empty.\nID: "+systemId+", Name: "+name+
                  ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2]+
                  ", Explored: "+ eod;
      else
         return "ID: "+systemId+", Name: "+name+
                  ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2]+
                  ", Number of Bodies: "+(bodies.size())+
                  ", Number of Wormholes: "+wormholes.size()+", Explored: "+ eod;
   }
}
