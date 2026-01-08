package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW_NG;

import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_MASKNAME;
import panter.gmbh.Echo2.RB.COMP.BETA.IF_Mask_Definition;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BEW_NG_ModelView_Generic_detail_grid extends E2_Grid{

	private MyRECORD rec = null;

	private String title = "";

	private BEW_NG_ModelView_DisplayContainer parent;

	
	private IF_Mask_Definition bew_detail = null;
	
	

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, MyRECORD record) throws myException{
		this.parent = oParent;

		if(record instanceof RECORD_BEWEGUNG){
			new BEW_NG_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG)record);
		}else {
			if(record instanceof RECORD_BEWEGUNG_VEKTOR){
				new BEW_NG_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_VEKTOR)record);
			}else if(record instanceof RECORD_BEWEGUNG_VEKTOR_POS){
					new BEW_NG_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_VEKTOR_POS) record);
				}
			if(record instanceof RECORD_BEWEGUNG_ATOM){
				new BEW_NG_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_ATOM)record);
			}else
				if(record instanceof RECORD_BEWEGUNG_STATION){
					new BEW_NG_ModelView_Generic_detail_grid(oParent, (RECORD_BEWEGUNG_STATION)record);
				}
		}
	}

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG bewegungRecord) throws myException {
		super();
		this.parent 	= oParent;
		this.rec		= bewegungRecord;

		this.title 		= "Bewegung ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";
		
		this.bew_detail = new bewegung_detail_grid();

		//		this.chkBox		= new MyE2_CheckBox(
//				"Leere Felder sichtbar",  
//				oParent.isLeereFeldernSichtBar(), 
//				false);
//		chkBox._aaa(new ownCheckBoxAgent());
//		onlyFullField 	= oParent.isLeereFeldernSichtBar();

		this._init();
	}

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_ATOM bewegungRecord) throws myException {

		super();
		this.parent 	= oParent;

		this.rec 		= bewegungRecord;
		this.title 		= "Atom ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";
		
		this.bew_detail = new bewegung_detail_grid();
		
//		this.chkBox 	= new MyE2_CheckBox(
//				"Leere Felder sichtbar",  
//				oParent.isLeereFeldernSichtBar(), 
//				false);
//
//		chkBox._aaa(new ownCheckBoxAgent());
//		onlyFullField 	= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_STATION bewegungRecord) throws myException {
		super();

		this.parent 		= oParent;

		this.rec			= bewegungRecord;
		this.title 			= "Station ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.bew_detail = new bewegung_detail_grid();
		
//		this.chkBox 		= new MyE2_CheckBox(
//				"Leere Felder sichtbar",  
//				oParent.isLeereFeldernSichtBar(), 
//				false);
//
//		chkBox._aaa(new ownCheckBoxAgent());
//		onlyFullField	 	= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_VEKTOR bewegungRecord) throws myException {
		super();

		this.parent 		= oParent;

		this.rec			= bewegungRecord;
		this.title 			= "Vektor ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";

		this.bew_detail = new bewegung_detail_grid();
		
//		this.chkBox 		= new MyE2_CheckBox(
//				"Leere Felder sichtbar",  
//				oParent.isLeereFeldernSichtBar(), 
//				false);
//
//		chkBox._aaa(new ownCheckBoxAgent());
//		onlyFullField 		= oParent.isLeereFeldernSichtBar();
		this._init();
	}

	public BEW_NG_ModelView_Generic_detail_grid(BEW_NG_ModelView_DisplayContainer oParent, RECORD_BEWEGUNG_VEKTOR_POS bewegungRecord) throws myException{
		super();

		this.parent 		= oParent;

		this.rec 			= bewegungRecord;
		this.title 			= "Vektor Pos ID " + rec.get_FormatedValue(rec.get_PRIMARY_KEY_NAME()) + " Detailaufnahme";
		
		this.bew_detail = new bewegung_detail_grid();
		
//		this.chkBox 		= new MyE2_CheckBox(
//				"Leere Felder sichtbar",  
//				oParent.isLeereFeldernSichtBar(), 
//				false);
//
//		chkBox._aaa(new ownCheckBoxAgent());
//		onlyFullField 		= oParent.isLeereFeldernSichtBar();
		this._init();
	}
	
	private class bewegung_detail_grid implements IF_Mask_Definition{
		@Override
		public ENUM_MASKNAME get_maskname() {
			return ENUM_MASKNAME.BEWEGUNG_INFO;
		}	
	}
	
	
	private void _init() throws myException{
		VEK<Rec20> records_vektor = new VEK<>();
		
		Rec20 converted_rec20 = new Rec20(_TAB.find_Table(this.rec.get_TABLE_NAME()))._fill_id(this.rec.get_UnFormatedValue(this.rec.get_PRIMARY_KEY_NAME()));
		
		records_vektor._a(converted_rec20);
		
		this._bo_ddd()._a(this.bew_detail._render(records_vektor)._bo_ddd());
	}

}
