package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlainMonospace;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.unboundDataFields.UB_CheckBox;
import panter.gmbh.Echo2.components.unboundDataFields.UB_SelectField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForDateFields;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class FIBU__BUCHUNGS_BLOCK
{
	
	private UB_TextFieldForDateFields  	dbf_BUCHUNGSDATUM = 				new UB_TextFieldForDateFields("BUCHUNGSDATUM",false,bibALL.get_cDateNOW(),80,10);
	public UB_TextFieldForNumbers  		dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG = new UB_TextFieldForNumbers("ZAHLUNGSBETRAG_FREMD_WAEHRUNG",2,false,"",140,12);
	public UB_TextField 				dbf_BUCHUNGSINFO = 					new UB_TextField("BUCHUNGSINFO",false,"",400,200);;
	public UB_TextArea	       			dbf_INTERN_INFO = 					new UB_TextArea("INTERN_INFO",true,"",400,3,800);
	public UB_TextField 				dbf_WAEHRUNG = 						null;
	public UB_SelectField             	dfs_BUCHUNGSTYP = 					null;
	public UB_CheckBox                  dcb_KORREKTURBUCHUNG = 				new UB_CheckBox("KORREKTURBUCHUNG",null,"N");
	
	public UB_TextFieldForNumbers       dbf_SCHECKNUMMER = 					new UB_TextFieldForNumbers("SCHECKNUMMER",0,true,"",80,12);
	public UB_TextFieldForNumbers       dbf_SCHECKDRUCKANZAHL = 			new UB_TextFieldForNumbers("ANZAHL_SCHECK_DRUCK",0,true,"",30,5);
	public UB_TextArea	       			dbf_SCHECK_VERWENDUNG = 			new UB_TextArea("SCHECK_VERWENDUNGSZWECK",true,"",600,5,600);
	
	public UB_CheckBox                  dcb_SKONTODATUM_UEBERSCHRITTEN = 	new UB_CheckBox("SKONTODATUM_UEBERSCHRITTEN",null,"N");

	
	public boolean   				  	bEditable = false;
	public RECORD_FIBU     				recFibu = null;
	
	public MyE2_Button  				oButtonNehmeSaldo = new MyE2_Button(); 
	public MyE2_Button  				oButtonSaveBuchung = new MyE2_Button();
	
	private boolean    					bScheckBuchung = false;

	private RECORD_FIBU                 RecFibuBasis = null;
	
	private FIBU__BUCHUNGS_CONTAINER    oBuchungsContainer = null;
	
	private zauberButtonSammleGutschriften  oZauberstab =  new zauberButtonSammleGutschriften();

	private zauberButtonSchreibeBuchungsInfo  oZauberBuchungsInfo = new zauberButtonSchreibeBuchungsInfo();


	/**
	 * 
	 * @param oFibu_Container
	 * @param recFibu  (wenn null, dann ist es eine neue buchungszeile innerhalb des blocks)
	 * @param ScheckBuchung
	 * @throws myException
	 */
	public FIBU__BUCHUNGS_BLOCK(FIBU__BUCHUNGS_CONTAINER oFibu_Container, RECORD_FIBU recFibu, boolean ScheckBuchung) throws myException
	{
		super();
		
		this.oBuchungsContainer = oFibu_Container;
		
		this.RecFibuBasis  = this.oBuchungsContainer.get_recFibuBasis();
		
		this.recFibu = 			recFibu;
		this.dbf_WAEHRUNG = 	new UB_TextField("WAEHRUNG_FREMD",false,this.oBuchungsContainer.get_cWaehrung(),40,10);
		

		if (this.recFibu!=null)
		{
		this.dcb_SKONTODATUM_UEBERSCHRITTEN.setSelected(this.recFibu.is_SKONTO_DATUM_UEBERSCHRITTEN_YES());
		this.dcb_SKONTODATUM_UEBERSCHRITTEN.setToolTipText(new MyE2_String("Das Skontodatum wurde überschritten, Skonto wird ungültig!").CTrans());
		this.dcb_SKONTODATUM_UEBERSCHRITTEN.add_oActionAgent(new actionSchalteSkontoAus());
		
			if (this.recFibu.is_BUCHUNG_GESCHLOSSEN_YES())
			{
				this.dcb_SKONTODATUM_UEBERSCHRITTEN.set_bEnabled_For_Edit(false);
			}
		}
		
		//dbf_SCHECK_VERWENDUNG.setFont(new E2_FontPlain(-2));
		dbf_SCHECK_VERWENDUNG.setFont(new E2_FontPlainMonospace(-2));
		dbf_SCHECKNUMMER.setFont(new E2_FontPlain(-2));

		
		this.bScheckBuchung = ScheckBuchung;        // bei leerem fibu-satz
		
		if (recFibu != null)                        // bei vorhandenem fibu-satz ist der eintrag dort das entscheidende
		{
			this.bScheckBuchung = S.isFull(recFibu.get_SCHECKNUMMER_cUF_NN(""));
		}
		
		String[][] cHelp = {{"-",""}};
		

		if (this.recFibu == null)
		{
			if (this.bScheckBuchung)
			{
				this.dbf_SCHECK_VERWENDUNG.set_bEmptyAllowd(false);
				
				if (RecFibuBasis != null && RecFibuBasis.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
				{
					if (RecFibuBasis.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_GUTSCHRIFT))
					{
						this.dbf_SCHECK_VERWENDUNG.setText(this.erzeugeInfoStringFuerScheck(RecFibuBasis));
					}
				}
				this.dbf_BUCHUNGSINFO.setText("Scheck vom "+bibALL.get_cDateNOW());
				this.dfs_BUCHUNGSTYP = new UB_SelectField("BUCHUNGSTYP",false,bibALL.concat_arrays(cHelp, myCONST.BUCHUNGSTYPEN_SCHECK),myCONST.BT_SCHECKDRUCK,false);
				this.dbf_SCHECKDRUCKANZAHL.setText("1");
				
				//this.dbf_SCHECK_VERWENDUNG.set_StartValue(this.recFibu.get_SCHECK_VERWENDUNGSZWECK_cUF_NN(""));
				//den startwert wie mit dem zauberstab setzen
				new actionSammleGutschriften().executeAgentCode(null);
				this.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG.EXT().set_bHasFocusOnWindowPopup(true);
				
			}
			else if (this.oBuchungsContainer.get_bdSaldoBuchungBlock().compareTo(BigDecimal.ZERO)>0)
			{
				//2011-02-24: Buchungsdatum und Info leer machen
				this.dbf_BUCHUNGSDATUM.set_StartValue("");
				this.dbf_BUCHUNGSINFO.set_StartValue("");
				
				this.dfs_BUCHUNGSTYP = new UB_SelectField("BUCHUNGSTYP",false,bibALL.concat_arrays(cHelp, myCONST.BUCHUNGSTYPEN_AKTIV),myCONST.BT_ZAHLUNGSEINGANG,false);
				this.dbf_BUCHUNGSDATUM.EXT().set_bHasFocusOnWindowPopup(true);
			}
			else
			{
				this.dbf_BUCHUNGSDATUM.set_StartValue("");
				this.dbf_BUCHUNGSINFO.set_StartValue("");

				this.dfs_BUCHUNGSTYP = new UB_SelectField("BUCHUNGSTYP",false,bibALL.concat_arrays(cHelp, myCONST.BUCHUNGSTYPEN_AKTIV),myCONST.BT_ZAHLUNGSAUSGANG,false);
				this.dbf_BUCHUNGSDATUM.EXT().set_bHasFocusOnWindowPopup(true);
			}

			
			this.bEditable = true;
		}
		else
		{
			// wenn es ein bestehender block ist, dann muessen alle 5 buchungstype vorhanden sein
			// ein update-SQL wird nur gebraucht, wenn ein scheck nachgedruckt wird (das Feld ANZAHL_SCHECK_DRUCK wird erhoeht)
			this.dfs_BUCHUNGSTYP = new UB_SelectField("BUCHUNGSTYP",false,bibALL.concat_arrays(cHelp, myCONST.BUCHUNGSTYPEN),this.recFibu.get_BUCHUNGSTYP_cF_NN(""),false);
			this.dfs_BUCHUNGSTYP.set_StartValue(this.recFibu.get_BUCHUNGSTYP_cF_NN(""));
			
			//2011-03-22: Buchungsdatum und Druckdatum richtig eintragen
			if (this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG) || this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT))
			{
				this.dbf_BUCHUNGSDATUM.set_StartValue(this.recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DRUCKDATUM_cF_NN(""));
			}
			else
			{
				this.dbf_BUCHUNGSDATUM.set_StartValue(this.recFibu.get_BUCHUNGSDATUM_cF_NN(""));
			}
			
			this.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG.set_StartValue(this.recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cF_NN(""));
			this.dbf_BUCHUNGSINFO.set_StartValue(this.recFibu.get_BUCHUNGSINFO_cF_NN(""));
			
			this.dbf_INTERN_INFO.set_StartValue(this.recFibu.get_INTERN_INFO_cF_NN(""));
			
			this.dcb_KORREKTURBUCHUNG.set_StartValue(this.recFibu.get_KORREKTURBUCHUNG_cUF_NN("N"));
			
			this.dbf_SCHECKNUMMER.set_StartValue(this.recFibu.get_SCHECKNUMMER_cUF_NN(""));
			this.dbf_SCHECKDRUCKANZAHL.set_StartValue(""+this.recFibu.get_ANZAHL_SCHECK_DRUCK_lValue(new Long(0)).longValue());
			if (this.bScheckBuchung)
			{
				this.dbf_SCHECKDRUCKANZAHL.setText(""+(this.recFibu.get_ANZAHL_SCHECK_DRUCK_lValue(new Long(0)).longValue()+1));
			}
			else
			{
				this.dbf_SCHECKDRUCKANZAHL.set_StartValue("");
			}
			
			
			//wenn es ein bereits vorhandener satz ist, dann den scheckverwendungszweck laden
			this.dbf_SCHECK_VERWENDUNG.set_StartValue(this.recFibu.get_SCHECK_VERWENDUNGSZWECK_cUF_NN(""));
		
			this.bEditable = false;
		}
		
		this.oButtonNehmeSaldo.setText(MyNumberFormater.formatDez(this.oBuchungsContainer.get_bdSaldoBuchungBlock().abs(), 2, true));
		this.oButtonNehmeSaldo.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.oButtonNehmeSaldo.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo)	throws myException
			{
				FIBU__BUCHUNGS_BLOCK.this.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG.setText(MyNumberFormater.formatDez(FIBU__BUCHUNGS_BLOCK.this.oBuchungsContainer.get_bdSaldoBuchungBlock().abs(), 2, true));
			}
		});
		
		
		this.dfs_BUCHUNGSTYP.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo)	throws myException
			{
				FIBU__BUCHUNGS_BLOCK 	oThis = 			FIBU__BUCHUNGS_BLOCK.this;
				oThis.oZauberBuchungsInfo.doActionPassiv();
			}
			
		});
		
		this.oButtonSaveBuchung.set_Text(new MyE2_String("Speichern"));
		this.oButtonSaveBuchung.add_GlobalAUTHValidator_AUTO("BUCHUNG_SPEICHERN");
		if (this.bScheckBuchung)
		{
			this.oButtonSaveBuchung.set_Text(new MyE2_String("Scheck drucken"));
			this.oButtonSaveBuchung.add_GlobalAUTHValidator_AUTO("SCHECK_DRUCK");
			
			this.oButtonSaveBuchung.add_GlobalValidator(new ownValidatorPruefeKunde_WG_ScheckDruck());
			
		}
		this.oButtonSaveBuchung.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.oButtonSaveBuchung.add_oActionAgent(new actionAgentSave());
	}

	
	
	
	private String erzeugeInfoStringFuerScheck(RECORD_FIBU recFibu) throws myException
	{
		String cHelpText = recFibu.get_BUCHUNGSINFO_cF_NN("--");
		
		cHelpText = S.makeStringLonger(cHelpText, " ", 40, false)+"";
		cHelpText = cHelpText+S.makeStringLonger(recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cF_NN("0")+" "+recFibu.get_WAEHRUNG_FREMD_cUF_NN("?")," ",20,true);
		
		return cHelpText;

	}

	
	private class actionAgentSave extends XX_ActionAgent
	{
		
		public actionAgentSave()
		{
			super();
			this.set_oPopup_ContainerBeforeExecute(new E2_BasicModuleContainer_PopUp_BeforeExecute()
			{
				@Override
				public void doBuildContent(ExecINFO execInfo)	throws myException
				{
					this.RESET_Content();
					
					FIBU__BUCHUNGS_BLOCK oThis = FIBU__BUCHUNGS_BLOCK.this;

					String cAnzahl = "";
					if (S.isFull(oThis.dbf_SCHECKDRUCKANZAHL.get_cText()))
					{
						if (new MyInteger(oThis.dbf_SCHECKDRUCKANZAHL.get_cText()).get_iValue()>1)
						{
							cAnzahl = ""+(new MyInteger(oThis.dbf_SCHECKDRUCKANZAHL.get_cText()).get_iValue()-1);
						}
					}
					
					MyE2_String cAlarmText = new MyE2_String("Achtung! Der Scheck wurde bereits ",true, cAnzahl,false,"-mal gedruckt !!",true);
					
					this.add(new MyE2_Label(cAlarmText,MyE2_Label.STYLE_ERROR_BIGBIG()), E2_INSETS.I_10_10_10_10);
					
					this.get_oButtonForAbbruch().set_Text(new MyE2_String("Nicht nochmal drucken"));
					this.get_oButtonForOK().set_Text(new MyE2_String("TROTZDEM nochmal drucken"));
					this.add(new E2_ComponentGroupHorizontal(0,this.get_oButtonForAbbruch(), this.get_oButtonForOK(),E2_INSETS.I_0_0_10_0), E2_INSETS.I_10_10_10_10);
					this.set_oExtWidth(new Extent(700));
					this.set_oExtHeight(new Extent(250));
			
				}

				@Override
				protected void doOwnCode_in_cancel_button()		throws myException
				{
				}

				@Override
				protected void doOwnCode_in_ok_button() throws myException
				{
				}

				@Override
				public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO execInfo) throws myException
				{
					FIBU__BUCHUNGS_BLOCK oThis = FIBU__BUCHUNGS_BLOCK.this;
					boolean bRueck = false;
					
					if (oThis.bScheckBuchung)
					{
						if (S.isFull(oThis.dbf_SCHECKDRUCKANZAHL.get_cText()))
						{
							if (new MyInteger(oThis.dbf_SCHECKDRUCKANZAHL.get_cText()).get_iValue()>1)
							{
								bRueck = true;
							}
						}
					}
					
					return bRueck;
				}
			});

		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FIBU__BUCHUNGS_BLOCK		oThisBuchBlock = FIBU__BUCHUNGS_BLOCK.this;
			FIBU__BUCHUNGS_CONTAINER	oThisContainer = FIBU__BUCHUNGS_BLOCK.this.oBuchungsContainer;
			
			VECTOR_UB_FIELDS vUB = new VECTOR_UB_FIELDS();
			
			//sicherheitshalber eintragen, eigentlich sollte bei rechnungen und gutschriften nie die maske gespeichert werden; in diesem Feld steht bei Rechnungen 
			//und Gutschriften aber nicht das Buchungsdatum, sondern das Rechnung-Druckdatum#
			//Diese Sequenz verhindert das versehentliche aendern des Buchungseintrags  
			boolean bBuchungsDatumSpeichern = true;
			if (oThisBuchBlock.dfs_BUCHUNGSTYP.get_ActualWert().equals(myCONST.BT_DRUCK_RECHNUNG) || oThisBuchBlock.dfs_BUCHUNGSTYP.get_ActualWert().equals(myCONST.BT_DRUCK_GUTSCHRIFT) )
			{
				bBuchungsDatumSpeichern=false;
			}
			
			if (bBuchungsDatumSpeichern)
			{
				vUB.add(oThisBuchBlock.dbf_BUCHUNGSDATUM);
			}
			vUB.add(oThisBuchBlock.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG);
			vUB.add(oThisBuchBlock.dbf_BUCHUNGSINFO);
			vUB.add(oThisBuchBlock.dbf_WAEHRUNG);
			vUB.add(oThisBuchBlock.dfs_BUCHUNGSTYP);
			vUB.add(oThisBuchBlock.dcb_KORREKTURBUCHUNG);
			vUB.add(oThisBuchBlock.dbf_SCHECK_VERWENDUNG);
			vUB.add(oThisBuchBlock.dbf_SCHECKDRUCKANZAHL);
			vUB.add(oThisBuchBlock.dbf_SCHECKNUMMER);
			vUB.add(oThisBuchBlock.dbf_INTERN_INFO);
			
			vUB.add(oThisContainer.get_oSearchAdress());
			
			String cPlusMinus = "1";
			if (oThisBuchBlock.dfs_BUCHUNGSTYP.get_ActualWert().equals(myCONST.BT_ZAHLUNGSEINGANG))
			{
				cPlusMinus = "-1";
			}
			
			bibMSG.add_MESSAGE(vUB.get_MV_AllFieldsAreOK_ShowErrorInput());

			//einige spezial-validierungen fuer scheckdruck
			String cDruckBefehl = "";
			if (oThisBuchBlock.bScheckBuchung)
			{
				cDruckBefehl=oThisContainer.get_oSelectDrucker().get_ActualWert();
				
				if (S.isEmpty(cDruckBefehl))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurde kein Drucker angegeben !"));
					return;
				}
				
				//bei erstdruck kann ein scheck nur bei offenen zahlungen erfolgen, nachdruck ist immer erlaubt
				if (RecFibuBasis == null && oThisContainer.get_bdSaldoBuchungBlock().compareTo(BigDecimal.ZERO)>0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Scheckdruck kann nur bei offenen Zahlungen erfolgen !!"));
					return;
				}
				
			}
			
			
			if (bibMSG.get_bIsOK())
			{
				
				
				if (S.isEmpty(oThisBuchBlock.dbf_SCHECKNUMMER.getText()) && oThisBuchBlock.bScheckBuchung)   //im fall "neueingabe"
				{
					oThisBuchBlock.dbf_SCHECKNUMMER.setText(bibALL.get_SEQUENCE_VECT_VALUES("SEQ_"+bibALL.get_ID_MANDANT()+"_SCHECKNUMMER_FIBU", 1).get(0));
				}

				
				//die neuen auch markieren nach dem rebuild
				Vector<String> vMark = new Vector<String>();
				vMark.addAll(oThisContainer.get_oNaviList().get_vSelectedIDs_Unformated());
				
				String cID_FIBU_NEXT = null;   //falls neueingabe
				String cID_FIBU_SCHECKDRUCK = null;   //falls neueingabe
				
				String cSQL = null;
				
				if (oThisBuchBlock.recFibu==null)
				{
					cID_FIBU_NEXT = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_FIBU", 1).get(0);

					cSQL = "INSERT INTO "+bibE2.cTO()+".JT_FIBU ("+vUB.get_cInsertFieldsBlock()+"" +
												",ID_FIBU" +
												",BUCHUNGSBLOCK_NR" +
												",FAKTOR_BUCHUNG_PLUS_MINUS" +
												",BEARBEITERKUERZEL" +
												",DATUMVERAENDERUNG" +
												",VORLAEUFIG) " +
												" VALUES("+vUB.get_cInsertValuesBlock()+","+
												cID_FIBU_NEXT+","+
												""+oThisContainer.get_iBuchungsBlockNummer()+","+
												cPlusMinus+","+
												bibALL.MakeSql(bibALL.get_KUERZEL())+"," +
												oThisBuchBlock.dbf_BUCHUNGSDATUM.get_cInsertValuePart()+","+
												"'N'"+
												")";

					vMark.add(cID_FIBU_NEXT);
					cID_FIBU_SCHECKDRUCK = cID_FIBU_NEXT;
				}
				else
				{ 
					//falls ein scheck nochmal gedruckt wird, wird die scheck-druck-anzahl erhoeht
					cSQL= "UPDATE "+bibE2.cTO()+".JT_FIBU SET "+vUB.get_cUpdateBlock(true)+" WHERE ID_FIBU="+oThisBuchBlock.recFibu.get_ID_FIBU_cUF();
					cID_FIBU_SCHECKDRUCK = oThisBuchBlock.recFibu.get_ID_FIBU_cUF();
				}
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(cSQL), true));



				oThisContainer.refresh_SaldoBuchungsBlock();
				
				if (oThisContainer.get_bdSaldoBuchungBlock().compareTo(BigDecimal.ZERO)==0)
				{
					//KORREKTUR-FIBU-BUG 2015-02-13 (mandanten-bedingung eingefuegt)
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(
							"UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='Y' WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND BUCHUNGSBLOCK_NR="+oThisContainer.get_iBuchungsBlockNummer()), true));
				}
				
				boolean bDruckeBlattAmEnde = false;
				
				//falls der basis-record der buchungscontainers null ist, dann wird der gerade gespeicherte rec basis-record
				//und es wird automatisch ein buchungsblatt gedruckt bei blanko eingegebenen schecks / buchungen
				if (oThisContainer.get_recFibuBasis()==null)
				{
					oThisContainer.set_recFibuBasis(new RECORD_FIBU(cID_FIBU_NEXT));
					
					oThisContainer.BAUE_ALLES_NEU_AUF();
					
//					oThisContainer.rebuild_Buchungsblock_List_Vector();
//					oThisContainer.BerechneRestBetraegeBei_GELD_REIN_POSITIONEN();
//					oThisContainer.rebuild_Buchungsblock_List_Vector();
//					oThisContainer.rebuild_Buchungsblock_Mask();
					//oThisContainer.Drucke_Buchungsblatt();
					bDruckeBlattAmEnde = true;
				}
				else
				{
					oThisContainer.BAUE_ALLES_NEU_AUF();

//					oThisContainer.rebuild_Buchungsblock_List_Vector();
//					oThisContainer.BerechneRestBetraegeBei_GELD_REIN_POSITIONEN();
//					oThisContainer.rebuild_Buchungsblock_List_Vector();
//					oThisContainer.rebuild_Buchungsblock_Mask();
				}

				
				//jetzt drucken (im falle es ist ein Scheckdruck und bisher alles ok
				if (oThisBuchBlock.bScheckBuchung && bibMSG.get_bIsOK())
				{
					E2_JasperHASH_STD  oJasperScheck = new E2_JasperHASH_STD("scheck",new JasperFileDef_PDF());
					
					
					RECORD_FIBU recFibuNeu = new RECORD_FIBU(cID_FIBU_SCHECKDRUCK);
					
					BigDecimal bdBetrag = recFibuNeu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO, 2);
					String cBetrag = MyNumberFormater.formatDez(bdBetrag, 2, false,',','.',false);
					if (cBetrag.indexOf(',')>0)
					{
						cBetrag = cBetrag.substring(0,cBetrag.indexOf(','));
					}
					
					HashMap<String, String> hmUebersetzer = new HashMap<String, String>();
					
					hmUebersetzer.put("0", "NULL");
					hmUebersetzer.put("1", "EINS");
					hmUebersetzer.put("2", "ZWEI");
					hmUebersetzer.put("3", "DREI");
					hmUebersetzer.put("4", "VIER");
					hmUebersetzer.put("5", "FÜNF");
					hmUebersetzer.put("6", "SECHS");
					hmUebersetzer.put("7", "SIEBEN");
					hmUebersetzer.put("8", "ACHT");
					hmUebersetzer.put("9", "NEUN");
					
					String cBetragAsText = "";
					for (int i=0;i<cBetrag.length();i++)
					{
						String Ziffer = cBetrag.substring(i,i+1);
						cBetragAsText+=hmUebersetzer.get(Ziffer)+" ";
					}
					
					oJasperScheck.put("id_fibu", cID_FIBU_SCHECKDRUCK);
					oJasperScheck.put("betrag_in_buchstaben", cBetragAsText);
					
					oJasperScheck.Build_tempFile(true);
					oJasperScheck.sendPdfFile_to_Printer(cDruckBefehl);
					
				}
				
				if (S.isFull(cID_FIBU_NEXT))
				{
					oThisContainer.get_oNaviList().ADD_NEW_ID_TO_ALL_VECTORS(cID_FIBU_NEXT);
				}
				oThisContainer.get_oNaviList()._REBUILD_ACTUAL_SITE("");
				oThisContainer.get_oNaviList().Check_ID_IF_IN_Page(vMark);
				
				if (bDruckeBlattAmEnde)
				{
					oThisContainer.Drucke_Buchungsblatt();
				}
			}
		}
	}
	
	
	
	private class ownValidatorPruefeKunde_WG_ScheckDruck extends XX_ActionValidator
	{
		
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException 
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			FIBU__BUCHUNGS_BLOCK oThis = FIBU__BUCHUNGS_BLOCK.this;
			
			String cID_ADRESSE = oThis.oBuchungsContainer.get_oSearchAdress().get_cText();
			DotFormatter  DF = new DotFormatterGermanFixed(cID_ADRESSE);
			
			if (DF.doFormat())
			{
				RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(DF.getStringUnFormated());
				
				if (recAdresse.is_BARKUNDE_NO() && recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_SCHECKDRUCK_JN_NO())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Scheckdruck nur möglich bei Adressen, die entweder als BAR oder für SCHECK  !!")));
				}
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Bitte die Adresse korrekt definieren !!")));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)		throws myException 
		{
			return null;
		}
		
	}
	
	
	
	public void set_enabled(boolean bEnabled) throws myException
	{
		this.dbf_BUCHUNGSDATUM.set_bEnabled_For_Edit(bEnabled);
		this.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG.set_bEnabled_For_Edit(bEnabled);
		this.dbf_BUCHUNGSINFO.set_bEnabled_For_Edit(bEnabled);
		this.dbf_INTERN_INFO.set_bEnabled_For_Edit(bEnabled);
		this.dfs_BUCHUNGSTYP.set_bEnabled_For_Edit(bEnabled);
		this.dcb_KORREKTURBUCHUNG.set_bEnabled_For_Edit(bEnabled);
		this.dbf_WAEHRUNG.set_bEnabled_For_Edit(false);   								// waehrung immer gesperrt
		this.dbf_SCHECK_VERWENDUNG.set_bEnabled_For_Edit(bEnabled);
		this.dbf_SCHECKDRUCKANZAHL.set_bEnabled_For_Edit(false);                        // wird automatisch gesetzt
		this.dbf_SCHECKNUMMER.set_bEnabled_For_Edit(false);                         	// wird automatisch gesetzt
		
		this.oZauberstab.set_bEnabled_For_Edit(bEnabled);
		this.oZauberBuchungsInfo.set_bEnabled_For_Edit(bEnabled);
		if (this.bScheckBuchung)
		{
			this.oZauberBuchungsInfo.set_bEnabled_For_Edit(false);
		}
	}
	
	
	public MyE2_Grid get_BuchungsdatumsFeld() throws myException
	{
		MyE2_Grid oGrid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		if (this.bScheckBuchung)
		{
			oGrid.setSize(1);

			oGrid.add(this.dbf_BUCHUNGSDATUM, E2_INSETS.I_0_0_0_0);
		}
		else
		{
			//zahlungsziel einblenden
			if (this.recFibu!=null && (this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG) || this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT)))
			{
				oGrid.setSize(1);
				MyE2_Grid oGridHelp = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				oGridHelp.add(this.dbf_BUCHUNGSDATUM, E2_INSETS.I_0_0_0_0);
				MyE2_Label labelHelp = new MyE2_Label(this.recFibu.get_ZAHLUNGSZIEL_cF_NN("??"),MyE2_Label.STYLE_SMALL_PLAIN());
				labelHelp.setToolTipText(new MyE2_String("Fälligkeitsdatum der Rechnung oder Gutschrift").CTrans());
				oGridHelp.add(labelHelp, new Insets(5,2,0,0));
				oGrid.add(oGridHelp);
			}
			else
			{
				oGrid.setSize(2);
				oGrid.add(this.dbf_BUCHUNGSDATUM, E2_INSETS.I_0_0_0_0);
				oGrid.add(this.oZauberBuchungsInfo, E2_INSETS.I_2_0_0_0);
			}
		}
		return oGrid;	
	}
	
	
	public MyE2_Grid get_InfoBlock() throws myException
	{
		MyE2_Grid oGrid = new MyE2_Grid(2,0);
		
		if ( !this.bScheckBuchung)
		{
			oGrid.add(this.dbf_BUCHUNGSINFO, 2, E2_INSETS.I_0_0_0_0);
			oGrid.add(this.dbf_INTERN_INFO, 2, E2_INSETS.I_0_0_0_0);
		}
		else
		{
			oGrid.add(this.dbf_BUCHUNGSINFO, 2, E2_INSETS.I_0_0_0_0);
			oGrid.add(this.dbf_INTERN_INFO, 2, E2_INSETS.I_0_0_0_0);
		}
		
		return oGrid;
	}

	//kommt nur bei scheckdruck != null zurueck
	public MyE2_Grid get_InfoAddonBlock() throws myException
	{
		MyE2_Grid oGrid = null;
		
		if ( this.bScheckBuchung)
		{
			Alignment  oAlign = new Alignment(Alignment.LEFT, Alignment.TOP);
			oGrid = new MyE2_Grid(2,0);
			
			MyE2_Grid oGridInnenLinks = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

			oGridInnenLinks.add(this.dbf_SCHECKNUMMER, E2_INSETS.I_1_1_1_1);
			oGridInnenLinks.add(this.oZauberstab, E2_INSETS.I_1_1_1_1);
			oGrid.add(oGridInnenLinks,1,1,E2_INSETS.I_0_2_2_2, oAlign);
			oGrid.add(this.dbf_SCHECK_VERWENDUNG,1,1,E2_INSETS.I_0_2_2_2, oAlign);
		}
		
		return oGrid;
	}
	
	
	//blendet bei den positionen, wo es sinn macht, den restbetrag und die skontobehandlung ein
	public MyE2_Grid get_Block_Zahlbetrag_RestBetrag() throws myException
	{
		MyE2_Grid oGrid = new MyE2_Grid(1,0);
		
		oGrid.add(this.dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG, E2_INSETS.I_0_0_0_0);
		
		if (this.recFibu != null)
		{
			RECORD_FIBU_ext recFibuExt = new RECORD_FIBU_ext(this.recFibu);
			
			if (this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG) || this.recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT))
			{
				MyE2_Grid oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
				
				if (recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0)
				{
					oGridHelp.add(new MyE2_Label(new MyE2_String("Skto:"), MyE2_Label.STYLE_NORMAL_PLAIN()),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_2_0_0));
					oGridHelp.add(new MyE2_Label(recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_cF_NN(""), MyE2_Label.STYLE_NORMAL_PLAIN()),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_2_2_2_0));
					
					//wenn der schalter "ueberschritten" gedrueckt wurde, dann den gesamtbetrag anzeigen
					if (this.recFibu.is_SKONTO_DATUM_UEBERSCHRITTEN_YES())
					{
						oGridHelp.add(new MyE2_Label(new MyE2_String("Gesamt:"), MyE2_Label.STYLE_NORMAL_PLAIN()),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_2_0_0));
						oGridHelp.add(new MyE2_Label(recFibu.get_ENDBETRAG_FREMD_WAEHRUNG_cF_NN(""), MyE2_Label.STYLE_NORMAL_PLAIN()),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_2_2_2_0));
					}
					
					oGridHelp.add(new MyE2_Label(new MyE2_String("Überschritten"), MyE2_Label.STYLE_SMALL_PLAIN()),LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_0_2_0_0));
					oGridHelp.add(this.dcb_SKONTODATUM_UEBERSCHRITTEN,LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_2_2_2_0));
					
				}
				
				if (recFibuExt.get_bKannGemahntWerden())
				{
					oGridHelp.add(new MyE2_Label(new MyE2_String("offen:"), MyE2_Label.STYLE_NORMAL_PLAIN()),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_2_0_0));

					MyE2_Label  oLabelRestbetrag = new MyE2_Label(recFibuExt.get_RESTBETRAG_FREMD_WAEHRUNG_cF_NN("0"), MyE2_Label.STYLE_NORMAL_PLAIN());
					oLabelRestbetrag.setToolTipText(new MyE2_String("Restbetrag der Rechnung/Gutschrift").CTrans());
					oGridHelp.add(oLabelRestbetrag, LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_2_2_2_0));
				}
				
				oGrid.add(oGridHelp, LayoutDataFactory.get_GridLayoutGridRight_TOP(new Insets(0,2,0,0)));
			}
		}
		return oGrid;
	}

	

	public boolean get_bScheckBuchung()
	{
		return bScheckBuchung;
	}

	public zauberButtonSchreibeBuchungsInfo getoZauberBuchungsInfo() 
	{
		return oZauberBuchungsInfo;
	}
	
	
	//button der einen vorschlag fuer das scheckinfo-feld erzeugt
	private class zauberButtonSammleGutschriften extends MyE2_Button
	{
		public zauberButtonSammleGutschriften() 
		{
			super(E2_ResourceIcon.get_RI("wizard_mini.png"), true);
			this.add_oActionAgent(new actionSammleGutschriften());
		}
	}
	
	


	
	
	private class actionSammleGutschriften extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU__BUCHUNGS_BLOCK		oThisBuchBlock = FIBU__BUCHUNGS_BLOCK.this;
			FIBU__BUCHUNGS_CONTAINER	oThisContainer = FIBU__BUCHUNGS_BLOCK.this.oBuchungsContainer;
			
			Vector<FIBU__BUCHUNGS_BLOCK> vBuchungsBlock = oThisContainer.get_vBuchungsBlock();
			String cBuchungsText = "";
			
			for (int i=0;i<vBuchungsBlock.size();i++)
			{
				FIBU__BUCHUNGS_BLOCK oBuchBlock = vBuchungsBlock.get(i);
				
				if (oBuchBlock.recFibu!=null && oBuchBlock.recFibu.get_BUCHUNGSTYP_cF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT))
				{
					cBuchungsText+=oThisBuchBlock.erzeugeInfoStringFuerScheck(oBuchBlock.recFibu)+"\n";
				}
			}
			FIBU__BUCHUNGS_BLOCK.this.dbf_SCHECK_VERWENDUNG.setText(cBuchungsText);
			
		}
	}

	
	//button der einen vorschlag fuer das scheckinfo-feld erzeugt
	private class zauberButtonSchreibeBuchungsInfo extends MyE2_Button
	{
		public zauberButtonSchreibeBuchungsInfo() 
		{
			super(E2_ResourceIcon.get_RI("wizard_mini.png"), true);
			this.add_oActionAgent(new actionSchreibeBuchungsInfo());
		}
	}
	
	private class actionSchreibeBuchungsInfo extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU__BUCHUNGS_BLOCK oThis = FIBU__BUCHUNGS_BLOCK.this;

			String cDatum = "";
			if (S.isFull(oThis.dbf_BUCHUNGSDATUM.get_cText()))
			{
				MyDate  oDate = new MyDate(oThis.dbf_BUCHUNGSDATUM.get_cText());
				if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
				{
					cDatum = oDate.get_cUmwandlungsergebnis();
				}
				else
				{
					cDatum = "";
				}
			}
			else
			{
				cDatum = bibALL.get_cDateNOW();
			}
			
			oThis.dbf_BUCHUNGSDATUM.setText(S.NN(cDatum));
			
			if (S.isFull(cDatum))
			{
				if (oThis.dfs_BUCHUNGSTYP.get_ActualWert().equals(myCONST.BT_ZAHLUNGSEINGANG))
				{
					oThis.dbf_BUCHUNGSINFO.setText("Zahlungseingang vom "+cDatum);
				}
				else
				{
					oThis.dbf_BUCHUNGSINFO.setText("Zahlungsausgang vom "+cDatum);
				}
			}
			
		}
	}
	
	
	private class actionSchalteSkontoAus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU__BUCHUNGS_BLOCK		oThisBuchBlock = FIBU__BUCHUNGS_BLOCK.this;
			FIBU__BUCHUNGS_CONTAINER	oThisContainer = FIBU__BUCHUNGS_BLOCK.this.oBuchungsContainer;

			
			RECORD_FIBU  recFibu = new RECORD_FIBU(oThisBuchBlock.recFibu.get_ID_FIBU_cUF());
			
			String 		cNeuWert = 		"N";
			BigDecimal  bdRestBetrag = 	recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO);
			
			if (oThisBuchBlock.dcb_SKONTODATUM_UEBERSCHRITTEN.isSelected())
			{
				cNeuWert = "Y";
				bdRestBetrag.add(recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO));
			}
			recFibu.set_NEW_VALUE_SKONTO_DATUM_UEBERSCHRITTEN(cNeuWert);
			
			bdRestBetrag.multiply(recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO));
			recFibu.set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(MyNumberFormater.formatDez(bdRestBetrag, 2, true));
			
			bibMSG.add_MESSAGE(recFibu.UPDATE(null, true));
			
			//jetzt alles neu aufbauen
			if (oThisContainer.get_recFibuBasis()!=null)      //sollte bei null-bloecken nie vorkommen, da die checkbox nur bei Belegen angezeigt wird (DRUCK_RECHNUNG, DRUCK_GUTSCHRIFT) 
			{
//				oThisContainer.rebuild_Buchungsblock_List_Vector();
//				oThisContainer.BerechneRestBetraegeBei_GELD_REIN_POSITIONEN();
//				oThisContainer.rebuild_Buchungsblock_List_Vector();
//				oThisContainer.rebuild_Buchungsblock_Mask();
				oThisContainer.BAUE_ALLES_NEU_AUF();
			}
		}
	}
	
	
	
}
