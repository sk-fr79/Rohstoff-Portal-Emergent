package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.exceptions.myException;

public class UMA_MASK extends MyE2_Grid 
{

	
	public UMA_MASK(UMA_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//oFiller.add_Trenner(this, E2_INSETS.I_1_1_1_1);
		oFiller.add_Line(this, new MyE2_String("UMA-Kontrakt-ID:"), 				1, "ID_UMA_KONTRAKT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Vertragsdatum:"), 					1, "DATUM_VERTRAG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Beteuer des Vertrags:"), 			1, "ID_USER_BETREUER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Firma:"), 							1, "ID_ADRESSE|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_5_5_5_5);
		oFiller.add_Line(this, new MyE2_String("Liefersorte:"), 					1, UMA_CONST.MASK_COMP_TOCHTER_LIEFERSORTE+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_5_5_5_5);
		oFiller.add_Line(this, new MyE2_String("Rueckliefersorte:"), 				1, UMA_CONST.MASK_COMP_TOCHTER_RUECKLIEFERSORTE+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_5_5_5_5);
		oFiller.add_Line(this, new MyE2_String("Ausgangsmenge:"), 					1, "MENGE_ARTIKEL_AUSGANG|#  |"+UMA_CONST.MASK_COMP_EINHEIT_AUSGANGSORTE+"|#   |# Startsaldo:|"+RECORD_UMA_KONTRAKT.FIELD__STARTSALDO_MENGE_AUSGANG,3);
		oFiller.add_Line(this, new MyE2_String("Zielmenge:"), 						1, "MENGE_ARTIKEL_ZIEL|#  |"+UMA_CONST.MASK_COMP_EINHEIT_ZIELSORTE+"|#   |# Startsaldo:|"+RECORD_UMA_KONTRAKT.FIELD__STARTSALDO_MENGE_ZIEL,3);
		oFiller.add_Line(this, new MyE2_String("Bemerkungen:"), 					1, "BEMERKUNGEN|#  |",3);
	}

}
