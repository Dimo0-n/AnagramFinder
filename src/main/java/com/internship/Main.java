package com.internship;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        //citirea datelor din fisier si plasarea lor intr-o lista
        List<String> cuvinte = new ArrayList<>();

        InputStream file = Main.class.getClassLoader().getResourceAsStream("sample.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            
        String linie;
        while ((linie = reader.readLine()) != null) {
            linie = linie.trim();
            cuvinte.add(linie);
        }

        Map<String, List<String>> anagrame = cuvinte.parallelStream()
                .collect(Collectors.groupingByConcurrent(Main::cuvantSort));

        System.out.println("\nGrupuri de anagrame:");
        for (List<String> grup : anagrame.values()) {
            System.out.println(grup);
        }

    }

    //sortam literele fiecarui cuvint in ordine alfabetica pentru a gasi mai rapid anagramele
    private static String cuvantSort(String cuvant) {
        char[] litere = cuvant.toCharArray();
        Arrays.sort(litere);
        return new String(litere);
    }

}