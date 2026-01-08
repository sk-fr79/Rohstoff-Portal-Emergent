package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;
/**
 * The DATEV exporter module, part of the FIBU suite.
 * 
 * Provides functionality for selecting date ranges over the JT_VKOPF_RG and JT_VPOS_RG
 * entries (Rechnungspositionen) that are finalized and ready to be exported 
 * and imported into Datev automatically. 
 * 
 * The UI is basically the same as in the rest of the portal (list/mask logics).
 * The main interaction happends in the {@see FIBU_EXPORT_LIST_BedienPanel} class,
 * where some {@see ExportProperties} classes are generated, which is then passed
 * to the {@see DatevExporter}, which includes the main business logic.
 * 
 * Within {@link DatevExporter#bookEntriesFromDatabase(java.util.ArrayList)},
 * every VPOS is encapsulated in an {@see ExportBuchung} object, and then passed
 * to an {@link AccountFinder}, which assigns it an account.  
 * 
 * The general flow is:
 * 
 * 1. The user chooses a timespan for the data to be exported.
 * 2. Upon execution, this fills the JT_VKOPF_EXPORT_RG and JT_VPOS_EXPORT_RG
 *    tables with the relevant data for the export. This is basically copies
 *    of the IDs of the underlying JT_VKOPF_RG and JT_VPOS_RG tables. It generates
 *    an export ID and finds out the Datev account numbers according to the
 *    rules specified by JT_FIBU_KONTENREGEL_NEU tables and the therein referenced
 *    filter (JT_FILTER). 
 * 3. The resulting list is printed to the user within the portal. The user
 *    can now check the results.
 * 4. After this, the export id is given back to the {@see DatevExporter}, 
 *    and all the data is printed to some CSV files. These will usually then
 *    be transferred with SCP ("printed").
 * 
 * 
 * All the database logic happens in {@see DBUtil} class.
 * 
 * Please see the {@see Config} class for configuring and maintaining this module
 * over the time. The configuration flags described there have an influence on which
 * data is being taken into account by the exporter.
 * 
 * @author nilsandre
 */