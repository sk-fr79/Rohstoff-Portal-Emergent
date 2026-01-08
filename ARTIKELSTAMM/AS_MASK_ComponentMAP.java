package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.DATENBLATT.ART_DB_FullDaughter4Mask;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.MASK_VALID.ARTIKEL_MAP_SET_AND_VALID_TYPEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class AS_MASK_ComponentMAP extends E2_ComponentMAP
{
	//NEU_09   aktiv raus
	String  cFieldsList = "ARTBEZ1|ARTBEZ2|BEMERKUNG_INTERN|ANR1|ZOLLTARIFNR|ZOLLTARIFNOTIZ|MENGENDIVISOR|EUNOTIZ|EUCODE|BASEL_NOTIZ|BASEL_CODE|DIENSTLEISTUNG|IST_PRODUKT";
	String  cBeschrList = "Artikelbezeichnung 1|Artikelbezeichnung 2|Bemerkung Intern|ANR1|Warennummer|Warennotiz|Mengendivisor|OECD-Notiz|OECD-Code|Basel-Notiz|Basel-Code|Dienstleistungsartikel|Produkt";

	
	ARTIKEL_MAP_SET_AND_VALID_TYPEN oMapSetAndValid = new ARTIKEL_MAP_SET_AND_VALID_TYPEN();

	
	//2020-10-28: behandlung der RC-Settings in den laendern
	private VEK<String>   sqlStatementsAfterSave = new VEK<String>();

	
	public AS_MASK_ComponentMAP(AS_BasicModuleContainerMASK oBasicModuleContainerMask) throws myException
	{
		super(new AS_MASK_SQLFieldMap());
		
		E2_DropDownSettings ddEinheit = new E2_DropDownSettings( "JT_EINHEIT", "  NVL(EINHEITKURZ,'-')", "ID_EINHEIT", null, true);
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsList,cBeschrList,this.get_oSQLFieldMAP(),false,false,this,400);

		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Artikel"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFM.get_("ID_EINHEIT"),ddEinheit.getDD(),false),new MyE2_String("Mengen-Einheit"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFM.get_("ID_EINHEIT_PREIS"),ddEinheit.getDD(),false),new MyE2_String("Preis-Einheit"));

		//2020-10-28: behandlung der RC-Settings in den laendern
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new OwnBuilder_AddOnSQL_STATEMENTS());
		
		
		//NEU_09
		// aktiv-button wird geschuetzt durch buttonkenner
		MyE2_DB_CheckBox oDBAktiv = new MyE2_DB_CheckBox(oSQLFM.get_("AKTIV"));
		oDBAktiv.add_GlobalValidator(new E2_ButtonAUTHValidator(oBasicModuleContainerMask.get_MODUL_IDENTIFIER(),"AKTIVIEREN_ARTIKEL"));
//		oDBAktiv.add_oActionAgent(new XX_ActionAgent()
//		{
//			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
//		});
		this.add_Component(oDBAktiv,new MyE2_String("Aktiv"));
		//this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("AKTIV"),oSQLFieldMAP,bibE2.get_YN_Ary_WithLeer(),false),new MyE2_String("Aktiv"));
		//ENDE NEU_09

		//gefahrgut-button mit anzeigerefresh
		MyE2_DB_CheckBox oCB_Gefahr = new MyE2_DB_CheckBox(oSQLFM.get_("GEFAHRGUT"));
		oCB_Gefahr.add_oActionAgent(new actionRefreshSceen());
		this.add_Component(oCB_Gefahr,new MyE2_String("Gefahrg."));

		
		String[][] cGenauigkeit = {{"-",""},{"Ganze Zahlen","0"},{"1 Nachkomma","1"},{"2 Nachkomma","2"},{"3 Nachkomma","3"}};
		MyE2_DB_SelectField oSelGenauigkeit = new MyE2_DB_SelectField(oSQLFM.get_("GENAUIGKEIT_MENGEN"),cGenauigkeit,false);
		
		this.add_Component(oSelGenauigkeit, new MyE2_String("Nachkommastellen in Mengenberechnungen"));
		
		
		this.add_Component(AS___CONST.HASHKEY_FULL_DAUGHTER_ARTBEZ, 
						new AS_MASK_BEZ_LIST_FullDaughterArtBez( 
								       (SQLFieldForPrimaryKey)oSQLFM.get_("ID_ARTIKEL"),
								       oBasicModuleContainerMask), new MyE2_String("Artikelbezeichnungen"));
		
		ownAVV_Search oSearchEAK_Code = new ownAVV_Search(	oSQLFM.get_("ID_EAK_CODE"),	null,null);
		oSearchEAK_Code.get_oTextForAnzeige().setToolTipText(new MyE2_String("AVV-Code für Baranlieferungen / Kleinanlieferer !").CTrans());
		this.add_Component(oSearchEAK_Code,new MyE2_String("AVV-Code für Baranlieferungen"));


		ownAVV_Search oSearchEAK_Code_Ex_MANDANT = new ownAVV_Search(	oSQLFM.get_("ID_EAK_CODE_EX_MANDANT"),	null,null);
		oSearchEAK_Code_Ex_MANDANT.get_oTextForAnzeige().setToolTipText(new MyE2_String("AVV-Code, die für den Mandanten gelten (falls keine spezifische Sorte festgelegt ist)!").CTrans());
		this.add_Component(oSearchEAK_Code_Ex_MANDANT,new MyE2_String("AVV-Code, die für den Mandanten gelten"));
		oSearchEAK_Code_Ex_MANDANT.set_oActionAfterFound(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				AS_MASK_ComponentMAP.this.FillMask_Info_Block();
				AS_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings_SimpleClick_Without_Registered_Component(
						AS_MASK_ComponentMAP.this, null);
			}
		});
		oSearchEAK_Code_Ex_MANDANT.get_oButtonErase().add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				AS_MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings_SimpleClick_Without_Registered_Component(
						AS_MASK_ComponentMAP.this, null);
			}
		});
		
		
		//suchhilfen fuer zolltarif und oecd-codes
		//this.add_Component(AS___CONST.HASH_KEY_MASK_SEARCH_ZOLLTARIFNUMMER, new AS_MASK_SEARCH_ZOLLNUMMER(), new MyE2_String("Suche nach Zolltarifnummern"));
		this.add_Component(AS___CONST.HASH_KEY_MASK_SEARCH_OECD_CODE, new AS_MASK_SEARCH_OECD(), new MyE2_String("Suche nach OECD-Code"));
		
		//2014-04-29: basel-code: ebenfalls via suchfeld
		this.add_Component(AS___CONST.HASH_KEY_MASK_SEARCH_BASEL_CODE, new AS_MASK_SEARCH_BASEL(), new MyE2_String("Suche nach BASEL-Code"));

		//aenderung 2010-12-29: zolltarif-dropdown fuer suche nach zolltarifnummer
		this.add_Component(new AS_MASK_SEARCH_ZOLLNUMMER(oSQLFM.get_("ID_ZOLLTARIFNUMMER")), new MyE2_String("ID-Zolltarifnummer"));
		
		//anzeige (Sortenanzeige ueber alle tabs)
		this.add_Component(AS___CONST.HASH_KEY_MASK_INFO_FIELD, new MyE2_Grid(MyE2_Grid.STYLE_GRID_DDARK_BORDER()), new MyE2_String("Information"));
		
		
		//artikelgruppe
		MyE2_DB_SelectField  oSelArtikelGruppe = new MyE2_DB_SelectField(oSQLFM.get_("ID_ARTIKEL_GRUPPE"),"SELECT GRUPPE,ID_ARTIKEL_GRUPPE FROM "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE ORDER BY GRUPPE",false,false);
		oSelArtikelGruppe.setWidth(new Extent(600));
		
		this.add_Component(oSelArtikelGruppe, new MyE2_String("Auswahl der Sortengruppe"));
		
		
		//2014-09-09: artikelgruppe fibu
		MyE2_DB_SelectField  oSelArtikelGruppeFIBU = new MyE2_DB_SelectField(oSQLFM.get_("ID_ARTIKEL_GRUPPE_FIBU"),"SELECT GRUPPE,ID_ARTIKEL_GRUPPE_FIBU FROM "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE_FIBU ORDER BY GRUPPE",false,false);
		oSelArtikelGruppeFIBU.setWidth(new Extent(600));
		
		this.add_Component(oSelArtikelGruppeFIBU, new MyE2_String("Auswahl der Sortengruppe (Fibu)"));

		
		
		//tochtertabelle fuer die datenblaetter
		this.add_Component(AS___CONST.HASH_KEY_MASK_DATENBLATT_TOCHTER, new ART_DB_FullDaughter4Mask(oSQLFM.get_oSQLFieldPKMainTable(), oBasicModuleContainerMask), new MyE2_String("Datenblätter"));
		
		
		//weitere Codes fuer Anhang7
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_(_DB.ARTIKEL$ANHANG7_3A_CODE), true, 150), 	new MyE2_String("Anhang 7 (IIIA)-Code"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_(_DB.ARTIKEL$ANHANG7_3A_TEXT), 600,3), 			new MyE2_String("Anhang 7 (IIIA)-Text"));
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_(_DB.ARTIKEL$ANHANG7_3B_CODE), true, 150), 	new MyE2_String("Anhang 7 (IIIB)-Code"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_(_DB.ARTIKEL$ANHANG7_3B_TEXT), 600,3), 			new MyE2_String("Anhang 7 (IIIB)-Text"));
		
		
		//2013-09-19: den leergutschalter in die maske einfuehren
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_(_DB.ARTIKEL$IST_LEERGUT), new MyE2_String("Schalter für Sorten, die in Leergut-Stellen-Fuhren verwendet werden")), new MyE2_String("Leergut-Artikel"));
		
		//2014-01-24: END-OF-WASTE
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_(_DB.ARTIKEL$END_OF_WASTE)),new MyE2_String("End of Waste"));

		
		
		// ein bisschen formatieren
		((MyE2_DB_TextField)this.get__Comp("ARTBEZ1")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ARTBEZ2")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ARTBEZ2")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("EUNOTIZ")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("EUNOTIZ")).set_iRows(3);
		((MyE2_DB_TextArea)this.get__Comp("ZOLLTARIFNOTIZ")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ZOLLTARIFNOTIZ")).set_iRows(5);
		((MyE2_DB_TextArea)this.get__Comp("BASEL_NOTIZ")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("BASEL_NOTIZ")).set_iRows(3);

		((MyE2_DB_TextField)this.get__Comp("EUCODE")).set_iWidthPixel(150);
		((MyE2_DB_TextField)this.get__Comp("ZOLLTARIFNR")).set_iWidthPixel(150);
		((MyE2_DB_TextField)this.get__Comp("BASEL_CODE")).set_iWidthPixel(150);
		
		((MyE2_DB_TextField)this.get__Comp("MENGENDIVISOR")).set_iWidthPixel(80);
		
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextArea)this.get__Comp("EUNOTIZ")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextArea)this.get__Comp("ZOLLTARIFNOTIZ")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextArea)this.get__Comp("BASEL_NOTIZ")).setFont(new E2_FontPlain(-2));

		
//		this.add_oMAPValidator(new AS_MAP_ValidatorEinheiten());
//	
		
//		this.add_oMAPValidator(new ownValidatorCheckZolltarif());
		
		//this.set_oSubQueryAgent(new AS_MASK_ComponentMap_SubQueryAgent());
		
		
//		//2012-07-16: masksetter der festlegt, dass einheiten nach anlegen einer sorte nicht mehr geaendert werden koenne
//		this.set_oMAPSettingAgent(new ownMapSettingAgent());
		
		
		//validierer-klasse registrieren
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$AKTIV, 			this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$GEFAHRGUT, 		this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$IST_PRODUKT, 		this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$IST_LEERGUT, 		this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$DIENSTLEISTUNG, 	this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$END_OF_WASTE, 		this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$ID_EINHEIT, 		this.oMapSetAndValid);
		this.register_Interactiv_settings_validation(_DB.ARTIKEL$ID_EINHEIT_PREIS, 	this.oMapSetAndValid);

		//20201029: wird ersetzt durch OwnBuilder_AddOnSQL_STATEMENTS / in zusammenhang mit break4popupContainer
		//2014-02-27: bei neuen sorten muss die RC-beziehung ins land D eingetragen werden
		//this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new ownAddonStatement_Write_RC_Sorts());
	}
	
	private class actionRefreshSceen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//2011-01-10: mehrere subquery-agents
			AS_MASK_ComponentMAP.this.get_vComponentMapSubQueryAgents().fillComponents(AS_MASK_ComponentMAP.this, null);
		}
	}
	
	
	
	private class ownAVV_Search extends MASK_COMPONENT_SEARCH_EAK_CODES
	{
		public ownAVV_Search(SQLField osqlField, FS_MASK_ComponentMAP ocomponentMAPADRESS,	DB_SEARCH_ArtikelBez SearchArtBez) throws myException 
		{
			super(osqlField, ocomponentMAPADRESS, SearchArtBez);
			this.get_oTextForAnzeige().setFont(new E2_FontItalic());
			this.get_oTFDatenFeldWithID().setFont(new E2_FontItalic());

			this.get_oTextForAnzeige().set_iWidthPixel(300);
			
		}
	}


	
	
	/**
	 * @return the sqlStatementsAfterSave
	 */
	public VEK<String> getSqlStatementsAfterSave() {
		return sqlStatementsAfterSave;
	}

	
	private class OwnBuilder_AddOnSQL_STATEMENTS implements E2_ComponentMAP.builder_AddOnSQL_STATEMENTS {
		@Override
		public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException {
			return sammle(oMV);
		}
		@Override
		public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oInputMAP, MyE2_MessageVector oMV) throws myException {
			return sammle(oMV);
		}
		private VEK<String> sammle(MyE2_MessageVector mv) {
			VEK<String> sqls = new VEK<String>();
			int countInserts = 0;
			int countDeletes = 0;
			for (String s: sqlStatementsAfterSave) {
				sqls._a(s);
				if (s.trim().toUpperCase().startsWith("INSERT")) {
					countInserts++;
				} 
				if (s.trim().toUpperCase().startsWith("DELETE")) {
					countDeletes++;
				} 
			}
			if (countInserts>0) {mv._addInfo(S.ms("Anzahl RC-Ländereinträge hinzugefügt:").ut(""+countInserts));}
			if (countDeletes>0) {mv._addInfo(S.ms("Anzahl RC-Ländereinträge entfernt:").ut(""+countDeletes));}
			return sqls;
		}
	}
	
	
	
}
