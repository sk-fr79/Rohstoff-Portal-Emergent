package rohstoff.Echo2BusinessLogic._4_ALL;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FirmenSearch_USTID 
{
	private String cUST_LKZ = 	null;
	private String cUST_ID = 	null;
	
	
	
	public FirmenSearch_USTID(RECORD_ADRESSE recAdresse) throws myException 
	{
		super();
		
		this.cUST_ID = recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_cUF();
		this.cUST_LKZ= recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_cUF();

		if (S.isEmpty(this.cUST_ID) || S.isEmpty(this.cUST_LKZ))
		{
			RECLIST_ADRESSE_UST_ID   recListUST = recAdresse.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();
			
			if (recListUST.get_vKeyValues().size()>0)
			{
				this.cUST_ID = recListUST.get(0).get_UMSATZSTEUERID_cUF();
				this.cUST_LKZ= recListUST.get(0).get_UMSATZSTEUERLKZ_cUF();
			}
		}
	}



	/**
	 * 
	 * @return s first found UST_LKZ or null
	 */
	public String get_cUST_LKZ() 
	{
		return cUST_LKZ;
	}
	
	
	
	/**
	 * 
	 * @return s first found UST_ID or null
	 */
	public String get_cUST_ID() 
	{
		return cUST_ID;
	}

	
	public boolean get_bHasUST_LKZ_ID()
	{
		return (S.isFull(this.cUST_ID) && S.isFull(this.cUST_LKZ));
	}
	
	
	

}
