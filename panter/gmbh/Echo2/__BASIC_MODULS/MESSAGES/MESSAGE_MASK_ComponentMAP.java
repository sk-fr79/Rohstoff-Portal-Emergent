package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class MESSAGE_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public MESSAGE_MASK_ComponentMAP() throws myException
	{
		super(new MESSAGE_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
//		String[] cFieldsStandard = {"BESTAETIGT","ERZEUGT_AM","ERZEUGT_VON","GEAENDERT_VON","ID_MANDANT","ID_NACHRICHT","ID_NACHRICHT_PARENT","ID_USER","ID_USER_SENDER","LETZTE_AENDERUNG","NACHRICHT","TITEL"}; 
//		String[] cFieldsInfolabs = {"BESTAETIGT","ERZEUGT_AM","ERZEUGT_VON","GEAENDERT_VON","ID_MANDANT","ID_NACHRICHT","ID_NACHRICHT_PARENT","ID_USER","ID_USER_SENDER","LETZTE_AENDERUNG","NACHRICHT","TITEL"}; 
//
//		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_NACHRICHT_WICHTIGKEIT","BESCHREIBUNG","ID_MESSAGE_WICHTIGKEIT","ISTSTANDARD",true);
//		
//		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("BESTAETIGT")), new MyE2_String("Bestätigt"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_NACHRICHT"),true,200,10,false), new MyE2_String("ID Nachricht"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_NACHRICHT_PARENT"),true,200,10,false), new MyE2_String("ID Ursprungsnachricht"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_USER"),true,200,10,false), new MyE2_String("ID Empfänger"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_USER_SENDER"),true,200,10,false), new MyE2_String("ID Sender"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("NACHRICHT"),500,8), new MyE2_String("Nachricht"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TITEL"),true,500,100,false), new MyE2_String("Titel"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_GRUPPE"),true,200,10,false), new MyE2_String("SendeID"));
		

		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_MESSAGE_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MESSAGE")), new MyE2_String("ID"));

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
		this.set_oMAPSettingAgent(new MESSAGE_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MESSAGE_MASK_FORMATING_Agent());
	}
	
}
