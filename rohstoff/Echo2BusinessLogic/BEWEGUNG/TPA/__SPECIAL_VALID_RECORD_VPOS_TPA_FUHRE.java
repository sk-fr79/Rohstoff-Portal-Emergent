package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceCheckAH7AdresseIsReady4AH7Print;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceReadOrCreateAndProfileAH7Steuerdatensatz;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;


/*
 * spezielle record-vpos-tpa-fuhre-version, die benutzt wird, um vor dem drucken unvolstaendige Anhang 7 - dateien zu finden
 */
public class __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE extends RECORD_VPOS_TPA_FUHRE
{
	private String cID_VPOS_TPA_FUHRE = null;
	private String cID_VPOS_TPA_FUHRE_ORT = null;
	
	private boolean bAnhang7_nur_warnung_keine_fehler = false;
	
	public __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE(String ID_VPOS_TPA_FUHRE, String ID_VPOS_TPA_FUHRE_ORT) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN " +
				" WHERE ID_VPOS_TPA_FUHRE="+ID_VPOS_TPA_FUHRE+" AND ID_VPOS_TPA_FUHRE_ORT "+(S.isEmpty(ID_VPOS_TPA_FUHRE_ORT)?" IS NULL":"="+ID_VPOS_TPA_FUHRE_ORT));
		
		this.cID_VPOS_TPA_FUHRE = 		ID_VPOS_TPA_FUHRE;
		this.cID_VPOS_TPA_FUHRE_ORT = 	ID_VPOS_TPA_FUHRE_ORT;

		this.bAnhang7_nur_warnung_keine_fehler = __RECORD_MANDANT_ZUSATZ.IS__Value("ANHANG7_FELDCHECK_NUR_WARN", "N", "N");
		
		
		if ( (S.isEmpty(this.cID_VPOS_TPA_FUHRE)))   //sicherheitshalber auch die messages fuellen, duerfte sowieso nicht vorkommen
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Programmfehler! Nur erlaubt: FUHRE + NULL or FUHRE+FUHRE_ORT !"));
		}
		
		
		if ( (S.isEmpty(this.cID_VPOS_TPA_FUHRE)))
			throw new myException(this,"Programm error. Allowed: FUHRE + NULL or FUHRE+FUHRE_ORT !");

		
		try
		{
			bibMSG.add_MESSAGE(this.pruefe_vollstaendigkeit());
		}
		catch (myException e)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler !!(1)")));
			throw e;
			
		}
		catch (Exception e)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler !!(2)")));
			throw new myException(e.getLocalizedMessage());
		}
		
		
	}
	
	
	private MyE2_MessageVector  pruefe_vollstaendigkeit() throws myException 	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		if (this.is_PRINT_EU_AMTSBLATT_YES()) {
		
			//komplett unabhaengig pruefen
			RECORD_VPOS_TPA_FUHRE      recFuhre = 	new RECORD_VPOS_TPA_FUHRE(this.cID_VPOS_TPA_FUHRE);
			
			//jetzt pruefen, ob es eine korrekte menge gibt
			RECLIST_VPOS_TPA_FUHRE_ORT  recListLadestellen = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("UPPER(NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N'))='N' " +
																																	" AND NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'-')='EK'",null,true);

			
			RECLIST_VPOS_TPA_FUHRE_ORT  recListAbladestellen = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("UPPER(NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N'))='N' " +
																																	" AND NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'-')='VK'",null,true);

			
			Vector<AdressHelper>  vAdressenZuPruefen = new Vector<AdressHelper>(); 
			
			vAdressenZuPruefen.add(new AdressHelper(bibALL.get_ID_ADRESS_MANDANT(), new MyE2_String("Mandanten-Adresse")));
			vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_START_cUF_NN(""), new MyE2_String("Hauptfuhre, Adresse Lieferant")));
			vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN(""), new MyE2_String("Hauptfuhre, Ladeort")));
			vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN(""), new MyE2_String("Hauptfuhre, Adresse Abnehmer")));
			vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""), new MyE2_String("Hauptfuhre, Abladeort")));
			
			if (S.isFull(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("")))
			{
				vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(""), 	new MyE2_String("Hauptfuhre, Fremdauftraggeber")));
			}
			if (S.isFull(recFuhre.get_ID_ADRESSE_SPEDITION_cUF_NN("")))
			{
				vAdressenZuPruefen.add(new AdressHelper(recFuhre.get_ID_ADRESSE_SPEDITION_cUF_NN(""), 		new MyE2_String("Hauptfuhre, Spedition")));
			}
			if (this.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
			{
				vAdressenZuPruefen.add(new AdressHelper(this.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_ADRESSE_cUF_NN(""),new MyE2_String("Transportauftrag, Spedition")));
			}		

			
			//jetzt alle orte checken
			for (int i=0; i<recListLadestellen.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_ORT  recFO = recListLadestellen.get(i);
				vAdressenZuPruefen.add(new AdressHelper(recFO.get_ID_ADRESSE_cUF_NN(""), new MyE2_String("Zusatzladeort, Adresse Lieferant")));
				vAdressenZuPruefen.add(new AdressHelper(recFO.get_ID_ADRESSE_LAGER_cUF_NN(""), new MyE2_String("Zusatzladeort, Ladestelle")));
			}
			for (int i=0; i<recListAbladestellen.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_ORT  recFO = recListAbladestellen.get(i);
				vAdressenZuPruefen.add(new AdressHelper(recFO.get_ID_ADRESSE_cUF_NN(""), new MyE2_String("Zusatzabladeort, Adresse Abnehmer")));
				vAdressenZuPruefen.add(new AdressHelper(recFO.get_ID_ADRESSE_LAGER_cUF_NN(""), new MyE2_String("Zusatzabladeort, Abladestelle")));
			}
			
			
			
			//jetzt pruefungen durchfuehren
			oMV.add_MESSAGE(this.pruefe_fuhrenfelder(recFuhre));
			for (int i=0; i<recListLadestellen.get_vKeyValues().size();i++)
			{
				oMV.add_MESSAGE(this.pruefe_fuhrenortfelder(recListLadestellen.get(i)));
			}
			for (int i=0; i<recListAbladestellen.get_vKeyValues().size();i++)
			{
				oMV.add_MESSAGE(this.pruefe_fuhrenortfelder(recListAbladestellen.get(i)));
			}
			
			oMV.add_MESSAGE(this.pruefe_adressen(vAdressenZuPruefen));
			
			
			
			
			//jetzt noch die mengen-pruefung
			
			boolean 	bFuhreHatAbladestellen = (recListAbladestellen.get_vKeyValues().size()>0);
			
			
			boolean     bPlanMengeIstGefuellt = true;
			if (S.isEmpty(this.get_ANTEIL_PLANMENGE_LIEF_cUF_NN("")))
			{
				bPlanMengeIstGefuellt = false;
			}
			for (int i=0;i<recListLadestellen.get_vKeyValues().size();i++)
			{
				if (S.isEmpty(recListLadestellen.get(i).get_ANTEIL_PLANMENGE_cUF_NN("")))
				{
					bPlanMengeIstGefuellt = false;
				}
			}
			
			
			
			boolean     bLadeMengeIstGefuellt = true;
			if (S.isEmpty(this.get_ANTEIL_LADEMENGE_LIEF_cUF_NN("")))
			{
				bLadeMengeIstGefuellt = false;
			}
			for (int i=0;i<recListLadestellen.get_vKeyValues().size();i++)
			{
				if (S.isEmpty(recListLadestellen.get(i).get_ANTEIL_LADEMENGE_cUF_NN("")))
				{
					bLadeMengeIstGefuellt = false;
				}
			}
			
			boolean bSonderMengeIstGefuellt = S.isFull(this.get_EU_BLATT_MENGE_cUF_NN(""));
			boolean bSonderVolumenIstGefuellt = S.isFull(this.get_EU_BLATT_VOLUMEN_cUF_NN(""));
			
			//wenn folgendes eintritt
			if (!bSonderMengeIstGefuellt && !bSonderVolumenIstGefuellt)
			{
				if (bFuhreHatAbladestellen || (!bPlanMengeIstGefuellt && !bLadeMengeIstGefuellt))
				{
					// dann muss der anwender bei den relevante Abladeseiten mindestens einen anhang 7 - Volumeneintrag vornehmen
					
					if (S.isEmpty(cID_VPOS_TPA_FUHRE_ORT))
					{
						oMV.add_MESSAGE(new ownAlarmMessage(
								new MyE2_String("Anhang 7-Fehler: Bitte geben Sie in der Hauptfuhre mindestens das Sonderfeld  <EU-Blatt abw.  Volumen> an"+
										(bFuhreHatAbladestellen?"----> Mehrere Abladestellen":""))));
					}
					else
					{
						oMV.add_MESSAGE(new ownAlarmMessage(
								new MyE2_String("Anhang 7-Fehler: Bitte geben Sie in den relevanten Abladeorten mindestens das Sonderfeld  <EU-Blatt abw.  Volumen> an"+
										(bFuhreHatAbladestellen?"----> Mehrere Abladestellen":""))));
					}
				}
			}
			
			
			//__hier stimmt was nicht
			/*
			 * diese pruefung muss in die erzeugung der jasperhashes, da z.B. ah7-blatter auch gedruckt werden, ohne dass 
			 * in der fuhre der schalter eingeschaltet ist !!	
			 */
			
			
			
		}
		
		return oMV;
	}
	
	
	
	public MyE2_MessageVector pruefeAH7Verfahren(E2_JasperHASH hashMapWithAH7) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		String id_adresse_start = this.get_ID_ADRESSE_LAGER_START_cUF_NN("0");
		String id_adresse_ziel =  this.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("0");
		
		if (id_adresse_start.equals("0") || id_adresse_ziel.equals("0") ) {

			mv._addAlarm(S.ms("Anhang 7-Fehler: Keine Feststellung der id_adresse_start -/ziel möglich!"));
			return mv;
			
		} else {
			
			//zuerst pruefen, ob es einen steuersatz gibt
			Rec21_AH7_Steuerdatei recAH7Steuer = new Rec21_AH7_Steuerdatei(id_adresse_start, id_adresse_ziel);
			
			//DEBUG.println("ID:"+recAH7Steuer.getActualID().toString());
			
			boolean satzIstVorhanden = 	!recAH7Steuer.is_newRecordSet();
			boolean satzIstBereit = 	satzIstVorhanden && recAH7Steuer.isPresentAndReadyToUse(mv);
			if (mv.hasAlarms())  {
				satzIstBereit=false;
			}

			hashMapWithAH7.put(___ENUM_FUHRE_JASPERPARAMETER.USE_AH7_STEUERTABELLE.db_val(), new Boolean(false));
			if (satzIstVorhanden && satzIstBereit) {
				
				//jetzt noch die vollstaendigkeit der gefundenen adressen ueberpruefen
				VEK<Long> vAdressesForBeleg = new VEK<Long>();
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_abfallerzeuger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_import_empfaenger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_verbr_veranlasser));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_verwertungsanlage));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_abfallerzeuger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_import_empfaenger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_verbr_veranlasser));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_verwertungsanlage));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_abfallerzeuger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_import_empfaenger));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_verbr_veranlasser));
				vAdressesForBeleg._addValidated((v)->{ return (v!=null && v>=0); },  recAH7Steuer.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_verwertungsanlage));

				for (Long l: vAdressesForBeleg) {
					new _PdServiceCheckAH7AdresseIsReady4AH7Print().isReadyForAH7Print(l, mv);
				}
				hashMapWithAH7.put(___ENUM_FUHRE_JASPERPARAMETER.USE_AH7_STEUERTABELLE.db_val(), new Boolean(true));

				return mv;
			} else if (satzIstVorhanden && (!satzIstBereit)) {
				String idFormated = "";
				try {
					idFormated = recAH7Steuer.getFs(AH7_STEUERDATEI.id_ah7_steuerdatei,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
				//dann meldung an qualifizierer
				mv._add(ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.generateMessages(MODUL.AH7_STEUERDATEI_LISTE, 
						recAH7Steuer.get_key_value(), "Bitte um Qualifizierung / Korrektur des AH7-Steuerdatensatz (ID: "+idFormated+")","AH7-Druck-Steuertabelle"));

				//der anwender kann nicht weitermachen, da erst die qualifizierung erfolgen muss
				mv._addAlarm("Druck des Anhang 7 kann nicht erfolgen, da ein neuer oder ungültiger AH7-Druck-Steuerdatensatz (ID: "+idFormated+") zuerst qualifiziert werden muss !");
						
				return mv;
			} else if ((!satzIstVorhanden) && ENUM_MANDANT_DECISION.AH7_USE_OLD_IF_NO_RULE_EXISTING.is_YES() ) {
				return mv;   //dann das alte verfahren
			}  else if ((!satzIstVorhanden) && ENUM_MANDANT_DECISION.AH7_USE_OLD_IF_NO_RULE_EXISTING.is_NO()) {

				//dann satz anlegen und meldung an Qualifizierer senden, Benutzer bekommt abbruch-info
				_PdServiceReadOrCreateAndProfileAH7Steuerdatensatz creator = new _PdServiceReadOrCreateAndProfileAH7Steuerdatensatz(id_adresse_start,id_adresse_ziel,mv);
				MyLong lid_relation = new MyLong(creator.get_id_ah7_steuerdatei_found_or_created());
					
				if (lid_relation.isOK() && mv.isOK()) {
					//dann eine Meldung mit der Bitte um Qualifizierung versenden
					mv._add(ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.generateMessages(MODUL.AH7_STEUERDATEI_LISTE, 
							lid_relation.get_cUF_LongString(), "Bitte um Fertigstellung und Freigabe der neu angelegten AH7-Druck-Steuerdatensatz (ID: "+lid_relation.get_cF_LongString()+")","AH7-Druck-Steuertabelle"));
	
					//der anwender kann nicht weitermachen, da erst die qualifizierung erfolgen muss
					mv._addAlarm("Druck des Anhang 7 kann nicht erfolgen, da ein neuer AH7-Steuerdatensatz zuerst qualifiziert werden");
							
					return mv;
				} else {
					mv._addAlarm(S.ms("Fehler beim Erstellen eines Steuerungsdatensatzes mit id_adresse_start: "+id_adresse_start+" und id_adresse_ziel: "+id_adresse_ziel));
					return mv;
				}
			} else {
				//kann eigentlich nie hier landen
				mv._addAlarm(S.ms("Undefinierter AH7-Fehlerzustand !"));
				return mv;
			}
		}
	}
	
	

	private MyE2_MessageVector  pruefe_adressen(Vector<AdressHelper> vAdressen) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		VectorSingle  vGegenpruefung = new VectorSingle();             //sorgt dafuer, dass eine adresse nur beim ersten auftreten in der fehlermeldung erscheint
		
		for (int i=0;i<vAdressen.size();i++)
		{
			if (!vGegenpruefung.contains(vAdressen.get(i).ID_ADRESS))
			{
				vGegenpruefung.add(vAdressen.get(i).ID_ADRESS);
				
				RECORD_ADRESSE  recAdresseToCheck = new RECORD_ADRESSE(vAdressen.get(i).ID_ADRESS);
				
				if (S.isEmpty(recAdresseToCheck.get_NAME1_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Name1> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				if (S.isEmpty(recAdresseToCheck.get_STRASSE_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Strasse> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				if (S.isEmpty(recAdresseToCheck.get_ORT_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Ort> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				
				if (S.isEmpty(recAdresseToCheck.get_EU_BEIBLATT_ANSPRECH_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Ansprechpa. EU-Blatt> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				if (S.isEmpty(recAdresseToCheck.get_EU_BEIBLATT_EMAIL_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <e-Mail EU-Blatt> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				if (S.isEmpty(recAdresseToCheck.get_EU_BEIBLATT_TEL_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Telefonfeld EU-Blatt> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
				if (S.isEmpty(recAdresseToCheck.get_EU_BEIBLATT_FAX_cUF_NN("")))
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: <Telefaxfeld EU-Blatt> ist leer: ",true,vAdressen.get(i).Info.CTrans(),false," -> ID: "+vAdressen.get(i).ID_ADRESS,false)));
				}
			}
		}
		
		return oMV;
	
	}
	
	
	

	private MyE2_MessageVector  pruefe_fuhrenfelder(RECORD_VPOS_TPA_FUHRE  rec_fuhre) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (S.isEmpty(rec_fuhre.get_ARTBEZ1_EK_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Artikelbezeichnung fehlt in Hauptfuhre (links)")));
		}
		
		if (S.isEmpty(rec_fuhre.get_ARTBEZ1_VK_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Artikelbezeichnung fehlt in Hauptfuhre (rechts)")));
		}

		if (S.isEmpty(rec_fuhre.get_L_LAENDERCODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Ländercode fehlt in Hauptfuhre (links)")));
		}

		if (S.isEmpty(rec_fuhre.get_A_LAENDERCODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Ländercode fehlt in Hauptfuhre (rechts)")));
		}
		
		if (S.isEmpty(rec_fuhre.get_ID_EAK_CODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: AVV-Code in Hauptfuhre fehlt")));
		}

		if (S.isEmpty(rec_fuhre.get_BASEL_CODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Basel-Code in Hauptfuhre fehlt")));
		}
		
		if (S.isEmpty(rec_fuhre.get_TRANSPORTMITTEL_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Transportmittel in Hauptfuhre fehlt!")));
		}
		
		if (S.isEmpty(rec_fuhre.get_GEAENDERT_VON_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Benutzer der letzten Änderung Hauptfuhre ist nicht zu ermitteln!")));
		}
	
		
		oMV.add_MESSAGE(this.pruefe_benutzerkuerzel(rec_fuhre.get_GEAENDERT_VON_cUF_NN("")));
		
		return oMV;
	}
	
	

	private MyE2_MessageVector  pruefe_fuhrenortfelder(RECORD_VPOS_TPA_FUHRE_ORT  rec_ort) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		String cZusatz = "Zusatz-Abladeort";
		if (rec_ort.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"))
		{
			 cZusatz = "Zusatz-Ladeort";
		}
		
		if (S.isEmpty(rec_ort.get_ARTBEZ1_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Artikelbezeichnung fehlt in "+cZusatz)));
		}

		if (S.isEmpty(rec_ort.get_LAENDERCODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Ländercode fehlt in "+cZusatz)));
		}

		if (S.isEmpty(rec_ort.get_ID_EAK_CODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: AVV-Code fehlt in "+cZusatz)));
		}

		if (S.isEmpty(rec_ort.get_BASEL_CODE_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Basel-Code fehlt in "+cZusatz)));
		}
		
		if (S.isEmpty(rec_ort.get_GEAENDERT_VON_cUF_NN("")))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Benutzer der letzten Änderung  ist nicht zu ermitteln: "+cZusatz)));
		}

		oMV.add_MESSAGE(this.pruefe_benutzerkuerzel(rec_ort.get_GEAENDERT_VON_cUF_NN("")));

		
		return oMV;
	}
	

	
	
	private MyE2_MessageVector pruefe_benutzerkuerzel(String cKuerzel)  throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (S.isEmpty(cKuerzel))
		{
			oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("In der Fuhre oder einem Ort ist ein Benutzerkürzel der letzten Änderung leer!",true)));
		}
		else
		{
			RECLIST_USER  recTest = new RECLIST_USER("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+this.get_ID_MANDANT_cUF()+
													" AND NVL(AKTIV,'N')='Y' AND UPPER(KUERZEL)=UPPER("+bibALL.MakeSql(cKuerzel)+")");
	
			if (recTest.get_vKeyValues().size()!=1)    //nicht vorhanden oder mehrdeutig
			{
				oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Sachbearbeiter mit dem Kürzel ",true,cKuerzel,false," unbekannt oder nicht eindeutig!",true)));
			}
			else
			{
				//jetzt pruefen, ob eine unterschriftsdatei vorliegt
				RECLIST_ARCHIVMEDIEN  recListArchivMedien = new RECLIST_ARCHIVMEDIEN(
						"SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE TABLENAME='USER' AND ID_TABLE="+
						recTest.get(0).get_ID_USER_cUF()+" AND PROGRAMM_KENNER='" +
								Archiver_CONST.PROGRAMMKENNER.BENUTZER_SIGNATUR.get_DB_WERT()+ "'");
				
				if (recListArchivMedien.get_vKeyValues().size()!=1)
				{
					oMV.add_MESSAGE(new ownAlarmMessage(new MyE2_String("Anhang 7-Fehler: Sachbearbeiter mit dem Kürzel ",true,cKuerzel,false," hat keine oder nicht eindeutige Signatur!",true)));
				}
			}
		}
		return oMV;
	}
	
	


	
	private class ownAlarmMessage extends MyE2_Message {
		public ownAlarmMessage(MyString cmessage) {
			super(cmessage, __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE.this.bAnhang7_nur_warnung_keine_fehler?MyE2_Message.TYP_WARNING:TYP_ALARM, false);
		}
	}
	
	
	
	
	
	
//	private class own_AH7_Steuerdatei_AlarmMessageWithButton extends MyE2_BASIC_MessageWithAddonComponent {
//	
//		/**
//		 * @param cmessage
//		 */
//		public own_AH7_Steuerdatei_AlarmMessageWithButton( MyString cmessage) {
//			super(	__SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE.this.bAnhang7_nur_warnung_keine_fehler?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM, 
//					cmessage, new ownButtonCreateAH7Relation(), new Extent(400), new Extent(40));
//		}
//
//		@Override
//		public Color get_Color_4_MessageBackground(MyE2_Message oMessage) {
//			return __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE.this.bAnhang7_nur_warnung_keine_fehler?new E2_ColorHelpBackground():new E2_ColorAlarm();
//		}
//	}
//	
//	private class ownButtonCreateAH7Relation extends E2_Button {
//		public ownButtonCreateAH7Relation() {
//			super();
//			this._t(S.ms("Aktivieren"))._fsa(-2)._backGreen()._bord_black();
//			this.setInsets(new Insets(4));
//			this._aaa(new XX_ActionAgent() {
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					__SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE oThis = __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE.this;
//					if (S.isAllFull(oThis.get_ID_ADRESSE_LAGER_START_cUF_NN(""), oThis.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""))) {
//						MyE2_MessageVector mv = new MyE2_MessageVector();
//						_Service_Read_OR_CreateAndProfile_AH_7_steuerdatei generatingService = new _Service_Read_OR_CreateAndProfile_AH_7_steuerdatei(
//											oThis.get_ID_ADRESSE_LAGER_START_cUF_NN(""), oThis.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""), mv);
//						
//						if (S.isFull(generatingService.get_id_ah7_steuerdatei_found_or_created()) && mv.get_bIsOK()) {
//							
//							if (generatingService.hmIdsAndProfiles.values().size()>0) {		//wenn hier =0, dann existiert eine relation, die konnte aber nicht profiliert werden
//
//								String profilTyp = S.NN(new VEK<String>()._a(generatingService.hmIdsAndProfiles.values()).get(0)," --fehler" );
//								//ueberblick verschaffen
//								Rec20 recAH7_Steuerdatei = new Rec20(_TAB.ah7_steuerdatei)._fill_id(generatingService.get_id_ah7_steuerdatei_found_or_created());
//								
//								if (!recAH7_Steuerdatei.get_ufs_dbVal(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
//									if (generatingService.isRelationNew()) {
//										bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Es wurde ein AH7-Steuerungs-Datensatz neu erzeugt: Mögliche Bewertungsanfragen werden gestellt. Typ des Profils: ")
//														.ut("<"+profilTyp+">")
//														.t(" // Die ID des AH7-Steuerungs-Datensatzes ist: ")
//														.ut("<"+generatingService.get_id_ah7_steuerdatei_found_or_created()+">")));
//										/*
//										 * meldungen verschicken (nur einmal, bei neu angelegten
//										 */
//										ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.generateMessages(MODUL.AH7_STEUERDATEI_LISTE, generatingService.get_id_ah7_steuerdatei_found_or_created(), "Bitte um Freigabe der AH-7-Steuer-Relation","AH7-Steuertabelle");
//										
//									} else {
//										bibMSG.add_MESSAGE(new MyE2_Warning_Message(
//												S.ms("Es wurde eine nicht aktive Relation gefunden. Diese muss von einer berechtigten Person aktiviert werden ! Typ des Profils: ")
//												.ut("<"+profilTyp+">")
//												.t(" // Die ID des AH7-Steuerungs-Datensatzes ist: ")
//												.ut("<"+generatingService.get_id_ah7_steuerdatei_found_or_created()+">")));
//									}
//								} else {
//									if (generatingService.isRelationNew()) {
//										bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Es wurde eine neue Relation erzeugt und aktiviert. Sie steht zur Verfügung. Typ des Profils: ").ut("<"+profilTyp+">")));
//									} else {
//										bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Es wurde eine bestehende Relation gefunden, bewertet und aktiviert. Sie steht zur Verfügung. Typ des Profils: ").ut("<"+profilTyp+">")));
//									}
//								}
//							
//							} else {
//								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Es exisitert eine Relation, aber es konnte kein A7-Profil gefunden werden, das darauf passt. Das Profil ")
//																				.ut(" <"+generatingService.get_id_ah7_steuerdatei_found_or_created()+">")
//																				.t(" muss manuell behandelt und bewertet werden !") ));
//							}
//
//							
//						} else {
//							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler bei der Prüfung der Relation !")));
//							bibMSG.add_MESSAGE(mv);
//						}
//					}
//				}
//			});
//		}
//	}

	
	
	private class AdressHelper 
	{
		public String    ID_ADRESS = null;
		public MyString  Info = null;
		
		public AdressHelper(String iD_ADRESS, MyString cInfo)
		{
			super();
			ID_ADRESS = iD_ADRESS;
			this.Info = cInfo;
		}
	}
	
}
