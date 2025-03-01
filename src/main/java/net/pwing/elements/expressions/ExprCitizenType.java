package net.pwing.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean; 
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import net.citizensnpcs.api.trait.trait.MobType;

import javax.annotation.Nullable;

public class ExprCitizenType extends SimpleExpression<EntityType> {
    private Expression<Integer> npcId;

    @Override
    protected EntityType[] get(Event event) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) {
                return new EntityType[]{npc.getEntity().getType()};
            }
        }
        return new EntityType[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends EntityType> getReturnType() {
        return EntityType.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "type of citizen with id " + npcId.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (expressions.length > 0 && expressions[0] != null) {
            npcId = (Expression<Integer>) expressions[0];
            return true;
        }
        return false;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return new Class[]{EntityType.class};
        }
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null && mode == Changer.ChangeMode.SET) {
                EntityType newType = (EntityType) delta[0];
                npc.setBukkitEntityType(newType);
            }
        }
    }
}
