/*
 * Copyright 2010-2011 the original author or authors.
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

package org.springframework.cache.interceptor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * Advisor driven by a {@link CacheDefinitionSource}, used to include a
 * transaction advice bean for methods that are transactional.
 * 
 * @author Costin Leau
 */
@SuppressWarnings("serial")
public class BeanFactoryCacheDefinitionSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

	private CacheDefinitionSource cacheDefinitionSource;

	private final CacheDefinitionSourcePointcut pointcut = new CacheDefinitionSourcePointcut() {
		@Override
		protected CacheDefinitionSource getCacheDefinitionSource() {
			return cacheDefinitionSource;
		}
	};

	/**
	 * Set the cache operation attribute source which is used to find cache
	 * attributes. This should usually be identical to the source reference
	 * set on the cache interceptor itself.
	 * @see CacheInterceptor#setCacheAttributeSource
	 */
	public void setCacheDefinitionSource(CacheDefinitionSource cacheDefinitionSource) {
		this.cacheDefinitionSource = cacheDefinitionSource;
	}

	/**
	 * Set the {@link ClassFilter} to use for this pointcut.
	 * Default is {@link ClassFilter#TRUE}.
	 */
	public void setClassFilter(ClassFilter classFilter) {
		this.pointcut.setClassFilter(classFilter);
	}

	public Pointcut getPointcut() {
		return this.pointcut;
	}
}