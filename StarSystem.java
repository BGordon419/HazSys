public class StarSystem {

   private String systemId;
   private String name;
   private double[] coords = new double[3];
   private boolean explored;

   private Wormhole[] wormholes;
   private Star[] stars;
   private Planet[] planets;

   public StarSystem(String id, String nameIn, double[] coordsIn, boolean exp) {
      systemId = id;
      name = nameIn;
      coords = coordsIn;
      explored = exp;
   }
   public StarSystem(String id, String nameIn, double x, double y, double z, boolean exp) {
      systemId = id;
      name = nameIn;
      coords[0] = x;
      coords[1] = y;
      coords[2] = z;
      explored = exp;
   }

   public void fillSystem(Wormhole[] whIn, Star[] starsIn, Planet[] planetsIn) {
      wormholes = whIn;
      stars = starsIn;
      planets = planetsIn;
   }
   public Systems copy() {
      Systems newSystem =  new Systems(systemId, name, coords, explored);
      newSystem.fillSystem(wormholes, stars, planets);
      return newSystem;
   }
   public String toString() {
      if (stars==null||wormholes==null||planets==null)
         return "Error: System Empty.\nID: "+systemId+", Name: "+name+
                  ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2]+
                  ", Explored: "+ explored;
      else
         return "ID: "+systemId+", Name: "+name+
                  ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2]+
                  ", Number of Bodies: "+(stars.length+planets.length)+
                  ", Number of Wormholes: "+wormholes.length+", Explored: "+ explored;
   }
}
