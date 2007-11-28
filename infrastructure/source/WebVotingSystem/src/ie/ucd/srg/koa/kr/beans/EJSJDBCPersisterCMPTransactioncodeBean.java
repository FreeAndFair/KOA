package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.persistence.*;
import javax.ejb.EntityBean;
import java.sql.*;

/**
 * EJSJDBCPersisterCMPTransactioncodeBean
 * @generated
 */
public class EJSJDBCPersisterCMPTransactioncodeBean extends EJSJDBCPersister implements ie.ucd.srg.koa.kr.beans.EJSFinderTransactioncodeBean {
	/**
	 * @generated
	 */
	private static final String _createString = "INSERT INTO KOA01.TRANSACTIONCODE (TRANSACTIENUMMER, ALREADYUSED) VALUES (?, ?)";
	/**
	 * @generated
	 */
	private static final String _removeString = "DELETE FROM KOA01.TRANSACTIONCODE  WHERE TRANSACTIENUMMER = ?";
	/**
	 * @generated
	 */
	private static final String _storeString = "UPDATE KOA01.TRANSACTIONCODE  SET ALREADYUSED = ? WHERE TRANSACTIENUMMER = ?";
	/**
	 * @generated
	 */
	private static final String _loadString = "SELECT T1.TRANSACTIENUMMER, T1.ALREADYUSED FROM KOA01.TRANSACTIONCODE  T1 WHERE T1.TRANSACTIENUMMER = ?";
	/**
	 * @generated
	 */
	private static final String _loadForUpdateString = _loadString + " FOR UPDATE";
	/**
	 * @generated
	 */
	private byte[] serObj = null;
	/**
	 * EJSJDBCPersisterCMPTransactioncodeBean
	 * @generated
	 */
	public EJSJDBCPersisterCMPTransactioncodeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * postInit
	 * @generated
	 */
	public void postInit() {
	}
	/**
	 * _create
	 * @generated
	 */
	public void _create(EntityBean eb) throws Exception {
		Object objectTemp = null;
		TransactioncodeBean b = (TransactioncodeBean) eb;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_createString);
		try {
			if (b.transactienummer == null) {
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(1, b.transactienummer);
			}
			objectTemp = ie.ucd.srg.vap.converters.VapCharToBoolean.singleton().dataFrom(new Boolean(b.alreadyused));
			if (objectTemp == null) {
				pstmt.setNull(2, java.sql.Types.CHAR);
			}
			else {
				pstmt.setString(2, (java.lang.String)objectTemp);
			}
			pstmt.executeUpdate();
		}
		finally {
			returnPreparedStatement(pstmt);
		}
	}
	/**
	 * hydrate
	 * @generated
	 */
	public void hydrate(EntityBean eb, Object data, Object pKey) throws Exception {
		Object objectTemp = null;
		TransactioncodeBean b = (TransactioncodeBean) eb;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey _primaryKey = (ie.ucd.srg.koa.kr.beans.TransactioncodeKey)pKey;
		java.sql.ResultSet resultSet = (java.sql.ResultSet) data;
		b.transactienummer = _primaryKey.transactienummer;
		objectTemp = ie.ucd.srg.vap.converters.VapCharToBoolean.singleton().objectFrom(resultSet.getString(2));
		b.alreadyused = (objectTemp == null) ? false : ((Boolean)objectTemp).booleanValue();
	}
	/**
	 * load
	 * @generated
	 */
	public void load(EntityBean eb, Object pKey, boolean forUpdate) throws Exception {
		Object objectTemp = null;
		TransactioncodeBean b = (TransactioncodeBean) eb;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey _primaryKey = (ie.ucd.srg.koa.kr.beans.TransactioncodeKey)pKey;
		PreparedStatement pstmt;
		ResultSet resultSet = null;
		pstmt = (forUpdate) ?
			getPreparedStatement(_loadForUpdateString):
			getPreparedStatement(_loadString);
		try {
			if (_primaryKey.transactienummer == null) {
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(1, _primaryKey.transactienummer);
			}
			resultSet = pstmt.executeQuery();
			if (!(resultSet.next())) throw new javax.ejb.ObjectNotFoundException();
			hydrate(eb, resultSet, pKey);
		}
		finally {
			if (resultSet != null) resultSet.close();
			returnPreparedStatement(pstmt);
		}
	}
	/**
	 * refresh
	 * @generated
	 */
	public void refresh(EntityBean eb, boolean forUpdate) throws Exception {
		TransactioncodeBean b = (TransactioncodeBean) eb;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey _primaryKey = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		_primaryKey.transactienummer = b.transactienummer;
		load(b, _primaryKey, forUpdate);
	}
	/**
	 * store
	 * @generated
	 */
	public void store(EntityBean eb) throws Exception {
		Object objectTemp = null;
		TransactioncodeBean b = (TransactioncodeBean) eb;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey _primaryKey = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		_primaryKey.transactienummer = b.transactienummer;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_storeString);
		try {
			if (_primaryKey.transactienummer == null) {
				pstmt.setNull(2, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(2, _primaryKey.transactienummer);
			}
			objectTemp = ie.ucd.srg.vap.converters.VapCharToBoolean.singleton().dataFrom(new Boolean(b.alreadyused));
			if (objectTemp == null) {
				pstmt.setNull(1, java.sql.Types.CHAR);
			}
			else {
				pstmt.setString(1, (java.lang.String)objectTemp);
			}
			pstmt.executeUpdate();
		}
		finally {
			returnPreparedStatement(pstmt);
		}
	}
	/**
	 * remove
	 * @generated
	 */
	public void remove(EntityBean eb) throws Exception {
		Object objectTemp = null;
		TransactioncodeBean b = (TransactioncodeBean) eb;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey _primaryKey = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		_primaryKey.transactienummer = b.transactienummer;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_removeString);
		try {
			if (_primaryKey.transactienummer == null) {
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(1, _primaryKey.transactienummer);
			}
			pstmt.executeUpdate();
		}
		finally {
			returnPreparedStatement(pstmt);
		}
	}
	/**
	 * getPrimaryKey
	 * @generated
	 */
	public Object getPrimaryKey(Object data) throws Exception {
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey key = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		java.sql.ResultSet resultSet = (java.sql.ResultSet) data;

		if (resultSet != null) {
			Object objectTemp = null;
			key.transactienummer = resultSet.getString(1);
			return key;
		}
		return null;
	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Transactioncode findByPrimaryKey(ie.ucd.srg.koa.kr.beans.TransactioncodeKey primaryKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
		return (ie.ucd.srg.koa.kr.beans.Transactioncode) home.activateBean(primaryKey);
	}
}
