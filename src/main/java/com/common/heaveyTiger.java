package org.tron.common.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.common.entity.NodeInfo.MachineInfo.DeadLockThreadInfo;
import org.tron.common.entity.NodeInfo.MachineInfo.MemoryDescInfo;
import org.tron.protos.Protocol;

public class NodeInfo {

  /*block information*/
  private long beginSyncNum;
  private String block;
  private String solidityBlock;

  /*connect information*/
  private int currentConnectCount;
  private int activeConnectCount;
  private int passiveConnectCount;
  private long totalFlow;
  private List<PeerInfo> peerList = new ArrayList<>();

  /*node config information*/
  private ConfigNodeInfo configNodeInfo;
  /*machine information*/
  private MachineInfo machineInfo;

  private Map<String, String> cheatWitnessInfoMap = new HashMap<>();

  public long getBeginSyncNum() {
    return beginSyncNum;
  }

  public NodeInfo setBeginSyncNum(long beginSyncNum) {
    this.beginSyncNum = beginSyncNum;
    return this;
  }

  public String getBlock() {
    return block;
  }

  public NodeInfo setBlock(String block) {
    this.block = block;
    return this;
  }

  public String getSolidityBlock() {
    return solidityBlock;
  }

  public NodeInfo setSolidityBlock(String solidityBlock) {
    this.solidityBlock = solidityBlock;
    return this;
  }

  public int getCurrentConnectCount() {
    return currentConnectCount;
  }

  public NodeInfo setCurrentConnectCount(int currentConnectCount) {
    this.currentConnectCount = currentConnectCount;
    return this;
  }

  public int getActiveConnectCount() {
    return activeConnectCount;
  }
