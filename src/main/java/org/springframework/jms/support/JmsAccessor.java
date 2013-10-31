/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.jms.support;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Constants;
import org.springframework.jms.JmsException;

/**
 * Base class for JmsTemplate and other JMS-accessing gateway helpers,
 * defining common properties such as the ConnectionFactory. The subclass
 * JmsDestinationAccessor adds further, destination-related properties.
 *
 * <p>Not intended to be used directly. See JmsTemplate.
 *
 * @author Juergen Hoeller
 * @since 1.2
 * @see org.springframework.jms.support.destination.JmsDestinationAccessor
 * @see org.springframework.jms.core.JmsTemplate
 */
public abstract class JmsAccessor implements InitializingBean {

	/** Constants instance for javax.jms.Session */
	private static final Constants constants = new Constants(Session.class);


	protected final Log logger = LogFactory.getLog(getClass());

	private ConnectionFactory connectionFactory;

	private boolean sessionTransacted = false;

	private int sessionAcknowledgeMode = Session.AUTO_ACKNOWLEDGE;


	/**
	 * Set the ConnectionFactory used for obtaining JMS Connections.
	 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * Return the ConnectionFactory that this accessor uses for
	 * obtaining JMS Connections.
	 */
	public ConnectionFactory getConnectionFactory() {
		return this.connectionFactory;
	}

	/**
	 * Set the transaction mode that is used when creating a JMS Session.
	 * Default is "false".
	 * <p>Note that within a JTA transaction, the parameters passed to
	 * <code>create(Queue/Topic)Session(boolean transacted, int acknowledgeMode)</code>
	 * method are not taken into account. Depending on the J2EE transaction context,
	 * the container makes its own decisions on these values. Analogously, these
	 * parameters are not taken into account within a locally managed transaction
	 * either, since the accessor operates on an existing JMS Session in this case.
	 * @param sessionTransacted the transaction mode
	 * @see javax.jms.Connection#createSession(boolean, int)
	 */
	public void setSessionTransacted(boolean sessionTransacted) {
		this.sessionTransacted = sessionTransacted;
	}

	/**
	 * Return whether the JMS Sessions used are transacted.
	 */
	public boolean isSessionTransacted() {
		return this.sessionTransacted;
	}

	/**
	 * Set the JMS acknowledgement mode by the name of the corresponding constant
	 * in the JMS Session interface, e.g. "CLIENT_ACKNOWLEDGE".
	 * @param constantName name of the constant
	 * @see javax.jms.Session#AUTO_ACKNOWLEDGE
	 * @see javax.jms.Session#CLIENT_ACKNOWLEDGE
	 * @see javax.jms.Session#DUPS_OK_ACKNOWLEDGE
	 * @see javax.jms.Connection#createSession(boolean, int)
	 */
	public void setSessionAcknowledgeModeName(String constantName) {
		setSessionAcknowledgeMode(constants.asNumber(constantName).intValue());
	}

	/**
	 * Set the JMS acknowledgement mode that is used when creating a JMS Session.
	 * Vendor extensions to the acknowledgment mode can be set here as well.
	 * Default is "AUTO_ACKNOWLEDGE".
	 * <p>Note that that inside an EJB the parameters to
	 * create(Queue/Topic)Session(boolean transacted, int acknowledgeMode) method are not
	 * taken into account. Depending on the transaction context in the EJB, the container
	 * makes its own decisions on these values. See section 17.3.5 of the EJB Spec.
	 * @param sessionAcknowledgeMode the acknowledgement mode
	 * @see javax.jms.Session#AUTO_ACKNOWLEDGE
	 * @see javax.jms.Session#CLIENT_ACKNOWLEDGE
	 * @see javax.jms.Session#DUPS_OK_ACKNOWLEDGE
	 * @see javax.jms.Connection#createSession(boolean, int)
	 */
	public void setSessionAcknowledgeMode(int sessionAcknowledgeMode) {
		this.sessionAcknowledgeMode = sessionAcknowledgeMode;
	}

	/**
	 * Return the acknowledgement mode for JMS Sessions.
	 */
	public int getSessionAcknowledgeMode() {
		return this.sessionAcknowledgeMode;
	}

	public void afterPropertiesSet() {
		if (this.connectionFactory == null) {
			throw new IllegalArgumentException("connectionFactory is required");
		}
	}


	/**
	 * Convert the specified checked {@link javax.jms.JMSException JMSException} to
	 * a Spring runtime {@link org.springframework.jms.JmsException JmsException}
	 * equivalent.
	 * <p>Default implementation delegates to JmsUtils.
	 * @param ex the original checked JMSException to convert
	 * @return the Spring runtime JmsException wrapping <code>ex</code>
	 * @see org.springframework.jms.support.JmsUtils#convertJmsAccessException
	 */
	protected JmsException convertJmsAccessException(JMSException ex) {
		return JmsUtils.convertJmsAccessException(ex);
	}

}
