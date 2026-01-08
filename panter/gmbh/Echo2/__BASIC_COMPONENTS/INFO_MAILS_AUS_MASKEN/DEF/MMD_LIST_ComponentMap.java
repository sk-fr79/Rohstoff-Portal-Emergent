package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MMD_LIST_ComponentMap extends E2_ComponentMAP 
{

	public MMD_LIST_ComponentMap() throws myException
	{
		super(new MMD_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(MMD_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MMD_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$MODULKENNER)), 				new MyE2_String("Kennung des Maskenmoduls"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$BUTTONKENNER)), 				new MyE2_String("Berechtigungskenner des Knopfs"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$BUTTONBESCHRIFTUNG)), 		new MyE2_String("Beschriftung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$BASISTABELLE)), 				new MyE2_String("Basistabelle"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$BETREFF)), 					new MyE2_String("Betreff"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$MAILTEXT)), 					new MyE2_String("Text"),false,true);
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBE_FREIEMAILADRESSE)), 		new MyE2_String("Freie Mail erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$SIGNATUR_ANHAENGEN)), 			new MyE2_String("Signatur"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$GROOVY_BEDINGUNG)), 			new MyE2_String("Groovy-Bedingung"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.MAIL_AUS_MASK$ERLAUBT_BEI_NEUEINGABE)), 		new MyE2_String("Aufruf bei Neueingabe"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$SICHERHEITSABFRAGE_VOR_START)),new MyE2_String("Sicherheitsabfrage"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK)), 			new MyE2_String("ID"));
		
		this.set_oSubQueryAgent(new MMD_LIST_FORMATING_Agent());
	}

}
