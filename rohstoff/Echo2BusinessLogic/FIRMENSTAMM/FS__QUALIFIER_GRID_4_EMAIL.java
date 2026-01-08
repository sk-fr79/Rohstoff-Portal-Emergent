package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CheckBoxGrid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_CheckBox;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_Element;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * qualifiziererklasse, um zu definieren, wofuer eine mail-adresse gebraucht wird
 * @author martin
 *
 */
public class FS__QUALIFIER_GRID_4_EMAIL extends Q_DB_CheckBoxGrid
{
	private 		String 		cTableName = null;


	/**
	 * 
	 * @param oSQLField
	 * @param TableName
	 * @throws myException
	 */
	public FS__QUALIFIER_GRID_4_EMAIL(SQLField oSQLField, String TableName) throws myException
	{
		super(oSQLField, false, 2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_5_0), new E2_FontPlain(-2),true);
		this.cTableName = TableName;
	}

	
	@Override
	public Vector<Q_DEF_Element> build_vQ_DEF_Elements(MyE2IF__Component oMotherComponent) throws myException
	{
		//2014-12-12
		//return FS__QUALIFIER_GRID_4_EMAIL.VECT_Q_DEF_Elements(oMotherComponent);
		return new __VECTOR_QUALIFIER_EMAIL_MATRIX(oMotherComponent, false);
	}

	
	@Override
	public String get_cTABLE_NAME() throws myException
	{
		return this.cTableName;
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			FS__QUALIFIER_GRID_4_EMAIL oCopy = new FS__QUALIFIER_GRID_4_EMAIL(this.EXT_DB().get_oSQLField(), this.cTableName);
			oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));
			return oCopy;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}


	@Override
	public HashMap<String, Vector<XX_ActionAgent>> get_hmZusatzActionAgents4Checkboxes(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		return null;
	}

	@Override
	public HashMap<String, Vector<XX_ActionValidator>> get_hmZusatzGlobalValidators(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		return null;
	}


	@Override
	public void Format_CB(Q_DEF_CheckBox oFormatedCheckBox) throws myException
	{
		
	}


	@Override
	public String get_cCLASS_KEY()
	{
		return Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP;
	}

	
//	public static Vector<Q_DEF_Element> VECT_Q_DEF_Elements(MyE2IF__Component oMotherComponent) throws myException
//	{
//		Vector<Q_DEF_Element>  vRueck = new Vector<Q_DEF_Element>();
//		
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_ANGEBOT,			new MyE2_String("Angebot"),			new MyE2_String("Ang"),		"01",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verkaufsangebote benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT,	new MyE2_String("Abn.Ang."),		new MyE2_String("AAg"), 	"02",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Abnahmeangebote/Preislisten benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT,		new MyE2_String("EK-Kon."),			new MyE2_String("EKK"), 	"03",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Einkaufs-Kontrakte benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT,		new MyE2_String("VK-Kon."),			new MyE2_String("VKK"), 	"04",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verkaufskontrakte benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_RECHNUNG,			new MyE2_String("Rech."),			new MyE2_String("RE"), 		"05",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Rechnung benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT,		new MyE2_String("Gut."),			new MyE2_String("GUT"), 	"06",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Gutschrift benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_TRANSPORT,		new MyE2_String("TPA"),				new MyE2_String("TPA"), 	"07",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Transportpapiere benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN,		new MyE2_String("Begl."),			new MyE2_String("LS"),		"08",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verpackungsbegleitscheine benutzt werden soll ()")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_AUFT_BEST,		new MyE2_String("Auft.Best."),		new MyE2_String("ABF"),		"09",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Auftragsbestätigungen benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_FIBU,				new MyE2_String("Fibu"),			new MyE2_String("Fibu"),	"10",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Versendung von Fibu-Dokumenten/Kontoblättern benutzt werden soll")));
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_MAHNUNG,			new MyE2_String("Mahnung"),			new MyE2_String("Mahn"),	"11",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Versendung von Mahnungen benutzt werden soll")));
//		//2014-01-13: neuer eMail-Verteiler: Inventur 
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_LAGER,			new MyE2_String("Lager"),			new MyE2_String("Lager"),	"12",	oMotherComponent,	new MyE2_String("Markieren für Mails zum Thema Lager, Lagerkontrolle, Inventur usw.")));
//		
//		vRueck.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT, new MyE2_String("Original Rechnung/Gutschrift"),	new MyE2_String("Orig.Rech/Gut"),	"13",	oMotherComponent,	new MyE2_String("Diese Adresse soll Rechungen und Gutschriften erhalten (es darf nur eine einzige eMail dieses Merkmal tragen !")));			
//		return vRueck;
//	}

	
	
//	public static HashMap<String, MyE2_String> get_HM_DB_KEY___VS___USER_TEXT_SHORT() throws myException
//	{
//		HashMap<String, MyE2_String>  hmRueck = new HashMap<String, MyE2_String>();
//		
//		Vector<Q_DEF_Element>  vHelp = new __VECTOR_QUALIFIER_EMAIL_MATRIX(null, true);
//		
//		
//		for (Q_DEF_Element oQDEF: vHelp)
//		{
//			hmRueck.put(oQDEF.get_cDB_DATENBANKTAG(), oQDEF.get_cSHORT_TEXT_4_USER());
//		}
//		return hmRueck;
//	}
	
	
	
	public void Preset_Values(Vector<String> vMAILTYPEN) {
		for (String cMAILTYP: this.get_hmQ_DEF_CheckBoxen().keySet()) {
			this.get_hmQ_DEF_CheckBoxen().get(cMAILTYP).setSelected(vMAILTYPEN.contains(cMAILTYP));
		}
	}
	
	
	
}
