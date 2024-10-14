package com.blockhain;
import java.util.ArrayList;
import java.util.Scanner;
//import com.google.gson.GsonBuilder;
//import java.util.Date;
public class Main {
    public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
    public static int difficulty = 3;

	public static void main(String[] args) {	
//		blockchain.add(new Block("Hi im the first block", "0"));
//		System.out.println("Trying to Mine block 1... ");
//		blockchain.get(0).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine block 2... ");
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine block 3... ");
//		blockchain.get(2).mineBlock(difficulty);	
//		
//		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
//		Block init = new Block("First Block","0");
		
		int count = 1;
		String transaction;
		Scanner scan = new Scanner(System.in);
		while(count <=  3) {
			System.out.println("Enter your transaction: ");
			transaction =  scan.nextLine();
			Database get_db = new Database();
			ArrayList<String> hashes = get_db.getinsert();
			Block init = new Block(transaction,hashes.get(0));
			String prevhash = hashes.get(1);
			init.mineBlock(difficulty);
			String hash = init.gethash();
			long timestamp = init.gettime();
			
			Database db = new Database(prevhash,hash,timestamp,transaction);
			db.insert();
			count += 1;
		}
		scan.close();
		
		
//        Jsonbuilder test = new Jsonbuilder(blockchain);
//        test.write("mined.json");
	}
    public static Boolean isChainValid() {
        Block currentBlock; 
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
        return true;
    }
}
