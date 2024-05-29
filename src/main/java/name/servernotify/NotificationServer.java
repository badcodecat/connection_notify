package name.servernotify;

import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;

public class NotificationServer extends Thread
{
	public final int PORT = 14141;
	public ServerSocket server_socket = null;
	public static Logger LOGGER = null;
	public ArrayList<Socket> sockets = new ArrayList<Socket>();
	public boolean should_run = true;

	public NotificationServer(Logger logger)
	{
		LOGGER = logger;
		try
		{
			server_socket = new ServerSocket(PORT, 0, InetAddress.getByAddress(new byte[] {0, 0, 0, 0}));
		}
		catch (Exception e)
		{
			LOGGER.error("ServerNotify: Failed to create server socket on port {}", PORT);
		}
	}

	public void broadcast_message(String message)
	{
		synchronized (sockets)
		{
			for (Socket socket : sockets)
			{
				try
				{
					socket.getOutputStream().write(message.getBytes());
				}
				catch (Exception e)
				{
					LOGGER.error("ServerNotify: Failed to send message to socket");
				}
			}
		}
	}

	public void run()
	{
		while (should_run)
		{
			try
			{
				Socket socket = server_socket.accept();
				synchronized (sockets)
				{
					sockets.add(socket);
				}
			}
			catch (Exception e)
			{
				LOGGER.error("ServerNotify: Failed to accept connection on port {}", PORT);
			}
		}
	}

}
