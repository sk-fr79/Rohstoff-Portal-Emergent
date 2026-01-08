package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Vector;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN extends __SYSTEM_MESSAGE_GENERATOR
{

	//2012-05-16: umstellung auf Vector statt einfacher typuebergabe
	private VectorSingle vVORGANG_TYP = new VectorSingle();
	
	public __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(String cID_ADRESSE, String VORGANG_TYP) throws myException
	{
		super(cID_ADRESSE);
		if (S.isFull(VORGANG_TYP))
		{
			this.vVORGANG_TYP.add(VORGANG_TYP);
		}
	}

	public __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(String cID_ADRESSE, Vector<String> vVORGANG_TYPEN) throws myException
	{
		super(cID_ADRESSE);
		if (vVORGANG_TYPEN!=null)
		{
			this.vVORGANG_TYP.addAllOnlyNotEmpty(vVORGANG_TYPEN);
		}
	}

	
	
	@Override
	public Vector<String> get_VectorSammleMeldungsTypen() throws myException
	{
		if (this.vVORGANG_TYP.size()==0)
		{
			throw new myException(this,"Achtung! Programm-Modul kann nicht bestimmt werden, Meldungen können nicht geprüft werden !!!");
		}
		
		Vector<String> vTypSelection = new Vector<String>();
		
		if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_ABNAHMEANGEBOT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ANGEBOT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ABGEBOT_EK_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_EINKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_ANGEBOT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ANGEBOT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ANGEBOT_VK_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_VERKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_EK));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_EINKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_VK));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_VERKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_RECHNUNG))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_RECH_GUT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_RECHNUNG_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_VERKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_RECH_GUT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_GUTSCHRIFT_INFO));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_EINKAUF));
		}
		else if (this.vVORGANG_TYP.contains(myCONST.VORGANGSART_TRANSPORT))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_TPA_INFO));
		}
		else
		{
			throw new myException(this,"Vorgangtyp: "+bibALL.Concatenate(this.vVORGANG_TYP,",","")+" is not allowed !!");
		}

		
		
		return vTypSelection;
	}

}
