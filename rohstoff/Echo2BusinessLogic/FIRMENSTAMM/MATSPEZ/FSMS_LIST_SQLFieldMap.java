package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_LIST_SQLFieldMap extends SQLFieldMAP
{

	
	public FSMS_LIST_SQLFieldMap() throws myException
	{
		super("JT_MAT_SPEZ",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_MAT_SPEZ",":ID_MAT_SPEZ:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_MAT_SPEZ","ID_MAT_SPEZ","ID_MAT_SPEZ",new MyE2_String("ID-Mat-Spez"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MAT_SPEZ.NEXTVAL FROM DUAL",true), false);
	
	
		this.addCompleteTable_FIELDLIST("JT_ARTIKEL",":ANR1:ARTBEZ1:",true,true, "A_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ARTIKEL","ID_ARTIKEL","A_ID_ARTIKEL",new MyE2_String("ID-Artikel"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ARTIKEL.NEXTVAL FROM DUAL",true), false);

		/*
		 * connect-felder
		 */
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_MAT_SPEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL"));

		
		/*
		 * restrict: id_MAT_SPEZ_basis - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_MAT_SPEZ","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		
		/*
		 * 2013-04-10: neues feld: SOLL_IST_STATUS 
		 */
		this.get_(_DB.MAT_SPEZ$SOLL_IST_STATUS).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
