package org.blackbananacoin.narakabridge.foo;

import java.math.BigInteger;
import java.util.List;

import org.blackbananacoin.narakabridge.Utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

public class Mining {

	void mine(String root, int extranonce) {

		for (int layerIndex = 1; layerIndex < 10; layerIndex++) {
			long nodeNum = getNodeNum(layerIndex);
			System.out.println("nodes=" + nodeNum);
			int nodeNumPreLayer = getNodeNum(layerIndex - 1);
			List<Integer> preLayer = getLayer(layerIndex - 1);
			List<Integer> layer = getLayer(layerIndex);

			for (int nodeIndex = 0; nodeIndex < nodeNum; nodeIndex++) {
				List<Integer> p = Lists.newArrayList();
				// TODO
				String prefix = todoGetPrefix(root, extranonce, layerIndex);
				int spread = getSpread(layerIndex);
				for (int spreadIndex = 0; spreadIndex < spread; spreadIndex++) {
					// TODO
					byte[] toHash = todoHash(prefix, nodeIndex, spreadIndex);
					byte[] h = Utils.sha3(toHash);
					int hnum = fromBinary(h);
					int ind = hnum % nodeNumPreLayer;
					p.add(preLayer.get(ind));
				}

				//layer.add(Utils.sha3(Joiner.on("").join(p).getBytes()));

			}
		}
	}

	private List<Integer> getLayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private byte[] todoHash(String prefix, int i, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	private String todoGetPrefix(String root, int extranonce, int layer) {
		// TODO Auto-generated method stub
		return null;
	}

	private int fromBinary(byte[] h) {
		// TODO Auto-generated method stub
		return 0;
	}

	int getSpread(int layerNum) {
		return layerNum == 9 ? 16 : 3;
	}

	int getNodeNum(int layerNum) {
		return layerNum == 9 ? IntMath.pow(2, 25) : IntMath.pow(8, layerNum);
	}

	public static void main(String[] args) {
		new Mining().mine("", 123);
	}
}
