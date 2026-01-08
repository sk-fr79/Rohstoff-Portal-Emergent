package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_SelectField_FileType;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_SETTINGS;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_ComponentMap extends E2_ComponentMAP 
{

	public SCAN_LIST_ComponentMap() throws myException
	{
		super(new SCAN_LIST_SqlFieldMAP());

		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		MyE2_DB_CheckBox loopChckBox = new MyE2_DB_CheckBox(oFM.get_(SCANNER_SETTINGS.loop_scan));
		loopChckBox.EXT().set_oLayout_ListElement(LayoutDataFactory.get_GridLayout(
																					E2_INSETS.I(0,0,0,0), 
																					new E2_ColorBase(),
																					new Alignment(Alignment.CENTER, Alignment.CENTER), 
																					1)
																					);
		
		this.add_Component(SCAN_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,							new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(SCAN_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,						new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.scanner_name)),							new MyE2_String("Name"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.filetype)), 								new MyE2_String("Filetype"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.beschreibung),true), 						new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_CheckBox(	oFM.get_(SCANNER_SETTINGS.loop_scan)), 								new MyE2_String("Schreifenscan"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.loop_timeout_seconds),true), 				new MyE2_String("Schleifen-Wartezeit"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.dpi)), 									new MyE2_String("Auflösung"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.module_kenner)), 							new MyE2_String("Module kenner"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.programm_kenner)),						new MyE2_String("Programm kenner"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.scanner_aufruf1),true), 					new MyE2_String("Scanner aufruf 1"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.scanner_aufruf2),true), 					new MyE2_String("Scanner aufruf 2"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.scanner_aufruf3),true), 					new MyE2_String("Scanner aufruf 3"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.scanner_aufruf4),true), 					new MyE2_String("Scanner aufruf 4"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SCANNER_SETTINGS.id_scanner_settings),true), 				new MyE2_String("Id"), false, true);
        this.add_Component(SCAN_CONST.ADDITIONNAL_COMPONENTS.BT_TEST_SCANNER.name(), 	new SCAN_LIST_BT_test_scanner(),new MyE2_String("Test"));
		this.set_oSubQueryAgent(new SCAN_LIST_FORMATING_Agent());

		this.set_Factory4Records(new factory4Records());
	}


	private class factory4Records extends E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_SCANNER_SETTINGS(cID_MAINTABLE);
		}

	}


}
