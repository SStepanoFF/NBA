import framework.DataBase;
import framework.Operations;
import framework.ProprtyLoader;

/**
 * Created by sergii.stepanov on 20.01.2015.
 */
public class test {
    public static void main(String []args){
        //DataBase dataBase=new DataBase();

        //int q=dataBase.gamesCount();
        //ProprtyLoader.writeToFile("" + q);

        String text="2015-01-19";
        String res=text.substring(5, 7);
        System.out.print(res);
        String res1="";
        if (Integer.parseInt(res.substring(0,1))==0) res=res.substring(1);
        System.out.print(res);
    }
}
