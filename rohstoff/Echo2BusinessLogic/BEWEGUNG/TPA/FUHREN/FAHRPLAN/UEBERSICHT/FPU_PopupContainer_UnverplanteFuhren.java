package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.SortGrid.E2_SortButtonInList;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGridListenZeile;
import panter.gmbh.Echo2.components.SortGrid.E2_SortGrid_USERSETTING_LastSort;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__UMBUCHER;

public class FPU_PopupContainer_UnverplanteFuhren extends E2_SortGrid
{
	private static String 				SAVE_KEY_USERSETTINGS	= "SAVE_SORT_IN_FAHRPLAN_UEBERSICHT@@";
	private static int    				ANZAHL_SPALTEN 		= 8;
	
	private static int[]     			SPALTENBREITEN = {10,50,10,50,40,30,40,30};
	private static String[]  			SPALTENTITEL   = {"-","Fahrt beginnt bei","-","Fahrt endet bei","Sorte","Tätigkeit","Container","Vormerkung"};
	private static int[]	 			arrAusrichtung_left_center_right_012 = {1,0,1,0,0,1,1,1};

	
	private ownBasicModuleContainer    	oownPopUp = 					null;
	private FPU_BasicModuleContainer   	oFPU_BasicModuleContainer = 	null;
	private LKW_CheckBox   				oCB_LKW 		= 				null;
	private String 						cId_maschinen_lkw 		= 		null;
	private String 						dFahrplanDat_DB_Format 	= 		null;

	
	
	public FPU_PopupContainer_UnverplanteFuhren(	FPU_BasicModuleContainer 	o_FPU_BasicModuleContainer,
													LKW_CheckBox             	LKW_CheckBox,	
													String  					id_maschinen_lkw,
													String  					FahrplanDat_DB_Format
														) throws myException
	{
		super(FPU_PopupContainer_UnverplanteFuhren.SPALTENBREITEN, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		this.oownPopUp = new ownBasicModuleContainer();

		
		this.oFPU_BasicModuleContainer = o_FPU_BasicModuleContainer;
		this.oCB_LKW = LKW_CheckBox;
		
		this.cId_maschinen_lkw = 		id_maschinen_lkw;
		this.dFahrplanDat_DB_Format = 	FahrplanDat_DB_Format;
		
		//standard-einstellung
		this.set_iLastSortedColumn(new Integer(0));
		this.set_cLastSortStatus(E2_SortGrid.SORTED_UP);
		
		
		
		//letzter sortierstatus wieder laden und ausfuehren
		String[] cSortStatus= new E2_SortGrid_USERSETTING_LastSort(null,this).get_Status_aus_Database(E2_MODULNAMES.NAME_MODUL_FAHRPLANUEBERSICHT);
		if (cSortStatus!=null && S.isFull(cSortStatus[0]) && S.isFull(cSortStatus[1]) && bibALL.isLong(cSortStatus[0]))
		{
			this.set_iLastSortedColumn(new Integer(cSortStatus[0]));
			this.set_cLastSortStatus(cSortStatus[1]);
		}
		
		this.__NeuBau();
		
		
		//anzeigen
		this.oownPopUp.add(this, E2_INSETS.I_4_4_4_4);
		
		this.oownPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Noch unverplante Fahrten ..."));
		
	}

	
	public void __NeuBau() throws myException
	{
		this.baue_recListNeu(false);
		this.RESET_LISTE();              //zwingt zum neuaufbau des Vector<Component[]>
		
		this.SortListe();
		this.BaueListeAuf();
	}
	
	
	/*
	 * die recliste liegt im Container und muss deshalb nicht jedesmal neu gebaut werden
	 */
	private void baue_recListNeu(boolean bForceRebuild) throws myException
	{
		if (this.oFPU_BasicModuleContainer.get_vectorUnverplanteFuhren()==null || bForceRebuild)
		{	
		
			this.oFPU_BasicModuleContainer.set_vectorUnverplanteFuhren(
					new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																	" WHERE NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND " +
																	"       NVL(IST_STORNIERT,'N')='N' AND " +
																	"       NVL(DELETED,'N')='N' AND " +
																	"       NVL(ID_MASCHINEN_LKW_FP,0)=0 AND " +
																	"       DAT_FAHRPLAN_FP IS NULL " + 
																	"   ORDER BY DAT_VORGEMERKT_FP,ID_ADRESSE_LAGER_START"));
			
//			System.out.println("Neubau");
		}
	}
		
	
	@Override
	public Integer get_iSpaltenZahl() throws myException
	{
		return FPU_PopupContainer_UnverplanteFuhren.ANZAHL_SPALTEN;
	}

	@Override
	public Component get_TitelComponent(int iSpalte) throws myException
	{
		switch (iSpalte)
		{
			case (0):
			case (2):
			{
				MyE2_Label oLabel = new MyE2_Label("");
				oLabel.setLayoutData(new FPU_LayoutData(FPU_PopupContainer_UnverplanteFuhren.arrAusrichtung_left_center_right_012[iSpalte], new E2_ColorDDDark()));
				return oLabel;
			}
			case (1):
			case (3):
			case (4):
			case (5):
			case (6):
			case (7):
			{
				E2_SortButtonInList oButton = new E2_SortButtonInList(	iSpalte,
												this,
												new MyE2_String(FPU_PopupContainer_UnverplanteFuhren.SPALTENTITEL[iSpalte]),
												false, 
												true,
												null,
												E2_MODULNAMES.NAME_MODUL_FAHRPLANUEBERSICHT,
												FPU_PopupContainer_UnverplanteFuhren.this.get_iLastSortedColumn(),
												FPU_PopupContainer_UnverplanteFuhren.this.get_cLastSortStatus());
				oButton.setLayoutData(new FPU_LayoutData(FPU_PopupContainer_UnverplanteFuhren.arrAusrichtung_left_center_right_012[iSpalte], new E2_ColorDDDark()));
				return oButton;
			}
		};
		
		return new MyE2_Label("");
	}

	@Override
	public Vector<E2_SortGridListenZeile> build_VectorWithComponentArrays() throws myException
	{
		Vector<E2_SortGridListenZeile>  vRueck = new Vector<E2_SortGridListenZeile>();
		
		if (this.oFPU_BasicModuleContainer.get_vectorUnverplanteFuhren()==null)
		{
			throw new myException(this, "The basic Vector<ownRECORD_VPOS_TPA_FUHRE>  is null !!");
		}
			
		for (int i=0;i<this.oFPU_BasicModuleContainer.get_vectorUnverplanteFuhren().size();i++)
		{
			vRueck.add(new ownListenZeile(this.oFPU_BasicModuleContainer.get_vectorUnverplanteFuhren().get(i)));
		}
		
		return vRueck;
	}

	@Override
	public String get_SaveKeyForUserSettings()
	{
		return FPU_PopupContainer_UnverplanteFuhren.SAVE_KEY_USERSETTINGS;
	}

	
	
	private class ownListenZeile extends E2_SortGridListenZeile
	{
		
		private ownRECORD_VPOS_TPA_FUHRE   recFuhre = null;
		
		public ownListenZeile(ownRECORD_VPOS_TPA_FUHRE rec_Fuhre)
		{
			super();
			this.recFuhre = rec_Fuhre;
		}

		
		@Override
		public Component[] get_KomponentenZeile() throws myException
		{
			Component[]  compZeile = new Component[FPU_PopupContainer_UnverplanteFuhren.ANZAHL_SPALTEN];
			
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
			
			String cTaetigkeit = recFuhre.get_TAETIGKEIT_FP_cUF_NN("");
			String cContainer = recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() == null?
											new MyE2_String("-- Container unbekannt --").CTrans():
											recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp().get_KURZBEZEICHNUNG_cUF_NN("");

			compZeile[0]=new UebernahmeButton(	FPU_PopupContainer_UnverplanteFuhren.this.cId_maschinen_lkw,
												FPU_PopupContainer_UnverplanteFuhren.this.dFahrplanDat_DB_Format,
												recFuhre,
												FPU_PopupContainer_UnverplanteFuhren.this.oownPopUp);
			
			compZeile[1]=new MyE2_Label(new MyE2_String(cStart,false));
			compZeile[2]=new MyE2_Label(new MyE2_String("--->",false));
			compZeile[3]=new MyE2_Label(new MyE2_String(cZiel,false));
			compZeile[4]=new MyE2_Label(new MyE2_String(cSorte,false));
			compZeile[5]=new MyE2_Label(new MyE2_String(cTaetigkeit,false));
			compZeile[6]=new MyE2_Label(new MyE2_String(cContainer,false));
			compZeile[7]=new MyE2_Label(new MyE2_String(recFuhre.get_DAT_VORGEMERKT_FP_cF_NN(""),false));
			
			for (int i=0;i<compZeile.length;i++)
			{
				compZeile[i].setLayoutData(new FPU_LayoutData(FPU_PopupContainer_UnverplanteFuhren.arrAusrichtung_left_center_right_012[i], new E2_ColorBase()));
			}
			
			return compZeile;
		}

		@Override
		public boolean get_bZeileIstSichtbar() throws myException
		{
			boolean bRueck = true;
			
			bRueck = bRueck && this.recFuhre.get_bIstFrei();
			
			return bRueck;
		}

		@Override
		public Comparable get_SortableObject(int iColumn) throws myException
		{
			switch (iColumn)
			{
				case (0):
					return "";
				
				case (1):
					return recFuhre.get_L_NAME1_cUF_NN("")+" "+recFuhre.get_L_ORT_cUF_NN("");
				
				case (2):
					return "";
				
				case (3):
					return recFuhre.get_A_NAME1_cUF_NN("")+" "+recFuhre.get_A_ORT_cUF_NN("");
				
				case (4):
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
					return cSorte;

					
					
				case (5):
					return recFuhre.get_TAETIGKEIT_FP_cUF_NN("");
					
				case (6):
					return (recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp() == null?
							new MyE2_String("-- Container unbekannt --").CTrans():
							recFuhre.get_UP_RECORD_CONTAINERTYP_id_containertyp_fp().get_KURZBEZEICHNUNG_cUF_NN(""));
					
				case (7):
					return recFuhre.get_DAT_VORGEMERKT_FP_VALUE_FOR_SQLSTATEMENT();
					
			};

			
			
			return "";
		}
		
	}
	
	
	
	
	/*
	 * uebernahmebutton auf der popup-liste
	 */
	private class UebernahmeButton extends MyE2_Button
	{
		private String 						cID_MASCHINEN_LKW = null;
		private String 						cFahrplanDat_DB_Format = null;
		private ownRECORD_VPOS_TPA_FUHRE 	RecordFuhre = null;
		private E2_BasicModuleContainer   	oContainer = null;

		public UebernahmeButton(	String 						cid_maschinen_lkw,
									String 						fahrplanDat_DB_Format, 
									ownRECORD_VPOS_TPA_FUHRE 	recFuhre,
									E2_BasicModuleContainer   	Container ) throws myException
		{
			super(E2_ResourceIcon.get_RI("zeileneng.png"));
			this.setToolTipText(new MyE2_String("Fahrt in Fahrplan übernehmen !",true,"  (ID-Fuhre: "+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("")+")",false).CTrans());
			
			this.cID_MASCHINEN_LKW 	= 		cid_maschinen_lkw;
			this.cFahrplanDat_DB_Format = 	fahrplanDat_DB_Format;
			this.RecordFuhre = recFuhre;
			this.oContainer = Container;
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					UebernahmeButton oThis = UebernahmeButton.this;
					
					RECLIST_VPOS_TPA_FUHRE reclistFuhren = new RECLIST_VPOS_TPA_FUHRE();
					reclistFuhren.ADD(oThis.RecordFuhre, true);
					
					new FP__UMBUCHER(reclistFuhren,
									cFahrplanDat_DB_Format.substring(8, 10)+"."+cFahrplanDat_DB_Format.substring(5, 7)+cFahrplanDat_DB_Format.substring(0, 4),
									oThis.cID_MASCHINEN_LKW);

					FPU_PopupContainer_UnverplanteFuhren.this.oCB_LKW.Rebuild_Fuhren_Zu_LKW();
					FPU_PopupContainer_UnverplanteFuhren.this.oFPU_BasicModuleContainer.REPAINT_GRID_MIT_Gewuenschten_LKW_Anzeigen();
					
					//jetzt die fuhre als unfrei kennzeichnen
					oThis.RecordFuhre.set_bIstFrei(false);
					
					oThis.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
			
		}
	}

	
	
	/*
	 * eigene klasse zur speicherung der groesse und position
	 */
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer()
		{
			super();
		}
		
	}
	
	
}
