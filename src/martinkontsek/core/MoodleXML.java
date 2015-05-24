/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Martin Kontsek
 */
public class MoodleXML 
{
    private File aOutDir;

    public MoodleXML(File paOutDir) 
    {
        aOutDir = paOutDir;
    }
    
    public void createFilesXML(ArrayList<MoodleFile> paMFileList)
    {
        String output = null;
        output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                 "<files>\n";
        
        for (MoodleFile moodleFile : paMFileList) 
        {
           output += this.prepareFileXML(moodleFile);
        }
        
        output += "</files>\n";
        
        this.writeFile(new File(aOutDir, "files.xml"), output);
    }
    
    private String prepareFileXML(MoodleFile paMFile)
    {
        String output = null;
        String fileHash = paMFile.getHash();
        String fileName = paMFile.getResultFileName();
        long fileSize = paMFile.getSourceFileSize();
        int fileID = paMFile.getFileID();
        
        int ID_offset = 100;
        
        output ="  <file id=\""+(fileID+ID_offset)+"\">\n" +
                "    <contenthash>"+fileHash+"</contenthash>\n" +
                "    <contextid>28</contextid>\n" +
                "    <component>mod_lesson</component>\n" +
                "    <filearea>page_contents</filearea>\n" +
                "    <itemid>"+fileID+"</itemid>\n" +
                "    <filepath>/</filepath>\n" +
                "    <filename>"+fileName+"</filename>\n" +
                "    <userid>2</userid>\n" +
                "    <filesize>"+fileSize+"</filesize>\n" +
                "    <mimetype>image/png</mimetype>\n" +
                "    <status>0</status>\n" +
                "    <timecreated>1432199255</timecreated>\n" +
                "    <timemodified>1432199277</timemodified>\n" +
                "    <source>"+fileName+"</source>\n" +
                "    <author>Admin</author>\n" +
                "    <license>allrightsreserved</license>\n" +
                "    <sortorder>0</sortorder>\n" +
                "    <repositorytype>$@NULL@$</repositorytype>\n" +
                "    <repositoryid>$@NULL@$</repositoryid>\n" +
                "    <reference>$@NULL@$</reference>\n" +
                "  </file>\n" +
                "  <file id=\""+(fileID+ID_offset+1)+"\">\n" +
                "    <contenthash>da39a3ee5e6b4b0d3255bfef95601890afd80709</contenthash>\n" +
                "    <contextid>28</contextid>\n" +
                "    <component>mod_lesson</component>\n" +
                "    <filearea>page_contents</filearea>\n" +
                "    <itemid>"+fileID+"</itemid>\n" +
                "    <filepath>/</filepath>\n" +
                "    <filename>.</filename>\n" +
                "    <userid>2</userid>\n" +
                "    <filesize>0</filesize>\n" +
                "    <mimetype>$@NULL@$</mimetype>\n" +
                "    <status>0</status>\n" +
                "    <timecreated>1432199255</timecreated>\n" +
                "    <timemodified>1432199277</timemodified>\n" +
                "    <source>$@NULL@$</source>\n" +
                "    <author>$@NULL@$</author>\n" +
                "    <license>$@NULL@$</license>\n" +
                "    <sortorder>0</sortorder>\n" +
                "    <repositorytype>$@NULL@$</repositorytype>\n" +
                "    <repositoryid>$@NULL@$</repositoryid>\n" +
                "    <reference>$@NULL@$</reference>\n" +
                "  </file>\n";
        
        return output;
    }
    
    private void writeFile(File paFile, String paContent)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(paFile);
            out.println(paContent);
        } catch (FileNotFoundException ex) {
            
        } finally {
            out.close();
        }
    }
    
}
