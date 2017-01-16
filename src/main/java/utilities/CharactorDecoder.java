/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Nolit
 */
public class CharactorDecoder {
    public static String decodeString(String decodesr){
        try {
          byte[] byteData = decodesr.getBytes("ISO_8859_1");
          decodesr = new String(byteData, "UTF-8");
        }catch(UnsupportedEncodingException e){
          return null;
        }

    return decodesr;
  }
}
