package remoteapkinstallerclient.com.br.remoteinstaller.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;

/**
 * Created by Eduardo on 14/01/2017.
 */

public class DateUtils {
    private static String[] months = {
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"
    };

    public static Date toDate(String format, String dateToParse) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(dateToParse);
    }

    public static Date toDateAndZeroSeconds(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date toDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date toDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today());
        calendar.add(DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static String toString(String format, long dateToParse) {
        return toString(format, new Date(dateToParse), "");
    }

    public static String toString(String format, Date dateToParse) {
        return toString(format, dateToParse, "");
    }

    public static String toString(String format, Date dateToParse, String defaultVale) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
            return simpleDateFormat.format(dateToParse);
        } catch (Exception e) {
        }
        return defaultVale;
    }

    public static Date getRemainingTime(Long remainingMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MILLISECOND, remainingMillis.intValue());
        return calendar.getTime();
    }

    public static String getMonthName(Date date) {
//        months = context.getResources().getStringArray(R.array.months);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int mes = calendar.get(Calendar.MONTH);
        return months[mes];
    }

    public static boolean isThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisYear = calendar.get(Calendar.YEAR);
        calendar.setTime(date);
        int dateMonth = calendar.get(Calendar.MONTH);
        int dateYear = calendar.get(Calendar.YEAR);
        return (dateMonth == thisMonth) && (dateYear == thisYear);
    }

    public static boolean isThisDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisYear = calendar.get(Calendar.YEAR);
        int thisDay = calendar.get(DAY_OF_MONTH);
        calendar.setTime(date);
        int dateMonth = calendar.get(Calendar.MONTH);
        int dateYear = calendar.get(Calendar.YEAR);
        int dateDay = calendar.get(DAY_OF_MONTH);
        return (dateMonth == thisMonth) && (dateYear == thisYear) && (dateDay == thisDay);
    }

    public static Date initDate(Date date) {
        Calendar firstDay = Calendar.getInstance();
        firstDay.setTime(date);
        firstDay.set(DAY_OF_MONTH, 1);
        return firstDay.getTime();
    }

    public static Date finalDate(Date date) {
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(date);
        lastDay.add(Calendar.MONTH, 1);
        lastDay.set(DAY_OF_MONTH, 1);
        lastDay.add(DAY_OF_MONTH, -1);
        return lastDay.getTime();
    }

    public static int getFinalDayOfMonth(Date date) {
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(date);
        int diaFinal = lastDay.getMaximum(DAY_OF_MONTH);
        return diaFinal;
    }

    public static Date addMonth(Date date) {
        return addWithCalendar(date, Calendar.MONTH, 1);
    }

    public static Date subtractMonth(Date date) {
        return addWithCalendar(date, Calendar.MONTH, -1);
    }

    private static Date addWithCalendar(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }


}
