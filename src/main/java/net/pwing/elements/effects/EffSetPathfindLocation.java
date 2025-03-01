package net.pwing.elements.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSetPathfindLocation extends Effect {
    private Expression<Integer> npcId;
    private Expression<Location> location;

    @Override
    protected void execute(Event event) {
        Integer id = npcId.getSingle(event);
        Location loc = location.getSingle(event);
        
        if (id != null && loc != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null && npc.isSpawned()) {
                Navigator navigator = npc.getNavigator();
                navigator.setTarget(loc);
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "make citizen with id " + npcId.toString(event, b) + " pathfind to " + location.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        npcId = (Expression<Integer>) expressions[0];
        location = (Expression<Location>) expressions[1];
        return true;
    }
}
