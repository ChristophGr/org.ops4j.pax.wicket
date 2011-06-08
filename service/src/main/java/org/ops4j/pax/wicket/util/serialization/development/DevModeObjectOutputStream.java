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
package org.ops4j.pax.wicket.util.serialization.development;

import java.io.IOException;
import java.io.OutputStream;

import org.ops4j.pax.wicket.util.serialization.deployment.PaxWicketObjectOutputStream;

/**
 * @author edward.yakop@gmail.com
 */
public final class DevModeObjectOutputStream extends PaxWicketObjectOutputStream {

    public DevModeObjectOutputStream(OutputStream outputStream)
        throws IOException, IllegalArgumentException {
        super(outputStream);
    }

    @Override
    protected final void writeObjectOverride(Object object)
        throws IOException {
        String objectClassName = object.getClass().getName();
        super.writeObjectOverride(objectClassName);
        super.writeObjectOverride(object);
    }
}
