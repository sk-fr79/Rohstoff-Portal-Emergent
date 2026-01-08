package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_LIEFERBEDINGUNGEN;

public class FU_AL_Component extends MyE2_Grid {

	private MyE2_Label 	label_Lieferbedingungen = 	new MyE2_Label(true);
	private MyE2_Button oBT_OpenInputFieldLFBD = 	new MyE2_Button(E2_ResourceIcon.get_RI("edit_mini2.png"),true);
	private MyE2_Button oBT_FieldRefresh = 	new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);

	// verstecktes datenbank-feld
	private BS_ComboBox_LIEFERBEDINGUNGEN 	oDD_HiddenLieferbed = null;
	
	//suchmethode wird hier definiert
	private FU_AL_SucheLieferBed   			oSucheLiefBed = null;
	
	private int[] iWidthStd = {265,50};

	public FU_AL_Component(			BS_ComboBox_LIEFERBEDINGUNGEN tf_HiddenLieferbed,
									E2_ComponentMAP  	oMAP, 
									String 				nAME_FU_ID_ADRESSE, 
									String 				nAME_FU_LIEFEBED_ALTERNATIV,
									String 				nAME_FU_ID_VPOS_KON,
									String 				nAME_FU_ID_VPOS_STD, 
									String 				nAME_AD_ID_LIEFERBED) throws myException {
		super(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		this.oDD_HiddenLieferbed = tf_HiddenLieferbed;
		
		this.oSucheLiefBed = new FU_AL_SucheLieferBed(		oMAP, 
															nAME_FU_ID_ADRESSE, 
															nAME_FU_LIEFEBED_ALTERNATIV, 
															nAME_FU_ID_VPOS_KON, 
															nAME_FU_ID_VPOS_STD, 
															nAME_AD_ID_LIEFERBED,
															false);
		
		this.oBT_OpenInputFieldLFBD.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new FU_AL_PopupEingabe(FU_AL_Component.this);
			}
		});

		
		this.oBT_FieldRefresh.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FU_AL_Component.this.refreshLieferbedingung();
			}
		});
		
		this.oBT_OpenInputFieldLFBD.add_GlobalAUTHValidator_AUTO("LIEFERBEDINGUNGEN_ALTERNATIV");
		this.oBT_OpenInputFieldLFBD.setToolTipText(new MyE2_String("Falls unterschiedliche Lieferbedingungen vorliegen als in der Findungsautomatik, dann hier eingeben").CTrans());

		this.oBT_FieldRefresh.setToolTipText(new MyE2_String("Aktualisieren der Anzeige der Lieferbedingung").CTrans());

		MyE2_Grid oGridHelp = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridHelp.add(this.oBT_FieldRefresh,E2_INSETS.I(0,0,0,0));
		oGridHelp.add(this.oBT_OpenInputFieldLFBD,E2_INSETS.I(0,0,0,0));
		
		
		this.add(this.label_Lieferbedingungen, MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(1, 1, 1, 1)));
		this.add(oGridHelp, MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I(1, 1, 1, 1)));

		this.set__ColWidth(this.iWidthStd);
	}

	public void set__ColWidth(int[] iWidth) {
		if (iWidth.length==2) {
			this.setColumnWidth(0, new Extent(iWidth[0]));
			this.setColumnWidth(1, new Extent(iWidth[1]));
		}
	}
	
	public void set___bEnabled_For_Edit(boolean enabled) throws myException {
		this.oBT_OpenInputFieldLFBD.set_bEnabled_For_Edit(enabled);
	}


	public void set____LIEFERBEDINGUNG(String cLieferBed) {
		this.label_Lieferbedingungen.setText(S.NN(cLieferBed));
	}

	/*
	 * einstellungen/darstellung wenn die lieferbedingung gefunden wird
	 */
	public void set___Darstellung_LiefBed_AutomatischPerFindung() {
		this.label_Lieferbedingungen.setFont(new E2_FontItalic());
		this.label_Lieferbedingungen.setForeground(new E2_ColorGray(30));
	}

	/*
	 * einstellungen/darstellung wenn die lieferbedingung in der fuhre gesetzt
	 * wird
	 */
	public void set___Darstellung_LiefFuhre() {
		this.label_Lieferbedingungen.setFont(new E2_FontPlain());
		this.label_Lieferbedingungen.setForeground(Color.BLACK);
	}


	public void clear_Lieferbedingungen() {
		this.label_Lieferbedingungen.setText("");
	}

	
	public void refreshLieferbedingung() throws myException {
		this.oSucheLiefBed.INIT_SEARCH();
		this.set____LIEFERBEDINGUNG(this.oSucheLiefBed.get_cLIEFERBED_incl_FUHRE());
		
		if (this.oSucheLiefBed.get_GefundenerTYP()==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_FUHRE) {
			this.set___Darstellung_LiefFuhre();
		} else {
			this.set___Darstellung_LiefBed_AutomatischPerFindung();
		}
		this.label_Lieferbedingungen.setToolTipText(this.oSucheLiefBed.get_cToolTipInfos().CTrans());
	}
	
	/**
	 * baut nur das suchobjekt neu
	 * @throws myException
	 */
	public void refreshLieferbedingung_in_Background() throws myException {
		this.oSucheLiefBed.INIT_SEARCH();
	}	
	

	public BS_ComboBox_LIEFERBEDINGUNGEN get_oDD_HiddenLieferbed() {
		return oDD_HiddenLieferbed;
	}

	public MyE2_Label get_label_Lieferbedingungen() {
		return label_Lieferbedingungen;
	}
	
	
	public String get_Lieferbedingung_OhneFuhre() throws myException {
		return this.oSucheLiefBed.get_cLIEFERBED_ohne_FUHRE();
	}
	
	
	public MyE2_String get_cHerkunftsinfo_ohne_Fuhre() throws myException {
		return this.oSucheLiefBed.get_cHerkunftsinfo_ohne_Fuhre();
	}
	
	
	
	public boolean get_bIS_MANDANT() throws myException {
		return this.oSucheLiefBed.get_bIS_MANDANT();
	}
	
	
	
}
