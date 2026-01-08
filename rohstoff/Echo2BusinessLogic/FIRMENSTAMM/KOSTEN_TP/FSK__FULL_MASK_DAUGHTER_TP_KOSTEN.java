package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK__FULL_MASK_DAUGHTER_TP_KOSTEN extends XX_FULL_DAUGHTER
{
	public static String    FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE = "FIELD_ZAUBERSTAB_FUER_KOSTEN_IN_ZEILE";

	private FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER oListContainer = null;


	public FSK__FULL_MASK_DAUGHTER_TP_KOSTEN(	SQLFieldMAP 					osqlFM,
												E2_BasicModuleContainer_MASK	oOwnE2_ModulContainerMASK) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFM.get_("ID_ADRESSE"));
		this.oListContainer = new FSK__FULL_MASK_DAUGHTER_TP_KOSTEN_LIST_CONTAINER(oOwnE2_ModulContainerMASK);
		this.set_bIsActive(false);
	}

	
	/*
	 * fuellen des maskenobjektes mit der gerade aktuellen adress-id 
	 * 
	 */
	public Component build_content_for_Mask(String cValueFormated,String cValueUnformated, String cMASK_STATUS) throws myException
	{
		
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		/*
		 * falls der listcontainer vorher im status edit war und jetzt neu aufgebaut wird,
		 * mussen evtl. im vorigen aufbau vorhandene IDs geloescht werden 
		 */
		this.oListContainer.get_oBASIC_Bedienpanel().get_vID_EditActive().removeAllElements();
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))	{
			return new MyE2_Label(new MyE2_String("Zusatzinfos können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!"));
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))	{
			this.oListContainer.set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oComponentMAP_FSK().get_m_oAdresseStart().refreshDD(cValueUnformated);
			this.oListContainer.get_oComponentMAP_FSK().get_m_oArtikelDropdown().refreshDD(cValueUnformated);
			this.oListContainer.get_oNavigationList().Fill_NavigationList("");
			this.oListContainer.get_oBASIC_Bedienpanel().set_BUTTON_STATUS_VIEW();
			
			return this.oListContainer;
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)) {
			this.oListContainer.set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oComponentMAP_FSK().get_m_oAdresseStart().refreshDD(cValueUnformated);
			this.oListContainer.get_oComponentMAP_FSK().get_m_oArtikelDropdown().refreshDD(cValueUnformated);
			this.oListContainer.get_oNavigationList().Fill_NavigationList("");
			this.oListContainer.get_oBASIC_Bedienpanel().set_ALL_ButtonsEnabled_Without_Refresh(false);
			return this.oListContainer;
		} else {
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		}
		
		
		
	}

	
	

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Zusatzinfos können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!")));
	}


	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException {
		
	}

	public Component build_non_active_placeholder() throws myException {
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}


}
