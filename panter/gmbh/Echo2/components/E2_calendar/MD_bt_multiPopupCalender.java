package panter.gmbh.Echo2.components.E2_calendar;

import static panter.gmbh.Echo2.components.MyE2_Grid.LAYOUT_CENTER_TOP;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MultiValueSelector.E2IF_MultiValueSelectorContainer;
import panter.gmbh.Echo2.components.MultiValueSelector.IF_InBox;
import panter.gmbh.Echo2.components.MultiValueSelector.MultiValueSelectorContainer;
import panter.gmbh.Echo2.components.MultiValueSelector.MultiValueSelector_SaveKeySizeofPopup;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MD_bt_multiPopupCalender extends MyE2_Button  implements E2IF_MultiValueSelectorContainer{

	private LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN>  	hm_date_fields_from_mask = new LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN>();
	private LinkedHashMap<String,Component>  						hm_titleText = new LinkedHashMap<String,Component>();
	private LinkedHashMap<String,MD_CalenderInPopup>  				hm_correspondingCalendersInPopup = new LinkedHashMap<String,MD_CalenderInPopup>();
	
	private MultiValueSelector_SaveKeySizeofPopup                   saveSizeKey4popup = null;
	/**
	 * lambda-ausdruck um die datums-controlls im popup besser anzuordnen
	 */
	private IF_InBox  ib = (comp)-> { 
					return comp.in_border(	new E2_ColorDDDark(),
											MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(1,1,1,1)), 
											null, 
											new Extent(220)); };
	
	public MD_bt_multiPopupCalender(	LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN> 	p_hm_calling_date_fields,
										LinkedHashMap<String,Component>  						p_hm_titleText,
										MultiValueSelector_SaveKeySizeofPopup               	p_saveSizeKey4popup
										) throws myException {
		this(E2_ResourceIcon.get_RI("calendar_multi.png"),E2_ResourceIcon.get_RI("calendar_multi__.png"),p_hm_calling_date_fields,p_hm_titleText,p_saveSizeKey4popup);
	}


	
	public MD_bt_multiPopupCalender(	ImageReference oImg, 
										ImageReference oimgDisabled, 
										LinkedHashMap<String,MyE2_DB_TextField_DatePOPUP_OWN> p_hm_calling_date_fields,
										LinkedHashMap<String,Component>  					p_hm_titleText,
										MultiValueSelector_SaveKeySizeofPopup               p_saveSizeKey4popup
										) throws myException {
		super(oImg, oimgDisabled);
		this.hm_date_fields_from_mask.putAll(p_hm_calling_date_fields);
		this.hm_titleText.putAll(p_hm_titleText);
		
		this.saveSizeKey4popup = p_saveSizeKey4popup;
		
		for (String key: this.hm_date_fields_from_mask.keySet()) {
			this.hm_correspondingCalendersInPopup.put(key, new MD_CalenderInPopup());
		}
		
		for (Component c: p_hm_titleText.values()) {
			c.setLayoutData(LAYOUT_CENTER_TOP(E2_INSETS.I(2,0,0,0),new E2_ColorDDark(),null));
		}
		
		for (Component c: hm_correspondingCalendersInPopup.values()) {
			c.setLayoutData(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)));
		}
		
		this.add_oActionAgent(new ownActionAgent());
	}

	@Override
	public void refreshParentComponents() throws myException {
		for (String key: this.hm_date_fields_from_mask.keySet()) {
			String c_date = (String)this.hm_correspondingCalendersInPopup.get(key).getValue();
			if (S.isEmpty(c_date)) {
				c_date = "";
			} else {
				MyDate date = new MyDate(c_date);
				if (date.get_bOK()) {
					c_date = date.get_cDateStandardFormat();
				} else {
					c_date = "";
				}
			}
			this.hm_date_fields_from_mask.get(key).get_oTextField().setText(c_date);
		}
		
	}

	@Override
	public void getValueFromParentComponents() throws myException {
		for (String key: this.hm_date_fields_from_mask.keySet()) {
			hm_correspondingCalendersInPopup.get(key).setValue(S.NN(this.hm_date_fields_from_mask.get(key).get_oTextField().getText()));
		}
	}

	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MD_bt_multiPopupCalender 		oThis = 	MD_bt_multiPopupCalender.this;
			MultiValueSelectorContainer  	container = new MultiValueSelectorContainer(oThis, oThis.saveSizeKey4popup);
			container.get_buttonSave().setText(new MyE2_String("Datumswerte übernehmen").CTrans());
			container.get_buttonSave().setFont(new E2_FontBold());

			container.set_inBox(oThis.ib);
			
			for (String key: oThis.hm_correspondingCalendersInPopup.keySet()) {
				container.addComponent(key, oThis.hm_titleText.get(key), oThis.hm_correspondingCalendersInPopup.get(key)) ;
			}
			
			container.setGridWidth(oThis.hm_correspondingCalendersInPopup.size());
			int i_width = 200*oThis.hm_correspondingCalendersInPopup.size();
			container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(i_width), new Extent(400), new MyE2_String("Bitte geben Sie die Daten ein ..."));
		}
	}
	
	
	
}
