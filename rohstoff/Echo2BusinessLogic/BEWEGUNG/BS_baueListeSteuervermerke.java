package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_STEUERVERMERK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

public class BS_baueListeSteuervermerke 
{
	
	private String[][] 					cSteuerVermerke = null;
	
	private BS_STEUERVERMERK_HASH		hmSteuervermerke = new BS_STEUERVERMERK_HASH();




	/**
	 * 
	 * @param bLagervermerk
	 * @param bLeerVermerk
	 * @param cEK_VK     (kann sein: EK oder VK oder ALLE)
	 * @throws myException
	 */
	public BS_baueListeSteuervermerke(boolean bLagervermerk, boolean bLeerVermerk, String cEK_VK) throws myException 
	{
		super();
		
		RECORD_MANDANT  					recMandant = new RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_lValue(new Long(-1)));
		
		RECLIST_MANDANT_STEUERVERMERK   	reclistVermerke = recMandant.get_DOWN_RECORD_LIST_MANDANT_STEUERVERMERK_id_mandant();
		
		//die zieltabelle hat anzahl laendervermerke + 3 + lager- und leervermerk
		
		LinkedHashMap<String, String> hmVermerke= new LinkedHashMap<String, String>();
		
		hmVermerke.put("-","");
		hmSteuervermerke.put("-", new BS_STEUERVERMERK("-", "", null, null));
		
		hmVermerke.put(        "Innergemeinschaftliche Lieferung",																	recMandant.get_EU_STEUER_VERMERK_cUF_NN("<EU-Vermerk im Mandantenstamm>"));
		hmSteuervermerke.put(  "Innergemeinschaftliche Lieferung", new BS_STEUERVERMERK("Innergemeinschaftliche Lieferung", 	recMandant.get_EU_STEUER_VERMERK_cUF_NN("<EU-Vermerk im Mandantenstamm>"), null, "0"));
		
		hmVermerke.put(        "Steuerfreie Aussenlieferung",															recMandant.get_AUSSEN_STEUER_VERMERK_cUF_NN("<Aussen-Steuer-Vermerk im Mandantenstamm>"));
		hmSteuervermerke.put(  "Steuerfreie Aussenlieferung",		new BS_STEUERVERMERK("Steuerfreie Aussenlieferung", recMandant.get_AUSSEN_STEUER_VERMERK_cUF_NN("<Aussen-Steuer-Vermerk im Mandantenstamm>"), null, "0"));
		
		hmVermerke.put(        "EU-Dienstleistung <Reverse Charge>",																recMandant.get_VERMERK_STEUERFR_DIENSTLEIST_cUF_NN("<EU-Dienstleistung reverse Charge>"));
		hmSteuervermerke.put(  "EU-Dienstleistung <Reverse Charge>", new BS_STEUERVERMERK("EU-Dienstleistung <Reverse Charge>", recMandant.get_VERMERK_STEUERFR_DIENSTLEIST_cUF_NN("<EU-Dienstleistung reverse Charge>"), null, "0"));
		
		
		if (bLeerVermerk)
		{
			hmVermerke.put(			"Kein Vermerk in RE/GUT",													FU___CONST.EU_STEUERVERMERK_LEER);
			hmSteuervermerke.put(	"Kein Vermerk in RE/GUT", 	new BS_STEUERVERMERK("Kein Vermerk in RE/GUT", 	FU___CONST.EU_STEUERVERMERK_LEER, null, ""));
		}
		if (bLagervermerk)
		{
			hmVermerke.put(			"Lager",								FU___CONST.EU_STEUERVERMERK_LAGER);
			hmSteuervermerke.put(	"Lager", 					new BS_STEUERVERMERK("Lager", 	FU___CONST.EU_STEUERVERMERK_LAGER, null, ""));
		}
		
		//jetzt die laender-vermerke
		for (int i=0;i<reclistVermerke.size();i++)
		{
			RECORD_MANDANT_STEUERVERMERK recLaenderVermerk = reclistVermerke.get(i); 
			if (cEK_VK.equals("EK"))
			{
				String cHash = "Ländervermerk: "+recLaenderVermerk.get_UP_RECORD_LAND_id_land().get_LAENDERNAME_cUF_NN("<Land>")+"   ("+recLaenderVermerk.get_DROPDOWN_INFO_cUF_NN("")+")";
				String cSteuerText = recLaenderVermerk.get_STEUERVERMERK_GUTSCHRIFT_cUF_NN("<Steuervermerk Gutschrift>");
				
				hmVermerke.put(			cHash, cSteuerText);
				hmSteuervermerke.put(	cHash, new BS_STEUERVERMERK(cHash, 	cSteuerText, recLaenderVermerk.get_GUELTIG_AB_cF_NN(""), recLaenderVermerk.get_STEUERSATZ_cF_NN("")));
			}
			else if (cEK_VK.equals("VK"))
			{
				String cHash = "Ländervermerk: "+recLaenderVermerk.get_UP_RECORD_LAND_id_land().get_LAENDERNAME_cUF_NN("<Land>")+"   ("+recLaenderVermerk.get_DROPDOWN_INFO_cUF_NN("")+")";
				String cSteuerText = recLaenderVermerk.get_STEUERVERMERK_RECHNUNG_cUF_NN("<Steuervermerk Rechnung>");
				
				hmVermerke.put(			cHash, cSteuerText);
				hmSteuervermerke.put(	cHash, new BS_STEUERVERMERK(cHash, 	cSteuerText, recLaenderVermerk.get_GUELTIG_AB_cF_NN(""), recLaenderVermerk.get_STEUERSATZ_cF_NN("")));

			}
			else if (cEK_VK.equals("ALLE"))
			{
				String cHash = "Ländervermerk (GUT): "+recLaenderVermerk.get_UP_RECORD_LAND_id_land().get_LAENDERNAME_cUF_NN("<Land>")+"   ("+recLaenderVermerk.get_DROPDOWN_INFO_cUF_NN("")+")";
				String cSteuerText = recLaenderVermerk.get_STEUERVERMERK_GUTSCHRIFT_cUF_NN("<Steuervermerk Gutschrift>");
				
				hmVermerke.put(			cHash, cSteuerText);
				hmSteuervermerke.put(	cHash, new BS_STEUERVERMERK(cHash, 	cSteuerText, recLaenderVermerk.get_GUELTIG_AB_cF_NN(""), recLaenderVermerk.get_STEUERSATZ_cF_NN("")));

				cHash = "Ländervermerk (RECH): "+recLaenderVermerk.get_UP_RECORD_LAND_id_land().get_LAENDERNAME_cUF_NN("<Land>")+"   ("+recLaenderVermerk.get_DROPDOWN_INFO_cUF_NN("")+")";
				cSteuerText = recLaenderVermerk.get_STEUERVERMERK_RECHNUNG_cUF_NN("<Steuervermerk Rechnung>");
				
				hmVermerke.put(			cHash, cSteuerText);
				hmSteuervermerke.put(	cHash, new BS_STEUERVERMERK(cHash, 	cSteuerText, recLaenderVermerk.get_GUELTIG_AB_cF_NN(""), recLaenderVermerk.get_STEUERSATZ_cF_NN("")));
			}
			else
			{
				throw new myException("Parameter: cEK_VK can only be:  EK /  VK / ALLE");
			}
		}
		
		hmVermerke.put("--","");
		hmSteuervermerke.put("--", new BS_STEUERVERMERK("--", "", null, null));

		
		this.cSteuerVermerke = new String[hmVermerke.size()][2];
		
		int i=0;
		Iterator<Entry<String, String>>  iter = hmVermerke.entrySet().iterator();
		
		while (iter.hasNext())
		{
			Entry<String, String> Eintrag = iter.next();
			this.cSteuerVermerke[i][0] = Eintrag.getKey();
			this.cSteuerVermerke[i][1] = Eintrag.getValue();
			i++;
		}
		
	}
	
	
	public String[][] get_cSteuerVermerke() 
	{
		return cSteuerVermerke;
	}
	
	
	public BS_STEUERVERMERK_HASH get_hmSteuervermerke() 
	{
		return hmSteuervermerke;
	}

}
