package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectionComponentVector_Version1 extends E2_SelectionComponentsVector
{
	private MyE2_CheckBox 					oCheckBoxOnlyEmptyLadegewicht = new MyE2_CheckBox(new MyE2_String("Leere LadeMg."));
	private MyE2_CheckBox 					oCheckBoxOnlyEmptyAbLadegewicht = new MyE2_CheckBox(new MyE2_String("Leere AbladeMg."));

	private static int[] iBreite = {70,102};
	
	
	public FU_SelectionComponentVector_Version1(E2_NavigationList onavigationList, MyE2_Button  oButtonSwitchToNew, String cMODULE_KENNER) throws myException
	{
		super(onavigationList);
		
		int  iDropDownBreite = 130;
		
		GridLayoutData oLayoutDataSpace = new GridLayoutData();
		oLayoutDataSpace.setInsets(new Insets(0,0,4,0));
		oLayoutDataSpace.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		

		GridLayoutData oLayoutDataSpace_span6 = new GridLayoutData();
		oLayoutDataSpace_span6.setInsets(new Insets(0,0,4,0));
		oLayoutDataSpace_span6.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		oLayoutDataSpace_span6.setColumnSpan(6);

		
		GridLayoutData  oLayoutDataShort = new GridLayoutData();
		oLayoutDataShort.setInsets(new Insets(0,0,2,0));
		oLayoutDataShort.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));

		
		E2_FontPlain  oFont = new E2_FontPlain();


		//this.oSelVector_old = new E2_SelectionComponentsVector(oNavList);
		MyE2_CheckBox 					oCheckBoxOnlyGebuchteTPA = new MyE2_CheckBox(new MyE2_String("Geb. TPAs"), new MyE2_String("Fuhren anzeigen, die zu einem gedruckten/verbuchten Transportauftrag gehören"));
		MyE2_CheckBox 					oCheckBoxOnlyStrecke = new MyE2_CheckBox(new MyE2_String("Strecken"), new MyE2_String("Streckenfuhren anzeigen"));
		MyE2_CheckBox 					oCheckBoxOnlySTORNIERT = new MyE2_CheckBox(new MyE2_String("Storniert"), new MyE2_String("Nur Stornierte Fuhren anzeigen"));
		MyE2_CheckBox 					oCheckBoxOnlyNoTPA = new MyE2_CheckBox(new MyE2_String("Freie TP-Pos"), new MyE2_String("Fuhren, die nicht zu einem Transportauftrag gehören"));
		MyE2_CheckBox 					oCheckBoxOnlyTPA = new MyE2_CheckBox(new MyE2_String("TPA-Position"), new MyE2_String("Fuhren, die zu einem Transportauftrag gehören"));
		MyE2_CheckBox	 				oCheckBoxHasRechnungsEingang = new MyE2_CheckBox(new MyE2_String("TPA Rech."), new MyE2_String("Fuhren mit vorhandener Speditionsrechnung"));
		MyE2_CheckBox	 				oCheckBoxHasNoRechnungsEingang = new MyE2_CheckBox(new MyE2_String("Keine TPA-Rech"), new MyE2_String("Fuhren ohne Speditionsrechnung"));
		MyE2_CheckBox  					oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Gelöschte"), new MyE2_String("Gelöschte Fuhren mit anzeigen"));
		MyE2_CheckBox  					oCB_ShowWareneingang = new MyE2_CheckBox(new MyE2_String("Zeige WE"), new MyE2_String("Nur Wareneingänge anzeigen"));
		MyE2_CheckBox  					oCB_ShowWarenausgang = new MyE2_CheckBox(new MyE2_String("Zeige WA"), new MyE2_String("Nur Warenausgänge anzeigen"));
		
		MyE2_CheckBox  					oCB_ausblendenStornierte = 				new MyE2_CheckBox("Storn./AKTIV");
		MyE2_CheckBox  					oCB_ausblendenArchivAusblenden = 		new MyE2_CheckBox("ARCH.,n.buchbar");
		MyE2_CheckBox  					oCB_ausblendenEingabeUnfertig = 		new MyE2_CheckBox("Unvollständig");
		MyE2_CheckBox  					oCB_ausblendenEingabeKeineBuchungspos = new MyE2_CheckBox("ohne BP");
		MyE2_CheckBox  					oCB_ausblendenUngebucht = 				new MyE2_CheckBox("Ungebucht");
		MyE2_CheckBox  					oCB_ausblendenTeilgebucht = 			new MyE2_CheckBox("Teils gebucht");
		MyE2_CheckBox  					oCB_ausblendenFertige = 				new MyE2_CheckBox("Fertig gebucht");
		
		FU_Selector_Land_1				oSelLand = 								new FU_Selector_Land_1();

		oCB_ausblendenStornierte.setToolTipText(new MyE2_String("Ausblenden von stornierten Fuhren aus dem AKTIVEN Bereich").CTrans());
		oCB_ausblendenArchivAusblenden.setToolTipText(new MyE2_String("Ausblenden von Fuhren aus dem ARCHIV / INAKTIV, die nicht gebucht werden können").CTrans());
		oCB_ausblendenEingabeUnfertig.setToolTipText(new MyE2_String("Ausblenden von unvollständig ausgefüllten Fuhren").CTrans());
		oCB_ausblendenEingabeKeineBuchungspos.setToolTipText(new MyE2_String("Ausblenden von Fuhren ohne Buchungspositionen (z.B. leere Mengen oder nur Transporte)").CTrans());
		oCB_ausblendenUngebucht.setToolTipText(new MyE2_String("Ausblenden von ungebuchten Fuhren").CTrans());
		oCB_ausblendenTeilgebucht.setToolTipText(new MyE2_String("Ausblenden von teilweise gebuchten Fuhren").CTrans());
		oCB_ausblendenFertige.setToolTipText(new MyE2_String("Ausblenden von komplett gebuchten Fuhren").CTrans());
		
		
		Vector<MyE2_CheckBox> vHelp = new Vector<MyE2_CheckBox>(); 
		vHelp.add(oCheckBoxOnlyEmptyLadegewicht); vHelp.add(oCheckBoxOnlyEmptyAbLadegewicht);
		
		/*
		 * dem oCheckBoxOnlySTORNIERT einen actionagent mitgeben, der die anderen checkboxen deaktiviert
		 */
		oCheckBoxOnlySTORNIERT.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						MyE2_CheckBox oCheckStorn = (MyE2_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
						if (oCheckStorn.isSelected())
						{
							FU_SelectionComponentVector_Version1.this.oCheckBoxOnlyEmptyLadegewicht.setSelected(false);
							FU_SelectionComponentVector_Version1.this.oCheckBoxOnlyEmptyAbLadegewicht.setSelected(false);
						}
					}
			
				});
		
		
		oCB_ausblendenFertige.setSelected(true);
		oCB_ausblendenStornierte.setSelected(true);
		oCB_ausblendenArchivAusblenden.setSelected(true);
		oCB_ausblendenEingabeKeineBuchungspos.setSelected(true);

		/*
		 * list-selektor aus abfrage
		 */
		String cSQL = "SELECT distinct JT_ADRESSE.NAME1,JT_VKOPF_TPA.ID_ADRESSE " +
						" FROM "+bibE2.cTO()+".JT_VKOPF_TPA ,  "+bibE2.cTO()+".JT_ADRESSE " +
						" WHERE " +
						" JT_VKOPF_TPA.ID_ADRESSE =JT_ADRESSE.ID_ADRESSE ORDER BY JT_ADRESSE.NAME1";

		
		String cSQLWhere = "JT_VKOPF_TPA.ID_ADRESSE=#WERT#";
		
		E2_ListSelectorStandard oSelSpedition = new E2_ListSelectorStandard(cSQL,cSQLWhere,true, new MyE2_String("Sped.:"), new Integer(5));
		
		String cSQL2 = "SELECT distinct JT_ARTIKEL.ANR1||' - '||JT_ARTIKEL.ARTBEZ1,JT_ARTIKEL.ID_ARTIKEL " +
						" FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,  "+bibE2.cTO()+".JT_ARTIKEL " +
						" WHERE JT_VPOS_TPA_FUHRE.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  " +
						" ORDER BY JT_ARTIKEL.ANR1||' - '||JT_ARTIKEL.ARTBEZ1";
		
		String cSQLWhere2 = "JT_VPOS_TPA_FUHRE.ID_ARTIKEL=#WERT#";

		
		E2_ListSelectorStandard oSelSorte = new E2_ListSelectorStandard(cSQL2,cSQLWhere2,true, new MyE2_String("ANR1:"), new Integer(12));
		
		String cSQL_AUSNAHMEN = "SELECT AUSNAHME,ID_VPOS_TPA_FUHRE_SONDER FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER ORDER BY AUSNAHME";
		String cSQL_AUSNAHMEN_WHERE = "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE_SONDER=#WERT#";
		E2_ListSelectorStandard oSelAusnahme = new E2_ListSelectorStandard(cSQL_AUSNAHMEN,cSQL_AUSNAHMEN_WHERE,true, new MyE2_String("Ausnahmen:"), new Integer(5));
		
		String cSQL_ERZEUGT_VON = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')',KUERZEL FROM "+bibE2.cTO()+".JD_USER " +
				" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_VERWALTUNG,'N')='Y' AND  NVL(AKTIV,'N')='Y' ORDER BY 1 ";

		
//		String cSQL_ERZEUGT_VON = "SELECT KUERZEL AS A,KUERZEL AS B FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY KUERZEL";
		String cSQL_ERZEUGT_VON_WHERE = "JT_VPOS_TPA_FUHRE.ERZEUGT_VON='#WERT#'";
		E2_ListSelectorStandard oSelErzeugtVon = new E2_ListSelectorStandard(cSQL_ERZEUGT_VON,cSQL_ERZEUGT_VON_WHERE,true, new MyE2_String("Bearbeit:"), new Integer(12));

		
		String[][] cWerte = {{"-",""},	{"Wurde storniert",""+			myCONST.STATUS_FUHRE__IST_STORNIERT},
										{"Archiv, wird nicht gebucht",""+	myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT},
										{"Nicht komplett erfasst",""+	myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG},
										{"Keine Buchungsfuhre",""+		myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS},
										{"Bereit zum Buchen",""+		myCONST.STATUS_FUHRE__UNGEBUCHT},
										{"Teilweise gebucht",""+		myCONST.STATUS_FUHRE__TEILSGEBUCHT},
										{"Fertig gebucht",""+			myCONST.STATUS_FUHRE__GANZGEBUCHT}};

		E2_ListSelectorStandard  oSelBuchungsStatus = new E2_ListSelectorStandard(new MyE2_SelectField(cWerte,"",true),"  NVL(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG,0)=#WERT#", new MyE2_String("Status:"), new Integer(5));

		// einige dropdowns etwas schmaler machen
		((MyE2_SelectField)oSelSorte.get_oComponentWithoutText()).setWidth(new Extent(iDropDownBreite));
		((MyE2_SelectField)oSelSpedition.get_oComponentWithoutText()).setWidth(new Extent(iDropDownBreite));
		((MyE2_SelectField)oSelBuchungsStatus.get_oComponentWithoutText()).setWidth(new Extent(iDropDownBreite));
		((MyE2_SelectField)oSelAusnahme.get_oComponentWithoutText()).setWidth(new Extent(iDropDownBreite));
		((MyE2_SelectField)oSelErzeugtVon.get_oComponentWithoutText()).setWidth(new Extent(iDropDownBreite));
		
		
		
		E2_SelektorDateFromTo oSelBereichDatumAbhol = 		new E2_SelektorDateFromTo(new MyE2_String("Abholzeit (PLAN):"),"JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG","JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG_ENDE",oFont);
		E2_SelektorDateFromTo oSelBereichDatumLiefer = 		new E2_SelektorDateFromTo(new MyE2_String("Lieferzeit(PLAN):"),"JT_VPOS_TPA_FUHRE.DATUM_ANLIEFERUNG","JT_VPOS_TPA_FUHRE.DATUM_ANLIEFERUNG_ENDE",oFont);
		E2_SelektorDateFromTo oSelBereichDatumAufladen = 	new E2_SelektorDateFromTo(new MyE2_String("Ladedatum (IST) :"),"JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN","JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN",oFont);
		E2_SelektorDateFromTo oSelBereichDatumAbladen = 	new E2_SelektorDateFromTo(new MyE2_String("Abladedat.(IST) :"),"JT_VPOS_TPA_FUHRE.DATUM_ABLADEN","JT_VPOS_TPA_FUHRE.DATUM_ABLADEN",oFont);
		oSelBereichDatumAbhol.setWidthInPixel_InputFields(70);
		oSelBereichDatumLiefer.setWidthInPixel_InputFields(70);
		oSelBereichDatumAufladen.setWidthInPixel_InputFields(70);
		oSelBereichDatumAbladen.setWidthInPixel_InputFields(70);
		
		oSelBereichDatumAbhol.setToolTip(new MyE2_String("Fuhren selektieren, deren Abholzeitraum (GEPLANT) den angegebenen Zeitraums überschneidet"));
		oSelBereichDatumLiefer.setToolTip(new MyE2_String("Fuhren selektieren, deren Lieferzeitraum (GEPLANT) den angegebenen Zeitraums überschneidet"));
		oSelBereichDatumAufladen.setToolTip(new MyE2_String("Fuhren selektieren, deren Ladedatum (REAL) innerhalb des angegebenen Zeitraums liegt"));
		oSelBereichDatumAbladen.setToolTip(new MyE2_String("Fuhren selektieren, deren Abladedatum (REAL) innerhalb des angegebenen Zeitraums liegt"));
	
		
		
		

		//String cQueryRechnungVorhanden = "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE #1# (SELECT   NVL(ID_VPOS_TPA_FUHRE,0) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN WHERE  NVL(FUKO_TYP,'-')= 'SPEDITIONSRECHNUNG' )";
		
		//2014-03-18: fuhrenselektor beruecksichtigen jetzt auch den loeschzustand der kosten
		String cQueryRechnungVorhanden =  "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE #1# "+
											"(SELECT   NVL(ID_VPOS_TPA_FUHRE,0) "+ 
											" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN  FUKO "+
											" LEFT OUTER JOIN "+bibE2.cTO()+".JT_FUHREN_KOSTEN_TYP  KOTY ON (KOTY.ID_FUHREN_KOSTEN_TYP=FUKO.ID_FUHREN_KOSTEN_TYP) "+
											" WHERE NVL(KOTY.SPEDITIONSRECHNUNG,'N')='Y'" +
											" AND   NVL(FUKO."+ _DB.VPOS_TPA_FUHRE_KOSTEN$DELETED+ ",'N')='N' ) ";
		
		
		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyEmptyLadegewicht,"JT_VPOS_TPA_FUHRE.MENGE_AUFLADEN_KO is NULL",""));
		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyEmptyAbLadegewicht,"JT_VPOS_TPA_FUHRE.MENGE_ABLADEN_KO is NULL",""));
		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyGebuchteTPA,"JT_VKOPF_TPA.BUCHUNGSNUMMER is not NULL",""));

		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyStrecke,"  NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_START,0)<>"+bibALL.get_ID_ADRESS_MANDANT()+" AND "+
																		"  NVL(JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL,0)<>"+bibALL.get_ID_ADRESS_MANDANT(),""));

		this.add(new E2_ListSelectorStandard(oCheckBoxOnlySTORNIERT,"NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='Y'",""));
		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyNoTPA,"JT_VPOS_TPA_FUHRE.ID_VPOS_TPA is NULL",""));
		this.add(new E2_ListSelectorStandard(oCheckBoxOnlyTPA,"JT_VPOS_TPA_FUHRE.ID_VPOS_TPA is NOT NULL",""));
		this.add(new E2_ListSelectorStandard(oCheckBoxHasRechnungsEingang,bibALL.ReplaceTeilString(cQueryRechnungVorhanden,"#1#"," IN "),""));
		this.add(new E2_ListSelectorStandard(oCheckBoxHasNoRechnungsEingang,bibALL.ReplaceTeilString(cQueryRechnungVorhanden,"#1#"," NOT IN "),""));
		this.add(new E2_ListSelectorStandard(oCB_ShowDeletedRows,"","  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'"));

		this.add(oSelLand);
		
		this.add(new E2_ListSelectorStandard(oCB_ShowWareneingang,"JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL = "+bibALL.get_ID_ADRESS_MANDANT(),""));
		this.add(new E2_ListSelectorStandard(oCB_ShowWarenausgang,"JT_VPOS_TPA_FUHRE.ID_ADRESSE_START = "+bibALL.get_ID_ADRESS_MANDANT(),""));
		
		
		/*
		 * 		MyE2_CheckBox  					oCB_ausblendenStornierte = 				new MyE2_CheckBox("Stornierte");
		MyE2_CheckBox  					oCB_ausblendenArchivAusblenden = 		new MyE2_CheckBox("Alt, nicht buchbar");
		MyE2_CheckBox  					oCB_ausblendenEingabeUnfertig = 		new MyE2_CheckBox("Nicht komplette Eingaben");
		MyE2_CheckBox  					oCB_ausblendenEingabeKeineBuchungspos = new MyE2_CheckBox("Hat keine Buchungspositionen");
		MyE2_CheckBox  					oCB_ausblendenUngebucht = 				new MyE2_CheckBox("Ungebucht");
		MyE2_CheckBox  					oCB_ausblendenTeilgebucht = 			new MyE2_CheckBox("Teils Gebuchte");
		MyE2_CheckBox  					oCB_ausblendenFertige = 				new MyE2_CheckBox("Komplett gebuchte");

		 */
		
		//2011-09-06: status sauberer definieren
//		/*
//		 * S O N D E R F A L L: Selektion stornierte ausblenden: alte, nicht buchbare werden auch im falle, sie sind storniert, mit dem status STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT definiert !!
//		 */
//		this.add(new E2_ListSelectorStandard(oCB_ausblendenStornierte,			"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG <> "+myCONST.STATUS_FUHRE__IST_STORNIERT+" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N')",""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenStornierte,			"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG <> "+myCONST.STATUS_FUHRE__IST_STORNIERT,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenArchivAusblenden,		"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG  <> "+myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenEingabeUnfertig,		"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG <> "+myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenEingabeKeineBuchungspos,"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG  <> "+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenUngebucht,				"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG <> "+myCONST.STATUS_FUHRE__UNGEBUCHT,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenTeilgebucht,			"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG  <> "+myCONST.STATUS_FUHRE__TEILSGEBUCHT,""));
		this.add(new E2_ListSelectorStandard(oCB_ausblendenFertige,				"JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG <> "+myCONST.STATUS_FUHRE__GANZGEBUCHT,""));
		
		
		this.add(oSelSpedition);
		this.add(oSelSorte);
		this.add(oSelBereichDatumAbhol);
		this.add(oSelBereichDatumLiefer);
		this.add(oSelBereichDatumAufladen);
		this.add(oSelBereichDatumAbladen);
		
		this.add(oSelBuchungsStatus);
		
		//ENZALARM2
		this.add(oSelAusnahme);
		this.add(oSelErzeugtVon);

		
		//2013-03-05: neuer db-gestuetzter listselektor
		//2021-03-01: umstellung auf den Kombi-DB-Selektor
		E2_ListSelector_DB_Defined2 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined2(cMODULE_KENNER);
		this.add(oDB_BasedSelektor);

		//2013-07-05: haendler-selektor
		//2013-06-21: selektor fuer die firmenbetreuer
		FU_Selektor_WaehleBetreuerFremdfirma   oSelBetreuerInFuhre = new FU_Selektor_WaehleBetreuerFremdfirma();
		this.add(oSelBetreuerInFuhre);
		
		
		//11 spalten
		int iBreite[] = {100,100,100,100,100,100,100,100,100,100,100};
		MyE2_Grid oGridSel = new MyE2_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		//MyE2_Grid oGridSel = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
	
		Alignment  oAL = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		//Zeile 1
		oGridSel.add(oCheckBoxOnlyEmptyLadegewicht,							1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxOnlyGebuchteTPA,								1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxOnlyNoTPA,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCB_ShowWareneingang,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oSelBereichDatumAbhol.get_oComponentForSelection(),	2, 1, E2_INSETS.I_0_0_5_0, oAL);
		oGridSel.add(oSelBereichDatumLiefer.get_oComponentForSelection(),	2, 1, E2_INSETS.I_5_0_5_0, oAL);
		oGridSel.add(this.HG("Sped.",oSelSpedition.get_oComponentWithoutText()),		1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(this.HG("ANR1",oSelSorte.get_oComponentWithoutText()),				1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(new MyE2_Label(""),									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		
		// Zeile 2
		oGridSel.add(oCheckBoxOnlyEmptyAbLadegewicht,						1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxOnlyStrecke,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxOnlyTPA,										1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCB_ShowWarenausgang,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oSelBereichDatumAufladen.get_oComponentForSelection(),	2, 1, E2_INSETS.I_0_0_5_0, oAL);
		oGridSel.add(oSelBereichDatumAbladen.get_oComponentForSelection(),	2, 1, E2_INSETS.I_5_0_5_0, oAL);
		oGridSel.add(this.HG("Bearb.",oSelErzeugtVon.get_oComponentWithoutText()),			1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(this.HG("Status",oSelBuchungsStatus.get_oComponentWithoutText()),		1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(new MyE2_Label(""),									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		
		
		// Zeile 3
		oGridSel.add(oCheckBoxOnlySTORNIERT,								1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxHasRechnungsEingang,							1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCheckBoxHasNoRechnungsEingang,						1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oCB_ShowDeletedRows,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		
//		oGridSel.add(new MyE2_Label(""),									4, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(oSelLand.get_oComponentForSelection(),					4, 1, E2_INSETS.I_0_0_2_0, oAL);

		oGridSel.add(this.HG("Ausn.",oSelAusnahme.get_oComponentWithoutText()),			1, 1, E2_INSETS.I_0_0_2_0, oAL);
//		oGridSel.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse"),iDropDownBreite,FU_SelectionComponentVector_Version1.iBreite),		
//																			1, 1, E2_INSETS.I_0_0_2_0, oAL);
		oGridSel.add(this.HG("Div.",oDB_BasedSelektor.get_oComponentForSelection()),	1, 1, E2_INSETS.I_0_0_2_0, oAL);

		oGridSel.add(new MyE2_Label(""),									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		
		
		
//		oGridSel.add(new E2_ComponentGroupHorizontal(0,	oSelSpedition.get_oComponentForSelection(),
//														oSelSorte.get_oComponentForSelection(),
//														E2_INSETS.I_0_0_10_0),	
//																			3, 1, E2_INSETS.I_0_0_2_0, oAL);
//		oGridSel.add(new E2_ComponentGroupHorizontal(0,	oSelErzeugtVon.get_oComponentForSelection(),
//														oSelBuchungsStatus.get_oComponentForSelection(),
//														E2_INSETS.I_0_0_10_0),	
//																			4, 1, E2_INSETS.I_0_0_2_0, oAL);
//
		
		
		
		//zwischenzeile
		oGridSel.add(new MyE2_Label(new MyE2_String("Ausblenden:"), new E2_FontItalic(-2)),7,1,E2_INSETS.I_0_0_0_0, oAL, new E2_ColorDDark());
		oGridSel.add(new MyE2_Label(""),4,E2_INSETS.I_0_0_0_0);
		
//		// Zeile 4
		oGridSel.add(oCB_ausblendenArchivAusblenden,						1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenStornierte,								1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenEingabeUnfertig,							1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenEingabeKeineBuchungspos,					1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenUngebucht,								1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenTeilgebucht,								1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());
		oGridSel.add(oCB_ausblendenFertige,									1, 1, E2_INSETS.I_0_0_2_0, oAL, new E2_ColorDDark());

		//oGridSel.add(new MyE2_Label(""),									1, 1, E2_INSETS.I_0_0_2_0, oAL);
		E2_ComponentGroupHorizontal_NG  oGroup = new E2_ComponentGroupHorizontal_NG(
													oSelBetreuerInFuhre.get_grid4Anzeige(bibARR.get_Array(70, 100, 30)),150,new Insets(10, 0, 10, 0),
													this.get_AktivPassivComponent(),95,E2_INSETS.I_0_0_0_0,
													oButtonSwitchToNew, 90,E2_INSETS.I_10_0_0_0);
		
		oGridSel.add(oGroup,4,1,E2_INSETS.I_10_0_10_0, oAL);
		
//		oGridSel.add(,1, 1, E2_INSETS.I_2_0_2_0, oAL);
//		
//		oGridSel.add(,						1, 1, E2_INSETS.I_0_0_2_0, oAL);
//		oGridSel.add(,									1, 1, E2_INSETS.I_0_0_2_0, oAL);
//		oGridSel.add(new MyE2_Label(""),									1, 1, E2_INSETS.I_0_0_2_0, oAL);

		
		
//		oGridSel.add(new E2_ComponentGroupHorizontal(0,	new MyE2_Label(new MyE2_String("Ausblenden:")),
//														oCB_ausblendenArchivAusblenden,
//														oCB_ausblendenStornierte, 
//														oCB_ausblendenEingabeUnfertig,
//														oCB_ausblendenEingabeKeineBuchungspos,
//														oCB_ausblendenUngebucht,
//														oCB_ausblendenTeilgebucht,
//														oCB_ausblendenFertige,
//														oSelAusnahme.get_oComponentForSelection(),
//														oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),
//														this.get_AktivPassivComponent(),
//														oButtonSwitchToNew,
//														E2_INSETS.I_0_0_10_0),oLayoutDataSpace_span6);


		
		
		this.set_oSelComponent(oGridSel);
		
	}

	
	private MyE2_Grid HG(String cText,  Component oComp) {
		MyE2_Grid gridRueck = new MyE2_Grid(FU_SelectionComponentVector_Version1.iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		gridRueck.add(new MyE2_Label(new MyE2_String(cText)), E2_INSETS.I_0_0_2_0);
		gridRueck.add(oComp, E2_INSETS.I_0_0_0_0);
		return gridRueck;
	}
	
	
}
