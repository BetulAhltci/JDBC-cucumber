package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class Stepdefinition {

    // JDBC (DB) testi yapilmaya baslamadan önce Sistem Yöneticisi ile görüsülüp Database
    // bilgileri alinir.

        /*
        Database baglantisi icin gerekli bilgiler.

        type: jdbc:mysql
        host/ip: 45.84.206.41
        port:3306
        database: u480337000_tlb_training
        username: u480337000_tbl_training_u
        password: pO9#4bmxU
         */

        String url= "jdbc:mysql://45.84.206.41:3306/u480337000_tlb_training";
        String username= "u480337000_tbl_training_u";
        String password="pO9#4bmxU";

        // Database Sistem Yöneticisinden alinan bilgiler ile bir Url olusturuldu
        // Username ve password String data type'inda bir veriable atandi.

        Connection connection;
        Statement statement;
        ResultSet resultset;

        List<Object> staffId=new ArrayList<>();
        List<Object> addressList=new ArrayList<>();

        @Given("Database ile iletisimi baslat")
        public void database_ile_iletisimi_baslat() throws SQLException {

                connection= DriverManager.getConnection(url,username,password);
                statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

             // connection objemizi olusturduk ve (url,username ve password datalarini connection objesinin icine koyduk.)
             // olusturdugumuz connection objesini kullanarak typelari belli bir satatement create ettik.
        }
        @Then("Query statement araciligi ile database gonderilir")
        public void query_statement_araciligi_ile_database_gonderilir() throws SQLException {
                String query= "SELECT * FROM u480337000_tlb_training.users;";

               resultset= statement.executeQuery(query);
                // statement araciligi ile Database'e gönderdigimiz query sonucunu (datayi) bir resultset icinde store ettik.

        }
        @Then("Databaseden donen resulset verisi test edilir")
        public void databaseden_donen_resulset_verisi_test_edilir() throws SQLException {

                resultset.first();
                System.out.println(resultset.getString("first_name"));//Super

                String actualName= resultset.getString("first_name");
                String expectedName="Super";

                assertEquals(expectedName,actualName);

                resultset.next();
                System.out.println(resultset.getString("first_name"));//2.sıradaki isim

                resultset.next();
                System.out.println(resultset.getString("first_name"));//3.sıradaki isim

                resultset.absolute(11);
                System.out.println(resultset.getString("first_name"));//11.sıradaki isim

                System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");

                resultset.absolute(0);

                int sira=1;
                while(resultset.next()){
                        System.out.println(sira+"--"+resultset.getString("first_name"));
                        sira++;
                }

                resultset.absolute(6);
                System.out.println(resultset.getString("email"));
                System.out.println(resultset.getString("phone"));
                System.out.println(resultset.getString("username"));
                System.out.println(resultset.getString("password"));

        }
        @Then("Database kapatilir")
        public void database_kapatilir() throws SQLException {
                connection.close();
        }
//==================================================================================0
        @Given("Database baglantisi kurulur")
        public void database_baglantisi_kurulur() {

        DBUtils.getConnection();

}
        @Given("Staff tablosundaki {string} leri listelenir")
        public void staff_tablosundaki_leri_listelenir(String id) {

                staffId=DBUtils.getColumnData("SELECT * FROM u480337000_tlb_training.staff",id);
                System.out.println(staffId);


        }
        @Given("Verilen {string} dogrulanir")
        public void verilen_dogrulanir(String verilenID) {
                assertTrue(staffId.toString().contains(verilenID));



        }

        //===========================================================0



        @Given("{string} degeri verilen customerin {string} gümcellenir")
        public void degeri_verilen_customerin_guncellenir(String id, String address) throws SQLException {
                String query="UPDATE u480337000_tlb_training.customer_addresses\n" +
                                "SET address= '"+address+"' WHERE id="+id;
                System.out.println(query);

                DBUtils.update(query);


        }
        @Given("custumer addresss tablosundaki {string} bilgileri istenir")
        public void custumer_addresss_tablosundaki_bilgileri_istenir(String columname) {

                String query= "SELECT * FROM u480337000_tlb_training.customer_addresses";

              addressList =DBUtils.getColumnData(columname,query);
                System.out.println(addressList);


        }
        @Given("customerin {string} guncelledigi dogrulanir")
        public void customerin_guncelledigi_dogrulanir(String string) {

        }
        @Given("Data baglantisi kapatilir")
        public void data_baglantisi_kapatilir() {

        }



}
