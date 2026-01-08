package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class _SPECIAL_REC_UMA_KONTRAKT extends RECORD_UMA_KONTRAKT
{

	public _SPECIAL_REC_UMA_KONTRAKT() throws myException
	{
		super();
	}

	public _SPECIAL_REC_UMA_KONTRAKT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public _SPECIAL_REC_UMA_KONTRAKT(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public _SPECIAL_REC_UMA_KONTRAKT(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public _SPECIAL_REC_UMA_KONTRAKT(RECORD_UMA_KONTRAKT recordOrig)
	{
		super(recordOrig);
	}

	public _SPECIAL_REC_UMA_KONTRAKT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public _SPECIAL_REC_UMA_KONTRAKT(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	public String get_LabelTextKunde() throws myException
	{
		return this.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cUF_NN("");
	}
	
	
//	public String get_LabelTextAusgangsSorte() throws myException
//	{
//		return this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ausgang().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"-"+
//					this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ausgang().get_ANR2_cUF_NN("")+"  "+
//					this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ausgang().get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("");
//	}
//	
//	public String get_LabelTextZielSorte() throws myException
//	{
//		return 	this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ziel().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"-"+
//				this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ziel().get_ANR2_cUF_NN("")+"  "+
//				this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ziel().get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("");
//
//	}

	
	
}



