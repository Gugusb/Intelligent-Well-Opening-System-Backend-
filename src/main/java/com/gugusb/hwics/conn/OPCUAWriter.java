package com.gugusb.hwics.conn;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.gugusb.hwics.pojo.DFPMix;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OPCUAWriter implements ClientConnectManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Variant v = null;;
    private String place = null;

    public OPCUAWriter(Boolean data, String place) {

        if(data){
            this.v = new Variant(Unsigned.ushort(1));
        }else{
            this.v = new Variant(Unsigned.ushort(0));
        }
        this.place = place;
        System.out.println(place);
    }

    public OPCUAWriter(Short data, String place) {
        this.v = new Variant(data);
        this.place = place;
    }

    public OPCUAWriter(Float data, String place) {
        this.v = new Variant(data);
        this.place = place;
    }

    @Override
    public DFPMix run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect().get();
        List<NodeId> nodeIds = ImmutableList.of(new NodeId(2, place));
        // don't write status or timestamps
        DataValue dv = new DataValue(v, null, null);
        // write asynchronously....
        CompletableFuture<List<StatusCode>> f = client.writeValues(nodeIds, ImmutableList.of(dv));
        // ...but block for the results so we write in order
        DFPMix dfpMix = new DFPMix();
        List<StatusCode> statusCodes = f.get();
        StatusCode status = statusCodes.get(0);
        if (status.isGood()) {
            logger.info("Wrote '{}' to nodeId={}", v, nodeIds.get(0));
            dfpMix.setSucceed(true);
        }else {
            logger.info("Failed Wrote '{}' to nodeId={}", v, nodeIds.get(0));
            System.out.println(status.toString());
            dfpMix.setSucceed(false);
        }
        future.complete(client);
        return dfpMix;
    }
}