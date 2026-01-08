package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_NG;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTEN_LIEFERBED_ADR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public class FSK_ListComponentMAP extends E2_ComponentMAP_NG<RECORD_KOSTEN_LIEFERBED_ADR> {

	private FSK_DD_Artikel  					m_oArtikelDropdown = null;
	private FSK_DD_AdressenStart				m_oAdresseStart = null;
	private FSK_DB_TextFieldKosten       		m_oKosten = null;
	private FSK_SearchZielAdresseOderLager  	m_oSearchZielAdresse = null;
	private FSK_DB_TextFieldDistanz       		m_oDistanz = null;
	private FSK_CB_LOCK  						m_oCB_Lock = null;
	
	
	private FSK_SQLFieldMAP 					oFSK_FM = null;
	
	
	private E2_BasicModuleContainer_MASK	 	E2_ModulContainerMASK_Adress = null;
	
	
	public FSK_ListComponentMAP(boolean bOriginal, E2_BasicModuleContainer_MASK	 e2_ModulContainerMASK_Adress) throws myException {
		super();
		
		this.E2_ModulContainerMASK_Adress = e2_ModulContainerMASK_Adress;
		
		if (bOriginal) {
			this.oFSK_FM = new FSK_SQLFieldMAP();
			this.set_oSQLFieldMAP(this.oFSK_FM);
			this.m_oArtikelDropdown = 	new FSK_DD_Artikel(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$ID_ARTIKEL));
			this.m_oAdresseStart 	= 	new FSK_DD_AdressenStart(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE));
			this.m_oKosten = 			new FSK_DB_TextFieldKosten(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$BETRAG_KOSTEN));
			this.m_oDistanz = 			new FSK_DB_TextFieldDistanz(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$DISTANZ));
			this.m_oSearchZielAdresse = new FSK_SearchZielAdresseOderLager(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_ZIEL));
			this.m_oCB_Lock = 			new FSK_CB_LOCK(this.oFSK_FM.get_(_DB.KOSTEN_LIEFERBED_ADR$LOCK_PRICE));
			
			
			
			this.m_oArtikelDropdown.EXT().set_bLineWrapListHeader(true);
			this.m_oAdresseStart.EXT().set_bLineWrapListHeader(true);
			this.m_oKosten.EXT().set_bLineWrapListHeader(true);
			this.m_oDistanz.EXT().set_bLineWrapListHeader(true);
			this.m_oSearchZielAdresse.EXT().set_bLineWrapListHeader(true);
			this.m_oSearchZielAdresse.EXT().set_bLineWrapListHeader(true);
			
			this.m_oAdresseStart.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("STA.NAME1 DESC,STA.NAME2 DESC, STA.ORT DESC");
			this.m_oAdresseStart.EXT_DB().set_cSortAusdruckFuerSortbuttonUP("STA.NAME1 ASC,STA.NAME2 ASC, STA.ORT ASC");
			this.m_oSearchZielAdresse.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("ZIEL.NAME1 DESC,ZIEL.NAME2 DESC, ZIEL.ORT DESC");
			this.m_oSearchZielAdresse.EXT_DB().set_cSortAusdruckFuerSortbuttonUP("ZIEL.NAME1 ASC, ZIEL.NAME2 ASC, ZIEL.ORT ASC");
			this.m_oArtikelDropdown.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("ART.ANR1 DESC");
			this.m_oArtikelDropdown.EXT_DB().set_cSortAusdruckFuerSortbuttonUP("ART.ANR1 ASC");
			
			
			
			
			
			
			this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
			
			this.add_Component(this.m_oAdresseStart, 		new MyE2_String("Startadresse der Warenbewegung"));
			this.add_Component(this.m_oArtikelDropdown, 	new MyE2_String("Artikel / Sorte"));
			this.add_Component(this.m_oSearchZielAdresse, 	new MyE2_String("Zieladresse der Warenbewegung"));
			this.add_Component(this.m_oKosten, 				new MyE2_String("Kosten / Preis-EH"));
			this.add_Component(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE, new FSK_BT_ErmittleKosten_In_Zeile(),
					new MyE2_String("?"));
			this.add_Component(this.m_oCB_Lock, 			new MyE2_String("?"));
			
			this.add_Component(this.m_oDistanz, 			new MyE2_String("km"));
			
			this.register_Interactiv_settings_validation(	FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE, 
															new ownMapSetterDisablePreiszauberstab());
		}
		
	}

	@Override
	public RECORD_KOSTEN_LIEFERBED_ADR create__LocalRecord() 	throws myException {
		return RECORD_KOSTEN_LIEFERBED_ADR.create_Instance(this.get__ID_BASE_TABLE());
	}

	@Override
	public String get__BaseTableName() throws myException {
		return RECORD_KOSTEN_LIEFERBED_ADR.TABLENAME;
	}

	@Override
	public String get__BaseIDField() throws myException {
		return RECORD_KOSTEN_LIEFERBED_ADR.IDFIELD;
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		
		FSK_ListComponentMAP mapRueck = null;
		try {
			mapRueck = new FSK_ListComponentMAP(false, this.E2_ModulContainerMASK_Adress);
			mapRueck.set_oSQLFieldMAP(this.oFSK_FM);
			
			E2_ComponentMAP.Copy_FieldsAndSettings(this, mapRueck);
			
			return mapRueck;
			
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy("FSK_ListComponentMAP: Copy-Error");
		}
	}

	public FSK_DD_Artikel get_m_oArtikelDropdown() {
		return m_oArtikelDropdown;
	}

	public FSK_DD_AdressenStart get_m_oAdresseStart() {
		return m_oAdresseStart;
	}

	
	// setter, der dafuer sorgt, dass bei maskenaufrufen im editmodus der preisermittlungszauberstab nicht aktiv ist
	private class ownMapSetterDisablePreiszauberstab extends XX_MAP_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
				oMAP.get__Comp(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE).set_bEnabled_For_Edit(false);
			}
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
				oMAP.get__Comp(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE).set_bEnabled_For_Edit(false);
			}
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
				oMAP.get__Comp(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE).set_bEnabled_For_Edit(false);
			}
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
				oMAP.get__Comp(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE).set_bEnabled_For_Edit(true);
			}
			//wenn die drunterliegende maske im view-modus ist, dann immer sperren
			if (FSK_ListComponentMAP.this.E2_ModulContainerMASK_Adress.get_vCombinedComponentMAPs().get(0).get_STATUS_LAST_FILL().equals(E2_ComponentMAP.STATUS_VIEW)) {
				oMAP.get__Comp(FSK__FULL_MASK_DAUGHTER_TP_KOSTEN.FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE).set_bEnabled_For_Edit(false);
			}

			
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return new MyE2_MessageVector();
		}
		
	}
	
	
}
