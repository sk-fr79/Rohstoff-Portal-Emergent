package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class HAD_HelpImage_DataRow extends AR_DataRow {

	
	private RECORD_ARCHIVMEDIEN_ext   recArch = null;
	
	public HAD_HelpImage_DataRow(RECORD_ARCHIVMEDIEN   p_recArch) {
		super();
		this.recArch = new RECORD_ARCHIVMEDIEN_ext(p_recArch);
	}

	@Override
	public IF_AR_Component[][] _generate_Components() throws myException {
		
		IF_AR_Component[][] arrComp = new IF_AR_Component[1][1];
		
		//arrComp[0][0]=new HAD_Image(new AR_LayoutData(18, E2_INSETS.I(2,2,4,3)), this.recArch.get_LabelWithImage());
		arrComp[0][0]=new HAD_comp_ButtonImage(new AR_LayoutData(17, E2_INSETS.I(2,2,4,3), new Alignment(Alignment.CENTER, Alignment.TOP),-20), this.recArch);
		//arrComp[0][0]=new AR_ImageIcon(this.recArch,new AR_LayoutData(18, E2_INSETS.I(2,2,4,3))).setWidth(100);
		
		
		return arrComp;
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfRow()	throws myException {
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterRow()	throws myException {
		IF_AR_Component[][] comp = new IF_AR_Component[1][1];
		
		comp[0][0] = new HAD_comp_Label(new AR_LayoutData(17, E2_INSETS.I(0,5,5,5),0),"",new E2_FontBold());
	
		return comp;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		return true;
	}

}
