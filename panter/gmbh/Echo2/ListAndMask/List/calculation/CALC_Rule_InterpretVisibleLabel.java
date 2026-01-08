package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class CALC_Rule_InterpretVisibleLabel extends CALC_Rule_ABSTRACT {

	private static 					String ERR_Code = 	"@ERROR@"; 
	private static 					String LEER_Code = 	"@LEER@";
	
	private MyE2IF__Component  		o_Component = 	null;
	
	
	/**
	 * 
	 * @param oComponent (!!! die Komponente aus der Referenz-Componentmap der E2_NavigationList)
	 */
	public CALC_Rule_InterpretVisibleLabel(MyE2IF__Component  oComponent) {
		super();

		this.o_Component = oComponent;
		
		MyE2_String cInfoText = new MyE2_String("Summe: ");
		if (this.o_Component.EXT().get_cList_or_Mask_Titel()!=null) {
			cInfoText.addString(this.o_Component.EXT().get_cList_or_Mask_Titel());
		} 
		this.set_cINFOTEXT(cInfoText);

	}



	@Override
	public BigDecimal get_bdVALUE_ERGEBNISS(Vector<String> vID_COL) throws myException {
		
		BigDecimal bdRueck = new BigDecimal(0);
		this.set_cFehlerInfo4User(null);
		
		for (String cID: vID_COL) {
			E2_ComponentMAP  oMAP_Line = this._GET_NAVILIST_THIS_BELONGS_TO().get_ComponentMAP(cID);
			if (oMAP_Line != null ) {
				
				MyE2IF__Component  oComp = oMAP_Line.get_hmRealComponents().get(this.o_Component.EXT().get_C_HASHKEY());
				
				if (oComp != null) {
					String cText = this.extractStringFromComponent(oComp).trim();
					
					//jetzt ein paar als leer zu interpretierende faelle durchgehen 
					if (	cText.equals(CALC_Rule_InterpretVisibleLabel.LEER_Code) || 
							S.isEmpty(cText) || 
							cText.equals("-") || 
							cText.equals("--") ||
							cText.equals("---")) {
						continue;   //wird wie 0 behandelt
					}

					MyBigDecimal oBD = new MyBigDecimal(cText);
					
					if (oBD.get_bOK()) {
						bdRueck = bdRueck.add(oBD.get_bdWert());
					} else {
						bdRueck = null;
						this.set_cFehlerInfo4User(new MyE2_String("Ein vorhandener Eintrag ist nicht als Zahl zu interpretieren: ",true,cText,false));
						break;
					}
				
				} else {
					bdRueck = null;
					this.set_cFehlerInfo4User(new MyE2_String("Die zu summierende Spalte ist nicht sichtbar!"));
					break;
				}
				
			} else {
				bdRueck = null;
				this.set_cFehlerInfo4User(new MyE2_String("Es werden nicht sichtbare Datensätze angefordert!"));
				break;
			}
		}
		
		return bdRueck;
	}

	
	private String extractStringFromComponent(MyE2IF__Component oComp) {
		String cRueck = CALC_Rule_InterpretVisibleLabel.ERR_Code;
		
		if (oComp instanceof MyE2_DB_Label_INROW) {
			cRueck = S.NN(((MyE2_DB_Label_INROW)oComp).get_oErsatzButton().getText());
			if (cRueck.trim().equals( S.NN( ((MyE2_DB_Label_INROW)oComp).get_cErsatzFuerLeeranzeige()).trim()   )  ) {
				cRueck = CALC_Rule_InterpretVisibleLabel.LEER_Code;
			}
		} else if (oComp instanceof MyE2_DB_Label) {
			cRueck = S.NN(((MyE2_DB_Label)oComp).getText());
			if (cRueck.trim().equals(S.NN(((MyE2_DB_Label)oComp).get_cErsatzFuerLeeranzeige()).trim())) {
				cRueck = CALC_Rule_InterpretVisibleLabel.LEER_Code;
			}
		} else if (oComp instanceof MyE2_Label) {
			cRueck = S.NN(((MyE2_Label)oComp).getText());
			if (cRueck.trim().equals(S.NN(((MyE2_Label)oComp).get_cErsatzFuerLeeranzeige()).trim())) {
				cRueck = CALC_Rule_InterpretVisibleLabel.LEER_Code;
			}
		}
		return cRueck;
	}
	
	
	
}
