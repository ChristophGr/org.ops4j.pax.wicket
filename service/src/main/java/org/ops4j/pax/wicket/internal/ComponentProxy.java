package org.ops4j.pax.wicket.internal;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import net.sf.cglib.proxy.MethodProxy;

public class ComponentProxy implements OverwriteProxy, Serializable {

    private static final long serialVersionUID = 1848500647893384991L;

    private Map<String, String> overwrites;

    public ComponentProxy(Map<String, String> overwrites) {
        this.overwrites = overwrites;
    }

    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (isFinalizeMethod(method)) {
            // swallow finalize call
            return null;
        } else if (isEqualsMethod(method)) {
            return equals(args[0]) ? Boolean.TRUE : Boolean.FALSE;
        } else if (isHashCodeMethod(method)) {
            return new Integer(hashCode());
        } else if (isToStringMethod(method)) {
            return toString();
        } else if (isGetOverwritesMethod(method)) {
            return getOverwrites();
        }

        return proxy.invokeSuper(object, args);
    }

    public Map<String, String> getOverwrites() {
        return overwrites;
    }

    protected static boolean isGetOverwritesMethod(Method method) {
        return method.getReturnType() == Map.class && method.getParameterTypes().length == 0 &&
                method.getName().equals("getOverwrites");
    }

    /**
     * Checks if the method is derived from Object.equals()
     * 
     * @param method method being tested
     * @return true if the method is derived from Object.equals(), false otherwise
     */
    protected static boolean isEqualsMethod(Method method) {
        return method.getReturnType() == boolean.class && method.getParameterTypes().length == 1 &&
                method.getParameterTypes()[0] == Object.class && method.getName().equals("equals");
    }

    /**
     * Checks if the method is derived from Object.hashCode()
     * 
     * @param method method being tested
     * @return true if the method is defined from Object.hashCode(), false otherwise
     */
    protected static boolean isHashCodeMethod(Method method) {
        return method.getReturnType() == int.class && method.getParameterTypes().length == 0 &&
                method.getName().equals("hashCode");
    }

    /**
     * Checks if the method is derived from Object.toString()
     * 
     * @param method method being tested
     * @return true if the method is defined from Object.toString(), false otherwise
     */
    protected static boolean isToStringMethod(Method method) {
        return method.getReturnType() == String.class && method.getParameterTypes().length == 0 &&
                method.getName().equals("toString");
    }

    /**
     * Checks if the method is derived from Object.finalize()
     * 
     * @param method method being tested
     * @return true if the method is defined from Object.finalize(), false otherwise
     */
    protected static boolean isFinalizeMethod(Method method) {
        return method.getReturnType() == void.class && method.getParameterTypes().length == 0 &&
                method.getName().equals("finalize");
    }

}