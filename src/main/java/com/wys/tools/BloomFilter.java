package com.wys.tools;

import java.util.BitSet;

public class BloomFilter {
	private static final int BIT_COUNT=2<<24;
	private BitSet bset;
	private int seeds[]={3,7,11,13,31,37,61};
	public BloomFilter(int nBits){
		bset=new BitSet(nBits);
	}
	public BloomFilter(){
		this(BIT_COUNT);
	}
	public int getHashValue(String str,int index){
		if(index>=seeds.length)
			return -1;
		int strlen=str.length();
		int res=0;
		for(int i=0;i<strlen;i++){
			res+=str.charAt(i)*seeds[index];
			if(res>=BIT_COUNT)
				res%=BIT_COUNT;
		}
		return res;
	}
	public void addToBloom(String str){
		int len=seeds.length;
		for(int i=0;i<len;i++){
			int hashValue=getHashValue(str,i);
			bset.set(hashValue);
		}
	}
	public boolean isInBloom(String str){
		int len=seeds.length;
		for(int i=0;i<len;i++){
			int hashValue=getHashValue(str,i);
			if(!bset.get(hashValue)){
				return false;
			}
		}
		return true;
	}
}
