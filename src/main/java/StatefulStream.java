import client.MockProducerServer;
import streams.AccountMaintainer;

public class StatefulStream {

    public static void main(String[] args) {

        MockProducerServer mockProducerServer = new MockProducerServer(8000);
        mockProducerServer.start();


        AccountMaintainer accountMaintainer = new AccountMaintainer();
        accountMaintainer.getTopology().describe();
        accountMaintainer.start();

    }
}
