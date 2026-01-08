/**
 * panter.gmbh.indep
 * @author martin
 * @date 14.06.2019
 * 
 */
package panter.gmbh.indep;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.indep.myVectors.VEK;
/**
 * @author martin
 * @date 14.06.2019
 *
 */
public class TimeTools {


    /**
     *  @param  dateTimeString  (erlaubt: 31.12.2019 , 31.12.2019 HH:mm,  31.12.19)
     * @return
     */
    public static LocalDateTime getTime(String dateTimeString) {
        LocalDateTime ld = null;

        String pattern = "";
        if (dateTimeString.length()==10) {
            dateTimeString = dateTimeString+" 00:00";
            pattern = "dd.MM.yyyy HH:mm";
        } else if (dateTimeString.length()==8){
            dateTimeString = dateTimeString+" 00:00";
            pattern = "dd.MM.yy HH:mm";
        } else if (dateTimeString.length()==16) {
            pattern = "dd.MM.yyyy HH:mm";
        }


        try {
            ld =  LocalDateTime.parse(dateTimeString,  DateTimeFormatter.ofPattern(pattern) );
        } catch (Exception e) {
        }

        return ld;
    }


    public static Date convertLocalDateTimeToDate(LocalDateTime dt) {
        try {
            ZonedDateTime zdt = dt.atZone(ZoneId.systemDefault());
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
        }
        return null;
    }
    
    
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    

    
    public static  LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime();
    }
    
    

    /**
     *
     * @param d1
     * @param d2
     * @return seconds
     */
    public static Long getZeitZwischenInSec(LocalDateTime d1, LocalDateTime d2) {
        Long abstand = null;

        Duration d = Duration.between(d1,d2);

        abstand = d.getSeconds();
        return abstand;
    }

    /**
    *
    * @param d1
    * @param d2
    * @return seconds
    */
   public static Long getDaysBetween(LocalDate d1, LocalDate d2) {
       return ChronoUnit.DAYS.between(d1, d2);
   }



  

   
   
    public static String tryToCorrectTimeString(String zeit) {
        String s = S.NN(zeit).trim();

        int posOfSeparat = s.indexOf(":");

        if (posOfSeparat==-1) {
            s=s+":00";
            posOfSeparat = s.indexOf(":");
        }

        String std = s.substring(0,posOfSeparat);
        String min = s.substring(posOfSeparat+1);

        if (S.isEmpty(std) || S.isEmpty(min)) {
            return zeit;        //falsch, kann nicht korrigiert werden
        }

        try {
            int iMin = Integer.parseInt(min);
            int iStd = Integer.parseInt(std);

            if (iMin>=0 && iMin<=59) {
                min = ""+iMin;
                if (min.length()==1) {
                    min="0"+min;
                }
            }

            if (iStd>=0 && iStd<=23) {
                std = ""+iStd;
                if (std.length()==1) {
                    std="0"+std;
                }
            }

            return std+":"+min;

        } catch (NumberFormatException e) {
            return zeit;        //falsch, kann nicht korrigiert werden
        }

    }


    /**
     *
     * @param zeit
     * @return s true, when time is like : "01:01 - 23:59"
     */
    public static boolean checkCorrectTimeString(String zeit) {
        String s = S.NN(zeit).trim();

        int posOfSeparat = s.indexOf(":");

        if (posOfSeparat==-1) {
            s=s+":00";
            posOfSeparat = s.indexOf(":");
        }

        String std = s.substring(0,posOfSeparat);
        String min = s.substring(posOfSeparat+1);

        if (S.isEmpty(std) || S.isEmpty(min)) {
            return false;
        }

        try {
            int iMin = Integer.parseInt(min);
            int iStd = Integer.parseInt(std);

            if (iMin>=0 && iMin<=59) {
                min = ""+iMin;
                if (min.length()==1) {
                    min="0"+min;
                }
            }

            if (iStd>=0 && iStd<=23) {
                std = ""+iStd;
                if (std.length()==1) {
                    std="0"+std;
                }
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }




    public static VEK<String> getMonthList() {
        return new VEK<String>()._a("01")._a("02")._a("03")._a("04")._a("05")._a("06")._a("07")._a("08")._a("09")._a("10")._a("11")._a("12");
    }



    public static  VEK<String> getYearsNearby() {
        VEK<String> jahre = new VEK<String>();


        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        for (int i=2015;i<=cal.get(GregorianCalendar.YEAR)+5;i++) {
            jahre._a(""+i);
        }

        return jahre;
    }
    
    
    

    public static TRIPLE<Integer,Integer, Integer> getActualDayMonthYear() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        return new TRIPLE<Integer,Integer, Integer>()._setVal1(cal.get(GregorianCalendar.DAY_OF_MONTH))._setVal2(cal.get(GregorianCalendar.MONTH))._setVal3(cal.get(GregorianCalendar.YEAR));
    }

    
    
    
    public static Integer getDiffInTage(Date d1, Date d2) {
		LocalDate ld1  =	TimeTools.convertToLocalDateViaInstant(d1);
		LocalDate ld2  = 	TimeTools.convertToLocalDateViaInstant(d2);
		
		return TimeTools.getDaysBetween(ld1, ld2).intValue();
    }

	
}
