package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import com.sun.org.apache.xpath.internal.operations.Mod;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print;

public class FPU_BUTTON_PRINT_MAIL_BELEG extends	___BUTTON_LIST_BT_Mail_and_Print
{

	public FPU_BUTTON_PRINT_MAIL_BELEG(FPU_BasicModuleContainer Modulcontainer)
	{
		super(new ownSammler_ID_VPOS_TPA_FUHRE(Modulcontainer),"FAHRPLANUEBERSICHT");
//		this.add_IDValidator(new ownValidator());
	}

	
	//aenderung 2010-12-22: Validierung erfolgt in der Superklasse
//	private class ownValidator extends XX_ActionValidator
//	{
//		@Override
//		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
//		{
//			return null;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String unformated)	throws myException
//		{
//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
//			
//			RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(unformated);
//			
//			if (recFuhre.is_FUHRE_KOMPLETT_NO())
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es koennen nur Belege von vollständigen Fuhren erzeugt werden !")));
//			}
//			
//			return oMV;
//		}
//	}
//	
	
	
	
	public static class ownSammler_ID_VPOS_TPA_FUHRE extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private FPU_BasicModuleContainer 		ModulContainer = null;

		public ownSammler_ID_VPOS_TPA_FUHRE(FPU_BasicModuleContainer Modulcontainer)
		{
			super();
			this.ModulContainer = Modulcontainer;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			
			for (int i=0;i<ModulContainer.get_vLKW_CheckBox().size();i++) {
				if (ModulContainer.get_vLKW_CheckBox().get(i).isSelected())	{
					for (int k=0;k<ModulContainer.get_vLKW_CheckBox().get(i).get_vREC_Fuhren_ZuLKW().size();k++) {
						if (ModulContainer.get_vLKW_CheckBox().get(i).get_vREC_Fuhren_ZuLKW().get(k).get_CB_DruckeFahrt().isSelected()) {
							vRueck.add(ModulContainer.get_vLKW_CheckBox().get(i).get_vREC_Fuhren_ZuLKW().get(k).get_ID_VPOS_TPA_FUHRE_cUF());
						}
					}
				}
			}
			
			//falls nix gefunden, dann meldung und abbruch
			if (vRueck.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss mindestens eine Fahrt angekreuzt werden !")));
			}
			
		    return vRueck;
		}


		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
		}

	}

	
	
}
