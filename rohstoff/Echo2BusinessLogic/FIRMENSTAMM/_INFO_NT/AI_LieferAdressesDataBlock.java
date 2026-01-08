package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel0;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class AI_LieferAdressesDataBlock extends AR_DataBlock {

	private RECORD_ADRESSE_extend 	rec_Adresse   = null;
	private RECLIST_LIEFERADRESSE  	rl_la = null;  
	public AI_LieferAdressesDataBlock(RECORD_ADRESSE ra) throws myException {
		super();
		this.rec_Adresse = new RECORD_ADRESSE_extend(ra);

		this.rl_la = ra.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
		if (rl_la.size()>0) {
			RECLIST_ADRESSE       	rl_a  = new RECLIST_ADRESSE();
			for (RECORD_LIEFERADRESSE  la: rl_la) {
				rl_a.ADD(la.get_UP_RECORD_ADRESSE_id_adresse_liefer(), false);
			}
			//jetzt den sortierten Vector rausziehen
			Vector<RECORD_ADRESSE> v_adressen_sortiert = rl_a.GET_SORTED_VECTOR(bibVECTOR.get_Vector(ADRESSE.name1.fn(),ADRESSE.name2.fn()), true);
			for (RECORD_ADRESSE a: v_adressen_sortiert) {
				this.add(new LieferAdress(a));
			}
		}
	}

	public AI_AdressBlock  get_topAdressBlock() {
		return (AI_AdressBlock)this.find_top_levelBlock_in_chain();
	}


	@Override
	public boolean _must_be_filled() throws myException {
		AR_DataBlock  startBlock = this.find_top_levelBlock_in_chain();
		
		if (startBlock!=null) {
			IF_Saveable steuerknopf = ((AI_AdressBlock)startBlock).get_SteuerpultKnopf(AI__Const.BLOCK_TO_SHOW.LIEFERADRESSEN);
			if (steuerknopf!=null && steuerknopf instanceof AI__CheckBox) {
				return ((AI__CheckBox)steuerknopf).isSelected() && this.rl_la.size()>0;
			}
		}
		return this.rl_la.size()>0;
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 1;
	}

	@Override
	public boolean _is_top_level_block() {
		return false;
	}


	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		if (this.get_topAdressBlock().zeigeBeschriftung()) {
			IF_AR_Component[][] comps = new IF_AR_Component[1][1];
			comps[0][0] = new AR_Label(	new AR_LayoutData(19,get_topAdressBlock().get_insets(), -1*0),
										new MyE2_String("Lieferadressen",true," <",false,this.rec_Adresse.get__FullNameAndAdress_Typ1(),false,">",false),
										new AR_StyleLabel(false, true, true, -2));
			return comps;
		}
		return null;
	}


	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterBlock() throws myException {
//		IF_AR_Component[][] comps = new IF_AR_Component[1][1];
//		comps[0][0] = new AR_Label(" ",null,new AR_LayoutData(19,E2_INSETS.I(1,5,1,5),null,0));
		return null;
	}

	
	
	private class LieferAdress extends AR_DataRow {
		
		private RECORD_ADRESSE_extend recADRESS = null;
		
		public LieferAdress(RECORD_ADRESSE p_recADRESS) throws myException {
			super();
			this.recADRESS = new RECORD_ADRESSE_extend(p_recADRESS);
			this.add_daughter_DataBlock(new AI_TelefonFaxDataBlock(this.recADRESS, new MyE2_String("Lieferadresse").CTrans(),AI__Const.BLOCK_TO_SHOW.LIEFERADRESSEN_TELEFON));
			this.add_daughter_DataBlock(new AI_eMailDataBlock(this.recADRESS, new MyE2_String("Lieferadresse").CTrans(),AI__Const.BLOCK_TO_SHOW.LIEFERADRESSEN_MAIL));
		}

		@Override
		public IF_AR_Component[][] _generate_Components() throws myException {
			AR_StyleLabel0 style = new AR_StyleLabel0(false,false,false);

			//je nach einrueckung die hintergrundfarbe etwas erhellen
			int iColorDiff = get_topAdressBlock().get_colorDiff()*this.get_DataBlockThisBelongsTo().get_complete_insets();
			
			IF_AR_Component[][] comps = new IF_AR_Component[1][4];
			comps[0][0] = new AR_Label(new AR_LayoutData(8,get_topAdressBlock().get_insets(),iColorDiff),	this.recADRESS.get_block_alle_namen(), style);
			comps[0][1] = new AR_Label(new AR_LayoutData(6,get_topAdressBlock().get_insets(),iColorDiff),	this.recADRESS.get_block_strasse_hr(), style);
			comps[0][2] = new AR_Label(new AR_LayoutData(4,get_topAdressBlock().get_insets(),iColorDiff),		this.recADRESS.get_block_plz_ort(), style);
			comps[0][3] = new AI_AktivLabel(this.recADRESS.is_AKTIV_YES(), 	new AR_LayoutData(1,get_topAdressBlock().get_insets(),new Alignment(Alignment.RIGHT,Alignment.TOP),iColorDiff));
			
			return comps;
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
		
			AR_DataBlock  startBlock = this.find_top_levelBlock_in_chain();
			
			if (startBlock==null) {
				return true;
			}
			
			IF_Saveable  steuerknopf = ((AI_AdressBlock)startBlock).get_SteuerpultKnopf(AI__Const.BLOCK_TO_SHOW.INAKTIVE_LIEFERADRESSEN_AUSBLENDEN);
			return this.recADRESS.is_AKTIV_YES() || (!((AI__CheckBox)steuerknopf).isSelected());
			
		}

	}
	
}
