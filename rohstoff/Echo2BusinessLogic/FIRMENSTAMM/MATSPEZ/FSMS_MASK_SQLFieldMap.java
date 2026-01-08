package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_MASK_SQLFieldMap extends SQLFieldMAP
{

	
	public FSMS_MASK_SQLFieldMap() throws myException
	{
		super("JT_MAT_SPEZ",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_MAT_SPEZ",":ID_MAT_SPEZ:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_MAT_SPEZ","ID_MAT_SPEZ","ID_MAT_SPEZ",new MyE2_String("ID-Mat-Spez"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MAT_SPEZ.NEXTVAL FROM DUAL",true), false);
		
		/*
		 * muss-felder deklarieren
		 */
		this.get_("ID_ARTIKEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		/*
		 * restrict: id_adresse - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_MAT_SPEZ","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * 2013-04-10: neues feld: SOLL_IST_STATUS 
		 */
		this.get_(_DB.MAT_SPEZ$SOLL_IST_STATUS).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		this.initFields();
	}

}
