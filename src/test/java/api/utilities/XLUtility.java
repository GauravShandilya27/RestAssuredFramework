package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	public class XLUtility {

		public FileInputStream fi;
		public FileOutputStream fo;
		
		public XSSFWorkbook workbook;
		public XSSFSheet sheet;
		public XSSFRow row;
		public XSSFCell cell;
		public CellStyle style;
		String path;
		

	    // Constructor to initialize the file path
	    public XLUtility(String path) {
	        this.path = path;
	    }

	    public int getRowCount(String sheetName) throws IOException {
			
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			int rowcount=sheet.getLastRowNum();
			workbook.close();
			return rowcount;
			}
		public int getCellCount(String sheetName,int rownum) throws IOException {
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount=row.getLastCellNum();
			workbook.close();
			return cellcount;

	}
	    // Method to get the cell data as a string from the specified sheet, row, and column
	    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
	        fi = new FileInputStream(path);  // Open the file
	        workbook = new XSSFWorkbook(fi);  // Create workbook instance
	        sheet = workbook.getSheet(sheetName);  // Get the specified sheet
	        row = sheet.getRow(rowNum);  // Get the specified row
	        cell = row.getCell(colNum);  // Get the specified cell
	        
	        // Use DataFormatter to handle different types of data (text, numeric, date, etc.)
	        DataFormatter formatter = new DataFormatter();
	        String data = formatter.formatCellValue(cell);  // Format the cell value
	        
	        workbook.close();  // Close the workbook
	        fi.close();  // Close the FileInputStream
	        
	        return data;  // Return the formatted data as a string
	    }
	

	
    // Method to set the cell data at a specified row and column
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
    	
    	File xlfile=new File(path);
    	
    	if(!xlfile.exists())       // If file not exists then create new file
    	{ 
    		workbook=new XSSFWorkbook();
    	
    	fo=new FileOutputStream(path);
    	workbook.write(fo);
    	}
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	
    	if (workbook. getSheetIndex(sheetName) ==-1) // If sheet not exists then create new Sheet
    	workbook. createSheet(sheetName) ;
    	sheet=workbook.getSheet(sheetName) ;
    	
    	if(sheet.getRow(rownum) ==null)// If row not exists then create new Row
    	sheet.createRow(rownum);
    	row=sheet.getRow(rownum);
    	
    	
    	cell=row.createCell(colnum);
    	cell.setCellValue(data);
    	fo=new FileOutputStream(path);
    	workbook.write(fo); workbook.close();
    	fi.close();
    	fo.close();
    }
    public void fillGreenColor (String sheetName, int rownum, int colnum) throws IOException
    {
    fi=new FileInputStream(path);
    workbook=new XSSFWorkbook(fi);
    sheet=workbook.getSheet(sheetName);
    row=sheet.getRow(rownum);
    cell=row.getCell(colnum);
    style=workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    workbook.write(fo); workbook.close();
    fi. close();
    fo.close();
    
    
    }
    public void fillRedColor (String sheetName, int rownum, int colnum) throws IOException
    {
    fi=new FileInputStream(path);
    workbook=new XSSFWorkbook(fi);
    sheet=workbook.getSheet(sheetName);
    row=sheet.getRow(rownum);
    cell=row.getCell(colnum);
    style=workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.RED.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    workbook.write(fo); workbook.close();
    fi. close();
    fo.close();
    
    
    }

    // Method to add a row to a specified sheet
    public void addRow(String sheetName, int rowNum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        
        // If the row already exists, overwrite it; otherwise, create a new row
        row = sheet.createRow(rowNum);
        
        // Write the changes to the file
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }
}
	
	
	
	
	
