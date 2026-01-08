package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer_ActionOnClose;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_COL_Status_Kunde;

public class FS_Component_MASK_INFO_FIRMENSTATUS extends XX_FULL_DAUGHTER implements E2_BasicModuleContainer_ActionOnClose {

	//	private String m_sIDAdresse = null;
	private E2_ComponentMAP	m_oComponentMAPParent  = null;
	STATKD_COL_Status_Kunde m_oBlockStatus     = null;
	
	 
	public FS_Component_MASK_INFO_FIRMENSTATUS(	SQLFieldMAP 			osqlFieldMap,
												FS_ModulContainer_MASK	oModulContainerMASK) throws myException{
		super((SQLFieldForPrimaryKey)osqlFieldMap.get_("ID_ADRESSE"));
		
		m_oComponentMAPParent = oModulContainerMASK.get_oComponentMAP();
		m_oBlockStatus = new STATKD_COL_Status_Kunde(oModulContainerMASK);
		

         
        this.add(m_oBlockStatus);
        this.set_bIsActive(false);
	}

	


	@Override
	public Component build_content_for_Mask(String cValueFormated,
			String cValueUnformated, String cMASK_STATUS) throws myException {
		
		MyE2_Grid oGridInner = new MyE2_Grid(1,0);
		MyE2_Label oHeading = new MyE2_Label(new MyE2_String("Aktueller Kundenstatus Forderungen / Verbindlichkeiten") );
		oHeading.setStyle(MyE2_Label.STYLE_TITEL_BIG());
		
		oGridInner.add(oHeading);
        oGridInner.add(m_oBlockStatus);
		
		m_oBlockStatus.setIDAdresse(cValueUnformated);
		m_oBlockStatus.refresh();
        

		return oGridInner; 
	}


	@Override
	public Component build_non_active_placeholder() throws myException {
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}


	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException {
		
	}


	@Override
	public void prepare_DaughterContentForNew() throws myException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void do_ActionOnClose(E2_BasicModuleContainer oContainer)	throws myException {
		this.set_bIsActive(false);
	}
	
}
