package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.SortGrid.E2_SortButtonInList;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGridListenZeile;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid_USERSETTING_LastSort;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.E2_Date_Selection_Von_Bis_TF;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FSI_HighLighter;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FSI___GridLayoutList;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE;

public class INFO_BLOCK_PREISE_NG extends E2_SortGrid
{

	private static String        	    SAVE_SORT_INFOBLOCK_PREISE = "SAVE_SORT_INFOBLOCK_PREISE";
	private static String[]             arraySpaltenTitel = {"Typ","Buch-Nr.","Gültig","FU-Plan","FU-LadeM.","ANR1-2","Artbez1","Artbez2","Preis","-","-"};
	private static int[]		        arrAusrichtung_left_center_right_012 = {0,0,0,2,2,0,0,0,2,1,1};

	
	private ownZeitraumSelectionField	 oZeitraum_comp = new ownZeitraumSelectionField();
	
//	private MyE2_SelectField_month_year  o_1_SelField = null;
	
	private MyE2_TextField    			oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       			oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png")); 

	
	private BigDecimal 	 				BD0 = new BigDecimal(0);

	private MyE2_ContainerEx  			oContainerEx = new MyE2_ContainerEx();
	
	private Vector<Component> 			vSelcomponents = new Vector<Component>();

	private FS__Adress_Info_Zentrum_NG   	oZentrum = null;
	
	private String                      cEK_VK = "";

	private Vector<INFO_BLOCK_PREISE_NG.RECORD_VPOS_STD_own>  vPreise = new Vector<INFO_BLOCK_PREISE_NG.RECORD_VPOS_STD_own>();
	
	
	public INFO_BLOCK_PREISE_NG(FS__Adress_Info_Zentrum_NG oInfoZentrum, boolean bAufbau, String EK_VK) throws myException 
	{
		super(11, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		this.cEK_VK = EK_VK;
		
		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));

		oZentrum = oInfoZentrum;
		
		this.oBT_Refresh.add_oActionAgent(		new actionCheckHighlight());

		this.oZeitraum_comp._fsa(-2)._i();
		
		//dann status speichern
		this.oBT_Refresh.add_oActionAgent(		new actionSaveStatus());

		vSelcomponents.add(new MyE2_Label(new MyE2_String("Gueltig ab:"), new E2_FontItalic(-2)));
		vSelcomponents.add(this.oZeitraum_comp);
		
		vSelcomponents.add(this.oTF_Suche);
		vSelcomponents.add(this.oBT_Refresh);
		
		this.restore_einstellungen();
		
		
		if (bAufbau)
		{
			this.__aufbau();
		}
		
	}
	

	private void restore_einstellungen() throws myException
	{
		//standard-einstellungen der liste
		this.set_iLastSortedColumn(0);
		this.set_cLastSortStatus(E2_SortGrid.SORTED_UP);
		
		String[] cSortStatus= new E2_SortGrid_USERSETTING_LastSort(null,this).get_Status_aus_Database(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER);
		if (cSortStatus!=null && S.isFull(cSortStatus[0]) && S.isFull(cSortStatus[1]) && bibALL.isLong(cSortStatus[0]))
		{
			this.set_iLastSortedColumn(new Integer(cSortStatus[0]));
			this.set_cLastSortStatus(cSortStatus[1]);
		}
		
		this.restore_status_der_selektionen();
		
	}

	
	public void __aufbau() throws myException
	{
		this.removeAll();

		
		RECORD_ADRESSE recADRESSE = this.oZentrum.get_recADRESSE();

		
		String cQueryBase = "SELECT JT_VPOS_STD.*       FROM "+bibE2.cTO()+".JT_VPOS_STD  " +
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD) "+
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VKOPF_STD ON (JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD) "+
		 					" WHERE  NVL(JT_VPOS_STD.DELETED,'N')='N' AND NVL(JT_VKOPF_STD.DELETED,'N')='N' AND JT_VPOS_STD.EINZELPREIS_FW IS NOT NULL AND JT_VKOPF_STD.ID_ADRESSE="+recADRESSE.get_ID_ADRESSE_cUF();

		String cWhereAnteilSelDatumLieferant = "";
		String cWhereAnteilSelDatumAbnehmer ="";
		if(S.isEmpty(oZeitraum_comp.get_bis()) || S.isEmpty(oZeitraum_comp.get_von()) ){
			
		}
		
		cWhereAnteilSelDatumLieferant = 	S.isEmpty(this.oZeitraum_comp.get_von())?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM-DD'),'9999-12-31')>="+bibALL.MakeSql(this.oZeitraum_comp.get_von_SQLFORMAT());
		cWhereAnteilSelDatumLieferant = cWhereAnteilSelDatumLieferant + (S.isEmpty(this.oZeitraum_comp.get_bis().trim())?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'YYYY-MM-DD'),'9999-12-31')<="+bibALL.MakeSql(this.oZeitraum_comp.get_bis_SQLFORMAT()));
		
		 cWhereAnteilSelDatumAbnehmer = 	S.isEmpty(this.oZeitraum_comp.get_von())?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM-DD'),'9999-12-31')>="+bibALL.MakeSql(this.oZeitraum_comp.get_von_SQLFORMAT());
		cWhereAnteilSelDatumAbnehmer = cWhereAnteilSelDatumAbnehmer + (S.isEmpty(this.oZeitraum_comp.get_bis())?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'YYYY-MM-DD'),'9999-12-31')<="+bibALL.MakeSql(this.oZeitraum_comp.get_bis_SQLFORMAT()));
		
		String cQueryEK = cQueryBase+" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ABNAHMEANGEBOT)+	cWhereAnteilSelDatumLieferant+" ORDER BY JT_VPOS_STD_ANGEBOT.GUELTIG_VON,JT_VPOS_STD.ANR1";
		String cQueryVK = cQueryBase+" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ANGEBOT)+			cWhereAnteilSelDatumAbnehmer+" 	ORDER BY JT_VPOS_STD_ANGEBOT.GUELTIG_VON,JT_VPOS_STD.ANR1";
		

		//nur die noetigen records aufbauen
		RECLIST_VPOS_STD reclistVPOS_STD_EK = this.cEK_VK.equals("EK")?new RECLIST_VPOS_STD(cQueryEK):new RECLIST_VPOS_STD(cQueryVK);
		

		for (int i=0;i<reclistVPOS_STD_EK.get_vKeyValues().size();i++)
		{
			this.vPreise.add(new RECORD_VPOS_STD_own(reclistVPOS_STD_EK.get(i),reclistVPOS_STD_EK.get(i).get_ANR1_cUF_NN("-")+"-"+reclistVPOS_STD_EK.get(i).get_ANR2_cUF_NN("-"),true));
		}
		
		
		this.SortListe(this.get_iLastSortedColumn(),this.get_cLastSortStatus().equals(E2_SortGrid.SORTED_UP));
		this.BaueListeAuf();
		
		if(vPreise.size() == 0){
			this.add(new RB_lab("KEINE DATEN")._fsa(15)._fo_bold()._col_fore_lgrey(), 
					new RB_gld()._span(11)._col(new E2_ColorBase())._center_mid()._ins(10, 20, 10, 20));
		}
		

	}
	
	
	

	
	public String get_Status_der_Selektoren() throws myException
	{
		
		String cRueck = "";
		cRueck += S.NN(this.oZeitraum_comp.get_von());
		cRueck += "|";
		cRueck += (this.oTF_Suche.getText());
		cRueck += "|";
		cRueck += S.NN(this.oZeitraum_comp.get_bis());

		return cRueck;
	}

	
	
	private void restore_status_der_selektionen() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_Checkbox_und_Selektoren().get_Settings(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER);
		
		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");
			
			String[] zeitraum = {"",""};
			
			if (vWerte.size()>0) zeitraum[0]=vWerte.get(0);
			if (vWerte.size()>1) this.oTF_Suche.setText(vWerte.get(1));
			if (vWerte.size()>2) zeitraum[1]=vWerte.get(2);
			
			if(S.isFull(zeitraum[0])&&S.isFull(zeitraum[1])){
				oZeitraum_comp.set_datum_range(zeitraum[0], zeitraum[1]);
			}
		}
	}

	
	
	private E2_ComponentGroupHorizontal get_BedienzeileFuerFuhrenliste() 
	{
		return new E2_ComponentGroupHorizontal(0,vSelcomponents,new Insets(2,0,5,0));
	}
	
	//gibt ein zweizeiliges grid zurueck mit bedienzeile und eigentlicher liste in containerEx
	public MyE2_Grid get_ContainerGridMitBedienZeile() throws myException
	{
		MyE2_Grid gridRueck = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oContainerEx.removeAll();
		this.oContainerEx.add(this);
		
		gridRueck.add(this.get_BedienzeileFuerFuhrenliste());
		gridRueck.add(oContainerEx);
		
		return gridRueck;
	}

	
	
	//ein user-setting-objekt
	private class E2_UserSettings_Checkbox_und_Selektoren extends XXX_UserSetting 
	{

		private String cSessionHash = "SESSION_HASH_FIRMENSTAMM_JUMPBOX_SPEICHERE_SELECTOR_PREISE";
		
		public E2_UserSettings_Checkbox_und_Selektoren() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash+INFO_BLOCK_PREISE_NG.this.cEK_VK;
		}

		@Override
		protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
		{
			return (String)oSetting;
		}

		@Override
		protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
		{
			return cDatabaseSetting;
		}
		
		
	}
	
	private class actionSaveStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER, INFO_BLOCK_PREISE_NG.this.get_Status_der_Selektoren());
		}
	}


	
	public class actionNeubauPreiseGrid extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//2012-04-11: fehler beim aufbau nach selektion des monats
			INFO_BLOCK_PREISE_NG.this.RESET_LISTE();
			INFO_BLOCK_PREISE_NG.this.vPreise.removeAllElements();
			//2012-04-11
			
			INFO_BLOCK_PREISE_NG.this.__aufbau();
		}
	}


	
	private class RECORD_VPOS_STD_own extends RECORD_VPOS_STD
	{
		public String   					ANR1_2 = "";
		public Boolean   					bEK = false;
		
		private BigDecimal[]  				arrayBDMengePlanEcht = null;
		private BigDecimal    				bdMengeBerechnet = null;


		public RECORD_VPOS_STD_own(RECORD_VPOS_STD rec_vposSTD, String _ANR1_2, boolean EK) throws myException 
		{
			super(rec_vposSTD);
			this.ANR1_2 = _ANR1_2;
			this.bEK = EK;
		}
		
		/**
		 * 
		 * @return s Bigdecimal[2]: (EK:) plan oder lade / lade    (VK:) plan oder ablade / ablade
		 *           [0] = plan
		 *           [1] = lade
		 *           [2] = realer Abzug
		 * @throws myException
		 */
		public BigDecimal[]  get_MengeGeliefertPlanEcht() throws myException
		{
			
			if (this.arrayBDMengePlanEcht!=null)
			{
				return this.arrayBDMengePlanEcht;        //nur einmal berechnen
			}
			else
			{
			
				this.arrayBDMengePlanEcht = new BigDecimal[3];
				
				this.arrayBDMengePlanEcht[0] = new BigDecimal(0);  //plan
				this.arrayBDMengePlanEcht[1] = new BigDecimal(0);  //echt
				this.arrayBDMengePlanEcht[2] = new BigDecimal(0);  //abzug (nicht inhalt)
				
		
				if (this.bEK)
				{
					RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
					RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK'",null,true);
					
					Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
		
					while (iteratorFuhre.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
						if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(new BigDecimal(0)));
						}
					}
					
					while (iteratorFuhreOrt.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
						if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
						}
					}
				}
				else
				{
					RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
					RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK'",null,true);
					
					Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
		
					while (iteratorFuhre.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
						if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(new BigDecimal(0)));
						}
					}
					
					while (iteratorFuhreOrt.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
						if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
						}
					}
				}
				return this.arrayBDMengePlanEcht;
			}
		}
		

		


		
	}
	
	
	
	public class actionCheckHighlight extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_PREISE_NG.this.BaueListeAuf();
		}
	}



	public MyE2_ContainerEx get_oContainerEx()
	{
		return oContainerEx;
	}



	@Override
	public Integer get_iSpaltenZahl() throws myException
	{
		return INFO_BLOCK_PREISE_NG.arraySpaltenTitel.length;
	}



	@Override
	public Component get_TitelComponent(int iSpalte) throws myException
	{
		E2_SortButtonInList oSortButton = new E2_SortButtonInList(iSpalte, this, new MyE2_String(	INFO_BLOCK_PREISE_NG.arraySpaltenTitel[iSpalte]), 
																									null, 
																									null, 
																									E2_SortButtonInList.SORT_BUTTON_STYLE_SMALL(), 
																									FS__Adress_Info_Zentrum.STORE_MODUL_KENNER);
		

		if (INFO_BLOCK_PREISE_NG.arraySpaltenTitel[iSpalte].length() < 4)     // die sonder-buttons sind nicht sortierbar
		{
			oSortButton.set_bSortable(false);
		}

		if (this.get_iLastSortedColumn() != null && S.isFull(this.get_cLastSortStatus()))
		{
			if (this.get_iLastSortedColumn() == iSpalte)
			{
				if (this.get_cLastSortStatus().equals(E2_SortGrid.SORTED_UP))
				{
					oSortButton.set_StatusUP();
				}
				else
				{
					oSortButton.set_StatusDOWN();
				}
			}
		}

		oSortButton.setLayoutData(new FSI___GridLayoutList(INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[iSpalte], new E2_ColorDDDark()));

		return oSortButton;
	}


	@Override
	public Vector<E2_SortGridListenZeile> build_VectorWithComponentArrays() throws myException
	{
		Vector<E2_SortGridListenZeile> vComponentsVector = new Vector<E2_SortGridListenZeile>();
		
		for (int i=0;i<this.vPreise.size();i++)
		{
			vComponentsVector.add(new ownListenZeile(this.vPreise.get(i)));
		}
		
		return vComponentsVector;
	}



	@Override
	public String get_SaveKeyForUserSettings()
	{
		return INFO_BLOCK_PREISE_NG.SAVE_SORT_INFOBLOCK_PREISE+this.cEK_VK;
	}

	
	private class ownListenZeile extends E2_SortGridListenZeile
	{
		private RECORD_VPOS_STD_own 	REC_Preis = null;
		private Component[]     		ownArrayComp = null;

		public ownListenZeile(RECORD_VPOS_STD_own rEC_Preis)
		{
			super();
			this.REC_Preis = rEC_Preis;
		}

		@Override
		public Component[] get_KomponentenZeile() throws myException
		{
			INFO_BLOCK_PREISE_NG oThis = INFO_BLOCK_PREISE_NG.this;
			
			Component[] arrComp = new Component[INFO_BLOCK_PREISE_NG.this.arraySpaltenTitel.length];
			
			BigDecimal[] bdMengePlanEcht =  this.REC_Preis.get_MengeGeliefertPlanEcht();   //    recVKON.get_MengeGeliefertPlanEcht();

			
			String cBuchungsNummer = this.REC_Preis.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+this.REC_Preis.get_ID_VKOPF_STD_cF()+">");
			
			String cGueltig =this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(0,6);
			cGueltig +=this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(8);
			cGueltig += (" - "+this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(0,6));
			cGueltig +=this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(8);
			
			Vector<String> vID_Fuhren = new Vector<String>();
			RECLIST_VPOS_TPA_FUHRE  reclistFuhren = this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
			RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhrenOrt = this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N'",null,true);
			vID_Fuhren.addAll(reclistFuhren.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
			vID_Fuhren.addAll(reclistFuhrenOrt.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());

			
			arrComp[0]= new FSI_Label_NG(oThis.cEK_VK,INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[0]);
			arrComp[1]= new FSI_Label_NG(cBuchungsNummer,INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[1]);
			arrComp[2]= new FSI_Label_NG(cGueltig,INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[2],false);
			arrComp[3]= new FSI_Label_NG(MyNumberFormater.formatDez( bdMengePlanEcht[0],0,true),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[3]);
			arrComp[4]= new FSI_Label_NG(MyNumberFormater.formatDez( bdMengePlanEcht[1],0,true),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[4]);
			arrComp[5]= new FSI_Label_NG(this.REC_Preis.get_ANR1_cUF_NN("")+"-"+this.REC_Preis.get_ANR2_cUF_NN(""),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[5]);
			arrComp[6]= new FSI_Label_NG(this.REC_Preis.get_ARTBEZ1_cF_NN("<Artbez1>"),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[6]);
			arrComp[7]= new FSI_Label_NG(this.REC_Preis.get_ARTBEZ2_cF_NN("<Artbez2>"),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[7]);
			arrComp[8]= new FSI_Label_NG(MyNumberFormater.formatDez( this.REC_Preis.get_EINZELPREIS_FW_bdValue(BD0),2,true),INFO_BLOCK_PREISE_NG.arrAusrichtung_left_center_right_012[8]);
			arrComp[9]= new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT(	
																	bibALL.get_Vector(this.REC_Preis.get_ID_VKOPF_STD_cUF()), 
																	oThis.oZentrum.get_oContainerToCloseAfterJump(),
																	oThis.cEK_VK,
																	oThis.oZentrum.is_jump_is_active());
			arrComp[10]= new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(	
																	vID_Fuhren, 
																	oThis.oZentrum.get_oContainerToCloseAfterJump(),
																	oThis.oZentrum.is_jump_is_active());
			
			
			new FSI_HighLighter(oThis.oTF_Suche, arrComp);
			
			return arrComp;
		}

		@Override
		public boolean get_bZeileIstSichtbar() throws myException
		{
			return true;
		}


		@SuppressWarnings("rawtypes")
		@Override
		public Comparable get_SortableObject(int iColumn) throws myException
		{
			// {"Typ","Buch-Nr.","Gültig","FU-Plan","FU-LadeM.","ANR1-2","Artbez1","Artbez2","Preis","-","-"};
			
			switch(iColumn)
			{
				case (0):   //typ, nicht sortierbar
				{
					return "";
				}
				case (1):
				{
					return this.REC_Preis.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+this.REC_Preis.get_ID_VKOPF_STD_cF()+">");
				}
				case (2):
				{
					String cSort = bibALL.ReplaceTeilString(this.REC_Preis.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_VALUE_FOR_SQLSTATEMENT(),"'","");
					if (cSort.equals("NULL"))
					{
						cSort = "1900-01-01";
					}
					return cSort;
				}
				case (3):
				{
					return this.REC_Preis.get_MengeGeliefertPlanEcht()[0];
				}
				case (4):
				{
					return this.REC_Preis.get_MengeGeliefertPlanEcht()[1];
				}
				case (5):
				{
					return this.REC_Preis.get_ANR1_cUF_NN("")+"-"+this.REC_Preis.get_ANR2_cUF_NN("");
				}
				case (6):
				{
					return this.REC_Preis.get_ARTBEZ1_cF_NN("<Artbez1>");
				}
				case (7):
				{
					return this.REC_Preis.get_ARTBEZ2_cF_NN("<Artbez2>");
				}
				case (8):
				{
					return this.REC_Preis.get_EINZELPREIS_FW_bdValue(BD0);
				}
				case (9):
				{
					return "";
				}
				case (10):
				{
					return "";
				}
				
			}
			return null;
		}
		
	}

	private class ownZeitraumSelectionField extends E2_Date_Selection_Von_Bis_TF{

		public ownZeitraumSelectionField() throws myException {
			super();
		}

		@Override
		public void saveDatumRange() {
			try {
				INFO_BLOCK_PREISE_NG.this.RESET_LISTE();
				INFO_BLOCK_PREISE_NG.this.vPreise.removeAllElements();
				
				INFO_BLOCK_PREISE_NG.this.__aufbau();
				
				new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER, INFO_BLOCK_PREISE_NG.this.get_Status_der_Selektoren());
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}
		
	}


}
