package controllers;

import Model.*;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.control.Alert;
import play.libs.Json;
import play.mvc.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getClubData(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();

        clublistData = (List<SportsClub>) deserialized.get(0);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(variableName2);

    }
    public Result getMatchData(){
        List <Match> matchListData = new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();

        matchListData = (List<Match>) deserialized.get(1);
        JsonNode variableName2= Json.toJson(matchListData);
        return ok(variableName2);
    }
    public Result getMatchDataDate(String date){
        List <Match> matchListData = new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();

        matchListData = (List<Match>) deserialized.get(1);
        List<Match> searchArray = new ArrayList<>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        try {
            LocalDate dateIn = LocalDate.parse(date, dateFormat);
        } catch (Exception ex) {
            return ok("ERROR WRONG DATE FORMAT");
        }

        for (Match match :matchListData){
            if (match.getDate().toString().equals(date)){
                searchArray.add(match);
            }
        }

        JsonNode variableName2= Json.toJson(searchArray);
        return ok(variableName2);
    }
    public Result genarateMatch() {
        PremierLeagueManager club = PremierLeagueManager.getInstance();

        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        List <SportsClub> clublistData= new ArrayList<>();
        List <Match> matchListData = new ArrayList<>();

        clublistData = (List<SportsClub>) deserialized.get(0);
        matchListData = (List<Match>) deserialized.get(1);

        if (clublistData.size()<2){
            return ok("you need 2 or more teams");
        }

//            date
        LocalDate start = LocalDate.of(2020, Month.JANUARY, 31);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));
//            score
        int oneClubScore = generateScore();
        int twoClubScore = generateScore();
//            team
        SportsClub oneClub = clublistData.get(generateTeam());
        SportsClub twoClub = clublistData.get(generateTeam());
        while (oneClub==twoClub){
//            System.out.println("repeat");
            twoClub = clublistData.get(generateTeam());
        }

        matchListData.add(club.addMatch(randomDate, oneClub, oneClubScore, twoClub, twoClubScore));
        Serialize load = new Serialize();
        load.serialize(clublistData,matchListData);
        return ok("Team A :"+oneClub.getName()+
                "\nTeam A Score :"+oneClubScore+
                "\nTeam B :"+twoClub.getName()+
                "\nTeam B Score :"+twoClubScore+
                "\nDate : "+randomDate+
                "\nLengthArray :"+matchListData.size());
    }
    public int generateScore(){
        return (int) (Math.random() * ((20 - 1) + 1)) + 1;
    }
    public int generateTeam(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);
        return (int) (Math.random() * (clublistData.size()));
    }

    public Result sortGoal(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);

        clublistData.sort((SportsClub::sortGoal));
        Collections.reverse(clublistData);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(variableName2);
    }
    public Result sortWins(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);

        clublistData.sort((SportsClub::sortWins));
        Collections.reverse(clublistData);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(variableName2);
    }
    public Result sortReset(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);

        clublistData.sort((SportsClub::sortPointsGoal));
        Collections.reverse(clublistData);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(variableName2);
    }





}
