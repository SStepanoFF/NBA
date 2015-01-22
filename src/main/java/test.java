import framework.DataBase;
import framework.Operations;
import framework.ProprtyLoader;

/**
 * Created by sergii.stepanov on 20.01.2015.
 */
public class test {
    public static void main(String []args){
        DataBase dataBase=new DataBase();

        int q=dataBase.gamesCount();
        ProprtyLoader.writeToFile("" + q);
    }
}
