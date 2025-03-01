package net.pwing.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffDespawn extends Effect {
    private Expression<Integer> npcId;
    private boolean respawn;

    @Override
    protected void execute(Event event) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) {
                if (respawn) {
                    if (!npc.isSpawned()) {
                        npc.spawn(npc.getStoredLocation());
                    }
                } else {
                    if (npc.isSpawned()) {
                        npc.despawn();
                    }
                }
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return (respawn ? "respawn" : "despawn") + " citizen with id " + npcId.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        npcId = (Expression<Integer>) expressions[0];
        respawn = matchedPattern == 1;
        return true;
    }
}
