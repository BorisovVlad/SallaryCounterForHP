import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CounterForKonushennaya {

    protected static void calculateIntoDay (String fileName) throws ParseException {
        double konushennayaSallary = 0;
        Map<Date, Double> konushennayaMap = new LinkedHashMap<>();
        List<List<String>> arrayForKon = ExcelHandler.parse(fileName);
        Date startOfNextSallaryDay = DateHandler.startOfNextDay(arrayForKon.get(0).get(0));
        Date dateOfDay;
        for (int i = 0; i < arrayForKon.size(); i++) {
            String categoryOfHpProgram = arrayForKon.get(i).get(1);
            dateOfDay = Handler.simpleDateFormat.parse(arrayForKon.get(i).get(0));
            double sumOfPossition = Double.parseDouble(arrayForKon.get(i).get(2));

            if (Handler.currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) {
                switch (categoryOfHpProgram){
                    case ("Кальяны"):
                    case ("Закуски"):
                    case ("Чай"):
                        konushennayaSallary += sumOfPossition * 0.2;
                        break;
                    case ("Маркетинг"):
                    case ("Стаф"):
                    case ("Учередосы"):
                    case ("Продажи"):
                        break;
                }
            }
            if ((!Handler.currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) || i == arrayForKon.size() - 1) {
                konushennayaMap.put(DateHandler.startOfCurrentDay(dateOfDay), konushennayaSallary);
                startOfNextSallaryDay = DateHandler.startOfNextDay(arrayForKon.get(i).get(0));
                if (categoryOfHpProgram.equals("Кальяны") || categoryOfHpProgram.equals("Закуски") || categoryOfHpProgram.equals("Чай")){
                    konushennayaSallary = sumOfPossition * 0.2;
                } else {
                    konushennayaSallary = 0;
                }
            }
        }

        PrinterMap.printKonMaster(konushennayaMap);

    }
}
