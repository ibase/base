package base.test.other;

import base.test.constants.ConstantsHelper;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by base on 2016/4/6.
 */
public class Excel2007 {

    private static final String _writeFileName = ConstantsHelper._constant_RES_PATH+"\\docs\\excel2007_write";
    private static final String _fileExtName = ".xlsx";

    @Test
    public void testExcel2007_write() throws Exception{
        //创建excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建sheet工作表
        XSSFSheet sheet = workbook.createSheet("users");
        sheet.setDefaultColumnWidth(250*256);
        sheet.setDefaultRowHeight((short)(1*256));//一个字符的高度
        //设置字体
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)14);
        //设置单元格样式
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        //创建表头行中单元格
        int rowCount=21,columnCount = 10; //行数,列数
        //创建工作表的行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;
        for (int i = 0; i < columnCount; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue("title-" + i);
        }
        //表数据
        for (int i = 1; i < rowCount; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j);
                //cell.setCellStyle(style);//可设置与表头不同的字体
                cell.setCellValue("data-"+i+"-"+j);
            }
        }
        //数据写入输出文件流
        FileOutputStream fos = new FileOutputStream(_writeFileName+_fileExtName);
        workbook.write(fos);
        fos.close();
    }

    @Test
    public void testExcel2007_read() throws Exception{

        //读取excel文件
        FileInputStream is = new FileInputStream(_writeFileName+_fileExtName);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        //获取第一个工作表
        XSSFSheet sheet1 = wb.getSheetAt(0);

        int columnCount;
        XSSFRow row;
        //读取标题行
        if (sheet1.getRow(0) != null) {
            row = sheet1.getRow(0);
            columnCount = row.getLastCellNum();
            for (int i = 0; i < columnCount; i++) {
                System.out.print(row.getCell(i) + "\t\t");
            }
            System.out.println();
        }
        //读取数据
        for (int i = 1; sheet1.getRow(i) != null; i++) {
            row = sheet1.getRow(i);
            columnCount = row.getLastCellNum();
            for (int j = 0; j<columnCount; j++) {
                System.out.print(row.getCell(j) + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testExcel2007_read_2_write() throws Exception{

        //读取excel文件
        FileInputStream is = new FileInputStream(_writeFileName+_fileExtName);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        //获取第一个工作表
        XSSFSheet sheet1 = wb.getSheetAt(0);

        //设置字体
        XSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        //设置单元格样式
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        int oldRowCount = sheet1.getLastRowNum();
        int newRowCount = 10,newColumnCount=10;
        XSSFRow row;
        XSSFCell cell;
        for (int i = 0; i < newRowCount; i++) {
            row = sheet1.createRow(i+oldRowCount);
            for (int j = 0; j < newColumnCount; j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellValue("append-" + i+"-"+j);
            }
        }

        //数据写入输出文件流
        FileOutputStream fos = new FileOutputStream(_writeFileName+"_"+_fileExtName);
        wb.write(fos);
        fos.close();
    }
}
