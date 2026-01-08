/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.Befundung
 * @author manfred
 * @date 02.07.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.Befundung;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Befund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;

/**
 * @author manfred
 * @date 02.07.2020
 *
 */
@Deprecated
public class WK_RB_CHILD_Befundung extends RB_MaskDaughterSimple {

	private E2_Grid 							_artikel_inlay 	 = null;
	
	private RB_TransportHashMap  				m_tpHashMap = null;
	
	
    
    
	public WK_RB_CHILD_Befundung( RB_TransportHashMap  p_tpHashMap ) throws myException {
		super();
		m_tpHashMap = p_tpHashMap;
			   
	}

	public void _initInlay() throws myException {
		this._clear();
		
        WK_RB_MASK_ComponentMap_Wiegekarte  mapWK = (WK_RB_MASK_ComponentMap_Wiegekarte) this.m_tpHashMap.getMaskComponentMapCollector().get(RecDOWiegekarte.key);
        WK_RB_MASK_ComponentMap_Befund		mapBef = (WK_RB_MASK_ComponentMap_Befund) this.m_tpHashMap.getMaskComponentMapCollector().get(RecDOWiegekarteBefund.key);
        
        this.setBorder(new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        _artikel_inlay = new E2_Grid()._setSize(100,600)._bo_b();
        
        _artikel_inlay
		        ._a(new RB_lab("Befundungsauftrag")._fsa(2), new RB_gld()._left_mid())
        		._a(mapWK.getComp(WIEGEKARTE.strahlung_geprueft), new RB_gld()._right_mid())
        		;
        
        _artikel_inlay
        		._a(new RB_lab("Durchführung von"), new RB_gld()._left_mid())
        		._a(mapBef.getComp(WIEGEKARTE_BEFUND.id_wiegekarte_user_befund), new RB_gld()._right_mid())
        		;
        
        E2_Grid _gridArbeitsauftrag = new E2_Grid()._bo_b()
        		._a(mapBef.getComp(WIEGEKARTE_BEFUND.sortierung), new RB_gld()._right_mid())
        		._a(mapBef.getComp(WIEGEKARTE_BEFUND.analyse), new RB_gld()._right_mid())
        		._a(mapBef.getComp(WIEGEKARTE_BEFUND.naesseprobe), new RB_gld()._right_mid());
        
        _artikel_inlay
				._a(new RB_lab("Arbeitsauftrag"), new RB_gld()._left_mid())
				._a(_gridArbeitsauftrag,new RB_gld()._left_top());
				;
        
        _artikel_inlay
				._a(new RB_lab("Bemerkung"), new RB_gld()._left_mid())
        		._a(mapBef.getComp(WIEGEKARTE_BEFUND.analyse), new RB_gld()._left_top())
				;
        
		this._a(_artikel_inlay);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_Neutral()
	 */
	@Override
	public void mark_Neutral() throws myException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_MustField()
	 */
	@Override
	public void mark_MustField() throws myException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_Disabled()
	 */
	@Override
	public void mark_Disabled() throws myException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_FalseInput()
	 */
	@Override
	public void mark_FalseInput() throws myException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		// inlay erst hier generieren
		_initInlay();

		if (dataObject.isStatusNew()) {
			
		} else {
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#rb_set_db_value_manual(java.lang.String)
	 */
	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {
		// TODO Auto-generated method stub
		
	}
	
	
    
}
