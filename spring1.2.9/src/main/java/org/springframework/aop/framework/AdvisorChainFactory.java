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

package org.springframework.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Factory interface for advisor chains.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public interface AdvisorChainFactory extends AdvisedSupportListener {

	/**
	 * Determine a list of {@link org.aopalliance.intercept.MethodInterceptor} and
	 * {@link InterceptorAndDynamicMethodMatcher} elements (using the latter
	 * if there is a dynamic method matcher that needs evaluation at runtime)
	 * @param config the AOP configuration in the form of an Advised obkect
	 * @param proxy the proxy object
	 * @param method the proxied method
	 * @param targetClass the target class
	 * @see AdvisorChainFactoryUtils#calculateInterceptorsAndDynamicInterceptionAdvice
	 */
	List getInterceptorsAndDynamicInterceptionAdvice(Advised config, Object proxy, Method method, Class targetClass);

}
