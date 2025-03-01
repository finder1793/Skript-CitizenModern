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

@Name("Citizen Is Spawned")
@Description("Checks if a citizen NPC is currently spawned in the world.")
@Examples({
    "if citizen with id 5 is spawned:",
    "  send \"Citizen 5 is active in the world!\" to player"
})
@Since("1.0.0")
public class CondCitizenIsSpawned extends PropertyCondition<Integer> {

    @Override
    public boolean check(Integer id) {
        NPC npc = CitizensAPI.getNPCRegistry().getById(id);
        return npc != null && npc.isSpawned();
    }

    @Override
    protected String getPropertyName() {
        return "spawned";
    }
}
