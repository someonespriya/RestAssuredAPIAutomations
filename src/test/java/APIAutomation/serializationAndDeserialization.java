package APIAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


 class Test implements Serializable
 {
		int i=10,j=20;
 }
 
 
 
public class serializationAndDeserialization {

public static void main(String[]args) throws IOException, ClassNotFoundException{
	 Test t1=new Test();
	 
	//Serialization
	
	FileOutputStream fos=new FileOutputStream("text.txt");//write our file /object into our memory
	ObjectOutputStream oos=new ObjectOutputStream(fos);//convert object into file
	
	oos.writeObject(t1);//method will convert object into file format
	
	//Deserialization
	
	FileInputStream fis=new FileInputStream("text.txt");//Converting file into Object
	ObjectInputStream ois=new ObjectInputStream(fis);
	Test t2=(Test)ois.readObject();//Typecasting 
	System.out.println(t1.i + "   "+t2.j);
	
}
}
