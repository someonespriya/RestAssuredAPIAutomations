  package DataDriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlFile,String xlSheet) throws IOException
	{
		fis=new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlSheet);
		int rowCount=ws.getLastRowNum();
		System.out.println(rowCount);
		wb.close();
		fis.close(); 
		return rowCount;
		
	}
	public static int getCellCount(String xlFile,String xlSheet,int rownum) throws IOException
	{
		fis=new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rownum);
		int cellCount=row.getLastCellNum();
		System.out.println(cellCount);
		wb.close();
		fis.close(); 
		return cellCount;
		
	}
	
	public static String getCellData(String xlFile,String xlSheet,int rownum ,int colnum) throws IOException
	{
		fis=new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String cellData=formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data="";
		}
		wb.close();
		fis.close();
		return data;	
	}
	
	public static void  setCellData(String xlFile,String xlSheet,int rownum,int colnum,String data) throws IOException
	{
		fis=new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlSheet);
		cell=row.getCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fis.close(); 
		fo.close();

	}
}
