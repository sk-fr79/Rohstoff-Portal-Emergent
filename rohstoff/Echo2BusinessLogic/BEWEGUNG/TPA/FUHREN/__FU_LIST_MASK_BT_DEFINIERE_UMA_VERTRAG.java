package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_TAG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KONTRAKT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA._SPECIAL_REC_UMA_KONTRAKT;
import echopointng.Separator;


public abstract class __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG extends MyE2_Button
{
	
	//variable, die das objekt mehrmals braucht (werden immer im validierer gefuellt)
	private Long lID_ADRESSE_START= 0l;
	private Long lID_ADRESSE_ZIEL= 0l;
	
	private Long lID_ARTKELBEZ_EK = 0l;
	private Long lID_ARTKELBEZ_VK = 0l;

	private Long lID_UMA_KONTRAKT = 0l;
	
	private boolean bIsFremdwarenFuhre = false;
	private Long lID_ADRESSE_FREMDAUFTRAG = 0l;
	
	private Long lID_VPOS_TPA_FUHRE = 0l;
	
	
	private Vector<String>  vIDsPassendZurFuhre = new Vector<String>();
	
	
	public  __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG()
	{
		super("UMA");
		//this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
//		E2_MutableStyle  oStyle = MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN();
//		oStyle.setProperty(AbstractButton.PROPERTY_BACKGROUND, new E2_ColorDDark(), Color.LIGHTGRAY); 
//		oStyle.setProperty(AbstractButton.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));
//		oStyle.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontPlain());
//		oStyle.setProperty(AbstractButton.PROPERTY_PRESSED_FONT, new E2_FontBold());
		
		this.setStyle(this.getStyle_FUHRE_OHNE_UMA());
		
		this.add_GlobalValidator(new ownValidator());
		this.add_GlobalAUTHValidator_AUTO("UMA_KONTRAKT_DEFINIEREN");
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				new ownPopupContainer();
				__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.MarkiereNavilistZeile();
			}
		});
	}
	
	
	private void MarkiereNavilistZeile() throws myException
	{
		if (this.lID_VPOS_TPA_FUHRE!=null && this.lID_VPOS_TPA_FUHRE.longValue()>0)
		{
			//dann einen tag-search ausfuehren
			E2_NavigationList oNaviListFuhre = (E2_NavigationList)(new E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.NAVIGATIONLIST_IN_FUHRENZENTRALE_FAHRPLAN_FAHRPLANPOOL).get_SingleFoundComponent());
			
			if (oNaviListFuhre!=null)
			{
				oNaviListFuhre.Mark_ID_IF_IN_Page(""+this.lID_VPOS_TPA_FUHRE.longValue());
			}
		}
	}
	

	
	/**
	 * wird von fuhren.masksetter und auch von dieser klasse benutzt, um anzueigen, ob die fuhre eine uma-zuordnung hat
	 * @param bUMA_Ein
	 * @throws myException 
	 */
	public void KorrigiereAnzeigeFuer_UMA_STATUS(Long lID_UMA) throws myException
	{
		boolean bUMA_Ein = (lID_UMA!=null && lID_UMA.longValue()>0);
		
		if (bUMA_Ein)
		{
			MyLong oLong= new MyLong(""+lID_UMA.longValue());
			this.setStyle(this.getStyle_FUHRE_MIT_UMA());
			this.set_Text(new MyE2_String("UMA ",true,oLong.get_cF_LongString(),false));
		}
		else
		{
			this.setStyle(this.getStyle_FUHRE_OHNE_UMA());
			this.set_Text(new MyE2_String("UMA"));
		}
		this.INDIVIDUELLE_Anzeige(lID_UMA);

	}
	

	
	
	//popup-container zum fuellen des UMA-Textfeldes
	private class ownPopupContainer extends E2_BasicModuleContainer
	{
		private ownUmaGrid 				oGrid = 	new ownUmaGrid(); 

		private ownCheckBoxZeigeAlle  	oCB_ZeigeAlle = new ownCheckBoxZeigeAlle();
		
		public ownPopupContainer() throws myException
		{
			super();
			this.add(oGrid);

			this.baue_innereien();
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(600), new MyE2_String("Auswahl eines Umarbeitungsvertrages"));
		}
		
		
		private void baue_innereien() throws myException
		{

			boolean bNurPassende = !this.oCB_ZeigeAlle.isSelected();
			
			this.oGrid.removeAll();
			
			//maske auslesen
			__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.PRUEFE_und_SETZE_STATUS_DER_NOTWENDIGEN_FELDER();

			__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG oThis = __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this;
			RECORD_ARTIKEL_BEZ  			  oArtBezEK = new RECORD_ARTIKEL_BEZ(oThis.lID_ARTKELBEZ_EK);
			RECORD_ARTIKEL_BEZ  			  oArtBezVK = new RECORD_ARTIKEL_BEZ(oThis.lID_ARTKELBEZ_VK);
			
			String cID_ARTIKEL_AUSGANG = 	oArtBezEK.get_ID_ARTIKEL_cUF();
			String cID_ARTIKEL_ZIEL = 		oArtBezVK.get_ID_ARTIKEL_cUF();

			//jetzt die passenden uma-kontrakte suchen (firmen und artikelgleichheit
			String cSQL_QUERY_ALLE = "SELECT DISTINCT A.NAME1,UMA.* FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT UMA "+
									    "LEFT OUTER JOIN "+bibE2.cTO()+".JT_UMA_KON_ARTB_LIEF      UAL ON (UAL.ID_UMA_KONTRAKT=UMA.ID_UMA_KONTRAKT) "+
									    "LEFT OUTER JOIN "+bibE2.cTO()+".JT_UMA_KON_ARTB_RUECKLIEF UAR ON (UAR.ID_UMA_KONTRAKT=UMA.ID_UMA_KONTRAKT) "+
										"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABA ON (ABA.ID_ARTIKEL_BEZ=UAL.ID_ARTIKEL_BEZ) "+
										"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABZ ON (ABZ.ID_ARTIKEL_BEZ=UAR.ID_ARTIKEL_BEZ) "+
										"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE A  ON (A.ID_ADRESSE=UMA."+RECORD_UMA_KONTRAKT.FIELD__ID_ADRESSE+") "+
										" WHERE NVL(UMA.DELETED,'N')='N' AND NVL(UMA."+RECORD_UMA_KONTRAKT.FIELD__ABGESCHLOSSEN+",'N')='N' " ;
			
			String cSQL_QUERY_passende = cSQL_QUERY_ALLE+" AND " +
								" ( "+
								   "( UMA."+RECORD_UMA_KONTRAKT.FIELD__ID_ADRESSE+"="+oThis.lID_ADRESSE_START.intValue()+" AND ABA.ID_ARTIKEL="+cID_ARTIKEL_AUSGANG+") "+
								   		" OR "+
								    "( UMA."+RECORD_UMA_KONTRAKT.FIELD__ID_ADRESSE+"="+oThis.lID_ADRESSE_ZIEL.intValue()+" AND ABZ.ID_ARTIKEL="+cID_ARTIKEL_ZIEL+") "+								    
								" ) ";
			
			String cORDER_BLOCK = " ORDER BY 1";
			
			RECLIST_UMA_KONTRAKT  reclistUMAPassendZurFuhre = new RECLIST_UMA_KONTRAKT(cSQL_QUERY_passende+cORDER_BLOCK);
			RECLIST_UMA_KONTRAKT  reclistUMAAlleOffenen = new RECLIST_UMA_KONTRAKT(cSQL_QUERY_ALLE+cORDER_BLOCK);
			__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.vIDsPassendZurFuhre.removeAllElements();
			
			__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.vIDsPassendZurFuhre.addAll(reclistUMAPassendZurFuhre.get_vKeyValues());
			
			 
			GridLayoutData  oGLTitle = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1, 1);
			GridLayoutData  oGLNormal = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1, 1);
			
			if (oThis.lID_UMA_KONTRAKT>0)
			{
				oGrid.add(new MyE2_Label(new MyE2_String("Momentaner UMA-Kontrakt für diese Fuhre: ")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 6, 1));
				oGrid.add_Zeile_Label(new _SPECIAL_REC_UMA_KONTRAKT(oThis.lID_UMA_KONTRAKT), oGLNormal);
				oGrid.add_Separator();
			}
			
			oGrid.add_Zeile_Titel(oGLTitle);
			
			if (bNurPassende)
			{
				this.fuelle_liste(reclistUMAPassendZurFuhre, oGLNormal, reclistUMAPassendZurFuhre,oThis.lID_UMA_KONTRAKT);
			}
			else
			{
				this.fuelle_liste(reclistUMAAlleOffenen, oGLNormal, reclistUMAPassendZurFuhre,oThis.lID_UMA_KONTRAKT);
			}

		}
		
		private void fuelle_liste(RECLIST_UMA_KONTRAKT  recList, GridLayoutData oGLNormal, RECLIST_UMA_KONTRAKT  recListPassende, Long ID_UMA_ZuGedordnet) throws myException
		{
			long lEigene = 0;
			
			if (ID_UMA_ZuGedordnet!=null)
			{
				lEigene = ID_UMA_ZuGedordnet.longValue();
			}
			
			for (int i=0;i<recList.get_vKeyValues().size();i++)
			{
				_SPECIAL_REC_UMA_KONTRAKT  recUMA = new _SPECIAL_REC_UMA_KONTRAKT(recList.get(i));
			
				if (recUMA.get_ID_UMA_KONTRAKT_lValue(new Long(-1)).longValue()==lEigene)
				{
					continue;                                      //die zeule mit dem zugeordneten uma-kontrakt nicht anzeigen
				}
				
				boolean bEnabled = recUMA.get_ID_UMA_KONTRAKT_lValue(-1l).longValue()!=__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.lID_UMA_KONTRAKT.longValue() &&
										recListPassende.get_vKeyValues().contains(recUMA.get_ID_UMA_KONTRAKT_cUF());

				oGrid.add_Zeile_BT(recUMA, bEnabled, oGLNormal);
			}

		}
		
		private class ownSelectButton extends MyE2_Button
		{
			public ownSelectButton(String cLabel, _SPECIAL_REC_UMA_KONTRAKT  rec_UMA, boolean bEnabled) throws myException
			{
				super(cLabel,MyE2_Button.StyleTextButton_LOOK_like_LABEL(),new MyE2_String("Ordnet den angezeigten Umarbeitungsvertrag dieser Fuhre zu"), new ownActionUMA_Uebernehmen(rec_UMA));
				
				if (S.isEmpty(cLabel))
				{
					this.__setImages(E2_ResourceIcon.get_RI("ok.png"), true);
				}
				
				if (!bEnabled)
				{
					this.set_bEnabled_For_Edit(false);
				}
			}
		}


		
		/*
		 * innere liste im popup-grid, der die zugeordneten sorten innerhalb eines um-vertrags zeigt
		 */
		private class ownSelectList extends MyE2_Grid
		{

			public ownSelectList(_SPECIAL_REC_UMA_KONTRAKT recUMA, boolean bEnabled, boolean bLieferseite, GridLayoutData oGLNormal, boolean bButtons) throws myException
			{
				super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				if (bLieferseite)
				{
					RECLIST_UMA_KON_ARTB_LIEF  recList = recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_uma_kontrakt();
					
					for (int i=0;i<recList.get_vKeyValues().size();i++)
					{
						RECORD_UMA_KON_ARTB_LIEF  recArtbez = recList.get(i);
						
						String cANR1 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("<anr1>");
						String cANR2 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cF_NN("<anr2>");
						String cARTBEZ1 = 	recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");
						String cProz = 		recArtbez.get_NUTZBAR_PROZENT_cF_NN("<%>")+" %";
						
						if (bButtons)
						{
							this.add(new ownSelectButton(cANR1+" "+cANR2+" "+cARTBEZ1+" ("+cProz+")",recUMA, bEnabled), 	oGLNormal);
						}
						else
						{
							this.add(new MyE2_Label(cANR1+" "+cANR2+" "+cARTBEZ1+" ("+cProz+")"), 	oGLNormal);
						}
					}
				}
				else
				{
					RECLIST_UMA_KON_ARTB_RUECKLIEF  recList = recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_uma_kontrakt();
					
					for (int i=0;i<recList.get_vKeyValues().size();i++)
					{
						RECORD_UMA_KON_ARTB_RUECKLIEF  recArtbez = recList.get(i);
						
						String cANR1 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("<anr1>");
						String cANR2 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cF_NN("<anr2>");
						String cARTBEZ1 = 	recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");
						String cProz = 		recArtbez.get_NUTZBAR_PROZENT_cF_NN("<%>")+" %";

						if (bButtons)
						{
							this.add(new ownSelectButton(cANR1+" "+cANR2+" "+cARTBEZ1+" ("+cProz+")",recUMA, bEnabled), 	oGLNormal);
						}
						else
						{
							this.add(new MyE2_Label(cANR1+" "+cANR2+" "+cARTBEZ1+" ("+cProz+")"), 	oGLNormal);
						}
					}
				}
			}
		}
		
		
		private class ownUmaGrid extends MyE2_Grid
		{
			public ownUmaGrid()
			{
				super(bibARR.get_Array(20, 170,150,20,150,20), MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
			}
			
			public void add_Zeile_BT(_SPECIAL_REC_UMA_KONTRAKT  recUMA, boolean bEnabled, GridLayoutData oGLNormal) throws myException
			{
				this.add(new ownSelectButton(recUMA.get_ID_UMA_KONTRAKT_cF(),recUMA, bEnabled), 		oGLNormal);
				this.add(new ownSelectButton(recUMA.get_LabelTextKunde(),recUMA, bEnabled), 			oGLNormal);
				this.add(new ownSelectList(recUMA, bEnabled,true, 	oGLNormal,true));
				this.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_ITALLIC()),		oGLNormal);
				this.add(new ownSelectList(recUMA, bEnabled,false, 	oGLNormal,true));
				this.add(new ownSelectButton("", recUMA, bEnabled),										oGLNormal);
			}
			public void add_Zeile_Label(_SPECIAL_REC_UMA_KONTRAKT  recUMA, GridLayoutData oGLNormal) throws myException
			{
				this.add(new MyE2_Label(recUMA.get_ID_UMA_KONTRAKT_cF()), 							oGLNormal);
				this.add(new MyE2_Label(recUMA.get_LabelTextKunde()), 								oGLNormal);
				this.add(new ownSelectList(recUMA, true,true, 	oGLNormal,false));
				this.add(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_ITALLIC()),	oGLNormal);
				this.add(new ownSelectList(recUMA, true,false, 	oGLNormal,false));
				this.add(new ownRemoveButton(), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 5, 1));
			}
			public void add_Zeile_Titel(GridLayoutData oGLTitle)
			{
				ownPopupContainer oThis = ownPopupContainer.this;
				this.add(new MyE2_Label(new MyE2_String("UMA-K.-ID"),		MyE2_Label.STYLE_NORMAL_ITALLIC()), 	oGLTitle);
				this.add(new MyE2_Label(new MyE2_String("Kunde"), 			MyE2_Label.STYLE_NORMAL_ITALLIC()), 	oGLTitle);
				this.add(new MyE2_Label(new MyE2_String("Liefersorte"), 	MyE2_Label.STYLE_NORMAL_ITALLIC()), 	oGLTitle);
				this.add(new MyE2_Label(new MyE2_String(""), 				MyE2_Label.STYLE_NORMAL_ITALLIC()), 	oGLTitle);
				this.add(new MyE2_Label(new MyE2_String("Rückliefersorte"), MyE2_Label.STYLE_NORMAL_ITALLIC()), 	oGLTitle);
				this.add(oThis.oCB_ZeigeAlle, 	oGLTitle);
			}
			public void add_Separator()
			{
				this.add(new Separator(),this.getSize(),E2_INSETS.I_1_1_1_1);
			}
			
		}
		

		
		private class ownActionUMA_Uebernehmen extends XX_ActionAgent
		{
			private _SPECIAL_REC_UMA_KONTRAKT  recUMA = null;
			public ownActionUMA_Uebernehmen(_SPECIAL_REC_UMA_KONTRAKT rec_UMA)
			{
				super();
				this.recUMA = rec_UMA;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				
				if (__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.vIDsPassendZurFuhre.contains(recUMA.get_ID_UMA_KONTRAKT_cUF()))
				{
					//unterscheiden, ob liste oder maske
					//zuerst maske (wird in das dummy-feld uma-kontrakt geschrieben und erst nach dem speichern abgelegt
					if ((__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP().containsKey("ID_UMA_KONTRAKT")))
					{
						MyE2IF__DB_Component  oTF_UMA_KONTRAKT = (MyE2IF__DB_Component)__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP().get__Comp("ID_UMA_KONTRAKT");
						oTF_UMA_KONTRAKT.set_cActualMaskValue(this.recUMA.get_ID_UMA_KONTRAKT_cF_NN(""));
						ownPopupContainer.this.baue_innereien();
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Der UMA-Vertrag wurde zugeordnet. Die Änderung wird nach dem nachsten Speichern der Fuhre gültig!"));
					}
					else
					{
						// in der list wird automatisch gespeichert
						RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.get_lID_VPOS_TPA_FUHRE());
						recFuhre.set_NEW_VALUE_ID_UMA_KONTRAKT(this.recUMA.get_ID_UMA_KONTRAKT_cF());
						bibMSG.add_MESSAGE(recFuhre.UPDATE(null, true));
						
						//dann die listenzeile neu laden
						__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);

						ownPopupContainer.this.baue_innereien();
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Der UMA-Vertrag wurde zugeordnet. Die Änderung ist bereits in der Fuhre gespeichert!"));

					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Der ausgewählte UMA-Vertrag passt nicht zur Fuhre !! Bitte passen Sie zuerst die Fuhre an!"));
				}
				
				if (bibMSG.get_bIsOK())
				{
					ownPopupContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			}
		}
		
		
		
		private class ownRemoveButton extends MyE2_Button
		{
			public ownRemoveButton()
			{
				super(E2_ResourceIcon.get_RI("delete_mini.png"),new MyE2_String("Entfernt den zugeordneten UMA-Vertrag von dieser Fuhre"), new ownActionUMA_Entfernen());
			}
		}

		
		private class ownActionUMA_Entfernen extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				//unterscheiden, ob liste oder maske
				//zuerst maske (wird in das dummy-feld uma-kontrakt geschrieben und erst nach dem speichern abgelegt
				if ((__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP().containsKey("ID_UMA_KONTRAKT")))
				{
					//unterscheiden, ob liste oder maske
					MyE2IF__DB_Component  oTF_UMA_KONTRAKT = (MyE2IF__DB_Component)__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP().get__Comp("ID_UMA_KONTRAKT");
					oTF_UMA_KONTRAKT.set_cActualMaskValue("");
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Die UMA-Vertrags-Zuordnung wurde entfernt. Die Änderung wird nach dem nachsten Speichern der Fuhre gültig!"));
					ownPopupContainer.this.baue_innereien();
				}
				else
				{
					// in der list wird automatisch gespeichert
					RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.get_lID_VPOS_TPA_FUHRE());
					recFuhre.set_NEW_VALUE_ID_UMA_KONTRAKT(null);
					bibMSG.add_MESSAGE(recFuhre.UPDATE(null, true));
					
					//dann die listenzeile neu laden
					__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
					ownPopupContainer.this.baue_innereien();
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Die UMA-Vertrags-Zuordnung wurde entfernt. Die Änderung ist bereits in de Fuhre gespeichert!"));

				}
				
//				if (bibMSG.get_bIsOK())
//				{
//					ownPopupContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//				}

			}
		}
		

		private class ownCheckBoxZeigeAlle extends MyE2_CheckBox
		{
			public ownCheckBoxZeigeAlle()
			{
				super(new MyE2_String("Alle"), new MyE2_String("Alle offenen UMA-Kontrakte anzeigen oder nur die zur Fuhre passenden ..."));
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						ownPopupContainer.this.baue_innereien();
					}
				});
			}
		}

		
		
	}
	
	
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG oThis = __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG.this;
			
			MyE2_MessageVector 	oMV = oThis.PRUEFE_und_SETZE_STATUS_DER_NOTWENDIGEN_FELDER();
			
			E2_ComponentMAP  oMAP = oThis.EXT().get_oComponentMAP();
			
			if (!oMAP.get_bIs_Neueingabe())
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				if (recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL("+RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DELETED+",'N')='N'", null, true).get_vKeyValues().size()>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Umarbeitungsfuhren MÜSSEN immer Fuhren ohne Fuhrenort sein !")));
				}
			}
			
			if (oThis.lID_ADRESSE_START==0 || oThis.lID_ADRESSE_ZIEL==0 || oThis.lID_ARTKELBEZ_EK==0 || oThis.lID_ARTKELBEZ_VK==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie zuerst Lieferant/Abnehmer und die Sortenbezeichnungen ein !")));
			}
			if (oThis.lID_ADRESSE_FREMDAUFTRAG!=0 || oThis.bIsFremdwarenFuhre)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA-Verträge bei Fremdaufträgen/Fremdwarenfuhren sind nicht erlaubt !")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
		
	}

	
	
	protected abstract MyE2_MessageVector 	PRUEFE_und_SETZE_STATUS_DER_NOTWENDIGEN_FELDER() throws myException;

	protected abstract void   				INDIVIDUELLE_Anzeige(Long lID_UMA) throws  myException;
	
	
	
	
	private  E2_MutableStyle getStyle_FUHRE_OHNE_UMA()
	{
		E2_MutableStyle oStyle = new E2_MutableStyle();

		oStyle.setProperty(AbstractButton.PROPERTY_BACKGROUND, new E2_ColorDDark(), Color.LIGHTGRAY); 
		oStyle.setProperty(AbstractButton.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));
		oStyle.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		oStyle.setProperty(AbstractButton.PROPERTY_PRESSED_FONT, new E2_FontBold());
		oStyle.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		
		if (bibSES.get_Color4_Border_UMA_Button().getRgb()==Color.BLACK.getRgb()) {
			oStyle.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, bibSES.get_Color4_Border_UMA_Button(), Border.STYLE_SOLID));
		} else {
			oStyle.setProperty( AbstractButton.PROPERTY_BORDER, new Border(2, bibSES.get_Color4_Border_UMA_Button(), Border.STYLE_SOLID));
		}
		
		oStyle.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		oStyle.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oStyle.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_1_0_1_0);
		
		oStyle.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		oStyle.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
	
		return oStyle;
	}
	
	private  E2_MutableStyle getStyle_FUHRE_MIT_UMA()
	{
		E2_MutableStyle oStyle = new E2_MutableStyle();

		oStyle.setProperty(AbstractButton.PROPERTY_BACKGROUND, Color.GREEN, Color.GREEN); 
		oStyle.setProperty(AbstractButton.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));
		oStyle.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		oStyle.setProperty(AbstractButton.PROPERTY_PRESSED_FONT, new E2_FontBold());
		oStyle.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		oStyle.setProperty( AbstractButton.PROPERTY_BORDER, new Border(2, Color.BLACK, Border.STYLE_GROOVE)); 
		
		oStyle.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		oStyle.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oStyle.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_1_0_1_0);
		
		oStyle.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		oStyle.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
	
		return oStyle;
	}

	public Long get_lID_ADRESSE_START()
	{
		return lID_ADRESSE_START;
	}

	public Long get_lID_ADRESSE_ZIEL()
	{
		return lID_ADRESSE_ZIEL;
	}

	public Long get_lID_ARTKELBEZ_EK()
	{
		return lID_ARTKELBEZ_EK;
	}

	public Long get_lID_ARTKELBEZ_VK()
	{
		return lID_ARTKELBEZ_VK;
	}

	public Long get_lID_UMA_KONTRAKT()
	{
		return lID_UMA_KONTRAKT;
	}

	public boolean get_bIsFremdwarenFuhre()
	{
		return bIsFremdwarenFuhre;
	}

	public Long get_lID_ADRESSE_FREMDAUFTRAG()
	{
		return lID_ADRESSE_FREMDAUFTRAG;
	}

	public Vector<String> get_vIDsPassendZurFuhre()
	{
		return vIDsPassendZurFuhre;
	}

	public void set_lID_ADRESSE_START(Long l_ID_ADRESSE_START)
	{
		this.lID_ADRESSE_START = l_ID_ADRESSE_START;
	}

	public void set_lID_ADRESSE_ZIEL(Long l_ID_ADRESSE_ZIEL)
	{
		this.lID_ADRESSE_ZIEL = l_ID_ADRESSE_ZIEL;
	}

	public void set_lID_ARTKELBEZ_EK(Long l_ID_ARTKELBEZ_EK)
	{
		this.lID_ARTKELBEZ_EK = l_ID_ARTKELBEZ_EK;
	}

	public void set_lID_ARTKELBEZ_VK(Long l_ID_ARTKELBEZ_VK)
	{
		this.lID_ARTKELBEZ_VK = l_ID_ARTKELBEZ_VK;
	}

	public void set_lID_UMA_KONTRAKT(Long l_ID_UMA_KONTRAKT)
	{
		this.lID_UMA_KONTRAKT = l_ID_UMA_KONTRAKT;
	}

	public void set_bIsFremdwarenFuhre(boolean b_IsFremdwarenFuhre)
	{
		this.bIsFremdwarenFuhre = b_IsFremdwarenFuhre;
	}

	public void set_lID_ADRESSE_FREMDAUFTRAG(Long l_ID_ADRESSE_FREMDAUFTRAG)
	{
		this.lID_ADRESSE_FREMDAUFTRAG = l_ID_ADRESSE_FREMDAUFTRAG;
	}

	public void set_vIDsPassendZurFuhre(Vector<String> v_IDsPassendZurFuhre)
	{
		this.vIDsPassendZurFuhre = v_IDsPassendZurFuhre;
	}

	public Long get_lID_VPOS_TPA_FUHRE()
	{
		return lID_VPOS_TPA_FUHRE;
	}

	public void set_lID_VPOS_TPA_FUHRE(Long l_ID_VPOS_TPA_FUHRE)
	{
		this.lID_VPOS_TPA_FUHRE = l_ID_VPOS_TPA_FUHRE;
	}

	
}
