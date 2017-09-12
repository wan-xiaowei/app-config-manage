package com.wxw.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReaderExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReaderExcelUtil.class);
	
	
	private InputStream is;
	private POIFSFileSystem fs;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	
	

	public ReaderExcelUtil(InputStream is,String filename, String password){
		this.is = is;
		try {
			if(isExcel2003(filename)){
				if (StringUtils.isNotBlank(password)) {
					org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword(password);
					this.fs = new POIFSFileSystem(getIs());
					this.workbook = WorkbookFactory.create(fs);
				} else {
					this.fs = new POIFSFileSystem(getIs());
					this.workbook = new HSSFWorkbook(fs);
				}
			}
			if(isExcel2007(filename)){
				if (StringUtils.isNotBlank(password)) {
					POIFSFileSystem pfs = new POIFSFileSystem(is);
					EncryptionInfo encInfo = new EncryptionInfo(pfs);
					Decryptor decryptor = Decryptor.getInstance(encInfo);
					decryptor.verifyPassword(password);
					this.workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
				} else {
 					this.workbook = new XSSFWorkbook(is);
				} 
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
		}
		
		
	}
	/**
	 * 通过索引获取字段序号
	 * @param filed
	 * @return
	 */
	public static String getFiledIndex(String filed){
		if (StringUtils.isBlank(filed)) return "";
		String[] splits = filed.split("#");
		if (splits == null || splits.length < 2) return "";
		return splits[0];
	}
	
	/**
	 * 获取多个字段序号
	 * @param filed
	 * @return
	 */
	public static List<String> getGroupFiledIndex(String filed){
		
		if (StringUtils.isBlank(filed)) return null;
		String[] splits = filed.trim().split(",");
		if(splits.length<1) return null;
		
		List<String> list = new ArrayList<String>();
		
		for(String str : splits){
			if(!StringUtils.isBlank(str)){
				String[] index = str.trim().split("#");
				if(index!=null && index.length != 0 && !StringUtils.isBlank(index[0])){
						list.add(index[0]);	
						
				}
				
			}	
		}
		
		
		
		return list;
	}
	
	/**
	 * 获得多字段内容 
	 * @param list
	 * @param row
	 * @return
	 */
	public String getGroupValue(List<String> list,int row){
		StringBuilder build = new StringBuilder();
		if(!list.isEmpty()){
			for(String str : list){
				String value = this.getValue(row, Integer.parseInt(str));
				if(!StringUtils.isBlank(value)){
					build.append(value);
				}
			}
		}
		
		return build.toString();
	}
	
	
	/**
	 * 格式日期
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public Date formateDate(String str){
		
		if(StringUtils.isBlank(str))
			return null;	
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");	
		
		try {
			return formate.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 获取行数
	 * @return
	 */
	public Integer getRowNum(){
	
        sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        
        return rowNum;
		
	}
	
	/**
	 * 获取指定行的列数
	 * @return
	 */
	public Integer getCellNum(int rowNum){
        sheet = workbook.getSheetAt(0);
        return sheet.getRow(rowNum).getPhysicalNumberOfCells();
	}
	
	 /**
     * 读取Excel表格的内容
     * @return String 表头内容的数组
     */
    public  String getValue(Integer rowIndex,Integer celIndex) {
        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(rowIndex);
        
        return this.cleanXSS(getCellFormatValue(row == null ? null : row.getCell(celIndex)));
    }
	
	 /**
     * 读取Excel表格表头的内容
     * @return String 表头内容的数组
     */
    public  List<String> readExcelTitle() {
    	
    	List<String> list = new ArrayList<String>();
       
        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            String value =  getCellFormatValue(row.getCell(i));
            if(!StringUtils.isBlank(value)){
            	list.add(value);
            }
        }
        return list;
    }
    

	 /**
    * 读取Excel表格表头的内容
    * @return String 表头内容的数组
    */
   public  Map<String,Object> readExcelTitleAndIndex() {
		 
	   Map<String,Object> map = new LinkedHashMap<String,Object>();
      
       sheet = workbook.getSheetAt(0);
       row = sheet.getRow(0);
       // 标题总列数
       int colNum = row.getPhysicalNumberOfCells();
       for (int i = 0; i < colNum; i++) {
           //title[i] = getStringCellValue(row.getCell((short) i));
           String value =  getCellFormatValue(row.getCell(i));
           if(!StringUtils.isBlank(value)){
        	   map.put(String.valueOf(i),value);
           }
       }
       return map;
   }
	
	
	
	/**
	 * 读取Excel表格
	 * @param is
	 * @return
	 */
	public List<Object> readerExcel(InputStream is){
		
		
		try {
		
			
			int sheetNum = workbook.getNumberOfSheets();//工作表数
			//遍历工作表
			for(int n=0;n<sheetNum;n++){
				sheet = workbook.getSheetAt(n);
				if(sheet == null){
					continue;
				}
				//遍历行
				for(int i=1;i<sheet.getLastRowNum();i++){
					row = sheet.getRow(i);
					
					if(row == null){
						continue;
					}
					//遍历列
					for(int j=0;j<row.getLastCellNum();j++){
					
						System.out.print(getCellFormatValue(row.getCell(j)));
					}
					System.out.println();
					
				}
				
			}
		
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		
		
		return null;
	}
	
	
	/**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private  String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        SimpleDateFormat sdf ;
        Date date ;
        DecimalFormat decimalFormat ;
        if (cell != null) {
        	
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            	short format = cell.getCellStyle().getDataFormat();  
				if (format == 14 || format == 31 || format == 57 || format == 58) {
					// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					date = HSSFDateUtil.getJavaDate(value);
					cellvalue = sdf.format(date);
				} else if (format == 20 || format == 32) {
					// 时间

				} else {
					// 取得当前Cell的数值
					decimalFormat = new DecimalFormat("0.###############");
					cellvalue = decimalFormat.format(cell.getNumericCellValue());
				}
            	  
            break;
            case HSSFCell.CELL_TYPE_FORMULA:
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                   
                  
                    
                	sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = cell.getDateCellValue();
                   
                    
                    cellvalue = sdf.format(date);
                  
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                	 decimalFormat = new DecimalFormat("0.###############");
                	cellvalue =	decimalFormat.format(cell.getNumericCellValue());
                 
                }
                break;
           
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                 String  value = cell.getRichStringCellValue().getString();
                 if(value.indexOf('%')>-1){
                	String num =  value.substring(0, value.indexOf('%')+1);
                	if(isNumber(num)){
                		BigDecimal number = new BigDecimal(num);
                		BigDecimal divideNumber = number.divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_UP);
                		cellvalue = divideNumber.toString();
                	}
                 }else{
                	 cellvalue = value;
                 }
                
                
            	
                break;
                
            case HSSFCell.CELL_TYPE_BOOLEAN:
            	boolean flag = cell.getBooleanCellValue();
            	cellvalue = flag==true?"是":"否";
            	
            	break;
            	
            case HSSFCell.CELL_TYPE_ERROR:
            	byte errorValue = cell.getErrorCellValue();
            	cellvalue = Byte.toString(errorValue);
            	
            	break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        if (null != cellvalue) cellvalue = cellvalue.trim();
        return cellvalue;

    }
    
    /**
     * 判断03版本
     * @param filePath
     * @return
     */
    public  boolean isExcel2003(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xls)$");  
  
    }  
    /**
     * 判断07版本
     * @param filePath
     * @return
     */
    public  boolean isExcel2007(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
  
    }  
    
	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}


	public Cell getCell() {
		return cell;
	}



	public Workbook getWorkbook() {
		return workbook;
	}

	
	private String cleanXSS(String value) {
		/*value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");*/
		if (null == value) return null;
		return StringEscapeUtils.escapeHtml4(value);
	}

	/**
	 * 判断是否有数字
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number){
		int index = number.indexOf('.');
		if(index<0){
			return StringUtils.isNumeric(number);
		}else{
			String num1 = number.substring(0,index);
			String num2 = number.substring(index+1);
			return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
		}
	}
	
	public static void main(String[] args) {
		
		List<String> list = getGroupFiledIndex("3#,");
		for(String str : list){
			System.out.println("adf:"+str);
		}
		
	}
	
}
