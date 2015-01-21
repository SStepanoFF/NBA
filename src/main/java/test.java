import framework.DataBase;
import framework.ProprtyLoader;

/**
 * Created by sergii.stepanov on 20.01.2015.
 */
public class test {
    public static void main(String []args){
        DataBase dataBase=new DataBase();
        int q=dataBase.taskCount(111111132);
        ProprtyLoader.writeToFile("" + q);
    }
}
