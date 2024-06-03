/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ademi
 */
public class BlockChain {
     private List<Block> blockChain;
    private int complexity;
    private String proofOfWork;

    public BlockChain(int complexity, String proofChar) {
        this.blockChain = new ArrayList<>();
        this.complexity = complexity;
        this.proofOfWork = "";
        for (int i = 0; i < this.complexity; i++) {
            this.proofOfWork += proofChar;
        }
        createGenesis();
    }

    private void createGenesis() {
        Block genesisBlock = new Block("0", 0);
        blockChain.add(genesisBlock);
        mineBlock(genesisBlock);
    }

    public void addBlock(Block newBlock) {
        if (blockChain.size() > 0) {
            newBlock.setPreviousHash(blockChain.get(blockChain.size() - 1).getHash());
        }
        mineBlock(newBlock);
        blockChain.add(newBlock);
    }

    public Block getBlock(int index) {
        if (index < 0 || index >= blockChain.size()) {
            return null;
        }
        return blockChain.get(index);
    }

    public int size() {
        return blockChain.size();
    }

    public Block createBlock() {
        Block newBlock = new Block(getLastBlock().getHash(), blockChain.size());
        addBlock(newBlock);
        return newBlock;
    }

    public void createTransaction(String sender, String component, String brand, int year, int quantity, String recipient, double amount) {
        Block lastBlock = getLastBlock();
        if (lastBlock != null) {
            int transactionId = lastBlock.getTransactions().size();
            Transaction newTransaction = new Transaction(transactionId, sender, component, brand, year, quantity, recipient, amount);
            lastBlock.addTransaction(newTransaction);
            mineBlock(lastBlock);
        } else {
            System.out.println("Error: No last block found.");
        }
    }

    public Block getLastBlock() {
        if (blockChain.size() == 0) {
            return null;
        }
        return blockChain.get(blockChain.size() - 1);
    }

    public String transactionReport(int blockId) {
        Block block = getBlock(blockId);
        if (block == null) {
            return "Block not found";
        }
        StringBuilder report = new StringBuilder();
        for (Transaction tx : block.getTransactions()) {
            report.append(tx.toString()).append("\n");
        }
        return report.toString();
    }

    public void mineBlock(Block block) {
        String target = new String(new char[complexity]).replace('\0', '0');
        while (!block.getHash().substring(0, complexity).equals(target)) {
            block.mineBlock(complexity);
        }
        System.out.println("Block mined: " + block.getHash());
    }

    private String generateHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
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
}
