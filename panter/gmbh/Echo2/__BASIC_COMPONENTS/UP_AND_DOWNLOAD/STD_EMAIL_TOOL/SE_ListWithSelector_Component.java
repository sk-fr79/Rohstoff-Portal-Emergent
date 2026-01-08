package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.myCONST_ENUM.FAXNUMMER_DEF;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class SE_ListWithSelector_Component extends MyE2_Grid  {

	private HashMap<String, MyE2_CheckBox> chkBoxList;

	private boolean isChecked = false;
	private MyE2_Button[] parent;
	private processtype typ;

	private boolean multipleSelectionAllowed = true;

	private E2_NavigationList navilist;

	private boolean only_five_contact = true;

	private int widthInPx = 200;
	private int heightInPx = 75;

	public SE_ListWithSelector_Component(MyE2_Button bt_print, MyE2_Button bt_mail, MyE2_Button bt_preview, processtype e_typ) {
		super(1);
		this.__setBasic(e_typ);
		this.parent = new MyE2_Button[] {  bt_print, bt_mail,bt_preview};
		this.chkBoxList.clear();

	}

	public SE_ListWithSelector_Component(E2_NavigationList c_list, MyE2_Button bt_print, MyE2_Button bt_mail, MyE2_Button bt_preview, processtype e_typ) throws myException {	
		super(1);
		this.__setBasic(e_typ);
		this.navilist = c_list;

//		preloadContactList(this.navilist.get_vSelectedIDs_Unformated());

		this.parent = new MyE2_Button[] {bt_print, bt_mail,bt_preview};
	}

	private void __setBasic(processtype e_typ){		
		chkBoxList = new HashMap<>();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setLayoutData(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));

		this.typ = e_typ;
		if(typ== processtype.PRINT){
			multipleSelectionAllowed = false;
		}else if (typ == processtype.MAIL){
			multipleSelectionAllowed = true;
		}

	}


	public void preloadContactList(
			String adresseId, 
			MAILDEF maildef, 
			boolean basisMailsEinbeziehen,
			boolean mitarbeiterEinbeziehen, 
			boolean	lieferadressenEinbeziehen

			) throws myException{
		chkBoxList.clear();
		this.removeAll();
		VectorSingle contactList = new VectorSingle();

		if(this.typ == processtype.PRINT){
			SE_FaxNummerSearcher faxNumberResearch = new SE_FaxNummerSearcher(adresseId);
			if((faxNumberResearch.get_vFax_number_found().size()>4) ){
				for(int i = 0;i<5 ;i++){
					contactList.add(faxNumberResearch.get_vFax_number_found().get(i));
				}
			}else contactList.addAll(faxNumberResearch.get_vFax_number_found());
			setKontakts(contactList);
		}else if(this.typ == processtype.MAIL){
			if(maildef == MAILDEF.EMAIL_ALLTYPES){
				for(MAILDEF undef : MAILDEF.values()){
					searchMails(adresseId, undef, contactList, basisMailsEinbeziehen, mitarbeiterEinbeziehen, lieferadressenEinbeziehen);
				}
			}else{
				searchMails(adresseId, maildef, contactList, basisMailsEinbeziehen, mitarbeiterEinbeziehen, lieferadressenEinbeziehen);
			}

			ArrayList<String> mails= new ArrayList<>();
			if(only_five_contact){
				for(int i=0;i<5;i++){
					if(i<contactList.size()){
						mails.add(contactList.get(i));
					}

				}
				setKontakts(mails);
			}else{ 
				mails.addAll(contactList);
				setKontakts_with_scrollBar(mails);
			}

		}
	}

	private void searchMails(String adresseId, MAILDEF maildef, VectorSingle contactList,boolean basisMailsEinbeziehen,
			boolean mitarbeiterEinbeziehen, 
			boolean	lieferadressenEinbeziehen) throws myException {
		SE_MailAdressSearcher mailSearcher = new SE_MailAdressSearcher(new RECORD_ADRESSE(adresseId), maildef, basisMailsEinbeziehen, mitarbeiterEinbeziehen, lieferadressenEinbeziehen);
		if(mailSearcher.get_v_MailAdressesFound().size()>4){
			if(only_five_contact){
				for(int i=0; i < 5 ; i++){
					contactList.add(mailSearcher.get_v_MailAdressesFound().get(i));
				}
			}else{
				contactList.addAll(mailSearcher.get_v_MailAdressesFound());
			}
		}else contactList.addAll(mailSearcher.get_v_MailAdressesFound());
	}

	public void preloadContactList(Vector<String> v_selectedIds) throws myException{
		chkBoxList.clear();
		this.removeAll();
		ArrayList<String> contactList = new ArrayList<String>();
		Vector<String> selectedIds = v_selectedIds;

		MyE2_MessageVector omv = new MyE2_MessageVector();

		if(selectedIds.size()>0){	
			String adresseId = new RECORD_FIBU(selectedIds.get(0)).get_ID_ADRESSE_cUF();

			if(this.typ == processtype.PRINT){
				SE_FaxNummerSearcher faxNumberResearch = new SE_FaxNummerSearcher(adresseId);
				if(faxNumberResearch.get_vFax_number_found().size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Faxnummer")));
				}else if(faxNumberResearch.get_vFax_number_found().size()>4){
					for(int i = 0;i<5 ;i++){
						contactList.add(faxNumberResearch.get_vFax_number_found().get(i));
					}
				}else contactList.addAll(faxNumberResearch.get_vFax_number_found());

			}else if(this.typ == processtype.MAIL){
				SE_MailAdressSearcher mailSearcher = new SE_MailAdressSearcher(new RECORD_ADRESSE(adresseId), MAILDEF.EMAIL_FIBU, true, true, false);
				if(mailSearcher.get_v_MailAdressesFound().size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Mail")));
				}else if(mailSearcher.get_v_MailAdressesFound().size()>4){
					for(int i=0; i < 5 ; i++){
						contactList.add(mailSearcher.get_v_MailAdressesFound().get(i));
					}
				}else contactList.addAll(mailSearcher.get_v_MailAdressesFound());
			}
			bibMSG.add_MESSAGE(omv);
			setKontakts(contactList);
		}

	}

	
	public void preloadContactList(Vector<String> v_selectedIds, FAXNUMMER_DEF def, boolean bMitarbeiter, boolean showMitarbeiterName) throws myException{
		chkBoxList.clear();
		this.removeAll();
		ArrayList<String> contactList = new ArrayList<String>();
		Vector<String> selectedIds = v_selectedIds;

		MyE2_MessageVector omv = new MyE2_MessageVector();

		if(selectedIds.size()>0){	
			String adresseId = new RECORD_FIBU(selectedIds.get(0)).get_ID_ADRESSE_cUF();

			if(this.typ == processtype.PRINT){
				SE_FaxNummerSearcher_Ext faxNumberResearch = new SE_FaxNummerSearcher_Ext(adresseId, def, bMitarbeiter, showMitarbeiterName);
				if(faxNumberResearch.get_vFax_number_found().size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Faxnummer")));
				}else if(faxNumberResearch.get_vFax_number_found().size()>4){
					for(int i = 0;i<5 ;i++){
						contactList.add(faxNumberResearch.get_vFax_number_found().get(i));
					}
				}else contactList.addAll(faxNumberResearch.get_vFax_number_found());

			}else if(this.typ == processtype.MAIL){
				SE_MailAdressSearcher mailSearcher = new SE_MailAdressSearcher(new RECORD_ADRESSE(adresseId), MAILDEF.EMAIL_FIBU, true, true, false);
				if(mailSearcher.get_v_MailAdressesFound().size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Mail")));
				}else if(mailSearcher.get_v_MailAdressesFound().size()>4){
					for(int i=0; i < 5 ; i++){
						contactList.add(mailSearcher.get_v_MailAdressesFound().get(i));
					}
				}else contactList.addAll(mailSearcher.get_v_MailAdressesFound());
			}
			bibMSG.add_MESSAGE(omv);
			setKontakts(contactList);
		}

	}
	
	public void preloadContactList(Vector<String> v_selectedIds, MAILDEF... mailDef) throws myException{
		chkBoxList.clear();
		this.removeAll();
		ArrayList<String> contactList = new ArrayList<String>();
		Vector<String> selectedIds = v_selectedIds;

		MyE2_MessageVector omv = new MyE2_MessageVector();

		if(selectedIds.size()>0){	
			String adresseId = new RECORD_FIBU(selectedIds.get(0)).get_ID_ADRESSE_cUF();

			if(this.typ == processtype.PRINT){
				SE_FaxNummerSearcher faxNumberResearch = new SE_FaxNummerSearcher(adresseId);
				if(faxNumberResearch.get_vFax_number_found().size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Faxnummer")));
				}else if(faxNumberResearch.get_vFax_number_found().size()>4){
					for(int i = 0;i<5 ;i++){
						contactList.add(faxNumberResearch.get_vFax_number_found().get(i));
					}
				}else contactList.addAll(faxNumberResearch.get_vFax_number_found());

			}else if(this.typ == processtype.MAIL){
				VectorSingle mailSearcher = new VectorSingle();

				for(MAILDEF def : mailDef){
					SE_MailAdressSearcher searchResult = new SE_MailAdressSearcher(new RECORD_ADRESSE(adresseId), def, true, true, false);
					mailSearcher.addAll(searchResult.get_v_MailAdressesFound());
				}

				if(mailSearcher.size()==0){
					omv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung: kein Empfänger Mail")));
				}else {
					ArrayList<String> mails= new ArrayList<>();
					if(only_five_contact){
						for(int i=0;i<5;i++){
							if(i<contactList.size()){
								mails.add(contactList.get(i));
							}

						}
						setKontakts(mails);
					}else{ 
						mails.addAll(mailSearcher);
						setKontakts_with_scrollBar(mails);
					}
				}
			}
		}
	}
	
	
	public SE_ListWithSelector_Component setKontakts_with_scrollBar(List<String> kontaktLists){
		chkBoxList.clear();
		MyE2_Grid subGrid=new MyE2_Grid(1);
		subGrid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		for(String kontakt: kontaktLists){
			MyE2_CheckBox chkBox = new MyE2_CheckBox();
			chkBox.setText(kontakt);
			chkBox.setSelected(false);
			if(isMultipleSelectionAllowed()){
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_multiple());
			}else{
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_single());
			}
			chkBoxList.put(kontakt, chkBox);
			subGrid.add(chkBox);
		}

		MyE2_ContainerEx  container = new MyE2_ContainerEx();
		container.setHeight(new Extent(getHeightInPx()));
		container.setWidth(new Extent(this.getWidthInPx()));
		container.add(subGrid);
		this.add(container);
		return this;
	}

	public SE_ListWithSelector_Component setKontakts(List<String> kontaktLists){
		chkBoxList.clear();

		for(String kontakt: kontaktLists){
			MyE2_CheckBox chkBox = new MyE2_CheckBox();
			chkBox.setText(kontakt);
			chkBox.setSelected(false);
			if(isMultipleSelectionAllowed()){
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_multiple());
			}else{
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_single());
			}
			chkBoxList.put(kontakt, chkBox);
			this.add(chkBox);
		}

		return this;
	}

	public ArrayList<String> getSelectedKontakt(){
		ArrayList<String> selectedKontaktList = new ArrayList<>();
		for(String kontakt: chkBoxList.keySet()){
			if(chkBoxList.get(kontakt).isSelected()){
				selectedKontaktList.add(kontakt);
			}
		}
		return selectedKontaktList;
	}	


	public boolean updateCheckedContact(List<String> ckeckedContactList){
		boolean bCheckedContacts =false;
		for(String checkedContact :  ckeckedContactList){
			if(! (S.isEmpty(checkedContact) ) ){
				MyE2_CheckBox chk = chkBoxList.get(checkedContact);
				if(!(chk== null)){
					chk.setSelected(true);
					bCheckedContacts = true;
				}

			}
		}
		return bCheckedContacts;
	}

	public String getFaxNummer(){
		if(this.typ==processtype.PRINT){
			for(String kontakt: chkBoxList.keySet()){
				if(chkBoxList.get(kontakt).isSelected()){
					return kontakt;
				}
			}
		}
		return "";
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isMultipleSelectionAllowed() {
		return multipleSelectionAllowed;
	}

	public void setMultipleSelectionAllowed(boolean multipleSelectionAllowed) {
		this.multipleSelectionAllowed = multipleSelectionAllowed;
	}

	public boolean isOnly_five_contact() {
		return only_five_contact;
	}

	public void setOnly_five_contact(boolean only_five_contact) {
		this.only_five_contact = only_five_contact;
	}

	private void updateButtonEnabledStatus() throws myException{	

		if(typ==processtype.MAIL){
			if(!(parent[1]==null)){
				parent[1].set_bEnabled_For_Edit(isChecked);
			}
		}

		if(typ == processtype.PRINT){
			if(!(parent[0]==null)){
				parent[0].set_bEnabled_For_Edit(isChecked);
			}
		}

		if(!(parent[2]==null)){
			boolean temp = false;
			if(!(parent[0] == null)){
				temp = parent[0].isEnabled();
			}
			if(!(parent[1] == null )){
				temp = temp|| parent[1].isEnabled();
			}
			parent[2].set_bEnabled_For_Edit(temp);

		}
	}

	public int getWidthInPx() {
		return this.widthInPx;
	}

	public void setWidthInPx(int width) {
		this.widthInPx = width;
	}

	public int getHeightInPx() {
		return heightInPx;
	}

	public void setHeightInPx(int heightInPx) {
		this.heightInPx = heightInPx;
	}

	private class ownCheckboxActionAgent_multiple extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			isChecked = false;

			for(String key : chkBoxList.keySet()){

				if(chkBoxList.get(key).isSelected()){
					setChecked(true);
					break;
				}
			}
			updateButtonEnabledStatus();
		}
	}

	private class ownCheckboxActionAgent_single extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_CheckBox oCB = (MyE2_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
			isChecked = false;

			for (String key : chkBoxList.keySet())
			{
				MyE2_CheckBox oCB1 = (MyE2_CheckBox)chkBoxList.get(key);

				if (oCB!=oCB1)
				{
					oCB1.setSelected(false);

				}
				else
				{
					setChecked(oCB.isSelected());

				}

				updateButtonEnabledStatus();

			}


		}

	}

	public void setFixedSize(int width, int height) {
		this.heightInPx = height;
		this.widthInPx = width-20;
	}


}

