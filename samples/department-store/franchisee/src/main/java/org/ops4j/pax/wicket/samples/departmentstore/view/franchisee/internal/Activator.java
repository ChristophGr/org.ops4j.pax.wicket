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
package org.ops4j.pax.wicket.samples.departmentstore.view.franchisee.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.ops4j.pax.wicket.samples.departmentstore.model.DepartmentStore;
import org.ops4j.pax.wicket.samples.departmentstore.model.Floor;
import org.ops4j.pax.wicket.samples.departmentstore.model.Franchisee;
import org.ops4j.pax.wicket.service.Content;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.ops4j.pax.wicket.samples.departmentstore.view.franchisee.FranchiseeContent;

/**
 * {@code Activator}
 *
 * @author Edward Yakop
 * @since 1.0.0
 */
public class Activator
    implements BundleActivator
{
    private List<ServiceRegistration> m_registrations;

    public Activator()
    {
        m_registrations = new ArrayList<ServiceRegistration>();
    }

    public void start( BundleContext bundleContext )
        throws Exception
    {
        ServiceReference depStoreService = bundleContext.getServiceReference( DepartmentStore.class.getName() );
        DepartmentStore departmentStore = (DepartmentStore) bundleContext.getService( depStoreService );

        String serviceName = Content.class.getName();
        m_registrations = new ArrayList<ServiceRegistration>();
        List<Floor> floors = departmentStore.getFloors();
        for( Floor floor: floors )
        {
            List<Franchisee> franchisees = floor.getFranchisees();
            for( Franchisee franchisee : franchisees )
            {
                String destinationId = floor.getName() + ".franchisee";
                FranchiseeContent content = new FranchiseeContent( franchisee, destinationId  );
                Properties properties = new Properties();
                properties.put( Content.CONFIG_DESTINATIONID, destinationId );
                ServiceRegistration registration = bundleContext.registerService( serviceName, content, properties );
                m_registrations.add( registration );
            }
        }

    }

    public void stop( BundleContext bundleContext )
        throws Exception
    {
        for( ServiceRegistration registeration : m_registrations )
        {
            registeration.unregister();
        }
        m_registrations.clear();
    }
}
