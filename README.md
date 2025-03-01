# Skript-CitizenModern

A comprehensive Skript addon that provides full integration with Citizens NPC plugin, allowing script authors to create, manage, and interact with NPCs directly through Skript syntax.

## Overview

Skript-CitizenModern seamlessly bridges Skript's easy-to-use scripting language with the powerful Citizens NPC API. This addon enables server administrators to create rich NPC interactions, pathfinding behaviors, and event-driven scripts without needing to write Java plugins.

## Features

### Expressions

- npc name of id %integer% - Get an NPC's name by its ID
- all citizens - Get a list of all NPCs
- entity of citizen with id %integer% - Get the entity of an NPC by its ID
- citizen from entity %entity% - Get the NPC object from an entity
- id of citizen from entity %entity% - Get the ID of an NPC from its entity
- name of citizen with id %integer% - Get/set the name of an NPC
- protection of citizen with id %integer% - Get/set whether an NPC is protected
- type of citizen with id %integer% - Get/set the entity type of an NPC
- held item of citizen with id %integer% - Get/set the item an NPC is holding
- last spawned npc - Get the most recently spawned NPC

### Effects

- despawn citizen with id %integer% - Despawn an NPC
- respawn citizen with id %integer% - Respawn an NPC
- make citizen with id %integer% (pathfind|walk|move) to %location% - Make an NPC navigate to a location
- spawn citizen with id %integer% at %location% - Spawn an NPC at a specific location

### Conditions

- citizen [with id] %integer% is spawned - Check if an NPC is currently spawned
- %entity% is [a] citizen - Check if an entity is an NPC
- citizen [with id] %integer% is [currently] navigating - Check if an NPC is currently navigating

### Events

- on (citizen|npc) click - Triggered when an NPC is clicked
- on (citizen|npc) left click - Triggered when an NPC is left-clicked
- on (citizen|npc) right click - Triggered when an NPC is right-clicked
- on (citizen|npc) select - Triggered when an NPC is selected
- on (citizen|npc) despawn - Triggered when an NPC despawns
- on (citizen|npc) spawn - Triggered when an NPC spawns
- on (citizen|npc) create - Triggered when an NPC is created

## Requirements

- Spigot/Paper 1.8.8+
- Skript 2.6+
- Citizens 2.0.30+

## Installation

1. Ensure that both Skript and Citizens are installed on your server
2. Download the latest version of Skript-CitizenModern from GitHub Releases
3. Place the .jar file in your server's plugins folder
4. Restart your server
5. The addon will automatically register with Skript

## Usage Examples

### Creating and Managing NPCs

```skript
# Create a new NPC at the player's location
command /createnpc <text>:
    trigger:
        execute console command "npc create %arg-1%"
        set {_npc} to last spawned npc
        set {_id} to id of citizen from entity {_npc}
        message "Created NPC with ID %{_id}%"
```

```skript
# Teleport an NPC to the player
command /tpnpc <integer>:
    trigger:
        spawn citizen with id %arg-1% at player's location
        message "Teleported NPC %arg-1% to your location"
```

### NPC Interactions

```skript
# Make an NPC speak to a player when right-clicked
on npc right click:
    set {_id} to id of citizen from entity event-entity
    message "Hello there! I'm NPC #%{_id}%!"
```

```skript
# Make an NPC walk to a clicked location
command /npcgoto <integer>:
    trigger:
        set {_loc} to targeted block's location
        if {_loc} is set:
            make citizen with id %arg-1% pathfind to {_loc}
            message "NPC %arg-1% is now walking to your target"
```

### Event-Based Behaviors

```skript
# Make an NPC say something when it spawns
on citizen spawn:
    set {_id} to id of citizen from entity event-entity
    wait 1 second
    execute console command "npc select %{_id}%"
    execute console command "npc speak Hello world! I've just spawned!"
```

```skript
# Track when players approach an NPC
every 5 seconds:
    loop all players:
        loop all entities in radius 5 around loop-player:
            if loop-entity-2 is a citizen:
                set {_id} to id of citizen from entity loop-entity-2
                send "You're near NPC #%{_id}%" to loop-player
```

## Configuration

Skript-CitizenModern doesn't require any specific configuration. It automatically integrates with your existing Skript and Citizens installations.

## License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

## Credits

- Original Citizens API: [CitizensDev](https://github.com/CitizensDev/Citizens2)
- Skript: [SkriptLang](https://github.com/SkriptLang/Skript)

## Support & Contributing

If you encounter any issues or have suggestions for improvements, please open an issue on the GitHub repository. Pull requests are welcome!

---

*This addon is not affiliated with or endorsed by either the Skript project or the Citizens project.*