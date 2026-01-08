package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.KOSTEN_LIEFERBED_ADR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class FSK_ListSelector extends E2_ListSelectorContainer

{
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private MyE2_SelectField   				oSEL_STARTADRESSE = null;
	private MyE2_SelectField   				oSEL_ZIELADRESSE = null;
	private MyE2_SelectField   				oSEL_SORTE = null;
	

	public FSK_ListSelector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);
		
		this.oSEL_STARTADRESSE = 	new MyE2_SelectField(new Extent(120));
		this.oSEL_ZIELADRESSE = 	new MyE2_SelectField(new Extent(120));
		this.oSEL_SORTE = 			new MyE2_SelectField(new Extent(120));
		
		//dummy-werte reinschreiben
		this.oSEL_STARTADRESSE.set_ListenInhalt(bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT, false);
		this.oSEL_ZIELADRESSE.set_ListenInhalt(bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT, false);
		this.oSEL_SORTE.set_ListenInhalt(bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT, false);
		
		
		//20170608:fix wegen oracle12
//		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_STARTADRESSE,_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE+"=#WERT#", null, null));
//		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_ZIELADRESSE,_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_ZIEL+"=#WERT#", null, null));
//		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_SORTE,KOSTEN_LIEFERBED_ADR.id_artikel.tnfn()  +"=#WERT#", null, null));
////		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_SORTE,_DB.KOSTEN_LIEFERBED_ADR$ID_ARTIKEL+"=#WERT#", null, null));
		//alt
		//neu
		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_STARTADRESSE,  KOSTEN_LIEFERBED_ADR.id_adresse.tnfn()+"=#WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_ZIELADRESSE,   KOSTEN_LIEFERBED_ADR.id_adresse_ziel.tnfn()+"=#WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSEL_SORTE,			KOSTEN_LIEFERBED_ADR.id_artikel.tnfn()+"=#WERT#", null, null));
		
		
		this.add(new MyE2_Label(new MyE2_String("Start:")),E2_INSETS.I(2,2,2,2));
		this.add(this.oSEL_STARTADRESSE,E2_INSETS.I(2,2,2,2));
		this.add(new MyE2_Label(new MyE2_String("Sorte:")),E2_INSETS.I(2,2,2,2));
		this.add(this.oSEL_SORTE,E2_INSETS.I(2,2,2,2));
		this.add(new MyE2_Label(new MyE2_String("Ziel:")),E2_INSETS.I(2,2,2,2));
		this.add(this.oSEL_ZIELADRESSE,E2_INSETS.I(2,2,2,2));
		
	}


	
	public E2_SelectionComponentsVector get_oSelVector() {
		return oSelVector;
	}

	
	public void Refresh(String cID_ADRESSE_BASIS_UF) throws myException {
		
		
		String cSQL_Start = "SELECT " +
					" CASE WHEN ADRESSTYP=1 " +
					" THEN CSCONVERT('HA -- ','NCHAR_CS') " +
					" ELSE CSCONVERT('LA -- ','NCHAR_CS') END" +
					"|| ' '||NAME1||' '||NAME2||' ('||ID_ADRESSE||')', " +
					" TO_CHAR(ID_ADRESSE) " +
					" FROM "+bibE2.cTO()+".JT_ADRESSE"+
					  	   " WHERE "+_DB.ADRESSE$SONDERLAGER+" IS NULL "+	
						   " AND ID_ADRESSE IN " +
						   " (SELECT "+bibE2.cTO()+".JT_LIEFERADRESSE.ID_ADRESSE_LIEFER FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+cID_ADRESSE_BASIS_UF+")" +
						   		" OR ID_ADRESSE="+cID_ADRESSE_BASIS_UF+"";
			
		E2_DropDownSettingsNew oDD_Start = new E2_DropDownSettingsNew(cSQL_Start+ " ORDER By 1",  true, false);
		String[][] arrDD_START = oDD_Start.getDD(); 
		this.oSEL_STARTADRESSE.set_ListenInhalt(arrDD_START, false);
		
		
		
		String cSQL_ZIEL = "SELECT " +
			" CASE WHEN ADRESSTYP=1 " +
			" THEN CSCONVERT('HA -- ','NCHAR_CS') " +
			" ELSE CSCONVERT('LA -- ','NCHAR_CS') " +
			" END|| ' '||NAME1||' '||NAME2||' ('||ID_ADRESSE||')', " +
			" ID_ADRESSE " +
			" FROM "+bibE2.cTO()+".JT_ADRESSE"+
			  	   " WHERE "+_DB.ADRESSE$SONDERLAGER+" IS NULL "+	
				   " AND ID_ADRESSE IN " +
				   " ("+this.get_SQLPART_HOLE_IDS(cID_ADRESSE_BASIS_UF)+")";

		E2_DropDownSettingsNew oDD_Ziel = new E2_DropDownSettingsNew(cSQL_ZIEL+ " ORDER By 1",  true,false);
		String[][] arrDD_ZIEL = oDD_Ziel.getDD(); 
		this.oSEL_ZIELADRESSE.set_ListenInhalt(arrDD_ZIEL, false);

		
		
		String cSQL_SORTEN = this.get_SQL_HoleSorten(cID_ADRESSE_BASIS_UF);
		E2_DropDownSettingsNew oDD_SORTE = new E2_DropDownSettingsNew(cSQL_SORTEN,  true, false);
		String[][] arrDD_SORTEN = oDD_SORTE.getDD(); 
		this.oSEL_SORTE.set_ListenInhalt(arrDD_SORTEN, false);
		

	}

	private String get_SQLPART_HOLE_IDS(String cID_ADRESSE_BASIS) {
		
		return "SELECT K.ID_ADRESSE_ZIEL FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR K WHERE ID_ADRESSE_BASIS="+ cID_ADRESSE_BASIS;
	}
	

	
	private String get_SQL_HoleSorten(String cID_ADRESSE_BASIS) throws myException {
		String cSQL = "SELECT NVL("+_DB.Z_ARTIKEL$ANR1+",CSCONVERT('<ANR1>','NCHAR_CS'))||'-'||NVL("+
							_DB.Z_ARTIKEL$ARTBEZ1+",CSCONVERT('<ARTBEZ1>','NCHAR_CS')), "+
							_DB.Z_ARTIKEL$ID_ARTIKEL+" FROM "+
								bibE2.cTO()+"."+_DB.ARTIKEL ;
	
		cSQL = cSQL+" WHERE "+_DB.Z_ARTIKEL$ID_ARTIKEL+
								" IN (SELECT K.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR K WHERE ID_ADRESSE_BASIS="+ cID_ADRESSE_BASIS+")";
		
	
		return cSQL;
	}
}
