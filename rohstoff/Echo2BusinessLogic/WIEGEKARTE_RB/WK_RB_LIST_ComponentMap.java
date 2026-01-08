package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_LIST_BT_CONNECT_to_FUHRE;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_LIST_BT_JUMP_to_FUHRE;

public class WK_RB_LIST_ComponentMap extends E2_ComponentMAP_V22 
{
    private RB_TransportHashMap   m_tpHashMap = null;
    
	public WK_RB_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException
	{
		super(new WK_RB_LIST_SqlFieldMAP(p_tpHashMap));
		
		m_tpHashMap = p_tpHashMap;    
		        
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		LayoutData oLayoutLeft = LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_4_0_0_0);
		
		
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
		
		//hier optionale spalten fuer direktes loeschen/edit/view
//		this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_DEL.db_val(),    	new WK_RB_LIST_bt_DeleteInListRow(this.m_tpHashMap)
//																					._setGridLayout4List(new RB_gld()._ins(4))
//																					._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
//																	new MyE2_String("?"));
//		
//		this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_EDIT.db_val(),   	new WK_RB_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
//																					._setGridLayout4List(new RB_gld()._ins(4))
//																					._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
//																	new MyE2_String("?"));
		
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_VIEW.db_val(),   	new WK_RB_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																	new MyE2_String("?"));
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(WK_RB_VALIDATORS.ATTACHMENT.getValidator())
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
																				, S.ms("?"));

		//hier kommen die Felder	
		MyE2_DB_MultiComponentColumn oColIDs = new MyE2_DB_MultiComponentColumn();
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_WIEGEKARTE")), new MyE2_String("ID"),null,null);
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_WIEGEKARTE_PARENT")),new MyE2_String("IDGesamt"),null,null);
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_WIEGEKARTE_STORNO")), new MyE2_String("IDStorno"),null,null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_IDS.db_val(),oColIDs, new MyE2_String("Wiegeschein-ID/Parent-Id/Storno"));

		//hier kommen die Felder	
		MyE2_DB_MultiComponentColumn oColStorno = new MyE2_DB_MultiComponentColumn();
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNO")),new MyE2_String("Storno"),null,null);
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNIERT_AM")),new MyE2_String("am"),null,null);
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNIERT_VON")),new MyE2_String("von"),null,null);
		oColStorno.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNO_GRUND")),new MyE2_String("Grund"),null,null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_STORNO.db_val(),oColStorno, new MyE2_String("Storno"));

		
		// Wiegeschein-NR / Folgewägung
		MyE2_DB_MultiComponentColumn oColWaegenr_Folge = new MyE2_DB_MultiComponentColumn();
		oColWaegenr_Folge.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LFD_NR")),new MyE2_String("Wiegeschein-Nr."),null);
		oColWaegenr_Folge.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ES_NR")),new MyE2_String("Eingangsschein-Nr"),null,null);
		oColWaegenr_Folge.add_Component(new MyE2_DB_Label_INROW(oFM.get_("INFO_GESAMTVERWIEGUNG")),new MyE2_String("Gesamtverwiegung"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_FOLGEWAEGUNG.db_val(),oColWaegenr_Folge, new MyE2_String("Wiegeschein-Nr/Eingangsschein-Nr"));

		
		// Lieferant / Wiegekarten-Typ
		MyE2_DB_MultiComponentColumn oColSped_WE_TYP = new MyE2_DB_MultiComponentColumn();
		
		WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN btnKFZ = new WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN(true, this.m_tpHashMap);
		btnKFZ.setFont(new E2_FontBold(4));
		oColSped_WE_TYP.add_Component(btnKFZ, new MyE2_String("Kennzeichen"),WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_KENNZEICHEN.db_val());
		
		MyE2_DB_Label_INROW lblTrailer = new MyE2_DB_Label_INROW(oFM.get_(WIEGEKARTE.trailer));
		lblTrailer.setFont(new E2_FontBold(4));
		;
		oColSped_WE_TYP.add_Component(lblTrailer, new MyE2_String("Trailer"), null);
		 
//		MyE2_DB_Button btnKennzeichen = new MyE2_DB_Button(oFM.get_("KENNZEICHEN"));
//		btnKennzeichen.setFont(new E2_FontBold(4));
//		oColSped_WE_TYP.add_Component(btnKennzeichen, new MyE2_String("Kennzeichen"),null);
		
		MyE2_DB_Label_INROW lblWE = new MyE2_DB_Label_INROW(oFM.get_("IST_LIEFERANT"));
		lblWE.setFont(new E2_FontBold());
		oColSped_WE_TYP.add_Component(lblWE,new MyE2_String("WE/WA"),null);
		oColSped_WE_TYP.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TYP_WIEGEKARTE")),new MyE2_String("Wiegekarten-Typ"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_WE_TYP.db_val(),oColSped_WE_TYP, new MyE2_String("Kennzeichen / WE-WA / Typ"));

		
		// Gewicht/Datum W1
		MyE2_DB_MultiComponentColumn oColW1GewichtDatum = new MyE2_DB_MultiComponentColumn();
		oColW1GewichtDatum.setLayoutData(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		MyE2_DB_Label_INROW lbl_w1gewicht = new MyE2_DB_Label_INROW(oFM.get_("W1_GEWICHT"));
		lbl_w1gewicht.setFont(new E2_FontBold(2));
		MyE2_DB_Label_INROW lbl_w1datum = new MyE2_DB_Label_INROW(oFM.get_("W1_DATUM"));
		MyE2_DB_Label_INROW lbl_w1User = new MyE2_DB_Label_INROW(oFM.get_("UW1_NAME"));
		lbl_w1User.setFont(new E2_FontPlain(-2));
		MyE2_DB_Label_INROW lbl_w1HandDesc = new MyE2_DB_Label_INROW(oFM.get_("W1_HANDEINGABE_BEM"));
		lbl_w1HandDesc.setFont(new E2_FontPlain(-2));
		
		oColW1GewichtDatum.add_Component(lbl_w1gewicht,new MyE2_String("1.Wägung  Gewicht"),null,null);
		oColW1GewichtDatum.add_Component(lbl_w1datum,new MyE2_String("Datum / Zeit"),null,null);
		oColW1GewichtDatum.add_Component(lbl_w1User,new MyE2_String("Wiegemeister"),null,null);
		oColW1GewichtDatum.add_Component(lbl_w1HandDesc,new MyE2_String("Bemerkung Hand"),null,null);
		


		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_W1_GEWICHT.db_val(),oColW1GewichtDatum, new MyE2_String("Wägung 1"));
	
		// Gewicht/Datum W2
		MyE2_DB_MultiComponentColumn oColW2GewichtDatum = new MyE2_DB_MultiComponentColumn();
		oColW2GewichtDatum.setLayoutData(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		MyE2_DB_Label_INROW lbl_w2gewicht = new MyE2_DB_Label_INROW(oFM.get_("W2_GEWICHT"));
		lbl_w2gewicht.setFont(new E2_FontBold(2));
		MyE2_DB_Label_INROW lbl_w2datum = new MyE2_DB_Label_INROW(oFM.get_("W2_DATUM"));
		MyE2_DB_Label_INROW lbl_w2User = new MyE2_DB_Label_INROW(oFM.get_("UW2_NAME"));
		lbl_w2User.setFont(new E2_FontPlain(-2));
		MyE2_DB_Label_INROW lbl_w2HandDesc = new MyE2_DB_Label_INROW(oFM.get_("W2_HANDEINGABE_BEM"));
		lbl_w2HandDesc.setFont(new E2_FontPlain(-2));

		oColW2GewichtDatum.add_Component(lbl_w2gewicht,new MyE2_String("2.Wägung  Gewicht"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColW2GewichtDatum.add_Component(lbl_w2datum,new MyE2_String("Datum / Zeit"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColW2GewichtDatum.add_Component(lbl_w2User,new MyE2_String("Wiegemeister"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColW2GewichtDatum.add_Component(lbl_w2HandDesc,new MyE2_String("Bemerkung Hand"),null,null);
		
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_W2_GEWICHT.db_val(),oColW2GewichtDatum, new MyE2_String("Wägung 2"));
		
		// Nettogewichte / Abzug
		MyE2_DB_MultiComponentColumn oColGewicht = new MyE2_DB_MultiComponentColumn();
		oColGewicht.setLayoutData(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
				
		MyE2_DB_Label_INROW lblNettoGewicht = new MyE2_DB_Label_INROW(oFM.get_("NETTOGEWICHT"));
		MyE2_DB_Label_INROW lblGewicht_Abzug = new MyE2_DB_Label_INROW(oFM.get_("GEWICHT_ABZUG"));
		MyE2_DB_Label_INROW lblGewicht_nach_Abzug = new MyE2_DB_Label_INROW(oFM.get_("GEWICHT_NACH_ABZUG"));
		MyE2_DB_Label_INROW lblGewicht_Abzug_Fuhre = new MyE2_DB_Label_INROW(oFM.get_("GEWICHT_ABZUG_FUHRE"));
		MyE2_DB_Label_INROW lblGewicht_nach_Abzug_Fuhre = new MyE2_DB_Label_INROW(oFM.get_("GEWICHT_NACH_ABZUG_FUHRE"));
		
		lblGewicht_nach_Abzug.setFont(new E2_FontBold(2));
		lblGewicht_nach_Abzug_Fuhre.setFont(new E2_FontBold(2));
		
		oColGewicht.add_Component(lblNettoGewicht,new MyE2_String("Gewicht"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColGewicht.add_Component(lblGewicht_Abzug,new MyE2_String("Verpackung"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColGewicht.add_Component(lblGewicht_nach_Abzug,new MyE2_String("Netto"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColGewicht.add_Component(lblGewicht_Abzug_Fuhre,new MyE2_String("Abzug"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColGewicht.add_Component(lblGewicht_nach_Abzug_Fuhre,new MyE2_String("Netto"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		
		
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_GEWICHT.db_val(),oColGewicht, new MyE2_String("Gewicht"));
		
		
		// Sorte
		// Befund
		MyE2_DB_MultiComponentColumn oColSorteKunde = new MyE2_DB_MultiComponentColumn();

		oColSorteKunde.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")),new MyE2_String("Sorte"),null);
		oColSorteKunde.add_Component(new MyE2_Label("-"), new MyE2_String("Verpackung"),WK_RB_CONST.WK_RB_NAMES.AUFLISTUNG_GEBINDE.db_val());
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_SORTE_KUNDE.db_val(),oColSorteKunde, new MyE2_String("Sorte/Gesamtverwiegung/Behälter"));
		
		
		// Kunde /  Spedition / Abnehmer Strecke
		MyE2_DB_MultiComponentColumn oColSped_AbnStrecke = new MyE2_DB_MultiComponentColumn();
		oColSped_AbnStrecke.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO_KUNDE"),true),new MyE2_String("Kunde"),null);
		oColSped_AbnStrecke.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO_SPED"),true),new MyE2_String("Spedition"),null);
		oColSped_AbnStrecke.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO_STRECKE"),true),new MyE2_String("Abnehmer Strecke"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_SPED_STRECKE.db_val(),oColSped_AbnStrecke, new MyE2_String("Sorte/Kunde"));

		// Befund / Bem1 / Bem2 / Bem Intern
//		MyE2_DB_TextArea o_Befund =new MyE2_DB_TextArea(oFM.get_("BEFUND"),350,2);
//		o_Befund.__setStyleFactory(new StyleFactory_TextArea_INROW());
//		oColSorteKunde.add_Component(o_Befund, new MyE2_String("Befund"),null);
		MyE2_DB_MultiComponentColumn oColBem = new MyE2_DB_MultiComponentColumn();
		oColBem.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG1")),new MyE2_String("Bemerkung Wiegekarte"),null);
		oColBem.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG_INTERN")),new MyE2_String("Bemerkung Eingangsschein"),null);
		oColBem.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEFUND")),new MyE2_String("Befund"),null);
		//oColBem.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG2")),new MyE2_String("Bemerkung 2"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_BEMERKUNG.db_val(),oColBem, new MyE2_String("Bemerkungen"));
	
		// Befundungs-Tab
		MyE2_DB_MultiComponentColumn oColBefund = new MyE2_DB_MultiComponentColumn();
		oColBefund.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEFUNDUNG_USER")),new MyE2_String("Befundung von"),null);
		oColBefund.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WK_BE_SORTIERUNG")),new MyE2_String("Sortierung"),null);
		oColBefund.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WK_BE_ANALYSE")),new MyE2_String("Analyse"),null);
		oColBefund.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WK_BE_NAESSEPROBE")),new MyE2_String("Nässeprobe"),null);
		
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_BEFUNDUNG.db_val(),oColBefund, new MyE2_String("Befundungen"));

		
		
		
		// Druckdatum
		MyE2_DB_MultiComponentColumn oColGedruckt = new MyE2_DB_MultiComponentColumn();
		oColGedruckt.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEDRUCKT_AM")), new MyE2_String("Gedruckt am"),null);
		oColGedruckt.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DRUCKZAEHLER")), new MyE2_String("Zähler WK"),null);
		oColGedruckt.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DRUCKZAEHLER_EINGANGSSCHEIN")), new MyE2_String("Zähler ES/LS"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_GEDRUCKT.db_val(),oColGedruckt, new MyE2_String("Druckstatus"));
		
		// Druckdatum
		MyE2_DB_MultiComponentColumn oColContainer = new MyE2_DB_MultiComponentColumn();
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oFM.get_(WIEGEKARTE.container_nr.fieldName())), new MyE2_String("Container Nr."),null);
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WK_CONT_UVV_DATUM")), new MyE2_String("UVV-Datum"),null);
		oColContainer.add_Component(new MyE2_Label("-"), new MyE2_String("Lagerplatz eingebucht"),WK_RB_CONST.WK_RB_NAMES.LAGERPLATZ_PFAD.db_val());
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oFM.get_(WIEGEKARTE.gueterkategorie.fieldName())), new MyE2_String("Güterkategorie"),null);
		oColContainer.add_Component(new MyE2_DB_Label_INROW(oFM.get_(WIEGEKARTE.siegel_nr.fieldName())), new MyE2_String("Siegel Nr."),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_CONTAINER_NR.db_val(),oColContainer, new MyE2_String("Container NR. / Siegel Nr."));

		
		
		// Fuhren-IDs
		MyE2_DB_MultiComponentColumn oColIDsVPOS = new MyE2_DB_MultiComponentColumn();
		oColIDsVPOS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")),new MyE2_String("ID Fuhre"),null);
		oColIDsVPOS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")),new MyE2_String("ID FuhrenOrt"),null);
		
		//2012-05-07: sprungfunktion von wiegekarte zu fuhre
		oColIDsVPOS.add_Component(new WK_RB_LIST_BT_JUMP_to_FUHRE(),new MyE2_String("Verknüpfung zur Fuhre..."),WK_RB_CONST.WK_RB_NAMES.SPRUNGBUTTON_ZU_FUHRE.db_val(),LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));

		//2017-09-29: verbindung der Wiegekarte zur Fuhre
		if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES()){
			oColIDsVPOS.add_Component(new WK_RB_LIST_BT_CONNECT_to_FUHRE(),new MyE2_String(""),WK_RB_CONST.WK_RB_NAMES.BUTTON_VERKNUEPFUNG_MIT_FUHRE.db_val(),LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		}				
		
		
//		oColIDsVPOS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FUHRE_FUHRE")),new MyE2_String("WK übertragen"),null);
//		oColIDsVPOS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FUHRE_ORT_FUHRE")),new MyE2_String("ID FuhrenOrt(Fuhre)"),null);
		this.add_Component(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_ID_FUHREN.db_val(),oColIDsVPOS, new MyE2_String("IdFuhre/IdFuhrenOrt"));
		
		
		// Formatierungen
		this.get__Comp_From_RealComponents("STORNO").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(new Insets(0, 0, 6, 0)));
		this.get__Comp_From_RealComponents("ES_NR").EXT().set_oLayout_ListElement_AND_Titel(oLayoutLeft);
		
		this.get__Comp_From_RealComponents("W1_GEWICHT").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("W1_DATUM").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("UW1_NAME").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("W1_HANDEINGABE_BEM").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		
		this.get__Comp_From_RealComponents("W2_GEWICHT").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("W2_DATUM").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("UW2_NAME").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.get__Comp_From_RealComponents("W2_HANDEINGABE_BEM").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		
		this.set_oSubQueryAgent(new WK_RB_LIST_FORMATING_Agent(this.m_tpHashMap));
		
	}
	
	@Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
        E2_ComponentMAP_V22.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new WK_RB_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
    	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class WK_RB_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public WK_RB_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recWK_RB = map.getRec22();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recWK_RB.is_no_db_val(WIEGEKARTE.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    
    

}
