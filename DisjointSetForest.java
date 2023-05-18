package dsaa.lab09;

public class DisjointSetForest implements DisjointSetDataStructure {
	
	private class Element{
		int rank;
		int parent;
	}

	Element []arr;
	
	public DisjointSetForest(int size) {
		arr = new Element[size];
		for (int i =0; i < size; i++)
		{
			arr[i] = new Element();
			arr[i].parent = i;
			arr[i].rank = 0;

		}
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].parent = item;
		arr[item].rank = 0;
	}

	@Override
	public int findSet(int item) {
		if (arr[item].parent != item) {
			arr[item].parent = findSet(arr[item].parent);
		}
		return arr[item].parent;
	}


	@Override
	public boolean union(int itemA, int itemB) {
		int rootA = findSet(itemA);
		int rootB = findSet(itemB);

		if (rootA == rootB) {
			return false; // already in the same set
		}

		// union by rank
		if (arr[rootA].rank < arr[rootB].rank) {
			arr[rootA].parent = arr[rootB].parent;
		} else if (arr[rootA].rank > arr[rootB].rank) {
			arr[rootB].parent = arr[rootA].parent;
		} else {
			arr[rootA].parent = arr[rootB].parent;
			arr[rootB].rank++;
		}

		return true; // union successful
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Disjoint sets as forest:\n");
		for (int i = 0; i < arr.length; i++) {
			sb.append(String.format("%d -> %d%n", i, arr[i].parent));
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

}
