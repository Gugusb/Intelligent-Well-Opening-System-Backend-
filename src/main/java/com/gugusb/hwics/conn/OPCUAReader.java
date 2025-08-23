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
import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.pojo.*;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class OPCUAReader implements ClientConnectManager{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DFP1 storeDPF1(OpcUaClient client) throws Exception {
        // 1. 从classpath加载JSON映射文件
        InputStream configStream = getClass().getResourceAsStream("/config/dfp1-mapping.json");
        JsonReader jsonReader = Json.createReader(configStream);
        JsonObject mappingConfig = jsonReader.readObject();
        jsonReader.close();

        // 2. 创建目标对象
        DFP1 dfp1 = new DFP1();

        // 3. 遍历映射关系读取数据
        for (String fieldName : mappingConfig.keySet()) {
            String tag = mappingConfig.getString(fieldName);

            try {
                // 构建节点ID并读取值
                NodeId nodeId = new NodeId(2, "gugu通道1." + tag);
                DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                Object value = dataValue.getValue().getValue();

                // 4. 反射设置字段值
                Field field = DFP1.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                // 处理不同字段类型
                if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(dfp1, ((Number) value).floatValue());
                }
                else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(dfp1, ((Number) value).doubleValue());
                }
                else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(dfp1, ((Number) value).intValue());
                }
                else if (field.getType() == String.class) {
                    field.set(dfp1, value.toString());
                }
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    if(Objects.equals(value.toString(), "0")){
                        field.set(dfp1, false);
                    }else
                        field.set(dfp1, true);
                }
                // 添加其他类型处理...
            } catch (Exception e) {
                System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
            }
        }
        dfp1.fixData();
        System.out.println("Page1数据更新完成");
        return dfp1;
    }

    private DFP2 storeDPF2(OpcUaClient client) {
        // 1. 从classpath加载JSON映射文件
        InputStream configStream = getClass().getResourceAsStream("/config/dfp2-mapping.json");
        JsonReader jsonReader = Json.createReader(configStream);
        JsonObject mappingConfig = jsonReader.readObject();
        jsonReader.close();

        // 2. 创建目标对象
        DFP2 dfp2 = new DFP2();

        // 3. 遍历映射关系读取数据
        for (String fieldName : mappingConfig.keySet()) {
            String tag = mappingConfig.getString(fieldName);

            try {
                // 构建节点ID并读取值
                NodeId nodeId = new NodeId(2, "gugu通道1." + tag);

                DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                Object value = dataValue.getValue().getValue();

                // 4. 反射设置字段值
                Field field = DFP2.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                // 处理不同字段类型
                if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(dfp2, ((Number) value).floatValue());
                }
                else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(dfp2, ((Number) value).doubleValue());
                }
                else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(dfp2, ((Number) value).intValue());
                }
                else if (field.getType() == String.class) {
                    field.set(dfp2, value.toString());
                }
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    if(Objects.equals(value.toString(), "0") || Objects.equals(value.toString(), "false")){
                        field.set(dfp2, false);
                    }else
                        field.set(dfp2, true);
                }
                // 添加其他类型处理...
            } catch (Exception e) {
                System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
            }
        }
        dfp2.fixData();
        System.out.println("Page2数据更新完成");
        return dfp2;
    }

    private DFP3 storeDPF3(OpcUaClient client) {
        // 1. 从classpath加载JSON映射文件
        InputStream configStream = getClass().getResourceAsStream("/config/dfp3-mapping.json");
        JsonReader jsonReader = Json.createReader(configStream);
        JsonObject mappingConfig = jsonReader.readObject();
        jsonReader.close();

        // 2. 创建目标对象
        DFP3 dfp2 = new DFP3();

        // 3. 遍历映射关系读取数据
        for (String fieldName : mappingConfig.keySet()) {
            String tag = mappingConfig.getString(fieldName);

            try {
                // 构建节点ID并读取值
                NodeId nodeId = new NodeId(2, "gugu通道1." + tag);
                DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                Object value = dataValue.getValue().getValue();

                // 4. 反射设置字段值
                Field field = DFP3.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                // 处理不同字段类型
                if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(dfp2, ((Number) value).floatValue());
                }
                else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(dfp2, ((Number) value).doubleValue());
                }
                else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(dfp2, ((Number) value).intValue());
                }
                else if (field.getType() == String.class) {
                    field.set(dfp2, value.toString());
                }
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    if(Objects.equals(value.toString(), "0") || Objects.equals(value.toString(), "false")){
                        field.set(dfp2, false);
                    }else
                        field.set(dfp2, true);
                }
                // 添加其他类型处理...
            } catch (Exception e) {
                System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
            }
        }
        dfp2.fixData();
        System.out.println("Page3数据更新完成");
        return dfp2;
    }

    private DFP4 storeDPF4(OpcUaClient client) {
        // 1. 从classpath加载JSON映射文件
        InputStream configStream = getClass().getResourceAsStream("/config/dfp4-mapping.json");
        JsonReader jsonReader = Json.createReader(configStream);
        JsonObject mappingConfig = jsonReader.readObject();
        jsonReader.close();

        // 2. 创建目标对象
        DFP4 dfp2 = new DFP4();

        // 3. 遍历映射关系读取数据
        for (String fieldName : mappingConfig.keySet()) {
            String tag = mappingConfig.getString(fieldName);

            try {
                // 构建节点ID并读取值
                NodeId nodeId = new NodeId(2, "gugu通道1." + tag);
                DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                Object value = dataValue.getValue().getValue();

                // 4. 反射设置字段值
                Field field = DFP4.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                // 处理不同字段类型
                if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(dfp2, ((Number) value).floatValue());
                }
                else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(dfp2, ((Number) value).doubleValue());
                }
                else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(dfp2, ((Number) value).intValue());
                }
                else if (field.getType() == String.class) {
                    field.set(dfp2, value.toString());
                }
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    if(Objects.equals(value.toString(), "0") || Objects.equals(value.toString(), "false")){
                        field.set(dfp2, false);
                    }else
                        field.set(dfp2, true);
                }
                // 添加其他类型处理...
            } catch (Exception e) {
                System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
            }
        }
        dfp2.fixData();
        System.out.println("Page4数据更新完成");
        return dfp2;
    }

    private DFP5 storeDPF5(OpcUaClient client) {
        // 1. 从classpath加载JSON映射文件
        InputStream configStream = getClass().getResourceAsStream("/config/dfp5-mapping.json");
        JsonReader jsonReader = Json.createReader(configStream);
        JsonObject mappingConfig = jsonReader.readObject();
        jsonReader.close();

        // 2. 创建目标对象
        DFP5 dfp2 = new DFP5();

        // 3. 遍历映射关系读取数据
        for (String fieldName : mappingConfig.keySet()) {
            String tag = mappingConfig.getString(fieldName);

            try {
                // 构建节点ID并读取值
                NodeId nodeId = new NodeId(2, "gugu通道1." + tag);
                DataValue dataValue = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
                Object value = dataValue.getValue().getValue();

                // 4. 反射设置字段值
                Field field = DFP5.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                // 处理不同字段类型
                if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(dfp2, ((Number) value).floatValue());
                }
                else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(dfp2, ((Number) value).doubleValue());
                }
                else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(dfp2, ((Number) value).intValue());
                }
                else if (field.getType() == String.class) {
                    field.set(dfp2, value.toString());
                }
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    if(Objects.equals(value.toString(), "0") || Objects.equals(value.toString(), "false")){
                        field.set(dfp2, false);
                    }else
                        field.set(dfp2, true);
                }
                // 添加其他类型处理...
            } catch (Exception e) {
                System.err.println("标签处理失败 [" + tag + "]: " + e.getMessage());
            }
        }
        dfp2.fixData();
        System.out.println("Page5数据更新完成");
        return dfp2;
    }

    @Override
    public DFPMix run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        DFPMix dfpMix = new DFPMix();
        dfpMix.setDfp1(storeDPF1(client));
        dfpMix.setDfp2(storeDPF2(client));
        dfpMix.setDfp3(storeDPF3(client));
        dfpMix.setDfp4(storeDPF4(client));
        dfpMix.setDfp5(storeDPF5(client));
        dfpMix.setSucceed(true);

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

        return dfpMix;
    }


    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
                Identifiers.Server_ServerStatus_State,
                Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
