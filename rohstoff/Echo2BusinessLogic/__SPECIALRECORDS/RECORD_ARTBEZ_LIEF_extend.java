package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_ARTBEZ_LIEF_extend extends RECORD_ARTIKELBEZ_LIEF
{

	public RECORD_ARTBEZ_LIEF_extend() throws myException
	{
		super();
	}

	public RECORD_ARTBEZ_LIEF_extend(long unformated, MyConnection Conn)
			throws myException
	{
		super(unformated, Conn);
	}

	public RECORD_ARTBEZ_LIEF_extend(long unformated) throws myException
	{
		super(unformated);
	}

	public RECORD_ARTBEZ_LIEF_extend(RECORD_ARTIKELBEZ_LIEF recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_ARTBEZ_LIEF_extend(String c_id_or_whereblock,
			MyConnection Conn) throws myException
	{
		super(c_id_or_whereblock, Conn);
	}

	public RECORD_ARTBEZ_LIEF_extend(String c_id_or_whereblock)
			throws myException
	{
		super(c_id_or_whereblock);
	}

	
	public String get_ARTBEZ_2_Incl_Specials() throws myException
	{
        String cArtbez2 = S.isEmpty		(this.get_ARTBEZ2_ALTERNATIV_cUF())?
        								 this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ2_cUF_NN(""):
        							     this.get_ARTBEZ2_ALTERNATIV_cUF_NN("");
		if (this.get_UP_RECORD_ARTBEZ_VERUNREINIGUNG_id_artbez_verunreinigung()!=null) 	cArtbez2 += ("\n"+this.get_UP_RECORD_ARTBEZ_VERUNREINIGUNG_id_artbez_verunreinigung().get_VERUNREINIGUNG_cUF_NN(""));
		if (this.get_UP_RECORD_ARTBEZ_MECH_ZUSTAND_id_artbez_mech_zustand()!=null) 		cArtbez2 += ("\n"+this.get_UP_RECORD_ARTBEZ_MECH_ZUSTAND_id_artbez_mech_zustand().get_MECH_ZUSTAND_cUF_NN(""));
		
		if (cArtbez2.startsWith("\n"))
		{
			cArtbez2 = cArtbez2.substring(1);
		}
		return cArtbez2;
	}
	
	
	
}
