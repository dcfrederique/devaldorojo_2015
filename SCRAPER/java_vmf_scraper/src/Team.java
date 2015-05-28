import java.util.Formatter;

/**
 * Created by Frederique on 28-4-2015.
 */
public class Team {
    public String naam;
    public int gespeeld;
    public int gewonnen;
    public int verloren;
    public int gelijk;
    public int doelpuntenvoor;
    public int doelpuntentegen;
    public int punten;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getGespeeld() {
        return gespeeld;
    }

    public void setGespeeld(int gespeeld) {
        this.gespeeld = gespeeld;
    }

    public int getGewonnen() {
        return gewonnen;
    }

    public void setGewonnen(int gewonnen) {
        this.gewonnen = gewonnen;
    }

    public int getVerloren() {
        return verloren;
    }

    public void setVerloren(int verloren) {
        this.verloren = verloren;
    }

    public int getGelijk() {
        return gelijk;
    }

    public void setGelijk(int gelijk) {
        this.gelijk = gelijk;
    }

    public int getDoelpuntenvoor() {
        return doelpuntenvoor;
    }

    public void setDoelpuntenvoor(int doelpuntenvoor) {
        this.doelpuntenvoor = doelpuntenvoor;
    }

    public int getDoelpuntentegen() {
        return doelpuntentegen;
    }

    public void setDoelpuntentegen(int doelpuntentegen) {
        this.doelpuntentegen = doelpuntentegen;
    }

    public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);

        String template = "%-25s %-15d %-15d %-15d %-15d %-15d %-15d %-15d"; // a rough guess!
        formatter.format(template, getNaam(), getGespeeld(), getGewonnen(),getVerloren(),
                getGelijk(),getDoelpuntenvoor(),getDoelpuntentegen(),getPunten());
        return stringBuilder.toString();
    }

}
