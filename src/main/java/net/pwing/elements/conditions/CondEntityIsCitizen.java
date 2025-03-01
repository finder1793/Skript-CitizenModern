package net.pwing.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Entity Is Citizen")
@Description("Checks if an entity is a Citizens NPC.")
@Examples({
    "if target entity is a citizen:",
    "  send \"You're looking at an NPC!\" to player"
})
@Since("1.0.0")
public class CondEntityIsCitizen extends PropertyCondition<Entity> {

    @Override
    public boolean check(Entity entity) {
        return entity != null && CitizensAPI.getNPCRegistry().isNPC(entity);
    }

    @Override
    protected String getPropertyName() {
        return "citizen";
    }
}
