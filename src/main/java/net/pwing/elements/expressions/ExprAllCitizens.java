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
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class ExprAllCitizens extends SimpleExpression<String> {

    @Override
    protected String[] get(Event event) {
        List<NPC> npcs = new ArrayList<>();
        CitizensAPI.getNPCRegistry().forEach(npcs::add);
        npcs.sort(Comparator.comparingInt(NPC::getId));
        List<String> names = new ArrayList<>();
        for (NPC npc : npcs) {
            names.add(npc.getName());
        }
        return names.toArray(new String[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "all citizens";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        return true;
    }
}
