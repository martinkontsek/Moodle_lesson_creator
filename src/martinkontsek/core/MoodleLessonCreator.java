/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JTable;
import martinkontsek.gui.Main;
import martinkontsek.gui.TableModel;

/**
 *
 * @author Martin Kontsek
 */
public class MoodleLessonCreator 
{

    private Main aMainGUI;
    private ArrayList<MoodleFile> aMFileList;
    private TableModel aTableModel; 
    
    public MoodleLessonCreator(Main paMainGUI) 
    {
        aMainGUI = paMainGUI;
        aMFileList = new ArrayList<>();
        aTableModel = null;
    }
    
    public void fillTable(File paDirFiles)
    {
        File[] filesInFolder = paDirFiles.listFiles();
         

        for(File sFile : filesInFolder) 
        {
            aMFileList.add(new MoodleFile(sFile));
        }

        aTableModel = new TableModel(aMFileList);
        JTable table = aMainGUI.getTable();
        table.setModel(aTableModel);
        table.setVisible(true);
    }
    
    public void renameAll(String paStartString)
    {
        if(paStartString == null || paStartString.equals(""))
            return;
       
        int i = 1;
        for(MoodleFile mFile: aMFileList)
        {
            mFile.setResultFileName(paStartString+"_"+i);
            i++;        
        }
        
        aTableModel.fireTableDataChanged();
    }
    
    private void encodeMoodleImages(File paMoodleDir)
    {
        File filesDir = new File(paMoodleDir, "files");
        Utilities.createDir(filesDir);
        
        for(MoodleFile mFile: aMFileList)
        {
            mFile.saveFileMoodle(filesDir);
        }
    }
    
    private void setRandomID()
    {
        int id = 10 + (int)(Math.random()*800);
        for(MoodleFile mFile: aMFileList)
        {
            mFile.setFileID(id);
            id += 2;
        }
    }
    
    private void saveFileResource(File paOutDir, String paFilePath, String paFileName)
    {
        InputStream is = getClass().getResourceAsStream(paFilePath+paFileName);
        
//        int pos = paFilePath.lastIndexOf('/');
//        String fileName = paFilePath.substring(pos, paFilePath.length());
        File outputFile = new File(paOutDir, paFileName);
        
        OutputStream os = null;
        try {
            os = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            
            is.close();
            os.close();
        } catch (IOException ex) {
        }
    }
    
    public void saveFileResourcesToBackup(File paMoodleDir, File paLessonDir)
    {
        String mainRes = "/martinkontsek/resources/";
        this.saveFileResource(paMoodleDir, mainRes, "groups.xml");
        this.saveFileResource(paMoodleDir, mainRes, "moodle_backup.log");
        this.saveFileResource(paMoodleDir, mainRes, "outcomes.xml");
        this.saveFileResource(paMoodleDir, mainRes, "questions.xml");
        this.saveFileResource(paMoodleDir, mainRes, "roles.xml");
        this.saveFileResource(paMoodleDir, mainRes, "scales.xml");
        
        String lessonRes = "/martinkontsek/resources/lesson/";
        this.saveFileResource(paLessonDir, lessonRes, "calendar.xml");
        this.saveFileResource(paLessonDir, lessonRes, "filters.xml");
        this.saveFileResource(paLessonDir, lessonRes, "grade_history.xml");
        this.saveFileResource(paLessonDir, lessonRes, "module.xml");
        this.saveFileResource(paLessonDir, lessonRes, "roles.xml");        
    }
    
    public void createMoodleBackup()
    {
        File moodleDir = new File("MoodleBackup");        
        Utilities.createDir(moodleDir);
                
        MoodleXML mXml = new MoodleXML(moodleDir);
        this.setRandomID();
        
        //encode all images to files dir
        this.encodeMoodleImages(moodleDir);
        //create files.xml
        mXml.createFilesXML(aMFileList);
        
        File activitiesDir = new File(moodleDir, "activities");
        Utilities.createDir(activitiesDir);        
        File lessonDir = new File(activitiesDir, "lesson_5");
        Utilities.createDir(lessonDir);
        
        //save files which don't need editing from resources
        this.saveFileResourcesToBackup(moodleDir, lessonDir);
    }
}
