package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG.BLOCK_TO_SHOW;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class AI_eMailDataBlock_NG extends AR_DataBlock {

	private String  				zusatz_text = "";
	private RECORD_ADRESSE_extend 	rec_Adresse   = null;
	private BLOCK_TO_SHOW  	   		used_Block = null;		
	private RECLIST_EMAIL 			rl_kom = null; 
	
	public AI_eMailDataBlock_NG(RECORD_ADRESSE recAdresse, String p_zusatztext, BLOCK_TO_SHOW block) throws myException {
		super();
		this.zusatz_text = p_zusatztext;
		this.used_Block = block;

		this.rec_Adresse = new RECORD_ADRESSE_extend(recAdresse);

		this.rl_kom = recAdresse.get_DOWN_RECORD_LIST_EMAIL_id_adresse();
		
		if (rl_kom.size()>0) {
			Vector<RECORD_EMAIL> v_komm = rl_kom.GET_SORTED_VECTOR(bibALL.get_Vector(EMAIL.email.fn()), true);
			
			for (RECORD_EMAIL komm: v_komm) {
				this.add(new EMailFaxRow(komm));
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
			comp[0][0] = new AR_Label(	new AR_LayoutData(20-this.get_complete_insets(),this.get_topAdressBlock().get_insets(),0), 
										new MyE2_String("eMail-Adressen ",true,this.zusatz_text,false," <",false,this.rec_Adresse.get__FullNameAndAdress_Typ2(),false,">",false), 
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
		return (this.get_topAdressBlock().is_set(this.used_Block) && this.rl_kom.size()>0);
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 1;
	}
	
	@Override
	public boolean _is_top_level_block() {
		return false;
	}

	
	private class EMailFaxRow extends AR_DataRow {

		private RECORD_EMAIL rec_kom = null;
		public EMailFaxRow(RECORD_EMAIL kom) {
			super();
			this.rec_kom = kom;
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			IF_AR_Component[][] comp = new IF_AR_Component[1][1];
		
			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int iColorDiff = get_topAdressBlock().get_colorDiff()*AI_eMailDataBlock_NG.this.get_complete_insets();
			
			comp[0][0] = new AR_Label(	new AR_LayoutData(20-AI_eMailDataBlock_NG.this.get_complete_insets(),get_topAdressBlock().get_insets(),iColorDiff), 
											this.rec_kom.get_EMAIL_cUF_NN(""), 
											new AR_StyleLabel(0,true));
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
