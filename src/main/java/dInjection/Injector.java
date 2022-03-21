package dInjection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration(packages = {"sorting.QuickSort", "utils.validator.modelChecker.ContractChecker", "utils.validator.modelChecker.PersonChecker"})
public class Injector {

    private static List<Object> objectPool = new ArrayList<>();

    public Injector(Object obj) {
        objectPool.clear();

        Configuration configuration = this.getClass().getAnnotation(Configuration.class);

        for (String url : configuration.packages()) {
            objectPool.addAll(getAllClassesFrom(url));
        }

        inject(obj);
    }

    public static <T> T inject(T object) {

        int indicator = 0;

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autoinjectable.class) && !field.getType().isAssignableFrom(List.class)) {

                Object objectForInject = null;

                for (Object tempObject : objectPool) {

                    if (field.getType().isAssignableFrom(tempObject.getClass())) {

                        indicator++;
                        objectForInject = tempObject;
                    }
                }
                try {
                    if (indicator > 1)
                        throw new Exception("More than one class of the corresponding type is found in the package");
                    if (indicator <= 0)
                        throw new Exception("No classes were found in the package to implement the dependency");
                    if (indicator == 1) {

                        field.setAccessible(true);
                        field.set(object, objectForInject);
                        System.out.println(objectForInject + " is being injecting!");
                    }
                } catch (Exception e) {
                    objectForInject = null;
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(Autoinjectable.class) && field.getType().isAssignableFrom(List.class)) {
                try {
                    field.setAccessible(true);
                    field.set(object, objectPool);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            indicator = 0;
        }

        return object;
    }

    private Collection<?> getAllClassesFrom(String packageName) {
        return new Reflections(packageName, new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name).getConstructor().newInstance();
                    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
