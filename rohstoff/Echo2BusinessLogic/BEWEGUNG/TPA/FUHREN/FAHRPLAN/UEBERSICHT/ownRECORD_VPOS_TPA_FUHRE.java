package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class ownRECORD_VPOS_TPA_FUHRE extends RECORD_VPOS_TPA_FUHRE
{

	private boolean bIstFrei = true; 
	
	
	public ownRECORD_VPOS_TPA_FUHRE() throws myException
	{
		super();
	}

	public ownRECORD_VPOS_TPA_FUHRE(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public ownRECORD_VPOS_TPA_FUHRE(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public ownRECORD_VPOS_TPA_FUHRE(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public ownRECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig)
	{
		super(recordOrig);
	}

	public ownRECORD_VPOS_TPA_FUHRE(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public ownRECORD_VPOS_TPA_FUHRE(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	public boolean get_bIstFrei()
	{
		return bIstFrei;
	}

	public void set_bIstFrei(boolean bIstFrei)
	{
		this.bIstFrei = bIstFrei;
	}

}
