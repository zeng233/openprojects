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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;

/**
 * Utility methods for use by {@link AdvisorChainFactory} implementations.
 *
 * <p>The {@link #calculateInterceptorsAndDynamicInterceptionAdvice} method
 * is the definitive way of working out an advice chain for a Method,
 * given an {@link Advised} object.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see AdvisorChainFactory
 */
public abstract class AdvisorChainFactoryUtils {

	/**
	 * Canonical instance of a simple AdvisorChainFactory implementation that
	 * delegates to the {@link #calculateInterceptorsAndDynamicInterceptionAdvice}
	 * method.
	 */
	public static final AdvisorChainFactory SIMPLE_ADVISOR_CHAIN_FACTORY =
			new AdvisorChainFactory() {
				public List getInterceptorsAndDynamicInterceptionAdvice(
						Advised config, Object proxy, Method method, Class targetClass) {
					return calculateInterceptorsAndDynamicInterceptionAdvice(config, proxy, method, targetClass);
				}
				public void activated(AdvisedSupport advisedSupport) {
				}
				public void adviceChanged(AdvisedSupport advisedSupport) {
				}
			};


	/**
	 * Return the static interceptors and dynamic interception advice that may apply
	 * to this method invocation.
	 * @param config the AOP configuration in the form of an Advised obkect
	 * @param proxy the proxy object
	 * @param method the proxied method
	 * @param targetClass the target class
	 * @return list of {@link org.aopalliance.intercept.MethodInterceptor} and
	 * {@link InterceptorAndDynamicMethodMatcher} elements (using the latter
	 * if there is a dynamic method matcher that needs evaluation at runtime)
	 */
	public static List calculateInterceptorsAndDynamicInterceptionAdvice(
			Advised config, Object proxy, Method method, Class targetClass) {

		List interceptorList = new ArrayList(config.getAdvisors().length);
		AdvisorAdapterRegistry registry = GlobalAdvisorAdapterRegistry.getInstance();
		Advisor[] advisors = config.getAdvisors();
		for (int i = 0; i < advisors.length; i++) {
			Advisor advisor = advisors[i];
			if (advisor instanceof PointcutAdvisor) {
				// Add it conditionally.
				PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
				if (pointcutAdvisor.getPointcut().getClassFilter().matches(targetClass)) {
					Interceptor[] interceptors = registry.getInterceptors(advisor);
					MethodMatcher mm = pointcutAdvisor.getPointcut().getMethodMatcher();
					if (mm.matches(method, targetClass)) {
						if (mm.isRuntime()) {
							// Creating a new object instance in the getInterceptors() method
							// isn't a problem as we normally cache created chains.
							for (int j = 0; j < interceptors.length; j++) {
								interceptorList.add(new InterceptorAndDynamicMethodMatcher((MethodInterceptor) interceptors[j], mm));
							}
						}
						else {
							interceptorList.addAll(Arrays.asList(interceptors));
						}
					}
				}
			}
			else if (advisor instanceof IntroductionAdvisor) {
				IntroductionAdvisor ia = (IntroductionAdvisor) advisor;
				if (ia.getClassFilter().matches(targetClass)) {
					Interceptor[] interceptors = registry.getInterceptors(advisor);
					interceptorList.addAll(Arrays.asList(interceptors));
				}
			}
		}
		return interceptorList;
	}

}
