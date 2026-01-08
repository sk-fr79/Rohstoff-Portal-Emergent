package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_FUHREN_PRUEFUNG
{
	private RECORD_VPOS_TPA_FUHRE  recFuhre = null;
	private RECLIST_VPOS_TPA_FUHRE  recListFuhre = null;
	

	public XX_FUHREN_PRUEFUNG(RECORD_VPOS_TPA_FUHRE rec_Fuhre)
	{
		super();
		this.recFuhre = rec_Fuhre;
	}
	

	public XX_FUHREN_PRUEFUNG(RECLIST_VPOS_TPA_FUHRE rec_ListFuhre)
	{
		super();
		this.recListFuhre = rec_ListFuhre;
	}

	
	public abstract MyE2_MessageVector  mache_Pruefung() throws myException;


	public RECORD_VPOS_TPA_FUHRE get_recFuhre()
	{
		return recFuhre;
	} 
	
	public RECLIST_VPOS_TPA_FUHRE get_recListFuhre()
	{
		return recListFuhre;
	} 
	
	
}
