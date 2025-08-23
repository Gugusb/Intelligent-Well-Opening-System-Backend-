package com.gugusb.hwics.conn;

import com.gugusb.hwics.pojo.DFPMix;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;


public interface ClientConnectManager {

    default String getEndpointUrl() {
        return  "opc.tcp://127.0.0.1:49320";
    }

    default Predicate<EndpointDescription> endpointFilter() {
        return e -> getSecurityPolicy().getUri().equals(e.getSecurityPolicyUri());
    }

    default SecurityPolicy getSecurityPolicy() {
        return SecurityPolicy.Basic256Sha256;
    }

    default IdentityProvider getIdentityProvider() {
        return new UsernameProvider("gugu","Gu_15668456691");
    }

    DFPMix run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception;
}
