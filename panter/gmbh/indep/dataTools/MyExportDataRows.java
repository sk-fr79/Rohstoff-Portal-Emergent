package panter.gmbh.indep.dataTools;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.exceptions.myException;

/**
 * 2012-08-06: klasse zum erzeugen von INSERT-statements
 * @author martin
 *
 */
public class MyExportDataRows
{
	
	/*
	 * ersatzfelder, die Feldname-Feldwert-Paare muessen datenbankkonform formuliert sein (z.B. "ID_ADRESSE","SEQ_ADRESSE.NEXTVAL")
	 */
	private HashMap<String, String>		hmErsatzFields = new HashMap<String, String>();
	
	private MySqlStatementBuilder 		oSTMT = null;
	
	private String   					cTableName = null;
	
	public MyExportDataRows(String cID_RECORD, String TableName, String cID_MANDANT_target, HashMap<String, String> hm_ErsatzFields) throws myException
	{
		super();
		this.hmErsatzFields.putAll(hm_ErsatzFields);
		this.cTableName = TableName;
		
		MyRECORD  oRec = new MyRECORD("SELECT * FROM "+bibE2.cTO()+"."+this.cTableName+" WHERE ID_"+this.cTableName.substring(3)+"="+cID_RECORD);

		
		this.oSTMT = oRec.get_StatementBuilderFilledWithActualValues(false);
		
		//die sonderfelder austauschen
		for (String cKey: this.hmErsatzFields.keySet())
		{
			oSTMT.put(cKey, this.hmErsatzFields.get(cKey));
		}
		
		//das mandantenfeld austauschen
		oSTMT.put("ID_MANDANT", cID_MANDANT_target);
		
		//jetzt die datenbank-eintraege: '2012-12-31' umwandeln in to_date('2012-12-31','YYYY-MM-DD')
		
		this.oSTMT.ChangeDateFormat(oRec);
		
	}
	
	
	public String get_cSQL_INSERT_STATEMENT() throws myException
	{
		return this.oSTMT.get_CompleteInsertString(this.cTableName);
	}
	
	
	
	
	
	
}
