import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelHandler {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static Map<Date, Double> morningSallarying = new LinkedHashMap<>();
    private static Map<Date, Double> daySallarying = new LinkedHashMap<>();
    private static Map<Date, Double> nightSallarying = new LinkedHashMap<>();
    private static Map<Date, Double> teaMasterSallarying = new LinkedHashMap<>();
    private static double morningSallary = 0;
    private static double daySallary = 0;
    private static double nightSallary = 0;
    private static double teaMasterSallary = 0;
    private static double sumOfTeaZone = 0;
    private static double sumOfDrinks = 0;
    private static double sumOfMorning = 0;
    private static double sumOfNight = 0;
    private static double sumOfDay = 0;
    private static double sumOfEvening = 0;
    private static double sumOfStaff = 0;
    private static double sumOfOwners = 0;
    private static double otherPossitions = 0;

    public static void sallaryCallculate( String fileName ) throws ParseException {
        List<List<String>> arrayForPolt = parse(fileName);
        Date startOfNextSallaryDay = startOfNextDay(arrayForPolt.get(0).get(0));
        Date dateOfDay;

        for (int i = 0; i < arrayForPolt.size(); i++) {
            String categoryOfHpProgram = arrayForPolt.get(i).get(1);
            dateOfDay = simpleDateFormat.parse(arrayForPolt.get(i).get(0));
            double sumOfPossition = Double.parseDouble(arrayForPolt.get(i).get(2));

            if (currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) {
                calculateIntoDay(categoryOfHpProgram, sumOfPossition);
            }
            if ((!currentDayOfSallary(dateOfDay, startOfNextSallaryDay)) || i == arrayForPolt.size() - 1) {
                startOfNextSallaryDay = startOfNextDay(arrayForPolt.get(i).get(0));
                calculateAfterDay(categoryOfHpProgram, sumOfPossition, dateOfDay);

            }
        }
        printMorningMap(morningSallarying);
        printDayMap(daySallarying);
        printNightMap(nightSallarying);
        printTeaMasterMap(teaMasterSallarying);


    }
    private static void calculateIntoDay( String categoryOfHpProgram, Double sumOfPossition ) {
        switch (categoryOfHpProgram) {
            case ("Утро"):
                morningSallary += sumOfPossition * 0.2;
                sumOfMorning += sumOfPossition;
                break;
            case ("Кальяны"):
                daySallary += sumOfPossition * 0.1;
                morningSallary += sumOfPossition * 0.1;
                sumOfDay += sumOfPossition;
                break;
            case ("Лимонады 0.4"):
            case ("Еда"):
            case ("Лимонады 1л"):
            case ("Молочные Коктейли"):
            case ("Смузи"):
            case ("Кофе"):
            case ("Десерты"):
            case ("Чай"):
                teaMasterSallary += sumOfPossition * 0.15;
                sumOfTeaZone += sumOfPossition;
                break;
            case ("Вечер"):
                daySallary += sumOfPossition * 0.1;
                nightSallary += sumOfPossition * 0.1;
                sumOfEvening += sumOfPossition;
                break;
            case ("Напитки"):
                sumOfDrinks += sumOfPossition;
                break;
            case ("Ночь"):
                sumOfNight += sumOfPossition;
                break;
            case ("Стаф"):
                sumOfStaff += sumOfPossition;
                break;
            case ("Учередосы"):
                sumOfOwners++;
                break;
            case ("Маркетинг"):
            case ("Клубные карты"):
                otherPossitions++;
                break;
        }
    }
    private static void calculateAfterDay( String categoryOfHpProgram, Double sumOfPossition, Date dateOfDay){
        morningSallarying.put(dateOfDay, morningSallary);
        daySallarying.put(dateOfDay, daySallary);
        nightSallarying.put(dateOfDay, nightSallary + 2000);
        teaMasterSallarying.put(dateOfDay, teaMasterSallary + 1000);

        System.out.println("В этот день: " + dateOfDay + " \n было продано напитков на сумму: \n" +
                sumOfDrinks + " , сумма по чайной зоне: \n" +
                sumOfTeaZone + " , касса утреннего мастера составила: \n" +
                sumOfMorning + " , дневная касса по сумме составила: \n" +
                sumOfDay + " , вечерняя касса: \n" +
                sumOfEvening + " , а ночная касса составила " +
                sumOfNight + " , у учредосов было \n" +
                sumOfOwners + " позиций, а стафф посидел на \n" +
                sumOfStaff + " рублей. \n" +
                " количество других позиций: " +
                otherPossitions + " \nОбщая касса составила: " +
                (sumOfDay + sumOfDrinks + sumOfEvening + sumOfMorning + sumOfNight + sumOfTeaZone + sumOfStaff));

        switch (categoryOfHpProgram) {
            case ("Утро"):
                morningSallary = sumOfPossition * 0.2;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                sumOfMorning = sumOfPossition;
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                otherPossitions = 0;
                break;
            case ("Кальяны"):
                daySallary = sumOfPossition * 0.1;
                morningSallary = sumOfPossition * 0.1;
                nightSallary = 0;
                teaMasterSallary = 0;
                sumOfDay = sumOfPossition;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                otherPossitions = 0;
                break;
            case ("Лимонады 0.4"):
            case ("Еда"):
            case ("Лимонады 1л"):
            case ("Молочные Коктейли"):
            case ("Смузи"):
            case ("Кофе"):
            case ("Десерты"):
            case ("Чай"):
                teaMasterSallary = sumOfPossition * 0.15;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = sumOfPossition;
                otherPossitions = 0;
                break;
            case ("Вечер"):
                daySallary = sumOfPossition * 0.1;
                nightSallary = sumOfPossition * 0.1;
                morningSallary = 0;
                teaMasterSallary = 0;
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = sumOfPossition;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                otherPossitions = 0;
                break;
            case ("Ночь"):
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = sumOfPossition;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                otherPossitions = 0;
                break;
            case ("Маркетинг"):
            case ("Клубные карты"):
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                otherPossitions = 1;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                break;
            case ("Учередосы"):
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 1;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                break;
            case ("Стаф"):
                sumOfDay = 0;
                sumOfDrinks = 0;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = sumOfPossition;
                sumOfTeaZone = 0;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                break;
            case ("Напитки"):
                sumOfDay = 0;
                sumOfDrinks = sumOfPossition;
                sumOfMorning = 0;
                sumOfEvening = 0;
                sumOfNight = 0;
                sumOfOwners = 0;
                sumOfStaff = 0;
                sumOfTeaZone = 0;
                morningSallary = 0;
                daySallary = 0;
                nightSallary = 0;
                teaMasterSallary = 0;
                break;
        }
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


    private static List<List<String>> parse( String fileName ) throws ParseException {
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
