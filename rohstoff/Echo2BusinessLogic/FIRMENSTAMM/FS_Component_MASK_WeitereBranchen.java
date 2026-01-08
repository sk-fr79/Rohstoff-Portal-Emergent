package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_ZUSATZBRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.BRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_ZUSATZBRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSE_ZUSATZBRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_ZUSATZBRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BRANCHE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_WeitereBranchen extends MyE2_Grid implements MyE2IF__DB_Component {

	//key der Treemaps ist die kurzbezeichnung (wegen der sortierung), value ist die id_branche
	private TreeMap<String,String>  tmKeysAtLoad =    new TreeMap<String,String>();
	private TreeMap<String,String>  tmKeysAtActual =  new TreeMap<String,String>();
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	private MyE2_Grid     			grid4SelectedBranchen = new MyE2_Grid(MyE2_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS_W100());
	
	private static int[]	    	breite_sub_grids = {150,15};

	
	private MyE2_PopUpMenue      	popupAddBranche = new MyE2_PopUpMenue(	E2_ResourceIcon.get_RI("add_field.png"), 	
																			E2_ResourceIcon.get_RI("add_field__.png"), 
																			true, 
																			new Extent(150), new Extent(300), 0, 200);
	
	/**
	 * diese reclist wird beim ersten gebrauch gefuellt
	 */
	private RECLIST_BRANCHE  		rlAlleBranchen = null;
	
	public FS_Component_MASK_WeitereBranchen(SQLField  p_sql_field) throws myException {
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		E2_DropDownSettingsNew ddBranche = new E2_DropDownSettingsNew("JT_BRANCHE", "KURZBEZEICHNUNG", "ID_BRANCHE", "IST_STANDARD", true,false);

		for (int i=0;i<ddBranche.getDD().length;i++) {
			if (S.isFull(ddBranche.getDD()[i][1])) {
				MyE2_Button button = new MyE2_Button(new MyE2_String(ddBranche.getDD()[i][0],false));
				button.add_oActionAgent(new ownActionAddBranche(ddBranche.getDD()[i][1]));
				this.popupAddBranche.addButton(button, true);
			}
		}
		this.popupAddBranche.setToolTipText(new MyE2_String("Weitere Zusatzbranche zur Adresse zufügen").CTrans());
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(p_sql_field);

		this.grid4SelectedBranchen.setSize(4);
		this.add(this.grid4SelectedBranchen,MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)));
	}
	
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.tmKeysAtLoad.clear();
		this.tmKeysAtActual.clear();
		this.rebuild_component();
	}


	@Override
	public String get_cActualMaskValue() throws myException {
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String p_id_adress_formated,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		String id_adresse = new MyLong(p_id_adress_formated).get_cUF_LongString();
		
		this.tmKeysAtActual.clear();
		this.tmKeysAtLoad.clear();
		
		if (S.isEmpty(id_adresse)) {
			throw new myException("Error loading multiple <Branche> values ...");
		}
		
		SEL sel = new SEL("*").FROM(ADRESSE_ZUSATZBRANCHE.T()).WHERE(new vgl(ADRESSE_ZUSATZBRANCHE.id_adresse, id_adresse)); 
		RECLIST_ADRESSE_ZUSATZBRANCHE rl = new RECLIST_ADRESSE_ZUSATZBRANCHE(sel.s());
		
		for (RECORD_ADRESSE_ZUSATZBRANCHE r: rl) {
			this.tmKeysAtLoad.put(r.get_UP_RECORD_BRANCHE_id_branche().get_KURZBEZEICHNUNG_cUF(),r.get_UP_RECORD_BRANCHE_id_branche().get_ID_BRANCHE_cUF());
		}
		
		this.tmKeysAtActual.putAll(this.tmKeysAtLoad);
		
		this.rebuild_component();
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
	}

	@Override
	public boolean get_bIsComplexObject() {
		return true;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		return this.generateSqlStack(_DB.ADRESSE$$SEQ_CURR);
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP,	SQLMaskInputMAP oMaskInputMap) throws myException {
		return this.generateSqlStack(oE2_ComponentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
	}

	
	private Vector<String> generateSqlStack(String term4ID_ADRESS) throws myException {
		Vector<String> vSQL = new Vector<String>();
		Vector<String> v_newId = new Vector<String>(this.tmKeysAtActual.values());
		Vector<String> v_oldId = new Vector<String>(this.tmKeysAtLoad.values());
		
		for (String id_branche: v_newId) {
			if (!v_oldId.contains(id_branche)) {
				RECORDNEW_ADRESSE_ZUSATZBRANCHE rbn = new RECORDNEW_ADRESSE_ZUSATZBRANCHE();
				rbn.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(ADRESSE_ZUSATZBRANCHE.id_adresse.fn(), term4ID_ADRESS);
				rbn.set_NEW_VALUE_ID_BRANCHE(id_branche);
				DEBUG.System_println(rbn.get_InsertSQLStatementWith_Id_Field(true, true));
				vSQL.add(rbn.get_InsertSQLStatementWith_Id_Field(true, true));
			}
		}
		for (String id_branche: v_oldId) { 
			if (!v_newId.contains(id_branche)) {
				vSQL.add("DELETE FROM "+ADRESSE_ZUSATZBRANCHE.fullTabName()+" WHERE "+ADRESSE_ZUSATZBRANCHE.id_adresse+"="+term4ID_ADRESS+" AND "
							+ADRESSE_ZUSATZBRANCHE.id_branche+"="+id_branche);			}
		}
		
		
		return vSQL;
	}
	
	
	@Override
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component o_EXT_DB) {
		this.oEXTDB = o_EXT_DB;
	}

	
	private void rebuild_component() throws myException {
		this.grid4SelectedBranchen.removeAll();
		
		if (this.rlAlleBranchen == null) {
			this.rlAlleBranchen=new RECLIST_BRANCHE(new SEL("*").FROM(BRANCHE.T()).ORDERUP(BRANCHE.kurzbezeichnung).s());
		}
		
		for (String kurzbezeichung: this.tmKeysAtActual.keySet()) {
			String id_branche = this.tmKeysAtActual.get(kurzbezeichung);
			this.grid4SelectedBranchen.add(new ownSubGrid(this.rlAlleBranchen.get(id_branche)), E2_INSETS.I(2,0,2,0));
		}
	}
	
	
	private class ownSubGrid extends MyE2_Grid {
		private MyE2_Button  btRemove = null;
		public ownSubGrid(RECORD_BRANCHE recBRANCHE) throws myException {
			super(breite_sub_grids, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			this.btRemove = new MyE2_Button(E2_ResourceIcon.get_RI("delete2.png"),true);
			btRemove.setToolTipText(new MyE2_String("Zusatzbranche ",true,recBRANCHE.get_KURZBEZEICHNUNG_cUF(),false," aus dieser Adresse entfernen!",true).CTrans());
			btRemove.add_oActionAgent(new ownActionRemoveBranche(recBRANCHE.get_ID_BRANCHE_cUF()));
			
			this.add(new MyE2_Label(new MyE2_String(recBRANCHE.get_KURZBEZEICHNUNG_cUF(),false),new E2_FontPlain()),E2_INSETS.I(1,0,1,0));
			this.add(btRemove,E2_INSETS.I(1,0,3,0));
		}
		public MyE2_Button get_btRemove() {
			return btRemove;
		}
		
	}
	
	
	private class ownActionRemoveBranche extends XX_ActionAgent {
		private String id_branche = null;
		
		public ownActionRemoveBranche(String p_id_branche) {
			super();
			this.id_branche = p_id_branche;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_Component_MASK_WeitereBranchen oThis = FS_Component_MASK_WeitereBranchen.this;
			
			for (Entry<String, String> e: oThis.tmKeysAtActual.entrySet()) {
				if (e.getValue().equals(this.id_branche)) {
					oThis.tmKeysAtActual.remove(e.getKey());
					break;
				}
			}
			
			oThis.rebuild_component();
		}
	}
	
	
	
	private class ownActionAddBranche extends XX_ActionAgent {
		private String id_branche_to_add = null;
		
		public ownActionAddBranche(String p_id_branche_to_add) {
			super();
			this.id_branche_to_add = p_id_branche_to_add;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_Component_MASK_WeitereBranchen oThis = FS_Component_MASK_WeitereBranchen.this;
			
			if (S.isFull(this.id_branche_to_add)) {
				String id_branche = new MyLong(id_branche_to_add).get_cUF_LongString();
				RECORD_BRANCHE  brancheDazu = oThis.rlAlleBranchen.get(id_branche);
				if (brancheDazu!=null) {
					oThis.tmKeysAtActual.put(brancheDazu.get_KURZBEZEICHNUNG_cUF(), id_branche);
				}
				oThis.rebuild_component();
			}
		}
	}



	public MyE2_PopUpMenue get_popupAddBranche() {
		return popupAddBranche;
	}
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException 	{
		super.set_bEnabled_For_Edit(enabled);
		for (Component cmp: this.grid4SelectedBranchen.getComponents()) {
			if (cmp instanceof ownSubGrid) {
				((ownSubGrid)cmp).get_btRemove().set_bEnabled_For_Edit(enabled);
			}
		}
	}


}
