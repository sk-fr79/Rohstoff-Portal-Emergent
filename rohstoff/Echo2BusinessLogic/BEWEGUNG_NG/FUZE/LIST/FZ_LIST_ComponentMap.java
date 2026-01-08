package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW2;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_comp_ContainerFuhreJump;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_comp_VisualizeAtomStart;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_comp_VisualizeAtomZiel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP.FZ_LIST_comp_anzeigeVectorTypAndCount;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SPALTEN_NAMEN;

public class FZ_LIST_ComponentMap extends E2_ComponentMAP  {

	public FZ_LIST_ComponentMap(E2_NavigationList naviList) throws myException	{
		super(new FZ_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		
		this.add_Component(FZ_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FZ_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,		new E2_ButtonListMarker(),new MyE2_String("?"));
		
		//2016-11-15: neue spalte mit einem info-button
		MyE2IF__Component bt = null;
//		if(ENUM_MANDANT_DECISION.USE_NEW_BEWEGUNG_INFO_BUTTON.is_YES()) {
		bt = new FZ_LIST_bt_record_infos_ng();
//		}else {
//			bt = new FZ_LIST_bt_record_infos();
//		}

		this.add_Component(SPALTEN_NAMEN.BUTTON_VIEW_RECORDSTRUCTURE.hashKey(),	bt	,new MyE2_String("Info"));

		
		this.add_Component(new FZ_LIST_comp_anzeigeVectorTypAndCount(oFM.get_(BEWEGUNG_VEKTOR.variante),naviList), new MyE2_String("Typ"));
		this.get__Comp(BEWEGUNG_VEKTOR.variante.fieldName()).EXT().set_oColExtent(new Extent(130));
		
		this.add_Component(new ownLabelInRow(oFM.get_(FZ__CONST.FIELDS.STARTADRESSE_NAME_ORT.alias()),200), new MyE2_String("Startstation"));
		this.add_Component(new ownLabelInRow(oFM.get_(FZ__CONST.FIELDS.STARTBESITZER_NAME_ORT.alias()),200), new MyE2_String("Besitzer am Start"));
		
		this.add_Component(new ownLabelInRow(oFM.get_(FZ__CONST.FIELDS.ZIELADRESSE_NAME_ORT.alias()),200), new MyE2_String("Zielstation"));
		this.add_Component(new ownLabelInRow(oFM.get_(FZ__CONST.FIELDS.ZIELBESITZER_NAME_ORT.alias()),200), new MyE2_String("Besitzer am Ziel"));
		
		this.add_Component(new ownLabelInRow(oFM.get_(ARTIKEL.anr1.fieldName()),50), new MyE2_String("ANR1"));
		this.add_Component(new ownLabelInRow(oFM.get_(ARTIKEL.artbez1.fieldName()),250), new MyE2_String("Artbez1"));
		
		this.add_Component(new FZ_LIST_comp_VisualizeAtomStart(oFM.get_(FZ__CONST.SPALTEN_NAMEN.STARTORT.hashKey())), FZ__CONST.SPALTEN_NAMEN.STARTORT.userInfo());
		this.add_Component(new FZ_LIST_comp_VisualizeAtomZiel(oFM.get_(FZ__CONST.SPALTEN_NAMEN.ZIELORT.hashKey())), FZ__CONST.SPALTEN_NAMEN.ZIELORT.userInfo());
		
		this.add_Component(new FZ_LIST_comp_ContainerFuhreJump(oFM.get_("F_ID_VPOS_TPA_FUHRE"), naviList), new MyE2_String("Fuhre"));
		this.add_Component(new ownLabelInRow(oFM.get_("B_ID_BEWEGUNG"),150), new MyE2_String("ID (Bewegung)"));
		this.add_Component(new ownLabelInRow(oFM.get_("ID_BEWEGUNG_VEKTOR"),150), new MyE2_String("ID"));

		this.set_oSubQueryAgent(new FZ_LIST_FORMATING_Agent_showDeleted());
		this.add_oSubQueryAgent(new FZ_LIST_FORMATING_Agent_showStorno());
		
		this.set_Factory4Records(new ownRecordFactory());
		this.set_DeletedSetting_newVersion(true);
		
		
//		DEBUG.System_println("Alle keys ....");
//		DEBUG.System_print(this.keySet());
//		DEBUG.System_println("Ende alle keys ....");
		
		
	}
	
	
	private class ownLabelInRow extends MyE2_DB_Label_INROW2 {

		public ownLabelInRow(SQLField osqlField, int p_width) throws myException {
			super(osqlField);
			super.init(new E2_FontPlain(), p_width, true);
		}
		
	}
	
	
	private class ownRecordFactory extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String id_vector) throws myException 	{
			return new RECORD_BEWEGUNG_VEKTOR_SPEC(new RECORD_BEWEGUNG_VEKTOR(id_vector));
		}
	}
	
	
	public RECORD_BEWEGUNG_VEKTOR_SPEC get_record_bewegung_vektor() {
		return (RECORD_BEWEGUNG_VEKTOR_SPEC) this.get_Record4MainTable();
	}
	

	//2014-04-04: neue version der copy-struktur mit statischer hilfsmethode
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_ComponentMAP oRueck = new E2_ComponentMAP(this.get_oSQLFieldMAP());
		E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
		
		return oRueck;
	}

}
