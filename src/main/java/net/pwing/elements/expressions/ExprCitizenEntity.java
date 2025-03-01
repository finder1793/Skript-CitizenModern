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

public class ExprCitizenEntity extends SimpleExpression<Entity> {
    private Expression<Integer> npcId;

    @Override
    protected Entity[] get(Event event) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) {
                return new Entity[]{npc.getEntity()};
            }
        }
        return new Entity[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "entity of citizen with id " + npcId.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        // Implementation here
        npcId = (Expression<Integer>) expressions[0];
        return true;
    }
}
