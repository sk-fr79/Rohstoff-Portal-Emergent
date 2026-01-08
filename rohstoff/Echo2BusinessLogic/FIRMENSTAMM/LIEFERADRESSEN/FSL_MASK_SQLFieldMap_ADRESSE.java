package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSL_MASK_SQLFieldMap_ADRESSE extends SQLFieldMAP
{

	
	public FSL_MASK_SQLFieldMap_ADRESSE() throws myException
	{
		super("JT_ADRESSE",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);
	
		/*
		 * restrict: adresstyp = mitarbeiter (3)
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE","ADRESSTYP","ADRESSTYP",new MyE2_String("Adresstyp"),""+myCONST.ADRESSTYP_LIEFERADRESSE,bibE2.get_CurrSession()), false);
		
		/*
		 * muss-felder deklarieren
		 */
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STRASSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PLZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("AKTIV").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("AKTIV").set_cDefaultValueFormated("Y");
		
		
		this.initFields();
	}

}
