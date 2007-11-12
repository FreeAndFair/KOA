package ie.ucd.srg.koa.scheduler.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPScheduledjobHomeBean
 * @generated
 */
public class EJSCMPScheduledjobHomeBean extends EJSHome {
	/**
	 * EJSCMPScheduledjobHomeBean
	 * @generated
	 */
	public EJSCMPScheduledjobHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findScheduledjobByJobtype
	 * @generated
	 */
	public java.util.Enumeration findScheduledjobByJobtype(ie.ucd.srg.koa.scheduler.beans.JobtypeKey inKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
return super.getEnumeration(((ie.ucd.srg.koa.scheduler.beans.EJSFinderScheduledjobBean)persister).findScheduledjobByJobtype(inKey));	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob create(java.math.BigDecimal jobid) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.scheduler.beans.Scheduledjob _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean bean = (ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) beanO.getEnterpriseBean();
			bean.ejbCreate(jobid);
			_EJS_result = (ie.ucd.srg.koa.scheduler.beans.Scheduledjob) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(jobid);
		}
		catch (javax.ejb.CreateException ex) {
			createFailed = true;
			throw ex;
		} catch (java.rmi.RemoteException ex) {
			createFailed = true;
			throw ex;
		} catch (Throwable ex) {
			createFailed = true;
			throw new CreateFailureException(ex);
		} finally {
			if (createFailed) {
				super.createFailure(beanO);
			}
		}
		return _EJS_result;
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob create(java.math.BigDecimal jobid, ie.ucd.srg.koa.scheduler.beans.Jobtype aJobtype, java.sql.Timestamp startTime, java.lang.String status, java.lang.String context, java.lang.Integer priority) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.scheduler.beans.Scheduledjob _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean bean = (ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) beanO.getEnterpriseBean();
			bean.ejbCreate(jobid, aJobtype, startTime, status, context, priority);
			_EJS_result = (ie.ucd.srg.koa.scheduler.beans.Scheduledjob) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(jobid, aJobtype, startTime, status, context, priority);
		}
		catch (javax.ejb.CreateException ex) {
			createFailed = true;
			throw ex;
		} catch (java.rmi.RemoteException ex) {
			createFailed = true;
			throw ex;
		} catch (Throwable ex) {
			createFailed = true;
			throw new CreateFailureException(ex);
		} finally {
			if (createFailed) {
				super.createFailure(beanO);
			}
		}
		return _EJS_result;
	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob findByPrimaryKey(ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.scheduler.beans.EJSJDBCPersisterCMPScheduledjobBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean tmpEJB = (ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) generalEJB;
		ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey keyClass = new ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey();
		keyClass.jobid = tmpEJB.jobid;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey keyFromFields(java.math.BigDecimal f0) {
		ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey keyClass = new ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey();
		keyClass.jobid = f0;
		return keyClass;
	}
}
