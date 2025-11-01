package codes.threads.lista1;

import java.net.Socket;

public class MeuRunnable implements Runnable
{
    private Socket cliSocket;
    private ServerEco server;

    public MeuRunnable(Socket socket, ServerEco server)
    {
        this.cliSocket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {

    }
}
