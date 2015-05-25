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
 * @author Martin Kontsek
 */
public class TableModel extends AbstractTableModel
{

    private ArrayList<MoodleFile> aMFileList;
    private String[] aTableTitle = {"Slide No.", "Original name", "Name in Moodle", "Prev. Btn.", "Next Btn."};
    
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
       return 5; 
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
            case 3:
                return mf.getPreviousBtn();
            case 4:
                return mf.getNextBtn();
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) 
    {
        switch(columnIndex)
        {
            case 0:
                return Integer.class;
            case 3:
            case 4:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        if(columnIndex == 3 || columnIndex == 4)
            return true;
        else
            return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
        MoodleFile mf = aMFileList.get(rowIndex);
        boolean val = (boolean)aValue;
        if(columnIndex == 3)
        {
            mf.setPreviousBtn(val);
        } else if(columnIndex == 4)
        {
            mf.setNextBtn(val);
        }
            
    }
    
    
}
