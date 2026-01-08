package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.components.E2_AbstandsHalter;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.SortGrid.E2_SortButtonInList;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGridListenZeile;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid_USERSETTING_LastSort;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.E2_Date_Selection_Von_Bis_TF;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EAK_CODE_ext;

public class INFO_BLOCK_SORTEN_NG extends E2_SortGrid
{

	private static String        	    SAVE_SORT_INFOBLOCK_SORTEN = "SAVE_SORT_INFOBLOCK_SORTEN";
	private static String[]             arraySpaltenTitel = {"ANR1-ANR2","Sortenbezeichnung","Sortenbezeichnung 2","AVV-Code","Angeb.","Bemerkung","Menge","-"};
	private static int[]                arraySpaltenBreite = {30,100,100,40,20,100,30,10};
	private static int[]		        arrAusrichtung_left_center_right_012 = {0,0,0,1,1,0,2,1};
	
	private static Integer  			iAnzahlSpalten = 8; 
	private FS__Adress_Info_Zentrum_NG   	oZentrum = null;

	private MyE2_ContainerEx  			oContainerEx = new MyE2_ContainerEx();
	
	private Vector<ownARTIKEL_BEZ>      vArtikelBez = new Vector<INFO_BLOCK_SORTEN_NG.ownARTIKEL_BEZ>();

	private String                      cEK_VK = "";
	
	private MyE2_TextField    			oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       			oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true, new MyE2_String("Suche starten"),null); 

	//TODO:neue komponent
	private ownZeitraumSelectionField	oDateBetrachtungVonBis = new ownZeitraumSelectionField();
	
	
	
	public INFO_BLOCK_SORTEN_NG(FS__Adress_Info_Zentrum_NG oInfoZentrum, boolean bAufbau, String EK_VK) throws myException 
	{
		super(INFO_BLOCK_SORTEN_NG.arraySpaltenBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		this.oBT_Refresh.add_oActionAgent(new actionNeueAnzeigeDerListe());
		this.oBT_Refresh.add_oActionAgent(new actionSaveStatus_Suche_und_datum());
		
		this.cEK_VK = EK_VK;
		
		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));

		oZentrum = oInfoZentrum;
		
		this.restore_einstellungen();
		
		if (bAufbau)
		{
			this.__query_und_baue_auf();
		}
	}
	
	public void __query_und_baue_auf() throws myException
	{
		this.removeAll();        						//das grid loeschen
		this.vArtikelBez.removeAllElements();   		//den vector mit artikelbez loeschen
		this.RESET_LISTE();                             //die darauf aufbauenden komponenten loeschen und zum neuaufbau zwingen
		
		
		RECORD_ADRESSE recADRESSE = this.oZentrum.get_recADRESSE();
		
		RECLIST_ARTIKELBEZ_LIEF recListLiefEK = recADRESSE.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(RECORD_ARTIKELBEZ_LIEF.FIELD__ARTBEZ_TYP+"='"+this.cEK_VK+"'",null,true);
		
		for (int i=0;i<recListLiefEK.get_vKeyValues().size();i++)
		{
			this.vArtikelBez.add(new ownARTIKEL_BEZ(recListLiefEK.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez(), recListLiefEK.get(i)));
		}
		
		this.SortListe(this.get_iLastSortedColumn(),this.get_cLastSortStatus().equals(E2_SortGrid.SORTED_UP));
		this.BaueListeAuf();
		
		if(vArtikelBez.size() == 0){
			this.add(new RB_lab("KEINE DATEN")._fsa(15)._fo_bold()._col_fore_lgrey(), 
					new RB_gld()._span(iAnzahlSpalten)._col(new E2_ColorBase())._center_mid()._ins(10, 20, 10, 20));
		}
		
	}
	
	

	
	
	private void restore_einstellungen() throws myException
	{
		//standard-einstellungen der liste
		this.set_iLastSortedColumn(0);
		this.set_cLastSortStatus(E2_SortGrid.SORTED_UP);
		
		String[] cSortStatus= new E2_SortGrid_USERSETTING_LastSort(null,this).get_Status_aus_Database(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER);
		if (cSortStatus!=null && S.isFull(cSortStatus[0]) && S.isFull(cSortStatus[1]) && bibALL.isLong(cSortStatus[0]))
		{
			this.set_iLastSortedColumn(new Integer(cSortStatus[0]));
			this.set_cLastSortStatus(cSortStatus[1]);
		}
		
		this.restore_status_Suche_und_Datum();
		
	}

	
	//gibt ein zweizeiliges grid zurueck mit bedienzeile und eigentlicher liste in containerEx
	public MyE2_Grid get_ContainerGridMitBedienZeile() throws myException
	{
		MyE2_Grid gridRueck = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oContainerEx.removeAll();
		this.oContainerEx.add(this);
		
		gridRueck.add(new E2_ComponentGroupHorizontal(	0,
														new MyE2_Label(new MyE2_String("Suche: ")),
														this.oTF_Suche, 
														this.oBT_Refresh,  
														new E2_AbstandsHalter(40),
														new MyE2_Label(new MyE2_String("Betrachtungszeitraum Mengen: ")),
														this.oDateBetrachtungVonBis,
//														this.oDateBetrachtungBis,
														E2_INSETS.I_0_0_5_0) ,
						E2_INSETS.I_0_0_0_2);
		gridRueck.add(oContainerEx,E2_INSETS.I_0_0_0_0);
		
		return gridRueck;
	}

	
	
	@Override
	public Integer get_iSpaltenZahl() throws myException
	{
		return INFO_BLOCK_SORTEN_NG.iAnzahlSpalten;
	}

	@Override
	public Component get_TitelComponent(int iSpalte) throws myException
	{
		E2_SortButtonInList oSortButton = new E2_SortButtonInList(
												iSpalte, 
												this, 
												new MyE2_String(INFO_BLOCK_SORTEN_NG.arraySpaltenTitel[iSpalte]), 
												null, 
												null, 
												E2_SortButtonInList.SORT_BUTTON_STYLE_SMALL(),
												FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER);
		
		if (INFO_BLOCK_SORTEN_NG.arraySpaltenTitel[iSpalte].length()<3)         //die sonder-buttons sind nicht sortierbar
		{
			oSortButton.set_bSortable(false);   
		}
		
		if (this.get_iLastSortedColumn()!=null && S.isFull(this.get_cLastSortStatus()))
		{
			if (this.get_iLastSortedColumn()==iSpalte)
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
		
		oSortButton.setLayoutData(new FSI___GridLayoutList_NG(INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[iSpalte],new E2_ColorDDDark()));
		
		return oSortButton;
	}

	
	
	@Override
	public Vector<E2_SortGridListenZeile> build_VectorWithComponentArrays() throws myException
	{
		Vector<E2_SortGridListenZeile> vComponentsVector = new Vector<E2_SortGridListenZeile>();
		
		for (int i=0;i<this.vArtikelBez.size();i++)
		{
			vComponentsVector.add(new ownListenZeile(this.vArtikelBez.get(i)));
		}
		
		return vComponentsVector;
	}

	@Override
	public String get_SaveKeyForUserSettings()
	{
		return INFO_BLOCK_SORTEN_NG.SAVE_SORT_INFOBLOCK_SORTEN+this.cEK_VK;
	}
	
	
	
	private class ownListenZeile extends E2_SortGridListenZeile
	{
		private ownARTIKEL_BEZ 	REC_ART_BEZ = null;
		private Component[]     ownArrayComp = null;

		public ownListenZeile(ownARTIKEL_BEZ rEC_ART_BEZ)
		{
			super();
			this.REC_ART_BEZ = rEC_ART_BEZ;
		}

		@Override
		public Component[] get_KomponentenZeile() throws myException
		{
			INFO_BLOCK_SORTEN_NG oThis = INFO_BLOCK_SORTEN_NG.this;
			
			Component[] arrComp = new Component[INFO_BLOCK_SORTEN_NG.iAnzahlSpalten];
			
			arrComp[0]=new FSI_Label_NG(this.REC_ART_BEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+this.REC_ART_BEZ.get_ANR2_cUF_NN("<anr2>"),
														INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[0]);
			arrComp[1]=new FSI_Label_NG(this.REC_ART_BEZ.get_ARTBEZ1_cUF_NN("<artbez1>"),INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[1]);
			arrComp[2]=new FSI_Label_NG(this.REC_ART_BEZ.ARTBEZLIEF.get_ARTBEZ2_ALTERNATIV_cF_NN("<artbez2_alternativ>"),INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[2]);
			
			RECORD_EAK_CODE_ext recEAK = null;
			if (this.REC_ART_BEZ.ARTBEZLIEF.get_UP_RECORD_EAK_CODE_id_eak_code()!=null)
			{
				recEAK = new RECORD_EAK_CODE_ext(this.REC_ART_BEZ.ARTBEZLIEF.get_UP_RECORD_EAK_CODE_id_eak_code());
			}
			arrComp[3]=new FSI_Label_NG(recEAK==null?"<eak-code>":recEAK.get_AVV_Code_Gesamt(),INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[3]);

			arrComp[4]=new FSI_Label_NG(this.REC_ART_BEZ.ARTBEZLIEF.is_ANGEBOT_YES()?"X":"",INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[4]);
			arrComp[5]=new FSI_Label_NG(this.REC_ART_BEZ.get_BEMERKUNG_INTERN_cUF_NN("<bemerkung>"),INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[5]);
			
			arrComp[6]=new FSI_Label_NG(MyNumberFormater.formatDez(this.REC_ART_BEZ.bdMengenSumme,0,true),INFO_BLOCK_SORTEN_NG.arrAusrichtung_left_center_right_012[6]);
			((FSI_Label_NG)arrComp[6]).setToolTipText(new MyE2_String("Abgerechnete Menge dieser Sorten-Bezeichnung im angegebenen Betrachtungszeitraum").CTrans());
			
		
			arrComp[7] = new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(	
															this.REC_ART_BEZ.vID_VPOS_TPA_FUHRE, 
															oThis.oZentrum.get_oContainerToCloseAfterJump(),
															oThis.oZentrum.is_jump_is_active());
			if (this.REC_ART_BEZ.vID_VPOS_TPA_FUHRE.size()==0)
			{
				((FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG)arrComp[7]).set_bEnabled_For_Edit(false);
			}
				
			
			this.ownArrayComp = arrComp;
			
			new FSI_HighLighter_NG(oThis.oTF_Suche, this.ownArrayComp);
			
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
			
			// {"ANR1-ANR2","Sortenbezeichnung","Sortenbezeichnung 2","AVV-Code","Angeb.","Bemerkung","-","."};

			switch(iColumn)
			{
				case (0):
				{
					return this.REC_ART_BEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+this.REC_ART_BEZ.get_ANR2_cUF_NN("<anr2>");
				}
				case (1):
				{
					return this.REC_ART_BEZ.get_ARTBEZ1_cUF_NN("<artbez1>");
				}
				case (2):
				{
					return this.REC_ART_BEZ.ARTBEZLIEF.get_ARTBEZ2_ALTERNATIV_cF_NN("<artbez2_alternativ>");
				}
				case (3):
				{
					RECORD_EAK_CODE_ext recEAK = null;
					if (this.REC_ART_BEZ.ARTBEZLIEF.get_UP_RECORD_EAK_CODE_id_eak_code()!=null)
					{
						recEAK = new RECORD_EAK_CODE_ext(this.REC_ART_BEZ.ARTBEZLIEF.get_UP_RECORD_EAK_CODE_id_eak_code());
					}
					return recEAK==null?"<eak-code>":recEAK.get_AVV_Code_Gesamt();
				}
				case (4):
				{
					return this.REC_ART_BEZ.ARTBEZLIEF.is_ANGEBOT_YES()?"X":" ";
				}
				case (5):
				{
					return this.REC_ART_BEZ.get_BEMERKUNG_INTERN_cUF_NN("<bemerkung>");
				}
				case (6):
				{
					return this.REC_ART_BEZ.bdMengenSumme;
				}
				case (7):
				{
					return "";
				}
				case (8):
				{
					return "";
				}
				
			}
			return null;
		}
		
	}

	public MyE2_ContainerEx get_oContainerEx()
	{
		return oContainerEx;
	}
	
	
	private class actionNeueAnzeigeDerListe extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_SORTEN_NG.this.BaueListeAuf();
		}
	}

	private class actionKompletterNeuAufbau extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_SORTEN_NG.this.__query_und_baue_auf();	
		}
	}

	
	
	//ein user-setting-objekt
	private class E2_UserSettings_SAVE_SUCHFELD_UND_DATUMSBEREICH extends XXX_UserSetting 
	{

		private String cSessionHash = "SESSION_HASH_INFOBOX_SAVE_SUCHFELD_SORTE_@@@";
		
		public E2_UserSettings_SAVE_SUCHFELD_UND_DATUMSBEREICH() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash+INFO_BLOCK_SORTEN_NG.this.cEK_VK;
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
	
	private class actionSaveStatus_Suche_und_datum extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new E2_UserSettings_SAVE_SUCHFELD_UND_DATUMSBEREICH().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_SORTEN_NG.this.get_Status_Suche_und_Datum());
		}
	}
	
	public String get_Status_Suche_und_Datum() throws myException
	{
		String cRueck = "";
		cRueck += "|";
		cRueck += (S.NN(this.oTF_Suche.getText()));
		cRueck += "|";
		cRueck += (S.NN(this.oDateBetrachtungVonBis.get_von()));
		cRueck += "|";
		cRueck += (S.NN(this.oDateBetrachtungVonBis.get_bis()));
		
		return cRueck;
	}

	private void restore_status_Suche_und_Datum() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_SAVE_SUCHFELD_UND_DATUMSBEREICH().get_Settings(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER);
		
		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");

			String[] zeitraum = {"",""};
			
			if (vWerte.size()>0) this.oTF_Suche.setText(vWerte.get(0));
			if (vWerte.size()>1) zeitraum[0]=vWerte.get(1);
			if (vWerte.size()>2) zeitraum[1]=vWerte.get(2);
			
			if(S.isFull(zeitraum[0])&&S.isFull(zeitraum[1])){
				oDateBetrachtungVonBis.set_datum_range(zeitraum[0], zeitraum[1]);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class ownARTIKEL_BEZ extends RECORD_ARTIKEL_BEZ
	{
		public RECORD_ARTIKELBEZ_LIEF 	ARTBEZLIEF = null;
			
		public BigDecimal	            bdMengenSumme = BigDecimal.ZERO;
		
		public Vector<String> 			vID_VPOS_TPA_FUHRE = new Vector<String>();
		
		
		public ownARTIKEL_BEZ(RECORD_ARTIKEL_BEZ recordOrig,  RECORD_ARTIKELBEZ_LIEF artbezlief) throws myException
		{
			super(recordOrig);
			this.ARTBEZLIEF=artbezlief;
			this.bdMengenSumme = this.get_bdMengeEK_VK();
			
			this.vID_VPOS_TPA_FUHRE.addAll(this.get_vIDs_Fuhren());
		}
		
		
		
		/**
		 * 
		 * @return s  array von bigdecimals , zuerst EK-Menge netto, dann VK-Menge-Netto 
		 * @throws myException
		 */
		private BigDecimal   get_bdMengeEK_VK() throws myException
		{
			BigDecimal bdRueck=new BigDecimal(0);
			
			String cDatumStart = "01.01.1800";
			String cDatumEnde =  "31.12.2199";
			
			INFO_BLOCK_SORTEN_NG  oThis = INFO_BLOCK_SORTEN_NG.this;
 
			MyDate oDatumStart = new MyDate(oThis.oDateBetrachtungVonBis.get_von());//oThis.oDateBetrachtungVon.get_oDateFromTextField_even_when_not_OK();
			MyDate oDatumEnde  = new MyDate(oThis.oDateBetrachtungVonBis.get_bis());
			
			if (oDatumStart.get_bOK())
			{
				cDatumStart = oDatumStart.get_cDateStandardFormat();
			}
			if (oDatumEnde.get_bOK())
			{
				cDatumEnde = oDatumEnde.get_cDateStandardFormat();
			}
			
			 String cQuery = "SELECT NVL(SUM(NVL(VP.ANZAHL,0)-NVL(VP.ANZAHL_ABZUG_LAGER,0)),0) AS NETTOMENGE " +
			 					" FROM "+bibE2.cTO()+".JT_VPOS_RG VP LEFT OUTER JOIN JT_VKOPF_RG VK ON (VP.ID_VKOPF_RG=VK.ID_VKOPF_RG) " +
			 					" WHERE VP.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
			 					" AND   VP.ID_VPOS_RG_STORNO_NACHFOLGER IS NULL  " +
			 					" AND   VK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
			 					" AND   VK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL  " +
			 					" AND   NVL(VK.ID_ADRESSE,VP.ID_ADRESSE)="+this.ARTBEZLIEF.get_ID_ADRESSE_cUF()+ 
			 					" AND   TO_DATE('"+cDatumStart+"','DD.MM.YYYY')<= VP.AUSFUEHRUNGSDATUM" +
			 					" AND   TO_DATE('"+cDatumEnde+"','DD.MM.YYYY')>= VP.AUSFUEHRUNGSDATUM" +
			 					" AND   VP.ID_ARTIKEL_BEZ="+this.ARTBEZLIEF.get_ID_ARTIKEL_BEZ_cUF()+
			 					" AND   NVL(VP.DELETED,'N')='N'" +
			 					" AND   NVL(VK.DELETED,'N')='N'" +
			 					" AND   LAGER_VORZEICHEN="+(INFO_BLOCK_SORTEN_NG.this.cEK_VK.equals("EK")?"1":"-1");
			 					
			 
			 MyRECORD  	recWert = new MyRECORD(cQuery);
			 bdRueck=	recWert.get_bdValue("NETTOMENGE", BigDecimal.ZERO, 3);

			 return bdRueck; 
		}
		
		
		
		/**
		 * 
		 * @return s  array von bigdecimals , zuerst EK-Menge netto, dann VK-Menge-Netto 
		 * @throws myException
		 */
		private Vector<String>   get_vIDs_Fuhren() throws myException
		{
			VectorSingle  vRueck  = new VectorSingle();
			
			String cDatumStart = "01.01.1800";
			String cDatumEnde =  "31.12.2199";
			
			INFO_BLOCK_SORTEN_NG  oThis = INFO_BLOCK_SORTEN_NG.this;

			MyDate oDatumStart = new MyDate(oThis.oDateBetrachtungVonBis.get_von());
			MyDate oDatumEnde  = new MyDate(oThis.oDateBetrachtungVonBis.get_bis());
			
			if (oDatumStart.get_bOK())
			{
				cDatumStart = oDatumStart.get_cDateStandardFormat();
			}
			if (oDatumEnde.get_bOK())
			{
				cDatumEnde = oDatumEnde.get_cDateStandardFormat();
			}
			
			String cADRESSFELD=(INFO_BLOCK_SORTEN_NG.this.cEK_VK.equals("EK")?"FU.ID_ADRESSE_START":"FU.ID_ADRESSE_ZIEL");
			
			String cARTIKELBEZ_FELD = (INFO_BLOCK_SORTEN_NG.this.cEK_VK.equals("EK")?"FU.ID_ARTIKEL_BEZ_EK":"FU.ID_ARTIKEL_BEZ_VK");
			
			String cLADEDAT_FELD=		(INFO_BLOCK_SORTEN_NG.this.cEK_VK.equals("EK")?"NVL(FU.DATUM_AUFLADEN,FU.DATUM_ABHOLUNG)":"NVL(FU.DATUM_ABLADEN,FU.DATUM_ANLIEFERUNG)");
			String cLADEDAT_FELD_FUO=	(INFO_BLOCK_SORTEN_NG.this.cEK_VK.equals("EK")?"NVL(FUO.DATUM_LADE_ABLADE,FU2.DATUM_ABHOLUNG)":"NVL(FUO.DATUM_LADE_ABLADE,FU2.DATUM_ANLIEFERUNG)");
			
			
			String cQuery = "SELECT FU.ID_VPOS_TPA_FUHRE FROM " +bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU "+
			 					" WHERE " + cADRESSFELD +" = "+this.ARTBEZLIEF.get_ID_ADRESSE_cUF()+
			 					" AND   NVL(FU.IST_STORNIERT,'N')='N' " +
			 					" AND   NVL(FU.DELETED,'N')='N' " +
			 					" AND   TO_DATE('"+cDatumStart+"','DD.MM.YYYY')<= " +cLADEDAT_FELD +
			 					" AND   TO_DATE('"+cDatumEnde+"','DD.MM.YYYY')>= " + cLADEDAT_FELD +
			 					" AND   "+cARTIKELBEZ_FELD+"="+this.ARTBEZLIEF.get_ID_ARTIKEL_BEZ_cUF()+
			 				" UNION "+
			 				" SELECT FUO.ID_VPOS_TPA_FUHRE_ORT FROM " +bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO " +
			 						" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE FU2 ON (FUO.ID_VPOS_TPA_FUHRE=FU2.ID_VPOS_TPA_FUHRE)"+
			 				    " WHERE FUO.ID_ADRESSE = "+this.ARTBEZLIEF.get_ID_ADRESSE_cUF()+
			 				    " AND   NVL(FUO.DELETED,'N')='N' " +
			 					" AND   NVL(FU2.IST_STORNIERT,'N')='N' " +
			 					" AND   NVL(FU2.DELETED,'N')='N' " +
			 					" AND   TO_DATE('"+cDatumStart+"','DD.MM.YYYY')<= " +cLADEDAT_FELD_FUO +
			 					" AND   TO_DATE('"+cDatumEnde+"','DD.MM.YYYY')>= " + cLADEDAT_FELD_FUO +
			 					" AND   FUO.ID_ARTIKEL_BEZ="+this.ARTBEZLIEF.get_ID_ARTIKEL_BEZ_cUF()+
			 				    " AND   FUO.DEF_QUELLE_ZIEL="+bibALL.MakeSql(INFO_BLOCK_SORTEN_NG.this.cEK_VK);

			String[][] arrRueck = bibDB.EinzelAbfrageInArray(cQuery);
			
			if (arrRueck!=null && arrRueck.length>0)
			{
				vRueck.addAll(bibVECTOR.get_VectorFromArray(arrRueck));
			}

			 return vRueck; 
		}

		
		
	}
	
	private class ownZeitraumSelectionField extends E2_Date_Selection_Von_Bis_TF{

		public ownZeitraumSelectionField() throws myException {
			super();
		}

		@Override
		public void saveDatumRange() {
			try {
				INFO_BLOCK_SORTEN_NG.this.__query_und_baue_auf();
				new E2_UserSettings_SAVE_SUCHFELD_UND_DATUMSBEREICH().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_SORTEN_NG.this.get_Status_Suche_und_Datum());
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
}
