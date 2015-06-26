package com.frederique.devaldo.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by frederique on 26.06.15.
 */
@ParseClassName("PlayerMatchPresence")
public class PlayerMatchPresence extends ParseObject {
    public PlayerMatchPresence(){

    }
    public Calendar getMatch(){
        return (Calendar)getParseObject("MatchCalendarId");
    }
}
