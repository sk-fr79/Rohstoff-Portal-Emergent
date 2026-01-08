package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class BS_MaskSetter_POS_TYP
{

	/*
	 * hilfsklasse fuer das maskensetting bei der auswahl der verschiedenen positionstypen
	 * (ARTIKEL,ZUSATZTEXT,ALTERNATIVE)
	 */
	public BS_MaskSetter_POS_TYP(E2_ComponentMAP oComponentMAP, String cPOS_TYP, Vector<String> vZusatzFelder) throws myException
	{
		// aktivieren (fuer das setting vor der maske)
		Vector<String> vF = new Vector<String>();
		vF.add("EINHEITKURZ");
		vF.add("EINHEIT_PREIS_KURZ");
		vF.add("MENGENDIVISOR");
		vF.add("LIEFERBEDINGUNGEN");
		vF.add("ZAHLUNGSBEDINGUNGEN");
		vF.add("ID_ZAHLUNGSBEDINGUNGEN");
		vF.add("ANZAHL");
		vF.add("EINZELPREIS");
		vF.add("STEUERSATZ");
		vF.add("ANR1");
		vF.add("ANR2");
		vF.add("ANZAHL");
		vF.add("ID_ARTIKEL_BEZ");
		vF.add("ID_ARTIKEL");
		
		vF.addAll(vZusatzFelder);
		
		// standard
		oComponentMAP.set_bEnabled_For_Edit(true,true,vF,true,true);
		
		
		// der gesamtpreis ist immer gesperrt
		oComponentMAP.set_bEnabled_For_Edit(false,true,bibALL.get_Vector("GESAMTPREIS"),true,true);

		/*
		 * falls die action von einem klick auf den BS_SelectField_POSITIONSTYP - Selektor kommt,
		 * dann muessen im fall des Artikel-Typs die Default-werte gesetzt werden
		 */
		if (bibE2.get_LAST_ACTIONEVENT().getSource() instanceof BS_SelectField_POSITIONSTYP)
		{
			if (bibALL.null2leer(cPOS_TYP).equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			{
				oComponentMAP.prepare_ContentForNew(true,vF,true,true);     // die felder ins default setzen
				// der mengendivisor muss separat behandelt werden, er darf nicht null werden
				oComponentMAP.prepare_ContentForNew(true,bibALL.get_Vector("MENGENDIVISOR"),true,true);
			}
		}
		
		
		if (bibALL.null2leer(cPOS_TYP).equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT))
		{
			oComponentMAP.set_bEnabled_For_Edit(false,true,vF,true,true);
			oComponentMAP.prepare_ContentForNew(false,vF,true,true);     // die felder leeren
			
			// der mengendivisor muss separat behandelt werden, er darf nicht null werden
			oComponentMAP.prepare_ContentForNew(true,bibALL.get_Vector("MENGENDIVISOR"),true,true);
		}
		else if (bibALL.null2leer(cPOS_TYP).equals(myCONST.VG_POSITION_TYP_ALTERNATIV))
		{
			oComponentMAP.prepare_ContentForNew(false,bibALL.get_Vector("GESAMTPREIS"),true,true);
		}
		
		
	}
	

}
