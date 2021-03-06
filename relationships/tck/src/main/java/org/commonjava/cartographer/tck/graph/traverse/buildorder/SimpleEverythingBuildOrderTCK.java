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
package org.commonjava.cartographer.tck.graph.traverse.buildorder;

import org.commonjava.cartographer.graph.RelationshipGraph;
import org.commonjava.maven.atlas.graph.rel.SimpleDependencyRelationship;
import org.commonjava.maven.atlas.graph.rel.SimpleParentRelationship;
import org.commonjava.maven.atlas.graph.rel.SimplePluginRelationship;
import org.commonjava.cartographer.graph.traverse.BuildOrderTraversal;
import org.commonjava.cartographer.graph.traverse.model.BuildOrder;
import org.commonjava.maven.atlas.ident.DependencyScope;
import org.commonjava.maven.atlas.ident.ref.ProjectRef;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.maven.atlas.ident.ref.SimpleProjectVersionRef;
import org.junit.Test;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.join;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by jdcasey on 8/24/15.
 */
public class SimpleEverythingBuildOrderTCK
    extends AbstractBuildOrderTCK
{

    @Test
    public void run()
            throws Exception
    {
        final ProjectVersionRef c = new SimpleProjectVersionRef( "group.id", "c", "3" );
        final ProjectVersionRef b = new SimpleProjectVersionRef( "group.id", "b", "2" );
        final ProjectVersionRef a = new SimpleProjectVersionRef( "group.id", "a", "1" );
        final ProjectVersionRef pa = new SimpleProjectVersionRef( "plugin.dep.id", "p-a", "1" );
        final ProjectVersionRef pb = new SimpleProjectVersionRef( "plugin.id", "p-b", "2" );

        final Map<ProjectVersionRef, ProjectVersionRef> relativeOrder =
                new HashMap<ProjectVersionRef, ProjectVersionRef>();
        relativeOrder.put( c, b );
        relativeOrder.put( b, a );
        relativeOrder.put( c, pb );
        relativeOrder.put( pb, pa );

        final URI source = sourceURI();
        final RelationshipGraph graph = simpleGraph( c );

        /* @formatter:off */
        graph.storeRelationships( new SimpleParentRelationship( source, c ),
                                  new SimpleDependencyRelationship( source, c, b.asJarArtifact(), null, 0, false, false, false ),
                                  new SimplePluginRelationship( source, c, pb, 0, false, false ),
                                  new SimpleDependencyRelationship( source, b, a.asJarArtifact(), DependencyScope.runtime, 0, false, false, false ),
                                  new SimpleDependencyRelationship( source, pb, pa.asJarArtifact(), null, 0, false, false, false )
        );
        /* @formatter:on */

        System.out.println( "Got relationships:\n\n  " + join( graph.getAllRelationships(), "\n  " ) );
        assertThat( graph.getAllRelationships()
                         .size(), equalTo( 4 ) );

        final BuildOrderTraversal bo = new BuildOrderTraversal();
        graph.traverse( bo );

        final BuildOrder buildOrderObj = bo.getBuildOrder();
        final List<ProjectRef> buildOrder = buildOrderObj.getOrder();

        System.out.printf( "Build order: %s\n", buildOrder );

        assertRelativeOrder( relativeOrder, buildOrder );
    }

}
