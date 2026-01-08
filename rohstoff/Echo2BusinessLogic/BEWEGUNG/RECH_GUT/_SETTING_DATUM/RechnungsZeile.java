/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM
 * @author martin
 * @date 07.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.text.SimpleDateFormat;
import java.util.Date;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VkopfRG;


/**
 * @author martin
 * @date 07.06.2019
 *
 */
public class RechnungsZeile {

	private Long   				fakturierungsFrist = null;
	private Date 				druckDatum = null;
	private Date 				relevantesZahlungsZiel = null;
	private Date  				maxLeistungsDatum = null;
	private Date 				zahlungsZielNachLeistungsDatum = null;
	private Rec21_VkopfRG 		rec21VKopf = null;


	private RB_TextField 		tfID_VKOPF_RG = null;
	private RB_TextField 		tfRECHUNGSDATUM = null;
	private RB_TextField 		tfZAHLUNGSDATUM = null;
	private RB_TextField 		tfINFO_TEXT = null;
	
	private Component  			componentShowVerlaengerteFakturierung = null;
	
	
	public RechnungsZeile() throws myException {
		super();
	}

	
	public RechnungsZeile _render() throws myException {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		tfID_VKOPF_RG = 	new RB_TextField(100,10)._t(rec21VKopf.getFs(VKOPF_RG.id_vkopf_rg));
		tfRECHUNGSDATUM = 	new RB_TextField(100,10)._t(df.format(this.getDruckDatum()));
		tfZAHLUNGSDATUM = 	new RB_TextField(100,10)._t(df.format(this.getRelevantesZahlungsZiel()));
		tfINFO_TEXT = 		new RB_TextField(400,400)._t(rec21VKopf.get_ufs_kette(" ", VKOPF_RG.name1,VKOPF_RG.name2, VKOPF_RG.ort));

		if (O.NN(fakturierungsFrist, 0l)>0) { 
			componentShowVerlaengerteFakturierung = new E2_Button()._t(""+this.fakturierungsFrist.longValue())
																	._setShapeBigHighLightText()
																	._aaa(new ActionAgentKlickAufFakturierungsFrist())
																	._ttt("Informationen zu verlängerter Fakturierungsfrist")
																	;
		} else {
			componentShowVerlaengerteFakturierung = new RB_lab();
		}
		
		tfID_VKOPF_RG.set_bEnabled_For_Edit(false);
		tfINFO_TEXT.set_bEnabled_For_Edit(false);
		
		return this;
	}
	
	
	public MyE2_MessageVector pruefe_DatumsFelder() {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		MyDate oDateRech = new MyDate(this.tfRECHUNGSDATUM.getText());
		MyDate oDateZahl = new MyDate(this.tfZAHLUNGSDATUM.getText());

		this.tfRECHUNGSDATUM.mark_Neutral();
		this.tfZAHLUNGSDATUM.mark_Neutral();
		
		if (!oDateRech.get_bOK()) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in Rechnungsdatum: ",true,this.tfID_VKOPF_RG.getText(),false)));
			this.tfRECHUNGSDATUM.mark_FalseInput();
		}
		if (!oDateZahl.get_bOK()) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in Zahlungsziel: ",true,this.tfID_VKOPF_RG.getText(),false)));
			this.tfZAHLUNGSDATUM.mark_FalseInput();
		}
		
		return oMV;
	}
	
	
	public MyDate getMyDateRECHUNGSDATUM() {
		return new MyDate(this.tfRECHUNGSDATUM.getText());
	}
	public MyDate getMyDateZAHLUNGSDATUM() {
		return new MyDate(this.tfZAHLUNGSDATUM.getText());
	}


	public RB_TextField getTfIdKopfRg() {
		return tfID_VKOPF_RG;
	}


	public RB_TextField getTfRechnungsDatum() {
		return tfRECHUNGSDATUM;
	}


	public RB_TextField getTfZahlungsDatum() {
		return tfZAHLUNGSDATUM;
	}


	public RB_TextField getTfInfo() {
		return tfINFO_TEXT;
	}


	public Component getComponentShowVerlaengerteFakturierung() {
		return componentShowVerlaengerteFakturierung;
	}



	private class ActionAgentKlickAufFakturierungsFrist extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

			try {
				OwnContainer popup = new OwnContainer();
				
				MyE2_String s_tage = S.ms("").ut(" ("+O.NN(fakturierungsFrist,0l).longValue()).t(" Tage ").ut(")");
				
				E2_Grid gInfo = new E2_Grid()._setSize(300,140)
											._a(new RB_lab()._fsa(2)._tr("Verändertes Rechnungsdatum wegen verlängerter Fakturierungsfrist")
																	._t_add(s_tage)
																	._lw(), new RB_gld()._span(2)._col_back_d()._ins(2, 2, 10, 2))

											._a(new RB_lab()._tr("Rechnungsdatum wg. verlängerter Fakt.Frist:"),new RB_gld()._left_mid()._ins(2, 10, 10, 2))
											._a(new RB_lab()._t(df.format(druckDatum)), new RB_gld()._center_mid()._colL()._ins(2, 10, 10, 2))

											._a(new RB_lab()._tr("Zahlungsziel wg. verlängerter Fakt.Frist:"),new RB_gld()._left_mid()._ins(2, 10, 10, 2))
											._a(new RB_lab()._t(df.format(relevantesZahlungsZiel)), new RB_gld()._center_mid()._colL()._ins(2, 10, 10, 2))
											
											._a(new RB_lab()._tr(""),new RB_gld()._left_mid()._span(2)._ins(2, 30, 10, 2))
											._a(new RB_lab()._tr("Vergleichswerte basierend auf Leistungsdatum:"),new RB_gld()._left_mid()._span(2)._ins(2, 2, 10, 2)._col_back_d())
											
											._a(new RB_lab()._tr("Rechnungsdatum nach Leistungsdatum:"),new RB_gld()._left_mid()._ins(2, 10, 10, 2))
											._a(new RB_lab()._t(df.format(maxLeistungsDatum)), new RB_gld()._center_mid()._colL()._ins(2, 10, 10, 2))
											
											._a(new RB_lab()._tr("Zahlungsziel nach Leistungsdatum:"),new RB_gld()._left_mid()._ins(2, 10, 10, 2))
											._a(new RB_lab()._t(df.format(zahlungsZielNachLeistungsDatum)), new RB_gld()._center_mid()._colL()._ins(2, 10, 10, 2))
											
											._a(new E2_Button()._tr("Schliesse Fenster")
																._width(120)
																._aaa(()->popup.CLOSE_AND_DESTROY_POPUPWINDOW(true))
																._setShapeStandardTextButton(), new RB_gld()._left_mid()._ins(2, 10, 2, 2))
											;
				
				popup.add(gInfo, E2_INSETS.I(5,5,5,5));
				
				popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500),new Extent(350),S.ms("Information ..."));
			} catch (Exception e) {
				e.printStackTrace();
				bibMSG.MV()._addAlarm("Systemfehler: <bf24d530-8910-11e9-bc42-526af7764f64>");
			}
			
			
		}
		
	}
	
	
	
	private class OwnContainer extends E2_BasicModuleContainer {

		public OwnContainer() {
			super();
		}
		
	}
	
	
	public RechnungsZeile _clear() {
		druckDatum = 						null;
		relevantesZahlungsZiel = 			null;
		fakturierungsFrist = 				null;
		rec21VKopf =			 			null;
		maxLeistungsDatum = 				null;
		zahlungsZielNachLeistungsDatum = 	null;
	
		
		return this;
	}


	public Date getDruckDatum() {
		return druckDatum;
	}


	public RechnungsZeile _setDruckDatum(Date druckDatum) {
		this.druckDatum = druckDatum;
		return this;
	}


	public Date getRelevantesZahlungsZiel() {
		return relevantesZahlungsZiel;
	}


	public RechnungsZeile _setRelevantesZahlungsZiel(Date relevantesZahlungsZiel) {
		this.relevantesZahlungsZiel = relevantesZahlungsZiel;
		return this;
	}


	public Long getFakturierungsFrist() {
		return fakturierungsFrist;
	}


	public RechnungsZeile _setFakturierungsFrist(Long fakturierungsFrist) {
		this.fakturierungsFrist = fakturierungsFrist;
		return this;
	}


	public Rec21_VkopfRG getRec21VKopf() {
		return rec21VKopf;
	}


	public RechnungsZeile _setRec21VKopf(Rec21_VkopfRG rec21vKopf) {
		rec21VKopf = rec21vKopf;
		return this;
	}


	public Date getMaxLeistungsDatum() {
		return maxLeistungsDatum;
	}


	public RechnungsZeile _setMaxLeistungsDatum(Date maxLeistungsDatum) {
		this.maxLeistungsDatum = maxLeistungsDatum;
		return this;
	}


	public Date getZahlungsZielNachLeistungsDatum() {
		return zahlungsZielNachLeistungsDatum;
	}


	public RechnungsZeile _setZahlungsZielNachLeistungsDatum(Date zahlungsZielNachLeistungsDatum) {
		this.zahlungsZielNachLeistungsDatum = zahlungsZielNachLeistungsDatum;
		return this;
	}

	
	
}
