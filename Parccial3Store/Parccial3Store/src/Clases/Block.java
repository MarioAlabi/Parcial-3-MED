/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ademi
 */
public class Block {
    private String hash;
    private String previousHash;
    private long timeStamp;
    private int nonce;
    private ArrayList<Transaction> transactions;
    private int id;
    public String getId;

    public Block(String previousHash, int id) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.transactions = new ArrayList<>();
        this.hash = calculateHash();
        this.id = id;
    }

    public String calculateHash() {
        String calculatedhash = applySha256(
            previousHash +
            Long.toString(timeStamp) +
            Integer.toString(nonce) +
            transactions.toString()
        );
        return calculatedhash;
    }

    private String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Block [hash=" + hash + ", previousHash=" + previousHash + ", transactions=" + transactions + "]";
    }
}
