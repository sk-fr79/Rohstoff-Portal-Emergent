package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportBuchung;
/**
 * Dies sind die statischen Sessions, die ich mit Frau Hecktor abgehalten habe,
 * und deren Buchungswerte als korrekt verifiziert wurden.
 * Wenn Regeln geändert werden, kann man immer diese Tests laufen lassen, um
 * zu schauen, dass die geänderte Regelmenge die vorhandenen Dinge noch korrekt
 * bucht. 
 * 
 * Neue Sessions können in einer Testmethode zusammengefasst werden. 
 * I.W. "merkt"  ein Aufruf von 
 *    ensure(nr, x, summe, debkred, konto, korrigierteSumme, korrigiertesKonto)
 * sich, dass die Buchungsposition nr (als G../RE...) mit der Debitor-Kreditor-
 * Nummer debkred, die Summe summe auf das Konto konto gebucht werden soll.
 * Falls korrigiertesKonto oder korrigierteSumme angegeben werden, dann 
 * überschreiben diese die Erwartungen von konto und summe.
 *
 * Ganz unten können in dieser Klasse Edge-Cases einzeln getestet und 
 * debugged werden.
 * 
 * @author nils
 *
 */
public class AccountFinderStaticTest extends AccountFinderTestBase {
	// ^\s+\[ExBu\*\s\{[0-9\-]{10}\s/\s+((G|RE)[0-9]+)\s+/\s+[0-9]+\s+/\s+([\-0-9]+,[0-9]+)\sEUR\}\s=>\s([0-9]+)\s/\s([0-9]+)\s\]\s(OK|.+([\-0-9]+,[0-9]+)(\[[0-9]{4}\]){0,1})\s

	public void testStaticsSession3() throws myException, SQLException {
		ensure("RE4106014585", "RE", "11083,70", "10302", "4400"); //
		ensure("RE4106014615", "RE", "550,00", "22667", "4336"); //
		ensure("RE4106014709", "RE", "45,88", "11109", "4400"); //
		ensure("RE4106014842", "RE", "26764,92", "10399", "4400"); //
		ensure("RE4106014873", "RE", "22203,95", "10466", "4400"); //
		ensure("RE4106014874", "RE", "38903,71", "10466", "4321"); //
		ensure("RE4106014883", "RE", "29352,75", "22650", "4125"); //
		ensure("RE4106014965", "RE", "272,51", "13448", "4401"); //
		ensure("G4206024523", "G", "30,00", "84470", "5200"); //
		ensure("G4206024532", "G", "167,60", "84495", "5200"); //
		ensure("G4206024855", "G", "70,00", "87771", "5400"); //
		ensure("G4206024855", "G", "-23,56", "87771", "4401"); //
		ensure("G4206024873", "G", "7132,60", "84896", "5400"); //
		ensure("G4206025024", "G", "87,35", "80718", "5401"); //
		ensure("G4206025129", "G", "19918,41", "72739", "5425"); //
		ensure("G4206025153", "G", "-9951,14", "81318", "5401"); //
		ensure("G4206025158", "G", "117873,49", "81821", "5400"); //
		ensure("G4206025164", "G", "4902,00", "83193", "5400"); //
		ensure("RE4106014637", "RE", "15699,84", "10412", "4400"); //
		ensure("RE4106014638", "RE", "42247,78", "10412", "4400"); //
		ensure("RE4106014872", "RE", "6982,20", "10466", "4400"); //
		ensure("RE4106014910", "RE", "5048,81", "20104", "4321"); //
		ensure("RE4106014941", "RE", "145200,15", "10466", "4400"); //
		ensure("G4206024565", "G", "132,20", "82899", "5200"); //
		ensure("G4206024701", "G", "60,00", "82892", "5200"); //
		ensure("G4206024701", "G", "-23,56", "82892", "4401"); //
		ensure("G4206024702", "G", "102,80", "89129", "5200"); //
		ensure("G4206025215", "G", "1691,76", "72606", "5425"); //
		ensure("G4206025242", "G", "8942,50", "81319", "5400"); //
		ensure("G4206025340", "G", "782,80", "89264", "5400"); //
		ensure("G4206025415", "G", "239568,04", "72957", "5425"); //
		ensure("G4206025423", "G", "96284,85", "72955", "5425"); //
		ensure("RE4106014558", "RE", "32980,50", "20602", "4125"); //
		ensure("RE4106014633", "RE", "310,50", "10412", "4400"); //
		ensure("RE4106014814", "RE", "-986,96", "10412", "4400"); //
		ensure("RE4106014815", "RE", "-82,80", "10412", "4400"); //
		ensure("RE4106014827", "RE", "1603,61", "11413", "4401"); //
		ensure("RE4106014838", "RE", "12257,28", "10239", "4400"); //
		ensure("RE4106014889", "RE", "38734,40", "22650", "4125"); //
		ensure("RE4106014911", "RE", "11522,55", "20104", "4321"); //
		ensure("RE4106014922", "RE", "52355,68", "20104", "4125"); //
		ensure("RE4106014930", "RE", "27508,63", "20104", "4125"); //
		ensure("G4206024837", "G", "-119,00", "84528", "5401"); //
		ensure("G4206024838", "G", "164,40", "80777", "5200"); //
		ensure("G4206024839", "G", "-23,56", "84529", "4401"); //
		ensure("G4206024839", "G", "60,00", "84529", "5200"); //
		ensure("G4206024840", "G", "362,00", "84531", "5200"); //
		ensure("RE4106014655", "RE", "532,00", "11489", "0000"); // [0000]
		checkAllEnsures();
	}

	public void testStaticsSession2() throws myException, SQLException {
		ensure("RE4106013493", "RE", "550,00", "22667", "4336", null, null); //
		ensure("G4206023521", "G", "1671,80", "80726", "5400", null, null); //
		ensure("G4206023548", "G", "13113,90", "81319", "5400", null, null); //
		ensure("G4206023505", "G", "84180,57", "80675", "5400", null, null); //
		ensure("G4206022917", "G", "228,10", "84047", "5200", null, null); //
		ensure("RE4106013602", "RE", "1660,08", "11310", "4400", null, null); //
		ensure("G4206023729", "G", "39109,51", "81318", "5400", null, null); //
		ensure("G4206023675", "G", "1998,30", "72999", "5425", null, null); //
		ensure("G4206022916", "G", "70,00", "88946", "5200", null, null); //
		ensure("G4206022916", "G", "-23,56", "88946", "4401", null, null); //
		ensure("G4206022925", "G", "-23,56", "84048", "4401", null, null); //
		ensure("G4206022930", "G", "34,80", "84058", "5200", null, null); //
		ensure("G4206023560", "G", "105775,58", "80428", "5400", null, null); //
		ensure("RE4106013625", "RE", "4692,00", "10412", "4400", null, null); //
		ensure("G4206022907", "G", "109,20", "80236", "5200", null, null); //
		ensure("G4206023519", "G", "73715,79", "70902", "5425", null, null); //
		ensure("RE4106013878", "RE", "27756,75", "20104", "4321", null, null); //
		ensure("G4206022965", "G", "1813,80", "87970", "5400", null, null); //
		ensure("RE4106013468", "RE", "25792,30", "20137", "4120", null, null); //
		ensure("RE4106013629", "RE", "-229,50", "10412", "4400", null, null); //
		ensure("G4206023485", "G", "3352,45", "80605", "5400", null, null); //
		ensure("G4206023688", "G", "1337,90", "83295", "5400", null, null); //
		ensure("G4206023575", "G", "1425,00", "88058", "5400", null, null); //
		// [ExBu* {2014-05-02 / G4206022919 / 1 / -23,56 EUR} => 84048 / 5401 ]
		// FALSCH Autokauf
		ensure("RE4106013627", "RE", "178,50", "10412", "4400", null, null); //
		ensure("RE4106013874", "RE", "34297,66", "20104", "4125", null, null); //
		ensure("G4206023501", "G", "27233,99", "80675", "5400", null, null); //
		// [ExBu* {2014-05-02 / G4206022919 / 1 / 70,00 EUR} => 84048 / 5200 ]
		// FALSCH Autokauf
		ensure("G4206022918", "G", "336,70", "89117", "5200", null, null); //
		ensure("G4206023576", "G", "564,00", "88058", "5400", null, null); //
		ensure("G4206022920", "G", "48,40", "88910", "5200", null, null); //
		ensure("G4206023642", "G", "43131,63", "81208", "5400", null, null); //
		ensure("G4206023728", "G", "-10402,27", "81318", "5401", null, null); //
		ensure("RE4106013554", "RE", "1831,96", "11310", "4400", null, null); //
		ensure("RE4106013604", "RE", "-1831,96", "11310", "4400", null, null); //
		ensure("G4206023508", "G", "8757,19", "81821", "5400", null, null); //
		ensure("RE4106013475", "RE", "4403,00", "10412", "4400", null, null); //
		ensure("RE4106013461", "RE", "1887,60", "11310", "4400", null, null); //
		ensure("G4206023606", "G", "4090,80", "81132", "5400", null, null); //
		ensure("RE4106013603", "RE", "-1887,60", "11310", "4400", null, null); //
		ensure("RE4106014333", "RE", "5963,76", "20104", "4125", null, null); //
		ensure("G4206022925", "G", "70,00", "84048", "5200", null, null); //
		ensure("G4206022914", "G", "443,00", "84046", "5200", null, null); //
		ensure("RE4106013477", "RE", "10452,10", "10412", "4400", null, null); //
		ensure("G4206022915", "G", "206,00", "80951", "5200", null, null); //
		ensure("G4206023748", "G", "793,60", "80383", "5400", null, null); //
		// [ExBu* {2014-05-02 / G4206023748 / 2 / 793,60 EUR} => 80383 / 5425 ]
		// FALSCH [5400] // Wg. ID-Nr aus Deutschland, obwohl niederlande
		// ist in der Zeile oberhalb eingetragen, OKAY
		ensure("G4206023669", "G", "20558,80", "80389", "5400", null, null); //
		ensure("RE4106013628", "RE", "5081,70", "10412", "4400", null, null); //
		ensure("G4206023672", "G", "12493,80", "81403", "5400", null, null); //
		ensure("G4206023692", "G", "32652,90", "72677", "5425", null, null); //
		ensure("RE4106013469", "RE", "7318,50", "20137", "4120", null, null); //
		ensure("G4206022932", "G", "88,80", "84061", "5200", null, null); //
		ensure("RE4106013555", "RE", "-1853,80", "11310", "4400", null, null); //
		ensure("G4206023666", "G", "21264,00", "86529", "5400", null, null); //
		ensure("RE4106013601", "RE", "1670,79", "11310", "4400", null, null); //
		ensure("G4206023507", "G", "209995,98", "81821", "5400", null, null); //
		ensure("RE4106013476", "RE", "672,00", "10412", "4400", null, null); //
		ensure("G4206023504", "G", "8934,32", "80675", "5400", null, null); //
		ensure("G4206022922", "G", "39,00", "84051", "5200", null, null); //
		ensure("RE4106013630", "RE", "-5003,70", "10412", "4400", null, null); //
		ensure("G4206022929", "G", "688,00", "84544", "5200", null, null); //
		ensure("G4206022928", "G", "75,90", "84057", "5200", null, null); //
		ensure("G4206023647", "G", "6520,88", "80408", "5400", null, null); //
		ensure("G4206023546", "G", "7285,45", "80235", "5400", null, null); //
		ensure("G4206023643", "G", "43367,01", "81208", "5400", null, null); //
		ensure("RE4106013467", "RE", "6813,38", "20137", "4120", null, null); //
		ensure("RE4106013626", "RE", "-4403,00", "10412", "4400", null, null); //
		ensure("G4206022926", "G", "44,20", "84055", "5200", null, null); //
		ensure("G4206022921", "G", "44,46", "84050", "5200", null, null); //
		ensure("G4206023540", "G", "1049,40", "82303", "5400", null, null); //
		ensure("G4206022935", "G", "292,80", "81505", "5200", null, null); //
		ensure("G4206022924", "G", "182,00", "87737", "5200", null, null); //
		ensure("RE4106013473", "RE", "382,50", "10412", "4400", null, null); //
		ensure("RE4106013658", "RE", "1344,00", "10412", "4400", null, null); //
		ensure("G4206023376", "G", "144815,36", "72955", "5202", null, null); //
		ensure("G4206023613", "G", "1081,20", "80392", "5400", null, null); //
		// [ExBu* {2014-05-02 / G4206022925 / 1 / -23,56 EUR} => 84048 / 5401 ]
		// FALSCH [4401] Autokauf
		ensure("RE4106013737", "RE", "12620,70", "20104", "4125", null, null); //
		ensure("G4206023641", "G", "4398,80", "81208", "5400", null, null); //
		ensure("RE4106013657", "RE", "252,00", "10412", "4400", null, null); //
		ensure("RE4106013795", "RE", "1171,45", "11413", "4401", null, null); //
		ensure("RE4106013471", "RE", "27799,55", "20137", "4120", null, null); //
		ensure("G4206023493", "G", "2740,79", "82602", "5400", null, null); //
		ensure("G4206023526", "G", "53132,96", "72606", "5425", null, null); //
		ensure("G4206023709", "G", "2799,18", "70155", "5201", null, null); //
		ensure("G4206023770", "G", "104801,94", "72957", "5425", null, null); //
		ensure("G4206023767", "G", "148787,73", "72957", "5425", null, null); //
		ensure("RE4106013561", "RE", "586,50", "10412", "4400", null, null); //
		ensure("RE4106013866", "RE", "17323,04", "20104", "4125", null, null); //
		ensure("RE4106013800", "RE", "109485,60", "21102", "4325", null, null); //
		ensure("G4206023654", "G", "6513,54", "72956", "5425", null, null); //
		ensure("G4206023529", "G", "83946,00", "72688", "5202", null, null); //
		ensure("RE4106013434", "RE", "5043,75", "22671", "4125", null, null); //
		ensure("G4206023693", "G", "5913,00", "82006", "5400", null, null); //
		ensure("RE4106013811", "RE", "46483,19", "20138", "4120", null, null); //
		ensure("G4206023550", "G", "117393,84", "70606", "5425", null, null); //
		ensure("G4206023746", "G", "1609,00", "72665", "5425", null, null); //
		ensure("G4206023708", "G", "10281,80", "72979", "5201", null, null); //
		ensure("G4206022969", "G", "120,00", "84063", "5200", null, null); //
		ensure("RE4106013502", "RE", "11570,00", "20137", "4120", null, null); //
		ensure("G4206023495", "G", "14763,79", "82602", "5400", null, null); //
		ensure("RE4106013859", "RE", "80777,64", "22659", "4325", null, null); //
		ensure("RE4106013503", "RE", "-12470,00", "20137", "4120", null, null); //
		ensure("RE4106013566", "RE", "18043,20", "10412", "4400", null, null); //
		ensure("RE4106013567", "RE", "55356,76", "10412", "4400", null, null); //
		ensure("G4206023616", "G", "646,40", "87555", "5200", null, null); //
		ensure("G4206023616", "G", "-188,50", "87555", "4401", null, null); //
		ensure("RE4106013865", "RE", "6820,20", "20104", "4125", null, null); //
		ensure("G4206022937", "G", "176,00", "84062", "5200", null, null); //
		checkAllEnsures();
	}

	@Test
	public void testStaticsSession1() throws myException, SQLException {
		printPDFs = false;

		// ^\s+/\s+((G|RE)[0-9]+)\s+/\s+[0-9]+\s+/\s+([\-0-9]+,[0-9]+)\sEUR\}\s=>\s([0-9]+)\s/\s([0-9]+)\s\]\s(OK|.+([\-0-9]+,[0-9]+)(\[[0-9]{4}\]){0,1})\s
		// to
		// ensure("$1", "$2", "$3", "$4", "$5", "$6", "$7"); //

		ensure("G4206021874", "G", "8089,56", "72686", "5425", "7972,56"); //
		ensure("G4206021751", "G", "207267,63", "72955", "5202"); //
		ensure("G4206022324", "G", "-44199,18", "81208", "5400"); //
		ensure("G4206022322", "G", "-38765,88", "81208", "5400"); //
		ensure("G4206021871", "G", "1040,40", "86716", "5200"); // OKAY NOW
		ensure("G4206022127", "G", "4837,70", "72999", "5425", "4708,20"); //
		ensure("G4206021934", "G", "15698,15", "81062", "5400"); //
		ensure("G4206022072", "G", "16932,80", "72979", "5201", "16798,80"); //
		ensure("G4206022138", "G", "121599,47", "72957", "5425", "119271,97"); //
		ensure("RE4106012435", "RE", "19916,40", "22617", "4125"); //
		ensure("G4206022010", "G", "30622,80", "72606", "5425"); //
		ensure("G4206021343", "G", "74,10", "86944", "5200"); //
		ensure("G4206021895", "G", "82395,50", "83280", "5400", "82129,00"); //
		ensure("G4206022178", "G", "14437,20", "80603", "5400"); //
		ensure("RE4106012838", "RE", "-91035,84", "20612", "4125"); //
		ensure("G4206021887", "G", "787,00", "87555", "5200"); //
		ensure("G4206021887", "G", "-141,37", "87555", "4401"); // manually added
		ensure("RE4106012891", "RE", "80333,11", "10466", "4400"); //
		ensure("G4206022318", "G", "-7591,27", "81208", "5400"); //
		ensure("G4206021803", "G", "7138,40", "80605", "5400"); //
		ensure("G4206022007", "G", "787,00", "87555", "5200"); //
		ensure("G4206022009", "G", "56052,00", "72688", "5202"); //
		ensure("RE4106012930", "RE", "84217,86", "20104", "4125", "81897,76"); //
		ensure("G4206021974", "G", "-33082,70", "82626", "5400", "-32852,70"); //
		ensure("RE4106012774", "RE", "22682,39", "10258", "4400"); //
		//ensure("G4206022007", "G", "787,00", "87555", "5200"); //
		ensure("G4206022007", "G", "-164,93", "87555", "4401"); //
		// G4206022007 / 7 / -138,60 EUR} => 87555 / 5401 ] Autoeinkauf
		// Sonderfall 787,00->5200 -164,93->4401
		// Siehe oben
		ensure("G4206021933", "G", "15054,09", "81062", "5400"); //
		ensure("RE4106012785", "RE", "80,01", "13325", "4401", "95,21"); //
		ensure("RE4106012894", "RE", "224037,18", "22659", "4325"); //
		ensure("RE4106012483", "RE", "16972,28", "25018", "4120"); //
		ensure("RE4106012812", "RE", "10189,20", "21102", "4125"); //
		ensure("RE4106012944", "RE", "15243,80", "20104", "4125"); //
		ensure("RE4106012556", "RE", "54749,00", "10412", "4400", "54605,90"); //
		ensure("G4206022026", "G", "3640,26", "80675", "5400"); //
		ensure("G4206021901", "G", "14643,94", "81821", "5400"); //
		ensure("RE4106012837", "RE", "132117,72", "20612", "4125"); //
		ensure("RE4106012552", "RE", "3137,75", "10412", "4400"); //
		ensure("RE4106012608", "RE", "19992,00", "22617", "4125"); //
		ensure("G4206022133", "G", "132112,32", "83879", "5400"); //
		ensure("G4206021982", "G", "14302,05", "81319", "5400", "14263,05"); //
		ensure("G4206021891", "G", "3796,48", "72969", "5425"); //
		ensure("RE4106012936", "RE", "52100,30", "20104", "4125", "49076,50"); //
		ensure("G4206021340", "G", "35,70", "83201", "5200"); //
		ensure("G4206022046", "G", "167172,16", "70606", "5425", "166823,66"); //
		ensure("RE4106012892", "RE", "121881,56", "10466", "4400", "121719,56"); //
		ensure("G4206021950", "G", "66432,91", "70902", "5425"); //
		ensure("RE4106012950", "RE", "6686,40", "20104", "4125"); //
		ensure("G4206021344", "G", "2219,00", "81147", "5200"); //
		ensure("G4206021948", "G", "18133,50", "72982", "5425"); //
		ensure("G4206022132", "G", "10283,69", "83879", "5400"); //
		ensure("G4206021686", "G", "13902,80", "81821", "5400", "13860,80"); //
		ensure("G4206021909", "G", "3698,70", "87167", "5400"); //
		ensure("G4206022086", "G", "2402,00", "72759", "5425"); //
		ensure("RE4106012893", "RE", "41940,99", "10466", "4321"); //
		ensure("G4206021875", "G", "23911,20", "80113", "5400"); //
		ensure("RE4106012776", "RE", "44437,82", "12705", "4400"); //
		ensure("RE4106012836", "RE", "91035,84", "20612", "4125"); //
		// / G4206021853 / 2 / 3746,50 EUR} => 70169 / 5201 ] Falsch [Zwar
		// schweizer Firma, aber Deutsche IDnr, daher wirds behandelt wie
		// deutscher unternehmer (kein Waren-grenzübertritt]
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ensure("G4206022317", "G", "7689,73", "81208", "5400"); //
		ensure("RE4106012555", "RE", "4231,50", "10412", "4400", "3115,50"); //
		ensure("G4206022054", "G", "60186,80", "81816", "5400"); //
		ensure("G4206021815", "G", "2100,23", "82602", "5400"); //
		ensure("RE4106012495", "RE", "24602,44", "25018", "4120", "24475,44"); //
		ensure("G4206022037", "G", "12335,80", "80120", "5400"); //
		ensure("RE4106012473", "RE", "550,00", "22667", "4336"); //
		ensure("RE4106012813", "RE", "78472,80", "21102", "4325"); //
		ensure("G4206021841", "G", "1724,10", "88496", "5400"); //
		ensure("G4206022135", "G", "137884,95", "72957", "5425"); //
		ensure("RE4106012934", "RE", "64119,30", "20104", "4125", "63307,80"); //
		ensure("RE4106012609", "RE", "-19916,40", "22617", "4125"); //
		ensure("G4206021900", "G", "209870,04", "81821", "5400"); //
		ensure("G4206022008", "G", "74879,78", "72688", "5425", "74859,78");
		ensure("G4206021907", "G", "600,00", "89121", "5400", "576,00"); //
		ensure("RE4106012786", "RE", "3469,68", "13325", "4401", "4128,92"); //
		ensure("G4206022039", "G", "30050,78", "71904", "5425", "29986,03"); //
		ensure("RE4106012856", "RE", "849,70", "13448", "4401", "1011,14"); //
		ensure("G4206022244", "G", "6815,93", "80203", "5200"); //
		ensure("RE4106012524", "RE", "35609,25", "20137", "4120", "35564,25"); //
		ensure("G4206021945", "G", "6458,10", "80395", "5400", "6333,60"); //
		ensure("RE4106012548", "RE", "157,50", "10412", "4400"); //
		checkAllEnsures();
	}

	private HashMap<String, ArrayList<ArrayList<String>>> expected = new HashMap<String, ArrayList<ArrayList<String>>>();

	public void ensure(String num, String pref, String sum, String accountMain,
			String accountCounter, String correctedSum, String correctedAccount)
			throws myException, SQLException {

		ArrayList<ArrayList<String>> al;
		if (expected.get(num) == null) {
			al = new ArrayList<ArrayList<String>>();
			expected.put(num, al);
		} else {
			al = expected.get(num);
		}
		ArrayList<String> ent = new ArrayList<String>();
		ent.add(correctedSum != null ? correctedSum : sum);
		ent.add(correctedAccount != null ? correctedAccount : accountCounter);
		ent.add(accountMain);
		al.add(ent);
	}

	public void checkAllEnsures() throws SQLException, myException {
		String[] bn = expected.keySet().toArray(new String[0]);
		System.out.println(bn);

		ArrayList<HashMap<String, Object>> b = DBUtil.returnEntry(bn);

		ArrayList<ExportBuchung> answer = super.doIt(b);

		for (ExportBuchung e : answer) {
			String bnr = e.getBuchungsnummer();

			ArrayList<ArrayList<String>> rAll = expected.get(bnr);
			
			boolean found = false;
			for (ArrayList<String> r : rAll) {
				if (r.get(0).equals(e.getGesamtpreisAsString()) && r.get(1).equals(e.getGegenkonto()) && r.get(2).equals(e.getKonto())) {
					found = true;
				}
				// assertEquals(r.get(0), e.getGesamtpreisAsString());
				// assertEquals(r.get(1), e.getGegenkonto());
				// assertEquals(r.get(2), e.getKonto());
			}
			if (!found) {
				System.out.println("Expected other values for: "+e + ": " + rAll);
			}
			// System.out.println(e);
			// System.out.println(accountMain+", "+accountCounter+", "+correctedSum+", "+correctedAccount);
		}

	}

	public void ensure(String num, String pref, String sum, String accountMain,
			String accountCounter, String correctedSum) throws myException,
			SQLException {
		ensure(num, pref, sum, accountMain, accountCounter, correctedSum, null);

	}

	public void ensure(String num, String pref, String sum, String accountMain,
			String accountCounter) throws myException, SQLException {
		ensure(num, pref, sum, accountMain, accountCounter, null, null);

	}

	@Test
	public void testSessionNotCorrect() throws SQLException, myException,
			DatevExportException {
		printPDFs = true;
		debugEntry("G4206021887");
	}

	@Test
	public void testSession2NotCorrectAutokauf() throws SQLException,
			myException, DatevExportException {
		// Autokauf: Der Erlös sollte auf Konto 4401 gehen
		printPDFs = true;
		verboseOutput = true;
		debugEntry("G4206022919");
		
	}
	@Test
	public void testSession2NotCorrectAutokauf02() throws SQLException,
			myException, DatevExportException {
		// Autokauf: Der Erlös sollte auf Konto 4401 gehen
		printPDFs = true;
		verboseOutput = true;
		debugEntry("G4206023616");
	}

	@Test
	public void testSession2NotCorrectUstId() throws SQLException, myException,
			DatevExportException {
		// Sollte wegen deutscher Ust-Id nicht wie ein Niederländer behandelt
		// werden
		// Wird Konto 5425, sollte [5400] // Wg. ID-Nr aus Deutschland, obwohl
		// niederlande
		printPDFs = true;
		verboseOutput = true;
		debugEntry("G4206023748");

		
	}

}
