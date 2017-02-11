/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
public final class ImageUtil {
    private static final String S = "/";
    private static final String USER_IMG_PATH = "img" + S + "users" + S;
    private static final String USER_STAMP_FOLDER = "stamp" + S;
    private static final String USER_THUMBNAIL_FOLDER = "thumbnail" + S;    
    private static final String EXTENTION = "jpg";
    private static final String DEFAULT_PATH = "img/common/noimage.jpg";
    private ImageUtil(){}
    
    public static String createStamp(int userId, byte[] image){
        String fileDir = USER_IMG_PATH + userId + S + USER_STAMP_FOLDER;
        File userDir = new File(fileDir);
        userDir.mkdirs();
        
        String filePath = fileDir + (userDir.listFiles().length + 1) + "." + EXTENTION;
        createImage(filePath, image);

        return filePath;
    }
    
    public static String createUser(int userId, byte[] image){
        String fileDir = USER_IMG_PATH + userId + S + USER_THUMBNAIL_FOLDER;
        File userDir = new File(fileDir);
        userDir.mkdirs();
        
        String filePath = fileDir + (userDir.listFiles().length + 1) + "." + EXTENTION;
        createImage(filePath, image);

        return filePath;
    }
    
    private static void createImage(String filePath, byte[] image){
        ByteArrayInputStream bais = new ByteArrayInputStream(image);
        BufferedImage writeImage;
        try {
            writeImage = ImageIO.read(bais);
            if(writeImage == null){
                throw new IOException("画像の形式が不正です");
            }
            ImageIO.write(writeImage, EXTENTION, new File(filePath));
        } catch (IOException ex) {
            Logger.getLogger(ImageUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static byte[] read(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();;
        try {
            String nonNullPath = path != null ? path : DEFAULT_PATH;
            File file = new File(nonNullPath);
            file = file.exists() ? file : new File(DEFAULT_PATH);
            BufferedImage img = ImageIO.read(file);
            ImageIO.write(img, EXTENTION, baos);
        } catch (Exception ex) {
            Logger.getLogger(ImageUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return baos.toByteArray();
    }
}
