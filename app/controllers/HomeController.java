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
import java.util.*;

import utils.ApplicationUtil;


class AppSummary {
    private String content;

    AppSummary(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

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
        return ok(ApplicationUtil.createResponse(variableName2,true));

    }
    public Result getMatchData(){
        List <Match> matchListData = new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();

        matchListData = (List<Match>) deserialized.get(1);
        matchListData.sort(Comparator.comparing(Match::getDate));

        JsonNode variableName2= Json.toJson(matchListData);
        return ok(ApplicationUtil.createResponse(variableName2,true));
    }
    public Result getMatchDataDate(Http.Request request){
        String dateFind;
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            dateFind = json.findValue("date").textValue();
            if (dateFind == null) {
                return badRequest("Missing parameter [name]");
            } else {
                System.out.println(dateFind);
            }
        }

        List <Match> matchListData;
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();

        matchListData = (List<Match>) deserialized.get(1);
        List<Match> searchArray = new ArrayList<>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        try {
            LocalDate dateIn = LocalDate.parse(dateFind, dateFormat);
        } catch (Exception ex) {
            return badRequest("ERROR WRONG DATE FORMAT");
        }

        for (Match match :matchListData){
            if (match.getDate().toString().equals(dateFind)){
                searchArray.add(match);
            }
        }
        for(Match mt:searchArray){
            System.out.println(mt);
        }
        JsonNode variableName2= Json.toJson(searchArray);
        return ok(ApplicationUtil.createResponse(variableName2,true));
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
        Match genMatch = club.addMatch(randomDate, oneClub, oneClubScore, twoClub, twoClubScore);
        matchListData.add(genMatch);
        Serialize load = new Serialize();
        load.serialize(clublistData,matchListData);
        JsonNode variableName2= Json.toJson(genMatch);
        return ok(ApplicationUtil.createResponse(variableName2,true));
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
        return ok(ApplicationUtil.createResponse(variableName2,true));
    }
    public Result sortWins(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);

        clublistData.sort((SportsClub::sortWins));
        Collections.reverse(clublistData);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(ApplicationUtil.createResponse(variableName2,true));
    }
    public Result sortReset(){
        List <SportsClub> clublistData= new ArrayList<>();
        Model.Serialize data = new Model.Serialize();
        ArrayList <Object> deserialized = data.deserialize();
        clublistData = (List<SportsClub>) deserialized.get(0);

        clublistData.sort((SportsClub::sortPointsGoal));
        Collections.reverse(clublistData);
        JsonNode variableName2= Json.toJson(clublistData);
        return ok(ApplicationUtil.createResponse(variableName2,true));
    }

    public Result appSummary() {
        JsonNode jsonNode = Json.toJson(new AppSummary("IIT Java Play-Angular Session (Week 3)"));
        return ok(jsonNode).as("application/json");
    }

    public Result postTest() {
        JsonNode jsonNode = Json.toJson(new AppSummary("Sample Post Request => Data has been sent successfully"));
        return ok(jsonNode).as("application/json");
    }





}
