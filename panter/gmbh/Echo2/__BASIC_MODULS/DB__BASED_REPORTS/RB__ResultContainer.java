/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS
 * @author martin
 * @date 05.11.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_bt_export_excel;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_CONST.RQ_NAMES;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS.RQF_LEFT_MID_RIGHT;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_FIELD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 05.11.2018
 *
 */
public class RB__ResultContainer extends Project_BasicModuleContainer {

	private Rec21 				record = null;
	private E2_NavigationList 	navilist = null;
	
	public RB__ResultContainer(Rec21 p_record) throws myException {
		super(RQ_CONST.getModuKenner(p_record));
		
		this.record = 	p_record;

//		this.set_MODUL_IDENTIFIER(RQ_CONST.getModuKenner(this.record));

		//dafuer sorgen, dass die fenstergroessen korrekt gespeichert werden (fuer jede abfrage einzeln)
		
		this.set_cADDON_TO_CLASSNAME(this.record.getUfs(REPORTING_QUERY.realname_temptable));
		
		String 			nameOfTempTable = this.record.getUfs(REPORTING_QUERY.realname_temptable);
		String 			idOfTempTable = "ID"+nameOfTempTable.substring(2);
		
		SQLFieldMAP 	sqlFieldmap = new SQLFieldMAP(nameOfTempTable);
		String 			excludeBlock = ":"+idOfTempTable+":"+RQ_NAMES.SESSIONFIELDNAME.db_val()+":"+RQ_NAMES.USERIDFIELDNAME.db_val()+":"+RQ_NAMES.TIMESTAMPFIELDNAME.db_val()+":";
				

		sqlFieldmap.add_SQLField(new SQLFieldForPrimaryKey(nameOfTempTable,idOfTempTable,idOfTempTable,new MyE2_String("ID"),bibE2.get_CurrSession(),
						"SELECT SEQ_"+myCONST_ENUM.NUMMERNKREISE_GLOBAL.TR_TEMPIDS.name()+".NEXTVAL FROM DUAL",true), false);

		sqlFieldmap.addCompleteTable_FIELDLIST(nameOfTempTable, excludeBlock, false, false, "");

		sqlFieldmap.initFields();
		
		//jetzt dafuer sorgen, dass der benutzer immer nur die eintraege seiner session sieht
		sqlFieldmap.add_BEDINGUNG_STATIC(RQ_NAMES.SESSIONFIELDNAME.db_val()+"="+RQ_CONST.getSESCode());
		
		
		
		E2_ComponentMAP componentMap = new E2_ComponentMAP(sqlFieldmap);
		
		componentMap.add_Component(RQ_CONST.RQ_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
		componentMap.add_Component(RQ_CONST.RQ_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));

		//jetzt die felder in der manuellen Feldliste abfragen / sortieren
		RecList21 rlFields = this.record.get_down_reclist21(REPORTING_QUERY_FIELD.id_reporting_query, null, REPORTING_QUERY_FIELD.sortierfolge.fn());
		
		for (Rec21 rF: rlFields) { 
			String fname = rF.getUfs(REPORTING_QUERY_FIELD.fieldname);
			if (sqlFieldmap.keySet().contains(fname) && !componentMap.containsKey(fname)) {
				componentMap.add_Component(new MyE2_DB_Label_INGRID(sqlFieldmap.get(fname)),
									S.msUt(rF.getUfs(REPORTING_QUERY_FIELD.fieldname_4_user)));
				Long lWidth = rF.get_raw_resultValue_Long(REPORTING_QUERY_FIELD.breite_liste_px, 100l);
				Alignment al = new Alignment(Alignment.LEFT, Alignment.CENTER);
				if (S.isFull(rF.getUfs(REPORTING_QUERY_FIELD.alignment))) {
					RQF_LEFT_MID_RIGHT l_m_r = RQF_LEFT_MID_RIGHT.LEFT.getEnum(rF.getUfs(REPORTING_QUERY_FIELD.alignment));
					if (l_m_r!=null) {
						al = l_m_r.getAlignment();
					}
				}
				
				componentMap._setColExtent(new Extent(lWidth.intValue()),fname);
				componentMap._setLayoutElements(new RB_gld()._al(al)._ins(3,1,3,1)._col(new E2_ColorBase()), fname);
				componentMap._setLayoutTitles(  new RB_gld()._al(al)._ins(1,2,1,1)._col(new E2_ColorDark()), fname);
			}
		}
		
		
		for (String fname: sqlFieldmap.keySet()) {
			if (!componentMap.containsKey(fname)) {
				componentMap.add_Component(new MyE2_DB_Label_INGRID(sqlFieldmap.get(fname)), S.msUt(fname));
				Alignment al = new Alignment(Alignment.LEFT, Alignment.CENTER);
				componentMap._setColExtent(new Extent(100),fname);
				componentMap._setLayoutElements(new RB_gld()._al(al)._ins(3,1,3,1)._col(new E2_ColorBase()), fname);
				componentMap._setLayoutTitles(  new RB_gld()._al(al)._ins(1,2,1,1)._col(new E2_ColorDark()), fname);
			}
		}
		
		navilist = new E2_NavigationList();
		navilist.INIT_WITH_ComponentMAP(componentMap, null, RQ_CONST.getModuKenner(this.record) );
		navilist._REBUILD_COMPLETE_LIST("");
		
        this.add(new OwnBedienPanel(), E2_INSETS.I_2_2_2_2);
		this.add(navilist, E2_INSETS.I(5,5,5,5));
		
	}

	
	
	
	private class OwnBedienPanel extends E2_Grid {

		public OwnBedienPanel() throws myException {
			super();
	        E2_Grid grid4Components = new E2_Grid()._s(8);
	        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(navilist),  new RB_gld()._ins(2,2,20,2)._left_mid());
	        grid4Components._a(new BtExportExport(), new RB_gld()._ins(2,2,10,2)._left_mid());
	        
	        this._a(new OwnSelector(),   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
	        this._a(grid4Components,   		new RB_gld()._ins(0,2,10,2)._left_mid());
	        this._a(new OwnDataSearch(), 	new RB_gld()._ins(0,2,10,2)._left_mid());

		}
		
	}
	
	

	
	/**
	 * @author martin
	 * @date 08.11.2018
	 */
	private class BtExportExport extends EXP_bt_export_excel {
		public BtExportExport() throws myException {
			super(navilist, RQ_CONST.getModuKenner(record), "EXPORT");
			this._ttt(S.ms("Erstelle Exceltabelle der temporären Auswertung ..."));
		}
	}
	
	
	private class OwnSelector extends E2_ListSelectorContainer {

		private E2_Grid g = new E2_Grid();
		
		public OwnSelector() throws myException {
			super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
			
			E2_SelectionComponentsVector selVector= new E2_SelectionComponentsVector(navilist);

			
			E2_ListSelector_DB_Defined2 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined2(RQ_CONST.getModuKenner(record));
			oDB_BasedSelektor.set_extOfSelectComponentDropDown(new Extent(300));
			selVector.add(oDB_BasedSelektor);
			this.g._a(oDB_BasedSelektor.get_grid4Anzeige());
			
			this.add(this.g,E2_INSETS.I(0,0,0,0));
		}
		
	}
	
	
	private class OwnDataSearch extends E2_DataSearch {

		/**
		 * @author martin
		 * @date 06.11.2018
		 *
		 * @param cbaseTableName
		 * @param ckeyNameTable
		 * @param MODUL_KENNER
		 * @throws myException 
		 */
		public OwnDataSearch() throws myException {
			super(record.getUfs(REPORTING_QUERY.realname_temptable)
						, "ID_"+record.getUfs(REPORTING_QUERY.realname_temptable).substring(3)
						, RQ_CONST.getModuKenner(record));
			
			RecList21 rlFields = record.get_down_reclist21(REPORTING_QUERY_FIELD.id_reporting_query,null,REPORTING_QUERY_FIELD.fieldname_4_user.fn());
			
	        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(navilist);
	        this.set_oSearchAgent(oSearchAgent);

			
			for (Rec21 f: rlFields) {
				if (f.is_yes_db_val(REPORTING_QUERY_FIELD.is_searchfield)) {
					this.addSearchDef(f.getUfs(REPORTING_QUERY_FIELD.fieldname),f.getUfs(REPORTING_QUERY_FIELD.fieldname_4_user),	true);
				}
			}
			
			//20180523: datenbank gestützte suche zufuegen
			this.initAfterConstruction();

			
		}
		
	    
	    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
	    
	    	String table = record.getUfs(REPORTING_QUERY.realname_temptable);
	    	String id = 	"ID_"+record.getUfs(REPORTING_QUERY.realname_temptable).substring(3);
	    	
	    	
	    	
	        String cSearch = null;
	        if (searchWithLike) {
	           cSearch = "SELECT "+id+"  FROM "+bibE2.cTO()+"."+table+" WHERE UPPER(TO_CHAR("+table+"."+cFieldName+")) like UPPER('%#WERT#%')";
	        } else {
	           cSearch = "SELECT "+id+"  FROM "+bibE2.cTO()+"."+table+" WHERE UPPER(TO_CHAR("+table+"."+cFieldName+"))=UPPER('#WERT#')";
	        }           
	        
	        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	    }
	    

	}
	
	

	
	
}
