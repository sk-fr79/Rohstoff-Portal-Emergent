/*
 * myExportTable.java
 *
 * Created on 11. April 2003, 13:35
 */
package panter.gmbh.basics4project;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.exceptions.myException;



// klasse zur übersetzung der textblöcke innerhalb der anwendung
public class myTranslator
{
    private 	int 						iSaveIntervall 		= 0; // nach sovielen neuen idiomen wird zurückgespeichert
    private 	Vector<String> 				vSaveOriginal 		= new Vector<String>();
    private 	HashMap<String,String>		hmTrans				= new HashMap<String,String>();
    private 	MyDBToolBox 				oDB					= null;
    private		String 						cTO					= null;
    private		String 						cID_USER			= null;
    private		boolean 					bIS_OK 				= false;
    private		boolean 					bIS_TEXTCOLLECTOR 	= false; // wenn true, dann wird die ausdruckstabelle gefüllt
    private		boolean 					bIS_STANDARDSPRACHE = false; // wenn true, dann wird nicht übersetzt
    private		boolean 					bDEBUG 				= false; // prüft, ob die anwendung in debug-mode läuft
    private		String 						cID_SPRACHE 		= "";

    // strings werden als ersatz für zeilenumbrüche in die text geschrieben
    private		String 		cErsatzFuerCR_LF 	= "<crlf>"; // ch + lf = 13 + 10
    private		String 		cErsatzFuerZeilenumbruch = "<br>"; // /n = 10 

    /** Creates a new instance of myTranslator */
    public myTranslator() throws myException
    {

        // jeder translator bekommt ein eigenes MyConnection - Objekt
        try
        {
	        oDB = new MyDBToolBox((MyConnection)bibALL.getSessionValue("CONN_NORMAL"));
	        //oDB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
	        oDB.setZusatzFelder(DB_STATICS.get_DB_ZusatzFelder_STD());
        }
        catch (myException ex) {}

        cTO = 			bibE2.cTO();
        cID_USER = 		bibALL.get_RECORD_USER().get_ID_USER_cUF_NN("");

         
        String cTest = (String) bibALL.getSessionValue("DEBUG");
        if (cTest.equals("0"))
            this.bDEBUG = false;

        String cSQL = "SELECT JD_USER.ID_SPRACHE,JD_USER.TEXTCOLLECTOR,JD_SPRACHE.ISTSTANDARD " + "  FROM " + cTO + ".JD_USER," + cTO + ".JD_SPRACHE " + "  WHERE JD_SPRACHE.ID_SPRACHE=JD_USER.ID_SPRACHE " + "  AND   JD_USER.ID_USER=" + cID_USER;

        String[][] cInfo = oDB.EinzelAbfrageInArray(cSQL, "");

        if (cInfo != null)
        {
            if (cInfo.length > 0)
            {
                this.cID_SPRACHE = cInfo[0][0];

                if (cInfo[0][1].equals("Y"))
                    this.bIS_TEXTCOLLECTOR = true;

                if (cInfo[0][2].equals("Y"))
                    this.bIS_STANDARDSPRACHE = true;

                
                String cSQL2 = "SELECT ";
                if (bibALL.get_DBKENNUNG().equals(DB_META.DB_SAP))
                {
                	cSQL2 = "SELECT " +
							" JD_TEXTKONSTANTE.ORIGINALTEXT, VALUE(JD_TEXTUEBERSETZUNG.UEBERSETZUNG,'') as UEBERSETZUNG FROM " + 
							cTO + ".JD_TEXTKONSTANTE," + cTO + ".JD_TEXTUEBERSETZUNG " +
								" WHERE  " +
								" JD_TEXTKONSTANTE.ID_TEXTKONSTANTE = JD_TEXTUEBERSETZUNG.ID_TEXTKONSTANTE and  JD_TEXTUEBERSETZUNG.ID_SPRACHE =" + this.cID_SPRACHE;

                }
                else if (bibALL.get_DBKENNUNG().equals(DB_META.DB_ORA))
                {
                	cSQL2 = "SELECT " +
					" JD_TEXTKONSTANTE.ORIGINALTEXT, NVL(JD_TEXTUEBERSETZUNG.UEBERSETZUNG,'@@@') as UEBERSETZUNG FROM " + 
					cTO + ".JD_TEXTKONSTANTE," + cTO + ".JD_TEXTUEBERSETZUNG " +
						" WHERE  " +
						" JD_TEXTKONSTANTE.ID_TEXTKONSTANTE = JD_TEXTUEBERSETZUNG.ID_TEXTKONSTANTE and  JD_TEXTUEBERSETZUNG.ID_SPRACHE =" + this.cID_SPRACHE;
                }
                
                String cOrig = "";
                String cTrans = "";

                MyDBResultSet oRS = this.oDB.OpenResultSet(cSQL2);
                if (oRS.RS != null)
                {
                    try
                    {
                        while (oRS.RS.next())
                        {
                            cOrig = oRS.GetStringRS("ORIGINALTEXT");
                            cTrans = oRS.GetStringRS("UEBERSETZUNG");
                            if (cTrans.equals("@@@")) cTrans="";				 // oracle liefert bei nvl( ... ,'') null
                            
                            this.hmTrans.put(cOrig,cTrans);
                        }

                        this.bIS_OK = true;
                    }
                    catch (Exception e)
                    {
                    	e.printStackTrace();
                    }
                }
                else
                {
                    bibALL.WriteLogInfoLine(this.getClass().getName()+"Abfragefehler beim Aufbau der Übersetzungsliste !");
                    bibALL.WriteLogInfoLine("      "+cSQL2);
                }
            }
            else
            {
                bibALL.WriteLogInfoLine("Class myTranslator:\nLeere Rückgabe bei der Abfrage nach Benutzer-Infos !");
                bibALL.WriteLogInfoLine("     " + cSQL);
            }
        }
        else
        {
            bibALL.WriteLogInfoLine("Class myTranslator:\nFehler bei der Abfrage nach Benutzer-Infos !");
            bibALL.WriteLogInfoLine("     " + cSQL);
        }
    }

    
    /**
     * macht die übersetzung
     * @param pOriginal
     * @return
     */
    public String translate(String pOriginal)
    {
        String 		cOriginal = 			pOriginal;
        String 		cSonderzeichenPool = 	"*+-#/1234567890{[()]}?@ ";
        String 		cUebersetzung = 		"";
        boolean 	bNurSonderzeichen = 	true; // spezialfall wenn nur sonderzeichen vorkommen
        String 		cTest = 				"";

        
        if (bibALL.null2leer(cOriginal).equals(""))
            return "";
        
 
        String cRueck = cOriginal;

        
        
        // jetzt sonderfall prüfen: nurSonderZeichen
        for (int i = 0; i < cOriginal.length(); i++)
        {
            cTest = cOriginal.substring(i, i + 1);

            if (cSonderzeichenPool.indexOf(cTest) < 0)
            {
                bNurSonderzeichen = false;

                break; // ende prüfung
            }
        }

        if (!bNurSonderzeichen)
        {
            // cr + lf wird ersetzt durch <crlf>
            cOriginal = bibALL.ReplaceTeilString(cOriginal, "\r\n", this.cErsatzFuerCR_LF);

            // der zeilenumbruch wird ersetzt durch <br>
            cOriginal = bibALL.ReplaceTeilString(cOriginal, "\n", this.cErsatzFuerZeilenumbruch);


            if (this.bIS_TEXTCOLLECTOR || !this.bIS_STANDARDSPRACHE)
            {
                // sonderzeichen wie z.b. * <leerzeichen> usw werden am anfang und am ende rausgefiltert, damit spezielle 
                // markierungen nicht zu doppelten einträgen führen (z.b. * für sortier-button)
                String cSonderzeichenBegin = "";
                String cSonderzeichenEnde = "";

                // jetzt die sonderzeichen vorne und hintern checken
                for (int i = 0; i < cOriginal.length(); i++)
                {
                    cTest = cOriginal.substring(i, i + 1);

                    if (cSonderzeichenPool.indexOf(cTest) > -1)
                        cSonderzeichenBegin += cTest;
                    else
                        break; // ende prüfung

                }

                for (int i = cOriginal.length() - 1; i >= 0; i--)
                {
                    cTest = cOriginal.substring(i, i + 1);

                    if (cSonderzeichenPool.indexOf(cTest) > 0)
                        cSonderzeichenEnde = cTest + cSonderzeichenEnde;
                    else
                        break; // ende prüfung
                }

                // jetzt den inneren bereich checken und übersetzen
                // rechts abschneiden
                cOriginal = cOriginal.substring(0, cOriginal.length() - cSonderzeichenEnde.length());

                // links abschneiden
                cOriginal = cOriginal.substring(cSonderzeichenBegin.length());

                
                
                if (!cOriginal.trim().equals(""))
                {
                    cUebersetzung = (String)this.hmTrans.get(cOriginal);

                    if (cUebersetzung == null)
                    {
                        if (this.bIS_TEXTCOLLECTOR)
                        {
                            /*
                             * in die uebersetzungsmap und den speichervector eintragen
                             */
                            this.hmTrans.put(cOriginal,"");
                            this.vSaveOriginal.add(cOriginal);

                            if (this.vSaveOriginal.size() > this.iSaveIntervall)
                                this.saveNewText(); //neue wörter rausspeichern
                            
                        }

                        // falls keine übersetzung gefunden wurde und der debug-mode aktiv ist,
                        // dann wird ein kenner an die worte angehängt
                        if (this.bDEBUG)
                             cRueck = "$$$" + cRueck;
                    }
                    else
                    {
                        // falls es standardsprache ist, dann wird nicht übersetzt
                        if (!this.bIS_STANDARDSPRACHE)
                        {
                            if (!cUebersetzung.trim().equals(""))
                            {
                                cRueck = cSonderzeichenBegin + cUebersetzung + cSonderzeichenEnde;
                            }
                            else
                            {
                                if (this.bDEBUG)
                                    cRueck = "$$$" + cRueck;
                            }
                        }
                    }
                }
                 // bSpeichern
            }
             // this.bIS_TEXTCOLLECTOR || !this.bIS_STANDARDSPRACHE

            // zeilenumbruch-sondercode wieder rückgängig
            cRueck = bibALL.ReplaceTeilString(cRueck, this.cErsatzFuerZeilenumbruch, "\n");
            cRueck = bibALL.ReplaceTeilString(cRueck, this.cErsatzFuerCR_LF, "\r\n");
        }
         // bSpeichern

        return cRueck;
    }

    private boolean saveNewText()
    {
        Vector<String> vSQL = new Vector<String>();
        String cText = "";
        boolean bRueck = false;

        for (int i = 0; i < this.vSaveOriginal.size(); i++)
        {
            cText = (String) this.vSaveOriginal.get(i);
            
            /*
             * im textsammler werden nur texte gesammelt, die nicht exact so in einer
             * uebersetzungstabelle stehen, damit werden doppelte uebersetzungen verhindert
             */
            String cKontrolle = "SELECT COUNT(*) FROM "+this.cTO+".JD_TEXTUEBERSETZUNG  WHERE UEBERSETZUNG = "+bibALL.MakeSql(cText);
            if (this.oDB.EinzelAbfrage(cKontrolle).toString().equals("0"))
                vSQL.add("INSERT INTO " + this.cTO + ".JD_TEXTKONSTANTE (ID_TEXTKONSTANTE,ORIGINALTEXT) " + "VALUES(SEQ_TEXTKONSTANTE.NEXTVAL," + bibALL.MakeSql(cText) + ")");
            
        }

        if (this.oDB.ExecSQL(vSQL,true))
        {
            bRueck = true;
        }
        else
        {
            // log-info rausschreiben
            bibALL.WriteLogInfoLine("Fehler beim Sichern von gesammelten Textkonstanten ...");

            for (int i = 0; i < vSQL.size(); i++)
            {
                bibALL.WriteLogInfoLine("        ---> " + (String) vSQL.get(i));
            }
        }

        this.vSaveOriginal.removeAllElements(); // elemente werden auf jeden fall geleert

        return bRueck;
    }

    public boolean get_bIS_OK()
    {
        return this.bIS_OK;
    }

    public boolean get_bIS_TEXTCOLLECTOR()
    {
        return this.bIS_TEXTCOLLECTOR;
    }

    public boolean get_bIS_STANDARDSPRACHE()
    {
        return this.bIS_STANDARDSPRACHE;
    }

    public String get_cID_SPRACHE()
    {
        return this.cID_SPRACHE;
    }
}
