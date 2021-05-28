# MobHeadDisguises
Bukkit plugin that makes mobs not attack players wearing mob heads

## Configuration
The `config.yml` file contains every option provided by this plugin. These settings can also be set via an in-game command (see below). Descriptions of each option are shown:

### magic-disguises
This sets whether or not a "disguise" (aka head) should work on all mobs this plugin affects or just mobs of the head's type. For example, when this is on, neither zombies, creepers, skeletons, or wither skeletons will attack a player wearing a zombie head. While this is off, wearing a zombie head will only prevent attacks from zombies.

### x-are-dumb
These settings specificy whether or not mobs of type "x" are dumb in that they do not attack players wearing their type of head. This setting is ignored if `magic-disguises` is set to `true`.

## The plugin command
This plugin has one simple root command, `/disguises`. The subcommands are listed below.

### /disguises reload
This command reloads the configuration from the disk, allowing you to change settings without restarting the server.

### /disguises set
This command allows you to set options in the config file in-game. The format is as follows:
`/disguises set <config-name> <value (true or false>`

## I'm wearing an x head and have x-are-dumb set to true, but they still attack me. Why is this?
This plugin does not entirely prevent mobs from attacking you. For example, if you attack a zombie, it will still retaliate, and nearby zombies will as well. This is applicable to real mobs as well: if a skeleton shoots another skeleton, the second one will shoot back, so why should that not be the case if a player wearing a skeleton skull shoots a skeleton?
