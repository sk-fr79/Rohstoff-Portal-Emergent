package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_FuhrenPreisInfos extends XXX_ViewBuilder
{
	
	/*
	 * View sucht die moeglichen Preise aus den quellen
	 * RG-Position zu der Fuhre/Fuhrenort oder
	 * Lagerpreis  (kann fur die unbestimmten fuhren benutzt werden)
	 */
	
	
	
	@Override
	public boolean build_View_forAll_Mandants() throws myException
	{
		
		RECLIST_MANDANT  oRecList = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		String c_SQL =  	" CREATE OR REPLACE VIEW A#MANDANT#_FUHRENPREISE AS ("+
							" SELECT * FROM "+
							" ( "+
							"  "+
							" SELECT FU.ID_VPOS_TPA_FUHRE,ID_MANDANT,NULL AS ID_VPOS_TPA_FUHRE_ORT, 'EK' AS EK_VK, ANTEIL_LADEMENGE_LIEF AS ANTEIL_MENGE, "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+ 
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=1 ))  AS PREIS_AUS_RG, "+
							"  "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_RESULT_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+ 
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=1 ))  AS PREIS_RESULT_AUS_RG, "+
							"  "+
							"  "+
							"  (SELECT "+
							"             PREIS "+
							"       FROM "+
							"             JT_LAGER_KONTO LG "+
							"       WHERE  LG.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE "+
							"                AND   LG.ID_VPOS_TPA_FUHRE_ORT IS NULL "+
							"                AND   NVL(LG.STORNO,'N')='N' "+
							"                AND   NVL(LG.BUCHUNGSTYP,'--')='WE' "+
							"                AND   LG.ID_LAGER_KONTO = ( "+
							"                SELECT MAX(ID_LAGER_KONTO) "+
							"                     FROM "+
							"                           JT_LAGER_KONTO  LGK "+
							"                     WHERE LGK.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE "+
							"                      AND   LGK.ID_VPOS_TPA_FUHRE_ORT IS NULL "+
							"                      AND   NVL(LGK.STORNO,'N')='N' "+
							"                      AND   NVL(LGK.BUCHUNGSTYP,'--')='WE')) AS LAGER_PREIS "+
							"  "+
							" FROM JT_VPOS_TPA_FUHRE  FU WHERE FU.ID_MANDANT=#MANDANT# "+
							"  "+
							"  "+
							"  "+
							" UNION "+
							"  "+
							"  "+
							"  "+
							" SELECT FU.ID_VPOS_TPA_FUHRE, ID_MANDANT, NULL AS ID_VPOS_TPA_FUHRE_ORT,  'VK' AS EK_VK, ANTEIL_ABLADEMENGE_ABN AS ANTEIL_MENGE, "+
							"  "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=-1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+ 
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=-1 ))  AS PREIS_AUS_RG, "+
							"  "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_RESULT_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=-1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FU.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL "+ 
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=-1 ))  AS PREIS_RESULT_AUS_RG, "+
							"  "+
							"  "+
							"  "+
							"  (SELECT "+
							"             PREIS "+
							"       FROM "+
							"             JT_LAGER_KONTO LG "+
							"       WHERE  LG.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE "+
							"                AND   LG.ID_VPOS_TPA_FUHRE_ORT IS NULL "+
							"                AND   NVL(LG.STORNO,'N')='N' "+
							"                AND   NVL(LG.BUCHUNGSTYP,'--')='WA' "+
							"                AND   LG.ID_LAGER_KONTO = ( "+
							"                SELECT MAX(ID_LAGER_KONTO) "+
							"                    FROM "+
							"                           JT_LAGER_KONTO  LGK "+
							"                   WHERE LGK.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE "+
							"                   AND   LGK.ID_VPOS_TPA_FUHRE_ORT IS NULL "+
							"                   AND   NVL(LGK.STORNO,'N')='N' "+
							"                   AND   NVL(LGK.BUCHUNGSTYP,'--')='WA')) AS LAGER_PREIS "+
							"  "+
							"  "+
							"  "+
							"  "+
							" FROM JT_VPOS_TPA_FUHRE  FU WHERE FU.ID_MANDANT=#MANDANT# "+
							"  "+
							"  "+
							"  "+
							" UNION "+
							"  "+
							"  "+
							" SELECT FUO.ID_VPOS_TPA_FUHRE, ID_MANDANT, FUO.ID_VPOS_TPA_FUHRE_ORT,'EK' AS EK_VK, ANTEIL_LADEMENGE AS ANTEIL_MENGE, "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=1 ))  AS PREIS_AUS_RG, "+
							"  "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_RESULT_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=1 ))  AS PREIS_RESULT_AUS_RG, "+
							"  "+
							"  "+
							"  (SELECT "+
							"             PREIS "+
							"       FROM "+
							"             JT_LAGER_KONTO LG "+
							"       WHERE  LG.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE "+
							"        AND   LG.ID_VPOS_TPA_FUHRE_ORT =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"        AND   NVL(LG.STORNO,'N')='N' "+
							"        AND   NVL(LG.BUCHUNGSTYP,'--')='WE' "+
							"        AND   LG.ID_LAGER_KONTO = ( "+
							"                    SELECT MAX(ID_LAGER_KONTO) "+
							"                     FROM "+
							"                           JT_LAGER_KONTO  LGK "+
							"                     WHERE LGK.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE "+
							"                      AND   LGK.ID_VPOS_TPA_FUHRE_ORT =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                      AND   NVL(LGK.STORNO,'N')='N' "+
							"                      AND   NVL(LGK.BUCHUNGSTYP,'--')='WE')) AS LAGER_PREIS "+
							"  "+
							" FROM JT_VPOS_TPA_FUHRE_ORT  FUO "+
							" WHERE FUO.DEF_QUELLE_ZIEL='EK' "+
							" AND   FUO.ID_MANDANT=#MANDANT# "+
							"  "+
							"  "+
							" UNION "+
							"  "+
							"  "+
							"  "+
							" SELECT FUO.ID_VPOS_TPA_FUHRE, ID_MANDANT, FUO.ID_VPOS_TPA_FUHRE_ORT,'VK' AS EK_VK, ANTEIL_ABLADEMENGE AS ANTEIL_MENGE, "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=-1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=-1 ))  AS PREIS_AUS_RG, "+
							"  "+
							"  "+
							" (SELECT "+
							"          (CASE WHEN JT_VPOS_RG.ANZAHL>0 THEN EINZELPREIS_RESULT_FW  ELSE NULL END) "+
							"      FROM "+
							"         JT_VPOS_RG "+
							"     WHERE  JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"     AND       JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"     AND       NVL(JT_VPOS_RG.DELETED,'N')='N' "+
							"     AND       JT_VPOS_RG.LAGER_VORZEICHEN=-1 "+
							"     AND       JT_VPOS_RG.ID_VPOS_RG = ( "+
							"               SELECT MAX(ID_VPOS_RG) FROM JT_VPOS_RG VP "+
							"                 WHERE   VP.ID_VPOS_TPA_FUHRE_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE "+
							"                 AND        VP.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD=FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                 AND        NVL(VP.DELETED,'N')='N' "+
							"                 AND        VP.LAGER_VORZEICHEN=-1 ))  AS PREIS_RESULT_AUS_RG, "+
							"  "+
							"  "+
							"  (SELECT "+
							"             PREIS "+
							"       FROM "+
							"             JT_LAGER_KONTO LG "+
							"       WHERE  LG.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE "+
							"        AND   LG.ID_VPOS_TPA_FUHRE_ORT =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"        AND   NVL(LG.STORNO,'N')='N' "+
							"        AND   NVL(LG.BUCHUNGSTYP,'--')='WA' "+
							"        AND   LG.ID_LAGER_KONTO = ( "+
							"                    SELECT MAX(ID_LAGER_KONTO) "+
							"                     FROM "+
							"                           JT_LAGER_KONTO  LGK "+
							"                     WHERE LGK.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE "+
							"                      AND   LGK.ID_VPOS_TPA_FUHRE_ORT =FUO.ID_VPOS_TPA_FUHRE_ORT "+
							"                      AND   NVL(LGK.STORNO,'N')='N' "+
							"                      AND   NVL(LGK.BUCHUNGSTYP,'--')='WA')) AS LAGER_PREIS "+
							"  "+
							" FROM JT_VPOS_TPA_FUHRE_ORT  FUO "+
							" WHERE FUO.DEF_QUELLE_ZIEL='VK' "+
							" AND   FUO.ID_MANDANT=#MANDANT# "+
							"  "+
							"  "+
							" ) "+
//							" WHERE PREIS_AUS_RG>0 OR PREIS_RESULT_AUS_RG>0 OR LAGER_PREIS>0 "+
							")";

									

		for (int i=0;i<oRecList.get_vKeyValues().size();i++)
		{
			
			String cSQL = bibALL.ReplaceTeilString(c_SQL,"#MANDANT#",oRecList.get(i).get_ID_MANDANT_cUF());
			
			
			if (bibDB.ExecSQL(cSQL, true))
			{
				MyE2_String cInfo = new MyE2_String("Fuhrenpreis-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> erfolgreich erstellt !",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Fuhrenpreis-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> KONNTE NICHT ERSTELLT WERDEN !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		
		return false;
	}

	
	
	
	
	
}
