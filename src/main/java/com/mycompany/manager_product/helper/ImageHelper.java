/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.manager_product.helper;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author saotr
 */
public class ImageHelper {
    public static Image resize(Image originalImamge, int targetWidth, int targetHeight){
        Image resultingImage = originalImamge.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return resultingImage;
    }
    
    public static byte[] toByteArray(Image image,String type) throws IOException{
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0,0, null);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, type, baos);
        byte[] imageInByte = baos.toByteArray();
        
        return imageInByte;
    }
    
    public static Image createImageFromByteArray(byte[] data, String type) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BufferedImage bufferedImage2 = ImageIO.read(byteArrayInputStream);
        
        Image img = bufferedImage2.getScaledInstance(bufferedImage2.getWidth(), bufferedImage2.getHeight(),
                Image.SCALE_SMOOTH);
        return img;
    }
}
