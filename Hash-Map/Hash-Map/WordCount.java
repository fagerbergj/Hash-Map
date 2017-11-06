package edu.wit.comp2000.jasonfagerberg.adt5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordCount
	{
		public String[] read(String path)
			{
				String[] words = new String[0];
				String inputString = "";
				try
					{
						File scannedFile = new File(path);
						if (scannedFile.canRead())
							{
								System.out.println("file read");
							}
						else
							System.out.println("no file found");
						Scanner input = new Scanner(scannedFile);
						while (input.hasNextLine())
							{
								String line = input.nextLine();
								inputString += line.toLowerCase() + " ";
							}
					}
				catch (FileNotFoundException e)
					{

						e.printStackTrace();
					}
				words = inputString.split("\\s+");
				for(int i = 0; i < words.length; i++)
					{
						if(words[i].charAt(words[i].length() -1) == ',' || words[i].charAt(words[i].length() -1) == '.')
							{
								words[i] = words[i].substring(0,words[i].length()-1);
							}
					}
				return words;
			}

		public HashMap map(String[] words)
			{
				HashMap map = new HashMap();
				for (int i = 0; i < words.length; i++)
					{
						//If it already exists in map
						if(map.contains(words[i]))
							{
								map.add(words[i], map.getValue(words[i]) + 1);
							}
						else
							{
								map.add(words[i], 1);
							}
					}
				return map;
			}

		public static void main(String[] args)
			{
				WordCount wc = new WordCount();
				
				String[] wit = wc.read("/Users/fagerbergj1/Dropbox/workspace/Lab09/src/edu/wit/comp2000/jasonfagerberg/adt5/wit-attendance-policy.txt");
				HashMap res = wc.map(wit);
				System.out.println("The Word Counts for 'wit-attendance-policy': \n" + res + "\n");
				res.displayMetrics();
				res.clear();
				
				String[] cotton = wc.read("/Users/fagerbergj1/Dropbox/workspace/Lab09/src/edu/wit/comp2000/jasonfagerberg/adt5/the-lancashire-cotton-famine.txt");
				res = wc.map(cotton);
				System.out.println("The Word Counts for 'the-lancashire-cotton-famine': \n" + res + "\n");
				res.displayMetrics();
				res.clear();
				
				String[] english = wc.read("/Users/fagerbergj1/Dropbox/workspace/Lab09/src/edu/wit/comp2000/jasonfagerberg/adt5/american-english-JL.txt");
				res = wc.map(english);
				System.out.println("The Word Counts for 'american-english-JL': \n" + res +"\n");
				res.displayMetrics();
				res.clear();

			}
	}
