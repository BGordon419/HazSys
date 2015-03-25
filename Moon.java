import java.util.ArrayList;

public class Moon extends Body {

   private String bodyType;
   private String zone;

   private ArrayList<Resource> geo;
   private ArrayList<Resource> bio;

   public Moon(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> g, ArrayList<Resource> a, ArrayList<Resource> b) {
      geo = g;
      bio = b;
   }

   public Moon copy() {
      return new Moon(super.getId(), super.getName(), bodyType, super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: "+bodyType+", Zone: "+zone;
   }
}
