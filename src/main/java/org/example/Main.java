package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Test amaçlı LinkedList tanımı ve elemanların eklenmesi
        List<Employee> employees = new LinkedList<>();

        Employee e1 = new Employee(1, "Ahmet", "Yılmaz");
        Employee e2 = new Employee(2, "Mehmet", "Kaya");
        Employee e3 = new Employee(1, "Ahmet", "Yılmaz"); // Tekrar eden ID
        Employee e4 = new Employee(3, "Ayşe", "Demir");
        Employee e5 = new Employee(2, "Mehmet", "Kaya"); // Tekrar eden ID
        Employee e6 = new Employee(4, "Fatma", "Şahin");

        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);

        System.out.println("--- Tekrar Eden Elemanlar ---");
        System.out.println(findDuplicates(employees));

        System.out.println("\n--- Benzersiz Elemanlar Map Hali ---");
        System.out.println(findUniques(employees));

        System.out.println("\n--- Sadece Tek Sefer Geçen Elemanlar ---");
        System.out.println(removeDuplicates(employees));
    }

    // Tekrar eden employeeleri bulup başka bir listeye ekler ve döner
    public static List<Employee> findDuplicates(List<Employee> list) {
        List<Employee> duplicates = new LinkedList<>();
        Set<Employee> seenBefore = new HashSet<>();

        if (list == null) return duplicates;

        for (Employee emp : list) {
            // set.add() metodu eğer eleman set içinde zaten varsa false döner
            if (!seenBefore.add(emp) && !duplicates.contains(emp)) {
                duplicates.add(emp);
            }
        }
        return duplicates;
    }

    // Tekrar edenlerden sadece bir tane, tekrar etmeyenlerden de bir tane alarak Map'e ekler
    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> uniquesMap = new HashMap<>();

        if (list == null) return uniquesMap;

        for (Employee emp : list) {
            if (emp != null) {
                // Map yapısı aynı key geldiğinde üzerine yazar (overwrite), böylece otomatik tekilleşir
                uniquesMap.put(emp.getId(), emp);
            }
        }
        return uniquesMap;
    }

    // Birden fazla kez geçen tüm kayıtları tamamen siler, Sadece 1 kez listelenmiş elemanları döner
    public static List<Employee> removeDuplicates(List<Employee> list) {
        List<Employee> result = new LinkedList<>();

        if (list == null) return result;

        // Her bir elemanın listede kaç kez geçtiğini sayalım
        Map<Employee, Integer> countMap = new HashMap<>();
        for (Employee emp : list) {
            countMap.put(emp, countMap.getOrDefault(emp, 0) + 1);
        }

        // Sadece sayısı tam olarak 1 olanları listeye ekleyelim
        for (Employee emp : list) {
            if (countMap.get(emp) == 1) {
                result.add(emp);
            }
        }
        return result;
    }
}