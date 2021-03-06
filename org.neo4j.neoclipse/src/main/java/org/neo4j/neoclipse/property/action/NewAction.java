/**
 * Licensed to Neo Technology under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo Technology licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.neo4j.neoclipse.property.action;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Composite;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.neoclipse.graphdb.GraphDbUtil;
import org.neo4j.neoclipse.property.NeoPropertySheetPage;
import org.neo4j.neoclipse.property.PropertyTransform.PropertyHandler;

/**
 * Action to add a new property to a PropertyContainer.
 * 
 * @author Anders Nawroth
 */
public class NewAction extends PropertyAction
{
    private final PropertyHandler propertyHandler;

    public NewAction( final Composite parent,
            final NeoPropertySheetPage propertySheet,
            final PropertyHandler propertyHandler )
    {
        super( propertyHandler.name(), propertyHandler.descriptor(), parent,
                propertySheet );
        this.propertyHandler = propertyHandler;
    }

    @Override
    public void run()
    {
        PropertyContainer propertyContainer = getPropertyContainer();
        if ( propertyContainer == null )
        {
            return;
        }
        InputDialog keyInput = new InputDialog( null, "Key entry",
                "Please enter the key of the new property", null, null );
        if ( keyInput.open() != OK || keyInput.getReturnCode() != OK )
        {
            return;
        }
        String key = keyInput.getValue();
        // TODO dialog asking for both at the same time
        // GraphDbUtil.setProperty( propertyContainer, key,
        // propertyHandler.value(), propertySheet );
        // propertySheet.fireChangeEvent( propertyContainer, key, true );
        GraphDbUtil.addProperty( propertyContainer, key, propertyHandler,
                propertySheet );
    }
}
