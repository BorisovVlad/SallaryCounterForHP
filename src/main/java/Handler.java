import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Handler {
    protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    protected static Map<Date, Double> morningSallarying = new LinkedHashMap<>();
    protected static Map<Date, Double> daySallarying = new LinkedHashMap<>();
    protected static Map<Date, Double> nightSallarying = new LinkedHashMap<>();
    protected static Map<Date, Double> teaMasterSallarying = new LinkedHashMap<>();

    public static void sallaryCallculate( String fileName ) throws ParseException {
        List<List<String>> arrayForPolt = ExcelHandler.parse(fileName);
        Date startOfNextSallaryDay = startOfNextDay(arrayForPolt.get(0).get(0));
        Date dateOfDay;

        for (int i = 0; i < arrayForPolt.size(); i++) {
            String categoryOfHpProgram = arrayForPolt.get(i).get(1);
            dateOfDay = simpleDateFormat.parse(arrayForPolt.get(i).get(0));
            double sumOfPossition = Double.parseDouble(arrayForPolt.get(i).get(2));

            if (currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) {
                SallaryCalculate.calculateIntoDay(categoryOfHpProgram, sumOfPossition);
            }
            if ((!currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) || i == arrayForPolt.size() - 1) {
                startOfNextSallaryDay = startOfNextDay(arrayForPolt.get(i).get(0));
                SallaryCalculate.calculateAfterDay(categoryOfHpProgram, sumOfPossition, dateOfDay);

            }
        }
        printMorningMap(morningSallarying);
        printDayMap(daySallarying);
        printNightMap(nightSallarying);
        printTeaMasterMap(teaMasterSallarying);


    }


    private static void printMorningMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата утреннего мастера " + key + "     :     " + value + " рублей"));
    }

    private static void printDayMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата дневного мастера " + key + "     :     " + value + " рублей"));
    }

    private static void printNightMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата ночного мастера " + key + "     :     " + value + " рублей"));
    }

    private static void printTeaMasterMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата чайного мастера " + key + "     :     " + value + " рублей"));
    }
    private static Date startOfNextDay( String s ) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(simpleDateFormat.parse(s));//устанавливаю дату, с которой буду производить операции
        instance.add(Calendar.DAY_OF_MONTH, 1);// прибавляю 1 день к установленной дате
        instance.set(Calendar.HOUR, 11);// устанавливаю 11 часов в следущем дне
        instance.set(Calendar.MINUTE, 0);//ну и 00 минут
        Date startOfNextDay = instance.getTime(); // получаю измененную дату
        return startOfNextDay;
    }

    private static boolean currentDayOfSallary( Date dateOfDay, Date startOfNextDay ) throws ParseException {
        boolean correct = dateOfDay.getTime() < startOfNextDay.getTime();
        if (correct) {
            return true;
        } else {
            return false;
        }

    }
}
