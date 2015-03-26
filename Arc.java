import java.util.ArrayList;

public class Arc extends Body {

   private String zone;

   private ArrayList<Resource> geo;
   private ArrayList<Resource> atmo;
   private ArrayList<Resource> hydro;
   private ArrayList<Resource> bio;

   public Arc(String idIn, String nameIn, String orbitIn, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> g, ArrayList<Resource> a,
                    ArrayList<Resource> h, ArrayList<Resource> b) {
      geo = g;
      atmo = a;
      hydro = h;
      bio = b;
   }

   public ArrayList<Resource> getGeosphere() {
      return geo;
   }
   public ArrayList<Resource> getAtmosphere() {
      return atmo;
   }
   public ArrayList<Resource> getHydrosphere() {
      return hydro;
   }
   public ArrayList<Resource> getBiosphere() {
      return bio;
   }

   public Arc copy() {
      return new Arc(super.getId(), super.getName(), super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: Ringworld Arc, Zone: "+zone;
   }
}
