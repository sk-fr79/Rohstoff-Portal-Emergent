package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import panter.gmbh.indep.exceptions.myException;

/**
 * hat ein listselektor einen status, wo keine einschraenkung von ihm ausgeht,
 * dann kann man diesem einen  XX_ListSelektor_NeutralSetter uebergeben, der dann von aussen 
 * via durchlaufen der E2_SelectionComponentsVector - eintrage abgearbeitet werden kann.
 * Damit kann eine komplette Selektion auf "alles anzeigen" geschaltet werden
 * @author martin
 *
 */
public abstract class XX_ListSelektor_Neutralisator
{
	public abstract void set_to_Neutral() throws myException;
}
