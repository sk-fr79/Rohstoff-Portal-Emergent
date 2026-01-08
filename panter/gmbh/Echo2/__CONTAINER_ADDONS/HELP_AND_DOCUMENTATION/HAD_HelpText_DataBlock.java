package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_MultiLineLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;

public class HAD_HelpText_DataBlock extends AR_DataBlock {
	
	//sammelvektor von oben. damit wird eine info transportiert, welche datensaetze markiert sind
	private Vector<HAD__CheckBox>   v_cbs = null;
	
	public HAD_HelpText_DataBlock(RECLIST_HILFETEXT reclist, String p_textEnthalten, Vector<HAD__CheckBox>   p_v_cbs) throws myException {
		super();

		this.v_cbs = p_v_cbs;
		
		for (String id: reclist.get_vKeyValues()) {
			this.add(new HAD_HelpText_DataRow(reclist.get(id),p_textEnthalten,this.v_cbs));
		}
		
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		
		IF_AR_Component[][] comp = new IF_AR_Component[1][5];
		int i=0;
		comp[0][i++] = new HAD_comp_Label(		new AR_LayoutData(1, E2_INSETS.I(0,5,10,5),-30),   new MyE2_String("Num.").CTrans(),  new E2_FontBold());
		comp[0][i++] = new AR_MultiLineLabel(	new AR_LayoutData(2, E2_INSETS.I(0,5,10,5),-30),   new E2_FontBold(),	new MyE2_String("Modul"),new MyE2_String("Version"),new MyE2_String("Status"));
		comp[0][i++] = new AR_MultiLineLabel(	new AR_LayoutData(14, E2_INSETS.I(0,5,10,5),-30),   new E2_FontBold(),	new MyE2_String("Titel"),new MyE2_String("Information"));
		comp[0][i++] = new AR_MultiLineLabel(	new AR_LayoutData(2, E2_INSETS.I(0,5,10,5),-30),   new E2_FontBold(),new MyE2_String("Gemeldet von"),new MyE2_String("Bearbeitet von"));
		comp[0][i++] = new HAD_comp_Label(		new AR_LayoutData(1, E2_INSETS.I(0,5,10,5),-30),"",new E2_FontBold());
		
		return comp;
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
		return false;
	}

}
