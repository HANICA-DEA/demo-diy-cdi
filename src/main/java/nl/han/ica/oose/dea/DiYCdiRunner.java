package nl.han.ica.oose.dea;

import nl.han.ica.oose.dea.annotations.DiyGET;
import nl.han.ica.oose.dea.annotations.DiyInject;
import nl.han.ica.oose.dea.annotations.DiyPath;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class gives a brief demonstration of the possible Java Code behind the mysterious workings of JAX-RS and CDI.
 * And by doing so, hopefully clarifies some of the magic that junior Software Engineers might experience.
 * <p>
 * This Class does the following things:
 * <ul>
 *     <li>It scans all Classes in Package {@code nl.han.ica.oose.dea} for the Annotation {@link DiyPath}</li>
 *     <li>It creates instances of all found Classes</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyInject}</li>
 *     <li>It determines the type of the parameter of the method, annotated with {@link DiyInject}</li>
 *     <li>It creates a new instance with the same type as that parameter</li>
 *     <li>It calls the method annotated with {@link DiyInject} with that newly created instance</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyGET}</li>
 *     <li>It calls all methods annotated with {@link DiyGET}</li>
 * </ul>
 * <p>
 * Notice how this code is rather limited and assumes a lot about the following things:
 * <ul>
 *     <li>Only Setter injection is supported. Constructor en field injection is not</li>
 *     <li>The Constructor of the class annotated with {@link DiyPath} and the dependency must be empty </li>
 * </ul>
 */
public class DiYCdiRunner {

    private static final String PACKAGE_TO_SCAN = "nl.han.ica.oose.dea";

    public static void main(String[] args) {
        var runner = new DiYCdiRunner();
        runner.runDiyApplication();
    }

    private void runDiyApplication() {
        try {
            var jaxRSClasses = findAllJAXRSClasses(PACKAGE_TO_SCAN);
            var jaxRSInstances = createJAXRSInstances(jaxRSClasses);

            for (var instance : jaxRSInstances) {
                scanForInjectableSettersAndInject(instance);
            }

            for (var instance : jaxRSInstances) {
                scanForGetMethodsAndCall(instance);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Set<Class<?>> findAllJAXRSClasses(final String packageToScan) {
        System.out.println("Scanning for classes...");

        // Create a new instance of Relections. This is a helper class.
        var ref = new Reflections(packageToScan);

        // Get all classes annotated with @DiyPath. This will gather
        // all classes in "packageToScan" for classes annotated with "DiyPath".
        var jaxRSClasses = ref.getTypesAnnotatedWith(DiyPath.class);

        // Print info on all found classes. Just for convenience...
        for (Class<?> clazz : jaxRSClasses) {
            var path = clazz.getAnnotation(DiyPath.class);

            System.out.printf("Found DIY JAX-RS Class: %s, for path: %s%n",
                    clazz.getSimpleName(), path.value());
        }

        return jaxRSClasses;
    }

    public Set<Object> createJAXRSInstances(Set<Class<?>> classes) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // Create the HashSet that will be filled with all created Instances
        var jaxRSInstances = new HashSet<>();

        for (var clazz : classes) {
            // Get the Constructor from the Class
            var constructor = clazz.getConstructor();

            // Use the Constructor to create a new Instance
            var object = constructor.newInstance(new Object[]{});

            // Add the created instance to the HashSet
            jaxRSInstances.add(object);

            // Report proudly
            System.out.println("Created instance: " + object);
        }

        return jaxRSInstances;
    }

    private void scanForInjectableSettersAndInject(Object instance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // Report proudly
        System.out.println("Now scanning: " + instance);

        // Iterate though all methods from the given Object
        for (var method : instance.getClass().getMethods()) {

            // Only continue for those that have the "DiyInject" annotation
            if (method.isAnnotationPresent(DiyInject.class)) {

                // Get the parameter of the method, so we can use it to create an Instance and perform
                // Dependency Injection
                Class<?>[] parameterTypes = method.getParameterTypes();

                // We assume the method has exactly one parameter
                var classOfParameter = parameterTypes[0];

                // Again we create an instance through the constructor
                var constructorOfDependency = classOfParameter.getConstructor();
                var instanceOfDependency = constructorOfDependency.newInstance(new Object[]{});

                // In this case, we pass the dependency as the second argument of the invoke method
                method.invoke(instance, instanceOfDependency);
            }
        }
    }

    private void scanForGetMethodsAndCall(Object instance) throws InvocationTargetException, IllegalAccessException {
        for (var method : instance.getClass().getMethods()) {
            if (method.isAnnotationPresent(DiyGET.class)) {
                var response = method.invoke(instance);

                System.out.printf("Calling method %s(), gives response: %s\n", method.getName(), response);
            }
        }
    }
}
