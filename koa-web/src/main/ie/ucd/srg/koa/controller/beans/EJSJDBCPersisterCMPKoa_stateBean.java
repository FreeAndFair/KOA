package ie.ucd.srg.koa.controller.beans;
import ie.ucd.srg.ejs.persistence.*;
import javax.ejb.EntityBean;
import java.sql.*;

/**
 * EJSJDBCPersisterCMPKoa_stateBean
 * @generated
 */
public class EJSJDBCPersisterCMPKoa_stateBean extends EJSJDBCPersister implements ie.ucd.srg.koa.controller.beans.EJSFinderKoa_stateBean {
	/**
	 * @generated
	 */
	private static final String _createString = "INSERT INTO KOA01.KOA_STATE (ID, CURRENT_STATE) VALUES (?, ?)";
	/**
	 * @generated
	 */
	private static final String _removeString = "DELETE FROM KOA01.KOA_STATE  WHERE ID = ?";
	/**
	 * @generated
	 */
	private static final String _storeString = "UPDATE KOA01.KOA_STATE  SET CURRENT_STATE = ? WHERE ID = ?";
	/**
	 * @generated
	 */
	private static final String _loadString = "SELECT T1.ID, T1.CURRENT_STATE FROM KOA01.KOA_STATE  T1 WHERE T1.ID = ?";
	/**
	 * @generated
	 */
	private static final String _loadForUpdateString = _loadString + " FOR UPDATE";
	/**
	 * @generated
	 */
	private byte[] serObj = null;
	/**
	 * EJSJDBCPersisterCMPKoa_stateBean
	 * @generated
	 */
	//@ signals (Exception) false;
	public EJSJDBCPersisterCMPKoa_stateBean() throws java.rmi.RemoteException {
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
	//@ requires eb != null;
	//@ signals (Exception) false;
	public void _create(EntityBean eb) throws Exception {
		Object objectTemp = null;
		Koa_stateBean b = (Koa_stateBean) eb;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_createString);
		try {
			if (b.id == null) {
				pstmt.setNull(1, java.sql.Types.INTEGER);
			}
			else {
				pstmt.setInt(1, b.id.intValue());
			}
			if (b.current_state == null) {
				pstmt.setNull(2, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(2, b.current_state);
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
	//@ requires eb != null;
	//@ requires data != null;
	//@ requires pKey != null;
	//@ signals (Exception) false;
	public void hydrate(EntityBean eb, Object data, Object pKey) throws Exception {
		Object objectTemp = null;
		Koa_stateBean b = (Koa_stateBean) eb;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey _primaryKey = (ie.ucd.srg.koa.controller.beans.Koa_stateKey)pKey;
		java.sql.ResultSet resultSet = (java.sql.ResultSet) data;
		b.id = _primaryKey.id;
		b.current_state = resultSet.getString(2);
	}
	/**
	 * load
	 * @generated
	 */
	//@ requires eb != null;
	//@ requires pKey != null;
	//@ signals (Exception) false;
	public void load(EntityBean eb, Object pKey, boolean forUpdate) throws Exception {
		Object objectTemp = null;
		Koa_stateBean b = (Koa_stateBean) eb;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey _primaryKey = (ie.ucd.srg.koa.controller.beans.Koa_stateKey)pKey;
		PreparedStatement pstmt;
		ResultSet resultSet = null;
		pstmt = (forUpdate) ?
			getPreparedStatement(_loadForUpdateString):
			getPreparedStatement(_loadString);
		try {
			if (_primaryKey.id == null) {
				pstmt.setNull(1, java.sql.Types.INTEGER);
			}
			else {
				pstmt.setInt(1, _primaryKey.id.intValue());
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
	//@ requires eb != null;
	//@ signals (Exception) false;
	public void refresh(EntityBean eb, boolean forUpdate) throws Exception {
		Koa_stateBean b = (Koa_stateBean) eb;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey _primaryKey = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		_primaryKey.id = b.id;
		load(b, _primaryKey, forUpdate);
	}
	/**
	 * store
	 * @generated
	 */
	//@ requires eb != null;
	//@ signals (Exception) false;
	public void store(EntityBean eb) throws Exception {
		Object objectTemp = null;
		Koa_stateBean b = (Koa_stateBean) eb;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey _primaryKey = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		_primaryKey.id = b.id;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_storeString);
		try {
			if (_primaryKey.id == null) {
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}
			else {
				pstmt.setInt(2, _primaryKey.id.intValue());
			}
			if (b.current_state == null) {
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				pstmt.setString(1, b.current_state);
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
	//@ requires eb != null;
	//@ signals (Exception) false;
	public void remove(EntityBean eb) throws Exception {
		Object objectTemp = null;
		Koa_stateBean b = (Koa_stateBean) eb;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey _primaryKey = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		_primaryKey.id = b.id;
		PreparedStatement pstmt;
		pstmt = getPreparedStatement(_removeString);
		try {
			if (_primaryKey.id == null) {
				pstmt.setNull(1, java.sql.Types.INTEGER);
			}
			else {
				pstmt.setInt(1, _primaryKey.id.intValue());
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
	//@ requires data != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public Object getPrimaryKey(Object data) throws Exception {
		ie.ucd.srg.koa.controller.beans.Koa_stateKey key = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		java.sql.ResultSet resultSet = (java.sql.ResultSet) data;

		if (resultSet != null) {
			Object objectTemp = null;
			int intTemp;
			intTemp = resultSet.getInt(1);
			key.id = resultSet.wasNull() ? null : new Integer(intTemp);
			return key;
		}
		return null;
	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	//@ requires primaryKey != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public ie.ucd.srg.koa.controller.beans.Koa_state findByPrimaryKey(ie.ucd.srg.koa.controller.beans.Koa_stateKey primaryKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
		return (ie.ucd.srg.koa.controller.beans.Koa_state) home.activateBean(primaryKey);
	}
}
