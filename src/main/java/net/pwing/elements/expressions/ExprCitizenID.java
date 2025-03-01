package net.pwing.elements.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprCitizenID extends SimpleExpression<Integer> {
    private Expression<Entity> entity;

    @Override
    protected Integer[] get(Event event) {
        Entity ent = entity.getSingle(event);
        if (ent != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getNPC(ent);
            if (npc != null) {
                return new Integer[]{npc.getId()};
            }
        }
        return new Integer[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "id of citizen from entity " + entity.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        return true;
    }
}
