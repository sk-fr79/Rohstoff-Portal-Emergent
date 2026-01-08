/*
 * Created on 18.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * klasse zum einfachen abfragen von results eines datensatzes
 */
public class myDataRecord extends VectorDataBaseQuery
{
    String cTableName = "";
    String cRecordID  = "";
    
    public myDataRecord(String ctableName, String crecordID) throws myException
    {
        super("*", bibALL.get_TABLEOWNER()+"."+ctableName , "","ID_"+ctableName.substring(3)+"="+crecordID, "");
        
        this.cTableName = ctableName;
        this.cRecordID = crecordID;
        
        if (this.size() == 0)
        {
            throw new myException("myDataRecord:No Record Found !!");
        }
        
        if (this.size() > 1)
        {
            throw new myException("myDataRecord:Not unique value !!");
        }
    
    }
    
    
    
    
    public String get_resultValue(String cFIELDNAME)
    {
        return this.get_resultValue(cFIELDNAME,0);       
    }
    
    
    public String get_resultValueFormated(String cFIELDNAME)
    {
        return this.get_resultValueFormated(cFIELDNAME,0);       
    }
 
    
    
    public String get_cRecordID()
    {
        return cRecordID;
    }
    public String get_cTableName()
    {
        return cTableName;
    }
}
