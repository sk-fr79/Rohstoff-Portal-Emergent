package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.DB_ENUMS.KONTO;
import panter.gmbh.indep.exceptions.myException;

public class FSK_MASK_ComponentMAP extends E2_ComponentMAP
{
	private String cSTD_FIELDS1 = 	"KONTOTYP|KONTONUMMER|KREDITKARTENTYP|KREDITKARTENNUMMER|KREDITKARTENVV|KREDITKARTENABLAUFMM|KREDITKARTENABLAUFYYYY|KK_NAME|KK_STRASSE|KK_ORT|KK_LAND|KK_PLZ|IBAN_NR|";
	private String cNameList =		"Kontotyp|Kontonummer|Krditkartentyp|Kreditkartennummer|KK-Zusatz|KK-Ablaufmonat|KK-Ablaufjahr|Name auf KK|Strasse auf KK|Ort auf KK|Land auf KK|PLZ auf KK|IBAN|";
	
	
	
	
	public FSK_MASK_ComponentMAP() throws myException
	{
		super(new FSK_MASK_SQLFieldMap_KONTO());
		
		FSK_MASK_SQLFieldMap_KONTO oSQLFieldMAP_Konto = (FSK_MASK_SQLFieldMap_KONTO)this.get_oSQLFieldMAP();

		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP_Konto.get_("ID_KONTO")),new MyE2_String("ID-Konto"));
		MaskComponentsFAB.addStandardComponentsToMAP(cSTD_FIELDS1,cNameList,this.get_oSQLFieldMAP(),false,false,this,400);
		this.add_Component(new FSK_MASK_SearchBank(oSQLFieldMAP_Konto.get_(KONTO.id_bankenstamm.fn()),oSQLFieldMAP_Konto),new MyE2_String("Bank"));
		
		this.add_Component(FSK__CONST.FSK_SONDERFELDER.BANKINFO.name(), new FSK_MASK_BankInfo(), new MyE2_String("Informationen zur Bank"));
		
		
	}

	
	
}
