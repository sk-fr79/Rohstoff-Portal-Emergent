package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_MASK_SQLFieldMap_ADRESSE extends SQLFieldMAP
{

	
	public FS_MASK_SQLFieldMap_ADRESSE() throws myException
	{
		super("JT_ADRESSE",bibE2.get_CurrSession());
		
		//2014-11-27: geaendert von und am auch in der map
//		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",":ID_ADRESSE:ADRESSTYP:ID_MANDANT:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE.NEXTVAL FROM DUAL",true), false);
		//GEAENDERT_VON:LETZTE_AENDERUNG muessen als not writeble definiert werden
		this.get(_DB.ADRESSE$GEAENDERT_VON).set_bWriteable(false);
		this.get(_DB.ADRESSE$LETZTE_AENDERUNG).set_bWriteable(false);
		this.get(_DB.ADRESSE$ERZEUGT_VON).set_bWriteable(false);
		this.get(_DB.ADRESSE$ERZEUGT_AM).set_bWriteable(false);
		
		
		/*
		 * restrict: adresstyp = 1
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE","ADRESSTYP","ADRESSTYP",new MyE2_String("Adresstyp"),""+myCONST.ADRESSTYP_FIRMENINFO,bibE2.get_CurrSession()), false);
		
		/*
		 * muss-felder deklarieren
		 */
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STRASSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PLZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_SPRACHE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE_POTENTIAL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("AKTIV").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("ID_WAEHRUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		//NEU_09
		this.get_("AKTIV").set_cDefaultValueFormated("Y");
		this.get_("ERSTKONTAKT").set_cDefaultValueFormated(bibALL.get_cDateNOW());

		//2012-08-03: rechnungen und gutschriften sperren als standard
		this.get_("WARENEINGANG_SPERREN").set_cDefaultValueFormated("Y");
		this.get_("WARENAUSGANG_SPERREN").set_cDefaultValueFormated("Y");
		
		
		this.initFields();
	}

}
