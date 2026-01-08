package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;


/**
 * spezielle Vector-klasse, die dafuer sorgt, dass jeder stationstyp nur einmal aufgenommen wird
 * @author martin
 *
 */
public class HD_Stationen extends Vector<HD_Station>
{

	private boolean bDoppelteErlaubt = false;
	
	//2014-01-29: MessageVector als Sammler der Prueffehler beim Aufbau der Stationen
	private MyE2_MessageVector  oMV_SammlerVonFehlern = new MyE2_MessageVector();
	
	

	/**
	 * 
	 * @param DoppelteErlaubt
	 */
	public HD_Stationen(boolean DoppelteErlaubt)
	{
		super();
		this.bDoppelteErlaubt = DoppelteErlaubt;
	}


	public boolean add(HD_Station oStat)
	{
		if (oStat == null)
		{
			return false;
		}
		
		if (this.bDoppelteErlaubt)
		{
			this.oMV_SammlerVonFehlern.add_MESSAGE(oStat.get_oMV_FehlerHandelMoeglich());
			return super.add(oStat);
		}
		else
		{
			if (this.contains(oStat))
			{
				return false;
			}
			else
			{
				this.oMV_SammlerVonFehlern.add_MESSAGE(oStat.get_oMV_FehlerHandelMoeglich());
				return super.add(oStat);
			}
		}
	}
	
	
	public boolean addAll(HD_Stationen vCollection)
	{
		for (HD_Station oStat: vCollection)
		{
			this.add(oStat);
		}
		return true;
	}
	
	
	public HD_FehlerBerichte get_vFehlerVect()
	{
		HD_FehlerBerichte vRueck = new HD_FehlerBerichte();
		
		
		for (HD_Station  oStat: this)
		{
			vRueck.addAll(oStat.get_vFehlerVector());
		}
		
		
		return vRueck;
	}
	
	
	/**
	 * 
	 * @param bDoppeltErlaubt
	 * @param bMenge_0_Erlaubt
	 * @return
	 */
	public HD_Stationen getHD_StationenEK(boolean bDoppeltErlaubt) {
		HD_Stationen vRueck = new HD_Stationen(bDoppeltErlaubt);
		
		for (HD_Station oStat: this) {
			if (oStat.get_bEK()) {
				vRueck.add(oStat);
			}
		}
		
		return vRueck;
	}
	
	
	/**
	 * 
	 * @param bDoppeltErlaubt
	 * @param bMenge_0_Erlaubt
	 * @return
	 */
	public HD_Stationen getHD_StationenVK(boolean bDoppeltErlaubt) 	{
		HD_Stationen vRueck = new HD_Stationen(bDoppeltErlaubt);
		
		for (HD_Station oStat: this)	{
			if (!oStat.get_bEK())	{
				vRueck.add(oStat);
			}
		}
		
		return vRueck;
	}

	
	//2014-01-29
	public MyE2_MessageVector get_oMV_SammlerVonFehlern() {
		return oMV_SammlerVonFehlern;
	}


	
}
