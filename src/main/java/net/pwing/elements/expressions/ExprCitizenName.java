package net.pwing.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprCitizenName extends SimpleExpression<String> {
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
        return "name of citizen with id " + npcId.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        // Implementation here
        return true;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        Integer id = npcId.getSingle(event);
        if (id != null) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null && mode == Changer.ChangeMode.SET) {
                npc.setName((String) delta[0]);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return new Class[]{String.class};
        }
        return null;
    }
}
