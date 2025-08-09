package com.gugusb.hwics.service;

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
import com.gugusb.hwics.conn.ClientConnectManager;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class OPCUAReaderService implements ClientConnectManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void storeDPF1(OpcUaClient client) throws ExecutionException, InterruptedException {
        String[] ALL_TAGS1 = new String[]{
                // 机组状态和报警
                "1#机组状态1",
                "2#机组状态1",
                "3#机组状态1",
                "高液位",
                "低液位",
                "1#软启动报警",
                "2#软启动报警",
                "3#软启动报警",
                "可燃气体报警",

                // 1#机组参数
                "1#机组前端压力",
                "1#机组瞬时流量",
                "1#机组累计流量",
                "1#机组启泵次数",
                "1#机启动-小时",
                "1#机启动-分钟",
                "1#机停止-小时",
                "1#机停止-分钟",

                // 2#机组参数
                "2#机组前端压力",
                "2#机组瞬时流量",
                "2#机组累计流量",
                "2#机组启泵次数",
                "2#机组启动-小时",
                "2#机启动-分钟",
                "2#机停止-小时",
                "2#机停止-分钟",

                // 3#机组参数
                "3#机组前端压力",
                "3#机组瞬时流量",
                "3#机组累计流量",
                "3#机组启泵次数",
                "3#机组启动-小时",
                "3#机启动-分钟",
                "3#机停止-小时",
                "3#机停止-分钟",
                "3#机启动压力",
                "3#机停止压力",

                // 系统级参数
                "123#后端压力",
                "后端报警压力",
                "后端恢复压力",
                "报警代码",
                "123#总瞬时流量",
                "123#总累计流量",

                // 机组启用状态
                "1#机组启用或停运",
                "2#机组启用或停运",
                "3#机组启用或停运"
        };
        for(String tag : ALL_TAGS1){
            NodeId nodeId = new NodeId(2, "gugu通道1.gugu测试设备1.1#state1"); // 字符串类型的地址
            DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
            System.out.println("-----读取-----");
            System.out.println("-----gugu通道1.gugu测试设备1.1#state1" + dataValue.getValue().getValue());
        }
    }

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        NodeId nodeId = new NodeId(2, "gugu通道1.gugu测试设备1.1#state1"); // 字符串类型的地址
        DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
        System.out.println("-----读取-----");
        System.out.println("-----gugu通道1.gugu测试设备1.1#state1" + dataValue.getValue().getValue());
        //---------------------------------------
        //Identifiers.DataTypesFolder

        NodeId nodeId1 = new NodeId(2,"gugu通道1.gugu测试设备1");
        List<? extends UaNode> nodes = client.getAddressSpace().browseNodes(nodeId1);
        for(UaNode node:nodes){
            NodeId nodeId2 = node.getNodeId();
            if(node.getNodeClass() == NodeClass.Variable){
                DataValue value2 = client.readValue(0.0, TimestampsToReturn.Both, nodeId2).get();
                System.out.println(nodeId2.getIdentifier().toString() + ": "+ value2.getValue().getValue());
            }
        }

        // synchronous read request via VariableNode
        UaVariableNode node = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus_StartTime);
        DataValue value = node.readValue();

        logger.info("StartTime={}", value.getValue().getValue());

        // asynchronous read request
        readServerStateAndTime(client).thenAccept(values -> {
            DataValue v0 = values.get(0);
            DataValue v1 = values.get(1);

            logger.info("State={}", ServerState.from((Integer) v0.getValue().getValue()));
            logger.info("CurrentTime={}", v1.getValue().getValue());

            future.complete(client);
        });
    }

    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
                Identifiers.Server_ServerStatus_State,
                Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
