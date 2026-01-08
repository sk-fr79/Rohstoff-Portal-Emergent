/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.MELDUNG_REITER
 * @author sebastien
 * @date 27.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_MELDUNG;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_StyleLabel2;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI_AktivLabel;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * ubersicht fur meldung
 * @author sebastien
 * @date 27.11.2018
 *
 */
public class FSI_MR_AdressRow extends AR_DataRow {

	private RECORD_ADRESSE recAdresse = null;

	private LinkedHashMap<String, IF_Saveable> steuerung_hashmap = null;
	
	public FSI_MR_AdressRow(RECORD_ADRESSE p_recAdress, LinkedHashMap<String, IF_Saveable> hm_Steuerpult) throws myException {
		super();
		this.recAdresse = p_recAdress;

		this.steuerung_hashmap = hm_Steuerpult;
		
		this.add_daughter_DataBlock(new FSI_MR_MeldungenDataBlock(this.recAdresse, this.steuerung_hashmap));

	}

	@Override
	public IF_AR_Component[][] _generate_Components() throws myException {
		AR_StyleLabel2 style = new AR_StyleLabel2(true,false,false); 

		int iColorDiff = ((FSI_MR_HauptDataBlock)this.find_top_levelBlock_in_chain()).get_colorDiff();

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
		return null;
	}


	@Override
	public boolean _must_be_filled() throws myException {
		return true;
	}

	public FSI_MR_HauptDataBlock  get_topAdressBlock() {
		return (FSI_MR_HauptDataBlock)this.find_top_levelBlock_in_chain();
	}

}
