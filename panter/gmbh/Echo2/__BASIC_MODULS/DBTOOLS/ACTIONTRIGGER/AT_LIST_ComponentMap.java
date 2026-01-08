
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_ACTION_DEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class AT_LIST_ComponentMap extends E2_ComponentMAP {

	public AT_LIST_ComponentMap() throws myException {
		super(new AT_LIST_SqlFieldMAP());

		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		this.add_Component(AT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST, new E2_CheckBoxForList(),	new MyE2_String("?"));
		this.add_Component(AT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(),	new MyE2_String("?"));

		//button-spalte zum erzeugen der trigger
		this.add_Component(new AT_LIST_fieldButtonGenTrigger(oFM.get_(AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_DEF2.hashKey())), new MyE2_String("Aktion"));
		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.trigger_name), true),			new MyE2_String("Name des Triggers"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_NAME_REAL.sqlFieldAlias()), new E2_FontItalic(-2)),			new MyE2_String("Datenbankname"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.table_basename), true),			new MyE2_String("Tabelle"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.table_id), true),				new MyE2_String("ID der Tabelle"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.field_name), true),				new MyE2_String("Feldname"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.validation_class), true),		new MyE2_String("Validierungs-Klasse"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.execution_valid), true),		new MyE2_String("Validierung"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.execution_class), true),		new MyE2_String("Ausführungsklasse"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.execution_code), true),			new MyE2_String("Ausführungscode/-Info"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TRIGGER_ACTION_DEF.id_trigger_action_def), true),	new MyE2_String("ID"));

		this.set_Factory4Records(new factory4Records());
	}

	private class factory4Records extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_TRIGGER_ACTION_DEF(cID_MAINTABLE);
		}

	}

}
