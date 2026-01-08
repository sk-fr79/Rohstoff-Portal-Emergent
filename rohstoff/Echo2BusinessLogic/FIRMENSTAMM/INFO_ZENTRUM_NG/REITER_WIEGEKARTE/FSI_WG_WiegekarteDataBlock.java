package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_WIEGEKARTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_ArchivMedien;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FSI_WG_WiegekarteDataBlock extends AR_DataBlock {

	private String  						zusatz_text = "";

	private int 							i_inset_block = 1;
	
	private ownReclistZusatzdateien 		rl_zu = null;

	public FSI_WG_WiegekarteDataBlock(String p_baseTable, String id_table_uf, String p_zusatztext, int p_inset_block, AI__Const_NG.BLOCK_TO_SHOW block) throws myException {
		super();

		this.zusatz_text = p_zusatztext;

		this.i_inset_block = p_inset_block;

		this.rl_zu = new ownReclistZusatzdateien(p_baseTable, id_table_uf);

		if (rl_zu.size()>0) {
			for (Rec21 zd: rl_zu) {
				this.add(new ZusatzMediumRow(zd));
			}
		}
	}

	public FSI_WG_DataBlock  get_topAdressBlock() {
		return (FSI_WG_DataBlock)this.find_top_levelBlock_in_chain();
	}


	private class ownReclistZusatzdateien extends RecList21{

		public ownReclistZusatzdateien(String baseTable, String id_adresse_uf) throws myException {
			super(_TAB.archivmedien);
			Rec21_adresse recAddr = new Rec21_adresse()._fill_id(id_adresse_uf);

			String abfrage = null;

			SEL sel1 = new SEL(_TAB.archivmedien).FROM(_TAB.archivmedien)
					.INNERJOIN(_TAB.wiegekarte, ARCHIVMEDIEN.id_table, WIEGEKARTE.id_wiegekarte.fn())
					.INNERJOIN(_TAB.lieferadresse, LIEFERADRESSE.id_adresse_liefer, WIEGEKARTE.id_adresse_lieferant)
					.INNERJOIN(_TAB.adresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_liefer)
					.WHERE(new vgl(ARCHIVMEDIEN.tablename, _TAB.wiegekarte.baseTableName()))
					.AND(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT()))
					.AND(new vgl(ARCHIVMEDIEN.medienkenner, "PRINT"))
					.OR(new vgl(ARCHIVMEDIEN.medienkenner, "WIEGEKARTE"))
					.AND(new vgl_YN(ADRESSE.aktiv,true))
					.AND(new vgl(LIEFERADRESSE.id_adresse_basis,recAddr._getMainAdresse().get_key_value()))
					;					

			SEL sel2 =new SEL(_TAB.archivmedien).FROM(_TAB.wiegekarte)
					.INNERJOIN(_TAB.archivmedien, ARCHIVMEDIEN.id_table, WIEGEKARTE.id_wiegekarte.fn())
					.INNERJOIN(_TAB.adresse, ADRESSE.id_adresse, WIEGEKARTE.id_adresse_lieferant.fn())
					.WHERE(new vgl(ARCHIVMEDIEN.tablename, _TAB.wiegekarte.baseTableName()))
					.AND(new vgl(ARCHIVMEDIEN.medienkenner, "PRINT"))
					.OR(new vgl(ARCHIVMEDIEN.medienkenner, "WIEGEKARTE"))
					.AND(new vgl_YN(ADRESSE.aktiv, true))
					.AND(new vgl(WIEGEKARTE.id_mandant, bibALL.get_ID_MANDANT()))
					.AND(new vgl(WIEGEKARTE.id_adresse_lieferant,recAddr._getMainAdresse().get_key_value()))
					.OR(new vgl(WIEGEKARTE.id_adresse_spedition,recAddr._getMainAdresse().get_key_value()))
					;
			
			abfrage = "("+sel1.s() +") UNION (" + sel2.s()  +") ORDER BY ERSTELLUNGSDATUM DESC";

			this._fill(abfrage);
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

		private Rec21 rec_am = null;
		public ZusatzMediumRow(Rec21 zd) {
			super();
			this.rec_am = zd;
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int i_left_cols = this.get_DataBlockThisBelongsTo().get_complete_insets();

			int iColorDiffMulti = ((FSI_WG_DataBlock)this.find_top_levelBlock_in_chain()).get_colorDiff();

			int iColorDiff = iColorDiffMulti*i_left_cols;

			String kenner = "";

//			Rec21 rec_wiegekarte = this.rec_am.get_up_Rec21(ARCHIVMEDIEN.id_table,WIEGEKARTE.id_wiegekarte,false);
//			Rec21_adresse recAddr = new Rec21_adresse()._fill_id(id_adresse__quelle);
//			if(! recAddr.__is_main_adresse()) {
//				kenner = "Lager: " + recAddr.__get_name1_ort();
//			}else {
//				if(recAddr.get_key_value().equals(rec_wiegekarte.getUfs(WIEGEKARTE.id_adresse_spedition))){
//					kenner = "Spediteur: " + recAddr.__get_name1_ort();
//				}
//			}

			IF_AR_Component[][] comp = new IF_AR_Component[1][4];
			comp[0][0] = new AR_Label(	new AR_LayoutData(3,get_topAdressBlock().get_insets(),iColorDiff), 
					this.rec_am.getFs(ARCHIVMEDIEN.erstellungsdatum,""), 
					new AR_StyleLabel(0));

			comp[0][2] = new AR_Label(	new AR_LayoutData(5,get_topAdressBlock().get_insets(),iColorDiff), 
					kenner,
					new AR_StyleLabel(false, true, true,-1));

			comp[0][1] = new AR_Label(	new AR_LayoutData(15-i_left_cols-3-1,get_topAdressBlock().get_insets(),iColorDiff), 
					this.rec_am.getFs(ARCHIVMEDIEN.downloadname,""), 
					new AR_StyleLabel(0));

			comp[0][3] = new ownDownButton(	new Rec21_ArchivMedien(this.rec_am), new AR_LayoutData(1,null,new Alignment(Alignment.RIGHT, Alignment.TOP),iColorDiff)
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


	private class ownDownButton extends E2_Button implements IF_AR_Component{
		private Rec21_ArchivMedien recAM = null;
		private GridLayoutData          gl = null;

		public ownDownButton(Rec21_ArchivMedien p_recAM, GridLayoutData p_gl) throws myException {
			super();
			this._image(E2_ResourceIcon.get_RI("down_mini.png"));
			this.recAM = p_recAM;
			this.gl = p_gl;

			this.add_GlobalValidator(this.recAM.generate_ButtonValidator4Download());

			this.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownDownButton.this.recAM.starteDownLoad();
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
