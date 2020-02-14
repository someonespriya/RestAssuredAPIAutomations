import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	static Properties properties;//Properties is a class and it comes from java.util package
	public static void loadData() throws IOException {

		properties=new Properties();//class has a method , you can access the method of that class through object
		
		
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\prop\\Test1.properties");//creating a file object.file is a class & available in java.io package.File class has only parameterized constructor.
		//why we write sysytem.getproperty-System is a class & getproperty is a static method.here we are not making any object of system class because system class has a static method getProperty.Directly i cant read property file. I have to make object of file class
		
		FileReader obj=new FileReader(f);//FileReader is a class so we are making object of this fileReader .FileReader has so many methods & constructor
		properties.load(obj);
	
		
		File f1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\prop\\Test2.properties");
		FileReader obj1=new FileReader(f1);
		properties.load(obj1);
	}
	
	
	 public static String getobject(String Data) throws IOException{
		loadData();
		String data=properties.getProperty(Data);
		return data; 
	}
}
