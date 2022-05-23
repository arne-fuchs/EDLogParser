package de.paesserver.frames.logframe;

import de.paesserver.journalLog.DatabaseSingleton;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SystemInfo {

    public long currentSystemAddress = 0;


    String systemText = "";
    String bodyCountText = "";
    String bodySignalText = "";
    public SystemInfo(long currentSystemAddress) {
        this.currentSystemAddress = currentSystemAddress;
        setTextForSystem();
        setTextForBodyCounts();
    }

    public String getText() {
        return systemText + bodyCountText + bodySignalText;
    }

    public void setTextForSystem(){
        String query = "SELECT StarSystem,SystemAllegiance,SystemEconomy_Localised,SystemSecondEconomy_Localised,SystemGovernment_Localised,SystemSecurity_Localised,Population FROM SYSTEM WHERE SystemAddress = ?";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setLong(1, currentSystemAddress);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                systemText = "---"+ resultSet.getString("StarSystem") +"---\n" +
                        "Allegiance:  \t" + resultSet.getString("SystemAllegiance") + "\n" +
                        "Economy:     \t" + resultSet.getString("SystemEconomy_Localised") + "\n" +
                        "Sec. Economy:\t" + resultSet.getString("SystemSecondEconomy_Localised") + "\n" +
                        "Government:  \t" + resultSet.getString("SystemGovernment_Localised") + "\n\n" +

                        "Security:    \t" + resultSet.getString("SystemSecurity_Localised") + "\n"+
                        "Population:  \t" + resultSet.getLong("Population") + "\n";
            }
        } catch (SQLException e) {
        }
    }

    public void setTextForBodyCounts(){
        String query = "SELECT bodyCount, nonBodyCount FROM SYSTEM WHERE SystemAddress = ?;";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setLong(1,currentSystemAddress);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String bodyCount = resultSet.getString("bodyCount");
                String nonBodyCount = resultSet.getString("nonBodyCount");

                bodyCountText =
                        "\nBodies:      \t" + (bodyCount == null ? "Unknown" : bodyCount) + "\n" +
                        "Non-bodies:  \t" + (nonBodyCount == null ? "Unknown" : nonBodyCount) + "\n";
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void setTextForBodySignals(){
        StringBuilder builder = new StringBuilder();
        String query = "SELECT sum(Count),BodyName,Type,Type_Localised,count FROM BODYSIGNAL WHERE SystemAddress = ? GROUP BY Type";

        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setLong(1, currentSystemAddress);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String type = resultSet.getString("Type_Localised");
                String body = resultSet.getString("BodyName");
                long count = resultSet.getLong("count");
                long totalCount = resultSet.getLong("sum(Count)");

                builder.append("Total ");
                if(type == null)
                    builder.append(resultSet.getString("Type"));
                else
                    builder.append(type);
                builder.append(": ").append(totalCount).append("\n");

                builder.append("Max ").append(type).append(": ")
                        .append(body).append(": ").append(count).append("\n\n");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        bodySignalText = builder.toString();
    }
}