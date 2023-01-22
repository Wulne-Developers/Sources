package xu.zhixuan.wulne.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    public static Map<Method, Class<?>> registeredMethodMap = new HashMap<>();
    public static Map<Method, Object> methodObjectMap = new HashMap<>();

    public static void register(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        finding:
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation.annotationType() != EventTarget.class) {
                    continue;
                }

                if (method.getParameterTypes().length == 0) {
                    continue;
                }
                registeredMethodMap.put(method, method.getParameterTypes()[0]);
                methodObjectMap.put(method, obj);
                continue finding;
            }
        }
    }

    public static void unregister(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            registeredMethodMap.remove(method);
            methodObjectMap.remove(method);
        }
    }

    public static void unregister(Object obj, Method methodToUnregister) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if(method == methodToUnregister) {
                registeredMethodMap.remove(method);
                methodObjectMap.remove(method);
            }
        }
    }

    public static EventBase call(EventBase event) {
        try {
            registeredMethodMap.forEach((method, eventClazz) -> {
                if (event.getClass() != eventClazz) {
                    return;
                }
                Object obj = methodObjectMap.get(method);
                method.setAccessible(true);
                try {
                    method.invoke(obj, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (ConcurrentModificationException e){
            // ssh! Don't tell others the problem here will not affect the operation
        }
        return event;
    }
}
