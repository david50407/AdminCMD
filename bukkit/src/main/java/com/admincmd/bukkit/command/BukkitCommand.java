/*
 * This file is part of AdminCMD
 * Copyright (C) 2015 AdminCMD Team
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
package com.admincmd.bukkit.command;

import com.admincmd.api.AdminCMD;
import com.admincmd.api.command.parsing.Arguments;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BukkitCommand extends Command implements CommandExecutor {

    private com.admincmd.api.command.Command command;

    public BukkitCommand(com.admincmd.api.command.Command command) {
        super(command.getPrimaryAlias(), command.getDescription(), command.getUsage(), command.getSecondaryAliases());
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        return onCommand(sender, this, label, args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        com.admincmd.api.command.CommandSource source = new BukkitCommandSource(sender);
        Arguments arguments = new Arguments(args);

        AdminCMD.getCommandManager().callCommand(command, source, arguments);
        return true;
    }

}