/**
 * Copyright (C) 2012 Red Hat, Inc. (jdcasey@commonjava.org)
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
package org.commonjava.cartographer.db.spi;

import org.commonjava.cartographer.db.RelationshipGraph;
import org.commonjava.cartographer.db.RelationshipGraphException;
import org.commonjava.maven.atlas.graph.rel.ProjectRelationship;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;

import java.util.Collection;

public interface RelationshipGraphListener
{

    void projectError( RelationshipGraph graph, ProjectVersionRef ref,
                       Throwable error )
            throws RelationshipGraphException;

    void storing( RelationshipGraph graph,
                  Collection<? extends ProjectRelationship<?, ?>> relationships )
        throws RelationshipGraphException;

    void stored( RelationshipGraph graph,
                 Collection<? extends ProjectRelationship<?, ?>> relationships,
                 Collection<ProjectRelationship<?, ?>> rejected )
        throws RelationshipGraphException;

    void closing( RelationshipGraph graph )
        throws RelationshipGraphException;

    void closed( RelationshipGraph graph )
        throws RelationshipGraphException;

}
