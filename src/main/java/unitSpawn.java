
import arc.util.*;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.mod.Mod;
import mindustry.type.UnitType;

public class unitSpawn extends Mod {

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register( "spawn", "<unit> <count> <team>", "Spawn units", (args, player) ->{
            if(!player.admin){
                player.sendMessage("[red]You are not admin");
                return;
            }

            UnitType sunit = Vars.content.units().
                    find(b -> b.name.equals(args[0]));

            int count;
            try {
                count = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("[red]Count must be number!");
                return;
            }
            Team tteam;
            switch (args[2]) {
                case "sharded":
                    tteam = Team.sharded;
                    break;
                case "blue":
                    tteam = Team.blue;
                    break;
                case "crux":
                    tteam = Team.crux;
                    break;
                case "derelict":
                    tteam = Team.derelict;
                    break;
                case "green":
                    tteam = Team.green;
                    break;
                case "purple":
                    tteam = Team.purple;
                    break;
                default:
                    player.sendMessage("[accent]Teams: [yellow]sharded[], [blue]blue[], [red]crux[], [gray]derelict[], [green]green[], [purple]purple[].");
                    return;
            }

            if (sunit != null) {
                for (int i = 0; count > i; i++) {
                    Unit tunit = sunit.spawn(tteam, player.x, player.y);
                }
                player.sendMessage("[green]You are spawning" + " " +"[accent]"+count + " " +"[accent]"+sunit + " " + "[green]for" + " " +"[accent]"+tteam + " " + "[green]team");
            } else {
                player.sendMessage("[red]Units: [accent]dagger, mace, fortress, scepter, reign, nova, pulsar, quasar, vela, corvus, crawler, atrax, spiroct, arkyid, toxopid, mono, poly, mega, quad, oct, flare, eclipse, horizon, zenith, antumbra, risso, minke, bryde, sei, omura");
            }

        });
    }
}
