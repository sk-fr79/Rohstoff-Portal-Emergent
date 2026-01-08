package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ButtonMarker;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_TRAKT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PositionSorting;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_TPA_FUHRE_EXT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_TPA_FUHRE_ORT_EXT;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT;
import rohstoff.utils.VorgangTableNames;




public class KFIX_K_L_EXPANDER_4_ComponentMAP_NG extends XX_List_EXPANDER_4_ComponentMAP
{
	public static GridLayoutData GridLayoutDataLeft = null;
	public static GridLayoutData GridLayoutDataRight = null;
	public static GridLayoutData GridLayoutDataLeft2 = null;
	public static GridLayoutData GridLayoutDataRight2 = null;
	public static GridLayoutData GridLayoutDataRight_INNENRechts = null;
	public static GridLayoutData GridLayoutDataRight_INNENLinks = null;
	public static GridLayoutData GridLayoutData4PositionsAuflistung = null;
	public static GridLayoutData GridLayoutData4PreisAuflistung = null;

	public static GridLayoutData GridLayoutDataButtonGreenBack = null;
	public static GridLayoutData GridLayoutDataButtonRedBack = null;
	
	static
	{
		GridLayoutData left = 				new GridLayoutData();
		GridLayoutData right = 				new GridLayoutData();
		GridLayoutData left2 = 				new GridLayoutData();
		GridLayoutData right2 = 			new GridLayoutData();
		GridLayoutData right_innen = 		new GridLayoutData();
		GridLayoutData left_innen = 		new GridLayoutData();
		GridLayoutData posauflistung = 		new GridLayoutData();
		GridLayoutData preisauflistung = 	new GridLayoutData();
		
		GridLayoutData greenBack = 		new GridLayoutData();
		GridLayoutData redBack = 		new GridLayoutData();

		left.setInsets(E2_INSETS.I_4_2_4_2);
		left.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		right.setInsets(E2_INSETS.I_4_2_4_2);
		right.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
	
		left2.setInsets(E2_INSETS.I_1_1_1_1);
		left2.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));

		right2.setInsets(E2_INSETS.I_1_1_1_1);
		right2.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));

		right_innen.setInsets(new Insets(4,0,2,0));
		right_innen.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));

		left_innen.setInsets(new Insets(4,0,2,0));
		left_innen.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));

		posauflistung.setInsets(new Insets(4,0,2,0));
		posauflistung.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		
		preisauflistung.setInsets(new Insets(4,0,2,0));
		preisauflistung.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));

		greenBack.setInsets(E2_INSETS.I_1_1_1_1);
		greenBack.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		greenBack.setBackground(Color.GREEN);
		
		redBack.setInsets(E2_INSETS.I_1_1_1_1);
		redBack.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		redBack.setBackground(Color.RED);
		

		
		
		GridLayoutDataLeft=left;
		GridLayoutDataRight=right;
		GridLayoutDataLeft2=left2;
		GridLayoutDataRight2=right2;
		GridLayoutDataRight_INNENRechts=		right_innen;
		GridLayoutDataRight_INNENLinks=			left_innen;
		GridLayoutData4PositionsAuflistung = 	posauflistung;
		GridLayoutData4PreisAuflistung = 		preisauflistung;
		
		GridLayoutDataButtonGreenBack = 		greenBack;
		GridLayoutDataButtonRedBack = 			redBack;
	}
	
	public static E2_ResourceIcon oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	private IF_wrappedMulticomponentsInGrid wrapV = (Component... comps )-> {	E2_Grid g = new E2_Grid(); 
																				g.setOrientation(Grid.ORIENTATION_VERTICAL); 
																				for (Component c: comps) {g._a(c, new RB_gld()._ins(1)._center_mid());}
																				g._setRowHight(20,20,20,20,20);
																				return g._s(comps.length); };

	private MyE2_Row	  						oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private String 		 						ID_ROW_Unformated_VKOPF = null;
	private KFIX_K_L__ModulContainer		oKopfModulcontainerList = null;
	private VORGANGSART							belegTyp = null;
	
	private VEK<E2_ButtonMarker<Rec20>>   		v_markers = new VEK<>();

	
	public KFIX_K_L_EXPANDER_4_ComponentMAP_NG(KFIX_K_L__ModulContainer	 KopfModulcontainerList, E2_NavigationList NavigationList, VORGANGSART ek_vk_kontrakt) throws myException {
		super(NavigationList);
		
		int iOffset = 7;
		
		this.set_iLeftColumnOffset(iOffset);
		this.oKopfModulcontainerList = KopfModulcontainerList;

		this.belegTyp=ek_vk_kontrakt;

//		if(belegTyp==VORGANGSART.VK_KONTRAKT){
//			this.modulKenner = E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG;
//		}else{
//			this.modulKenner = E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG;
//		}

		
	}

	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException{
		this.ID_ROW_Unformated_VKOPF = cID_ROW_Unformated; 
		
		this.FillComponent();
		
		return this.oRow;
	}

	
	/**
	 * dynmisches feststellen des offsets zu den auska
	 */
	@Override
	public int get_iLeftColumnOffset() {
		int iOffset = 7;
		//feststellen, an welcher position der expand-button kommt
		Vector<String>  vVisibleHashes = this.get_oNavigationList().get_oComponentMAP__REF().get_vVisibleElementsInList();
		int i=0;
		for (String hash: vVisibleHashes) {
			i++;
			if (hash.equals("EXTENDER")) {
				iOffset=i;
				break;
			}
		}
		return iOffset;
	}

	
	private void FillComponent() throws myException 
	{
		//2011-12-05: hier pruefen, ob die Spalte BSK__CONST.BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE
		//            sichtbar ist, wenn ja, auch einen jumper einblenden
		
		this.oRow.removeAll();
		
		// sicherheitsabfrage
		if (bibALL.isEmpty(this.ID_ROW_Unformated_VKOPF)) {
			return;
		}

		boolean bZeigeRechnungsPositionenAn = this.oKopfModulcontainerList.get_bZeigeRechnungsPositionenAn();
		boolean bZeigeFuhrenAn              = this.oKopfModulcontainerList.get_bZeigeFuhrenListeAn();
		
		int iBreiteRGSpalte = (bZeigeRechnungsPositionenAn||bZeigeFuhrenAn?260:80);
		int iBreitePlanmenge = (bZeigeRechnungsPositionenAn||bZeigeFuhrenAn?200:100);
		
		//aenderung 2010-11-26: Positionsnummer muss angezeigt werden
		MyE2_Grid oGrid = new MyE2_Grid(bibE2.I(20,20,20,20,20,20,100,70,iBreitePlanmenge,70,70,70, iBreiteRGSpalte,80,150,120,100,90,110,60), MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());

		oGrid.setBackground(new E2_ColorLLLight());
		
		RECORD_VKOPF_KON recKOPF = new RECORD_VKOPF_KON(this.ID_ROW_Unformated_VKOPF);
		Rec20 recVkopf = new Rec20(_TAB.vkopf_kon)._fill(recKOPF);

		boolean bEK = (belegTyp.get_DBValue().equals(myCONST.VORGANGSART_EK_KONTRAKT));
		boolean vKopf_is_deleted, vKopf_is_Abgeschlossen, vKopf_is_fixierung;
		vKopf_is_deleted = recVkopf.get_fs_dbVal(VKOPF_KON.deleted,"N").equals("Y")?true:false;
		vKopf_is_Abgeschlossen = 	recVkopf.get_fs_dbVal(VKOPF_KON.abgeschlossen,"N").equals("Y");
		vKopf_is_fixierung = 		recVkopf.get_fs_dbVal(VKOPF_KON.ist_fixierung,"N").equals("Y");

		/*
		 * unterscheiden, ob mit eingeschalteten DELETED - saetzen oder ohne
		 */
		String cZusatzWhere = "";
		if (! this.oKopfModulcontainerList.get_oSelektor().get_oCB_ShowDeletedRows().isSelected())
			cZusatzWhere = "NVL(JT_VPOS_KON.DELETED,'N')='N' ";
			
		/*
		 * es kann auch sein, dass bei ausgeschalteten DELETED gerade einer geloescht wurde,
		 * der noch in der liste steht
		 */
		if (recKOPF.is_DELETED_YES()){
			cZusatzWhere = "";
		}
		
		
		/*
		 * weiterer zusatzwert fuer sortenselektion (entweder ganz oder kurz oder leer)
		 */
		String cSorteANR1=this.oKopfModulcontainerList.get_cActualSorteGanz();
		if (S.isEmpty(cSorteANR1))
		{
			cSorteANR1=this.oKopfModulcontainerList.get_cActualSorteKurz();
		}
		
		if (S.isFull(cSorteANR1))
		{
			cZusatzWhere = cZusatzWhere+" AND JT_VPOS_KON.ANR1 LIKE '"+cSorteANR1+"%'";
		}
		
		if (cZusatzWhere.startsWith(" AND "))
		{
			cZusatzWhere = cZusatzWhere.substring(5);
		}
				
		
		
		// ueberschrift
		try
		{
			
//			if (bJumperIsVisible)	{
//			oGrid.add(new MyE2_Label(BSK_K_LIST_EXPANDER_4_ComponentMAP_NG.oIconLeer),1,1,E2_INSETS.I_1_1_1_1,new Alignment(Alignment.CENTER, Alignment.TOP));       //jumper-button
//			} else {
			oGrid._add(new RB_lab(E2_ResourceIcon.get_RI("empty.png")), new RB_gld());
//			}
			
			
			if ((!vKopf_is_Abgeschlossen) && (!vKopf_is_deleted)) 	{
				oGrid._add(new KFIX_P_M_BT_new(oKopfModulcontainerList.get_MODUL_IDENTIFIER(),this, recVkopf), new RB_gld());
			} else if	((!vKopf_is_Abgeschlossen) && (!vKopf_is_deleted) && vKopf_is_fixierung){
				oGrid._add(new KFIX_P_M_BT_new(oKopfModulcontainerList.get_MODUL_IDENTIFIER(),this, recVkopf), new RB_gld());
			} else  {
				oGrid._add(new RB_lab(E2_ResourceIcon.get_RI("empty.png")), new RB_gld());
			}
		
		
			// ueberschrift
//			if(vKopf_is_fixierung){
//				oGrid.add(new KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung(recVkopf, this.get_oNavigationList()), new RB_gld());
//			}else{
				oGrid.add(new MyE2_Label("S"));				
//			}

			oGrid.add(new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer));   // fuer den zuordnungsknopf
			oGrid.add(new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer));   // fuer den abschliessen/oeffnen - knopf
			
			oGrid.add(new MyE2_Label("ÜL?"));             //schalter ueberlieferung yes/no
			
			oGrid.add(this.makeLabel("Pos/Best-Nr",true, true,false,false, null));
			
			oGrid.add(this.makeLabel("Menge",true, true,false,false, null));
			oGrid.add(this.makeLabel("PLAN",true, true,false,false, null));
			oGrid.add(this.makeLabel(bEK?"Lade":"Ablade",true, true,false,false, null));
			oGrid.add(this.makeLabel("Abzug",true, true,false,false, null));
			oGrid.add(this.makeLabel("REST",true, true,false,false, null));
			oGrid.add(this.makeLabel(bEK?"Gutschrift":"Rechnung",true, true,false,false, null));
			
			oGrid.add(this.makeLabel("Sortencode",false, true,false,false, null));
			oGrid.add(this.makeLabel("Bezeichnung",false, true,false,false, null));
			oGrid.add(this.makeLabel("Ladeort",false, true,false,false, null));
			oGrid.add(this.makeLabel("Gültig",false, true,false,false, null));
			oGrid.add(this.makeLabel("Einzel",true, true,false,false, null));
			oGrid.add(this.makeLabel("Gesamt",true, true,false,false, null));
			oGrid.add(this.makeLabel("IdPos",true, true,false,false, null));
			
			String cSortStr = "JT_VPOS_KON.ANR1,JT_VPOS_KON.ANR2";
			
			if (oKopfModulcontainerList.get_bSortListePOS_NR())
			{
				 cSortStr = "POSITIONSNUMMER";
			}
			
			
			for (int i=0;i<recKOPF.get_DOWN_RECORD_LIST_VPOS_KON_id_vkopf_kon(cZusatzWhere,cSortStr,false).get_vKeyValues().size();i++) {
				
				RECORD_VPOS_KON 		recVPOS_KON = 			recKOPF.get_DOWN_RECORD_LIST_VPOS_KON_id_vkopf_kon().get(i);
				RECORD_VPOS_KON_TRAKT 	recVPOS_KON_TRAKT = 	recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0);
				
				Rec20 recVposKon = new Rec20(_TAB.vpos_kon)._fill(recVPOS_KON);
				
				
				String cDatumKurz = recVPOS_KON_TRAKT.get___KETTE(bibALL.get_Vector("GUELTIG_VON", "GUELTIG_BIS"),"","","","-");
				cDatumKurz = cDatumKurz.substring(0,6)+ cDatumKurz.substring(8,17)+cDatumKurz.substring(19,21);
				String cWaehrung = recVPOS_KON.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN("?");

				
				//komponenten fuer die anzeige der artbez2
				Component oCompArtbez2 = new MyE2_Label("<artbez12>");
				
				if (this.oKopfModulcontainerList.get_bZeigeArtikelbez2() && S.isFull(recVPOS_KON.get_ARTBEZ2_cF_NN("")))
				{
					MyE2_Grid oGridHelp = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
					oGridHelp.add(this.makeLabel(recVPOS_KON.get_ARTBEZ1_cF_NN(""),false, false, recVPOS_KON.is_DELETED_YES(),true, null));
					
					String[] cTrennung = StringUtils.split(recVPOS_KON.get_ARTBEZ2_cF_NN(""),"\n");
					if (cTrennung != null && cTrennung.length>0)
					{
						for (int ii=0;ii<cTrennung.length;ii++)
						{
							oGridHelp.add(this.makeLabel(cTrennung[ii],false, false, recVPOS_KON.is_DELETED_YES(),true, null));
						}
					}
					oCompArtbez2=oGridHelp;
				}
				else
				{
					oCompArtbez2= this.makeLabel(recVPOS_KON.get_ARTBEZ1_cF_NN(""),false, false, recVPOS_KON.is_DELETED_YES(),true, null);
				}
				
				
				//2017-02-06:  neues label zu typdefinition
									
//				if (bJumperIsVisible) {
				oGrid._add(wrapV.grid(new KFIX_P_L_JUMPER_TO_Fuhre(recVposKon),	new E2_ButtonMarker<Rec20>(this.v_markers)._set_reference(recVposKon)), new RB_gld());       //jumper-button
//				} else {
//					oGrid._add(wrapV.grid(new RB_lab(),								co, new E2_ButtonMarker<Rec20>(this.v_markers)._set_reference(recVposKon)),	new RB_gld());   
//				}
				
				oGrid._add(wrapV.grid(
						new KFIX_P_M_BT_List2Mask(false, recVposKon.get_fs_dbVal(VPOS_KON.id_vpos_kon), this, oKopfModulcontainerList.get_MODUL_IDENTIFIER(), new Rec20(_TAB.vkopf_kon)._fill_id(recVposKon.get_fs_dbVal(VPOS_KON.id_vkopf_kon))),
						new KFIX_P_M_BT_List2Mask(true, recVposKon.get_fs_dbVal(VPOS_KON.id_vpos_kon), this, oKopfModulcontainerList.get_MODUL_IDENTIFIER(), new Rec20(_TAB.vkopf_kon)._fill_id(recVposKon.get_fs_dbVal(VPOS_KON.id_vkopf_kon))),
						new KFIX_P_M_BT_Delete(recVposKon.get_fs_dbVal(VPOS_KON.id_vpos_kon), this)
						), new RB_gld());

				
				
				// button-spalten
//				if(vKopf_is_fixierung){
				oGrid.add(new KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung(recVkopf, this.get_oNavigationList()), KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataLeft2);		
//				}else{	
//					oGrid.add(new moveUpButton(recVPOS_KON.get_ID_VPOS_KON_cUF(),recKOPF.is_ABGESCHLOSSEN_NO() && recVPOS_KON.is_DELETED_NO()).get_Comp(),KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataLeft2);
//				}
				
				KFIX_P_L_BT_Position_Anlage_Management anlageKnopf = new KFIX_P_L_BT_Position_Anlage_Management(this, recVposKon);
				
				
				//2012-08-06: zusaetzlicher button fuer upload
				//oGrid.add(new zuordnenButton(recVPOS_KON.get_ID_VPOS_KON_cUF(),recVPOS_KON.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL)).get_Comp(),BSK_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataLeft2);
				MyE2_Grid oGridInnen = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				oGridInnen.add(new zuordnenButton(recVPOS_KON.get_ID_VPOS_KON_cUF(),recVPOS_KON.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL)).get_Comp());
				oGridInnen.add(anlageKnopf
//						new BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_UPLOAD_NG(modulKenner,recVposKon.get_ufs_dbVal(VPOS_KON.id_vpos_kon, ""))
						);
				oGrid.add(oGridInnen,KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataLeft2);
				
				
				
				oGrid.add(new BT_LOCK_UNLOCK_KONTRAKTPOS(recVPOS_KON.get_ID_VPOS_KON_cUF(),recVPOS_KON_TRAKT.is_ABGESCHLOSSEN_YES(),recVPOS_KON.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL), 
						KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataButtonGreenBack,KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataButtonRedBack).get_Comp());
				
				cbUeberlieferungYesNo cbUeberliefer = new cbUeberlieferungYesNo(new Rec20(_TAB.vpos_kon)._fill(recVPOS_KON));
				if (cbUeberliefer.valid_GlobalValidation().get_bHasAlarms())
				{
					//cbUeberliefer.setEnabled(false);         //weil der haken schon gesetzt ist, wenn die validierung losgeht
				}
				oGrid.add(cbUeberliefer,KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataLeft2);

				int iNachkomma = 3;
				if (recVPOS_KON.get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_lValue(new Long(1))>100)
				{
					iNachkomma = 0;
				}
				
				BigDecimal dKontraktMenge = recVPOS_KON.get_ANZAHL_bdValue(new BigDecimal(0));
				RECORD_VPOS_KON_EXT recHelp = new RECORD_VPOS_KON_EXT(recVPOS_KON);
				BigDecimal[] bdHelp = recHelp.get_MengeGeliefertPlanEcht();
				
				BigDecimal dPlanEchtMenge = bdHelp[0];
				BigDecimal dEchtMenge  = 	bdHelp[1];
				BigDecimal dAbzug  = 		bdHelp[2];
				BigDecimal dRestmenge  = 	dKontraktMenge.subtract(dEchtMenge).add(dAbzug);
				BigDecimal dRechGut  = 		recHelp.get_MengeBerechnet();

				//in eine Spalte die Buchungsnummer des Kontrakts und die Bestellnummer
				MyE2_Grid oGridPos = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				oGridPos.add(this.makeLabel(recKOPF.get_BUCHUNGSNUMMER_cUF_NN("<ID:"+recKOPF.get_ID_VKOPF_KON_cUF()+">")+"-"+recVPOS_KON.get_POSITIONSNUMMER_cF_NN("??"),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
				oGridPos.add(new KFIX_P_L_BT_Edit_BestellNummer_NG(recVPOS_KON));
				oGrid.add(oGridPos,GridLayoutDataRight2);
				
				oGrid.add(this.makeLabel(MyNumberFormater.formatDez(recVPOS_KON.get_ANZAHL_dValue(new Double(0)),iNachkomma,true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
				
				
				
				if (bZeigeFuhrenAn)
				{
					RECLIST_VPOS_TPA_FUHRE  				recListFuhren = bEK?	recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N'","DATUM_AUFLADEN",true):
																					recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N'","DATUM_ABLADEN",true);
					
					RECLIST_VPOS_TPA_FUHRE_ORT     			recListFUO    = 		recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'","DATUM_LADE_ABLADE",true);
				
					//4 Vectoren mit spaltenwerten
					Vector<Component[]>  vFuhrenBuchungsnummerUndPlanmenge_und_datum = 	new Vector<Component[]>();
					Vector<Component[]>  vFuhrenRealmenge = 					new Vector<Component[]>();
					Vector<Component[]>  vFuhrenAbzug = 						new Vector<Component[]>();
					Vector<Component[]>  vFuhrenRechnungsKalk = 				new Vector<Component[]>();
					Vector<Component[]>  vFuhrenPosPreis = 						new Vector<Component[]>();
					
					//2011-12-02: postennummer anzeigen
					Vector<Component[]>  vFuhrenPostennummer = 					new Vector<Component[]>();
					
					
					Vector<String>  vSortNachDatum = new Vector<String>();
					
					//while (iteratorFU.hasNext())
					for (int k=0;k<recListFuhren.get_vKeyValues().size();k++)
					{
						__RECORD_VPOS_TPA_FUHRE_EXT     recFuhre = new __RECORD_VPOS_TPA_FUHRE_EXT(recListFuhren.get_vKeyValues().get(k));
						vFuhrenBuchungsnummerUndPlanmenge_und_datum.addAll(	recFuhre.get_v_Fuhrenummer_undPlanmenge_und_datum(bEK, iNachkomma,true));
						vFuhrenRealmenge.addAll(							recFuhre.get_v_RealMenge(bEK, iNachkomma,true));
						vFuhrenAbzug.addAll(								recFuhre.get_v_Abzuege(bEK, iNachkomma,true));
						vFuhrenRechnungsKalk.addAll(						recFuhre.get_v_Gutschrift_Rechnungs_Nummern_undZusaetze(iNachkomma, bEK,true).get("BELEGNUMMER"));
						vFuhrenPosPreis.addAll(						        recFuhre.get_v_Gutschrift_Rechnungs_Nummern_undZusaetze(iNachkomma, bEK,true).get("PREIS"));
						
						//2011-12-02: postennummer anzeigen
						vFuhrenPostennummer.addAll(						    recFuhre.get_v_Postennummer(bEK,true));
						
						vSortNachDatum.add(myDateHelper.ChangeNormalString2DBFormatString(bEK?
								recFuhre.get_DATUM_AUFLADEN_cF_NN(recFuhre.get_DATUM_ABHOLUNG_cF_NN("99.99.9999")):
								recFuhre.get_DATUM_ABLADEN_cF_NN(recFuhre.get_DATUM_ANLIEFERUNG_cF_NN("99.99.9999")))+"<P"+(vFuhrenRechnungsKalk.size()-1)+"P>");
					}

					
					for (int k=0;k<recListFUO.get_vKeyValues().size();k++)
					{
						__RECORD_VPOS_TPA_FUHRE_ORT_EXT     recFUO = new __RECORD_VPOS_TPA_FUHRE_ORT_EXT(recListFUO.get(k));
						vFuhrenBuchungsnummerUndPlanmenge_und_datum.addAll(	recFUO.get_v_Fuhrenummer_undPlanmenge_und_datum(iNachkomma,bEK,true));
						vFuhrenRealmenge.addAll(							recFUO.get_v_RealMenge(iNachkomma,true));
						vFuhrenAbzug.addAll(								recFUO.get_v_Abzuege(iNachkomma,true));
						vFuhrenRechnungsKalk.addAll(						recFUO.get_v_Gutschrift_Rechnungs_Nummern_undZusaetze(iNachkomma, bEK,true).get("BELEGNUMMER"));
						vFuhrenPosPreis.addAll(						        recFUO.get_v_Gutschrift_Rechnungs_Nummern_undZusaetze(iNachkomma, bEK,true).get("PREIS"));

						//2011-12-02: postennummer anzeigen
						vFuhrenPostennummer.addAll(						    recFUO.get_v_Postennummer(true));

						
						vSortNachDatum.add(myDateHelper.ChangeNormalString2DBFormatString(recFUO.get_DATUM_LADE_ABLADE_cF_NN("99.99.9999"))+"<P"+(vFuhrenRechnungsKalk.size()-1)+"P>");

					}

					GridLayoutData[] arrayLayout1 = new GridLayoutData[1];
					GridLayoutData[] arrayLayout2 = new GridLayoutData[2];
					GridLayoutData[] arrayLayout3 = new GridLayoutData[3];
//					GridLayoutData[] arrayLayout3a = new GridLayoutData[4];
					GridLayoutData[] arrayLayout4 = new GridLayoutData[2];


					
					arrayLayout1[0]=GridLayoutDataRight_INNENRechts;
					
					arrayLayout2[0]=GridLayoutDataRight_INNENLinks;
					arrayLayout2[1]=GridLayoutDataRight_INNENRechts;
					
					arrayLayout3[0]=GridLayoutDataRight_INNENLinks;
					arrayLayout3[1]=GridLayoutDataRight_INNENRechts;
					arrayLayout3[2]=GridLayoutDataRight_INNENRechts;
					
					arrayLayout4[0]=GridLayoutDataRight_INNENRechts;
					arrayLayout4[1]=GridLayoutDataRight_INNENRechts;

//					arrayLayout3a[0]=GridLayoutDataRight_INNENLinks;
//					arrayLayout3a[1]=GridLayoutDataRight_INNENRechts;
//					arrayLayout3a[2]=GridLayoutDataRight_INNENRechts;
//					arrayLayout3a[3]=new RB_gld()._center_mid();

					
					//2011-12-02: 
					GridLayoutData[] arrayLayout5 = new GridLayoutData[1];
					arrayLayout5[0]=GridLayoutDataRight_INNENLinks;
					
					int[] iWidth1 = {60};
					int[] iWidth2 = {120,100,100};
					int[] iWidth3 = {120,100,100};
					int[] iWidth4 = {100,30};

					//2011-12-02: 
					int[] iWidth5 = {100};
					
					
					Collections.sort(vSortNachDatum);
					
					if (vFuhrenBuchungsnummerUndPlanmenge_und_datum.size()>0)   //alle Vectoren sind gleich lang !!!
					{
						//spalte 1:
						oGrid.add(this.makeHelpGrid(this.makeLabel(MyNumberFormater.formatDez(dPlanEchtMenge, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
								  					this.makeBlockGrid(vFuhrenBuchungsnummerUndPlanmenge_und_datum, vSortNachDatum, arrayLayout3,iWidth2),GridLayoutDataRight2),GridLayoutDataRight2);
						
						oGrid.add(this.makeHelpGrid(this.makeLabel(MyNumberFormater.formatDez(dEchtMenge, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
			  										this.makeBlockGrid(vFuhrenRealmenge, vSortNachDatum, arrayLayout1,iWidth1),GridLayoutDataRight2),GridLayoutDataRight2);
						
						oGrid.add(this.makeHelpGrid(this.makeLabel(MyNumberFormater.formatDez(dAbzug, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
			  										this.makeBlockGrid(vFuhrenAbzug,vSortNachDatum,  arrayLayout1,iWidth1),GridLayoutDataRight2),GridLayoutDataRight2);
						
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRestmenge, iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null),GridLayoutDataRight2);
						
						oGrid.add(this.makeHelpGrid(this.makeLabel(MyNumberFormater.formatDez(dRechGut, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
			  										this.makeBlockGrid(vFuhrenRechnungsKalk, vSortNachDatum, arrayLayout3,iWidth3),GridLayoutDataRight2),GridLayoutDataRight2);

						oGrid.add(this.makeLabel(recVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","","","-"),false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						
						oGrid.add(oCompArtbez2,GridLayoutDataRight_INNENLinks);
						
						
						oGrid.add(this.makeLabel(recVPOS_KON_TRAKT.get_LIEFERORT_cUF_NN("-"),false, false, recVPOS_KON.is_DELETED_YES(),true, null));

						
						//2011-12-02: hier werden die postennummern eingstellt (unterhalb der lieferorte)
						//oGrid.add(this.makeLabel(cDatumKurz,false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeHelpGrid(
								     this.makeLabel(cDatumKurz,false, false, recVPOS_KON.is_DELETED_YES(),false, null), 
								     this.makeBlockGrid(vFuhrenPostennummer, vSortNachDatum, arrayLayout5,iWidth5),GridLayoutDataLeft2),GridLayoutDataLeft2);
						//ende aenderung
						
						oGrid.add(this.makeHelpGrid(this.makeLabel(recVPOS_KON.get_EINZELPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
													this.makeBlockGrid(vFuhrenPosPreis, vSortNachDatum, arrayLayout4,iWidth4),GridLayoutDataRight2),GridLayoutDataRight2);
						
						
						oGrid.add(this.makeLabel(recVPOS_KON.get_GESAMTPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						//2012-04-16: es wird auch letzte aenderung angezeigt
						//oGrid.add(this.makeLabel(recVPOS_KON.get_ID_VPOS_KON_cF_NN(""),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.get_ID_plus_letzteAenderung(recVPOS_KON),GridLayoutDataRight2);

					}
					else
					{
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dPlanEchtMenge, 	iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dEchtMenge, 		iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dAbzug, 			iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRestmenge, 		iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRechGut, 			iNachkomma, 	true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","","","-"),false, false, recVPOS_KON.is_DELETED_YES(),false, null));

						oGrid.add(oCompArtbez2,GridLayoutDataRight_INNENLinks);
						
						oGrid.add(this.makeLabel(recVPOS_KON_TRAKT.get_LIEFERORT_cUF_NN(""),false, false, recVPOS_KON.is_DELETED_YES(),true, null));
						oGrid.add(this.makeLabel(cDatumKurz,false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get_EINZELPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get_GESAMTPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));

						
						//2012-04-16: es wird auch letzte aenderung angezeigt
						//oGrid.add(this.makeLabel(recVPOS_KON.get_ID_VPOS_KON_cF_NN(""),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.get_ID_plus_letzteAenderung(recVPOS_KON),GridLayoutDataRight2);


					}
				}
				else
				{
					//fuhrenanzeigen nur die mengensummen, keine weitern fuhreninfos
					//rechnungsmenge ist vorhanden, dann werden die Rechnungsnummern eingetragen
					if (dRechGut.compareTo(new BigDecimal(0))!=0 && bZeigeRechnungsPositionenAn)
					{
						
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dPlanEchtMenge, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dEchtMenge, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dAbzug, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRestmenge, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						int[] iSpalten  =  {120,100,100};
						int[] iSpalten2 =  {100,30};
						HashMap<String,MyE2_Grid> hmSpaltenGrids = recHelp.get_grid_Gutschrift_Rechnungs_Nummern_undZusaetze(GridLayoutDataRight_INNENLinks,GridLayoutDataRight_INNENRechts,iSpalten,iSpalten2);

						oGrid.add(this.makeHelpGrid(this.makeLabel(MyNumberFormater.formatDez(dRechGut, iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null), 
								                    hmSpaltenGrids.get("RECHINFOS"),GridLayoutDataRight2),GridLayoutData4PositionsAuflistung);

						oGrid.add(this.makeLabel(recVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","","","-"),false, false, recVPOS_KON.is_DELETED_YES(),false, null));						

						oGrid.add(oCompArtbez2,GridLayoutDataRight_INNENLinks);
						
						oGrid.add(this.makeLabel(recVPOS_KON_TRAKT.get_LIEFERORT_cUF_NN(""),false, false, recVPOS_KON.is_DELETED_YES(),true, null));
						oGrid.add(this.makeLabel(cDatumKurz,false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						oGrid.add(this.makeHelpGrid(this.makeLabel(recVPOS_KON.get_EINZELPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null),
													hmSpaltenGrids.get("PREIS"),GridLayoutDataRight2),GridLayoutData4PreisAuflistung);
						
						oGrid.add(this.makeLabel(recVPOS_KON.get_GESAMTPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						//2012-04-16: es wird auch letzte aenderung angezeigt
						//oGrid.add(this.makeLabel(recVPOS_KON.get_ID_VPOS_KON_cF_NN(""),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.get_ID_plus_letzteAenderung(recVPOS_KON),GridLayoutDataRight2);
						
					}
					else
					{
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dPlanEchtMenge, 	iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dEchtMenge, 		iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dAbzug, 			iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRestmenge, 		iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(MyNumberFormater.formatDez(dRechGut, 			iNachkomma, true),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","","","-"),false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						oGrid.add(oCompArtbez2,GridLayoutDataRight_INNENLinks);
						
						oGrid.add(this.makeLabel(recVPOS_KON_TRAKT.get_LIEFERORT_cUF_NN(""),false, false, recVPOS_KON.is_DELETED_YES(),true, null));
						oGrid.add(this.makeLabel(cDatumKurz,false, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get_EINZELPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.makeLabel(recVPOS_KON.get_GESAMTPREIS_FW_cF_NN("")+" "+cWaehrung,true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						
						//2012-04-16: es wird auch letzte aenderung angezeigt
						//oGrid.add(this.makeLabel(recVPOS_KON.get_ID_VPOS_KON_cF_NN(""),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
						oGrid.add(this.get_ID_plus_letzteAenderung(recVPOS_KON),GridLayoutDataRight2);

					}
				}
			}
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			oGrid.removeAll();
			oGrid.add(new MyE2_Label(new MyE2_String("Integrity-check-Error !!")),8,E2_INSETS.I_5_0_5_0);
		}

		this.oRow.add(oGrid,new Insets(0));
	}

	
	//2012-04-16: in der liste wird unter der id der position noch klein das letzte aenderungsdatum und -kuerzel angezeigt
	private MyE2_Grid get_ID_plus_letzteAenderung(RECORD_VPOS_KON recVPOS_KON) throws myException
	{
		MyE2_Grid  oGridRueck = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		oGridRueck.add(this.makeLabel(recVPOS_KON.get_ID_VPOS_KON_cF_NN(""),true, false, recVPOS_KON.is_DELETED_YES(),false, null));
		
		String cDatum = recVPOS_KON.get_LETZTE_AENDERUNG_cF_NN("--.--.----");
		if (cDatum.length()==10)
		{
			cDatum = cDatum.substring(0,6)+cDatum.substring(8, 10);
		}
		
		MyE2_Label oLabZusatz = new MyE2_Label(cDatum+" ("+recVPOS_KON.get_GEAENDERT_VON_cF_NN("??")+")");
		oLabZusatz.setFont(new E2_FontItalic(-2));
		oLabZusatz.setForeground(Color.DARKGRAY);
		oGridRueck.add(oLabZusatz, MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_0_0_0_0));
		return oGridRueck;
	}
	
	
	
	//2011-02-18: sortierung nach datum aller positionen, fuhren und fuhrenort zusammen
	private MyE2_Grid makeBlockGrid(Vector<Component[]> vComponents, Vector<String> vSort, GridLayoutData[] arrayLayoutdata, int[] iColWidth) throws myException
	{
		MyE2_Grid oGrid = new MyE2_Grid(iColWidth, MyE2_Grid.STYLE_GRID_BORDER(Color.BLACK));
		
		
		if (vComponents.size()!=vSort.size())
		{
			throw new myException("Error: Vectors are not the same lenght !!");
		}
		
		if (vComponents.size()>0)
		{
			int iSize = vComponents.get(0).length;
			
			if (arrayLayoutdata.length!=iSize || iColWidth.length!=iSize)
			{
				throw new myException("Array-lenght not correct !!");
			}
			
			oGrid.setSize(iSize);
			
			for (int i=0;i<vSort.size();i++)
			{
				Integer iReihe = S.get_InWertInStringCode(new StringBuffer(vSort.get(i)), "P");
				if (iReihe==null)
				{
					throw new myException("Error: Position-Value not found !!");
				}
				
				Component[] array = vComponents.get(iReihe);
				if (array.length != iSize)
				{
					throw new myException("Array-lenght not correct !!");
				}
				else
				{
					for (int l=0;l<array.length;l++)
					{
						try
						{
						oGrid.add(array[l], arrayLayoutdata[l]);
						}
						catch (Exception ex)
						{
							System.out.println("--halt--");
						}
					}
				}
			}
		}
		return oGrid;	
	}
	


	
	
	private MyE2_Grid makeHelpGrid(Component oComp, MyE2_Grid gridInnen, GridLayoutData oLayout)
	{
		MyE2_Grid gridHelp = new MyE2_Grid(1,0);
		gridHelp.add(oComp,oLayout);
		gridHelp.add(gridInnen, oLayout);
		return gridHelp;
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			KFIX_K_L_EXPANDER_4_ComponentMAP_NG oCopy = 
				new KFIX_K_L_EXPANDER_4_ComponentMAP_NG(this.oKopfModulcontainerList,this.get_oNavigationList(),this.belegTyp);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
	}


	/**
	 * 
	 * @param cText
	 * @param bRechtsbuendig
	 * @param bIsTitle
	 * @param bIsDeleted
	 * @param bUmbruch
	 * @param oColText
	 * @return
	 */
	private MyE2_Label makeLabel(String cText,boolean bRechtsbuendig, boolean bIsTitle, boolean bIsDeleted, boolean bUmbruch, Color oColText)
	{
		String ccText = "";
		
		if (cText.equals("###"))
			ccText = "-";
		else
			ccText = cText;
		
		MyE2_Label oLabRueck = null;
		
		E2_Font oDelFont = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);

		
		if (bIsTitle)
		{
			oLabRueck = new MyE2_Label(new MyE2_String(ccText));
			oLabRueck.setFont(new E2_FontItalic(-2));
		}
		else
		{
			oLabRueck = new MyE2_Label(ccText);
			if (bIsDeleted)
				oLabRueck.setFont(oDelFont);
			else
				oLabRueck.setFont(new E2_FontPlain());
		}
		
		if (bRechtsbuendig)
		{
			oLabRueck.setLayoutData(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataRight);
		}
		else
		{
			oLabRueck.setLayoutData(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.GridLayoutDataLeft);
		}
			
		
		if (bUmbruch)
		{
			oLabRueck.setLineWrap(true);
		}
		
		if (oColText!=null)
		{
			oLabRueck.setForeground(oColText);
		}
		
		return oLabRueck;
	}


	
	
	public void refreshDaughterComponent() throws myException
	{
		this.FillComponent();
	}
	
	
	
	

	
	
	
	
	private class moveUpButton extends MyE2_Button
	{
		private boolean bActiv = false;

		public moveUpButton(String cID_VPOS, boolean Activ)
		{
			super(E2_ResourceIcon.get_RI("moveup.png"));
			this.bActiv = Activ;
			this.EXT().set_C_MERKMAL(cID_VPOS);
			this.add_oActionAgent(new ownActionAgentMoveUp());
			
			this.setToolTipText(new MyE2_String("Reihenfolge verändern ...").CTrans());
			
			this.add_GlobalValidator(new ownValidator());
		}
		
		public Component get_Comp()
		{
			return this.bActiv?this:new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer);
		}
		
		private class ownValidator extends XX_ActionValidator
		{
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				String cID_VKOPF_KON = KFIX_K_L_EXPANDER_4_ComponentMAP_NG.this.ID_ROW_Unformated_VKOPF;
				
				try 
				{
					String cQuery1 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_EK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
					String cQuery2 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_VK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
					String cQuery3 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";				
					
					int i1 = new Integer(bibDB.EinzelAbfrage(cQuery1)).intValue();
					int i2 = new Integer(bibDB.EinzelAbfrage(cQuery2)).intValue();
					int i3 = new Integer(bibDB.EinzelAbfrage(cQuery3)).intValue();
					
					int iAlle = i1+i2+i3;

					if (iAlle>0)
					{
						oMV.add(new MyE2_Alarm_Message("Umsortieren ist nur solange möglich, solange keine Fuhre auf den Vertrag gebucht ist!"));
					}
					
				} 
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
					oMV.add(new MyE2_Alarm_Message("Fehler beim Feststellen der Buchungszahlen !"));
				}
				
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
			{
				return null;
			}
			
		}
		
	}
	
	
	private class ownActionAgentMoveUp extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_Button oButtonSource = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			String cID = oButtonSource.EXT().get_C_MERKMAL();

			if (bibALL.isEmpty(cID))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Sortieren !"));
			}
			else
			{
				try
				{
					new BS_PositionSorting(cID,BS_PositionSorting.DOWN,new VorgangTableNames(belegTyp.get_DBValue()));
					KFIX_K_L_EXPANDER_4_ComponentMAP_NG.this.refreshDaughterComponent();
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Reihenfolge wurde geändert"));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}

	
	
	private class zuordnenButton extends MyE2_Button
	{
		private boolean bActiv = false;

		public zuordnenButton(String cID_POS_UNFORMATED, boolean Activ)
		{
			super(E2_ResourceIcon.get_RI("connect_small.png"), false);
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
																"ZUORDNUNG_EK_VK_KONTRAKTE"));

			this.bActiv = Activ;
			this.EXT().set_C_MERKMAL(cID_POS_UNFORMATED);
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Kontraktposition wurde bereits gelöscht !")));
//2014-01-31			this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_POSITION_OFFEN);
			
			this.setToolTipText(new MyE2_String("Kontraktzuordnungen festlegen (Vertragsclearing)").CTrans());
			
			this.add_oActionAgent(new actionAgentShowZuordnung());
		}
		
		public Component get_Comp()
		{
			return this.bActiv?this:new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer);
		}

	}
	
	
	private class actionAgentShowZuordnung extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_Button 	oButtonZuordnung = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			String 			cID_VPOS_KON = 	((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_C_MERKMAL();

			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(oButtonZuordnung.EXT().get_C_MERKMAL()));

			if (bibMSG.get_bIsOK())
			{
				try
				{
					new own_ZUORDNUNG_EK_VK(cID_VPOS_KON);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(),false);
				}
			}
		}
	}


	
	private class own_ZUORDNUNG_EK_VK extends KFIX_P_L_ZUORDNUNG_EK_VK
	{

		public own_ZUORDNUNG_EK_VK(String ID_VPOS_KON) throws myException
		{
			super(ID_VPOS_KON);
		}

		@Override
		public void doAfterSave() throws myException
		{
			KFIX_K_L_EXPANDER_4_ComponentMAP_NG.this.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
		}
		
	}
	
	
	
	// button zum oeffnen/schliessen einer position
	private class BT_LOCK_UNLOCK_KONTRAKTPOS extends MyE2_Button
	{
		private String cID_VPOS_KON = null;
		private boolean bActiv = false;

		private GridLayoutData   GL_Green = null;
		private GridLayoutData   GL_Red = null;
		
		
		public BT_LOCK_UNLOCK_KONTRAKTPOS(	String 					ID_VPOS_KON,
											boolean 				gesperrtAmStart,
											boolean                 Activ,
											GridLayoutData          oGL_Open,
											GridLayoutData          oGL_Closed
											) throws myException
		{
			super(E2_ResourceIcon.get_RI("unlocked.png"));
		
			this.GL_Green = oGL_Open;
			this.GL_Red = oGL_Closed;
			
			this.cID_VPOS_KON = ID_VPOS_KON;
			this.bActiv = Activ;
		
			this.setLayoutData(oGL_Open);
			
			if (gesperrtAmStart)
			{
				this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
				this.setLayoutData(oGL_Closed);
			}

			//hier folgt die ausfuehrung nicht auf die tabelle jt_vpos_kon sondern auf jt_vpos_kon_trakt, deshalb ist die validierung auch dort durchzufuehren
			this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
															" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
															" ANZAHL IS NOT NULL AND EINZELPREIS IS NOT NULL AND "+
															" ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON_TRAKT=#WERT#)",
															bibALL.get_Array("1"),
															true, new MyE2_String("Nur bei komplett ausgefüllten Mengenpositionen erlaubt !")));

			this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
															" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
															" NVL(DELETED,'N')='N' AND "+
															" ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON_TRAKT=#WERT#)",
															bibALL.get_Array("1"),
															true, new MyE2_String("Die Position wurde bereits gelöscht !")));

			this.add_oActionAgent(new ownActionAgentToggleOeffnenSchliessen());
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),"LOCK_UNLOCK_KONTRAKTPOS"));
			this.setToolTipText(new MyE2_String("Öffnen/Schliessen einer Kontraktposition").CTrans());
		}
		
		
		public Component get_Comp()
		{
			return this.bActiv?this:new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer);
		}

		
		private class ownActionAgentToggleOeffnenSchliessen extends ButtonActionAgent_TOGGLE_Y_N
		{
			public ownActionAgentToggleOeffnenSchliessen() throws myException
			{
				super(new MyE2_String("Öffnen/Schliessen Kontrakt-Position"),
										null,
										"ABGESCHLOSSEN",
										"JT_VPOS_KON_TRAKT",
										"ID_VPOS_KON_TRAKT");
				
				this.set_oAddonDialogBuilder(new KFIX__ADD_ON_DIALOG_BUILDER_Benachrichtige_NG()
				{

					@Override
					public void fill_v_ID_VPOS_KON_ToToggle() throws myException
					{
						this.get_vIDsToToggle().removeAllElements();
						this.get_vIDsToToggle().add(BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON);
					}
					
				});
				
			}
			
			
			/*
			 * methode, um die id aus der liste mit der id, die geaendert wird,
			 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
			 */
			public Vector<String> get_IDS_FOR_Toggle() throws myException
			{
				String cID_VPOS_KON_TRAKT = bibDB.EinzelAbfrage("SELECT ID_VPOS_KON_TRAKT FROM "+
									bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON);
				if (!bibALL.isEmpty(cID_VPOS_KON_TRAKT) && bibALL.isLong(cID_VPOS_KON_TRAKT))
				{
					return bibALL.get_Vector(cID_VPOS_KON_TRAKT);	
				}
				else
				{
					throw new myException("Error finding ID_VPOS_KON_TRAKT ");
				}
			}


			public MyE2_MessageVector CheckIdToToggle(Vector<String> vID_UnformatedToDelete){return null;}

			public void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException 
			{
				// knopf richtig stellen
				String Abgeschlossen = bibDB.EinzelAbfrage("SELECT NVL(ABGESCHLOSSEN,'N') FROM "+
						bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON);
				if (Abgeschlossen.equals("Y"))
				{
					BT_LOCK_UNLOCK_KONTRAKTPOS.this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
					BT_LOCK_UNLOCK_KONTRAKTPOS.this.setLayoutData(BT_LOCK_UNLOCK_KONTRAKTPOS.this.GL_Red);
				}
				else if (Abgeschlossen.equals("N"))
				{
					BT_LOCK_UNLOCK_KONTRAKTPOS.this.setIcon(E2_ResourceIcon.get_RI("unlocked.png"));
					BT_LOCK_UNLOCK_KONTRAKTPOS.this.setLayoutData(BT_LOCK_UNLOCK_KONTRAKTPOS.this.GL_Green);
				}
				else
				{
					throw new myException("Error querying the correct status after toggle !");
				}
			}
			
			public void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException {}
			public Vector<String> get_vSQL_After_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}
			public Vector<String> get_vSQL_Before_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}

		}	

	}
	
	
	
	
	private class cbUeberlieferungYesNo extends MyE2_CheckBox
	{
		private Rec20  	recVPOS = null;
		private Rec20  	recVPOS_KON = null;

		public cbUeberlieferungYesNo(Rec20  rec_VPOS) throws myException
		{
			super();
			this.recVPOS = rec_VPOS;
			this.recVPOS_KON = this.recVPOS.get_down_reclist20(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0);//get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0);

			this.setSelected(this.recVPOS_KON.get_fs_dbVal(VPOS_KON_TRAKT.ueberliefer_ok).equals("Y"));

			this.add_oActionAgent(new ownActionAgent());

			GridLayoutData oGLCenter = new GridLayoutData();
			oGLCenter.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));

			this.setLayoutData(oGLCenter);
			this.setToolTipText(new MyE2_String("Überlieferung erlaubt ?").CTrans());

			this.add_GlobalAUTHValidator_AUTO("UEBERLIEFERUNG_EIN_AUS");

		}


		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				Rec20  	recVPOS_KON = cbUeberlieferungYesNo.this.recVPOS_KON;
				recVPOS_KON._rebuild();

				boolean isAbgechlossen = recVPOS_KON.get_fs_dbVal(VPOS_KON_TRAKT.abgeschlossen).equals("Y");
				boolean isDeleted = recVPOS_KON.get_fs_dbVal(VPOS_KON_TRAKT.deleted).equals("Y");
				if (isAbgechlossen || isDeleted)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist bereits geschlossen/gelöscht worden !!"));
					cbUeberlieferungYesNo.this.setSelected(recVPOS_KON.get_fs_dbVal(VPOS_KON_TRAKT.ueberliefer_ok).equals("Y"));
				}
				else
				{
					recVPOS_KON.set_NewValueForDatabase(VPOS_KON_TRAKT.ueberliefer_ok.fieldName(), recVPOS_KON.get_fs_dbVal(VPOS_KON_TRAKT.ueberliefer_ok).equals("Y")?"N":"Y");
					MyE2_MessageVector mv = new MyE2_MessageVector();

					recVPOS_KON.get_sql_4_save(false, mv);
					recVPOS_KON._SAVE(true, mv);
					bibMSG.add_MESSAGE(mv);

					recVPOS_KON._rebuild();

					cbUeberlieferungYesNo.this.setSelected(recVPOS_KON.is_yes_db_val(VPOS_KON_TRAKT.ueberliefer_ok));
				}
			}
		}
		
		
	}




	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}
	
	
	public VEK<E2_ButtonMarker<Rec20>> get_v_markers() {
		return v_markers;
	}

	
	
}
	