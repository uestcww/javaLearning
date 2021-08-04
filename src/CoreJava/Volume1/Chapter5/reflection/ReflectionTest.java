package CoreJava.Volume1.Chapter5.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {

    public static void printFields(Class cl){
        Field[] fields = cl.getFields();
        for(Field f : fields){
            Class type = f.getType();
            String name = f.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }

    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getConstructors();
        for(Constructor c : constructors){
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");
            Class[] paraTypes = c.getParameterTypes();
            for(int i=0;i<paraTypes.length;++i){
                if(i > 0){
                    System.out.print(", ");
                }
                System.out.print(paraTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class cl){
        Method[] methods = cl.getMethods();
        for(Method m : methods){
            Class returnType = m.getReturnType();
            String name = m.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.print(returnType.getName() + " " + name + "(");
            Class[] paraTypes = m.getParameterTypes();
            for(int i=0;i<paraTypes.length;++i){
                if(i > 0){
                    System.out.print(", ");
                }
                System.out.print(paraTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void main(String[] args) {
        String name;
        if(args.length > 0){
            name = args[0];
        }else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date):");
            name = in.next();
        }
        try {
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + name);
            if(supercl != null && supercl != Object.class){
                System.out.print(" extends " + supercl.getName() + "{");
            }
            System.out.println();
            printFields(cl);
            System.out.println();
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println("}");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
