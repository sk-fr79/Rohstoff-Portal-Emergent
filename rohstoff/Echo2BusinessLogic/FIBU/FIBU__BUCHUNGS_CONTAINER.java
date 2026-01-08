package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.bibREP;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;
import rohstoff.utils.ECHO2.UB_MaskSearchField_SEARCH_Adress;

public class FIBU__BUCHUNGS_CONTAINER extends E2_BasicModuleContainer
{

	private Vector<FIBU__BUCHUNGS_BLOCK> 		vBuchungsBlock = 	new Vector<FIBU__BUCHUNGS_BLOCK>();
	private RECORD_FIBU  						recFibuBasis = 		null;
	private MyE2_Column             			oColBasic = 		new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	private long   								iBuchungsBlockNummer = 0;
	private String  							cWaehrung=null;
	private BigDecimal             				bdSaldoBuchungBlock = null;
	private UB_MaskSearchField_SEARCH_Adress 	oSearchAdress = new UB_MaskSearchField_SEARCH_Adress("ID_ADRESSE",false);
	private E2_NavigationList  					oNaviList = null;
	private boolean   							bScheckDruck = false;
	private MyE2_SelectField		            oSelectDrucker = new MyE2_SelectField();
	private RECLIST_FIBU  						recListFibu = null;

	private FIBU__DRUCKE_BUCHUNGSBLATT 			oButtonPrintBuchungsblatt = null;
	private MyE2_Button  			     		oButtonSchliesseFenster = new MyE2_Button(new MyE2_String("Schliesse Fenster"));
	
	//wenn ein buchungsblock einen saldo von 0 hat, dann den schliessknopf / oeffnenknopf anbieten
	private MyE2_Button  			     		oButtonSchliesseBuchungsblock = new MyE2_Button(new MyE2_String("Buchungsblock schliessen"));
	private MyE2_Button  			     		oButtonOeffneBuchungsblock = new MyE2_Button(new MyE2_String("Buchungsblock öffnen"));
	
	
	private ownJasperHash   					o_JasperHash = null;
	
	public FIBU__BUCHUNGS_CONTAINER(String cID_FIBU_uf, E2_NavigationList NaviList, boolean ScheckDruck) throws myException
	{
		super();
		this.set_MODUL_IDENTIFIER(E2_MODULNAMES.NAME_MODUL_FIBU_LIST);
		
		this.oNaviList=NaviList;
		this.bScheckDruck = ScheckDruck;

		this.oButtonPrintBuchungsblatt = new FIBU__DRUCKE_BUCHUNGSBLATT("Drucke Buchungsblatt und schliesse Fenster", this);
		
		//2011-02-21: refresh maske nach suchvorgang, damit evtl vorhandene adress-meldungen eingeblendet werden
		this.oSearchAdress.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB() 
		{
			
			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(	String cMaskValue,
																	MyE2_MaskSearchField oSearchField, boolean bAfterAction)  throws myException 
			{
				FIBU__BUCHUNGS_CONTAINER.this.rebuild_Buchungsblock_Mask();
			}
		});
		
		String[][] ddArrayDrucker = bibREP.get_ArrayDruckerNamePlusDruckBefehl(bibREP.get_DruckerFuerModulUndBenutzer(this.get_MODUL_IDENTIFIER(), null, true),true);
		
		this.oSelectDrucker.set_ListenInhalt(ddArrayDrucker, false);
		this.oSelectDrucker.setSelectedIndex(ddArrayDrucker.length==1?0:1); //falls  mindestens einer gefunden wurde, wird der selektiert

		this.add(oColBasic);
		
		this.oSearchAdress.get_oTextForAnzeige().setWidth(new Extent(300));
		
		if (S.isFull(cID_FIBU_uf))
		{
			this.recFibuBasis = new RECORD_FIBU(cID_FIBU_uf);
		}
		this.rebuild_Buchungsblock_List_Vector();
		this.BerechneRestBetraegeBei_GELD_REIN_POSITIONEN();
		this.rebuild_Buchungsblock_List_Vector();                         //hier wird auch der saldo berechnet
		this.rebuild_Buchungsblock_Mask();
		
		this.oButtonPrintBuchungsblatt.add_oActionAgent(new ownActionAgentCloseWindow());
		this.oButtonSchliesseFenster.add_oActionAgent(new ownActionAgentCloseWindow());
		
		this.oButtonSchliesseBuchungsblock.add_oActionAgent(new actionOeffneSchliesseBuchungsBlock("Y"));
		this.oButtonOeffneBuchungsblock.add_oActionAgent(new actionOeffneSchliesseBuchungsBlock("N"));
		
		/*
		 * falls buchungsblock vorhanden (keine frei buchung) und der saldo ist null, und die buchung war noch nicht geschlossen
		 * sofort ausbuchen
		 */
		boolean bAlleGeschlossen = true;
		for (int i=0;i<this.recListFibu.get_vKeyValues().size();i++)
		{
			if (this.recListFibu.get(i).is_BUCHUNG_GESCHLOSSEN_NO())
			{
				bAlleGeschlossen = false;
			}
		}
		
		if (this.recFibuBasis!=null && this.bdSaldoBuchungBlock.compareTo(BigDecimal.ZERO)==0 && !bAlleGeschlossen)
		{
			//KORREKTUR-FIBU-BUG 2015-02-13: mandant eingefuegt
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(
					"UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='Y' WHERE  ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND BUCHUNGSBLOCK_NR="+this.recFibuBasis.get_BUCHUNGSBLOCK_NR_cUF()
					), true));
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Buchungssaldo des Blocks war 0, deshalb wurde die Buchung geschlossen !"));
				for (int i=0;i<this.recListFibu.get_vKeyValues().size();i++)
				{
					this.oNaviList.Refresh_ComponentMAP(this.recListFibu.get_vKeyValues().get(i), E2_ComponentMAP.STATUS_VIEW);
				}
				this.rebuild_Buchungsblock_List_Vector();
				this.rebuild_Buchungsblock_Mask();
			}
			else
			{
				return;
			}
			
		}
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(500), new MyE2_String("Buchung"));
	}

	
	
	private class ownActionAgentCloseWindow extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU__BUCHUNGS_CONTAINER.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	public void rebuild_Buchungsblock_List_Vector() throws myException
	{
		this.vBuchungsBlock.removeAllElements();
		
		if (this.recFibuBasis!=null)
		{
			this.iBuchungsBlockNummer = this.recFibuBasis.get_BUCHUNGSBLOCK_NR_lValue(new Long(0));
			this.cWaehrung = this.recFibuBasis.get_WAEHRUNG_FREMD_cUF_NN("");
			this.oSearchAdress.set_StartValue(""+this.recFibuBasis.get_ID_ADRESSE_cF());
			this.oSearchAdress.FillLabelWithDBQuery(""+this.recFibuBasis.get_ID_ADRESSE_lValue(new Long(0)));
			this.oSearchAdress.set_bEnabled_For_Edit(false);
		}
		else
		{
			//programm-marker: 	KORREKTUR-FIBU-BUG
			
//			this.iBuchungsBlockNummer = new Long(bibALL.get_SEQUENCE_VECT_VALUES("SEQ_"+bibALL.get_ID_MANDANT()+"_BUCHUNGSBLOCK_FIBU", 1).get(0));
			//2015-02-13: austausch gegen globalen sequencer
			this.iBuchungsBlockNummer = new Long(bibALL.get_SEQUENCE_VECT_VALUES("SEQ_BUCHUNGSBLOCK_FIBU", 1).get(0));
			this.oSearchAdress.get_oTextFieldForSearchInput().setText("");
			this.oSearchAdress.FillLabelWithDBQuery("");
			this.oSearchAdress.set_bEnabled_For_Edit(true);
			
			try
			{
				//2012-03-06: aenderung: waehrung wird nicht mehr mit dem mandanten-waehrungs-kuerzel besetzt, sondern mit dem symbol
				//this.cWaehrung = new RECORD_WAEHRUNG(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN("")).get_KURZBEZEICHNUNG_cUF()
				RECORD_WAEHRUNG  recWaehrMand =  new RECORD_WAEHRUNG(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN(""));
				this.cWaehrung = recWaehrMand.get_WAEHRUNGSSYMBOL_cUF_NN(recWaehrMand.get_KURZBEZEICHNUNG_cUF());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				throw new myException("Mandant has no Setting in Field WAEHRUNG !!!");
			}
		}
		
		this.refresh_SaldoBuchungsBlock();
		
		this.recListFibu = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE BUCHUNGSBLOCK_NR="+this.iBuchungsBlockNummer+" AND NVL(STORNIERT,'N')='N'  ORDER BY ID_FIBU");
		
		//jetzt fuer jeden aus dem buchungsblock eine Buchungszeile bauen, plus eine zusaetzliche
		for (int i=0;i<this.recListFibu.get_vKeyValues().size();i++)
		{
			vBuchungsBlock.add(new FIBU__BUCHUNGS_BLOCK(this, this.recListFibu.get(i),this.bScheckDruck));
			vBuchungsBlock.get(vBuchungsBlock.size()-1).set_enabled(false);
		}
		
		//jetzt noch einen neuen buchungsblock dazu (leer, editierbar) (nur wenn noch was offen ist oder die buchung ist leer/ohne grundbeleg)
		if (this.bdSaldoBuchungBlock.compareTo(BigDecimal.ZERO)!=0 || this.recFibuBasis==null) 
		{
			if (this.bScheckDruck)   //hier sogar nur noch eine leere (Neubuchungszeile) einfuegen, wenn der saldo negativ ist, sonst ist scheckdruck ohne sinn
			{
				if (this.bdSaldoBuchungBlock.compareTo(BigDecimal.ZERO)<0 || this.recFibuBasis==null)
				{
					vBuchungsBlock.add(new FIBU__BUCHUNGS_BLOCK(this, null,this.bScheckDruck));
					vBuchungsBlock.get(vBuchungsBlock.size()-1).set_enabled(true);
				}
			}
			else
			{
				vBuchungsBlock.add(new FIBU__BUCHUNGS_BLOCK(this, null,this.bScheckDruck));
				vBuchungsBlock.get(vBuchungsBlock.size()-1).set_enabled(true);
			}
		}
	}
	
	
	
	/*
	 * verteilen der anteiligen zahlungseingaenge
	 */
	public void BerechneRestBetraegeBei_GELD_REIN_POSITIONEN() throws myException
	{
		//zuerst alle Buchungspositionen sammeln, die einen Zahlungseingang bewirken
		Vector<FIBU__BUCHUNGS_BLOCK>  vIchBekommeGeld = new Vector<FIBU__BUCHUNGS_BLOCK>();
		//zweiter Block fuer alle mit ueberschrittenem Skontodatum
		Vector<FIBU__BUCHUNGS_BLOCK>  vIchBekommeGeldSkonto = new Vector<FIBU__BUCHUNGS_BLOCK>();
		
		Vector<FIBU__BUCHUNGS_BLOCK>  vIchGegenbetraege = new Vector<FIBU__BUCHUNGS_BLOCK>();
		
		BigDecimal bdSummeGegenbetrag = new BigDecimal(0);
		
		for (int i=0;i<this.vBuchungsBlock.size();i++)
		{
			if (this.vBuchungsBlock.get(i).recFibu!=null)
			{
				RECORD_FIBU_ext oFibu = new RECORD_FIBU_ext(this.vBuchungsBlock.get(i).recFibu); 
				
				if (oFibu.get_bKannGemahntWerden())
				{
					vIchBekommeGeld.add(this.vBuchungsBlock.get(i));
					
					if (oFibu.is_SKONTO_DATUM_UEBERSCHRITTEN_YES())
					{
						vIchBekommeGeldSkonto.add(this.vBuchungsBlock.get(i));
					}
				}
				else
				{
					//gegengerechnet werden nur positive zahlungseingaenge und gutschriften, negative rechnungen
					if (oFibu.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_ZAHLUNGSEINGANG) && 
						oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0)
					{
						vIchGegenbetraege.add(this.vBuchungsBlock.get(i));
						bdSummeGegenbetrag=bdSummeGegenbetrag.add(oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO));
					}
					
					if (oFibu.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT) && 
						oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0)
					{
						vIchGegenbetraege.add(this.vBuchungsBlock.get(i));
						bdSummeGegenbetrag=bdSummeGegenbetrag.add(oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO));
					}
					
					if (oFibu.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG) && 
						oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)<0)
					{
						vIchGegenbetraege.add(this.vBuchungsBlock.get(i));
						bdSummeGegenbetrag=bdSummeGegenbetrag.add(oFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(new BigDecimal(-1)));
					}
					
					
				}
			}
		}

		
		//jetzt die vIchBekommeGeld sortieren nach Faelligkeit
		Collections.sort(vIchBekommeGeld, new Comparator<FIBU__BUCHUNGS_BLOCK>() 
		{
			@Override
			public int compare(FIBU__BUCHUNGS_BLOCK o1, FIBU__BUCHUNGS_BLOCK o2) 
			{
				
				try 
				{
					String c1=o1.recFibu.get_ZAHLUNGSZIEL_VALUE_FOR_SQLSTATEMENT()+ S.makeStringLonger(o1.recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN("0.00"),"0",20,true);
					String c2=o2.recFibu.get_ZAHLUNGSZIEL_VALUE_FOR_SQLSTATEMENT()+ S.makeStringLonger(o2.recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN("0.00"),"0",20,true);
					return c1.compareTo(c2);
				} 
				catch (myException e) 
				{
					e.printStackTrace();
				}
				return 0;
			}
		});
		

		//jetzt die vIchBekommeGeld sortieren nach Faelligkeit
		Collections.sort(vIchBekommeGeldSkonto, new Comparator<FIBU__BUCHUNGS_BLOCK>() 
		{
			@Override
			public int compare(FIBU__BUCHUNGS_BLOCK o1, FIBU__BUCHUNGS_BLOCK o2) 
			{
				
				try 
				{
					String c1=o1.recFibu.get_ZAHLUNGSZIEL_VALUE_FOR_SQLSTATEMENT()+ S.makeStringLonger(o1.recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN("0.00"),"0",20,true);
					String c2=o2.recFibu.get_ZAHLUNGSZIEL_VALUE_FOR_SQLSTATEMENT()+ S.makeStringLonger(o2.recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_cUF_NN("0.00"),"0",20,true);
					return c1.compareTo(c2);
				} 
				catch (myException e) 
				{
					e.printStackTrace();
				}
				return 0;
			}
		});
		

		
		
		Vector<String> vSQL = new  Vector<String>();
		
		//beim ersten durchlauf wird alles verteilt, ausser den skontobetraegen
		for (int i=0;i<vIchBekommeGeld.size();i++)
		{
			BigDecimal  bdPositionsBetrag = null;
			BigDecimal  bdSkontoBetrag = null;
			
			if (vIchBekommeGeld.get(i).recFibu.is_SKONTO_DATUM_UEBERSCHRITTEN_YES())
			{
				bdPositionsBetrag	=	vIchBekommeGeld.get(i).recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(
										vIchBekommeGeld.get(i).recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ONE),MathContext.DECIMAL64);
				
				bdSkontoBetrag	=		vIchBekommeGeld.get(i).recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(
										vIchBekommeGeld.get(i).recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ONE),MathContext.DECIMAL64);
			}
			else
			{
				bdPositionsBetrag=		vIchBekommeGeld.get(i).recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(
										vIchBekommeGeld.get(i).recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ONE),MathContext.DECIMAL64);
				bdSkontoBetrag = 		new BigDecimal(0);
			}
				
			
			RECORD_FIBU oFibu = new RECORD_FIBU(vIchBekommeGeld.get(i).recFibu.get_ID_FIBU_cUF());
			if (bdSummeGegenbetrag.compareTo(bdPositionsBetrag)>=0)
			{
				oFibu.set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(MyNumberFormater.formatDez(bdSkontoBetrag, 2, true));   //hier bleibt der skontobetrag stehen
				vSQL.add(oFibu.get_SQL_UPDATE_STATEMENT(null, true));
				
				bdSummeGegenbetrag = bdSummeGegenbetrag.subtract(bdPositionsBetrag);
			}
			else
			{
				//letzte verbuchung
				BigDecimal bdRestbetrag = bdPositionsBetrag.add(bdSkontoBetrag).subtract(bdSummeGegenbetrag);
				oFibu.set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(MyNumberFormater.formatDez(bdRestbetrag,2,true));
				vSQL.add(oFibu.get_SQL_UPDATE_STATEMENT(null, true));
				
				bdSummeGegenbetrag=new BigDecimal(0);
				break;                                  //alles verteilt
			}
		}
		if (vSQL.size()>0)
		{
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
		}

		
		//2. verteilungslauf: wenn noch was zu verteilen ist, dann auf die skonto-betraege verteilen
		if (bibMSG.get_bIsOK() && bdSummeGegenbetrag.compareTo(BigDecimal.ZERO)>0)
		{
			//beim ersten durchlauf wird alles verteilt, ausser den skontobetraegen
			for (int i=0;i<vIchBekommeGeldSkonto.size();i++)
			{
				BigDecimal  bdSkontoBetrag = null;
				
				bdSkontoBetrag	=		vIchBekommeGeld.get(i).recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(
										vIchBekommeGeld.get(i).recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ONE),MathContext.DECIMAL32);
					
				
				RECORD_FIBU oFibu = new RECORD_FIBU(vIchBekommeGeldSkonto.get(i).recFibu.get_ID_FIBU_cUF());
				if (bdSummeGegenbetrag.compareTo(bdSkontoBetrag)>=0)
				{
					oFibu.set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG("0");      //hier ist der skontobetrag auch verbucht
					vSQL.add(oFibu.get_SQL_UPDATE_STATEMENT(null, true));
					
					bdSummeGegenbetrag = bdSummeGegenbetrag.subtract(bdSkontoBetrag);
				}
				else
				{
					//letzte verbuchung
					BigDecimal bdRestbetrag = bdSkontoBetrag.subtract(bdSummeGegenbetrag);

					oFibu.set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(MyNumberFormater.formatDez(bdRestbetrag,2,true));
					vSQL.add(oFibu.get_SQL_UPDATE_STATEMENT(null, true));
					
					bdSummeGegenbetrag=new BigDecimal(0);
					break;                                  //alles verteilt
				}
			}

			
			if (vSQL.size()>0)
			{
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			}
		}
	}
	
	
	
	public void refresh_SaldoBuchungsBlock() throws myException
	{
		
//		String cSQL_Saldo_Query = "SELECT SUM(NVL(JT_FIBU.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)*FAKTOR_BUCHUNG_PLUS_MINUS) AS SUMME FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(STORNIERT,'N')='N' AND BUCHUNGSBLOCK_NR="+this.iBuchungsBlockNummer;			
		
		String cSQL_Saldo_Query = " SELECT SUM( "+
								 " CASE WHEN NVL(SKONTO_DATUM_UEBERSCHRITTEN,'N')='Y' THEN "+
								 " (NVL(JT_FIBU.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)+NVL(JT_FIBU.SKONTOBETRAG_FREMD_WAEHRUNG,0))*FAKTOR_BUCHUNG_PLUS_MINUS "+
								 " ELSE "+
								 " (NVL(JT_FIBU.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)*FAKTOR_BUCHUNG_PLUS_MINUS) "+
								 " END "+
								 " ) "+
								 " AS SUMME FROM JT_FIBU where JT_FIBU.BUCHUNGSBLOCK_NR="+this.iBuchungsBlockNummer;
		
		this.bdSaldoBuchungBlock = new MyRECORD(cSQL_Saldo_Query).get_bdValue("SUMME", BigDecimal.ZERO, 2); 
	}
	
	
	public void rebuild_Buchungsblock_Mask() throws myException
	{
		MyE2_Grid  oGrid = new MyE2_Grid(8,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oColBasic.removeAll();
		this.oColBasic.add(oGrid,E2_INSETS.I_10_10_10_10);

		Insets oIn = new Insets(0,2,8,2);
		Insets oIn2 = new Insets(0,2,8,10);
		
		//hier werden die Infos der Adresse zum thema Buchhaltung mit eingeblendet
		MyE2_Grid oGridAdressePlusZusatz = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		oGridAdressePlusZusatz.add(this.oSearchAdress, E2_INSETS.I_0_0_0_1);
        
		DotFormatter dfID_ADRESSE = new DotFormatterGermanFixed(oSearchAdress.get_oTextFieldForSearchInput().getText(),true);
		if (dfID_ADRESSE.get_oLong()!=null)
		{
			RECORD_ADRESSE recAdresse = new RECORD_ADRESSE(dfID_ADRESSE.get_oLong());
			RECLIST_ADRESSE_INFO  recListAdressInfosFibu = recAdresse.get_DOWN_RECORD_LIST_ADRESSE_INFO_id_adresse("NVL(IST_MESSAGE,'N')='Y' AND MESSAGE_TYP="+bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_FIBU), null, true);
			if (recListAdressInfosFibu.get_vKeyValues().size()>0)
			{
				for (int i=0;i<recListAdressInfosFibu.get_vKeyValues().size();i++)
				{
					oGridAdressePlusZusatz.add(new MyE2_Label(recListAdressInfosFibu.get(i).get_TEXT_cUF_NN("<leer ??>"),new E2_FontPlain(),true).get_InBorderGrid(new Border(1,Color.BLACK,Border.STYLE_SOLID),new Extent(300),null),E2_INSETS.I_1_0_1_0);
				}
			}
		}
		
		
		
		oGrid.add(new E2_ComponentGroupHorizontal_NG(
												new MyE2_Label(new MyE2_String("Saldo:"),MyE2_Label.STYLE_TITEL_NORMAL()),
												new MyE2_Label(MyNumberFormater.formatDez(this.bdSaldoBuchungBlock, 2, true)+" "+this.cWaehrung,MyE2_Label.STYLE_TITEL_NORMAL()),
												new MyE2_Label(new MyE2_String("Block: ",true,""+this.iBuchungsBlockNummer,false)),
												oGridAdressePlusZusatz,
												new MyE2_Label(new MyE2_String("Scheck-Drucker:"),MyE2_Label.STYLE_TITEL_NORMAL()),
												this.oSelectDrucker,new Insets(0,0,10,0)),8,oIn2);
		
		
		//ueberschrift
		oGrid.add(new MyE2_Label(new MyE2_String("Datum")), 					MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("Info/Scheck-Nr/Verwendung (unten: Intern)")), MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("Betrag (skontiert)")), 		MyE2_Grid.LAYOUT_RIGHT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("Wäh.")), 						MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("Buchungsart")),				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("Korr")),						MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("")),							MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		oGrid.add(new MyE2_Label(new MyE2_String("")),							MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
		
		for (int i=0;i<this.vBuchungsBlock.size();i++)
		{
			oGrid.add(this.vBuchungsBlock.get(i).get_BuchungsdatumsFeld(),		MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			oGrid.add(this.vBuchungsBlock.get(i).get_InfoBlock(), 				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			
			
			//oGrid.add(this.vBuchungsBlock.get(i).dbf_ZAHLUNGSBETRAG_FREMD_WAEHRUNG, MyE2_Grid.LAYOUT_RIGHT_TOP(oIn));
			oGrid.add(this.vBuchungsBlock.get(i).get_Block_Zahlbetrag_RestBetrag(), MyE2_Grid.LAYOUT_RIGHT_TOP(oIn));
//			get_Block_Zahlbetrag_RestBetrag
			
			oGrid.add(this.vBuchungsBlock.get(i).dbf_WAEHRUNG, 					MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			oGrid.add(this.vBuchungsBlock.get(i).dfs_BUCHUNGSTYP, 				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			oGrid.add(this.vBuchungsBlock.get(i).dcb_KORREKTURBUCHUNG, 			MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			
			
			if (this.vBuchungsBlock.get(i).bEditable)
			{
				oGrid.add(this.vBuchungsBlock.get(i).oButtonNehmeSaldo, 	MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
				oGrid.add(this.vBuchungsBlock.get(i).oButtonSaveBuchung, 	MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
			}
			else
			{
				if (this.vBuchungsBlock.get(i).get_bScheckBuchung())     //der scheckdruck-button wird immer eingeblendet
				{
					oGrid.add(new MyE2_Label(new MyE2_String("")),				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
					oGrid.add(this.vBuchungsBlock.get(i).oButtonSaveBuchung, 	MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
				}
				else
				{
					oGrid.add(new MyE2_Label(new MyE2_String("")),				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
					oGrid.add(new MyE2_Label(new MyE2_String("")),				MyE2_Grid.LAYOUT_LEFT_TOP(oIn));
				}
			}
			
			
			//jetzt nachsehen, ob es eine Scheck-Zusatzzeile gibt
			MyE2_Grid oGridScheckZusatz = this.vBuchungsBlock.get(i).get_InfoAddonBlock();
			if (oGridScheckZusatz!=null)
			{
				oGrid.add(new MyE2_Label(""));
				oGrid.add(oGridScheckZusatz,MyE2_Grid.LAYOUT_LEFT_TOP(oIn, null, oGrid.getSize()-1));
			}
			
		}
		
		
		//anzeige der Buttons Belegdruck und Fenster schliessen
		GridLayoutData  oGL = LayoutDataFactory.get_GridLayoutGridRight_TOP(new Insets(0,10,0,0),8);
		oGrid.add(new E2_ComponentGroupHorizontal_NG(
				this.oButtonOeffneBuchungsblock, this.oButtonSchliesseBuchungsblock,
				this.oButtonPrintBuchungsblatt,
				this.oButtonSchliesseFenster, new Insets(0,10,8,0)),oGL);

		
		//jetzt entscheiden, ob einer der beiden oeffnen/schliessen-knoepfe aktiv ist
		this.oButtonOeffneBuchungsblock.setVisible(false);
		this.oButtonSchliesseBuchungsblock.setVisible(false);
		
		if (this.recFibuBasis!=null)       //bei neueingaben unsinnig
		{
			if (this.bdSaldoBuchungBlock.compareTo(BigDecimal.ZERO)==0)
			{
				if (new RECORD_FIBU(this.recFibuBasis.get_ID_FIBU_cUF()).is_BUCHUNG_GESCHLOSSEN_YES())
				{
					this.oButtonOeffneBuchungsblock.setVisible(true);
				}
				else
				{
					this.oButtonSchliesseBuchungsblock.setVisible(true);
				}
			}
		}
	}

	
	
	public void BAUE_ALLES_NEU_AUF() throws myException
	{
		this.rebuild_Buchungsblock_List_Vector();
		this.BerechneRestBetraegeBei_GELD_REIN_POSITIONEN();
		this.rebuild_Buchungsblock_List_Vector();
		this.rebuild_Buchungsblock_Mask();

		//navilist auch refreshen
		for (int i=0;i<this.vBuchungsBlock.size();i++)
		{
			if (this.vBuchungsBlock.get(i).recFibu!=null)
			{
				if (this.oNaviList.get_ComponentMAP(this.vBuchungsBlock.get(i).recFibu.get_ID_FIBU_cUF())!=null)
				{
					this.oNaviList.get_ComponentMAP(this.vBuchungsBlock.get(i).recFibu.get_ID_FIBU_cUF())._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
				}
			}
		}
		
	}

	
	
	
	private class actionOeffneSchliesseBuchungsBlock extends XX_ActionAgent
	{
		private String OeffneSchliesse = null;
		
		public actionOeffneSchliesseBuchungsBlock(String oeffneSchliesse) throws myException 
		{
			super();
			OeffneSchliesse = oeffneSchliesse;
			if (!(OeffneSchliesse.equals("Y") || OeffneSchliesse.equals("N")))
			{
				throw new myException(this,"only Y and N are allowed !!!");
			}
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU__BUCHUNGS_CONTAINER oThis = FIBU__BUCHUNGS_CONTAINER.this;
			
			//KORREKTUR-FIBU-BUG: 2015-02-13: mandantenbedingung einefuegt
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(
					"UPDATE "+bibE2.cTO()+".JT_FIBU SET "+RECORD_FIBU.FIELD__BUCHUNG_GESCHLOSSEN+"="+bibALL.MakeSql(this.OeffneSchliesse)+
						" WHERE  ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  "+RECORD_FIBU.FIELD__BUCHUNGSBLOCK_NR+"="+oThis.iBuchungsBlockNummer
			), true));
			
			oThis.BAUE_ALLES_NEU_AUF();
			
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Buchungsblock ",true,""+oThis.iBuchungsBlockNummer,false,
						(this.OeffneSchliesse.equals("Y")?" wurde geschlossen !":" wurde geöffnet !"),true)));
			}
			
		}
	}
	
	
	
	
	
	public RECORD_FIBU get_recFibuBasis()
	{
		return recFibuBasis;
	}
	
	
	public void set_recFibuBasis(RECORD_FIBU rec_FibuBasis)
	{
		this.recFibuBasis=rec_FibuBasis;
	}
	
	

	public String get_cWaehrung()
	{
		return cWaehrung;
	}

	public BigDecimal get_bdSaldoBuchungBlock()
	{
		return bdSaldoBuchungBlock;
	}

	public UB_MaskSearchField_SEARCH_Adress get_oSearchAdress()
	{
		return oSearchAdress;
	}

	public MyE2_SelectField get_oSelectDrucker()
	{
		return oSelectDrucker;
	}

	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}
	
	public long get_iBuchungsBlockNummer()
	{
		return iBuchungsBlockNummer;
	}

	public Vector<FIBU__BUCHUNGS_BLOCK> get_vBuchungsBlock()
	{
		return vBuchungsBlock;
	}

	
	
	public void Drucke_Buchungsblatt() throws myException
	{
//		ownJasperHash oJasperHash = new ownJasperHash();
//		oJasperHash.Build_tempFile(true);
//		oJasperHash.get_oTempFileWithSendeName().Download_File();
		
		this.o_JasperHash = new ownJasperHash();
		this.o_JasperHash.Build_tempFile(true);
		this.o_JasperHash.get_oTempFileWithSendeName().Download_File();
		
		
	}
	
	private class ownJasperHash extends E2_JasperHASH_STD
	{
		public ownJasperHash()	throws myException 
		{
			super("BUCHUNGS_BLATT", new JasperFileDef_PDF());
			
			if (FIBU__BUCHUNGS_CONTAINER.this.iBuchungsBlockNummer<=0)
			{
				throw new myException("Error  printing: BUCHUNGSBLOCK is undefined !!");
			}
			
			this.put("buchungsblock_nr", ""+FIBU__BUCHUNGS_CONTAINER.this.iBuchungsBlockNummer);
		}
	}

	
	
	
	
	
}
