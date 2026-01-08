package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;

public class BSAAL_ButtonBaueAngebote_StatementBuilder  {
	
	 public BSAAL_ButtonBaueAngebote_StatementBuilder() 
	 {
		 super();
	 }


	/*
     * aufbau der noch fehlenden preise (Methode mit dem neuen Parameter
	 * "vID_ARTIKELBEZLIEF" überladen)
     */
    public int[] build_Preise(		Vector<String> 					vID_ADRESSES, 
    		                        Vector<String> 					vID_ARTIKELBEZLIEF,
    								String 							ccGueltigVon, 
    								String 							ccGueltigBis,
    								String 							cTextblockAnfang, 
    								String 							cTextBlockEnde,
    								E2_ServerPushMessageContainer   oServerPushContainer)         throws myException
    {
        
    	
        if (vID_ADRESSES == null || vID_ADRESSES.size() == 0) 
            throw new myException("AAL_PreisBuilding:build_Preise:Error Query !!");
        
        
        int[] iRueck = new int[4];
        iRueck[0]=iRueck[1]=iRueck[2]=iRueck[3]=0;
        
        /*
         * die eingangs- und abschlusstexte fuer das formular, werden in bemerkungen und 
         * beschreibung extern geschrieben
         */
        String cEingangsText = cTextblockAnfang.trim();
        String cAbschlussText = cTextBlockEnde.trim();
        
        E2_FortsschrittsBalken   oBalken = new E2_FortsschrittsBalken(vID_ADRESSES.size(),20,new E2_ColorFortschrittsbalken());
        
        
        if (cEingangsText.length()>799 || cAbschlussText.length()>799)
        {
        	bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Formulartexte dürfen nicht mehr als 800 Zeichen haben !!!"));
        }
        
        if (bibMSG.get_bIsOK())
        {
	        int iAnzahlAdressenMitNeuemAngebot	= 0;
	        int iAnzahlAdressenMitError 		= 0;
	        int iAnzahlPosOK 					= 0;
	        int iAnzahlAdressOhneAngebot		= 0;
	        
	        /*
	         * zuerst das gewuenschte datum pruefen
	         */
	        TestingDate oDateTest2 = new TestingDate(ccGueltigVon);
	        TestingDate oDateTest3 = new TestingDate(ccGueltigBis);
	        
	        if (!oDateTest2.testing() | !oDateTest3.testing())
	            throw new myExceptionForUser(new MyE2_String("Bitte geben Sie korrekte Datumseinträge an !!").CTrans());
	        
	        String cGueltigVon		= oDateTest2.get_ISO_DateFormat(false);
	        String cGueltigBis		= oDateTest3.get_ISO_DateFormat(false);

	        
	        Vector<String> vInfoFields = bibALL.get_Vector("NAME1", "NAME2", "ORT");
	        
	        if (oServerPushContainer!=null)
	        {
	        	oServerPushContainer.get_oGridBaseForMessages().setSize(1);
	        }
	        /*
	         * jetzt gehts los
	         */
	        for (int i=0;i<vID_ADRESSES.size();i++)
	        {
	            /*
	             * hashmap fuer eintraege in den vorgangskopf
	             */
	            RECORD_ADRESSE_extend 		oAdress = 				new RECORD_ADRESSE_extend(vID_ADRESSES.get(i));

	            boolean bAbbruch = false;
		        if (oServerPushContainer!=null)
		        {
		            oServerPushContainer.get_oGridBaseForMessages().removeAll();
		            oBalken.set_Wert((i+1));
		            oServerPushContainer.get_oGridBaseForMessages().add(oBalken);
		            oServerPushContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Ich bearbeite gerade :")));
		            oServerPushContainer.get_oGridBaseForMessages().add(new MyE2_Label(oAdress.get___KETTE(vInfoFields, "", "<", ">", " ")));

		            bAbbruch = oServerPushContainer.get_bIsInterupted();
		        }
	            
	            
	            //2012-05-16: aenderung der zahlungsbedingungs-uebernahme, nicht mehr den text, sondern die komplette zahlungsbedingung
	            // String cZahlungsBedingungenKlarTextSQL = 
	            //	oAdress.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null?
	            //	oAdress.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT():
	            //	"NULL";
		        RECORD_ZAHLUNGSBEDINGUNGEN  recZahlBedFirmaBasis = oAdress.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen();
		        
		        
	            	
		        String cLieferbedingungenKlarTextSQL = 
		            	oAdress.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen()!=null?
		            	oAdress.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen().get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT():
		            	"NULL";
	            	
	            
	            
	            /*
	             * transaktionsvector fuer den kunden
	             */
	            Vector<String> vSQL_Stack = new Vector<String>();
	            
	            
	            /*
	             * sql-block fuer den kopf
	             */
	            MySqlStatementBuilder oSQL_ZU_Kopf = new MySqlStatementBuilder();
	            
	            oSQL_ZU_Kopf.addSQL_Paar("ID_VKOPF_STD","SEQ_VKOPF_STD.NEXTVAL");
	            oSQL_ZU_Kopf.addSQL_Paar("ID_ADRESSE",oAdress.get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("VORGANG_TYP",myCONST.VORGANGSART_ABNAHMEANGEBOT,true);
	            oSQL_ZU_Kopf.addSQL_Paar("ERSTELLUNGSDATUM","SYSDATE");
	            oSQL_ZU_Kopf.addSQL_Paar("ABGESCHLOSSEN","N",true);
	            oSQL_ZU_Kopf.addSQL_Paar("DELETED","N",true);
	            oSQL_ZU_Kopf.addSQL_Paar("LIEFERADRESSE_AKTIV","N",true);

				
//				((MyE2_DB_TextField) oMap.get__Comp("NAME_ANSPRECHPARTNER")).set_cActualMaskValue(hmAnsprechInfos.get("ANSPRECHPARTNER"));
//				((MyE2_DB_TextField) oMap.get__Comp("TELEFON_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("TELEFON"));
//				((MyE2_DB_TextField) oMap.get__Comp("TELEFAX_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("TELEFAX"));
//				((MyE2_DB_TextField) oMap.get__Comp("EMAIL_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("EMAIL"));

	            //alte findung 
//				oSQL_ZU_Kopf.addSQL_Paar("NAME_ANSPRECHPARTNER",oAdress.get_StandardAnsprechpartner(true,true,true,true,false,false),true);
//	            RECORD_ADRESSE recAnsprech = oAdress.get_Anpsrechpartner_or_First_RECORD_MITARBEITER();
//	            if (recAnsprech != null)
//	            {
//	            	oSQL_ZU_Kopf.addSQL_Paar("NAME_ANSPRECHPARTNER",recAnsprech.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
//	            }

				
				
//				oSQL_ZU_Kopf.addSQL_Paar("TELEFON_AUF_FORMULAR",oAdress.get_StandardTelefonNumber(),true);
//				oSQL_ZU_Kopf.addSQL_Paar("TELEFAX_AUF_FORMULAR",oAdress.get_StandardFaxNumber(),true);
//	            oSQL_ZU_Kopf.addSQL_Paar("EMAIL_AUF_FORMULAR",oAdress.get_First_eMailAdress4Paper(myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT,true,true),true);

	            //2011-02-28: andere Ansprechpartner-Findung
	            //2011-02-27: aenderung an der Feldfindung:
	            HashMap<String, String>  hmAnsprechInfos = oAdress.get_hmFormularFelderAnsprechpartner(myCONST.VORGANGSART_ABNAHMEANGEBOT);

	            oSQL_ZU_Kopf.addSQL_Paar("NAME_ANSPRECHPARTNER",hmAnsprechInfos.get("ANSPRECHPARTNER"),true);
				oSQL_ZU_Kopf.addSQL_Paar("TELEFON_AUF_FORMULAR",hmAnsprechInfos.get("TELEFON"),true);
				oSQL_ZU_Kopf.addSQL_Paar("TELEFAX_AUF_FORMULAR",hmAnsprechInfos.get("TELEFAX"),true);
	            oSQL_ZU_Kopf.addSQL_Paar("EMAIL_AUF_FORMULAR",hmAnsprechInfos.get("EMAIL"),true);

	            
	            oSQL_ZU_Kopf.addSQL_Paar("FORMULARTEXT_ANFANG",cEingangsText,true);
	            oSQL_ZU_Kopf.addSQL_Paar("FORMULARTEXT_ENDE",cAbschlussText,true);

	            
	            oSQL_ZU_Kopf.addSQL_Paar("ID_WAEHRUNG_FREMD",	bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT());

	            
	            //2011-01-21: neue uebernahme der ansprechpartner-felder
	            oSQL_ZU_Kopf.addSQL_Paar("ID_USER",					bibALL.get_RECORD_USER().get_ID_USER_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("NAME_BEARBEITER_INTERN",	bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
	            oSQL_ZU_Kopf.addSQL_Paar("TEL_BEARBEITER_INTERN",	bibALL.get_RECORD_USER().get_TELEFON_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("FAX_BEARBEITER_INTERN",	bibALL.get_RECORD_USER().get_TELEFAX_VALUE_FOR_SQLSTATEMENT());

	            
				if (oAdress.get_UP_RECORD_USER_id_user()!=null)
				{
					oSQL_ZU_Kopf.addSQL_Paar("ID_USER_ANSPRECH_INTERN",	oAdress.get_UP_RECORD_USER_id_user().get_ID_USER_VALUE_FOR_SQLSTATEMENT());
					oSQL_ZU_Kopf.addSQL_Paar("NAME_ANSPRECH_INTERN",	oAdress.get_UP_RECORD_USER_id_user().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
					oSQL_ZU_Kopf.addSQL_Paar("TEL_ANSPRECH_INTERN",		oAdress.get_UP_RECORD_USER_id_user().get_TELEFON_VALUE_FOR_SQLSTATEMENT());
					oSQL_ZU_Kopf.addSQL_Paar("FAX_ANSPRECH_INTERN",		oAdress.get_UP_RECORD_USER_id_user().get_TELEFAX_VALUE_FOR_SQLSTATEMENT());
				}
				
				if (oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter()!=null)
				{
					oSQL_ZU_Kopf.addSQL_Paar("ID_USER_SACHBEARB_INTERN",oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_ID_USER_VALUE_FOR_SQLSTATEMENT());
					oSQL_ZU_Kopf.addSQL_Paar("NAME_SACHBEARB_INTERN",	oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
					oSQL_ZU_Kopf.addSQL_Paar("TEL_SACHBEARB_INTERN",	oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFON_VALUE_FOR_SQLSTATEMENT());
					oSQL_ZU_Kopf.addSQL_Paar("FAX_SACHBEARB_INTERN",	oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFAX_VALUE_FOR_SQLSTATEMENT());
				}
	            

	            
	            oSQL_ZU_Kopf.addSQL_Paar("VORNAME",			oAdress.get_VORNAME_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("NAME1",			oAdress.get_NAME1_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("NAME2",			oAdress.get_NAME2_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("NAME3",			oAdress.get_NAME3_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("STRASSE",			oAdress.get_STRASSE_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("HAUSNUMMER",		oAdress.get_HAUSNUMMER_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("PLZ",				oAdress.get_PLZ_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("ORT",				oAdress.get_ORT_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("ORTZUSATZ",		oAdress.get_ORTZUSATZ_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("PLZ_POB",			oAdress.get_PLZ_POB_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("POB",				oAdress.get_POB_VALUE_FOR_SQLSTATEMENT());
	            
	            oSQL_ZU_Kopf.addSQL_Paar("LAENDERCODE",		oAdress.get_UP_RECORD_LAND_id_land() == null?"NULL": oAdress.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_VALUE_FOR_SQLSTATEMENT());
	            oSQL_ZU_Kopf.addSQL_Paar("SPRACHE",			oAdress.get_UP_RECORD_SPRACHE_id_sprache() == null?"NULL": oAdress.get_UP_RECORD_SPRACHE_id_sprache().get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
	            
	            //2012-05-16: zahlungsbedingungen werden jetzt komplett geladen
	            if (recZahlBedFirmaBasis==null)
	            {
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ID_ZAHLUNGSBEDINGUNGEN,"NULL");
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ZAHLUNGSBEDINGUNGEN,	"NULL");
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ZAHLTAGE,				"NULL");
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__FIXMONAT,				"NULL");
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__FIXTAG,				"NULL");
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__SKONTO_PROZENT,		"NULL");
	            }
	            else
	            {
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ID_ZAHLUNGSBEDINGUNGEN,recZahlBedFirmaBasis.get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ZAHLUNGSBEDINGUNGEN,	recZahlBedFirmaBasis.get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__ZAHLTAGE,				recZahlBedFirmaBasis.get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__FIXMONAT,				recZahlBedFirmaBasis.get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__FIXTAG,				recZahlBedFirmaBasis.get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
	            	oSQL_ZU_Kopf.addSQL_Paar(RECORD_VKOPF_STD.FIELD__SKONTO_PROZENT,		recZahlBedFirmaBasis.get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
	            }
	            //neu gesetzt
	            
	            oSQL_ZU_Kopf.addSQL_Paar("LIEFERBEDINGUNGEN",	cLieferbedingungenKlarTextSQL);
	            oSQL_ZU_Kopf.addSQL_Paar("GUELTIG_VON",			cGueltigVon,true);
	            oSQL_ZU_Kopf.addSQL_Paar("GUELTIG_BIS",			cGueltigBis,true);
	            
	            
	            
	            
	            String SQL_ADD_VKOPF = "INSERT INTO "+bibE2.cTO()+".JT_VKOPF_STD "+oSQL_ZU_Kopf.get_cFieldsBlock(true)+" VALUES "+oSQL_ZU_Kopf.get_cValuesBlock(true);
	            
	            boolean bKundeHatArtikel = false;
	            
	            RECLIST_ARTIKELBEZ_LIEF  reclistArtbezLief = oAdress.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
	            
	            //2021-04-01 aenderungen an der reihenfolge der positionen: in zukuft sortiert nach anr1/anr2
	            VEK<RECORD_ARTIKELBEZ_LIEF> artikelBezLiefs = new VEK<>();
	            
	            artikelBezLiefs._a(reclistArtbezLief.values());
	            
//	            //jetzt sortieren
	            artikelBezLiefs.sort((abl1,abl2)-> {
	            	String anr12_1 = "";
	            	String anr12_2 = "";
					try {
						anr12_1 = abl1.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")
											+"-"+abl1.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cUF_NN("");
						anr12_2 = abl2.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")
											+"-"+abl2.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cUF_NN("");
					} catch (myException e) {
						e.printStackTrace();
					}
	            	
	            	return anr12_1.compareTo(anr12_2);
	            });
	            
	            
	            
//alt	            for (int k=0;k<reclistArtbezLief.get_vKeyValues().size();k++)
	            for (int k=0;k<artikelBezLiefs.size();k++)
	            {
	            	
	            	RECORD_ARTIKELBEZ_LIEF 				recArtbezLief = 	artikelBezLiefs.get(k);           	
	            	
//	            	if (vID_ARTIKELBEZLIEF.contains(reclistArtbezLief.get(k).get_ID_ARTIKELBEZ_LIEF_VALUE_FOR_SQLSTATEMENT())) // nur für Artikel die im Sortenbereich liegen
	            	if (vID_ARTIKELBEZLIEF.contains(recArtbezLief.get_ID_ARTIKELBEZ_LIEF_VALUE_FOR_SQLSTATEMENT())) // nur für Artikel die im Sortenbereich liegen
	            	{
//alt	            	RECORD_ARTIKELBEZ_LIEF 				recArtbezLief = 	reclistArtbezLief.get(reclistArtbezLief.get_vKeyValues().get(k));
	            	RECORD_ARTIKEL_BEZ     				recArtBez     = 	recArtbezLief.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
	            	RECORD_ARTIKEL  	   				recArtikel    = 	recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();
	            	
	            	//2012-05-16: zahlungsbedingungen werden komplett gesetzt
	            	RECORD_ZAHLUNGSBEDINGUNGEN          recZahlBedBeiArtikel = null;
	            	if (recArtbezLief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
	            	{
	            		recZahlBedBeiArtikel = recArtbezLief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen();
	            	}
	            	else
	            	{
	            		recZahlBedBeiArtikel = recZahlBedFirmaBasis;
	            	}
	            	//2012-05-16
	            	
	            	
	            	if (recArtbezLief.get_ARTBEZ_TYP_cUF().equals("EK") && recArtbezLief.is_ANGEBOT_YES())    //nur ek-sorten
		            {
		                /*
		                 * pruefen, ob fuer diesen zeitraum und diesen kunden schon eine preisinfo jeder artikelbezeichung existiert
		                 */
		                String cQueryIstSchonDa = "SELECT COUNT(JT_VPOS_STD.ID_VPOS_STD) FROM "+
		                								bibE2.cTO()+".JT_VKOPF_STD, " +
		                								bibE2.cTO()+".JT_VPOS_STD_ANGEBOT, "+
		                								bibE2.cTO()+".JT_VPOS_STD "+
		                								" WHERE JT_VKOPF_STD.ID_ADRESSE="+oAdress.get_ID_ADRESSE_cUF()+
		                								" AND JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD "+
		                								" AND JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD "+
		                								" AND   NVL(JT_VPOS_STD.DELETED,'N')='N' "+
		                								" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ABNAHMEANGEBOT)+
		                								" AND JT_VPOS_STD_ANGEBOT.GUELTIG_VON="+bibALL.MakeSql(cGueltigVon)+
		                								" AND JT_VPOS_STD_ANGEBOT.GUELTIG_BIS="+bibALL.MakeSql(cGueltigBis)+
		                								" AND JT_VPOS_STD.ID_ARTIKEL_BEZ="+recArtbezLief.get_ID_ARTIKEL_BEZ_cUF();
		                
		                String cAnzahlBereitsVorhanden = bibDB.EinzelAbfrage(cQueryIstSchonDa,"","","");
		
		                if (cAnzahlBereitsVorhanden.equals(""))
		                    throw new myException(("Error (2) query for double PriceInfo ..."));   // duerfte hier nicht vorkommen
		
		            	
		            	if (cAnzahlBereitsVorhanden.equals("0"))
		            	{
		            		if (! bKundeHatArtikel)                  // beim der ersten gefundenen position wird auch ein kopf geschrieben
		            		{
		            			bKundeHatArtikel = true;
		            			vSQL_Stack.add(SQL_ADD_VKOPF);
		            		}
			            	
			            	MySqlStatementBuilder oSQL_ZU_Pos = new MySqlStatementBuilder(); 		// position
			 
			                oSQL_ZU_Pos.addSQL_Paar("ID_VPOS_STD","SEQ_VPOS_STD.NEXTVAL");
			                oSQL_ZU_Pos.addSQL_Paar("ID_VKOPF_STD","SEQ_VKOPF_STD.CURRVAL");
			                oSQL_ZU_Pos.addSQL_Paar("POSITION_TYP",myCONST.VG_POSITION_TYP_ARTIKEL,true);
			                oSQL_ZU_Pos.addSQL_Paar("POSITIONSNUMMER",""+(k+1));
			                oSQL_ZU_Pos.addSQL_Paar("ID_ARTIKEL",			recArtikel.get_ID_ARTIKEL_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("ID_ARTIKEL_BEZ",		recArtbezLief.get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("ANR1",					recArtikel.get_ANR1_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("ANR2",					recArtBez.get_ANR2_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("ARTBEZ1",				recArtBez.get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT());
			                
			                String cArtbez2 = new RECORD_ARTBEZ_LIEF_extend(recArtbezLief).get_ARTBEZ_2_Incl_Specials();
			                
			                oSQL_ZU_Pos.addSQL_Paar("ARTBEZ2",				cArtbez2,true);
			                oSQL_ZU_Pos.addSQL_Paar("EINHEITKURZ",			recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("EINHEIT_PREIS_KURZ",	recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis()==null?
			                												recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT():
			                												recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			                						
			                oSQL_ZU_Pos.addSQL_Paar("MENGENDIVISOR",		recArtikel.get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT());
			                oSQL_ZU_Pos.addSQL_Paar("LAGER_VORZEICHEN",		"0");
			                oSQL_ZU_Pos.addSQL_Paar("DELETED","N",true);
		
			                //2012-05-16: zahlungsbedingungen werden komplett gesetzt
//		                	oSQL_ZU_Pos.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",	S.isEmpty(recArtbezLief.get_ZAHLUNGSBEDINGUNGEN_cUF())?
//		                													cZahlungsBedingungenKlarTextSQL:
//		                													recArtbezLief.get_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				            //2012-05-16: zahlungsbedingungen werden jetzt komplett geladen
				            if (recZahlBedBeiArtikel==null)
				            {
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ID_ZAHLUNGSBEDINGUNGEN,"NULL");
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ZAHLUNGSBEDINGUNGEN,	"NULL");
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ZAHLTAGE,				"NULL");
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__FIXMONAT,				"NULL");
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__FIXTAG,				"NULL");
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__SKONTO_PROZENT,		"NULL");
				            }
				            else
				            {
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ID_ZAHLUNGSBEDINGUNGEN,	recZahlBedBeiArtikel.get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ZAHLUNGSBEDINGUNGEN,		recZahlBedBeiArtikel.get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__ZAHLTAGE,				recZahlBedBeiArtikel.get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__FIXMONAT,				recZahlBedBeiArtikel.get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__FIXTAG,					recZahlBedBeiArtikel.get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
				            	oSQL_ZU_Pos.addSQL_Paar(RECORD_VPOS_STD.FIELD__SKONTO_PROZENT,			recZahlBedBeiArtikel.get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
				            }
				            //2012-05-16

			                
		
		                	oSQL_ZU_Pos.addSQL_Paar("LIEFERBEDINGUNGEN",	S.isEmpty(recArtbezLief.get_LIEFERBEDINGUNGEN_cUF())?
		                													cLieferbedingungenKlarTextSQL:
																			recArtbezLief.get_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
		                	
		                	oSQL_ZU_Pos.addSQL_Paar("ID_WAEHRUNG_FREMD",	bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT());
		                	oSQL_ZU_Pos.addSQL_Paar("WAEHRUNGSKURS",		"1");

			                vSQL_Stack.add("INSERT INTO "+bibE2.cTO()+".JT_VPOS_STD "+oSQL_ZU_Pos.get_cFieldsBlock(true)+" VALUES "+oSQL_ZU_Pos.get_cValuesBlock(true));

			                
			                MySqlStatementBuilder oSQL_ZU_Pos_Angebot = new MySqlStatementBuilder(); 		// position_wurmfortsatz
			           	 
			                oSQL_ZU_Pos_Angebot.addSQL_Paar("ID_VPOS_STD_ANGEBOT","SEQ_VPOS_STD_ANGEBOT.NEXTVAL",false);
			                oSQL_ZU_Pos_Angebot.addSQL_Paar("ID_VPOS_STD","SEQ_VPOS_STD.CURRVAL",false);
			                oSQL_ZU_Pos_Angebot.addSQL_Paar("GUELTIG_VON",cGueltigVon,true);
			                oSQL_ZU_Pos_Angebot.addSQL_Paar("GUELTIG_BIS",cGueltigBis,true);
			                oSQL_ZU_Pos_Angebot.addSQL_Paar("DELETED","N",true);
		
			                vSQL_Stack.add("INSERT INTO "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT "+oSQL_ZU_Pos_Angebot.get_cFieldsBlock(true)+" VALUES "+oSQL_ZU_Pos_Angebot.get_cValuesBlock(true));
		            	
			                }
		                }
	                }
	            }
	            
	            if (vSQL_Stack.size()>0)
	            {
	            	MyE2_MessageVector oMV =bibDB.ExecMultiSQLVector(vSQL_Stack, true);
		            if (oMV.get_bIsOK())
		            {
		            	iAnzahlAdressenMitNeuemAngebot++;
		            	iAnzahlPosOK += (vSQL_Stack.size()-1);
		            }
		            else
		            {
		            	iAnzahlAdressenMitError ++;
		            }
	            }
	            else
	            {
	            	iAnzahlAdressOhneAngebot++;
	            }
	            
	            if (bAbbruch)
	            {
	            	break;
	            }
	            
	        }
	
	        iRueck[0] = iAnzahlAdressenMitNeuemAngebot;
	        iRueck[1] = iAnzahlAdressOhneAngebot;
	        iRueck[2] = iAnzahlAdressenMitError;
	        iRueck[3] = iAnzahlPosOK/2;				// jede position hat 2 sql-statements: position und wurmfortsatz
	        
        }
        return iRueck;
    }

}
