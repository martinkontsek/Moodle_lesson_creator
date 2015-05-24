/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import martinkontsek.gui.Main;
import martinkontsek.gui.TableModel;

/**
 *
 * @author Martin
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
    }
}
