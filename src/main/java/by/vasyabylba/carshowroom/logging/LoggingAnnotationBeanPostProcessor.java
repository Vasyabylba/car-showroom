package by.vasyabylba.carshowroom.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class LoggingAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> loggingBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Logging.class)) {
            loggingBeans.put(beanName, beanClass);
            return bean;
        }

        for (Method method : beanClass.getMethods()) {
            if (method.isAnnotationPresent(Logging.class)) {
                loggingBeans.put(beanName, beanClass);
                return bean;
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = loggingBeans.get(beanName);

        if (beanClass == null) {
            return bean;
        }

        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),
                getInvocationHandler(bean, beanClass)
        );
    }

    private InvocationHandler getInvocationHandler(Object bean, Class<?> beanClass) {
        return (proxy, method, args) -> {
            if (beanClass.isAnnotationPresent(Logging.class) || method.isAnnotationPresent(Logging.class)) {
                log.info("Input parameters of method {{}} = {{}}", method.getName(), args);
                Object retVal = method.invoke(bean, args);
                log.info("Output parameters of method {{}} = {{}}", method.getName(), retVal);
                return retVal;
            } else {
                return method.invoke(bean, args);
            }
        };
    }

}
