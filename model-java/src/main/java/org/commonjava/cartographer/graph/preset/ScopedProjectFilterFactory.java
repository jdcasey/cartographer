/**
 * Copyright (C) 2013 Red Hat, Inc. (jdcasey@commonjava.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.cartographer.graph.preset;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.commonjava.atservice.annotation.Service;
import org.commonjava.cartographer.graph.filter.ProjectRelationshipFilter;
import org.commonjava.maven.atlas.ident.DependencyScope;

@Named( "scope" )
@ApplicationScoped
@Service( PresetFactory.class )
public class ScopedProjectFilterFactory
    implements PresetFactory
{

    private static final String[] IDS = { "runtime", "test", "provided", "compile", "scope" };

    @Override
    public String[] getPresetIds()
    {
        return IDS;
    }

    @Override
    public ProjectRelationshipFilter newFilter( final String presetId, final Map<String, Object> parameters )
    {
        DependencyScope scope = (DependencyScope) parameters.get( CommonPresetParameters.SCOPE );
        if ( scope == null )
        {
            scope = DependencyScope.getScope( presetId );
            if ( scope == null )
            {
                scope = DependencyScope.runtime;
            }
        }

        Boolean managed = (Boolean) parameters.get( CommonPresetParameters.MANAGED );
        if ( managed == null )
        {
            managed = presetId.startsWith( "managed" );
        }

        return new ScopedProjectFilter( scope, managed );
    }

}
