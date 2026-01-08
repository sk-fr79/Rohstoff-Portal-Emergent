package panter.gmbh.indep.dataTools;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * aufbau eines kopie-sql-statements fuer einen beliebigen datensatz in form eines 
 * INSERT INTO JT_TABLE (FIELD1,FIELD2,...) values SELECT (FIELD1,FIELD2, ...) 
 * wobei eine beliebige liste von feldern mit einem anderen string-ausdruck uebergeben werden kann
 * Dazu wird ein Array NOT_Fields[] bergeben (diese werden nicht benutzt),
 * eine hashmap mit gross-geschriebenen feldnamen und den ersatzstrings, die im select-feld als konstante an die position gesetzt werden  
 */
public class myDataRecordCopySQLString
{
    private String 					cTableNameSourceTable = "";
    private String 					cKeyNameSourceTable = "";
    private String 					ID_WertSourceTable     = "";
    private String 					cTableNameTargetTable = null;
    private String 					cKeyNameTargetTable = null;
    
    private Vector<String>			vNOT_FIELDS = null;
    private HashMap<String,String>  hmErsatzFields = new HashMap<String, String>();
    private MyMetaFieldDEF_HashMap 	oFieldStructureTargetTable = null;
    private String					cTO  = "";
    private boolean 				bAddStandardErsatzFields = false;
    

    /**
     * @param ctableNameSourceTable
     * @param ckeyNameSourceTable
     * @param cid_WertSourceTable
     * @param ctableNameTargetTable
     * @param ckeyNameTargetTable
     * @param vnOT_FIELDS
     * @param hmersatzfields
     * @param baddStandardErsatzFields
     * @throws myException
     */
    public myDataRecordCopySQLString(   String ctableNameSourceTable,
    									String ckeyNameSourceTable, 
    									String cid_WertSourceTable, 
    									String ctableNameTargetTable, 
    									String ckeyNameTargetTable,
    									Vector<String> vnOT_FIELDS,
    									HashMap<String,String> hmersatzfields, 
    									boolean baddStandardErsatzFields) throws myException
    {
        super();
        try 
        {
			this.cTableNameSourceTable 		= ctableNameSourceTable;
			this.cKeyNameSourceTable		= ckeyNameSourceTable;
			this.ID_WertSourceTable 		= cid_WertSourceTable;
			
			this.cTableNameTargetTable     	= ctableNameTargetTable;
			this.cKeyNameTargetTable 		= ckeyNameTargetTable;
			
  
			if (bibALL.isEmpty(this.cTableNameTargetTable))
				this.cTableNameTargetTable = this.cTableNameSourceTable;
			
			if (bibALL.isEmpty(this.cKeyNameTargetTable))
				this.cKeyNameTargetTable = this.cKeyNameSourceTable;
			
			
			
			this.vNOT_FIELDS 		= vnOT_FIELDS;
			if (hmersatzfields!=null) this.hmErsatzFields.putAll(hmersatzfields);
			
			this.oFieldStructureTargetTable 	= new MyMetaFieldDEF_HashMap(this.cTableNameTargetTable);
			this.cTO				= bibALL.get_TABLEOWNER();
			this.bAddStandardErsatzFields = baddStandardErsatzFields;
			
			String cTableBaseName = this.cTableNameTargetTable.substring(3);
			
			if (this.vNOT_FIELDS == null) this.vNOT_FIELDS = new Vector<String>();
			
			if (this.bAddStandardErsatzFields)
			{
			    this.hmErsatzFields.put(this.cKeyNameTargetTable,"SEQ_" +cTableBaseName+ ".NEXTVAL");
			    this.hmErsatzFields.putAll(DB_STATICS.get_hmZusatzFelder_STD());
			}
			
			this.pruefe_hmErsatzFields();
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
			throw new myException(this+":SQLException:"+e.getLocalizedMessage());
		}
    }
    
	/*
	 * jetzt pruefen, ob ein ersatzfeld uebergeben wird, das nicht in der tabelle vorhanden ist,
	 * wenn ja = programmierfehler = myException
	 */
    private void pruefe_hmErsatzFields() throws myException
    {
		Vector<String> vKeys = bibALL.get_vBuildKeyVectorFromHashmap(this.hmErsatzFields);
		Vector<String> vRealFields = this.oFieldStructureTargetTable.get_vColumnNames();
		for (int i=0;i<vKeys.size();i++)
		{
			if (!vRealFields.contains(vKeys.get(i)))
			{
				throw new myException("myDataRecordCopySQLString:Hashmap with ERSATZFIELDs contains fieldname that is not in the Table !!:"+(String)vKeys.get(i));
			}
		}
    }

    
    public void add_ErsatzFields(HashMap<String, String> hmZusatzErsatzFields) throws myException
    {
    	this.hmErsatzFields.putAll(hmZusatzErsatzFields);
    	this.pruefe_hmErsatzFields();
    }
    
    
    
    public String get_cINSERT_String() throws myException
    {
        String cRueck = "INSERT INTO "+this.cTO+"."+this.cTableNameTargetTable +" (";
        
        /*
         * zuerst die felder, die direkt in der liste stehen, pruefen
         */
        Vector<String> vFields = new Vector<String>();
        
        for (int i=0,iZahl=this.oFieldStructureTargetTable.get_iNumberOfColumns();i<iZahl;i++)
        {
            vFields.add(this.oFieldStructureTargetTable.get_FieldName(i).toUpperCase());
        }
        
        /*
         * jetzt die nicht erwuensten raus
         */
        vFields.removeAll(this.vNOT_FIELDS);
        
        /*
         * jetzt den insert-block bauen
         */
        for (int i=0,iZahl=vFields.size()-1;i<iZahl;i++)
        {
            cRueck += (String) vFields.get(i)+",";
        }
        cRueck += (String) vFields.get(vFields.size()-1)+") SELECT ";
        
        /*
         * jetzt den neuen select-block
         */
        String cFeld;
        String cErsatz;
        for (int i=0,iZahl=vFields.size()-1;i<iZahl;i++)
        {
            cFeld 		= (String) vFields.get(i);
            cErsatz 	= (String)this.hmErsatzFields.get(cFeld);
            if (cErsatz == null)
            {
                cRueck += cFeld+",";
            }
            else
            {
                cRueck += cErsatz+" AS "+cFeld+",";
            }
        }
        /*
         * das letzte ohne komma anhaengen
         */
        cFeld 		= (String) vFields.get(vFields.size()-1);
        cErsatz 	= (String)this.hmErsatzFields.get(cFeld);
        if (cErsatz == null)
        {
            cRueck += cFeld;
        }
        else
        {
            cRueck += cErsatz+" AS "+cFeld;
        }
        
        cRueck += " FROM "+this.cTO+"."+this.cTableNameSourceTable+" WHERE "+this.cKeyNameSourceTable+"="+this.ID_WertSourceTable;
        
        return MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cRueck;
    }
    
    
    
}
