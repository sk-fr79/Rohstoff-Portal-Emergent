/*
 * Created on 27.01.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.AgentsAndValidators;


import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;


// allgemeine validator-klasse, die jedem button zugeordnet wird,
// der sowohl den modul als auch den button-kenn-string erhält
// und der als globaler validator auftritt
public class E2_ButtonAUTHValidator extends XX_ActionValidator
{
	
	private String	    cAUTHMODUL  = "";
	private String	    cAUTHBUTTON = "";

	//20171124: martin
	private boolean bSuperVisorDarfAlles = true;			//standard

	
	/**
	 * 20171124: weitere moeglichkeit funktionen auch fuer supervisor zu sperren
	 * @param caUTHMODUL
	 * @param caUTHBUTTON
	 * @param superVisorDarfAlles
	 */
	public E2_ButtonAUTHValidator(	String	 	caUTHMODUL,	String	 	caUTHBUTTON, boolean superVisorDarfAlles) {
		this(caUTHMODUL,caUTHBUTTON);
		this.bSuperVisorDarfAlles = superVisorDarfAlles;
	}

	public E2_ButtonAUTHValidator(	String	 	caUTHMODUL,
								 	String	 	caUTHBUTTON	)
	{
		this.cAUTHBUTTON = bibALL.null2leer(caUTHBUTTON);
		this.cAUTHMODUL  = bibALL.null2leer(caUTHMODUL);
	}



	public MyE2_MessageVector isValid(Component oCompWhichIsValidated)	{
		
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		//2015-05-20: auslagerung der datenbank-ops in eigene klasse
		oMV.add_MESSAGE(new E2_ButtonAUTHValidator_Helper(this.cAUTHMODUL,this.cAUTHBUTTON,this.bSuperVisorDarfAlles).get_oMV());
		
		return oMV;
	}



	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		return new MyE2_MessageVector();
	}

	
	public MyE2_MessageVector isValid() {
		return this.isValid((Component)null);
	}
	
}
