/*
 * Copyright 2006 Niclas Hedhman.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.wicket.samples.library.model;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator
    implements BundleActivator
{
    private LibraryImpl m_Library;
    private ServiceRegistration m_LibraryRegistration;

    public void start( BundleContext bundleContext )
        throws Exception
    {
        m_Library = new LibraryImpl();
        m_Library.initialize();
        m_LibraryRegistration = bundleContext.registerService( Library.class.getName(), m_Library, null );
    }

    public void stop( BundleContext bundleContext )
        throws Exception
    {
        m_Library.dispose();
        m_LibraryRegistration.unregister();
    }
}
