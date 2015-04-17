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
package org.commonjava.maven.cartographer.dto;

import static org.commonjava.maven.atlas.graph.util.RelationshipUtils.targets;

import java.util.List;
import java.util.Set;

import org.commonjava.maven.atlas.graph.rel.ProjectRelationship;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;

public class GraphCalculation
{

    private Type operation;

    private Set<ProjectRelationship<?>> result;

    private Set<ProjectVersionRef> roots;

    private List<GraphDescription> graphs;

    protected GraphCalculation()
    {
    }

    public GraphCalculation( final Type operation, final List<GraphDescription> graphs,
                             final Set<ProjectVersionRef> roots, final Set<ProjectRelationship<?>> result )
    {
        this.operation = operation;
        this.graphs = graphs;
        this.roots = roots;
        this.result = result;
    }

    public enum Type
    {
        ADD, SUBTRACT, INTERSECT;
    }

    public Type getOperation()
    {
        return operation;
    }

    public Set<ProjectVersionRef> getResultingRoots()
    {
        return roots;
    }

    public Set<ProjectRelationship<?>> getResultingRelationships()
    {
        return result;
    }

    public Set<ProjectVersionRef> getResultingProjects()
    {
        return targets( result );
    }

    public Set<ProjectRelationship<?>> getResult()
    {
        return result;
    }

    public List<GraphDescription> getGraphs()
    {
        return graphs;
    }

    protected void setOperation( final Type operation )
    {
        this.operation = operation;
    }

    protected void setResult( final Set<ProjectRelationship<?>> result )
    {
        this.result = result;
    }

    protected void setGraphs( final List<GraphDescription> graphs )
    {
        this.graphs = graphs;
    }

    @Override
    public String toString()
    {
        return String.format( "GraphCalculation [operation=%s, result=%s, graphs=%s]", operation, result, graphs );
    }

}
