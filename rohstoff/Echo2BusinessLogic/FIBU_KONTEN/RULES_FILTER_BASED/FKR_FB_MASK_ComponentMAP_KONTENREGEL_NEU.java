package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class FKR_FB_MASK_ComponentMAP_KONTENREGEL_NEU extends E2_ComponentMAP 
{

	public FKR_FB_MASK_ComponentMAP_KONTENREGEL_NEU(FKR_FB_MASK_SQLFieldMAP_KONTENREGEL_NEU  oFM_KR) throws myException
	{
		super();
		
		this.set_oSQLFieldMAP(oFM_KR);
		
		E2_DropDownSettingsNew  oDDFibuKonto = 	new E2_DropDownSettingsNew("SELECT CONCAT(CONCAT(KONTO, ' '), BESCHREIBUNG), ID_FIBU_KONTO FROM JT_FIBU_KONTO ORDER BY KONTO", true, true);
		MyE2_DB_SelectField 	sf_FIBU_KONTO = new MyE2_DB_SelectField(
										oFM_KR.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO), 
										oDDFibuKonto.getDD(), 
										false, 
										new Extent(300));

		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM_KR.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU),true,300,10,false), new MyE2_String("ID"));
		this.add_Component(sf_FIBU_KONTO, new MyE2_String("FIBU-Konto"));
		this.add_Component(new MyE2_DB_TextField(oFM_KR.get_(_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR),true,300,10,false), new MyE2_String("Kommentar"));
		this.add_Component(new MyE2_DB_CheckBox(oFM_KR.get_(_DB.FIBU_KONTENREGEL_NEU$AKTIV)), new MyE2_String("Aktiv?"));
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new FKR_FB_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FKR_FB_MASK_FORMATING_Agent());
	}
	
}
