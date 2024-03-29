/*
 * This file is part of Fallout.
 *
 * Copyright (c) 2013-2014 <http://github.com/ampayne2/Fallout//>
 *
 * Fallout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Fallout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Fallout.  If not, see <http://www.gnu.org/licenses/>.
 */
package ninja.amp.fallout.commands.character;

import ninja.amp.amplib.command.Command;
import ninja.amp.fallout.Fallout;
import ninja.amp.fallout.characters.Character;
import ninja.amp.fallout.characters.CharacterManager;
import ninja.amp.fallout.characters.Perk;
import ninja.amp.fallout.config.ConfigType;
import ninja.amp.fallout.message.FOMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that teaches a character a skill.
 */
public class Teach extends Command {
    private final Fallout fallout;

    public Teach(Fallout fallout) {
        super(fallout, "teach");
        setDescription("Teaches a character a perk.");
        setCommandUsage("/fo character teach <character> <perk>");
        setPermission(new Permission("fallout.character.teach", PermissionDefault.OP));
        setArgumentRange(2, 2);
        setPlayerOnly(false);
        this.fallout = fallout;
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        Character character;
        CharacterManager characterManager = fallout.getCharacterManager();
        if (characterManager.isCharacter(args[0])) {
            character = characterManager.getCharacterByName(args[0]);
        } else {
            fallout.getMessenger().sendMessage(sender, FOMessage.CHARACTER_DOESNTEXIST);
            return;
        }
        Perk perk = Perk.fromName(args[1]);
        if (perk == null) {
            fallout.getMessenger().sendMessage(sender, FOMessage.PERK_DOESNTEXIST, args[1]);
        } else if (character.hasPerk(perk)) {
            fallout.getMessenger().sendMessage(sender, FOMessage.PERK_ALREADYTAUGHT, character.getCharacterName());
        } else {
            character.teachPerk(perk);
            Player taughtPlayer = Bukkit.getPlayerExact(character.getOwnerName());
            if (taughtPlayer != null) {
                fallout.getMessenger().sendMessage(taughtPlayer, FOMessage.PERK_LEARN, perk.getName());
            }
            fallout.getMessenger().sendMessage(sender, FOMessage.PERK_TEACH, perk.getName(), character.getCharacterName());
        }
        character.save(fallout.getConfigManager().getConfig(ConfigType.CHARACTER).getConfigurationSection("Characters." + character.getOwnerId()));
        fallout.getConfigManager().getConfigAccessor(ConfigType.CHARACTER).saveConfig();
    }

    @Override
    public List<String> getTabCompleteList(String[] args) {
        if (args.length == 0) {
            return fallout.getCharacterManager().getCharacterList();
        } else if (args.length == 1) {
            return Perk.getPerkNames();
        } else {
            return new ArrayList<>();
        }
    }
}
