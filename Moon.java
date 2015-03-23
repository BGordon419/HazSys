public class Moon extends Body {

   private String bodyType;
   private String zone;

   private Geosphere geo;
   private Biosphere bio;

   public Moon(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(Geosphere g, Atmosphere a, Biosphere b) {
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
