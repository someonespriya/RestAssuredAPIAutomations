package APIAutomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBDrivenProperties {
	public static String getProperty (String propName) {

		String propValue = "";

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("dbdriven.properties");
			prop.load(input);
			propValue = prop.getProperty(propName);

		}
		catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return propValue;
	}

}