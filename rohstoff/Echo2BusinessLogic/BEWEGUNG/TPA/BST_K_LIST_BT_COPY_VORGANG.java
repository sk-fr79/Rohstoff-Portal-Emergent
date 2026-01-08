package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Iterator;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_L_ActionAgentCopyVorgang;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;

public class BST_K_LIST_BT_COPY_VORGANG extends MyE2_Button
{
	
	public BST_K_LIST_BT_COPY_VORGANG(E2_NavigationList onavigationList,BST_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png"), E2_ResourceIcon.get_RI("leer.png"));

		this.add_oActionAgent(new own_BS_L_ActionAgentCopyVorgang(onavigationList,this,"Transportauftrag(e)",omaskContainer.get_oSetting()));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_TPA_LIST,"KOPIEREN_TPA"));
		this.setToolTipText(new MyE2_String("Kopieren eines (oder mehrerer) Transportaufträge").CTrans());
		
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
			
			RECORD_VKOPF_TPA  recVkopf = new RECORD_VKOPF_TPA(cID_BelegToCopy);
			
			vRueck.add(recVkopf.get_ID_ADRESSE_cUF());
			
			RECLIST_VPOS_TPA  reclistPosTPA = recVkopf.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa("NVL(DELETED,'N')='N'", null, true);
			
			if (reclistPosTPA!=null)
			{
				Iterator<RECORD_VPOS_TPA> iter = reclistPosTPA.values().iterator();
				
				while (iter.hasNext())
				{
					RECORD_VPOS_TPA recPosTPA = iter.next();
					
					vRueck.add(recPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("0"));
					vRueck.add(recPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_ADRESSE_SPEDITION_cUF_NN("0"));
					vRueck.add(recPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_ADRESSE_START_cUF_NN("0"));
					vRueck.add(recPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_ADRESSE_ZIEL_cUF_NN("0"));

					RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = 
						recPosTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
					
					if (reclistOrte != null)
					{
						Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iterOrte = reclistOrte.values().iterator();
						
						while (iterOrte.hasNext())
						{
							RECORD_VPOS_TPA_FUHRE_ORT recOrt = iterOrte.next();
							
							vRueck.add(recOrt.get_ID_ADRESSE_cUF_NN("0"));
						}
					}
				}
			}
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
