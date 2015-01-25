package framework;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class DataBase {
    private Connection conn;
    private Statement statement;
    private ResultSet resultSets;

    public DataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//Регистрируем драйвер
            conn = DriverManager.getConnection("jdbc:mysql://mysql-kstest2.t1.tenet:3306/nba_t1_ssstest_com_PRR_3227",
                    "member", "1234");//Установка соединения с БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
            ProprtyLoader.writeToFile("Can't connect to DB\n");
            //throw new RuntimeException("Assert error BD connection");
        }
    }

    public int taskCount(String gameID){
        try{
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT COUNT(*) as C FROM GAMES JOIN TASKS ON GAMES.GAME_ID=TASKS.GAME_ID WHERE EXTERNAL_ID="+gameID);
            while(resultSets.next()){
                return resultSets.getInt("C");
            }
            return resultSets.getInt("C");  //MUST CORRECT!!!
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        finally{
            try {
                resultSets.close();
                statement.close();
               // conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int gamesCount(String date){
        try{
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT COUNT(*) AS C FROM GAMES WHERE  STARTUTC LIKE '"+date+"%' AND HOME_TEAM_ID=117");
            while(resultSets.next()){
                return resultSets.getInt("C");
            }
            return resultSets.getInt("C");  //MUST CORRECT!!!
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        finally{
            try {
                resultSets.close();
                statement.close();
               // conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
