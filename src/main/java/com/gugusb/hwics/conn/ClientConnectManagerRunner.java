package com.gugusb.hwics.conn;

import com.gugusb.hwics.pojo.DFPMix;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.Security;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ClientConnectManagerRunner {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private final ClientConnectManager clientExample;

    public ClientConnectManagerRunner(ClientConnectManager clientExample) throws Exception {
        this(clientExample, true);
    }

    public ClientConnectManagerRunner(ClientConnectManager clientExample, boolean serverRequired) throws Exception {
        this.clientExample = clientExample;
    }

    private OpcUaClient createClient() throws Exception {

        // 加载证书
        String filePath = ClientConnectManagerRunner.class.getResource("/").getPath();
        File file = new File(filePath);
        KeyStoreLoader loader = new KeyStoreLoader().load(file.toPath());

        return OpcUaClient.create(
                clientExample.getEndpointUrl(),
                endpoints ->
                        endpoints.stream()
                                .filter(clientExample.endpointFilter())
                                .findFirst(),
                configBuilder ->
                        configBuilder
                                .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
                                .setApplicationUri("urn:eclipse:milo:examples:client")
                    .setKeyPair(loader.getClientKeyPair()) // 密钥对
                    .setCertificate(loader.getClientCertificate()) // 证书
                    .setCertificateChain(loader.getClientCertificateChain()) // 证书信任链
//                    .setCertificateValidator(certificateValidator) // 验证证书
                                .setIdentityProvider(clientExample.getIdentityProvider())
                                .setRequestTimeout(uint(5000))
                                .build()
        );
    }

    public DFPMix run() {
        DFPMix dfpMix = null;
        try {
            OpcUaClient client = createClient();

            future.whenCompleteAsync((c, ex) -> {
                if (ex != null) {
                    logger.error("Error running example: {}", ex.getMessage(), ex);
                }

                try {
                    client.disconnect().get();
                    Stack.releaseSharedResources();
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("Error disconnecting: {}", e.getMessage(), e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            try {
                dfpMix = clientExample.run(client, future);
                future.get(15, TimeUnit.SECONDS);
            } catch (Throwable t) {
                logger.error("Error running client example: {}", t.getMessage(), t);
                future.completeExceptionally(t);
            }
        } catch (Throwable t) {
            logger.error("Error getting client: {}", t.getMessage(), t);

            future.completeExceptionally(t);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Opcua数据结算完成");
        return dfpMix;
    }
}