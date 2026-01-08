package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ADK_LIST_ComponentMap extends E2_ComponentMAP 
{

	public ADK_LIST_ComponentMap() throws myException
	{
		super(new ADK_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(ADK_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(ADK_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(ADRESSKLASSE_DEF.kurzbezeichnung.fn())), new MyE2_String("Kurzbezeichnung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(ADRESSKLASSE_DEF.bezeichnung.fn())), new MyE2_String("Bezeichnung (ausführlich)"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSKLASSE_DEF.ist_kunde.fn())), new MyE2_String("Kunde"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSKLASSE_DEF.ist_lieferant.fn())), new MyE2_String("Lief."));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSKLASSE_DEF.ist_standard.fn())), new MyE2_String("Std."));
		this.add_Component(new ADK_LIST_compShowSelectedColor(oFM.get_(ADK_CONST.SQL_FIELD_NAMES.ID2.fieldAlias())), new MyE2_String("Farbe"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(ADRESSKLASSE_DEF.colorsort.fn())), new MyE2_String("Sortierung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(ADRESSKLASSE_DEF.beschreibung.fn()),true), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(ADRESSKLASSE_DEF.id_adressklasse_def.fn())), new MyE2_String("ID"));
		
		this.get__Comp(ADRESSKLASSE_DEF.beschreibung.fn()).EXT().set_bIsVisibleInList(false);

		this.set_oSubQueryAgent(new ADK_LIST_FORMATING_Agent());
		
		this.set_Factory4Records(new ownRecordFactory());
		
	}

	private class ownRecordFactory extends E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE)	throws myException {
			return new RECORD_ADRESSKLASSE_DEF(cID_MAINTABLE);
		}
		
	}
	
	
}
