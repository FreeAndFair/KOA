/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminHelperBean.java
  *
  * -----------------------------------------------------------------------
  * 
  *  (c) 2003  Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
  *
  *  Project		: Kiezen Op Afstand (KOA)
  *  Project Number	: ECF-2651
  *  
  *  History:
  *  Version	Date		Name		Reason
  * ---------------------------------------------------------
  *  0.1		09-04-2003	MRo			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.databeheer.ejb.session;
import java.rmi.RemoteException;
import javax.ejb.*;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.FunctionalProps;
import ie.ucd.srg.koa.exception.KOADataBeheerException;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.koaschema.KandidaatcodesHome;
import ie.ucd.srg.koa.koaschema.Kieskringen;
import ie.ucd.srg.koa.koaschema.KieskringenHome;
import ie.ucd.srg.koa.koaschema.KieskringenKey;
import ie.ucd.srg.koa.koaschema.Kieslijsten;
import ie.ucd.srg.koa.koaschema.KieslijstenHome;
import ie.ucd.srg.koa.koaschema.KieslijstenKey;
import ie.ucd.srg.koa.koaschema.Lijstposities;
import ie.ucd.srg.koa.koaschema.LijstpositiesHome;
import ie.ucd.srg.koa.security.RandomGenerator;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.utils.ObjectCache;
/**
 * Bean implementation class for Enterprise Bean: KieslijstAdminHelper
 * 
 * This bean is a helper bean for the KieslijstAdminBean and takes care
 * of al the methodes that needs "requersNew" transaction
 * inserting a Kieslijst
 * inserting a LijstPostitie
 * 
 */
public class KieslijstAdminHelperBean implements javax.ejb.SessionBean
{
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext()
	{
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx)
	{
		mySessionCtx = ctx;
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate()
	{
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException
	{
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate()
	{
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove()
	{
	}
	/**
	 * Searches a kieskring and for that kieskring
	 * it inserts a kieslijst in the database
	 * 
	 * @param sKiesKringNr kiesking nummer
	 * @param sKieslijstNr kieslijst nummer
	 * @param sLijstnaam kieslijst naam
	 * @return Kieslijsten returns the new Kieslijst
	 */
	public Kieslijsten insertKieslijst(
		String sKiesKringNr,
		String sKieslijstNr,
		String sLijstnaam)
		throws KOAException
	{
		try
		{
			KieskringenHome xKringHome =
				ObjectCache.getInstance().getKieskringenHome();
			KieskringenKey xKringKey = new KieskringenKey(sKiesKringNr);
			Kieskringen xKieskring = xKringHome.findByPrimaryKey(xKringKey);
			KieslijstenHome xLijstHome =
				ObjectCache.getInstance().getKieslijstenHome();
			return xLijstHome.create(sKieslijstNr, xKieskring, sLijstnaam);
		}
		catch (FinderException fe)
		{
			throw new KOADataBeheerException(
				KOADataBeheerException.KIESKRING_NOT_FOUND);
		}
		catch (RemoteException ne)
		{
			String[] params = { "Kieslijsten and Kieskringen" };
			KOALogHelper.logErrorCode(
				"KieslijstAdminHelperBean",
				ErrorConstants.ERR_REMOTE,
				params,
				ne);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION);
		}
		catch (CreateException ce)
		{
			// function exception
			throw new KOADataBeheerException(
				KOADataBeheerException.CREATE_KIESLIJST_EXCETION);
		}
	}
	/**
	 * Inserts a lijstpositie and generates some kandidaat codes
	 * 
	 * @param xKieslijst kieslijst on wich the posistie is inserted
	 * @param sPositieNr positie nummer 
	 * @param sAchternaam achternaam of the posistie
	 * @param sVoorletters voorletters of the posistie
	 * @param sRoepnaam roepnaam of the posistie
	 * @param sGeslacht geslacht of the posistie
	 * @param sWoonplaats woonplaats of the posistie
	 */
	public void insertLijstPostitie(
		Kieslijsten xKieslijst,
		String sPositieNr,
		String sAchternaam,
		String sVoorletters,
		String sRoepnaam,
		char sGeslacht,
		String sWoonplaats)
		throws KOAException
	{
		try
		{
			KieslijstenKey xLijstKey =
				(KieslijstenKey) xKieslijst.getPrimaryKey();
			String sKieslijstNr = xLijstKey.kieslijstnummer;
			String sKieskringNr = xLijstKey.fk_kkr_1_kieskringnummer;
			LijstpositiesHome xLijstHome =
				ObjectCache.getInstance().getLijstpositiesHome();
			Lijstposities xLijst =
				xLijstHome.create(
					sPositieNr,
					xKieslijst,
					sAchternaam,
					sVoorletters,
					sRoepnaam,
					sGeslacht,
					sWoonplaats);
			KandidaatcodesHome xKandidaat =
				ObjectCache.getInstance().getKandidaatcodesHome();
			int numberOfCodes =
				FunctionalProps.getIntProperty(
					FunctionalProps.KANDIDAAT_CODE_NUMBER);
			RandomGenerator random = RandomGenerator.getInstance();
			String sStr = null;
			boolean create = false;
			for (int i = 0; i < numberOfCodes; i++)
			{
				create = false;
				sStr = null;
				while (!create)
				{
					try
					{
						sStr = random.getKandidaatCode();
						xKandidaat.create(
							sStr,
							sPositieNr,
							sKieslijstNr,
							sKieskringNr);
						create = true;
					}
					catch (DuplicateKeyException dke)
					{
						KOALogHelper.log(
							KOALogHelper.INFO,
							"[KieslijstAdminHelperBean] DuplicateKeyException KandidaatCode");
					}
				}
			}
		}
		catch (CreateException ce)
		{
			throw new KOADataBeheerException(
				KOADataBeheerException.CREATE_LIJSTPOS_EXCETION);
		}
		catch (RemoteException ne)
		{
			String[] params = { "Lijstposities and Kandidaatcodes" };
			KOALogHelper.logErrorCode(
				"KieslijstAdminHelperBean",
				ErrorConstants.ERR_REMOTE,
				params,
				ne);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION);
		}
	}
}