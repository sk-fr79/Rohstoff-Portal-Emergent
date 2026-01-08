package panter.gmbh.Echo2.components.MaskSearchField;

import panter.gmbh.indep.exceptions.myException;


/**
 * im gegensatz zu XX_MaskActionAfterFoundNonDB wird dieses objekt immer beim fuellen herangezogen, nicht nur nach einer Suche
 * die mit einem Klick abgeschlossen wird. Damit kann eine Maske beeinflusst werden, wenn Sie via Programmcode gefuellt wird
 * @author martin
 *
 */
public abstract class XX_DoSomeThingAfterComponentIsFilled
{
	public abstract void doSomeThingAfterComponentIsFilled(MyE2_MaskSearchField ownSearchField, String cActualMaskValue) throws myException;
}
