package panter.gmbh.Echo2.Auswertungen;

import java.util.Vector;

import echopointng.Separator;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class AW_BasicModuleContainer extends Project_BasicModuleContainer 
{

	/*
	 * Basiscontainer fuer auswertungen, die vor dem start des reports komplexe
	 * auswertemassnahmen erfordern (z.B. aufbau einer temporaeren tabelle)
	 */
	private Vector<XX_Auswertungen>  vAuswertungen = new Vector<XX_Auswertungen>();
	

	private MyE2_Grid                oGridAussenUmAlles = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	private MyE2_ContainerEx         oContainerEX_mit_Auswertungen = new MyE2_ContainerEx();
	
	private MyE2_Grid  		         oGrid_SuchfeldUndAuswertungen = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	private MyE2_Grid  		         oGrid_Suchfeld = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  		         oGrid_WithAuswerteButtons = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_TextArea            oTF_Hilfen = new MyE2_TextArea();
	private MyE2_Grid             	 oGrid4StartButton = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	
	//suchfeld und button zum ausloesen
	private MyE2_TextField           oTFSuchfeld = new MyE2_TextField("", 150, 200);


	//checkbox um nur die angehakten reports anzuzeigen
	private MyE2_CheckBox            oCB_ZeigeNurSelektierte = new MyE2_CheckBox("Nur Angehakte anzeigen");



	private MyE2_Button              oBTSuche   =   new MyE2_Button(E2_ResourceIcon.get_RI("suche_mini.png"));
	




	private Component                oComponentMitZusatzSteuerung = null;
	
	private Extent                   oBreiteButtonListe = null;
	private Extent                   oHoeheButtonListe = new Extent(500);
	

	//die auswertungen, die programmiert sind, kommen auf tab 1, die einfachen abfragelisten auf tab2
	private MyE2_TabbedPane          oTabbed = new MyE2_TabbedPane(700); 
	
	
	
	public AW_BasicModuleContainer(String MODULKENNER, Extent extBreiteButtonListe, Extent extHoeheButtonListe) throws myException 
	{
		super(MODULKENNER);
	
		this.oTabbed.set_bAutoHeight(true);
		this.set_iVerticalOffsetForTabbedPane(140);
		
		this.add(this.oTabbed,E2_INSETS.I_2_2_2_2);
		
		if (extBreiteButtonListe!=null) {this.oBreiteButtonListe=extBreiteButtonListe;}
		if (extHoeheButtonListe!=null) 	{this.oHoeheButtonListe=extHoeheButtonListe;}
		
		this.oCB_ZeigeNurSelektierte.setToolTipText(new MyE2_String("Nur die Auswertungen anzeigen, die in der Liste unten angehakt sind !").CTrans());
		
		if (this.oBreiteButtonListe!=null)
		{
			this.oContainerEX_mit_Auswertungen.setWidth(this.oBreiteButtonListe);
		}
		
		this.oContainerEX_mit_Auswertungen.setHeight(this.oHoeheButtonListe);
		
		this.oContainerEX_mit_Auswertungen.add(this.oGrid_WithAuswerteButtons);
		
		this.oBTSuche.add_oActionAgent(new actionSucheAuswertungen());
		this.oCB_ZeigeNurSelektierte.add_oActionAgent(new actionSucheAuswertungen());
		
		this.oGrid_Suchfeld.add(this.oTFSuchfeld,E2_INSETS.I_0_0_2_0);
		this.oGrid_Suchfeld.add(this.oBTSuche,E2_INSETS.I_0_0_0_0);
		this.oGrid_Suchfeld.add(this.oCB_ZeigeNurSelektierte,E2_INSETS.I_10_0_0_0);
		
		
		this.oGrid_SuchfeldUndAuswertungen.add(this.oGrid_Suchfeld,E2_INSETS.I_1_1_1_10);
		this.oGrid_SuchfeldUndAuswertungen.add(this.oContainerEX_mit_Auswertungen,E2_INSETS.I_1_1_1_1);
		
		this.oTF_Hilfen.setStyle(MyE2_TextArea.STYLE_ANZEIGE_FIELD(-4));
		this.oTF_Hilfen.EXT().set_bDisabledFromBasic(true);
		this.oTF_Hilfen.set_iWidthPixel(500);
		this.oTF_Hilfen.setHeight(this.oHoeheButtonListe);
		
		//int iWidth[] = {AW_BasicModuleContainer.BREITE_BUTTONFELD-5,400,350};
		
		this.oTabbed.add_Tabb(new MyE2_String("Auswertungen"), this.oGridAussenUmAlles);
		
		//hier noch einen reiter mit dem tab-modul anhaengen
		this.oTabbed.add_Tabb(new MyE2_String("Schnell-Abfragen"), new REPORT_MODULE_CONTAINER(null));
		
//		this.add(this.oGridAussenUmAlles, E2_INSETS.I_5_5_5_5);
		
	}


	
	public void set_Auswertungen(Vector<XX_Auswertungen>  Auswertungen, Component ComponentMitZusatzSteuerung) throws myException
	{
		this.vAuswertungen.removeAllElements();
		this.vAuswertungen.addAll(Auswertungen);
		
		this.oComponentMitZusatzSteuerung = ComponentMitZusatzSteuerung;
		
		this._FuelleModul(this.oCB_ZeigeNurSelektierte.isSelected());
	}
	
	
	private void _FuelleModul(boolean bBeruecksichtigeCheckboxen) throws myException
	{

		
		this.oGridAussenUmAlles.removeAll();
		
		if (this.oComponentMitZusatzSteuerung != null)
		{
			this.oGridAussenUmAlles.add(this.oComponentMitZusatzSteuerung,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5,3));
		}
		
		this.oGridAussenUmAlles.add(this.oGrid_SuchfeldUndAuswertungen,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5));
		this.oGridAussenUmAlles.add(this.oTF_Hilfen,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 35, 5, 10)));
		this.oGridAussenUmAlles.add(this.oGrid4StartButton,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 35, 5, 10)));

		
		
		this.oGrid_WithAuswerteButtons.removeAll();
		this.oTF_Hilfen.setText("");
		this.oGrid4StartButton.removeAll();
		
		String cAuswerteGruppeAlt = "";

		for (int i=0;i<this.vAuswertungen.size();i++)
		{
			XX_Auswertungen  oAuswert = this.vAuswertungen.get(i);
			
			MyE2_Button  oButton =oAuswert.get_ListButton();
			
			oButton.setToolTipText(oAuswert.get_ToolTip());
			
			oButton.add_oActionAgent(new ownActionWaehleAuswertungUndZeigeStartknopf(oAuswert));
			
			if (oAuswert.get_GlobalValidators4ListButton()!=null && oAuswert.get_GlobalValidators4ListButton().size()>0)
			{
				oButton.add_GlobalValidator(oAuswert.get_GlobalValidators4ListButton());
			}
			
			String cFilter = S.NN(this.oTFSuchfeld.getText());
			
			boolean bAdd = true;
			
			if (	S.isFull(cFilter) && 
					oButton.getText().toUpperCase().indexOf(cFilter.trim().toUpperCase())==-1  && 
					S.NN(oAuswert.get_cAuswerteGruppe()).toUpperCase().indexOf(cFilter.trim().toUpperCase())==-1)
			{
				bAdd=false;
			}

			Insets oIN_StartButtonsLeft = new Insets(2, 3, 7, 3);
			Insets oIN_StartButtons = new Insets(2, 3, 2, 3);
			

			//jetzt noch die checkbox beruecksichtigen
			if (bBeruecksichtigeCheckboxen)
			{
				if (!oAuswert.get_oCB_Anzeigen().isSelected())
				{
					bAdd = false;
				}
			}
			
			boolean showId = new RECORD_MANDANT(bibSES.get_ID_MANDANT_UF()).is_SHOW_IDS_ON_REPORT_LABELS_YES();
			
			this.oGrid_WithAuswerteButtons.set_Spalten(new int[]{20,1,400,showId?65:1});
			
			
			if (bAdd)
			{
				if (S.isEmpty(oAuswert.get_cAuswerteGruppe()))
				{

					this.oGrid_WithAuswerteButtons.add(oAuswert.get_oCB_Anzeigen(),1, oIN_StartButtons);
					this.oGrid_WithAuswerteButtons.add(oButton,2, oIN_StartButtons);
					this.oGrid_WithAuswerteButtons.add(new RB_lab(oButton.EXT().get_C_MERKMAL())._fsa(-1));
				}
				else
				{
					//beim wechsel der eigenen auf fremde auswertungen einen strich malen
					if (S.isEmpty(cAuswerteGruppeAlt))
					{
						this.oGrid_WithAuswerteButtons.add(new Separator(),4,E2_INSETS.I_2_10_2_10);
						cAuswerteGruppeAlt=oAuswert.get_cAuswerteGruppe();
					}

					this.oGrid_WithAuswerteButtons.add(oAuswert.get_oCB_Anzeigen(),1, oIN_StartButtons);
					this.oGrid_WithAuswerteButtons.add(new MyE2_Label(oAuswert.get_cAuswerteGruppe(), new E2_FontItalic()),1,oIN_StartButtonsLeft);
					this.oGrid_WithAuswerteButtons.add(oButton,1, oIN_StartButtons);
					this.oGrid_WithAuswerteButtons.add(new RB_lab(oButton.EXT().get_C_MERKMAL())._fsa(-1));
				}
			}
		}


	}
	

	
	private class ownActionWaehleAuswertungUndZeigeStartknopf extends XX_ActionAgent
	{
		private XX_Auswertungen oAuswertung = null;
		
		public ownActionWaehleAuswertungUndZeigeStartknopf(XX_Auswertungen Auswertung) 
		{
			super();
			this.oAuswertung = Auswertung;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			AW_BasicModuleContainer oThis = AW_BasicModuleContainer.this;
			
			oThis.oTF_Hilfen.setText("");
			if (this.oAuswertung.get_cAuswertungsErlaeuterung()!=null)
			{
				oThis.oTF_Hilfen.setText(this.oAuswertung.get_cAuswertungsErlaeuterung().CTrans());
			}
			
			oThis.oGrid4StartButton.removeAll();
			oThis.oGrid4StartButton.add(this.oAuswertung.get_StartButton(),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 0, 0, 5)));
			
			//2011-08-04: weiteres element (wahlweise) unterhalb des startbuttons anzeigen
			if (this.oAuswertung.get_Zusatzkomponente() != null)
			{
				oThis.oGrid4StartButton.add(this.oAuswertung.get_Zusatzkomponente(),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 0, 0, 5)));;
			}
			
		}
	}
	
	

	
	private class actionSucheAuswertungen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			AW_BasicModuleContainer.this._FuelleModul(AW_BasicModuleContainer.this.oCB_ZeigeNurSelektierte.isSelected());
		}
	}

	
	public void fuelle_modul_neu() throws myException
	{
		this._FuelleModul(AW_BasicModuleContainer.this.oCB_ZeigeNurSelektierte.isSelected());
	}
	
	
	
	public MyE2_TextField get_oTFSuchfeld()
	{
		return oTFSuchfeld;
	}

	public MyE2_Button get_oBTSuche()
	{
		return oBTSuche;
	}
	
	
	public MyE2_CheckBox get_oCB_ZeigeNurSelektierte()
	{
		return oCB_ZeigeNurSelektierte;
	}

	
	public Vector<XX_Auswertungen> get_vAuswertungen()
	{
		return vAuswertungen;
	}


	
}
