package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;


	}

	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
		for (int i = 0; i < size; i++) {
			arr[i] = new Element();
			arr[i].next = NULL;
			arr[i].representant = i;
			arr[i].last = i;
			arr[i].length = 1;
		}
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].representant = item;
		arr[item].next = NULL;
		arr[item].length = 1;
		arr[item].last = item;
	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int setA = findSet(itemA);
		int setB = findSet(itemB);

		if (setA == setB) {
			return false; // the elements are already in the same set
	}
		// append the shorter list to the end of the longer list
		if (arr[setA].length < arr[setB].length) {
			int tmp = setA;
			setA = setB;
			setB = tmp;
		}


		arr[arr[setA].last].next = setB;
		arr[setA].last = arr[setB].last;
		arr[setA].length += arr[setB].length;


		for (int i = setB; i != NULL; i = arr[i].next)
		{
			arr[i].representant = setA;
		}
		return true;

	}
//@Override
//public boolean union(int itemA, int itemB) {
//	int setA = findSet(itemA);
//	int setB = findSet(itemB);
//
//	if (setA == setB) {
//		return false; // already in the same set
//	}
//
//	if (arr[setA].length >= arr[itemB].length)
//	{
//		arr[arr[setA].last].next = setB;
//		arr[setA].last = arr[setB].last;
//		arr[setA].length += arr[setB].length;
//
//		for (int i = setB; i != NULL ; i=arr[i].next)
//		{
//			arr[i].representant = setA;
//		}
//	}
//	else
//	{
//		arr[arr[setB].last].next = setA;
//		arr[setB].last = arr[setA].last;
//		arr[setB].length += arr[setA].length;
//
//		for (int i = setA; i != NULL ; i=arr[i].next)
//		{
//			arr[i].representant = setB;
//		}
//	}
//	return true;
//}



	//	public void Link(int setA, int setB)
//	{
//
//	}
//
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Disjoint sets as linked list:\n");

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].representant == i) {
				sb.append(i);
				int next = arr[i].next;
				while (next != NULL) {
					sb.append(", ").append(next);
					next = arr[next].next;
				}

				if (i != arr.length - 2)
					sb.append("\n");
			}
		}

		return sb.toString();

	}


}
