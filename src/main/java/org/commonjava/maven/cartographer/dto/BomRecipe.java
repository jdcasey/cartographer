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

import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;

public class BomRecipe
    extends RepositoryContentRecipe
{

    private ProjectVersionRef output;

    private boolean generateVersionRanges;

    public BomRecipe()
    {
    }

    public boolean isGenerateVersionRanges()
    {
        return generateVersionRanges;
    }

    public void setGenerateVersionRanges( final boolean generateVersionRanges )
    {
        this.generateVersionRanges = generateVersionRanges;
    }

    public ProjectVersionRef getOutput()
    {
        return output;
    }

    public void setOutput( final ProjectVersionRef outputGav )
    {
        this.output = outputGav;
    }

}
