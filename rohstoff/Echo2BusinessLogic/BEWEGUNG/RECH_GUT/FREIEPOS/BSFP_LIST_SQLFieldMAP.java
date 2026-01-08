package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG;

public class BSFP_LIST_SQLFieldMAP extends Project_SQLFieldMAP
{

	/*
	 * im standalone-betrieb (cID_VPOS_TPA_FUHRE=null) werden alle, die noch keinen kopf-satz habe angezeigt,
	 * im nicht-standalone-betrieb tauchen nur positionen auf, die zu einer fuhre gehoeren
	 */
	public BSFP_LIST_SQLFieldMAP(String cID_VPOS_TPA_FUHRE) throws myException
	{
		super("JT_VPOS_RG", "ID_VPOS_TPA_FUHRE_ZUGEORD", false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG.ANR1,'--')||'-'||  NVL(JT_VPOS_RG.ANR2,'--')","ANR1_ANR2",new MyE2_String("ANR1-2"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG.EINHEIT_PREIS_KURZ,'--')||' ('||TO_CHAR(  NVL(JT_VPOS_RG.MENGENDIVISOR,0))||')'","EINH_PREIS",new MyE2_String("Pr.Einh."),bibE2.get_CurrSession()), false);
		
		this.add_ConnectedLookUpTable("JT_ADRESSE","NAME1:NAME2:ORT","A_"," JT_VPOS_RG.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE (+)");
		this.add_SQLField(new SQLField("  SUBSTR(NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||'-'||  NVL(JT_ADRESSE.ORT,'-'),1,30) ||'... ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' ","ADRESSE",new MyE2_String("Adresse"),bibE2.get_CurrSession()), false);
		
		//Aenderung 2010-11-25: pauschalabzuege separat anzeigen
		String cSubqueryAbzug = "SELECT SUM(ABZUG) FROM "+bibE2.cTO()+".JT_VPOS_RG_ABZUG WHERE ABZUGTYP='"+BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT+"' AND JT_VPOS_RG_ABZUG.ID_VPOS_RG=JT_VPOS_RG.ID_VPOS_RG";
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(("+cSubqueryAbzug+"),'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"C_ABZUG_PAUSCHAL", new MyE2_String("Abzug Pauschal EK"),bibE2.get_CurrSession()),false);

		//Aenderung 2010-11-25: mengen ohne nachkomma anzeigen
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANZAHL,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"C_ANZAHL", new MyE2_String("Menge"),bibE2.get_CurrSession()),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANZAHL_ABZUG,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"C_ANZAHL_ABZUG", new MyE2_String("Menge-Abzug"),bibE2.get_CurrSession()),false);

		//2011-04-07: adress-popup-button mit infobutton
		this.add_SQLField(new SQLField(" JT_VPOS_RG.ID_ADRESSE",	"INFO_ID_ADRESSE",		new MyE2_String("ID-Adresse"),bibE2.get_CurrSession()),false);

		
		//2011-10-13: neue felder in liste: geaendert von und erzeugt von
		this.add_SQLField(new SQLField(" NVL(JT_VPOS_RG.GEAENDERT_VON,'-')","GEAENDERT",		new MyE2_String("geaendert"),bibE2.get_CurrSession()),false);
		this.add_SQLField(new SQLField(" NVL(JT_VPOS_RG.ERZEUGT_VON,'-')",	"ERZEUGT",		    new MyE2_String("erzeugt"),bibE2.get_CurrSession()),false);
		
		
		if (cID_VPOS_TPA_FUHRE == null)   // bedeutet standalonebetrieb
		{
			this.add_SQLField(new SQLField(	"JT_VPOS_RG","ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",new MyE2_String("ID_VPOS_TPA_FUHRE_ZUGEORD"),true,bibE2.get_CurrSession()), false);
			this.add_BEDINGUNG_STATIC("JT_VPOS_RG.ID_VKOPF_RG IS NULL");
		}
		else
		{
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_RG","ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",new MyE2_String("ID_VPOS_TPA_FUHRE_ZUGEORD"),cID_VPOS_TPA_FUHRE,bibE2.get_CurrSession()), false);
		}
		
		this.add_ORDER_SEGMENT("JT_VPOS_RG.ID_VPOS_RG DESC");
		this.initFields();
	}
}
