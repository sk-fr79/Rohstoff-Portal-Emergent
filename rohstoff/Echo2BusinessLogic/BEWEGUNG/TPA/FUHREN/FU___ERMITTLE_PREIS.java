package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class FU___ERMITTLE_PREIS
{
	
	
	private String     cID_VPOS_STD_Rueck = null;    // rueckgabe neue Angebots-ID (falls eine gefunden wird
	private BigDecimal bdNeuerPreis_Rueck = null;    // rueckgabe eines neuen preises (falls einer gefunden wird)
	private String     cPreisManuell_Y_N_Rueck = null;  //rueckgabe des manuell-schalters (wird immer Y, wenn es eine eigene adresse ist (-->lagerseite) 
	
	private MyConnection  oConn   = bibALL.get_oConnectionNormal();
	/**
	 * 
	 * @param cid_adresse_uf
	 * @param cid_artikel_uf
	 * @param cid_vpos_kon_uf
	 * @param cid_vpos_std_uf
	 * @param cpreis_manuell_y_n
	 * @param cDatum_von      formate: 31.12.2010
	 * @param cDatum_bis
	 * @param bdEINZELPREIS
	 * @param oMV
	 * @param bEK
	 * @param oConn
	 * @throws myException
	 */
	public FU___ERMITTLE_PREIS(	String 				cid_adresse_uf,
								String 				cid_artikel_uf, 
								String 				cid_vpos_kon_uf,
								String 				cid_vpos_std_uf, 
								String 				cpreis_manuell_y_n,
								BigDecimal 			bdEINZELPREIS,
								String              cDatum_von,
								String              cDatum_bis,
								MyE2_MessageVector  oMV,
								boolean      		bEK,
								MyConnection        Conn
								) throws myException
	{
		super();
		
		if (Conn != null)
		{
			this.oConn = Conn;
		}
		
		if (S.isEmpty(cid_adresse_uf) )
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte legen Sie zuerst die "+(bEK?"Ladeort-Adressen ":"Abladeort-Adressen")+" fest !"));
		}
		

		if (S.isEmpty(cid_artikel_uf))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte legen Sie zuerst den "+(bEK?"Lade-Sortenbezeichner ":"Ablade-Sortenbezeichner")+" fest !"));
		}
		
		
		if (S.isEmpty(cDatum_von)  || S.isEmpty(cDatum_bis) )
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte legen Sie zuerst das "+(bEK?"Ladedatum ":"Abladedatum")+" fest !"));
		}

		
		
		if (bibMSG.get_bHasAlarms())
		{
			return;
		}
		
		
		//vorgaben fuer die rueckgabe:
		this.cID_VPOS_STD_Rueck = cid_vpos_std_uf;
		this.bdNeuerPreis_Rueck = bdEINZELPREIS;
		this.cPreisManuell_Y_N_Rueck = cpreis_manuell_y_n;
		if (S.isEmpty(this.cPreisManuell_Y_N_Rueck))
		{
			this.cPreisManuell_Y_N_Rueck="N";
		}
		
		this.ermittle_werte( 	cid_adresse_uf,
				 				cid_artikel_uf, 
				 				cid_vpos_kon_uf,
				 				cid_vpos_std_uf, 
				 				cpreis_manuell_y_n,
				 				bdEINZELPREIS,
				 				cDatum_von,
				 				cDatum_bis,
				 				oMV,
				 				bEK,
				 				false         //hinweis: preisfreigabe-schalter aus der maske wird sowieso geprueft
				 				);

	}
	
	

	/**
	 * 
	 * @param recFuhre
	 * @param oMV
	 * @param bEK
	 * @param oConn
	 * @throws myException
	 */
	public FU___ERMITTLE_PREIS(	RECORD_VPOS_TPA_FUHRE 	recFuhre,
														MyE2_MessageVector  	oMV,
														boolean      			bEK,
														MyConnection       	 	Conn ) throws myException
	{
		super();
		
		if (Conn != null)
		{
			this.oConn = Conn;
		}

		String 			cid_adresse_uf 	= recFuhre.get_ID_ADRESSE_START_cUF_NN("");
		String 			cid_artikel_uf  = "";
		if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek()!=null)
		{
			cid_artikel_uf = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ID_ARTIKEL_cUF_NN("");
		}
		String 			cid_vpos_kon_uf 	= recFuhre.get_ID_VPOS_KON_EK_cUF_NN("");
		String 			cid_vpos_std_uf 	= recFuhre.get_ID_VPOS_STD_EK_cUF_NN("");
		String 			cpreis_manuell_y_n  = recFuhre.get_MANUELL_PREIS_EK_cUF_NN("N");
		BigDecimal 		bdEINZELPREIS		= recFuhre.get_EINZELPREIS_EK_bdValue(null);
		String          cDatum_von			= recFuhre.get_DATUM_AUFLADEN_cF();
		String          cDatum_bis			= recFuhre.get_DATUM_AUFLADEN_cF();
		
		if (!bEK)
		{
			cid_adresse_uf 	= recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("");
			cid_artikel_uf  = "";
			if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk()!=null)
			{
				cid_artikel_uf = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_ID_ARTIKEL_cUF_NN("");
			}
			cid_vpos_kon_uf 	= recFuhre.get_ID_VPOS_KON_VK_cUF_NN("");
			cid_vpos_std_uf 	= recFuhre.get_ID_VPOS_STD_VK_cUF_NN("");
			cpreis_manuell_y_n  = recFuhre.get_MANUELL_PREIS_VK_cUF_NN("N");
			bdEINZELPREIS		= recFuhre.get_EINZELPREIS_VK_bdValue(new BigDecimal(0));
			cDatum_von			= recFuhre.get_DATUM_ABLADEN_cF();
			cDatum_bis			= recFuhre.get_DATUM_ABLADEN_cF();
		}
		
		
		//vorgaben fuer die rueckgabe:
		this.cID_VPOS_STD_Rueck = cid_vpos_std_uf;
		this.bdNeuerPreis_Rueck = bdEINZELPREIS;
		this.cPreisManuell_Y_N_Rueck = cpreis_manuell_y_n; 

		
		//2013-08-01: pruefen, ob die preisfreigabe erfolgt ist, danach nichts mehr aendern
		boolean bPreisFreigabeErteilt = bEK?recFuhre.is_PRUEFUNG_EK_PREIS_YES():recFuhre.is_PRUEFUNG_VK_PREIS_YES();
		
		this.ermittle_werte( 	cid_adresse_uf,
				 				cid_artikel_uf, 
				 				cid_vpos_kon_uf,
				 				cid_vpos_std_uf, 
				 				cpreis_manuell_y_n,
				 				bdEINZELPREIS,
				 				cDatum_von,
				 				cDatum_bis,
				 				oMV,
				 				bEK,
				 				bPreisFreigabeErteilt);

	}


	
	/**
	 * 
	 * @param recFuhreOrt
	 * @param oMV
	 * @param oConn
	 * @throws myException
	 */
	public FU___ERMITTLE_PREIS(	RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrt,
								MyE2_MessageVector  		oMV,
								MyConnection       	 		Conn ) throws myException
	{
		super();

		if (Conn != null)
		{
			this.oConn = Conn;
		}

		boolean bEK = recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK");
		
		
		String 			cid_adresse_uf 	= recFuhreOrt.get_ID_ADRESSE_cUF_NN("");
		String 			cid_artikel_uf  = "";
		if (recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez()!=null)
		{
			cid_artikel_uf = recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ID_ARTIKEL_cUF_NN("");
		}
		String 			cid_vpos_kon_uf 	= recFuhreOrt.get_ID_VPOS_KON_cUF_NN("");
		String 			cid_vpos_std_uf 	= recFuhreOrt.get_ID_VPOS_STD_cUF_NN("");
		String 			cpreis_manuell_y_n  = recFuhreOrt.get_MANUELL_PREIS_cUF_NN("N");
		BigDecimal 		bdEINZELPREIS		= recFuhreOrt.get_EINZELPREIS_bdValue(null);
		String          cDatum_von			= recFuhreOrt.get_DATUM_LADE_ABLADE_cF();
		String          cDatum_bis			= recFuhreOrt.get_DATUM_LADE_ABLADE_cF();
		
		if (!bEK)
		{
			cDatum_von			= recFuhreOrt.get_DATUM_LADE_ABLADE_cF();
			cDatum_bis			= recFuhreOrt.get_DATUM_LADE_ABLADE_cF();
		}
		
		
		//vorgaben fuer die rueckgabe:
		this.cID_VPOS_STD_Rueck = cid_vpos_std_uf;
		this.bdNeuerPreis_Rueck = bdEINZELPREIS;
		this.cPreisManuell_Y_N_Rueck = cpreis_manuell_y_n; 
		

		//2013-08-01: pruefen, ob die preisfreigabe erfolgt ist, danach nichts mehr aendern
		boolean bPreisFreigabeErteilt = recFuhreOrt.is_PRUEFUNG_PREIS_YES();
		
		
		this.ermittle_werte( 	cid_adresse_uf,
								cid_artikel_uf, 
								cid_vpos_kon_uf,
								cid_vpos_std_uf, 
								cpreis_manuell_y_n,
								bdEINZELPREIS,
								cDatum_von,
								cDatum_bis,
								oMV,
								bEK,
								bPreisFreigabeErteilt);
	
	}

	
	
	
	
	
	
	
	

	
	private void ermittle_werte(	String 				cid_adresse_uf,
									String 				cid_artikel_uf, 
									String 				cid_vpos_kon_uf,
									String 				cid_vpos_std_uf, 
									String 				cpreis_manuell_y_n,
									BigDecimal 			bdEINZEL_PREIS,
									String              cDatum_von,
									String              cDatum_bis,
									MyE2_MessageVector  oMV,
									boolean      		bEK,
									boolean             bPreisFreigabeErteilt) throws myException
	{
		
		//FALL 0: fuhre wurde kopiert ohne sorte: keine preisermittlung moeglich
		//2011-03-28
		if (S.isEmpty(cid_artikel_uf))
		{
			return;
		}
		
		
		//2013-08-01: pruefen, ob die preisfreigabe erfolgt ist, danach nichts mehr aendern
		if (bPreisFreigabeErteilt) {
			return;                           //dann aendert sich nichts 
		}

		
		
		//FALL 1: Es ist eine eigene Adresse -> keine positionen zu erzeugen
		if (cid_adresse_uf.equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			this.cID_VPOS_STD_Rueck = "";
			this.bdNeuerPreis_Rueck = new BigDecimal(0);
			this.cPreisManuell_Y_N_Rueck = "Y";
			
			return;             //fertig
		}

		
		//FALL 2: der Preis steht auf manuelle eingabe
		if (cpreis_manuell_y_n.equals("Y"))
		{
			//alles bleibt beim alten
			return;
		}
		
		
		
		//FALL 3: Es ist gibt einen kontrakt
		if (S.isFull(cid_vpos_kon_uf))
		{
			RECORD_VPOS_KON  recVposKon = new RECORD_VPOS_KON(cid_vpos_kon_uf,this.oConn);
			
			//2014-04-28: ist der Kontrakt, der vorliegt, nicht abgeschlossen, dann wird der preis geloescht (die kontraktzuordnung bleibt
			if (recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF().equals(cid_adresse_uf) &&
				recVposKon.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF().equals(cid_artikel_uf) && 
				(recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().is_ABGESCHLOSSEN_YES() ||  bibALL.get_RECORD_MANDANT().is_PREISFIND_KONTR_NUR_GEDRUCKT_NO())
				)
			{
				this.bdNeuerPreis_Rueck = recVposKon.get_EINZELPREIS_bdValue(null,2);
				this.cID_VPOS_STD_Rueck = null;
			}
			else
			{
				String cHelp = bEK?"Einkauf":"Verkauf";

				if (recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().is_ABGESCHLOSSEN_NO() && bibALL.get_RECORD_MANDANT().is_PREISFIND_KONTR_NUR_GEDRUCKT_YES()) {
					oMV.add_MESSAGE(new MyE2_Warning_Message(cHelp+": Der gezogene Kontrakt ist nicht geschlossen -> der Preis wird gelöscht"));
				} 
				this.cID_VPOS_STD_Rueck = null;
				this.bdNeuerPreis_Rueck = null;      //der fehler wird anderweitig abgefangen
			}
			return;
		}
		
		//FALL 4: Es ist gibt eine angebotsposition
		if (S.isFull(cid_vpos_std_uf))
		{
			RECORD_VPOS_STD  recVposStd = new RECORD_VPOS_STD(cid_vpos_std_uf,this.oConn);
			
//2014-04-28: ist das angebot, das vorliegt nicht abgeschlossen, dann wird der preis geloescht
//			if (recVposStd.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_ID_ADRESSE_cUF().equals(cid_adresse_uf) &&
//				recVposStd.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF().equals(cid_artikel_uf) )
//			{
//				this.bdNeuerPreis_Rueck = recVposStd.get_EINZELPREIS_bdValue(null,2);
//				
//			}
			if (recVposStd.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_ID_ADRESSE_cUF().equals(cid_adresse_uf) &&
				recVposStd.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF().equals(cid_artikel_uf) &&
				(recVposStd.get_UP_RECORD_VKOPF_STD_id_vkopf_std().is_ABGESCHLOSSEN_YES() || bibALL.get_RECORD_MANDANT().is_PREISFIND_ANGEB_NUR_GEDRUCKT_NO()))
			{
				this.bdNeuerPreis_Rueck = recVposStd.get_EINZELPREIS_bdValue(null,2);
					
			}
			else
			{
				this.cID_VPOS_STD_Rueck = null;
				this.bdNeuerPreis_Rueck = null;
				
				String cHelp = bEK?"Einkauf":"Verkauf";
				
				if (recVposStd.get_UP_RECORD_VKOPF_STD_id_vkopf_std().is_ABGESCHLOSSEN_NO() && bibALL.get_RECORD_MANDANT().is_PREISFIND_ANGEB_NUR_GEDRUCKT_YES()) {
					oMV.add_MESSAGE(new MyE2_Warning_Message(cHelp+": Das gezogene Angebot ist nicht abgeschlossen -> wird gelöscht"));
				} else {
					oMV.add_MESSAGE(new MyE2_Warning_Message(cHelp+": Angebots-Position stimmt nicht mit der Adresse oder Sorte überein -> wird gelöscht"));
				}
			}
			return;
		}
		
		
		//FALL 5: Eine passende angebotsposition suchen
		MyDate  mdVon = new MyDate(cDatum_von,"","");
		MyDate  mdBis = new MyDate(cDatum_bis,"","");
		
		if (S.isFull(mdVon.get_cUmwandlungsergebnis()) && S.isFull(mdBis.get_cUmwandlungsergebnis())) 
		{
		   String cSQL_DB_VON = "TO_DATE('"+mdVon.get_cUmwandlungsergebnis()+"','DD.MM.YYYY')";
		   String cSQL_DB_BIS = "TO_DATE('"+mdBis.get_cUmwandlungsergebnis()+"','DD.MM.YYYY')";
		
		   FU___RECLIST_VPOS_STD_FOR_PREIS recListAngebote = new FU___RECLIST_VPOS_STD_FOR_PREIS(cSQL_DB_VON,cSQL_DB_BIS,cid_adresse_uf,cid_artikel_uf,bEK,null);
		   
		   if (recListAngebote.size()==1)
		   {
			   this.cID_VPOS_STD_Rueck = recListAngebote.get(0).get_ID_VPOS_STD_cUF();
			   this.bdNeuerPreis_Rueck = recListAngebote.get(0).get_EINZELPREIS_bdValue(new BigDecimal(0));
		   }
		   else
		   {
				this.cID_VPOS_STD_Rueck = null;
				this.bdNeuerPreis_Rueck = null;
		   }
		}
		else
		{
			this.cID_VPOS_STD_Rueck = null;
			this.bdNeuerPreis_Rueck = null;
		}
		
	}
	

	public String get_ID_VPOS_STD_Rueck_UF()
	{
		return cID_VPOS_STD_Rueck;
	}


	public BigDecimal get_bdNeuerPreis_Rueck()
	{
		return bdNeuerPreis_Rueck;
	}


	public String get_cPreisManuell_Y_N_Rueck()
	{
		return this.cPreisManuell_Y_N_Rueck;
	}
	
	
}
