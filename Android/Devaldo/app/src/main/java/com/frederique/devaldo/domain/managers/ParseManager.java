package com.frederique.devaldo.domain.managers;

import com.frederique.devaldo.domain.Player;
import com.frederique.devaldo.domain.Team;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by frederique on 23.06.15.
 */
public class ParseManager {
    private Team team;
    public ParseManager()
    {
        fetchTeam("Devaldo Rojo");
    }

    private void fetchTeam(String s) {
        try {
             team = (Team)ParseQuery.getQuery("Team").whereEqualTo("Name",s).getFirst();
             List<Player> list = team.getPlayers();
             team.getName();
            team.pin();
            ParseObject.pinAll(list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Team getTeam() {
        return team;
    }


}
