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
package org.ops4j.pax.wicket.aries.internal;

import org.apache.aries.blueprint.ParserContext;
import org.apache.aries.blueprint.mutable.MutableBeanMetadata;
import org.ops4j.pax.wicket.util.injection.FilterFactoryDecorator;
import org.ops4j.pax.wicket.util.injection.InjectionParserUtil;
import org.w3c.dom.Element;

public class BlueprintFilterFactoryBeanDefinitionParser extends AbstractBlueprintBeanDefinitionParser {

    @Override
    public Class<?> getRuntimeClass() {
        return FilterFactoryDecorator.class;
    }

    @Override
    protected void extractRemainingMetaData(Element element, ParserContext context, MutableBeanMetadata beanMetadata)
        throws Exception {
        addPropertyValueFromElement("filterClass", element, context, beanMetadata);
        addPropertyValueFromElement("priority", element, context, beanMetadata);
        addPropertyValueFromElement("applicationName", element, context, beanMetadata);
        addPropertyReferenceForMap("initParams", context, beanMetadata, InjectionParserUtil.retrieveInitParam(element));
    }

}
