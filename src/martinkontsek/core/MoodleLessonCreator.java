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
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
    private JTable aTable;
    private TableModel aTableModel; 
    private String aLessonName;
    private String aPreviousCaption;
    private String aNextCaption;
    
    public MoodleLessonCreator(Main paMainGUI) 
    {
        aMainGUI = paMainGUI;
        aMFileList = new ArrayList<>();
        aTable = null;
        aTableModel = null;
        aLessonName = "Lesson1";
        aPreviousCaption = "Previous";
        aNextCaption = "Next";
    }
    
    public String getLessonName()
    {
        return aLessonName;
    }
    
    public void setLessonName(String paLessonName)
    {
        if(paLessonName == null || paLessonName.equals("") || paLessonName.equals(" "))
            return;
        aLessonName = paLessonName;
    }

    public String getPreviousCaption() 
    {
        return aPreviousCaption;
    }

    public void setPreviousCaption(String paPreviousCaption) 
    {
        if(paPreviousCaption == null || paPreviousCaption.equals("") || paPreviousCaption.equals(" "))
            return;
        this.aPreviousCaption = paPreviousCaption;
    }

    public String getNextCaption() 
    {
        return aNextCaption;
    }

    public void setNextCaption(String paNextCaption) 
    {
        if(paNextCaption == null || paNextCaption.equals("") || paNextCaption.equals(" "))
            return;
        this.aNextCaption = paNextCaption;
    }
      
    public void fillTable(File paDirFiles)
    {
        File[] filesInFolder = paDirFiles.listFiles();
         

        for(File sFile : filesInFolder) 
        {
            aMFileList.add(new MoodleFile(sFile));
        }

        aTableModel = new TableModel(aMFileList);
        aTable = aMainGUI.getTable();
        aTable.setModel(aTableModel);
        aTable.getTableHeader().setReorderingAllowed(false);
        aTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        aTable.setVisible(true);
    }
    
    public void moveUp()
    {
        try {
            int rowIndex = aTable.getSelectedRow();
            if(rowIndex < 0 || rowIndex >= aMFileList.size())
                return;
            Collections.swap(aMFileList, rowIndex, rowIndex-1);
            aTableModel.fireTableDataChanged();
            aTable.setRowSelectionInterval(rowIndex-1, rowIndex-1);
        } catch (NullPointerException ex) {           
        }
    }
    
    public void moveDown()
    {
        try {
            int rowIndex = aTable.getSelectedRow(); 
            if(rowIndex < 0 || rowIndex >= aMFileList.size())
                return;
            Collections.swap(aMFileList, rowIndex, rowIndex+1);
            aTableModel.fireTableDataChanged();
            aTable.setRowSelectionInterval(rowIndex+1, rowIndex+1);
        } catch (NullPointerException ex) {           
        }
    }
    
    public void moveStart()
    {
        try {
            int rowIndex = aTable.getSelectedRow();
            if(rowIndex < 0 || rowIndex >= aMFileList.size())
                return;
            MoodleFile mf = aMFileList.remove(rowIndex);
            aMFileList.add(0, mf);
            aTableModel.fireTableDataChanged();
            aTable.setRowSelectionInterval(0, 0);
        } catch (NullPointerException ex) {           
        }
    }
    
    public void moveEnd()
    {
        try {
            int rowIndex = aTable.getSelectedRow();
            if(rowIndex < 0 || rowIndex >= aMFileList.size())
                return;
            MoodleFile mf = aMFileList.remove(rowIndex);
            aMFileList.add(mf);
            aTableModel.fireTableDataChanged();
            aTable.setRowSelectionInterval(aMFileList.size()-1, aMFileList.size()-1);
        } catch (NullPointerException ex) {           
        }
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
                
        MoodleXML mXml = new MoodleXML();
        this.setRandomID();
        
        //encode all images to files dir
        this.encodeMoodleImages(moodleDir);
        //create files.xml
        mXml.createFilesXML(moodleDir, aMFileList);
        //create moodle_backup.xml
        mXml.createMoodleBackupXML(moodleDir, aLessonName);
        
        File activitiesDir = new File(moodleDir, "activities");
        Utilities.createDir(activitiesDir);        
        File lessonDir = new File(activitiesDir, "lesson_5");
        Utilities.createDir(lessonDir);
        
        //create inforef.xml
        mXml.createInforefXML(lessonDir);
        //create grades.xml
        mXml.createGradesXML(lessonDir, aLessonName);
        //create lesson.xml
        mXml.createLessonXML(lessonDir, aMFileList, aLessonName, aPreviousCaption, aNextCaption);
        
        //save files which don't need editing from resources
        this.saveFileResourcesToBackup(moodleDir, lessonDir);
        
        //compress moodle backup
        Utilities.compressFolder(moodleDir);
        
        //cleanup
        Utilities.deleteDir(moodleDir);
    }
}
