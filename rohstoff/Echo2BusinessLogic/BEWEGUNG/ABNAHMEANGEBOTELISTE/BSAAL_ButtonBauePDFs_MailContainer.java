package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ButtonBauePDFs_MailContainer extends E2_MassMailer
{

	public BSAAL_ButtonBauePDFs_MailContainer(MailBlock_Vector  vmailZiele,Vector<MyE2IF__Component> vZusaetze) throws myException
	{
		super("AA_MAIL_BETREFF", "AA_MAIL_TEXTBLOCK",myCONST.VORGANGSART_ABNAHMEANGEBOT, new MailSecurityPolicyAllowNothing());
		this.baue_MailMaske(vmailZiele, bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""), true, true, false, 0, vZusaetze);
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(900), new Extent(650), new MyE2_String("Versende Abnahmeangebote"));
	}

	@Override
	public MailBlock build_MailBlock4Added_EmptyMails() throws myException
	{
		return null;
	}

	@Override
	public MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException
	{
		return null;
	}

	@Override
	public MailBlock build_MailBlock4Added_SearchedMails() throws myException
	{
		return null;
	}

	
	/*
	 * (non-Javadoc)
	 * @ueberschreiben der methode, um den split-pane mit den email-adresse an das fenster anzupassen
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(		Extent 					oWidth, 
													Extent 					oHeight,
													boolean 				bSplit,
													Extent    				oSplitPosition,
													MyE2_String 			oTitle) throws myException
	{
		super.CREATE_AND_SHOW_POPUPWINDOW(oWidth,oHeight,bSplit,oSplitPosition,oTitle);
		
		Extent ownHeight = this.get_oWindowPane().getHeight();
		int iOwnHeight = ownHeight.getValue();
		int iHeightEmailBlock = iOwnHeight-370;
		this.get_oContainerEx().setHeight(new Extent(iHeightEmailBlock));
//		this.oContainerEx.setBackground(Color.WHITE);
		//this.oContainerEx.setHeight(new Extent(100,Extent.PERCENT));
		
	}

	
}
