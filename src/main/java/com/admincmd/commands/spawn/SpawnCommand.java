/*
 * This file is part of AdminCMD
 * Copyright (C) 2017 AdminCMD Team
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package com.admincmd.commands.spawn;

import com.admincmd.commandapi.BaseCommand;
import com.admincmd.commandapi.CommandArgs;
import com.admincmd.commandapi.CommandHandler;
import com.admincmd.commandapi.CommandResult;
import com.admincmd.spawn.SpawnManager;
import com.admincmd.utils.Locales;
import com.admincmd.utils.Messager;
import com.admincmd.utils.Utils;
import org.bukkit.entity.Player;

@CommandHandler
public class SpawnCommand {

    @BaseCommand(command = "spawn", sender = BaseCommand.Sender.PLAYER, permission = "admincmd.spawn.spawn", helpArguments = {"", "<-p player>"})
    public CommandResult executeSpawn(Player sender, CommandArgs args) {
        if (args.isEmpty()) {
            sender.teleport(SpawnManager.getSpawn(sender));
            return Messager.sendMessage(sender, Locales.SPAWN_TP, Messager.MessageType.INFO);
        } else if (args.getLength() == 1) {
            if (!args.hasFlag("p")) {
                return CommandResult.ERROR;
            }

            if (!sender.hasPermission("admincmd.spawn.spawn.other")) {
                return CommandResult.NO_PERMISSION_OTHER;
            }

            CommandArgs.Flag f = args.getFlag("p");
            if (!f.isPlayer()) {
                return CommandResult.NOT_ONLINE;
            }
            Player t = f.getPlayer();
            t.teleport(SpawnManager.getSpawn(t));
            Messager.sendMessage(t, Locales.SPAWN_TP, Messager.MessageType.INFO);
            Messager.sendMessage(sender, Locales.SPAWN_TP_OTHER.getString().replaceAll("%player%", Utils.replacePlayerPlaceholders(t)), Messager.MessageType.INFO);
            return CommandResult.SUCCESS;
        } else {
            return CommandResult.ERROR;
        }
    }

}
