import java.util.ArrayList;

public class Star extends Body {

   private String spectralClass;
   private String size;
   private String habitableZone;
   private String diameter;

   private ArrayList<Resource> photo;

   public Star(String idIn, String nameIn, String orbitIn
              ,String specClass, String sizeIn, String habZone, String dia) {

      super(idIn, nameIn, orbitIn);
      spectralClass = specClass;
      size = sizeIn;
      habitableZone = habZone;
      diameter = dia;
   }
   public void fill(ArrayList<Resource> ps) {
      photo = ps;
   }

   public ArrayList<Resource> getPhotosphere() {
      return photo;
   }

   public Star copy() {
      Star newStar = new Star(super.getId(), super.getName(), super.getOrbit(),
                              spectralClass, size, habitableZone, diameter);
      newStar.fill(photo);
      return newStar;
   }
   public String toString() {
      return super.toString()+", Spectral Class: "+spectralClass+", Size: "+size+", Habitable Zone: "+habitableZone+", Diameter: "+diameter;
   }
}
