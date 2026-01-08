package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_SingularButton;
import panter.gmbh.Echo2.components.E2_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__Validator_Fuhre_hat_buchungs_positionen_ODER_ist_gloescht_ODER_ist_storniert;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_ActionAgent_UMBUCHEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__SortButtonInList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__SortPanel_Fahrplan;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_USER_FAHRER;
import echopointng.Separator;


public class FPU_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static 					Font FontTitle = new E2_FontBold(0);
	public static 					Font FontList = new E2_FontPlain(-2);

	private E2_DateBrowser 			oDateForFahrplan = new E2_DateBrowser();
	private MyE2_Button				oButtonRefresh_LKWs = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
	private MyE2_Button				oButtonRefresh_Fahrten = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));

	private MyE2_Row				oRowForLKWs 	= new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());
	private MyE2_Grid				oGridForLKWs	= new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER());
	private MyE2_Grid				oGridForLists	= new MyE2_Grid(13,MyE2_Grid.STYLE_GRID_NO_BORDER());
	
	private Vector<LKW_CheckBox>	vLKW_CheckBox = new Vector<LKW_CheckBox>(); 
	
	private FPU_SelectLKW_Typen		oSelLKWType = new FPU_SelectLKW_Typen(this);

	
	/*
	 * ein fuhrenmaskencontainer fuer die bearbeitung der fahrten aus der fahrplanuebersicht
	 */
	private FU_MASK_ModulContainer  oFU_MASK_CONTAINER = null;
	
	/*
	 * array fuer die definition der anhaenger
	 */
	private String[][]    	     	cAnhaenger = null;
	
	private RECLIST_USER_FAHRER 	recListFahrer = null;

	/*
	 * wird nur beim neuaufbau der liste neu gestartet
	 */
	private Vector<ownRECORD_VPOS_TPA_FUHRE>   vectorUnverplanteFuhren = null;
	
	
	public FPU_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FAHRPLANUEBERSICHT);
		
		this.oFU_MASK_CONTAINER = new FU_MASK_ModulContainer(null, null,true,true);
		this.oDateForFahrplan.get_oDatumsFeld().setText(bibALL.get_cDateNOW());

		// Nur aktive Maschinenanhänger anzeigen 
		this.cAnhaenger = bibDB.EinzelAbfrageInArray("SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
									bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
									" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
									" JT_MASCHINENTYP.IST_ANHAENGER='Y' AND NVL(JT_MASCHINEN.AKTIV,'N') = 'Y' ORDER BY KFZKENNZEICHEN", true);
		
		this.recListFahrer = new RECLIST_USER_FAHRER();
		
		
		if (this.cAnhaenger == null)
		{
			throw new myException(this,"Error Querying Anhaenger-List!");
		}
		
		
		this.oButtonRefresh_LKWs.add_oActionAgent(new XX_ActionAgent() 
		{
			public void executeAgentCode(ExecINFO execInfo) 
			{
				try
				{
					FPU_BasicModuleContainer.this.clearAllRebuild_LKW_Liste();
					FPU_BasicModuleContainer.this.vectorUnverplanteFuhren=null;
				}
				catch (myExceptionForUser ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		});
		
		
		this.oButtonRefresh_Fahrten.add_oActionAgent(new XX_ActionAgent() 
		{
			public void executeAgentCode(ExecINFO execInfo) 
			{
				try
				{
					FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();         // die fahrten-listen der lkws werden in den Checkbox-objecten ueber die klick-events aufgebaut
					FPU_BasicModuleContainer.this.vectorUnverplanteFuhren=null;
				}
				catch (myExceptionForUser ex)
				{
					if (execInfo.get_MyActionEvent().getSource() instanceof MyE2_Button)
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}

		});
		
		
		
		this.add(new E2_ComponentGroupHorizontal(0,this.oDateForFahrplan,this.oButtonRefresh_LKWs,E2_INSETS.I_0_2_20_2), E2_INSETS.I_10_2_2_10);
		
		this.add(this.oRowForLKWs, E2_INSETS.I_10_2_2_10);
		this.add(this.oGridForLists, E2_INSETS.I_10_2_2_10);
		
		this.oGridForLists.setVisible(false);
		
	}

	
	
	/**
	 * Aufbau der LKW-Auswahl nach dem Fahrplandatum
	 * @throws myException
	 */
	private void clearAllRebuild_LKW_Liste() throws myException
	{
		this.vLKW_CheckBox.removeAllElements();
		
		this.oSelLKWType.setSelectedIndex(0);
		this.oGridForLKWs.removeAll();
		this.oGridForLists.removeAll();
		this.oGridForLists.setVisible(false);
		
		String cDatumFP_YYYYMMDD = this.oDateForFahrplan.DO_EvaluateActualTextAndGiveBackFormatedText("yyyy-MM-dd");
		if(cDatumFP_YYYYMMDD == null)
			throw new myExceptionForUser(new MyE2_String("Bitte geben Sie ein korrektes Datum an !!!").CTrans());

		//alle lkws
		RECLIST_MASCHINEN  recLKWs = new RECLIST_MASCHINEN("SELECT JT_MASCHINEN.* FROM JT_MASCHINEN " +
															" INNER JOIN JT_MASCHINENTYP ON (JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP)" +
															" WHERE NVL(JT_MASCHINEN.AKTIV,'N')='Y' AND NVL(JT_MASCHINENTYP.IST_LKW,'N')='Y' ORDER BY JT_MASCHINEN.KFZKENNZEICHEN");
		

		if (recLKWs.size() == 0)
			throw new myException("Error NO LKW-MACHINES ");
		
		this.vLKW_CheckBox.removeAllElements();

		for (int i=0;i<recLKWs.get_vKeyValues().size();i++)
		{
			LKW_CheckBox oCB = new LKW_CheckBox(recLKWs.get(i),cDatumFP_YYYYMMDD);  //laedt auch gleichzeitig die fuhren zum datum/lkw und hakt an, wenn fuhren vorhanden sind   

			this.vLKW_CheckBox.add(oCB);
			this.oGridForLKWs.add(oCB,E2_INSETS.I_2_2_10_2);
		}
		
		this.oGridForLKWs.add(this.oButtonRefresh_Fahrten,E2_INSETS.I_10_2_2_2);
		
		if (this.vLKW_CheckBox.size()>0)
		{
			this.oRowForLKWs.add(this.oGridForLKWs, E2_INSETS.I_0_2_2_2);
			this.oRowForLKWs.add(this.oSelLKWType, E2_INSETS.I_10_2_2_2);
		}
		else
		{
			this.oRowForLKWs.removeAll();
		}
	}
	
	
	
	
	/**
	 * aufbau der listen der angekreuzten lkws
	 * @throws myException
	 */
	public void REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen() throws myException
	{
		this.oGridForLists.removeAll();
		
		String cAktuelles_datum_db_Format = this.oDateForFahrplan.DO_EvaluateActualTextAndGiveBackFormatedText("yyyy-MM-dd");
		if(cAktuelles_datum_db_Format == null)
			throw new myExceptionForUser(new MyE2_String("Bitte geben Sie ein korrektes Datum an !!!").CTrans());

		int iCountLKWs = 0;

		for (int i=0;i<this.vLKW_CheckBox.size();i++)
		{
			LKW_CheckBox oCB_LKW = this.vLKW_CheckBox.get(i);
			
			if (oCB_LKW.isSelected())
			{
				iCountLKWs++;
				
				//1. ueberschrift
				this.oGridForLists.add(new localLabel(new MyE2_String("Start"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("Ziel"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("Z"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("Container"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("Sorte"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("Tätigkeit"),true),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localButtonWithPopup_SetFahrer(new MyE2_String("Fahrer"),oCB_LKW),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localButtonWithPopup_SetAnhaenger(new MyE2_String("Anh."),oCB_LKW),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new localLabel(new MyE2_String("OK?"),true),this.GRID_LAYOUT_TITEL());
				//2015-05-28: upload-button
				this.oGridForLists.add(new localLabel(new MyE2_String("^"),true),this.GRID_LAYOUT_TITEL());

				this.oGridForLists.add(new ownButtonStarteSortFenster(cAktuelles_datum_db_Format,oCB_LKW),this.GRID_LAYOUT_TITEL());
				this.oGridForLists.add(new ownButton_HoleFahrtAusPool(oCB_LKW,cAktuelles_datum_db_Format),this.GRID_LAYOUT_TITEL());   //snoopy
				this.oGridForLists.add(new Button_To_Select_Fahrten_wg_Belegdruck(oCB_LKW),this.GRID_LAYOUT_TITEL());   //snoopy

				//2. ueberschrift (lkw)
				this.oGridForLists.add(new localLabel(oCB_LKW.get_recLKW().get_KFZKENNZEICHEN_cUF_NN(""),true),this.GRID_LAYOUT_TITEL_LKW());
	
				
				for (int k=0;k<oCB_LKW.get_vREC_Fuhren_ZuLKW().size();k++)
				{
					SPEC_RECORD_VPOS_TPA_FUHRE  recFuhre = oCB_LKW.get_vREC_Fuhren_ZuLKW().get(k);
					
						
					String cStart = recFuhre.get_L_NAME1_cUF_NN("")+" "+recFuhre.get_L_ORT_cUF_NN("");
					String cZiel = recFuhre.get_A_NAME1_cUF_NN("")+" "+recFuhre.get_A_ORT_cUF_NN("");
					
					String cSorte = new MyE2_String("--- Sorte unbekannt ---").CTrans();
					if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek() != null)
					{
						cSorte = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" - "+
									recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ANR2_cUF_NN("")+"   "+
									recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF_NN("");
					}
					else
					{
						if (recFuhre.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
						{
							cSorte = recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" "+recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("");
						}
					}
					cSorte = bibALL.get_LeftString(cSorte, 25);
					String cContainer = recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() == null?
													new MyE2_String("-- Container unbekannt --").CTrans():
													recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp().get_KURZBEZEICHNUNG_cUF_NN("");
							
					this.oGridForLists.add(new localLabel(cStart,false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(cZiel,false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(recFuhre.get_ANZAHL_CONTAINER_FP_cUF_NN("-"),false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(cContainer,false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(cSorte,false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(recFuhre.get_TAETIGKEIT_FP_cUF_NN(""),false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(recFuhre.get_FAHRER_FP_cUF_NN(""),false),E2_INSETS.I_0_2_20_2);
					this.oGridForLists.add(new localLabel(recFuhre.get_UP_RECORD_MASCHINEN_id_maschinen_anh_fp()!=null?
							recFuhre.get_UP_RECORD_MASCHINEN_id_maschinen_anh_fp().get_KFZKENNZEICHEN_cUF_NN("-"):"--",false),E2_INSETS.I_0_2_20_2);
					
					
					//hier den hinweis auf fuhre ok oder nicht
					ButtonOpenFuhre oButton = null;
					if (recFuhre.is_FUHRE_KOMPLETT_YES())
					{
						oButton = new ButtonOpenFuhre(oCB_LKW, E2_ResourceIcon.get_RI("lkw.png"),recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
						oButton.setToolTipText(new MyE2_String("Fuhre bearbeiten ...").CTrans());
					}
					else
					{
						oButton = new ButtonOpenFuhre(oCB_LKW, E2_ResourceIcon.get_RI("lkw_grau_durchgestrichen.png"),recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
						oButton.setToolTipText(new MyE2_String("Fuhre MUSS noch bearbeitet werden (ist noch nicht vollständig ausgefüllt worden !").CTrans());
					}
					this.oGridForLists.add(oButton,E2_INSETS.I_0_2_10_2);

					//2015-05-28: upload-button
					E2_ButtonUpDown_SingularButton up_but =new E2_ButtonUpDown_SingularButton(_DB.VPOS_TPA_FUHRE.substring(3), recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,true);
					up_but.setToolTipText(new MyE2_String("Uploaden von Anlagen zur betreffenden Fuhre ...").CTrans());
					up_but.add_GlobalAUTHValidator_AUTO("ZUSATZDATEIEN");
					this.oGridForLists.add(up_but,E2_INSETS.I_0_2_10_2);

					
					//sortbutton:
					FP__SortButtonInList oSortButton = new FP__SortButtonInList(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),
																				recFuhre.get_SORTIERUNG_FP_cUF_NN("0"));
					this.oGridForLists.add(oSortButton,E2_INSETS.I_0_2_10_2);
					oSortButton.add_oActionAgent(new ownActionAfterSort(oCB_LKW));

					//jetzt noch einen popup mit den befehlen umbuchen und zurueck in pool
					MyE2_PopUpMenue oPop = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup_small.png"),E2_ResourceIcon.get_RI("popup_small.png"),false);
					oPop.setToolTipText(new MyE2_String("Weitere Befehle ...").CTrans());
					oPop.addButton(new ownButtonUmbuchen(oCB_LKW, recFuhre), true);
					oPop.addButton(new ownButtonZurueckinPool(oCB_LKW, recFuhre), true);
					this.oGridForLists.add(oPop,E2_INSETS.I_0_2_10_2); 

					//schalter fuer druckkontrolle
					this.oGridForLists.add(recFuhre.get_CB_DruckeFahrt(),E2_INSETS.I_0_2_10_2); 				//snoopy
				}
				this.oGridForLists.add(new Separator(),13,E2_INSETS.I_0_2_10_2);         //snoopy
			}
			
		}
		
		this.oGridForLists.add(	new E2_ComponentGroupHorizontal(0,
								new FPU_BUTTON_PRINT_FAHRPLANUEBERSICHT(this),
								new FPU_BUTTON_PRINT_FAHRPLAN(this),
								new FPU_BUTTON_PRINT_MAIL_BELEG(this),E2_INSETS.I_0_2_10_2));   //SNOOPY
		
		if (iCountLKWs>0)
		{
			this.oGridForLists.setVisible(true);
		}
		else
		{
			throw new myExceptionForUser(new MyE2_String("Es sind keine LKWs markiert !").CTrans());
		}
	}

	
	
	private class ownButtonStarteSortFenster extends MyE2_Button
	{

		
		private String 			cDatum_YYYYMMDD = null;
		private LKW_CheckBox 	oCB_LKW = null;

		public ownButtonStarteSortFenster(String Datum_YYYYMMDD, LKW_CheckBox CB_LKW)
		{
			super(E2_ResourceIcon.get_RI("up_low.png"));
			this.cDatum_YYYYMMDD = Datum_YYYYMMDD;
			this.oCB_LKW = CB_LKW; 
			
			this.setToolTipText(new MyE2_String("Sortierung im Fahrplan verändern ").CTrans());
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new ownFP__SortPanel_Fahrplan(  ownButtonStarteSortFenster.this.oCB_LKW.get_recLKW().get_ID_MASCHINEN_cUF(),
													ownButtonStarteSortFenster.this.cDatum_YYYYMMDD);
				}
				
			});
		}

		
		private class ownFP__SortPanel_Fahrplan extends FP__SortPanel_Fahrplan
		{

			public ownFP__SortPanel_Fahrplan(String maschinen_LKW,	String date_in_YYYYMMDD) throws myException
			{
				super(maschinen_LKW, date_in_YYYYMMDD);
			}

			@Override
			public void do_action_after_Sorting(String cid_maschinen_lkw,String date_YYYYMMDD) throws myException
			{
				//jetzt den ziel-lkw suchen und auch aktualisieren
				FPU_BasicModuleContainer  oThis = FPU_BasicModuleContainer.this;
				String cID_Neu = bibALL.ReplaceTeilString(cid_maschinen_lkw, ".", "");
				
				for (LKW_CheckBox oCB:  oThis.vLKW_CheckBox)
				{
					if (oCB.get_recLKW().get_ID_MASCHINEN_cUF().equals(cID_Neu))
					{
						oCB.Rebuild_Fuhren_Zu_LKW();
						oCB.setSelected(true);
					}
				}
				
				FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            

			}
			
		}
		
		
	}
	
	
	
	
	
	
	private class ownActionAfterSort extends XX_ActionAgent
	{
		private LKW_CheckBox oCB_LKW = null;
		public ownActionAfterSort(LKW_CheckBox ocb_lkw)
		{
			super();
			oCB_LKW = ocb_lkw;
		}
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
			FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();
		}
	}
	
	
	
	
//	private void baue_recListFuhrenUnverbraucht() throws myException
//	{
//		this.recListFuhrenUnverbraucht = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
//																	" WHERE NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND " +
//																	"       NVL(IST_STORNIERT,'N')='N' AND " +
//																	"       NVL(DELETED,'N')='N' AND " +
//																	"       NVL(ID_MASCHINEN_LKW_FP,0)=0 AND " +
//																	"       DAT_FAHRPLAN_FP IS NULL " + 
//																	"   ORDER BY DAT_VORGEMERKT_FP,ID_ADRESSE_LAGER_START");
//	}
	

	
	// snoopy start
	private class Button_To_Select_Fahrten_wg_Belegdruck extends MyE2_Button
	{
		private LKW_CheckBox  CB_LKW = null;
		
		public Button_To_Select_Fahrten_wg_Belegdruck(LKW_CheckBox  oCB_LKW)
		{
			super(E2_ResourceIcon.get_RI("printer.png"));
			this.CB_LKW = oCB_LKW;
			
			try {
				this._ttt(S.ms("Die Auswahl der Fuhren zum Belegdruck invertieren ...").CTrans());
			} catch (myException e) {
				e.printStackTrace();
			}
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO execInfo)
				{
					Vector<SPEC_RECORD_VPOS_TPA_FUHRE> vRecLKW_Fuhre = Button_To_Select_Fahrten_wg_Belegdruck.this.CB_LKW.get_vREC_Fuhren_ZuLKW();
					
					for (int i=0;i<vRecLKW_Fuhre.size();i++)
					{
						vRecLKW_Fuhre.get(i).get_CB_DruckeFahrt().setSelected(!vRecLKW_Fuhre.get(i).get_CB_DruckeFahrt().isSelected());
					}
				}
			});
					
		}
	}
	
	
	
	
	
	
	
	private class localLabel extends MyE2_Label
	{
		public localLabel(Object cText, boolean bTitle) 
		{
			super(cText);
			if (bTitle)
				this.setFont(FPU_BasicModuleContainer.FontTitle);
			else
				this.setFont(FPU_BasicModuleContainer.FontList);
				
		}
	}
	
	
	/*
	 * popup-Menue um die Fahrer zu definieren
	 */
	private class localButtonWithPopup_SetFahrer extends MyE2_Row
	{
		private LKW_CheckBox oCB_LKW = null;      //in der checkbox sind alle infos, die noetig sind
		
		public localButtonWithPopup_SetFahrer(Object cText, LKW_CheckBox CB_LKW) throws myException
		{
			super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			this.oCB_LKW = CB_LKW;
			MyE2_Label oLabHelp = new MyE2_Label(cText);
			oLabHelp.setFont(FPU_BasicModuleContainer.FontTitle);
			MyE2_PopUpMenue oPop = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup_small.png"),E2_ResourceIcon.get_RI("popup_small.png"),false);
			oPop.setToolTipText(new MyE2_String("Fahrer festlegen").CTrans());
			oPop.set_ContainerExActive(true);
			oPop.get_oContainerEx().setWidth(new Extent(150));
			oPop.get_oContainerEx().setHeight(new Extent(200));

			
			this.add(oLabHelp,E2_INSETS.I_0_0_2_0);
			this.add(oPop,E2_INSETS.I_0_0_2_0);
			
			
			for (int i=0;i<FPU_BasicModuleContainer.this.recListFahrer.get_vKeyValues().size();i++)
			{
				oPop.addButton(new ownFahrer_Button(FPU_BasicModuleContainer.this.recListFahrer.get(i)),true);
			}
		}
		
		private class ownFahrer_Button extends MyE2_Button
		{
			public ownFahrer_Button(RECORD_USER recFahrer) throws myException
			{
				super(recFahrer.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo) 	throws myException
					{
						localButtonWithPopup_SetFahrer oThis = localButtonWithPopup_SetFahrer.this;
						ownFahrer_Button    			   ooThis = ownFahrer_Button.this;
						
						Vector<String> vSQL = new Vector<String>();
						
						for (RECORD_VPOS_TPA_FUHRE recFuhre: oThis.oCB_LKW.get_vREC_Fuhren_ZuLKW())
						{
							recFuhre.set_NEW_VALUE_FAHRER_FP(ooThis.getText());
							vSQL.add(recFuhre.get_SQL_UPDATE_STATEMENT(null, true));
						}
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
						localButtonWithPopup_SetFahrer.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
						FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            //neuaufbau
						
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Fahrer für diesen LKW wurde geändert !"));
						}
						
					}
				});
			}
		}
	}

	
	
	
	/*
	 * popup-Menue um die Fahrer zu definieren
	 */
	private class localButtonWithPopup_SetAnhaenger extends MyE2_Row
	{
		private LKW_CheckBox oCB_LKW = null;      //in der checkbox sind alle infos, die noetig sind
		
		public localButtonWithPopup_SetAnhaenger(Object cText, LKW_CheckBox CB_LKW) throws myException
		{
			super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			this.oCB_LKW = CB_LKW;
			MyE2_Label oLabHelp = new MyE2_Label(cText);
			oLabHelp.setFont(FPU_BasicModuleContainer.FontTitle);
			MyE2_PopUpMenue oPop = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup_small.png"),E2_ResourceIcon.get_RI("popup_small.png"),false);
			oPop.setToolTipText(new MyE2_String("Anhänger festlegen").CTrans());
			this.add(oLabHelp,E2_INSETS.I_0_0_2_0);
			this.add(oPop,E2_INSETS.I_0_0_2_0);
			
			oPop.set_ContainerExActive(true);
			oPop.get_oContainerEx().setWidth(new Extent(150));
			oPop.get_oContainerEx().setHeight(new Extent(200));
			
			
			for (int i=0;i<FPU_BasicModuleContainer.this.cAnhaenger.length;i++)
			{
				oPop.addButton(new ownAnhaenger_Button(FPU_BasicModuleContainer.this.cAnhaenger[i][0],FPU_BasicModuleContainer.this.cAnhaenger[i][1]),true);
			}
		}
		
		private class ownAnhaenger_Button extends MyE2_Button
		{
			public ownAnhaenger_Button(String cAnhaenger, String cID_MASCHINEN_ANHAENGER) throws myException
			{
				super(cAnhaenger);
				this.EXT().set_C_MERKMAL(cID_MASCHINEN_ANHAENGER);
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo) 	throws myException
					{
						localButtonWithPopup_SetAnhaenger oThis = localButtonWithPopup_SetAnhaenger.this;
						ownAnhaenger_Button    			  ooThis = ownAnhaenger_Button.this;
						
						Vector<String> vSQL = new Vector<String>();
						
						for (RECORD_VPOS_TPA_FUHRE recFuhre: oThis.oCB_LKW.get_vREC_Fuhren_ZuLKW())
						{
							recFuhre.set_NEW_VALUE_ID_MASCHINEN_ANH_FP(ooThis.EXT().get_C_MERKMAL());
							vSQL.add(recFuhre.get_SQL_UPDATE_STATEMENT(null, true));
						}
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
						
						localButtonWithPopup_SetAnhaenger.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
						FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            //neuaufbau
						
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Anhänger für diesen LKW wurde geändert !"));
						}
					}
				});
			}
		}
	}

	
	
	
	


	public Vector<LKW_CheckBox> get_vLKW_CheckBox() 
	{
		return vLKW_CheckBox;
	}

	public MyE2_Button get_oButtonRefreshLists() 
	{
		return oButtonRefresh_Fahrten;
	}
	
	
	//button um eine fahrt zurueck in den pool zu buchen und einen um eine fahrt in einen anderen fahrplan zu stecken
	private class ownButtonZurueckinPool extends MyE2_Button
	{
		private RECORD_VPOS_TPA_FUHRE  RecFuhre = null;
		private LKW_CheckBox   	oCB_LKW = null;
		
		
		public ownButtonZurueckinPool(LKW_CheckBox  CB_LKW, RECORD_VPOS_TPA_FUHRE recFuhre)
		{
			super(new MyE2_String("Fahrt zurueck in Pool"));
			this.RecFuhre = recFuhre;
			this.oCB_LKW = CB_LKW;
		
			this.add_GlobalAUTHValidator_AUTO("FAHRT_ZURUECK_IN_POOL");
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					ownButtonZurueckinPool.this.RecFuhre.set_NEW_VALUE_DAT_FAHRPLAN_FP(null);
					ownButtonZurueckinPool.this.RecFuhre.set_NEW_VALUE_ID_MASCHINEN_LKW_FP(null);
					MyE2_MessageVector oMV = ownButtonZurueckinPool.this.RecFuhre.UPDATE(DB_STATICS.get_AutomaticWrittenFields_STD(),true);
					
					if (oMV.get_bHasAlarms())
					{
						bibMSG.add_MESSAGE(oMV);
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Fahrt wurde zurueckgebucht !"));
					}
					ownButtonZurueckinPool.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
					FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            //neuaufbau
					
					//jetzt die Fuhre im pool-vector wieder als frei markieren
					if (FPU_BasicModuleContainer.this.vectorUnverplanteFuhren != null)
					{
						for (int i=0;i<FPU_BasicModuleContainer.this.vectorUnverplanteFuhren.size();i++)
						{
							if (FPU_BasicModuleContainer.this.vectorUnverplanteFuhren.get(i).get_ID_VPOS_TPA_FUHRE_cUF_NN("--").equals(
									ownButtonZurueckinPool.this.RecFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
							{
								FPU_BasicModuleContainer.this.vectorUnverplanteFuhren.get(i).set_bIstFrei(true);
							}
						}
					}
				}
			});
		}
	}
	
	
	
	//button um eine fahrt zurueck in den pool zu buchen und einen um eine fahrt in einen anderen fahrplan zu stecken
	private class ownButtonUmbuchen extends MyE2_Button
	{
		private LKW_CheckBox   	oCB_LKW = null;
		
		public ownButtonUmbuchen(LKW_CheckBox  CB_LKW,RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
		{
			super(new MyE2_String("Fahrt UMBUCHEN"));
			this.oCB_LKW = CB_LKW;

			this.add_GlobalAUTHValidator_AUTO("FAHRT_UMBUCHEN");
			
			this.add_oActionAgent(new ownFP_ActionAgentUmbuchen(null,recFuhre,null));
		}
		
		
		
		private class ownFP_ActionAgentUmbuchen extends FP_ActionAgent_UMBUCHEN
		{

			public ownFP_ActionAgentUmbuchen(E2_NavigationList naviList, RECORD_VPOS_TPA_FUHRE RecFuhre,	XX_ActionAgent ZusatzActionNachSave) throws myException
			{
				super(naviList, RecFuhre, ZusatzActionNachSave);
			}

			@Override
			public void aktion_nach_umbuchen(String cID_LKW_ZIEL)	throws myException
			{
				ownButtonUmbuchen.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
				
				//jetzt den ziel-lkw suchen und auch aktualisieren
				FPU_BasicModuleContainer  oThis = FPU_BasicModuleContainer.this;
				String cID_Neu = bibALL.ReplaceTeilString(cID_LKW_ZIEL, ".", "");
				
				for (LKW_CheckBox oCB:  oThis.vLKW_CheckBox)
				{
					if (oCB.get_recLKW().get_ID_MASCHINEN_cUF().equals(cID_Neu))
					{
						oCB.Rebuild_Fuhren_Zu_LKW();
						oCB.setSelected(true);
					}
				}
				
				FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            
				
			}
			
		}
		
	}
	

	
	
	/*
	 * button, um eine uebersicht alle unzugeordneten fahrten aufzubauen und fahrten aus dem pool in einen fahrplan zu holen
	 */
	private class ownButton_HoleFahrtAusPool extends MyE2_Button
	{
		private String 			cID_MASCHINEN_LKW = null;
		private String 			cFahrplanDat_DB_Format = null;
		private LKW_CheckBox   	oCB_LKW = null;

		public ownButton_HoleFahrtAusPool(LKW_CheckBox  CB_LKW,String fahrplanDat_DB_Format_yyyy_MM_dd) throws myException
		{
			super(E2_ResourceIcon.get_RI("zeileneng.png"));
			this.add_GlobalAUTHValidator_AUTO("FUHRE_AUS_POOL_ZUBUCHEN");
			this.oCB_LKW = CB_LKW;
			this.cID_MASCHINEN_LKW 	= 		this.oCB_LKW.get_recLKW().get_ID_MASCHINEN_cUF();
			this.cFahrplanDat_DB_Format = 	fahrplanDat_DB_Format_yyyy_MM_dd;
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					// new ownContainer_HoleFahrtAusPool(ownButton_HoleFahrtAusPool.this.cID_MASCHINEN_LKW,ownButton_HoleFahrtAusPool.this.cFahrplanDat_DB_Format);
					
					new FPU_PopupContainer_UnverplanteFuhren(	FPU_BasicModuleContainer.this, 
																ownButton_HoleFahrtAusPool.this.oCB_LKW , 
																ownButton_HoleFahrtAusPool.this.cID_MASCHINEN_LKW, 
																ownButton_HoleFahrtAusPool.this.cFahrplanDat_DB_Format);
					
				}
				
			});
			
		}
	}
	
	
	
		// --
		/*
		 * popup-container mit offenen fahrten zum laden in einen fahrplan
		 */
//		private class ownContainer_HoleFahrtAusPool extends E2_BasicModuleContainer
//		{
//			public ownContainer_HoleFahrtAusPool(String cid_maschinen_lkw,String fahrplanDat_DB_Format) throws myException
//			{ 
//				//zuerst die reclist der offenen fuhren aufbauen
//				if (FPU_BasicModuleContainer.this.recListFuhrenUnverbraucht == null)
//				{
//					FPU_BasicModuleContainer.this.baue_recListFuhrenUnverbraucht();
//				}
//				else
//				{
//					FPU_BasicModuleContainer.this.recListFuhrenUnverbraucht.REFRESH();
//				}
//
//				RECLIST_VPOS_TPA_FUHRE oRecListFuhren =  FPU_BasicModuleContainer.this.recListFuhrenUnverbraucht;
//				
//				
//				MyE2_Grid  oGridFuerFuhren = new MyE2_Grid(8,1);
//				
//				for (int i=0;i<oRecListFuhren.get_vKeyValues().size();i++)
//				{
//					RECORD_VPOS_TPA_FUHRE recFuhre = oRecListFuhren.get(i);
//					
//					String cStart = recFuhre.get_L_NAME1_cUF_NN("")+" "+recFuhre.get_L_ORT_cUF_NN("");
//					String cZiel = recFuhre.get_A_NAME1_cUF_NN("")+" "+recFuhre.get_A_ORT_cUF_NN("");
//					
//					String cSorte = new MyE2_String("--- Sorte unbekannt ---").CTrans();
//					if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek() != null)
//					{
//						cSorte = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" - "+
//									recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ANR2_cUF_NN("")+"   "+
//									recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF_NN("");
//					}
//					else
//					{
//						if (recFuhre.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
//						{
//							cSorte = recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" "+recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("");
//						}
//					}
//					cSorte = bibALL.get_LeftString(cSorte, 25);
//					
//					String cTaetigkeit = recFuhre.get_TAETIGKEIT_FP_cUF_NN("");
//					String cContainer = recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() == null?
//													new MyE2_String("-- Container unbekannt --").CTrans():
//													recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp().get_KURZBEZEICHNUNG_cUF_NN("");
//
//					
//					Insets oIN = new Insets(2,1,7,1);
//					oGridFuerFuhren.add(new UebernahmeButton(cid_maschinen_lkw,fahrplanDat_DB_Format,recFuhre,this), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(cStart,false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String("--->",false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(cZiel,false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(cSorte,false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(cTaetigkeit,false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(cContainer,false)), oIN);
//					oGridFuerFuhren.add(new MyE2_Label(new MyE2_String(recFuhre.get_DAT_VORGEMERKT_FP_cF_NN(""),false)), oIN);
//				}
//				
//				this.add(oGridFuerFuhren, E2_INSETS.I_10_10_10_10);
//				
//				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Noch unverplante Fahrten ..."));
//			}
//			
//			
//			
//			/*
//			 * uebernahmebutton auf der popup-liste
//			 */
//			private class UebernahmeButton extends MyE2_Button
//			{
//				private String cID_MASCHINEN_LKW = null;
//				private String cFahrplanDat_DB_Format = null;
//				private RECORD_VPOS_TPA_FUHRE RecordFuhre = null;
//				private E2_BasicModuleContainer   oContainer = null;
//
//				public UebernahmeButton(String cid_maschinen_lkw,String fahrplanDat_DB_Format, RECORD_VPOS_TPA_FUHRE recFuhre,E2_BasicModuleContainer   Container )
//				{
//					super(E2_ResourceIcon.get_RI("zeileneng.png"));
//					this.setToolTipText(new MyE2_String("Fahrt in Fahrplan übernehmen !").CTrans());
//					
//					this.cID_MASCHINEN_LKW 	= 		cid_maschinen_lkw;
//					this.cFahrplanDat_DB_Format = 	fahrplanDat_DB_Format;
//					this.RecordFuhre = recFuhre;
//					this.oContainer = Container;
//					
//					this.add_oActionAgent(new XX_ActionAgent()
//					{
//						@Override
//						public void executeAgentCode(ExecINFO execInfo) throws myException
//						{
//							UebernahmeButton oThis = UebernahmeButton.this;
//							
//							RECLIST_VPOS_TPA_FUHRE reclistFuhren = new RECLIST_VPOS_TPA_FUHRE();
//							reclistFuhren.ADD(oThis.RecordFuhre, true);
//							
//							new FP__UMBUCHER(reclistFuhren,
//											cFahrplanDat_DB_Format.substring(8, 10)+"."+cFahrplanDat_DB_Format.substring(5, 7)+cFahrplanDat_DB_Format.substring(0, 4),
//											oThis.cID_MASCHINEN_LKW);
//
//							ownButton_HoleFahrtAusPool.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
//							FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();
//							oThis.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//						}
//					});
//					
//				}
//			}
//
//			
//		}
//	}
	
	
	
	
	
	
	private class ButtonOpenFuhre extends E2_BUTTON_OPEN_MASK_FromID
	{
		
		private LKW_CheckBox  oCB_LKW = null;
		
		public ButtonOpenFuhre(LKW_CheckBox  CB_LKW, E2_ResourceIcon oIcon, String cID_Fuhre_UF) throws myException
		{
			super(FPU_BasicModuleContainer.this.oFU_MASK_CONTAINER, new MyE2_String("Fuhrenbearbeitung"), FPU_BasicModuleContainer.this.get_MODUL_IDENTIFIER(),"EDIT_FUHRE", "VIEW_FUHRE");

			this.oCB_LKW = CB_LKW;
			
			this.setText("");
			this.setIcon(oIcon);
			this.EXT().set_C_MERKMAL(cID_Fuhre_UF);
			this.add_IDValidator(new FU__Validator_Fuhre_hat_buchungs_positionen_ODER_ist_gloescht_ODER_ist_storniert());
			
			this.get_vActionAgentsAfterSave().add(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					ButtonOpenFuhre.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
					FPU_BasicModuleContainer.this.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();            //neuaufbau
				}
			});
		}

		@Override
		public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row rowForButtons) throws myException
		{
		}

		@Override
		public void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row rowForButtons) throws myException
		{
		}
		
	}


//	public Vector<MyE2_CheckBox> get_vAVV_DRUCK_CheckBox()
//	{
//		return vBeleg_DRUCK_CheckBox;
//	}
//


	public E2_DateBrowser get_oDateForFahrplan()
	{
		return oDateForFahrplan;
	}
	
	
	private GridLayoutData GRID_LAYOUT_TITEL()
	{
		GridLayoutData oGLT = new GridLayoutData();
		oGLT.setInsets(E2_INSETS.I_0_2_20_2);
		oGLT.setBackground(new E2_ColorDDDark());
	
		return oGLT;
	}

	private GridLayoutData GRID_LAYOUT_TITEL_LKW()
	{
		GridLayoutData oGLTLKW = new GridLayoutData();
		oGLTLKW.setInsets(E2_INSETS.I_0_2_20_2);
		oGLTLKW.setBackground(new E2_ColorDDark());
		oGLTLKW.setColumnSpan(13);                             //snoopy
	
		return oGLTLKW;
	}



	public Vector<ownRECORD_VPOS_TPA_FUHRE> get_vectorUnverplanteFuhren()
	{
		return vectorUnverplanteFuhren;
	}



	public void set_vectorUnverplanteFuhren(RECLIST_VPOS_TPA_FUHRE rec_ListUnverplanteFuhren) throws myException
	{
		this.vectorUnverplanteFuhren = null;
		
		if (rec_ListUnverplanteFuhren!=null)
		{
			this.vectorUnverplanteFuhren = new Vector<ownRECORD_VPOS_TPA_FUHRE>();
			
			for (int i=0;i<rec_ListUnverplanteFuhren.get_vKeyValues().size();i++)
			{
				this.vectorUnverplanteFuhren.add(new ownRECORD_VPOS_TPA_FUHRE(rec_ListUnverplanteFuhren.get(i)));
			}
		}
		
		
	}

}
