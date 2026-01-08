package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ELEMENT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ.FSMS_Const;

public class FS_Selector_Multi_Matspez_elemente extends XX_ListSelektor {

	private static int[] iSpalten = {120,100,100,30,30};

	//container und die darstellung der vComponentsgroup
	private		ownSelectContainer  				oPopupContainer =			new ownSelectContainer(); 
	private 	MyE2_Grid  					oGrid4Popup = 				new MyE2_Grid(FS_Selector_Multi_Matspez_elemente.iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	// componenten fuer den selector
	private 	SpecialButtonStartSelectMatSpez          oButtonStartInSelector = 	null;
	private 	MyE2_Grid                  	oGrid4Infos_Status = 		new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	//array zum aufbau der elemente-selektion fuer die selektoren in den zeilen
	private 	String[][]   				arrElementeText_ID = 		null;
	
	private 	vComponentGroup  			vComponentsGroupOld = 		new vComponentGroup();
	private 	vComponentGroup  			vComponentsGroup = 			new vComponentGroup();
	
	private 	MyE2_SelectField            oSelFieldSollIstStatus = 	new MyE2_SelectField(FSMS_Const.FSMS_Status_DD,"",false);
	private 	MyE2_SelectField            oSelFieldTypLiefAbnehmer= 	new MyE2_SelectField(FSMS_Const.FSMS_TYP_LIEF_ABN_DD,"",false);
	
	private   	ownButtonStartSelection     oButtonStartSelection = 	new ownButtonStartSelection();
	private   	ownButtonDeleteSelection    oButtonDeleteSelection = 	new ownButtonDeleteSelection();
	private 	ownButtonCancelSelection   	oButtonCancelSelection = 	new ownButtonCancelSelection();
	
	
	
	//fuer die hervorhebung der gefundenen materialspezifikationen muss ein vector mit passenden id_mat_spez gefuellt werden und 
	//fuer die masken-anzeige dann bereitgehalten werden
	private 	VectorSingle  				vFound_ID_Matspez = 		new VectorSingle();
	

	public FS_Selector_Multi_Matspez_elemente() throws myException {
		super();
		
		RECLIST_ELEMENT  rlElement = new RECLIST_ELEMENT("SELECT * FROM "+bibE2.cTO()+"."+_DB.ELEMENT+" ORDER BY "+_DB.ELEMENT$LANG);
		
		arrElementeText_ID = new String[rlElement.get_vKeyValues().size()+1][2];

		arrElementeText_ID[0][0] = "-";
		arrElementeText_ID[0][1] = "";
		for (int i=0;i<rlElement.get_vKeyValues().size();i++)
		{
			arrElementeText_ID[i+1][0] = rlElement.get(i).get_LANG_cUF_NN(rlElement.get(i).get_KURZ_cUF_NN(""))+" ("+rlElement.get(i).get_KURZ_cUF_NN("-")+")";
			arrElementeText_ID[i+1][1] = rlElement.get(i).get_ID_ELEMENT_cUF();
		}
		
		this.oButtonStartInSelector = new SpecialButtonStartSelectMatSpez(new MyE2_String("Mat-Spez"));
		this.oButtonStartInSelector.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FS_Selector_Multi_Matspez_elemente.this.oPopupContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(500), new MyE2_String("Materialspezifikation suchen"));
			}
		});
		
		//eine componentgroup anfuegen
		this.vComponentsGroup.add(new ownComponentGroup());
		this.resfresh_liste_und_anzeige();
		
		this.oPopupContainer.add(this.oGrid4Popup,E2_INSETS.I_2_2_2_2);

	}

	private String get_cQuery_4_ID_MATSPEZ() throws myException
	{
		String cSQL_Rueck = "";
		
		//jeder sql-part im vector enthaelt was in der art:
		//(V.ID_ELEMENT=1002 AND ((V.WERT_UNTEN<=0.1 AND V.WERT_OBEN>=0.1) OR (V.WERT_UNTEN<=0.2 AND V.WERT_OBEN>=0.2)))"
		
		Vector<String>  vSQL_Parts = 		this.vComponentsGroup.get_vSQL_Parts();
		Vector<String>  vSQL_Parts_ext = 	new Vector<String>();
		String cVName = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_MATSPEZ_4_SELECTION";

		
		if (vSQL_Parts.size()>0)
		{

			for (String cSQL_p: vSQL_Parts)
			{
				vSQL_Parts_ext.add("SELECT VI.ID_MAT_SPEZ FROM "+cVName+" VI WHERE "+cSQL_p);
			}
			
			cSQL_Rueck = bibALL.Concatenate(vSQL_Parts_ext, " INTERSECT ", "");
		}
		//DEBUG.System_println("Teil 1 : "+cSQL_Rueck,"");
		return cSQL_Rueck;
	}

	
	
	@Override
	public String get_WhereBlock() throws myException {
		
		String cSQL_Rueck = "";
		
		String cVName = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_MATSPEZ_4_SELECTION";

		String cQueryInnen = 	this.get_cQuery_4_ID_MATSPEZ();
		
		if (S.isFull(cQueryInnen)){
			cSQL_Rueck = "(JT_ADRESSE.ID_ADRESSE IN (" +
					"SELECT V.ID_ADRESSE FROM "+cVName+" V WHERE V.ID_MAT_SPEZ IN (" +cQueryInnen+
					")))";
		}
		
		//DEBUG.System_println("Teil 2 : "+cSQL_Rueck,"");
		
		return cSQL_Rueck;
	}

	@Override
	public Component get_oComponentForSelection() throws myException
	{
		GridLayoutData  oGL = new GridLayoutData();
		oGL.setBackground(new E2_ColorDark());
		oGL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		oGL.setInsets(E2_INSETS.I_0_0_0_0);
		
		int[] iWidth = {80,80,30}; 
		this.oGrid4Infos_Status.setWidth(new Extent(30));
		MyE2_Grid oGridRueck = new MyE2_Grid(iWidth, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.add(new MyE2_Label(""),E2_INSETS.I_0_0_0_0);
		oGridRueck.add(this.oButtonStartInSelector,E2_INSETS.I_0_0_2_0);
		oGridRueck.add(this.oGrid4Infos_Status,oGL);
		return oGridRueck;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException
	{
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.oButtonStartSelection.add_oActionAgent(oAgent);
		this.oButtonDeleteSelection.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {
		
		MyE2_MessageVector  oMV = this.vComponentsGroup.check_ob_komplett_und_korrekt();
		bibMSG.add_MESSAGE(oMV);
	}
	

	private void fill_vFound_ID_MatSpez() throws myException
	{
		this.vFound_ID_Matspez.removeAllElements();
		String[][]  vIDs = bibDB.EinzelAbfrageInArray(this.get_cQuery_4_ID_MATSPEZ());
		
		if (vIDs != null &&  vIDs.length>0)
		{
			for (int i=0;i<vIDs.length;i++)
			{
				this.vFound_ID_Matspez.add(vIDs[i][0]);
			}
		}
	}
	
	
	public Vector<String> get_vFound_ID_Matspez() {
		return this.vFound_ID_Matspez;
	}


	private void resfresh_liste_und_anzeige() throws myException
	{
		this.oGrid4Popup.removeAll();
		this.oGrid4Popup.set_Spalten(FS_Selector_Multi_Matspez_elemente.iSpalten);
		
		GridLayoutData  oGL_Titel = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_2_2, new E2_ColorDDark(), 1, 1);
		GridLayoutData  oGL_TitelR = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_0_2, new E2_ColorDDark(), 1, 1);
		
		GridLayoutData  oGL_Body = 		MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_2_2, new E2_ColorBase(), 1, 1);
		GridLayoutData  oGL_BodyR = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_0_2, new E2_ColorBase(), 1, 1);
		
		GridLayoutData  oGL_BodyButtons = 	MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 10, 2, 2), new E2_ColorBase(), 1, 1);
		GridLayoutData  oGL_BodyButtonsR = 	MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 10, 0, 2), new E2_ColorBase(), 1, 1);

		
		//zuerst die uebergreifenden felder
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("Lieferant/Abnehmer")),MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 2, 0, 10),  new E2_ColorDDark(), 2, 1));
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("Typ (Soll/Ist")),MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 2, 0, 10),  new E2_ColorDDark(), 3, 1));
		this.oGrid4Popup.add(this.oSelFieldTypLiefAbnehmer,MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 2, 0, 10), null, 2, 1));
		this.oGrid4Popup.add(this.oSelFieldSollIstStatus,MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 2, 0, 10), null, 3, 1));
	//	this.oGrid4Popup.add(new MyE2_Label(""),MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 2, 0, 10), null, this.oGrid4Popup.getSize()-4, 1));
		
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("Element")), oGL_Titel);
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("Unterer Wert (%)")), oGL_Titel);
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("Oberer Wert (%)")), oGL_Titel);
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("")), oGL_Titel);
		this.oGrid4Popup.add(new MyE2_Label(new MyE2_String("")), oGL_TitelR);

		String cToolTips = "";

		for (ownComponentGroup oComps: this.vComponentsGroup)
		{
			this.oGrid4Popup.add(oComps.get_oSelFieldElemente(), oGL_Body);
			this.oGrid4Popup.add(oComps.get_oTF_VON(), oGL_Body);
			this.oGrid4Popup.add(oComps.get_oTF_BIS(), oGL_Body);
			if (! this.vComponentsGroup.get_bIstErstesElement(oComps)) {
				this.oGrid4Popup.add(oComps.get_oButtonDelete(), oGL_Body);
			} else	{
				this.oGrid4Popup.add(new MyE2_Label(""), oGL_Body);
			}
			if (this.vComponentsGroup.get_bIstLetztesElement(oComps)) {
				this.oGrid4Popup.add(oComps.get_oButtonAddNew(), oGL_BodyR);
			} else	{
				this.oGrid4Popup.add(new MyE2_Label(""), oGL_Body);
			}
			
			if (S.isFull(oComps.get_oSelFieldElemente().get_ActualWert())) {
				cToolTips = cToolTips+oComps.get_oSelFieldElemente().get_ActualView()+": "+oComps.get_oTF_VON().getText()+"  --  "+oComps.get_oTF_BIS().getText()+"\n";
			}
				
		}
		
		//die aktionsbuttons fuer das popup-fenster
		this.oGrid4Popup.add(this.oButtonStartSelection, oGL_BodyButtons);
		this.oGrid4Popup.add(this.oButtonDeleteSelection, oGL_BodyButtons);
		this.oGrid4Popup.add(this.oButtonCancelSelection, oGL_BodyButtonsR);
		
		//jetzt noch den info-label neben dem startbutton fuellen
		this.oGrid4Infos_Status.removeAll();
		int iAnzahlGefuellteSelektionen = this.vComponentsGroup.get_vComponentGroupsFilled().size();
		if (iAnzahlGefuellteSelektionen==0)	{
			this.oGrid4Infos_Status.add(new MyE2_Label("0"),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
		} else {
			this.oGrid4Infos_Status.add(new MyE2_Label(""+iAnzahlGefuellteSelektionen),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0,new E2_ColorHelpBackground(),1));
		}
		
		this.oButtonStartInSelector.setToolTipText(cToolTips);
		if (S.isEmpty(cToolTips))	{
			this.oButtonStartInSelector.setToolTipText(new MyE2_String("Firmen anhand der eingetragenen Materialspezifikationen suchen").CTrans());
		}
		
	}
	
	
	private class ownButtonStartSelection extends MyE2_Button
	{
		public ownButtonStartSelection() {
			super("OK",new MyE2_String("Auswahl speichern und Mehrfachselektion ausführen"),null,true);


			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					FS_Selector_Multi_Matspez_elemente.this.doInternalCheck();
				}
			});
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					FS_Selector_Multi_Matspez_elemente.this.fill_vFound_ID_MatSpez();
					
					FS_Selector_Multi_Matspez_elemente.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroupOld.removeAllElements();
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroupOld.addAll(FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup);
					FS_Selector_Multi_Matspez_elemente.this.resfresh_liste_und_anzeige();
					
				}
			});
		}
	}
	

	private class ownButtonDeleteSelection extends MyE2_Button
	{
		public ownButtonDeleteSelection() {
			super("Selektion löschen",new MyE2_String("Alle Felder löschen"),null,true);
			this.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));

			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					FS_Selector_Multi_Matspez_elemente.this.vFound_ID_Matspez.removeAllElements();
					
					//vector leermachen
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.removeAllElements();
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.add(new ownComponentGroup());
					FS_Selector_Multi_Matspez_elemente.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					FS_Selector_Multi_Matspez_elemente.this.resfresh_liste_und_anzeige();
				}
			});
		}
	}
	
	private class ownButtonCancelSelection extends MyE2_Button
	{
		public ownButtonCancelSelection() {
			super("Abbrechen",	new MyE2_String("Auswahl abbrechen, nichts verändern"),null,true);
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					//alten zustand wiederherstellen
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.removeAllElements();
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.addAll(FS_Selector_Multi_Matspez_elemente.this.vComponentsGroupOld);
					FS_Selector_Multi_Matspez_elemente.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					FS_Selector_Multi_Matspez_elemente.this.resfresh_liste_und_anzeige();
				}
			});
		}
	}

	
	/**
	 * klasse repraesentiert eine zeile im selektor-popup-fenster
	 * @author martin
	 *
	 */
	private class ownComponentGroup
	{
		private MyE2_SelectField  					oSelFieldElemente = 	null;
		private MyE2_TextField    					oTF_VON = 				null;
		private MyE2_TextField    					oTF_BIS = 				null;
		private ownButtonHinzuFuegen       			oButtonAddNew = 		null;
		private ownButtonDeleteLine       			oButtonDelete = 		null;
		
		public ownComponentGroup() throws myException {
			super();
			this.oSelFieldElemente = new MyE2_SelectField(FS_Selector_Multi_Matspez_elemente.this.arrElementeText_ID, "", false);
			this.oSelFieldElemente.setWidth(new Extent(90));
			this.oTF_VON = new MyE2_TextField("", 100, 10);
			this.oTF_BIS = new MyE2_TextField("", 100, 10);
			this.oButtonAddNew = new ownButtonHinzuFuegen();
			this.oButtonDelete = new ownButtonDeleteLine();
			
			//der delete-button muss das element kennen
			this.oButtonDelete.EXT().set_O_PLACE_FOR_EVERYTHING(this);
			
		}
		public MyE2_SelectField get_oSelFieldElemente() {
			return oSelFieldElemente;
		}
		public MyE2_TextField get_oTF_VON() {
			return oTF_VON;
		}
		public MyE2_TextField get_oTF_BIS() {
			return oTF_BIS;
		}
		public ownButtonHinzuFuegen get_oButtonAddNew() {
			return oButtonAddNew;
		}
		public ownButtonDeleteLine get_oButtonDelete() {
			return oButtonDelete;
		}

		
		public MyE2_MessageVector  check_ob_komplett_und_korrekt(vComponentGroup vGroupVector)
		{
			MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
			
			try
			{
				//wenn alle 3 elemente leer sind in einer zeile, dann ist alles ok
				if (S.isEmpty(this.oSelFieldElemente.get_ActualWert()) && S.isEmpty(this.oTF_VON.getText()) && S.isEmpty(this.oTF_BIS.getText()))
				{
					return oMVRueck;
				}
						
				
				//zuerst pruefen, ob das element leer ist, 
				//danach, ob es doppelt ist
				if (S.isEmpty(this.oSelFieldElemente.get_ActualWert()))	{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte die Elemente in den Auswahlzeilen definieren !")));
				} else {
					
					if (vGroupVector != null)
					{
						for (ownComponentGroup oCompGroup: vGroupVector)
						{
							if (oCompGroup.get_oSelFieldElemente().get_ActualWert().equals(this.oSelFieldElemente.get_ActualWert()) &&
								oCompGroup != this)	{
								oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," wurde mehrfach ausgewählt!",true)));
							}
						}
					}
					//jetzt die feldwerte auf gefuellt und zwischen 0 und 1 pruefen
					if (S.isEmpty(this.oTF_VON.getText()))
					{
						oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keinen <VON>-Wert !",true)));
					}
					else
					{
						MyDouble oDB = new MyDouble(this.oTF_VON.getText());
						if (!oDB.get_bOK())
						{
							oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keine korrekte Zahl im <VON>-Wert !",true)));
						}
						else
						{
							if (oDB.get_oDouble()<0 || oDB.get_oDouble()>100) {
								oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keine korrekte Zahl zwischen 0 und 100  im <VON>-Wert !",true)));
							}
						}
					}
						
					//jetzt die feldwerte auf gefuellt und zwischen 0 und 100 pruefen
					if (S.isEmpty(this.oTF_BIS.getText()))
					{
						oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keinen <BIS>-Wert !",true)));
					}
					else
					{
						MyDouble oDB = new MyDouble(this.oTF_BIS.getText());
						if (!oDB.get_bOK())
						{
							oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keine korrekte Zahl im <BIS>-Wert !",true)));
						}
						else
						{
							if (oDB.get_oDouble()<0 || oDB.get_oDouble()>100) {
								oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Element ",true,this.oSelFieldElemente.get_ActualView(),false," hat keine korrekte Zahl zwischen 0 und 100  im <BIS>-Wert!",true)));
							}
						}
					}
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
				oMVRueck.add(e.get_ErrorMessage());
			}
			return oMVRueck;
		}
		
		
		
		public String get_cSQL_PART() throws myException
		{
			String cRueck = "";
			
			MyE2_MessageVector oMV = this.check_ob_komplett_und_korrekt(null);
			if (oMV.get_bIsOK() && S.isFull(this.oSelFieldElemente.get_ActualWert()))
			{
				MyBigDecimal oBD_VON = new MyBigDecimal(this.oTF_VON.getText(), true);
				MyBigDecimal oBD_BIS = new MyBigDecimal(this.oTF_BIS.getText(), true);
				
				
				if (oBD_VON.get_bOK() && oBD_BIS.get_bOK())
				{
					String cID_ELEMENT = this.oSelFieldElemente.get_ActualWert();
					String cWert_von = oBD_VON.get_UnFormatedRoundedNumber(5);
					String cWert_bis = oBD_BIS.get_UnFormatedRoundedNumber(5);
					
					//sonderbedingungen erfassen, die die definition lieferant/abnehmer und soll/ist betrifft
					Vector<String> vTopLevelBedingungen = new Vector<String>();
					if (S.isFull(FS_Selector_Multi_Matspez_elemente.this.oSelFieldSollIstStatus.get_ActualWert())) 	{
						vTopLevelBedingungen.add("NVL(VI.SOLL_IST_STATUS,'-')="+bibALL.MakeSql(FS_Selector_Multi_Matspez_elemente.this.oSelFieldSollIstStatus.get_ActualWert()));
					}
					if (S.isFull(FS_Selector_Multi_Matspez_elemente.this.oSelFieldTypLiefAbnehmer.get_ActualWert()))	{
						if (FS_Selector_Multi_Matspez_elemente.this.oSelFieldTypLiefAbnehmer.get_ActualWert().equals(FSMS_Const.FSMS_TYP_LIEFERANT))	{
							vTopLevelBedingungen.add("NVL(VI."+_DB.MAT_SPEZ$IST_LIEFERANT+",'N')='Y'");
						} else
						{
							vTopLevelBedingungen.add("NVL(VI."+_DB.MAT_SPEZ$IST_ABNEHMER+",'N')='Y'");
						}
					}
					String cSQL_TL = "";
					if (vTopLevelBedingungen.size()>0){
						cSQL_TL = bibALL.Concatenate(vTopLevelBedingungen, " AND ", "")+" AND ";
					}
					
					cRueck = "(VI.ID_ELEMENT="+cID_ELEMENT+" AND "+cSQL_TL +
							    " (" +
						   		" (VI.WERT_UNTEN<="+cWert_von+" AND VI.WERT_OBEN>="+cWert_von+") OR " +
						   		" (VI.WERT_UNTEN<="+cWert_bis+" AND VI.WERT_OBEN>="+cWert_bis+") OR " +
						   		" (VI.WERT_UNTEN<="+cWert_von+" AND VI.WERT_OBEN>="+cWert_bis+") OR " +
						   		" (VI.WERT_UNTEN>="+cWert_von+" AND VI.WERT_OBEN<="+cWert_bis+") " +
						   		" ) " +
						   	" )";
				}
			}
			
			return cRueck;
		}
		
	}
	
	
	/**
	 * Vector mit allen zeilen im Selektor-popup-fenster
	 */
	private class vComponentGroup extends Vector<ownComponentGroup> {
		public vComponentGroup() {
			super();
		}
		
		public boolean get_bIstErstesElement(ownComponentGroup oCompGroup) {
			if (this.size()>0) {
				if (oCompGroup==this.get(0)) { 
					return true;
				}
			}
			return false;
		}

		public boolean get_bIstLetztesElement(ownComponentGroup oCompGroup) {
			if (this.size()>0) {
				if (oCompGroup==this.get(this.size()-1)) {
					return true;
				}
			}
			return false;
		}
		
		public void deleteElement(ownComponentGroup oCompGroupToDelete) {
			if (this.size()>0) {
				for (int i=0;i<this.size();i++){
					if (this.get(i)==oCompGroupToDelete)
					{
						this.remove(i);
						break;
					}
				}
			}
		}
		
		public vComponentGroup get_vComponentGroupsFilled() throws myException {
			vComponentGroup  vRueck = new vComponentGroup();
			for (ownComponentGroup oCompGrp: this) {
				if (S.isFull(oCompGrp.get_oTF_VON().getText()) && new MyDouble(oCompGrp.get_oTF_VON().getText()).get_bOK() &&
					S.isFull(oCompGrp.get_oTF_BIS().getText()) && new MyDouble(oCompGrp.get_oTF_BIS().getText()).get_bOK()	 &&
					S.isFull(oCompGrp.get_oSelFieldElemente().get_ActualWert()))
				{
					vRueck.add(oCompGrp);
				}
			}
			return vRueck;
		}

		
		public MyE2_MessageVector  check_ob_komplett_und_korrekt() {
			MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
			
			for (ownComponentGroup oCompGroup: this) {
				oMVRueck.add_MESSAGE(oCompGroup.check_ob_komplett_und_korrekt(this));
			}
			return oMVRueck;
		}
		
		
		public Vector<String>  get_vSQL_Parts() throws myException	{
			Vector<String>  vRueck = new Vector<String>();
			for (ownComponentGroup oCompGroup: this) {
				String cSQL_Part = oCompGroup.get_cSQL_PART();
				if (S.isFull(cSQL_Part)) {
					vRueck.add(cSQL_Part);
				}
			}
			return vRueck;
		}
		
	}

	
	private class ownButtonHinzuFuegen extends MyE2_Button 	{
		public ownButtonHinzuFuegen() 	{
			super(E2_ResourceIcon.get_RI("multi_select_add_new.png"), true);
			this.setToolTipText(new MyE2_String("Weitere Auswahlmöglichkeit hinzufügen").CTrans());
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.add(new ownComponentGroup());
					FS_Selector_Multi_Matspez_elemente.this.resfresh_liste_und_anzeige();
				}
			});
		}
	}

	

	private class ownButtonDeleteLine extends MyE2_Button {
		public ownButtonDeleteLine(){
			super(E2_ResourceIcon.get_RI("multi_select_delete.png"), true);
			this.setToolTipText(new MyE2_String("Diese Auswahlmöglichkeit entfernen").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent()	{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException{
					FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup.deleteElement(
							(ownComponentGroup)ownButtonDeleteLine.this.EXT().get_O_PLACE_FOR_EVERYTHING());
					FS_Selector_Multi_Matspez_elemente.this.resfresh_liste_und_anzeige();
				}
			});
		}
	}


	private class ownSelectContainer extends E2_BasicModuleContainer {
		public ownSelectContainer() {
			super();
		}
	}
	

	/**
	 * spezielle klasse um eine "recursive-searchbaren" selektor-button zu erzeugen
	 */
	public class SpecialButtonStartSelectMatSpez extends MyE2_Button 	{

		public SpecialButtonStartSelectMatSpez(MyString cText) {
			super(cText);
		}
		
		public FS_Selector_Multi_Matspez_elemente get_SelectorThisBelongsTo() {
			return FS_Selector_Multi_Matspez_elemente.this;
		}
		
		public Vector<String> get_vIDs_Selected_Elements() throws myException 	{
			Vector<String> vRueck = new Vector<String>();
			
			for (ownComponentGroup oComps: FS_Selector_Multi_Matspez_elemente.this.vComponentsGroup)
			{
				vRueck.add(oComps.get_oSelFieldElemente().get_ActualWert());
			}
			
			return vRueck;
		}
	}
	
	
	
	
}
