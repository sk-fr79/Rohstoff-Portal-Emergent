package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_DAUGHTER_MITARBEITER extends XX_FULL_DAUGHTER
{

	private FSM_ModulContainer_LIST oListContainer = null;


	public FS_Component_MASK_DAUGHTER_MITARBEITER(	SQLFieldMAP 					osqlFieldGroup,
													E2_BasicModuleContainer_MASK	oModulContainerMASK) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldGroup.get_("ID_ADRESSE"));
		this.oListContainer = new FSM_ModulContainer_LIST();
		this.set_bIsActive(false);

	}

	
	/*
	 * fuellen des maskenobjektes mit der gerade aktuellen adress-id 
	 * 
	 */
	public Component build_content_for_Mask(String cValueFormated,String cValueUnformated, String cMASK_STATUS) throws myException
	{
		
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Mitarbeiter können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
//			this.setMaskTitle();
			
			this.oListContainer.set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oMASK_Container().set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oSelVector().doActionPassiv();
			return this.oListContainer;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
	}

	
//	private void setMaskTitle() throws myException
//	{
//		/*
//		 * jetzt eine info in die maske schreiben (falls diese benutzt wird
//		 */
//		E2_ComponentMAP oMapBaseAdress = this.EXT().get_oComponentMAP();
//		SQLResultMAP oResult = oMapBaseAdress.get_oInternalSQLResultMAP();
//		String cInfoAdress = bibALL.null2leer(oResult.get_FormatedValue("NAME1"))+" - "+
//							  bibALL.null2leer(oResult.get_FormatedValue("STRASSE"))+" - "	+					
//							  bibALL.null2leer(oResult.get_FormatedValue("ORT"));
//		
//		MyE2_String oString = new MyE2_String("Mitarbeiter bei der Firma: ");
//		oString.addUnTranslated(cInfoAdress);
//		MyE2_Label oLabInfo = new MyE2_Label(oString);
//		oLabInfo.setFont(new E2_FontItalic(-2));
//		this.oListContainer.get_oMASK_Container().add_Headline(oLabInfo);
//
//	}
	
	
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		this.oListContainer.get_oBT_DELETE_MITARBEITER().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_EDIT_MITARBEITER().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_VIEW_MITARBEITER().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_NEW_MITARBEITER().set_bEnabled_For_Edit(false);
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oListContainer.get_oBT_DELETE_MITARBEITER().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_EDIT_MITARBEITER().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_VIEW_MITARBEITER().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_NEW_MITARBEITER().set_bEnabled_For_Edit(true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oListContainer.get_oBT_VIEW_MITARBEITER().set_bEnabled_For_Edit(true);
		}
		
		
	}

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Mitarbeiter können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!")));

	}


	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}




}
