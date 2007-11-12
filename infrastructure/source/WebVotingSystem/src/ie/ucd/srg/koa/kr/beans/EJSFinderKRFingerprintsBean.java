package ie.ucd.srg.koa.kr.beans;
/*import ie.ucd.srg.ejs.persistence.EJSFinder;
import javax.ejb.FinderException;
import java.rmi.RemoteException;*/
/**
 * EJSFinderKRFingerprintsBean
 * @generated
 */
public interface EJSFinderKRFingerprintsBean {
	/**
	 * findLatestFingerprint
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRFingerprints findLatestFingerprint(java.lang.Integer type) throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findLastDynamicFP
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRFingerprints findLastDynamicFP(java.lang.Integer type, java.lang.String firstState, java.lang.String secondState) throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findByTypeAndState
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRFingerprints findByTypeAndState(java.lang.Integer type, java.lang.String systemstatus) throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * findLastFP
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRFingerprints findLastFP() throws javax.ejb.FinderException, java.rmi.RemoteException;
}
