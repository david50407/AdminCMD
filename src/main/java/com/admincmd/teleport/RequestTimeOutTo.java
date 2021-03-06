/*
 * This file is part of AdminCMD
 * Copyright (C) 2016 AdminCMD Team
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
package com.admincmd.teleport;

import com.admincmd.player.BukkitPlayer;
import java.util.TimerTask;

public class RequestTimeOutTo extends TimerTask {

    private final BukkitPlayer requester, target;

    public RequestTimeOutTo(BukkitPlayer requester, BukkitPlayer target) {
        this.requester = requester;
        this.target = target;
    }

    @Override
    public void run() {
        RequestManagerTo.timeOutRequest(requester, target);
    }

}
