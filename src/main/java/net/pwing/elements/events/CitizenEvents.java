package net.pwing.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.event.NPCSelectEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.event.NPCCreateEvent;

/**
 * Class to register all Citizens NPC-related events with Skript
 */
public class CitizenEvents {

    static {
        // Events
        Skript.registerEvent("Citizen Click", SimpleEvent.class, NPCClickEvent.class,
                        "(citizen|npc) click")
                .description("Called when an NPC is clicked by a player.")
                .examples("on npc click:", "\tbroadcast \"an NPC has been clicked!\"")
                .since("1.0.0");

        Skript.registerEvent("Citizen Left Click", SimpleEvent.class, NPCLeftClickEvent.class,
                        "(citizen|npc) left click")
                .description("Called when an NPC is left-clicked by a player.")
                .examples("on npc left click:", "\tkill player")
                .since("1.0.0");

        Skript.registerEvent("Citizen Right Click", SimpleEvent.class, NPCRightClickEvent.class,
                        "(citizen|npc) right click")
                .description("Called when an NPC is right-clicked by a player.")
                .examples("on npc right click:", "\tteleport player to spawn of world \"world\"")
                .since("1.0.0");

        Skript.registerEvent("Citizen Select", SimpleEvent.class, NPCSelectEvent.class,
                        "(citizen|npc) select")
                .description("Called when an NPC is selected by a player.")
                .since("1.0.0");

        Skript.registerEvent("Citizen Despawn", SimpleEvent.class, NPCDespawnEvent.class,
                        "(citizen|npc) despawn")
                .description("Called when an NPC despawns.")
                .since("1.0.0");

        Skript.registerEvent("Citizen Spawn", SimpleEvent.class, NPCSpawnEvent.class,
                        "(citizen|npc) spawn")
                .description("Called when an NPC spawns.")
                .since("1.0.0");

        Skript.registerEvent("Citizen Create", SimpleEvent.class, NPCCreateEvent.class,
                        "(citizen|npc) create")
                .description("Called when an NPC has been created")
                .examples("on npc create: \tteleport event-npc to spawn of world \"world\"")
                .since("1.0.0");
    }
}
