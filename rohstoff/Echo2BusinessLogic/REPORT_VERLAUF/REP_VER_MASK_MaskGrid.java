package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.indep.exceptions.myException;

public class REP_VER_MASK_MaskGrid extends E2_Grid {

	private RB_TransportHashMap   m_tpHashMap = null;
	
	public REP_VER_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		
		int iWidthComplete = REP_VER_CONST.REP_VER_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
				REP_VER_CONST.REP_VER_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
		
		this._setSize(iWidthComplete)._bo_no();


		this._setSize(240,600)._bo_no();

		this.m_tpHashMap = p_tpHashMap;
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);

		REP_VER_MASK_ComponentMap  map1 = (REP_VER_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());

		RB_gld gld_left = new RB_gld()._ins(2,4,2,4)._left_mid();//E2_ALIGN.LEFT_TOP);
		
		this
		._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_jasperfile)),	gld_left)
		._a(map1.getComp(REPORT_LOG.report_jasperfile),											gld_left)

		._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_datei_name)) ,	gld_left)
		._a(map1.getComp(REPORT_LOG.report_datei_name), 										gld_left)

		._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_am)) ,	gld_left)
		._a(map1.getComp(REPORT_LOG.report_druck_am), 											gld_left)

		._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_von)) ,	gld_left)
		._a(map1.getComp(REPORT_LOG.report_druck_von), 											gld_left)

		//        ._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_titel)) ,		gld_left)
		//        ._a(map1.getComp(REPORT_LOG.report_titel), 												gld_left)

		._a(new RB_lab(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_weg)) ,		gld_left)
		._a(map1.getComp(REPORT_LOG.report_weg), 												gld_left)

		._a(new RB_lab("Parameter"), 															new RB_gld()._ins(2,20,2,2)._al(E2_ALIGN.LEFT_TOP)._span(2))
		._a(map1.getComp(REP_VER_CONST.REP_VER_MASK_COMPONENT.PARAMETER_DETAIL.fk()),			new RB_gld()._ins(2,4,2,2)._al(E2_ALIGN.LEFT_TOP)._span(2));
	}

  
  	 /*
  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
  	  */
     public void resize(int width, int height) {
	   this.setWidth(new Extent(width-60));
	   this.setHeight(new Extent(height-170));
	 }
	
}

