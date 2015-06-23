package com.frederique.devaldo.domain;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by frederique on 23.06.15.
 */
@ParseClassName("Player")
public class Player extends ParseObject{
    private String position,firstName,lastName;
    private Team team;
    private int number;

    public Player(){

    }

    public String getPosition(){
        position = getString("Position");
        return position;
    }
    public String getFirstName(){
        firstName =  getString("FirstName");
        return firstName;
    }
    public Team getTeam(){
        team = (Team)getParseObject("Team");
        return team;
    }
    public String getLastName(){
        lastName = getString("LastName");
        return lastName;
    }
    public int getNumber(){
        number = getNumber("Number").intValue();
        return number;
    }


}
