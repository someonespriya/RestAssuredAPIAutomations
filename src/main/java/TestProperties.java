import java.io.IOException;

import org.testng.annotations.Test;

public class TestProperties extends TestBase{
	
@Test
void testproperties() throws IOException{
	
	String name=getobject("Name");
	System.out.println(name);
	
	String place=getobject("Place");
	System.out.println(place);
	
	String pin=getobject("Pin");
	System.out.println(pin);
}

}
