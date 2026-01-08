/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;


import java.math.BigDecimal;
import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import echopointng.KeyStrokeListener;

public class FU_LIST_BT_INPUT_INVOICE extends MyE2_Button
{
	private E2_NavigationList 	oNavigationList = null;

	
	// komponenten fuer das popup-fenster
	private MyE2_TextField		oTFBetrag = 			new MyE2_TextField("",100,20);
	private MyE2_TextField		oTFFremdbeleg = 		new MyE2_TextField("",200,50);
	private MyE2_TextField		oTFVerwendung = 		new MyE2_TextField("TPA",200,200);
	private MyE2_ButtonWithKey	oButtonSave = 			new MyE2_ButtonWithKey(new MyE2_String("Speichern"),KeyStrokeListener.VK_RETURN);
	private MyE2_ButtonWithKey	oButtonCancel = 		new MyE2_ButtonWithKey(new MyE2_String("Abbruch"),KeyStrokeListener.VK_ESCAPE);
	private ownPopupContainer   oPopUpContainer = 	    null;
	
	public FU_LIST_BT_INPUT_INVOICE(E2_NavigationList onavigationlist)
	{
		super(E2_ResourceIcon.get_RI("geld.png"),true);
		this.oNavigationList = onavigationlist;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText((new MyE2_String("Eintragen des Rechnungs-Betrags der Spedition ...")).CTrans());
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"RECHNUNGSBETRAG_FUHRE"));

		this.oTFBetrag.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
		this.oTFFremdbeleg.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
		this.oTFVerwendung.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
		
	
		this.oButtonCancel.add_oActionAgent(new actionClose());
		this.oButtonSave.add_oActionAgent(new actionSave());
		
		this.oTFBetrag.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));

		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Fuhre wurde bereits gelöscht !")));

	}

	

	
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			FU_LIST_BT_INPUT_INVOICE oThis = FU_LIST_BT_INPUT_INVOICE.this;
			Vector<String> vIDs = oThis.oNavigationList.get_vSelectedIDs_Unformated();

			MyE2_Button oBut = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			
			if (vIDs.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau eine Transport-Position auswählen !"));
			}
			try
			{

				bibMSG.add_MESSAGE(oBut.valid_IDValidation(vIDs));
				
				if (bibMSG.get_bIsOK())
				{
					RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(vIDs.get(0));
				
					// in merkmal3 steht die fuhren-id
					oThis.oButtonSave.EXT().set_C_MERKMAL3((String)vIDs.get(0));
	
					RECLIST_VPOS_TPA_FUHRE_KOSTEN  recListKosten = 
						recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_KOSTEN.DELETED,'N')='N'","ID_VPOS_TPA_FUHRE_KOSTEN",true); 
					
					int iAnzahlSpeditionsRechnungen = 0;
					
					RECORD_VPOS_TPA_FUHRE_KOSTEN  recSpeditionsKosten1 = null;
					for (int i=0;i<recListKosten.get_vKeyValues().size();i++)
					{
						RECORD_VPOS_TPA_FUHRE_KOSTEN  recKosten = recListKosten.get(i);
						
						if (recKosten.get_UP_RECORD_FUHREN_KOSTEN_TYP_id_fuhren_kosten_typ()!=null && 
							recKosten.get_UP_RECORD_FUHREN_KOSTEN_TYP_id_fuhren_kosten_typ().is_SPEDITIONSRECHNUNG_YES())
						{
							iAnzahlSpeditionsRechnungen++;
							if (recSpeditionsKosten1==null)
							{
								recSpeditionsKosten1 = recKosten;
							}
						}
					}
					
					
					
					if (iAnzahlSpeditionsRechnungen>1)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Die Position hat mehr als einen Rechnungsbetrag - Bitte prüfen !!"));
						return;
					}
					else if (iAnzahlSpeditionsRechnungen==1)   // edit
					{
						oThis.oButtonSave.EXT().set_O_PLACE_FOR_EVERYTHING(recSpeditionsKosten1);
						oThis.oTFBetrag.setText(recSpeditionsKosten1.get_BETRAG_KOSTEN_cF_NN(""));
						oThis.oTFVerwendung.setText(recSpeditionsKosten1.get_INFO_KOSTEN_cF_NN(""));
						oThis.oTFFremdbeleg.setText(recSpeditionsKosten1.get_FREMDBELEG_NUMMER_cF_NN(""));
					}
					else if (iAnzahlSpeditionsRechnungen==0)	// neu
					{
						oThis.oButtonSave.EXT().set_O_PLACE_FOR_EVERYTHING(null);
						oThis.oTFBetrag.setText("");
						oThis.oTFVerwendung.setText("TPA");
						oThis.oTFFremdbeleg.setText("");
					}
					
	
					oThis.oPopUpContainer = new ownPopupContainer(recFuhre);
				
				}
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: ").CTrans()));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	private class ownPopupContainer extends E2_BasicModuleContainer
	{
		private MyE2_Grid   			oGridInPopUp = 			new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER());
		private RECORD_VPOS_TPA_FUHRE  	recFuhre = null;

		public ownPopupContainer(RECORD_VPOS_TPA_FUHRE  rec_Fuhre) throws myException
		{
			super();
			
			FU_LIST_BT_INPUT_INVOICE oThis = FU_LIST_BT_INPUT_INVOICE.this;
			
			this.recFuhre = rec_Fuhre;
			
			MyE2_Label		 	oLabInfo = 				new MyE2_Label("",MyE2_Label.STYLE_SMALL_ITALIC());

			String 				cEPreis = "";
			String 				cEinheit_Preis = "";
			String 				cMenge_aufladen = "";
			String 				cMengen_Einheit = "";

			if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
			{
				cEPreis = 			recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_EINZELPREIS_cF_NN("0,00");
				cEinheit_Preis = 	recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_EINHEIT_PREIS_KURZ_cUF_NN("<EH.Preis>");
				cMenge_aufladen = 	recFuhre.get_MENGE_AUFLADEN_KO_cF_NN("0,00");
				cMengen_Einheit = 	recFuhre.get_EINHEIT_MENGEN_cUF_NN("<EH.Mge>");
				
				oLabInfo.set_Text(cEPreis+" EUR / "+cEinheit_Preis+"  (Ladung: "+cMenge_aufladen+" "+cMengen_Einheit+")");
			}
			
			this.oGridInPopUp.add(new MyE2_Label(new MyE2_String("Einzelpreis TPA:"),MyE2_Label.STYLE_SMALL_ITALIC()),1,E2_INSETS.I_10_20_10_5);
			this.oGridInPopUp.add(oLabInfo,1,E2_INSETS.I_10_20_10_5);
			
			this.oGridInPopUp.add(new MyE2_Label(new MyE2_String("Betrag:")),1,E2_INSETS.I_10_20_10_5);
			this.oGridInPopUp.add(oThis.oTFBetrag,1,E2_INSETS.I_10_20_10_5);
			
			this.oGridInPopUp.add(new MyE2_Label(new MyE2_String("Fremdbeleg:")),1,E2_INSETS.I_10_5_10_5);
			this.oGridInPopUp.add(oThis.oTFFremdbeleg,1,E2_INSETS.I_10_5_10_5);
			
			this.oGridInPopUp.add(new MyE2_Label(new MyE2_String("Verwendung:")),1,E2_INSETS.I_10_5_10_5);
			this.oGridInPopUp.add(oThis.oTFVerwendung,1,E2_INSETS.I_10_5_10_5);
			
			this.oGridInPopUp.add(oThis.oButtonSave,1,E2_INSETS.I_10_10_10_10);
			this.oGridInPopUp.add(oThis.oButtonCancel,1,E2_INSETS.I_10_10_10_10);

			this.add(this.oGridInPopUp);
			this.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,oThis.oButtonSave,oThis.oButtonCancel,E2_INSETS.I_10_2_10_2));
			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
			
			this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(430),new Extent(300),new MyE2_String("Rechnungsbetrag verändern"));
			ApplicationInstance.getActive().setFocusedComponent(oThis.oTFBetrag); 
		}
	}
	
	
	private class actionSave extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_BT_INPUT_INVOICE oThis = FU_LIST_BT_INPUT_INVOICE.this;
			
			String cID_VPOS_TPA_FUHRE = oThis.oButtonSave.EXT().get_C_MERKMAL3();
			
			if (bibALL.isEmpty(oThis.oTFBetrag.getText()) ||  
				bibALL.isEmpty(oThis.oTFFremdbeleg.getText()) ||
				bibALL.isEmpty(oThis.oTFVerwendung.getText()))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Bitte alle Felder ausfuellen !!"));
				return;
				
			}
			
			
			String cEingabeWertBetrag 		= bibALL.null2leer(oThis.oTFBetrag.getText());
			String cEingabeWertVerwendung	= bibALL.null2leer(oThis.oTFVerwendung.getText());
			String cEingabeFremdbeleg		= bibALL.null2leer(oThis.oTFFremdbeleg.getText());
			
			
			
			DotFormatter oDF = new DotFormatter(cEingabeWertBetrag,2,Locale.GERMAN,true,3);
			if (!oDF.doFormat())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Bitte korrekten Betrag eingeben !!"));
				return;
			}
			
			//zuerst in der kostentypen-tabelle den speditionsrechungswert suchen
			Integer  id_FuhrenKostenTyp_speditionsrechnung = null;
			RECLIST_FUHREN_KOSTEN_TYP  reclistTypen = new RECLIST_FUHREN_KOSTEN_TYP("SELECT * FROM "+bibE2.cTO()+".JT_FUHREN_KOSTEN_TYP WHERE NVL(SPEDITIONSRECHNUNG,'N')='Y'");
			if (reclistTypen.get_vKeyValues().size()>0)
			{
				//dann den ersten eintrag nehmen
				id_FuhrenKostenTyp_speditionsrechnung = new Integer(reclistTypen.get(0).get_ID_FUHREN_KOSTEN_TYP_bdValue(BigDecimal.ZERO).intValue());
			}
			
			if (oThis.oButtonSave.EXT().get_O_PLACE_FOR_EVERYTHING()==null)
			{
				RECORDNEW_VPOS_TPA_FUHRE_KOSTEN recNew = new RECORDNEW_VPOS_TPA_FUHRE_KOSTEN();
				recNew.set_NEW_VALUE_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
				recNew.set_NEW_VALUE_INFO_KOSTEN(cEingabeWertVerwendung);
				recNew.set_NEW_VALUE_FREMDBELEG_NUMMER(cEingabeFremdbeleg);
				recNew.set_NEW_VALUE_BETRAG_KOSTEN(oDF.getStringFormated());
				recNew.set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(id_FuhrenKostenTyp_speditionsrechnung==null?null:""+id_FuhrenKostenTyp_speditionsrechnung);
				recNew.set_NEW_VALUE_BELEG_VORHANDEN("Y");
				recNew.set_NEW_VALUE_IST_PREIS_PRO_TONNE("N");
				recNew.do_WRITE_NEW_VPOS_TPA_FUHRE_KOSTEN(bibMSG.MV());
			}
			else
			{
				RECORD_VPOS_TPA_FUHRE_KOSTEN  recKosten = (RECORD_VPOS_TPA_FUHRE_KOSTEN) oThis.oButtonSave.EXT().get_O_PLACE_FOR_EVERYTHING();
				
				recKosten.set_NEW_VALUE_INFO_KOSTEN(cEingabeWertVerwendung);
				recKosten.set_NEW_VALUE_FREMDBELEG_NUMMER(cEingabeFremdbeleg);
				recKosten.set_NEW_VALUE_BETRAG_KOSTEN(oDF.getStringFormated());
				recKosten.set_NEW_VALUE_ID_FUHREN_KOSTEN_TYP(id_FuhrenKostenTyp_speditionsrechnung==null?null:""+id_FuhrenKostenTyp_speditionsrechnung);
				recKosten.set_NEW_VALUE_BELEG_VORHANDEN("Y");
				recKosten.set_NEW_VALUE_IST_PREIS_PRO_TONNE("N");
				bibMSG.add_MESSAGE(recKosten.UPDATE(null, true));
			}
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Eingabe wurde gespeichert !"));
			try
			{
				oThis.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				oThis.oNavigationList.Refresh_ComponentMAP(cID_VPOS_TPA_FUHRE,E2_ComponentMAP.STATUS_VIEW);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}

	
	
	
	
	private class actionClose extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_BT_INPUT_INVOICE.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	
}