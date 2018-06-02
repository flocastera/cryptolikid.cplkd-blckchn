package lbp.toolsfordev.blckchn.blockchain.block.models;

import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.common.interfaces.IValidableModel;
import lbp.toolsfordev.blckchn.node.exceptions.MinerMissingInformationException;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
import lombok.*;
import org.springframework.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
public class Block implements Serializable, IValidableModel {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** index */
    private Integer index;

    /** timestamp */
    private Date timestamp;

    /** transactions */
    private Vector<FinalTransaction> transactions;

    /** proof */
    private Proof proof;

    /** previousHash */
    private String previousHash;

    /**
     * @throws Exception if model is not valid
     */
    @Override
    public void validate() throws Exception, MinerMissingInformationException {
        if(StringUtils.isEmpty(previousHash) || proo || StringUtils.isEmpty())
    }
}
