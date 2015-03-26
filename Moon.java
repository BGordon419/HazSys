import java.util.ArrayList;

public class Moon extends Body {

   private String zone;

   private ArrayList<Resource> geo;
   private ArrayList<Resource> bio;

   public Moon(String idIn, String nameIn, String orbitIn, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> g, ArrayList<Resource> b) {
      geo = g;
      bio = b;
   }

   public ArrayList<Resource> getGeosphere() {
      return geo;
   }
   public ArrayList<Resource> getBiosphere() {
      return bio;
   }

   public Moon copy() {
      return new Moon(super.getId(), super.getName(), super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: Moon, Zone: "+zone;
   }
}
