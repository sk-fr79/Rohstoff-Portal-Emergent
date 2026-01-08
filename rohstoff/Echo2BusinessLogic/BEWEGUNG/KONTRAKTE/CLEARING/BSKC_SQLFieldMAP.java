package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_SQLFieldMAP extends Project_SQLFieldMAP 
{
	
	
	/**
	 * 
	 * @param cEK_VK
	 * @param cID_VPOS_KON_Gegen  wird != null, wenn die liste als tochterausklappliste benutzt wird
	 *                                          dann wird eine zusaetzliche bedingung eingefuegt 
	 * @throws myException
	 */
	public BSKC_SQLFieldMAP(String cEK_VK, String cID_VPOS_KON_Gegen)   throws myException
	{
		super("JT_VPOS_KON","ANZAHL",false);
		
		
		this.add_SQLField(new SQLField("  NVL(ROUND(JT_VPOS_KON.ANZAHL,1),0)","ANZAHL",new MyE2_String("ANZAHL",false),bibE2.get_CurrSession()), false);
		
		
		this.add_JOIN_Table("JT_VKOPF_KON", 
							"JT_VKOPF_KON", 
							SQLFieldMAP.LEFT_OUTER, 
							":ID_ADRESSE:VORNAME:NAME1:NAME2:NAME3:STRASSE:HAUSNUMMER:PLZ:ORT:ID_VKOPF_KON:BUCHUNGSNUMMER:", true, "JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON", "K_", 
							null);

		this.add_JOIN_Table("JT_VPOS_KON_TRAKT", 
							"JT_VPOS_KON_TRAKT", 
							SQLFieldMAP.LEFT_OUTER, 
							":ID_VPOS_KON:LIEFERZEIT:LIEFERORT:UEBERLIEFER_OK:TRANSPORTMITTEL:ABGESCHLOSSEN:GUELTIG_VON:GUELTIG_BIS:", true,
							"JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON", "KO_", 
							null);
		
		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
							"JT_ARTIKEL_BEZ", 
							SQLFieldMAP.LEFT_OUTER, 
							":ARTBEZ1:ANR2:", true,
							"JT_VPOS_KON.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ", "AB_", 
							null);
	
		this.add_JOIN_Table("JT_ARTIKEL", 
							"JT_ARTIKEL", 
							SQLFieldMAP.LEFT_OUTER, 
							":ANR1:", true,
							"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", "A_", 
							null);
	

		this.add_JOIN_Table("JT_ADRESSE", 
							"JT_ADRESSE", 
							SQLFieldMAP.LEFT_OUTER, 
							":ID_USER:ID_ADRESSE", true,
							"JT_VKOPF_KON.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", "AD_", 
							null);
		
		// nur VK-kontrakte
		this.add_BEDINGUNG_STATIC("JT_VKOPF_KON.VORGANG_TYP='"+(cEK_VK.equals("EK")?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT)+"'");
		this.add_BEDINGUNG_STATIC("JT_VPOS_KON.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'");
		this.add_BEDINGUNG_STATIC("  NVL(JT_VPOS_KON.DELETED,'N')='N'");


		if (S.isFull(cID_VPOS_KON_Gegen))
		{
			String cZusatzBedingung = "";
			
			if (cEK_VK.equals("EK"))     //dann ist es tochterliste der vk-kontrakte
			{
				cZusatzBedingung = " (JT_VPOS_KON.ID_VPOS_KON IN (SELECT ID_VPOS_KON_EK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+cID_VPOS_KON_Gegen+")) ";
			}
			else
			{
				cZusatzBedingung = " (JT_VPOS_KON.ID_VPOS_KON IN (SELECT ID_VPOS_KON_VK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+cID_VPOS_KON_Gegen+")) ";
			}
			this.add_BEDINGUNG_STATIC(cZusatzBedingung);
		}

		
		// sonderfeld, ANR1 - ANR2
		this.add_SQLField(new SQLField("  NVL(JT_ARTIKEL.ANR1,'--')||' - '||  NVL(JT_ARTIKEL_BEZ.ANR2,'--')","ANR1_ANR2",new MyE2_String("ANR1_ANR2",false),bibE2.get_CurrSession()), false);
		
		//GUELTIGKEITSZEIT
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'DD.MM.YY'),'-')||' - '||  NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'DD.MM.YY'),'-')","GUELTIGKEITSZEITRAUM",new MyE2_String("Gültigkeitszeitraum",false),bibE2.get_CurrSession()), false);
		
		// sonderfeld, STRASSE-HNR, plz-ort
		this.add_SQLField(new SQLField("  NVL(JT_VKOPF_KON.STRASSE,'-')||' '||  NVL(JT_VKOPF_KON.HAUSNUMMER,'-')","K_STRASSE_HNR",new MyE2_String("K_STRASSE_HNR",false),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(JT_VKOPF_KON.PLZ,'-')||' '||  NVL(JT_VKOPF_KON.ORT,'-')","K_PLZ_ORT",new MyE2_String("K_PLZ_ORT",false),bibE2.get_CurrSession()), false);
		
		
		this.initFields();
		
	}

}
