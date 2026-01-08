package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_ZUSATZDATEI;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class FSI_ZDR_ZusatzDateienDataBlock extends AR_DataBlock {

	private String  						zusatz_text = "";
	
	private int 							i_inset_block = 1;
	private ownReclistZusatzdateien 		rl_zu = null;
	
	public FSI_ZDR_ZusatzDateienDataBlock(String p_baseTable, String id_table_uf, String p_zusatztext, int p_inset_block, AI__Const_NG.BLOCK_TO_SHOW block) throws myException {
		super();
		
		this.zusatz_text = p_zusatztext;
		
		this.i_inset_block = p_inset_block;
		
		this.rl_zu = new ownReclistZusatzdateien(p_baseTable, id_table_uf);
		
		if (rl_zu.size()>0) {
			Vector<RECORD_ARCHIVMEDIEN> v_zu = new Vector<RECORD_ARCHIVMEDIEN>();
			v_zu.addAll(rl_zu.GET_SORTED_VECTOR(bibALL.get_Vector(ARCHIVMEDIEN.erstellungsdatum.fn()), false));
			
			for (RECORD_ARCHIVMEDIEN zd: v_zu) {
				this.add(new ZusatzMediumRow(zd));
			}
		}
		
	}
	
	public FSI_ZDR_DataBlock  get_topAdressBlock() {
		return (FSI_ZDR_DataBlock)this.find_top_levelBlock_in_chain();
	}

	
	private class ownReclistZusatzdateien extends RECLIST_ARCHIVMEDIEN {

		public ownReclistZusatzdateien(String baseTable, String id_adresse_uf) throws myException {
			super();
			
			SEL sel = new SEL("*").FROM(ARCHIVMEDIEN.T())
							.WHERE(new vgl(ARCHIVMEDIEN.tablename,baseTable)).AND(new vgl(ARCHIVMEDIEN.id_table,id_adresse_uf))
							.AND(ARCHIVMEDIEN.medienkenner.fn(), COMP.NOT_EQ.ausdruck(), "'WIEGEKARTE'")
							.AND(ARCHIVMEDIEN.tablename.fn(), COMP.NOT_EQ.ausdruck(), "'"+_TAB.wiegekarte.baseTableName()+"'")
							.ORDERUP(ARCHIVMEDIEN.downloadname);
			
			this.set_cQueryString(sel.s());
			this.REFRESH();
		}
		
	}
	

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		if (this.get_topAdressBlock().zeigeBeschriftung()) {
			IF_AR_Component[][] comp = new IF_AR_Component[1][1];
			comp[0][0] = new AR_Label(new AR_LayoutData(20-this.get_complete_insets(),get_topAdressBlock().get_insets(),0), this.zusatz_text, new AR_StyleLabel(false, true, true, -2));
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
		return this.rl_zu.size()>0;

	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return this.i_inset_block;
	}

	@Override
	public boolean _is_top_level_block() {
		return false;
	}

	private class ZusatzMediumRow extends AR_DataRow {

		private RECORD_ARCHIVMEDIEN rec_am = null;
		public ZusatzMediumRow(RECORD_ARCHIVMEDIEN am) {
			super();
			this.rec_am = am;
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int i_left_cols = this.get_DataBlockThisBelongsTo().get_complete_insets();
			
			int iColorDiffMulti = ((FSI_ZDR_DataBlock)this.find_top_levelBlock_in_chain()).get_colorDiff();

			int iColorDiff = iColorDiffMulti*i_left_cols;
			
			IF_AR_Component[][] comp = new IF_AR_Component[1][3];
			comp[0][0] = new AR_Label(	new AR_LayoutData(3,get_topAdressBlock().get_insets(),iColorDiff), 
										this.rec_am.get_ERSTELLUNGSDATUM_cF_NN(""), 
										new AR_StyleLabel(0));

			comp[0][1] = new AR_Label(	new AR_LayoutData(20-i_left_cols-3-1,get_topAdressBlock().get_insets(),iColorDiff), 
												this.rec_am.get_DOWNLOADNAME_cF_NN(""), 
												new AR_StyleLabel(0));
			
			comp[0][2] = new ownDownButton(	new RECORD_ARCHIVMEDIEN_ext(this.rec_am), 
													new AR_LayoutData(1,null,new Alignment(Alignment.RIGHT, Alignment.TOP),iColorDiff)
											);
			
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
	
	
	private class ownDownButton extends  MyE2_Button implements IF_AR_Component{
		private RECORD_ARCHIVMEDIEN_ext recAM = null;
		private GridLayoutData          gl = null;

		public ownDownButton(RECORD_ARCHIVMEDIEN_ext p_recAM, GridLayoutData p_gl) throws myException {
			super(E2_ResourceIcon.get_RI("down_mini.png"));
			this.recAM = p_recAM;
			this.gl = p_gl;
			this.add_GlobalValidator(this.recAM.generate_ButtonValidator4Download());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownDownButton.this.recAM.starte__downLoad();
				}
			});
		}

		
		@Override
		public GridLayoutData get_layoutData() {
			return gl;
		}


		@Override
		public Component comp() {
			return this;
		}
	}


}
