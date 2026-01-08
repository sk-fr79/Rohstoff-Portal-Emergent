/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.MELDUNG_REITER
 * @author sebastien
 * @date 27.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_MELDUNG;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__Const_NG;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI__CheckBox;

/**
 * ubersicht fur meldung
 * @author sebastien
 * @date 27.11.2018
 *
 */
public class FSI_MR_HauptDataBlock extends AR_DataBlock {

	private VEK<String> v_id_adresses = new VEK<String>();
	
	private LinkedHashMap<String, IF_Saveable> hm_Steuerpult = new LinkedHashMap<String, IF_Saveable>();

	public FSI_MR_HauptDataBlock(Vector<String> id_adresses, LinkedHashMap<String, IF_Saveable>  	p_hm_Steuerpult) throws myException{
		super();
		this.v_id_adresses.addAll(id_adresses);
		this.hm_Steuerpult = p_hm_Steuerpult;

		for (String id: id_adresses) {
			this.add(new FSI_MR_AdressRow(new RECORD_ADRESSE(id),hm_Steuerpult));
		}
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() 	throws myException {
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterBlock()	throws myException {
		return null;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		return true;
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 0;
	}

	@Override
	public boolean _is_top_level_block() {
		return true;
	}

	public IF_Saveable get_SteuerpultKnopf(String block) {
		return this.hm_Steuerpult.get(block);
	}

	
	public int get_Abstand() throws myException {
		int abstand = 0;
		try {
			abstand = new MyInteger(this.get_SteuerpultKnopf(ENUM_Selectfield_Meldung.SF_INSETS.name()).get_value_to_save()).get_iValue();
		} catch (Exception e) {
			e.printStackTrace();
			abstand = 0;
		}

		return abstand;
	}

	
	public int get_colorDiff() throws myException {
		int diff =10;
		try {
			diff = new MyInteger(this.get_SteuerpultKnopf(ENUM_Selectfield_Meldung.SF_FARBDIFF.name()).get_value_to_save()).get_iValue();
		} catch (Exception e) {
			e.printStackTrace();
			diff = 10;
		}

		return diff;
	}
	
	
	public boolean is_set(String block) throws myException {
		boolean rueck = true;
		
		try {
			if (this.get_SteuerpultKnopf(block) instanceof AI__CheckBox) {
				rueck=(this.get_SteuerpultKnopf(block).get_value_to_save().equals("Y"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rueck;
	}

	
	public boolean zeigeBeschriftung() throws myException {
		return this.is_set(AI__Const_NG.BLOCK_TO_SHOW.BESCHRIFTUNG.name());
	}
	
	
	public Insets get_insets() throws myException {
		return E2_INSETS.I(2, this.get_Abstand(), 10, this.get_Abstand());
	}
}
