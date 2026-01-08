package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

//done

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_L_ActionAgentCopyVorgang;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;

public class BSRG_K_LIST_BT_COPY_VORGANG extends MyE2_Button
{
	public BSRG_K_LIST_BT_COPY_VORGANG(E2_NavigationList onavigationList,BSRG_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png"), E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new own_BS_L_ActionAgentCopyVorgang(onavigationList,this,"Vorgang",omaskContainer.get_SETTING()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"KOPIEREN_VORGANG"));
		this.setToolTipText(new MyE2_String("Kopieren eines (oder mehrerer) Vorgänge").CTrans());
		
		//aenderung 2010-12-22: keine kopie von inaktiven adressen
		this.add_IDValidator(new ownValidatior());
	}
	
	
	//aenderung 2010-12-22: keine kopie von inaktiven adressen
	private class ownValidatior extends valid_KopiereNurBelegeMitAktiveAdressen
	{

		@Override
		public VectorSingle SammleAdressIDs(String cID_BelegToCopy)		throws myException 
		{
			VectorSingle vRueck = new VectorSingle();
			
			RECORD_VKOPF_RG  recVkopf = new RECORD_VKOPF_RG(cID_BelegToCopy);
			
			vRueck.add(recVkopf.get_ID_ADRESSE_cUF());
			
			return vRueck;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException 
		{
			return null;
		}
		
	}

	
	private class own_BS_L_ActionAgentCopyVorgang extends BS_L_ActionAgentCopyVorgang
	{

		public own_BS_L_ActionAgentCopyVorgang(
				E2_NavigationList onavigationList, MyE2_Button oownButton,
				String nameFuerFenster, BS__SETTING Setting)
		{
			super(onavigationList, oownButton, nameFuerFenster, Setting);
		}

		@Override
		public E2_ConfirmBasicModuleContainer create_ConfirmBasicModuleContainer(
				MyE2_String windowtitle, MyE2_String texttitle,
				MyE2_String innerTextblock, MyE2_String labelOKButton,
				MyE2_String labelCancelButton, Extent width, Extent height)
				throws myException
		{
			return new ownConfirmBasicModuleContainer(windowtitle, texttitle,
					innerTextblock, labelOKButton,
					labelCancelButton, width, height);
		}
		
	}

	
	private class ownConfirmBasicModuleContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmBasicModuleContainer(
				MyE2_String windowtitle, MyE2_String texttitle,
				MyE2_String innerTextblock, MyE2_String labelOKButton,
				MyE2_String labelCancelButton, Extent width, Extent height)  throws myException
		{
			super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,
					width, height);
		}
		
	}

}
