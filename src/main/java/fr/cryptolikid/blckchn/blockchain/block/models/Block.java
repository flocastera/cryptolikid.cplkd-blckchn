package fr.cryptolikid.blckchn.blockchain.block.models;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BadBlockException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;
import lombok.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
 * Block
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Block implements Serializable, IValidableModel {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** index */
    private Integer index;

    /** timestamp */
    private Date timestamp = new Date();

    /** transactions */
    private Vector<FinalTransaction<? extends TransactionData>> transactions = new Vector<>();

    /** proof */
    private Proof proof;

    /** previousHash */
    private String previousHash;

    /**
     * @throws BadBlockException if model is not valid
     */
    @Override
    public void validate() throws BadBlockException {
        if(StringUtils.isEmpty(previousHash))
            throw new BadBlockException("No ancestor block !");
        if(CollectionUtils.isEmpty(transactions) && index > 0)
            throw new BadBlockException("No transactions ! Block is useless !");
    }
}
