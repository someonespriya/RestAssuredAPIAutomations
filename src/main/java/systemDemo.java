
public class systemDemo {
public  static void main(String[] args){
	System.out.print("Previous : ");
    System.out.println(System.getProperty("java.runtime.version" ));
    System.setProperty("java.runtime.version", "priya");
   
    // prints Java Runtime Version after property set
    System.out.print("New : ");
    System.out.println(System.getProperty("java.runtime.version" ));
}
}
