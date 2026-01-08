package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class BST___Valid_TPA_KOPF_POS_FUHRE_NOT_DELETED_NOT_STORNIERT_IST_KOMPLETT extends XX_ActionValidator
{
  
	@Override
	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
	{
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VPOS_TPA_FUHRE) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
		
		if (recFuhre.is_DELETED_YES() )
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde bereits gelöscht !"));
			return oMV;
		}
		
		//aenderung 2010-12-22: fuhren, die nicht vollstaendig sind, koennen nicht gedruckt werden
		if (recFuhre.is_FUHRE_KOMPLETT_NO())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Es können nur Fuhren gedruckt werden, die bearbeitet wurden"));
			return oMV;
		}
		
		
		if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa() != null)
		{
			if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Transportposition wurde bereits gelöscht !"));
				return oMV;
			}
			
			//aenderung 2010-12-06: betrifft nur die lieferpapiere, keine tpa-position
//			if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ANZAHL_cUF_NN("--").equals("--") || 
//				recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_EINZELPREIS_cUF_NN("--").equals("--") )
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message("Mindestens eine Transportposition ist noch nicht komplett (Menge/Preis) !"));
//				return oMV;
//			}
//			
			
			
			if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Transportauftrag wurde bereits geloescht !"));
				return oMV;
			}
		}
		
		return oMV;
	}

}
