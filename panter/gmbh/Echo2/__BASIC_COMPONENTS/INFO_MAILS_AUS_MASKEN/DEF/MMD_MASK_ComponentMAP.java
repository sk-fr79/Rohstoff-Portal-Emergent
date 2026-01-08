package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class MMD_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public MMD_MASK_ComponentMAP() throws myException
	{
		super(new MMD_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		//hier kommen die Felder	
		this.add_Component(new MMD_MASK_ComponentDropDownTabellen(oFM.get_(_DB.MAIL_AUS_MASK$BASISTABELLE)), 	new MyE2_String("BASISTABELLE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MAIL_AUS_MASK$BETREFF),true,600), 				new MyE2_String("BETREFF"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBE_FREIEMAILADRESSE)), 			new MyE2_String("ERLAUBE_FREIEMAILADRESSE"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.MAIL_AUS_MASK$GROOVY_BEDINGUNG),600,8), 			new MyE2_String("GROOVY_BEDINGUNG"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK),true,200,10,false), new MyE2_String("ID_MAIL_AUS_MASK"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.MAIL_AUS_MASK$MAILTEXT),600,8), 					new MyE2_String("MAILTEXT"));
		this.add_Component(new MMD_MASK_ComponentDropDownModulnames(oFM.get_(_DB.MAIL_AUS_MASK$MODULKENNER)), 	new MyE2_String("MODULKENNER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MAIL_AUS_MASK$BUTTONKENNER),true,600,100,false), 	new MyE2_String("BUTTONKENNER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MAIL_AUS_MASK$BUTTONBESCHRIFTUNG),true,600,100,false), 	new MyE2_String("BUTTONBESCHRIFTUNG"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$SIGNATUR_ANHAENGEN)), 				new MyE2_String("SIGNATUR_ANHAENGEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.MAIL_AUS_MASK$SICHERHEITSABFRAGE_VOR_START),true,600,400,false), 		new MyE2_String("SICHERHEITSABFRAGE_VOR_START"));

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBT_BEI_NEUEINGABE)), 			new MyE2_String("ERLAUBT_BEI_NEUEINGABE"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBT_BEI_EDIT)), 					new MyE2_String("ERLAUBT_BEI_EDIT"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBT_BEI_VIEW)), 					new MyE2_String("ERLAUBT_BEI_VIEW"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBT_BEI_UNDEF)), 				new MyE2_String("ERLAUBT_BEI_UNDEF"));
		
		this.add_Component(MMD__CONST.MASK_KEY_DAUGHTER_EMAIL,new MMD_MASK_SimpleDAUGHTER_MailVerteiler(this.get_oSQLFieldMAP(), this), new MyE2_String("Mail-Verteiler"));
		this.add_Component(MMD__CONST.MASK_KEY_DAUGHTER_JASPER,new MMD_MASK_SimpleDAUGHTER_Report(this.get_oSQLFieldMAP(), this), new MyE2_String("Report-Anhänge"));

		this.add_Component(MMD__CONST.MASK_KEY_INFO_BUTTON1,new MMD_MASK_HelpButton(false), 					new MyE2_String("Hilfe zu Betreff"));
		this.add_Component(MMD__CONST.MASK_KEY_INFO_BUTTON2,new MMD_MASK_HelpButton(false), 					new MyE2_String("Hilfe zu eMail-Text"));
		this.add_Component(MMD__CONST.MASK_KEY_INFO_BUTTON3,new MMD_MASK_HelpButton(true), 						new MyE2_String("Hilfe zu Groovy"));
		this.add_Component(MMD__CONST.MASK_KEY_INFO_BUTTON4,new MMD_MASK_HelpButton_Sicherheitsabfrage(), 		new MyE2_String("Hilfe zur Formulierung der Sicherheitsabfrage..."));
		this.add_Component(MMD__CONST.MASK_KEY_INFO_BUTTON5,new MMD_MASK_HelpButton(false), 					new MyE2_String("Hilfe zu Report-Parametern"));

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new MMD_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MMD_MASK_FORMATING_Agent());
	}
	
}
