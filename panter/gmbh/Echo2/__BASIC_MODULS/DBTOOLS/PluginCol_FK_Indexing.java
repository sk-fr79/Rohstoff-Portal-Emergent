package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class PluginCol_FK_Indexing extends Basic_PluginColumn
{
	
	private Vector<ownCB4index>   	v_cb_liste_4_index = new Vector<ownCB4index>();
	private MyE2_Grid   			gridIndexCBs = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
	
	private int   					indexZaehler = 0;		//zaehler stellt namens-gleichheit fest
	
	private MyE2_TextField          tfSuche = new MyE2_TextField("",200,30);
	
	
	private int    					iMaxNumberVorhanden = 0;
	
	public PluginCol_FK_Indexing(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		MyE2_Button oButtonSearchTableTriggers = new MyE2_Button(new MyE2_String("SUCHE Fremdkey-Felder"));
		oButtonSearchTableTriggers.add_oActionAgent(new actionAgentSucheIndexFelder());

		
		MyE2_Button oButtonBuildTableTriggers = new MyE2_Button(new MyE2_String("NEUAUFBAU FremdKey-Indexe"));
		oButtonBuildTableTriggers.add_oActionAgent(new actionAgentBuildIndizes());
		
		MyE2_Button oButtonDeleteTriggers = new MyE2_Button(new MyE2_String("Löschen ALLE FremdKey-Indexe"));
		oButtonDeleteTriggers.add_oActionAgent(new actionAgentDeleteIndexe());
		
		MyE2_Button  oButtonSearch = new MyE2_Button(E2_ResourceIcon.get_RI("suche_mini.png"));
		oButtonSearch.add_oActionAgent(new actionSearchTextInCheckboxList());
		
		MyE2_Button  oButtInvert = new MyE2_Button(new MyE2_String("Invertiere Auswahl"));
		oButtInvert.add_oActionAgent(new actionInvertiereAuswahl());
		
		// column fuer das tab-pane aufbauen
		this.add(new E2_ComponentGroupHorizontal(0,oButtonSearchTableTriggers, oButtonBuildTableTriggers,oButtonDeleteTriggers,this.tfSuche,oButtonSearch,oButtInvert,E2_INSETS.I_0_2_10_2),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
		this.add(this.gridIndexCBs,ContainerForVerwaltungsTOOLS.INSETS_LIST);

	}

	
	private void render_cb_array() 	{
		this.gridIndexCBs.removeAll();

		for (int i=0;i<this.v_cb_liste_4_index.size();i++) {
			this.gridIndexCBs.add(this.v_cb_liste_4_index.get(i),E2_INSETS.I_1_1_1_1);
		}

	}
	
	
	private class actionInvertiereAuswahl extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			for (ownCB4index cb: PluginCol_FK_Indexing.this.v_cb_liste_4_index) {
				cb.setSelected(!cb.isSelected());
			}
		}
	}
	

	
	private void build_cb_array() {
		String cQueryFK_Cols = "SELECT DISTINCT"+
				"    B.TABLE_NAME   AS TB,"+ 
				"    CB.COLUMN_NAME AS FI"+ 
				" FROM"+ 
				"    USER_CONSTRAINTS A ,"+ 
				"    USER_CONSTRAINTS B,"+ 
				"    USER_CONS_COLUMNS CA,"+ 
				"    USER_CONS_COLUMNS CB"+ 
				" WHERE"+ 
				"    A.CONSTRAINT_NAME    = B.R_CONSTRAINT_NAME"+ 
				"    AND A.CONSTRAINT_NAME= CA.CONSTRAINT_NAME"+ 
				"    AND B.CONSTRAINT_NAME= CB.CONSTRAINT_NAME"+
				" ORDER BY TB,FI";
		String[][] arrFK_Cols = bibDB.EinzelAbfrageInArray(cQueryFK_Cols) ;
		
		this.v_cb_liste_4_index.clear();
		
		for (int i=0;i<arrFK_Cols.length;i++) {
			ownCB4index oCB = new ownCB4index(arrFK_Cols[i][0]+"."+arrFK_Cols[i][1],arrFK_Cols[i][0],arrFK_Cols[i][1]);
			
			this.v_cb_liste_4_index.add(oCB);
			oCB.setFont(new E2_FontPlain(-2));		
		}
	}
	
	
	private void qualify_cb_array() {
		String cExistingIndexes = "select table_name,column_name,index_name from dba_ind_columns where INDEX_NAME like 'FK_IND_%' AND upper(TABLE_OWNER)= upper('"+bibE2.cTO()+"')";
		String[][] arrExistingIndexes = bibDB.EinzelAbfrageInArray(cExistingIndexes) ;

		this.iMaxNumberVorhanden=0;
		
		//nachsehen, ob der index schon existiert
		for (ownCB4index cb: this.v_cb_liste_4_index) {
			String indexname=null;
			for (int k=0;k<arrExistingIndexes.length;k++) {
				if (arrExistingIndexes[k][0].equals(cb.getTablename()) && arrExistingIndexes[k][1].equals(cb.getFieldname())) {
					indexname = arrExistingIndexes[k][2];
					//alte nummer extrahieren
					String s_number = "";
					if (indexname.contains("$")) {
						s_number = indexname.substring(indexname.indexOf("$")+1);
						try {
							int i_number = Integer.parseInt(s_number);
							if (this.iMaxNumberVorhanden<i_number) {
								this.iMaxNumberVorhanden=i_number;
								DEBUG.System_println("Startwert ..."+this.iMaxNumberVorhanden);
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (S.isFull(indexname)) {
				cb._setExists()._setIndexName(indexname)._ttt_when_possible("Index:"+indexname);
			} else {
				cb._setMissing()._setIndexName(null);
			}
		}

		//existierende markieren
		for (ownCB4index cb: this.v_cb_liste_4_index) {
			if (cb.is_index_existing()) {
				cb._fo_bold()._col_fore_black();
			} else {
				cb._fo_plain()._col_fore_dgrey();
			}
		}
		
	}
	
	
	
	private class actionSearchTextInCheckboxList extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			PluginCol_FK_Indexing oThis = PluginCol_FK_Indexing.this;
			
			for (ownCB4index cb: oThis.v_cb_liste_4_index) 	{
				if (cb.getText().toUpperCase().indexOf(oThis.tfSuche.getText().toUpperCase())>=0) {
					cb._col_back(bibALL.get_colorMaskHighLight());
				} else {
					cb._col_back(new E2_ColorBase());
				}
			}
		}
	}
	
	
	private class actionAgentSucheIndexFelder extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			PluginCol_FK_Indexing oThis = PluginCol_FK_Indexing.this;
			oThis.build_cb_array();
			oThis.qualify_cb_array();
			oThis.render_cb_array();
		}
	}
	


	private class actionAgentDeleteIndexe extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			PluginCol_FK_Indexing oThis = PluginCol_FK_Indexing.this;

			int iCountAll =0;
			int iCountERRDelete = 0;
			int iCountERRnotExist = 0;
			
			//nachsehen, ob der index schon existiert
			for (ownCB4index cb: oThis.v_cb_liste_4_index) {
				if (S.isFull(cb.getIndexname())) {
					if (!bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP INDEX "+cb.getIndexname(), true)){
						iCountERRDelete++;
					}else {
						iCountAll++;
//						DEBUG.System_println("ausgeführt: DROP INDEX "+cb.getIndexname());
					}
				} else {
					iCountERRnotExist++;
				}
			}
			oThis.qualify_cb_array();
			oThis.render_cb_array();
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Gefunden: ",true,""+iCountAll,false
																	,"   --> Fehler (beim Löschen): ",true,""+iCountERRDelete,false
																	,"   --> Fehler (Index hat nicht existiert): ",true,""+iCountERRnotExist,false)));
		}
	}

	
	
	
	private class actionAgentBuildIndizes extends XX_ActionAgent {
	
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			PluginCol_FK_Indexing oThis = PluginCol_FK_Indexing.this;
			
			oThis.indexZaehler = 0;
			
			int iCountSelected =0;
			int iCountERRDelete = 0;
			int iCountERRCreate = 0;
			int iCountOK = 0;

			//zuerst alle loeschen, die angekreuzt sind un die einen index enthalten 
			for (ownCB4index cb: oThis.v_cb_liste_4_index) {
				if (cb.isSelected()) {
					iCountSelected++;
					if (cb.index_is_existing && S.isAllFull(cb.getIndexname()))	{
						//dann wird der index erstmal geloescht
						if (!bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP INDEX "+cb.getIndexname(), true))	{
							iCountERRDelete++;
						}
					}
				}
			}

			//dann alle erzeugen die angekreuzt sind
			for (ownCB4index cb: oThis.v_cb_liste_4_index) {
				if (cb.isSelected()) {
					if (createIndex(cb)){
						cb.setText(cb.getText());
						iCountOK++;
					} else {
						cb.setText(cb.getText());
						iCountERRCreate++;
					}
				}
			}

			oThis.qualify_cb_array();
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Protokoll:  ",true
																	," //  Gewählt: ",true,""+iCountSelected,false
																	," //  Fehler beim Löschen: "+iCountERRDelete,false
																	," //  Korrekt erstellt: "+iCountOK,false
																	," //  Fehler beim Erzeugen: "+iCountERRCreate,false
																	)));
		}
	
		
		private boolean createIndex(ownCB4index cb) 	{
			PluginCol_FK_Indexing oThis = PluginCol_FK_Indexing.this;
 
			oThis.indexZaehler = oThis.indexZaehler+1;
			
			String cHelpNumber = bibALL.fillString(""+(oThis.iMaxNumberVorhanden + (oThis.indexZaehler)),5,'0',true);
			
			String cSQL = "CREATE INDEX "+
						bibALL.HoechstLaenge(" FK_IND_"+cb.getFieldname(),24)+"$"+cHelpNumber+" ON "+cb.getTablename()+" ("+
						cb.getFieldname()+" ASC ) ";
		
			return bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQL, true);
		}
		
		
	}
	
	
	private class ownCB4index extends MyE2_CheckBox implements IF_FontandText<ownCB4index>, IF_Color<ownCB4index>{
		
		private boolean index_is_existing = false;
		private String  tablename = null;
		private String  fieldname = null;
		private String  indexname = null;
		
		public ownCB4index(String cText, String  tablename, String  fieldname) {
			super(cText);
			this.tablename = tablename;
			this.fieldname = fieldname;
//			DEBUG.System_println("Table-field: "+this.tablename+"->"+this.fieldname);
		}
		
		public ownCB4index _setExists() {
			this.index_is_existing=true;
			return this;
		}

		public ownCB4index _setMissing() {
			this.index_is_existing=false;
			return this;
		}

		public ownCB4index _setIndexName(String indexname) {
			this.indexname=indexname;
			return this;
		}

		
		public boolean is_index_existing() {
			return index_is_existing;
		}

		public String getTablename() {
			return tablename;
		}

		public String getFieldname() {
			return fieldname;
		}

		public String getIndexname() {
			return indexname;
		}

	}

}
		
