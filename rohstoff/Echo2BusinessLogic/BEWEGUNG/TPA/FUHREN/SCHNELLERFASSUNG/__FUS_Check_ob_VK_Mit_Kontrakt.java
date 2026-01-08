package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;

public class __FUS_Check_ob_VK_Mit_Kontrakt
{

	private boolean bOK = true;
	
	public boolean get_bOK()
	{
		return bOK;
	}

	public __FUS_Check_ob_VK_Mit_Kontrakt() throws myException
	{
		super();
		if ((new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_bIsCorrectFilled(true)))
		{
			if (new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_bIsCorrectFilled(true))
			{
				RECORD_ADRESSE recAbnehmer = new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_ActualRecHauptAdresse();
				RECORD_ARTIKEL recVKSorte =  new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_ActualRecArtikel();
				
				if (recAbnehmer!=null && recVKSorte!=null)
				{
					if (!recAbnehmer.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")))
					{
						if (recVKSorte.is_DIENSTLEISTUNG_NO() && recVKSorte.is_IST_PRODUKT_NO() && recVKSorte.is_END_OF_WASTE_NO())
						{
							RECORD_VPOS_KON  recVPOS_KON  = new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().get_Actual_RECORD_VPOS_KON();
							
							if (recVPOS_KON == null)
							{
								this.bOK = false;
							}
						}
					}
				}
			}
		}

	}

	
	public __FUS_Check_ob_VK_Mit_Kontrakt(FUS_FuhreRepraesentantInListe oFuhreInListe) throws myException
	{
		super();
		RECORD_ADRESSE recAbnehmer = oFuhreInListe.get_ABNEHMER();
		RECORD_ARTIKEL recVKSorte =  oFuhreInListe.get_record_ABLADESORTE()==null?null:oFuhreInListe.get_record_ABLADESORTE().get_UP_RECORD_ARTIKEL_id_artikel();
		
		if (recAbnehmer!=null && recVKSorte!=null)
		{
			if (!recAbnehmer.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")))
			{
				if (recVKSorte.is_DIENSTLEISTUNG_NO() && recVKSorte.is_IST_PRODUKT_NO() &&  recVKSorte.is_END_OF_WASTE_NO())
				{
					RECORD_VPOS_KON  recVPOS_KON  = new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().get_Actual_RECORD_VPOS_KON();
					
					if (recVPOS_KON == null)
					{
						this.bOK = false;
					}
				}
			}
		}

	}

	
}
