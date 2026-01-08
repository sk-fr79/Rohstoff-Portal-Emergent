package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_INBOX_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public MAIL_INBOX_MASK_ComponentMAP(MAIL_INBOX_MASK_BasicModuleContainer oModulContainerMask) throws myException
	{
		super(new MAIL_INBOX_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
	
			
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_EMAIL_INBOX"),true,80,10,true), new MyE2_String("Inbox-ID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAIL_FROM"),true,500,10,true), new MyE2_String("Absender"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAIL_TO"),true,300,10,true), new MyE2_String("Empfänger"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAIL_CC"),true,300,10,true), new MyE2_String("CC"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAIL_ACCOUNT"),true,300,10,true), new MyE2_String("Mailaccount"));
		
		MyE2_DB_TextField oTF =  new MyE2_DB_TextField(oFM.get_("DATE_RECEIVED"),true,80,10,true) ;
		this.add_Component(oTF, new MyE2_String("Zustelldatum"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("DATE_SEND"),true,80,10,true), new MyE2_String("Sendedatum"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("SUBJECT"),true,300,10,true), new MyE2_String("Betreff"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("MESSAGE_TEXT"),500,20,true,new E2_FontPlain(0)), new MyE2_String("Text"));

		
		// Die zugeordnete Adresse
		MAIL_INBOX_DB_Search_Adress oAdr = new MAIL_INBOX_DB_Search_Adress(oFM.get_("ID_ADRESSE_ZUGEORDNET"),oModulContainerMask);
		oAdr.get_oTextForAnzeige().setWidth(new Extent(300));
		oAdr.get_oTextForAnzeige().setFont(new E2_FontPlain());
		this.add_Component(oAdr,new MyE2_String("ID-Adresse"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ADDRESS_INFO"),true,500,10,true), new MyE2_String("Zugeordnete Adresse"));
		this.add_Component(MAIL_INBOX_Const.BUTTON_DISCONNECT_ADRESSE, new MAIL_INBOX_BT_DISCONNECT_ADDRESS(oModulContainerMask), new MyE2_String("Adresszuordnung löschen"));
		this.add_Component(MAIL_INBOX_Const.BUTTON_CONNECT_ADRESSE, new MAIL_INBOX_BT_CONNECT_ADDRESS(oModulContainerMask), new MyE2_String("Adresszuordnung automatisch ermitteln"));
		
		this.add_Component(MAIL_INBOX_Const.BUTTON_SET_CONFIRMATION, new MAIL_INBOX_BT_SET_CONFIRMATION(), new MyE2_String("Mail bestätigen"));
		this.add_Component(MAIL_INBOX_Const.BUTTON_CLEAR_CONFIRMATION, new MAIL_INBOX_BT_CLEAR_CONFIRMATION(), new MyE2_String("Bestätigung löschen"));

		this.get__Comp(MAIL_INBOX_Const.BUTTON_SET_CONFIRMATION).EXT().set_bEnabled_For_Edit(false);
		this.get__Comp(MAIL_INBOX_Const.BUTTON_CLEAR_CONFIRMATION).EXT().set_bEnabled_For_Edit(false);
		
		
		
		//User und Datum, wann gelesen.	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("GELESEN_AM"),true,80), new MyE2_String("Bestätigt am"));
		
		DB_Component_USER_DROPDOWN oSelectID_USER_GELESEN = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER_GELESEN"));
		oSelectID_USER_GELESEN.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_DB_SelectField oSelectUserFinal = (MyE2_DB_SelectField)MAIL_INBOX_MASK_ComponentMAP.this.get__Comp("ID_USER_GELESEN");
				E2_ComponentMAP oMAP_Own = oSelectUserFinal.EXT().get_oComponentMAP();
				if (bibALL.isEmpty(oSelectUserFinal.get_ActualWert()))
				{
					((MyE2_DB_TextField)oMAP_Own.get__Comp("GELESEN_AM")).setText("");
				}
				else
				{
					((MyE2_DB_TextField)oMAP_Own.get__Comp("GELESEN_AM")).setText(bibALL.get_cDateNOW());
				}
			}
		});
		
		this.add_Component(oSelectID_USER_GELESEN, new MyE2_String("Bestätigt von:"));
	    this.add_Component(new MyE2_DB_TextField(oFM.get_("VERARBEITET_VON_INFO"),true,500,10,true), new MyE2_String("Bestätigt von:"));
	    
	    this.get__Comp("ID_USER_GELESEN").EXT().set_bDisabledFromBasic(true);
	    this.get__Comp("GELESEN_AM").EXT().set_bDisabledFromBasic(true);

//		String sTablename = RECORD_EMAIL_INBOX.TABLENAME.substring(3);
//		String sID = this.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
//		this.add_Component(	MAIL_INBOX_Const.DAUGHTER_PAGE_ARCHIVE_CONTENTS, 
//							new E2_PopUp_For_UP_AND_DOWN_FILES(sTablename,sID,oModulContainerMask.get_MODUL_IDENTIFIER(),true), 
//							new MyE2_String("Angehängte Dateien"));
		this.add_Component( MAIL_INBOX_Const.DAUGHTER_PAGE_ARCHIVE_CONTENTS,new MyE2_Column_IMMER_ENABLED(),new MyE2_String("Angehängte Dateien"),true);
		
	    
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("MAIL_UID"),true,200,10,false), new MyE2_String("MAIL_UID"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("MESSAGE_ID"),500,5), new MyE2_String("MESSAGE_ID"));

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new MAIL_INBOX_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MAIL_INBOX_MASK_FORMATING_Agent());
	}
	
	
	
	
	/**
	 * Setzt die Stati der Buttons die zur Verlinkung der Mail zu den Adressen nötig ist
	 * @author manfred
	 * @throws myException 
	 * @date   14.03.2013
	 */
	public void setButtonStatus_Of_MaskButtons( ) throws myException{
		
		boolean bIsAddressSet = false;
		boolean bIsConfirmed = false;

		// Prüft ob die Mail bestätigt wurde
		MyE2_DB_SelectField oSelectUserFinal = (MyE2_DB_SelectField)this.get__Comp("ID_USER_GELESEN");
		if (bibALL.isEmpty(oSelectUserFinal.get_ActualWert()))
		{
			bIsConfirmed = false;
		} else {
			bIsConfirmed = true;
		}
			
		
		// prüft ob eine Adresse verknüpft wurd
		MAIL_INBOX_DB_Search_Adress oAdresse = (MAIL_INBOX_DB_Search_Adress)this.get__Comp("ID_ADRESSE_ZUGEORDNET");
		if (bibALL.isEmpty(oAdresse.get_cActualDBValueFormated())){
			bIsAddressSet = false;
		} else {
			bIsAddressSet = true;
		}
		
		// setzen der Bestätigungs-Knöpfe
		this.set_ActiveADHOC(MAIL_INBOX_Const.BUTTON_CLEAR_CONFIRMATION, bIsConfirmed, false);
		this.set_ActiveADHOC(MAIL_INBOX_Const.BUTTON_SET_CONFIRMATION, !bIsConfirmed, false);
		
		// setzen der Adress-Knöpfe
		this.set_ActiveADHOC("ID_ADRESSE_ZUGEORDNET", !bIsAddressSet && !bIsConfirmed, false);
		this.set_ActiveADHOC(MAIL_INBOX_Const.BUTTON_CONNECT_ADRESSE, !bIsAddressSet && !bIsConfirmed, false);
		this.set_ActiveADHOC(MAIL_INBOX_Const.BUTTON_DISCONNECT_ADRESSE, bIsAddressSet && !bIsConfirmed, false);
		
		
		
	} 

	
}
