package rohstoff.utils.MAILCOLLECTORS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.XXX_MailBlockAdress_FILLER;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class MAILFillerTPA extends XXX_MailBlockAdress_FILLER
{
	
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.Reporting.XXX_MailAdress4MailBlockGenerator#fill_MAILAdresses_to_MailBLOCK(panter.gmbh.Echo2.MAILING.MailBlock, panter.gmbh.Echo2.Reporting.E2_JasperHASH)
	 */
	@Override
	public void fill_MAILAdresses_to_MailBLOCK(MailBlock oMb, E2_JasperHASH jasperHash,boolean bAddEmptyMailAdressWhen_no_MailAdressFound) throws myException
	{
		
		if (jasperHash.get_vID_ADRESSE_FOR_MailLoad().size()==0)
		{
			throw new myException(this,"Error: jasperHash doesnt has an ID_ADRESSE-Value");
		}

		for (String cID_ADRESS: jasperHash.get_vID_ADRESSE_FOR_MailLoad())
		{
			oMb.add_VMailAdress4MailBlock(
					new MailAdressSearcher(	cID_ADRESS,
											true,
											true,
											false,
											myCONST.VORGANGSART_TRANSPORT,
											new MyE2_String(myCONST.VORGANGSART_TRANSPORT_FOR_USER),
											null, bAddEmptyMailAdressWhen_no_MailAdressFound, new Boolean(true), new Boolean(true))
					);
		}
	
	}

}
