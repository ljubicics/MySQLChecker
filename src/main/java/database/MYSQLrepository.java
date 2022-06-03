package database;

import com.opencsv.CSVWriter;
import database.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.Data;
import resource.DBNode;
import resource.data.Row;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import javax.swing.plaf.nimbus.State;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Data
public class MYSQLrepository implements Repository{

    private Settings settings;
    private Connection connection;

    public MYSQLrepository(Settings settings) {
        this.settings = settings;
    }

    private void initConnection() throws SQLException, ClassNotFoundException{
        String ip = (String) settings.getParameter("mysql_ip");
        String database = (String) settings.getParameter("mysql_database");
        String username = (String) settings.getParameter("mysql_username");
        String password = (String) settings.getParameter("mysql_password");
        //Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+database,username,password);


    }

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }


    @Override
    public DBNode getSchema() {

        try{
            this.initConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            InformationResource ir = new InformationResource("RAF_BP_TIM30");

            String tableType[] = {"TABLE"};
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);

            while (tables.next()){

                String tableName = tables.getString("TABLE_NAME");
                if(tableName.contains("trace"))continue;
                Entity newTable = new Entity(tableName, ir);
                ir.addChild(newTable);

                //Koje atribute imaja ova tabela?

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);

                while (columns.next()){

                    // COLUMN_NAME TYPE_NAME COLUMN_SIZE ....

                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");

                    System.out.println(columnType);

                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));

//                    ResultSet pkeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
//
//                    while (pkeys.next()){
//                        String pkColumnName = pkeys.getString("COLUMN_NAME");
//                    }


                    Attribute attribute = new Attribute(columnName, newTable,
                            AttributeType.valueOf(
                                    Arrays.stream(columnType.toUpperCase().split(" "))
                                    .collect(Collectors.joining("_"))),
                            columnSize);
                    newTable.addChild(attribute);

                }



            }


            //TODO Ogranicenja nad kolonama? Relacije?


            return ir;
            //String isNullable = columns.getString("IS_NULLABLE");
            // ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, table.getName());
            // ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, table.getName());

        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        catch (ClassNotFoundException e2){ e2.printStackTrace();}
        finally {
            this.closeConnection();
        }

        return null;
    }

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while (rs.next()){

                Row row = new Row();
                row.setName(from);

                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

    public List<Row> runQuery(String query) {
        List<Row> rows = new ArrayList<>();

        try{
            this.initConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            String name = resultSetMetaData.getColumnName(1);

            while (rs.next()){
                Row row = new Row();
                row.setName(name);

                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

    public void bulkImport(List<String[]> list, String selected) {
        try {
            this.initConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + selected);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            String valueStr = "";

            for(int i = 0; i < count - 1; i++) {
                valueStr += "?,";
            }
            valueStr += "?";

            System.out.println(valueStr);
            int INT = 4;
            int DECIMAL = 3;
            int DATE = 91;
            int VARCHAR = 12;

            for(int i = 0; i < list.size(); i++) {
                String query = "INSERT INTO " + selected + " VALUES(" + valueStr + ")";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                String[] line = list.get(i);
                for(int j = 0; j < line.length; j++) {
                    if(resultSetMetaData.getColumnType(j + 1) == DECIMAL) {
                        preparedStatement.setFloat(j + 1, Float.parseFloat(line[j]));
                        continue;
                    } else if(resultSetMetaData.getColumnType(j + 1) == INT) {
                        preparedStatement.setInt(j + 1, Integer.parseInt(line[j]));
                        continue;
                    } else if(resultSetMetaData.getColumnType(j + 1) == DATE) {
                        preparedStatement.setDate(j + 1, Date.valueOf(line[j]));
                        continue;
                    } else if(resultSetMetaData.getColumnType(j + 1) == VARCHAR) {
                        preparedStatement.setString(j + 1, line[j].toString());
                        continue;
                    } else {
                        preparedStatement.setString(j + 1, line[j]);
                    }
                }
                preparedStatement.executeUpdate();
               System.out.println("USPEO INSERTTTT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }
    public void resultSetExport(String selected) {
        try {
            this.initConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + selected);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();

            List<String[]> list = new ArrayList<>();

            int br = 0;
            while(resultSet.next()) {
                String row = "";
                for(int i = 1; i < columnCount + 1; i++)  {
                    row += resultSet.getString(i) + ",";
                }
                list.add(row.split(","));
            }
            try (CSVWriter writer = new CSVWriter(new FileWriter("src/test.csv"))) {
                writer.writeAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }
}
