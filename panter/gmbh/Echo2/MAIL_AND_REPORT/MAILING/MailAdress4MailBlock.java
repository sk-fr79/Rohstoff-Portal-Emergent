/**
 * 
 */
package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextFieldLessContrast;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class MailAdress4MailBlock
{
	private MyE2_TextField 				oTextFieldWithMailAdress = 	new MyE2_TextField("",250,50);
	private MyE2_Label					oLabelSendeStatus = 		new MyE2_Label(MailBlock.ICON_LEER);
	private String 						cOriginalMailAdress = 		null;
	private MyE2_CheckBox 				oCheckBox_MailForSending = 	null;
	private MailBlock  					oMailBlock_This_BelongsTo = null;

	private Boolean 					AllowEditMailAdress = 		null;
	
	
	/**
	 * 2014-10-22: protokolliert, ob der letzte sendevorgang erfolgreich war 
	 */
	private boolean   					bLastSendingWasSuccessfull = false;
	
	/**
	 * 2014-10-23: speichern der letzten Absenderadresse
	 */
	private String  					cLastSendAdresse = null;
	
	/**
	 * 
	 * @param cMailAdress
	 * @param MailBlockThisBelongsTo
	 * @param allow_Edit_MailAdress 
	 * @param mailAdressInfo
	 * @throws myException
	 */
	public MailAdress4MailBlock(String cMailAdress,  MailBlock  MailBlockThisBelongsTo, Boolean allow_Edit_MailAdress) throws myException
	{
		super();
		
		if (cMailAdress==null)
			throw new myException("HelpClassForMailingBusinessPapers:RowWithMailAdress:Constructor: NULL mail-adress not allowed !");
		
		this.cOriginalMailAdress = 		 	cMailAdress;
		this.oMailBlock_This_BelongsTo = 	MailBlockThisBelongsTo;
		this.AllowEditMailAdress = 			allow_Edit_MailAdress;
		
		this.oTextFieldWithMailAdress.setText(cMailAdress);
		this.oTextFieldWithMailAdress.EXT().set_STYLE_FACTORY(new StyleFactory_TextFieldLessContrast());
		
		if (this.AllowEditMailAdress!=null)
		{
			this.oTextFieldWithMailAdress.setEnabled(this.AllowEditMailAdress.booleanValue());
		}
		else
		{
			if (MailBlockThisBelongsTo != null) this.oTextFieldWithMailAdress.setEnabled(this.oMailBlock_This_BelongsTo.get_oMailSecurityPolicy().bAllowChangeMailAdress);
		}
		this.oTextFieldWithMailAdress.setFont(new E2_FontPlain());
		this.oCheckBox_MailForSending = new MyE2_CheckBox();
		this.oCheckBox_MailForSending.setSelected(true);
		this.oCheckBox_MailForSending.add_oActionAgent(new ownActionAgentForAuswahlCheckBox());
	}

	
	
	public abstract String       	get_cID_ADRESSE_EMPFAENGER() throws myException;        // fuer adress-ID des Empfaengers
	public abstract String    		get_cAdressInfo() throws myException;             		// fuer daten wie z.B. Name, Ort
	public abstract MyString    	get_cComponentText() throws myException;   				// was ist auf der Componente zu sehen  
	
	public abstract Component  		get_ComponentForMailerList() throws myException;		//componente fuer die massmailer-liste			

	
	
	public void setLabelNeutral() 	{			this.oLabelSendeStatus.setIcon(MailBlock.ICON_LEER);		}
	public void setLabelOK() 		{			this.oLabelSendeStatus.setIcon(MailBlock.ICON_OK);		}
	public void setLabelError() 	{			this.oLabelSendeStatus.setIcon(MailBlock.ICON_ERROR);		}
	
	
	// falls eingabe erfolgt ist, wird hier wieder die originale mail-adresse hergestellt
	public void Fill_OriginalMail()
	{
		this.oTextFieldWithMailAdress.setText(this.cOriginalMailAdress);
	}
	
	
	public String          get_eMailAdresseZiel()
	{
		return S.NN(this.oTextFieldWithMailAdress.getText());
	}
	
	
	public MyE2_Label 		get_oLabelSendeStatus() 		{			return oLabelSendeStatus;				}
	public MyE2_TextField 	get_oTextFieldWithMailAdress() 	{			return oTextFieldWithMailAdress;		}
	public MyE2_CheckBox 	get_oCheckBox_MailForSending() 	{			return oCheckBox_MailForSending;		}
	
	
	private class ownActionAgentForAuswahlCheckBox extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			MailAdress4MailBlock oThis = MailAdress4MailBlock.this;
			
			if (oThis.oCheckBox_MailForSending.isSelected())
			{
				oThis.oTextFieldWithMailAdress.set_bEnabled_For_Edit(true);
				//falls generell das editieren verboten ist, das abschalten
				if (oThis.AllowEditMailAdress!=null)
				{
					oThis.oTextFieldWithMailAdress.setEnabled(oThis.AllowEditMailAdress.booleanValue());
				}
				else
				{
					oThis.oTextFieldWithMailAdress.setEnabled(oThis.oMailBlock_This_BelongsTo.get_oMailSecurityPolicy().bAllowChangeMailAdress);
				}

			}
			else
			{
				oThis.oTextFieldWithMailAdress.set_bEnabled_For_Edit(false);
			}
		}
	}


	public void set_oMailBlock_This_BelongsTo(MailBlock mailBlock_This_BelongsTo)
	{
		this.oMailBlock_This_BelongsTo = mailBlock_This_BelongsTo;
		if (this.AllowEditMailAdress!=null)
		{
			this.oTextFieldWithMailAdress.setEnabled(this.AllowEditMailAdress.booleanValue());
		}
		else
		{
			this.oTextFieldWithMailAdress.setEnabled(this.oMailBlock_This_BelongsTo.get_oMailSecurityPolicy().bAllowChangeMailAdress);
		}

	}



//	/**
//	 * 2014-10-22: neuer platz um weitere SQL-Statements unterzubringen, die bezug zu den Mailadressen haben
//	 * @return
//	 */
//	public Vector<String> get_vSQL_Statements_After_Sending() {
//		return vSQL_Statements_After_Sending;
//	}


	/**
	 * 2014-10-22: protokolliert, ob der letzte sendevorgang erfolgreich war 
	 */
	public boolean get_bLastSendingWasSuccessfull() {
		return bLastSendingWasSuccessfull;
	}


	/**
	 * 2014-10-22: protokolliert, ob der letzte sendevorgang erfolgreich war 
	 */
	public void set_bLastSendingWasSuccessfull(boolean bLastSendingWasSuccessfull) {
		this.bLastSendingWasSuccessfull = bLastSendingWasSuccessfull;
	}



	public String get_cLastSendAdresse() {
		return cLastSendAdresse;
	}



	public void set_cLastSendAdresse(String cLastSendAdresse) {
		this.cLastSendAdresse = cLastSendAdresse;
	}
	
}