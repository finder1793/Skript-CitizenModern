package net.pwing.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Citizen Is Navigating")
@Description("Checks if a citizen NPC is currently navigating to a target.")
@Examples({
    "if citizen with id 3 is navigating:",
    "  send \"NPC is on the move!\" to player"
})
@Since("1.0.0")
public class CondIsNavigating extends PropertyCondition<Integer> {

    @Override
    public boolean check(Integer id) {
        NPC npc = CitizensAPI.getNPCRegistry().getById(id);
        if (npc != null && npc.isSpawned()) {
            return npc.getNavigator().isNavigating();
        }
        return false;
    }

    @Override
    protected String getPropertyName() {
        return "navigating";
    }
}
