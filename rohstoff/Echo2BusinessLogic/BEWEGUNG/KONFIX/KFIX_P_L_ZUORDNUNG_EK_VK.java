package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContaier_Refreshable;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.BigDecimalFactory;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_AND_EDIT_EVERYWHERE_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_KON;

public abstract class KFIX_P_L_ZUORDNUNG_EK_VK extends Project_BasicModuleContainer implements IF_BasicModuleContaier_Refreshable
{
	
	public static MyE2_String   oTooltipKontraktMenge = new MyE2_String("Menge, die für diese Kontraktposition definiert ist");
	public static MyE2_String   oTooltipZuordnungsMengeKontrakte = new MyE2_String("Menge, die für diese Kontraktposition anderen Kontrakten zugeordnet wurde");
	public static MyE2_String   oTooltipZuordnungsMengeLager = new MyE2_String("Menge, die in dieser Kontraktposition für eigene Lager vorgesehen ist");
	public static MyE2_String   oTooltipZuordnungsMengeKontraktOben = new MyE2_String("Menge, die aus dieser Kontraktposition dem OBEN GENANNTEN Kontrakt zugeordnet wird");
	
	
	public static Font          oButtonFontNormal = 		new E2_FontPlain(0);
	public static Font          oButtonFontSmallItalic = 	new E2_FontItalic(-2);
	
	// die zuordnungsart wird definiert davon, ob ein EK-Kontrakt oder ein VK-Kontrakt uebergeben wird !
	private MyE2_Grid						oBaseGrid = new MyE2_Grid(10,1);

    private MyE2_Button						oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
    private MyE2_Button						oButtonSave =   new MyE2_Button(new MyE2_String("Speichern"));

    /*
     * die folgenden vectoren beinhalten jeweils BSK__METAMAP_KONTRAKT_KOPF_POS_ALLEFELDER - objekte fuer jede 
     * zuordnungszeile und werden bei jedem neuaufbau resettet
     */
    private Vector<PRUEF_RECORD_VPOS_KON>	vREC_VPOS_KON_VorhandeneZuord = new Vector<PRUEF_RECORD_VPOS_KON>();
    
    private GridLayoutData  				oLayoutDataRechtsBuendig = LayoutDataFactory.get_GridLayoutGridRight(E2_INSETS.I_10_2_2_2,null, null);
    private GridLayoutData  				oLayoutDataLinksBuendig = LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_10_2_2_2,null, null);
    private GridLayoutData  				oLayoutDataLinksBuendigDunkel = LayoutDataFactory.get_GridLayoutGridLeft_DARK(E2_INSETS.I_10_2_2_2);
    private GridLayoutData  				oLayoutDataRechtsBuendigDunkel = LayoutDataFactory.get_GridLayoutGridRight_DARK(E2_INSETS.I_10_2_2_2);
    private GridLayoutData  				oLayoutDataLinksBuendigDunkler = LayoutDataFactory.get_GridLayoutGridLeft_DDARK(E2_INSETS.I_10_2_2_2);
    private GridLayoutData  				oLayoutDataRechtsBuendigDunkler = LayoutDataFactory.get_GridLayoutGridRight_DDARK(E2_INSETS.I_10_2_2_2);
    
 	private SelektorSortenInKontrakten      oSelSorten = null;
 	
 	private String   						cID_VPOS_KON = null;    // der kontrakt, dem etwas zugeordnet wird
 	private E2_ComponentGroupHorizontal 	oHelpGroup = new E2_ComponentGroupHorizontal(0,E2_INSETS.I_10_5_0_0);
 	
 	private PRUEF_RECORD_VPOS_KON 			recVPOS_KON = null;
 	
 	
 	//2014-01-31: neue variante: Fenster auch bei geschlossenen positionen aufrufbar, aber es kann nichts geaendert werden
 	private boolean   						bReadOnlyMask = false;

 	
    private XX_ActionAgent		ownAgentCancel = new XX_ActionAgent()
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			KFIX_P_L_ZUORDNUNG_EK_VK.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Zuordnung wurde abgebrochen"));
		}
    	
    };
	
    
	
	
	/**
	 * @param ID_VPOS_KON
	 * @param AgentAfterSave
	 * @param oEvent
	 * @throws myException
	 */
	public KFIX_P_L_ZUORDNUNG_EK_VK(String 		ID_VPOS_KON) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_EK_VK_KONTRAKTZUORDNUNGEN);
		this.oButtonCancel.add_oActionAgent(this.ownAgentCancel);
		this.oButtonSave.add_oActionAgent(new ActionSaveZuordnung());
		this.cID_VPOS_KON = ID_VPOS_KON;
		
		this.recVPOS_KON = new PRUEF_RECORD_VPOS_KON(this.cID_VPOS_KON,false);
		
		/*
		 * die zuordnung ist nur lesend moeglich, wenn der kontrakt geloescht ist oder die 
		 * die position geschlossen ist 
		 * 
		 */
		this.bReadOnlyMask =  
			this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES() ||
			this.recVPOS_KON.is_DELETED_YES() ||
			this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().is_DELETED_YES();
	
		this.add_In_ContainerEX(this.oBaseGrid,null,new Extent(450), E2_INSETS.I_2_0_2_0);
		
		this.Refresh(null);
		
		String cInfo = "Zuordnung EK-Kontrakt(-Teil)-Mengen zu einem VK-Kontrakt";
		if (this.recVPOS_KON.IS_EK_KONTRAKT())
			cInfo = "Zuordnung VK-Kontrakt(-Teil)-Mengen zu einem EK-Kontrakt";

		if (this.bReadOnlyMask) {
			cInfo+="    (!!! KEINE ÄNDERUNGEN, DA SCHON ABGESCHLOSSEN !!!)";
		}
		
		this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(1080), new Extent(600),new MyE2_String(cInfo));
	}


	public abstract void doAfterSave() throws myException;
	
	
	public void Refresh(Vector<String> vID_ARTIKEL) throws myException
	{
		this.recVPOS_KON = new PRUEF_RECORD_VPOS_KON(this.cID_VPOS_KON,true);

		this.oBaseGrid.removeAll();
		
		//zuerst die ausgangsposition anzeigen
		this.add_Ueberschrift(this.oBaseGrid,this.oLayoutDataLinksBuendigDunkler,this.oLayoutDataRechtsBuendigDunkler);
		
		MyE2_Label labHelp = new MyE2_Label(MyNumberFormater.formatDez(this.recVPOS_KON.get_hmKONTRAKT_POS_INFO().get_ZUGEORDNETE_KONTRAKTMENGE_0_WENN_LEER().doubleValue(),3,true),
									MyE2_Label.STYLE_SMALL_ITALIC());
		labHelp.setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipZuordnungsMengeKontrakte.CTrans());
		this.add_Zeile(	this.oBaseGrid,this.recVPOS_KON,
						labHelp,
						this.oLayoutDataLinksBuendigDunkel,
						this.oLayoutDataRechtsBuendigDunkel,
						false,true);

		this.oBaseGrid.add(new Separator(),10, E2_INSETS.I_10_0_0_0);
		this.oBaseGrid.add(new MyE2_Label(new MyE2_String("Vorhandene Zuordnungen :"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),10,E2_INSETS.I_10_10_0_0);

		//dann  die zuordnungen anzeigen
		this.baue_VorhandeneZuordnungen();
		this.oBaseGrid.add(new Separator(),10, E2_INSETS.I_10_0_0_0);
		
		this.oSelSorten = new SelektorSortenInKontrakten();
		this.oBaseGrid.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Neue Zuordnungen :"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),
				this.oSelSorten,null),10,E2_INSETS.I_10_10_0_0);
		
		if (this.bReadOnlyMask) {
			this.oSelSorten.set_bEnabled_For_Edit(false);
		}
		
		
		//hier der bereich mit noch nicht zugeordneten neuen positionen
		if (vID_ARTIKEL!=null)
		{
			this.baue_MoeglicheNeueZuordnungen(vID_ARTIKEL.get(0));
		}
		
		this.oHelpGroup.removeAll();
		if (!this.bReadOnlyMask) {
			this.oHelpGroup.add(this.oButtonSave);
		}
		this.oHelpGroup.add(this.oButtonCancel);
		this.set_Component_To_ButtonPane(oHelpGroup);
	}
	
	@Override
	public void Prepare_for_Refresh(E2_BasicModuleContainer calingContainer) throws myException
	{
	}

	
	/*
	 * zeigt alle moeglichen kontrakte einer sorte an zur eingabe der zuordnung
	 */
	private void baue_MoeglicheNeueZuordnungen(String cID_ARTIKEL) throws myException
	{
		
		
		if (bibALL.isEmpty(cID_ARTIKEL))
		{
			return;
		}
		
		String cTypKomplement = this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT)?
																					myCONST.VORGANGSART_VK_KONTRAKT:
																					myCONST.VORGANGSART_EK_KONTRAKT;
		

		// welche komplementaeren kontrakt-positionen mit menge gibt es zu der gewuenschten sorte ...
		// ... die auch nicht in den bereits zugeordneten sein duerfen
		String cExclude = " AND JT_VPOS_KON.ID_VPOS_KON NOT IN (SELECT ID_VPOS_KON_EK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+this.recVPOS_KON.get_ID_VPOS_KON_cUF()+")";
		if (this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT))
			cExclude = " AND JT_VPOS_KON.ID_VPOS_KON NOT IN (SELECT ID_VPOS_KON_VK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.recVPOS_KON.get_ID_VPOS_KON_cUF()+")";
			
		String cQuery =
				"SELECT JT_VPOS_KON.* " +
				" FROM "+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT  WHERE " +
					"JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON AND "+
					"JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "+
					"  NVL(JT_VPOS_KON.ANZAHL,0)>0 AND "+
					"  NVL(JT_VKOPF_KON.DELETED,'N')='N' AND "+
					"  NVL(JT_VPOS_KON.DELETED,'N')='N' AND "+
					"JT_VKOPF_KON.VORGANG_TYP="+bibALL.MakeSql(cTypKomplement)+" AND "+
					"JT_VPOS_KON.ID_ARTIKEL="+cID_ARTIKEL+" AND "+
					"   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'"+cExclude;


		RECLIST_VPOS_KON recLIST_VPOS_KON = new RECLIST_VPOS_KON(cQuery);
		
		
		
		for (int i=0;i<recLIST_VPOS_KON.get_vKeyValues().size();i++)
		{
			String neueMenge = "";
			PRUEF_RECORD_VPOS_KON recHelp = new PRUEF_RECORD_VPOS_KON(recLIST_VPOS_KON.get(recLIST_VPOS_KON.get_vKeyValues().get(i)),false);
			this.vREC_VPOS_KON_VorhandeneZuord.add(recHelp);
			UB_TextFieldForNumbers oTextField = new UB_TextFieldForNumbers("ANZAHL",3,true,neueMenge,100,12);
			oTextField.EXT().set_O_PLACE_FOR_EVERYTHING(recHelp);
			oTextField.EXT().set_C_MERKMAL("NEU");
			

			
			this.add_Zeile(this.oBaseGrid,recHelp,oTextField,this.oLayoutDataLinksBuendig,this.oLayoutDataRechtsBuendig,true,false);
		}
	}

	
	
	private void add_Ueberschrift(MyE2_Grid oGrid,GridLayoutData LayOutLinks, GridLayoutData LayOutRechts) throws myException
	{
		oGrid.add(new MyE2_Label(new MyE2_String("Typ"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		if (this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			oGrid.add(new MyE2_Label(new MyE2_String("Lieferant"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
			oGrid.add(new MyE2_Label(new MyE2_String("Anschrift"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		}
		else
		{
			oGrid.add(new MyE2_Label(new MyE2_String("Abnehmer"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
			oGrid.add(new MyE2_Label(new MyE2_String("Anschrift"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		}
		
		oGrid.add(new MyE2_Label(new MyE2_String("Kontr.Mg"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutRechts);
		oGrid.add(new MyE2_Label(new MyE2_String("Zug.Alle"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutRechts);
		oGrid.add(new MyE2_Label(new MyE2_String("Zug.Lager"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutRechts);
		oGrid.add(new MyE2_Label(new MyE2_String("Zug.Kon."),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutRechts);
		oGrid.add(new MyE2_Label(new MyE2_String("Sorte"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		oGrid.add(new MyE2_Label(new MyE2_String("Artikelbez."),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		oGrid.add(new MyE2_Label(new MyE2_String("ID_VPOS"),MyE2_Label.STYLE_SMALL_ITALIC()),LayOutLinks);
	}


	
	
	/*
	 * aufbau der liste, der zuordnungen, die bereits vorhanden sind
	 */
	private void baue_VorhandeneZuordnungen() throws myException
	{

		this.vREC_VPOS_KON_VorhandeneZuord.removeAllElements();
		
		for (int i=0;i<this.recVPOS_KON.get_vID_RELATION_EK_VK_KOMPLEMENT().size();i++)
		{
			PRUEF_RECORD_VPOS_KON recHelp = new PRUEF_RECORD_VPOS_KON(this.recVPOS_KON.get_vID_RELATION_EK_VK_KOMPLEMENT().get(i),false);
			
			/*
			 * die zuordnungsmenge abfragen
			 */
			String[][] cMenge = null;
			if (this.recVPOS_KON.IS_EK_KONTRAKT())
				cMenge = bibDB.EinzelAbfrageInArrayFormatiert(	"SELECT   NVL(ANZAHL,0) FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG " +
																" WHERE" +
																" ID_VPOS_KON_EK="+this.recVPOS_KON.get_ID_VPOS_KON_cUF()+" AND "+
																" ID_VPOS_KON_VK="+recHelp.get_ID_VPOS_KON_cUF());
			else
				cMenge = bibDB.EinzelAbfrageInArrayFormatiert(	"SELECT   NVL(ANZAHL,0) FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG " +
																" WHERE" +
																" ID_VPOS_KON_VK="+this.recVPOS_KON.get_ID_VPOS_KON_cUF()+" AND "+
																" ID_VPOS_KON_EK="+recHelp.get_ID_VPOS_KON_cUF());

			if (cMenge == null)
				throw new myException("BSK__ZUORDNUNG_EK_VK:add_VorhandeneZuordnungen:Error Querying EK-VK-Bezug !");
			
			String cAlteMenge = "";
			if (cMenge.length>0)
				cAlteMenge = cMenge[0][0];
			
			this.vREC_VPOS_KON_VorhandeneZuord.add(recHelp);
			UB_TextFieldForNumbers oTextField = new UB_TextFieldForNumbers("ANZAHL",3,true,cAlteMenge,100,12);
			oTextField.EXT().set_O_PLACE_FOR_EVERYTHING(recHelp);
			oTextField.EXT().set_C_MERKMAL("ALT");
			
			if (this.bReadOnlyMask) {
				oTextField.set_bEnabled_For_Edit(false);
			}
			
			this.add_Zeile(this.oBaseGrid,recHelp,oTextField,this.oLayoutDataLinksBuendig,this.oLayoutDataRechtsBuendig,false,false);
		}
	}
	
	
	

	

	
	private void add_Zeile(	MyE2_Grid 				oGrid,
							PRUEF_RECORD_VPOS_KON 	recordVPOS_KON, 
							Component 				oKomponentMengeSpalte, 
							GridLayoutData 			LayOutLinks, 
							GridLayoutData 			LayOutRechts, 
							boolean 				bMakeMengeAktiv,
							boolean    				bHauptZeile) throws myException
	{
		RECORD_VKOPF_KON  recKOPF = recordVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon();
		
		MyE2_Label  oLabLagerMenge = new MyE2_Label(recordVPOS_KON.get_hmKONTRAKT_POS_INFO().get_Formated_GESAMTE_PLAN_LAGERMENGE(), MyE2_Label.STYLE_SMALL_ITALIC());
		oLabLagerMenge.setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipZuordnungsMengeLager.CTrans());
		
		oGrid.add(new MyE2_Label(recKOPF.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT)?"EK":"VK",MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),LayOutLinks);
		oGrid.add(new MyE2_Label(recKOPF.get___KETTE(bibALL.get_Vector("NAME1", "NAME1", null), "", "", "", "-"),MyE2_Label.STYLE_SMALL_ITALIC()),LayOutLinks);
		oGrid.add(new MyE2_Label(recKOPF.get___KETTE(bibALL.get_Vector("PLZ", "ORT", null), "", "", "", "-"),MyE2_Label.STYLE_SMALL_ITALIC()),LayOutLinks);
		
		
		String cMenge = recordVPOS_KON.get_ANZAHL_cF_NN("--");
		if (!cMenge.equals("--"))
		{
			cMenge = recordVPOS_KON.get_ANZAHL_cF_NN(new Double(0), 1, true);
		}
		if (bMakeMengeAktiv)
		{
			
			ButtonUebernehmeGanzeRestMenge oButt = new ButtonUebernehmeGanzeRestMenge(cMenge,recordVPOS_KON.get_ID_VPOS_KON_cUF(),oKomponentMengeSpalte);
			oButt.setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipKontraktMenge.CTrans());
			oGrid.add(oButt,LayOutRechts);
		}
		else
		{
			MyE2_Label oLab = new MyE2_Label(cMenge,MyE2_Label.STYLE_SMALL_ITALIC());
			oLab.setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipKontraktMenge.CTrans());
			oGrid.add(oLab,LayOutRechts);
		}
		String cMengeGesamt = MyNumberFormater.formatDez(recordVPOS_KON.get_hmKONTRAKT_POS_INFO().get_ZUGEORDNETE_KONTRAKTMENGE().doubleValue(),3,true);
		if (bHauptZeile)
		{
			// in der Zeile des "HAUPTKONTRAKTS" ist die Summe der Kontraktmengen doppelt, da weiter rechts unter Zugeordnet bereits vorhanden
			oGrid.add(new MyE2_Label("",MyE2_Label.STYLE_SMALL_ITALIC()),LayOutRechts);
			
		}
		else
		{
			MyE2_Label oLabMengeGes = new MyE2_Label(cMengeGesamt,MyE2_Label.STYLE_SMALL_ITALIC());
			oLabMengeGes.setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipZuordnungsMengeKontrakte.CTrans());
			
			oGrid.add(oLabMengeGes,LayOutRechts);
			if (oKomponentMengeSpalte instanceof MyE2_TextField) 
			{ 
				((MyE2_TextField)oKomponentMengeSpalte).setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipZuordnungsMengeKontraktOben.CTrans());
			}
			if (oKomponentMengeSpalte instanceof MyE2_Label) { ((MyE2_Label)oKomponentMengeSpalte).setToolTipText(KFIX_P_L_ZUORDNUNG_EK_VK.oTooltipZuordnungsMengeKontrakte.CTrans());}
		}
		oGrid.add(oLabLagerMenge,LayOutRechts);
		oGrid.add(oKomponentMengeSpalte,LayOutRechts);
		oGrid.add(new MyE2_Label(recordVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2", null), "", "", "", "-"),MyE2_Label.STYLE_SMALL_ITALIC()),LayOutLinks);
		oGrid.add(new MyE2_Label(recordVPOS_KON.get_ARTBEZ1_cF_NN(""),MyE2_Label.STYLE_SMALL_ITALIC()),LayOutLinks);

		String cBeschriftungButton = null;
		Font   oFont = KFIX_P_L_ZUORDNUNG_EK_VK.oButtonFontNormal;
		if (S.isFull(recordVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF_NN("")))
		{
			cBeschriftungButton = recordVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF_NN("")+"-"+recordVPOS_KON.get_POSITIONSNUMMER_cUF_NN("");
			oFont = KFIX_P_L_ZUORDNUNG_EK_VK.oButtonFontSmallItalic; 
		}
		
		POPUP_AND_EDIT_EVERYWHERE_VPOS_KON oButt = new POPUP_AND_EDIT_EVERYWHERE_VPOS_KON(	recordVPOS_KON.get_ID_VPOS_KON_cUF(),
																							null,
																							new MyE2_String(cBeschriftungButton,false),
																							null,
																							true, 
																							true);
		oButt.setToolTipText(new MyE2_String("ID: ",true,recordVPOS_KON.get_ID_VPOS_KON_cF(),false).CTrans());
		oButt.setFont(oFont);
		
		oGrid.add(oButt,LayOutRechts);
	}
	

	
	
	/*
	 * ein selektor mit allen vorhandenen Sorten
	 */
	private class SelektorSortenInKontrakten extends MyE2_SelectField
	{

		public SelektorSortenInKontrakten() throws myException
		{
			super();

			String cKomplementaerTyp = myCONST.VORGANGSART_EK_KONTRAKT;
			if (KFIX_P_L_ZUORDNUNG_EK_VK.this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT))
				cKomplementaerTyp = myCONST.VORGANGSART_VK_KONTRAKT;
			
			//nur solche kontrakte zulassen, die die gleichen ersten 2 ziffern der sortenhauptnummer haben
			String cZ12 = KFIX_P_L_ZUORDNUNG_EK_VK.this.recVPOS_KON.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF().substring(0,2);
			
			String[][] cSorten = bibDB.EinzelAbfrageInArray(
					"SELECT DISTINCT   NVL(JT_ARTIKEL.ANR1,'-')||' - '||  NVL(JT_ARTIKEL.ARTBEZ1,'-'),JT_ARTIKEL.ID_ARTIKEL " +
					" FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT  WHERE " +
						"JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON AND "+
						"JT_ARTIKEL.ID_ARTIKEL=JT_VPOS_KON.ID_ARTIKEL AND "+
						"JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "+
						"  NVL(JT_VPOS_KON.ANZAHL,0)>0 AND "+
						"  NVL(JT_VKOPF_KON.DELETED,'N')='N' AND "+
						"  NVL(JT_VPOS_KON.DELETED,'N')='N' AND "+
						"  SUBSTR(JT_ARTIKEL.ANR1,1,2)="+bibALL.MakeSql(cZ12)+" AND "+
						"JT_VKOPF_KON.VORGANG_TYP="+bibALL.MakeSql(cKomplementaerTyp)+" AND "+
							"   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'" +
							" ORDER BY 1");
			
			String[][] cSorten2 = new String[cSorten.length+1][2];
			cSorten2[0][0]="-";cSorten2[0][1]="";
			for (int i=0;i<cSorten.length;i++)
			{
				cSorten2[i+1][0]=cSorten[i][0];
				cSorten2[i+1][1]=cSorten[i][1];
			}
			
			this.setFont(new E2_FontBoldItalic(-2));
			this.set_ListenInhalt(cSorten2,false);
			this.setSelectedIndex(0);
			this.add_oActionAgent(new ActionBaueAuswahl());
		}
	}

	
	
	private class ActionBaueAuswahl extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			KFIX_P_L_ZUORDNUNG_EK_VK oThis = KFIX_P_L_ZUORDNUNG_EK_VK.this;
			try 
			{
				oThis.Refresh(bibALL.get_Vector(((SelektorSortenInKontrakten)bibE2.get_LAST_ACTIONEVENT().getSource()).get_ActualWert()));
			} 
			catch (myException e) 
			{
				e.printStackTrace();
				bibMSG.add_MESSAGE(e.get_ErrorMessage(), false);
			}
			
		}
		
	}
	
	
	
	
	private class ButtonUebernehmeGanzeRestMenge extends MyE2_Button
	{
		private Component	 	oComponente = null;
		private String          c_ID_VPOS_KON = null;
		
		
		public ButtonUebernehmeGanzeRestMenge(String TextMitGesamterKontraktMenge,String ID_VPOS_KON, Component componente) 
		{
			super(TextMitGesamterKontraktMenge);
			oComponente = componente;
			this.c_ID_VPOS_KON = ID_VPOS_KON;
			
			this.add_oActionAgent( new XX_ActionAgent()
					{
						public void executeAgentCode(ExecINFO oExecInfo) 
						{
							if (oComponente instanceof UB_TextFieldForNumbers)
							{
								try
								{
									// die infos ueber den zugeordneten kontrakt erfragen
									PRUEF_RECORD_VPOS_KON oPos = new PRUEF_RECORD_VPOS_KON(c_ID_VPOS_KON,false);
									
									if (oPos.get_NichtZugeordnete_RestMenge()<0)
									{
										bibMSG.add_MESSAGE(new MyE2_Warning_Message("Der Vertrag ist bereits überlieft, keine freie Restmenge !"));
									}
									else
									{
										((UB_TextFieldForNumbers)oComponente).setText(
												MyNumberFormater.formatDez(oPos.get_NichtZugeordnete_RestMenge().doubleValue(),3,true));
									}
								}
								catch (myException ex)
								{
									ex.printStackTrace();
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei Übernahme der Restmenge !"));
								}
							}
								
						}
					});
		}
		
	}
	


	
	
	
	// speichern der zuordnungen
	private class ActionSaveZuordnung extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			KFIX_P_L_ZUORDNUNG_EK_VK oThis = KFIX_P_L_ZUORDNUNG_EK_VK.this;
			
			// zuerst die E2_BasicModuleContainer des popup-fensters suchen
			E2_BasicModuleContainer oContainer = new E2_RecursiveSearchParent_BasicModuleContainer().get_First_FoundContainer();
			
			if (oContainer == null)
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Speichern: Recursive Suche nach Container fehlgegangen !"), false);
			
			if (bibMSG.get_bIsOK())
			{
				E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(oContainer,bibALL.get_Vector(UB_TextFieldForNumbers.class.getName()),null);
				
				Vector<Component> vFields = oSearch.get_vAllComponents();
				
				Vector<String> vSQL = new Vector<String>();
				
				try
				{
					// eingabepruefen und zuordnungen summieren
					for (int i=0;i<vFields.size();i++)
					{
						UB_TextFieldForNumbers oTF = (UB_TextFieldForNumbers)vFields.get(i);
						
						if (! oTF.get_MV_InputOK().get_bIsOK())
						{
							oTF.mark_ErrorInput(false);
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Eingabe-Fehler"), false);
						}
						else
						{
							oTF.mark_ErrorInput(true);
						}
					}
						
					if (bibMSG.get_bIsOK())
					{
						// jetzt die statements bauen
						for (int i=0;i<vFields.size();i++)
						{
							UB_TextFieldForNumbers oTF = (UB_TextFieldForNumbers)vFields.get(i);
							oTF.mark_ErrorInput(true);    // auf stand ok stellen
							
							if (!oTF.get_bFieldHasChanged())
								continue;
							
							String cNewZuordnungsMenge = "0";
							if (!bibALL.null2leer(oTF.getText().trim()).equals(""))
								cNewZuordnungsMenge = oTF.get_cInsertValuePart();
							
		
							// es muss eine neue kontraktpos erzeugt werden, damit die validierung auf aktuellen daten beruht
							PRUEF_RECORD_VPOS_KON oKontraktPos_Innere = (PRUEF_RECORD_VPOS_KON)oTF.EXT().get_O_PLACE_FOR_EVERYTHING();
							if (oTF.EXT().get_C_MERKMAL().equals("ALT"))
							{
								BigDecimal bd1 = BigDecimalFactory.BigDecimal3Round(0);
								BigDecimal bd2 = BigDecimalFactory.BigDecimal3Round(oTF.get_ValueAsDOUBLE(true));

								if (bd1.compareTo(bd2)==0)
								{
									if (oThis.recVPOS_KON.IS_EK_KONTRAKT())
										vSQL.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG "+
												" WHERE ID_VPOS_KON_EK="+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+
										         " AND ID_VPOS_KON_VK="+oKontraktPos_Innere.get_ID_VPOS_KON_cUF());
									else
										vSQL.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG "+
												" WHERE ID_VPOS_KON_VK="+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+
										         " AND ID_VPOS_KON_EK="+oKontraktPos_Innere.get_ID_VPOS_KON_cUF());
									
								}
								else
								{
									if (oThis.recVPOS_KON.IS_EK_KONTRAKT())
										vSQL.add("UPDATE "+bibE2.cTO()+".JT_EK_VK_BEZUG SET ANZAHL="+cNewZuordnungsMenge+
												" WHERE ID_VPOS_KON_EK="+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+
										         " AND ID_VPOS_KON_VK="+oKontraktPos_Innere.get_ID_VPOS_KON_cUF());
									else
										vSQL.add("UPDATE "+bibE2.cTO()+".JT_EK_VK_BEZUG SET ANZAHL="+cNewZuordnungsMenge+
												" WHERE ID_VPOS_KON_VK="+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+
										         " AND ID_VPOS_KON_EK="+oKontraktPos_Innere.get_ID_VPOS_KON_cUF());
								}
										
							}
							else if (oTF.EXT().get_C_MERKMAL().equals("NEU"))
							{
								if (new Double(cNewZuordnungsMenge).doubleValue()>0)
								{
									if (oThis.recVPOS_KON.IS_EK_KONTRAKT())
										vSQL.add("INSERT INTO  "+bibE2.cTO()+".JT_EK_VK_BEZUG (ID_EK_VK_BEZUG, ANZAHL,ID_VPOS_KON_EK,ID_VPOS_KON_VK) VALUES(SEQ_EK_VK_BEZUG.NEXTVAL,"+
												cNewZuordnungsMenge+","+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+","+oKontraktPos_Innere.get_ID_VPOS_KON_cUF()+")");
									else
										vSQL.add("INSERT INTO  "+bibE2.cTO()+".JT_EK_VK_BEZUG (ID_EK_VK_BEZUG, ANZAHL,ID_VPOS_KON_VK,ID_VPOS_KON_EK) VALUES(SEQ_EK_VK_BEZUG.NEXTVAL,"+
												cNewZuordnungsMenge+","+oThis.recVPOS_KON.get_ID_VPOS_KON_cUF()+","+oKontraktPos_Innere.get_ID_VPOS_KON_cUF()+")");
								}
							}
							
							vSQL.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ANZAHL=0");
							
						}
						
						if (vSQL.size()>0)
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
							if (bibMSG.get_bIsOK())
							{
								oThis.Refresh(bibALL.get_Vector( oThis.oSelSorten.get_ActualWert()));
							}
							else
							{
								if (bibMSG.get_bIsOK())
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !!")));
							}
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Es gibt keine Änderungen zu speichern !!"), false);
						}
					}
					
					oThis.Refresh(null);
					oThis.doAfterSave();
					
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), true);
				}
			}
		}
	}







}
