package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FS__Adress_Info_Zentrum_NG;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class FZ_addon_AdressComponents_generator  implements  MyE2IF_IsMarkable {
	
	
	private MyE2IF__Component  mother = null;

	private FZ_addon_Button_to_SelectROW  	label_with_geografic_adress_text = new FZ_addon_Button_to_SelectROW();
	private FZ_addon_Button_to_SelectROW  	label_with_juristic_adress_addon = new FZ_addon_Button_to_SelectROW();
	private ownInternal_INFO_Button  		infoButton = new ownInternal_INFO_Button();
	private ownListButton_open_AdressMask  	editButton = new ownListButton_open_AdressMask();
	
	
	public FZ_addon_AdressComponents_generator(MyE2IF__Component  p_motherComp) throws myException{
		this.mother = p_motherComp;
	}
	
	
	
	public void fill(RECORD_ADRESSE_extend recAdress) throws myException {
		RECORD_ADRESSE_extend recAdressHpt = recAdress.get_main_Adress();
		
		this.label_with_geografic_adress_text=this.generate_label_with_geografic_adress_text(recAdress,recAdressHpt);
		this.label_with_juristic_adress_addon=this.generate_label_with_juristic_adress_addon(recAdress,recAdressHpt);
		this.infoButton=this.generate_info_button(recAdress,recAdressHpt);
		this.editButton=this.generate_edit_button(recAdress,recAdressHpt);
	}
	
	
	private FZ_addon_Button_to_SelectROW generate_label_with_geografic_adress_text(RECORD_ADRESSE_extend recAdressGeo, RECORD_ADRESSE_extend recAdressMain) throws myException {
		String s_text_4_lager_adress = recAdressGeo.get_Signatur_name_ort();
		if (S.isFull(recAdressGeo.get_SONDERLAGER_cUF_NN(""))) {
			ENUM_SONDERLAGER lag = ENUM_SONDERLAGER.find_SonderlagerFromDBValue(recAdressGeo.get_SONDERLAGER_cUF_NN(""));
			if (lag==null) {
				s_text_4_lager_adress=new MyE2_String("Fehler: undefiniert!").CTrans();
			} else {
				s_text_4_lager_adress = new MyE2_String(lag.user_text()).CTrans();
			}
		} else if (recAdressGeo.get_ID_ADRESSE_cUF().equals(recAdressMain.get_ID_ADRESSE_cUF())) {
			s_text_4_lager_adress = recAdressGeo.get_Signatur_name_ort();
		} else {
			s_text_4_lager_adress = recAdressGeo.get_Signatur_name_ort();
		}
		return new FZ_addon_Button_to_SelectROW(s_text_4_lager_adress, new E2_FontPlain(), this.mother);
	}
	
	
	private FZ_addon_Button_to_SelectROW generate_label_with_juristic_adress_addon(RECORD_ADRESSE_extend recAdressGeo, RECORD_ADRESSE_extend recAdressMain) throws myException {
		String s_text = recAdressMain.get_Signatur_name_ort();
		if (S.isFull(recAdressGeo.get_SONDERLAGER_cUF_NN(""))) {
			s_text = "";
		} else if (recAdressGeo.get_ID_ADRESSE_cUF().equals(recAdressMain.get_ID_ADRESSE_cUF())) {
			s_text = "";
		} else {
			s_text = recAdressMain.get_Signatur_name_ort();
		}
		
		if (S.isFull(s_text)) {
			return  new FZ_addon_Button_to_SelectROW(s_text, new E2_FontItalic(-2), this.mother);
		} else {
			return new FZ_addon_Button_to_SelectROW("", new E2_FontItalic(-2), this.mother);
		}
	}
	
	
	
	private ownInternal_INFO_Button generate_info_button(RECORD_ADRESSE_extend recAdressGeo, RECORD_ADRESSE_extend recAdressMain) throws myException {
		return  new ownInternal_INFO_Button(recAdressMain.get_ID_ADRESSE_cUF());
	}

	
	private ownListButton_open_AdressMask generate_edit_button(RECORD_ADRESSE_extend recAdressGeo, RECORD_ADRESSE_extend recAdressMain) throws myException {
		ownListButton_open_AdressMask buttonEdit = new ownListButton_open_AdressMask();
		buttonEdit.EXT().set_C_MERKMAL(recAdressMain.get_ID_ADRESSE_cUF());
		buttonEdit.setBackground(new E2_ColorBase());
		buttonEdit.setBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		
		return buttonEdit;
	}

	
	
	
	
	private class ownListButton_open_AdressMask extends E2_BUTTON_OPEN_MASK_FromID {

		
		public ownListButton_open_AdressMask() throws myException {
			super(	bibSES.get_FS_ModulContainer_MASK(), 
					new MyE2_String("Adressen"), 
					E2_MODULNAME_ENUM.MODUL.MODUL_KENNER_PROGRAMM_WIDE.get_callKey(),	
					ENUM_VALIDATION.ADRESSE_EDIT.getButtonKey(), 
					ENUM_VALIDATION.ADRESSE_VIEW.getButtonKey());
			
			this.setText("");
			this.setIcon(E2_ResourceIcon.get_RI("edit_list2.png"));
			
			this.setToolTipText(new MyE2_String("Adresse direkt bearbeiten ....").CTrans());
		}

		@Override
		public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row oRowForButtons) throws myException {
		}

		@Override
		public void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row oRowForButtons) throws myException {
			
		}
		
	}
	
	
	private class ownInternal_INFO_Button extends MyE2_Button  {

		private String id_adresse = null;
		
		public ownInternal_INFO_Button() {
			super("");
		}
		
		//##TODO@Sebastien: 25.07.2016 - neues Adresse info system
		
		public ownInternal_INFO_Button(String p_id_adresse_uf) throws myException {
			super();
			this.__setImages(E2_ResourceIcon.get_RI("inforound_mini.png"),E2_ResourceIcon.get_RI("inforound_mini.png"));
			this.setStyle(MyE2_Button.StyleImageButton());
			
			this.id_adresse = p_id_adresse_uf;
			
			this.add_GlobalValidator(ENUM_VALIDATION.ADRESSE_INFOBUTTON.getValidator());
//			this.add_GlobalAUTHValidator_PROGRAMM_WIDE(E2_GLOBAL_VALID_KEYS.ANZEIGE_ADRESSUEBERSICHT.db_val());
			
			this.setToolTipText(new MyE2_String("Adress-Informationen anzeigen ...").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent() 	{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					if(ENUM_MANDANT_DECISION.USE_NEW_INFO_BUTTON.is_YES()){
						new ownBasicContainer_ng();
					}else{
						new ownBasicContainer();
					}
				}
			});
			
		}

		
		private class ownBasicContainer extends E2_BasicModuleContainer {
			
			public ownBasicContainer() throws myException {
				super();
				FS__Adress_Info_Zentrum oAdressInfo = new FS__Adress_Info_Zentrum(this);

				oAdressInfo.init_INFO(ownInternal_INFO_Button.this.id_adresse);
				
				this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);
				
				this.set_iVerticalOffsetForTabbedPane(130);
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), 
						new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false));
				
			}
		}
		
		
		private class ownBasicContainer_ng extends E2_BasicModuleContainer {
			
			public ownBasicContainer_ng() throws myException {
				super();
				FS__Adress_Info_Zentrum_NG oAdressInfo = new FS__Adress_Info_Zentrum_NG(this);

				oAdressInfo.init_INFO(ownInternal_INFO_Button.this.id_adresse);
				
				this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);
				
				this.set_oResizeHelper(new ownResizer());
				
				this.set_iVerticalOffsetForTabbedPane(130);
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), 
						new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false," (*)", false));
				
			}
		}

		private class ownResizer extends XX_BasicContainerResizeHelper {
			@Override
			public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
				
				Extent  oWidth = ownContainer.get_oExtWidth();
				Extent  oHeight = ownContainer.get_oExtHeight();
				
				if (oWidth.getUnits()==Extent.PX && oHeight.getUnits()==Extent.PX)
				{
					E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(ownContainer, bibALL.get_Vector(MyE2_ContainerEx.class.getName()), null);
					
					if (oSearch.get_vAllComponents().size()==1)
					{
						MyE2_ContainerEx oContainerEx = (MyE2_ContainerEx)oSearch.get_vAllComponents().get(0);
						
						oContainerEx.setHeight(new Extent(oHeight.getValue()-100));
					}
				}
				
			}
	}
	
	}


	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
//		if (this.label_with_geografic_adress_text!=null) {		this.label_with_geografic_adress_text.make_Look_Deleted(bIsDeleted);	}
//		if (this.label_with_juristic_adress_addon!=null) {		this.label_with_juristic_adress_addon.make_Look_Deleted(bIsDeleted);	}
		this.label_with_geografic_adress_text.make_Look_Deleted(bIsDeleted);	
		this.label_with_juristic_adress_addon.make_Look_Deleted(bIsDeleted);
	}


	@Override
	public void setForeColorActive(Color ForeColor) {
//		if (this.label_with_geografic_adress_text!=null) {	this.label_with_geografic_adress_text.setForeground(ForeColor);	}
//		if (this.label_with_juristic_adress_addon!=null) {	this.label_with_juristic_adress_addon.setForeground(ForeColor);	}
		this.label_with_geografic_adress_text.setForeground(ForeColor);	
		this.label_with_juristic_adress_addon.setForeground(ForeColor);	
	}



	@Override
	public void setFontActive(Font font) {
//		if (this.label_with_geografic_adress_text!=null) {	this.label_with_geografic_adress_text.setFont(Font);	}
		//der 2.label mit geografischem hinweis behält seinen font immer
		this.label_with_geografic_adress_text.setFont(bibFONT.equal_LineThrough_status(font, this.label_with_geografic_adress_text));	
		//der 2.label mit geografischem hinweis behält seinen font immer
	}



	@Override
	public Color get_Unmarked_ForeColor() {
		return this.label_with_geografic_adress_text.getForeground();
	}



	@Override
	public Font get_Unmarked_Font() {
//		if (this.label_with_geografic_adress_text!=null) {return this.label_with_geografic_adress_text.getFont();};
//		return new E2_FontPlain();
		return this.label_with_geografic_adress_text.getFont();

	}



	public FZ_addon_Button_to_SelectROW get__label_with_geografic_adress_text() {
		return label_with_geografic_adress_text;
	}



	public FZ_addon_Button_to_SelectROW get__label_with_juristic_adress_addon() {
		return label_with_juristic_adress_addon;
	}



	public ownInternal_INFO_Button get__infoButton() {
		return infoButton;
	}



	public ownListButton_open_AdressMask get__editButton() {
		return editButton;
	}


	
	
	
}
