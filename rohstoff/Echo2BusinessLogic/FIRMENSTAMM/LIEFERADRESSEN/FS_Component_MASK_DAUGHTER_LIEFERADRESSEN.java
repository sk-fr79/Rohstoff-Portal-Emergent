package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class FS_Component_MASK_DAUGHTER_LIEFERADRESSEN extends XX_FULL_DAUGHTER
{

	private FSL_ModulContainer_LIST oListContainer = null;


	public FS_Component_MASK_DAUGHTER_LIEFERADRESSEN(	SQLFieldMAP 			osqlFieldMap,
														FS_ModulContainer_MASK	oModulContainerMASK) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMap.get_("ID_ADRESSE"));
		this.oListContainer = new FSL_ModulContainer_LIST();
		this.set_bIsActive(false);
	}

	
	
	/*
	 * fuellen des maskenobjektes mit der gerade aktuellen adress-id 
	 * 
	 */
	public Component build_content_for_Mask(String cValueFormated,String cValueUnformated, String cMASK_STATUS) throws myException
	{
		
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		boolean bAdresseIstMandant = (bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--").equals(cValueUnformated));
		
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Lieferadressen können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.setMaskTitle();
			
//			((FS_Component_MASK_DAUGHTER_LIEFERADRESSEN) oMAP.get__Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN))
			
			
			
//			this.get_oListContainer().get_oNavigationList()
//									.get_oComponentMAP__REF()
//									.get__Comp("ADRESS_INFO")
//									.EXT().set_bIsVisibleInList(bAdresseIstMandant);
			this.get_oListContainer().get_oNavigationList()
									.get_oComponentMAP__REF()
									.get__Comp(FSL_ListCompFremdAdressen.key)
									.EXT().set_bIsVisibleInList(bAdresseIstMandant);
			
			
			this.oListContainer.set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oMASK_Container().set_BASE_ADRESS_ID(cValueUnformated);
//			this.oListContainer.get_oNavigationList().Fill_NavigationList("");
			
			//2014-01-07: selector vor jedem maskenaufbau neutralisieren
			this.oListContainer.get_oSelector().set_Neutral();
			this.oListContainer.get_oSelector().get_oSelVector().doActionPassiv();
			//jetzt ausblenden, wenn es nicht der mandant ist
			this.oListContainer.get_oSelector().setVisible(bAdresseIstMandant);
			
			
			
			
			return this.oListContainer;
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.setMaskTitle();

//			this.get_oListContainer().get_oNavigationList()
//									.get_oComponentMAP__REF()
//									.get__Comp("ADRESS_INFO")
//									.EXT().set_bIsVisibleInList(bAdresseIstMandant);

			this.get_oListContainer().get_oNavigationList()
									.get_oComponentMAP__REF()
									.get__Comp(FSL_ListCompFremdAdressen.key)
									.EXT().set_bIsVisibleInList(bAdresseIstMandant);

			
			this.oListContainer.set_BASE_ADRESS_ID(cValueUnformated);
			this.oListContainer.get_oMASK_Container().set_BASE_ADRESS_ID(cValueUnformated);
			//2014-01-07: selector vor jedem maskenaufbau neutralisieren
			this.oListContainer.get_oSelector().set_Neutral();
			this.oListContainer.get_oSelector().get_oSelVector().doActionPassiv();
			//jetzt ausblenden, wenn es nicht der mandant ist
			this.oListContainer.get_oSelector().setVisible(bAdresseIstMandant);

			
			return this.oListContainer;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		
		
		
	}

	
	private void setMaskTitle() throws myException
	{
		/*
		 * jetzt eine info in die maske schreiben (falls diese benutzt wird
		 */
		E2_ComponentMAP oMapBaseAdress = this.EXT().get_oComponentMAP();
		SQLResultMAP oResult = oMapBaseAdress.get_oInternalSQLResultMAP();
		String cInfoAdress = bibALL.null2leer(oResult.get_FormatedValue("NAME1"))+" - "+
							  bibALL.null2leer(oResult.get_FormatedValue("STRASSE"))+" - "	+					
							  bibALL.null2leer(oResult.get_FormatedValue("ORT"));
		
		MyE2_String oString = new MyE2_String("Lieferadresse bei der Firma: ");
		oString.addUnTranslated(cInfoAdress);
		MyE2_Label oLabInfo = new MyE2_Label(oString);
		oLabInfo.setFont(new E2_FontItalic(-2));
		this.oListContainer.get_oMASK_Container().add_Headline(oLabInfo);

	}
	
	
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		this.oListContainer.get_oBT_DELETE_LIEFERADRESSE().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_EDIT_LIEFERADRESSE().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_VIEW_LIEFERADRESSE().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oBT_NEW_LIEFERADRESSE().set_bEnabled_For_Edit(false);
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oListContainer.get_oBT_DELETE_LIEFERADRESSE().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_EDIT_LIEFERADRESSE().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_VIEW_LIEFERADRESSE().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oBT_NEW_LIEFERADRESSE().set_bEnabled_For_Edit(true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oListContainer.get_oBT_VIEW_LIEFERADRESSE().set_bEnabled_For_Edit(true);
		}


	}

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Lieferadressen können erst erfasst werden, wenn die Firmenadresse gespeichert wurde!")));

	}


	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}



	/**
	 * @return the oListContainer
	 */
	public FSL_ModulContainer_LIST get_oListContainer() {
		return oListContainer;
	}



}
