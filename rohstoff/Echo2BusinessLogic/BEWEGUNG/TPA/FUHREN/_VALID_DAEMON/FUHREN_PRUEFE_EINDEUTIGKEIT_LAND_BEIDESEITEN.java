package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

/**
 * 
 * @author martin
 * pruefung, ob bei einer fuhre lade- und abladeorte jeweils innerhalb eines landes liegen
 *
 */
public class FUHREN_PRUEFE_EINDEUTIGKEIT_LAND_BEIDESEITEN extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFE_EINDEUTIGKEIT_LAND_BEIDESEITEN(RECORD_VPOS_TPA_FUHRE recFuhre)
	{
		super(recFuhre);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (this.get_recFuhre().is_DELETED_YES() || this.get_recFuhre().is_IST_STORNIERT_YES())
		{
			return oMV;
		}

		VectorSingle  vLandEK = new VectorSingle();
		VectorSingle  vLandVK = new VectorSingle();
		
		vLandEK.add(this.get_recFuhre().get_L_LAENDERCODE_cUF_NN(this.get_recFuhre().get_ID_VPOS_TPA_FUHRE_cUF()));
		vLandVK.add(this.get_recFuhre().get_A_LAENDERCODE_cUF_NN(this.get_recFuhre().get_ID_VPOS_TPA_FUHRE_cUF()));
		
		for (int i=0;i<this.get_recFuhre().get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE_ORT  recORT = this.get_recFuhre().get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get(i);
			
			if (recORT.is_DELETED_NO())
			{
				if (recORT.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
				{
					vLandEK.add(recORT.get_LAENDERCODE_cUF_NN(this.get_recFuhre().get_ID_VPOS_TPA_FUHRE_cUF()+recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()));
				}
				else if (recORT.get_DEF_QUELLE_ZIEL_cUF().equals("VK"))
				{
					vLandVK.add(recORT.get_LAENDERCODE_cUF_NN(this.get_recFuhre().get_ID_VPOS_TPA_FUHRE_cUF()+recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()));
				}
				else
				{
					oMV.add(new MyE2_Alarm_Message("Modul: FUHREN_PRUEFE_EINDEUTIGKEIT_LAND_BEIDESEITEN: Interner Fehler: Zusatzort konnte nicht klassifiziert werden !"));
				}
			}
		}
		
		if (vLandEK.size()>1)
		{
			oMV.add(new MyE2_Alarm_Message("Eine Fuhre kann mehrere Ladeorte nur in EINEM Land besitzen !!"));
		}
		if (vLandVK.size()>1)
		{
			oMV.add(new MyE2_Alarm_Message("Eine Fuhre kann mehrere Abladeorte nur in EINEM Land besitzen !!"));
		}
		
		return oMV;
	}

}
