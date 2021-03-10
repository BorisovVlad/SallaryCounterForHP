import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelHandler {
    protected static List<List<String>> parse( String fileName ) throws ParseException {
        //инициализирую потоки
        List<List<String>> arrays = new ArrayList<>();
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираю первый лист входного файла на объектную модель
        assert workBook != null;
        XSSFSheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        //прохожу по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            List<String> temparr = new ArrayList<>();
            int i = -1;
            while (cells.hasNext()) {
                i++;
                Cell cell = cells.next();
                //перебираю возможные типы ячеек
                if (i == 1 || i == 3 || i == 7) {
                    temparr.add(String.valueOf(cell));
                }
            }
            arrays.add(temparr);
        }
        arrays.remove(0);
        return arrays;
    }
}
