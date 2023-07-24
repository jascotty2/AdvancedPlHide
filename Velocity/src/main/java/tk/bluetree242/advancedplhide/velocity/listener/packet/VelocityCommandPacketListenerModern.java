/*
 *  LICENSE
 * AdvancedPlHide
 * -------------
 * Copyright (C) 2021 - 2022 BlueTree242
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 *  END
 */

package tk.bluetree242.advancedplhide.velocity.listener.packet;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.proxy.connection.client.ConnectedPlayer;
import com.velocitypowered.proxy.protocol.packet.chat.session.SessionPlayerCommand;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;
import tk.bluetree242.advancedplhide.velocity.AdvancedPlHideVelocity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import tk.bluetree242.advancedplhide.Group;
import tk.bluetree242.advancedplhide.PlatformPlugin;

public class VelocityCommandPacketListenerModern extends AbstractPacketListener<SessionPlayerCommand> {
    private final AdvancedPlHideVelocity core;

    public VelocityCommandPacketListenerModern(AdvancedPlHideVelocity core) {
        super(SessionPlayerCommand.class, Direction.DOWNSTREAM, 0);
        this.core = core;
    }

    @Override
    public void packetReceive(PacketReceiveEvent<SessionPlayerCommand> e) {
		// Doesn't happen..
    }

    @Override
    public void packetSend(PacketSendEvent<SessionPlayerCommand> e) {
        if (!e.cancelled()) {
			Player player = core.server.getPlayer(e.player().uniqueId()).orElse(null);
			if (player == null || !player.isActive()) {
				e.cancelled(true);
				return;
			}
			Group group = core.getGroupForPlayer(player);
			if (group == null) return;
			// now check if this command is disabled in this player's group:
			final String cmd = e.packet().getCommand();
			// TODO: need a better way to do this for other platforms
			for (String match : group.getExecuteCommands()) {
				// star matches:
				if (cmd.length() > match.length() && match.endsWith("*")
						&& cmd.substring(0, match.length() - 1).equalsIgnoreCase(match.substring(0, match.length() - 1))) {
					e.cancelled(true);
					break;
				}
				// command matches:
				else if (((cmd.length() > match.length() && cmd.charAt(match.length()) == ' ') || (cmd.length() == match.length()))
						&& cmd.substring(0, match.length()).equalsIgnoreCase(match)) {
					e.cancelled(true);
					break;
				}
			}
			if (e.cancelled()) {
				Component response = LegacyComponentSerializer.legacy('&').deserialize(PlatformPlugin.get().getConfig().pl_message().replace("<command>", cmd));
				((ConnectedPlayer) e.player().handle()).sendMessage(response);
			}
			
		}
//        if (legacy) {
//            if (!notCompleted.contains(" ")) {
//                OfferCompleterList list = new OfferCompleterList(e.packet().getOffers(), true);
//                CompleterModifier.handleCompleter(list, core.getGroupForPlayer(player), 
//						player.hasPermission(Constants.WHITELIST_MODE_PERMISSION),
//						PlatformPlugin.get().getConfig().remove_plugin_prefix() && !player.hasPermission(Constants.BYPASS_PREFIX_CLEAR));
//            } else {
//                OfferSubCommandCompleterList list = new OfferSubCommandCompleterList(e.packet().getOffers(), notCompleted);
//                CompleterModifier.handleSubCompleter(list, core.getGroupForPlayer(player), player.hasPermission(Constants.WHITELIST_MODE_PERMISSION));
//                if (list.isCancelled()) e.cancelled(true);
//            }
//        } else {
//            if ((!notCompleted.contains(" "))) {
//                OfferCompleterList list = new OfferCompleterList(e.packet().getOffers(), false);
//                CompleterModifier.handleCompleter(list, core.getGroupForPlayer(player), 
//						player.hasPermission(Constants.WHITELIST_MODE_PERMISSION),
//						PlatformPlugin.get().getConfig().remove_plugin_prefix() && !player.hasPermission(Constants.BYPASS_PREFIX_CLEAR));
//            } else {
//                OfferSubCommandCompleterList list = new OfferSubCommandCompleterList(e.packet().getOffers(), notCompleted);
//                CompleterModifier.handleSubCompleter(list, core.getGroupForPlayer(player), player.hasPermission(Constants.WHITELIST_MODE_PERMISSION));
//                if (list.isCancelled()) e.cancelled(true);
//            }
//        }
    }

}
