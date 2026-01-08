package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Separator;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel2;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG.BLOCK_TO_SHOW;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI_AktivLabel;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class AI_AdressRow_NG extends AR_DataRow {

	private RECORD_ADRESSE recAdresse = null;
	private boolean        add_end_line = false;

	public AI_AdressRow_NG(RECORD_ADRESSE p_recAdress, boolean p_add_end_line) throws myException {
		super();
		this.recAdresse = p_recAdress;
		this.add_end_line = p_add_end_line;
				
		this.add_daughter_DataBlock(new AI_LieferAdressesDataBlock_NG(this.recAdresse));
		this.add_daughter_DataBlock(new AI_MitarbeiterAdressDataBlock_NG(this.recAdresse));
		this.add_daughter_DataBlock(new AI_TelefonFaxDataBlock_NG(this.recAdresse, new MyE2_String("Hauptadresse").CTrans(),BLOCK_TO_SHOW.TELEFON));
		this.add_daughter_DataBlock(new AI_eMailDataBlock_NG(this.recAdresse, new MyE2_String("Hauptadresse").CTrans(),BLOCK_TO_SHOW.EMAIL));
		this.add_daughter_DataBlock(new AI_ZusatzInfosDataBlock_NG(this.recAdresse));
//		this.add_daughter_DataBlock(new AI_MeldungenDataBlock(this.recAdresse));
//		this.add_daughter_DataBlock(new AI_ZusatzDateienDataBlock(	ADRESSE.baseTabName(),
//																	this.recAdresse.get_ID_ADRESSE_cUF(), 
//																	new MyE2_String("Zusatzdateien zur Hauptadresse").CTrans(),
//																	1,
//																	AI__Const_NG.BLOCK_TO_SHOW.ANLAGEN));
//		
	}

	@Override
	public IF_AR_Component[][] _generate_Components() throws myException {
		AR_StyleLabel2 style = new AR_StyleLabel2(true,false,false); 
		
		int iColorDiff = ((AI_AdressBlock_NG)this.find_top_levelBlock_in_chain()).get_colorDiff();
		
		if (this.recAdresse != null) {
			RECORD_ADRESSE_extend ra = new RECORD_ADRESSE_extend(
					this.recAdresse);

			IF_AR_Component c_rueck[][] = new IF_AR_Component[1][5];
			
			c_rueck[0][0] = new AR_Label(new AR_LayoutData(1,this.get_topAdressBlock().get_insets(),-1*iColorDiff),	ra.get_ID_ADRESSE_cF(), style);
			c_rueck[0][1] = new AR_Label(new AR_LayoutData(8,this.get_topAdressBlock().get_insets(),-1*iColorDiff),ra.get_block_alle_namen_incl_anrede(), style);
			c_rueck[0][2] = new AR_Label(new AR_LayoutData(6,this.get_topAdressBlock().get_insets(),-1*iColorDiff),ra.get_block_strasse_hr(), style);
			c_rueck[0][3] = new AR_Label(new AR_LayoutData(4,this.get_topAdressBlock().get_insets(),-1*iColorDiff),	ra.get_block_plz_ort(), style);
			c_rueck[0][4] = new AI_AktivLabel(this.recAdresse.is_AKTIV_YES(), 	new AR_LayoutData(1,get_topAdressBlock().get_insets(),new Alignment(Alignment.RIGHT,Alignment.TOP),-1*iColorDiff));

			return c_rueck;
		}

		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfRow() throws myException {
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterRow() throws myException {
		if (this.add_end_line) {
			IF_AR_Component[][] comp = new IF_AR_Component[1][1];
			comp[0][0] = new AR_Separator(new AR_LayoutData(20,E2_INSETS.I(0,2,0,2),null,null));
			return comp;
		}
		return null;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		return true;
	}
	
	
	public AI_AdressBlock_NG  get_topAdressBlock() {
		return (AI_AdressBlock_NG)this.find_top_levelBlock_in_chain();
	}

}
