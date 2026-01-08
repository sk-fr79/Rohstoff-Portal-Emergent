package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW;

import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class BEW_ModelView_Generic_detail_grid extends E2_Grid{

	private MyRECORD rec = null;

	private boolean onlyFullField = true;

	private String title = "";

	private BEW_ModelView_DisplayContainer parent;
	
	private MyE2_CheckBox chkBox;

	
	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, MyRECORD record) throws myException{
		this.parent = oParent;
		
		if(record instanceof RECORD_BEWEGUNG){
			new BEW_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG)record);
		}else
			if(record instanceof RECORD_BEWEGUNG_VEKTOR){
				new BEW_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_VEKTOR)record);
			}else
				if(record instanceof RECORD_BEWEGUNG_VEKTOR_POS){
					new BEW_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_VEKTOR_POS) record);
				}
		if(record instanceof RECORD_BEWEGUNG_ATOM){
			new BEW_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_ATOM)record);
		}else
			if(record instanceof RECORD_BEWEGUNG_STATION){
				new BEW_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_STATION)record);
			}
	}

	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG bewegungRecord) throws myException {
		super();
		this.parent 	= oParent;
		this.rec		= bewegungRecord;
		
		this.title 		= "Bewegung ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";
		
		this.chkBox		= new MyE2_CheckBox(
				"Leere Felder sichtbar",  
				oParent.isLeereFeldernSichtBar(), 
				false);
		
		chkBox._aaa(new ownCheckBoxAgent());

		onlyFullField 	= oParent.isLeereFeldernSichtBar();
		
		this._init();
	}

	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_ATOM bewegungRecord) throws myException {
		
		super();
		this.parent 	= oParent;
		
		this.rec 		= bewegungRecord;
		this.title 		= "Atom ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.chkBox 	= new MyE2_CheckBox(
								"Leere Felder sichtbar",  
								oParent.isLeereFeldernSichtBar(), 
								false);
		
		chkBox._aaa(new ownCheckBoxAgent());
		onlyFullField 	= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_STATION bewegungRecord) throws myException {
		super();
		
		this.parent 		= oParent;
		
		this.rec			= bewegungRecord;
		this.title 			= "Station ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.chkBox 		= new MyE2_CheckBox(
								"Leere Felder sichtbar",  
								oParent.isLeereFeldernSichtBar(), 
								false);
		
		chkBox._aaa(new ownCheckBoxAgent());
		onlyFullField	 	= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_VEKTOR bewegungRecord) throws myException {
		super();
		
		this.parent 		= oParent;
		
		this.rec			= bewegungRecord;
		this.title 			= "Vektor ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.chkBox 		= new MyE2_CheckBox(
								"Leere Felder sichtbar",  
								oParent.isLeereFeldernSichtBar(), 
								false);
		
		chkBox._aaa(new ownCheckBoxAgent());
		onlyFullField 		= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_VEKTOR_POS bewegungRecord) throws myException{
		super();
		
		this.parent 		= oParent;
		
		this.rec 			= bewegungRecord;
		this.title 			= "Vektor Pos ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.chkBox 		= new MyE2_CheckBox(
								"Leere Felder sichtbar",  
								oParent.isLeereFeldernSichtBar(), 
								false);
		
		chkBox._aaa(new ownCheckBoxAgent());
		onlyFullField 		= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	private void _init() throws myException{

		this._setSize(175,210);

		Color backColor = new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();

		this._bc(backColor);

		E2_Grid headerGrid = new E2_Grid()._s(2)
				._a(new RB_lab(this.title)._b())
				._a(chkBox);

		E2_Grid inGrid = new E2_Grid()._gld(new RB_gld())._bc(backColor);

		inGrid.setOrientation(ORIENTATION_VERTICAL);

		int nb_of_spalten = 30;//((rec.size()/4)+1)*25;

		int[] i_total_breite = new int [nb_of_spalten]; 

		for(int i=0;i<nb_of_spalten;i++){
			i_total_breite[i] = 250;
		}

		inGrid._s(nb_of_spalten)._setSize(i_total_breite)._bo_ddd();		

		Vector <String> keySet =  new Vector<>(rec.keySet());
		Collections.sort(keySet);

		feldernOrdnung(keySet);

		for(String feldName: keySet){

			RB_lab lbl =  new RB_lab(feldName+":")._fsa(-2);

			MyE2_TextField tf = new MyE2_TextField("",200,2000);

			if(feldName.equals(rec.get_PRIMARY_KEY_NAME())){
				lbl._b();
				tf.setFont(new E2_FontBold(-1));
			}else{
				tf.setFont(new E2_FontPlain(-1));
			}

			inGrid._gld(new RB_gld()._ins(2, 2, 2, 2));
			Component cmp = null;

			if(rec.get(feldName).get_MetaFieldDef().is_boolean_single_char()){
				MyE2_CheckBox cb = new MyE2_CheckBox();
				cb.setSelected(S.NN(rec.get(feldName).get_FieldValueUnformated()).toUpperCase().equals("Y"));
				cmp = cb;
			}else if(rec.get(feldName).get_MetaFieldDef().get_FieldTextLENGTH()>100){
				RB_TextArea ta = new RB_TextArea(200, 3);
				ta.setText(rec.get(feldName).get_FieldValueFormated());
				ta.set_bEnabled_For_Edit(false);
				cmp=ta;
			}else{

				tf.setText(rec.get(feldName).get_FieldValueFormated());
				tf.set_bEnabled_For_Edit(false);

				cmp=(tf);
			}

			E2_Grid cmpGrid = new E2_Grid()._setSize(new int[]{250,250});

			if(! onlyFullField){
				if(S.isFull(rec.get(feldName).get_FieldValueFormated())){
					cmpGrid._a_lm(lbl)._ins(E2_INSETS.I(2,2,2,2));
					cmpGrid._a_rm(cmp);
				}
			}else{
				cmpGrid._a_lt(lbl)._ins(E2_INSETS.I(2,2,2,2));
				cmpGrid._a_rt(cmp);
			}

			inGrid._ins(E2_INSETS.I(2,2,2,2))._a_lt(cmpGrid);
		}


		this._gld(new RB_gld()._span(2)._col(backColor)._al(E2_ALIGN.CENTER_MID))._a_lt(headerGrid);
		this._gld(new RB_gld()._span(1)._col(backColor))._a_lt(inGrid);

	}

	
	private void feldernOrdnung(Vector<String> keySet) throws myException {
		Vector <String> endBlock = new Vector<>();

		for(String feldName: keySet){
			if(feldName.equals(rec.get_PRIMARY_KEY_NAME())){
				Collections.swap(keySet, keySet.indexOf(feldName), 0);	
			}
		}
		
		for(String feldName: keySet){
			if(rec.get_vSonderFelder().contains(feldName)){
				String wert_zu_move = feldName;
				endBlock.add(wert_zu_move);
			}
		}
		
		keySet.removeAll(endBlock);
		
		keySet.addAll(endBlock);
	}
	

	@Override
	public void setWidth(Extent newValue) {
		super.setWidth(newValue);

	}

	private class ownCheckBoxAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			onlyFullField = ((MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource()).isSelected();
			
			parent.setLeereFeldernSichtBar(onlyFullField);
			
			
			
			BEW_ModelView_Generic_detail_grid.this.removeAll();
			BEW_ModelView_Generic_detail_grid.this._init();
		}

	}

}
