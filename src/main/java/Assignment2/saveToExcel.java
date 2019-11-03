package Assignment2;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

public class saveToExcel {
    public static void main() {
    }

    public void saveData() {
        try {

            scrapeGitHub scrapeGitHub = new scrapeGitHub();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("GitHub");

            //Create Heading
            Row rowHeading = sheet.createRow(0);
            rowHeading.createCell(0).setCellValue("Follower Name");
            rowHeading.createCell(1).setCellValue("Total Repo");
            rowHeading.createCell(2).setCellValue("Total Follower");
            rowHeading.createCell(3).setCellValue("Total Following");
            rowHeading.createCell(4).setCellValue("GitHub Link");

            //1st Row Font Size
            for (int i = 0; i <= 4; i++) {
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 12);
                style.setFont(font);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                rowHeading.getCell(i).setCellStyle(style);
            }


            int r = 1;
            for (data data : scrapeGitHub.findAll()) {
                //Create row
                Row row = sheet.createRow(r);
                //Column 1: follower
                Cell cellId = row.createCell(0);
                cellId.setCellValue(data.getColumn1());
                //Column 2 : totalRepo
                Cell cellColumn1 = row.createCell(1);
                cellColumn1.setCellValue(data.getColumn2());
                //Column 3 : total
                Cell cellColumn2 = row.createCell(2);
                cellColumn2.setCellValue(data.getColumn3());
                //Column 4 : trying
                Cell cellColumn3 = row.createCell(3);
                cellColumn3.setCellValue(data.getColumn4());
                //Column 5 : URL
                Cell cellColumn4 = row.createCell(4);
                cellColumn4.setCellValue(data.getColumn5());
                r++;
            }
            //Auto Adjust Width
            for (int i = 0; i < 6; i++)
                sheet.autoSizeColumn(i);

            //Save to Excel FILE
            FileOutputStream out = new FileOutputStream(new File("D:\\Assignment2.xls"));
            workbook.write(out);
            out.close();
            workbook.close();

        } catch (
                Exception e) {
            System.out.print(e.getStackTrace());
        }
    }
}
