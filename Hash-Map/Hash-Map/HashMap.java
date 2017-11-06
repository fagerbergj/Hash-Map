package edu.wit.comp2000.jasonfagerberg.adt5;

import java.util.ArrayList;
import java.util.Scanner;

// hash table
class HashMap
	{
		// ArrayList that has head of each chain
		private ArrayList<Node> buckets;

		// Number of buckets in list
		private int numBuckets;

		// amount of elements in table
		private int size;

		// Constructor (Initializes capacity, size and
		// empty chains.
		public HashMap()
			{
				buckets = new ArrayList<>();
				// READING INPUT FOR NUMBER OF BUCKETS
				Scanner reader = new Scanner(System.in); // Reading from
															// System.in
				System.out.println("Enter table size: ");
				int n = reader.nextInt(); // Scans the next token of the input
											// as an int.
				numBuckets = nextPrime(n);
				size = 0;

				// Create empty chains for now
				for (int i = 0; i < numBuckets; i++)
					{
						buckets.add(null);
					}
			}

		// return number of elements
		public int size()
			{
				return size;
			}

		// return if there are no elements
		public boolean isEmpty()
			{
				return size() == 0;
			}

		// This implements hash function to find index
		// for a key
		private int getHashIndex(String key)
			{
				int hashCode = 0;
				char[] characters = key.toCharArray();
				for (int i = 0; i < characters.length; i++)
					{
						hashCode += Math.abs(characters[i] + (i * 100));
					}
				int index = hashCode % numBuckets;
				return index;
			}

		// Returns value for a key
		public int getValue(String key)
			{
				// Find head of chain for given key
				int bucketIndex = getHashIndex(key);
				Node head = buckets.get(bucketIndex);

				// Search key in chain
				while (head != null)
					{
						if (head.key.equals(key))
							return head.value;
						head = head.next;
					}

				// If key not found
				return -1;
			}

		// Adds a key value pair to hash
		public void add(String key, int value)
			{
				// Find head of chain for given key
				int bucketIndex = getHashIndex(key);
				Node head = buckets.get(bucketIndex);

				// Check if key is already present
				while (head != null)
					{
						if (head.key.equals(key))
							{
								head.value = value;
								return;
							}
						head = head.next;
					}

				// Insert key in chain
				size++;
				head = buckets.get(bucketIndex);
				Node newNode = new Node(key, value);
				newNode.next = head;
				buckets.set(bucketIndex, newNode);

				// If load factor goes beyond threshold, then
				// double hash table size
				if ((1.0 * size) / numBuckets >= .9)
					{
						ArrayList<Node> temp = buckets;
						buckets = new ArrayList<>();
						numBuckets = nextPrime(2 * numBuckets);
						size = 0;
						for (int i = 0; i < numBuckets; i++)
							buckets.add(null);

						for (Node headNode : temp)
							{
								while (headNode != null)
									{
										add(headNode.key, headNode.value);
										headNode = headNode.next;
									}
							}
					}
			}

		public Node[] toArray()
			{
				Node[] res = new Node[size];
				int resIndex = 0;
				for (int i = 0; i < numBuckets; i++)
					{
						// starting node
						Node currentNode = buckets.get(i);
						// check collisions and add them
						while (currentNode != null)
							{
								res[resIndex] = currentNode;
								resIndex++;
								currentNode = currentNode.next;
							}
					}
				return res;
			}

		@Override
		public String toString()
			{
				String res = "";
				Node[] list = toArray();
				ArrayList<String> counts = new ArrayList<String>();
				for(int i = 0 ; i < 101; i++)
					{
						counts.add("");
					}
				for (int i = 0; i < list.length; i++)
					{
						if(counts.get(list[i].value).equals(""))
							{
								counts.set(list[i].value, list[i].key);
							}
						else
							{
						String toAdd = counts.get(list[i].value) + ", ";
						counts.set(list[i].value, toAdd + list[i].key);
							}

					}
				for (int i = 0; i < counts.size(); i++)
					{
						if (counts.get(i) != "")
							res += i + " : " + counts.get(i) + "\n";
					}
				return res;
			}

		public boolean contains(String key)
			{
				Node currentNode = buckets.get(getHashIndex(key));
				while (currentNode != null)
					{
						if (currentNode.key.equals(key))
							{
								return true;
							}
						currentNode = currentNode.next;
					}
				return false;
			}

		// Node that will chain with nodes with the same value
		private class Node
			{
				String key;
				int value;
				Node next;

				// Constructor
				public Node(String key, int value)
					{
						this.key = key;
						this.value = value;
					}

				public String toString()
					{
						return key;
					}
			}

		public void clear()
			{
				size = 0;
				numBuckets = 0;
				buckets.clear();
			}

		private int nextPrime(int n)
			{
				while (true)
					{
						if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0)
							{
								n++;
							}
						else
							{
								return n;
							}
					}
			}
		public void displayMetrics()
		{
			System.out.println("Current Table Size: " + numBuckets + "\n" +
								"Number Of Entries: " + size + "\n" + 
								"Load Factor: " + (1.0 * size) / numBuckets + "\n");
		}

		// unit test
		public static void main(String[] args)
			{

			}
	}
