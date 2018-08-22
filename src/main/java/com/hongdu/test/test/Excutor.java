package com.hongdu.test.test;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author Administrator
 *
 */
public class Excutor implements Watcher {

	ZooKeeper zk;
	String hostPort;
	String znode1;
	String znode2;
	
	public Excutor(String hostPort,String znode1, String znode2) throws Exception{
		this.hostPort = hostPort;
		this.znode1 = znode1;
		this.znode2 = znode2;
		
		zk = new ZooKeeper(hostPort, 3000, this);
		Stat exists = zk.exists(znode1, false);
		if (exists == null) {			//假如节点不存在，则创建临时节点
			zk.create(znode1, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		}
		//监听第二个节点
		zk.exists(znode2, true);
	}

	
	@Override
	public void process(WatchedEvent event) {
		try {
			
			if (event.getType() == EventType.NodeDeleted) {	
				//临时节点被删除了，则节点不在线；触发后，watcher被删除，继续添加watcher
					System.out.println("节点" + znode2 + "当前不在线");
					zk.exists(znode2, true);
			}else if (event.getType() == EventType.NodeCreated) {
				//临时节点被创建了，则节点上线了；触发后，watcher被删除，继续添加watcher
				System.out.println("节点" + znode2 + "上线了");
				zk.exists(znode2, true);
			}
			
			} catch (KeeperException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
