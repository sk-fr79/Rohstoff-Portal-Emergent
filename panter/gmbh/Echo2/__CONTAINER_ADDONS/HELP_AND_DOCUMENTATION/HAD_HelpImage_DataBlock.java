package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataBlock;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Separator;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;

public class HAD_HelpImage_DataBlock extends AR_DataBlock {

	private RECORD_HILFETEXT  recHilfe = null;
	private RECLIST_ARCHIVMEDIEN_ext  ra = null;
	
	
	public HAD_HelpImage_DataBlock(RECORD_HILFETEXT p_recHilfe) throws myException {
		super();
		this.recHilfe = p_recHilfe;
		
		this.ra = new RECLIST_ARCHIVMEDIEN_ext(_TAB.hilfetext.n(),this.recHilfe.get_ID_HILFETEXT_cUF(),null,null,ARCHIVMEDIEN.downloadname.fn());

		for (String id: ra.get_vKeyValues()) {
			this.add(new HAD_HelpImage_DataRow(ra.get(id)));
		}
		
		
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfBlock() throws myException {
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterBlock()	throws myException {
		IF_AR_Component[][] comps = new IF_AR_Component[1][1];
		comps[0][0]= new AR_Separator(new AR_LayoutData(17, E2_INSETS.I(0,2,0,10)));
		
		return comps;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		return this.ra.get_vKeyValues().size()>0;
	}

	@Override
	public int _get_i_cols_to_inset_relativ_to_mother() {
		return 3;
	}

	@Override
	public boolean _is_top_level_block() {
		return false;
	}

}
