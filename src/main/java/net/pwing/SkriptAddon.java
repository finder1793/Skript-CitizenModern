package net.pwing;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import net.pwing.elements.expressions.*;
import net.pwing.elements.effects.*;
import net.pwing.elements.events.CitizenEvents;
import net.pwing.elements.conditions.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import net.citizensnpcs.api.npc.NPC;

public class SkriptAddon {
    private final SkriptCitizensModern plugin;

    public SkriptAddon(SkriptCitizensModern plugin) {
        this.plugin = plugin;
    }

    public void register() {
        Skript.registerAddon(plugin);
        
        // Register expressions
        Skript.registerExpression(ExprNPCName.class, String.class, ExpressionType.SIMPLE, "npc name of id %integer%");
        Skript.registerExpression(ExprAllCitizens.class, String.class, ExpressionType.SIMPLE, "all citizens");
        Skript.registerExpression(ExprCitizenEntity.class, Entity.class, ExpressionType.SIMPLE, "entity of citizen with id %integer%");
        Skript.registerExpression(ExprCitizenFromEntity.class, NPC.class, ExpressionType.SIMPLE, "citizen from entity %entity%");
        Skript.registerExpression(ExprCitizenID.class, Integer.class, ExpressionType.SIMPLE, "id of citizen from entity %entity%");
        Skript.registerExpression(ExprCitizenName.class, String.class, ExpressionType.SIMPLE, "name of citizen with id %integer%");
        Skript.registerExpression(ExprCitizenProtection.class, Boolean.class, ExpressionType.SIMPLE, "protection of citizen with id %integer%");
        Skript.registerExpression(ExprCitizenType.class, EntityType.class, ExpressionType.SIMPLE, "type of citizen with id %integer%");
        Skript.registerExpression(ExprHeldItem.class, ItemStack.class, ExpressionType.SIMPLE, "held item of citizen with id %integer%");
        Skript.registerExpression(ExprLastSpawnedNPC.class, NPC.class, ExpressionType.SIMPLE, "last spawned npc");
        
        // Register effects
        Skript.registerEffect(EffDespawn.class, "despawn citizen with id %integer%", "respawn citizen with id %integer%");
        Skript.registerEffect(EffSetPathfindLocation.class, "make citizen with id %integer% (pathfind|walk|move) to %location%");
        Skript.registerEffect(EffSpawnCitizen.class, "spawn citizen with id %integer% at %location%");
        
        // Register conditions
        Skript.registerCondition(CondCitizenIsSpawned.class, "citizen [with id] %integer% is spawned", "citizen [with id] %integer% (isn't|is not) spawned");
        Skript.registerCondition(CondEntityIsCitizen.class, "%entity% is [a] citizen", "%entity% (isn't|is not) [a] citizen");
        Skript.registerCondition(CondIsNavigating.class, "citizen [with id] %integer% is [currently] navigating", "citizen [with id] %integer% (isn't|is not) [currently] navigating");
        
        // Load events class to register all events
        try {
            Class.forName(CitizenEvents.class.getName());
            plugin.getLogger().info("Registered Citizens events");
        } catch (ClassNotFoundException e) {
            plugin.getLogger().severe("Failed to register Citizens events: " + e.getMessage());
        }
    }
}
