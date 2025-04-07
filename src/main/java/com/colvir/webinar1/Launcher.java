package com.colvir.webinar1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        int digit = 100;
        var num = 100; //не рекомендую
        double value = 20.0;
        boolean bool = true;

        Integer integer = 112;
        Integer integer2 = new Integer(112);
        Float digitValue = null;
//        digitValue.intValue();

        String text = new String("Hello World!");
        text += "Test";

        System.out.println(100 == num);
        System.out.println(integer == num);
        System.out.println(integer.equals(integer2));

        Student student = new Student();

        Student ivanovVitaly = new Student(1, "Vitaly", "Ivanov", 37);

        Student[] students = {ivanovVitaly, student};

        int[] ints = new int[10];

        for (Student s : students) {
            System.out.println(s);
        }

        List<String> result = List.of("Ivanov", "Petrov", "Sidorov", "Makarov", "Pet", "Met");
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            values.add(i);
        }

        System.out.println(values);
        System.out.println(values.get(0)); // O(1)
        System.out.println(values.get(values.size() - 1));

        List<String> linkedList = new LinkedList<>();
        linkedList.add("Ivanov");

        Set<Student> studentSet = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        });
        studentSet.add(ivanovVitaly);
        studentSet.add(student);

        Map<String, Student> studentDictionary = new HashMap<>();
        studentDictionary.put("IVA", ivanovVitaly);
        studentDictionary.put("NUL", student);

        System.out.println(studentDictionary);
        System.out.println(studentDictionary.getOrDefault("IVA", null));

        Stream<String> stream = result.stream();
        stream = stream.map(String::toUpperCase);
        stream = stream.filter(it -> it.length() == 3);
        System.out.println(stream.count());
        System.out.println(result.stream()
                .map(String::toUpperCase)
                .filter(it -> it.length() == 3)
                .collect(Collectors.joining(", ")));

        Class<Student> studentClass = Student.class;
        Class<? extends Student> ivanovVitalyClass = ivanovVitaly.getClass();
        try {
            Class<?> stClass = Class.forName("com.colvir.webinar1.Student");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            System.out.println(name);
            declaredMethod.setAccessible(true);
            if (name.equals("hashCode") || name.equals("toString")) {
                System.out.println(declaredMethod.invoke(ivanovVitaly));
            }
        }
    }
}
