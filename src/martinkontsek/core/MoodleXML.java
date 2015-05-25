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
    public MoodleXML() 
    {
    }
    
    public void createFilesXML(File paOutDir, ArrayList<MoodleFile> paMFileList)
    {
        String output = null;
        output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                 "<files>\n";
        
        for (MoodleFile moodleFile : paMFileList) 
        {
           output += this.prepareFileXML(moodleFile);
        }
        
        output += "</files>\n";
        
        this.writeFile(new File(paOutDir, "files.xml"), output);
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
    
    public void createMoodleBackupXML(File paOutDir, String paLessonTitle)
    {
        String output = "";        
        String lessonTitle = paLessonTitle;
        
        output ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<moodle_backup>\n" +
                "  <information>\n" +
                "    <name>MoodleLessonCreator.mbz</name>\n" +
                "    <moodle_version>2014111005.12</moodle_version>\n" +
                "    <moodle_release>2.8.5+ (Build: 20150501)</moodle_release>\n" +
                "    <backup_version>2014111000</backup_version>\n" +
                "    <backup_release>2.8</backup_release>\n" +
                "    <backup_date>1432199300</backup_date>\n" +
                "    <mnet_remoteusers>0</mnet_remoteusers>\n" +
                "    <include_files>1</include_files>\n" +
                "    <include_file_references_to_external_content>0</include_file_references_to_external_content>\n" +
                "    <original_wwwroot>http://192.168.1.1/moodle</original_wwwroot>\n" +
                "    <original_site_identifier_hash>99a67b919486e43495dd57eca9972ee6</original_site_identifier_hash>\n" +
                "    <original_course_id>2</original_course_id>\n" +
                "    <original_course_fullname>Course1</original_course_fullname>\n" +
                "    <original_course_shortname>CO1</original_course_shortname>\n" +
                "    <original_course_startdate>1430784000</original_course_startdate>\n" +
                "    <original_course_contextid>18</original_course_contextid>\n" +
                "    <original_system_contextid>1</original_system_contextid>\n" +
                "    <details>\n" +
                "      <detail backup_id=\"b94171f7d3a0d6743309ea83a02512e7\">\n" +
                "        <type>activity</type>\n" +
                "        <format>moodle2</format>\n" +
                "        <interactive>1</interactive>\n" +
                "        <mode>10</mode>\n" +
                "        <execution>1</execution>\n" +
                "        <executiontime>0</executiontime>\n" +
                "      </detail>\n" +
                "    </details>\n" +
                "    <contents>\n" +
                "      <activities>\n" +
                "        <activity>\n" +
                "          <moduleid>5</moduleid>\n" +
                "          <sectionid>2</sectionid>\n" +
                "          <modulename>lesson</modulename>\n" +
                "          <title>"+lessonTitle+"</title>\n" +
                "          <directory>activities/lesson_5</directory>\n" +
                "        </activity>\n" +
                "      </activities>\n" +
                "    </contents>\n" +
                "    <settings>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>filename</name>\n" +
                "        <value>MoodleLessonCreator.mbz</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>users</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>anonymize</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>role_assignments</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>activities</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>blocks</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>filters</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>comments</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>badges</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>calendarevents</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>userscompletion</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>logs</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>grade_histories</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>root</level>\n" +
                "        <name>questionbank</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>activity</level>\n" +
                "        <activity>lesson_5</activity>\n" +
                "        <name>lesson_5_included</name>\n" +
                "        <value>1</value>\n" +
                "      </setting>\n" +
                "      <setting>\n" +
                "        <level>activity</level>\n" +
                "        <activity>lesson_5</activity>\n" +
                "        <name>lesson_5_userinfo</name>\n" +
                "        <value>0</value>\n" +
                "      </setting>\n" +
                "    </settings>\n" +
                "  </information>\n" +
                "</moodle_backup>\n";
        
        this.writeFile(new File(paOutDir, "moodle_backup.xml"), output);
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
