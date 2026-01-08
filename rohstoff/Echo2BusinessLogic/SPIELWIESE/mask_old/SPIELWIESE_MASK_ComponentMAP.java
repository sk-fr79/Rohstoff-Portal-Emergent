package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask_old;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class SPIELWIESE_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public SPIELWIESE_MASK_ComponentMAP() throws myException
	{
		super(new SPIELWIESE_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
		//String[] cFieldsStandard = {"AKTIV","ID_FIBU_KONTENREGEL_NEU","ID_FIBU_KONTO","ID_FILTER","KOMMENTAR"}; 
		//String[] cFieldsInfolabs = {}; //"AKTIV","ID_FIBU_KONTENREGEL_NEU","ID_FIBU_KONTO","ID_FILTER","KOMMENTAR"}; 

		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_FIBU_KONTENREGEL_NEU_WICHTIGKEIT","BESCHREIBUNG","ID_SPIELWIESE_WICHTIGKEIT","ISTSTANDARD",true);
		
		//MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("AKTIV"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_FIBU_KONTENREGEL_NEU"),true,200,10,false), new MyE2_String("ID_FIBU_KONTENREGEL_NEU"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_FIBU_KONTO"),true,200,10,false), new MyE2_String("ID_FIBU_KONTO"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_FILTER"),true,200,10,false), new MyE2_String("ID_FILTER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KOMMENTAR"),true,500,100,false), new MyE2_String("KOMMENTAR"));
		

		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_SPIELWIESE_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_SPIELWIESE")), new MyE2_String("ID"));

		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
		
		((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
		((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);

		((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
		*/	

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new SPIELWIESE_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new SPIELWIESE_MASK_FORMATING_Agent());
	}
	
}
