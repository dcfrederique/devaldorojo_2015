package com.frederique.devaldo.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by frederique on 23.06.15.
 */
@ParseClassName("Calendar")
public class Calendar extends ParseObject {
    public Calendar(){

    }
    public Date getDate(){
        return getDate("Date");
    }
    public Team getHomeTeam(){
        return (Team)getParseObject("HomeTeam");
    }
    public Team getAwayTeam(){
        return (Team)getParseObject("AwayTeam");
    }


}
