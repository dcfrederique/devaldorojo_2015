import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        try{
            UserAgent userAgent = new UserAgent();
            userAgent.visit("http://www.ledenbeheer.vmf.be/Publicatie/Klassement?ReeksId=324");
            Table table = userAgent.doc.getTable("<table class=klassement>");
            for(int i = 1;i<15;i++) {
                Elements elements = table.getRow(i);
                List<Element> elementen = elements.toList();
                Team team = new Team();
                team.setNaam(elementen.get(1).innerHTML().toString().replaceAll("\\r\\n", "").trim());
                team.setGespeeld(Integer.parseInt(elementen.get(2).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setGewonnen(Integer.parseInt(elementen.get(3).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setVerloren(Integer.parseInt(elementen.get(4).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setGelijk(Integer.parseInt(elementen.get(5).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setDoelpuntenvoor(Integer.parseInt(elementen.get(6).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setDoelpuntentegen(Integer.parseInt(elementen.get(7).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                team.setPunten(Integer.parseInt(elementen.get(8).innerHTML().toString().replaceAll("\\r\\n", "").replaceAll(" ", "")));
                System.out.println(team);
            }






        }
        catch(JauntException e){
            System.err.println(e);
        }
    }
}
