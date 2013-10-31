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

package org.springframework.web.servlet.support;

import javax.servlet.ServletException;

/**
 * Exception thrown when a web content generator does not support a
 * specific request method.
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see WebContentGenerator
 */
public class RequestMethodNotSupportedException extends ServletException {

	/**
	 * Create a new RequestMethodNotSupportedException.
	 * @param msg the detail message
	 */
	public RequestMethodNotSupportedException(String msg) {
		super(msg);
	}

}
