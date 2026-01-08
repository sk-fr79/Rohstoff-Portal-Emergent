package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.RULES.FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND;


public class FKR_FB_MASK_ComponentMAP_FILTER extends E2_ComponentMAP 
{

	public FKR_FB_MASK_ComponentMAP_FILTER(FKR_FB_MASK_SQLFieldMAP_FILTER oFM,FKR_FB_MASK_BasicModuleContainer callingMaskContainer) throws myException
	{
		super();
		
		this.set_oSQLFieldMAP(oFM);
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.FILTER$ID_FILTER),true,300,10,false), new MyE2_String("ID"));
		//this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.FILTER$BESCHREIBUNG),true,300,10,false), new MyE2_String("Filter"));
		
		//hier die fulldaughter der "filter-and" - Tabelle
		this.add_Component(FKR_FB__CONST.FELD_FULL_DAUGHTER_FILTER_AND, new FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND(oFM.get_oSQLFieldPKMainTable(),callingMaskContainer),new MyE2_String("Filterliste"));
		
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
