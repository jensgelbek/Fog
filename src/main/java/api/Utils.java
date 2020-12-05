
package api;

import domain.items.Carport;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Utils {
    /**
     * @param src String to be stripped
     * @return String without HTML tags
     */
    public static String removeHtml(String src) {
        /*
         * Tak https://www.tutorialspoint.com/how-to-remove-the-html-tags-from-a-given-string-in-java
         */
        return src.replaceAll("\\<.*?\\>", "");
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp){
        if (timestamp!=null){
          return  timestamp.toLocalDateTime().toLocalDate();
        }
        return null;
    }
}
