package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Auswertungen.XX_Auswertungen;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.BaseJumper;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_XLS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import echopointng.Separator;


 
public class AuswertungWarenstroemeNachLagerUndSorten extends XX_Auswertungen 
{

	private static MyE2_String cErlaeuterung = new MyE2_String(
			"Summe der Mengen gruppiert nach Sorten/Lager/Einheiten\n"+
			"mit Durchschnittspreis EK und VK \n"+
			"und damit Rohertag \n"+
			"(Es werden nur Fuhren betrachtet, die den Status <FERTIG GEBUCHT>\n"+
			" tragen !)"
			);	
	
	
	private MyE2_TextField_DatePOPUP_OWN tfDatumVon = null;
	private MyE2_TextField_DatePOPUP_OWN tfDatumBis = null;
	
	private String    					 cREPORTNUMMER = null;

	
	private MyE2_SelectField             oSelLieferanten = null;
	private MyE2_SelectField             oSelAbnehmer = null;
	
	private MyE2_SelectField             oSelLadesorte = null;
	private MyE2_SelectField             oSelAbladesorte = null;
	
	
	private Component_USER_DROPDOWN_NEW   oSelBetreuerLieferant = null;
	private Component_USER_DROPDOWN_NEW   oSelBetreuerAbnehmer = null;
	
	private MyE2_CheckBox                 oCB_NurFuhrenOhneOrte = new MyE2_CheckBox();
	private MyE2_CheckBox                 oCB_NurStreckenFuhren = new MyE2_CheckBox();
	
	private MyE2_CheckBox                 oCB_ShowErlaueterungen = new MyE2_CheckBox();
	
	
	//erstes grid- Vorbereitung der auswertung (im popup-fenster)
	private MyE2_Grid  oGridSelektion = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	
	//zweites grid- Druckvorbereitung
	private MyE2_Grid  oGridDruck = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	
	
	//2011-08-04: zusatzkontroll-element mit sprung in die fuhrenzentrale zu den fuhren der jeweiligen bloecke
	private MyE2_Grid  							oGridSprungInFuhreNachergebnis = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	private HashMap<String, Vector<String>>  	hmJumperIDs = new HashMap<String, Vector<String>>();
//	private MyE2_SelectField					lbJumpers = new MyE2_ListBox();

	
	public AuswertungWarenstroemeNachLagerUndSorten() throws myException 
	{
		super(new MyE2_String("Warenströme/Sorten-Lager"), cErlaeuterung, "AUSWERTUNG_WARENSTROEME", "JT_ZZ_AW_WARENSTROEME","warenstroeme.jasper",null);
		
		myDateHelper oDateHelp = new myDateHelper(new GregorianCalendar());
		
		//lbJumpers.add_oActionAgent(new ActionFuhrenJumper());
		
		//Standard-Bereich = 1.1.des jahres bis heute
		this.tfDatumVon = new MyE2_TextField_DatePOPUP_OWN(oDateHelp.get_cDateFormatForMask_FirstDayInYear(),100);
		this.tfDatumBis = new MyE2_TextField_DatePOPUP_OWN(oDateHelp.get_cDateFormatForMask(),100);
		
		
		this.oSelBetreuerLieferant = new Component_USER_DROPDOWN_NEW(false, 150);
		this.oSelBetreuerAbnehmer = new Component_USER_DROPDOWN_NEW(false, 150);
		
		
		String cSQL_Lieferanten = "SELECT NVL(NAME1,'')||' '||NVL(ORT,''),ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN (" +
									" SELECT ID_ADRESSE_START FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
									" UNION "+
									" SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL='EK'" +
										") ORDER BY 1";

		String cSQL_Abnehmer    = "SELECT NVL(NAME1,'')||' '||NVL(ORT,''),ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN (" +
									" SELECT ID_ADRESSE_ZIEL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
									" UNION "+
									" SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL='VK'" +
										") ORDER BY 1";


		
		this.oSelLieferanten = 	new MyE2_SelectField(cSQL_Lieferanten, false, true, false, false);
		this.oSelAbnehmer = 	new MyE2_SelectField(cSQL_Abnehmer, false, true, false, false);
		

		String cSQL_Sorte       = "SELECT NVL(ANR1,'<anr1>')||' '||NVL(ARTBEZ1,''), ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_ARTIKEL IN (" +
										"SELECT ID_ARTIKEL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
										" UNION "+
										"SELECT ID_ARTIKEL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
										" ) ORDER BY 1";
		
		this.oSelLadesorte = 	new MyE2_SelectField(cSQL_Sorte, false, true, false, false);
		this.oSelAbladesorte = 	new MyE2_SelectField(cSQL_Sorte, false, true, false, false);
		
		
		String cTooltipDatum =  	"Es werden Fuhren oder Teilfuhren berücksichtigt, bei denen das Wiegedatum im definierten Zeitraum liegt. \n"+
									"Dabei kann es vorkommen, dass eine Fuhre nur halbseitigt berücksichtigt wird, da auf der Ladeseite ein \n"+
									"Wiegedatum innerhalb des Zeitraums und auf der Abladeseite ein Wiegedatum außerhalb des Zeitraums vorhanden ist";
		
		String cTooltipLieferant = 	"Es werden alle Fuhren-Teilorte berücksichtigt, auf deren linker Seite (Fuhre oder Fuhrenort) diese Adresse vorkommt. \n" +
									"Dabei werden Fuhren mit unterschiedlichen Lieferanten nur anteilig berücksichtigt.";
		
		String cTooltipAbnehmer = 	"Es werden alle Fuhren-Teilorte berücksichtigt, auf deren rechter Seite (Fuhre oder Fuhrenort) diese Adresse vorkommt. \n" +
									"Dabei werden Fuhren mit unterschiedlichen Abnehmern nur anteilig berücksichtigt.";

		String cTooltipLadesorte = 	"Es werden alle Fuhren berücksichtigt, auf deren linker Seite (Fuhre oder Fuhrenort) diese Sorte vorkommt. \n" +
									"Werden in der Fuhre unterschiedliche Sorten verwendet, werden diese Fuhren nur anteilig berücksichtigt.";

		String cTooltipAbladesorte=	"Es werden alle Fuhren berücksichtigt, auf deren rechter Seite (Fuhre oder Fuhrenort) diese Sorte vorkommt. \n" +
									"Werden in der Fuhre unterschiedliche Sorten verwendet, werden diese Fuhren nur anteilig berücksichtigt.";

		String cTooltipBetreuerLief =	"Es werden alle Fuhren berücksichtigt, auf deren linker Seite (Fuhre oder Fuhrenort) eine Firma des gewählten Betreuers vorkommt. \n" +
										"Werden in der Fuhre Firmen unterschiedlicher Betreuer verwendet, werden diese Fuhren nur anteilig berücksichtigt.";

		String cTooltipBetreuerAbn =	"Es werden alle Fuhren berücksichtigt, auf deren rechter Seite (Fuhre oder Fuhrenort) eine Firma des gewählten Betreuers vorkommt. \n" +
										"Werden in der Fuhre Firmen unterschiedlicher Betreuer verwendet, werden diese Fuhren nur anteilig berücksichtigt.";

		String cTooltipNurFuhrenOhneOrt =	"Es werden nur Fuhren berücksichtigt, die keine Zusatzlade- oder -Abladeorte haben";
		
		String cTooltipNurStrecken =		"Es werden nur Fuhren berücksichtigt, die keine eigenen Lagerorte enthalten";
		
		String cTooltipZeigeErlaeuterungen = "Druckt am Ende der Liste eine Erklärung aus, wie die Spalten zustande kommen ";
		
		
		
		this.tfDatumVon.get_oTextField().setToolTipText(new MyE2_String(cTooltipDatum).CTrans());
		this.tfDatumBis.get_oTextField().setToolTipText(new MyE2_String(cTooltipDatum).CTrans());
		
		this.oSelLieferanten.setToolTipText(new MyE2_String(cTooltipLieferant).CTrans());
		this.oSelAbnehmer.setToolTipText(new MyE2_String(cTooltipAbnehmer).CTrans());

		this.oSelLadesorte.setToolTipText(new MyE2_String(cTooltipLadesorte).CTrans());
		this.oSelAbladesorte.setToolTipText(new MyE2_String(cTooltipAbladesorte).CTrans());

		this.oSelBetreuerLieferant.setToolTipText(new MyE2_String(cTooltipBetreuerLief).CTrans());
		this.oSelBetreuerAbnehmer.setToolTipText(new MyE2_String(cTooltipBetreuerAbn).CTrans());

		this.oCB_NurFuhrenOhneOrte.setToolTipText(new MyE2_String(cTooltipNurFuhrenOhneOrt).CTrans());
		this.oCB_NurStreckenFuhren.setToolTipText(new MyE2_String(cTooltipNurStrecken).CTrans());
		
		this.oCB_ShowErlaueterungen.setToolTipText(new MyE2_String(cTooltipZeigeErlaeuterungen).CTrans());
		
		this.oSelLieferanten.setWidth(new Extent(130));
		this.oSelAbnehmer.setWidth(new Extent(130));
		this.oSelLadesorte.setWidth(new Extent(130));
		this.oSelAbladesorte.setWidth(new Extent(130));
		this.oSelBetreuerLieferant.setWidth(new Extent(130));
		this.oSelBetreuerAbnehmer.setWidth(new Extent(130));

		
		
	}


	@Override
	public MyE2_Button get_StartButton() throws myException 
	{
		MyE2_Button oBT_Start = new MyE2_Button("Starte Auswertung der Warenströme");
		oBT_Start.add_oActionAgent(new ownActionAgent());
		oBT_Start.setStyle(MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		oBT_Start.setLineWrap(true);
		
		return oBT_Start;
	}

	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new ownAuswahlcontainer();
		}

	}
	
	
	private class ownAuswahlcontainer extends E2_BasicModuleContainer
	{

		public ownAuswahlcontainer() throws myException 
		{
			super();

			AuswertungWarenstroemeNachLagerUndSorten oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
			
			oThis.oGridSelektion.setSize(5);
			oThis.oGridSelektion.removeAll();
			oThis.oGridDruck.removeAll();
			
			oThis.oSelBetreuerLieferant.add_oActionAgent(new ownActionZusatzCheckboxFuerMitarbeiterBewertungen());
			oThis.oSelBetreuerAbnehmer.add_oActionAgent(new ownActionZusatzCheckboxFuerMitarbeiterBewertungen());
			
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Fuhren mit Wiegedatum ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("von ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(tfDatumVon, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" bis ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(tfDatumBis, E2_INSETS.I_5_5_5_5);

			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Lieferant ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelLieferanten, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" Abnehmer ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelAbnehmer, E2_INSETS.I_5_5_5_5);
			
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Ladesorte ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelLadesorte, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" Abladesorte ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelAbladesorte, E2_INSETS.I_5_5_5_5);
			
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Betreuer Lief.Seite")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelBetreuerLieferant, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" Betreuer Abneh.Seite ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oSelBetreuerAbnehmer, E2_INSETS.I_5_5_5_5);

			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Nur Fuhren berücksichtigen, die KEINE Zusatzorte haben")),3, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oCB_NurFuhrenOhneOrte,1, E2_INSETS.I_5_5_5_5);
			
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Nur reine Streckenfuhren berücksichtigen")),3, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oCB_NurStreckenFuhren,1, E2_INSETS.I_5_5_5_5);

			oThis.oGridSelektion.add(new Separator(),5, E2_INSETS.I_5_5_5_5);
			
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String("Erläuterungen der Felder anzeigen")),3, E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(new MyE2_Label(new MyE2_String(" ")), E2_INSETS.I_5_5_5_5);
			oThis.oGridSelektion.add(oCB_ShowErlaueterungen,1, E2_INSETS.I_5_5_5_5);
			
			

			MyE2_Button oBT_StartINNEN= new MyE2_Button(new MyE2_String("Erzeuge Auswerte-Tabelle"));
			oBT_StartINNEN.add_oActionAgent(new ownActionStarteAuswertungINNEN());
			
			oThis.oGridSelektion.add(oBT_StartINNEN, new Insets(5,10,5,5));
			
			
			
			oThis.oGridDruck.add(new buttonDruckePdf(),E2_INSETS.I_10_10_10_10);
			oThis.oGridDruck.add(new buttonDruckeXLS(),E2_INSETS.I_10_10_10_10);

			
			//startzustand:
			oThis.oGridSelektion.setVisible(true);
			oThis.oGridDruck.setVisible(false);
			
			this.add(oThis.oGridSelektion,E2_INSETS.I_10_10_10_10);
			this.add(oThis.oGridDruck,E2_INSETS.I_10_10_10_10);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(400), new MyE2_String("Auswertung Warenströme"));
			
		}
		
		
		/*
		 * actionagent setzt den schalter nurFuhrenOhneOrte wenn ein Haendler im Spiel ist, um dessen Ertrag unabhängig von 
		 * zusatzorten, die den wert verfaelschen koenne, zu ermitteln
		 */
		private class ownActionZusatzCheckboxFuerMitarbeiterBewertungen extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_SelectField oSeleFieldBetreuer = (MyE2_SelectField)oExecInfo.get_MyActionEvent().getSource();
				
				if (S.isFull(oSeleFieldBetreuer.get_ActualWert()))
				{
					AuswertungWarenstroemeNachLagerUndSorten oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
					oThis.oCB_NurFuhrenOhneOrte.setSelected(true);
					oThis.oCB_NurStreckenFuhren.setSelected(true);
				}
			}
		}
		
		
		private class buttonDruckePdf extends MyE2_Button
		{

			public buttonDruckePdf() 
			{
				super("Drucke die Liste als PDF");
				
				this.add_oActionAgent(new XX_ActionAgent() 
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{
						ownJasperHASH  oJasperHash = new ownJasperHASH(new JasperFileDef_PDF());
						
						try
						{
							oJasperHash.Build_tempFile(true);
							oJasperHash.get_oTempFileWithSendeName().Download_File();
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error printing !!")));
						}
						
					}
				});
				
			}
			
		}
		
		
		private class buttonDruckeXLS extends MyE2_Button
		{

			public buttonDruckeXLS() 
			{
				super("Drucke die Liste als XLS-Datei");
				
				this.add_oActionAgent(new XX_ActionAgent() 
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{
						ownJasperHASH  oJasperHash = new ownJasperHASH(new JasperFileDef_XLS());

						try
						{
							oJasperHash.Build_tempFile(true);
							oJasperHash.get_oTempFileWithSendeName().Download_File();
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error printing !!")));
						}
						
					}
				});
				
			}
			
		}
		

		
		private class ownJasperHASH extends E2_JasperHASH_STD
		{

			public ownJasperHASH(JasperFileDef jasperFileDef) throws myException
			{
				super("AW_WARENSTROEME.jasper", jasperFileDef);
				
				AuswertungWarenstroemeNachLagerUndSorten oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
				
				this.put("REPORTNUMMER", AuswertungWarenstroemeNachLagerUndSorten.this.cREPORTNUMMER);
				
				this.put("ZEIGE_ERLAEUTERUNGEN", oThis.oCB_ShowErlaueterungen.isSelected()?"Y":"N");
				
				//folgende parameter sind nur fuer die beschriftung des report-titels
				HashMap<String, String>  hmTitel = new HashMap<String, String>();
				
				hmTitel.put("WIEGEDATUM_START", AuswertungWarenstroemeNachLagerUndSorten.this.tfDatumVon.get_oFormatedDateFromTextField());
				hmTitel.put("WIEGEDATUM_ENDE", 	AuswertungWarenstroemeNachLagerUndSorten.this.tfDatumBis.get_oFormatedDateFromTextField());
				
				hmTitel.put("LIEFERANT", "");
				if (S.isFull(oThis.oSelLieferanten.get_ActualWert()))
				{
					RECORD_ADRESSE  recLief = new RECORD_ADRESSE(oThis.oSelLieferanten.get_ActualWert());
					hmTitel.put("LIEFERANT", recLief.get___KETTE(bibALL.get_Vector("NAME1", "ORT")));
				}
						
				hmTitel.put("ABNEHMER", "");
				if (S.isFull(oThis.oSelAbnehmer.get_ActualWert()))
				{
					RECORD_ADRESSE  recLief = new RECORD_ADRESSE(oThis.oSelAbnehmer.get_ActualWert());
					hmTitel.put("ABNEHMER", recLief.get___KETTE(bibALL.get_Vector("NAME1", "ORT")));
				}
						
				hmTitel.put("LADESORTE", "");
				if (S.isFull(oThis.oSelLadesorte.get_ActualWert()))
				{
					RECORD_ARTIKEL  recLief = new RECORD_ARTIKEL(oThis.oSelLadesorte.get_ActualWert());
					hmTitel.put("LADESORTE", recLief.get___KETTE(bibALL.get_Vector("ANR1", "ARTBEZ1")));
				}
						
				hmTitel.put("ABLADESORTE", "");
				if (S.isFull(oThis.oSelAbladesorte.get_ActualWert()))
				{
					RECORD_ARTIKEL  recLief = new RECORD_ARTIKEL(oThis.oSelAbladesorte.get_ActualWert());
					hmTitel.put("ABLADESORTE", recLief.get___KETTE(bibALL.get_Vector("ANR1", "ARTBEZ1")));
				}
				
				hmTitel.put("EINKAEUFER", "");
				if (S.isFull(oThis.oSelBetreuerLieferant.get_ActualWert()))
				{
					RECORD_USER  recLief = new RECORD_USER(oThis.oSelBetreuerLieferant.get_ActualWert());
					hmTitel.put("EINKAEUFER", recLief.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
				}

				hmTitel.put("VERKAEUFER", "");
				if (S.isFull(oThis.oSelBetreuerAbnehmer.get_ActualWert()))
				{
					RECORD_USER  recLief = new RECORD_USER(oThis.oSelBetreuerAbnehmer.get_ActualWert());
					hmTitel.put("VERKAEUFER", recLief.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
				}
				
				hmTitel.put("NUR_FUHREN_OHNE_ORTE", "");
				if (oThis.oCB_NurFuhrenOhneOrte.isSelected())
				{
					hmTitel.put("NUR_FUHREN_OHNE_ORTE", "Nur Fuhren, die keine Zusatzorte haben");
				}
				
				hmTitel.put("NUR_STRECKENFUHREN", "");
				if (oThis.oCB_NurStreckenFuhren.isSelected())
				{
					hmTitel.put("NUR_STRECKENFUHREN", "Nur Streckenfuhren (keine eigenen Lade/Abladestellen)");
				}
				
				
				this.put("TITELINFO", hmTitel);
				
			

			}
			
		}
		
		
		
		
		private class ownActionStarteAuswertungINNEN extends XX_ActionAgent
		{
			private int 										iAnzahlEinheitenFehler = 0;
			private int 										iAnzahlOrteFehler = 0;
			private String   									cNewReportNummer = null;
			
			//private RECLIST_VPOS_TPA_FUHRE                      reclistFuhren = null;
			private String[][]   					    		cID_FUHREN_SELEKTION = null;
			
			private String   									cDateVon = null;
			private String   									cDateBis = null;
			
			
			private RECLIST_ARTIKEL_BEZ 						recListArtbez = null;
			private RECLIST_ARTIKEL 							recListArt = null;
			
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				AuswertungWarenstroemeNachLagerUndSorten oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
				
				//zuerst den Ergebnis-Button neu aufbauen
				oThis.oGridSprungInFuhreNachergebnis.removeAll();
				
				
				oThis.DELETE_OLD_TEMPTABLE_VALUES();
				this.cNewReportNummer=oThis.get_NextReportAktionNumber();
				oThis.cREPORTNUMMER = this.cNewReportNummer;                   //fuers reporting
				
				// Manfred:
				// Cache von Artikel und Artikelbez
				this.recListArtbez = new RECLIST_ARTIKEL_BEZ("","");
				this.recListArt	  = new RECLIST_ARTIKEL("", "");

				// hier wird jetzt ausgewertet
				String cSQL_BasisQueryFuhre = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
												" WHERE JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG=5 " +
												" AND   NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' " +
												" AND   NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' ";
				
				try
				{
					myDateHelper oDateVon = new myDateHelper(oThis.tfDatumVon.get_oTextField().getText());
					myDateHelper oDateBis = new myDateHelper(oThis.tfDatumBis.get_oTextField().getText());
					
					this.cDateVon = oDateVon.get_cDateFormat_ISO_FORMAT();
					this.cDateBis = oDateBis.get_cDateFormat_ISO_FORMAT();
					
					
					
					
					cSQL_BasisQueryFuhre = cSQL_BasisQueryFuhre + " AND ID_VPOS_TPA_FUHRE IN (" +
												" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE TO_CHAR(FU.DATUM_AUFLADEN,'YYYY-MM-DD')>="+bibALL.MakeSql(cDateVon)+
																														" AND TO_CHAR(FU.DATUM_AUFLADEN,'YYYY-MM-DD')<="+bibALL.MakeSql(cDateBis)+
												" UNION "+
												" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE TO_CHAR(FU.DATUM_ABLADEN,'YYYY-MM-DD')>="+bibALL.MakeSql(cDateVon)+
																														" AND TO_CHAR(FU.DATUM_ABLADEN,'YYYY-MM-DD')<="+bibALL.MakeSql(cDateBis)+
																														
												"  UNION  "+
												" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO WHERE TO_CHAR(FUO.DATUM_LADE_ABLADE,'YYYY-MM-DD')>="+bibALL.MakeSql(cDateVon)+
																														" AND TO_CHAR(FUO.DATUM_LADE_ABLADE,'YYYY-MM-DD')<="+bibALL.MakeSql(cDateBis)+														
												" ) ";
					
					
					String cQueryAddOn = "";
					
					if (S.isFull(oThis.oSelLieferanten.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE FU.ID_ADRESSE_START="+oThis.oSelLieferanten.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO WHERE NVL(FUO.DELETED,'N')='N' AND  FUO.DEF_QUELLE_ZIEL='EK' AND FUO.ID_ADRESSE="+oThis.oSelLieferanten.get_ActualWert()+
															" ) ";
					}
					
					if (S.isFull(oThis.oSelAbnehmer.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE FU.ID_ADRESSE_ZIEL="+oThis.oSelAbnehmer.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO WHERE NVL(FUO.DELETED,'N')='N' AND   FUO.DEF_QUELLE_ZIEL='VK' AND FUO.ID_ADRESSE="+oThis.oSelAbnehmer.get_ActualWert()+
															" ) ";
					}

					
					if (S.isFull(oThis.oSelLadesorte.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=FU.ID_ARTIKEL_BEZ_EK)" +
																	" WHERE AB.ID_ARTIKEL="+oThis.oSelLadesorte.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO  LEFT OUTER JOIN JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=FUO.ID_ARTIKEL_BEZ)" +
																	" WHERE NVL(FUO.DELETED,'N')='N' AND   FUO.DEF_QUELLE_ZIEL='EK' AND AB.ID_ARTIKEL="+oThis.oSelLadesorte.get_ActualWert()+
															" ) ";
					}
					
					if (S.isFull(oThis.oSelAbladesorte.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=FU.ID_ARTIKEL_BEZ_VK)" +
																	" WHERE AB.ID_ARTIKEL="+oThis.oSelLadesorte.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO  LEFT OUTER JOIN JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=FUO.ID_ARTIKEL_BEZ)" +
																	" WHERE NVL(FUO.DELETED,'N')='N' AND   FUO.DEF_QUELLE_ZIEL='VK' AND AB.ID_ARTIKEL="+oThis.oSelAbladesorte.get_ActualWert()+
															" ) ";
					}

					if (S.isFull(oThis.oSelBetreuerLieferant.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN JT_ADRESSE AD ON (AD.ID_ADRESSE=FU.ID_ADRESSE_START)" +
																	" WHERE AD.ID_USER="+oThis.oSelBetreuerLieferant.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO  LEFT OUTER JOIN JT_ADRESSE AD ON (AD.ID_ADRESSE=FUO.ID_ADRESSE)" +
																	" WHERE NVL(FUO.DELETED,'N')='N' AND   FUO.DEF_QUELLE_ZIEL='EK' AND AD.ID_USER="+oThis.oSelBetreuerLieferant.get_ActualWert()+
															" ) ";
					}
					
					if (S.isFull(oThis.oSelBetreuerAbnehmer.get_ActualWert()))
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
															" SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN JT_ADRESSE AD ON (AD.ID_ADRESSE=FU.ID_ADRESSE_ZIEL)" +
																	" WHERE AD.ID_USER="+oThis.oSelBetreuerAbnehmer.get_ActualWert()+
															"  UNION  "+
															" SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO  LEFT OUTER JOIN JT_ADRESSE AD ON (AD.ID_ADRESSE=FUO.ID_ADRESSE)" +
																	" WHERE NVL(FUO.DELETED,'N')='N' AND  FUO.DEF_QUELLE_ZIEL='VK' AND AD.ID_USER="+oThis.oSelBetreuerAbnehmer.get_ActualWert()+
															" ) ";
					}
					
					
					if (oThis.oCB_NurFuhrenOhneOrte.isSelected())
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
						" SELECT ID_VPOS_TPA_FUHRE  FU1 FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU1 " +
								" WHERE (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO1 " +
										" WHERE NVL(FUO1.DELETED,'N')='N' AND FUO1.ID_VPOS_TPA_FUHRE=FU1.ID_VPOS_TPA_FUHRE)" +
										"=0 )";
					}
					
					
					if (oThis.oCB_NurStreckenFuhren.isSelected())
					{
						cQueryAddOn = cQueryAddOn + " AND ID_VPOS_TPA_FUHRE IN (" +
												" SELECT ID_VPOS_TPA_FUHRE  FU2  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU2  "+ 
											    " WHERE (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO2 " +
											    		" WHERE NVL(FUO2.DELETED,'N')='N' " +
											    		" AND FUO2.ID_ADRESSE="+bibALL.get_ID_ADRESS_MANDANT()+"" +
											    		" AND FUO2.ID_VPOS_TPA_FUHRE=FU2.ID_VPOS_TPA_FUHRE)" +
											    		"=0 "+
											    " AND FU2.ID_ADRESSE_START<>"+bibALL.get_ID_ADRESS_MANDANT()+
											    " AND FU2.ID_ADRESSE_ZIEL<>"+bibALL.get_ID_ADRESS_MANDANT()+" )";
					}					
					
					cSQL_BasisQueryFuhre = cSQL_BasisQueryFuhre+cQueryAddOn;
					
					
					//System.out.println(cSQL_BasisQuery);
					
					//this.reclistFuhren = new RECLIST_VPOS_TPA_FUHRE(cSQL_BasisQueryFuhre);
					this.cID_FUHREN_SELEKTION = bibDB.EinzelAbfrageInArray(cSQL_BasisQueryFuhre);
					
					if (this.cID_FUHREN_SELEKTION==null)
					{
						throw new myException("Error Fuhren-Query "+cSQL_BasisQueryFuhre);
					}
					
									
					new E2_ServerPushMessageContainer_STD(	new Extent(500),
															new Extent(150),
															new MyE2_String("Prüfung der Fuhren läuft ..."),
															true,
															false,
															5000,
															cID_FUHREN_SELEKTION.length,
															20,
															null)
					{

						@Override
						public void Run_Loop() throws myException
						{
							ownActionStarteAuswertungINNEN  			ooThis = ownActionStarteAuswertungINNEN.this;
							
							//aufbau einer hashmap mit records, die im falle eines neueitrages in der map ergaenzt werden
							//der key der map ist: ID_ADRESSE_LAGER+"|"+ID_SORTE+"|"+ID_EINHEIT+"|"+ID_EINHEIT_PREIS
							//das Lager "strecke" bekommt die ID 0
							//das Lager "mixed" bekommt die ID -1
							//das lager "intern" bekommt die ID -2
							
							
							//hasmmap mit allen gesammelten werten in den jeweiligen objekten
							hm_DatenTypUndMenge hmTypUndMenge = new hm_DatenTypUndMenge();

							
							String[][] arrayFuhren = ownActionStarteAuswertungINNEN.this.cID_FUHREN_SELEKTION;
							
							
							for (int i=0;i<arrayFuhren.length;i++)
							{
								RECORD_VPOS_TPA_FUHRE_EXT  recFuhre = new RECORD_VPOS_TPA_FUHRE_EXT(arrayFuhren[i][0]);
								
								if (i%100==0)
								{
									if (this.get_oBalken()!=null)
										this.get_oBalken().set_Wert(i);
								}
								
							    int iKlasse =	recFuhre.get_FuhrenTyp();
						
								
								if (iKlasse==RECORD_VPOS_TPA_FUHRE_EXT.STRECKE) 
								{
									String cID_SorteEK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();
									String cID_SorteVK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();		
									String cID_EinheitEK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 
									String cID_EinheitVK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 

									String cMengendivisorEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									String cMengendivisorVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									

									if ((!cID_EinheitEK.equals(cID_EinheitVK)) || (!cID_EinheitPreisEK.equals(cID_EinheitPreisVK)) ||  (!cMengendivisorEK.equals(cMengendivisorVK))) 
									{
										ownActionStarteAuswertungINNEN.this.iAnzahlEinheitenFehler++;
										continue;
									}
								   
									
									if (ooThis.get_bIstInDerAuswahl(recFuhre,true))
									{
										DatenTypUndMenge  dtEK = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator("0",cID_SorteEK,cID_EinheitEK,cID_EinheitPreisEK,cMengendivisorEK),
												recFuhre);
	
										recFuhre.fill_PreisInfos(dtEK, null);
									   
										dtEK.ADD("MENGE_FUHRE_WE",recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
										dtEK.ADD("MENGE_FUHRE_ABZUG_WE",recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
									}
									if (ooThis.get_bIstInDerAuswahl(recFuhre,false))
									{
										DatenTypUndMenge  dtVK = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator("0",cID_SorteVK,cID_EinheitVK,cID_EinheitPreisVK,cMengendivisorVK),
												recFuhre);
	
										recFuhre.fill_PreisInfos(null, dtVK);
										
										dtVK.ADD("MENGE_FUHRE_WA",recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
										dtVK.ADD("MENGE_FUHRE_ABZUG_WA",recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
									}

									
									
									//jetzt die zusatzorte untersuchen
									RECLIST_VPOS_TPA_FUHRE_ORT  reclistZusatzort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
															"NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", "", false);
									

									for (int k=0;k<reclistZusatzort.get_vKeyValues().size();k++)
									{
										RECORD_VPOS_TPA_FUHRE_ORT_EXT recOrtExt = new RECORD_VPOS_TPA_FUHRE_ORT_EXT(reclistZusatzort.get(k));
										
										if (!ooThis.get_bIstInDerAuswahl(recOrtExt))
										{
											continue;
										}
										
										DatenTypUndMenge dtORT = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator("0",
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_PREIS_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF()),
																	recOrtExt);
										
										if (recOrtExt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
										{
											dtORT.ADD("MENGE_FUHRE_WE",recOrtExt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO));
											dtORT.ADD("MENGE_FUHRE_ABZUG_WE",recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
										}
										else
										{
											dtORT.ADD("MENGE_FUHRE_WA",recOrtExt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO));
											dtORT.ADD("MENGE_FUHRE_ABZUG_WA",recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
										}
										
										recOrtExt.fill_PreisInfo(dtORT);	

									}
									   
								}
								else if (iKlasse==RECORD_VPOS_TPA_FUHRE_EXT.EX_LAGER)
								{
									String cID_ADRESSE_LAGER = recFuhre.get_ID_ADRESSE_LAGER_START_cUF();

									String cID_SorteEK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();
									String cID_SorteVK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();		
									String cID_EinheitEK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 
									String cID_EinheitVK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 

									String cMengendivisorEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									String cMengendivisorVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									
									//sicherung gegen fehler
									if ((!cID_EinheitEK.equals(cID_EinheitVK)) || (!cID_EinheitPreisEK.equals(cID_EinheitPreisVK)) ||  (!cMengendivisorEK.equals(cMengendivisorVK))) 
									{
										ownActionStarteAuswertungINNEN.this.iAnzahlEinheitenFehler++;
										continue;
									}
								   

									if (ooThis.get_bIstInDerAuswahl(recFuhre, false))
									{
										DatenTypUndMenge  	dtVK = 			hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(cID_ADRESSE_LAGER,cID_SorteVK,cID_EinheitVK,cID_EinheitPreisVK,cMengendivisorVK),
																												recFuhre);
										dtVK.ADD("MENGE_FUHRE_WA",			recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
										dtVK.ADD("MENGE_FUHRE_ABZUG_WA", 	recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
										recFuhre.fill_PreisInfos(null, dtVK);
									}
								
									if (ooThis.get_bIstInDerAuswahl(recFuhre, true))
									{
										//bei ex-lager-fuhren ist es immer eine fuhre: links 1 ort (eigen)   -> rechts n orte (fremd)
										//die fuhrenorte duerfen in diesem Wert nicht beruecksichtigt werden, da diese alle rechts stehen
										DatenTypUndMenge  	dtExLager =		hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(cID_ADRESSE_LAGER,cID_SorteEK,cID_EinheitEK,cID_EinheitPreisEK,cMengendivisorEK),
																												recFuhre);
										dtExLager.ADD("MENGE_LAGERAUSGANG", 		recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
										dtExLager.ADD("MENGE_ABZUG_LAGERAUSGANG", 	recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
									}
									
								   
									//jetzt die zusatzorte untersuchen (duerfte nur VK-Orte geben)
									RECLIST_VPOS_TPA_FUHRE_ORT  reclistZusatzort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
															"NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", "", false);
									

									for (int k=0;k<reclistZusatzort.get_vKeyValues().size();k++)
									{
										RECORD_VPOS_TPA_FUHRE_ORT_EXT recOrtExt = new RECORD_VPOS_TPA_FUHRE_ORT_EXT(reclistZusatzort.get(k));
										
										if (!ooThis.get_bIstInDerAuswahl(recOrtExt))
										{
											continue;
										}

										
										if (recOrtExt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))   //bei dieser fuhrenart duerften keine ladeorte mehr vorkommen
										{
											ooThis.iAnzahlOrteFehler++;
											continue;
										}

										
										DatenTypUndMenge dtORT = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(
																									cID_ADRESSE_LAGER,
																									recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(),
																									recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_cUF(),
																									recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_PREIS_cUF(),
																									recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF()),
																									recOrtExt);
										
										dtORT.ADD("MENGE_FUHRE_WA",				recOrtExt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO));
										dtORT.ADD("MENGE_FUHRE_ABZUG_WA",		recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));

										recOrtExt.fill_PreisInfo(dtORT);	
									}
								}
								else  if (iKlasse==RECORD_VPOS_TPA_FUHRE_EXT.IN_LAGER)
								{
									String cID_ADRESSE_LAGER = recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF();
									
									String cID_SorteEK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();
									String cID_SorteVK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();		
									String cID_EinheitEK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 
									String cID_EinheitVK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 

									String cMengendivisorEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									String cMengendivisorVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 

									//sicherung gegen fehler
									if ((!cID_EinheitEK.equals(cID_EinheitVK)) || (!cID_EinheitPreisEK.equals(cID_EinheitPreisVK)) ||  (!cMengendivisorEK.equals(cMengendivisorVK))) 
									{
										ownActionStarteAuswertungINNEN.this.iAnzahlEinheitenFehler++;
										continue;
									}

									
									if (ooThis.get_bIstInDerAuswahl(recFuhre, true))
									{

										DatenTypUndMenge  dtEK = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(cID_ADRESSE_LAGER,cID_SorteEK,cID_EinheitEK,cID_EinheitPreisEK,cMengendivisorEK),
																									recFuhre);
										dtEK.ADD("MENGE_FUHRE_WE",		recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
										dtEK.ADD("MENGE_FUHRE_ABZUG_WE",recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
										recFuhre.fill_PreisInfos(dtEK, null);
	
	
									}

									if (ooThis.get_bIstInDerAuswahl(recFuhre, false))
									{
										//bei in-lager-fuhren ist es immer eine fuhre: links  n orte (fremd)   -> rechts 1 ort (eigen)
										//die fuhrenorte duerfen in diesem Wert nicht beruecksichtigt werden, da diese alle links stehen
										DatenTypUndMenge  	dtInLager =		hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(cID_ADRESSE_LAGER,cID_SorteVK,cID_EinheitVK,cID_EinheitPreisVK,cMengendivisorVK),
																												recFuhre);
										dtInLager.ADD("MENGE_LAGEREINGANG",recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
										dtInLager.ADD("MENGE_ABZUG_LAGEREINGANG",recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
	
									}

									
									
									//jetzt die zusatzorte untersuchen (duerfte nur VK-Orte geben)
									RECLIST_VPOS_TPA_FUHRE_ORT  reclistZusatzort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
															"NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", "", false);

									for (int k=0;k<reclistZusatzort.get_vKeyValues().size();k++)
									{
										RECORD_VPOS_TPA_FUHRE_ORT_EXT recOrtExt = new RECORD_VPOS_TPA_FUHRE_ORT_EXT(reclistZusatzort.get(k));
										
										if (!ooThis.get_bIstInDerAuswahl(recOrtExt))
										{
											continue;
										}
										
										if (recOrtExt.get_DEF_QUELLE_ZIEL_cUF().equals("VK"))
										{
											ooThis.iAnzahlOrteFehler++;
											continue;
										}
										
										DatenTypUndMenge dtORT = hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(
																	cID_ADRESSE_LAGER,
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_PREIS_cUF(),
																	recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF()),
																									recOrtExt);
										
										dtORT.ADD("MENGE_FUHRE_WE",			recOrtExt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO));
										dtORT.ADD("MENGE_FUHRE_ABZUG_WE",	recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));

										recOrtExt.fill_PreisInfo(dtORT);	
									}
								}
								else if (iKlasse==RECORD_VPOS_TPA_FUHRE_EXT.MIXED)
								{
									
									String cID_SorteEK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();
									String cID_SorteVK 			= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_ARTIKEL_cUF();		
									String cID_EinheitEK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 
									String cID_EinheitVK 		= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_cUF();  	
									String cID_EinheitPreisVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_ID_EINHEIT_PREIS_cUF(); 

									String cMengendivisorEK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 
									String cMengendivisorVK 	= recListArt.get( (recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF())).get_ID_ARTIKEL_cUF()).get_MENGENDIVISOR_cUF(); 

									//sicherung gegen fehler
									if ((!cID_EinheitEK.equals(cID_EinheitVK)) || (!cID_EinheitPreisEK.equals(cID_EinheitPreisVK)) ||  (!cMengendivisorEK.equals(cMengendivisorVK))) 
									{
										ownActionStarteAuswertungINNEN.this.iAnzahlEinheitenFehler++;
										continue;
									}
								   
									
									if (ooThis.get_bIstInDerAuswahl(recFuhre, true))
									{
										if (! (recFuhre.get_ID_ADRESSE_START_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"))))
										{
											DatenTypUndMenge  dtEK=hmTypUndMenge.get_DatenTypUndMenge(new LagerSorteHashGenerator(
																											"-1",
																											cID_SorteEK,
																											cID_EinheitEK,
																											cID_EinheitPreisEK,
																											cMengendivisorEK),
																											recFuhre);
											
											dtEK.ADD("MENGE_FUHRE_WE",			recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
											dtEK.ADD("MENGE_FUHRE_ABZUG_WE",	recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
											recFuhre.fill_PreisInfos(dtEK, null);
										}
										else   //dann ist es lagerausgang
										{
											DatenTypUndMenge  dtLager=	hmTypUndMenge.get_DatenTypUndMenge(
																		new LagerSorteHashGenerator(recFuhre.get_ID_ADRESSE_LAGER_START_cUF(),cID_SorteEK,cID_EinheitEK,cID_EinheitPreisEK,cMengendivisorEK),
																		recFuhre);
											dtLager.ADD("MENGE_LAGERAUSGANG",			recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
											dtLager.ADD("MENGE_ABZUG_LAGERAUSGANG", 	recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
										}
									}
									
									if (ooThis.get_bIstInDerAuswahl(recFuhre, false))
									{
										if (! (recFuhre.get_ID_ADRESSE_ZIEL_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"))))
										{
											DatenTypUndMenge  dtVK	=	hmTypUndMenge.get_DatenTypUndMenge(
																				new LagerSorteHashGenerator("-1",cID_SorteVK,cID_EinheitVK,cID_EinheitPreisVK,cMengendivisorVK),
																										recFuhre);
											dtVK.ADD("MENGE_FUHRE_WA",			recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
											dtVK.ADD("MENGE_FUHRE_ABZUG_WA",	recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
											recFuhre.fill_PreisInfos(null, dtVK);
										}
										else  //dann ist es lagereingang
										{
											DatenTypUndMenge  dtLager =hmTypUndMenge.get_DatenTypUndMenge(
															new LagerSorteHashGenerator(recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF(),cID_SorteVK,cID_EinheitVK,cID_EinheitPreisVK,cMengendivisorVK),recFuhre);
											
											dtLager.ADD("MENGE_LAGEREINGANG",			recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
											dtLager.ADD("MENGE_ABZUG_LAGEREINGANG",		recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
										}

									}
									
									//jetzt die zusatzorte untersuchen (duerfte nur VK-Orte geben)
									RECLIST_VPOS_TPA_FUHRE_ORT  reclistZusatzort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
															"NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", "JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT", true);
									
									for (int k=0;k<reclistZusatzort.get_vKeyValues().size();k++)
									{
										RECORD_VPOS_TPA_FUHRE_ORT_EXT recOrtExt = new RECORD_VPOS_TPA_FUHRE_ORT_EXT(reclistZusatzort.get(k));
										
										if (!ooThis.get_bIstInDerAuswahl(recOrtExt))
										{
											continue;
										}

										
										
										DatenTypUndMenge dtORT = hmTypUndMenge.get_DatenTypUndMenge(
																	new LagerSorteHashGenerator(
																			"-1",
																			recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(),
																			recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_cUF(),
																			recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_PREIS_cUF(),
																			recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF()),
																	recOrtExt);

										if (!recOrtExt.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1")))
										{
											if (recOrtExt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
											{
												dtORT.ADD("MENGE_FUHRE_WE",			recOrtExt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO));
												dtORT.ADD("MENGE_FUHRE_ABZUG_WE",	recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
											}
											else
											{
												dtORT.ADD("MENGE_FUHRE_WA",			recOrtExt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO));
												dtORT.ADD("MENGE_FUHRE_ABZUG_WA",	recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
											}
											recOrtExt.fill_PreisInfo(dtORT);
										}
										else    //bei eigenem ort ist es ein lagerein- oder ausgang
										{
											
											DatenTypUndMenge dtLager = hmTypUndMenge.get_DatenTypUndMenge(
																					new LagerSorteHashGenerator(
																								recOrtExt.get_ID_ADRESSE_LAGER_cUF(),
																								recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(),
																								recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_cUF(),
																								recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_EINHEIT_PREIS_cUF(),
																								recOrtExt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF()),
																					recOrtExt);
											
											if (recOrtExt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
											{
												dtLager.ADD("MENGE_LAGERAUSGANG",			recOrtExt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO));
												dtLager.ADD("MENGE_ABZUG_LAGERAUSGANG", 	recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
											}
											else
											{
												dtLager.ADD("MENGE_LAGEREINGANG", 			recOrtExt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO));
												dtLager.ADD("MENGE_ABZUG_LAGEREINGANG",  	recOrtExt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
											}
										}
									}
								}
								
							}
							
							//zusatzfelder rechnen und speichern
							Iterator<DatenTypUndMenge>  oIter = hmTypUndMenge.values().iterator();
							
							while (oIter.hasNext())
							{
								DatenTypUndMenge oDT = oIter.next();
								oDT.BERECHNE_SONDER_FELDER();
								bibMSG.add_MESSAGE(oDT.SpeichereSatz(AuswertungWarenstroemeNachLagerUndSorten.this.cREPORTNUMMER));
							}
							
							
							
							if (ooThis.iAnzahlEinheitenFehler>0)
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden Fuhren mit falschen Sorten-Einheiten gefunden !! ("+ooThis.iAnzahlEinheitenFehler)));
							}
							if (ooThis.iAnzahlOrteFehler>0)
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden Fuhren mit falschen Ortsdefinitionen gefunden !! ("+ooThis.iAnzahlEinheitenFehler)));
							}
							
							//jetzt die grids von auswahl auf druck stellen
							
							AuswertungWarenstroemeNachLagerUndSorten.this.oGridSelektion.setVisible(false);
							AuswertungWarenstroemeNachLagerUndSorten.this.oGridDruck.setVisible(true);
							
							
							
							
							Iterator<DatenTypUndMenge>  ooIter = hmTypUndMenge.values().iterator();


							HashMap<String, Vector<String>>  	hmJumperIDs = 		AuswertungWarenstroemeNachLagerUndSorten.this.hmJumperIDs;

							Vector<String>   vSort = new Vector<String>();
							
							while (ooIter.hasNext())
							{
								DatenTypUndMenge oDT = ooIter.next();
								
								String cHashText = oDT.get_oLagSortHashGen().get_DropDownText();
								vSort.add(cHashText);
								hmJumperIDs.put(cHashText, oDT.get_vIDsFuhren());
							}

							Collections.sort(vSort);
							
							String[] cListBoxValues = new String[hmTypUndMenge.size()+1];
							cListBoxValues[0]="--";
							for (int i=0;i<vSort.size();i++)
							{
								cListBoxValues[i+1]=vSort.get(i);
							}
							
							
							MyE2_SelectField         oSelFieldJumpers = new MyE2_SelectField(cListBoxValues, "", false,new Extent(250));
							
							oSelFieldJumpers.add_oActionAgent(new ActionFuhrenJumper());
							
							//jetzt fuer jeden bereich einen jumper in die fuhrenzentrale aufbauen und in ein listbox einblenden
							AuswertungWarenstroemeNachLagerUndSorten.this.oGridSprungInFuhreNachergebnis.removeAll();
							AuswertungWarenstroemeNachLagerUndSorten.this.oGridSprungInFuhreNachergebnis.add(oSelFieldJumpers);
							
						}
						
						@Override
						public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
						{
						}

					};
				}
				
				catch (myExceptionForUser ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt nichts auszuwerten ...")));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler ",true,cSQL_BasisQueryFuhre,false)));
				}
				
			}
			
			
			
			private boolean get_bIstInDerAuswahl(RECORD_VPOS_TPA_FUHRE recFuhre, boolean bEK) throws myException
			{
				
				boolean bRueck = false;
				
				AuswertungWarenstroemeNachLagerUndSorten  oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
				
				String cSelektion_DatumVon =				oThis.tfDatumVon.get_oDBFormatedDateFromTextField();
				String cSelektion_DatumBis =	 			oThis.tfDatumBis.get_oDBFormatedDateFromTextField();
				
				String cSelektion_ID_ADRESSE_LIEFERANT = 	bibALL.ReplaceTeilString(oThis.oSelLieferanten.get_ActualWert(),".","").trim();
				String cSelektion_ID_ADRESSE_ABNEHMER = 	bibALL.ReplaceTeilString(oThis.oSelAbnehmer.get_ActualWert(),".","").trim();
				
				String cSelektion_ID_ARTIKEL_LADE = 		bibALL.ReplaceTeilString(oThis.oSelLadesorte.get_ActualWert(),".","").trim();
				String cSelektion_ID_ARTIKEL_ABLADE = 		bibALL.ReplaceTeilString(oThis.oSelAbladesorte.get_ActualWert(),".","").trim();
				
				String cSelektion_ID_USER_EINKAUF = 		bibALL.ReplaceTeilString(oThis.oSelBetreuerLieferant.get_ActualWert(),".","").trim();
				String cSelektion_ID_USER_VERKAUF = 		bibALL.ReplaceTeilString(oThis.oSelBetreuerAbnehmer.get_ActualWert(),".","").trim();
				
				if (bEK)
				{
					
					String cWiegeDatumFuhre = 		bibALL.ReplaceTeilString(recFuhre.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT(),"'","");
					
					if (cWiegeDatumFuhre.compareTo(cSelektion_DatumVon)>=0 && cWiegeDatumFuhre.compareTo(cSelektion_DatumBis)<=0)
					{
						bRueck = true;
						
						if (S.isFull(cSelektion_ID_ADRESSE_LIEFERANT) && !cSelektion_ID_ADRESSE_LIEFERANT.equals(
										recFuhre.get_ID_ADRESSE_START_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_ARTIKEL_LADE) && !cSelektion_ID_ARTIKEL_LADE.equals(
										recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF()).get_ID_ARTIKEL_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_USER_EINKAUF))
						{
							RECORD_ADRESSE  recLieferant = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start();
							RECORD_USER     recUser      = recLieferant.get_UP_RECORD_USER_id_user();
							
							String cID_UserFuhre = "-1";
							if (recUser!=null)
							{
								cID_UserFuhre = recUser.get_ID_USER_cUF().trim();
							}
							
							if (!cSelektion_ID_USER_EINKAUF.equals(cID_UserFuhre))
							{
								bRueck = false;
							}
						}
					}
				}
				else
				{
					String cWiegeDatumFuhre = 		bibALL.ReplaceTeilString(recFuhre.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT(),"'","");
					
					if (cWiegeDatumFuhre.compareTo(cSelektion_DatumVon)>=0 && cWiegeDatumFuhre.compareTo(cSelektion_DatumBis)<=0)
					{
						bRueck = true;
						
						if (S.isFull(cSelektion_ID_ADRESSE_ABNEHMER) && !cSelektion_ID_ADRESSE_ABNEHMER.equals(
										recFuhre.get_ID_ADRESSE_ZIEL_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_ARTIKEL_ABLADE) && !cSelektion_ID_ARTIKEL_ABLADE.equals(
										recListArtbez.get(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF()).get_ID_ARTIKEL_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_USER_VERKAUF))
						{
							RECORD_ADRESSE  recAbnehmer =  recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel();
							RECORD_USER     recUser      = recAbnehmer.get_UP_RECORD_USER_id_user();
							
							String cID_UserFuhre = "-1";
							if (recUser!=null)
							{
								cID_UserFuhre = recUser.get_ID_USER_cUF().trim();
							}
							
							if (!cSelektion_ID_USER_VERKAUF.equals(cID_UserFuhre))
							{
								bRueck = false;
							}
						}
					}
				}
				
				return bRueck;
			}
			
			
			
			private boolean get_bIstInDerAuswahl(RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt) throws myException
			{
				
				boolean bRueck = false;
				
				AuswertungWarenstroemeNachLagerUndSorten  oThis = AuswertungWarenstroemeNachLagerUndSorten.this;
				
				String cSelektion_DatumVon =				oThis.tfDatumVon.get_oDBFormatedDateFromTextField();
				String cSelektion_DatumBis =	 			oThis.tfDatumBis.get_oDBFormatedDateFromTextField();
				
				String cSelektion_ID_ADRESSE_LIEFERANT = 	bibALL.ReplaceTeilString(oThis.oSelLieferanten.get_ActualWert(),".","").trim();
				String cSelektion_ID_ADRESSE_ABNEHMER = 	bibALL.ReplaceTeilString(oThis.oSelAbnehmer.get_ActualWert(),".","").trim();
				
				String cSelektion_ID_ARTIKEL_LADE = 		bibALL.ReplaceTeilString(oThis.oSelLadesorte.get_ActualWert(),".","").trim();
				String cSelektion_ID_ARTIKEL_ABLADE = 		bibALL.ReplaceTeilString(oThis.oSelAbladesorte.get_ActualWert(),".","").trim();
				
				String cSelektion_ID_USER_EINKAUF = 		bibALL.ReplaceTeilString(oThis.oSelBetreuerLieferant.get_ActualWert(),".","").trim();
				String cSelektion_ID_USER_VERKAUF = 		bibALL.ReplaceTeilString(oThis.oSelBetreuerAbnehmer.get_ActualWert(),".","").trim();
				
				
				boolean bEK = recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK");
				
				if (bEK)
				{
					
					String cWiegeDatumFuhre = 		bibALL.ReplaceTeilString(recFuhreOrt.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT(),"'","");
					
					if (cWiegeDatumFuhre.compareTo(cSelektion_DatumVon)>=0 && cWiegeDatumFuhre.compareTo(cSelektion_DatumBis)<=0)
					{
						bRueck = true;
						
						if (S.isFull(cSelektion_ID_ADRESSE_LIEFERANT) && !cSelektion_ID_ADRESSE_LIEFERANT.equals(
												recFuhreOrt.get_ID_ADRESSE_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_ARTIKEL_LADE) && !cSelektion_ID_ARTIKEL_LADE.equals(
										recListArtbez.get(recFuhreOrt.get_ID_ARTIKEL_BEZ_cUF()).get_ID_ARTIKEL_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_USER_EINKAUF))
						{
							RECORD_ADRESSE  recLieferant = recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse();
							RECORD_USER     recUser      = recLieferant.get_UP_RECORD_USER_id_user();
							
							String cID_UserFuhre = "-1";
							if (recUser!=null)
							{
								cID_UserFuhre = recUser.get_ID_USER_cUF().trim();
							}
							
							if (!cSelektion_ID_USER_EINKAUF.equals(cID_UserFuhre))
							{
								bRueck = false;
							}
						}
					}
				}
				else
				{
					String cWiegeDatumFuhre = 		bibALL.ReplaceTeilString(recFuhreOrt.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT(),"'","");
					
					if (cWiegeDatumFuhre.compareTo(cSelektion_DatumVon)>=0 && cWiegeDatumFuhre.compareTo(cSelektion_DatumBis)<=0)
					{
						bRueck = true;
						
						if (S.isFull(cSelektion_ID_ADRESSE_ABNEHMER) && !cSelektion_ID_ADRESSE_ABNEHMER.equals(
											recFuhreOrt.get_ID_ADRESSE_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_ARTIKEL_ABLADE) && !cSelektion_ID_ARTIKEL_ABLADE.equals(
										recListArtbez.get(recFuhreOrt.get_ID_ARTIKEL_BEZ_cUF()).get_ID_ARTIKEL_cUF()
										.trim()))
						{
							bRueck = false;
						}
						
						if (S.isFull(cSelektion_ID_USER_VERKAUF))
						{
							RECORD_ADRESSE  recAbnehmer =  recFuhreOrt.get_UP_RECORD_ADRESSE_id_adresse();
							RECORD_USER     recUser      = recAbnehmer.get_UP_RECORD_USER_id_user();
							
							String cID_UserFuhre = "-1";
							if (recUser!=null)
							{
								cID_UserFuhre = recUser.get_ID_USER_cUF().trim();
							}
							
							if (!cSelektion_ID_USER_VERKAUF.equals(cID_UserFuhre))
							{
								bRueck = false;
							}
						}
					}
				}
				
				return bRueck;
			}
			
			
		}
	}


	@Override
	public Vector<XX_ActionValidator> get_GlobalValidators4ListButton() throws myException
	{
		return null;
	}


	@Override
	public Component get_Zusatzkomponente() throws myException
	{
		return this.oGridSprungInFuhreNachergebnis;
	}
	

	
	
	private class ActionFuhrenJumper extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_SelectField  oSelJumpers = (MyE2_SelectField) oExecInfo.get_MyActionEvent().getSource();
			
			String cActual = oSelJumpers.get_ActualWert();
			
			if (cActual.equals("--"))
			{
				return;
			}
			
			Vector<String> vIDsFuhren = AuswertungWarenstroemeNachLagerUndSorten.this.hmJumperIDs.get(cActual);
			
			new BaseJumper(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhren", vIDsFuhren, true, null);
		}
	}


	@Override
	public String get_ToolTip() throws myException {
		return "";
	}


	@Override
	public MyE2_Button get_ListButton() throws myException {
		MyE2_Button oButtonList = new MyE2_Button(this.get_cAuswertungsNamen());
		oButtonList.setToolTipText(this.get_ToolTip());
		
		oButtonList.add_GlobalAUTHValidator_AUTO(this.get_cALLOW_FLAG());
		oButtonList.setLineWrap(true);

		return oButtonList;
	}
	
	
	
}
