package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_Select_Lieferbedingungen extends MyE2_DB_SelectField {

	public FS_Component_MASK_Select_Lieferbedingungen(SQLField osqlField) throws myException {
		super(osqlField);
		
		//E2_DropDownSettings ddLiefBed = new E2_DropDownSettings( "JT_LIEFERBEDINGUNGEN", "KURZBEZEICHNUNG", "ID_LIEFERBEDINGUNGEN", "IST_STANDARD", true);
		String cQueryActiv = "SELECT "+		_DB.LIEFERBEDINGUNGEN$KURZBEZEICHNUNG+","+
											_DB.LIEFERBEDINGUNGEN$ID_LIEFERBEDINGUNGEN+","+
											_DB.LIEFERBEDINGUNGEN$IST_STANDARD+" FROM "+bibE2.cTO()+"."+_DB.LIEFERBEDINGUNGEN+" WHERE "+
											"NVL("+_DB.LIEFERBEDINGUNGEN$AKTIV+",'N')='Y' ORDER BY "+_DB.LIEFERBEDINGUNGEN$KURZBEZEICHNUNG;

		String cQueryInActiv = "SELECT "+	_DB.LIEFERBEDINGUNGEN$KURZBEZEICHNUNG+","+
											_DB.LIEFERBEDINGUNGEN$ID_LIEFERBEDINGUNGEN+","+
											_DB.LIEFERBEDINGUNGEN$IST_STANDARD+" FROM "+bibE2.cTO()+"."+_DB.LIEFERBEDINGUNGEN+" WHERE "+
											"NVL("+_DB.LIEFERBEDINGUNGEN$AKTIV+",'N')='N' ORDER BY "+_DB.LIEFERBEDINGUNGEN$KURZBEZEICHNUNG;
		
		
				
		E2_DropDownSettings ddLiefBed = new E2_DropDownSettings(cQueryActiv,cQueryInActiv, true);

		this.set_ListenInhalt(ddLiefBed.getDD(), false);
		if (ddLiefBed.get_DD_Shadow()!=null && ddLiefBed.get_DD_Shadow().length>0) {
			this.set_odataToViewShadow(new dataToView(ddLiefBed.get_DD_Shadow(),false,bibE2.get_CurrSession()));
		}
	}

}
