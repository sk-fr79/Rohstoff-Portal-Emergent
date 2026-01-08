package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyArtikelBezeichnung;

public class BS_HashMap_FieldValues_ArtikelBez_For_VPOS extends HashMap<String,String>
{
	private String cID_ArtikelBezUnformatiert = null;

	public BS_HashMap_FieldValues_ArtikelBez_For_VPOS(String ID_ArtikelBez_Unformatiert) throws myException
	{
		super();
		cID_ArtikelBezUnformatiert = ID_ArtikelBez_Unformatiert;
		
		MyArtikelBezeichnung  oArtBez = new MyArtikelBezeichnung(cID_ArtikelBezUnformatiert, bibE2.get_CurrSession());
		
		this.put("ANR1",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_ANR1())));
		this.put("ANR2",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_ANR2())));
		this.put("ARTBEZ1",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_BEZ_ARTBEZ1())));
		this.put("ARTBEZ2",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_BEZ_ARTBEZ2())));
		this.put("ZOLLTARIFNR",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_ZOLLTARIFNR())));
		this.put("MENGENDIVISOR",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_MENGENDIVISOR())));
		this.put("EUNOTIZ",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_EUNOTIZ())));
		this.put("EUCODE",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_EUCODE())));
		this.put("EINHEITKURZ",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_EINHEITKURZ())));
		this.put("EINHEIT_PREIS_KURZ",bibALL.MakeSql(bibALL.null2leer(oArtBez.get_EINHEIT_PREIS_KURZ())));

		
	}
	
	
	
	
	
}
