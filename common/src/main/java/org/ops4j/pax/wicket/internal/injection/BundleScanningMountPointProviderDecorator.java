/**
 * Copyright OPS4J
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.wicket.internal.injection;

import java.net.URL;
import java.util.Enumeration;

import org.apache.wicket.Page;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class BundleScanningMountPointProviderDecorator implements InjectionAwareDecorator {

    private BundleContext bundleContext;
    private String applicationName;

    // TODO: [PAXWICKET-255] reintroduce
    // private List<DefaultPageMounter> mountPointRegistrations = new ArrayList<DefaultPageMounter>();

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void start() throws Exception {
        Bundle bundleToScan = bundleContext.getBundle();
        Enumeration<?> findEntries = bundleToScan.findEntries("", "*.class", true);
        while (findEntries.hasMoreElements()) {
            URL object = (URL) findEntries.nextElement();
            String className = object.getFile().substring(1, object.getFile().length() - 6).replaceAll("/", ".");
            Class<?> candidateClass = bundleToScan.loadClass(className);
            if (!Page.class.isAssignableFrom(candidateClass)) {
                continue;
            }
            @SuppressWarnings("unchecked")
            Class<? extends Page> pageClass = (Class<? extends Page>) candidateClass;
            PaxWicketMountPoint mountPoint = pageClass.getAnnotation(PaxWicketMountPoint.class);
            if (mountPoint != null) {
                // TODO: [PAXWICKET-255] reintroduce
                // DefaultPageMounter mountPointRegistration = new DefaultPageMounter(applicationName, bundleContext);
                // mountPointRegistration.addMountPoint(mountPoint.mountPoint(), pageClass);
                // mountPointRegistration.register();
                // mountPointRegistrations.add(mountPointRegistration);
            }
        }
    }

    public void stop() throws Exception {
        // TODO: [PAXWICKET-255] reintroduce
        // for (DefaultPageMounter pageMounter : mountPointRegistrations) {
        // pageMounter.dispose();
        // }
        // mountPointRegistrations.clear();
    }

}
