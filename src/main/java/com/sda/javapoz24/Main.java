package com.sda.javapoz24;

import com.sda.javapoz24.dao.MysqlDBConnection;
import com.sda.javapoz24.dao.PetDao;
import com.sda.javapoz24.model.Pet;
import com.sda.javapoz24.model.Race;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetDao dao = new PetDao(new MysqlDBConnection());
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Podaj komendę [insert (data..), list, delete [id],update [id],quit]");
            command = scanner.nextLine();

            if(command.startsWith("insert")){
                String[] words = command.split(" ");
                Pet pet = Pet.builder()
                        .name(words[1])
                        .age(Integer.parseInt(words[2]))
                        .ownerName(words[3])
                        .weight(Double.parseDouble(words[4]))
                        .pureRace(Boolean.parseBoolean(words[5]))
                        .race(Race.valueOf(words[6]))
                        .build();

                dao.insertPet(pet);

            } else if (command.startsWith("list")){
                List<Pet> list = dao.getAllPets();

                System.out.println("Rekordy:");
                list.forEach(System.out::println);
                System.out.println();

            }   else if (command.startsWith("delete")){
                String[] words = command.split(" ");
                dao.deletePet(Long.parseLong(words[1]));

            } else if (command.startsWith("update")){
                String[] words = command.split(" ");
                Pet pet = dao.getOnePet(Long.parseLong(words[1]));

                System.out.println("Aktualizacja danych zwierzaka:");
                System.out.println(pet);
                System.out.println("Podaj w kolejności:\n " +
                        "nazwę, wiek, nazwisko_właściciela, wagę, czy_czystej_rasy[true/false], rasa[z listy] ");
                String[] updateWords = scanner.nextLine().split(" ");

                Pet updatedPet = Pet.builder()
                        .id(pet.getId())
                        .name(updateWords[0])
                        .age(Integer.parseInt(updateWords[1]))
                        .ownerName(updateWords[2])
                        .weight(Double.parseDouble(updateWords[3]))
                        .pureRace(Boolean.parseBoolean(updateWords[4]))
                        .race(Race.valueOf(updateWords[5].toUpperCase()))
                        .build();

                dao.updatePet(updatedPet);
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}
