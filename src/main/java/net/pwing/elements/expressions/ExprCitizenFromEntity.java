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

public class ExprCitizenFromEntity extends SimpleExpression<NPC> {
    private Expression<Entity> entity;

    @Override
    protected NPC[] get(Event event) {
        Entity ent = entity.getSingle(event);
        if (ent != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getNPC(ent);
            if (npc != null) {
                return new NPC[]{npc};
            }
        }
        return new NPC[0];
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
        return "citizen from entity " + entity.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        // Implementation here
        entity = (Expression<Entity>) expressions[0];
        return true;
    }
}
