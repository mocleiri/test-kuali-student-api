/*
 * Copyright 2012 The Kuali Foundation
 *
 * Licensed under the the Educational Community License, Version 1.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.common.spring;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kuali Student Team
 *
 */
public final class AnnotationUtils {

	private AnnotationUtils() {
	}
	

	/**
	 * Extract the set of java interfaces from the class hierarchy of the bean provided that match the annotation provided.
	 * 
	 * @param bean
	 * @param annotation
	 * @return the set of java interfaces that match the annotation.
	 */
	public static Set<Class<?>> extractInterfaceContainingAnnotation(Object bean, Class <? extends Annotation> annotation) {
		
		Class<? extends Object> beanClass = bean.getClass();
		
		Class<?>[] interfaces = beanClass.getInterfaces();
		
		Set<Class<?>>matchingInterfaces = new HashSet<Class<?>>();
		
		for (Class<?> candidateInterface : interfaces) {
			
			Annotation ws = candidateInterface.getAnnotation(annotation);
			
			if (ws != null)
				matchingInterfaces.add(candidateInterface);
		}
		
		return matchingInterfaces;
	}
	
}
