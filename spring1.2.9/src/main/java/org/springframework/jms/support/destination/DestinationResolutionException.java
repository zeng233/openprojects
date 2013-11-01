/*
 * Copyright 2002-2005 the original author or authors.
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

package org.springframework.jms.support.destination;

import org.springframework.jms.JmsException;

/**
 * Thrown by DestinationResolver when it cannot resolve a destination name.
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see DestinationResolver
 */
public class DestinationResolutionException extends JmsException {

	public DestinationResolutionException(String msg) {
		super(msg);
	}

	public DestinationResolutionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
