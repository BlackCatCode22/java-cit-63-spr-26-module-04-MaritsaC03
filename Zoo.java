import java.util.*;
import java.io.*;

public class Zoo {

    public static void main(String[] args) {

        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<>();


        ArrayList<String> hyenaNames = new ArrayList<>(Arrays.asList(
                "Shenzi","Banzai","Ed","Zig","Bud","Lou","Kamari","Wema","Nne","Madoa","Prince","Nevarah"
        ));
        ArrayList<String> lionNames = new ArrayList<>(Arrays.asList(
                "Scar","Mufasa","Simba","Kiara","King","Drooper","Kimba","Nala","Leo","Samson","Elsa","Cecil"
        ));
        ArrayList<String> bearNames = new ArrayList<>(Arrays.asList(
                "Yogi","Smokey","Paddington","Lippy","Bungle","Baloo","Rupert","Winnie the Pooh","Snuggles","Bert"
        ));
        ArrayList<String> tigerNames = new ArrayList<>(Arrays.asList(
                "Tony","Tigger","Amber","Cosimia","Cuddles","Dave","Jiba","Rajah","Rayas","Ryker"
        ));

        int hyenaIndex = 0, lionIndex = 0, bearIndex = 0, tigerIndex = 0;


        File inputFile = new File("arrivingAnimals.txt");
        if (!inputFile.exists()) {
            System.out.println("ERROR: The file arrivingAnimals.txt was not found!");
            System.out.println("Make sure it is in the project root folder.");
            return; // stop the program
        }

        try (Scanner animalFile = new Scanner(inputFile)) {

            while (animalFile.hasNextLine()) {
                String line = animalFile.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String species = parts[0].trim();
                int age;
                try {
                    age = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age in line: " + line);
                    continue;
                }

                Animal animal;


                if(species.equalsIgnoreCase("Hyena")) {
                    if(hyenaIndex >= hyenaNames.size()) {
                        System.out.println("No more Hyena names available!");
                        continue;
                    }
                    String name = hyenaNames.get(hyenaIndex++);
                    animal = new Hyena(name, age);
                } else if(species.equalsIgnoreCase("Lion")) {
                    if(lionIndex >= lionNames.size()) {
                        System.out.println("No more Lion names available!");
                        continue;
                    }
                    String name = lionNames.get(lionIndex++);
                    animal = new Lion(name, age);
                } else if(species.equalsIgnoreCase("Tiger")) {
                    if(tigerIndex >= tigerNames.size()) {
                        System.out.println("No more Tiger names available!");
                        continue;
                    }
                    String name = tigerNames.get(tigerIndex++);
                    animal = new Tiger(name, age);
                } else { // Bear
                    if(bearIndex >= bearNames.size()) {
                        System.out.println("No more Bear names available!");
                        continue;
                    }
                    String name = bearNames.get(bearIndex++);
                    animal = new Bear(name, age);
                }

                animals.add(animal);
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unexpected error: file not found.");
            return;
        }


        try (PrintWriter writer = new PrintWriter("newAnimals.txt")) {

            for(Animal a : animals) {
                writer.println(a.getSpecies() + " - " + a.getName() + " Age: " + a.getAge());
            }

            writer.println("\nSpecies Count:");
            for(String species : speciesCount.keySet()) {
                writer.println(species + ": " + speciesCount.get(species));
            }

        } catch (IOException e) {
            System.out.println("Error writing to newAnimals.txt: " + e.getMessage());
            return;
        }

        System.out.println("Report written successfully to newAnimals.txt!");
    }
}