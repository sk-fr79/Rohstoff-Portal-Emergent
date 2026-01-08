package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class FS_Component_MASK_WeitereWaehrungen extends E2_Grid implements MyE2IF__DB_Component {

	//key der Treemaps ist die kurzbezeichnung (wegen der sortierung), value ist die id_waehrung
	private TreeMap<String,String>  tmKeysAtLoad =    new TreeMap<String,String>();
	private TreeMap<String,String>  tmKeysAtActual =  new TreeMap<String,String>();
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	private E2_Grid     			gridKundenWaehrungen = new E2_Grid()._setSize(70,70)._bo_ddd();
	
	private MyE2_PopUpMenue      	popupAddWaehrung = new MyE2_PopUpMenue(	E2_ResourceIcon.get_RI("add_field.png"), 	
																			E2_ResourceIcon.get_RI("add_field__.png"), 
																			true, 
																			new Extent(150), new Extent(150), 0, 100);

	private RecList21    			rlAllWaehrungen = null;
	
	private int[]  spalten =        {50,20};          //je eine waehrung und ein delButton
	
	public FS_Component_MASK_WeitereWaehrungen(SQLField  p_sql_field) throws myException {
		super();
		this._setSize(140,20);
		
		this.rlAllWaehrungen = new RecList21(_TAB.waehrung)._fillWithAll();
		
		VEK<Rec21>  rlW = this.rlAllWaehrungen.get_sorted_vector(new ownComparer());
		
		for (Rec21 r: rlW) {
			popupAddWaehrung.addButton(  (MyE2_Button)new MyE2_Button(S.msUt(r.getUfs(WAEHRUNG.kurzbezeichnung)))
										._aaa(new ownActionAddWaehrung(r.getUfs(WAEHRUNG.id_waehrung)))
										,true);
		}
		
		this.popupAddWaehrung.setToolTipText(new MyE2_String("Weitere Währung zur Adresse zufügen").CTrans());
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(p_sql_field);

		this._a(this.gridKundenWaehrungen,new RB_gld()._ins(1)._left_mid())._a(this.popupAddWaehrung, new RB_gld()._ins(2,1,2,1)._left_mid());
	}
	
	private class ownComparer implements Comparator<Rec21> {
		@Override
		public int compare(Rec21 o1, Rec21 o2) {
			try {
				return o1.getUfs(WAEHRUNG.kurzbezeichnung).compareTo( o2.getUfs(WAEHRUNG.kurzbezeichnung));
			} catch (myException e) {
				e.printStackTrace();
				return 0;
			}
		}
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
			throw new myException("Error loading multiple <WAEHRUNG> values ...");
		}
		
		SEL sel = new SEL("*").FROM(_TAB.adresse_waehrung).WHERE(new vgl(ADRESSE_WAEHRUNG.id_adresse, id_adresse)); 
		RecList21 rAW = new RecList21(_TAB.adresse_waehrung)._fill(sel.s());
		
		for (Rec21 r: rAW) {
			this.tmKeysAtLoad.put(r.get_up_Rec21(WAEHRUNG.id_waehrung).getUfs(WAEHRUNG.kurzbezeichnung),r.get_up_Rec21(WAEHRUNG.id_waehrung).getUfs(WAEHRUNG.id_waehrung));
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
		MyE2_MessageVector mv = new MyE2_MessageVector();
		Vector<String> vSQL = new Vector<String>();
		Vector<String> v_newId = new Vector<String>(this.tmKeysAtActual.values());
		Vector<String> v_oldId = new Vector<String>(this.tmKeysAtLoad.values());
		
		for (String id_waehrung: v_newId) {
			if (!v_oldId.contains(id_waehrung)) {
				Rec21 recNew = new Rec21(_TAB.adresse_waehrung);
				recNew._setNewVal(ADRESSE_WAEHRUNG.id_waehrung,new MyLong(id_waehrung).get_oLong(),mv);
				recNew._add_field_val_pair(ADRESSE_WAEHRUNG.id_adresse, term4ID_ADRESS);
				vSQL.add(recNew.get_sql_4_save(true));
			}
		}
		for (String id_waehrung: v_oldId) { 
			if (!v_newId.contains(id_waehrung)) {
				vSQL.add("DELETE FROM "+_TAB.adresse_waehrung.fullTableName()+" WHERE "+ADRESSE_WAEHRUNG.id_adresse+"="+term4ID_ADRESS+" AND "
							+ADRESSE_WAEHRUNG.id_waehrung+"="+id_waehrung);			}
		}
		
		if (mv.get_bHasAlarms()) {
			bibMSG.MV().add_MESSAGE(mv);
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
		this.gridKundenWaehrungen.removeAll();
		
		for (String kurzbezeichung: this.tmKeysAtActual.keySet()) {
			String id_waehrung = this.tmKeysAtActual.get(kurzbezeichung);
			if (this.rlAllWaehrungen.get(id_waehrung)!=null) {
				this.gridKundenWaehrungen._a(new ownSubGrid(this.rlAllWaehrungen.get(id_waehrung)), new RB_gld()._ins(1, 1, 1, 1));
			}
		}
	}
	
	
	private class ownSubGrid extends E2_Grid {
		private E2_Button  btRemove = null;
		public ownSubGrid(Rec21 recWaehrung) throws myException {
			super();
			this._setSize(spalten);
			this.btRemove = new E2_Button()._image(E2_ResourceIcon.get_RI("delete2.png"),true)
							._ttt(S.ms("Zusatzwährung ").ut(recWaehrung.getUfs(WAEHRUNG.kurzbezeichnung)).t(" aus dieser Adresse entfernen!"))
							._aaa(new OwnActionRemoveWaehrung(recWaehrung.get_PRIMARY_KEY_VALUE()))
							;
			this._a(recWaehrung.getUfs(WAEHRUNG.kurzbezeichnung))._a(btRemove);
		}
		public E2_Button get_btRemove() {
			return btRemove;
		}
		
	}
	
	
	private class OwnActionRemoveWaehrung extends XX_ActionAgent {
		private Long id_waehrung = null;
		
		public OwnActionRemoveWaehrung(Long p_id_waehrung) {
			super();
			this.id_waehrung = p_id_waehrung;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_Component_MASK_WeitereWaehrungen oThis = FS_Component_MASK_WeitereWaehrungen.this;
			
			for (Entry<String, String> e: oThis.tmKeysAtActual.entrySet()) {
				if (e.getValue().equals(this.id_waehrung.toString())) {
					oThis.tmKeysAtActual.remove(e.getKey());
					break;
				}
			}
			
			oThis.rebuild_component();
		}
	}
	
	
	
	private class ownActionAddWaehrung extends XX_ActionAgent {
		private String id_waehrung = null;
		
		public ownActionAddWaehrung(String p_id) {
			super();
			this.id_waehrung = p_id;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_Component_MASK_WeitereWaehrungen oThis = FS_Component_MASK_WeitereWaehrungen.this;
			
			E2_ComponentMAP maskMap = oThis.EXT().get_oComponentMAP();
			String id_waehrung_haupt = maskMap.get_cActualDBValueFormated(ADRESSE.id_waehrung.fn(),"-1");
			if (id_waehrung_haupt!="-1" && this.id_waehrung.equals(id_waehrung_haupt)) {
				Rec21 rw = rlAllWaehrungen.get(S.NN(this.id_waehrung,"-1")); 
				if (rw==null) {
					bibMSG.MV()._addAlarm("System-Error: 345ww454361209");
				} else {
					bibMSG.MV()._addAlarm(S.ms("Die Währung ").ut("<"+rw.getUfs(WAEHRUNG.kurzbezeichnung)+">").t(" ist bereits die Hauptwährung!"));
				}
			} else {
				Rec21 rW = rlAllWaehrungen.get(S.NN(this.id_waehrung,"-1")); 
				if (rW != null) {
					oThis.tmKeysAtActual.put(rW.getUfs(WAEHRUNG.kurzbezeichnung), rW.getUfs(WAEHRUNG.id_waehrung));
				}
			}
			
			oThis.rebuild_component();
		}
	}



	public MyE2_PopUpMenue get_popupAddWaehrung() {
		return popupAddWaehrung;
	}
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException 	{
		super.set_bEnabled_For_Edit(enabled);
		for (Component cmp: this.gridKundenWaehrungen.getComponents()) {
			if (cmp instanceof ownSubGrid) {
				((ownSubGrid)cmp).get_btRemove().set_bEnabled_For_Edit(enabled);
			}
		}
	}


	public VEK<String>  getIdsZusatzWaehrung() throws myException {
		return new VEK<String>()._a(this.tmKeysAtActual.values());
	}
	
	
}
