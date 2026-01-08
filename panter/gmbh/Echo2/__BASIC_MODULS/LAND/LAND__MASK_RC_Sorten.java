package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain_LineThrough;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_SelectAllNoneInvert_ABS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAND__MASK_RC_Sorten extends MyE2_Grid implements MyE2IF__DB_Component{

	public static int[] iBreiteKompenente = {10,10,30,100};
	
	private MyE2EXT__DB_Component 			oEXTDB=		new MyE2EXT__DB_Component(this);
	
	private Vector<LandSorte> 				vLandSorte = new Vector<LAND__MASK_RC_Sorten.LandSorte>(); 
	
	private MyE2_Grid 						oGrid4Sorten = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	private MyE2_Grid  						oGrid4Buttons = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	


	private ownButtonAuswahlInvertieren    			oButtonAuswahlInvertieren = null;
	private ownButtonMarkSelectedToDel     			oButtonMarkSelectedToDel = null;
	private ownButtonZeigePopup_4_InsertANR1List  	oButtonZeigePopup_4_InsertANR1List = null;
	private ownButtonZeigePopup_4_CopyANR1List   	oButtonZeigePopup_4_CopyANR1List = null;
//	private ownButtonAddZollTarifSorten  			oButtonAddZollTarifSorten = null;
	private ownSearchSorte  						oSearchSorte = null;
	
	//komponente wird als grid dargestellt, wo spalte fuer spalte jeweils N sorten angezeigt werden 
	public LAND__MASK_RC_Sorten(LAND__MASK_SQLFieldMAP oSQLFM) throws myException {
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.add(oGrid4Buttons, 	E2_INSETS.I(0,0,0,0));
		this.add(oGrid4Sorten, 		E2_INSETS.I(0,5,0,0));
		
		this.oGrid4Sorten.setOrientation(Grid.ORIENTATION_VERTICAL);
		this.oGrid4Sorten.setSize(30);
		
		this.oGrid4Buttons.setSize(5);
		this.oGrid4Buttons.add(this.oButtonAuswahlInvertieren = new ownButtonAuswahlInvertieren(),E2_INSETS.I(0,0,5,0));
		this.oGrid4Buttons.add(this.oButtonMarkSelectedToDel = new ownButtonMarkSelectedToDel(),E2_INSETS.I(0,0,5,0));
		this.oGrid4Buttons.add(this.oButtonZeigePopup_4_InsertANR1List = new ownButtonZeigePopup_4_InsertANR1List(),E2_INSETS.I(0,0,5,0));
		this.oGrid4Buttons.add(this.oButtonZeigePopup_4_CopyANR1List = new ownButtonZeigePopup_4_CopyANR1List(),E2_INSETS.I(0,0,5,0));
//		this.oGrid4Buttons.add(this.oButtonAddZollTarifSorten =  new ownButtonAddZollTarifSorten(),E2_INSETS.I(0,0,5,0));
		this.oGrid4Buttons.add(this.oSearchSorte = new ownSearchSorte(),E2_INSETS.I(0,0,5,0));
		
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(oSQLFM.get_(_DB.LAND$ID_LAND));
	}
	

	public void set_AktivPassiv(boolean bAktiv) throws myException {
		this.oButtonAuswahlInvertieren.set_bEnabled_For_Edit(true);
		this.oButtonMarkSelectedToDel.set_bEnabled_For_Edit(bAktiv);
		this.oButtonZeigePopup_4_InsertANR1List.set_bEnabled_For_Edit(bAktiv);
		this.oButtonZeigePopup_4_CopyANR1List.set_bEnabled_For_Edit(true);
//		this.oButtonAddZollTarifSorten.set_bEnabled_For_Edit(bAktiv);
		this.oSearchSorte.set_bEnabled_For_Edit(bAktiv);
		
		for (LandSorte oLS: this.vLandSorte) {
			oLS.oDelButton.set_bEnabled_For_Edit(bAktiv);
		}
	}
	
	
	public int get_iAnzahlAktiveRcSorten() {
		int iAktiveSorten = 0;
		for (LandSorte oLS: this.vLandSorte) {
			if (!oLS.bDeleted) {
				iAktiveSorten++;
			}
		}
		return iAktiveSorten;
	}
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bAllesZusammen = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();
		
		this.set_AktivPassiv(bAllesZusammen);
		
	}

	
	
	@Override
	public String get_cActualMaskValue() throws myException {
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
	}
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.vLandSorte.removeAllElements();
		
		this.baue_grid(this.vLandSorte);
	}
	

	private boolean get_bSorteVorhanden(String cID_Sorte_uf_chk) {
		boolean bRueck = false;
		for (LandSorte oLS: this.vLandSorte) {
			if (oLS.cID_SORTE_UF.equals(cID_Sorte_uf_chk)) {
				bRueck = true;
				break;
			}
		}
		
		return bRueck;
	}

	
	
	
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cID_LAND_F,  String cMASK_STATUS,  SQLResultMAP oResultMAP) throws myException {
		
		this.vLandSorte.removeAllElements();
		
		RECLIST_LAND_RC_SORTEN  rlLandSorte = new RECLIST_LAND_RC_SORTEN(_DB.LAND_RC_SORTEN$ID_LAND+"="+oResultMAP.get_cUNFormatedROW_ID(), "");
		
		for (RECORD_LAND_RC_SORTEN recLS: rlLandSorte.values()) {
			this.vLandSorte.add(new LandSorte(recLS));
		}
		
		
		
		this.baue_grid(this.vLandSorte);
	}

	
	private void baue_grid(Vector<LandSorte> v_LandSorte) throws myException {
		
		this.oGrid4Sorten.removeAll();
		
		//this.add(new ownButtonAddZollTarifSorten());
		
		
		//sortieren
		//jetzt nach der sort-spalte sortieren ([3])
		Collections.sort(this.vLandSorte, new Comparator<LandSorte>()
		{
			@Override
			public int compare(LandSorte o1, LandSorte o2)
			{
				int iComp = 0;
				
				try {
					iComp = S.NN(o1.recSorte.get_ANR1_cUF_NN("")).compareTo(S.NN(o2.recSorte.get_ANR1_cUF_NN("")));
				} catch (myException e) {
					e.printStackTrace();
				}
				
				return iComp;
			}
		});


		
		
		for (LandSorte  oLS: v_LandSorte) {
			this.oGrid4Sorten.add(oLS.get_oAnzeigeObject());
		}
		
	}
	
	
	
	
	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
	}

	@Override
	public boolean get_bIsComplexObject() {
		return true;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		return this.get_vInsert_or_Update(oE2_ComponentMAP, oMaskInputMap);
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		return this.get_vInsert_or_Update(oE2_ComponentMAP, oMaskInputMap);
	}

	
	private Vector<String> get_vInsert_or_Update(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		String cID_LAND = oE2_ComponentMAP.get_bIs_Neueingabe() 
				? 
				oE2_ComponentMAP.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cNewValueFormated()
				:
				oE2_ComponentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				

		Vector<String> vSQL = new Vector<String>();
		
		for (LandSorte  oLandSorte: this.vLandSorte) {
			String cSQL = oLandSorte.get_cSQL_String(cID_LAND);
			if (S.isFull(cSQL)) {
				vSQL.add(cSQL);
			}
		}
		
		
		
		return vSQL;
	}
	
	
	@Override
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) {
	}

	public static int[] getiBreiteKompenente() {
		return iBreiteKompenente;
	}


	public MyE2EXT__DB_Component getoEXTDB() {
		return oEXTDB;
	}


	public Vector<LandSorte> getvLandSorte() {
		return vLandSorte;
	}


	public MyE2_Grid getoGrid4Sorten() {
		return oGrid4Sorten;
	}


	public MyE2_Grid getoGrid4Buttons() {
		return oGrid4Buttons;
	}


	public ownButtonAuswahlInvertieren get_oButtonAuswahlInvertieren() {
		return oButtonAuswahlInvertieren;
	}


	public ownButtonMarkSelectedToDel get_oButtonMarkSelectedToDel() {
		return oButtonMarkSelectedToDel;
	}


	public ownButtonZeigePopup_4_InsertANR1List get_oButtonZeigePopup_4_InsertANR1List() {
		return oButtonZeigePopup_4_InsertANR1List;
	}


	public ownButtonZeigePopup_4_CopyANR1List get_oButtonZeigePopup_4_CopyANR1List() {
		return oButtonZeigePopup_4_CopyANR1List;
	}


//	public ownButtonAddZollTarifSorten get_oButtonAddZollTarifSorten() {
//		return oButtonAddZollTarifSorten;
//	}


	public ownSearchSorte get_oSearchSorte() {
		return oSearchSorte;
	}

	
	private class LandSorte {
		public String 					cID_SORTE_UF = 	null;   //muss immer vorhanden sein
		public RECORD_LAND_RC_SORTEN 	recLandSorte = 	null;   //wenn neuer eintrag, dann null
		public RECORD_ARTIKEL          recSorte =	 	null;   //muss immer vorhanden sein

		public boolean   				bDeleted = 		false;
		
		public MyE2_CheckBox   	 		oCB = new MyE2_CheckBox();
		
		private MyE2_Label   		    oLabANR1 = 		null;
		private MyE2_Label   		    oLabARTBEZ1 = 	null;
		private ownDelButton   			oDelButton = null;
		
		/**
		 * konstruktor fuer vorhandene sorten
		 * @param rec_LandSorte
		 * @throws myException
		 */
		public LandSorte(RECORD_LAND_RC_SORTEN rec_LandSorte) throws myException {
			super();
			this.recLandSorte = rec_LandSorte;
			this.cID_SORTE_UF = this.recLandSorte.get_ID_ARTIKEL_cUF();
					
			this.recSorte = this.recLandSorte.get_UP_RECORD_ARTIKEL_id_artikel();
			this.oDelButton = new ownDelButton(this);
		}
		
		/**
		 * konstruktor fuer neue sorten 
		 * @param iD_SORTE_UF
		 * @throws myException
		 */
		public LandSorte(String iD_SORTE_UF) throws myException {
			super();
			this.cID_SORTE_UF = iD_SORTE_UF;
			this.recSorte = 	new RECORD_ARTIKEL(this.cID_SORTE_UF);
			this.oDelButton = 	new ownDelButton(this);
		}
		
		public String get_cSQL_String(String cID_LAND) throws myException {
			
			if (this.recLandSorte == null && (!this.bDeleted)) {
					
				MySqlStatementBuilder  oStatementBuilder = new MySqlStatementBuilder();
				oStatementBuilder.addSQL_Paar(_DB.LAND_RC_SORTEN$ID_LAND_RC_SORTEN, "SEQ_LAND_RC_SORTEN.NEXTVAL");
				oStatementBuilder.addSQL_Paar(_DB.LAND_RC_SORTEN$ID_LAND, cID_LAND);
				oStatementBuilder.addSQL_Paar(_DB.LAND_RC_SORTEN$ID_ARTIKEL, this.cID_SORTE_UF);
				
				return oStatementBuilder.get_CompleteInsertString(_DB.LAND_RC_SORTEN);
				
			}  else if (this.recLandSorte != null && this.bDeleted) {
				return this.recLandSorte.get_DELETE_STATEMENT();
			}
			
			return "";
		}

		

		
		public MyE2_Grid get_oAnzeigeObject() throws myException {
			
			MyE2_Grid  oGridHelp = new MyE2_Grid(LAND__MASK_RC_Sorten.iBreiteKompenente,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			GridLayoutData oLayout = new GridLayoutData();
			oLayout.setAlignment(Alignment.ALIGN_CENTER);
			oLayout.setInsets(E2_INSETS.I(0,0,4,0));

			
			GridLayoutData oGL_LEFT_CENTER = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,0,10,0));
			GridLayoutData oGL_LEFT_CENTER2 = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,2,10,0));
			
			Font  oFontNormal10 = new E2_FontPlain();
			Font  oFontNormal10Del = new E2_FontPlain_LineThrough();
			
			Font  oFontNormal8 = new E2_FontPlain(-2);
			Font  oFontNormal8Del = new E2_FontPlain_LineThrough(-2);
			
			
			oGridHelp.add(this.oDelButton,oGL_LEFT_CENTER);
			oGridHelp.add(this.oCB,oGL_LEFT_CENTER);
			oGridHelp.add(this.oLabANR1=new MyE2_Label(this.recSorte.get_ANR1_cUF_NN("<anr1>"),this.bDeleted?oFontNormal10Del:oFontNormal10),oGL_LEFT_CENTER);
			oGridHelp.add(this.oLabARTBEZ1=new MyE2_Label(this.recSorte.get_ARTBEZ1_cUF_NN("<artbez1>"),this.bDeleted?oFontNormal8Del:oFontNormal8),oGL_LEFT_CENTER2);
			
			return oGridHelp;
		}
		
		
		public void set_Deleted(boolean deleted) {
			this.bDeleted = deleted;
			if (this.bDeleted) {
				this.oLabANR1.setFont(new E2_FontPlain_LineThrough());
				this.oLabARTBEZ1.setFont(new E2_FontPlain_LineThrough(-2));
			} else {
				this.oLabANR1.setFont(new E2_FontPlain());
				this.oLabARTBEZ1.setFont(new E2_FontPlain(-2));
			}
			
		}
		
	}
	
	
	private class ownDelButton extends MyE2_Button {
		public ownDelButton(LandSorte o_LandSorte) {
			super(E2_ResourceIcon.get_RI("delete_mini.png"), true);
			this.add_oActionAgent(new ownActionDelete(o_LandSorte));
		}
		
		private class ownActionDelete extends XX_ActionAgent {
			private LandSorte oLandSorte = null;
			
			public ownActionDelete(LandSorte o_LandSorte) {
				super();
				this.oLandSorte = o_LandSorte;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				this.oLandSorte.set_Deleted(!this.oLandSorte.bDeleted);
				
				if (this.oLandSorte.bDeleted) {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Zuordnung der Sorte ",true,this.oLandSorte.recSorte.get_ANR1_cUF_NN("<anr1>"),false," wurde zum Löschen markiert!",true)));
				}
				
			}
		}

	}

	
	
	
	
//	private class ownButtonAddZollTarifSorten extends MyE2_Button {
//        
//		LAND__MASK_RC_Sorten oThis = LAND__MASK_RC_Sorten.this;
//		
//		public ownButtonAddZollTarifSorten() {
//			//super(E2_ResourceIcon.get_RI("add_ZT.png") , true);
//			super(new MyE2_String("RC-Sorten aus Zolltarif zufügen"));
//			
//			this.setToolTipText(new MyE2_String("Alle Sorten laden, die der RC-Zolltarifstruktur entstammen ...").CTrans());
//			
//			this.add_oActionAgent(new ownActionAgentAddZollTarifSorten());
//			this.add_GlobalAUTHValidator_AUTO(LAND__CONST.MASK_BT_ADD_ZOLLTARIF);
//			
//		}
//		
//		
//		private class ownActionAgentAddZollTarifSorten extends XX_ActionAgent {
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				String cSQL = "SELECT ART.ID_ARTIKEL FROM "+
//							bibE2.cTO()+".JT_ARTIKEL ART LEFT OUTER JOIN " +
//									" JT_ZOLLTARIFNUMMER ZT ON (ZT.ID_ZOLLTARIFNUMMER=ART.ID_ZOLLTARIFNUMMER) WHERE " +
//									" NVL(ZT."+_DB.ZOLLTARIFNUMMER$REVERSE_CHARGE+",'N')='Y'";
//				
//				String[][] cID_Table = bibDB.EinzelAbfrageInArray(cSQL, "");
//				
//				if (cID_Table != null ) {
//					for (int i=0;i<cID_Table.length;i++) {
//						if (!oThis.get_bSorteVorhanden(cID_Table[i][0])) {
//							LAND__MASK_RC_Sorten.this.vLandSorte.add(new LandSorte(cID_Table[i][0]));
//						}
//					}
//				}
//				
//				LAND__MASK_RC_Sorten.this.baue_grid(LAND__MASK_RC_Sorten.this.vLandSorte);
//				
//			}
//			
//			
//		}
//
//	}

	
	
	
	private class ownButtonAuswahlInvertieren extends E2_SelectAllNoneInvert_ABS {

		public ownButtonAuswahlInvertieren() {
			super();
		}

		@Override
		public Vector<MyE2_CheckBox> get_vAllCheckboxes() throws myException {
			Vector<MyE2_CheckBox> vRueck = new Vector<MyE2_CheckBox>();
			for (LandSorte oLS: LAND__MASK_RC_Sorten.this.vLandSorte) {
				vRueck.add(oLS.oCB);
			}
			return vRueck;
		}

		@Override
		public void do_after_action() throws myException {
		}
		
	}

	
	
	private class ownButtonMarkSelectedToDel extends MyE2_Button {

		public ownButtonMarkSelectedToDel() {
			super(new MyE2_String("Ausgewählte löschmarkieren"));
			this.add_oActionAgent(new ownActionMarkAll());
			this.setToolTipText(new MyE2_String("Merkt die selektierten Sorten zur Entfernung vor").CTrans());
			this.add_GlobalAUTHValidator_AUTO(LAND__CONST.MASK_BT_MARK_TO_DEL);

		}
		
		private class ownActionMarkAll extends XX_ActionAgent {
			LAND__MASK_RC_Sorten oThis = LAND__MASK_RC_Sorten.this;

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				int i=0; 
				for (LandSorte oLS: oThis.vLandSorte) {
					if (oLS.oCB.isSelected()) {
						oLS.set_Deleted(true);
						i++;
					} else {
						oLS.set_Deleted(false);
					}
				}
				if (i>0) {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anzahl zur Entfernung markierte Sorten: ",true,""+i,false)));
				}
			}
		}
	}

	
	
	/*
	 * button, der die eingabe eine ganzen liste von ANR1 erlaubt
	 */
	private class ownButtonZeigePopup_4_InsertANR1List extends MyE2_Button
	{
		//LAND__MASK_RC_Sorten oThis = LAND__MASK_RC_Sorten.this;
		
		private MyE2_TextArea  	oText4Sorten = new MyE2_TextArea("", 400, 10000, 50);

		public ownButtonZeigePopup_4_InsertANR1List()
		{
			super(new MyE2_String("ANR1-Liste eingeben und hinzufügen"));
			
			this.setToolTipText(new MyE2_String("Liste mit ANR1-Einträgen in Textfeld eintragen und anfügen ...").CTrans());
			this.add_oActionAgent(new ownActionZeigePopup_4_InsertANR1List());
			this.add_GlobalAUTHValidator_AUTO(LAND__CONST.MASK_BT_INPUT_LISTE);

		}
		
		
		private class ownActionZeigePopup_4_InsertANR1List extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{
				
				E2_BasicModuleContainer oContainerForInputListe = new E2_BasicModuleContainer();
				oContainerForInputListe.set_bVisible_Row_For_Messages(false);
				
				oText4Sorten.setText("");
				
				oContainerForInputListe.add(oText4Sorten,E2_INSETS.I_10_10_10_10);
				
				E2_ComponentGroupHorizontal oGroupButtons = new E2_ComponentGroupHorizontal(0,
										new ownButtonSaveInputSorten(oContainerForInputListe),
										new ownButtonCloseInputSorten(oContainerForInputListe),
										E2_INSETS.I_10_2_10_2);
				
				oContainerForInputListe.set_Component_To_ButtonPane(oGroupButtons);
				
				try
				{
					oContainerForInputListe.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(500),new Extent(500),new MyE2_String("Eingabe von IDs"));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				
			}
			
			
			private class ownButtonSaveInputSorten extends MyE2_Button {

				LAND__MASK_RC_Sorten oThis = LAND__MASK_RC_Sorten.this;
				E2_BasicModuleContainer oContainerForInputListe = null;
				
				public ownButtonSaveInputSorten(E2_BasicModuleContainer containerForInputListe) {
					super(new MyE2_String("Eingegebene Sorten zur RC-Liste zufügen"));
					this.oContainerForInputListe=containerForInputListe;
					this.add_oActionAgent(new ownActionAgentSaveInputSorten());
				}
				
				private class ownActionAgentSaveInputSorten extends XX_ActionAgent {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						String cTextRohfassung = ownButtonZeigePopup_4_InsertANR1List.this.oText4Sorten.getText();
						
						if (S.isFull(cTextRohfassung)) {
							
							// zuerst die trenner definieren - alles wird umgesetzt auf /
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"\n\r","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"\r","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"\n","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,";","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,",","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"\t","/");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"        "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"       "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"      "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"     "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"    "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"   "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung,"  "," ");
							cTextRohfassung = bibALL.ReplaceTeilString(cTextRohfassung," ","/");
							
							StringTokenizer textTrenner = new StringTokenizer("/"+cTextRohfassung+"/","/");
							String		    cANR1 = null;

							int iGefunden = 0;
							int iFehler = 0;
							int iZugefuegt = 0;
							
							while (textTrenner.hasMoreElements()) {
								cANR1 = textTrenner.nextToken();

								if (!cANR1.trim().equals("")) {
									RECLIST_ARTIKEL  recArt = new RECLIST_ARTIKEL("SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ANR1="+bibALL.MakeSql(cANR1.trim()));
								
									if (recArt.get_vKeyValues().size()==1)
									{
										iGefunden++;
										if (!oThis.get_bSorteVorhanden(recArt.get(0).get_ID_ARTIKEL_cUF())) {
											iZugefuegt++;
											LAND__MASK_RC_Sorten.this.vLandSorte.add(new LandSorte(recArt.get(0).get_ID_ARTIKEL_cUF()));
										}
									} else {
										iFehler++;
									}
								}
							}

							bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(
											"Korrekte ANR1 identifiziert:",true,""+iGefunden,false,
											", fehlerhafte Erfassungen:",true,""+iFehler,false,
											", angefügt (weil noch nicht vorhanden): ",true,""+iZugefuegt,false)));
							
							if (iZugefuegt>0) {
								LAND__MASK_RC_Sorten.this.baue_grid(LAND__MASK_RC_Sorten.this.vLandSorte);
							}
							ownButtonSaveInputSorten.this.oContainerForInputListe.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						}
					}
				}
			}

			private class ownButtonCloseInputSorten extends MyE2_Button {
				E2_BasicModuleContainer oContainerForInputListe = null;
				public ownButtonCloseInputSorten(E2_BasicModuleContainer containerForInputListe) {
					super(new MyE2_String("Abbruch"));
					this.oContainerForInputListe=containerForInputListe;
					this.add_oActionAgent(new closeContainer());
				}
				
				private class closeContainer extends XX_ActionAgent {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownButtonCloseInputSorten.this.oContainerForInputListe.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
			
		}
		
	}
	
	
	
	/*
	 * button, der die eingabe eine ganzen liste von ANR1 erlaubt
	 */
	private class ownButtonZeigePopup_4_CopyANR1List extends MyE2_Button
	{
		LAND__MASK_RC_Sorten oThis = LAND__MASK_RC_Sorten.this;
		
		private MyE2_TextArea  	oText4Sorten = new MyE2_TextArea("", 400, 10000, 50);

		public ownButtonZeigePopup_4_CopyANR1List()
		{
			super(new MyE2_String("ANR1-Liste zum Export anzeigen"));
			
			this.setToolTipText(new MyE2_String("Liste mit ANR1-Einträgen der ausgewählten Sorten anzeigen (zum Kopieren) ...").CTrans());
			this.add_oActionAgent(new ownActionZeigePopup_4_CopyANR1List());
			this.add_GlobalAUTHValidator_AUTO(LAND__CONST.MASK_BT_ZEIGE_LISTE);

		}
		
		
		private class ownActionZeigePopup_4_CopyANR1List extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				
				E2_BasicModuleContainer oContainerForInputListe = new E2_BasicModuleContainer();
				oContainerForInputListe.set_bVisible_Row_For_Messages(false);
				
				StringBuffer cText = new StringBuffer();
				
				for (LandSorte oLS: oThis.vLandSorte) {
					if (oLS.oCB.isSelected()) {
						cText.append(oLS.recSorte.get_ANR1_cUF()+"\r");
					}
				}

				oText4Sorten.setText(cText.toString());
				
				oContainerForInputListe.add(oText4Sorten,E2_INSETS.I_10_10_10_10);
				
				E2_ComponentGroupHorizontal oGroupButtons = new E2_ComponentGroupHorizontal(0,
										new ownButtonCloseAnzeigeSorten(oContainerForInputListe),
										E2_INSETS.I_10_2_10_2);
				
				oContainerForInputListe.set_Component_To_ButtonPane(oGroupButtons);
				
				try
				{
					oContainerForInputListe.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(500),new Extent(500),new MyE2_String("Eingabe von IDs"));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				
			}
			
			

			private class ownButtonCloseAnzeigeSorten extends MyE2_Button {
				E2_BasicModuleContainer oContainerForInputListe = null;
				public ownButtonCloseAnzeigeSorten(E2_BasicModuleContainer containerForInputListe) {
					super(new MyE2_String("Schliessen"));
					this.oContainerForInputListe=containerForInputListe;
					this.add_oActionAgent(new closeContainer());
				}
				
				private class closeContainer extends XX_ActionAgent {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownButtonCloseAnzeigeSorten.this.oContainerForInputListe.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
			
		}
		
	}

	
	/*
	 * suchfeld fuer sorten
	 */
	private class ownSearchSorte extends MyE2_MaskSearchField {

		public ownSearchSorte() throws myException {
			super("JT_ARTIKEL.ID_ARTIKEL,JT_ARTIKEL.ANR1,JT_ARTIKEL.ARTBEZ1", 
					bibE2.cTO()+".JT_ARTIKEL ", 
					"ANR1",
					"  NVL(JT_ARTIKEL.AKTIV,'N')='Y'", 
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%') OR TO_CHAR(JT_ARTIKEL.ID_ARTIKEL)='#WERT#'",                 //NEU_09
					null,
					 null, 
					 "SELECT trim(  NVL(ANR1,'-')) || ' - ' || trim(  NVL(JT_ARTIKEL.ARTBEZ1,'-')) "+
					" from " + 
					bibE2.cTO() + ".JT_ARTIKEL " +
					"WHERE " +
    				"ID_ARTIKEL=#WERT#", E2_INSETS.I_0_0_2_0, false);
			
			this.set_bTextForAnzeigeVisible(false);
			this.set_oMaskActionAfterMaskValueIsFound(new ownActionAfterFound());
			this.get_buttonStartSearch().add_GlobalAUTHValidator_AUTO(LAND__CONST.MASK_BT_SEARCHSORTE);

		}

		@Override
		public E2_BasicModuleContainer get_ownContainer() throws myException {
			return new ownContainer();
		}
		
		private class ownContainer extends E2_BasicModuleContainer {
		}
		
		public class ownActionAfterFound extends XX_MaskActionAfterFoundNonDB {

			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(String ID_ARTIKEL, MyE2_MaskSearchField oSearchField,boolean bAfterAction) throws myException {
				if (LAND__MASK_RC_Sorten.this.get_bSorteVorhanden(ID_ARTIKEL)) {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Sorte bereits vorhanden..")));
				} else {
					LAND__MASK_RC_Sorten.this.vLandSorte.add(new LandSorte(ID_ARTIKEL));
					LAND__MASK_RC_Sorten.this.baue_grid(LAND__MASK_RC_Sorten.this.vLandSorte);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Sorte wurde zugefügt..")));
				}
				oSearchField.get_oTextFieldForSearchInput().setText("");
			}
		}
		
	}
	
}
