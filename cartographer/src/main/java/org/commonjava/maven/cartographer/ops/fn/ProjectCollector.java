package org.commonjava.maven.cartographer.ops.fn;

import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.maven.cartographer.CartoRequestException;
import org.commonjava.maven.cartographer.data.CartoDataException;

public interface ProjectCollector<T>
{

    void accept( ProjectVersionRef ref, T result )
                    throws CartoRequestException, CartoDataException;

}
