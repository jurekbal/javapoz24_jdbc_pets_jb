package com.sda.javapoz24.dao;

import com.sda.javapoz24.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private MysqlDBConnection connector;

    public PetDao(MysqlDBConnection connector) {
        this.connector = connector;

        CreateDatabaseAndTable();
    }

    private void CreateDatabaseAndTable() {
        try (Connection connection = connector.createConnection()){
            PreparedStatement statement = connection.prepareStatement(PetQuerries.CREATE_TABLE);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertPet(Pet pet) {
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    PetQuerries.INSERT_PET,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setInt(2, pet.getAge());
            preparedStatement.setString(3, pet.getOwnerName());
            preparedStatement.setDouble(4, pet.getWeight());
            preparedStatement.setBoolean(5, pet.isPureRace());
            preparedStatement.setString(6, pet.getRace().toString());

            int affectedRecords = preparedStatement.executeUpdate();
            System.out.println("Dodanie rekordów: " + affectedRecords);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                Long recordId = generatedKeys.getLong(1);
                pet.setId(recordId);
                System.out.println("Generated id: " + pet.getId());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.SELECT_PETS);

            ResultSet rekordy = preparedStatement.executeQuery();

            while (rekordy.next()) {
                Pet pet = new Pet();
                pet.setId(rekordy.getLong(1));
                pet.setName(rekordy.getString(2));
                pet.setAge(Integer.parseInt(rekordy.getString(3)));
                pet.setOwnerName(rekordy.getString(4));
                pet.setWeight(Double.parseDouble(rekordy.getString(5)));
                pet.setPureRace(Boolean.parseBoolean(rekordy.getString(6)));

                pets.add(pet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pets;
    }

    public void deletePet(long identifier){
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.DELETE_PET);
            preparedStatement.setLong(1, identifier);

            int affectedRecords = preparedStatement.executeUpdate();
            System.out.println("Usuniętych rekordów: " + affectedRecords);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // auxiliary READ for specific, required UPDATE demands
    public Pet getOnePet(long identifier) {
        Pet pet = new Pet();
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.SELECT_PET_BY_ID);
            preparedStatement.setLong(1, identifier);

            ResultSet rekord = preparedStatement.executeQuery();

            while (rekord.next()){
                pet.setId(identifier);
                pet.setName(rekord.getString(1));
                pet.setAge(Integer.parseInt(rekord.getString(2)));
                pet.setOwnerName(rekord.getString(3));
                pet.setWeight(Double.parseDouble(rekord.getString(4)));
                pet.setPureRace(Boolean.parseBoolean(rekord.getString(5)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pet;
    }

    public void updatePet(Pet updatedPet) {
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.UPDATE_PET);
            preparedStatement.setString(1, updatedPet.getName());
            preparedStatement.setInt(2, updatedPet.getAge());
            preparedStatement.setString(3, updatedPet.getOwnerName());
            preparedStatement.setDouble(4, updatedPet.getWeight());
            preparedStatement.setBoolean(5, updatedPet.isPureRace());
            preparedStatement.setString(6, updatedPet.getRace().toString());
            preparedStatement.setLong(7, updatedPet.getId());

            int affectedRecord = preparedStatement.executeUpdate();
            System.out.println("Affected records: " + affectedRecord);
            
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
