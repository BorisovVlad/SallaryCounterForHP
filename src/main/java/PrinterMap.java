import java.util.Date;
import java.util.Map;

public class PrinterMap {

    protected static void printMorningMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата утреннего мастера " + key + "     :     " + value + " рублей"));
    }

    protected static void printDayMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата дневного мастера " + key + "     :     " + value + " рублей"));
    }

    protected static void printNightMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата ночного мастера " + key + "     :     " + value + " рублей"));
    }

    protected static void printTeaMasterMap( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата чайного мастера " + key + "     :     " + value + " рублей"));
    }
    protected static void printKonMaster( Map<Date, Double> map ) {
        map.forEach(( key, value ) -> System.out.println("Зарплата мастера на конюшенной " + key + "     :     " + value + " рублей"));
    }
}
