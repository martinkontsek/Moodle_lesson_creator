/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 *
 * @author Martin Kontsek
 */
public class Utilities 
{
    public static boolean createDir(File paDir)
    {
        boolean result = false;
        
        if (!paDir.exists()) {           

            try{
                paDir.mkdir();
                result = true;
            } 
            catch(SecurityException se) 
            {
                //handle it
            }      
            
        } else {
            result = true;
        }
        
        return result;
    }
    
    public static void compressFolder(File paFolderToCompress)
    {
        try {
            // Initiate ZipFile object with the path/name of the zip file.
            ZipFile zipFile = new ZipFile("MoodleLessonCreator.mbz");

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            File[] files = paFolderToCompress.listFiles();
            ArrayList<File> fileList = new ArrayList<>();
            
            for(File file: files)
            {
                if(file.isDirectory())
                {
                    // Add folder to zip
                    zipFile.addFolder(file, parameters);
                } else {
                    fileList.add(file);
                }
            }
            
            // Add files to the zip file
            zipFile.addFiles(fileList, parameters);
        } catch(ZipException ex) {
            
        }
    }
    
    public static boolean deleteDir(File paDir) 
    { 
        if (paDir.isDirectory()) 
        { 
            String[] children = paDir.list(); 
            for (int i=0; i<children.length; i++)
            { 
                boolean success = deleteDir(new File(paDir, children[i])); 
                if (!success) 
                    return false;
            } 
        }  
      // The directory is now empty or this is a file so delete it 
      return paDir.delete(); 
    } 
    
    public static boolean existsClass(String paClassName) 
    {
        boolean exist = true;
        try  {
            Class.forName(paClassName);
        }  catch (ClassNotFoundException e) {
            exist = false;
        }
        return exist;
    }
    
    public static ImageIcon getScaledImage(ImageIcon srcImg, int w, int h)
    {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
        g2.dispose();
        return new ImageIcon(resizedImg);
    } 
    
    

    public static void openWebpage(String urlString) 
    {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
        }
    }
}
