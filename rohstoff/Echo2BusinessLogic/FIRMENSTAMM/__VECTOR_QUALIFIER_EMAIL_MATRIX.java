package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_Element;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class __VECTOR_QUALIFIER_EMAIL_MATRIX extends Vector<Q_DEF_Element> {

	public __VECTOR_QUALIFIER_EMAIL_MATRIX( MyE2IF__Component  oMotherComponent, boolean bADD_TYP_RECH_GUT_SEND_ORIG) throws myException {
		super();
		
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_ANGEBOT,		new MyE2_String("Angebot"),			new MyE2_String("Ang"),		"01",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verkaufsangebote benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT,	new MyE2_String("Abn.Ang."),		new MyE2_String("AAg"), 	"02",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Abnahmeangebote/Preislisten benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT,	new MyE2_String("EK-Kon."),			new MyE2_String("EKK"), 	"03",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Einkaufs-Kontrakte benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT,	new MyE2_String("VK-Kon."),			new MyE2_String("VKK"), 	"04",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verkaufskontrakte benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_RECHNUNG,		new MyE2_String("Rech."),			new MyE2_String("RE"), 		"05",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Rechnung benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT,		new MyE2_String("Gut."),			new MyE2_String("GUT"), 	"06",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Gutschrift benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_TRANSPORT,		new MyE2_String("TPA"),				new MyE2_String("TPA"), 	"07",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Transportpapiere benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN,	new MyE2_String("Begl."),			new MyE2_String("LS"),		"08",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Verpackungsbegleitscheine benutzt werden soll ()")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_AUFT_BEST,		new MyE2_String("Auft.Best."),		new MyE2_String("ABF"),		"09",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Auftragsbestätigungen benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_FIBU,			new MyE2_String("Fibu"),			new MyE2_String("Fibu"),	"10",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Versendung von Fibu-Dokumenten/Kontoblättern benutzt werden soll")));
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_MAHNUNG,		new MyE2_String("Mahnung"),			new MyE2_String("Mahn"),	"11",	oMotherComponent,	new MyE2_String("Markieren, wenn diese Mail für Versendung von Mahnungen benutzt werden soll")));
		//2014-01-13: neuer eMail-Verteiler: Inventur 
		this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_LAGER,			new MyE2_String("Lager"),			new MyE2_String("Lager"),	"12",	oMotherComponent,	new MyE2_String("Markieren für Mails zum Thema Lager, Lagerkontrolle, Inventur usw.")));
		
		if (bADD_TYP_RECH_GUT_SEND_ORIG) {
			this.add(new Q_DEF_Element(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT, new MyE2_String("Orig. Rech./Gut."),	new MyE2_String("Rech/Gut"),	"13",	oMotherComponent,	new MyE2_String("Diese Adresse soll Rechnungen und Gutschriften erhalten (es darf nur eine einzige eMail dieses Merkmal tragen !")));
		}
	}

	
	/**
	 * 
	 * @return s a hashmap key = DBTAG, value = Text4User
	 */
	public HashMap<String, MyE2_String>  get_hm_DBTAG_TEXT4USER() {
		
		HashMap<String, MyE2_String> hmRueck = new HashMap<String, MyE2_String>();
		
		for (Q_DEF_Element oQDEF: this)
		{
			hmRueck.put(oQDEF.get_cDB_DATENBANKTAG(), oQDEF.get_cSHORT_TEXT_4_USER());
		}
		return hmRueck;
	}
}
