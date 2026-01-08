package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATIONS_TYP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG.BLOCK_TO_SHOW;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class AI_TelefonFaxDataBlock_NG extends AR_DataBlock {

	private String  				zusatz_text = "";
	private RECORD_ADRESSE_extend 	rec_Adresse   = null;
	private BLOCK_TO_SHOW  	   		used_Block = null;		
	
	private RECLIST_KOMMUNIKATION 	rl_kom = null; 
	
	public AI_TelefonFaxDataBlock_NG(RECORD_ADRESSE recAdresse, String p_zusatztext, BLOCK_TO_SHOW block) throws myException {
		super();
		this.zusatz_text = p_zusatztext;
		this.used_Block = block;
		this.rec_Adresse = new RECORD_ADRESSE_extend(recAdresse);

		this.rl_kom = recAdresse.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse();
		
		if (rl_kom.size()>0) {
			Vector<RECORD_KOMMUNIKATION> v_komm = rl_kom.GET_SORTED_VECTOR(
							bibALL.get_Vector(KOMMUNIKATION.wert_laendervorwahl.fn(),KOMMUNIKATION.wert_vorwahl.fn(),KOMMUNIKATION.wert_rufnummer.fn()), true);
			
			for (RECORD_KOMMUNIKATION komm: v_komm) {
				this.add(new TelefonFaxRow(komm));
			}
		}
		
	}

	public AI_AdressBlock_NG  get_topAdressBlock() {
		return (AI_AdressBlock_NG)this.find_top_levelBlock_in_chain();
	}

	
	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		if (this.get_topAdressBlock().zeigeBeschriftung()) {
			IF_AR_Component[][] comp = new IF_AR_Component[1][1];
			comp[0][0] = new AR_Label(	new AR_LayoutData(20-this.get_complete_insets(),get_topAdressBlock().get_insets(),0), 
										new MyE2_String("Telefon/Fax ",true,this.zusatz_text,false," <",false,this.rec_Adresse.get__FullNameAndAdress_Typ2(),false,">",false), 
										new AR_StyleLabel(false, true, true, -2));
			return comp;
		}
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterBlock()	throws myException {
		return null;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		
		AR_DataBlock  startBlock = this.find_top_levelBlock_in_chain();
		
		if (startBlock!=null) {
			IF_Saveable steuerknopf = ((AI_AdressBlock_NG)startBlock).get_SteuerpultKnopf(this.used_Block);
			if (steuerknopf!=null && steuerknopf instanceof AI__CheckBox_NG) {
				return ((AI__CheckBox_NG)steuerknopf).isSelected() && this.rl_kom.size()>0;
			}
		}
		return this.rl_kom.size()>0;
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 1;
	}

	@Override
	public boolean _is_top_level_block() {
		return false;
	}

	private class TelefonFaxRow extends AR_DataRow {

		private RECORD_KOMMUNIKATION rec_kom = null;
		public TelefonFaxRow(RECORD_KOMMUNIKATION kom) {
			super();
			this.rec_kom = kom;
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int i_left_cols = this.get_DataBlockThisBelongsTo().get_complete_insets();
			int iColorDiffMulti = ((AI_AdressBlock_NG)this.find_top_levelBlock_in_chain()).get_colorDiff();

			int iColorDiff = iColorDiffMulti*i_left_cols;
			
			IF_AR_Component[][] comp = new IF_AR_Component[1][2];
			comp[0][0] = new AR_Label(	new AR_LayoutData(4,get_topAdressBlock().get_insets(),iColorDiff), 
											this.rec_kom.get___KETTE(	KOMMUNIKATION.wert_laendervorwahl.fn(),
																									KOMMUNIKATION.wert_vorwahl.fn(),
																									KOMMUNIKATION.wert_rufnummer.fn()), 
											new AR_StyleLabel(0,true));
			
			RECORD_KOMMUNIKATIONS_TYP rt = this.rec_kom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ();
			

			comp[0][1] =  new AR_Label(new AR_LayoutData(20-i_left_cols-4,get_topAdressBlock().get_insets(),iColorDiff), rt==null?"":rt.get_BEZEICHNUNG_cUF_NN(""), new AR_StyleLabel(0));
			
			return comp;
		}

		@Override
		public IF_AR_Component[][] _generate_titelComponentsInFrontOfRow() throws myException {
			return null;
		}

		@Override
		public IF_AR_Component[][] _generate_footComponentsAfterRow()	throws myException {
			return null;
		}
		
		@Override
		public boolean _must_be_filled() throws myException {
			return true;
		}

	}
	
	

}
