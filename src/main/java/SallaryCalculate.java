import org.apache.poi.ss.formula.functions.T;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SallaryCalculate {

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
    static Map <Date, Double> morningSallaring = new LinkedHashMap<>();
    static Map <Date, Double> daySallaring = new LinkedHashMap<>();
    static Map <Date, Double> nigthSallaring = new LinkedHashMap<>();
    static Map <Date, Double> teaMasterSallaring = new LinkedHashMap<>();


    protected static void calculateIntoDay( String categoryOfHpProgram, Double sumOfPossition ) {
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
    protected static void calculateAfterDay( String categoryOfHpProgram, Double sumOfPossition, Date dateOfDay) throws ParseException {
        Date currentDate = DateHandler.startOfCurrentDay(dateOfDay);
        morningSallaring.put(currentDate, morningSallary);
        daySallaring.put(currentDate, daySallary);
        nigthSallaring.put(currentDate, nightSallary + 2000);
        teaMasterSallaring.put(currentDate, teaMasterSallary + 1000);

        System.out.println("В этот день: " + currentDate + " \n было продано напитков на сумму: \n" +
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
}
