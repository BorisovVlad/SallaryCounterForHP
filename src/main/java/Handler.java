import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Handler {
    protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");


    public static void sallaryCallculate( String fileName ) throws ParseException {
        List<List<String>> arrayForPolt = ExcelHandler.parse(fileName);
        Date startOfNextSallaryDay = DateHandler.startOfNextDay(arrayForPolt.get(0).get(0));
        Date dateOfDay;

        for (int i = 0; i < arrayForPolt.size(); i++) {
            String categoryOfHpProgram = arrayForPolt.get(i).get(1);
            dateOfDay = simpleDateFormat.parse(arrayForPolt.get(i).get(0));
            double sumOfPossition = Double.parseDouble(arrayForPolt.get(i).get(2));

            if (currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) {
                SallaryCalculate.calculateIntoDay(categoryOfHpProgram, sumOfPossition);
            }
            if ((!currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) || i == arrayForPolt.size() - 1) {
                startOfNextSallaryDay = DateHandler.startOfNextDay(arrayForPolt.get(i).get(0));
                SallaryCalculate.calculateAfterDay(categoryOfHpProgram, sumOfPossition, dateOfDay);

            }
        }
        PrinterMap.printMorningMap(SallaryCalculate.morningSallaring);
        PrinterMap.printDayMap(SallaryCalculate.daySallaring);
        PrinterMap.printNightMap(SallaryCalculate.nigthSallaring);
        PrinterMap.printTeaMasterMap(SallaryCalculate.teaMasterSallaring);
    }

    static boolean currentDayOfSallary( Date dateOfDay, Date startOfNextDay ) throws ParseException {
        boolean correct = dateOfDay.getTime() < startOfNextDay.getTime();
        if (correct) {
            return true;
        } else {
            return false;
        }
    }
}
