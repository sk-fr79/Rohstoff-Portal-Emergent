package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;


/*
 * popup-container, der anzeigt, welche postionen mit einem kontrakt in verbindung stehen, und diese
 * (falls noch offen) zum aendern des preises anbietet
 */
public class BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise extends E2_BasicModuleContainer_PopUp_BeforeExecute 
{

	private BSK_P_MASK__ModulContainer   oKontrakt_Pos_Mask_container = null;
	private RECLIST_VPOS_RG 			oMapVpos_RG = null; 
	private MyE2_Grid  					oGridWithContent = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

	private Vector<MyE2_CheckBox>		vCheckBoxes = new Vector<MyE2_CheckBox>(); 
	
	private String   					cID_VPOS_KON_ID = null;
	
	public BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise(BSK_P_MASK__ModulContainer kontrakt_Pos_Mask_container) 
	{
		super();
		oKontrakt_Pos_Mask_container = kontrakt_Pos_Mask_container;
		this.set_oExtWidth(new Extent(800));
		this.set_oExtHeight(new Extent(500));
		
		this.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie die Positionssätze aus, deren Preis automatisch geändert werden soll !!")), E2_INSETS.I_5_5_5_5);
		
		this.add_In_ContainerEX(this.oGridWithContent, new Extent(98,Extent.PERCENT), new Extent(320), E2_INSETS.I_5_5_5_5);
		
		E2_ComponentGroupHorizontal oButtonLeiste = new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_10_2_10_2);
		this.set_Component_To_ButtonPane(oButtonLeiste);
		this.set_bShowWindowAsSplitpane(true);
		
	}



	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException
	{
		
		
		// Beschaffen der JT_VPOS_KON - ID
		this.cID_VPOS_KON_ID = this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		this.oMapVpos_RG =  new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_KON_ZUGEORD="+cID_VPOS_KON_ID+" AND NVL(DELETED,'N')='N'");
		
		// um die eingaben der maske vorab zu pruefen, eine SQLInputMap erstellen
		SQLMaskInputMAP oInput =  this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_ActualInputMAP_And_MarkFalseInput();

		//wenn die map-validation fehlerhaft ist, dann muss auch nichts angezeigt werden
		MyE2_MessageVector oMV_MapValidation = this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().make_MapValidation(oInput, E2_ComponentMAP.STATUS_EDIT);
	
		
		//hier nachsehen, ob die relevanten felder veraendert wurden
		Vector<String> vChanged = this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_vAllChangedFields();
		
		String[] cRelevanteFelder = {	"ANZAHL","EINZELPREIS","MENGENDIVISOR","WAEHRUNGSKURS","ID_WAEHRUNG_FREMD","EINHEITKURZ",
										"EINHEIT_PREIS_KURZ","ID_ZAHLUNGSBEDINGUNGEN","ZAHLUNGSBEDINGUNGEN","ZAHLTAGE","FIXMONAT","FIXTAG","SKONTO_PROZENT"};
		
		boolean bWasGeaendert = false;
		for (int i=0;i<cRelevanteFelder.length;i++)
		{
			bWasGeaendert = bWasGeaendert || vChanged.contains(cRelevanteFelder[i]); 
		}
		
		// nur wenn zugeordnete positionen vorhanden sind und gleichzeitig die maske keine Fehler enthaelt, muss der popup-mechanismus starten
		return (this.oMapVpos_RG.get_vKeyValues().size()>0 && oInput.get_vFieldsWithFalseInput().size()==0 && oMV_MapValidation.get_bIsOK() && bWasGeaendert);
	}

	

	protected void doOwnCode_in_ok_button() throws myException 
	{
		//hier werden die aenderungsstatements fuer die vorgangspositionen gespeichert
		Vector<String>  vSQL_Zusatz = new Vector<String>();
		
		// zuerst die formatierten felder beschaffen
		String cAnzahlAusMaske = 			this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("ANZAHL");
		
		String cEPreisAusMaske = 			this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("EINZELPREIS");
		String cMengenDivisorAusMaske = 	this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("MENGENDIVISOR");
		String cWaehrungsKursAusMaske = 	this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("WAEHRUNGSKURS");
		
		Long   ID_WAEHRUNG_FREMD_AusMaske = this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_LActualDBValue("ID_WAEHRUNG_FREMD",false, false, new Long(0));

		String cEINHEIT_AusMaske = 			this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("EINHEITKURZ");
		String cEINHEIT_Preis_AusMaske = 	this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("EINHEIT_PREIS_KURZ");
		
		Long 	LID_ZAHLUNGSBEDINGUNGEN =   this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN", true, true, null);
		String 	cZAHLUNGSBEDINGUNGEN = 		this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("ZAHLUNGSBEDINGUNGEN");
		Long 	LZAHLTAGE = 				this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_LActualDBValue("ZAHLTAGE", true, true, null);
		Long 	LFIXMONAT = 				this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_LActualDBValue("FIXMONAT", true, true, null);
		Long 	LFIXTAG = 					this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_LActualDBValue("FIXTAG", true, true, null);
	
		String cSkontoWertFuerDB  = 		this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_cActualDBValueFormated("SKONTO_PROZENT", "");
		
		if (!S.isEmpty(cSkontoWertFuerDB))
		{
			DotFormatter oDF_skonto = 			new DotFormatter(cSkontoWertFuerDB,3,Locale.GERMAN,true,3);
			if (!oDF_skonto.doFormat())
			{
				throw new myException(this,"Error Reading Values from Mask !!"); 
			}
			cSkontoWertFuerDB = oDF_skonto.getStringUnFormated();
		}
		else
		{
			cSkontoWertFuerDB = "NULL";
		}
		
		BS_PreisCalculator oPC_Kontrakt = new BS_PreisCalculator(cAnzahlAusMaske,cEPreisAusMaske,cMengenDivisorAusMaske,cWaehrungsKursAusMaske,true);
		
		if (oPC_Kontrakt.is_bCalcOK() && ID_WAEHRUNG_FREMD_AusMaske!=null)
		{
			// alle angekreutzten checkboxen durchgehen
			for (MyE2_CheckBox oCB: this.vCheckBoxes)
			{
				if (oCB.isSelected())
				{
					String cID_VPOS_RG = oCB.EXT().get_C_MERKMAL();
					
					//dann muss mit der menge aus der rg_position neu gerechnet werden
					RECORD_VPOS_RG oRG_Map = new RECORD_VPOS_RG(new Long(cID_VPOS_RG).longValue());
					String cAnzahl_RG_Pos = oRG_Map.get_ANZAHL_cF_NN("0");
					
					//fuer die position mit der eigenen menge nochmal alles kalkulieren
					BS_PreisCalculator oPC_RG = new BS_PreisCalculator(cAnzahl_RG_Pos,cEPreisAusMaske,cMengenDivisorAusMaske,cWaehrungsKursAusMaske,true);
					
					if (oPC_RG.is_bCalcOK())
					{
						MySqlStatementBuilder oSQL = new MySqlStatementBuilder();
						oSQL.addSQL_Paar("EINHEITKURZ", 		cEINHEIT_AusMaske, true);
						oSQL.addSQL_Paar("EINHEIT_PREIS_KURZ", 	bibALL.isEmpty(cEINHEIT_Preis_AusMaske)?cEINHEIT_AusMaske:cEINHEIT_Preis_AusMaske, true);
						oSQL.addSQL_Paar("ANZAHL", 				MyNumberFormater.formatDez(oPC_RG.get_dAnzahl().doubleValue(), 3, false,'.',','),false);
						oSQL.addSQL_Paar("EINZELPREIS",  		MyNumberFormater.formatDez(oPC_RG.get_dEinzelPreis().doubleValue(), 2, false,'.',','),false);
						oSQL.addSQL_Paar("EINZELPREIS_FW",  	MyNumberFormater.formatDez(oPC_RG.get_dEinzelPreis_FW().doubleValue(), 2, false,'.',','),false);
						oSQL.addSQL_Paar("WAEHRUNGSKURS",  		MyNumberFormater.formatDez(oPC_RG.get_dWaehrungskurs().doubleValue(), 4, false,'.',','),false);
						oSQL.addSQL_Paar("GESAMTPREIS",  		MyNumberFormater.formatDez(oPC_RG.get_dGesamtPreis().doubleValue(), 2, false,'.',','),false);
						oSQL.addSQL_Paar("GESAMTPREIS_FW",  	MyNumberFormater.formatDez(oPC_RG.get_dGesamtPreis_FW().doubleValue(), 2, false,'.',','),false);
						oSQL.addSQL_Paar("MENGENDIVISOR",  		""+oPC_Kontrakt.get_lMengenDivisor().longValue(),false);
						oSQL.addSQL_Paar("ID_WAEHRUNG_FREMD",  	""+ID_WAEHRUNG_FREMD_AusMaske.longValue(),false);
						oSQL.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",  LID_ZAHLUNGSBEDINGUNGEN==null?"NULL":""+LID_ZAHLUNGSBEDINGUNGEN.longValue(),false);
						oSQL.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",  S.NN(cZAHLUNGSBEDINGUNGEN),true);
						oSQL.addSQL_Paar("ZAHLTAGE", 			 LZAHLTAGE==null?"NULL":""+LZAHLTAGE.longValue(),false);
						oSQL.addSQL_Paar("FIXMONAT", 			 LFIXMONAT==null?"NULL":""+LFIXMONAT.longValue(),false);
						oSQL.addSQL_Paar("FIXTAG", 				 LFIXTAG==null?"NULL":""+LFIXTAG.longValue(),false);
						oSQL.addSQL_Paar("SKONTO_PROZENT", 		 cSkontoWertFuerDB,false);
						
						vSQL_Zusatz.add(oSQL.get_CompleteUPDATEString("JT_VPOS_RG", bibE2.cTO(), "ID_VPOS_RG="+cID_VPOS_RG, null));
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Kalkulation der zugeordneten Positionen !")));
						break;
					}
				}
			}
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Kalkulation der Kontrakt-Positionen !")));
		}
		
		if (bibMSG.get_bIsOK())
		{
			this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().set_Update_ADDON_Statements(vSQL_Zusatz, true);
		}
	}


	public void doBuildContent(ExecINFO oExecInfo) throws myException
	{
		
		
		this.oGridWithContent.removeAll();
		this.oGridWithContent.setSize(11);
		
		this.vCheckBoxes.removeAllElements();

		
		
		//den konstrakt-positionssatz rausziehen um die E-Preise zu vergleichen
		Double dPreisKontrakt_Neu = this.oKontrakt_Pos_Mask_container.get_oComponentMAP_Mask().get_DActualDBValue("EINZELPREIS", true, false, new Double(0));
		
		GridLayoutData oLayoutLeft = MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_2_10_2, 1, new Alignment(Alignment.LEFT,Alignment.CENTER));
		GridLayoutData oLayoutRight = MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_2_10_2, 1, new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		this.oGridWithContent.add(new MyE2_Label("?"),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(" "),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Name1"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Name2"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Ort"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("ANR1"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("ANR2"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Artikelbezeichnung"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutLeft);

		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Menge"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutRight);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Einzelpreis ",true,bibE2.get_cBASISWAEHRUNG_SYMBOL(),false),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutRight);
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Einzelpreis FW",false),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),oLayoutRight);

		for (int i=0;i<this.oMapVpos_RG.get_vKeyValues().size();i++)
		{
			
			RECORD_VPOS_RG recVPOS = this.oMapVpos_RG.get(i);
			
			String cID_VPOS_RG = this.oMapVpos_RG.get(i).get_ID_VPOS_RG_cUF();
			
			MyE2_CheckBox oCB = new MyE2_CheckBox();
			oCB.EXT().set_C_MERKMAL(cID_VPOS_RG);                   //im merkmal steht die ID_VPOS_RG
			
			this.vCheckBoxes.add(oCB);
			
			// ankreuzen, wenn positionspreis gleich kontraktpreis oder positionspreis = 0 oder leer
			boolean bAnkreuzen = false;
			
			
			//nachsehen, ob der kopf_rg vorhanden ist
			boolean bAbgeschlossenOderStorniert = false;

			if (this.oMapVpos_RG.get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg() != null)
			{
				if (this.oMapVpos_RG.get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
				{
					bAbgeschlossenOderStorniert = true;
				}
			}

			
			//2011-04-12: Aenderungen auch sperren bei gesetzten stornokennzeichen
			if (S.isFull(this.oMapVpos_RG.get(i).get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN(""))  || 
				S.isFull(this.oMapVpos_RG.get(i).get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
			{
				bAbgeschlossenOderStorniert = true;
			}
			
			
			
			if (dPreisKontrakt_Neu==0 || bAbgeschlossenOderStorniert)   //dann duerfen keine preise uebernommen werden
			{
				oCB.set_bEnabled_For_Edit(false);
			}
			else
			{
				bAnkreuzen = true;
			}			

			
			oCB.setSelected(bAnkreuzen);
			
			String cName1 = "";
			String cName2 = "";
			String cOrt = "";
			
			String cFremdWaehrungsymbol = recVPOS.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN("");
			
			//nachsehen, ob es einen kopf gibt
			if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				cName1 = recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_NAME1_cUF_NN("");
				cName2 = recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_NAME2_cUF_NN("");
				cOrt   = recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_ORT_cUF_NN("");
			}
			else    //sonst muss es einen ID_ADRESSE in der position geben
			{
				cName1 = recVPOS.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cUF_NN("");
				cName2 = recVPOS.get_UP_RECORD_ADRESSE_id_adresse().get_NAME2_cUF_NN("");
				cOrt   = recVPOS.get_UP_RECORD_ADRESSE_id_adresse().get_ORT_cUF_NN("");
			}
			
			this.oGridWithContent.add(oCB,oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(E2_ResourceIcon.get_RI(bAbgeschlossenOderStorniert?"locked.png":"unlocked.png")),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(cName1,MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(cName2,MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(cOrt,MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_ANR1_cUF_NN(""),MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_ANR2_cUF_NN(""),MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);
			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_ARTBEZ1_cUF_NN(""),MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutLeft);

			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_ANZAHL_cF_NN(""),MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutRight);
			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_EINZELPREIS_cF_NN(""),MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutRight);
			this.oGridWithContent.add(new MyE2_Label(recVPOS.get_EINZELPREIS_FW_cF_NN("")+" "+cFremdWaehrungsymbol,MyE2_Label.STYLE_SMALL_PLAIN()),oLayoutRight);
		}
	}



	@Override
	protected void doOwnCode_in_cancel_button() throws myException
	{

		
	}

	
}
