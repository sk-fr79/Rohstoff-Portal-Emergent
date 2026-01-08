package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___ERMITTLE_STEUERVERMERK_UND_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___RESEARCH_AVV_CODE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_Pruefer_auf_AVV_UND_NOTI;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchAdresse;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchArtikelBez;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchKontrakte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Grid_InnereErfassungsMaske;
import rohstoff.utils.MyArtikelbezeichung_NG;

public class FUS_FuhreRepraesentantInListe
{

	private RECORD_ADRESSE			record_LADESTELLE		= null;
	private RECORD_ADRESSE			record_ABLADESTELLE		= null;

	private RECORD_ARTIKEL_BEZ		record_LADESORTE		= null;
	private RECORD_ARTIKEL_BEZ		record_ABLADESORTE		= null;

	private RECORD_VPOS_KON			record_LADEKONTRAKT		= null;
	private RECORD_VPOS_KON			record_ABLADEKONTRAKT	= null;

	private RECORD_VPOS_STD			record_LADEANGEBOT		= null;
	private RECORD_VPOS_STD			record_ABLADEANGEBOT	= null;

	private MyDate					oWiegeDatum				= null;
	
	//fuer die fahrplanvariante ein zweites datum
	private MyDate					oDatum2					= null;
	
	private MyBigDecimal			bdWiegeMenge			= null;

	// der vector, zu dem dieses element gehoert
	private FUS_Grid_ErfassteFuhren	gridThisBelongsTo		= null;

	private MyE2_Button				oButtonDeleteRow		= null;
	private MyE2_Button				oButtonEditRow			= null;

	private String					cTransportmittel		= null;
	
	private MyBigDecimal            bdPreisEK 				= null;
	private MyBigDecimal            bdPreisVK 				= null;

	//private boolean                 bHatWarnung 			= false;
	private String                 	cLKW_Nummer   			= null;
	private String                 	cLKW_AnhaengerNummer	= null;

	private String                 	cWiegekarte_EK   		= null;
	private String                 	cWiegekarte_VK			= null;
	
	//fuer fahrplanpool anzahlContainer dazu
	private MyBigDecimal          	bdAnzahlContainer 		= null;
	
	
	
	
	public FUS_FuhreRepraesentantInListe(	RECORD_ADRESSE 		record_LADESTELLE, 
											RECORD_ADRESSE 		record_ABLADESTELLE, 
											RECORD_ARTIKEL_BEZ 	record_LADESORTE, 
											RECORD_ARTIKEL_BEZ 	record_ABLADESORTE, 
											RECORD_VPOS_KON 	record_LADEKONTRAKT,
											RECORD_VPOS_KON 	record_ABLADEKONTRAKT, 
											RECORD_VPOS_STD 	record_LADEANGEBOT, 
											RECORD_VPOS_STD 	record_ABLADEABGEBOT, 
											MyDate 				o_WiegeDatum, 
											MyDate 				o_Datum2,                  //fuer fahrplanpool-version
											MyBigDecimal 		bd_WiegeMenge, 
											String 				c_TransportMittel, 
											MyBigDecimal    	BD_PreisEK,
											MyBigDecimal    	BD_PreisVK,
											String              LKW_Nummer,
											String             	LKW_AnhaengerNummer,
											String	    		Wiegekarte_EK,
											String             	Wiegekarte_VK,
											MyBigDecimal        bd_AnzahlContainer,
											FUS_Grid_ErfassteFuhren grid_ThisBelongsTo) throws myException
	{
		super();
		this.record_LADESTELLE = record_LADESTELLE;
		this.record_ABLADESTELLE = record_ABLADESTELLE;
		this.record_LADESORTE = record_LADESORTE;
		this.record_ABLADESORTE = record_ABLADESORTE;
		this.record_LADEKONTRAKT = record_LADEKONTRAKT;
		this.record_ABLADEKONTRAKT = record_ABLADEKONTRAKT;
		this.record_LADEANGEBOT = record_LADEANGEBOT;
		this.record_ABLADEANGEBOT = record_ABLADEABGEBOT;
		this.oWiegeDatum = o_WiegeDatum;
		this.oDatum2 = o_Datum2;
		this.bdWiegeMenge = bd_WiegeMenge;
		this.cTransportmittel = c_TransportMittel;
		this.bdPreisEK = BD_PreisEK;
		this.bdPreisVK = BD_PreisVK;
		
		this.bdAnzahlContainer = bd_AnzahlContainer;
		
		this.gridThisBelongsTo = grid_ThisBelongsTo;

		this.cLKW_Nummer = 			LKW_Nummer;
		this.cLKW_AnhaengerNummer = LKW_AnhaengerNummer;
		this.cWiegekarte_EK =		Wiegekarte_EK;
		this.cWiegekarte_VK =		Wiegekarte_VK;
		
		this.oButtonDeleteRow = new ownButtonDeleteME();
		this.oButtonEditRow = new ownButtonLoad_ME_ToMaske();
		
	}

	public RECORD_ADRESSE get_LIEFERANT() throws myException
	{
		if (this.record_LADESTELLE != null)
		{
			if (this.record_LADESTELLE.get_ADRESSTYP_lValue(-1l) == myCONST.ADRESSTYP_FIRMENINFO)
			{
				return this.record_LADESTELLE;
			}
			else
			{
				return this.record_LADESTELLE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			}
		}
		else
		{
			return null;
		}
	}

	public RECORD_ADRESSE get_ABNEHMER() throws myException
	{
		if (this.record_ABLADESTELLE != null)
		{
			if (this.record_ABLADESTELLE.get_ADRESSTYP_lValue(-1l) == myCONST.ADRESSTYP_FIRMENINFO)
			{
				return this.record_ABLADESTELLE;
			}
			else
			{
				return this.record_ABLADESTELLE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			}
		}
		else
		{
			return null;
		}
	}

	public MyE2_Button get_oButtonDeleteRow()
	{
		return oButtonDeleteRow;
	}

	public MyE2_Button get_oButtonEditRow()
	{
		return oButtonEditRow;
	}

	public RECORD_ADRESSE get_record_LADESTELLE()
	{
		return record_LADESTELLE;
	}

	public RECORD_ADRESSE get_record_ABLADESTELLE()
	{
		return record_ABLADESTELLE;
	}

	public RECORD_ARTIKEL_BEZ get_record_LADESORTE()
	{
		return record_LADESORTE;
	}

	public RECORD_ARTIKEL_BEZ get_record_ABLADESORTE()
	{
		return record_ABLADESORTE;
	}

	public RECORD_VPOS_KON get_record_LADEKONTRAKT()
	{
		return record_LADEKONTRAKT;
	}

	public RECORD_VPOS_KON get_record_ABLADEKONTRAKT()
	{
		return record_ABLADEKONTRAKT;
	}

	public RECORD_VPOS_STD get_record_LADEANGEBOT()
	{
		return record_LADEANGEBOT;
	}

	public RECORD_VPOS_STD get_record_ABLADEABGEBOT()
	{
		return record_ABLADEANGEBOT;
	}

	public MyDate get_oWiegeDatum()
	{
		return oWiegeDatum;
	}

	public MyDate get_oDatum2()
	{
		return oDatum2;
	}
	
	
	public MyBigDecimal get_bdWiegeMenge()
	{
		return bdWiegeMenge;
	}

		
	public MyBigDecimal get_bdAnzahlContainer()
	{
		return this.bdAnzahlContainer;
	}

	
	
	// -----------------------------

	public Component get_Comp_LADESTELLE() throws myException
	{
		String cString = "<ladestelle>";

		if (this.record_LADESTELLE != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchAdresse.cSQL4Label, "#WERT#", this.record_LADESTELLE.get_ID_ADRESSE_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_ABLADESTELLE() throws myException
	{
		String cString = "<abladestelle>";

		if (this.record_ABLADESTELLE != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchAdresse.cSQL4Label, "#WERT#", this.record_ABLADESTELLE.get_ID_ADRESSE_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_LADESORTE() throws myException
	{
		String cString = "<ladesorte>";

		if (this.record_LADESORTE != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchArtikelBez.cSQL4Label, "#WERT#", this.record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_ABLADESORTE() throws myException
	{
		String cString = "<abladesorte>";

		if (this.record_ABLADESORTE != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchArtikelBez.cSQL4Label, "#WERT#", this.record_ABLADESORTE.get_ID_ARTIKEL_BEZ_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_LADEKONTRAKT() throws myException
	{
		String cString = "<-->";

		if (this.record_LADEKONTRAKT != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchKontrakte.cSQL4Label, "#WERT#", this.record_LADEKONTRAKT.get_ID_VPOS_KON_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_ABLADEKONTRAKT() throws myException
	{
		String cString = "<-->";

		if (this.record_ABLADEKONTRAKT != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchKontrakte.cSQL4Label, "#WERT#", this.record_ABLADEKONTRAKT.get_ID_VPOS_KON_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_LADEANGEBOT() throws myException
	{
		String cString = "<-->";

		if (this.record_LADEANGEBOT != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchKontrakte.cSQL4Label, "#WERT#", this.record_LADEANGEBOT.get_ID_VPOS_STD_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_ABLADEANGEBOT() throws myException
	{
		String cString = "<-->";

		if (this.record_ABLADEANGEBOT != null)
		{
			cString = bibDB.EinzelAbfrage(bibALL.ReplaceTeilString(FUS_SearchKontrakte.cSQL4Label, "#WERT#", this.record_ABLADEANGEBOT.get_ID_VPOS_STD_cUF()));
		}
		return new ownLabel(cString);
	}

	public Component get_Comp_Datum_und_oder_Datum2()
	{
		String cString = "";

		if (this.oWiegeDatum.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			cString = this.oWiegeDatum.get_cDateStandardFormat();
		}

		//variante aus der fahrplanseite
		if (this.oDatum2!= null)
		{
			cString = cString.substring(0, 6)+"-"+this.oDatum2.get_cDateStandardFormat().substring(0, 6);
		}
		
		return new ownLabel(cString);
	}

	public Component get_Comp_WiegeMenge()
	{
		String cString = "";

		if (this.bdWiegeMenge.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			cString = this.bdWiegeMenge.get_FormatedRoundedNumber(3);
		}

		return new ownLabel(cString);
	}

	
	public Component get_Comp_AnzahlContainer()
	{
		String cString = "";

		if (this.bdAnzahlContainer.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			cString = this.bdAnzahlContainer.get_FormatedRoundedNumber(0);
		}

		ownLabel  labRueck = new ownLabel(cString);
		labRueck.setFont(new E2_FontBold(2));
		labRueck.setLayoutData(MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_2_2_2_2));
		return labRueck;
	}

	
	
	private class ownLabel extends MyE2_Label
	{

		public ownLabel(String cText)
		{
			super(cText);
			this.setLineWrap(true);
			this.setFont(new E2_FontPlain(-2));

			this.setLayoutData(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_1_1));

		}

	}

	private class ownButtonDeleteME extends MyE2_Button
	{

		public ownButtonDeleteME()
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png"), true, new MyE2_String("Zeile aus der Vorerfassung entfernen ..."), new ownActionAgentDelete());
		}

	}

	private class ownActionAgentDelete extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUS_FuhreRepraesentantInListe.this.gridThisBelongsTo.get_vFuhren().remove(FUS_FuhreRepraesentantInListe.this);
			FUS_FuhreRepraesentantInListe.this.gridThisBelongsTo.baue_liste_auf();
		}
	}

	private class ownButtonLoad_ME_ToMaske extends MyE2_Button
	{

		public ownButtonLoad_ME_ToMaske()
		{
			super(E2_ResourceIcon.get_RI("moveup.png"), true, new MyE2_String("Zeile in die Maske holen ..."), new ownActionAgentLoad_ME_ToMaske());
		}

	}

	private class ownActionAgentLoad_ME_ToMaske extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUS_FuhreRepraesentantInListe oThis = FUS_FuhreRepraesentantInListe.this;
			FUS_Grid_InnereErfassungsMaske oInputBlock = new _SEARCH_FUS_Grid_InnereErfassungsMaske().get_Found_FUS_Grid_InnereErfassungsMaske();

			oInputBlock.fill_Comp_LADESTELLE(oThis.record_LADESTELLE);
			oInputBlock.fill_Comp_ABLADESTELLE(oThis.record_ABLADESTELLE);
			oInputBlock.fill_Comp_LADESORTE(oThis.record_LADESORTE);
			oInputBlock.fill_Comp_ABLADESORTE(oThis.record_ABLADESORTE);
			oInputBlock.fill_Comp_LADEKONTRAKT(oThis.record_LADEKONTRAKT);
			oInputBlock.fill_Comp_ABLADEKONTRAKT(oThis.record_ABLADEKONTRAKT);
			oInputBlock.fill_Comp_LADEANGEBOT(oThis.record_LADEANGEBOT);
			oInputBlock.fill_Comp_ABLADEANGEBOT(oThis.record_ABLADEANGEBOT);
			oInputBlock.fill_Comp_WiegeDatum(oThis.oWiegeDatum);
			oInputBlock.fill_Comp_Datum2(oThis.oDatum2);
			oInputBlock.fill_Comp_AnzahlContainer(oThis.bdAnzahlContainer);
			oInputBlock.fill_Comp_WiegeMenge(oThis.bdWiegeMenge);
			oInputBlock.fill_Comp_TransportMittel(oThis.cTransportmittel);

			oInputBlock.fill_Comp_EK_PREIS(oThis.bdPreisEK);
			oInputBlock.fill_Comp_VK_PREIS(oThis.bdPreisVK);
			
			oInputBlock.fill_Comp_LKW_Kennzeichen(S.NN(oThis.get_cLKW_Nummer()));
			oInputBlock.fill_Comp_LKW_AnhaengerKennzeichen(S.NN(oThis.get_cLKW_AnhaengerNummer()));
			
			oInputBlock.fill_Comp_Wiegekarte_EK(S.NN(oThis.get_cWiegekarte_EK()));
			oInputBlock.fill_Comp_Wiegekarte_VK(S.NN(oThis.get_cWiegekarte_VK()));
		
			FUS_FuhreRepraesentantInListe.this.gridThisBelongsTo.baue_liste_auf();

			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag der Zeile wurde in die Maske übertragen ...")));
		}
	}

	public String get_cTransportmittel()
	{
		return cTransportmittel;
	}

	/**
	 * methode, die aus den angaben der Zeile eine neue Fuhre erzeugt
	 * 
	 * 
	 * @return s ID_VPOS_TPA_FUHRE
	 * @throws myException
	 */
	public RECORDNEW_VPOS_TPA_FUHRE  get_RECORDNEW_VPOS_TPA_FUHRE() throws myException
	{
		RECORDNEW_VPOS_TPA_FUHRE  recNewFuhre = new RECORDNEW_VPOS_TPA_FUHRE();

		boolean bFahrplan = new _Check_ob_fahrplan().get_bIsFahrPlan();
		
		if (this.record_LADESTELLE==null)
		{
			throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without LADESTELLE");
		}
		if (this.record_ABLADESTELLE==null)
		{
			throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without ABLADESTELLE");
		}
		if (this.record_LADESORTE==null)
		{
			throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without LADESORTE");
		}
		if (this.record_ABLADESORTE==null)
		{
			throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without ABLADESORTE");
		}
		
		if (!bFahrplan)
		{
			if (this.bdWiegeMenge==null || (!this.bdWiegeMenge.get_cErrorCODE().equals(MyBigDecimal.ALL_OK)))
			{
				throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without MENGE");
			}
		}
		
		if (this.oWiegeDatum==null || (!this.oWiegeDatum.get_cErrorCODE().equals(MyDate.ALL_OK)))
		{
			throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without DATE");
		}
		
		if (bFahrplan)
		{
			if (this.oDatum2==null || (!this.oDatum2.get_cErrorCODE().equals(MyDate.ALL_OK)))
			{
				throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE to database without DATE(2)");
			}
			
			if (this.bdAnzahlContainer ==null || (!this.bdAnzahlContainer.get_cErrorCODE().equals(MyDate.ALL_OK)))
			{
				throw new myException(this,"cannot store JT_VPOS_TPA_FUHRE without ANZAHLContainer");
			}
			
		}
		
		

//		System.out.println("ID-pos-kon: "+this.record_LADEKONTRAKT.get_ID_VPOS_KON_cUF());
		
		boolean bLadeortIstMandant = this.get_LIEFERANT().get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-"));
		
		//nach den ersten pruefungen gehts los
		recNewFuhre.set_NEW_VALUE_ID_ADRESSE_START(this.get_LIEFERANT().get_ID_ADRESSE_cUF());
		recNewFuhre.set_NEW_VALUE_ID_ADRESSE_LAGER_START(this.record_LADESTELLE.get_ID_ADRESSE_cUF());

		recNewFuhre.set_NEW_VALUE_ID_ADRESSE_ZIEL(this.get_ABNEHMER().get_ID_ADRESSE_cUF());
		recNewFuhre.set_NEW_VALUE_ID_ADRESSE_LAGER_ZIEL(this.record_ABLADESTELLE.get_ID_ADRESSE_cUF());
		
		//sorten EK
		recNewFuhre.set_NEW_VALUE_ID_ARTIKEL_BEZ_EK(this.record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF());
		recNewFuhre.set_NEW_VALUE_ANR1_EK(this.record_LADESORTE.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_ANR2_EK(this.record_LADESORTE.get_ANR2_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_ARTBEZ1_EK(this.record_LADESORTE.get_ARTBEZ1_cUF_NN(""));
		
		//2012-01-05: hier zuerst pruefen, ob die sort gelistet ist
		boolean bGelistet = true;
		
		if (!bLadeortIstMandant)
		{
			bGelistet = false;
			RECLIST_ARTIKELBEZ_LIEF  recListArtbez = this.get_LIEFERANT().get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
			for (int l=0;l<recListArtbez.get_vKeyValues().size();l++)
			{
				if (recListArtbez.get(l).get_ID_ARTIKEL_BEZ_cUF_NN("--").equals(this.record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF_NN("-")))
				{
					bGelistet = true;
				}
			}
			
			if (!bGelistet)
			{
				throw new myExceptionForUser(new MyE2_String("Fehler: Die Sorte ",true,
											this.record_LADESORTE.get_ARTBEZ1_cUF_NN("<artbez1>"),false,
											" ist nicht gelistet bei Firma: ",true, this.get_LIEFERANT().get_NAME1_cUF_NN("<name1>"),false));
			}
		}
		
		
		//artbez 2 muss evtl. aus der kundenspezifischen tabelle geholt werden
		MyArtikelbezeichung_NG oArtBezEK = new MyArtikelbezeichung_NG(	this.record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF(),
				this.get_LIEFERANT().get_ID_ADRESSE_cUF(),"EK",!bLadeortIstMandant);
		
		recNewFuhre.set_NEW_VALUE_ARTBEZ2_EK(oArtBezEK.get_ARTBEZ2());
		

		//sorten VK
		recNewFuhre.set_NEW_VALUE_ID_ARTIKEL_BEZ_VK(this.record_ABLADESORTE.get_ID_ARTIKEL_BEZ_cUF());
		recNewFuhre.set_NEW_VALUE_ANR1_VK(this.record_ABLADESORTE.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_ANR2_VK(this.record_ABLADESORTE.get_ANR2_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_ARTBEZ1_VK(this.record_ABLADESORTE.get_ARTBEZ1_cUF_NN(""));
		//artbez 2 muss evtl. aus der kundenspezifischen tabelle geholt werden
		MyArtikelbezeichung_NG oArtBezVK = new MyArtikelbezeichung_NG(	this.record_ABLADESORTE.get_ID_ARTIKEL_BEZ_cUF(),
				this.get_LIEFERANT().get_ID_ADRESSE_cUF(),"VK",false);
		recNewFuhre.set_NEW_VALUE_ARTBEZ2_VK(oArtBezVK.get_ARTBEZ2());
		
		recNewFuhre.set_NEW_VALUE_ID_ARTIKEL(this.record_LADESORTE.get_ID_ARTIKEL_cUF());

		
		//ladesorte=abladesorte
		
		int iAnzahlVergleich = bibALL.get_RECORD_MANDANT().get_ANR1_GLEICHHEIT_FUHRE_STELLEN_lValue(2l).intValue();
		String cANR1_EK = (this.record_LADESORTE.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("------------")+"------------------").substring(0,iAnzahlVergleich);
		String cANR1_VK = (this.record_ABLADESORTE.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("------------")+"------------------").substring(0,iAnzahlVergleich);
		
		recNewFuhre.set_NEW_VALUE_EK_VK_SORTE_LOCK(cANR1_EK.equals(cANR1_VK)?"Y":"N");

		
		
		if (bFahrplan)
		{
			recNewFuhre.set_NEW_VALUE_DATUM_ABHOLUNG(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_ANLIEFERUNG(this.oDatum2.get_Calendar());
			
			recNewFuhre.set_NEW_VALUE_DAT_VORGEMERKT_FP(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DAT_VORGEMERKT_ENDE_FP(this.oDatum2.get_Calendar());
			
			if (this.bdWiegeMenge!=null && this.bdWiegeMenge.get_bdWert()!=null)
			{
				recNewFuhre.set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(this.bdWiegeMenge.get_bdWert());
				recNewFuhre.set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(this.bdWiegeMenge.get_bdWert());
			}
			
			recNewFuhre.set_NEW_VALUE_ANZAHL_CONTAINER_FP(this.bdAnzahlContainer.get_bdWert());
			
			recNewFuhre.set_NEW_VALUE_FUHRE_AUS_FAHRPLAN("Y");
		}
		else
		{
			recNewFuhre.set_NEW_VALUE_DATUM_ABHOLUNG(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_ABHOLUNG_ENDE(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_ANLIEFERUNG(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_ANLIEFERUNG_ENDE(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_AUFLADEN(this.oWiegeDatum.get_Calendar());
			recNewFuhre.set_NEW_VALUE_DATUM_ABLADEN(this.oWiegeDatum.get_Calendar());
			
			recNewFuhre.set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(this.bdWiegeMenge.get_bdWert());
			recNewFuhre.set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(this.bdWiegeMenge.get_bdWert());
			recNewFuhre.set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(this.bdWiegeMenge.get_bdWert());
			recNewFuhre.set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(this.bdWiegeMenge.get_bdWert());
			
			recNewFuhre.set_NEW_VALUE_ANZAHL_CONTAINER_FP("1");           //formal noetig
			recNewFuhre.set_NEW_VALUE_FUHRE_AUS_FAHRPLAN("N");
		}
		
		//wenn umgekehrte abrechnung, dann noch die zusatzmengen fuellen
		if (this.get_LIEFERANT().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_ABLADEMENGE_FUER_GUTSCHRIFT_YES())
		{
			if (!bFahrplan) {recNewFuhre.set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(this.bdWiegeMenge.get_bdWert());}
			recNewFuhre.set_NEW_VALUE_LADEMENGE_GUTSCHRIFT("N");
		}
		else
		{
			recNewFuhre.set_NEW_VALUE_LADEMENGE_GUTSCHRIFT("Y");     //normalfall
		}

		if (this.get_ABNEHMER().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_LADEMENGE_FUER_RECHNUNG_YES())
		{
			if (!bFahrplan) {recNewFuhre.set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(this.bdWiegeMenge.get_bdWert());}
			recNewFuhre.set_NEW_VALUE_ABLADEMENGE_RECHNUNG("N");
		}
		else
		{
			recNewFuhre.set_NEW_VALUE_ABLADEMENGE_RECHNUNG("Y");     //normalfall
		}
		
		//lieferadressen setzen
		String[] arrayFelder = {"NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","ORTZUSATZ"};
		for (int i=0;i<arrayFelder.length;i++)
		{
			recNewFuhre.set_NewValueForDatabase("L_"+arrayFelder[i],this.record_LADESTELLE.get(arrayFelder[i]).get_FieldValueUnformated());
			recNewFuhre.set_NewValueForDatabase("A_"+arrayFelder[i],this.record_ABLADESTELLE.get(arrayFelder[i]).get_FieldValueUnformated());
		}
		
		recNewFuhre.set_NEW_VALUE_L_LAENDERCODE((this.record_LADESTELLE.get_UP_RECORD_LAND_id_land()!=null?this.record_LADESTELLE.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):""));
		recNewFuhre.set_NEW_VALUE_A_LAENDERCODE((this.record_ABLADESTELLE.get_UP_RECORD_LAND_id_land()!=null?this.record_ABLADESTELLE.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):""));
		
		
		
		RECORD_EAK_CODE recEAK_Code = 		new FU___RESEARCH_AVV_CODE(this.record_LADESTELLE, this.record_LADESORTE).get_recEAK_Code();
		RECORD_ARTIKEL  recLadeHauptSorte = this.record_LADESORTE.get_UP_RECORD_ARTIKEL_id_artikel();

		
		//avv-code klaeren
		String cID_EAK_Code = "";
		if (recEAK_Code!=null)
		{
			cID_EAK_Code=recEAK_Code.get_ID_EAK_CODE_cUF_NN("");
		}
		
		if (S.isEmpty(cID_EAK_Code) && bLadeortIstMandant)
		{
			cID_EAK_Code = recLadeHauptSorte.get_ID_EAK_CODE_EX_MANDANT_cUF_NN("");
		}

		
		if (S.isFull(cID_EAK_Code))
		{
			recNewFuhre.set_NEW_VALUE_ID_EAK_CODE(cID_EAK_Code);
		}
		
		
		
		
		recNewFuhre.set_NEW_VALUE_EUCODE(recLadeHauptSorte.get_EUCODE_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_EUNOTIZ(recLadeHauptSorte.get_EUNOTIZ_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_BASEL_CODE(recLadeHauptSorte.get_BASEL_CODE_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_BASEL_NOTIZ(recLadeHauptSorte.get_BASEL_NOTIZ_cUF_NN(""));
		recNewFuhre.set_NEW_VALUE_ZOLLTARIFNR(recLadeHauptSorte.get_ZOLLTARIFNR_cUF_NN(""));
		
		recNewFuhre.set_NEW_VALUE_FUHRE_KOMPLETT("N");                //zwingt den anwender einmal zu speichern

		
		recNewFuhre.set_NEW_VALUE_TRANSPORTMITTEL(this.cTransportmittel);
		
		
		//kontrakt oder angebote
		if (this.record_LADEKONTRAKT!=null)
		{
			recNewFuhre.set_NEW_VALUE_ID_VPOS_KON_EK(this.record_LADEKONTRAKT.get_ID_VPOS_KON_cUF_NN(""));
		}
		
		if (this.record_ABLADEKONTRAKT!=null)
		{
			recNewFuhre.set_NEW_VALUE_ID_VPOS_KON_VK(this.record_ABLADEKONTRAKT.get_ID_VPOS_KON_cUF_NN(""));
		}
		
		
		//angebote
		if (this.record_LADEANGEBOT!=null)
		{
			recNewFuhre.set_NEW_VALUE_ID_VPOS_STD_EK(this.record_LADEANGEBOT.get_ID_VPOS_STD_cUF_NN(""));
		}

		if (this.record_ABLADEANGEBOT!=null)
		{
			recNewFuhre.set_NEW_VALUE_ID_VPOS_STD_VK(this.record_ABLADEANGEBOT.get_ID_VPOS_STD_cUF_NN(""));
		}
		
		
		
		
		//jetzt die checkbox drucke eu-blatt pruefen
		boolean bEU_Blatt_Ein = new __FU_Pruefer_auf_AVV_UND_NOTI().get_bMustHaveAVV_Blatt(	this.record_LADESORTE.get_ID_ARTIKEL_cUF(), 
																							cID_EAK_Code, 
																							recLadeHauptSorte.get_BASEL_CODE_cUF_NN(""), recLadeHauptSorte.get_EUCODE_cUF_NN(""), 
																							this.record_LADESTELLE.get_ID_ADRESSE_lValue(null),
																							this.record_ABLADESTELLE.get_ID_ADRESSE_lValue(null),
																							false);
		
		recNewFuhre.set_NEW_VALUE_PRINT_EU_AMTSBLATT(bEU_Blatt_Ein?"Y":"N");
		

		
		//jetzt den steuer-vermerk-und steuersatz-zauberstab simulieren
		FU___ERMITTLE_STEUERVERMERK_UND_MWST oSteuerFahnder = new FU___ERMITTLE_STEUERVERMERK_UND_MWST(
				record_LADESTELLE, record_ABLADESTELLE, recLadeHauptSorte, record_ABLADESORTE.get_UP_RECORD_ARTIKEL_id_artikel(),
				this.oWiegeDatum.get_cDateStandardFormat(), 
				this.oWiegeDatum.get_cDateStandardFormat(),
				this.bdPreisEK.get_bdWert(),
				this.bdPreisVK.get_bdWert());
		
		
		if (oSteuerFahnder.get_bSteuerSatzErmittelt_EK())
		{
			recNewFuhre.set_NEW_VALUE_STEUERSATZ_EK(oSteuerFahnder.get_bdRUECK_MWST_EK());
		}
		if (oSteuerFahnder.get_bSteuerSatzErmittelt_VK())
		{
			recNewFuhre.set_NEW_VALUE_STEUERSATZ_VK(oSteuerFahnder.get_bdRUECK_MWST_VK());
		}
		
		if (oSteuerFahnder.get_bSteuerVermerkErmittelt_EK())
		{
			recNewFuhre.set_NEW_VALUE_EU_STEUER_VERMERK_EK(oSteuerFahnder.get_cRUECK_STEUERVERMERK_EK());
		}
		
		if (oSteuerFahnder.get_bSteuerVermerkErmittelt_VK())
		{
			recNewFuhre.set_NEW_VALUE_EU_STEUER_VERMERK_VK(oSteuerFahnder.get_cRUECK_STEUERVERMERK_VK());
		}
		
		
		
		//jetzt pruefen, ob ein preis manuell ist
		if (this.bdPreisEK != null && this.bdPreisEK.get_bdWert()!=null)
		{
			if (this.record_LADEANGEBOT==null && this.record_LADEKONTRAKT==null)
			{
				recNewFuhre.set_NEW_VALUE_MANUELL_PREIS_EK("Y");
			}
			else
			{
				recNewFuhre.set_NEW_VALUE_MANUELL_PREIS_EK("N");
			}
			recNewFuhre.set_NEW_VALUE_EINZELPREIS_EK(this.bdPreisEK.get_bdWert());
		}
		
		
		if (this.bdPreisVK != null && this.bdPreisVK.get_bdWert()!=null)
		{
			if (this.record_ABLADEANGEBOT==null && this.record_ABLADEKONTRAKT==null)
			{
				recNewFuhre.set_NEW_VALUE_MANUELL_PREIS_VK("Y");
			}
			else
			{
				recNewFuhre.set_NEW_VALUE_MANUELL_PREIS_VK("N");
			}
			recNewFuhre.set_NEW_VALUE_EINZELPREIS_VK(this.bdPreisVK.get_bdWert());
		}

		
		//jetzt die felder kennzeichen und wiegeschein-nummer noch dazu
		recNewFuhre.set_NEW_VALUE_TRANSPORTKENNZEICHEN(this.get_cLKW_Nummer());
		recNewFuhre.set_NEW_VALUE_ANHAENGERKENNZEICHEN(this.get_cLKW_AnhaengerNummer());
		recNewFuhre.set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(this.get_cWiegekarte_EK());
		recNewFuhre.set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(this.get_cWiegekarte_VK());
		
		
		
		
		// erzeugt den datensatz und gibt einen neuen record zurueck
		return recNewFuhre;
	}

	public String get_cLKW_Nummer()
	{
		return cLKW_Nummer;
	}

	public String get_cLKW_AnhaengerNummer()
	{
		return cLKW_AnhaengerNummer;
	}

	public String get_cWiegekarte_EK()
	{
		return cWiegekarte_EK;
	}

	public String get_cWiegekarte_VK()
	{
		return cWiegekarte_VK;
	}

}
