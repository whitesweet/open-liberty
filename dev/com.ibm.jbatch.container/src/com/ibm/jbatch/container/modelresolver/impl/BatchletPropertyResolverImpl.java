/*
 * Copyright 2012 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.ibm.jbatch.container.modelresolver.impl;

import java.util.List;
import java.util.Properties;

import com.ibm.jbatch.jsl.model.Batchlet;
import com.ibm.jbatch.jsl.model.Property;



public class BatchletPropertyResolverImpl extends AbstractPropertyResolver<Batchlet> {


    public BatchletPropertyResolverImpl(boolean isPartitionStep) {
		super(isPartitionStep);
	}

	@Override
    public Batchlet substituteProperties(final Batchlet batchlet, final Properties submittedProps, final Properties parentProps) {

        //resolve all the properties used in attributes and update the JAXB model
        batchlet.setRef(this.replaceAllProperties(batchlet.getRef(), submittedProps, parentProps));

        // Resolve all the properties defined for this batchlet
        if (batchlet.getProperties() != null) {
            this.resolveElementProperties((List<Property>) batchlet.getProperties().getPropertyList(), submittedProps, parentProps);
        }

        return batchlet;
    }

}
