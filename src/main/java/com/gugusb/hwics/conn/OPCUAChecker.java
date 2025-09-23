package com.gugusb.hwics.conn;

/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */


import com.google.common.collect.ImmutableList;
import com.gugusb.hwics.pojo.*;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class OPCUAChecker implements ClientConnectManager{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String channelName = "gugu通道2.";

    private DataCheckResult checkDPFs(OpcUaClient client) throws Exception {
        // 0. 建立返回列表
        List<Pair<String, String>> checkResults = new ArrayList<>();
        Integer succeedCount = 0;
        Integer failedCount = 0;

        // 1. 从classpath加载JSON映射文件
        List<JsonObject> mappingConfigs = new ArrayList<>();
        for(int i = 1;i <= 5;i ++){
            InputStream configStream = getClass().getResourceAsStream("/config/dfp" + i + "-mapping.json");
            JsonReader jsonReader = Json.createReader(configStream);
            JsonObject mappingConfig = jsonReader.readObject();
            mappingConfigs.add(mappingConfig);
            jsonReader.close();
        }

        // 2. 遍历映射关系读取数据
        for(JsonObject mappingConfig : mappingConfigs){
            for (String fieldName : mappingConfig.keySet()) {
                String tag = mappingConfig.getString(fieldName);
                try {
                    // 构建节点ID并读取值
                    NodeId nodeId = new NodeId(2, channelName + tag);
                    DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                    Object value = dataValue.getValue().getValue();
                    if(value != null){
                        checkResults.add(Pair.of(channelName + tag, "正常"));
                        succeedCount++;
                    }else{
                        checkResults.add(Pair.of(channelName + tag, "异常"));
                        failedCount++;
                    }
                } catch (Exception e) {
                    System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
                }
            }
        }
        DataCheckResult dataCheckResult = new DataCheckResult(checkResults, succeedCount, failedCount);
        System.out.println("数据检查完成");
        return dataCheckResult;
    }

    @Override
    public DFPMix run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        DFPMix dfpMix = new DFPMix();
        dfpMix.setDataCheckResult(checkDPFs(client));
        dfpMix.setSucceed(true);

        // synchronous read request via VariableNode
        UaVariableNode node = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus_StartTime);
        DataValue value = node.readValue();

        // asynchronous read request
        readServerStateAndTime(client).thenAccept(values -> {
            DataValue v0 = values.get(0);
            DataValue v1 = values.get(1);
            future.complete(client);
        });

        return dfpMix;
    }


    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
                Identifiers.Server_ServerStatus_State,
                Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
