package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;





public class INFO_BLOCK_Kontrakte_NG extends MyE2_Grid 
{
	private cbAuswahlWasZeigt cb_1_EK = null;
	private cbAuswahlWasZeigt cb_2_VK = null;

	private MyE2_ContainerEx  oContainerEx = new MyE2_ContainerEx();
	
	private MyE2_TextField    oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png")); 

	private ownRECLIST_VPOS_KON reclistVPOS_KON_EK  = null;
	private ownRECLIST_VPOS_KON reclistVPOS_KON_VK  = null;

	private Vector<Component> vSelcomponents = new Vector<Component>();

	private FS__Adress_Info_Zentrum_NG   oZentrum = null;

	private String            cActualSortKennerEK = null;
	private String            cActualSortKennerVK = null;
	private boolean           bActualSort_UP_EK = true;
	private boolean           bActualSort_UP_VK = true;
	
	private Vector<Component[]>   vComponentZeilen = new Vector<Component[]>();
	
	
	public INFO_BLOCK_Kontrakte_NG(FS__Adress_Info_Zentrum_NG oInfoZentrum) throws myException 
	{
		super(FSI_CONST_NG.vKONTRAKT_SORTERS.size()+4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));

		oZentrum = oInfoZentrum;
		
		this.cb_1_EK=				new cbAuswahlWasZeigt("EK-Kontrakt-Pos",	new MyE2_String("Zeigt EK-Kontrakt-Positionen an") ,	 	true, false);
		this.cb_2_VK=				new cbAuswahlWasZeigt("VK-Kontrakt-Pos",	new MyE2_String("Zeigt EK-Kontrakt-Positionen an") ,		true, false);

		
		this.cb_1_EK.add_oActionAgent(			new actionNeubauFuhrenGrid());
		this.cb_2_VK.add_oActionAgent(			new actionNeubauFuhrenGrid());
		this.oBT_Refresh.add_oActionAgent(		new actionCheckHighlight());
		
		//dann status speichern
		this.cb_1_EK.add_oActionAgent(			new actionSaveStatus());
		this.cb_2_VK.add_oActionAgent(			new actionSaveStatus());
		this.oBT_Refresh.add_oActionAgent(		new actionSaveStatus());
		
		vSelcomponents.add(this.cb_1_EK);
		vSelcomponents.add(this.cb_2_VK);
		vSelcomponents.add(this.oTF_Suche);
		vSelcomponents.add(this.oBT_Refresh);
		
		this.restore_status_der_selektionen_und_sorters();
	}
	
	
	
	
	public void set_ActualSortKennerEK(String ActualSortKennerEK)
	{
		this.cActualSortKennerEK = ActualSortKennerEK;
	}


	public void set_ActualSortKennerVK(String ActualSortKennerVK)
	{
		this.cActualSortKennerVK = ActualSortKennerVK;
	}


	public void set_bActualSort_UP_EK(boolean ActualSort_UP_EK)
	{
		this.bActualSort_UP_EK = ActualSort_UP_EK;
	}


	public void set_bActualSort_UP_VK(boolean ActualSort_UP_VK)
	{
		this.bActualSort_UP_VK = ActualSort_UP_VK;
	}


	public String get_ActualSortKennerEK()
	{
		return cActualSortKennerEK;
	}

	public String get_ActualSortKennerVK()
	{
		return cActualSortKennerVK;
	}

	public boolean get_bActualSort_UP_EK()
	{
		return bActualSort_UP_EK;
	}

	public boolean get_bActualSort_UP_VK()
	{
		return bActualSort_UP_VK;
	}



	
	
	
	public MyE2_ContainerEx get_oContainerEx() 
	{
		return oContainerEx;
	}


	public void __aufbau1() throws myException
	{
		
		RECORD_ADRESSE recADRESSE = this.oZentrum.get_recADRESSE();

		
		String cQueryBase = "SELECT JT_VPOS_KON.*       FROM "+bibE2.cTO()+".JT_VPOS_KON  " +
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VPOS_KON_TRAKT ON (JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON) "+
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VKOPF_KON ON (JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON) "+
		 					" WHERE NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND NVL(JT_VPOS_KON.DELETED,'N')='N' AND NVL(JT_VKOPF_KON.DELETED,'N')='N' AND JT_VKOPF_KON.ID_ADRESSE="+recADRESSE.get_ID_ADRESSE_cUF();
		
		String cQueryEK = cQueryBase+" AND JT_VKOPF_KON.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_EK_KONTRAKT)+" ORDER BY JT_VPOS_KON.ANR1,JT_VPOS_KON_TRAKT.GUELTIG_VON";
		String cQueryVK = cQueryBase+" AND JT_VKOPF_KON.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_VK_KONTRAKT)+" ORDER BY JT_VPOS_KON.ANR1,JT_VPOS_KON_TRAKT.GUELTIG_VON";
		

		//nur die noetigen records aufbauen
		this.reclistVPOS_KON_EK = this.cb_1_EK.isSelected()?new ownRECLIST_VPOS_KON(cQueryEK):new ownRECLIST_VPOS_KON();
		this.reclistVPOS_KON_VK = this.cb_2_VK.isSelected()?new ownRECLIST_VPOS_KON(cQueryVK):new ownRECLIST_VPOS_KON();
		
		this.__aufbau2();
	}
	

	/*
	 * wird auch zur sortierung verwendet
	 */
	public void __aufbau2() throws myException
	{
		this.removeAll();
		
		this.vComponentZeilen.removeAllElements();
		
		
		Vector<FS___RECORD_VPOS_KON_EXT_NG> vVectorEK = this.reclistVPOS_KON_EK.get_standard_order_vector();
		Vector<FS___RECORD_VPOS_KON_EXT_NG> vVectorVK = this.reclistVPOS_KON_VK.get_standard_order_vector();
		
		if (S.isFull(this.cActualSortKennerEK))
		{
			vVectorEK = this.reclistVPOS_KON_EK.get_order_vector(this.cActualSortKennerEK, this.bActualSort_UP_EK);
		}

		if (S.isFull(this.cActualSortKennerVK))
		{
			vVectorVK = this.reclistVPOS_KON_VK.get_order_vector(this.cActualSortKennerVK, this.bActualSort_UP_VK);
		}

		
		if (vVectorEK.size()>0 && this.cb_1_EK.isSelected())
		{
			this.add_Block(vVectorEK, "EK");
		}

		if(vVectorEK.size()==0 && vVectorVK.size()==0){
			_aufbau_empty_grid();
		}
		
		if (vVectorVK.size()>0 && this.cb_2_VK.isSelected())
		{
			if (vVectorVK.size()>0 && this.cb_1_EK.isSelected())
			{
				//trenner einbauen
				this.add(new Separator(),this.getSize(),E2_INSETS.I_1_1_1_1);
			}

			this.add_Block(vVectorVK, "VK");

		}
		
		//this.__CheckHighLight();
	}
	
	
	private void _aufbau_empty_grid(){
		int iSize = -2;
		
		this.add(new RB_lab("Typ")._i()._fsa(iSize), 		new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Buch-Nr.")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Fr.Best-Nr.")._i()._fsa(iSize),new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Beleg.Dat.")._i()._fsa(iSize), new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Gültig")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Menge")._i()._fsa(iSize), 		new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("FU-Plan")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("FU-LadeM.")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Rest.")._i()._fsa(iSize), 		new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Gutsch.")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("ANR1-2")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Artbez1")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Artbez2")._i()._fsa(iSize), 	new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("Preis")._i()._fsa(iSize), 		new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("-")._i()._fsa(iSize), 			new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("-")._i()._fsa(iSize), 			new RB_gld()._col(new E2_ColorDDark()));
		this.add(new RB_lab("-")._i()._fsa(iSize), 			new RB_gld()._col(new E2_ColorDDark()));
		
		this.add(new RB_lab("KEINE DATEN")._fsa(15)._fo_bold()._col_fore_lgrey(), 
				new RB_gld()._span(FSI_CONST_NG.vKONTRAKT_SORTERS.size()+4)._col(new E2_ColorBase())._center_mid()._ins(10, 20, 10, 20));
	}
	
	
	/**
	 * EK- und VK-Kontrakte werden separat aufgefuehrt
	 * @param vKontraktPos
	 * @param cEK_VK
	 * @throws myException
	 */
	private void add_Block(Vector<FS___RECORD_VPOS_KON_EXT_NG>  vKontraktPos, String cEK_VK) throws myException
	{
		//ueberschrift
		if (cEK_VK.equals("EK"))
		{
			this.add(new FSI__SortKontrakteButtonEK_NG("Typ","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Buch-Nr.",FSI_CONST_NG.KON_BUCHNUMMER,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Fr.Best-Nr.",FSI_CONST_NG.KON_FR_BEST_NUMMER,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Beleg.Dat.",FSI_CONST_NG.KON_DRUCKDATUM,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Gültig",FSI_CONST_NG.KON_GUELTIG,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Menge",FSI_CONST_NG.KON_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("FU-Plan",FSI_CONST_NG.KON_FU_PLAN_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG(cEK_VK.equals("EK")?"FU-LadeM.":"FU-AbladeM.",FSI_CONST_NG.KON_FU_LADE_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Rest.",FSI_CONST_NG.KON_REST_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG(cEK_VK.equals("EK")?"Gutsch.":"Rechn.",FSI_CONST_NG.KON_RECH_GUT,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("ANR1-2",FSI_CONST_NG.KON_ANR1_2,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Artbez1",FSI_CONST_NG.KON_ARTBEZ1,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Artbez2",FSI_CONST_NG.KON_ARTBEZ2,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("Preis",FSI_CONST_NG.KON_PREIS,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonEK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
		}
		else
		{
			this.add(new FSI__SortKontrakteButtonVK_NG("Typ","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Buch-Nr.",FSI_CONST_NG.KON_BUCHNUMMER,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Fr.Best-Nr.",FSI_CONST_NG.KON_FR_BEST_NUMMER,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Beleg.Dat.",FSI_CONST_NG.KON_DRUCKDATUM,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Gültig",FSI_CONST_NG.KON_GUELTIG,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Menge",FSI_CONST_NG.KON_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("FU-Plan",FSI_CONST_NG.KON_FU_PLAN_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG(cEK_VK.equals("EK")?"FU-LadeM.":"FU-AbladeM.",FSI_CONST_NG.KON_FU_LADE_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Rest.",FSI_CONST_NG.KON_REST_MENGE,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG(cEK_VK.equals("EK")?"Gutsch.":"Rechn.",FSI_CONST_NG.KON_RECH_GUT,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("ANR1-2",FSI_CONST_NG.KON_ANR1_2,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Artbez1",FSI_CONST_NG.KON_ARTBEZ1,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Artbez2",FSI_CONST_NG.KON_ARTBEZ2,true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("Preis",FSI_CONST_NG.KON_PREIS,false,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
			this.add(new FSI__SortKontrakteButtonVK_NG("-","",true,this),new RB_gld()._col(new E2_ColorDDark()));
		}
		
		
		for (int i=0;i<vKontraktPos.size();i++)
		{
			FS___RECORD_VPOS_KON_EXT_NG  recVKON = vKontraktPos.get(i);
		
			
			Component[]  arrZeile = new Component[this.getSize()];
			
			arrZeile[0]=new ownLabelLeft(cEK_VK,recVKON);
			
			arrZeile[1]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_BUCHNUMMER).ANZEIGE,recVKON);
			arrZeile[2]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_FR_BEST_NUMMER).ANZEIGE,recVKON);
			arrZeile[3]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_DRUCKDATUM).ANZEIGE,recVKON);
			arrZeile[4]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_GUELTIG).ANZEIGE,recVKON);
			arrZeile[5]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_MENGE).ANZEIGE,recVKON);
			arrZeile[6]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_FU_PLAN_MENGE).ANZEIGE,recVKON);
			arrZeile[7]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_FU_LADE_MENGE).ANZEIGE,recVKON);
			arrZeile[8]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_REST_MENGE).ANZEIGE,recVKON);
			arrZeile[9]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_RECH_GUT).ANZEIGE,recVKON);
			arrZeile[10]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_ANR1_2).ANZEIGE,recVKON);
			arrZeile[11]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_ARTBEZ1).ANZEIGE,recVKON);
			arrZeile[12]=new ownLabelLeft(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_ARTBEZ2).ANZEIGE,recVKON);
			arrZeile[13]=new ownLabelRight(recVKON.get_View_and_Sort_Pair(FSI_CONST_NG.KON_PREIS).ANZEIGE,recVKON);
			arrZeile[14]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT_NG(
									bibALL.get_Vector(recVKON.get_ID_VKOPF_KON_cUF()), 
									this.oZentrum.get_oContainerToCloseAfterJump(),cEK_VK,
									this.oZentrum.is_jump_is_active());
			arrZeile[15]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT_Clearing_NG(
									bibALL.get_Vector(recVKON.get_ID_VPOS_KON_cUF()), 
									this.oZentrum.get_oContainerToCloseAfterJump(),cEK_VK,
									this.oZentrum.is_jump_is_active());
			
			if (recVKON.get_bIsEK())
			{
				Vector<String> vID_Fuhren = new Vector<String>();
				RECLIST_VPOS_TPA_FUHRE  reclistFuhren = recVKON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhrenOrt = recVKON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'",null,true);
				vID_Fuhren.addAll(reclistFuhren.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				vID_Fuhren.addAll(reclistFuhrenOrt.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				arrZeile[16]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(	vID_Fuhren, 
																							this.oZentrum.get_oContainerToCloseAfterJump(),
																							this.oZentrum.is_jump_is_active());
			}
			else
			{
				Vector<String> vID_Fuhren = new Vector<String>();
				RECLIST_VPOS_TPA_FUHRE  reclistFuhren = recVKON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhrenOrt = recVKON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'",null,true);
				vID_Fuhren.addAll(reclistFuhren.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				vID_Fuhren.addAll(reclistFuhrenOrt.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				arrZeile[16]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(	vID_Fuhren, 
																							this.oZentrum.get_oContainerToCloseAfterJump(),
																							this.oZentrum.is_jump_is_active());
			}
			
			
			new FSI_HighLighter_NG(this.oTF_Suche, arrZeile);
			this.vComponentZeilen.add(arrZeile);
			
			for (int k=0;k<arrZeile.length;k++)
			{
				this.add(arrZeile[k]);
			}

		}
		
	}

	public String get_Status_der_Selektoren() throws myException
	{
		
		String cRueck = "";
		cRueck += (this.cb_1_EK.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_2_VK.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (S.NN(this.oTF_Suche.getText()));
		
		return cRueck;
	}
	
	private void restore_status_der_selektionen_und_sorters() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_Checkbox_und_Selektoren().get_Settings(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER);
		
		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");
			
			if (vWerte.size()>0) cb_1_EK.setSelected(vWerte.get(0).equals("Y"));
			if (vWerte.size()>1) cb_2_VK.setSelected(vWerte.get(1).equals("Y"));
			if (vWerte.size()>2) this.oTF_Suche.setText(vWerte.get(2));
		}
		
		String[] cSorteInfoEK = new FSI__SortKontrakteButtonEK_SaveStatus_NG().get_Status_aus_Database();
		if (cSorteInfoEK != null && FSI_CONST_NG.vKONTRAKT_SORTERS.contains(cSorteInfoEK[0]))
		{
			this.set_ActualSortKennerEK(cSorteInfoEK[0]);
			this.set_bActualSort_UP_EK(cSorteInfoEK[1].equals("UP"));
		}
		
		String[] cSorteInfoVK = new FSI__SortKontrakteButtonVK_SaveStatus_NG().get_Status_aus_Database();
		if (cSorteInfoVK != null && FSI_CONST_NG.vKONTRAKT_SORTERS.contains(cSorteInfoVK[0]))
		{
			this.set_ActualSortKennerVK(cSorteInfoVK[0]);
			this.set_bActualSort_UP_VK(cSorteInfoVK[1].equals("UP"));
		}
		
		
	}

	
	
	private E2_ComponentGroupHorizontal get_BedienzeileFuerFuhrenliste() 
	{
		return new E2_ComponentGroupHorizontal(0,vSelcomponents,new Insets(2,0,10,0));
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

		private String cSessionHash = "SESSION_HASH_FIRMENSTAMM_JUMPBOX_SPEICHERE_SELECTOR_KONTRAKT";
		
		public E2_UserSettings_Checkbox_und_Selektoren() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash;
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
			new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_Kontrakte_NG.this.get_Status_der_Selektoren());
		}
	}


	private class cbAuswahlWasZeigt extends MyE2_CheckBox
	{
		public cbAuswahlWasZeigt(Object cText, MyE2_String cToolTipText,
				boolean bIsSelected, boolean bSetDisabledFromBasic) {
			super(cText, cToolTipText, bIsSelected, bSetDisabledFromBasic);
		}
	}

	
	
	private class ownLabelLeft extends FSI_Label_NG 
	{
		public FS___RECORD_VPOS_KON_EXT_NG recVPOS = null;
		
		public ownLabelLeft(String cWert, FS___RECORD_VPOS_KON_EXT_NG rec_VPOS) 
		{
			super(S.NN(cWert),0);
			this.recVPOS = rec_VPOS;
		}
	}
	
	private class ownLabelRight extends FSI_Label_NG 
	{
		public FS___RECORD_VPOS_KON_EXT_NG recVPOS = null;

		public ownLabelRight(String cWert, FS___RECORD_VPOS_KON_EXT_NG rec_VPOS) 
		{
			super(S.NN(cWert), 2);
			this.recVPOS = rec_VPOS;
		}
	}
	

	public class actionNeubauFuhrenGrid extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Kontrakte_NG.this.__aufbau1();
		}
	}
	
	public class actionCheckHighlight extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Kontrakte_NG oThis = INFO_BLOCK_Kontrakte_NG.this;
			
			for (int i=0;i<oThis.vComponentZeilen.size();i++)
			{
				new FSI_HighLighter_NG(oThis.oTF_Suche, oThis.vComponentZeilen.get(i));
			}

		}
	}
	
	
	
	
	private class ownRECLIST_VPOS_KON extends RECLIST_VPOS_KON
	{
		
		public ownRECLIST_VPOS_KON() throws myException
		{
			super();
		}

		public ownRECLIST_VPOS_KON(String QueryString) throws myException
		{
			super(QueryString);
		}
		
		
		
		public Vector<FS___RECORD_VPOS_KON_EXT_NG>  get_standard_order_vector() throws myException
		{
			Vector<FS___RECORD_VPOS_KON_EXT_NG> vRueck = new Vector<FS___RECORD_VPOS_KON_EXT_NG>();
			
			for (int i=0;i<this.get_vKeyValues().size();i++)
			{
				vRueck.add(new FS___RECORD_VPOS_KON_EXT_NG(this.get(i)));
			}
			return vRueck;
		}

		
		
		/**
		 * @returns sorted vector of records
		 *
		 */
		public Vector<FS___RECORD_VPOS_KON_EXT_NG>  get_order_vector(String cKenner, boolean bUPDown_true_is_up) throws myException
		{
			Vector<FS___RECORD_VPOS_KON_EXT_NG> vRueck = new Vector<FS___RECORD_VPOS_KON_EXT_NG>();
			
			for (int i=0;i<this.get_vKeyValues().size();i++)
			{
				vRueck.add(new FS___RECORD_VPOS_KON_EXT_NG(this.get(i)));
			}
			
			ownSorter  	oSorter = new ownSorter(cKenner,bUPDown_true_is_up);
			
			Collections.sort(vRueck,oSorter);
			
			if (oSorter.bOK)
			{
				return vRueck;
			}
			else
			{
				throw new myException(this,"GET_SORTED_VECTOR: Fields not in RECORD!!");
			}
		}
		
		
		private class ownSorter implements Comparator<FS___RECORD_VPOS_KON_EXT_NG>
		{
			public boolean bOK = true;
			public boolean UP_DOWN = false;
			
			private String cKENNER4Sort = null;
			
			public ownSorter(String cKENNER_4_SORT, boolean bUP_DOWN) 
			{
				super();
				this.UP_DOWN = bUP_DOWN; 
				this.cKENNER4Sort = cKENNER_4_SORT;
			}

			
			public int compare(FS___RECORD_VPOS_KON_EXT_NG o1, FS___RECORD_VPOS_KON_EXT_NG o2) 
			{
				FSI_SortObject_NG cSortString1 = null;
				FSI_SortObject_NG cSortString2 = null;
				
				this.bOK = true;
				
				try 
				{
					cSortString1 = o1.get_View_and_Sort_Pair(this.cKENNER4Sort);
					cSortString2 = o2.get_View_and_Sort_Pair(this.cKENNER4Sort);
				} 
				catch (myException e) 
				{
					this.bOK=false;
					e.printStackTrace();
				}
				
				
				if (this.UP_DOWN)
				{
					return cSortString1.compareTo(cSortString2);
				}
				else
				{
					return cSortString2.compareTo(cSortString1);
				}
			}

			
		}

		
		
	}
	

}
