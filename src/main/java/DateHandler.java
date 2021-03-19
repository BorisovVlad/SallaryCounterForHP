import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    protected static Date startOfNextDay( String s ) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(Handler.simpleDateFormat.parse(s));//устанавливаю дату, с которой буду производить операции
        instance.add(Calendar.DAY_OF_MONTH, 1);// прибавляю 1 день к установленной дате
        Date startOfNextDay = instance.getTime(); // получаю измененную дату
        startOfNextDay.setHours(11);
        startOfNextDay.setMinutes(0);
        return startOfNextDay;
    }
    protected static Date startOfCurrentDay( Date date ) throws ParseException {
        Date startOfCurrentDay = date;
        Calendar instance = Calendar.getInstance();
        instance.setTime(startOfCurrentDay);//устанавливаю дату, с которой буду производить операции
        instance.add(Calendar.DAY_OF_MONTH, -1);// убавляю 1 день у даты
        startOfCurrentDay = instance.getTime();
        startOfCurrentDay.setHours(11);
        startOfCurrentDay.setMinutes(0);
        return startOfCurrentDay;
    }
}
