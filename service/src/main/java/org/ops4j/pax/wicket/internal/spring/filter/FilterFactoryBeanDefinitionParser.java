package org.ops4j.pax.wicket.internal.spring.filter;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class FilterFactoryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return DefaultFilterFactory.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addConstructorArgReference("bundleContext");
        setConstructorElement("filterClass", element, builder);
        setConstructorElement("priority", element, builder);
        setConstructorElement("applicationName", element, builder);
        builder.setInitMethodName("start");
        builder.setDestroyMethodName("stop");
        builder.setLazyInit(false);
        super.doParse(element, builder);
    }

    private void setConstructorElement(String id, Element element, BeanDefinitionBuilder builder) {
        String beanAttribute = element.getAttribute(id);
        builder.addConstructorArgValue(beanAttribute);
    }

}