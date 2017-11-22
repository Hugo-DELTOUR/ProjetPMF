package CAD;

public class main {

	public static void main(String[] args) {
		String chaine = new String("55.2 44.2 52.8");
          //System.out.print(chaine);
          
        String[] elements = chaine.split("\\s"); //split avec espace
        // contient ["abc.abc", "tr"]
        System.out.println(elements[0]) ;
        System.out.println(elements[1]) ; 
        System.out.println(elements[2]) ; 

	}

}
