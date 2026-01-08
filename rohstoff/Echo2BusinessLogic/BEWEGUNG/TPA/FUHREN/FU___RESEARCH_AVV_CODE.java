package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FU___RESEARCH_AVV_CODE
{
	
	public static String   			AVVCODE_NOT_FOUND = 						"AVVCODE_NOT_FOUND";
	public static String   			AVVCODE_NOT_FOUND_BUT_PRODUKT_OR_DIENST = 	"AVVCODE_NOT_FOUND_BUT_PRODUKT_OR_DIENST";
	public static String   			AVVCODE_FOUND 	= 							"AVVCODE_FOUND";

	private RECORD_ADRESSE  		recAdresseLadeort 	= null;
	private RECORD_ARTIKEL_BEZ 		recArtikelBez       = null;
	

	private RECORD_EAK_CODE         recEAK_Code         = null; 
	private String     				cSTATUS = FU___RESEARCH_AVV_CODE.AVVCODE_NOT_FOUND;
	
	public FU___RESEARCH_AVV_CODE(RECORD_ADRESSE rec_AdresseLadeort, RECORD_ARTIKEL_BEZ rec_ArtikelBez) throws myException
	{
		super();
		this.recAdresseLadeort = 	rec_AdresseLadeort;
		this.recArtikelBez = 		rec_ArtikelBez;
		
		RECORD_ADRESSE  recLieferant = null;
		
		//feststellen, ob es ein lieferant oder ein ladeort ist
		if (this.recAdresseLadeort.get_ADRESSTYP_lValue(-1L)==myCONST.ADRESSTYP_FIRMENINFO)
		{
			recLieferant = this.recAdresseLadeort;
		}
		else
		{
			recLieferant = this.recAdresseLadeort.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}
		
		
		//step 1 (unabhaengig ob beliebige ladestelle oder ex mandant)
		RECLIST_ARTIKELBEZ_LIEF    recListArtBezLief = 
			recLieferant.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(
					RECORD_ARTIKELBEZ_LIEF.FIELD__ARTBEZ_TYP+"='EK' AND "+
					RECORD_ARTIKELBEZ_LIEF.FIELD__ID_ARTIKEL_BEZ+"="+recArtikelBez.get_ID_ARTIKEL_BEZ_cUF(),null,true);
		
		if (recListArtBezLief.get_vKeyValues().size()>0)
		{
			if (S.isFull(recListArtBezLief.get(0).get_ID_EAK_CODE_cUF_NN("")))
			{
				this.recEAK_Code = recListArtBezLief.get(0).get_UP_RECORD_EAK_CODE_id_eak_code();    //TREFFER
				this.cSTATUS = FU___RESEARCH_AVV_CODE.AVVCODE_FOUND;
			}
		}
		
		//falls kein AVV-code gefunden, dann noch schauen, ob die ladestelle zum mandanten gehoert
		if (recEAK_Code == null && recLieferant.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")))
		{
			if (S.isFull(this.recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EAK_CODE_EX_MANDANT_cUF_NN("")))
			{
				this.recEAK_Code = this.recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EAK_CODE_id_eak_code_ex_mandant();
				this.cSTATUS = FU___RESEARCH_AVV_CODE.AVVCODE_FOUND;
			}
		}
		
		if (recEAK_Code == null && 
				(this.recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_IST_PRODUKT_YES() ||
				 this.recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_END_OF_WASTE_YES() || 		
				 this.recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_DIENSTLEISTUNG_YES()))
		{
			this.cSTATUS = FU___RESEARCH_AVV_CODE.AVVCODE_NOT_FOUND_BUT_PRODUKT_OR_DIENST;
		}
		
	}
	
	public RECORD_EAK_CODE get_recEAK_Code()
	{
		return recEAK_Code;
	}

	public String get_cSTATUS()
	{
		return cSTATUS;
	}

	
	public MyE2_Message  get_MessageAfterSearch()
	{
		MyE2_Message  oMSG = null;
		
		if (this.cSTATUS.equals(FU___RESEARCH_AVV_CODE.AVVCODE_FOUND))
		{
			oMSG = new MyE2_Info_Message(new MyE2_String("Es wurde ein passender AVV-Code identifiziert und zugeordnet"));
		}
		else if (this.cSTATUS.equals(FU___RESEARCH_AVV_CODE.AVVCODE_NOT_FOUND_BUT_PRODUKT_OR_DIENST))
		{
			oMSG = new MyE2_Warning_Message(new MyE2_String("Es wurde kein passender AVV-Code identifiziert kann aber ignoriert werden, da die Lieferung ein Produkt oder eine Dienstleistung betrifft"));
		}
		else
		{
			oMSG = new MyE2_Warning_Message(new MyE2_String("Es wurde kein passender AVV-Code identifiziert !"));
		}
		
		return oMSG;
	}
	
	
}
