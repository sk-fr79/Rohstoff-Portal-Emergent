package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_2_OriginalBeleg_exists_in_closed_Document;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_3_OriginalBeleg_Connected_to_EmailSend;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_4_eMail_Status;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_2;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_3;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_4;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * zwei buttons:
 *  der linke stellt den status der adresse an (Hat die Adresse eine original-verschick-email?)
 * 	der rechte zeigt, ob eine datei im archiv liegt und wenn ja, ob bereits verschickt
 * @author martin
 *
 */
public class RGOM_K_LIST_IconToShowSendStatusOrigMail extends MyE2_DB_PlaceHolder {
	
	private static int[] 			iBreite = {25,25,25};
	private E2_NavigationList  		naviList = null;               //die container-navilist
	
	public RGOM_K_LIST_IconToShowSendStatusOrigMail(SQLField osqlField, E2_NavigationList  n_aviList) throws myException {
		super(osqlField);
		this.EXT_DB().set_bIsSortable(false);
		this.naviList = n_aviList;
		MyE2_Grid_InLIST gl = new MyE2_Grid_InLIST(iBreite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		gl.add(new MyE2_Label(E2_ResourceIcon.get_RI("email.png")), E2_INSETS.I(1,1,1,1));
		gl.add(new RGOM_bt_SendeMail_header(naviList));
		
		this.EXT().set_oCompTitleInList(gl);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cID_VKOPF_RG, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {

		String 			cID_VKOPF_RG_UF = cID_VKOPF_RG.replace(".", "");
		RECORD_VKOPF_RG recVKOPF 		= new RECORD_VKOPF_RG(cID_VKOPF_RG_UF);

		this.removeAll();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.set_Spalten(RGOM_K_LIST_IconToShowSendStatusOrigMail.iBreite);
		
		//die aktionen nur ausfuehren, wenn die spalte sichtbar ist
		if (this.EXT().get_bIsVisibleInList()) {
		
			//jetzt fuellen
			MyE2IF__Component  oLab_Links = new MyE2_Label(RGOM__CONST.ICON__NIX);
			MyE2IF__Component  oLab_Mitte = new MyE2_Label(RGOM__CONST.ICON__NIX);
			
			//2021-02-26: falls rechnung/gutschriftsperre im adress-stamm, hier die firma beschaffen
			boolean originalMailSperren = false;
			String sperrText = null;
			try {
				Rec21_adresse firma = new Rec21_adresse()._fill_id(recVKOPF.get_ID_ADRESSE_lValue(null));
				if (recVKOPF.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
					originalMailSperren=firma.isRechnungenSperren();
					sperrText = new MyE2_String("Die Firma <",true, firma.get__FullNameAndAdress_name_anschrift(),false,
												"> besitzt eine eMailadresse zum Versand von Rechnungen. "
												+ " Der Versand ist allerdings gesperrt (Adress-Stamm, Rechnungen Sperrvermerk)!!",true).CTrans();
				} else {
					originalMailSperren=firma.isGutschriftenSperren();
					sperrText = new MyE2_String("Die Firma <",true, firma.get__FullNameAndAdress_name_anschrift(),false,
							"> besitzt eine eMailadresse zum Versand von Gutschriften. "
							+ " Der Versand ist allerdings gesperrt (Adress-Stamm, Gutschriften Sperrvermerk)!!",true).CTrans();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}
			//2021-08-19: weitere option: via Schalter das vorige verhalten konterkarieren 
			if (ENUM_MANDANT_DECISION.ERLAUBE_VERSAND_GESPERRTER_RECH_GUT_BELEGE.is_YES()) {
				originalMailSperren = false;
			}
			
			//2021-02-26: originalsperren ist definiert

			
			
			
			RECORD_ADRESSE_extend recADRESS = new RECORD_ADRESSE_extend(recVKOPF.get_UP_RECORD_ADRESSE_id_adresse());
			
			if (recADRESS.get_bAdresse_is_4_MailingOriginal_RG()) {
				if (originalMailSperren) {
					((MyE2_Label)oLab_Links).setIcon(RGOM__CONST.ICON__EMAIL_ADRESS_SUPRESSED);
					((MyE2_Label)oLab_Links).setToolTipText(sperrText);
					
				} else {
					((MyE2_Label)oLab_Links).setIcon(RGOM__CONST.ICON__EMAIL_ADRESS);
					((MyE2_Label)oLab_Links).setToolTipText(new MyE2_String("Die Firma <",true, recADRESS.get__FullNameAndAdress_Typ1(),false,"> besitzt eine eMailadresse zum Versand von Originalpapieren !",true).CTrans());
				}
			}

			MyE2_MessageVector oMVFehler = new MyE2_MessageVector();
			
			CHK_2_OriginalBeleg_exists_in_closed_Document chk_2_has_original = new CHK_2_OriginalBeleg_exists_in_closed_Document(
																							new CHK_RECORD_VKOPF_RG(recVKOPF));
			
			IF_STATUS status = chk_2_has_original.check_status(oMVFehler);
			
			//stufenweise pruefung auf fehler
			if (status==STATUS_CHK_2.HAS_ONE_ORIGINAL_ATTACHMENT) {
				CHK_3_OriginalBeleg_Connected_to_EmailSend check_EmailSendConnection = 
						new CHK_3_OriginalBeleg_Connected_to_EmailSend(chk_2_has_original.get_recAM().get(0));
				
				status = check_EmailSendConnection.check_status(oMVFehler);
				
				if (status == STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_MULTIPLE_MAIL_ERR) {
					oLab_Mitte = new RGOM_bt_ZeigeFehlerMeldungen(oMVFehler, recVKOPF);
				} else if (status == STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_NONE) {
					//standard-fall, wenn die adresse keinen eMail-Versand benutzt
					oLab_Mitte = new MyE2_Label(RGOM__CONST.ICON__NIX);
				} else {
					// STATUS_BEWERTUNG.ORIGINAL_IS_ATTATCHED_TO_SINGLE_MAIL
					CHK_4_eMail_Status check_Email = new CHK_4_eMail_Status(check_EmailSendConnection.get_RecordEmailSend());
					
					status = check_Email.check_status(oMVFehler);
					if (status == STATUS_CHK_4.MAIL_IS_CORRECT_UNSENT) {
						oLab_Mitte = new RGOM_bt_SendeMail(this);
					} else if (status == STATUS_CHK_4.MAIL_IS_CORRECT_SENT) {
						oLab_Mitte = new RGOM_bt_ZeigeSendestatus(recVKOPF);
						((MyE2_Button)oLab_Mitte).setToolTipText(new MyE2_String("Der Beleg besitzt ein Original-Dokument mit eMail-Versand (wurde bereits verschickt).").CTrans());
					} else {
						//fehler 
						oLab_Mitte = new RGOM_bt_ZeigeFehlerMeldungen(oMVFehler, recVKOPF);
					}
				}
			} else if (status == STATUS_CHK_2.HAS_NO_ORIGINAL_ATTACHMENT){
				oLab_Mitte = new MyE2_Label(RGOM__CONST.ICON__NIX);
			} else {
				oLab_Mitte = new RGOM_bt_ZeigeFehlerMeldungen(oMVFehler, recVKOPF);
			}
			this.add((Component)oLab_Links,E2_INSETS.I(1,0,0,1));
			this.add((Component)oLab_Mitte,E2_INSETS.I(1,0,0,1));
			
			this.add(new RGOM_bt_DownloadMailAttachments(recVKOPF),E2_INSETS.I(1,0,0,1));
			
		}
		
	}

	
	
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		RGOM_K_LIST_IconToShowSendStatusOrigMail oLabCopy = null;
		try
		{
			oLabCopy = new RGOM_K_LIST_IconToShowSendStatusOrigMail(this.EXT_DB().get_oSQLField(),this.naviList);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Icon:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		oLabCopy.setFont(this.getFont());
		
		oLabCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oLabCopy));
		oLabCopy.setLayoutData(this.getLayoutData());
		
		return oLabCopy;
	}

	
	
}
