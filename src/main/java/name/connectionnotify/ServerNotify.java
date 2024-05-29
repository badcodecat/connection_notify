package name.connectionnotify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerNotify implements ModInitializer
{
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("servernotify");
	public static NotificationServer notification_server = new NotificationServer(LOGGER);

	@Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		notification_server.start();

		ServerPlayConnectionEvents.JOIN.register
		(
			(handler, sender, server) ->
			{
				String message = "Player " + handler.getPlayer().getDisplayName().getString() + " joined the game";
				notification_server.broadcast_message(message);
			}
		);

		ServerPlayConnectionEvents.DISCONNECT.register
		(
			(handler, server) ->
			{
				String message = "Player " + handler.getPlayer().getDisplayName().getString() + " left the game";
				notification_server.broadcast_message(message);
			}
		);

		ServerLifecycleEvents.SERVER_STOPPING.register
		(
			server ->
			{
				notification_server.should_run = false;
				try
				{
					notification_server.server_socket.close();
				}
				catch (Exception e)
				{
					LOGGER.error("ServerNotify: Failed to close server socket");
				}
			}
		);
	}
}
