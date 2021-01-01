package controllers;

import Model.FootballClub;
import Model.Match;
import Model.SportsClub;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

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
    public Result first() {
        return ok("Heyy");
    }
    public Result getList(){
        return ok();
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

        for (Match match :matchListData){
            if (match.getDate().toString().equals(date)){
                searchArray.add(match);
            }
        }

        JsonNode variableName2= Json.toJson(searchArray);
        return ok(variableName2);
    }


}
