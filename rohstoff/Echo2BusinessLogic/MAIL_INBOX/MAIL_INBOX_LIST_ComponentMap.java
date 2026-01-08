package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea_INROW;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_ComponentMap extends E2_ComponentMAP 
{   
	

	public MAIL_INBOX_LIST_ComponentMap(String MODUL_IDENTIFIER) throws myException
	{
		super(new MAIL_INBOX_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(MAIL_INBOX_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MAIL_INBOX_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));


		// Spalte für die Anhänge
		MyE2_Row_EveryTimeEnabled 		oRowAnhaenge = new MyE2_Row_EveryTimeEnabled();
		this.add_Component(MAIL_INBOX_Const.ROW_SHOW_ANHANG_LISTE,oRowAnhaenge, new MyE2_String("Anhänge"),true);
		
				
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_EMAIL_INBOX")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATE_RECEIVED")), new MyE2_String("Zustelldatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MAIL_FROM")), new MyE2_String("Absender"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUBJECT")), new MyE2_String("Betreff"));
		MyE2_DB_TextArea oMessageText = new MyE2_DB_TextArea(oFM.get_("MESSAGE_TEXT"));
		oMessageText.set_iRows(3);
		oMessageText.set_WitdhExtent(new Extent(350));
		oMessageText.EXT().set_STYLE_FACTORY(new StyleFactory_TextArea_INROW());
		this.add_Component(oMessageText, new MyE2_String("Text"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE_ZUGEORDNET")), new MyE2_String("ID Adresse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Zugeordnete Adresse"));
		

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MAIL_TO")), new MyE2_String("Empfänger"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MAIL_CC")), new MyE2_String("CC"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATE_SEND")), new MyE2_String("Sendedatum"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VERARBEITET_VON_INFO")), new MyE2_String("Bestätigt von"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GELESEN_AM")), new MyE2_String("Bestätigt am"));

		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MAIL_ACCOUNT")), new MyE2_String("Mailaccount"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("MAIL_FOLDER")), new MyE2_String("Postfach"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("MAIL_HOST")), new MyE2_String("Mailhost"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("MAIL_UID")), new MyE2_String("UID"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("MESSAGE_ID")), new MyE2_String("MailID"));
		
		this.set_oSubQueryAgent(new MAIL_INBOX_LIST_FORMATING_Agent());
	}

}
