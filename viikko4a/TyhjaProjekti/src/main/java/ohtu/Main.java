package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;


public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "014483914";
        if ( args.length>0) {
            studentNr = args[0];
        }
        System.out.println("NYT ALKAA");
// Opiskelija tietojen haku
        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url)   
                .execute()
                .returnContent()
                .asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
// Kurssitietojen haku
 String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";

        String bodyText2 = Request.Get(url2)   
                .execute()
                .returnContent()
                .asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText2 );

        Gson mapper2 = new Gson();
        Course course = mapper2.fromJson(bodyText2, Course.class);
        

// Tulostus
//        System.out.println("Oliot:");
        System.out.println("Kurssi: " + course.getName());
        int i = 0;
        int j = 0;
        System.out.println("Opiskelijanumero: 014483914");
        for (Submission submission : subs) {
            System.out.print("viikko " + submission.getWeek() + ": ");
            System.out.print("aikaa kului yhteensä " + submission.getHours() + " tuntia, ");
            j = j + submission.getHours();
            System.out.print("Tehdyt tehtävät:");
            if (submission.getA1()) { System.out.print("1 "); i++;}
            if (submission.getA2()) { System.out.print("2 "); i++;}
            if (submission.getA3()) { System.out.print("3 "); i++;}
            if (submission.getA4()) { System.out.print("4 "); i++;}
            if (submission.getA5()) { System.out.print("5 "); i++;}
            if (submission.getA6()) { System.out.print("6 "); i++;}
            if (submission.getA7()) { System.out.print("7 "); i++;}
            if (submission.getA8()) { System.out.print("8 "); i++;}
            if (submission.getA9()) { System.out.print("9 "); i++;}
            if (submission.getA10()) { System.out.print("10 "); i++;}
            if (submission.getA11()) { System.out.print("11 "); i++;}
            if (submission.getA12()) { System.out.print("12 "); i++;}
            System.out.println("");
            
//            System.out.println(submission);
        }
        System.out.println("Yhteensä " + i + " tehtävää ja " + j + " tuntia.");
//        System.out.println(driver.getPageSource());

    }
}
