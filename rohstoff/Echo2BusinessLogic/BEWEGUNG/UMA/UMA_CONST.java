package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;

public class UMA_CONST
{
	public static String MASK_COMP_EINHEIT_AUSGANGSORTE = 			"MASK_COMP_EINHEIT_AUSGANGSORTE";
	public static String MASK_COMP_EINHEIT_ZIELSORTE = 				"MASK_COMP_EINHEIT_ZIELSORTE";
	public static final String NAME_OF_CHECKBOX_IN_LIST =			"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =			"NAME_OF_LISTMARKER_IN_LIST";

	public static final String NAME_OF_LOCK_UNLOCK_BUTTON =			"NAME_OF_LOCK_UNLOCK_BUTTON";

	public static String MASK_COMP_TOCHTER_LIEFERSORTE = 			"MASK_COMP_TOCHTER_LIEFERSORTE";
	public static String MASK_COMP_TOCHTER_RUECKLIEFERSORTE = 		"MASK_COMP_TOCHTER_RUECKLIEFERSORTE";

	public static String LIST_COMP_TOCHTER_LIEFERSORTEN = 			"LIST_COMP_TOCHTER_LIEFERSORTEN";
	public static String LIST_COMP_TOCHTER_RUECKLIEFERSORTEN = 		"LIST_COMP_TOCHTER_RUECKLIEFERSORTEN";
	
	
	
	public static GridLayoutData    get_GreenLayout4OpenCloseButton()
	{
		GridLayoutData  greenBack = new GridLayoutData();
		greenBack.setInsets(E2_INSETS.I_1_1_1_1);
		greenBack.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		greenBack.setBackground(Color.GREEN);
		
		return greenBack;
	}
	

	public static GridLayoutData    get_RedLayout4OpenCloseButton()
	{
		GridLayoutData  redBack = new GridLayoutData();
		redBack.setInsets(E2_INSETS.I_1_1_1_1);
		redBack.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		redBack.setBackground(Color.RED);
		
		return redBack;
	}

	
	public static GridLayoutData    get_DelLayout4OpenCloseButton()
	{
		GridLayoutData  redBack = new GridLayoutData();
		redBack.setInsets(E2_INSETS.I_1_1_1_1);
		redBack.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		redBack.setBackground(null);
		
		return redBack;
	}


	
}
