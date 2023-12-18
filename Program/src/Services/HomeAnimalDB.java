package Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.*;

public class HomeAnimalDB implements IDb<HomeAnimal> {
    private Creator homeAnimalCreator;
    private Statement sqlSt;
    private ResultSet resultSet;
    private String SQLstr;

    public HomeAnimalDB() {
        this.homeAnimalCreator = new HomeAnimalCreator();
    };

    @Override
    public List<HomeAnimal> getAll() {
        List<HomeAnimal> farm = new ArrayList<HomeAnimal>();
        HomeAnimal homeAnimal;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlSt = dbConnection.createStatement();
                SQLstr = "SELECT animals_id, id, name, birthday FROM home_animal ORDER BY id";
                resultSet = sqlSt.executeQuery(SQLstr);
                while (resultSet.next()) {

                    HomeAnimalType type = HomeAnimalType.getType(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();

                    homeAnimal = homeAnimalCreator.createHomeAnimal(type, name, birthday);
                    homeAnimal.setHomeAnimalId(id);
                    farm.add(homeAnimal);
                }
                return farm;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public HomeAnimal getById(int homeAnimalId) {
        HomeAnimal homeAnimal = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                SQLstr = "SELECT animals_id, id, name, birthday FROM home_animal WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, homeAnimalId);
                resultSet = prepSt.executeQuery();

                if (resultSet.next()) {

                    HomeAnimalType type = HomeAnimalType.getType(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();

                    homeAnimal = homeAnimalCreator.createHomeAnimal(type, name, birthday);
                    homeAnimal.setHomeAnimalId(id);
                }
                return homeAnimal;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int create(HomeAnimal homeAnimal) {
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                SQLstr = "INSERT INTO home_animal (name, birthday, animals_id) SELECT ?, ?, (SELECT Id FROM home_animals WHERE name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setString(1, homeAnimal.getName());
                prepSt.setDate(2, Date.valueOf(homeAnimal.getBirthdayDate()));
                prepSt.setString(3, homeAnimal.getClass().getSimpleName());

                rows = prepSt.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void train (int id, String command){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String SQLstr = "INSERT INTO home_animal_command (home_animal_id, command_id) SELECT ?, (SELECT id FROM commands WHERE name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, id);
                prepSt.setString(2, command);

                prepSt.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<String> getCommandsById (int homeAnimalId, int commands_type){

        // commands type = 1  - получить команды, выполняемые питомцем, 2 - команды, выполнимые животным того рода, к которому относится питомец

        List <String> commands = new ArrayList <>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                if (commands_type == 1){
                    SQLstr = "SELECT command_name FROM home_animal_command pc JOIN commands c ON pc.command_id = c.id WHERE pc.home_animal_id = ?";
                } else {
                    SQLstr = "SELECT command_name FROM commands c JOIN genus_command gc ON c.id = gc.command_id WHERE gc.animals_id = (SELECT animals_id FROM home_animal WHERE id = ?)";
                }
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, homeAnimalId);
                resultSet = prepSt.executeQuery();
                while (resultSet.next()) {
                    commands.add(resultSet.getString(1));
                }
                return commands;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int update(HomeAnimal homeAnimal) {
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                SQLstr = "UPDATE home_animal SET name = ?, birthday = ? WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);

                prepSt.setString(1, homeAnimal.getName());
                prepSt.setDate(2, Date.valueOf(homeAnimal.getBirthdayDate()));
                prepSt.setInt(3,homeAnimal.getHomeAnimalId());

                rows = prepSt.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete (int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                SQLstr = "DELETE FROM home_animal WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1,id);
                prepSt.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(HomeAnimalDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/Resources/database.properties")) {

            props.load(fis);
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        }
    }
}
