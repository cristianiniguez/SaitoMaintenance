package consultas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Auxiliar {

    public static boolean IsNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ArrayList<Date> SQLDateRange(Calendar cal1, Calendar cal2) {

        ArrayList<Date> dateRange = new ArrayList<>();

        Date date1 = Auxiliar.SQLDate(cal1);
        Date date2 = Auxiliar.SQLDate(cal2);

        Calendar cal = cal1;
        Date dt = date1;
        while (dt.compareTo(date2) <= 0) {
            dateRange.add(dt);
            cal.add(Calendar.DATE, 1);
            dt = Auxiliar.SQLDate(cal);
        }

        return dateRange;

    }

    public static Date SQLDate(Calendar cal) {
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return new Date(year - 1900, month, day);
    }

    public static Date FirstDayOfTheYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 1);
        return new Date(cal.getTimeInMillis());
    }

    public static Date LastDayOfTheYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 11, 31);
        return new Date(cal.getTimeInMillis());
    }
    
    public static int YearFromDate(Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal.get(Calendar.YEAR);
    }
}
