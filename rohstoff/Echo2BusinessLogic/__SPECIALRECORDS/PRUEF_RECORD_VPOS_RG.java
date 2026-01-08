package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class PRUEF_RECORD_VPOS_RG extends RECORD_VPOS_RG
{

	public PRUEF_RECORD_VPOS_RG() throws myException
	{
		super();
	}

	public PRUEF_RECORD_VPOS_RG(long unformated, MyConnection Conn)
			throws myException
	{
		super(unformated, Conn);
	}

	public PRUEF_RECORD_VPOS_RG(long unformated) throws myException
	{
		super(unformated);
	}

	public PRUEF_RECORD_VPOS_RG(RECORD_VPOS_RG recordOrig)
	{
		super(recordOrig);
	}

	public PRUEF_RECORD_VPOS_RG(String c_id_or_whereblock_or_sql,
			MyConnection Conn) throws myException
	{
		super(c_id_or_whereblock_or_sql, Conn);
	}

	public PRUEF_RECORD_VPOS_RG(String c_id_or_whereblock_or_sql)
			throws myException
	{
		super(c_id_or_whereblock_or_sql);
	}

	
	
	public boolean get_bIsTeilEinesStornoZyklus() throws myException
	{
		boolean bRueck = false;
		
		if ( S.isFull(this.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
			 S.isFull(this.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) ||
			 (this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && S.isFull(this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_ID_VKOPF_RG_STORNO_VORGAENGER_cUF_NN(""))) ||
			 (this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && S.isFull(this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_ID_VKOPF_RG_STORNO_NACHFOLGER_cUF_NN(""))))
		{
			bRueck = true;
		}

		return bRueck;
	}

	
	public boolean get_bIstTeilEinesAbgeschlossenenBelegs() throws myException
	{
		boolean bRueck = false;
		
		if (this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
		{
			bRueck = true;
		}

		return bRueck;
		
	}
	
	
	
}
