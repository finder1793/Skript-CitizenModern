package net.pwing.elements.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean; // Add this import
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprNPCName extends SimpleExpression<String> {
    private Expression<Integer> npcId;

    @Override
    protected String[] get(Event event) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) {
                return new String[]{npc.getName()};
            }
        }
        return new String[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "npc name of id " + npcId.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        // Implementation here
        npcId = (Expression<Integer>) expressions[0];
        return true;
    }
}
