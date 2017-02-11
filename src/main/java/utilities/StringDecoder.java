package utilities;

import java.io.UnsupportedEncodingException;

public class StringDecoder {
    public static String decode(String str){
        try {
            byte[] byteData = str.getBytes("ISO_8859_1");
            String decodedStr = new String(byteData, "UTF-8");
            
            return decodedStr;
        }catch(UnsupportedEncodingException e){
            return null;
        }
    }
}
