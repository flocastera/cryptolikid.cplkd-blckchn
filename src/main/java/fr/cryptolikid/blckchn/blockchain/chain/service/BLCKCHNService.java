package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BadBlockException;
import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.block.service.IBlockGeneratorService;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainAccessException;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.common.managers.IShutdownManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
class BLCKCHNService implements IBLCKCHNService {

	/** LOG */
	private Logger LOG = LogManager.getLogger(BLCKCHNService.class);

	/** chain */
	private Vector<Block> activeChain;

	/** currentTransactions */
	private Vector<FinalTransaction<? extends TransactionData>> pendingTransactions;

	/** blockGeneratorService */
	private final IBlockGeneratorService blockGeneratorService;

	/** shutdownManager */
	private final IShutdownManager shutdownManager;

	@Autowired
	public BLCKCHNService(IBlockGeneratorService blockGeneratorService, IShutdownManager shutdownManager) {
		this.blockGeneratorService = blockGeneratorService;
		this.shutdownManager = shutdownManager;

		LOG.info("Blockchain initialisation...");

		try {
			this.initBlockchain();
			this.createGenesisBlock();
		} catch (Exception e) {
			LOG.fatal(String.format("Something went wrong and application cannot start ! %s", e.getMessage()));
			e.printStackTrace();
			this.shutdownManager.initiateShutdown(1);
		}
	}

	/**
	 *
	 */
	private void initBlockchain() {
		this.activeChain = new Vector<>();
		this.pendingTransactions = new Vector<>();
		LOG.info("Blockchain initialized !");
	}

	/**
	 *
	 */
	private void createGenesisBlock() {
		this.addBlock(this.blockGeneratorService.createGenesisBlock());
		LOG.info("Genesis block added !");
	}

	/**
	 * @return Blockchain length
	 */
	@Override
	public int getCurrentIndex() {
		return this.activeChain.size();
	}

	/**
	 * @return Transactions that haven't been included in blocks
	 */
	@Override
	public Vector<FinalTransaction<? extends TransactionData>> getPendingTransactions() {
		return new Vector<>(this.pendingTransactions);
	}

	/**
	 * @param transaction @NotNull
	 * @throws TransactionException if transaction not added
	 */
	@Override
	public void addPendingTransaction(FinalTransaction<? extends TransactionData> transaction) throws TransactionException {
		if (transaction == null)
			throw new BadTransactionException("Transaction is not valid !");
		try {
			this.pendingTransactions.add(transaction);
			LOG.info("Transaction added !");
		} catch (Exception e) {
			throw new TransactionException("Something went wrong while adding transaction !");
		}
	}

	/**
	 * @param block @NotNull Block will be added to blockchain
	 * @throws BlockchainException if blockchain not updated
	 * @throws BlockException      if block is invalid
	 */
	@Override
	public void addBlock(Block block) {
		if (block == null)
			throw new BadBlockException("Block is not valid");
		try {
			this.activeChain.add(block);
			LOG.info(String.format("Block added ! Index : %s, transactions : %s", block.getIndex(), block.getTransactions().size()));
		} catch (Exception e) {
			throw new BlockchainAccessException(String.format("Something went wrong while adding block at index %s !", block.getIndex()));
		}
	}

	/**
	 * Delete all pending transactions. Used when adding new valid block to blockchain
	 */
	@Override
	public void clearPendingTransactions() {
		this.pendingTransactions.clear();
		LOG.debug("Pending transactions cleared !");
	}

	/**
	 * @return the last element added to active blockchain
	 */
	@Override
	public Block getLastBlockAddedInBlockchain() {
		return this.activeChain.lastElement().toBuilder().build();
	}

	/**
	 * @return the full blockchain
	 */
	@Override
	public Vector<Block> getFullBlockChain() {
		return new Vector<>(this.activeChain);
	}
}
