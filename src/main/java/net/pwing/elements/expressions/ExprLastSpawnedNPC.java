package net.pwing.elements.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExprLastSpawnedNPC extends SimpleExpression<NPC> {
    @Override
    protected NPC[] get(Event event) {
        List<NPC> npcs = new ArrayList<>();
        CitizensAPI.getNPCRegistry().forEach(npcs::add);
        npcs.sort(Comparator.comparingInt(NPC::getId));
        if (!npcs.isEmpty()) {
            return new NPC[]{npcs.get(npcs.size() - 1)};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends NPC> getReturnType() {
        return NPC.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "last spawned NPC";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        return true;
    }
}
