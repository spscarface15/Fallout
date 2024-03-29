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
package ninja.amp.fallout.message;

import ninja.amp.amplib.messenger.Message;

/**
 * Messages in Fallout.
 */
public enum FOMessage implements Message {
    CHARACTER_CREATE("Character.Create", "Created a character named %s."),
    CHARACTER_DELETE("Character.Delete", "Deleted your character."),
    CHARACTER_NAME("Character.Name", "%s's real name is %s."),
    CHARACTER_RACE("Character.Race", "%s is a %s."),
    CHARACTER_ALREADYEXISTS("Character.AlreadyExists", "&4You already have a character!"),
    CHARACTER_NAMETAKEN("Character.NameTaken", "&4A character of that name already exists!"),
    CHARACTER_NOTOWNER("Character.NotOwner", "&4You do not have a character!"),
    CHARACTER_DOESNTEXIST("Character.DoesntExist", "&4A character of that name doesn't exist!"),

    RACE_DOESNTEXIST("Race.DoesntExist", "&4The race %s doesn't exist!"),
    RACE_ONLYLEATHER("Race.OnlyLeather", "&4Your race can only wear leather armor!"),

    SPECIAL_SET("Special.Set", "Set %s's SPECIAL."),
    SPECIAL_LIST("Special.List", "%s's SPECIAL is %s."),
    SPECIAL_INVALID("Special.Invalid", "Invalid SPECIAL for your race."),

    PERK_LEARN("Perk.Learn", "Learned perk %s."),
    PERK_FORGET("Perk.Forget", "Forgot perk %s."),
    PERK_TEACH("Perk.Teach", "Taught perk %s to %s."),
    PERK_UNTEACH("Perk.UnTeach", "Untaught perk %s from %s."),
    PERK_LIST("Perk.List", "%s's perks are: %s."),
    PERK_DOESNTEXIST("Perk.DoesntExist", "&4The perk %s doesn't exist!"),
    PERK_ALREADYTAUGHT("Perk.AlreadyTaught", "&4%s already knows that perk."),
    PERK_NOTTAUGHT("Perk.NotTaught", "&4%s doesn't know that perk."),
    PERK_NOPERKS("Perk.NoPerks", "&4%s has no perks."),
    PERK_MISSINGREQUIRED("Perk.MissingRequired", "&4You are missing the required perk %s."),

    ROLL_MESSAGE("Roll.Message", "You rolled a %s in %s."),
    ROLL_BROADCAST("Roll.Broadcast", "%s rolled a %s in %s."),
    ROLL_NOTATRAIT("Roll.NotATrait", "&4%s is not a SPECIAL trait.");

    private String message;
    private final String path;
    private final String defaultMessage;

    private FOMessage(String path, String defaultMessage) {
        this.message = defaultMessage;
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDefault() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return message;
    }
}
