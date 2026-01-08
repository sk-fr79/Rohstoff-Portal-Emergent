package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.MS___AUFGABEN_LIST_BasicModule_Inlay;

public class MESSAGE_LIST_ComponentMap extends E2_ComponentMAP 
{

	public MESSAGE_LIST_ComponentMap() throws myException
	{
		super(new MESSAGE_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(MESSAGE_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MESSAGE_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		MyE2_Row_EveryTimeEnabled oRowButtons = new MyE2_Row_EveryTimeEnabled();
		this.add_Component(MESSAGE_CONST.MESSAGE_LIST_ROW_BUTTONS, oRowButtons,new MyE2_String("Link"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_NACHRICHT")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_NACHRICHT_PARENT")), new MyE2_String("Verweist auf"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("USER_ADRESSE")), new MyE2_String("An"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("USER_SENDER")), new MyE2_String("Von"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TITEL")), new MyE2_String("Betreff"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("NACHRICHT"),450,6), new MyE2_String("Nachricht"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KATEGORIE")), new MyE2_String("Kategorie"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("AKTIV_AB")), new MyE2_String("Anzeigen am"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SOFORTANZEIGE")), new MyE2_String("Direktanzeige"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SEND_EMAIL")), new MyE2_String("Email senden"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EMAIL_SENT")), new MyE2_String("Email gesendet "));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("BESTAETIGT")), new MyE2_String("Bestätigt"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("GELOESCHT")), new MyE2_String("Gelöscht"));
	
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		this.get_hmRealComponents().get("ID_NACHRICHT").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("ID_NACHRICHT_PARENT").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("BESTAETIGT").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("TITEL").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("NACHRICHT").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("AKTIV_AB").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("SOFORTANZEIGE").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("USER_ADRESSE").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("USER_SENDER").EXT().set_oLayout_ListElement(layout);
		
		this.get__Comp("ID_NACHRICHT").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_NACHRICHT_PARENT").EXT().set_bIsVisibleInList(false);
		
		this.set_oSubQueryAgent(new MESSAGE_LIST_FORMATING_Agent());
	}

	
	
}
