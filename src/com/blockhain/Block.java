package com.blockhain;
import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	protected long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append("{" + "\n");
		sb.append("\t"+"\t"+"\""+ "data" + "\"" + " : " +"\""+ this.data + "\"" + "," + "\n");
		sb.append("\t"+"\t"+"\""+ "previousHash" + "\"" + " : " +"\""+ this.previousHash + "\""+ ","+ "\n");
		sb.append("\t"+"\t"+"\""+ "timeStamp" + "\"" + " : " + this.timeStamp + ","+ "\n");
		sb.append("\t"+"\t"+"\""+ "hash" + "\"" + " : " +"\""+ this.hash + "\""+ "\n");
		sb.append("\t" + "}");
		return sb.toString();
	}
    public String getdata() {
    	return this.data;
    }
    public String getprevhash() {
    	return this.previousHash;
    }
    public long gettime() {
    	return this.timeStamp;
    }
    public String gethash() {
    	return this.hash;
    }
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
        return calculatedhash;
    }
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		System.out.println(target);
		while(!hash.substring( 0, difficulty).equals(target)) {
			//System.out.println(hash.substring( 0, difficulty));;
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}