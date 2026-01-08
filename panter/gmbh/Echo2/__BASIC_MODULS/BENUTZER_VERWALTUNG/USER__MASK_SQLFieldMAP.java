package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class USER__MASK_SQLFieldMAP extends SQLFieldMAP 
{

	public USER__MASK_SQLFieldMAP() throws myException 
	{
		super("JD_USER", bibE2.get_CurrSession());
		
		
		String cAusnahmen = ":"+"ID_USER:GEAENDERT_VON:LETZTE_AENDERUNG:";
		
		this.addCompleteTable_FIELDLIST("JD_USER",cAusnahmen,false,true, "");
		
		String cMain_Table = "JD_USER";
		String cID_Name = "ID_USER";
		String cSeq_Name = "SEQ_USER";
		
		this.add_SQLField(new SQLFieldForPrimaryKey(cMain_Table,cID_Name,cID_Name,new MyE2_String("ID"),bibE2.get_CurrSession(),
				"SELECT "+bibE2.cTO()+"."+cSeq_Name+".NEXTVAL FROM DUAL",true), false);
	
		
		//2011-12-06: weiteres feld fuer die unterschrift
		this.add_SQLField(new SQLField("JD_USER.ID_USER", "ID_USER_SIGNATUR", new MyE2_String("Signatur"), bibE2.get_CurrSession()), true);
		
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AKTIV").set_cDefaultValueFormated("Y");
		this.get_("HAT_FAHRPLAN_BUTTON").set_cDefaultValueFormated("Y");
		this.get_("ID_SPRACHE").set_cDefaultValueFormated("");
		this.get_("ID_USER").set_cDefaultValueFormated("");
		this.get_("ID_USERGROUP").set_cDefaultValueFormated("");
		this.get_("IST_FAHRER").set_cDefaultValueFormated("N");
		this.get_("IST_SUPERVISOR").set_cDefaultValueFormated("N");
		this.get_("TEXTCOLLECTOR").set_cDefaultValueFormated("N");
		this.get_("TODO_SUPERVISOR").set_cDefaultValueFormated("0");

		this.get_("LAUFZEIT_SESSION").set_cDefaultValueFormated("6000");
		this.get_("EIGENDEF_SCHRIFTGROESSE").set_cDefaultValueFormated("12");
		
		this.get_("EMAIL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_SPRACHE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KUERZEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MAIL_SIGNATUR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PASSWORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.initFields();
	}

}
