package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import java.util.Vector;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LabelInBorder;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class AI_ZusatzInfosDataBlock extends AR_DataBlock {

	private RECORD_ADRESSE_extend 	rec_Adresse   = null;
	private RECLIST_ADRESSE_INFO 	rl_ai = 		null;
	
	public AI_ZusatzInfosDataBlock(RECORD_ADRESSE recAdresse) throws myException {
		super();
		
		this.rec_Adresse = new RECORD_ADRESSE_extend(recAdresse);
		this.rl_ai = recAdresse.get_DOWN_RECORD_LIST_ADRESSE_INFO_id_adresse(new vgl(ADRESSE_INFO.ist_message,"N").s(),null,true);
		
		if (rl_ai.size()>0) {
			Vector<RECORD_ADRESSE_INFO> v_ai = new Vector<RECORD_ADRESSE_INFO>();
			v_ai.addAll(rl_ai.GET_SORTED_VECTOR(bibALL.get_Vector(ADRESSE_INFO.datumeintrag.fn()), false));
			
			for (RECORD_ADRESSE_INFO ai: v_ai) {
				this.add(new ZusatzInfosRow(ai));
			}
		}
		
	}
	
	public AI_AdressBlock  get_topAdressBlock() {
		return (AI_AdressBlock)this.find_top_levelBlock_in_chain();
	}


	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		if (this.get_topAdressBlock().zeigeBeschriftung()) {
			IF_AR_Component[][] comp = new IF_AR_Component[1][1];
			comp[0][0] = new AR_Label(	new AR_LayoutData(20-this.get_complete_insets(),get_topAdressBlock().get_insets(),0), 
										new MyE2_String("Informationen zur Hauptadresse",true," <",false,this.rec_Adresse.get__FullNameAndAdress_Typ1(),false,">",false), 
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
		return (get_topAdressBlock().is_set(AI__Const.BLOCK_TO_SHOW.ZUSATZINFOS) && this.rl_ai.size()>0);
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 1;
	}

	@Override
	public boolean _is_top_level_block() {
		return false;
	}

	private class ZusatzInfosRow extends AR_DataRow {

		private RECORD_ADRESSE_INFO rec_ai = null;
		public ZusatzInfosRow(RECORD_ADRESSE_INFO ai) throws myException {
			super();
			this.rec_ai = ai;
			
			this.add_daughter_DataBlock(new AI_ZusatzDateienDataBlock(	ADRESSE_INFO.baseTabName(),
																		this.rec_ai.get_ID_ADRESSE_INFO_cUF(), 
																		new MyE2_String("Zusatzdateien zu Adress-Information").CTrans(),
																		3,
																		AI__Const.BLOCK_TO_SHOW.ZUSATZINFOS_ANLAGEN));
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int i_left_cols = this.get_DataBlockThisBelongsTo().get_complete_insets();

			int iColorDiff = get_topAdressBlock().get_colorDiff()*i_left_cols;
			
			IF_AR_Component[][] comp = new IF_AR_Component[1][2];
			comp[0][0] = new AR_LabelInBorder(	new AR_LayoutData(3,get_topAdressBlock().get_insets(),null,iColorDiff), 
											this.rec_ai.get_DATUMEINTRAG_cF_NN(""), 
											new AR_StyleLabel(0),
											new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));

			comp[0][1] = new AR_LabelInBorder(	new AR_LayoutData(20-i_left_cols-3,get_topAdressBlock().get_insets(),null,iColorDiff), 
												this.rec_ai.get_TEXT_cF_NN(""), 
												new AR_StyleLabel(0),
												new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
			
			
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
