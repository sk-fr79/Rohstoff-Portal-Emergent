package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectorFirmenLieferort extends  SELECTOR_COMPONENT_FirmenAuswahl implements IF_CanBePopulated
{
	private String[][] arrayLeer =  {{"-",""}};
	

	
	public FU_SelectorFirmenLieferort(E2_SelectionComponentsVector SelVector,	String cEK_VK) throws myException
	{
		super("", 95, 180, SelVector, cEK_VK.equals("EK")?new MyE2_String("Ladeort: "):new MyE2_String("Abladeort: "),false);
		this.get_oSelKunden().setWidth(new Extent(100));
	}
	
	//wird immer von der daruberliegenden firmenadresse gefuellt
	public void populate(String cID_ADRESSE) throws myException
	{
		//String cID_ADRESSE = this.oSelectorAdresse.get_oSelKunden().get_ActualWert();
		
		if (S.isEmpty(cID_ADRESSE))
		{
			this.get_oSelKunden().set_ListenInhalt(this.arrayLeer, false);
		}
		else
		{
			RECORD_ADRESSE    recAdresse = new RECORD_ADRESSE(cID_ADRESSE);

			
			RECLIST_ADRESSE  reclistLief = new RECLIST_ADRESSE(
					"SELECT AD.* FROM "+bibE2.cTO()+".JT_LIEFERADRESSE LA INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (LA.ID_ADRESSE_LIEFER=AD.ID_ADRESSE) WHERE NVL(AD.AKTIV,'N')='Y' AND LA.ID_ADRESSE_BASIS="+cID_ADRESSE+" ORDER BY AD.ORT, AD.NAME1, AD.NAME2 "
					);

			String[][] cAdressen = new String[reclistLief.get_vKeyValues().size()+2][2];
			
			cAdressen[0][0]="-";
			cAdressen[0][1]="";
			cAdressen[1][0]=recAdresse.get_ORT_cUF_NN("N/A") + " (" + recAdresse.get_PLZ_cUF_NN("N/A") + ") - " + recAdresse.get_NAME1_cUF_NN("-")+"-"+recAdresse.get_NAME2_cUF_NN("-") + " ("+recAdresse.get_ID_ADRESSE_cUF()+")";
			cAdressen[1][1]=recAdresse.get_ID_ADRESSE_cUF();

			
			for (int i=0;i<reclistLief.get_vKeyValues().size();i++)
			{
				RECORD_ADRESSE recAd = reclistLief.get(i);
				cAdressen[i+2][0]=recAd.get_ORT_cUF_NN("N/A") + " (" + recAd.get_PLZ_cUF_NN("N/A") + ") - " + recAd.get_NAME1_cUF_NN("-")+"-"+recAd.get_NAME2_cUF_NN("-") + " ("+recAd.get_ID_ADRESSE_cUF()+")";
//				cAdressen[i+2][0]=recAd.get_NAME1_cUF_NN("-")+"-"+recAd.get_NAME2_cUF_NN("-")+"-"+recAd.get_ORT_cUF_NN("-")+" ("+recAd.get_ID_ADRESSE_cUF()+")";
				cAdressen[i+2][1]=recAd.get_ID_ADRESSE_cUF();
			}
			this.get_oSelKunden().set_ListenInhalt(cAdressen, false);
		}
		this.get_oSelKunden().set_ActiveInhalt_or_FirstInhalt("-");
	}

}
