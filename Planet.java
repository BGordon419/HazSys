import java.util.ArrayList;

public class Planet extends Body {

   private String bodyType;
   private String zone;

   private ArrayList<Resource> geo;
   private ArrayList<Resource> atmo;
   private ArrayList<Resource> hydro;
   private ArrayList<Resource> bio;

   public Planet(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> g, ArrayList<Resource> a,
                    ArrayList<Resource> h, ArrayList<Resource> b) {
      geo = g;
      atmo = a;
      hydro = h;
      bio = b;
   }

   public Planet copy() {
      return new Planet(super.getId(), super.getName(), bodyType, super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: "+bodyType+", Zone: "+zone;
   }
}
