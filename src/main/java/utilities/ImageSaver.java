/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author yaboo
 */
public final class ImageSaver {
    private static final String S = "/";
    private static final String USER_IMG_PATH = "img" + S + "users" + S;
    private static final String USER_STAMP_FOLDER = "stamp" + S;
    private static final String EXTENTION = ".png";
    private ImageSaver(){}
    
    public static String create(int userId, byte[] image){
        String fileDir = USER_IMG_PATH + userId + S + USER_STAMP_FOLDER;
        File userDir = new File(fileDir);
        userDir.mkdirs();
        
        String filePath = fileDir + (userDir.listFiles().length + 1) + EXTENTION;
        ByteArrayInputStream bais = new ByteArrayInputStream(image);
        BufferedImage writeImage;
        try {
            writeImage = ImageIO.read(bais);
            if(writeImage == null){
                throw new IOException("画像の形式が不正です");
            }
            ImageIO.write(writeImage, "png", new File(filePath));
        } catch (IOException ex) {
            Logger.getLogger(ImageSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filePath;
    }
}
