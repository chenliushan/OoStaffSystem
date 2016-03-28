package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by liushanchen on 16/3/28.
 */
public class CommonUtils {
    private static Logger logger = LogManager.getLogger(CommonUtils.class.getName());

    public Date date(String dateTime) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            if (dateTime != null) {
                date = sdf.parse(dateTime);
                return date;
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


}
