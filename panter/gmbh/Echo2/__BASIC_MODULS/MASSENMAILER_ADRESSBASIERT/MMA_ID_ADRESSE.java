package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT;

public class MMA_ID_ADRESSE {

	public String   ID_ADRESSE = null;
	public boolean  bINCLUDE_BASISMAILS = false;
	public boolean  bINCLUDE_MitarbeiterMails = false;
	public boolean  bINCLUDE_LagerMails = false;
	
	
	/**
	 * 
	 * @param iD_ADRESSE
	 * @param bINCLUDE_BASISMAILS
	 * @param bINCLUDE_MitarbeiterMails
	 * @param bINCLUDE_LagerMails
	 */
	public MMA_ID_ADRESSE(String iD_ADRESSE, boolean bINCLUDE_BASISMAILS, boolean bINCLUDE_MitarbeiterMails, boolean bINCLUDE_LagerMails) {
		super();
		ID_ADRESSE = iD_ADRESSE;
		this.bINCLUDE_BASISMAILS = bINCLUDE_BASISMAILS;
		this.bINCLUDE_MitarbeiterMails = bINCLUDE_MitarbeiterMails;
		this.bINCLUDE_LagerMails = bINCLUDE_LagerMails;
	}
	
	
	
	
	
}
