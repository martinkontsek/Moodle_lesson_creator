/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import martinkontsek.core.MoodleFile;

/**
 *
 * @author Martin
 */
public class TableModel extends AbstractTableModel
{

    private ArrayList<MoodleFile> aMFileList;
    private String[] aTableTitle = {"Number", "Original name", "Name in Moodle"};
    
    public TableModel(ArrayList<MoodleFile> paMFileList) 
    {
        aMFileList = paMFileList;
    }    
    
    @Override
    public int getRowCount() 
    {
        return aMFileList.size();
    }

    @Override
    public int getColumnCount() 
    {
       return 3; 
    }
    
    @Override
    public String getColumnName(int columnIndex) 
    {
        return aTableTitle[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        MoodleFile mf = aMFileList.get(rowIndex);
        switch(columnIndex)
        {
            case 0: 
                return rowIndex+1;
            case 1:
                return mf.getSourceFileName();
            case 2:
                return mf.getResultFileName();
            default:
                return "";
        }
    }
    
}
