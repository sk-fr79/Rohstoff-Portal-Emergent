package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.IF.IF_generate_RB_KF;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK_KAT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX___CONST;

public abstract class RB_TextArea_Selector extends E2_Grid implements IF_RB_Component_Savable, IF_generate_RB_KF {

	private static XX_ActionAgent ownAction;

	private Border borderNeutral = null;

	private RB_KF key = null;

	private RB_TextArea textArea = null;

	private MyE2_PopUpMenue	 popUp = null;

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private String cInfoWhenEmpty = "";

	private String kategorie = "";

	private String[][] cVarianten;

	private Component oTopInfoComponent;

	private String cZusatzText;

	private boolean bZusatzTextVorPopupText =false;

	private MyE2EXT__Component  EXT = new MyE2EXT__Component(this) ;

	private boolean default_NEW_button = false;

	public RB_TextArea_Selector(int iWidthInPixel, int iRows, boolean is_new_eintrage_button_always_visible) throws myException {
		super();

		_build(  iWidthInPixel,  iRows);

		this.default_NEW_button = is_new_eintrage_button_always_visible;

	}

	private void _build(int iWidthInPixel, int iRows) throws myException{

		textArea = new RB_TextArea(iWidthInPixel, iRows);
		textArea.set_rb_RB_K(key);

		popUp = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("textbaustein.png"),E2_ResourceIcon.get_RI("leer.png"), false);		

		this._a_lm(textArea);
		this._a_lt(popUp);
	}

	public RB_TextArea get_oTextArea() {
		return textArea;
	}

	public MyE2_PopUpMenue getPopUp() {
		return popUp;
	}

	private void _fill_popUp(String sqlQuery) throws myException {
		this.kategorie = get_kategorie_if_no_eintraege();

		String[][] ergArray = null;

		if (!bibALL.isEmpty(sqlQuery))
		{
			ergArray=bibDB.EinzelAbfrageInArray(sqlQuery,"");
		}

		String[][] 	Varianten = null;
		String 		cInfo_When_Empty = new MyE2_String("*** KEINE EINTRÄGE ***").CTrans();

		if (ergArray != null && ergArray.length>0)
		{
			if (ergArray[0].length==1)
			{
				Varianten = new String[ergArray.length][2];
				for (int i=0;i<ergArray.length;i++)
					Varianten[i][0]=Varianten[i][1]=ergArray[i][0];

			}
			else
			{
				Varianten = new String[ergArray.length][2];
				for (int i=0;i<ergArray.length;i++)
				{
					Varianten[i][0]=ergArray[i][0];
					Varianten[i][1]=ergArray[i][1];
				}
			}
		}

		this.set_Varianten(ergArray, cInfo_When_Empty, null);
	}

	public void set_Varianten(String[][] Varianten, String cInfoWhenempty, Component TopInfoComponent) throws myException
	{
		this.cInfoWhenEmpty=cInfoWhenempty;

		if (S.isEmpty(this.cInfoWhenEmpty))
		{
			this.cInfoWhenEmpty = new MyE2_String("*** KEINE EINTRÄGE ***").CTrans();
		}

		this.cVarianten = Varianten;
		this.oTopInfoComponent = TopInfoComponent;

		if (this.cVarianten !=null && this.cVarianten.length>0)
			if (this.cVarianten[0].length!=2)
				throw new myException("MyE2_DB_TextArea_WithSelektor: set_Varianten: cVarianten musst have an array nx2");

		this.popUp.removeAllButtons();
		if (this.oTopInfoComponent != null)
			this.popUp.get_oColInnen().add(this.oTopInfoComponent);

		if (this.cVarianten != null && this.cVarianten.length>0)
		{
			if(default_NEW_button){
				this.popUp.addButton(new MyE2_Button("Erfassen  <" + kategorie + ">", null, new ownActionAgent(kategorie)), false);
			}

			for (int i=0;i<this.cVarianten.length;i++)
			{
				MyE2_Button oButHelp = new MyE2_Button(new MyE2_String(this.cVarianten[i][0],false));
				oButHelp.EXT().set_C_MERKMAL(this.cVarianten[i][1]);
				oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(this);
				oButHelp.add_oActionAgent(RB_TextArea_Selector.ownAction);
				this.popUp.addButton(oButHelp,true);

			}
		}
		else
		{
			if (bibALL.isEmpty(this.cInfoWhenEmpty))
			{
				this.popUp.get_oColInnen().add(new RB_lab(new MyE2_String("Text-Auswahl ist undefiniert !")));
			}else{
				StringSeparator oSep = new StringSeparator(this.cInfoWhenEmpty,"|");
				for (int i=0;i<oSep.size();i++)
				{
					this.popUp.get_oColInnen().add(new RB_lab(new MyE2_String(oSep.get_(i),false))._col_back_d(),new Insets(2,2,2,2));
				}

				this.popUp.get_oColInnen().add(new MyE2_Button("Create new entry", null, new ownActionAgent(kategorie)));

			}
		}

	}

	static
	{
		XX_ActionAgent oACT = new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				/*
				 * popup-button hat den Text in  get_C_MERKMAL und das textobject in get_O_PLACE_FOR_EVERYTHING
				 */
				MyE2_Button 		oBUT = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource());
				RB_TextArea 		oTextArea = ((RB_TextArea_Selector)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING()).get_oTextArea();

				String 				cZusatzText = bibALL.null2leer(((RB_TextArea_Selector)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING()).getZusatzText());
				boolean 			bZusatzTextVorPopText = ((RB_TextArea_Selector)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING()).isZusatzTextVorPopupText();

				String 				cHelpText = oBUT.EXT().get_C_MERKMAL();

				if (!bibALL.isEmpty(cHelpText))
				{
					if (bZusatzTextVorPopText)
						cHelpText = cZusatzText+cHelpText;
					else
						cHelpText = cHelpText+cZusatzText;

				}
				oTextArea.setText(cHelpText);
			}
		};
		ownAction = oACT;
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());

	};

	public void set_ZusatzText(String ZusatzText,boolean bVorPopupText)
	{
		if (!bibALL.isEmpty(ZusatzText))
		{
			this.cZusatzText=ZusatzText;
			this.textArea.setText(this.cZusatzText);
			this.bZusatzTextVorPopupText=bVorPopupText;
		}
	}

	protected boolean isZusatzTextVorPopupText() {
		return bZusatzTextVorPopupText;
	}

	protected String getZusatzText() {
		return cZusatzText;
	}

	public abstract String queryForPopup() throws myException;

	public abstract String get_kategorie_if_no_eintraege() throws myException;

	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		_fill_popUp(queryForPopup());

		if (dataObject.get_RecORD()==null) {
			this.get_oTextArea().setText("");
		} else {
			this.get_oTextArea().setText(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.textArea.setText(S.NN(valueFormated));
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key= key;
	}

	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.EXT = oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.EXT;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public void mark_Neutral() throws myException {
		if (this.borderNeutral != null) {
			this.get_oTextArea().setBorder(this.borderNeutral);
		} else {
			this.get_oTextArea().setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
		}
		this.get_oTextArea().setBackground(new E2_ColorEditBackground());
		this.get_oTextArea().setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException {
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.get_oTextArea().setBackground(new E2_ColorGray(230));
		this.getPopUp().set_oIconInactiv(KFIX___CONST.IKON.EMPTY.getIkon());
		this.getPopUp().set_bEnabled_For_Edit(false);
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.get_oTextArea().setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		this.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.get_oTextArea().setAlignment(align);
	}

	@Override
	public RB_K getMyKeyInCollection() throws myException {
		return null;
	}

	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		return this.key;
	}

	@Override
	public RB_KF K(IF_Field field) throws myException {
		return new RB_KF(field);
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		if(S.isFull(this.get_oTextArea().getText())){
			return this.get_oTextArea().getText();
		}
		return null;
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		private String 					cKategorie = null;

		private MyE2_TextField  		oTextTitel = new MyE2_TextField("",400,30);
		private MyE2_TextArea  		 	oTextArea = new MyE2_TextArea("", 400, 500, 10);

		public ownActionAgent(String Kategorie) 
		{
			super();
			this.cKategorie = Kategorie;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//nachsehen, ob es die kategorie gibt, wenn nein, dann anlegen
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			if (oMV.get_bIsOK())
			{
				//dann einen Text erfassen
				this.oTextArea.setText("");
				this.oTextTitel.setText("");
				MyE2_Grid gridInput = new MyE2_Grid(1,0);
				gridInput.add(this.oTextTitel,E2_INSETS.I_0_0_0_1);
				gridInput.add(this.oTextArea,E2_INSETS.I_0_0_0_1);

				new ownMessageBox(new MyE2_String("Einen neuen Text erfassen"),
						new MyE2_String("Speichern"),
						new MyE2_String("Abbruch"),
						true,
						true,
						gridInput,
						new ownActionAgentSave(),
						new Extent(450),
						new Extent(340));
			}
			else
			{
				bibMSG.add_MESSAGE(oMV);
			}
		}


		private class ownActionAgentSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException		{
				
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				if (  ! (S.isFull(oTextArea.getText()) && S.isFull(oTextTitel.getText()))) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Ein leerer Text wird nicht gespeichert !!!")));
					return;
				
				} else {
				
					Rec20 r_new_text = new Rec20(_TAB.textblock);
					r_new_text	._nv(TEXTBLOCK.text, oTextArea.getText(), oMV)
						  		._nv(TEXTBLOCK.texttitel, oTextTitel.getText(), oMV)
						  		._nv(TEXTBLOCK.iststandard, "N", oMV)
						  		;
					
					RecList20 rl_text_kat = new RecList20(_TAB.textblock_kat)._fill(new vgl(TEXTBLOCK_KAT.bezeichnung, ownActionAgent.this.cKategorie).s(),null);
					
					if (rl_text_kat.size()==0) {
						Rec20 r_new_text_kat = new Rec20(_TAB.textblock_kat);
						try {
							r_new_text_kat	._nv(TEXTBLOCK_KAT.bezeichnung, ownActionAgent.this.cKategorie,oMV)
											._nv(TEXTBLOCK_KAT.ist_standard, "N",oMV)
											._SAVE(false, oMV);
							
							r_new_text._add_field_val_pair(TEXTBLOCK.id_textblock_kat, _TAB.textblock_kat.seq_currval());
							r_new_text._SAVE(true, oMV);
						} catch (Exception e) {
							bibDB.Rollback();
							oMV.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Fehler beim Speichern !")));
							e.printStackTrace();
							return;
						}
						
						
					} else if (rl_text_kat.size()>1) {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Systemfehler ! Doppelter Textschlüssel !")));
						return;
					} else {
						r_new_text._add_field_val_pair(TEXTBLOCK.id_textblock_kat, rl_text_kat.get(0).get_ufs_dbVal(TEXTBLOCK_KAT.id_textblock_kat));
						r_new_text._SAVE(true, oMV);
						
					}
					RB_TextArea_Selector.this.popUp.removeAllButtons();
					RB_TextArea_Selector.this._fill_popUp(queryForPopup());
				}
			}
		}

		private class ownMessageBox extends E2_MessageBoxYesNo
		{
			public ownMessageBox(	MyE2_String 		TextTitelZeile,
									MyE2_String 		TextYes, 
									MyE2_String 		TextNo, 
									boolean 			bShowYes,
									boolean		 		bShowNo, 
									MyE2IF__Component 	ComponentToShow,
									XX_ActionAgent 		ActionAgentStart, 
									Extent 				iWidth,
									Extent 				iHeight) throws myException {
				super(TextTitelZeile, TextYes, TextNo, bShowYes, bShowNo, ComponentToShow,
						ActionAgentStart, iWidth, iHeight);
			}
		}
	}

}
