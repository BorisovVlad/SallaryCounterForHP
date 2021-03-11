import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    protected static Date startOfNextDay( String s ) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(Handler.simpleDateFormat.parse(s));//устанавливаю дату, с которой буду производить операции
        instance.add(Calendar.DAY_OF_MONTH, 1);// прибавляю 1 день к установленной дате
        instance.set(Calendar.HOUR, 11);// устанавливаю 11 часов в следущем дне
        instance.set(Calendar.MINUTE, 0);//ну и 00 минут
        Date startOfNextDay = instance.getTime(); // получаю измененную дату
        return startOfNextDay;
    }
    protected static Date startOfCurrentDay( Date date ) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);//устанавливаю дату, с которой буду производить операции
        instance.add(Calendar.DAY_OF_MONTH, -1);// убавляю 1 день у даты
        instance.set(Calendar.HOUR, 11);// устанавливаю 11 часов в этом дне
        instance.set(Calendar.MINUTE, 0);//ну и 00 минут
        Date startOfCurrentDay = instance.getTime(); // получаю измененную дату
        return startOfCurrentDay;
    }
}
