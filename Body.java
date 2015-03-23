public class Body {

   private String name;
   private String id;
   private String orbit;

   public Body(String idIn, String nameIn, String orbitIn) {
      name = nameIn;
      id = idIn;
      orbit = orbitIn;
   }

   public String getName() {
      return name;
   }
   public String getId() {
      return id;
   }
   public String getOrbit() {
      return orbit;
   }

   public String toString() {
      return "ID: "+id+", Name: "+name+", Orbit: "+orbit;
   }
}
