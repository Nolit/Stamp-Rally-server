/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author yaboo
 */
public final class ImageSaver {
    private static final String S = "/";
    private static final String USER_IMG_PATH = "img" + S + "users" + S;
    private static final String USER_STAMP_FOLDER = "stamp" + S;
    private static final String EXTENTION = ".jpeg";
    private ImageSaver(){}
    
    public static String create(int userId, byte[] image){
        String path = USER_IMG_PATH + userId + S + USER_STAMP_FOLDER;
        File userDir = new File(path);
        userDir.mkdirs();
        
        String fileName = path + (userDir.listFiles().length + 1) + EXTENTION;
        try (FileOutputStream fileOutStm = new FileOutputStream(fileName)) {
            fileOutStm.write(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
    
}
