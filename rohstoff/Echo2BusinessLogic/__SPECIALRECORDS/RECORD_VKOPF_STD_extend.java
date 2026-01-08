package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_VKOPF_STD_extend extends RECORD_VKOPF_STD
{

	public RECORD_VKOPF_STD_extend() throws myException
	{
		super();
	}

	public RECORD_VKOPF_STD_extend(long unformated, MyConnection Conn)	throws myException
	{
		super(unformated, Conn);
	}

	public RECORD_VKOPF_STD_extend(long unformated) throws myException
	{
		super(unformated);
	}

	public RECORD_VKOPF_STD_extend(RECORD_VKOPF_STD recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_VKOPF_STD_extend(String c_id_or_whereblock, MyConnection Conn)	throws myException
	{
		super(c_id_or_whereblock, Conn);
	}

	public RECORD_VKOPF_STD_extend(String c_id_or_whereblock)	throws myException
	{
		super(c_id_or_whereblock);
	}

	
	
	public boolean get_bAlleWarenPositionenSindAusMonatJahr(int MONAT, int JAHR) throws myException
	{
		boolean bRueck = true;
		RECLIST_VPOS_STD  reclistPos = this.get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std();
		
		String cMONAT = ""+MONAT;
		if (cMONAT.length()==1)
			cMONAT="0"+cMONAT;
		
		String cJAHR = ""+JAHR;
		
		for (int i=0;i<reclistPos.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_STD recPos = reclistPos.get(i);
			if (recPos.is_DELETED_NO() && recPos.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			{
				if ( !(recPos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("----------").substring(6,10).equals(cJAHR) &&
					recPos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("----------").substring(6,10).equals(cJAHR) &&
					recPos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("----------").substring(3,5).equals(cMONAT) &&
					recPos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("----------").substring(3,5).equals(cMONAT)))
				{
					bRueck = false;
				}
			}
		}
		return bRueck;
	}
	
	
	public boolean get_bAlle_Warenpos_Preise_SindGefuellt() throws myException
	{
		boolean bRueck = true;
		RECLIST_VPOS_STD  reclistPos = this.get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std();
		
		for (int i=0;i<reclistPos.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_STD recPos = reclistPos.get(i);
			if (recPos.is_DELETED_NO() && recPos.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			{
				if (S.isEmpty(recPos.get_EINZELPREIS_cUF_NN("")))
				{
					bRueck = false;
				}
			}
		}
		return bRueck;
	}
		
	
}
